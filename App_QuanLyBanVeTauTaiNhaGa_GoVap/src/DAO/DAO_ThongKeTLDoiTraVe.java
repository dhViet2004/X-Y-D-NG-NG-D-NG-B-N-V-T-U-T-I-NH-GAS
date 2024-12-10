package DAO;

import Database.ConnectDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO_ThongKeTLDoiTraVe {
    private Connection con;

    public DAO_ThongKeTLDoiTraVe() {
        con = ConnectDatabase.getConnection();
    }
    // Thống kê số lượng vé theo một năm và trạng thái (Đã thanh toán, Đã đổi, Đã trả)
    public int thongKeVeTheoNam(int year, String trangThai) throws SQLException {
        String query = """
        SELECT COUNT(MaVe) AS SoLuongVe
        FROM VeTau
        WHERE YEAR(NgayDi) = ? 
        AND TrangThai = ?;
    """;  // Lọc theo năm và trạng thái

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, year);            // Thiết lập năm
            ps.setString(2, trangThai);    // Thiết lập trạng thái vé

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("SoLuongVe");
                }
            }
        }
        return 0;  // Nếu không có kết quả
    }

    // Thống kê số lượng vé đã bán (Trang thái "Đã thanh toán")
    public int thongKeVeDaThanhToan(String startDate, String endDate) throws SQLException {
        return thongKeVeTheoTrangThai(startDate, endDate, "Đã thanh toán");
    }

    // Thống kê số lượng vé đã đổi (Trang thái "Đã đổi")
    public int thongKeVeDaDoi(String startDate, String endDate) throws SQLException {
        return thongKeVeTheoTrangThai(startDate, endDate, "Đã đổi");
    }

    // Thống kê số lượng vé đã trả (Trang thái "Đã trả")
    public int thongKeVeDaTra(String startDate, String endDate) throws SQLException {
        return thongKeVeTheoTrangThai(startDate, endDate, "Đã trả");
    }
    // Thống kê số lượng vé cho một tháng và năm cụ thể và trạng thái
    public int thongKeVeTheoThangVaNam(int month, int year, String trangThai) throws SQLException {
        String query = """
        SELECT COUNT(MaVe) AS SoLuongVe
        FROM VeTau
        WHERE MONTH(NgayDi) = ?
        AND YEAR(NgayDi) = ?
        AND TrangThai = ?;
        """;  // Lọc theo tháng, năm và trạng thái

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, month);          // Thiết lập tháng (1 đến 12)
            ps.setInt(2, year);           // Thiết lập năm
            ps.setString(3, trangThai);   // Thiết lập trạng thái vé

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("SoLuongVe");
                }
            }
        }
        return 0;  // Nếu không có kết quả
    }


    // Phương thức chung để thống kê theo trạng thái
    private int thongKeVeTheoTrangThai(String startDate, String endDate, String trangThai) throws SQLException {
        String query = """
                SELECT COUNT(MaVe) AS SoLuongVe
                FROM VeTau
                WHERE TrangThai = ?
                AND NgayDi BETWEEN ? AND ?;
                """;

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, trangThai); // Thiết lập trạng thái vé (Đã thanh toán, Đã đổi, Đã trả)
            ps.setString(2, startDate);  // Ngày bắt đầu
            ps.setString(3, endDate);    // Ngày kết thúc

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("SoLuongVe");
                }
            }
        }
        return 0;  // Nếu không có kết quả
    }
}
