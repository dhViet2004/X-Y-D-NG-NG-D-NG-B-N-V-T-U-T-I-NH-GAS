package GUI;

import javax.swing.*;
import java.awt.*;

public class Frm_TraCuuVe extends JFrame {
    private JPanel contentPanel;
    private JPanel JPanel_Menu;
    private JPanel JPanel_Right = new JPanel(); //Panel chứa nội dung

    // hàm lấy JPanel_Right để gắn sang màn hình chính khi nhấn nút tk tl đổi trả vé
    public Component get_TraCuuVe_Panel() {
        return JPanel_Right;
    }

    public Frm_TraCuuVe() {
        setTitle("Form tra cứu vé");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //======================================================================//
        // thêm code cho JPanel_Right
        //======================================================================//
        add(JPanel_Menu, BorderLayout.WEST);
        add(JPanel_Right, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Frm_TraCuuVe frm = new Frm_TraCuuVe();
        frm.setVisible(true);
    }
}
