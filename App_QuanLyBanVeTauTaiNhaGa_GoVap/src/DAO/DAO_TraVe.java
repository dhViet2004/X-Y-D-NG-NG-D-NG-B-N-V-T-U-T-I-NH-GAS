package DAO;


import Database.ConnectDatabase;
import Entity.*;

import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import DAO.DAO_KhachHang;
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
                        (  rs.getString("GiayTo")),
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
    public boolean isLoaiKhachHangKH002ByMaHD(String maHD) {
        String sql = "SELECT kh.LoaiKhachHangMaLoaiKH " +
                "FROM HoaDon hd " +
                "JOIN KhachHang kh ON hd.MaKH = kh.MaKH " +
                "WHERE hd.MaHD = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maHD);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String maLoaiKH = rs.getString("LoaiKhachHangMaLoaiKH");
                return "KH002".equals(maLoaiKH);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi kiểm tra loại khách hàng: " + e.getMessage());
        }
        return false;
    }
    public List<VeTau> getVeTauByMaHDAndCCCDAndSDT(String maHD, String cccd, String sdt)  {
        List<VeTau> veTauList = new ArrayList<>();
        if(isLoaiKhachHangKH002ByMaHD(maHD)){
            DAO_KhachHang dao_khachHang = null;
            try {
                dao_khachHang = new DAO_KhachHang();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                cccd = dao_khachHang.encryptAES(cccd);
                sdt = dao_khachHang.encryptAES(sdt);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
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
    String sql = "SELECT kh.TenKH, kh.CCCD,kh.MaKH " +
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
            khachHang.setMaKhachHang(rs.getString("MaKH")); // Lấy mã khách hàng
            khachHang.setTenKhachHang(rs.getString("TenKH"));
            if(!Pattern.matches("([0-9]{12})", rs.getString("CCCD"))){
                DAO_KhachHang dao_khachHang = new DAO_KhachHang();
               String cccd = dao_khachHang.decryptAES(rs.getString("CCCD"));
                khachHang.setCCCD(cccd);

            }else {
                khachHang.setCCCD(rs.getString("CCCD"));
            }

        }
    } catch (SQLException e) {
        e.printStackTrace(); // In ra lỗi nếu có
    } catch (Exception e) {
        throw new RuntimeException(e);
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
    public boolean updateTrangThaiVe(String maVe) {
        String sql = "UPDATE VeTau SET TrangThai = N'Đã Trả' WHERE MaVe = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, maVe); // Thiết lập tham số cho truy vấn
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu có bản ghi được cập nhật


        } catch (SQLException e) {
            e.printStackTrace();
            return false;// In ra lỗi nếu có
        }
    }
    public String generateMaHoaDon() {
        // Lấy ngày hiện tại
        LocalDate today = LocalDate.now();
        String day = String.format("%02d", today.getDayOfMonth());
        String month = String.format("%02d", today.getMonthValue());
        String year = String.format("%02d", today.getYear() % 100); // Lấy 2 chữ số cuối của năm

        // Truy vấn số lượng hóa đơn đã có trong ngày
        String sqlCount = "SELECT COUNT(*) FROM HoaDon WHERE CONVERT(date, NgayHoaDon) = CONVERT(date, GETDATE())";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sqlCount)) {
            if (rs.next()) {
                int orderNumber = rs.getInt(1) + 1; // Thứ tự hóa đơn mới
                // Sinh mã hóa đơn theo công thức TVDDMMYYaaaa
                return String.format("TV%s%s%s%04d", day, month, year, orderNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String addHoaDon(String maKH, String nhanVienMaNV, float tongTien,Timestamp ngayHoaDon) {
        String sql = "INSERT INTO HoaDon (MaHD, MaKH, KhuyenMaiMaKM, NhanVienMaNV, NgayHoaDon, MaLoai, TienKhuyenMai, TongTien) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            // Tạo Timestamp hiện tại


            // Tạo mã hóa đơn
            String maHD = generateMaHoaDon();

            pstmt.setString(1, maHD);            // Mã hóa đơn
            pstmt.setString(2, maKH);            // Mã khách hàng
            pstmt.setString(3, null);            // Mã khuyến mãi (NULL nếu không có)
            pstmt.setString(4, nhanVienMaNV);    // Mã nhân viên
            pstmt.setString(6, "LHD02");         // Mã loại hóa đơn
            pstmt.setTimestamp(5, ngayHoaDon);   // Ngày hóa đơn
            pstmt.setFloat(7, 0.0f);             // Tiền khuyến mãi
            pstmt.setFloat(8, tongTien);         // Tổng tiền

            // Thực thi câu lệnh INSERT
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return maHD;  // Trả về mã hóa đơn nếu thêm thành công
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }

        return null;  // Trả về null nếu có lỗi
    }
    public int addChiTietHoaDon(String maVe, String maHD, int soLuong, float vat, float thanhTien, String tenThue) {
        // SQL query để thêm chi tiết hóa đơn vào bảng ChiTietHoaDon
        String sql = "INSERT INTO [UngDungQuanLyBanVeTaiGaGoVap].[dbo].[ChiTietHoaDon] " +
                "([MaVe], [MaHD], [SoLuong], [VAT], [ThanhTien], [TenThue]) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            // Cài đặt các giá trị cho PreparedStatement
            pstmt.setString(1, maVe);         // Mã vé
            pstmt.setString(2, maHD);         // Mã hóa đơn
            pstmt.setInt(3, soLuong);         // Số lượng
            pstmt.setFloat(4, vat);           // Thuế VAT
            pstmt.setFloat(5, thanhTien);     // Thành tiền (float)
            pstmt.setString(6, tenThue);      // Tên thuế

            // Thực thi câu lệnh INSERT
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return 1;  // Trả về 1 nếu thêm thành công
            }
        } catch (SQLException e) {
            e.printStackTrace();  // In ra lỗi nếu có
        }

        return -1;  // Trả về -1 nếu có lỗi
    }
    public KhachHang getKhachHangByMaKH(String maKH) {
        String sql = "SELECT kh.[MaKH], kh.[LoaiKhachHangMaLoaiKH], kh.[SoDT], kh.[TenKH], " +
                "kh.[CCCD], kh.[DiaChi], kh.[DiemTichLuy], kh.[NgaySinh], kh.[NgayThamGia], kh.[HangThanhVien], " +
                "lk.[TenLoaiKH] ,lk.[MaLoaiKH]" +
                "FROM [UngDungQuanLyBanVeTaiGaGoVap].[dbo].[KhachHang] kh " +
                "LEFT JOIN [UngDungQuanLyBanVeTaiGaGoVap].[dbo].[LoaiKhachHang] lk " +
                "ON kh.[LoaiKhachHangMaLoaiKH] = lk.[MaLoaiKH] " +
                "WHERE kh.[MaKH] = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, maKH);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Tạo đối tượng LoaiKhachHang
                    LoaiKhachHang loaiKH = new LoaiKhachHang();
                    loaiKH.setMaLoaiKhachHang(rs.getString("LoaiKhachHangMaLoaiKH"));
                    loaiKH.setTenLoaiKhachHang(rs.getString("TenLoaiKH"));

                    // Tạo đối tượng KhachHang
                    KhachHang kh = new KhachHang();
                    kh.setMaKhachHang(rs.getString("MaKH"));
                    kh.setLoaiKhachHang(loaiKH);
                   String hotenKH = null;
                     String soDienThoai = null;
                    String diaChi = null;
                    String cccd = null;
                    if(rs.getString("MaLoaiKH").equals("KH002")){
                        DAO_KhachHang dao_khachHang = new DAO_KhachHang();
                        hotenKH = dao_khachHang.decryptAES(rs.getString("TenKH"));
                        soDienThoai = dao_khachHang.decryptAES(rs.getString("SoDT"));
                        diaChi = dao_khachHang.decryptAES(rs.getString("DiaChi"));
                        cccd = dao_khachHang.decryptAES(rs.getString("CCCD"));
                        System.out.println(hotenKH);
                        System.out.println(soDienThoai);
                    }else{
                        hotenKH = rs.getString("TenKH");
                        soDienThoai = rs.getString("SoDT");
                        diaChi = rs.getString("DiaChi");
                        cccd= rs.getString("CCCD");
                    }
                    kh.setSoDienThoai(soDienThoai);
                    kh.setTenKhachHang(hotenKH);
                    kh.setCCCD(cccd);
                    kh.setDiaChi(diaChi);
                    kh.setDiemTichLuy(rs.getInt("DiemTichLuy"));
                    kh.setNgaySinh(rs.getDate("NgaySinh").toLocalDate());
                    kh.setNgayThamgGia(rs.getDate("NgayThamGia").toLocalDate());
                    kh.setHangThanhVien(rs.getString("HangThanhVien"));
                    return kh;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<ChiTietHoaDon> getChiTietHoaDonByMaHD(String maHD) {
        List<ChiTietHoaDon> chiTietHoaDonList = new ArrayList<>();
        String sql = "SELECT [MaVe], [MaHD], [SoLuong], [VAT], [ThanhTien], [TenThue] " +
                "FROM [UngDungQuanLyBanVeTaiGaGoVap].[dbo].[ChiTietHoaDon] " +
                "WHERE [MaHD] = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, maHD);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ChiTietHoaDon chiTiet = new ChiTietHoaDon();
                    chiTiet.setMaVe(rs.getString("MaVe"));
                    chiTiet.setMaHD(rs.getString("MaHD"));
                    chiTiet.setSoLuong(rs.getInt("SoLuong"));
                    chiTiet.setVAT(rs.getFloat("VAT"));
                    chiTiet.setThanhTien(rs.getFloat("ThanhTien"));
                    chiTiet.setTienThue(Double.parseDouble(rs.getString("TenThue")));

                    chiTietHoaDonList.add(chiTiet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chiTietHoaDonList;
    }
    public List<VeTau> getVeTauByMaHD(String maHD) {
        List<VeTau> danhSachVe = new ArrayList<>();
        String sql = "SELECT v.[MaVe], v.[TenKH], v.[GiayTo], v.[NgayDi], v.[DoiTuong], v.[GiaVe], v.[TrangThai], " +
                "lt.[MaLich], lt.[GioDi], lt.[NgayDi] AS NgayKhoiHanh, " +
                "cn.[MaCho], cn.[LoaiChoMaLoai], cn.TenCho, " +
                "toa.[MaToa], toa.ThuTu, t.[TenTau], " +
                "loaicho.[TenLoai] " +
                "FROM [VeTau] v " +
                "JOIN [ChiTietHoaDon] cthd ON v.MaVe = cthd.MaVe " + // Liên kết với ChiTietHoaDon qua MaVe
                "JOIN [LichTrinhTau] lt ON v.LichTrinhTauMaLich = lt.MaLich " +
                "JOIN [ChoNgoi] cn ON v.ChoNgoiMaCho = cn.MaCho " +
                "JOIN [ToaTau] toa ON cn.[LoaiToaMaToa] = toa.MaToa " +
                "JOIN [Tau] t ON toa.TauMaTau = t.MaTau " +
                "JOIN [LoaiCho] loaicho ON cn.LoaiChoMaLoai = loaicho.MaLoai " +
                "WHERE cthd.MaHD = ?";  // Tìm theo MaHD

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, maHD); // Gán giá trị cho tham số MaHD
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                // Tạo các đối tượng phụ trợ từ ResultSet
                LichTrinhTau lichTrinh = new LichTrinhTau(
                        rs.getString("MaLich"),
                        rs.getTime("GioDi").toLocalTime(),
                        rs.getDate("NgayKhoiHanh").toLocalDate(),
                        new Tau(null, null, rs.getString("TenTau"), 0), // Đối tượng Tau
                        rs.getString("TrangThai")
                );

                ChoNgoi choNgoi = new ChoNgoi(
                        rs.getString("MaCho"),
                        new LoaiCho(null, rs.getString("TenLoai")), // Đối tượng LoaiCho
                        new ToaTau(rs.getString("MaToa"), null, null, 0, null, rs.getInt("ThuTu")), // Đối tượng ToaTau
                        rs.getString("TenCho"),
                        true, // Tình trạng chỗ ngồi (có thể thay đổi theo dữ liệu thực tế)
                        rs.getFloat("GiaVe")
                );

                VeTau ve = new VeTau(
                        rs.getString("MaVe"),
                        lichTrinh, // Gán LichTrinhTau
                        choNgoi, // Gán ChoNgoi
                        rs.getString("TenKH"),
                        rs.getString("GiayTo"),
                        rs.getDate("NgayDi").toLocalDate(),
                        rs.getString("DoiTuong"),
                        rs.getDouble("GiaVe"),
                        rs.getString("TrangThai")
                );

                // Thêm vé vào danh sách
                danhSachVe.add(ve);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi
        }

        return danhSachVe; // Trả về danh sách vé
    }
    public HoaDon getHoaDonByMa(String maHD) {
        HoaDon hoaDon = null;
        String query = "SELECT [MaHD], [MaKH], [KhuyenMaiMaKM], [NhanVienMaNV], [MaLoai], [NgayHoaDon], [TienKhuyenMai], [TongTien] "
                + "FROM [UngDungQuanLyBanVeTaiGaGoVap].[dbo].[HoaDon] WHERE MaHD = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, maHD);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                hoaDon = new HoaDon(
                        rs.getString("MaHD"),
                        new KhachHang(rs.getString("MaKH")),
                        new KhuyenMai(rs.getString("KhuyenMaiMaKM")),
                        new NhanVien(rs.getString("NhanVienMaNV")),
                        new LoaiHoaDon(rs.getString("MaLoai")),
                        rs.getTimestamp("NgayHoaDon").toLocalDateTime(),
                        rs.getDouble("TienKhuyenMai"),
                        rs.getDouble("TongTien")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hoaDon;
    }

    public static void main(String[] args) {
        ConnectDatabase.getInstance().connect();
        DAO_TraVe dao = new DAO_TraVe();
        // Test getVeByMaVe

        // Test getVeTauByMaHDAndCCCDAndSDT

        // Test getTenVaCCCDKhachHangByMaVe
        KhachHang khachHang = dao.getKhachHangByMaKH("KH1112240001");
        System.out.println(khachHang);
    }




}
