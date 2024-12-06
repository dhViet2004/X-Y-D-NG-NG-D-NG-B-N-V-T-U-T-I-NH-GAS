package Entity;

import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.IOException;
import java.util.List;

public class InvoicePDFGenerator {

    public static void generateInvoicePdf(String invoiceNumber, KhachHang khachHang, List<ChiTietHoaDon> chiTietHoaDonList, List<VeTau> ticketsToSave, double totalAmount) {
        try {
            // Tạo đối tượng PdfWriter
            String filePath = "hoa_don_" + invoiceNumber + ".pdf";
            PdfWriter writer = new PdfWriter(filePath);

            // Tạo đối tượng PdfDocument
            PdfDocument pdf = new PdfDocument(writer);

            // Tạo đối tượng Document để thêm các phần tử vào PDF
            Document document = new Document(pdf);

            // Tiêu đề Hóa Đơn
            Paragraph title = new Paragraph("HÓA ĐƠN MUA VÉ TÀU")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFont(PdfFontFactory.createFont())
                    .setFontSize(18);
            document.add(title);

            // Thông tin khách hàng
            document.add(new Paragraph("Khách hàng: " + khachHang.getTenKhachHang()));
            document.add(new Paragraph("Số điện thoại: " + khachHang.getSoDienThoai()));
            document.add(new Paragraph("Địa chỉ: " + khachHang.getDiaChi()));

            // Tạo bảng chi tiết vé
            Table table = new Table(6); // 6 cột: Mã vé, Tên vé, Giá, Số lượng, Thành tiền, Thuế
            table.addCell(new Cell().add(new Paragraph("Mã Vé")));
            table.addCell(new Cell().add(new Paragraph("Tên Vé")));
            table.addCell(new Cell().add(new Paragraph("Giá")));
            table.addCell(new Cell().add(new Paragraph("Số Lượng")));
            table.addCell(new Cell().add(new Paragraph("Thành Tiền")));
            table.addCell(new Cell().add(new Paragraph("Thuế VAT")));

            // Lặp qua danh sách chi tiết hóa đơn và thêm vào bảng
            for (ChiTietHoaDon chiTiet : chiTietHoaDonList) {
                // Tìm Vé trong danh sách ticketsToSave theo mã vé
                VeTau veTau = getVeTauByMaVe(ticketsToSave, chiTiet.getMaVe());

                table.addCell(chiTiet.getMaVe());  // Mã vé
                table.addCell(veTau != null ? veTau.getTenKhachHang() : "Không xác định"); // Tên vé
                table.addCell(String.valueOf(veTau != null ? veTau.getGiaVe() : 0)); // Giá vé
                table.addCell(String.valueOf(chiTiet.getSoLuong())); // Số lượng
                table.addCell(String.valueOf(chiTiet.getThanhTien())); // Thành tiền
                table.addCell(String.valueOf(chiTiet.getTienThue())); // Thuế VAT
            }

            // Thêm bảng vào tài liệu PDF
            document.add(table);

            // Thêm tổng tiền
            Paragraph total = new Paragraph("Tổng tiền: " + totalAmount + " VND")
                    .setTextAlignment(TextAlignment.RIGHT);
            document.add(total);

            // Đóng tài liệu PDF
            document.close();

            System.out.println("Hóa đơn đã được tạo thành công: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Phương thức tìm Vé trong danh sách ticketsToSave theo mã vé
    private static VeTau getVeTauByMaVe(List<VeTau> ticketsToSave, String maVe) {
        for (VeTau veTau : ticketsToSave) {
            if (veTau.getMaVe().equals(maVe)) {
                return veTau;
            }
        }
        return null; // Trả về null nếu không tìm thấy mã vé
    }
}
