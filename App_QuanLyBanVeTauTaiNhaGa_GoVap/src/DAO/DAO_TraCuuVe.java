package DAO;

import Database.ConnectDatabase;
import Entity.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DAO_TraCuuVe {
    private Connection con;

    public DAO_TraCuuVe() {
        con = ConnectDatabase.getConnection();
    }
    public ChiTietHoaDon timChiTietHoaDonTheoMaVe(String maVe) throws SQLException {
        String query = """
            SELECT 
                MaVe,
                MaHD,
                SoLuong,
                VAT,
                ThanhTien,
                TenThue
            FROM ChiTietHoaDon
            WHERE MaVe = ?;
            """;

        ChiTietHoaDon chiTietHoaDon = null;

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, maVe);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Lấy thông tin từ ResultSet
                    String maHD = rs.getString("MaHD");
                    int soLuong = rs.getInt("SoLuong");
                    double VAT = rs.getDouble("VAT");
                    double thanhTien = rs.getDouble("ThanhTien");
                    double tienThue = rs.getDouble("TenThue"); // Giá trị thuế có thể có tên khác trong cơ sở dữ liệu (như 'TienThue')

                    // Tạo đối tượng ChiTietHoaDon
                    chiTietHoaDon = new ChiTietHoaDon(maVe, maHD, soLuong, VAT, thanhTien, tienThue);
                }
            }
        }

        return chiTietHoaDon;
    }

    public VeTau timMotVeTauTheoMa(String maVe) throws SQLException {
        String query = """
        SELECT 
            vt.MaVe,
            lt.MaLich AS MaLichTrinh,
            lt.GioDi,
            lt.NgayDi,
            tt.MaTau,
            tt.TenTau,
            tt.SoToa,
            tn.MaTuyen,
            tn.TenTuyen AS TuyenTau,
            tn.GaDi,
            tn.GaDen,
            tn.DiaDiemDi,
            tn.DiaDiemDen,
            cn.MaCho,
            cn.TenCho,
            cn.GiaTien,
            vt.TenKH,
            vt.GiayTo,
            vt.NgayDi AS NgayDiCuaVe,
            vt.DoiTuong,
            vt.GiaVe,
            vt.TrangThai,
            toa.MaToa,
            toa.LoaiToaMaLoai,
            toa.TenToa,
            toa.SoGhe,
            toa.ThuTu,
            loaiToa.TenLoai AS LoaiToa
        FROM VeTau vt
        JOIN LichTrinhTau lt ON vt.LichTrinhTauMaLich = lt.MaLich
        JOIN Tau tt ON lt.MaTau = tt.MaTau
        JOIN TuyenTau tn ON tt.MaTuyen = tn.MaTuyen
        JOIN ChoNgoi cn ON vt.ChoNgoiMaCho = cn.MaCho
        JOIN ToaTau toa ON tt.MaTau = toa.TauMaTau
        JOIN LoaiToa loaiToa ON toa.LoaiToaMaLoai = loaiToa.MaLoai
        WHERE vt.MaVe = ?;
    """;

        VeTau veTau = null;

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, maVe);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Lấy thông tin từ ResultSet
                    String maLichTrinh = rs.getString("MaLichTrinh");
                    LocalTime gioDi = rs.getTime("GioDi").toLocalTime();
                    LocalDate ngayDiLichTrinh = rs.getDate("NgayDi").toLocalDate();
                    String maTau = rs.getString("MaTau");
                    String tenTau = rs.getString("TenTau");
                    int soToa = rs.getInt("SoToa");
                    String maTuyen = rs.getString("MaTuyen");
                    String tenTuyen = rs.getString("TuyenTau");
                    String gaDi = rs.getString("GaDi");
                    String gaDen = rs.getString("GaDen");
                    String diaDiemDi = rs.getString("DiaDiemDi");
                    String diaDiemDen = rs.getString("DiaDiemDen");
                    String maCho = rs.getString("MaCho");
                    String tenCho = rs.getString("TenCho");
                    float giaTien = rs.getFloat("GiaTien");
                    String tenKhachHang = rs.getString("TenKH");
                    String giayTo = rs.getString("GiayTo");
                    LocalDate ngayDiVe = rs.getDate("NgayDiCuaVe").toLocalDate();
                    String doiTuong = rs.getString("DoiTuong");
                    double giaVe = rs.getDouble("GiaVe");
                    String trangThai = rs.getString("TrangThai");

                    // Lấy thông tin về toa tàu và loại toa
                    String maToa = rs.getString("MaToa");
                    String maLoaiToa = rs.getString("LoaiToaMaLoai");
                    String tenToa = rs.getString("TenToa");
                    int soGhe = rs.getInt("SoGhe");
                    int thuTu = rs.getInt("ThuTu");
                    String tenLoaiToa = rs.getString("LoaiToa");

                    // Tạo các đối tượng liên quan
                    TuyenTau tuyenTau = new TuyenTau(maTuyen, tenTuyen, gaDi, gaDen, diaDiemDi, diaDiemDen);
                    Tau tau = new Tau(maTau, tuyenTau, tenTau, soToa);
                    LichTrinhTau lichTrinhTau = new LichTrinhTau(maLichTrinh, gioDi, ngayDiLichTrinh, tau, null);

                    LoaiToa loaiToa = new LoaiToa(maLoaiToa, tenLoaiToa);
                    ToaTau toaTau = new ToaTau(maToa, loaiToa, tau, soGhe, tenToa, thuTu);
                    ChoNgoi choNgoi = new ChoNgoi(maCho, null, toaTau, tenCho, null, giaTien);
                    // Tạo đối tượng VeTau
                    veTau = new VeTau(maVe, lichTrinhTau, choNgoi, tenKhachHang, giayTo, ngayDiVe, doiTuong, giaVe, trangThai);

                }
            }
        }

        return veTau;
    }


    public List<VeTau> timVeTauTheoMa(String maVe) throws SQLException {
        String query = """
                SELECT 
                    vt.MaVe,
                    lt.MaLich AS MaLichTrinh,
                    lt.GioDi,
                    lt.NgayDi,
                    tt.MaTau,
                    tt.TenTau,
                    tt.SoToa,
                    tn.TenTuyen AS TuyenTau,
                    cn.MaCho,
                    cn.TenCho,
                    cn.GiaTien,
                    vt.TenKH,
                    vt.GiayTo,
                    vt.NgayDi AS NgayDiCuaVe,
                    vt.DoiTuong,
                    vt.GiaVe,
                    vt.TrangThai
                FROM VeTau vt
                JOIN LichTrinhTau lt ON vt.LichTrinhTauMaLich = lt.MaLich
                JOIN Tau tt ON lt.MaTau = tt.MaTau
                JOIN TuyenTau tn ON tt.MaTuyen = tn.MaTuyen
                JOIN ChoNgoi cn ON vt.ChoNgoiMaCho = cn.MaCho
                WHERE vt.MaVe = ?;
                """;

        List<VeTau> danhSachVeTau = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, maVe);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Lấy thông tin từ ResultSet
                    String maLichTrinh = rs.getString("MaLichTrinh");
                    LocalTime gioDi = rs.getTime("GioDi").toLocalTime();
                    LocalDate ngayDiLichTrinh = rs.getDate("NgayDi").toLocalDate();
                    String maTau = rs.getString("MaTau");
                    String tenTau = rs.getString("TenTau");
                    int soToa = rs.getInt("SoToa");
                    String tenTuyen = rs.getString("TuyenTau");
                    String maCho = rs.getString("MaCho");
                    String tenCho = rs.getString("TenCho");
                    float giaTien = rs.getFloat("GiaTien");
                    String tenKhachHang = rs.getString("TenKH");
                    String giayTo = rs.getString("GiayTo");
                    LocalDate ngayDiVe = rs.getDate("NgayDiCuaVe").toLocalDate();
                    String doiTuong = rs.getString("DoiTuong");
                    double giaVe = rs.getDouble("GiaVe");
                    String trangThai = rs.getString("TrangThai");

                    // Tạo các đối tượng liên quan
                    TuyenTau tuyenTau = new TuyenTau(null, tenTuyen, null, null, null, null);
                    Tau tau = new Tau(maTau, tuyenTau, tenTau, soToa);
                    LichTrinhTau lichTrinhTau = new LichTrinhTau(maLichTrinh, gioDi, ngayDiLichTrinh, tau, null);
                    ChoNgoi choNgoi = new ChoNgoi(maCho, null, null, tenCho, null, giaTien);

                    // Tạo đối tượng VeTau
                    VeTau veTau = new VeTau(maVe, lichTrinhTau, choNgoi, tenKhachHang, giayTo, ngayDiVe, doiTuong, giaVe, trangThai);

                    // Thêm vào danh sách
                    danhSachVeTau.add(veTau);
                }
            }
        }

        return danhSachVeTau;
    }
    // Tìm vé tàu theo giấy tờ (GiayTo)
    public List<VeTau> timVeTauTheoGiayTo(String giayTo) throws SQLException {
        String query = """
                SELECT 
                    vt.MaVe,
                    lt.MaLich AS MaLichTrinh,
                    lt.GioDi,
                    lt.NgayDi,
                    tt.MaTau,
                    tt.TenTau,
                    tt.SoToa,
                    tn.TenTuyen AS TuyenTau,
                    cn.MaCho,
                    cn.TenCho,
                    cn.GiaTien,
                    vt.TenKH,
                    vt.GiayTo,
                    vt.NgayDi AS NgayDiCuaVe,
                    vt.DoiTuong,
                    vt.GiaVe,
                    vt.TrangThai
                FROM VeTau vt
                JOIN LichTrinhTau lt ON vt.LichTrinhTauMaLich = lt.MaLich
                JOIN Tau tt ON lt.MaTau = tt.MaTau
                JOIN TuyenTau tn ON tt.MaTuyen = tn.MaTuyen
                JOIN ChoNgoi cn ON vt.ChoNgoiMaCho = cn.MaCho
                WHERE vt.GiayTo = ?;
                """;

        List<VeTau> danhSachVeTau = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, giayTo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Lấy thông tin từ ResultSet
                    String maVe = rs.getString("MaVe");
                    String maLichTrinh = rs.getString("MaLichTrinh");
                    LocalTime gioDi = rs.getTime("GioDi").toLocalTime();
                    LocalDate ngayDiLichTrinh = rs.getDate("NgayDi").toLocalDate();
                    String maTau = rs.getString("MaTau");
                    String tenTau = rs.getString("TenTau");
                    int soToa = rs.getInt("SoToa");
                    String tenTuyen = rs.getString("TuyenTau");
                    String maCho = rs.getString("MaCho");
                    String tenCho = rs.getString("TenCho");
                    float giaTien = rs.getFloat("GiaTien");
                    String tenKhachHang = rs.getString("TenKH");
                    String giayToDb = rs.getString("GiayTo");
                    LocalDate ngayDiVe = rs.getDate("NgayDiCuaVe").toLocalDate();
                    String doiTuong = rs.getString("DoiTuong");
                    double giaVe = rs.getDouble("GiaVe");
                    String trangThai = rs.getString("TrangThai");

                    // Tạo các đối tượng liên quan
                    TuyenTau tuyenTau = new TuyenTau(null, tenTuyen, null, null, null, null);
                    Tau tau = new Tau(maTau, tuyenTau, tenTau, soToa);
                    LichTrinhTau lichTrinhTau = new LichTrinhTau(maLichTrinh, gioDi, ngayDiLichTrinh, tau, null);
                    ChoNgoi choNgoi = new ChoNgoi(maCho, null, null, tenCho, null, giaTien);

                    // Tạo đối tượng VeTau
                    VeTau veTau = new VeTau(maVe, lichTrinhTau, choNgoi, tenKhachHang, giayToDb, ngayDiVe, doiTuong, giaVe, trangThai);

                    // Thêm vào danh sách
                    danhSachVeTau.add(veTau);
                }
            }
        }

        return danhSachVeTau;
    }
    public List<VeTau> timVeTauTheoTenKH(String tenKhachHang) throws SQLException {
        String query = """
            SELECT 
                vt.MaVe,
                lt.MaLich AS MaLichTrinh,
                lt.GioDi,
                lt.NgayDi,
                tt.MaTau,
                tt.TenTau,
                tt.SoToa,
                tn.TenTuyen AS TuyenTau,
                cn.MaCho,
                cn.TenCho,
                cn.GiaTien,
                vt.TenKH,
                vt.GiayTo,
                vt.NgayDi AS NgayDiCuaVe,
                vt.DoiTuong,
                vt.GiaVe,
                vt.TrangThai
            FROM VeTau vt
            JOIN LichTrinhTau lt ON vt.LichTrinhTauMaLich = lt.MaLich
            JOIN Tau tt ON lt.MaTau = tt.MaTau
            JOIN TuyenTau tn ON tt.MaTuyen = tn.MaTuyen
            JOIN ChoNgoi cn ON vt.ChoNgoiMaCho = cn.MaCho
            WHERE vt.TenKH = ?;
            """;

        List<VeTau> danhSachVeTau = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, tenKhachHang); // Thiết lập tên khách hàng vào câu truy vấn
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Lấy thông tin từ ResultSet
                    String maVe = rs.getString("MaVe");
                    String maLichTrinh = rs.getString("MaLichTrinh");
                    LocalTime gioDi = rs.getTime("GioDi").toLocalTime();
                    LocalDate ngayDiLichTrinh = rs.getDate("NgayDi").toLocalDate();
                    String maTau = rs.getString("MaTau");
                    String tenTau = rs.getString("TenTau");
                    int soToa = rs.getInt("SoToa");
                    String tenTuyen = rs.getString("TuyenTau");
                    String maCho = rs.getString("MaCho");
                    String tenCho = rs.getString("TenCho");
                    float giaTien = rs.getFloat("GiaTien");
                    String tenKhachHangDb = rs.getString("TenKH");
                    String giayTo = rs.getString("GiayTo");
                    LocalDate ngayDiVe = rs.getDate("NgayDiCuaVe").toLocalDate();
                    String doiTuong = rs.getString("DoiTuong");
                    double giaVe = rs.getDouble("GiaVe");
                    String trangThai = rs.getString("TrangThai");

                    // Tạo các đối tượng liên quan
                    TuyenTau tuyenTau = new TuyenTau(null, tenTuyen, null, null, null, null);
                    Tau tau = new Tau(maTau, tuyenTau, tenTau, soToa);
                    LichTrinhTau lichTrinhTau = new LichTrinhTau(maLichTrinh, gioDi, ngayDiLichTrinh, tau, null);
                    ChoNgoi choNgoi = new ChoNgoi(maCho, null, null, tenCho, null, giaTien);

                    // Tạo đối tượng VeTau
                    VeTau veTau = new VeTau(maVe, lichTrinhTau, choNgoi, tenKhachHangDb, giayTo, ngayDiVe, doiTuong, giaVe, trangThai);

                    // Thêm vào danh sách
                    danhSachVeTau.add(veTau);
                }
            }
        }

        return danhSachVeTau;
    }
    public List<VeTau> timVeTauTheoChitiet(String tenKhachHang, String giayTo, String ngayDi, String maChoNgoi, String doiTuong) throws SQLException {
        // Chuyển đổi chuỗi ngày theo định dạng dd-MM-yyyy thành LocalDate
        Date ngayDiFormatted = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date date = sdf.parse(ngayDi);  // Chuyển đổi chuỗi ngày thành java.util.Date
            ngayDiFormatted = new Date(date.getTime());  // Chuyển đổi java.util.Date thành java.sql.Date
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi nếu không thể chuyển đổi ngày
        }

        // Cập nhật câu truy vấn SQL phù hợp với tên cột đúng
        String query = """
        SELECT 
            vt.MaVe,
            lt.MaLich AS MaLichTrinh,
            lt.GioDi,
            lt.NgayDi,
            tt.MaTau,
            tt.TenTau,
            tt.SoToa,
            tn.TenTuyen AS TuyenTau,
            cn.MaCho,
            cn.TenCho,
            cn.GiaTien,
            vt.TenKH,
            vt.GiayTo,
            vt.NgayDi AS NgayDiCuaVe,
            vt.DoiTuong,
            vt.GiaVe,
            vt.TrangThai
        FROM VeTau vt
        JOIN LichTrinhTau lt ON vt.LichTrinhTauMaLich = lt.MaLich
        JOIN Tau tt ON lt.MaTau = tt.MaTau
        JOIN TuyenTau tn ON tt.MaTuyen = tn.MaTuyen
        JOIN ChoNgoi cn ON vt.ChoNgoiMaCho = cn.MaCho
        WHERE vt.TenKH = ? AND vt.GiayTo = ? AND vt.NgayDi = ? AND vt.ChoNgoiMaCho = ? AND vt.DoiTuong = ?;
    """;

        List<VeTau> veTauList = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(query)) {
            // Thiết lập giá trị cho các tham số trong câu truy vấn
            ps.setString(1, tenKhachHang);
            ps.setString(2, giayTo);
            ps.setDate(3, ngayDiFormatted);  // Gửi ngày đã chuyển đổi dưới dạng java.sql.Date
            ps.setString(4, maChoNgoi);
            ps.setString(5, doiTuong);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Lấy thông tin từ ResultSet
                    String maVe = rs.getString("MaVe");
                    String maLichTrinh = rs.getString("MaLichTrinh");
                    LocalTime gioDi = rs.getTime("GioDi").toLocalTime();
                    LocalDate ngayDiLichTrinh = rs.getDate("NgayDi").toLocalDate();
                    String maTau = rs.getString("MaTau");
                    String tenTau = rs.getString("TenTau");
                    int soToa = rs.getInt("SoToa");
                    String tenTuyen = rs.getString("TuyenTau");
                    String maCho = rs.getString("MaCho");
                    String tenCho = rs.getString("TenCho");
                    float giaTien = rs.getFloat("GiaTien");
                    String tenKhachHangDb = rs.getString("TenKH");
                    String giayToDb = rs.getString("GiayTo");
                    LocalDate ngayDiVe = rs.getDate("NgayDiCuaVe").toLocalDate();
                    String doiTuongDb = rs.getString("DoiTuong");
                    double giaVe = rs.getDouble("GiaVe");
                    String trangThai = rs.getString("TrangThai");

                    // Tạo các đối tượng liên quan
                    TuyenTau tuyenTau = new TuyenTau(null, tenTuyen, null, null, null, null);
                    Tau tau = new Tau(maTau, tuyenTau, tenTau, soToa);
                    LichTrinhTau lichTrinhTau = new LichTrinhTau(maLichTrinh, gioDi, ngayDiLichTrinh, tau, null);
                    ChoNgoi choNgoi = new ChoNgoi(maCho, null, null, tenCho, null, giaTien);

                    // Tạo đối tượng VeTau
                    VeTau veTau = new VeTau(maVe, lichTrinhTau, choNgoi, tenKhachHangDb, giayToDb, ngayDiVe, doiTuongDb, giaVe, trangThai);

                    // Thêm vào danh sách
                    veTauList.add(veTau);
                }
            }
        }

        return veTauList;
    }
    public List<VeTau> timVeTauTheoTenKHVaThoiGian(String hoTen, String strNgayDiFrom, String strNgayDiTo) throws SQLException {
        // Chuyển đổi chuỗi ngày thành LocalDate
        LocalDate ngayDiFrom = LocalDate.parse(strNgayDiFrom);
        LocalDate ngayDiTo = LocalDate.parse(strNgayDiTo);

        // Chuyển LocalDate thành java.sql.Date
        Date ngayDiFromFormatted = Date.valueOf(ngayDiFrom);
        Date ngayDiToFormatted = Date.valueOf(ngayDiTo);

        // Cập nhật câu truy vấn SQL
        String query = """
        SELECT 
            vt.MaVe,
            lt.MaLich AS MaLichTrinh,
            lt.GioDi,
            lt.NgayDi,
            tt.MaTau,
            tt.TenTau,
            tt.SoToa,
            tn.TenTuyen AS TuyenTau,
            cn.MaCho,
            cn.TenCho,
            cn.GiaTien,
            vt.TenKH,
            vt.GiayTo,
            vt.NgayDi AS NgayDiCuaVe,
            vt.DoiTuong,
            vt.GiaVe,
            vt.TrangThai
        FROM VeTau vt
        JOIN LichTrinhTau lt ON vt.LichTrinhTauMaLich = lt.MaLich
        JOIN Tau tt ON lt.MaTau = tt.MaTau
        JOIN TuyenTau tn ON tt.MaTuyen = tn.MaTuyen
        JOIN ChoNgoi cn ON vt.ChoNgoiMaCho = cn.MaCho
        WHERE vt.TenKH = ? AND vt.NgayDi BETWEEN ? AND ?;
    """;

        List<VeTau> veTauList = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(query)) {
            // Thiết lập giá trị cho các tham số trong câu truy vấn
            ps.setString(1, hoTen);  // Tên khách hàng
            ps.setDate(2, ngayDiFromFormatted);  // Ngày đi từ (định dạng java.sql.Date)
            ps.setDate(3, ngayDiToFormatted);    // Ngày đi đến (định dạng java.sql.Date)

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Lấy thông tin từ ResultSet
                    String maVe = rs.getString("MaVe");
                    String maLichTrinh = rs.getString("MaLichTrinh");
                    LocalTime gioDi = rs.getTime("GioDi").toLocalTime();
                    LocalDate ngayDiLichTrinh = rs.getDate("NgayDi").toLocalDate();
                    String maTau = rs.getString("MaTau");
                    String tenTau = rs.getString("TenTau");
                    int soToa = rs.getInt("SoToa");
                    String tenTuyen = rs.getString("TuyenTau");
                    String maCho = rs.getString("MaCho");
                    String tenCho = rs.getString("TenCho");
                    float giaTien = rs.getFloat("GiaTien");
                    String tenKhachHangDb = rs.getString("TenKH");
                    String giayToDb = rs.getString("GiayTo");
                    LocalDate ngayDiVe = rs.getDate("NgayDiCuaVe").toLocalDate();
                    String doiTuongDb = rs.getString("DoiTuong");
                    double giaVe = rs.getDouble("GiaVe");
                    String trangThai = rs.getString("TrangThai");

                    // Tạo các đối tượng liên quan
                    TuyenTau tuyenTau = new TuyenTau(null, tenTuyen, null, null, null, null);
                    Tau tau = new Tau(maTau, tuyenTau, tenTau, soToa);
                    LichTrinhTau lichTrinhTau = new LichTrinhTau(maLichTrinh, gioDi, ngayDiLichTrinh, tau, null);
                    ChoNgoi choNgoi = new ChoNgoi(maCho, null, null, tenCho, null, giaTien);

                    // Tạo đối tượng VeTau
                    VeTau veTau = new VeTau(maVe, lichTrinhTau, choNgoi, tenKhachHangDb, giayToDb, ngayDiVe, doiTuongDb, giaVe, trangThai);

                    // Thêm vào danh sách
                    veTauList.add(veTau);
                }
            }
        }

        return veTauList;
    }




}