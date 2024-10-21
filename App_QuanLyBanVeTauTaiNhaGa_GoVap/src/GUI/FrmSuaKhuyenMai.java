package GUI;

import DAO.DAO_KhuyenMai;
import Entity.KhuyenMai;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class FrmSuaKhuyenMai extends JDialog implements ActionListener {
    private JTextField txtMaKM;
    private JTextField txtNoiDungKM;
    private JTextField txtChietKhau;
    private JTextField txtDoiTuongApDung;
    private JDateChooser dateBatDau;
    private JDateChooser dateKetThuc;
    private JButton btnLuu, btnHuy;

    public FrmSuaKhuyenMai(JFrame parent) {
        super(parent, "Cập Nhật Chương Trình Khuyến Mãi", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        // Tạo layout cho form
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Mã KM"));
        txtMaKM = new JTextField();
        txtMaKM.setEditable(false);
        panel.add(txtMaKM);

        panel.add(new JLabel("Thời gian bắt đầu:"));
        dateBatDau = new JDateChooser();
        panel.add(dateBatDau);

        panel.add(new JLabel("Thời gian kết thúc:"));
        dateKetThuc = new JDateChooser();
        panel.add(dateKetThuc);

        // Tạo các label và text field
        panel.add(new JLabel("Nội dung KM:"));
        txtNoiDungKM = new JTextField();
        panel.add(txtNoiDungKM);

        panel.add(new JLabel("Chiết khấu (%):"));
        txtChietKhau = new JTextField();
        panel.add(txtChietKhau);

        panel.add(new JLabel("Đối tượng áp dụng:"));
        txtDoiTuongApDung = new JTextField();
        panel.add(txtDoiTuongApDung);

        // Tạo các nút Lưu và Hủy
        btnLuu = new JButton("Cập nhật");
        btnLuu.addActionListener(this);
        btnHuy = new JButton("Hủy");
        btnHuy.addActionListener(this);

        panel.add(btnLuu);
        panel.add(btnHuy);

        // Thêm panel vào dialog
        add(panel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLuu) {
            DAO_KhuyenMai dao_km = null;
            try {
                dao_km = new DAO_KhuyenMai();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            // lấy dữ liệu từ text
            String maKM = txtMaKM.getText();
            Date ngayBatDau = dateBatDau.getDate();
            Date ngayKetThuc = dateKetThuc.getDate();
            LocalDate begin = ngayBatDau.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate end = ngayKetThuc.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String noiDung = txtNoiDungKM.getText();
            Double chietKhau = Double.parseDouble(txtChietKhau.getText());
            String doiTuong = txtDoiTuongApDung.getText();
            KhuyenMai km = new KhuyenMai(maKM, begin, end, noiDung, chietKhau, doiTuong);
            // thêm vào dbs, trước khi thêm, tìm và xóa km đó trong dbs trước
            // xóa km theo mã
            try {
                if (dao_km.xoaKMTheoMa(txtMaKM.getText())) {
                    // thêm km mới vào
                    dao_km.themKhuyenMai(km);
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                dao_km.themKhuyenMai(km);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            // Đóng form sau khi lưu
            dispose();
        } else if (e.getSource() == btnHuy) {
            // Đóng form khi nhấn Hủy
            int kq = JOptionPane.showConfirmDialog(this, "Hủy thay đổi", "Xác nhận hủy", JOptionPane.YES_NO_OPTION);
            if (kq == JOptionPane.YES_OPTION) {
                dispose();
            }
        }
    }

    public void setMaKM(String maKM) {
        txtMaKM.setText(maKM);
    }

    public void setThoiGianBatDau(Date thoiGian) {
        dateBatDau.setDate(thoiGian);
    }

    public void setThoiGianKetThuc(Date thoiGian) {
        dateKetThuc.setDate(thoiGian);
    }

    public void setNoiDungKM(String noiDung) {
        txtNoiDungKM.setText(noiDung);
    }

    public void setChietKhau(String chietKhau) {
        txtChietKhau.setText(chietKhau);
    }

    public void setDoiTuongApDung(String doiTuong) {
        txtDoiTuongApDung.setText(doiTuong);
    }


}

