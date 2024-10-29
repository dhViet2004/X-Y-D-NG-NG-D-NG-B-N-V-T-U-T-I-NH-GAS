package DAO;

import Database.ConnectDatabase;
import Entity.NhanVien;
import Entity.TaiKhoan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO_TaiKhoan {
    private Connection con;

    public DAO_TaiKhoan() {
        if (ConnectDatabase.getConnection() == null) {
            ConnectDatabase.getInstance().connect();
        }
        con = ConnectDatabase.getConnection();
    }

    public NhanVien checkLogin(String maNhanVien, String password) throws Exception {
        String sql = "SELECT nv.ChucVu, nv.TenNV " +
                     "FROM TaiKhoan tk " +
                     "JOIN NhanVien nv ON tk.MaNV = nv.MaNV " +
                     "WHERE tk.MaNV = ? AND tk.Password = ?";
        
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, maNhanVien);
            stm.setString(2, password);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    NhanVien nv = new NhanVien();
                    nv.setMaNhanVien(maNhanVien);
                    nv.setTenNV(rs.getString("TenNV"));
                    nv.setChucVu(rs.getString("ChucVu"));
                    return nv;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }
        return null; // Trả về null nếu không tìm thấy
    }

    public boolean insert(TaiKhoan nd) throws Exception {
        String sql = "INSERT INTO dbo.TaiKhoan(MaNV, Password) VALUES (?, ?)";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            // Thiết lập giá trị cho các tham số trong câu lệnh SQL
            stm.setString(1, nd.getMaNhanVien()); // Mã tài khoản
            stm.setString(2, nd.getPassword()); // Mật khẩu

            return stm.executeUpdate() > 0; // Nếu có ít nhất 1 bản ghi được thêm, trả về true
        } catch (SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có
            return false; // Trả về false nếu có lỗi
        }
    }
}
