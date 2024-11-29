package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
    private JButton selectedButton = null;  // Biến lưu trữ button hiện tại đang được chọn

    public Main() {
        setTitle("Button Hover and Select Effect");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        // Tạo các button
        JButton button1 = createButton("Button 1");
        JButton button2 = createButton("Button 2");
        JButton button3 = createButton("Button 3");

        // Thêm button vào frame
        add(button1);
        add(button2);
        add(button3);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);

        // Thêm hiệu ứng hover
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (selectedButton != button) { // Nếu chưa chọn button này thì mới thay đổi màu
                    button.setBackground(Color.CYAN); // Màu khi hover
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (selectedButton != button) { // Nếu chưa chọn button này thì mới quay lại màu mặc định
                    button.setBackground(UIManager.getColor("Button.background")); // Màu mặc định khi hover hết
                }
            }
        });

        // Thêm hiệu ứng select (khi click)
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Nếu có button nào được chọn trước đó, quay lại màu mặc định
                if (selectedButton != null) {
                    selectedButton.setBackground(UIManager.getColor("Button.background"));
                }

                // Chọn button hiện tại và thay đổi màu
                button.setBackground(Color.GREEN);
                selectedButton = button; // Cập nhật button hiện tại đang được chọn
            }
        });

        // Cài đặt màu nền mặc định
        button.setBackground(UIManager.getColor("Button.background"));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main frame = new Main();
                frame.setVisible(true);
            }
        });
    }
}
