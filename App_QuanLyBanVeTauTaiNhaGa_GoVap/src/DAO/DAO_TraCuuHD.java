package DAO;

import Database.ConnectDatabase;
import Entity.*;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DAO_TraCuuHD {
    private Connection con;

    public DAO_TraCuuHD() {
        con = ConnectDatabase.getConnection();
    }

    // Tra cứu hóa đơn theo mã hóa đơn
public List<HoaDon> timHoaDonTheoMaHD(String maHD) {
    List<HoaDon> hoaDons = new ArrayList<>();  // Khởi tạo danh sách HoaDon
    try {
        String sql = "SELECT HoaDon.MaHD, HoaDon.MaKH, HoaDon.KhuyenMaiMaKM, HoaDon.NhanVienMaNV, " +
                     "HoaDon.MaLoai, HoaDon.NgayHoaDon, HoaDon.TienKhuyenMai, HoaDon.TongTien, " +
                     "KhachHang.TenKH, KhachHang.CCCD, KhachHang.SoDT, KhachHang.DiaChi " +
                     "FROM HoaDon " +
                     "JOIN KhachHang ON HoaDon.MaKH = KhachHang.MaKH " +
                     "WHERE HoaDon.MaHD = ?";  // Truy vấn theo mã hóa đơn

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, maHD);  // Set giá trị cho mã hóa đơn

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {  // Duyệt qua kết quả trả về từ database
                String maHDResult = rs.getString("MaHD");
                String maKH = rs.getString("MaKH");
                String khuyenMaiMaKM = rs.getString("KhuyenMaiMaKM");
                String nhanVienMaNV = rs.getString("NhanVienMaNV");
                String maLoai = rs.getString("MaLoai");

                Timestamp ngayHoaDonTimestamp = rs.getTimestamp("NgayHoaDon");
                LocalDateTime ngayLap = ngayHoaDonTimestamp.toLocalDateTime();  // Chuyển đổi thành LocalDateTime

                double tienKhuyenMai = rs.getDouble("TienKhuyenMai");
                double tongTien = rs.getDouble("TongTien");

                String tenKH = rs.getString("TenKH");
                String cccd = rs.getString("CCCD");
                String sdt = rs.getString("SoDT");
                String diaChi = rs.getString("DiaChi");

                KhachHang khachHang = new KhachHang(maKH, tenKH, cccd, sdt, diaChi);
                HoaDon hoaDon = new HoaDon(maHDResult, khachHang, new KhuyenMai(khuyenMaiMaKM), new NhanVien(), new LoaiHoaDon(maLoai), ngayLap, tienKhuyenMai, tongTien);

                hoaDons.add(hoaDon);  // Thêm hóa đơn vào danh sách
            }
            if (hoaDons.isEmpty()) {
                System.out.println("Không tìm thấy hóa đơn với mã: " + maHD);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // In lỗi nếu có
    }
    return hoaDons;  // Trả về danh sách hóa đơn
}


    // Tra cứu hóa đơn theo mã khuyến mãi
public List<HoaDon> timHoaDonTheoMaKM(String maKM) {
    List<HoaDon> hoaDons = new ArrayList<>();  // Danh sách các hóa đơn trả về
    try {
        String sql = "SELECT HoaDon.MaHD, HoaDon.MaKH, HoaDon.KhuyenMaiMaKM, HoaDon.NhanVienMaNV, " +
                     "HoaDon.MaLoai, HoaDon.NgayHoaDon, HoaDon.TienKhuyenMai, HoaDon.TongTien, " +
                     "KhachHang.TenKH, KhachHang.CCCD, KhachHang.SoDT, KhachHang.DiaChi " +
                     "FROM HoaDon " +
                     "JOIN KhachHang ON HoaDon.MaKH = KhachHang.MaKH " +
                     "WHERE HoaDon.KhuyenMaiMaKM = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, maKM);  // Set giá trị cho mã khuyến mãi

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {  // Duyệt qua tất cả kết quả trả về
                String maHDResult = rs.getString("MaHD");
                String maKH = rs.getString("MaKH");
                String khuyenMaiMaKM = rs.getString("KhuyenMaiMaKM");
                String nhanVienMaNV = rs.getString("NhanVienMaNV");
                String maLoai = rs.getString("MaLoai");

                Timestamp ngayHoaDonTimestamp = rs.getTimestamp("NgayHoaDon");
                LocalDateTime ngayLap = ngayHoaDonTimestamp.toLocalDateTime();  // Chuyển đổi thành LocalDateTime

                double tienKhuyenMai = rs.getDouble("TienKhuyenMai");
                double tongTien = rs.getDouble("TongTien");

                String tenKH = rs.getString("TenKH");
                String cccd = rs.getString("CCCD");
                String sdt = rs.getString("SoDT");
                String diaChi = rs.getString("DiaChi");

                KhachHang khachHang = new KhachHang(maKH, tenKH, cccd, sdt, diaChi);
                HoaDon hoaDon = new HoaDon(maHDResult, khachHang, new KhuyenMai(khuyenMaiMaKM), new NhanVien(), new LoaiHoaDon(maLoai), ngayLap, tienKhuyenMai, tongTien);
                
                hoaDons.add(hoaDon);  // Thêm hóa đơn vào danh sách
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // In lỗi nếu có
    }
    return hoaDons;  // Trả về danh sách hóa đơn
}


    // Tra cứu hóa đơn theo mã loại hóa đơn
public List<HoaDon> timHoaDonTheoMaLHD(String maLoaiHD) {
    List<HoaDon> hoaDons = new ArrayList<>();  // Danh sách các hóa đơn trả về
    try {
        String sql = "SELECT HoaDon.MaHD, HoaDon.MaKH, HoaDon.KhuyenMaiMaKM, HoaDon.NhanVienMaNV, " +
                     "HoaDon.MaLoai, HoaDon.NgayHoaDon, HoaDon.TienKhuyenMai, HoaDon.TongTien, " +
                     "KhachHang.TenKH, KhachHang.CCCD, KhachHang.SoDT, KhachHang.DiaChi " +
                     "FROM HoaDon " +
                     "JOIN KhachHang ON HoaDon.MaKH = KhachHang.MaKH " +
                     "WHERE HoaDon.MaLoai = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, maLoaiHD);  // Set giá trị cho mã hóa đơn

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {  // Duyệt qua tất cả kết quả trả về
                String maHDResult = rs.getString("MaHD");
                String maKH = rs.getString("MaKH");
                String khuyenMaiMaKM = rs.getString("KhuyenMaiMaKM");
                String nhanVienMaNV = rs.getString("NhanVienMaNV");
                String maLoai = rs.getString("MaLoai");

                Timestamp ngayHoaDonTimestamp = rs.getTimestamp("NgayHoaDon");
                LocalDateTime ngayLap = ngayHoaDonTimestamp.toLocalDateTime();  // Chuyển đổi thành LocalDateTime

                double tienKhuyenMai = rs.getDouble("TienKhuyenMai");
                double tongTien = rs.getDouble("TongTien");

                String tenKH = rs.getString("TenKH");
                String cccd = rs.getString("CCCD");
                String sdt = rs.getString("SoDT");
                String diaChi = rs.getString("DiaChi");

                KhachHang khachHang = new KhachHang(maKH, tenKH, cccd, sdt, diaChi);
                HoaDon hoaDon = new HoaDon(maHDResult, khachHang, new KhuyenMai(khuyenMaiMaKM), new NhanVien(), new LoaiHoaDon(maLoai), ngayLap, tienKhuyenMai, tongTien);
                
                hoaDons.add(hoaDon);  // Thêm hóa đơn vào danh sách
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // In lỗi nếu có
    }
    return hoaDons;  // Trả về danh sách hóa đơn
}


    // Tra cứu hóa đơn theo cccd của khách hàng
public List<HoaDon> timHoaDonTheoCCCD(String cccd) {
    List<HoaDon> hoaDonList = new ArrayList<>();
    try {
        String sql = "SELECT HoaDon.MaHD, HoaDon.MaKH, HoaDon.KhuyenMaiMaKM, HoaDon.NhanVienMaNV, " +
                     "HoaDon.MaLoai, HoaDon.NgayHoaDon, HoaDon.TienKhuyenMai, HoaDon.TongTien, " +
                     "KhachHang.TenKH, KhachHang.CCCD, KhachHang.SoDT, KhachHang.DiaChi " +
                     "FROM HoaDon " +
                     "JOIN KhachHang ON HoaDon.MaKH = KhachHang.MaKH " +
                     "WHERE KhachHang.CCCD = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, cccd);  // Set giá trị cho CCCD của khách hàng

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String maHD = rs.getString("MaHD");
                String maKH = rs.getString("MaKH");
                String khuyenMaiMaKM = rs.getString("KhuyenMaiMaKM");
                String nhanVienMaNV = rs.getString("NhanVienMaNV");
                String maLoai = rs.getString("MaLoai");

                Timestamp ngayHoaDonTimestamp = rs.getTimestamp("NgayHoaDon");
                LocalDateTime ngayLap = ngayHoaDonTimestamp.toLocalDateTime();  // Chuyển đổi thành LocalDateTime

                double tienKhuyenMai = rs.getDouble("TienKhuyenMai");
                double tongTien = rs.getDouble("TongTien");

                String tenKH = rs.getString("TenKH");
                String cccdKhachHang = rs.getString("CCCD");
                String sdt = rs.getString("SoDT");
                String diaChi = rs.getString("DiaChi");

                KhachHang khachHang = new KhachHang(maKH, tenKH, cccdKhachHang, sdt, diaChi);
                HoaDon hoaDon = new HoaDon(maHD, khachHang, new KhuyenMai(khuyenMaiMaKM), new NhanVien(), new LoaiHoaDon(maLoai), ngayLap, tienKhuyenMai, tongTien);
                hoaDonList.add(hoaDon); // Thêm vào danh sách hóa đơn
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // In lỗi nếu có
    }
    return hoaDonList;
}

    // Tra cứu hóa đơn theo khoảng thời gian
public List<HoaDon> timHoaDonTheoKhoangThoiGian(LocalDate startDate, LocalDate endDate, double minCost, double maxCost) {
    List<HoaDon> hoaDonList = new ArrayList<>();
    try {
        // Chuyển LocalDate thành LocalDateTime (với giờ là 00:00:00)
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);  // Để bao gồm toàn bộ ngày cuối

        // Cập nhật câu truy vấn SQL với điều kiện lọc theo giá
        String sql = "SELECT HoaDon.MaHD, HoaDon.MaKH, HoaDon.KhuyenMaiMaKM, HoaDon.NhanVienMaNV, " +
                     "HoaDon.MaLoai, HoaDon.NgayHoaDon, HoaDon.TienKhuyenMai, HoaDon.TongTien, " +
                     "KhachHang.TenKH, KhachHang.CCCD, KhachHang.SoDT, KhachHang.DiaChi " +
                     "FROM HoaDon " +
                     "JOIN KhachHang ON HoaDon.MaKH = KhachHang.MaKH " +
                     "WHERE HoaDon.NgayHoaDon BETWEEN ? AND ? " +
                     "AND HoaDon.TongTien >= ? AND HoaDon.TongTien <= ?"; // Lọc theo giá

        // Tạo PreparedStatement và truyền các tham số vào
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setTimestamp(1, Timestamp.valueOf(startDateTime));  // Set giá trị cho ngày bắt đầu
        stmt.setTimestamp(2, Timestamp.valueOf(endDateTime));    // Set giá trị cho ngày kết thúc
        stmt.setDouble(3, minCost);  // Set giá trị cho mức giá tối thiểu
        stmt.setDouble(4, maxCost);  // Set giá trị cho mức giá tối đa

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Lấy các trường dữ liệu từ ResultSet
                String maHD = rs.getString("MaHD");
                String maKH = rs.getString("MaKH");
                String khuyenMaiMaKM = rs.getString("KhuyenMaiMaKM");
                String nhanVienMaNV = rs.getString("NhanVienMaNV");
                String maLoai = rs.getString("MaLoai");

                Timestamp ngayHoaDonTimestamp = rs.getTimestamp("NgayHoaDon");
                LocalDateTime ngayLap = ngayHoaDonTimestamp.toLocalDateTime();  // Chuyển đổi thành LocalDateTime

                double tienKhuyenMai = rs.getDouble("TienKhuyenMai");
                double tongTien = rs.getDouble("TongTien");

                String tenKH = rs.getString("TenKH");
                String cccd = rs.getString("CCCD");
                String sdt = rs.getString("SoDT");
                String diaChi = rs.getString("DiaChi");

                // Tạo đối tượng KhachHang và HoaDon từ các dữ liệu lấy được
                KhachHang khachHang = new KhachHang(maKH, tenKH, cccd, sdt, diaChi);
                HoaDon hoaDon = new HoaDon(maHD, khachHang, new KhuyenMai(khuyenMaiMaKM), new NhanVien(), new LoaiHoaDon(maLoai), ngayLap, tienKhuyenMai, tongTien);
                
                // Thêm hóa đơn vào danh sách
                hoaDonList.add(hoaDon);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // In lỗi nếu có
    }
    return hoaDonList;
}


    // Tra cứu hóa đơn trong ngày hôm nay
public List<HoaDon> timHoaDonTheoNgayHienTai() {
    List<HoaDon> hoaDonList = new ArrayList<>();
    try {
        // Lấy ngày hiện tại và chuyển thành LocalDateTime với giờ 00:00:00 đến 23:59:59
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();  // 00:00:00 của ngày hôm nay
        LocalDateTime endOfDay = today.atTime(23, 59, 59);  // 23:59:59 của ngày hôm nay

        // Cập nhật câu truy vấn SQL để lọc hóa đơn của ngày hôm nay
        String sql = "SELECT HoaDon.MaHD, HoaDon.MaKH, HoaDon.KhuyenMaiMaKM, HoaDon.NhanVienMaNV, " +
                     "HoaDon.MaLoai, HoaDon.NgayHoaDon, HoaDon.TienKhuyenMai, HoaDon.TongTien, " +
                     "KhachHang.TenKH, KhachHang.CCCD, KhachHang.SoDT, KhachHang.DiaChi " +
                     "FROM HoaDon " +
                     "JOIN KhachHang ON HoaDon.MaKH = KhachHang.MaKH " +
                     "WHERE HoaDon.NgayHoaDon BETWEEN ? AND ?";  // Lọc hóa đơn trong ngày hôm nay

        // Tạo PreparedStatement và truyền các tham số vào
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setTimestamp(1, Timestamp.valueOf(startOfDay));  // Set giá trị cho 00:00:00
        stmt.setTimestamp(2, Timestamp.valueOf(endOfDay));    // Set giá trị cho 23:59:59

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Lấy các trường dữ liệu từ ResultSet
                String maHD = rs.getString("MaHD");
                String maKH = rs.getString("MaKH");
                String khuyenMaiMaKM = rs.getString("KhuyenMaiMaKM");
                String nhanVienMaNV = rs.getString("NhanVienMaNV");
                String maLoai = rs.getString("MaLoai");

                Timestamp ngayHoaDonTimestamp = rs.getTimestamp("NgayHoaDon");
                LocalDateTime ngayLap = ngayHoaDonTimestamp.toLocalDateTime();  // Chuyển đổi thành LocalDateTime

                double tienKhuyenMai = rs.getDouble("TienKhuyenMai");
                double tongTien = rs.getDouble("TongTien");

                String tenKH = rs.getString("TenKH");
                String cccd = rs.getString("CCCD");
                String sdt = rs.getString("SoDT");
                String diaChi = rs.getString("DiaChi");

                // Tạo đối tượng KhachHang và HoaDon từ các dữ liệu lấy được
                KhachHang khachHang = new KhachHang(maKH, tenKH, cccd, sdt, diaChi);
                HoaDon hoaDon = new HoaDon(maHD, khachHang, new KhuyenMai(khuyenMaiMaKM), new NhanVien(), new LoaiHoaDon(maLoai), ngayLap, tienKhuyenMai, tongTien);
                
                // Thêm hóa đơn vào danh sách
                hoaDonList.add(hoaDon);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // In lỗi nếu có
    }
    return hoaDonList;
}

//thống kê

    // Phương thức tìm kiếm hóa đơn theo khoảng thời gian
    public List<ThongKeCa> getHoaDonByDateRange(Date jDateChooserNBD, Date jDateChooserNKT) {
        // Chuyển đổi Date thành java.sql.Date
        Date sqlStartDate = new Date(jDateChooserNBD.getTime());
        Date sqlEndDate = new Date(jDateChooserNKT.getTime());

        // In ra ngày bắt đầu và ngày kết thúc
        System.out.println("Ngày bắt đầu: " + sqlStartDate);
        System.out.println("Ngày kết thúc: " + sqlEndDate);

        // Khởi tạo danh sách kết quả
        List<ThongKeCa> danhSachHD = new ArrayList<>();
        if (con == null) {
            System.out.println("Kết nối cơ sở dữ liệu không thành công!");
            return null; // Hoặc thông báo lỗi nếu cần
        }

        try {
            // Câu lệnh SQL
            String sql = "SELECT " +
                    "CONVERT(DATE, H.NgayHoaDon) AS Ngay, " +
                    "CASE " +
                    "WHEN DATEPART(HOUR, H.NgayHoaDon) >= 6 AND DATEPART(HOUR, H.NgayHoaDon) < 14 THEN 'Ca 1 (6h-14h)' " +
                    "WHEN DATEPART(HOUR, H.NgayHoaDon) >= 14 AND DATEPART(HOUR, H.NgayHoaDon) < 22 THEN 'Ca 2 (14h-22h)' " +
                    "ELSE 'Ca 3 (22h-6h)' " +
                    "END AS Ca, " +
                    "SUM(H.TongTien) AS DoanhThu, " +
                    "COUNT(V.MaVe) AS SoVeBan, " +
                    "COUNT(DISTINCT H.MaHD) AS SoHoaDon " +
                    "FROM HoaDon H " +
                    "JOIN ChiTietHoaDon CT ON H.MaHD = CT.MaHD " +
                    "JOIN VeTau V ON CT.MaVe = V.MaVe " +
                    "WHERE  CAST(H.NgayHoaDon AS DATE) BETWEEN ? AND ? " + // Lọc theo ngày
                    "GROUP BY CONVERT(DATE, H.NgayHoaDon), " +
                    "CASE " +
                    "WHEN DATEPART(HOUR, H.NgayHoaDon) >= 6 AND DATEPART(HOUR, H.NgayHoaDon) < 14 THEN 'Ca 1 (6h-14h)' " +
                    "WHEN DATEPART(HOUR, H.NgayHoaDon) >= 14 AND DATEPART(HOUR, H.NgayHoaDon) < 22 THEN 'Ca 2 (14h-22h)' " +
                    "ELSE 'Ca 3 (22h-6h)' " +
                    "END " +
                    "ORDER BY Ngay, Ca";

            // Tạo PreparedStatement và thiết lập tham số
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setDate(1, sqlStartDate);  // Set ngày bắt đầu
            pstmt.setDate(2, sqlEndDate);    // Set ngày kết thúc

            // Thực thi truy vấn
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // Lấy dữ liệu từ ResultSet
                    Date ngay = rs.getDate("Ngay");
                    String ca = rs.getString("Ca");
                    double doanhThu = rs.getDouble("DoanhThu");
                    int soVeBan = rs.getInt("SoVeBan");
                    int soHoaDon = rs.getInt("SoHoaDon");

                    // Tạo đối tượng ThongKeCa và thêm vào danh sách
                    ThongKeCa thongKeCa = new ThongKeCa(ngay, ca, doanhThu, soVeBan, soHoaDon);
                    danhSachHD.add(thongKeCa);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi SQL: " + e.getMessage());
        }
        // Trả về danh sách kết quả
        return danhSachHD;
    }

    // Phương thức tìm kiếm hóa đơn theo tháng và năm
    public List<ThongKeCa> getHoaDonByMonthYear(int month, int year) {
        // In ra tháng và năm để kiểm tra
        System.out.println("Tháng: " + month);
        System.out.println("Năm: " + year);

        // Khởi tạo danh sách kết quả
        List<ThongKeCa> danhSachHD = new ArrayList<>();

        if (con == null) {
            System.out.println("Kết nối cơ sở dữ liệu không thành công!");
            return null; // Hoặc thông báo lỗi nếu cần
        }

        try {
            // Câu lệnh SQL
            String sql = "SELECT " +
                    "CONVERT(DATE, H.NgayHoaDon) AS Ngay, " +
                    "CASE " +
                    "WHEN DATEPART(HOUR, H.NgayHoaDon) >= 6 AND DATEPART(HOUR, H.NgayHoaDon) < 14 THEN 'Ca 1 (6h-14h)' " +
                    "WHEN DATEPART(HOUR, H.NgayHoaDon) >= 14 AND DATEPART(HOUR, H.NgayHoaDon) < 22 THEN 'Ca 2 (14h-22h)' " +
                    "ELSE 'Ca 3 (22h-6h)' " +
                    "END AS Ca, " +
                    "SUM(H.TongTien) AS DoanhThu, " +
                    "COUNT(V.MaVe) AS SoVeBan, " +
                    "COUNT(DISTINCT H.MaHD) AS SoHoaDon " +
                    "FROM HoaDon H " +
                    "JOIN ChiTietHoaDon CT ON H.MaHD = CT.MaHD " +
                    "JOIN VeTau V ON CT.MaVe = V.MaVe " +
                    "WHERE MONTH(H.NgayHoaDon) = ? AND YEAR(H.NgayHoaDon) = ? " + // Lọc theo tháng và năm
                    "GROUP BY CONVERT(DATE, H.NgayHoaDon), " +
                    "CASE " +
                    "WHEN DATEPART(HOUR, H.NgayHoaDon) >= 6 AND DATEPART(HOUR, H.NgayHoaDon) < 14 THEN 'Ca 1 (6h-14h)' " +
                    "WHEN DATEPART(HOUR, H.NgayHoaDon) >= 14 AND DATEPART(HOUR, H.NgayHoaDon) < 22 THEN 'Ca 2 (14h-22h)' " +
                    "ELSE 'Ca 3 (22h-6h)' " +
                    "END " +
                    "ORDER BY Ngay, Ca";

            // Tạo PreparedStatement và thiết lập tham số
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, month);  // Set tháng
            pstmt.setInt(2, year);   // Set năm

            // Thực thi truy vấn
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // Lấy dữ liệu từ ResultSet
                    Date ngay = rs.getDate("Ngay");
                    String ca = rs.getString("Ca");
                    double doanhThu = rs.getDouble("DoanhThu");
                    int soVeBan = rs.getInt("SoVeBan");
                    int soHoaDon = rs.getInt("SoHoaDon");

                    // Tạo đối tượng ThongKeCa và thêm vào danh sách
                    ThongKeCa thongKeCa = new ThongKeCa(ngay, ca, doanhThu, soVeBan, soHoaDon);
                    danhSachHD.add(thongKeCa);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi SQL: " + e.getMessage());
        }

        // Trả về danh sách kết quả
        return danhSachHD;
    }
    // Phương thức tìm kiếm hóa đơn cho hôm qua
    public List<ThongKeCa> getHoaDonForYesterday() {
        // Lấy ngày hôm qua
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);  // Trừ đi 1 ngày
        java.util.Date yesterday = calendar.getTime();
        Date sqlYesterday = new Date(yesterday.getTime()); // Chuyển đổi thành java.sql.Date

        // Gọi phương thức tìm kiếm hóa đơn theo ngày (hôm qua)
        return getHoaDonByDateRange(sqlYesterday, sqlYesterday);
    }

    // Phương thức tìm kiếm hóa đơn cho hôm nay
    public List<ThongKeCa> getHoaDonForToday() {
        // Lấy ngày hôm nay
        java.util.Date today = new java.util.Date();
        Date sqlToday = new Date(today.getTime()); // Chuyển đổi thành java.sql.Date

        // Gọi phương thức tìm kiếm hóa đơn theo ngày (hôm nay)
        return getHoaDonByDateRange(sqlToday, sqlToday);
    }

}
