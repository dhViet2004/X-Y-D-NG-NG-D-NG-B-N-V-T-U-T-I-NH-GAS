package GUI;

import javax.swing.*;

public class FrmDangNhap {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button1;

    // Constructor to initialize the frame
    public FrmDangNhap() {
        // Create the JFrame
        JFrame frame = new JFrame("Đăng nhập");

        // Set size for the frame
        frame.setSize(500, 500);
        // Add the panel to the frame
        frame.setContentPane(panel1);

        // Set default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Make sure the frame is not resizable
        frame.setResizable(false);
        // Make the frame visible
        frame.setVisible(true);
        // Center the frame on the screen
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        // Run the login form
        new FrmDangNhap();
    }
}
