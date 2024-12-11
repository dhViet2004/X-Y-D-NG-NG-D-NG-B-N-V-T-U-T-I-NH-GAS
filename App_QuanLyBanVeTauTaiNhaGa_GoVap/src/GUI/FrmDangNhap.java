package GUI;

import DAO.DAO_LichLamViec;
import DAO.DAO_TaiKhoan;
import Database.ConnectDatabase;
import Entity.EmailSender;
import Entity.LichLamViec;
import Entity.NhanVien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    public String maNV = txtMaNhanVien.getText();
    public String getmaNV(){
        return maNV;
    }
    private JPasswordField txtMatKhau;
    private JButton btnQuenMatKhau;
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

        btnQuenMatKhau.addActionListener(this);

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
                    return;
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
                        System.out.println("Trang thai khi button dang nhap dc click:" + llv.getTrangThai());
                        // Cập nhật trạng thái vào cơ sở dữ liệu
                        llv_dao.updateTrangThai(llv.getMaLichLamViec(), llv.getTrangThai());
                    }
                }

                // Tiến hành mở form và thông báo đăng nhập thành công
                if (nv.getChucVu().trim().equalsIgnoreCase("Nhan Vien")) {
                    FrmBanVe a = new FrmBanVe(nv);
                    a.setVisible(true);
                    this.dispose();
                    JOptionPane.showMessageDialog(this, "Đăng nhập thành công với vai trò Nhân viên");
                } if(nv.getChucVu().trim().equalsIgnoreCase("Quan ly")) {
                    FrmBanVe b = new FrmBanVe(nv);
                    b.setVisible(true);
                    this.dispose();
                    JOptionPane.showMessageDialog(this, "Đăng nhập thành công với vai trò quản lý");
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }else if (e.getSource() == btnQuenMatKhau) {
            // Kiểm tra người dùng đã nhập tên đăng nhập chưa
            String user = txtMaNhanVien.getText();
            if (user == null || user.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên đăng nhập trước khi thực hiện quên mật khẩu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                txtMaNhanVien.requestFocus(); // Đặt tiêu điểm vào trường tên đăng nhập
                return;
            }

            // Hiển thị hộp thoại yêu cầu nhập email
            String email = JOptionPane.showInputDialog(this, "Vui lòng nhập email để lấy lại mật khẩu:", "Quên mật khẩu", JOptionPane.INFORMATION_MESSAGE);

            // Kiểm tra email nhập vào
            if (email == null || email.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Email không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Hiển thị thông báo đang gửi email
                JOptionPane.showMessageDialog(this, "Đang gửi email. Vui lòng chờ trong giây lát...", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                // Kiểm tra email trong cơ sở dữ liệu
                String password = dao.getPasswordByEmail(email); // Phương thức này phải được triển khai trong lớp DAO_TaiKhoan
                if (password == null) {
                    JOptionPane.showMessageDialog(this, "Email không tồn tại trong hệ thống", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Gửi email chứa mật khẩu
                    boolean emailSent = EmailSender.sendPasswordEmail(email, password);

                    if (emailSent) {
                        JOptionPane.showMessageDialog(this, "Mật khẩu đã được gửi đến email của bạn.", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Gửi email thất bại. Vui lòng thử lại sau.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }

    }
}
