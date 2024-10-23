package DAO;

import Database.ConnectDatabase;
import Entity.LichTrinhTau;
import Entity.Tau;
import Entity.TuyenTau;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for managing train schedules.
 */
public class DAO_ChuyenTau {
    private Connection con;

    public DAO_ChuyenTau() {
        this.con = ConnectDatabase.getConnection();
        if (this.con == null) {
            System.out.println("Connection is null. Please check the database connection.");
        }
    }

    public LichTrinhTau getTheoMa(String maChuyen) {
    LichTrinhTau lichTrinhTau = null;
     String sql = "SELECT lt.MaLich, lt.MaTau, lt.GioDi, lt.NgayDi, tau.MaTuyen, t.TenTuyen, lt.TrangThai, tau.TenTau " +
             "FROM LichTrinhTau lt " +
             "JOIN Tau tau ON lt.MaTau = tau.MaTau " +
             "JOIN TuyenTau t ON tau.MaTuyen = t.MaTuyen " +
             "WHERE lt.MaLich = ?";

    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
        pstmt.setString(1, maChuyen); // Gán giá trị cho tham số trong truy vấn
        ResultSet resultSet = pstmt.executeQuery(); // Thực thi truy vấn

        if (resultSet.next()) {
             String maLichTrinh = resultSet.getString("MaLich");
            String maTau = resultSet.getString("MaTau");
            LocalTime gioDi = resultSet.getTimestamp("GioDi").toLocalDateTime().toLocalTime();
            LocalDate ngayDi = resultSet.getDate("NgayDi").toLocalDate();
            String maTuyen = resultSet.getString("MaTuyen"); // Lấy mã tuyến
            String tenTuyen = resultSet.getString("TenTuyen"); // Lấy tên tuyến
            String trangThai = resultSet.getString("TrangThai");
            String tenTau = resultSet.getString("TenTau");
            // Tạo đối tượng TuyenTau
            TuyenTau tuyenTau = new TuyenTau( maTuyen,tenTuyen, null, null, null, null);

            // Tạo đối tượng Tau
             Tau tau = new Tau(maTau, tuyenTau, tenTau, 0); 

            // Tạo đối tượng LichTrinhTau
            lichTrinhTau = new LichTrinhTau(maLichTrinh, gioDi, ngayDi, tau, trangThai);
        }
    } catch (SQLException e) {
        e.printStackTrace(); // In ra thông báo lỗi nếu có
    }
    return lichTrinhTau; // Trả về đối tượng lịch trình tàu
    }
    public String getTenTuyenByMa(String maTuyen) {
    String tenTuyen = null;
    String sql = "SELECT TenTuyen FROM TuyenTau WHERE MaTuyen = ?";

    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
        pstmt.setString(1, maTuyen);
        ResultSet resultSet = pstmt.executeQuery();
        
        if (resultSet.next()) {
            tenTuyen = resultSet.getString("TenTuyen");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return tenTuyen; // Trả về tên tuyến
}
    
    public List<TuyenTau> getAllTuyenTau() {
    List<TuyenTau> listTuyenTau = new ArrayList<>();
    String sql = "SELECT MaTuyen, TenTuyen FROM TuyenTau";
    
    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
        ResultSet resultSet = pstmt.executeQuery();
        
        while (resultSet.next()) {
            String maTuyen = resultSet.getString("MaTuyen");
            String tenTuyen = resultSet.getString("TenTuyen");
            TuyenTau tuyenTau = new TuyenTau(maTuyen, tenTuyen,null,null,null,null);
            listTuyenTau.add(tuyenTau);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return listTuyenTau;
    }
    public List<Tau> getAllTau() {
        List<Tau> listTau = new ArrayList<>();
        String sql = "SELECT MaTau, TenTau FROM Tau"; // Lấy mã tàu và tên tàu từ bảng Tau

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String maTau = resultSet.getString("MaTau");
                String tenTau = resultSet.getString("TenTau");
                Tau tau = new Tau(maTau,null ,tenTau,0);
                listTau.add(tau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listTau;
    }
  
    public List<LichTrinhTau> locLichTrinhTau(LocalDate startDate, LocalDate endDate, String tenTau, String tenTuyen, boolean locTau, boolean locTuyen, String trangThai) {
        List<LichTrinhTau> lichTrinhList = new ArrayList<>();
        String sql = "SELECT lt.MaLich, lt.MaTau, lt.GioDi, lt.NgayDi, tau.MaTuyen, t.TenTuyen, lt.TrangThai, tau.TenTau " +
                     "FROM LichTrinhTau lt " +
                     "JOIN Tau tau ON lt.MaTau = tau.MaTau " +
                     "JOIN TuyenTau t ON tau.MaTuyen = t.MaTuyen " +
                     "WHERE lt.NgayDi >= ? AND lt.NgayDi <= ? ";

        // Xử lý điều kiện lọc theo tên tàu
        if (locTau && tenTau != null && !tenTau.isEmpty()) {
            sql += "AND tau.TenTau = ? ";
        }

        // Xử lý điều kiện lọc theo tên tuyến
        if (locTuyen && tenTuyen != null && !tenTuyen.isEmpty()) {
            sql += "AND t.TenTuyen = ? ";
        }

        // Xử lý điều kiện lọc trạng thái
        if (!trangThai.equals("Tất cả")) {
            sql += "AND lt.TrangThai = ? ";
        }

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            // Gán giá trị cho tham số trong câu truy vấn
            pstmt.setDate(1, java.sql.Date.valueOf(startDate));
            pstmt.setDate(2, java.sql.Date.valueOf(endDate));

            int index = 3;
            if (locTau && tenTau != null && !tenTau.isEmpty()) {
                pstmt.setString(index, tenTau);
                index++;
            }
            if (locTuyen && tenTuyen != null && !tenTuyen.isEmpty()) {
                pstmt.setString(index, tenTuyen);
                index++;
            }
            if (!trangThai.equals("Tất cả")) {
                pstmt.setString(index, trangThai);
            }

            ResultSet resultSet = pstmt.executeQuery();

            // Xử lý kết quả
            while (resultSet.next()) {
                String maLich = resultSet.getString("MaLich");
                String maTau = resultSet.getString("MaTau");
                LocalTime gioDi = resultSet.getTimestamp("GioDi").toLocalDateTime().toLocalTime();
                LocalDate ngayDi = resultSet.getDate("NgayDi").toLocalDate();
                String maTuyen = resultSet.getString("MaTuyen");
                String tenTuyenResult = resultSet.getString("TenTuyen");
                String tenTauResult = resultSet.getString("TenTau");
                String trangThaiResult = resultSet.getString("TrangThai");

                // Tạo đối tượng TuyenTau
                TuyenTau tuyenTau = new TuyenTau( maTuyen,tenTuyenResult, null, null, null, null);

                // Tạo đối tượng Tau
                Tau tau = new Tau(maTau, tuyenTau, tenTauResult, 0);

                // Tạo đối tượng LichTrinhTau
                LichTrinhTau lichTrinh = new LichTrinhTau(maLich, gioDi, ngayDi, tau, trangThaiResult);
                
                // Thêm vào danh sách kết quả
                lichTrinhList.add(lichTrinh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lichTrinhList;
    }
    public List<String> getAllTrangThai() {
        List<String> listTrangThai = new ArrayList<>();
        String sql = "SELECT DISTINCT TrangThai FROM LichTrinhTau"; // Truy vấn để lấy các trạng thái duy nhất

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String trangThai = rs.getString("TrangThai");
                listTrangThai.add(trangThai); // Thêm trạng thái vào danh sách
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listTrangThai;
    }
    public boolean themLichTrinh(LichTrinhTau lichTrinh) {
    String sql = "INSERT INTO LichTrinhTau (MaLich, MaTau, GioDi, NgayDi, TrangThai) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            // Thiết lập giá trị cho các cột
            pstmt.setString(1, lichTrinh.getMaLichTrinh()); // Mã lịch trình
            pstmt.setString(2, lichTrinh.getTau().getMaTau()); // Mã tàu
            pstmt.setTime(3, java.sql.Time.valueOf(lichTrinh.getGioDi())); // Giờ đi (cần đảm bảo lichTrinh.getGioDi() trả về LocalTime)
            pstmt.setDate(4, java.sql.Date.valueOf(lichTrinh.getNgayDi())); // Ngày đi (LocalDate)
            pstmt.setString(5, lichTrinh.getTrangThai()); // Trạng thái (ví dụ: "Hoạt động" hoặc "Hủy")

            int affectedRows = pstmt.executeUpdate(); // Thực thi lệnh và trả về số dòng bị ảnh hưởng
            return affectedRows > 0; // Trả về true nếu thêm thành công
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ SQL
        }
    return false; // Trả về false nếu có lỗi xảy ra
}
    public String taoMaLichTrinh(LocalDate ngayDi, String maTuyenTau, String maTau) {
    // Lấy 2 chữ số cuối của năm khởi hành (yy)
        String year = String.valueOf(ngayDi.getYear()).substring(2);

        // Lấy tháng và ngày khởi hành (MMdd)
        String month = String.format("%02d", ngayDi.getMonthValue());
        String day = String.format("%02d", ngayDi.getDayOfMonth());

        // Lấy số thứ tự chuyến trong ngày (NN) theo tàu
        int soThuTu = laySoThuTuChuyenTrongNgay(ngayDi, maTau); 
        String soThuTuStr = String.format("%02d", soThuTu);

        // Kết hợp các thành phần để tạo mã lịch trình (XXXXXXXXyyMMddNN)
        String maLichTrinh = maTuyenTau + year + month + day + soThuTuStr;

        return maLichTrinh;
    }
    public int laySoThuTuChuyenTrongNgay(LocalDate ngayDi, String maTau) {
    String sql = "SELECT COUNT(*) FROM LichTrinhTau WHERE MaTau = ? AND NgayDi = ?";
    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
        pstmt.setString(1, maTau);
        pstmt.setDate(2, java.sql.Date.valueOf(ngayDi));
        
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) + 1; // Tăng số thứ tự lên 1 nếu đã có chuyến trong ngày
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 1; // Nếu chưa có chuyến nào trong ngày, bắt đầu từ 1
    }
    public String getMaTauByTenTau(String tenTau) {
    String maTau = null; // Biến để lưu mã tàu
    String sql = "SELECT MaTau FROM Tau WHERE TenTau = ?"; // Câu truy vấn SQL để lấy mã tàu theo tên tàu

    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
        pstmt.setString(1, tenTau); // Thiết lập giá trị cho tham số tên tàu trong truy vấn

        ResultSet resultSet = pstmt.executeQuery(); // Thực thi truy vấn

        if (resultSet.next()) {
            maTau = resultSet.getString("MaTau"); // Lấy mã tàu từ kết quả truy vấn
        }

    } catch (SQLException e) {
        e.printStackTrace(); // Xử lý ngoại lệ SQL
    }

    return maTau; // Trả về mã tàu
}
public boolean capNhatLichTrinh(LichTrinhTau lichTrinh) {
    String sql = "UPDATE LichTrinhTau SET GioDi = ?, NgayDi = ?, TrangThai = ? WHERE MaLich = ?";
    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
        pstmt.setTime(1, java.sql.Time.valueOf(lichTrinh.getGioDi()));
        pstmt.setDate(2, java.sql.Date.valueOf(lichTrinh.getNgayDi()));
        pstmt.setString(3, lichTrinh.getTrangThai());
        pstmt.setString(4, lichTrinh.getMaLichTrinh());

        int affectedRows = pstmt.executeUpdate();
        return affectedRows > 0; // Trả về true nếu có ít nhất 1 dòng bị ảnh hưởng
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // Trả về false nếu có lỗi
}
}

