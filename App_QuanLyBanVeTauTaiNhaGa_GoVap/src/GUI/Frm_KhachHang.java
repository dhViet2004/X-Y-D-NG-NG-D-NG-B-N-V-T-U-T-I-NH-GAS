package GUI;

import javax.swing.*;
import java.awt.*;

public class Frm_KhachHang extends JFrame {

    private JPanel contentPanel;
    private JPanel JPanel_Menu;
    private JPanel Jpanel_Right;
    private JPanel JPanel_Right_Top;
    private JPanel JPanel_Right_Bottom;
    private JPanel JPanel_Right_Left;

    public Frm_KhachHang() {
        setTitle("Form Khách Hàng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);


        Jpanel_Right = new JPanel();
//        Jpanel_Right.setBackground(Color.WHITE);
        JPanel_Right_Top = new JPanel();
        JPanel_Right_Bottom = new JPanel();
        JPanel_Right_Left = new JPanel();
        JPanel_Right_Left.setPreferredSize(new Dimension(400, 800));
        JPanel_Right_Left.setBackground(Color.WHITE);

        Jpanel_Right.setLayout(new BorderLayout());
        // set layout cho right_top(box dọc chứa các dòng), right_bottom(box ngang chứa hai bảng), right_left(box dọc chứa các dòng)
        Box box_righttop = Box.createVerticalBox();
        Box box_rightbottom = Box.createHorizontalBox();
        Box box_rightleft = Box.createVerticalBox();
        //thêm box vào panel
        JPanel_Right_Top.add(box_righttop);
        JPanel_Right_Bottom.add(box_rightbottom);
        JPanel_Right_Left.add(box_rightleft);
        // thêm 3 panel vào right
        Jpanel_Right.add(JPanel_Right_Top, BorderLayout.NORTH);
        Jpanel_Right.add(JPanel_Right_Bottom, BorderLayout.SOUTH);
        Jpanel_Right.add(JPanel_Right_Left, BorderLayout.WEST);
        //BOX RIGHT_LEFT
        // Dòng 1: label chứa ảnh
        // tạo ảnh
        // Tạo ảnh
        ImageIcon iconUser = new ImageIcon(getClass().getResource("/Anh_HeThong/user.png"));
        // Scale ảnh nếu cần
        Image imgUser = iconUser.getImage();
        Image scaledImgUser = imgUser.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIconUser = new ImageIcon(scaledImgUser);

        // Tạo label chứa ảnh
        JLabel lblUser = new JLabel(scaledIconUser);
        lblUser.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Thêm label chứa ảnh vào box_rightleft
        box_rightleft.add(Box.createRigidArea(new Dimension(10, 40)));
        box_rightleft.add(lblUser);

        // Hiển thị form
        JPanel_Right_Left.add(box_rightleft);

        // thêm menu và right vào form
        add(JPanel_Menu, BorderLayout.WEST);
        add(Jpanel_Right, BorderLayout.CENTER);

        //DÒNG 2: Label chứa tên người dùng
        Label lbluserName = new Label("Nguyễn Văn A");
        lbluserName.setAlignment(Label.CENTER);
        box_rightleft.add(Box.createRigidArea(new Dimension(10, 10)));
        box_rightleft.add(lbluserName);

    }

    public static void main(String[] args) {
        Frm_KhachHang frm = new Frm_KhachHang();
        frm.setVisible(true);
    }
}
