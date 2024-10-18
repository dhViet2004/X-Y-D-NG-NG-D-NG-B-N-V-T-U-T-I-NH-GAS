package DAO;

import Database.ConnectDatabase;
import Entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO_BanVe {
    private Connection con;
    private LoaiCho loaiCho;

    public DAO_BanVe() {
        con = ConnectDatabase.getConnection();
    }

    // Phương thức lấy thông tin tàu từ ngày đi và ga đến
    public List<Tau> getTrainsByDateAndDestination(String destination, String date) throws SQLException {
        List<Tau> trainList = new ArrayList<>();

        // Câu truy vấn SQL
        String sql = "SELECT t.MaTau, t.TenTau, t.SoToa, tt.MaTuyen, tt.TenTuyen, tt.GaDi, tt.GaDen, tt.DiaDiemDi, tt.DiaDiemDen " +
                "FROM Tau t " +
                "JOIN TuyenTau tt ON t.MaTuyen = tt.MaTuyen " +
                "JOIN LichTrinhTau lt ON t.MaTau = lt.MaTau " +
                "WHERE tt.GaDen LIKE ? " +
                "AND lt.NgayDi = ?";

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + destination + "%");
            pstmt.setString(2, date);
            ResultSet rs = pstmt.executeQuery();

            // Lưu thông tin tàu vào danh sách
            while (rs.next()) {
                String maTau = rs.getString("MaTau");
                String tenTau = rs.getString("TenTau");
                int soToa = rs.getInt("SoToa");

                // Lấy thông tin tuyến tàu
                String maTuyen = rs.getString("MaTuyen");
                String tenTuyen = rs.getString("TenTuyen");
                String gaDi = rs.getString("GaDi");
                String gaDen = rs.getString("GaDen");
                String diaDiemDi = rs.getString("DiaDiemDi");
                String diaDiemDen = rs.getString("DiaDiemDen");

                // Tạo đối tượng TuyenTau
                TuyenTau tuyenTau = new TuyenTau(tenTuyen, maTuyen, gaDi, gaDen, diaDiemDi, diaDiemDen);

                // Tạo đối tượng Tau và thêm vào danh sách
                Tau tau = new Tau(tuyenTau, tenTau, maTau, soToa);
                trainList.add(tau);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trainList; // Trả về danh sách các chuyến tàu
    }


    //Phuương thức lấy danh sach Toa theo ma Tau
    public List<ToaTau> getToaByMaTau(String maTau) throws SQLException {
        List<ToaTau> toaList = new ArrayList<>();

        // Câu truy vấn SQL
        String sql = "SELECT tt.MaToa, lt.MaLoai, lt.TenLoai, tt.TenToa, tt.SoGhe, tt.ThuTu, t.MaTau, t.TenTau, t.SoToa, tu.MaTuyen, tu.TenTuyen " +
                "FROM ToaTau tt " +
                "JOIN LoaiToa lt ON tt.LoaiToaMaLoai = lt.MaLoai " +
                "JOIN Tau t ON tt.TauMaTau = t.MaTau " +
                "JOIN TuyenTau tu ON t.MaTuyen = tu.MaTuyen " +
                "WHERE t.MaTau = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, maTau); // Gán tham số mã tàu vào câu truy vấn
            ResultSet rs = pstmt.executeQuery();

            // Lặp qua kết quả trả về từ câu truy vấn
            while (rs.next()) {
                // Lấy thông tin loại toa
                String maLoai = rs.getString("MaLoai");
                String tenLoai = rs.getString("TenLoai");
                LoaiToa loaiToa = new LoaiToa(maLoai, tenLoai);

                // Lấy thông tin tàu
                String maTauResult = rs.getString("MaTau");
                String tenTau = rs.getString("TenTau");
                int soToa = rs.getInt("SoToa");

                // Lấy thông tin tuyến tàu
                String maTuyen = rs.getString("MaTuyen");
                String tenTuyen = rs.getString("TenTuyen");
                TuyenTau tuyenTau = new TuyenTau(tenTuyen, maTuyen, null, null, null, null); // Các thông tin khác của tuyến tàu có thể bỏ qua

                // Tạo đối tượng Tau
                Tau tau = new Tau(tuyenTau, tenTau, maTauResult, soToa);

                // Lấy thông tin toa tàu
                String maToa = rs.getString("MaToa");
                String tenToa = rs.getString("TenToa");
                int soGhe = rs.getInt("SoGhe");
                int thuTu = rs.getInt("ThuTu");

                // Tạo đối tượng ToaTau và thêm vào danh sách
                ToaTau toaTau = new ToaTau(maToa, loaiToa, tau, soGhe, tenToa, thuTu);
                toaList.add(toaTau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching ToaTau data", e);
        }
        return toaList;
    }



    public List<ChoNgoi> getSeatsByMaToa(String maToa) throws SQLException {
        List<ChoNgoi> seatsList = new ArrayList<>();

        // Câu truy vấn SQL
        String sql = "SELECT cho.MaCho, cho.LoaiChoMaLoai, toa.MaToa, cho.TenCho, cho.TinhTrang, cho.GiaTien " +
                "FROM ChoNgoi cho " +
                "JOIN LoaiCho loai ON cho.LoaiChoMaLoai = loai.MaLoai " +
                "JOIN ToaTau toa ON cho.LoaiToaMaToa = toa.MaToa " +
                "WHERE toa.MaToa = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, maToa); // Gán tham số mã toa vào câu truy vấn
            ResultSet rs = pstmt.executeQuery();

            // Lặp qua kết quả trả về từ câu truy vấn
            while (rs.next()) {
                String maCho = rs.getString("MaCho");
                String maLoai = rs.getString("LoaiChoMaLoai");
                String maToaTau  = rs.getString("MaToa");
                String tenCho = rs.getString("TenCho");
                Boolean tinhTrang = rs.getBoolean("TinhTrang");
                float gia = rs.getFloat("GiaTien");

                // Tạo đối tượng LoaiCho
                LoaiCho loaiCho = new LoaiCho(maLoai);
                ToaTau toaTau = new ToaTau(maToaTau);
                // Tạo đối tượng ChoNgoi và thêm vào danh sách
                ChoNgoi choNgoi = new ChoNgoi(maCho, loaiCho, toaTau, tenCho, tinhTrang, gia);
                seatsList.add(choNgoi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching ChoNgoi data", e);
        }

        return seatsList;
    }







}
