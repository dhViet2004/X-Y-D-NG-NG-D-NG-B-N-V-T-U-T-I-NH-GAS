package GUI;

import javax.swing.*;
import java.awt.*;

public class FrmDangNhap extends JDialog {
    private JPanel contentPane;
    private JLabel banner;
    private JPanel jPanel_Submit;
    private JButton dangNhapButton;
    private JPanel Jpanel_Input;
    private JTextField textField1;
    private JTextField textField2;

    public FrmDangNhap (JFrame parent){
        super(parent);
        setContentPane(contentPane);
        setTitle("Đăng nhập");
        setMinimumSize(new Dimension(850, 462));
        setModal(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(contentPane);
        setVisible(true);
    }

    public static void main(String[] args) {
        FrmDangNhap frmDangNhap = new FrmDangNhap(null);
    }
}
