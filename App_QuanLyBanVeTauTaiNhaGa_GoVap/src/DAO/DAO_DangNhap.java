package DAO;

import Database.ConnectDatabase;
import Entity.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
