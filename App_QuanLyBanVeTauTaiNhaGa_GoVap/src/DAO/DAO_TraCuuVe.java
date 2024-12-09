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

    public VeTau getVeTauByMaVe(String maVe) throws SQLException {
        VeTau veTau = null;

        String sql = "SELECT vt.MaVe, vt.LichTrinhTauMaLich, vt.ChoNgoiMaCho, vt.TenKH, vt.GiayTo, vt.NgayDi, vt.DoiTuong, vt.GiaVe, vt.TrangThai, " +
                "lt.MaLich, lt.MaTau, lt.GioDi, lt.NgayDi AS LichNgayDi, lt.TrangThai AS TrangThaiLich, " +
                "c.MaCho, c.LoaiChoMaLoai, c.LoaiToaMaToa, c.TenCho, c.TinhTrang, c.GiaTien " +
                "FROM VeTau vt " +
                "JOIN LichTrinhTau lt ON vt.LichTrinhTauMaLich = lt.MaLich " +
                "JOIN ChoNgoi c ON vt.ChoNgoiMaCho = c.MaCho " +
                "WHERE vt.MaVe = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, maVe);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Lấy thông tin vé tàu
                String tenKhachHang = rs.getString("TenKH");
                String giayTo = rs.getString("GiayTo");
                LocalDate ngayDi = rs.getDate("NgayDi").toLocalDate();
                String doiTuong = rs.getString("DoiTuong");
                double giaVe = rs.getDouble("GiaVe");
                String trangThaiVe = rs.getString("TrangThai");

                // Lấy thông tin lịch trình tàu
                String maLich = rs.getString("MaLich");
                String maTau = rs.getString("MaTau");
                LocalTime gioDi = rs.getTime("GioDi").toLocalTime();
                LocalDate lichNgayDi = rs.getDate("LichNgayDi").toLocalDate();
                String trangThaiLich = rs.getString("TrangThaiLich");
                Tau tau = new Tau(maTau, null, null, 0); // Giả định chỉ có mã tàu

                LichTrinhTau lichTrinhTau = new LichTrinhTau(maLich, gioDi, lichNgayDi, tau, trangThaiLich);

                // Lấy thông tin chỗ ngồi
                String maCho = rs.getString("MaCho");
                String loaiChoMaLoai = rs.getString("LoaiChoMaLoai");
                String loaiToaMaToa = rs.getString("LoaiToaMaToa");
                String tenCho = rs.getString("TenCho");
                boolean tinhTrang = rs.getBoolean("TinhTrang");
                float giaTien = rs.getFloat("GiaTien");
                ToaTau toaTau = new ToaTau(loaiToaMaToa, null, null, 0, null, 0);
                LoaiCho loaiCho = new LoaiCho(loaiChoMaLoai);
                ChoNgoi choNgoi = new ChoNgoi(maCho, loaiCho, toaTau, tenCho, tinhTrang, giaTien);

                // Tạo đối tượng VeTau
                veTau = new VeTau(maVe, lichTrinhTau, choNgoi, tenKhachHang, giayTo, ngayDi, doiTuong, giaVe, trangThaiVe);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching ticket information by MaVe", e);
        }

        return veTau;
    }

    public List<VeTau> getAllVeTau() throws SQLException {
        List<VeTau> veTauList = new ArrayList<>();

        String sql = "SELECT vt.MaVe, vt.LichTrinhTauMaLich, vt.ChoNgoiMaCho, vt.TenKH, vt.GiayTo, vt.NgayDi, vt.DoiTuong, vt.GiaVe, vt.TrangThai, " +
                "lt.MaLich, lt.MaTau, lt.GioDi, lt.NgayDi AS LichNgayDi, lt.TrangThai AS TrangThaiLich, " +
                "c.MaCho, c.LoaiChoMaLoai, c.LoaiToaMaToa, c.TenCho, c.TinhTrang, c.GiaTien " +
                "FROM VeTau vt " +
                "JOIN LichTrinhTau lt ON vt.LichTrinhTauMaLich = lt.MaLich " +
                "JOIN ChoNgoi c ON vt.ChoNgoiMaCho = c.MaCho";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                // Lấy thông tin vé tàu tương tự như trên
                String maVe = rs.getString("MaVe");
                String tenKhachHang = rs.getString("TenKH");
                String giayTo = rs.getString("GiayTo");
                LocalDate ngayDi = rs.getDate("NgayDi").toLocalDate();
                String doiTuong = rs.getString("DoiTuong");
                double giaVe = rs.getDouble("GiaVe");
                String trangThaiVe = rs.getString("TrangThai");

                String maLich = rs.getString("MaLich");
                String maTau = rs.getString("MaTau");
                LocalTime gioDi = rs.getTime("GioDi").toLocalTime();
                LocalDate lichNgayDi = rs.getDate("LichNgayDi").toLocalDate();
                String trangThaiLich = rs.getString("TrangThaiLich");
                Tau tau = new Tau(maTau, null, null, 0);
                LichTrinhTau lichTrinhTau = new LichTrinhTau(maLich, gioDi, lichNgayDi, tau, trangThaiLich);

                String maCho = rs.getString("MaCho");
                String loaiChoMaLoai = rs.getString("LoaiChoMaLoai");
                String loaiToaMaToa = rs.getString("LoaiToaMaToa");
                String tenCho = rs.getString("TenCho");
                boolean tinhTrang = rs.getBoolean("TinhTrang");
                float giaTien = rs.getFloat("GiaTien");
                ToaTau toaTau = new ToaTau(loaiToaMaToa, null, null, 0, null, 0);
                LoaiCho loaiCho = new LoaiCho(loaiChoMaLoai);
                ChoNgoi choNgoi = new ChoNgoi(maCho, loaiCho, toaTau, tenCho, tinhTrang, giaTien);

                VeTau veTau = new VeTau(maVe, lichTrinhTau, choNgoi, tenKhachHang, giayTo, ngayDi, doiTuong, giaVe, trangThaiVe);
                veTauList.add(veTau);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching all tickets", e);
        }

        return veTauList;
    }
}