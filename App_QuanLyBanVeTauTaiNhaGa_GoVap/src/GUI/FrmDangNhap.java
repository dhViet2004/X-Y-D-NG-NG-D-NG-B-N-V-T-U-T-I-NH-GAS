package GUI;

import DAO.DAO_DangNhap;
import Database.ConnectDatabase;
import Entity.TaiKhoan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class FrmDangNhap extends JFrame implements ActionListener {
    private JPanel contentPane;
    private JLabel banner;
    private JPanel jPanel_Submit;
    private JButton btnDangNhap;
    private JPanel Jpanel_Input;
    private JTextField txtMaNhanVien;
    private JPasswordField txtMatKhau;

    public FrmDangNhap() {
        setContentPane(contentPane);
        setTitle("Đăng nhập");
        setMinimumSize(new Dimension(800, 462));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Thêm ActionListener cho nút đăng nhập
        btnDangNhap.addActionListener(this);
        ConnectDatabase.getInstance().connect();

        btnDangNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maNV = txtMaNhanVien.getText(); // Lấy mã nhân viên từ ô nhập
                String matKhau = new String(txtMatKhau.getPassword()); // Lấy mật khẩu

                try {
                    DAO_DangNhap daoDangNhap = new DAO_DangNhap();
                    // Gọi phương thức đăng nhập từ DAO_DangNhap
                    TaiKhoan taiKhoan = daoDangNhap.dangNhap(maNV, matKhau);

                    if (taiKhoan != null) {
                        // Đăng nhập thành công
                        JOptionPane.showMessageDialog(
                                contentPane,
                                "Đăng nhập thành công!",
                                "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        // Điều hướng sang giao diện khác hoặc tiếp tục xử lý
                        FrmBanVe frmBanVe = new FrmBanVe();
                        frmBanVe.setVisible(true);
                        dispose();
                    } else {
                        // Thông báo sai thông tin đăng nhập
                        JOptionPane.showMessageDialog(
                                contentPane,
                                "Sai mã nhân viên hoặc mật khẩu!",
                                "Lỗi",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } catch (SQLException ex) {
                    // Xử lý ngoại lệ SQL
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(
                            contentPane,
                            "Lỗi hệ thống khi đăng nhập!",
                            "Lỗi",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public static void main(String[] args) {
        FrmDangNhap fm = new FrmDangNhap();
        fm.setVisible(true);
    }
}
