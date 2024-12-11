package Entity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TicketHTMLGenerator {

    public static void generateTicketHtml(String fileName, List<TicketDetails> ticketDetailsList, List<ChiTietHoaDon> chiTietHoaDonList) {
        try (FileWriter writer = new FileWriter(fileName)) {
            // Đưa thông tin HTML cơ bản vào file
            writer.write("<html><head><style>");
            writer.write("body {font-family: Arial, sans-serif; padding: 20px;}");
            writer.write(".title {text-align: center; font-size: 24px; font-weight: bold;}");
            writer.write(".sub-title {text-align: center; font-size: 18px; font-weight: bold;}");
            writer.write(".section-title {font-size: 16px; font-weight: bold; margin-top: 10px;}");
            writer.write(".content {font-size: 14px; margin-left: 20px;}");
            writer.write(".qr-code {display: block; margin: 20px auto;}");
            writer.write("</style></head><body>");

            for (int i = 0; i < ticketDetailsList.size(); i++) {
                TicketDetails ticket = ticketDetailsList.get(i);

                // Tiêu đề vé
                writer.write("<div class=\"title\">CONG TY CO PHAN VAN TAI DUONG SAT LAC HONG</div>");
                writer.write("<div class=\"sub-title\">THE LEN TAU HOA</div>");
                writer.write("<div class=\"sub-title\">BOARDING PASS</div>");

                // Tạo mã QR và lưu đường dẫn tương đối
                String qrCodeFileName = "QR_" + chiTietHoaDonList.get(i).getMaVe() + ".png";
                String qrCodePath = "../" + qrCodeFileName;
                createQRCode(chiTietHoaDonList.get(i).getMaVe(), qrCodeFileName);

                writer.write("<img class=\"qr-code\" src=\"" + qrCodePath + "\" width=\"100\" height=\"100\" alt=\"QR Code\"/>");

                // Thông tin vé
                writer.write("<div class=\"section-title\">MA VE: " + chiTietHoaDonList.get(i).getMaVe() + "</div>");
                writer.write("<div class=\"section-title\">THONG TIN KHACH HANG</div>");
                writer.write("<div class=\"content\">Ho va ten: " + removeDiacritics(ticket.getHoTen()) + "</div>");
                writer.write("<div class=\"content\">Doi tuong: " + removeDiacritics(ticket.getDoiTuong()) + "</div>");
                writer.write("<div class=\"content\">Giay to: " + removeDiacritics(ticket.getGiayTo()) + "</div>");

                // Thông tin hành trình
                writer.write("<div class=\"section-title\">THONG TIN HANH TRINH</div>");
                writer.write("<div class=\"content\">Ga di - Ga den: " + removeDiacritics(ticket.getGaDi()) + " - " + removeDiacritics(ticket.getGaDen()) + "</div>");
                writer.write("<div class=\"content\">Tau/Train: " + removeDiacritics(ticket.getTenTau()) + "</div>");
                writer.write("<div class=\"content\">Ngay di/Date: " + ticket.getNgayDi() + "</div>");
                writer.write("<div class=\"content\">Gi di/Time: " + ticket.getGioDi() + "</div>");
                writer.write("<div class=\"content\">Toa/Coach: " + removeDiacritics(ticket.getToaTau()) + "</div>");
                writer.write("<div class=\"content\">Cho/Seat: " + removeDiacritics(ticket.getChoNgoi()) + "</div>");

                // Thông tin giá vé
                writer.write("<div class=\"section-title\">THONG TIN GIA VE</div>");
                writer.write("<div class=\"content\">Gia Ve: " + ticket.getGiaVe() + " VND </div>");
                writer.write("<div class=\"content\">VAT 10 % </div>");
                writer.write("<div class=\"content\">Thanh tien: " + ticket.getThanhTien() + " VND</div>");

                // Định dạng ngày in
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss, dd/MM/yyyy");
                String formattedDate = LocalDateTime.now().format(formatter);
                writer.write("<div class=\"section-title\" style=\"text-align:center;\">Ngay in: " + formattedDate + "</div>");

                writer.write("<hr/>");

                // Xóa file QR sau khi thêm vào HTML
                Path path = FileSystems.getDefault().getPath(qrCodeFileName);
                Files.deleteIfExists(path);

                // Nếu không phải vé cuối cùng thì thêm ngắt dòng
                if (i < ticketDetailsList.size() - 1) {
                    writer.write("<div style=\"page-break-before: always;\"></div>");
                }
            }

            writer.write("</body></html>");
            System.out.println("Thông tin vé đã được tạo thành công: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createQRCode(String data, String filePath) {
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 200, 200);
            MatrixToImageWriter.writeToPath(matrix, "PNG", FileSystems.getDefault().getPath(filePath));
            System.out.println("QR Code saved at: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Phương thức chuyển chuỗi có dấu thành chuỗi không dấu
    public static String removeDiacritics(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        // Loại bỏ dấu
        String result = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        // Thay 'Đ' thành 'D'
        result = result.replace('Đ', 'D').replace('đ', 'd');

        return result;
    }
}
