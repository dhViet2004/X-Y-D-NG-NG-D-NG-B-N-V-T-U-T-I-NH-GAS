package DAO;

import Database.ConnectDatabase;
import Entity.KhachHang;
import Entity.LoaiKhachHang;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class DAO_KhachHang {
    private Connection con;

    public DAO_KhachHang() throws SQLException {
        if (ConnectDatabase.getConnection() == null) {
            ConnectDatabase.getInstance().connect();  // Kết nối nếu chưa kết nối
        }
        con = ConnectDatabase.getConnection();
    }

    public List<KhachHang> getAllList() throws SQLException {
        List<KhachHang> ds = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang";
        DAO_KhachHang dao = new DAO_KhachHang();

        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            //           / Lấy mã loại khách hàng từ ResultSet
            String maLoaiKhachHang = rs.getString("LoaiKhachHangMaLoaiKH");
            String tenLoaiKhachHang = "";

            // Kiểm tra mã loại khách hàng và đặt tên loại tương ứng
            if ("KH001".equals(maLoaiKhachHang)) {
                tenLoaiKhachHang = "Khách hàng thường";
            } else if ("KH002".equals(maLoaiKhachHang)) {
                tenLoaiKhachHang = "Khách hàng thân thiết";
            }

            // Tạo đối tượng LoaiKhachHang và KhachHang
            LoaiKhachHang loaiKhachHang = new LoaiKhachHang(maLoaiKhachHang, tenLoaiKhachHang);
            KhachHang kh = new KhachHang(
                    rs.getString("MaKH"),       // Lấy mã khách hàng từ ResultSet
                    loaiKhachHang,                     // Truyền đối tượng LoaiKhachHang đã tạo
                    rs.getString("soDT"),       // Số điện thoại
                    rs.getString("tenKH"),      // Tên khách hàng
                    rs.getString("CCCD"),              // CCCD
                    rs.getString("DiaChi"),            // Địa chỉ
                    rs.getDouble("DiemTichLuy"),       // Điểm tích lũy
                    rs.getDate("NgaySinh").toLocalDate(),  // Ngày sinh (chuyển từ SQL Date sang LocalDate)
                    rs.getDate("NgayThamGia").toLocalDate(), // Ngày tham gia
                    rs.getString("HangThanhVien")      // Hạng thành viên
            );

            // Thêm đối tượng KhachHang vào danh sách
            ds.add(kh);
        }

        return ds;
    }

    // kiểm tra xem có tồn tại hay không trả về trư false
    public boolean customerExists(String sdt) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM KhachHang WHERE SoDT = ?";
        DAO_KhachHang dao_khachHang = new DAO_KhachHang();

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            // Mã hóa số điện thoại trước khi tìm kiếm
            String encryptedSDT = encryptAES(sdt);

            preparedStatement.setString(1, encryptedSDT); // Gán số điện thoại đã mã hóa vào truy vấn
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rs.getInt("count") > 0;
            }
        } catch (Exception e) { // Thay đổi từ SQLException thành Exception để bao gồm cả lỗi mã hóa
            e.printStackTrace();
        }
        return false;
    }

    // kiểm tra xem tồn tại chưa, trả về KH
    public KhachHang findCustomerByEncryptedPhone(String sdt) throws SQLException {
        String sql = "SELECT * FROM KhachHang WHERE SoDT = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            // Mã hóa số điện thoại đầu vào
            String encryptedSDT = encryptAES(sdt);
            ps.setString(1, encryptedSDT);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String maLoaiKhachHang = rs.getString("LoaiKhachHangMaLoaiKH");
                String tenLoaiKhachHang = "";

                // Kiểm tra mã loại khách hàng và đặt tên loại tương ứng
                if ("KH001".equals(maLoaiKhachHang)) {
                    tenLoaiKhachHang = "Khách hàng thường";
                } else if ("KH002".equals(maLoaiKhachHang)) {
                    tenLoaiKhachHang = "Khách hàng thân thiết";
                }

                // Tạo đối tượng LoaiKhachHang và KhachHang
                LoaiKhachHang loaiKhachHang = new LoaiKhachHang(maLoaiKhachHang, tenLoaiKhachHang);
                KhachHang kh = new KhachHang(
                        rs.getString("MaKH"),       // Lấy mã khách hàng từ ResultSet
                        loaiKhachHang,                     // Truyền đối tượng LoaiKhachHang đã tạo
                        rs.getString("soDT"),       // Số điện thoại
                        rs.getString("tenKH"),      // Tên khách hàng
                        rs.getString("CCCD"),              // CCCD
                        rs.getString("DiaChi"),            // Địa chỉ
                        rs.getDouble("DiemTichLuy"),       // Điểm tích lũy
                        rs.getDate("NgaySinh").toLocalDate(),  // Ngày sinh (chuyển từ SQL Date sang LocalDate)
                        rs.getDate("NgayThamGia").toLocalDate(), // Ngày tham gia
                        rs.getString("HangThanhVien")      // Hạng thành viên
                );
                return kh;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi tìm kiếm khách hàng: " + e.getMessage(), e);
        }
        return null; // Nếu không tìm thấy
    }

    public boolean updateCustomer(KhachHang kh) throws SQLException {
        String deleteSql = "DELETE FROM KhachHang WHERE SoDT = ?";
        String insertSql = "INSERT INTO KhachHang (MaKH, LoaiKhachHangMaLoaiKH, soDT, tenKH, CCCD,DiaChi, DiemTichLuy, NgaySinh, NgayThamGia, HangThanhVien) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        DAO_KhachHang dao_khachHang = new DAO_KhachHang();
        try {
            // bắt đầu
            con.setAutoCommit(false);

            // Check if the customer exists
            if (customerExists(kh.getSoDienThoai())) {
                // Step 1: Delete the existing customer record
                try (PreparedStatement deleteStatement = con.prepareStatement(deleteSql)) {
                    deleteStatement.setString(1, kh.getSoDienThoai());
                    deleteStatement.executeUpdate();
                }

                // Step 2: Insert the updated customer record
                try (PreparedStatement insertStatement = con.prepareStatement(insertSql)) {
                    insertStatement.setString(1, kh.getMaKhachHang());
                    insertStatement.setString(2, kh.getLoaiKhachHang().getMaLoaiKhachHang());
                    insertStatement.setString(3, kh.getSoDienThoai());
                    insertStatement.setString(4, kh.getTenKhachHang());
                    insertStatement.setString(5, kh.getCCCD());
                    insertStatement.setString(6, kh.getDiaChi());
                    insertStatement.setDouble(7, kh.getDiemTichLuy());
                    insertStatement.setDate(8, Date.valueOf(kh.getNgaySinh()));
                    insertStatement.setDate(9, Date.valueOf(kh.getNgayThamgGia()));
                    insertStatement.setString(10, kh.getHangThanhVien());

                    insertStatement.executeUpdate();
                }

                // Commit the transaction
                con.commit();
                return true; // Update successful
            } else {
                System.out.println("Khách hàng chưa tồn tại.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback(); // Rollback if there is an error
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            try {
                con.setAutoCommit(true); // Re-enable auto-commit
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false; // Update failed
    }

//        public int addCustomer(KhachHang kh) throws SQLException {
//            String sql = "INSERT INTO KhachHang (MaKH, LoaiKhachHangMaLoaiKH, soDT, tenKH, CCCD,DiaChi, DiemTichLuy, NgaySinh, NgayThamGia, HangThanhVien) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//            try (PreparedStatement ps = con.prepareStatement(sql)) {
//                ps.setString(1, kh.getMaKhachHang());
//                ps.setString(2, kh.getLoaiKhachHang().getMaLoaiKhachHang()); // Sử dụng MaLoaiKhachHang ở đây
//                ps.setString(3, kh.getSoDienThoai());
//                ps.setString(4, kh.getTenKhachHang());
//                ps.setString(5, kh.getCCCD());
//                ps.setString(6, kh.getDiaChi());
//                ps.setDouble(7, kh.getDiemTichLuy());
//                ps.setDate(8, java.sql.Date.valueOf(kh.getNgaySinh()));
//                ps.setDate(9, java.sql.Date.valueOf(kh.getNgayThamgGia()));
//                ps.setString(10, kh.getHangThanhVien());
//
//                return ps.executeUpdate(); // Trả về số hàng bị ảnh hưởng
//            } catch (SQLException e) {
//                // Ghi lại ngoại lệ hoặc xử lý nó theo cách phù hợp
//                e.printStackTrace(); // Thay thế bằng ghi log hợp lý
//                throw new RuntimeException("Lỗi khi thêm khách hàng: " + e.getMessage(), e);
//            }
//        }

    private static final String AES_KEY = "1234567890123456"; // Khóa bí mật 16 byte
    private static final String ALGORITHM = "AES";

    // Hàm mã hóa bằng AES
    public static String encryptAES(String data) throws Exception {
        Key key = new SecretKeySpec(AES_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    // Hàm giải mã
    public String decryptAES(String encryptedText) throws Exception {
        // Chuyển đổi khóa bí mật thành SecretKey
        SecretKey secretKey = new SecretKeySpec(AES_KEY.getBytes(), ALGORITHM);

        // Khởi tạo cipher cho AES và chế độ giải mã
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Giải mã văn bản mã hóa từ Base64
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);

        return new String(decryptedBytes);
    }

    // Phương thức thêm khách hàng, sử dụng mã hóa trước khi lưu
    public int addCustomer(KhachHang kh) throws SQLException {
        String sql = "INSERT INTO KhachHang (MaKH, LoaiKhachHangMaLoaiKH, SoDT, TenKH, CCCD, DiaChi, DiemTichLuy, NgaySinh, NgayThamGia, HangThanhVien) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, kh.getMaKhachHang());
            ps.setString(2, kh.getLoaiKhachHang().getMaLoaiKhachHang());
            ps.setString(3, encryptAES(kh.getSoDienThoai())); // Mã hóa số điện thoại
            ps.setString(4, encryptAES(kh.getTenKhachHang()));
            ps.setString(5, encryptAES(kh.getCCCD())); // Mã hóa CCCD
            ps.setString(6, encryptAES(kh.getDiaChi())); // Mã hóa địa chỉ
            ps.setDouble(7, kh.getDiemTichLuy());
            ps.setDate(8, java.sql.Date.valueOf(kh.getNgaySinh()));
            ps.setDate(9, java.sql.Date.valueOf(kh.getNgayThamgGia()));
            ps.setString(10, kh.getHangThanhVien());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi thêm khách hàng: " + e.getMessage(), e);
        }
    }
}
