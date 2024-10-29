package DAO;

import Database.ConnectDatabase;
import Entity.LichLamViec;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DAO_LichLamViec {

    private Connection con;

     public DAO_LichLamViec() { 
       ConnectDatabase.getInstance();
       con = ConnectDatabase.getConnection();
    }


    public List<LichLamViec> findAll() throws Exception {
        List<LichLamViec> danhSachLichLamViec = new ArrayList<>();
      
        try {
              String sql = "SELECT * FROM LichLamViec";
              PreparedStatement stm = con.prepareStatement(sql);
              ResultSet rs = stm.executeQuery();
               while (rs.next()) {
                // Lấy dữ liệu từ ResultSet và tạo đối tượng LichLamViec
                String maLichLamViec = rs.getString("MaLichLamViec");
                String maNhanVien = rs.getString("MaNhanVien");
                LocalDateTime gioBatDau = rs.getTimestamp("GioBatDau").toLocalDateTime();
                LocalDateTime gioKetThuc = rs.getTimestamp("GioKetThuc").toLocalDateTime();
                String trangThai = rs.getString("TrangThai");
                String tenCa = rs.getString("TenCa");

                LichLamViec lichLamViec = new LichLamViec(maLichLamViec, maNhanVien, gioBatDau, gioKetThuc, trangThai, tenCa);
                danhSachLichLamViec.add(lichLamViec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return danhSachLichLamViec; // Trả về danh sách lịch làm việc
    }

    public boolean insert(LichLamViec lichLamViec) throws Exception {
        try {
                  String sql = "INSERT INTO LichLamViec (MaLichLamViec,MaNhanVien, GioBatDau, GioKetThuc, TrangThai, TenCa) "
                            + "VALUES (?, ?, ?, ?, ?,?)";
                  PreparedStatement stm = con.prepareStatement(sql);
                              stm.setString(1, lichLamViec.getMaLichLamViec());
            stm.setString(2, lichLamViec.getMaNhanVien());
            LocalDateTime gioBatDau = lichLamViec.getGioBatDau();
            LocalDateTime gioKetThuc = lichLamViec.getGioKetThuc();
            stm.setTimestamp(3, Timestamp.valueOf(gioBatDau));
            stm.setTimestamp(4, Timestamp.valueOf(gioKetThuc));
            stm.setString(5, lichLamViec.getTrangThai());
            stm.setString(6, lichLamViec.getTenCa());

            return stm.executeUpdate() > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(LichLamViec lichLamViec) throws Exception {
        try {
             String sql = "UPDATE LichLamViec SET MaNhanVien = ?, GioBatDau = ?, GioKetThuc = ?, TrangThai = ?, TenCa = ? "
                + "WHERE MaLichLamViec = ?";
             PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, lichLamViec.getMaNhanVien());
            stm.setTimestamp(2, Timestamp.valueOf(lichLamViec.getGioBatDau()));
            stm.setTimestamp(3, Timestamp.valueOf(lichLamViec.getGioKetThuc()));
            stm.setString(4, lichLamViec.getTrangThai());
            stm.setString(5, lichLamViec.getTenCa());
            return stm.executeUpdate() > 0; 
        } catch (SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có
            return false; // Trả về false nếu có lỗi
        }
    }
    public int getSoLuongLichLamViec() throws Exception {
        return findAll().size();
    }
    public List<LichLamViec> getCaLamViecForDate(String maNhanVien, LocalDate today) throws Exception {
        List<LichLamViec> lichLamViecs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM LichLamViec WHERE MaNhanVien = ? AND CAST(GioBatDau AS DATE) = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, maNhanVien);
            stm.setDate(2, java.sql.Date.valueOf(today));
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                    LichLamViec llv = new LichLamViec();
                    llv.setMaLichLamViec(rs.getString("MaLichLamViec"));
                    llv.setMaNhanVien(rs.getString("MaNhanVien"));
                    llv.setGioBatDau(rs.getTimestamp("GioBatDau").toLocalDateTime());
                    llv.setGioKetThuc(rs.getTimestamp("GioKetThuc").toLocalDateTime());
                    llv.setTrangThai(rs.getString("TrangThai"));
                    llv.setTenCa(rs.getString("TenCa"));
                    lichLamViecs.add(llv); // Thêm vào danh sách
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lichLamViecs; // Trả về danh sách ca làm việc
    }



//update trạng thía của lịch làm việc
   public void updateTrangThai(String maLichLamViec, String trangThai) {
       try {
           String sql = "UPDATE LichLamViec SET TrangThai = ? WHERE MaLichLamViec = ?";
           PreparedStatement stm = con.prepareStatement(sql);
           stm.setString(1, trangThai);
            stm.setString(2, maLichLamViec);
            stm.executeUpdate();
            System.out.println("Cập nhật trạng thái thành công!");
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

}
