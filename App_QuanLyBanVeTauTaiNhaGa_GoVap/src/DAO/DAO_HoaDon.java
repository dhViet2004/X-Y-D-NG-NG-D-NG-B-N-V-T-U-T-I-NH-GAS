package DAO;

import Database.ConnectDatabase;
import Entity.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DAO_HoaDon {
    private Connection con;

    public DAO_HoaDon() {
        if (ConnectDatabase.getConnection() == null) {
            ConnectDatabase.getInstance().connect();  // Kết nối nếu chưa kết nối
        }
        con = ConnectDatabase.getConnection();
    }

    public List<HoaDon> getAllList() throws SQLException {
        List<HoaDon> list = new ArrayList<HoaDon>();
        String sql = "select * from HoaDon";
        DAO_HoaDon dao = new DAO_HoaDon();
        try (Statement statement = con.createStatement(); ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getString("MaHD"), new KhachHang(rs.getString("MaKH")), new KhuyenMai(rs.getString("KhuyenMaiMaKM")), new NhanVien(rs.getString("NhanVienMaNV")), new LoaiHoaDon(rs.getString("MaLoai")), rs.getDate("NgayHoaDon").toLocalDate(), rs.getDouble("TienKhuyenMai"), rs.getDouble("TongTien"));
                list.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Ném lại ngoại lệ để xử lý ở nơi gọi
        }

        return list;
    }

    public List<Object[]> layDSHD() throws SQLException {
        List<Object[]> list = new ArrayList<>();
        String sql = "select h.NgayHoaDon,k.TenKH,m.DoiTuongApDung,m.NoiDungKM, m.ChietKhau, h.TienKhuyenMai\n" + "from KhachHang k join HoaDon h on k.MaKH=h.MaKH join KhuyenMai m on h.KhuyenMaiMaKM = m.MaKM";
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
}
