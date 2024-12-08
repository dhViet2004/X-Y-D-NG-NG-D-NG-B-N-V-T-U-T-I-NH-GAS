package Entity;

import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.io.IOException;
import java.text.Normalizer;
import java.util.List;

public class InvoicePDFGenerator {

    // Phương thức tạo hóa đơn PDF
    public static void generateInvoicePdf(HoaDon hoaDon, KhachHang khachHang,
                                          List<ChiTietHoaDon> chiTietHoaDonList, List<TicketDetails> danhSachVe) {
        try {
            // Tạo mã hóa đơn và tên file PDF
            String invoiceNumber = hoaDon.getMaHD(); // Lấy mã hóa đơn từ hoaDon
            String filePath = "hoa_don_" + invoiceNumber + ".pdf";
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
            Paragraph title = new Paragraph("HOA DON MUA VE TAU")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(18);
            document.add(title);

            // Ngày lập hóa đơn
            Paragraph ngayLapHD = new Paragraph(String.valueOf(hoaDon.getNgayLap()))
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(10);
            document.add(ngayLapHD);

            // Thông tin khách hàng
            document.add(new Paragraph("Khach hang: " + removeDiacritics(khachHang.getTenKhachHang())));
            document.add(new Paragraph("So dien thoai: " + removeDiacritics(khachHang.getSoDienThoai())));
            document.add(new Paragraph("Dia Chi: " + removeDiacritics(khachHang.getDiaChi())));
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
            table.addCell(new Cell().add(new Paragraph("Don gia")));
            table.addCell(new Cell().add(new Paragraph("VAT")));
            table.addCell(new Cell().add(new Paragraph("Thanh tien")));





            double totalAmount = 0.0; // Biến tính tổng tiền
            int stt = 1;

            // Lặp qua danh sách chi tiết hóa đơn và thêm vào bảng
            for (ChiTietHoaDon chiTiet : chiTietHoaDonList) {
                TicketDetails ticket = danhSachVe.get(stt - 1);
                // Thêm dòng thông tin vào bảng
                table.addCell(String.valueOf(stt)); // STT
                table.addCell("Ve tau, tuyen " + removeDiacritics(ticket.getGaDi()) + "-" + removeDiacritics(ticket.getGaDen()) + ", " + ticket.getNgayDi() + ", " + ticket.getGioDi() + ", " + removeDiacritics(ticket.getDoiTuong())); // Tên tàu (hoặc dịch vụ)
                table.addCell("Vé"); // Đơn vị tính
                table.addCell(String.format("%d", chiTiet.getSoLuong())); // Số lượng
                table.addCell(String.format("%,.0f", ticket.getGiaVe())); // Đơn giá
                table.addCell(String.format("%,.0f", chiTiet.getVAT())); // VAT
                table.addCell(String.format("%,.0f", chiTiet.getThanhTien())); // Thành tiền
                stt++; // Tăng STT
            }

            // Thêm bảng vào tài liệu PDF
            document.add(table);

            // Tiền khuyến mãi
            Paragraph khuyenMai = new Paragraph("Tien khuyen mai: " + String.format("%,.0f", hoaDon.getTienGiam()) + " VND")
                    .setTextAlignment(TextAlignment.RIGHT);
            document.add(khuyenMai);

            // Tổng tiền sau giảm giá
            Paragraph total = new Paragraph("Tong tien (sau giam gia): " + String.format("%,.0f", hoaDon.getTongTien()) + " VND")
                    .setTextAlignment(TextAlignment.RIGHT);
            document.add(total);

            // Đóng tài liệu PDF
            document.close();

            System.out.println("Hóa đơn đã được tạo thành công: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
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
