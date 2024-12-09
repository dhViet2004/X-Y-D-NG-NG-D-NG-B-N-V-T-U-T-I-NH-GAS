package DAO;

import Database.ConnectDatabase;
import Entity.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DAO_BanVe {
    private Connection con;
    public DAO_BanVe() {
        con = ConnectDatabase.getConnection();
    }

    public ChoNgoi getChoNgoiByMaCho(String maCho) throws SQLException {
        ChoNgoi choNgoi = null;

        // Câu truy vấn SQL
        String sql = "SELECT c.MaCho, c.LoaiChoMaLoai, c.LoaiToaMaToa, c.TenCho, c.TinhTrang, c.GiaTien, " +
                "t.MaToa, t.TenToa, t.SoGhe, t.ThuTu, " +
                "tau.MaTau, tau.TenTau, tau.SoToa, " +
                "tt.MaTuyen, tt.TenTuyen, tt.GaDi, tt.GaDen, tt.DiaDiemDi, tt.DiaDiemDen, " +
                "lt.MaLoai, lt.TenLoai " +
                "FROM ChoNgoi c " +
                "JOIN ToaTau t ON c.LoaiToaMaToa = t.MaToa " +
                "JOIN Tau tau ON t.TauMaTau = tau.MaTau " +
                "JOIN TuyenTau tt ON tau.MaTuyen = tt.MaTuyen " +
                "JOIN LoaiToa lt ON t.LoaiToaMaLoai = lt.MaLoai " +
                "WHERE c.MaCho = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, maCho); // Gán tham số mã chỗ vào câu truy vấn
            ResultSet rs = pstmt.executeQuery();

            // Kiểm tra nếu có kết quả trả về
            if (rs.next()) {
                // Lấy thông tin chỗ ngồi
                String loaiChoMaLoai = rs.getString("LoaiChoMaLoai");
                String tenCho = rs.getString("TenCho");
                boolean tinhTrang = rs.getBoolean("TinhTrang");
                float giaTien = rs.getFloat("GiaTien");

                // Lấy thông tin toa tàu
                String maToa = rs.getString("MaToa");
                String tenToa = rs.getString("TenToa");
                int soGhe = rs.getInt("SoGhe");
                int thuTu = rs.getInt("ThuTu");

                // Lấy thông tin tàu
                String maTau = rs.getString("MaTau");
                String tenTau = rs.getString("TenTau");
                int soToa = rs.getInt("SoToa");

                // Lấy thông tin tuyến tàu
                String maTuyen = rs.getString("MaTuyen");
                String tenTuyen = rs.getString("TenTuyen");
                String gaDi = rs.getString("GaDi");
                String gaDen = rs.getString("GaDen");
                String diaDiemDi = rs.getString("DiaDiemDi");
                String diaDiemDen = rs.getString("DiaDiemDen");

                // Lấy thông tin loại toa
                String maLoaiToa = rs.getString("MaLoai");
                String tenLoaiToa = rs.getString("TenLoai");

                // Tạo các đối tượng liên kết
                TuyenTau tuyenTau = new TuyenTau(maTuyen, tenTuyen, gaDi, gaDen, diaDiemDi, diaDiemDen);
                Tau tau = new Tau(maTau, tuyenTau, tenTau, soToa);
                LoaiToa loaiToa = new LoaiToa(maLoaiToa, tenLoaiToa);
                ToaTau toaTau = new ToaTau(maToa, loaiToa, tau, soGhe, tenToa, thuTu);
                LoaiCho loaiCho = new LoaiCho(loaiChoMaLoai); // Giả định LoaiCho chỉ có mã

                // Tạo đối tượng chỗ ngồi
                choNgoi = new ChoNgoi(maCho, loaiCho, toaTau, tenCho, tinhTrang, giaTien);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching seat information by MaCho", e);
        }

        return choNgoi; // Trả về đối tượng ChoNgoi hoặc null nếu không tìm thấy
    }
    public boolean isTicketCodeExists(String maVe) throws SQLException {
        // Câu truy vấn SQL để kiểm tra xem mã vé có tồn tại trong cơ sở dữ liệu không
        String query = "SELECT COUNT(*) FROM VeTau WHERE maVe = ?";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, maVe);  // Thiết lập tham số cho câu truy vấn
            ResultSet rs = ps.executeQuery();  // Thực thi câu truy vấn

            // Nếu có kết quả trả về, kiểm tra số lượng
            if (rs.next()) {
                return rs.getInt(1) > 0;  // Nếu có ít nhất 1 kết quả, tức là mã vé đã tồn tại
            }
        } catch (SQLException e) {
            e.printStackTrace();  // In ra lỗi nếu có
        }
        return false;  // Nếu không có mã vé nào trùng, trả về false
    }


    public int getTotalTicketsToday() {
        int totalTickets = 0; // Khởi tạo biến tổng số vé

        // Câu truy vấn SQL để đếm số lượng vé trong ngày hôm nay
        String sql = "SELECT COUNT(*) AS TongVe FROM VeTau WHERE CAST(NgayDi AS DATE) = CAST(GETDATE() AS DATE)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery(); // Thực thi câu truy vấn

            if (rs.next()) {
                totalTickets = rs.getInt("TongVe"); // Lấy tổng số vé
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalTickets; // Trả về tổng số vé
    }

    public int getTotalCustomersToday() {
        int totalCustomers = 0; // Khởi tạo biến tổng số khách hàng

        // Câu truy vấn SQL để đếm số lượng khách hàng tham gia hôm nay
        String sql = "SELECT COUNT(*) AS TongKhachHang FROM KhachHang WHERE CAST(NgayThamGia AS DATE) = CAST(GETDATE() AS DATE)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery(); // Thực thi câu truy vấn

            if (rs.next()) {
                totalCustomers = rs.getInt("TongKhachHang"); // Lấy tổng số khách hàng
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalCustomers; // Trả về tổng số khách hàng
    }

    public int getTotalInvoicesByDate() {
        int totalInvoices = 0; // Khởi tạo biến tổng hóa đơn

        // Câu truy vấn SQL để đếm tổng hóa đơn theo ngày hiện tại
        String sql = "SELECT COUNT(*) AS TongHoaDon FROM HoaDon WHERE CAST(NgayHoaDon AS DATE) = CAST(GETDATE() AS DATE)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery(); // Thực thi câu truy vấn

            if (rs.next()) {
                totalInvoices = rs.getInt("TongHoaDon"); // Lấy giá trị tổng hóa đơn
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalInvoices; // Trả về tổng hóa đơn
    }

    public List<LoaiKhachHang> getAllLoaiKhachHang() throws SQLException {
        List<LoaiKhachHang> danhSachLoaiKH = new ArrayList<>();

        // Câu truy vấn SQL
        String sql = "SELECT MaLoaiKH, TenLoaiKH FROM LoaiKhachHang";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Duyệt qua kết quả trả về
            while (rs.next()) {
                String maLoai = rs.getString("MaLoaiKH");
                String tenLoai = rs.getString("TenLoaiKH");

                // Tạo đối tượng LoaiKhachHang và thêm vào danh sách
                LoaiKhachHang loaiKH = new LoaiKhachHang(maLoai, tenLoai);
                danhSachLoaiKH.add(loaiKH);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching all customer types", e);
        }

        return danhSachLoaiKH; // Trả về danh sách các loại khách hàng
    }
    public KhuyenMai findKhuyenMaiByMa(String maKM) throws SQLException {
        String query = "SELECT [MaKM], [ThoiGianBatDau], [ThoiGianKetThuc], [NoiDungKM], [ChietKhau], [DoiTuongApDung] " +
                "FROM [UngDungQuanLyBanVeTaiGaGoVap].[dbo].[KhuyenMai] " +
                "WHERE [MaKM] = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, maKM);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String maKMResult = rs.getString("MaKM");
                LocalDate thoiGianBatDau = rs.getDate("ThoiGianBatDau").toLocalDate();
                LocalDate thoiGianKetThuc = rs.getDate("ThoiGianKetThuc").toLocalDate();
                String noiDungKM = rs.getString("NoiDungKM");
                double chietKhau = rs.getDouble("ChietKhau");
                String doiTuongApDung = rs.getString("DoiTuongApDung");

                return new KhuyenMai(maKMResult, thoiGianBatDau, thoiGianKetThuc, noiDungKM, chietKhau, doiTuongApDung);
            }
        }
        return null; // Trả về null nếu không tìm thấy mã khuyến mãi
    }

    public KhachHang getKhachHangBySoDienThoai(String soDienThoai) throws SQLException {
        KhachHang khachHang = null;

        // Câu truy vấn SQL để tìm khách hàng theo số điện thoại
        String sql = "SELECT MaKH, LoaiKhachHangMaLoaiKH, SoDT, TenKH, CCCD, DiaChi, " +
                "DiemTichLuy, NgaySinh, NgayThamGia, HangThanhVien " +
                "FROM KhachHang WHERE SoDT = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, soDienThoai); // Gán số điện thoại vào câu truy vấn
            ResultSet rs = pstmt.executeQuery();

            // Kiểm tra nếu có kết quả trả về
            if (rs.next()) {
                String maKH = rs.getString("MaKH");
                String loaiKH = rs.getString("LoaiKhachHangMaLoaiKH");
                String tenKH = rs.getString("TenKH");
                String cccd = rs.getString("CCCD");
                String diaChi = rs.getString("DiaChi");
                double diemTichLuy = rs.getDouble("DiemTichLuy");
                LocalDate ngaySinh = rs.getDate("NgaySinh").toLocalDate();
                LocalDate ngayThamGia = rs.getDate("NgayThamGia").toLocalDate();
                String hangThanhVien = rs.getString("HangThanhVien");

                // Tạo đối tượng LoaiKhachHang (giả sử bạn có constructor phù hợp)
                LoaiKhachHang loaiKhachHang = new LoaiKhachHang(loaiKH);

                // Tạo đối tượng KhachHang
                khachHang = new KhachHang(maKH, loaiKhachHang, soDienThoai, tenKH,
                        cccd, diaChi, diemTichLuy, ngaySinh,
                        ngayThamGia, hangThanhVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching customer by phone number", e);
        }

        return khachHang; // Trả về đối tượng KhachHang hoặc null nếu không tìm thấy
    }

    public Tau getTauByMaToa(String maToa) throws SQLException {
        Tau tau = null;

        // Câu truy vấn SQL
        String sql = "SELECT t.MaTau, t.TenTau, t.SoToa, tt.MaTuyen, tt.TenTuyen, tt.GaDi, tt.GaDen, tt.DiaDiemDi, tt.DiaDiemDen " +
                "FROM Tau t " +
                "JOIN ToaTau toa ON t.MaTau = toa.TauMaTau " +
                "JOIN TuyenTau tt ON t.MaTuyen = tt.MaTuyen " +
                "WHERE toa.MaToa = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, maToa); // Gán tham số mã toa vào câu truy vấn
            ResultSet rs = pstmt.executeQuery();

            // Kiểm tra nếu có kết quả trả về
            if (rs.next()) {
                String maTau = rs.getString("MaTau");
                String tenTau = rs.getString("TenTau");
                int soToa = rs.getInt("SoToa");

                // Lấy thông tin tuyến tàu
                String maTuyen = rs.getString("MaTuyen");
                String tenTuyen = rs.getString("TenTuyen");
                String gaDi = rs.getString("GaDi");
                String gaDen = rs.getString("GaDen");
                String diaDiemDi = rs.getString("DiaDiemDi");
                String diaDiemDen = rs.getString("DiaDiemDen");

                // Tạo đối tượng TuyenTau
                TuyenTau tuyenTau = new TuyenTau(tenTuyen, maTuyen, gaDi, gaDen, diaDiemDi, diaDiemDen);

                // Tạo đối tượng Tau
                tau = new Tau(maTau, tuyenTau, tenTau, soToa);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching train information by MaToa", e);
        }

        return tau; // Trả về đối tượng Tau hoặc null nếu không tìm thấy
    }

    public TuyenTau getTuyenTauByMaCho(String maCho) throws SQLException {
        TuyenTau tuyenTau = null;

        // Câu lệnh truy vấn SQL
        String sql = "SELECT tt.MaTuyen, tt.TenTuyen, tt.GaDi, tt.GaDen, tt.DiaDiemDi, tt.DiaDiemDen " +
                "FROM ChoNgoi cho " +
                "JOIN ToaTau toa ON cho.LoaiToaMaToa = toa.MaToa " +
                "JOIN Tau tau ON toa.TauMaTau = tau.MaTau " +
                "JOIN TuyenTau tt ON tau.MaTuyen = tt.MaTuyen " +
                "WHERE cho.MaCho = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, maCho); // Gán tham số mã chỗ vào câu truy vấn
            ResultSet rs = pstmt.executeQuery();

            // Kiểm tra nếu có kết quả trả về
            if (rs.next()) {
                String maTuyen = rs.getString("MaTuyen");
                String tenTuyen = rs.getString("TenTuyen");
                String gaDi = rs.getString("GaDi");
                String gaDen = rs.getString("GaDen");
                String diaDiemDi = rs.getString("DiaDiemDi");
                String diaDiemDen = rs.getString("DiaDiemDen");

                // Tạo đối tượng TuyenTau
                tuyenTau = new TuyenTau(tenTuyen, maTuyen, gaDi, gaDen, diaDiemDi, diaDiemDen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching train line information", e);
        }

        return tuyenTau; // Trả về đối tượng TuyenTau hoặc null nếu không tìm thấy
    }

    public void saveInvoice(HoaDon hoaDon) throws SQLException {
        // Đảm bảo mã loại hóa đơn tồn tại
        String checkSql = "SELECT COUNT(*) FROM LoaiHoaDon WHERE MaLoai = ?";
        try (PreparedStatement checkStmt = con.prepareStatement(checkSql)) {
            checkStmt.setString(1, hoaDon.getLoaiHoaDon().getMaLoaiHoaDon());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                // Nếu mã loại hóa đơn không tồn tại, thêm nó
                String insertSql = "INSERT INTO LoaiHoaDon (MaLoai, TenLoai) VALUES (?, ?)";
                try (PreparedStatement insertStmt = con.prepareStatement(insertSql)) {
                    insertStmt.setString(1, hoaDon.getLoaiHoaDon().getMaLoaiHoaDon());
                    insertStmt.setString(2, "Tên loại hóa đơn"); // Đặt tên loại hóa đơn cho phù hợp
                    insertStmt.executeUpdate();
                }
            }
        }

        String sql = "INSERT INTO HoaDon (MaHD, MaKH, KhuyenMaiMaKM, NhanVienMaNV, MaLoai, NgayHoaDon, TienKhuyenMai, TongTien) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, hoaDon.getMaHD());
            pstmt.setString(2, hoaDon.getKhachHang().getMaKhachHang());
            pstmt.setString(3, hoaDon.getKhuyenMai() != null ? hoaDon.getKhuyenMai().getMaKM() : null);
            pstmt.setString(4, hoaDon.getNv() != null ? hoaDon.getNv().getMaNhanVien() : null);
            pstmt.setString(5, hoaDon.getLoaiHoaDon().getMaLoaiHoaDon());

            // Chuyển LocalDateTime thành Timestamp
            Timestamp timestamp = Timestamp.valueOf(hoaDon.getNgayLap());
            pstmt.setTimestamp(6, timestamp); // Dùng setTimestamp thay vì setDate

            pstmt.setDouble(7, hoaDon.getTienGiam());
            pstmt.setDouble(8, hoaDon.getTongTien());
            pstmt.executeUpdate();
        }

    }




    public void saveInvoiceDetail(ChiTietHoaDon chiTiet) throws SQLException {
        String sql = "INSERT INTO ChiTietHoaDon (MaVe, MaHD, SoLuong, VAT, ThanhTien, TenThue) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, chiTiet.getMaVe());
            pstmt.setString(2, chiTiet.getMaHD()); // Đảm bảo MaHD đã được lưu trước
            pstmt.setInt(3, chiTiet.getSoLuong());
            pstmt.setDouble(4, chiTiet.getVAT());
            pstmt.setDouble(5, chiTiet.getThanhTien());
            pstmt.setDouble(6, chiTiet.getTienThue());
            pstmt.executeUpdate();
        }
    }
    // Thêm khách hàng mới
    public boolean saveCustomer(KhachHang kh) throws SQLException {
        String sql = "INSERT INTO KhachHang (MaKH, LoaiKhachHangMaLoaiKH, SoDT, TenKH, CCCD, DiaChi, DiemTichLuy, NgaySinh, NgayThamGia, HangThanhVien) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, kh.getMaKhachHang());
            pstmt.setString(2, kh.getLoaiKhachHang().getMaLoaiKhachHang());
            pstmt.setString(3, kh.getSoDienThoai());
            pstmt.setString(4, kh.getTenKhachHang());
            pstmt.setString(5, kh.getCCCD());
            pstmt.setString(6, kh.getDiaChi());
            pstmt.setDouble(7, kh.getDiemTichLuy());
            pstmt.setDate(8, Date.valueOf(kh.getNgaySinh()));
            pstmt.setDate(9, Date.valueOf(kh.getNgayThamgGia()));
            pstmt.setString(10, kh.getHangThanhVien());
            return pstmt.executeUpdate() > 0;
        }
    }

    // Lấy thông tin khách hàng theo mã
    public KhachHang getCustomerById(String maKH) throws SQLException {
        String sql = "SELECT * FROM KhachHang WHERE MaKH = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, maKH);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    LoaiKhachHang loaiKH = new LoaiKhachHang(rs.getString("LoaiKhachHangMaLoaiKH"));
                    return new KhachHang(
                            rs.getString("MaKH"),
                            loaiKH,
                            rs.getString("SoDT"),
                            rs.getString("TenKH"),
                            rs.getString("CCCD"),
                            rs.getString("DiaChi"),
                            rs.getDouble("DiemTichLuy"),
                            rs.getDate("NgaySinh").toLocalDate(),
                            rs.getDate("NgayThamGia").toLocalDate(),
                            rs.getString("HangThanhVien")
                    );
                }
            }
        }
        return null; // Nếu không tìm thấy khách hàng
    }

    // Kiểm tra khách hàng có tồn tại hay không
    public boolean checkCustomerExists(String maKH) throws SQLException {
        String sql = "SELECT COUNT(*) FROM KhachHang WHERE MaKH = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, maKH);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    public void saveTickets(List<VeTau> tickets) throws SQLException {
        String sql = "INSERT INTO VeTau (MaVe, LichTrinhTauMaLich, ChoNgoiMaCho, TenKH, GiayTo, NgayDi, DoiTuong, GiaVe, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            for (VeTau ticket : tickets) {
                pstmt.setString(1, ticket.getMaVe());
                pstmt.setString(2, ticket.getLichTrinhTau().getMaLichTrinh());
                pstmt.setString(3, ticket.getChoNgoi().getMaCho());
                pstmt.setString(4, ticket.getTenKhachHang());
                pstmt.setString(5, ticket.getGiayTo());
                pstmt.setDate(6, Date.valueOf(ticket.getNgayDi()));
                pstmt.setString(7, ticket.getDoiTuong());
                pstmt.setDouble(8, ticket.getGiaVe());
                pstmt.setString(9, ticket.getTrangThai());
                pstmt.addBatch(); // Thêm vào batch
            }
            pstmt.executeBatch(); // Thực hiện batch
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error saving tickets to the database", e);
        }
    }

    public Tau getThongTin(String maTau) throws SQLException {
        Tau tau = null;

        // Câu truy vấn SQL để lấy thông tin tàu dựa vào mã tàu
        String sql = "SELECT t.MaTau, t.TenTau, t.SoToa, tt.MaTuyen, tt.TenTuyen, tt.GaDi, tt.GaDen, tt.DiaDiemDi, tt.DiaDiemDen " +
                "FROM Tau t " +
                "JOIN TuyenTau tt ON t.MaTuyen = tt.MaTuyen " +
                "WHERE t.MaTau = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, maTau); // Gán tham số mã tàu vào câu truy vấn
            ResultSet rs = pstmt.executeQuery();

            // Kiểm tra nếu có kết quả trả về
            if (rs.next()) {
                // Lấy thông tin từ kết quả truy vấn
                String tenTau = rs.getString("TenTau");
                int soToa = rs.getInt("SoToa");

                // Lấy thông tin tuyến tàu
                String maTuyen = rs.getString("MaTuyen");
                String tenTuyen = rs.getString("TenTuyen");
                String gaDi = rs.getString("GaDi");
                String gaDen = rs.getString("GaDen");
                String diaDiemDi = rs.getString("DiaDiemDi");
                String diaDiemDen = rs.getString("DiaDiemDen");

                // Tạo đối tượng TuyenTau
                TuyenTau tuyenTau = new TuyenTau(tenTuyen, maTuyen, gaDi, gaDen, diaDiemDi, diaDiemDen);

                // Tạo đối tượng Tau
                tau = new Tau(maTau, tuyenTau, tenTau, soToa);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching train information", e);
        }

        return tau; // Trả về đối tượng Tau hoặc null nếu không tìm thấy
    }

    public VeTau getVeTaubyLichTrinhTauandMaCho(String lichTrinhTau, String maCho) {
        VeTau veTau = null;

        // Câu lệnh truy vấn
        String sql = "SELECT vt.MaVe, lt.MaLich, lt.MaTau, lt.GioDi, lt.NgayDi, vt.ChoNgoiMaCho, vt.TenKH, vt.GiayTo, vt.DoiTuong, vt.GiaVe, vt.TrangThai " +
                "FROM VeTau vt " +
                "JOIN LichTrinhTau lt ON vt.LichTrinhTauMaLich = lt.MaLich " +
                //đổi AND vt.TrangThai = N'Đã thanh toán'
                "WHERE vt.LichTrinhTauMaLich = ? AND vt.ChoNgoiMaCho = ? AND vt.TrangThai = N'Đã thanh toán'";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, lichTrinhTau);
            pstmt.setString(2, maCho);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String maVe = rs.getString("MaVe");
                String tenKH = rs.getString("TenKH");
                String giayTo = rs.getString("GiayTo");
                LocalDate ngayDi = rs.getDate("NgayDi").toLocalDate(); // Thay đổi nếu ngày di là cột khác
                String doiTuong = rs.getString("DoiTuong");
                double giaVe = rs.getDouble("GiaVe");
                String trangThai = rs.getString("TrangThai");

                // Tạo đối tượng LichTrinhTau
                String maLich = rs.getString("MaLich");
                String maTau = rs.getString("MaTau");
                LocalTime gioDi = rs.getTimestamp("GioDi").toLocalDateTime().toLocalTime();
                LocalDate ngayDiLichTau = rs.getDate("NgayDi").toLocalDate(); // Thay đổi nếu ngày đi lịch tàu là cột khác

                LichTrinhTau lichTrinhTau1 = new LichTrinhTau(maLich,gioDi,ngayDiLichTau,new Tau(maTau,null,null,0),null );

                // Tạo đối tượng ChoNgoi
                ChoNgoi choNgoi = new ChoNgoi(maCho);

                // Tạo đối tượng VeTau
                veTau = new VeTau(maVe, lichTrinhTau1, choNgoi, tenKH, giayTo, ngayDi, doiTuong, giaVe, trangThai);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching ticket information", e);
        }

        return veTau;
    }

    // Phương thức lấy thông tin tàu từ ngày đi và ga đến, lưu vào entity LichTrinhTau
    public List<LichTrinhTau> getTrainsByDateAndDestination(String destination, String date) throws SQLException {
        List<LichTrinhTau> lichTrinhList = new ArrayList<>();

        // Câu truy vấn SQL
        String sql = "SELECT lt.MaLich, t.MaTau, lt.GioDi, lt.NgayDi, t.TenTau, t.SoToa, tt.MaTuyen, tt.TenTuyen, tt.GaDi, tt.GaDen, tt.DiaDiemDi, tt.DiaDiemDen " +
                "FROM Tau t " +
                "JOIN TuyenTau tt ON t.MaTuyen = tt.MaTuyen " +
                "JOIN LichTrinhTau lt ON t.MaTau = lt.MaTau " +
                "WHERE tt.GaDen LIKE ? " +
                "AND lt.NgayDi = ?";

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + destination + "%");
            pstmt.setString(2, date);
            ResultSet rs = pstmt.executeQuery();

            // Lưu thông tin lịch trình tàu vào danh sách
            while (rs.next()) {
                // Lấy thông tin lịch trình
                String tenTau = rs.getString("TenTau");
                String maLich = rs.getString("MaLich");
                String maTau = rs.getString("MaTau");
                LocalTime gioDi = rs.getTimestamp("GioDi").toLocalDateTime().toLocalTime();
                LocalDate ngayDi = rs.getDate("NgayDi").toLocalDate(); // Chuyển đổi từ java.sql.Date sang java.time.LocalDate

                // Tạo đối tượng LichTrinhTau
                LichTrinhTau lichTrinhTau = new LichTrinhTau(maLich,gioDi, ngayDi,new Tau(maTau,null,tenTau,0),null );

                // Thêm đối tượng LichTrinhTau vào danh sách
                lichTrinhList.add(lichTrinhTau);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching train schedule data", e);
        }

        return lichTrinhList; // Trả về danh sách các lịch trình tàu
    }


    //Phuương thức lấy danh sach Toa theo ma Tau
    public List<ToaTau> getToaByMaTau(String maTau) throws SQLException {
        List<ToaTau> toaList = new ArrayList<>();

        // Câu truy vấn SQL
        String sql = "SELECT tt.MaToa, lt.MaLoai, lt.TenLoai, tt.TenToa, tt.SoGhe, tt.ThuTu, t.MaTau, t.TenTau, t.SoToa, tu.MaTuyen, tu.TenTuyen " +
                "FROM ToaTau tt " +
                "JOIN LoaiToa lt ON tt.LoaiToaMaLoai = lt.MaLoai " +
                "JOIN Tau t ON tt.TauMaTau = t.MaTau " +
                "JOIN TuyenTau tu ON t.MaTuyen = tu.MaTuyen " +
                "WHERE t.MaTau = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, maTau); // Gán tham số mã tàu vào câu truy vấn
            ResultSet rs = pstmt.executeQuery();

            // Lặp qua kết quả trả về từ câu truy vấn
            while (rs.next()) {
                // Lấy thông tin loại toa
                String maLoai = rs.getString("MaLoai");
                String tenLoai = rs.getString("TenLoai");
                LoaiToa loaiToa = new LoaiToa(maLoai, tenLoai);

                // Lấy thông tin tàu
                String maTauResult = rs.getString("MaTau");
                String tenTau = rs.getString("TenTau");
                int soToa = rs.getInt("SoToa");

                // Lấy thông tin tuyến tàu
                String maTuyen = rs.getString("MaTuyen");
                String tenTuyen = rs.getString("TenTuyen");
                TuyenTau tuyenTau = new TuyenTau(tenTuyen, maTuyen, null, null, null, null); // Các thông tin khác của tuyến tàu có thể bỏ qua

                // Tạo đối tượng Tau
                Tau tau = new Tau(maTau,tuyenTau,tenTau, soToa);

                // Lấy thông tin toa tàu
                String maToa = rs.getString("MaToa");
                String tenToa = rs.getString("TenToa");
                int soGhe = rs.getInt("SoGhe");
                int thuTu = rs.getInt("ThuTu");

                // Tạo đối tượng ToaTau và thêm vào danh sách
                ToaTau toaTau = new ToaTau(maToa, loaiToa, tau, soGhe, tenToa, thuTu);
                toaList.add(toaTau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching ToaTau data", e);
        }
        return toaList;
    }



    public List<ChoNgoi> getSeatsByMaToa(String maToa) throws SQLException {
        List<ChoNgoi> seatsList = new ArrayList<>();

        // Câu truy vấn SQL
        String sql = "SELECT cho.MaCho, cho.LoaiChoMaLoai, toa.MaToa, cho.TenCho, cho.TinhTrang, cho.GiaTien " +
                "FROM ChoNgoi cho " +
                "JOIN LoaiCho loai ON cho.LoaiChoMaLoai = loai.MaLoai " +
                "JOIN ToaTau toa ON cho.LoaiToaMaToa = toa.MaToa " +
                "WHERE toa.MaToa = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, maToa); // Gán tham số mã toa vào câu truy vấn
            ResultSet rs = pstmt.executeQuery();

            // Lặp qua kết quả trả về từ câu truy vấn
            while (rs.next()) {
                String maCho = rs.getString("MaCho");
                String maLoai = rs.getString("LoaiChoMaLoai");
                String maToaTau  = rs.getString("MaToa");
                String tenCho = rs.getString("TenCho");
                Boolean tinhTrang = rs.getBoolean("TinhTrang");
                float gia = rs.getFloat("GiaTien");

                // Tạo đối tượng LoaiCho
                LoaiCho loaiCho = new LoaiCho(maLoai);
                ToaTau toaTau = new ToaTau(maToaTau);
                // Tạo đối tượng ChoNgoi và thêm vào danh sách
                ChoNgoi choNgoi = new ChoNgoi(maCho, loaiCho, toaTau, tenCho, tinhTrang, gia);
                seatsList.add(choNgoi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching ChoNgoi data", e);
        }

        return seatsList;
    }



}
