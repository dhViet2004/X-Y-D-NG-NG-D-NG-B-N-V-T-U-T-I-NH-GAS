package DAO;


import Database.ConnectDatabase;
import Entity.ChoNgoi;
import Entity.KhachHang;
import Entity.LichTrinhTau;
import Entity.LoaiCho;
import Entity.Tau;
import Entity.ToaTau;
import Entity.VeTau;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAO_TraVe {
    private Connection con;

    public DAO_TraVe() {
         this.con = ConnectDatabase.getConnection();
        if (this.con == null) {
            System.out.println("Connection is null. Please check the database connection.");
        } // Kết nối cơ sở dữ liệu
    }

    

    // Phương thức lấy thông tin vé tàu theo mã vé
    public VeTau getVeByMaVe(String maVe) {
        VeTau ve = null;
        String sql ="SELECT v.[MaVe], v.[TenKH], v.[GiayTo], v.[NgayDi], v.[DoiTuong], v.[GiaVe], v.[TrangThai], " +
             "lt.[MaLich], lt.[GioDi], lt.[NgayDi] AS NgayKhoiHanh, " +
             "cn.[MaCho], cn.[LoaiChoMaLoai], cn.TenCho, " + // Thêm Gia từ bảng ChoNgoi
             "toa.[MaToa], toa.ThuTu, t.[TenTau], " + // Dấu cách sau 'TenTau'
             "loaicho.[TenLoai] " + // Dấu cách sau 'TenLoai'
             "FROM [VeTau] v " +
             "JOIN [LichTrinhTau] lt ON v.LichTrinhTauMaLich = lt.MaLich " +
             "JOIN [ChoNgoi] cn ON v.ChoNgoiMaCho = cn.MaCho " +
             "JOIN [ToaTau] toa ON cn.[LoaiToaMaToa] = toa.MaToa " +  // Liên kết với bảng ToaTau qua MaToa
             "JOIN [Tau] t ON toa.TauMaTau = t.MaTau " +
             "JOIN [dbo].[LoaiCho] loaicho ON cn.LoaiChoMaLoai = loaicho.MaLoai " +
             "WHERE v.MaVe = ?";  // Truy vấn SQL

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, maVe);
            ResultSet rs = pstmt.executeQuery();

            // Nếu có kết quả, tạo đối tượng VeTau
            if (rs.next()) {
                // Lấy thông tin từ result set
                LichTrinhTau lichTrinh = new LichTrinhTau(
                    rs.getString("MaLich"),
                    rs.getTime("GioDi").toLocalTime(),
                    rs.getDate("NgayKhoiHanh").toLocalDate(),
                    new Tau(null,null,rs.getString("TenTau"),0), // Tạo đối tượng Tau cho chuyến tàu
                    rs.getString("TrangThai")
                );

                ChoNgoi choNgoi = new ChoNgoi(
                    rs.getString("MaCho"),
                    new LoaiCho(null,rs.getString("TenLoai")), // Tạo đối tượng LoaiCho cho chỗ ngồi
                    new ToaTau(rs.getString("MaToa"),null,null,0,null,rs.getInt("ThuTu")), // Tạo đối tượng ToaTau cho toa tàu
                    rs.getString("TenCho"),
                    true, // Tình trạng chỗ ngồi (có thể thay đổi theo dữ liệu thực tế)
                    rs.getFloat("GiaVe")
                );

                ve = new VeTau(
                    rs.getString("MaVe"),
                    lichTrinh, // Gán LichTrinhTau vào Vé
                    choNgoi, // Gán ChoNgoi vào Vé
                    rs.getString("TenKH"),
                    rs.getString("GiayTo"),
                    rs.getDate("NgayDi").toLocalDate(),
                    rs.getString("DoiTuong"),
                    rs.getDouble("GiaVe"),
                    rs.getString("TrangThai")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ve; // Trả về đối tượng VeTau hoặc null nếu không tìm thấy
    }
    public List<VeTau> getVeTauByMaHDAndCCCDAndSDT(String maHD, String cccd, String sdt) {
        List<VeTau> veTauList = new ArrayList<>();
        String sql = "SELECT vt.MaVe, hd.MaHD, kh.CCCD, kh.SoDT "
                   + "FROM HoaDon hd "
                   + "JOIN ChiTietHoaDon cthd ON hd.MaHD = cthd.MaHD "
                   + "JOIN VeTau vt ON cthd.MaVe = vt.MaVe "
                   + "JOIN KhachHang kh ON hd.MaKH = kh.MaKH "
                   + "WHERE hd.MaHD = ? AND kh.CCCD = ? AND kh.SoDT = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
             // Thiết lập các tham số vào câu lệnh SQL
            pstmt.setString(1, maHD);  // MaHD
            pstmt.setString(2, cccd);  // CCCD
            pstmt.setString(3, sdt);   // SDT

            // Thực thi câu lệnh và nhận kết quả trả về
            ResultSet rs = pstmt.executeQuery();
            
            // Duyệt qua các dòng kết quả và ánh xạ dữ liệu vào đối tượng VeTau
            while (rs.next()) {
                String maVe = rs.getString("MaVe");
                String maHoaDon = rs.getString("MaHD");
                String cccdKhachHang = rs.getString("CCCD");
                String sdtKhachHang = rs.getString("SoDT");
                 
                // Tạo đối tượng VeTau và thêm vào danh sách
                VeTau veTau = new VeTau(maVe, (LichTrinhTau )null,null,null,null,null,null,0.0,null);
                veTauList.add(veTau);
          
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veTauList;
    }
    public KhachHang getTenVaCCCDKhachHangByMaVe(String maVe) {
    KhachHang khachHang = null;
    String sql = "SELECT kh.TenKH, kh.CCCD " +
                 "FROM ChiTietHoaDon cthd " +
                 "JOIN HoaDon hd ON cthd.MaHD = hd.MaHD " +
                 "JOIN KhachHang kh ON hd.MaKH = kh.MaKH " +
                 "WHERE cthd.MaVe = ?";

    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
        pstmt.setString(1, maVe); // Thiết lập tham số mã vé
        ResultSet rs = pstmt.executeQuery(); // Thực thi truy vấn

        // Kiểm tra nếu có kết quả trả về
        if (rs.next()) {
            khachHang = new KhachHang("MaKH");
            khachHang.setTenKhachHang(rs.getString("TenKH")); // Lấy tên khách hàng
            khachHang.setCCCD(rs.getString("CCCD"));          // Lấy CCCD
        }
    } catch (SQLException e) {
        e.printStackTrace(); // In ra lỗi nếu có
    }

    return khachHang; // Trả về đối tượng KhachHang hoặc null nếu không tìm thấy
}
      public double getThanhTienByMaVe(String maVe) {
        double thanhTien = 0;
         String sql = "SELECT cthd.ThanhTien, hd.KhuyenMaiMaKM, km.ChietKhau " +
                 "FROM [ChiTietHoaDon] cthd " +
                 "JOIN [HoaDon] hd ON cthd.MaHD = hd.MaHD " +
                 "LEFT JOIN [KhuyenMai] km ON hd.KhuyenMaiMaKM = km.MaKM " +
                 "WHERE cthd.MaVe = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
        pstmt.setString(1, maVe); // Thiết lập tham số cho truy vấn
        ResultSet rs = pstmt.executeQuery(); // Thực thi truy vấn

        // Kiểm tra nếu có kết quả trả về
        if (rs.next()) {
            thanhTien = rs.getDouble("ThanhTien");
            double chietKhau = rs.getDouble("ChietKhau");

            // Áp dụng chiết khấu nếu giá trị chiết khấu lớn hơn 0
           if (!rs.wasNull() && chietKhau > 0) {
                thanhTien *= (1 - chietKhau / 100);//thanhtien=thanh-thanhtien*chietkhau
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // In ra lỗi nếu có
    }

        return thanhTien; // Trả về thành tiền hoặc 0 nếu không tìm thấy
    }
        public String getTenTuyenByMaVe(String maVe) {
        String tenTuyen = null;

        String sql = """
            SELECT tt.TenTuyen
            FROM VeTau vt
            JOIN LichTrinhTau ltt ON vt.LichTrinhTauMaLich = ltt.MaLich
            JOIN Tau t ON ltt.MaTau = t.MaTau
            JOIN TuyenTau tt ON t.MaTuyen = tt.MaTuyen
            WHERE vt.MaVe = ?
        """;

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
        pstmt.setString(1, maVe); // Thiết lập tham số cho truy vấn
        ResultSet rs = pstmt.executeQuery(); // Thực thi truy vấn

        // Kiểm tra nếu có kết quả trả về
        if (rs.next()) {
            tenTuyen = rs.getString("TenTuyen");
        }
    } catch (SQLException e) {
        e.printStackTrace(); // In ra lỗi nếu có
    }


        return tenTuyen;
    }
    public static void main(String[] args) {
        ConnectDatabase.getInstance().connect();
      DAO_TraVe dao = new DAO_TraVe();
         
        String maVe = "SGNT100120241207135926-003"; // Thay bằng mã vé thực tế
        String tenTuyen = dao.getTenTuyenByMaVe(maVe);
        if (tenTuyen != null) {
            System.out.println("Tên tuyến: " + tenTuyen);
        } else {
            System.out.println("Không tìm thấy tên tuyến cho mã vé: " + maVe);
        }
     
    }

}
