package Main;

import DAO.DAO_BanVe;
import Database.ConnectDatabase;
import Entity.ToaTau;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Kết nối đến cơ sở dữ liệu
        ConnectDatabase.getInstance().connect();

        System.out.println("Hello World");

        // Tạo đối tượng DAO_BanVe
        DAO_BanVe dao = new DAO_BanVe();

        // Gọi phương thức để lấy danh sách toa tàu theo mã tàu T02
        List<ToaTau> toaTauList = dao.getToaByMaTau("T02");

        // In ra thông tin toa tàu từ danh sách
        for (ToaTau toaTau : toaTauList) {
            System.out.println(toaTau);
        }

        // Ngắt kết nối
        ConnectDatabase.disconnect();
    }
}
