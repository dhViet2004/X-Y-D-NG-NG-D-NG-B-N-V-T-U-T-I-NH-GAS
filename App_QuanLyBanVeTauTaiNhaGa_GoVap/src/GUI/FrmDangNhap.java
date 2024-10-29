package GUI;

import DAO.DAO_LichLamViec;
import DAO.DAO_TaiKhoan;
import Database.ConnectDatabase;
import Entity.LichLamViec;
import Entity.NhanVien;
import Entity.TaiKhoan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FrmDangNhap extends JFrame implements ActionListener {
    private JPanel contentPane;
    private JLabel banner;
    private JPanel jPanel_Submit;
    private JButton btnDangNhap;
    private JPanel Jpanel_Input;
    private JTextField txtMaNhanVien;
    private JPasswordField txtMatKhau;
    private NhanVien nv;
    private DAO_LichLamViec llv_dao;
    private DAO_TaiKhoan dao;

    public FrmDangNhap() {
        setContentPane(contentPane);
        setTitle("Đăng nhập");
        setMinimumSize(new Dimension(800, 462));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Thêm ActionListener cho nút đăng nhập
        btnDangNhap.addActionListener(this);
        ConnectDatabase.getInstance().connect();

        llv_dao = new DAO_LichLamViec();
        dao = new DAO_TaiKhoan();

        txtMaNhanVien.setText("NV001");
        txtMatKhau.setText("password123");

    }

    public static void main(String[] args) {
        FrmDangNhap fm = new FrmDangNhap();
        fm.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDangNhap) {
            // Kiểm tra tên đăng nhập
            String user = txtMaNhanVien.getText();
            if (user == null || user.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên đăng nhập không được để trống");
                txtMaNhanVien.requestFocus(); // Đặt tiêu điểm vào trường tên đăng nhập
                return; // Thoát khỏi phương thức nếu tên đăng nhập không hợp lệ
            }

// Kiểm tra mật khẩu
            String password = txtMatKhau.getText();
            if (password == null || password.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống");
                txtMatKhau.requestFocus(); // Đặt tiêu điểm vào trường mật khẩu
                return; // Thoát khỏi phương thức nếu mật khẩu không hợp lệ
            }
            try {
                // Kiểm tra đăng nhập
                nv = dao.checkLogin(user, password);
                if (nv == null) {
                    JOptionPane.showMessageDialog(this, "Tên đăng nhập sai hoặc mật khẩu sai", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return; // Thoát khỏi phương thức nếu đăng nhập không thành công
                }

                LocalDateTime now = LocalDateTime.now(); // Lấy thời gian hiện tại
                LocalDate today = now.toLocalDate(); // Lấy ngày hôm nay
                System.out.println("today:" + today);
                // Lấy ca làm việc cho nhân viên trong ngày hôm nay
                List<LichLamViec> lichLamViecs = llv_dao.getCaLamViecForDate(nv.getMaNhanVien(), today);

                // Kiểm tra nếu không có ca làm việc nào được trả về
                if (lichLamViecs == null || lichLamViecs.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Không có ca làm việc nào cho ngày hôm nay", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Cập nhật trạng thái cho các ca làm việc
                    for (LichLamViec llv : lichLamViecs) {
                        LocalDateTime gioBatDau = llv.getGioBatDau(); // Lấy giờ bắt đầu ca làm việc
                        System.out.println("Gio bat dau:" + gioBatDau);//ma oi quy roi`
                        // So sánh giờ đăng nhập với giờ bắt đầu,
                        if (now.isAfter(gioBatDau)) {
                            llv.setTrangThai("Tre");
                        } else {
                            llv.setTrangThai("Dung gio");
                        }
                        System.out.println("Ma lich lam viec khi click button dang nhap:" + llv.getMaLichLamViec());
                        System.out.println("Tragn thai khi button dang nhap dc click:" + llv.getTrangThai());
                        // Cập nhật trạng thái vào cơ sở dữ liệu
                        llv_dao.updateTrangThai(llv.getMaLichLamViec(), llv.getTrangThai());
                    }
                }

                // Tiến hành mở form và thông báo đăng nhập thành công
                if (nv.getChucVu().trim().equalsIgnoreCase("Nhan Vien")) {
                    FrmBanVe a = new FrmBanVe();
                    a.setVisible(true);
                    this.dispose();
                    JOptionPane.showMessageDialog(this, "Đăng nhập thành công với vai trò Nhân viên");
                } else {
                    FrmLichLamViec b = new FrmLichLamViec();
                    b.setVisible(true);
                    this.dispose();
                    JOptionPane.showMessageDialog(this, "Đăng nhập thành công với vai trò quản lý");
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }


    }
}
