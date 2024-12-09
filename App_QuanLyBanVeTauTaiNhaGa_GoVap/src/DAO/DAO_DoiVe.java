/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Database.ConnectDatabase;
import Entity.KhachHang;
import Entity.LichTrinhTau;
import Entity.VeTau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author viet6
 */
public class DAO_DoiVe {
    
    private Connection con;
    private VeTau veTau;
    private KhachHang khachHang;
    public DAO_DoiVe() {
        con = ConnectDatabase.getConnection();
    }
    
      // Phương thức tìm vé theo mã vé
public VeTau timVeTheoMaVe(String maVe) {
    try  {
            String sql = "SELECT * FROM VeTau WHERE MaVe = ?"; // Câu lệnh SQL để tìm vé theo mã vé
             PreparedStatement stmt = con.prepareStatement(sql);
             stmt.setString(1, maVe);  // Gán giá trị mã vé vào câu lệnh SQL

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                // Lấy thông tin từ kết quả truy vấn và tạo đối tượng VeTau
                String maVeTau = rs.getString("MaVe");
                String lichTrinhTauMaLich = rs.getString("LichTrinhTauMaLich");
                String choNgoiMaCho = rs.getString("ChoNgoiMaCho");
                String tenKH = rs.getString("TenKH");
                String giayTo = rs.getString("GiayTo");
                LocalDate ngayDi = rs.getDate("NgayDi").toLocalDate();
                String doiTuong = rs.getString("DoiTuong");
                double giaVe = rs.getDouble("GiaVe");
                String trangThai = rs.getString("TrangThai");

                // Tạo đối tượng VeTau với các giá trị lấy từ database
                  veTau = new VeTau(maVeTau, lichTrinhTauMaLich, choNgoiMaCho, tenKH, giayTo, ngayDi, doiTuong, giaVe, trangThai);
            } else {
                System.out.println("Không tìm thấy vé với mã: " + maVe);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // In lỗi nếu có
    }

    return veTau; // Trả về đối tượng VeTau (có thể là null nếu

}   
//tìm khách hàng theo mã vé
public KhachHang timTTKHtheoMaVe(String maVe) {
    try {
        // Câu lệnh SQL để tìm thông tin khách hàng theo mã vé
        String sql = "SELECT KhachHang.MaKH, KhachHang.TenKH, KhachHang.CCCD, KhachHang.SoDT, KhachHang.DiaChi " +
                     "FROM VeTau " +
                     "JOIN ChiTietHoaDon ON VeTau.MaVe = ChiTietHoaDon.MaVe " +
                     "JOIN HoaDon ON ChiTietHoaDon.MaHD = HoaDon.MaHD " +
                     "JOIN KhachHang ON HoaDon.MaKH = KhachHang.MaKH " +
                     "WHERE VeTau.MaVe = ?";  // Điều kiện tìm kiếm

        // Chuẩn bị câu lệnh SQL với tham số
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, maVe);  // Gán giá trị mã vé vào câu lệnh SQL

        // Thực thi câu lệnh SQL và lấy kết quả
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                // Lấy thông tin từ kết quả truy vấn và tạo đối tượng KhachHang
                String maKH = rs.getString("MaKH");
                String tenKH = rs.getString("TenKH");
                String cccd = rs.getString("CCCD");
                String soDT = rs.getString("SoDT");
                String diaChi = rs.getString("DiaChi");

                // Tạo đối tượng KhachHang với các giá trị lấy từ database
                khachHang = new KhachHang(maKH, tenKH, cccd, soDT, diaChi);
            } else {
                System.out.println("Không tìm thấy khách hàng với mã vé: " + maVe);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // In lỗi nếu có
    }

    return khachHang; // Trả về đối tượng KhachHang (có thể là null nếu không tìm thấy)
}
// Tìm khách hàng theo mã hóa đơn
public KhachHang timTTKHtheoMaHoaDon(String maHoaDon) {
    try {
        // Câu lệnh SQL để tìm thông tin khách hàng theo mã hóa đơn
        String sql = "SELECT KhachHang.MaKH, KhachHang.TenKH, KhachHang.CCCD, KhachHang.SoDT, KhachHang.DiaChi " +
                     "FROM HoaDon " +
                     "JOIN KhachHang ON HoaDon.MaKH = KhachHang.MaKH " +
                     "WHERE HoaDon.MaHD = ?";  // Điều kiện tìm kiếm

        // Chuẩn bị câu lệnh SQL với tham số
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, maHoaDon);  // Gán giá trị mã hóa đơn vào câu lệnh SQL

        // Thực thi câu lệnh SQL và lấy kết quả
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                // Lấy thông tin từ kết quả truy vấn và tạo đối tượng KhachHang
                String maKH = rs.getString("MaKH");
                String tenKH = rs.getString("TenKH");
                String cccd = rs.getString("CCCD");
                String soDT = rs.getString("SoDT");
                String diaChi = rs.getString("DiaChi");
                // Tạo đối tượng KhachHang với các giá trị lấy từ database
                khachHang = new KhachHang(maKH, tenKH, cccd, soDT, diaChi);

            } else {
                System.out.println("Không tìm thấy khách hàng với mã hóa đơn: " + maHoaDon);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // In lỗi nếu có
    }

    return khachHang; // Trả về đối tượng KhachHang (có thể là null nếu không tìm thấy)
}

//tìm danh sách vé theo hóa đơn
public List<VeTau> timVeTheoMaHoaDon(String maHoaDon) {
    List<VeTau> danhSachVeTau = new ArrayList<>();

    try {
        // Câu lệnh SQL để tìm vé theo mã hóa đơn, kết hợp các bảng VeTau, ChiTietHoaDon và HoaDon
        String sql = "SELECT v.MaVe, v.LichTrinhTauMaLich, v.ChoNgoiMaCho, v.TenKH, v.GiayTo, v.NgayDi, v.DoiTuong, v.GiaVe, v.TrangThai "
                   + "FROM VeTau v "
                   + "JOIN ChiTietHoaDon cthd ON v.MaVe = cthd.MaVe "
                   + "JOIN HoaDon hd ON cthd.MaHD = hd.MaHD "
                   + "WHERE hd.MaHD = ?"; // Lọc theo MaHD

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, maHoaDon);  // Gán giá trị MaHD vào câu lệnh SQL

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Lấy thông tin từ kết quả truy vấn và tạo đối tượng VeTau
                String maVe = rs.getString("MaVe");
                String lichTrinhTauMaLich = rs.getString("LichTrinhTauMaLich");
                String choNgoiMaCho = rs.getString("ChoNgoiMaCho");
                String tenKH = rs.getString("TenKH");
                String giayTo = rs.getString("GiayTo");
                LocalDate ngayDi = rs.getDate("NgayDi").toLocalDate();
                String doiTuong = rs.getString("DoiTuong");
                double giaVe = rs.getDouble("GiaVe");
                String trangThai = rs.getString("TrangThai");

                // Tạo đối tượng VeTau với các giá trị lấy từ database
                veTau = new VeTau(maVe, lichTrinhTauMaLich, choNgoiMaCho, tenKH, giayTo, ngayDi, doiTuong, giaVe, trangThai);

                // Thêm đối tượng VeTau vào danh sách
                danhSachVeTau.add(veTau);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // In lỗi nếu có
    }

    return danhSachVeTau; // Trả về danh sách vé (có thể rỗng nếu không tìm thấy vé)
}
public LichTrinhTau timLichTrinhTheoMaVe(String maVe) {
    LichTrinhTau lichTrinhTau = null;
    try {
        // Câu lệnh SQL để tìm thông tin lịch trình tàu theo mã vé
        String sql = "SELECT LichTrinhTau.MaLich, LichTrinhTau.GioDi, LichTrinhTau.NgayDi " +
                     "FROM VeTau " +
                     "JOIN LichTrinhTau ON VeTau.LichTrinhTauMaLich = LichTrinhTau.MaLich " +
                     "WHERE VeTau.MaVe = ?";

        // Chuẩn bị câu lệnh SQL
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, maVe); // Gán giá trị mã vé vào tham số

        // Thực thi câu lệnh SQL và lấy kết quả
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                // Lấy thông tin từ kết quả truy vấn
                String maLich = rs.getString("MaLich");
                LocalTime gioDi = rs.getTimestamp("GioDi").toLocalDateTime().toLocalTime(); // Lấy giờ đi
                LocalDate ngayDi = rs.getDate("NgayDi").toLocalDate(); // Lấy ngày đi

                // Tạo đối tượng LichTrinhTau với các giá trị cần thiết
                lichTrinhTau = new LichTrinhTau(maLich, ngayDi, gioDi);
            } else {
                System.out.println("Không tìm thấy lịch trình với mã vé: " + maVe);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // In lỗi nếu có
    }

    return lichTrinhTau; // Trả về đối tượng LichTrinhTau (có thể là null nếu không tìm thấy)
}

//đổi trạng thái vé
public boolean capNhatTrangThaiVe(String maVe, String trangThaiMoi) {
    try {
        // Câu lệnh SQL để cập nhật trạng thái vé
        String sql = "UPDATE VeTau SET TrangThai = ? WHERE MaVe = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // Gán giá trị cho các tham số trong câu lệnh SQL
        stmt.setString(1, trangThaiMoi); // Gán trạng thái mới
        stmt.setString(2, maVe);        // Gán mã vé cần cập nhật

        // Thực hiện lệnh cập nhật và kiểm tra kết quả
        int rowsUpdated = stmt.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Cập nhật trạng thái vé thành công!");
            return true; // Trả về true nếu cập nhật thành công
        } else {
            System.out.println("Không tìm thấy vé với mã: " + maVe);
        }
    } catch (SQLException e) {
        e.printStackTrace(); // In lỗi nếu có
    }

    return false; // Trả về false nếu có lỗi hoặc không tìm thấy vé
}

//điều kiện đổi vé
public boolean kiemTraThoiGianDoi(String maVe) {
    String sql = "SELECT NgayDi, GioDi FROM LichTrinhTau " +
            "WHERE MaLich = (SELECT LichTrinhTauMaLich FROM VeTau WHERE MaVe = ?)";
    try (PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setString(1, maVe); // Gán giá trị mã vé vào câu lệnh SQL

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                // Lấy ngày và giờ của tàu chạy từ cơ sở dữ liệu
                LocalDate ngayDi = rs.getDate("NgayDi").toLocalDate();
                LocalTime gioDi = rs.getTime("GioDi").toLocalTime();

                // Kết hợp ngày và giờ thành LocalDateTime
                LocalDateTime ngayGioDi = LocalDateTime.of(ngayDi, gioDi);

                // Lấy thời gian hiện tại
                LocalDateTime hienTai = LocalDateTime.now();

                // Tính toán thời gian còn lại
                Duration duration = Duration.between(hienTai, ngayGioDi);
                long hours = duration.toHours();

                // Kiểm tra nếu còn >= 24 giờ thì cho phép đổi vé
                return hours >= 24;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // In lỗi nếu có
    }
    return false; // Trả về false nếu không tìm thấy vé hoặc không thể tính toán thời gian
}


}
