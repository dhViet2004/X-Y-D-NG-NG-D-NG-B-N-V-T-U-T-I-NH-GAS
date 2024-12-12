package DAO;
import Database.ConnectDatabase;
import Entity.Tau;
import Entity.TuyenTau;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DAO_TuyenTau {
     private Connection con;

    // Constructor to initialize the database connection
    public DAO_TuyenTau() {
        this.con = ConnectDatabase.getConnection();
        if (this.con == null) {
            System.out.println("Connection is null. Please check the database connection.");
        } // Kết nối cơ sở dữ liệu
    }

    // Method to search for TuyenTau by GaDi or GaDen
     public List<TuyenTau> findTuyenTauByGa(String gaDi, String gaDen) {
        List<TuyenTau> tuyenTauList = new ArrayList<>();
        String sql = "SELECT TOP 1000 [MaTuyen], [TenTuyen], [GaDi], [GaDen], [DiaDiemDi], [DiaDiemDen] " +
                     "FROM [UngDungQuanLyBanVeTaiGaGoVap].[dbo].[TuyenTau] WHERE 1=1";

        // Add conditions for GaDi and GaDen
        if (gaDi != null && !gaDi.isEmpty()) {
            sql += " AND [GaDi] = ?";
        }
        if (gaDen != null && !gaDen.isEmpty()) {
            sql += " AND [GaDen] = ?";
        }

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            int index = 1;

            // Set parameters for GaDi
            if (gaDi != null && !gaDi.isEmpty()) {
                statement.setString(index++, gaDi);
            }

            // Set parameters for GaDen
            if (gaDen != null && !gaDen.isEmpty()) {
                statement.setString(index++, gaDen);
            }

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String maTuyen = resultSet.getString("MaTuyen");
                String tenTuyen = resultSet.getString("TenTuyen");
                String gaDiResult = resultSet.getString("GaDi");
                String gaDenResult = resultSet.getString("GaDen");
                String diaDiemDi = resultSet.getString("DiaDiemDi");
                String diaDiemDen = resultSet.getString("DiaDiemDen");

                TuyenTau tuyenTau = new TuyenTau(maTuyen, tenTuyen, gaDiResult, gaDenResult, diaDiemDi, diaDiemDen);
                tuyenTauList.add(tuyenTau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tuyenTauList;
    }
      public List<TuyenTau> findTuyenTauByTP(String gaDi, String gaDen) {
        List<TuyenTau> tuyenTauList = new ArrayList<>();
        String sql = "SELECT TOP 1000 [MaTuyen], [TenTuyen], [GaDi], [GaDen], [DiaDiemDi], [DiaDiemDen] " +
                     "FROM [UngDungQuanLyBanVeTaiGaGoVap].[dbo].[TuyenTau] WHERE 1=1";

        // Add conditions for GaDi and GaDen
        if (gaDi != null && !gaDi.isEmpty()) {
            sql += " AND [DiaDiemDi] = ?";
        }
        if (gaDen != null && !gaDen.isEmpty()) {
            sql += " AND [DiaDiemDen] = ?";
        }

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            int index = 1;

            // Set parameters for GaDi
            if (gaDi != null && !gaDi.isEmpty()) {
                statement.setString(index++, gaDi);
            }

            // Set parameters for GaDen
            if (gaDen != null && !gaDen.isEmpty()) {
                statement.setString(index++, gaDen);
            }

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String maTuyen = resultSet.getString("MaTuyen");
                String tenTuyen = resultSet.getString("TenTuyen");
                String gaDiResult = resultSet.getString("GaDi");
                String gaDenResult = resultSet.getString("GaDen");
                String diaDiemDi = resultSet.getString("DiaDiemDi");
                String diaDiemDen = resultSet.getString("DiaDiemDen");

                TuyenTau tuyenTau = new TuyenTau(maTuyen, tenTuyen, gaDiResult, gaDenResult, diaDiemDi, diaDiemDen);
                tuyenTauList.add(tuyenTau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tuyenTauList;
    }
      public List<Tau> findTausByTuyen(String maTuyen)  {
        List<Tau> tauList = new ArrayList<>();
        String sql = "SELECT [MaTau], [MaTuyen], [TenTau], [SoToa] FROM [Tau] WHERE [MaTuyen] = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, maTuyen);  // Gán giá trị MaTuyen vào câu truy vấn

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // Lấy thông tin tàu từ kết quả truy vấn và thêm vào danh sách
                String maTau = resultSet.getString("MaTau");
                String tenTau = resultSet.getString("TenTau");
                int soToa = resultSet.getInt("SoToa");

                // Tạo đối tượng Tau và thêm vào danh sách
                Tau tau = new Tau(maTau, new TuyenTau(), tenTau, soToa);
                tauList.add(tau);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return tauList;
    }
         public List<TuyenTau> getTuyenTauByTenTauAndListTuyen(String tenTau, List<TuyenTau> tuyenTauList) {
    List<TuyenTau> result = new ArrayList<>();
    if (tuyenTauList == null || tuyenTauList.isEmpty()) {
        return result; // Trả về danh sách rỗng nếu danh sách đầu vào rỗng
    }
    
    String placeholders = String.join(",", Collections.nCopies(tuyenTauList.size(), "?"));
    String sql = "SELECT T.MaTuyen, T.TenTuyen, T.GaDi, T.GaDen, T.DiaDiemDi, T.DiaDiemDen " +
                 "FROM TuyenTau T " +
                 "JOIN Tau Ta ON T.MaTuyen = Ta.MaTuyen " +
                 "WHERE Ta.TenTau = ? AND T.MaTuyen IN (" + placeholders + ")";
    
    try (
         PreparedStatement stmt = con.prepareStatement(sql)) {
        
        // Gán tham số
        stmt.setString(1, tenTau);
        for (int i = 0; i < tuyenTauList.size(); i++) {
            stmt.setString(i + 2, tuyenTauList.get(i).getMaTuyen()); // Lấy mã tuyến từ danh sách TuyenTau
        }
        
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                TuyenTau tuyenTau = new TuyenTau(
                    rs.getString("MaTuyen"),
                    rs.getString("TenTuyen"),
                    rs.getString("GaDi"),
                    rs.getString("GaDen"),
                    rs.getString("DiaDiemDi"),
                    rs.getString("DiaDiemDen")
                );
                result.add(tuyenTau);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Xử lý lỗi SQL
    }
    return result;
}

     
}
