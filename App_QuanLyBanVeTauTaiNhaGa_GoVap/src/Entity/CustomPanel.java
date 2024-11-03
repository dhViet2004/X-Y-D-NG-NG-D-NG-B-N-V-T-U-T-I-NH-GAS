package Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;

public class CustomPanel extends JPanel {
    private JTextField txtHoTen;
    private JTextField txtCCCD;
    private JComboBox<String> cbTrangThai;
    private float giaVe;  // Lưu giá vé
    private double discount;  // Lưu giảm giá
    private LocalDate birthDate;  // Lưu ngày sinh của trẻ

    public CustomPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Dimension textFieldSize = new Dimension(150, 30);

        // Panel nhập họ tên
        JPanel hoTenPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblHoTen = new JLabel("Họ tên: ");
        txtHoTen = new JTextField(15);
        txtHoTen.setPreferredSize(textFieldSize);
        hoTenPanel.add(lblHoTen);
        hoTenPanel.add(txtHoTen);

        // Panel chọn đối tượng (Người lớn, Sinh viên, Trẻ nhỏ)
        JPanel trangThaiPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblTrangThai = new JLabel("Đối tượng:");
        cbTrangThai = new JComboBox<>(new String[]{"Người lớn", "Sinh viên", "Trẻ nhỏ"});
        cbTrangThai.setPreferredSize(textFieldSize);
        trangThaiPanel.add(lblTrangThai);
        trangThaiPanel.add(cbTrangThai);

        // Panel nhập CCCD
        JPanel cccdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblCCCD = new JLabel("CCCD:  ");
        txtCCCD = new JTextField(15);
        txtCCCD.setPreferredSize(textFieldSize);
        cccdPanel.add(lblCCCD);
        cccdPanel.add(txtCCCD);

        // Thêm các panel vào CustomPanel
        add(hoTenPanel);
        add(trangThaiPanel);
        add(cccdPanel);

        // Thêm sự kiện cho JComboBox đối tượng
        cbTrangThai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) cbTrangThai.getSelectedItem();
                if ("Trẻ nhỏ".equals(selectedItem)) {
                    showBirthdateDialog();  // Hiển thị hộp thoại nhập ngày sinh
                } else {
                    txtCCCD.setEnabled(true);  // Mở khóa trường CCCD
                }
                updateGiaVe(giaVe);  // Cập nhật giá vé và giảm giá

                // Cập nhật giá trị trên bảng nếu có
                JTable table = (JTable) SwingUtilities.getAncestorOfClass(JTable.class, CustomPanel.this);
                if (table != null) {
                    int row = table.getEditingRow();
                    table.getModel().setValueAt(discount, row, 3);  // Cập nhật cột "Giảm đối tượng"
                    table.getModel().setValueAt(giaVe - discount, row, 5);  // Cập nhật cột "Thành tiền"
                    table.revalidate();
                }
            }
        });
    }

    // Hiển thị hộp thoại nhập ngày sinh với JComboBox
    private void showBirthdateDialog() {
        JComboBox<Integer> cbNgay = new JComboBox<>();
        JComboBox<Integer> cbThang = new JComboBox<>();
        JComboBox<Integer> cbNam = new JComboBox<>();

        // Thêm giá trị cho ngày (1-31)
        for (int i = 1; i <= 31; i++) {
            cbNgay.addItem(i);
        }

        // Thêm giá trị cho tháng (1-12)
        for (int i = 1; i <= 12; i++) {
            cbThang.addItem(i);
        }

        // Thêm giá trị cho năm (2000 - năm hiện tại)
        int currentYear = LocalDate.now().getYear();
        for (int i = 2000; i <= currentYear; i++) {
            cbNam.addItem(i);
        }

        // Panel chứa các JComboBox
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("Ngày:"));
        panel.add(cbNgay);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Tháng:"));
        panel.add(cbThang);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Năm:"));
        panel.add(cbNam);

        // Hiển thị hộp thoại nhập ngày sinh
        int result = JOptionPane.showConfirmDialog(
                this, panel, "Nhập ngày sinh của trẻ", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            int ngay = (int) cbNgay.getSelectedItem();
            int thang = (int) cbThang.getSelectedItem();
            int nam = (int) cbNam.getSelectedItem();
            System.out.println("Ngày sinh: " + ngay + "/" + thang + "/" + nam);

            // Lưu ngày sinh
            birthDate = LocalDate.of(nam, thang, ngay);

            // Kiểm tra và tính tuổi
            int tuoi = tinhTuoi();
            System.out.println("Tuổi của trẻ: " + tuoi);

            if (tuoi >= 12) {
                JOptionPane.showMessageDialog(this, "Trẻ lớn hơn 12 tuổi, không đủ điều kiện vé trẻ nhỏ!");
                cbTrangThai.setSelectedItem("Người lớn");
            } else if(tuoi <=6){
                JOptionPane.showMessageDialog(this,"Trẻ nhỏ hơn 6 tuổi không cần mua vé.Vui lòng xóa vé hoặc chọn lại đối tượng");
                cbTrangThai.setSelectedItem("Người lớn");
            }else {
                txtHoTen.setEnabled(true);
                txtCCCD.setText("");
                txtCCCD.setEnabled(false);
            }
        }
    }

    // Phương thức tính tuổi
    private int tinhTuoi() {
        if (birthDate == null) {
            return 0; // Nếu chưa có ngày sinh, trả về 0
        }
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    // Getter cho tuổi
    public int getTuoi() {
        return tinhTuoi(); // Gọi phương thức tính tuổi
    }

    // Cập nhật giá vé và giảm giá dựa trên đối tượng
    public void updateGiaVe(float giaVe) {
        String selectedItem = (String) cbTrangThai.getSelectedItem();
        switch (selectedItem) {
            case "Người lớn":
                setGiaVe(giaVe);
                discount = 0;
                break;
            case "Sinh viên":
                setGiaVe(giaVe);
                discount = 0.1* getGiaVe();
                break;
            case "Trẻ nhỏ":
                setGiaVe(giaVe);
                discount = 0.25 * getGiaVe();
                break;
            default:
                setGiaVe(0);
                discount = 0;
                break;
        }
    }

    // Getter và Setter cho các thuộc tính
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
        updateGiaVe(giaVe);
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
        return discount;
    }
}
