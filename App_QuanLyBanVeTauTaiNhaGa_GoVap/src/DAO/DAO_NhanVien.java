/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Database.ConnectDatabase;
import Entity.NhanVien;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author viet6
 */
public class DAO_NhanVien {
    private Connection con;
    public DAO_NhanVien(){
        ConnectDatabase.getInstance();
        con = ConnectDatabase.getConnection();
    }
    
  public List<NhanVien> findAll() throws Exception {
    List<NhanVien> danhSachNhanVien = new ArrayList<>(); // Khởi tạo danh sách nhân viên
      try {
          String sql = "select * from NhanVien";
          PreparedStatement stm = con.prepareStatement(sql);
          ResultSet rs = stm.executeQuery();
                  while (rs.next()) {
            // Lấy dữ liệu từ ResultSet
            String maNhanVien = rs.getString("MaNV");
            String tenNV = rs.getString("TenNV");
            String soDT = rs.getString("SoDT");
            String trangThai = rs.getString("TrangThai");
            String CCCD = rs.getString("CCCD");
            String diaChi = rs.getString("DiaChi");
            LocalDate ngayVaoLam = rs.getDate("NgayThamGia").toLocalDate(); // Đảm bảo sử dụng đúng cột
            String chucVu = rs.getString("ChucVu");
            String avata = rs.getString("Avata"); // Lấy giá trị avata từ ResultSet

            // Tạo đối tượng NhanVien và thêm vào danh sách
            NhanVien nv = new NhanVien(maNhanVien, tenNV, soDT, trangThai, CCCD, diaChi, ngayVaoLam, chucVu, avata); // Cập nhật constructor
            danhSachNhanVien.add(nv);
        }
      } catch (Exception e) {
      }
  
    return danhSachNhanVien; // Trả về danh sách nhân viên
}


    //lấy ra số lượng nhân viên
       public int getSoLuongNhanVien() throws Exception {
        return findAll().size();  // Trả về số lượng nhân viên
    }
//thêm
public boolean insert(NhanVien nv) throws Exception {
    try {
            String sql = "INSERT INTO NhanVien(MaNV, TenNV, SoDT, TrangThai, CCCD, DiaChi, NgayThamGia, ChucVu, Avata) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(sql);
             stm.setString(1, nv.getMaNhanVien()); // Mã nhân viên
            stm.setString(2, nv.getTenNV());      // Tên nhân viên
            stm.setString(3, nv.getSoDT());       // Số điện thoại
            stm.setString(4, nv.getTrangThai());   // Trạng thái
            stm.setString(5, nv.getCCCD());       // Số CCCD
            stm.setString(6, nv.getDiaChi());     // Địa chỉ
            stm.setDate(7, Date.valueOf(nv.getNgayVaoLam()));  // Ngày tham gia
            stm.setString(8, nv.getChucVu());     // Chức vụ
            System.out.println("nhan vien getAvate:"+ nv.getAvata());
            stm.setString(9, nv.getAvata());      // Avatar

        // Thực thi câu lệnh và kiểm tra kết quả
        return stm.executeUpdate() > 0;  // Nếu có ít nhất 1 bản ghi bị thay đổi, trả về true
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}



public boolean update(NhanVien nv) throws Exception {
    String sql = "UPDATE dbo.NhanVien SET TenNV = ?, SoDT = ?, TrangThai = ?, CCCD = ?, DiaChi = ?, NgayThamGia = ?, ChucVu = ?, Avata = ? WHERE MaNV = ?";
    try (PreparedStatement stm = con.prepareStatement(sql)) {
        stm.setString(1, nv.getTenNV());
        stm.setString(2, nv.getSoDT());
        stm.setString(3, nv.getTrangThai());
        stm.setString(4, nv.getCCCD());
        stm.setString(5, nv.getDiaChi());
        stm.setDate(6, Date.valueOf(nv.getNgayVaoLam()));  // Chuyển LocalDate thành java.sql.Date
        stm.setString(7, nv.getChucVu());
        stm.setString(8, nv.getAvata()); // Thêm dòng này để thiết lập giá trị cho trường Avata
        stm.setString(9, nv.getMaNhanVien()); // Cập nhật tham số cho maNhanVien

        return stm.executeUpdate() > 0; // Nếu có ít nhất 1 bản ghi bị thay đổi, trả về true
    } catch (SQLException e) {
        e.printStackTrace(); // In ra lỗi nếu có
        return false; // Trả về false nếu có lỗi
    }
}


       
    
}
