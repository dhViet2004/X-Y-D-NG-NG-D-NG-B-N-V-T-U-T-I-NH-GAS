/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Database.ConnectDatabase;
import Entity.LichTrinhTau;
import Entity.Tau;
import Entity.TuyenTau;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class DAO_TraCuuLichTrinh {
    private Connection con;

    public DAO_TraCuuLichTrinh() {
         this.con = ConnectDatabase.getConnection();
        if (this.con == null) {
            System.out.println("Connection is null. Please check the database connection.");
        } // Kết nối cơ sở dữ liệu
    }
    public List<String> getAllGaDi() {
            List<String> gaDiList = new ArrayList<>();
            String sql = "SELECT DISTINCT GaDi FROM TuyenTau"; // Truy vấn lấy tất cả Ga đi (không trùng lặp)

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    gaDiList.add(rs.getString("GaDi")); // Lấy giá trị cột ga_di và thêm vào danh sách
                }
            } catch (SQLException e) {
                System.err.println("Error while fetching Ga đi: " + e.getMessage());
            }

            return gaDiList;
        }
    public List<String> getAllDiaDiemDi() {
            List<String> gaDiList = new ArrayList<>();
            String sql = "SELECT DISTINCT DiaDiemDi FROM TuyenTau"; // Truy vấn lấy tất cả Ga đi (không trùng lặp)

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    gaDiList.add(rs.getString("DiaDiemDi")); // Lấy giá trị cột ga_di và thêm vào danh sách
                }
            } catch (SQLException e) {
                System.err.println("Error while fetching [DiaDiemDi]: " + e.getMessage());
            }

            return gaDiList;
        }
    public List<String> getAllDiaDiemDen() {
            List<String> gaDiList = new ArrayList<>();
            String sql = "SELECT DISTINCT DiaDiemDen FROM TuyenTau"; // Truy vấn lấy tất cả Ga đi (không trùng lặp)

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    gaDiList.add(rs.getString("DiaDiemDen")); // Lấy giá trị cột ga_di và thêm vào danh sách
                }
            } catch (SQLException e) {
                System.err.println("Error while fetching [DiaDiemDen]: " + e.getMessage());
            }

            return gaDiList;
        }
     public List<String> getAllGaDen() {
            List<String> gaDiList = new ArrayList<>();
            String sql = "SELECT DISTINCT GaDen FROM TuyenTau"; // Truy vấn lấy tất cả Ga đi (không trùng lặp)

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    gaDiList.add(rs.getString("GaDen")); // Lấy giá trị cột ga_di và thêm vào danh sách
                }
            } catch (SQLException e) {
                System.err.println("Error while fetching Ga đen: " + e.getMessage());
            }

            return gaDiList;
        }
      public List<String> getAllTau() {
            List<String> gaDiList = new ArrayList<>();
            String sql = "SELECT DISTINCT TenTau FROM Tau"; // Truy vấn lấy tất cả Ga đi (không trùng lặp)

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    gaDiList.add(rs.getString("TenTau")); // Lấy giá trị cột ga_di và thêm vào danh sách
                }
            } catch (SQLException e) {
                System.err.println("Error while fetching =Ten tau: " + e.getMessage());
            }

            return gaDiList;
        }
    public List<LichTrinhTau> getLichTrinh(String gaDi, String gaDen, String ngayDi, String gioTu, String gioDen, String tenTau, String trangThai) {
        List<LichTrinhTau> result = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
                "SELECT t.MaTau, t.TenTau, lt.MaLich, lt.NgayDi, lt.GioDi, tt.GaDi, tt.GaDen, lt.TrangThai " +
                        "FROM Tau t " +
                        "JOIN LichTrinhTau lt ON t.MaTau = lt.MaTau " +
                        "JOIN TuyenTau tt ON t.MaTuyen = tt.MaTuyen " +
                        "WHERE 1=1"
        ); // Sử dụng "1=1" để dễ dàng thêm các điều kiện tùy chọn.

        // Thêm các điều kiện tùy chọn
        if (gaDi != null && !gaDi.isEmpty()) {
            sql.append(" AND tt.GaDi = ?");
        }
        if (gaDen != null && !gaDen.isEmpty()) {
            sql.append(" AND tt.GaDen = ?");
        }
        if (ngayDi != null && !ngayDi.isEmpty()) {
            sql.append(" AND lt.NgayDi = ?");
        }
        if (gioTu != null && !gioTu.isEmpty()) {
            sql.append(" AND lt.GioDi >= ?");
        }
        if (gioDen != null && !gioDen.isEmpty()) {
            sql.append(" AND lt.GioDi <= ?");
        }
        if (tenTau != null && !tenTau.isEmpty()) {
            sql.append(" AND t.TenTau LIKE ?");
        }
        if (trangThai != null && !trangThai.isEmpty()) {
            sql.append(" AND lt.TrangThai = ?");
        }

        try (PreparedStatement ps = con.prepareStatement(sql.toString())) {
            int index = 1;

            // Gán giá trị cho các tham số tùy chọn
            if (gaDi != null && !gaDi.isEmpty()) {
                ps.setString(index++, gaDi);
            }
            if (gaDen != null && !gaDen.isEmpty()) {
                ps.setString(index++, gaDen);
            }
            if (ngayDi != null && !ngayDi.isEmpty()) {
                ps.setString(index++, java.sql.Date.valueOf(ngayDi).toString());
            }
            if (gioTu != null && !gioTu.isEmpty()) {
                gioTu = gioTu + ":00";  // Đảm bảo định dạng là HH:mm:ss
                ps.setString(index++, java.sql.Time.valueOf(gioTu).toString());
            }
            if (gioDen != null && !gioDen.isEmpty()) {
                gioDen = gioDen + ":00";  // Đảm bảo định dạng là HH:mm:ss
                ps.setString(index++, java.sql.Time.valueOf(gioDen).toString());
            }
            if (tenTau != null && !tenTau.isEmpty()) {
                ps.setString(index++, "%" + tenTau + "%");
            }
            if (trangThai != null && !trangThai.isEmpty()) {
                ps.setString(index++, trangThai);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LichTrinhTau lichTrinhTau = new LichTrinhTau();

                // Lấy dữ liệu từ ResultSet
                lichTrinhTau.setMaLichTrinh(rs.getString("MaLich"));
                lichTrinhTau.setNgayDi(rs.getDate("NgayDi").toLocalDate());
                lichTrinhTau.setGioDi(rs.getTime("GioDi").toLocalTime());
                lichTrinhTau.setTrangThai(rs.getString("TrangThai"));

                // Tạo đối tượng Tau và TuyenTau
                Tau tau = new Tau();
                tau.setMaTau(rs.getString("MaTau"));
                tau.setTenTau(rs.getString("TenTau"));
                TuyenTau tuyenTau = new TuyenTau();
                tuyenTau.setGaDi(rs.getString("GaDi"));
                tuyenTau.setGaDen(rs.getString("GaDen"));
                tau.setTuyenTau(tuyenTau);

                // Gán đối tượng Tau vào LichTrinhTau
                lichTrinhTau.setTau(tau);

                result.add(lichTrinhTau);
            }
        } catch (SQLException e) {
            System.err.println("Error while fetching schedule: " + e.getMessage());
        }

        return result;
    }




    }


