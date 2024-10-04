package DAO;

import Database.ConnectDatabase;
import Entity.KhuyenMai;

import java.sql.*;
import java.util.ArrayList;

public class DAO_KhuyenMai {

    public DAO_KhuyenMai() {
    }

    public ArrayList<KhuyenMai> getKhuyenMais() throws SQLException {
        ArrayList<KhuyenMai> listKM = new ArrayList<>();
        ConnectDatabase.getInstance();
        Connection con = ConnectDatabase.getConnection(); // Kiểm tra nếu connection không phải null

        if (con == null) {
            throw new SQLException("Không thể kết nối đến cơ sở dữ liệu");
        }

        String sql = "SELECT * FROM KhuyenMai";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            KhuyenMai km = new KhuyenMai(
                    rs.getString("MaKM"),
                    rs.getDate("ThoiGianBatDau").toLocalDate(),
                    rs.getDate("ThoiGianKetThuc").toLocalDate(),
                    rs.getString("NoiDungKM"),
                    rs.getDouble("ChietKhau"),
                    rs.getString("DoiTuongApDung")
            );
            listKM.add(km);
        }

        return listKM;
    }

    public static void main(String[] args) {
        Connection con = null;
        String url = "jdbc:sqlserver://localhost:1433;databasename=UngDungQuanLyBanVeTaiGaGoVap";
        String user = "sa";
        String password = "sapassword";

        try {
            System.out.println("Đang kết nối đến cơ sở dữ liệu...");
            con = DriverManager.getConnection(url, user, password);
            if (con != null) {
                System.out.println("Kết nối thành công!");
            }
        } catch (SQLException e) {
            System.out.println("Kết nối thất bại!");
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
