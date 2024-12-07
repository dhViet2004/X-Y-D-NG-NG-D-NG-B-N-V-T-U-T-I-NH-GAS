package main;

import DAO.DAO_KhachHang;
import Entity.KhachHang;

public class tmp {
    public static void main(String[] args) throws Exception {
        DAO_KhachHang dao = new DAO_KhachHang();
        String sdt = "0356307125";

        String enc;

        {
            try {
                enc = dao.encryptAES(sdt);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        KhachHang tmp;
        tmp = dao.findCustomerByEncryptedPhone(sdt);
        System.out.println(tmp);
        System.out.println(dao.decryptAES(tmp.getSoDienThoai()));
    }

}
