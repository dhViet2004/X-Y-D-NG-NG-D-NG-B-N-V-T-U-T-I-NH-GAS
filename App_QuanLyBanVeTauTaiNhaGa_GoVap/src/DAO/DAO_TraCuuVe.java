package DAO;

import Database.ConnectDatabase;
import Entity.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DAO_TraCuuVe {
    private Connection con;

    public DAO_TraCuuVe() {
        con = ConnectDatabase.getConnection();
    }

    public List<VeTau> timVeTauTheoMa(String maVe) throws SQLException {
        String query = """
                SELECT 
                    vt.MaVe,
                    lt.MaLich AS MaLichTrinh,
                    lt.GioDi,
                    lt.NgayDi,
                    tt.MaTau,
                    tt.TenTau,
                    tt.SoToa,
                    tn.TenTuyen AS TuyenTau,
                    cn.MaCho,
                    cn.TenCho,
                    cn.GiaTien,
                    vt.TenKH,
                    vt.GiayTo,
                    vt.NgayDi AS NgayDiCuaVe,
                    vt.DoiTuong,
                    vt.GiaVe,
                    vt.TrangThai
                FROM VeTau vt
                JOIN LichTrinhTau lt ON vt.LichTrinhTauMaLich = lt.MaLich
                JOIN Tau tt ON lt.MaTau = tt.MaTau
                JOIN TuyenTau tn ON tt.MaTuyen = tn.MaTuyen
                JOIN ChoNgoi cn ON vt.ChoNgoiMaCho = cn.MaCho
                WHERE vt.MaVe = ?;
                """;

        List<VeTau> danhSachVeTau = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, maVe);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Lấy thông tin từ ResultSet
                    String maLichTrinh = rs.getString("MaLichTrinh");
                    LocalTime gioDi = rs.getTime("GioDi").toLocalTime();
                    LocalDate ngayDiLichTrinh = rs.getDate("NgayDi").toLocalDate();
                    String maTau = rs.getString("MaTau");
                    String tenTau = rs.getString("TenTau");
                    int soToa = rs.getInt("SoToa");
                    String tenTuyen = rs.getString("TuyenTau");
                    String maCho = rs.getString("MaCho");
                    String tenCho = rs.getString("TenCho");
                    float giaTien = rs.getFloat("GiaTien");
                    String tenKhachHang = rs.getString("TenKH");
                    String giayTo = rs.getString("GiayTo");
                    LocalDate ngayDiVe = rs.getDate("NgayDiCuaVe").toLocalDate();
                    String doiTuong = rs.getString("DoiTuong");
                    double giaVe = rs.getDouble("GiaVe");
                    String trangThai = rs.getString("TrangThai");

                    // Tạo các đối tượng liên quan
                    TuyenTau tuyenTau = new TuyenTau(null, tenTuyen, null, null, null, null);
                    Tau tau = new Tau(maTau, tuyenTau, tenTau, soToa);
                    LichTrinhTau lichTrinhTau = new LichTrinhTau(maLichTrinh, gioDi, ngayDiLichTrinh, tau, null);
                    ChoNgoi choNgoi = new ChoNgoi(maCho, null, null, tenCho, null, giaTien);

                    // Tạo đối tượng VeTau
                    VeTau veTau = new VeTau(maVe, lichTrinhTau, choNgoi, tenKhachHang, giayTo, ngayDiVe, doiTuong, giaVe, trangThai);

                    // Thêm vào danh sách
                    danhSachVeTau.add(veTau);
                }
            }
        }

        return danhSachVeTau;
    }
    // Tìm vé tàu theo giấy tờ (GiayTo)
    public List<VeTau> timVeTauTheoGiayTo(String giayTo) throws SQLException {
        String query = """
                SELECT 
                    vt.MaVe,
                    lt.MaLich AS MaLichTrinh,
                    lt.GioDi,
                    lt.NgayDi,
                    tt.MaTau,
                    tt.TenTau,
                    tt.SoToa,
                    tn.TenTuyen AS TuyenTau,
                    cn.MaCho,
                    cn.TenCho,
                    cn.GiaTien,
                    vt.TenKH,
                    vt.GiayTo,
                    vt.NgayDi AS NgayDiCuaVe,
                    vt.DoiTuong,
                    vt.GiaVe,
                    vt.TrangThai
                FROM VeTau vt
                JOIN LichTrinhTau lt ON vt.LichTrinhTauMaLich = lt.MaLich
                JOIN Tau tt ON lt.MaTau = tt.MaTau
                JOIN TuyenTau tn ON tt.MaTuyen = tn.MaTuyen
                JOIN ChoNgoi cn ON vt.ChoNgoiMaCho = cn.MaCho
                WHERE vt.GiayTo = ?;
                """;

        List<VeTau> danhSachVeTau = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, giayTo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Lấy thông tin từ ResultSet
                    String maVe = rs.getString("MaVe");
                    String maLichTrinh = rs.getString("MaLichTrinh");
                    LocalTime gioDi = rs.getTime("GioDi").toLocalTime();
                    LocalDate ngayDiLichTrinh = rs.getDate("NgayDi").toLocalDate();
                    String maTau = rs.getString("MaTau");
                    String tenTau = rs.getString("TenTau");
                    int soToa = rs.getInt("SoToa");
                    String tenTuyen = rs.getString("TuyenTau");
                    String maCho = rs.getString("MaCho");
                    String tenCho = rs.getString("TenCho");
                    float giaTien = rs.getFloat("GiaTien");
                    String tenKhachHang = rs.getString("TenKH");
                    String giayToDb = rs.getString("GiayTo");
                    LocalDate ngayDiVe = rs.getDate("NgayDiCuaVe").toLocalDate();
                    String doiTuong = rs.getString("DoiTuong");
                    double giaVe = rs.getDouble("GiaVe");
                    String trangThai = rs.getString("TrangThai");

                    // Tạo các đối tượng liên quan
                    TuyenTau tuyenTau = new TuyenTau(null, tenTuyen, null, null, null, null);
                    Tau tau = new Tau(maTau, tuyenTau, tenTau, soToa);
                    LichTrinhTau lichTrinhTau = new LichTrinhTau(maLichTrinh, gioDi, ngayDiLichTrinh, tau, null);
                    ChoNgoi choNgoi = new ChoNgoi(maCho, null, null, tenCho, null, giaTien);

                    // Tạo đối tượng VeTau
                    VeTau veTau = new VeTau(maVe, lichTrinhTau, choNgoi, tenKhachHang, giayToDb, ngayDiVe, doiTuong, giaVe, trangThai);

                    // Thêm vào danh sách
                    danhSachVeTau.add(veTau);
                }
            }
        }

        return danhSachVeTau;
    }



}