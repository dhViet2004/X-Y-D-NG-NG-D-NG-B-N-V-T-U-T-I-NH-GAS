package Main;

import DAO.DAO_BanVe;
import Database.ConnectDatabase;
import Entity.ChoNgoi;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Kết nối đến cơ sở dữ liệu
        try {
            ConnectDatabase.getInstance().connect();
            System.out.println("Kết nối đến cơ sở dữ liệu thành công!");

            // Thử lấy danh sách chỗ ngồi của toa TT06
            String maToa = "TT06"; // Mã toa bạn muốn lấy danh sách chỗ ngồi
            DAO_BanVe dao = new DAO_BanVe(); // Khởi tạo đối tượng DAO_BanVe

            List<ChoNgoi> choNgoiList = dao.getSeatsByMaToa(maToa);

            // In ra thông tin chỗ ngồi từ danh sách
            if (choNgoiList.isEmpty()) {
                System.out.println("Không tìm thấy chỗ ngồi nào cho toa " + maToa);
            } else {
                System.out.println("Danh sách chỗ ngồi cho toa " + maToa + ":");
                for (ChoNgoi choNgoi : choNgoiList) {
                    System.out.println(choNgoi);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi kết nối đến cơ sở dữ liệu hoặc truy vấn dữ liệu: " + e.getMessage());
        } finally {
            // Ngắt kết nối
            ConnectDatabase.disconnect();
            System.out.println("Ngắt kết nối khỏi cơ sở dữ liệu.");
        }
    }
}
