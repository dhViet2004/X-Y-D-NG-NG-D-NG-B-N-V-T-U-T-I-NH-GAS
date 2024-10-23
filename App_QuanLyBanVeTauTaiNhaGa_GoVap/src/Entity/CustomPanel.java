package Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomPanel extends JPanel {
    private JTextField txtHoTen;
    private JTextField txtCCCD;
    private JComboBox<String> cbTrangThai;
    private float giaVe; // Biến để lưu giá vé
    private double discount; // Biến để lưu giá trị giảm

    public CustomPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Dimension textFieldSize = new Dimension(150, 30);

        JPanel hoTenPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblHoTen = new JLabel("Họ tên: ");
        txtHoTen = new JTextField(15);
        txtHoTen.setPreferredSize(textFieldSize);
        hoTenPanel.add(lblHoTen);
        hoTenPanel.add(txtHoTen);

        JPanel trangThaiPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblTrangThai = new JLabel("Đối tượng:");
        cbTrangThai = new JComboBox<>(new String[]{"Người lớn", "Sinh viên", "Trẻ nhỏ"});
        cbTrangThai.setPreferredSize(textFieldSize);
        trangThaiPanel.add(lblTrangThai);
        trangThaiPanel.add(cbTrangThai);

        JPanel cccdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblCCCD = new JLabel("CCCD:  ");
        txtCCCD = new JTextField(15);
        txtCCCD.setPreferredSize(textFieldSize);
        cccdPanel.add(lblCCCD);
        cccdPanel.add(txtCCCD);

        add(hoTenPanel);
        add(trangThaiPanel);
        add(cccdPanel);

        cbTrangThai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGiaVe(giaVe); // Cập nhật giá vé và giảm khi lựa chọn thay đổi
                System.out.println("Trạng thái: " + cbTrangThai.getSelectedItem());
                System.out.println("Giá vé: " + giaVe);
                System.out.println("Giảm giá: " + discount);

                // Lấy bảng và hàng hiện tại để cập nhật
                JTable table = (JTable) SwingUtilities.getAncestorOfClass(JTable.class, CustomPanel.this);
                if (table != null) {
                    int row = table.getEditingRow(); // Lấy dòng hiện tại
                    table.getModel().setValueAt(discount, row, 3); // Cập nhật cột "Giảm đối tượng"
                    table.getModel().setValueAt(giaVe - discount, row, 5); // Cập nhật cột "Thành tiền"
                    table.revalidate(); // Làm mới bảng để hiển thị thay đổi
                }
            }
        });
    }

    public void updateGiaVe(float giaVe) {
        String selectedItem = (String) cbTrangThai.getSelectedItem();
        switch (selectedItem) {
            case "Người lớn":
                setGiaVe(giaVe); // Giá cho người lớn
                discount = 0; // Không có giảm
                break;
            case "Sinh viên":
                setGiaVe(giaVe); // Giá cho sinh viên (giá gốc)
                discount = 90000; // Giảm 90,000 cho sinh viên
                break;
            case "Trẻ nhỏ":
                setGiaVe(giaVe); // Giá cho trẻ nhỏ (giá gốc)
                discount = 0.5 * getGiaVe(); // Giảm 50% cho trẻ nhỏ
                break;
            default:
                setGiaVe(0);
                discount = 0;
                break;
        }
    }

    public String getHoTen() {
        return txtHoTen.getText();
    }

    public void setHoTen(String hoTen) {
        txtHoTen.setText(hoTen);
    }

    public String getTrangThai() {
        return (String) cbTrangThai.getSelectedItem();
    }

    public void setTrangThai(String trangThai, Float giaVe) {
        cbTrangThai.setSelectedItem(trangThai);
        updateGiaVe(giaVe); // Cập nhật giá vé khi thay đổi trạng thái
    }

    public String getCCCD() {
        return txtCCCD.getText();
    }

    public void setCCCD(String cccd) {
        txtCCCD.setText(cccd);
    }

    public double getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(float giaVe) {
        this.giaVe = giaVe;
    }

    public double getDiscount() {
        return discount; // Thêm phương thức getter cho discount
    }
}
