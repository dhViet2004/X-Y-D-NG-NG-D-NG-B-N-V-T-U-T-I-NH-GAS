package Entity;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {
    public RoundedButton(String label) {
        super(label);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        setBorderPainted(false); // Tắt viền nút
        setFocusable(false); // Tắt chế độ focus
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Vẽ màu nền
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Bo tròn với bán kính 20

        // Vẽ chữ
        super.paintComponent(g);
    }

    @Override
    public void setBackground(Color color) {
        super.setBackground(color);
    }

    @Override
    public void setForeground(Color color) {
        super.setForeground(color);
    }
}


