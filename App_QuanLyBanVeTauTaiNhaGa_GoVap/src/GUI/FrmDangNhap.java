package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public static void main(String[] args) {
        FrmDangNhap fm = new FrmDangNhap();
        fm.setVisible(true);
    }
}
