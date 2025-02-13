package DAO;

import Database.ConnectDatabase;
import Entity.*;

import java.sql.*;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class DAO_HoaDon {
    private Connection con;

    public DAO_HoaDon() {
        if (ConnectDatabase.getConnection() == null) {
            ConnectDatabase.getInstance().connect();  // Kết nối nếu chưa kết nối
        }
        con = ConnectDatabase.getConnection();
    }

//    public List<HoaDon> getAllList() throws SQLException {
//        List<HoaDon> list = new ArrayList<HoaDon>();
//        String sql = "select * from HoaDon";
//        DAO_HoaDon dao = new DAO_HoaDon();
//        try (Statement statement = con.createStatement(); ResultSet rs = statement.executeQuery(sql)) {
//
//            while (rs.next()) {
//                HoaDon hd = new HoaDon(rs.getString("MaHD"), new KhachHang(rs.getString("MaKH")), new KhuyenMai(rs.getString("KhuyenMaiMaKM")), new NhanVien(rs.getString("MaNV")), new LoaiHoaDon(rs.getString("MaLoai")), rs.getDate("NgayHoaDon").toLocalDa, rs.getDouble("TienKhuyenMai"), rs.getDouble("TongTien"));
//                list.add(hd);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw e; // Ném lại ngoại lệ để xử lý ở nơi gọi
//        }
//
//        return list;
//    }

    public List<Object[]> layDSHD() throws SQLException {
        List<Object[]> list = new ArrayList<>();
        String sql = "select h.NgayHoaDon,k.TenKH,m.DoiTuongApDung,m.NoiDungKM, m.ChietKhau, m.ChietKhau/100*h.TongTien as TienKhuyenMai\n" + "from KhachHang k join HoaDon h on k.MaKH=h.MaKH join KhuyenMai m on h.KhuyenMaiMaKM = m.MaKM";
        DAO_HoaDon dao = new DAO_HoaDon();
        try (Statement statement = con.createStatement(); ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Object[]{
                        rs.getString("NgayHoaDon"),
                        rs.getString("TenKH"),
                        rs.getString("DoiTuongApDung"),
                        rs.getString("NoiDungKM"),
                        rs.getString("ChietKhau"),
                        rs.getString("TienKhuyenMai")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Ném lại ngoại lệ để xử lý ở nơi gọi
        }

        return list;
    }

    // lấy số lượng khách hàng theo tháng trong năm

    public List<Object[]> SLKH_MONTH_YEAR_TOTAL(int year) throws SQLException {
        List<Object[]> list = new ArrayList<>();
        String sql = "WITH Months AS ( " +
                "    SELECT 1 AS MonthNumber, N'Tháng 1' AS MonthName " +
                "    UNION ALL SELECT 2, N'Tháng 2' " +
                "    UNION ALL SELECT 3, N'Tháng 3' " +
                "    UNION ALL SELECT 4, N'Tháng 4' " +
                "    UNION ALL SELECT 5, N'Tháng 5' " +
                "    UNION ALL SELECT 6, N'Tháng 6' " +
                "    UNION ALL SELECT 7, N'Tháng 7' " +
                "    UNION ALL SELECT 8, N'Tháng 8' " +
                "    UNION ALL SELECT 9, N'Tháng 9' " +
                "    UNION ALL SELECT 10, N'Tháng 10' " +
                "    UNION ALL SELECT 11, N'Tháng 11' " +
                "    UNION ALL SELECT 12, N'Tháng 12' " +
                ") " +
                "SELECT m.MonthName, COALESCE(COUNT(DISTINCT hd.MaKH), 0) AS SL " +
                "FROM Months m " +
                "LEFT JOIN HoaDon hd ON MONTH(hd.NgayHoaDon) = m.MonthNumber " +
                "AND YEAR(hd.NgayHoaDon) = ? " +
                "GROUP BY m.MonthNumber, m.MonthName " +
                "ORDER BY m.MonthNumber;";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, year); // Truyền tham số year vào truy vấn
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            list.add(new Object[]{
                    rs.getString("MonthName"),
                    rs.getInt("SL")
            });
        }
        rs.close();           // Đóng ResultSet sau khi sử dụng
        statement.close();     // Đóng PreparedStatement sau khi sử dụng
        return list;
    }

    // lấy số lượng khách hàng theo tháng trong năm chi tiết
    public List<Object[]> SLKH_MONTH_YEAR_DETAIL(int year) throws SQLException {
        List<Object[]> list = new ArrayList<>();
        String sql = "WITH Months AS ( " +
                "    SELECT 1 AS MonthNumber, N'Tháng 1' AS MonthName " +
                "    UNION ALL SELECT 2, N'Tháng 2' " +
                "    UNION ALL SELECT 3, N'Tháng 3' " +
                "    UNION ALL SELECT 4, N'Tháng 4' " +
                "    UNION ALL SELECT 5, N'Tháng 5' " +
                "    UNION ALL SELECT 6, N'Tháng 6' " +
                "    UNION ALL SELECT 7, N'Tháng 7' " +
                "    UNION ALL SELECT 8, N'Tháng 8' " +
                "    UNION ALL SELECT 9, N'Tháng 9' " +
                "    UNION ALL SELECT 10, N'Tháng 10' " +
                "    UNION ALL SELECT 11, N'Tháng 11' " +
                "    UNION ALL SELECT 12, N'Tháng 12' " +
                ") " +
                "SELECT m.MonthName, COALESCE(COUNT(DISTINCT hd.MaKH), 0) AS SL, COALESCE(COUNT(DISTINCT hd.MaHD), 0) AS SLHD " +
                "FROM Months m " +
                "LEFT JOIN HoaDon hd ON MONTH(hd.NgayHoaDon) = m.MonthNumber " +
                "AND YEAR(hd.NgayHoaDon) = ? " +
                "GROUP BY m.MonthNumber, m.MonthName " +
                "ORDER BY m.MonthNumber;";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, year); // Truyền tham số year vào truy vấn
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            list.add(new Object[]{
                    rs.getString("MonthName"),
                    rs.getInt("SL"),
                    rs.getInt("SLHD")
            });
        }
        rs.close();           // Đóng ResultSet sau khi sử dụng
        statement.close();     // Đóng PreparedStatement sau khi sử dụng
        return list;
    }

    // lấy số lượng khách hàng hôm nay tổng quan
    public List<Object[]> SLKH_TODAY_TOTAL(LocalDate localDate) throws SQLException {
        List<Object[]> list = new ArrayList<>();
        String sql = "SELECT CAST(NgayHoaDon AS DATE) AS 'Ngay', COUNT(DISTINCT MaKH) AS 'SLKH' " +
                "FROM HoaDon " +
                "WHERE CAST(NgayHoaDon AS DATE) = ? " +
                "GROUP BY CAST(NgayHoaDon AS DATE)";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setDate(1, Date.valueOf(localDate)); // Truyền tham số year vào truy vấn
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            java.sql.Date sqlDate = rs.getDate("Ngay");  // Lấy giá trị ngày dưới dạng java.sql.Date
            LocalDate dateCasr = sqlDate.toLocalDate();  // Chuyển đổi java.sql.Date thành LocalDate

            list.add(new Object[]{
                    dateCasr,    // Thêm LocalDate vào list
                    rs.getInt("SLKH")
            });
        }

        rs.close();           // Đóng ResultSet sau khi sử dụng
        statement.close();     // Đóng PreparedStatement sau khi sử dụng
        return list;
    }

    // lấy số lượng khách hàng hôm nay chi tiết
    public List<Object[]> SLKH_TODAY_DETAIL(LocalDate localDate) throws SQLException {
        List<Object[]> list = new ArrayList<>();
        String sql = "SELECT CAST(NgayHoaDon AS DATE) AS 'Ngay', COUNT(DISTINCT MaKH) AS 'SLKH', COUNT(DISTINCT MaHD) AS 'SLHD' " +
                "FROM HoaDon " +
                "WHERE CAST(NgayHoaDon AS DATE) = ? " +
                "GROUP BY CAST(NgayHoaDon AS DATE)";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setDate(1, Date.valueOf(localDate)); // Truyền tham số ngày vào truy vấn
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            java.sql.Date sqlDate = rs.getDate("Ngay");  // Lấy giá trị ngày dưới dạng java.sql.Date
            LocalDate dateCast = sqlDate.toLocalDate();  // Chuyển đổi java.sql.Date thành LocalDate

            list.add(new Object[]{
                    dateCast,    // Thêm LocalDate vào list
                    rs.getInt("SLKH"),
                    rs.getInt("SLHD")
            });
        }

        rs.close();           // Đóng ResultSet sau khi sử dụng
        statement.close();     // Đóng PreparedStatement sau khi sử dụng
        return list;
    }


    public List<Object[]> SLKH_WEEK_TOTAL(LocalDate localDate) throws SQLException {
        List<Object[]> list = new ArrayList<>();

        // Tính toán ngày đầu và ngày cuối của tuần hiện tại
        LocalDate startOfWeek = localDate.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = localDate.with(DayOfWeek.SUNDAY);

        String sql = "WITH WeekDays AS (\n" +
                "    SELECT N'Thứ hai' AS NgayTrongTuan, 2 AS Weekday\n" +
                "    UNION ALL SELECT N'Thứ ba', 3\n" +
                "    UNION ALL SELECT N'Thứ tư', 4\n" +
                "    UNION ALL SELECT N'Thứ năm', 5\n" +
                "    UNION ALL SELECT N'Thứ sáu', 6\n" +
                "    UNION ALL SELECT N'Thứ bảy', 7\n" +
                "    UNION ALL SELECT N'Chủ nhật', 1\n" +
                ")\n" +
                "SELECT \n" +
                "    wd.NgayTrongTuan,\n" +
                "    COALESCE(COUNT(hd.NgayHoaDon), 0) AS SLKH\n" +
                "FROM \n" +
                "    WeekDays wd\n" +
                "LEFT JOIN \n" +
                "    HoaDon hd ON DATEPART(WEEKDAY, hd.NgayHoaDon) = wd.Weekday \n" +
                "    AND hd.NgayHoaDon BETWEEN ? AND ? " + // Sử dụng khoảng thời gian tuần hiện tại
                "GROUP BY \n" +
                "    wd.NgayTrongTuan, wd.Weekday\n" +
                "ORDER BY \n" +
                "    wd.Weekday;";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setDate(1, java.sql.Date.valueOf(startOfWeek)); // Ngày bắt đầu tuần
        statement.setDate(2, java.sql.Date.valueOf(endOfWeek)); // Ngày kết thúc tuần
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            list.add(new Object[]{
                    rs.getString("NgayTrongTuan"),
                    rs.getInt("SLKH"),
            });
        }
        rs.close();           // Đóng ResultSet sau khi sử dụng
        statement.close();     // Đóng PreparedStatement sau khi sử dụng
        return list;
    }

    public List<Object[]> SLKH_WEEK_DETAIL(LocalDate localDate) throws SQLException {
        List<Object[]> list = new ArrayList<>();

        // Tính toán ngày đầu và ngày cuối của tuần hiện tại
        LocalDate startOfWeek = localDate.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = localDate.with(DayOfWeek.SUNDAY);

        String sql = "WITH WeekDays AS ( " +
                "    SELECT N'Thứ hai' AS NgayTrongTuan, 2 AS Weekday " +
                "    UNION ALL SELECT N'Thứ ba', 3 " +
                "    UNION ALL SELECT N'Thứ tư', 4 " +
                "    UNION ALL SELECT N'Thứ năm', 5 " +
                "    UNION ALL SELECT N'Thứ sáu', 6 " +
                "    UNION ALL SELECT N'Thứ bảy', 7 " +
                "    UNION ALL SELECT N'Chủ nhật', 1 " +
                ") " +
                "SELECT " +
                "    wd.NgayTrongTuan, " +
                "    COALESCE(COUNT(DISTINCT hd.MaKH), 0) AS SLKH, " + // Số lượng khách hàng
                "    COALESCE(COUNT(hd.MaHD), 0) AS SLHD " + // Số lượng hóa đơn
                "FROM " +
                "    WeekDays wd " +
                "LEFT JOIN " +
                "    HoaDon hd ON DATEPART(WEEKDAY, hd.NgayHoaDon) = wd.Weekday " +
                "    AND hd.NgayHoaDon BETWEEN ? AND ? " + // Sử dụng khoảng thời gian tuần hiện tại
                "GROUP BY " +
                "    wd.NgayTrongTuan, wd.Weekday " +
                "ORDER BY " +
                "    wd.Weekday;";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setDate(1, java.sql.Date.valueOf(startOfWeek)); // Ngày bắt đầu tuần
        statement.setDate(2, java.sql.Date.valueOf(endOfWeek)); // Ngày kết thúc tuần
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            list.add(new Object[]{
                    rs.getString("NgayTrongTuan"),
                    rs.getInt("SLKH"),
                    rs.getInt("SLHD") // Thêm số lượng hóa đơn vào danh sách
            });
        }
        rs.close();           // Đóng ResultSet sau khi sử dụng
        statement.close();     // Đóng PreparedStatement sau khi sử dụng
        return list;
    }

    public List<Object[]> SLKH_MONTH_DETAIL(LocalDate monthStart) throws SQLException {
        List<Object[]> list = new ArrayList<>();

        // Lấy thông tin ngày kết thúc của tháng
        LocalDate monthEnd = monthStart.withDayOfMonth(monthStart.lengthOfMonth());

        String sql = "WITH DateRange AS ( " +
                "    SELECT DATEADD(DAY, n, CAST(? AS DATE)) AS Ngay " +
                "    FROM (SELECT TOP (?) ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) - 1 AS n " +
                "          FROM master..spt_values) AS x " +
                ") " +
                "SELECT " +
                "    dr.Ngay, " +
                "    COALESCE(COUNT(DISTINCT hd.MaKH), 0) AS SLKH, " + // Số lượng khách hàng
                "    COALESCE(COUNT(hd.MaHD), 0) AS SLHD " + // Số lượng hóa đơn
                "FROM " +
                "    DateRange dr " +
                "LEFT JOIN " +
                "    HoaDon hd ON CAST(hd.NgayHoaDon AS DATE) = dr.Ngay " +
                "GROUP BY " +
                "    dr.Ngay " +
                "ORDER BY " +
                "    dr.Ngay;";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setDate(1, java.sql.Date.valueOf(monthStart)); // Ngày bắt đầu tháng
        statement.setInt(2, monthStart.lengthOfMonth()); // Số ngày trong tháng

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            list.add(new Object[]{
                    rs.getDate("Ngay").toLocalDate(), // Chuyển đổi thành LocalDate
                    rs.getInt("SLKH"),
                    rs.getInt("SLHD")
            });
        }

        rs.close();
        statement.close();
        return list;
    }


    public List<Object[]> SLKH_MONTH_TOTAL(LocalDate monthStart) throws SQLException {
        List<Object[]> list = new ArrayList<>();

        // Lấy thông tin ngày kết thúc của tháng
        LocalDate monthEnd = monthStart.withDayOfMonth(monthStart.lengthOfMonth());

        String sql = "WITH DateRange AS ( " +
                "    SELECT DATEADD(DAY, n, CAST(? AS DATE)) AS Ngay " +
                "    FROM (SELECT TOP (?) ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) - 1 AS n " +
                "          FROM master..spt_values) AS x " +
                ") " +
                "SELECT " +
                "    dr.Ngay, " +
                "    COALESCE(COUNT(DISTINCT hd.MaKH), 0) AS SLKH " + // Số lượng khách hàng
                "FROM " +
                "    DateRange dr " +
                "LEFT JOIN " +
                "    HoaDon hd ON CAST(hd.NgayHoaDon AS DATE) = dr.Ngay " +
                "GROUP BY " +
                "    dr.Ngay " +
                "ORDER BY " +
                "    dr.Ngay;";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setDate(1, java.sql.Date.valueOf(monthStart)); // Ngày bắt đầu tháng
        statement.setInt(2, monthStart.lengthOfMonth()); // Số ngày trong tháng

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            list.add(new Object[]{
                    rs.getDate("Ngay").toLocalDate(), // Chuyển đổi thành LocalDate
                    rs.getInt("SLKH") // Số lượng khách hàng
            });
        }

        rs.close();
        statement.close();
        return list;
    }

    public int tongSLVeTheoThoiGian(Timestamp begin, Timestamp end) throws SQLException {
        String sql = """
                    SELECT count(*)
                    FROM VeTau v
                    JOIN ChiTietHoaDon c ON v.MaVe = c.MaVe
                    JOIN HoaDon h ON c.MaHD = h.MaHD
                    WHERE h.NgayHoaDon BETWEEN ? AND ?
                """;

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            // Chuyển Date thành Timestamp để phù hợp với SQL
            statement.setTimestamp(1, new Timestamp(begin.getTime()));
            statement.setTimestamp(2, new Timestamp(end.getTime()));

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0; // Trả về 0 nếu không có kết quả
    }


    public Map<String, Integer> slDoiTuongTheoThoiGian(Timestamp begin, Timestamp end) throws SQLException {
        String sql = """
                    SELECT v.DoiTuong, count(*)
                    FROM VeTau v
                    JOIN ChiTietHoaDon c ON v.MaVe = c.MaVe
                    JOIN HoaDon h ON c.MaHD = h.MaHD
                    WHERE h.NgayHoaDon BETWEEN ? AND ?
                    GROUP BY v.DoiTuong
                """;

        Map<String, Integer> resultMap = new HashMap<>();

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            // Gán giá trị tham số
            statement.setTimestamp(1, new Timestamp(begin.getTime()));
            statement.setTimestamp(2, new Timestamp(end.getTime()));

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    // Lấy DoiTuong và TongSoLuong từ kết quả truy vấn
                    String doiTuong = rs.getString(1);
                    int tongSoLuong = rs.getInt(2);
                    resultMap.put(doiTuong, tongSoLuong);
                }
            }
        }

        return resultMap;
    }

    public Map<Date, Integer> getTicketsByDateRange(Timestamp begin, Timestamp end) throws SQLException {
        // Dùng LinkedHashMap để giữ thứ tự chèn
        Map<Date, Integer> resultMap = new LinkedHashMap<>();

        String sql = """
        SELECT h.NgayHoaDon, COUNT(*)
        FROM VeTau v
        JOIN ChiTietHoaDon c ON v.MaVe = c.MaVe
        JOIN HoaDon h ON c.MaHD = h.MaHD
        WHERE h.NgayHoaDon BETWEEN ? AND ?
        GROUP BY h.NgayHoaDon
        ORDER BY h.NgayHoaDon
    """;

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setTimestamp(1, begin);
            statement.setTimestamp(2, end);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Date date = rs.getDate(1);
                    int soLuong = rs.getInt(2);

                    // Thêm kiểm tra và cộng dồn số lượng vé nếu trùng ngày
                    resultMap.merge(date, soLuong, Integer::sum);
                }
            }
        }

        return resultMap;
    }


}
