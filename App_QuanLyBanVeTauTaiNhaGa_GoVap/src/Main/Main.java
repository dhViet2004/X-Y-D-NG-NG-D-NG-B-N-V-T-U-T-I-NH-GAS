package Main;

import DAO.DAO_BanVe;
import Database.ConnectDatabase;
import Entity.ChoNgoi;
import Entity.LichTrinhTau;
import Entity.VeTau;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Kết nối đến cơ sở dữ liệu
        try {
            ConnectDatabase.getInstance().connect();
            System.out.println("Kết nối đến cơ sở dữ liệu thành công!");

            // Thử lấy danh sách chỗ ngồi của toa TT06
            String maCho = "CN60"; // Mã toa bạn muốn lấy danh sách chỗ ngồi
            String lichTrinhTau = "LT001";
            DAO_BanVe dao = new DAO_BanVe(); // Khởi tạo đối tượng DAO_BanVe
            VeTau veTau = dao.getVeTaubyLichTrinhTauandMaCho(lichTrinhTau, maCho);
            if (veTau != null) {
                System.out.println("Thông tin vé tàu:");
                System.out.println("Mã vé: " + veTau.getMaVe());
                System.out.println("Tên khách hàng: " + veTau.getTenKhachHang());
                System.out.println("Giấy tờ: " + veTau.getGiayTo());
                System.out.println("Ngày đi: " + veTau.getNgayDi());
                System.out.println("Gio di"+veTau.getLichTrinhTau().getGioDi());
                System.out.println("Đối tượng: " + veTau.getDoiTuong());
                System.out.println("Giá vé: " + veTau.getGiaVe());
                System.out.println("Trạng thái: " + veTau.getTrangThai());
            } else {
                System.out.println("Không tìm thấy vé tàu với mã lịch trình và mã chỗ ngồi đã cho.");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
