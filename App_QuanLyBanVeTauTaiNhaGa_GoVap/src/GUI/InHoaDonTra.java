package GUI;

import DAO.DAO_KhachHang;
import DAO.DAO_TraVe;
import Database.ConnectDatabase;
import Entity.*;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.event.PdfDocumentEvent;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.apache.xmlbeans.impl.common.ValidatorListener;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class InHoaDonTra {
    private static String hotenKH = null;
    private static String soDienThoai = null;
    private static String diaChi = null;
    private static String loaiKH = null;
    // Phương thức tạo hóa đơn PDF
    public static void generateInvoicePdf(HoaDon hoaDon, KhachHang khachHang,
                                          List<ChiTietHoaDon> chiTietHoaDonList, List<VeTau> danhSachVe,String maNV,double[] tienvegoc,String[] tau) {
        try {

            if(khachHang.getLoaiKhachHang().getMaLoaiKhachHang().equals("KH002")){
                DAO_KhachHang dao_khachHang = new DAO_KhachHang();
                hotenKH = dao_khachHang.decryptAES(khachHang.getTenKhachHang());
                soDienThoai = dao_khachHang.decryptAES(khachHang.getSoDienThoai());
                diaChi = dao_khachHang.decryptAES(khachHang.getDiaChi());

            }else{
                hotenKH = khachHang.getTenKhachHang();
                soDienThoai = khachHang.getSoDienThoai();
                diaChi = khachHang.getDiaChi();
            }


            // Tạo mã hóa đơn và tên file PDF
            String invoiceNumber = hoaDon.getMaHD(); // Lấy mã hóa đơn từ hoaDon
            String filePath = "hoadonTra.pdf";
            PdfWriter writer = new PdfWriter(filePath);

            // Tạo đối tượng PdfDocument
            PdfDocument pdf = new PdfDocument(writer);

            // Tạo đối tượng Document để thêm các phần tử vào PDF
            Document document = new Document(pdf);
            document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------").setHorizontalAlignment(HorizontalAlignment.CENTER));
            // Tiêu đề vé
            Paragraph tenCongTy = new Paragraph("CONG TY CO PHAN VAN TAI DUONG SAT LAC HONG")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFont(PdfFontFactory.createFont())
                    .setFontSize(14);
            document.add(tenCongTy);

            // Tiêu đề Hóa Đơn (In đậm)
            Paragraph title = new Paragraph("HOA DON TRA VE TAU")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(18);
            document.add(title);

            LocalDateTime ngayLap = hoaDon.getNgayLap();  // Đã là LocalDateTime

            // Định dạng ngày giờ Việt Nam (dd/MM/yyyy HH:mm:ss)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
                    .withZone(ZoneId.of("Asia/Ho_Chi_Minh"));

            // Định dạng ngày giờ
            String formattedDate = formatter.format(ngayLap);


// Sử dụng chuỗi định dạng ngày trong Paragraph
            Paragraph ngayLapHD = new Paragraph(formattedDate)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(10);
            document.add(ngayLapHD);
            document.add(new Paragraph("Ma Nhan Vien: " + maNV));
            document.add(new Paragraph("Ma hoa don: " + hoaDon.getMaHD()));
            // Thông tin khách hàng
            document.add(new Paragraph("Khach hang: " + removeDiacritics(hotenKH)));
            document.add(new Paragraph("So dien thoai: " + removeDiacritics(soDienThoai)));
            document.add(new Paragraph("Email: " + removeDiacritics(diaChi)));
            document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------")
                    .setHorizontalAlignment(HorizontalAlignment.CENTER));

            // Tạo bảng chi tiết vé
            // Đặt chiều rộng cho các cột
            float[] pointColumnWidths = {40F, 230F, 80F, 60F, 60F, 40F, 80F};
            Table table = new Table(pointColumnWidths); // 7 cột: STT, Tên hàng hóa, đơn vị tính, số lượng, đơn giá, thuế, thành tiền
            table.addCell(new Cell().add(new Paragraph("STT")));
            table.addCell(new Cell().add(new Paragraph("Ten hang hoa, dich vu")));
            table.addCell(new Cell().add(new Paragraph("Don vi tinh")));
            table.addCell(new Cell().add(new Paragraph("So luong")));
            table.addCell(new Cell().add(new Paragraph("Tien goc")));
            table.addCell(new Cell().add(new Paragraph("Tien Phi")));
            table.addCell(new Cell().add(new Paragraph("Thanh tien")));

            double totalAmount = 0.0; // Biến tính tổng tiền
            int stt = 1;
            DAO_TraVe dao_traVe = new DAO_TraVe();
            // Lặp qua danh sách chi tiết hóa đơn và thêm vào bảng
            for (ChiTietHoaDon chiTiet : chiTietHoaDonList) {
                VeTau ticket = danhSachVe.get(stt - 1); // Lấy vé tương ứng

                // Thêm dòng thông tin vào bảng
                table.addCell(String.valueOf(stt)); // STT
                table.addCell("Chỗ: "+ticket.getChoNgoi().getTenCho()+" Tau"+tau[stt-1]+ "\n"+"Tuyen " + removeDiacritics(dao_traVe.getTenTuyenByMaVe(ticket.getMaVe()))  + "\n"+"Ngay di :" +ticket.getNgayDi() + ", "  + removeDiacritics(ticket.getDoiTuong())); // Tên vé
                table.addCell("Vé"); // Đơn vị tính
                table.addCell(String.format("%d", chiTiet.getSoLuong())); // Số lượng
                table.addCell(String.format("%,.0f", tienvegoc[stt-1])); // Đơn giá
                table.addCell(String.format("%,.0f", tienvegoc[stt-1]-chiTiet.getThanhTien())); // VAT
                table.addCell(String.format("%,.0f", 0-chiTiet.getThanhTien())); // Thành tiền

                totalAmount += chiTiet.getThanhTien(); // Cộng dồn thành tiền
                stt++; // Tăng STT

            }


            // Thêm bảng vào tài liệu PDF
            document.add(table);

            Paragraph TongTien = new Paragraph("Tong Tien Ve: " + String.format("%,.0f",0- totalAmount) + " VND")
                    .setTextAlignment(TextAlignment.RIGHT);
            document.add(TongTien);

            // Tiền khuyến mãi
            Paragraph khuyenMai = new Paragraph("Tong Tien phi: " + String.format("%,.0f",totalAmount-hoaDon.getTongTien() ) + " VND")
                    .setTextAlignment(TextAlignment.RIGHT);
            document.add(khuyenMai);

            // Tổng tiền sau giảm giá
            Paragraph total = new Paragraph("Tong tien (sau khi tru phi): " + String.format("%,.0f", 0-hoaDon.getTongTien()) + " VND")
                    .setTextAlignment(TextAlignment.RIGHT);
            document.add(total);

            // Đóng tài liệu PDF
            document.close();

            System.out.println("Hóa đơn đã được tạo thành công: " + filePath);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Phương thức chuyển chuỗi có dấu thành chuỗi không dấu
    public static String removeDiacritics(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }


}
