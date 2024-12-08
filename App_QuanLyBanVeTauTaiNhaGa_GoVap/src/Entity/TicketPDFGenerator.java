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
import java.text.Normalizer;
import java.util.List;

public class TicketPDFGenerator {

    public static void generateTicketPdf(String fileName, List<TicketDetails> ticketDetailsList) {
        try {
            // Tạo đối tượng PdfWriter
            PdfWriter writer = new PdfWriter(fileName);

            // Tạo đối tượng PdfDocument
            PdfDocument pdf = new PdfDocument(writer);

            // Tạo đối tượng Document để thêm các phần tử vào PDF
            Document document = new Document(pdf);

            // Tiêu đề Hóa Đơn
            Paragraph title = new Paragraph("THÔNG TIN VÉ TÀU")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFont(PdfFontFactory.createFont())
                    .setFontSize(18);
            document.add(title);

            for (TicketDetails ticket : ticketDetailsList) {
                // Tiêu đề vé
                document.add(new Paragraph("\nTHONG TIN HANG KHACH").setFontSize(14));

                // Thông tin khách hàng
                document.add(new Paragraph("Ho ten: " + ticket.getHoTen()));
                document.add(new Paragraph("Doi tuong: " + removeDiacritics(ticket.getDoiTuong())));
                document.add(new Paragraph("Giấy tờ: " + ticket.getGiayTo()));

                // Thông tin hành trình
                document.add(new Paragraph("\nTHÔNG TIN HÀNH TRÌNH").setFontSize(14));
                document.add(new Paragraph("Ga đi-Ga đến: " + ticket.getGaDi() + " - " + ticket.getGaDen()));
                document.add(new Paragraph("Tàu/Train: " + ticket.getTenTau()));
                document.add(new Paragraph("Ngày đi/Date: " + ticket.getNgayDi()));
                document.add(new Paragraph("Giờ đi/Time: " + ticket.getGioDi()));
                document.add(new Paragraph("Toa/Coach: " + ticket.getToaTau()));
                document.add(new Paragraph("Chỗ/Seat: " + ticket.getChoNgoi()));

                // Thông tin giá vé
                document.add(new Paragraph("\nTHÔNG TIN GIÁ VÉ").setFontSize(14));
                document.add(new Paragraph("Giá vé: " + ticket.getGiaVe() + " VND      |     VAT: 10%"));
                document.add(new Paragraph("Thành tiền: " + ticket.getThanhTien() + " VND"));
            }

            // Đóng tài liệu PDF
            document.close();

            System.out.println("Thông tin vé đã được tạo thành công: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Phương thức chuyển chuỗi có dấu thành chuỗi không dấu
    public static String removeDiacritics(String input) {
        // Kiểm tra nếu chuỗi đầu vào là null hoặc rỗng thì trả về chuỗi gốc
        if (input == null || input.isEmpty()) {
            return input;
        }

        // Sử dụng Normalizer để loại bỏ dấu tiếng Việt
        return Normalizer.normalize(input, Normalizer.Form.NFD) // Tách các ký tự có dấu ra khỏi ký tự chính
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", ""); // Loại bỏ các dấu phụ
    }
}