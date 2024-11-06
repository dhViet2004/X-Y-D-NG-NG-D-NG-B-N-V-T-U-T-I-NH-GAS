package DAO;

import Database.ConnectDatabase;
import Entity.KhachHang;
import Entity.KhuyenMai;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DAO_KhuyenMai {
    private Connection con;

    public DAO_KhuyenMai() throws SQLException {
        if (ConnectDatabase.getConnection() == null) {
            ConnectDatabase.getInstance().connect();  // Kết nối nếu chưa kết nối
        }
        con = ConnectDatabase.getConnection();
    }

    public List<KhuyenMai> getKhuyenMais() throws SQLException {
        List<KhuyenMai> ds = new ArrayList<>();
        String sql = "SELECT * FROM KhuyenMai";
        DAO_KhuyenMai dao = new DAO_KhuyenMai();
        try (
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                KhuyenMai km = new KhuyenMai(
                        rs.getString("MaKM"),
                        rs.getDate("ThoiGianBatDau").toLocalDate(),
                        rs.getDate("ThoiGianKetThuc").toLocalDate(),
                        rs.getString("NoiDungKM"),
                        rs.getDouble("ChietKhau"),
                        rs.getString("DoiTuongApDung")
                );
                ds.add(km);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Ném lại ngoại lệ để xử lý ở nơi gọi
        }

        return ds;
    }

    public void themKhuyenMai(KhuyenMai km) throws SQLException {
        DAO_KhuyenMai dao_km = new DAO_KhuyenMai();
        String sql = "insert into KhuyenMai  (MaKM, ThoiGianBatDau, ThoiGianKetThuc, NoiDungKM, ChietKhau, DoiTuongApDung) values(?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, km.getMaKM());
        ps.setDate(2, Date.valueOf(km.getThoiGianBatDau()));
        ps.setDate(3, Date.valueOf(km.getThoiGianKetThuc()));
        ps.setString(4, km.getNoiDungKM());
        ps.setDouble(5, km.getChietKhau());
        ps.setString(6, km.getDoiTuongApDung());

        ps.executeUpdate();

    }

    public List<KhuyenMai> timKMTheoThoiGianApDung(LocalDate date) throws SQLException {
        DAO_KhuyenMai dao_khuyenMai = new DAO_KhuyenMai();
        String sql = "select * from KhuyenMai where  ThoiGianBatDau <= ? and ThoiGianKetThuc >= ?";
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<KhuyenMai> ds = new ArrayList<>();
        KhuyenMai km = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(date));
            ps.setDate(2, Date.valueOf(date));
            rs = ps.executeQuery();
            while (rs.next()) {
                km = new KhuyenMai(rs.getString(1), rs.getDate(2).toLocalDate(), rs.getDate(3).toLocalDate(), rs.getString(4), rs.getDouble(5), rs.getString(6));
                ds.add(km);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ds;
    }

    public List<KhuyenMai> timKMTheoThoiGianApDung_DoiTuongApDung(LocalDate date,String doiTuong) throws SQLException {
        DAO_KhuyenMai dao_khuyenMai = new DAO_KhuyenMai();
        String sql = "select * from KhuyenMai where  ThoiGianBatDau <= ? and ThoiGianKetThuc >= ? and DoiTuongApDung = ?";
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Entity.KhuyenMai> ds = new ArrayList<>();
        Entity.KhuyenMai km = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(date));
            ps.setDate(2, Date.valueOf(date));
            ps.setString(3, doiTuong);
            rs = ps.executeQuery();
            while (rs.next()) {
                km = new Entity.KhuyenMai(rs.getString(1), rs.getDate(2).toLocalDate(), rs.getDate(3).toLocalDate(), rs.getString(4), rs.getDouble(5), rs.getString(6));
                ds.add(km);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ds;
    }

    // xóa km theo mã
    public boolean xoaKMTheoMa(String ma) throws SQLException {
        String sql = "delete from KhuyenMai where MaKM = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, ma);

        return ps.executeUpdate() > 0;

    }
    public static void main(String[] args) throws SQLException {
        DAO_KhuyenMai dao= new DAO_KhuyenMai();
        List<KhuyenMai> ds = new ArrayList<>();
        try {
             ds = dao.timKMTheoThoiGianApDung_DoiTuongApDung(LocalDate.of(2024,9,30),"Tất cả khách hàng");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (KhuyenMai khachHang : ds) {
            System.out.println(khachHang.toString());
        }
    }
}

