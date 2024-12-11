package Main;

import DAO.DAO_TraCuuVe;
import Database.ConnectDatabase;
import Entity.ChiTietHoaDon;
import Entity.TicketDetails;
import Entity.TicketPDFGenerator;
import Entity.VeTau;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        ConnectDatabase.getInstance().connect();
        DAO_TraCuuVe daoTraCuuVe = new DAO_TraCuuVe();
        String maVe = "SGNT100120241024094438-001";
        try {
            VeTau veTau = daoTraCuuVe.timMotVeTauTheoMa(maVe);
            ChiTietHoaDon chiTietHoaDon = daoTraCuuVe.timChiTietHoaDonTheoMaVe(maVe);
            List<ChiTietHoaDon> chiTietHoaDonList = new ArrayList<>();
            chiTietHoaDonList.add(chiTietHoaDon);
            System.out.println(chiTietHoaDon);
            System.out.println(veTau);
            List<TicketDetails>list = new ArrayList<>();
            TicketDetails ticketDetails = new TicketDetails(veTau.getTenKhachHang(),veTau.getDoiTuong(),veTau.getGiayTo(),veTau.getLichTrinhTau().getTau().getTuyenTau().getGaDi(),veTau.getLichTrinhTau().getTau().getTuyenTau().getGaDen(),veTau.getLichTrinhTau().getTau().getTenTau(),veTau.getLichTrinhTau().getNgayDi(),veTau.getLichTrinhTau().getGioDi(),veTau.getChoNgoi().getToaTau().getTenToa(),veTau.getChoNgoi().getTenCho(),veTau.getGiaVe(),chiTietHoaDon.getThanhTien());
            list.add(ticketDetails);
            TicketPDFGenerator.generateTicketPdf("InLaiVe.pdf",list,chiTietHoaDonList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
