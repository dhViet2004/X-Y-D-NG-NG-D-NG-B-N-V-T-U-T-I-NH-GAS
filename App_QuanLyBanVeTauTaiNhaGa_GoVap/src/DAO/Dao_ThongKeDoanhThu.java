/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Database.ConnectDatabase;
import Entity.HoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author ASUS
 */
public class Dao_ThongKeDoanhThu {
     private Connection con;

    public Dao_ThongKeDoanhThu() {
         this.con = ConnectDatabase.getConnection();
        if (this.con == null) {
            System.out.println("Connection is null. Please check the database connection.");
        } // Kết nối cơ sở dữ liệu
    }
    public Map<String, Double> getTongTienTheoThu(String tuNgay, String denNgay) {
    Map<String, Double> tongTienTheoThu = new LinkedHashMap<>();

    String sql = "SELECT DATENAME(dw, hd.NgayHoaDon) AS Thu, " +
                 "SUM(hd.TongTien) AS TongTien, " +
                 "DATEPART(dw, hd.NgayHoaDon) AS SoThu " +
                 "FROM HoaDon hd " +
                 "WHERE hd.NgayHoaDon BETWEEN ? AND ? " +
                 "GROUP BY DATENAME(dw, hd.NgayHoaDon), DATEPART(dw, hd.NgayHoaDon) " +
                 "ORDER BY SoThu";

    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setDate(1, java.sql.Date.valueOf(tuNgay));
        ps.setDate(2, java.sql.Date.valueOf(denNgay));

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String thu = rs.getString("Thu");
            double tongTien = rs.getDouble("TongTien");
            tongTienTheoThu.put(thu, tongTien);
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi tính tổng tiền theo thứ: " + e.getMessage());
    }

    return tongTienTheoThu;
}


    public Map<String, Double> getTongTienTheoThang(String tuNgay, String denNgay) {
        Map<String, Double> tongTienTheoThang = new TreeMap<>(); // TreeMap để tự động sắp xếp theo thứ tự tháng
        String sql = "SELECT hd.NgayHoaDon, hd.TongTien " +
                     "FROM HoaDon hd " +
                     "WHERE hd.NgayHoaDon BETWEEN ? AND ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(tuNgay));
            ps.setDate(2, java.sql.Date.valueOf(denNgay));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LocalDateTime ngayHoaDon = rs.getTimestamp("NgayHoaDon").toLocalDateTime();
                double tongTien = rs.getDouble("TongTien");

                // Chỉ lấy tháng, không lấy năm
                String thang = String.format("%02d", ngayHoaDon.getMonthValue());
                tongTienTheoThang.put(thang, tongTienTheoThang.getOrDefault(thang, 0.0) + tongTien);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tính tổng tiền theo tháng: " + e.getMessage());
        }

        return tongTienTheoThang;
    }

    public Map<String, Double> getTongTienTheoNam(String tuNgay, String denNgay) {
    Map<String, Double> tongTienTheoNam = new TreeMap<>(); // TreeMap để tự động sắp xếp
    String sql = "SELECT hd.NgayHoaDon, hd.TongTien " +
                 "FROM HoaDon hd " +
                 "WHERE hd.NgayHoaDon BETWEEN ? AND ?";

    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setDate(1, java.sql.Date.valueOf(tuNgay));
        ps.setDate(2, java.sql.Date.valueOf(denNgay));

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            LocalDateTime ngayHoaDon = rs.getTimestamp("NgayHoaDon").toLocalDateTime();
            double tongTien = rs.getDouble("TongTien");

            String nam = String.valueOf(ngayHoaDon.getYear());
            tongTienTheoNam.put(nam, tongTienTheoNam.getOrDefault(nam, 0.0) + tongTien);
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi tính tổng tiền theo năm: " + e.getMessage());
    }

    return tongTienTheoNam;
}

    
    public Map<String, Double> getTongTienTheoGio(String tuNgay, String denNgay) {
    Map<String, Double> tongTienTheoGio = new TreeMap<>(); // TreeMap để tự động sắp xếp
    String sql = "SELECT hd.NgayHoaDon, hd.TongTien " +
                 "FROM HoaDon hd " +
                 "WHERE hd.NgayHoaDon BETWEEN ? AND ?";

    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setDate(1, java.sql.Date.valueOf(tuNgay));
        ps.setDate(2, java.sql.Date.valueOf(denNgay));

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            LocalDateTime ngayHoaDon = rs.getTimestamp("NgayHoaDon").toLocalDateTime();
            double tongTien = rs.getDouble("TongTien");

            String gio = String.format("%02d:00", ngayHoaDon.getHour());
            tongTienTheoGio.put(gio, tongTienTheoGio.getOrDefault(gio, 0.0) + tongTien);
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi tính tổng tiền theo giờ: " + e.getMessage());
    }

    return tongTienTheoGio;
}
     public Map<String, Double> getDoanhThuTheoTuyen(String tuNgay, String denNgay) {
        Map<String, Double> doanhThuTheoTuyen = new TreeMap<>();
        String sql = "SELECT t.MaTuyen, tt.TenTuyen, SUM(cth.ThanhTien) AS DoanhThu " +
                     "FROM HoaDon hd " +
                     "JOIN ChiTietHoaDon cth ON hd.MaHD = cth.MaHD " +
                     "JOIN VeTau vt ON cth.MaVe = vt.MaVe " +
                     "JOIN LichTrinhTau lt ON vt.LichTrinhTauMaLich = lt.MaLich " +
                     "JOIN Tau t ON lt.MaTau = t.MaTau " +
                     "JOIN TuyenTau tt ON t.MaTuyen = tt.MaTuyen " +
                     "WHERE hd.NgayHoaDon BETWEEN ? AND ? " +
                     "GROUP BY t.MaTuyen, tt.TenTuyen " +
                     "ORDER BY DoanhThu DESC";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            // Chuyển đổi chuỗi ngày tháng sang Date
            ps.setDate(1, java.sql.Date.valueOf(tuNgay));
            ps.setDate(2, java.sql.Date.valueOf(denNgay));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               
                String tenTuyen = rs.getString("TenTuyen");
                double doanhThu = rs.getDouble("DoanhThu");

                // Lưu doanh thu vào map theo mã tuyến
                doanhThuTheoTuyen.put(tenTuyen, doanhThu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doanhThuTheoTuyen;
    }
     public Map<String, Double> getDoanhThuTheoTau(String tuNgay, String denNgay) {
        Map<String, Double> doanhThuTheoTau = new TreeMap<>();
        String sql = "SELECT t.MaTau, tau.TenTau, SUM(cth.ThanhTien) AS DoanhThu " +
                     "FROM HoaDon hd " +
                     "JOIN ChiTietHoaDon cth ON hd.MaHD = cth.MaHD " +
                     "JOIN VeTau vt ON cth.MaVe = vt.MaVe " +
                     "JOIN LichTrinhTau lt ON vt.LichTrinhTauMaLich = lt.MaLich " +
                     "JOIN Tau tau ON lt.MaTau = tau.MaTau " +
                     "WHERE hd.NgayHoaDon BETWEEN ? AND ? " +
                     "GROUP BY t.MaTau, tau.TenTau " +
                     "ORDER BY DoanhThu DESC";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            // Chuyển đổi chuỗi ngày tháng sang Date
            ps.setDate(1, java.sql.Date.valueOf(tuNgay));
            ps.setDate(2, java.sql.Date.valueOf(denNgay));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maTau = rs.getString("MaTau");
                String tenTau = rs.getString("TenTau");
                double doanhThu = rs.getDouble("DoanhThu");

                // Lưu doanh thu vào map theo mã tàu
                doanhThuTheoTau.put(tenTau, doanhThu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doanhThuTheoTau;
    }
      public int countLichTrinhTrongKhoangThoiGian(String tuNgay, String denNgay) {
    String sql = "SELECT COUNT(*) AS SoLuongLichTrinh " +
                 "FROM LichTrinhTau " +
                 "WHERE NgayDi BETWEEN ? AND ? " +
                 "AND REPLACE(REPLACE(TrangThai, ' ', ''), N'đã hoàn thành', '') COLLATE SQL_Latin1_General_CP1_CI_AS = ''";  // Lọc theo trạng thái bỏ dấu và khoảng trắng

    int soLuongLichTrinh = 0;

    try (PreparedStatement ps = con.prepareStatement(sql)) {
        // Chuyển đổi tham số ngày thành Date
        ps.setDate(1, java.sql.Date.valueOf(tuNgay));
        ps.setDate(2, java.sql.Date.valueOf(denNgay));

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            soLuongLichTrinh = rs.getInt("SoLuongLichTrinh");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return soLuongLichTrinh;
}

       public double getDoanhThuTrongKhoangThoiGian(String tuNgay, String denNgay) {
        String sql = "SELECT SUM(hd.TongTien) AS DoanhThu " +
                     "FROM HoaDon hd " +
                     "WHERE hd.NgayHoaDon BETWEEN ? AND ?";
        double doanhThu = 0.0;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            // Chuyển đổi tham số ngày thành Date
            ps.setDate(1, java.sql.Date.valueOf(tuNgay));
            ps.setDate(2, java.sql.Date.valueOf(denNgay));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                doanhThu = rs.getDouble("DoanhThu");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doanhThu;
    }
       public int countHoaDonInRange(String tuNgay, String denNgay) {
        String sql = "SELECT COUNT(*) AS SoHoaDon FROM HoaDon WHERE NgayHoaDon BETWEEN ? AND ?";
        int soHoaDon = 0;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            // Thiết lập tham số ngày bắt đầu và kết thúc
            ps.setDate(1, java.sql.Date.valueOf(tuNgay));
            ps.setDate(2, java.sql.Date.valueOf(denNgay));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                soHoaDon = rs.getInt("SoHoaDon");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return soHoaDon;
    }
       public int countSoVeDaBanTrongThoiGian(String tuNgay, String denNgay) {
        String sql = "SELECT COUNT(*) AS TongSoVe " +
                     "FROM VeTau " +
                     "WHERE TrangThai COLLATE SQL_Latin1_General_CP1_CI_AS NOT IN ('đã đổi', 'đã trả') " +
                     "AND NgayDi BETWEEN ? AND ?";
        int tongSoVe = 0;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            // Chuyển đổi LocalDate thành java.sql.Date
            ps.setDate(1, java.sql.Date.valueOf(tuNgay));
            ps.setDate(2, java.sql.Date.valueOf(denNgay));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tongSoVe = rs.getInt("TongSoVe");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tongSoVe;
    }
    public List<Object[]> thongKeHoaDonTheoThang(String tuNgay, String denNgay) {
    List<Object[]> thongKeList = new ArrayList<>();

    // Bước 1: Lấy số lượng hóa đơn và tổng giá trị hóa đơn
    String sqlSoHoaDonVaGiaTriHoaDon = "SELECT YEAR(hd.NgayHoaDon) AS Nam, MONTH(hd.NgayHoaDon) AS Thang, " +
                                       "COUNT(hd.MaHD) AS SoHoaDon, SUM(hd.TongTien + hd.TienKhuyenMai) AS GiaTriHoaDon " +
                                       "FROM HoaDon hd " +
                                       "WHERE hd.NgayHoaDon BETWEEN ? AND ? " +
                                       "GROUP BY YEAR(hd.NgayHoaDon), MONTH(hd.NgayHoaDon) " +
                                       "ORDER BY Nam, Thang";

    // Bước 2: Lấy tổng doanh thu và tổng tiền khuyến mãi
    String sqlDoanhThuVaKhuyenMai = "SELECT YEAR(hd.NgayHoaDon) AS Nam, MONTH(hd.NgayHoaDon) AS Thang, " +
                                    "SUM(hd.TongTien) AS DoanhThu, SUM(hd.TienKhuyenMai) AS TongTienKhuyenMai " +
                                    "FROM HoaDon hd " +
                                    "WHERE hd.NgayHoaDon BETWEEN ? AND ? " +
                                    "GROUP BY YEAR(hd.NgayHoaDon), MONTH(hd.NgayHoaDon) " +
                                    "ORDER BY Nam, Thang";

    // Bước 3: Lấy số lượng vé đã bán và số lượng lịch trình
    String sqlSoVeDaBanVaSoLichTrinh = "SELECT YEAR(hd.NgayHoaDon) AS Nam, MONTH(hd.NgayHoaDon) AS Thang, " +
                                       "COUNT(vt.MaVe) AS SoVeDaBan, COUNT(DISTINCT lt.MaLich) AS SoLichTrinh " +
                                       "FROM HoaDon hd " +
                                       "JOIN ChiTietHoaDon cthd ON hd.MaHD = cthd.MaHD " +
                                       "JOIN VeTau vt ON cthd.MaVe = vt.MaVe " +
                                       "JOIN LichTrinhTau lt ON vt.LichTrinhTauMaLich = lt.MaLich " +
                                       "WHERE hd.NgayHoaDon BETWEEN ? AND ? " +
                                       "GROUP BY YEAR(hd.NgayHoaDon), MONTH(hd.NgayHoaDon) " +
                                       "ORDER BY Nam, Thang";

    try (PreparedStatement ps1 = con.prepareStatement(sqlSoHoaDonVaGiaTriHoaDon);
         PreparedStatement ps2 = con.prepareStatement(sqlDoanhThuVaKhuyenMai);
         PreparedStatement ps3 = con.prepareStatement(sqlSoVeDaBanVaSoLichTrinh)) {

        // Chuyển đổi chuỗi ngày tháng sang Date cho các câu truy vấn
        ps1.setDate(1, java.sql.Date.valueOf(tuNgay));
        ps1.setDate(2, java.sql.Date.valueOf(denNgay));
        
        ps2.setDate(1, java.sql.Date.valueOf(tuNgay));
        ps2.setDate(2, java.sql.Date.valueOf(denNgay));

        ps3.setDate(1, java.sql.Date.valueOf(tuNgay));
        ps3.setDate(2, java.sql.Date.valueOf(denNgay));

        // Thực thi câu truy vấn 1: Số hóa đơn và giá trị hóa đơn
        ResultSet rs1 = ps1.executeQuery();
        Map<String, Object[]> hoaDonMap = new HashMap<>();
        while (rs1.next()) {
            int nam = rs1.getInt("Nam");
            int thang = rs1.getInt("Thang");
            int soHoaDon = rs1.getInt("SoHoaDon");
            double giaTriHoaDon = rs1.getDouble("GiaTriHoaDon");

            // Khởi tạo mảng thongKe với đủ số lượng phần tử
            hoaDonMap.put(nam + "-" + thang, new Object[]{nam, thang, soHoaDon, giaTriHoaDon, 0.0, 0.0, 0, 0});
        }

        // Thực thi câu truy vấn 2: Doanh thu và tiền khuyến mãi
        ResultSet rs2 = ps2.executeQuery();
        while (rs2.next()) {
            int nam = rs2.getInt("Nam");
            int thang = rs2.getInt("Thang");
            double doanhThu = rs2.getDouble("DoanhThu");
            double tongTienKhuyenMai = rs2.getDouble("TongTienKhuyenMai");

            Object[] thongKe = hoaDonMap.get(nam + "-" + thang);
            if (thongKe != null) {
                thongKe[4] = doanhThu;  // Doanh thu
                thongKe[5] = tongTienKhuyenMai;  // Tiền khuyến mãi
            }
        }

        // Thực thi câu truy vấn 3: Số vé đã bán và số lịch trình
        ResultSet rs3 = ps3.executeQuery();
        while (rs3.next()) {
            int nam = rs3.getInt("Nam");
            int thang = rs3.getInt("Thang");
            int soVeDaBan = rs3.getInt("SoVeDaBan");
            int soLichTrinh = rs3.getInt("SoLichTrinh");

            Object[] thongKe = hoaDonMap.get(nam + "-" + thang);
            if (thongKe != null) {
                thongKe[6] = soVeDaBan;  // Số vé đã bán
                thongKe[7] = soLichTrinh;  // Số lịch trình
            }
        }

        // Lưu kết quả vào thongKeList
        thongKeList.addAll(hoaDonMap.values());

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return thongKeList;
}
public List<Object[]> thongKeHoaDonTheoThuTrongTuan(String tuNgay, String denNgay) {
    List<Object[]> thongKeList = new ArrayList<>();

    // Bước 1: Lấy số lượng hóa đơn và tổng giá trị hóa đơn theo thứ trong tuần
    String sqlSoHoaDonVaGiaTriHoaDon = "SELECT DATEPART(weekday, hd.NgayHoaDon) AS Thu, " +
                                       "COUNT(hd.MaHD) AS SoHoaDon, SUM(hd.TongTien + hd.TienKhuyenMai) AS GiaTriHoaDon " +
                                       "FROM HoaDon hd " +
                                       "WHERE hd.NgayHoaDon BETWEEN ? AND ? " +
                                       "GROUP BY DATEPART(weekday, hd.NgayHoaDon) " +
                                       "ORDER BY Thu";

    // Bước 2: Lấy tổng doanh thu và tổng tiền khuyến mãi theo thứ trong tuần
    String sqlDoanhThuVaKhuyenMai = "SELECT DATEPART(weekday, hd.NgayHoaDon) AS Thu, " +
                                    "SUM(hd.TongTien) AS DoanhThu, SUM(hd.TienKhuyenMai) AS TongTienKhuyenMai " +
                                    "FROM HoaDon hd " +
                                    "WHERE hd.NgayHoaDon BETWEEN ? AND ? " +
                                    "GROUP BY DATEPART(weekday, hd.NgayHoaDon) " +
                                    "ORDER BY Thu";

    // Bước 3: Lấy số lượng vé đã bán và số lượng lịch trình theo thứ trong tuần
    String sqlSoVeDaBanVaSoLichTrinh = "SELECT DATEPART(weekday, hd.NgayHoaDon) AS Thu, " +
                                       "COUNT(vt.MaVe) AS SoVeDaBan, COUNT(DISTINCT lt.MaLich) AS SoLichTrinh " +
                                       "FROM HoaDon hd " +
                                       "JOIN ChiTietHoaDon cthd ON hd.MaHD = cthd.MaHD " +
                                       "JOIN VeTau vt ON cthd.MaVe = vt.MaVe " +
                                       "JOIN LichTrinhTau lt ON vt.LichTrinhTauMaLich = lt.MaLich " +
                                       "WHERE hd.NgayHoaDon BETWEEN ? AND ? " +
                                       "GROUP BY DATEPART(weekday, hd.NgayHoaDon) " +
                                       "ORDER BY Thu";

    try (PreparedStatement ps1 = con.prepareStatement(sqlSoHoaDonVaGiaTriHoaDon);
         PreparedStatement ps2 = con.prepareStatement(sqlDoanhThuVaKhuyenMai);
         PreparedStatement ps3 = con.prepareStatement(sqlSoVeDaBanVaSoLichTrinh)) {

        // Chuyển đổi chuỗi ngày tháng sang Date cho các câu truy vấn
        ps1.setDate(1, java.sql.Date.valueOf(tuNgay));
        ps1.setDate(2, java.sql.Date.valueOf(denNgay));
        
        ps2.setDate(1, java.sql.Date.valueOf(tuNgay));
        ps2.setDate(2, java.sql.Date.valueOf(denNgay));

        ps3.setDate(1, java.sql.Date.valueOf(tuNgay));
        ps3.setDate(2, java.sql.Date.valueOf(denNgay));

        // Thực thi câu truy vấn 1: Số hóa đơn và giá trị hóa đơn
        ResultSet rs1 = ps1.executeQuery();
        Map<Integer, Object[]> hoaDonMap = new HashMap<>();
        while (rs1.next()) {
            int thu = rs1.getInt("Thu");
            int soHoaDon = rs1.getInt("SoHoaDon");
            double giaTriHoaDon = rs1.getDouble("GiaTriHoaDon");

            // Khởi tạo mảng thongKe với đủ số lượng phần tử
            hoaDonMap.put(thu, new Object[]{thu, soHoaDon, giaTriHoaDon, 0.0, 0.0, 0, 0});
        }

        // Thực thi câu truy vấn 2: Doanh thu và tiền khuyến mãi
        ResultSet rs2 = ps2.executeQuery();
        while (rs2.next()) {
            int thu = rs2.getInt("Thu");
            double doanhThu = rs2.getDouble("DoanhThu");
            double tongTienKhuyenMai = rs2.getDouble("TongTienKhuyenMai");

            Object[] thongKe = hoaDonMap.get(thu);
            if (thongKe != null) {
                thongKe[3] = doanhThu;  // Doanh thu
                thongKe[4] = tongTienKhuyenMai;  // Tiền khuyến mãi
            }
        }

        // Thực thi câu truy vấn 3: Số vé đã bán và số lịch trình
        ResultSet rs3 = ps3.executeQuery();
        while (rs3.next()) {
            int thu = rs3.getInt("Thu");
            int soVeDaBan = rs3.getInt("SoVeDaBan");
            int soLichTrinh = rs3.getInt("SoLichTrinh");

            Object[] thongKe = hoaDonMap.get(thu);
            if (thongKe != null) {
                thongKe[5] = soVeDaBan;  // Số vé đã bán
                thongKe[6] = soLichTrinh;  // Số lịch trình
            }
        }

        // Lưu kết quả vào thongKeList
        thongKeList.addAll(hoaDonMap.values());

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return thongKeList;
}
public List<Object[]> thongKeHoaDonTheoNam(String tuNgay, String denNgay) {
    List<Object[]> thongKeList = new ArrayList<>();

    // Bước 1: Lấy số lượng hóa đơn và tổng giá trị hóa đơn theo năm
    String sqlSoHoaDonVaGiaTriHoaDon = "SELECT YEAR(hd.NgayHoaDon) AS Nam, " +
                                       "COUNT(hd.MaHD) AS SoHoaDon, SUM(hd.TongTien + hd.TienKhuyenMai) AS GiaTriHoaDon " +
                                       "FROM HoaDon hd " +
                                       "WHERE hd.NgayHoaDon BETWEEN ? AND ? " +
                                       "GROUP BY YEAR(hd.NgayHoaDon) " +
                                       "ORDER BY Nam";

    // Bước 2: Lấy tổng doanh thu và tổng tiền khuyến mãi theo năm
    String sqlDoanhThuVaKhuyenMai = "SELECT YEAR(hd.NgayHoaDon) AS Nam, " +
                                    "SUM(hd.TongTien) AS DoanhThu, SUM(hd.TienKhuyenMai) AS TongTienKhuyenMai " +
                                    "FROM HoaDon hd " +
                                    "WHERE hd.NgayHoaDon BETWEEN ? AND ? " +
                                    "GROUP BY YEAR(hd.NgayHoaDon) " +
                                    "ORDER BY Nam";

    // Bước 3: Lấy số lượng vé đã bán và số lượng lịch trình theo năm
    String sqlSoVeDaBanVaSoLichTrinh = "SELECT YEAR(hd.NgayHoaDon) AS Nam, " +
                                       "COUNT(vt.MaVe) AS SoVeDaBan, COUNT(DISTINCT lt.MaLich) AS SoLichTrinh " +
                                       "FROM HoaDon hd " +
                                       "JOIN ChiTietHoaDon cthd ON hd.MaHD = cthd.MaHD " +
                                       "JOIN VeTau vt ON cthd.MaVe = vt.MaVe " +
                                       "JOIN LichTrinhTau lt ON vt.LichTrinhTauMaLich = lt.MaLich " +
                                       "WHERE hd.NgayHoaDon BETWEEN ? AND ? " +
                                       "GROUP BY YEAR(hd.NgayHoaDon) " +
                                       "ORDER BY Nam";

    try (PreparedStatement ps1 = con.prepareStatement(sqlSoHoaDonVaGiaTriHoaDon);
         PreparedStatement ps2 = con.prepareStatement(sqlDoanhThuVaKhuyenMai);
         PreparedStatement ps3 = con.prepareStatement(sqlSoVeDaBanVaSoLichTrinh)) {

        // Chuyển đổi chuỗi ngày tháng sang Date cho các câu truy vấn
        ps1.setDate(1, java.sql.Date.valueOf(tuNgay));
        ps1.setDate(2, java.sql.Date.valueOf(denNgay));
        
        ps2.setDate(1, java.sql.Date.valueOf(tuNgay));
        ps2.setDate(2, java.sql.Date.valueOf(denNgay));

        ps3.setDate(1, java.sql.Date.valueOf(tuNgay));
        ps3.setDate(2, java.sql.Date.valueOf(denNgay));

        // Thực thi câu truy vấn 1: Số hóa đơn và giá trị hóa đơn
        ResultSet rs1 = ps1.executeQuery();
        Map<Integer, Object[]> hoaDonMap = new HashMap<>();
        while (rs1.next()) {
            int nam = rs1.getInt("Nam");
            int soHoaDon = rs1.getInt("SoHoaDon");
            double giaTriHoaDon = rs1.getDouble("GiaTriHoaDon");

            // Khởi tạo mảng thongKe với đủ số lượng phần tử
            hoaDonMap.put(nam, new Object[]{nam, soHoaDon, giaTriHoaDon, 0.0, 0.0, 0, 0});
        }

        // Thực thi câu truy vấn 2: Doanh thu và tiền khuyến mãi
        ResultSet rs2 = ps2.executeQuery();
        while (rs2.next()) {
            int nam = rs2.getInt("Nam");
            double doanhThu = rs2.getDouble("DoanhThu");
            double tongTienKhuyenMai = rs2.getDouble("TongTienKhuyenMai");

            Object[] thongKe = hoaDonMap.get(nam);
            if (thongKe != null) {
                thongKe[3] = doanhThu;  // Doanh thu
                thongKe[4] = tongTienKhuyenMai;  // Tiền khuyến mãi
            }
        }

        // Thực thi câu truy vấn 3: Số vé đã bán và số lịch trình
        ResultSet rs3 = ps3.executeQuery();
        while (rs3.next()) {
            int nam = rs3.getInt("Nam");
            int soVeDaBan = rs3.getInt("SoVeDaBan");
            int soLichTrinh = rs3.getInt("SoLichTrinh");

            Object[] thongKe = hoaDonMap.get(nam);
            if (thongKe != null) {
                thongKe[5] = soVeDaBan;  // Số vé đã bán
                thongKe[6] = soLichTrinh;  // Số lịch trình
            }
        }

        // Lưu kết quả vào thongKeList
        thongKeList.addAll(hoaDonMap.values());

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return thongKeList;
}
public List<Object[]> thongKeHoaDonTheoGio(String tuNgay, String denNgay) {
    List<Object[]> thongKeList = new ArrayList<>();

    // Bước 1: Lấy số lượng hóa đơn và tổng giá trị hóa đơn theo giờ
    String sqlSoHoaDonVaGiaTriHoaDon = "SELECT DATEPART(HOUR, hd.NgayHoaDon) AS Gio, " +
                                       "COUNT(hd.MaHD) AS SoHoaDon, SUM(hd.TongTien + hd.TienKhuyenMai) AS GiaTriHoaDon " +
                                       "FROM HoaDon hd " +
                                       "WHERE hd.NgayHoaDon BETWEEN ? AND ? " +
                                       "GROUP BY DATEPART(HOUR, hd.NgayHoaDon) " +
                                       "ORDER BY Gio";

    // Bước 2: Lấy tổng doanh thu và tổng tiền khuyến mãi theo giờ
    String sqlDoanhThuVaKhuyenMai = "SELECT DATEPART(HOUR, hd.NgayHoaDon) AS Gio, " +
                                    "SUM(hd.TongTien) AS DoanhThu, SUM(hd.TienKhuyenMai) AS TongTienKhuyenMai " +
                                    "FROM HoaDon hd " +
                                    "WHERE hd.NgayHoaDon BETWEEN ? AND ? " +
                                    "GROUP BY DATEPART(HOUR, hd.NgayHoaDon) " +
                                    "ORDER BY Gio";

    // Bước 3: Lấy số lượng vé đã bán và số lượng lịch trình theo giờ
    String sqlSoVeDaBanVaSoLichTrinh = "SELECT DATEPART(HOUR, hd.NgayHoaDon) AS Gio, " +
                                       "COUNT(vt.MaVe) AS SoVeDaBan, COUNT(DISTINCT lt.MaLich) AS SoLichTrinh " +
                                       "FROM HoaDon hd " +
                                       "JOIN ChiTietHoaDon cthd ON hd.MaHD = cthd.MaHD " +
                                       "JOIN VeTau vt ON cthd.MaVe = vt.MaVe " +
                                       "JOIN LichTrinhTau lt ON vt.LichTrinhTauMaLich = lt.MaLich " +
                                       "WHERE hd.NgayHoaDon BETWEEN ? AND ? " +
                                       "GROUP BY DATEPART(HOUR, hd.NgayHoaDon) " +
                                       "ORDER BY Gio";

    try (PreparedStatement ps1 = con.prepareStatement(sqlSoHoaDonVaGiaTriHoaDon);
         PreparedStatement ps2 = con.prepareStatement(sqlDoanhThuVaKhuyenMai);
         PreparedStatement ps3 = con.prepareStatement(sqlSoVeDaBanVaSoLichTrinh)) {

        // Chuyển đổi chuỗi ngày tháng sang Date cho các câu truy vấn
        ps1.setDate(1, java.sql.Date.valueOf(tuNgay));
        ps1.setDate(2, java.sql.Date.valueOf(denNgay));
        
        ps2.setDate(1, java.sql.Date.valueOf(tuNgay));
        ps2.setDate(2, java.sql.Date.valueOf(denNgay));

        ps3.setDate(1, java.sql.Date.valueOf(tuNgay));
        ps3.setDate(2, java.sql.Date.valueOf(denNgay));

        // Thực thi câu truy vấn 1: Số hóa đơn và giá trị hóa đơn theo giờ
        ResultSet rs1 = ps1.executeQuery();
        Map<Integer, Object[]> hoaDonMap = new HashMap<>();
        while (rs1.next()) {
            int gio = rs1.getInt("Gio");
            int soHoaDon = rs1.getInt("SoHoaDon");
            double giaTriHoaDon = rs1.getDouble("GiaTriHoaDon");

            // Khởi tạo mảng thongKe với đủ số lượng phần tử
            hoaDonMap.put(gio, new Object[]{gio, soHoaDon, giaTriHoaDon, 0.0, 0.0, 0, 0});
        }

        // Thực thi câu truy vấn 2: Doanh thu và tiền khuyến mãi theo giờ
        ResultSet rs2 = ps2.executeQuery();
        while (rs2.next()) {
            int gio = rs2.getInt("Gio");
            double doanhThu = rs2.getDouble("DoanhThu");
            double tongTienKhuyenMai = rs2.getDouble("TongTienKhuyenMai");

            Object[] thongKe = hoaDonMap.get(gio);
            if (thongKe != null) {
                thongKe[3] = doanhThu;  // Doanh thu
                thongKe[4] = tongTienKhuyenMai;  // Tiền khuyến mãi
            }
        }

        // Thực thi câu truy vấn 3: Số vé đã bán và số lịch trình theo giờ
        ResultSet rs3 = ps3.executeQuery();
        while (rs3.next()) {
            int gio = rs3.getInt("Gio");
            int soVeDaBan = rs3.getInt("SoVeDaBan");
            int soLichTrinh = rs3.getInt("SoLichTrinh");

            Object[] thongKe = hoaDonMap.get(gio);
            if (thongKe != null) {
                thongKe[5] = soVeDaBan;  // Số vé đã bán
                thongKe[6] = soLichTrinh;  // Số lịch trình
            }
        }

        // Lưu kết quả vào thongKeList
        thongKeList.addAll(hoaDonMap.values());

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return thongKeList;
}
  public List<Object[]> thongKeDoanhThuTheoTuyen(String tuNgay, String denNgay) {
    List<Object[]> resultList = new ArrayList<>();

    // Câu truy vấn SQL để lấy doanh thu theo tuyến, số lịch trình đã chạy và số vé đã bán
    String sql = "SELECT t.MaTuyen, tt.TenTuyen, SUM(cth.ThanhTien) AS DoanhThu, " +
                 "COUNT(DISTINCT lt.MaLich) AS SoLichTrinh, " +
                 "COUNT(CASE WHEN vt.TrangThai NOT IN ('Đã đổi', 'Đã trả') THEN 1 END) AS SoVeDaBan " +
                 "FROM HoaDon hd " +
                 "JOIN ChiTietHoaDon cth ON hd.MaHD = cth.MaHD " +
                 "JOIN VeTau vt ON cth.MaVe = vt.MaVe " +
                 "JOIN LichTrinhTau lt ON vt.LichTrinhTauMaLich = lt.MaLich " +
                 "JOIN Tau t ON lt.MaTau = t.MaTau " +
                 "JOIN TuyenTau tt ON t.MaTuyen = tt.MaTuyen " +
                 "WHERE hd.NgayHoaDon BETWEEN ? AND ? " +
                 "GROUP BY t.MaTuyen, tt.TenTuyen " +
                 "ORDER BY DoanhThu DESC";

    try (PreparedStatement ps = con.prepareStatement(sql)) {
        // Chuyển đổi chuỗi ngày tháng sang Date cho các câu truy vấn
        ps.setDate(1, java.sql.Date.valueOf(tuNgay));
        ps.setDate(2, java.sql.Date.valueOf(denNgay));

        // Thực thi câu truy vấn và lấy kết quả
        ResultSet rs = ps.executeQuery();

        // Duyệt qua các dòng kết quả và thêm vào danh sách
        while (rs.next()) {
            // Lưu từng dòng kết quả vào mảng Object[]
            Object[] result = new Object[5];
            result[0] = rs.getString("MaTuyen"); // Mã Tuyến
            result[1] = rs.getString("TenTuyen"); // Tên Tuyến
            result[2] = rs.getDouble("DoanhThu"); // Doanh Thu
            result[3] = rs.getInt("SoLichTrinh"); // Số Lịch Trình
            result[4] = rs.getInt("SoVeDaBan"); // Số Vé Đã Bán

            // Thêm mảng vào danh sách kết quả
            resultList.add(result);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return resultList;
}

    
    public static void main(String[] args) {
         ConnectDatabase.getInstance().connect();
        Dao_ThongKeDoanhThu hoaDonDAO = new Dao_ThongKeDoanhThu();

       
              
                
              
             
    }
}
