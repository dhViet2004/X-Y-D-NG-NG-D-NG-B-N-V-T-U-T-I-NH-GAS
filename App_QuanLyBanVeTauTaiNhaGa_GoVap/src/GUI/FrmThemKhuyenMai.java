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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FrmThemKhuyenMai extends JDialog implements ActionListener {
    private JTextField txtNoiDungKM;
    private JTextField txtChietKhau;
    private JTextField txtDoiTuongApDung;
    private JDateChooser dateBatDau;
    private JDateChooser dateKetThuc;
    private JButton btnLuu, btnHuy;

    public FrmThemKhuyenMai(JFrame parent) {
        super(parent, "Thêm Chương Trình Khuyến Mãi", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        // Tạo layout cho form
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));



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
        btnLuu = new JButton("Lưu");
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
            // Lấy dữ liệu từ form
            Date ngayBatDau = dateBatDau.getDate();
            Date ngayKetThuc = dateKetThuc.getDate();
            String noiDung = txtNoiDungKM.getText().trim();
            String chietKhauText = txtChietKhau.getText().trim();
            String doiTuong = txtDoiTuongApDung.getText().trim();

            // Kiểm tra dữ liệu đầu vào
            if (ngayBatDau == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bắt đầu!");
                return;
            }
            if (ngayKetThuc == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày kết thúc!");
                return;
            }
            if (ngayKetThuc.before(ngayBatDau)) {
                JOptionPane.showMessageDialog(this, "Ngày kết thúc phải sau hoặc bằng ngày bắt đầu!");
                return;
            }
            if (noiDung.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nội dung khuyến mãi không được để trống!");
                return;
            }
            if (doiTuong.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Đối tượng áp dụng không được để trống!");
                return;
            }
            double chietKhau;
            try {
                chietKhau = Double.parseDouble(chietKhauText);
                if (chietKhau <= 0) {
                    JOptionPane.showMessageDialog(this, "Chiết khấu phải lớn hơn 0!");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Chiết khấu phải là một số hợp lệ!");
                return;
            }

            // Nếu tất cả dữ liệu hợp lệ, tiến hành lưu
            DAO_KhuyenMai dao_km;
            String maKM;
            try {
                dao_km = new DAO_KhuyenMai();
                List<KhuyenMai> list = dao_km.getKhuyenMais();
                int n = list.size() + 1;
                if (n <= 9) {
                    maKM = "KM00" + n;
                } else {
                    maKM = "KM0" + n;
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu!");
                ex.printStackTrace();
                return;
            }

            // Chuyển đổi ngày sang LocalDate
            LocalDate begin = ngayBatDau.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate end = ngayKetThuc.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            // Tạo đối tượng KhuyenMai và lưu vào cơ sở dữ liệu
            KhuyenMai km = new KhuyenMai(maKM, begin, end, noiDung, chietKhau, doiTuong);
            try {
                dao_km.themKhuyenMai(km);
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
                dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi thêm khuyến mãi vào cơ sở dữ liệu!");
                ex.printStackTrace();
            }
        } else if (e.getSource() == btnHuy) {
            // Đóng form khi nhấn Hủy
            int kq = JOptionPane.showConfirmDialog(this, "Hủy thay đổi", "Xác nhận hủy", JOptionPane.YES_NO_OPTION);
            if (kq == JOptionPane.YES_OPTION) {
                dispose();
            }
        }
    }

}

