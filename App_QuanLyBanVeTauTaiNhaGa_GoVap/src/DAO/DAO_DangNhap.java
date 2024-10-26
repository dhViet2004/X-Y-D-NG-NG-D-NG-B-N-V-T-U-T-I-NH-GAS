package DAO;

import Database.ConnectDatabase;
import Entity.NhanVien;
import Entity.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DAO_DangNhap {
  private Connection con;

  // Khởi tạo DAO_DangNhap và kết nối đến cơ sở dữ liệu
  public DAO_DangNhap() {
    con = ConnectDatabase.getConnection(); // Sử dụng hàm kết nối từ lớp ConnectDatabase
  }

  // Phương thức kiểm tra đăng nhập dựa trên mã nhân viên và mật khẩu
  public TaiKhoan dangNhap(String maNV, String password) throws SQLException {
    TaiKhoan taiKhoan = null;

    // Câu truy vấn SQL kiểm tra thông tin đăng nhập
    String sql = "SELECT MaNV, Password FROM TaiKhoan WHERE MaNV = ? AND Password = ?";

    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
      pstmt.setString(1, maNV);      // Gán mã nhân viên vào câu truy vấn
      pstmt.setString(2, password);  // Gán mật khẩu vào câu truy vấn

      ResultSet rs = pstmt.executeQuery();

      // Nếu tìm thấy tài khoản hợp lệ, tạo đối tượng TaiKhoan
      if (rs.next()) {
        String maNhanVien = rs.getString("MaNV");
        String matKhau = rs.getString("Password");
        taiKhoan = new TaiKhoan(maNhanVien, matKhau); // Khởi tạo đối tượng TaiKhoan
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new SQLException("Lỗi khi kiểm tra thông tin đăng nhập", e);
    }

    return taiKhoan; // Trả về đối tượng TaiKhoan nếu hợp lệ, nếu không trả về null
  }
  // Phương thức tìm nhân viên theo mã nhân viên
  public NhanVien timNhanVienTheoMa(String maNV) throws SQLException {
    NhanVien nhanVien = null;

    // Câu truy vấn SQL để tìm nhân viên theo mã
    String sql = "SELECT MaNV, TenNV, SoDT, TrangThai, CCCD, DiaChi, NgayThamGia, ChucVu " +
            "FROM NhanVien WHERE MaNV = ?";

    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
      pstmt.setString(1, maNV);  // Gán mã nhân viên vào câu truy vấn
      ResultSet rs = pstmt.executeQuery();

      // Nếu tìm thấy nhân viên, khởi tạo đối tượng NhanVien
      if (rs.next()) {
        String tenNV = rs.getString("TenNV");
        String soDT = rs.getString("SoDT");
        String trangThai = rs.getString("TrangThai");
        String CCCD = rs.getString("CCCD");
        String diaChi = rs.getString("DiaChi");
        LocalDate ngayVaoLam = rs.getDate("NgayThamGia").toLocalDate();
        String chucVu = rs.getString("ChucVu");

        nhanVien = new NhanVien(maNV, tenNV, soDT, trangThai, CCCD, diaChi, ngayVaoLam, chucVu);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new SQLException("Lỗi khi tìm nhân viên theo mã", e);
    }

    return nhanVien; // Trả về đối tượng NhanVien hoặc null nếu không tìm thấy
  }
  // Đóng kết nối
  public void closeConnection() {
    try {
      if (con != null && !con.isClosed()) {
        con.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
