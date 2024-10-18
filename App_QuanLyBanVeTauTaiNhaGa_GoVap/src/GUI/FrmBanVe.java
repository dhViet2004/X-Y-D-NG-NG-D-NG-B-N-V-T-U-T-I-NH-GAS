package GUI;

import DAO.DAO_BanVe;
import Database.ConnectDatabase;
import Entity.Tau;
import Entity.ToaTau;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public class FrmBanVe extends JFrame implements ActionListener {
    private JPanel contain;
    private JPanel JPanel_Menu;
    private JPanel Jpanel_Main;
    private JLabel lab_Title;
    private JPanel JPanel_BanVe;
    private JPanel JPanel_DSTau;
    private JPanel JPanel_TimTau;
    private JTextField txt_GaDen;
    private JButton btnTimTau;
    private JPanel JPanel_NgayDi;
    private JPanel JPanel_NgayVe;
    private JRadioButton btnRadio_MotChieu;
    private JRadioButton btnRadio_KhuHoi;
    private JPanel JPanelKetQuaTimTau;
    private JPanel JPanelTau;  // JPanel để hiển thị các nút chuyến tàu
    private JPanel JPanel_Toa;
    private JPanel JPanel_ChoNgoi;
    private JDateChooser dateChooserNgayDi = new JDateChooser();
    private JDateChooser dateChooserNgayVe = new JDateChooser();
    private DAO_BanVe daoBanVe;
    private ButtonGroup btnGroup;

    public FrmBanVe() {
        setTitle("Bán Vé");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(contain);

        // Calendar
        JPanel_NgayDi.add(dateChooserNgayDi);
        JPanel_NgayVe.add(dateChooserNgayVe);

        // RadioGroup
        btnGroup = new ButtonGroup();
        btnGroup.add(btnRadio_MotChieu);
        btnGroup.add(btnRadio_KhuHoi);

        ConnectDatabase.getInstance().connect(); // Kết nối database

        daoBanVe = new DAO_BanVe(); // Khởi tạo DAO_BanVe
        btnTimTau.addActionListener(this); // Thêm sự kiện cho nút tìm tàu
        btnRadio_KhuHoi.addActionListener(this);
    }

    public static void main(String[] args) {
        FrmBanVe frm = new FrmBanVe();
        frm.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTimTau) {
            // Lấy thông tin ga đến và ngày đi
            String gaDen = txt_GaDen.getText();

            // Định dạng ngày từ JDateChooser
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String ngayDi = sdf.format(dateChooserNgayDi.getDate());

            // Gọi DAO để tìm các chuyến tàu
            List<Tau> danhSachTau = null;
            try {
                danhSachTau = daoBanVe.getTrainsByDateAndDestination(gaDen, ngayDi);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            // Hiển thị kết quả dưới dạng các JButton có hình ảnh
            JPanelTau.removeAll(); // Xóa các nút trước đó nếu có
            JPanelTau.setLayout(new FlowLayout());

            if (danhSachTau.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy chuyến tàu nào.");
            } else {
                for (Tau tau : danhSachTau) {
                    String tauInfo = "Mã tàu: " + tau.getMaTau() + ", Tên tàu: " + tau.getTenTau();
                    JButton tauButton = new JButton(tauInfo);

                    // Thêm hình ảnh đại diện cho nút
                    URL imageUrl = getClass().getResource("/Anh_HeThong/DauTauHoa.png");
                    if (imageUrl != null) {
                        ImageIcon icon = new ImageIcon(imageUrl);
                        Image image = icon.getImage(); // Lấy hình ảnh từ ImageIcon
                        Image resizedImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Thay đổi kích thước
                        icon = new ImageIcon(resizedImage); // Tạo lại ImageIcon với hình ảnh đã thay đổi kích thước
                        tauButton.setIcon(icon);
                    } else {
                        System.out.println("File ảnh không tồn tại hoặc đường dẫn không chính xác.");
                    }

                    // Thêm ActionListener cho mỗi nút tàu
                    tauButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            DAO_BanVe daoBanVe = new DAO_BanVe();
                            // Gọi DAO để tìm các toa của tàu
                            List<ToaTau> danhSachToa = null;

                            try {
                                danhSachToa = daoBanVe.getToaByMaTau(tau.getMaTau());
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }

                            // Hiển thị toa dưới dạng hình ảnh
                            JPanel_Toa.removeAll();
                            JPanel_Toa.setLayout(new FlowLayout());

                            if (danhSachToa.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Không tìm thấy toa tàu.");
                            } else {
                                // Lấy đối tượng toa cuối cùng
                                ToaTau toaCuoi = danhSachToa.get(danhSachToa.size() - 1);

                                for (ToaTau toaTau : danhSachToa) {
                                    URL image = null;

                                    // Kiểm tra thứ tự của toa để gán hình ảnh tương ứng
                                    if (toaTau.getThuTu() == 1) {
                                        image = getClass().getResource("/Anh_HeThong/DauTauHoaMauVang.png");
                                    } else if (toaTau.getThuTu() == toaCuoi.getThuTu()) {
                                        image = getClass().getResource("/Anh_HeThong/DuoiTauHoaMauVang.png");
                                    } else {
                                        image = getClass().getResource("/Anh_HeThong/ThanToaMauVang.png");
                                    }

                                    JButton toaButton = new JButton("Toa: " + toaTau.getMaToa()); // Hiển thị mã toa

                                    // Thêm hình ảnh cho nút
                                    if (image != null) {
                                        ImageIcon icon = new ImageIcon(image);
                                        Image img = icon.getImage();
                                        Image resizedImage = img.getScaledInstance(100, 50, Image.SCALE_SMOOTH); // Tăng kích thước hình ảnh
                                        icon = new ImageIcon(resizedImage);
                                        toaButton.setIcon(icon);
                                    } else {
                                        System.out.println("Ảnh không tồn tại.");
                                    }

                                    // Thêm hiệu ứng hover
                                    Color originalBackground = toaButton.getBackground(); // Lưu màu nền ban đầu
                                    Color hoverBackground = new Color(250,196,58); // Màu nền khi hover

                                    toaButton.addMouseListener(new java.awt.event.MouseAdapter() {
                                        @Override
                                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                                            toaButton.setBackground(hoverBackground); // Đổi màu khi hover
                                            toaButton.setOpaque(true); // Đảm bảo màu được hiển thị
                                        }

                                        @Override
                                        public void mouseExited(java.awt.event.MouseEvent evt) {
                                            toaButton.setBackground(originalBackground); // Trả lại màu nền ban đầu
                                            toaButton.setOpaque(false); // Xóa màu nền khi không hover
                                        }
                                    });

                                    // Thêm nút toa vào JPanel_Toa
                                    JPanel_Toa.add(toaButton);
                                    toaButton.setHorizontalTextPosition(SwingConstants.CENTER);
                                    toaButton.setVerticalTextPosition(SwingConstants.BOTTOM);
                                    toaButton.setContentAreaFilled(false);
                                    toaButton.setBorderPainted(false);

                                    // Căn chỉnh biểu tượng với viền của nút
                                    toaButton.setIconTextGap(0);  // Không có khoảng cách giữa biểu tượng và văn bản
                                    toaButton.setMargin(new Insets(0, 0, 0, 0));  // Loại bỏ phần đệm của nút
                                    toaButton.setContentAreaFilled(false);  // Loại bỏ vùng nội dung mặc định của nút
                                    toaButton.setBorderPainted(false);  // Loại bỏ viền mặc định của nút
                                }

                                // Cập nhật giao diện
                                JPanel_Toa.revalidate();
                                JPanel_Toa.repaint();
                            }
                        }
                    });

                    // Thêm nút vào JPanelTau
                    JPanelTau.add(tauButton);
                    tauButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    tauButton.setVerticalTextPosition(SwingConstants.BOTTOM);
                    tauButton.setContentAreaFilled(false);
                    tauButton.setBorderPainted(false);
                    // Thêm hiệu ứng hover
                    Color originalBackground = tauButton.getBackground(); // Lưu màu nền ban đầu
                    Color hoverBackground = new Color(250,196,58); // Màu nền khi hover


                }
                JPanelTau.revalidate(); // Cập nhật giao diện
                JPanelTau.repaint();
            }
        } else if (e.getSource() == btnRadio_MotChieu) {

        } else if (e.getSource() == btnRadio_KhuHoi) {

        }
    }
}
