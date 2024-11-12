package GUI;

import DAO.DAO_KhachHang;
import Entity.KhachHang;
import Entity.LoaiKhachHang;
import Entity.RoundedButton;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Frm_KhachHang extends JFrame implements ActionListener, MouseListener {
    private final JButton btnBanVe;
    private final JButton btnTraCuu;
    private final JButton btnThongKeTheoCa;
    private final JButton btnQuanLyKhachHang;
    private final JButton btnQuanLyNhanVien;
    private final JButton btnQuanLyChuyenTau;
    private final JButton btnQuanLyKhuyenMai;
    private final JButton btnQuanLyDoanhThu;
    private final JButton btnThemKH;
    private final JButton btnSuaKH;
    private final JButton btnLuu;
    private final JComboBox<String> txtLoaiKH;
    private final JTextField txtSDT;
    private final JTextField txtTenKH;
    private final JComboBox<String> txtHangThanhVien;
    private final JTextField txtDiaChi;
    private final JTextField txtCCCD;
    private final JTextField txtDiemTichLuy;
    private final JDateChooser txtNgaySinh;
    private final JDateChooser txtNgayThamGia;
    private final Label lbluserName;
    private final DefaultTableModel modelNoiDung;
    private final JTable tableNoiDung;
    private JPanel contentPanel;
    private JPanel JPanel_Menu;
    private JPanel Jpanel_Right;
    private JPanel JPanel_Right_Top;
    private JPanel JPanel_Right_Bottom;
    private JPanel JPanel_Right_Left;
    private JTextField txtMaKH;

    public Component getKHPanel(){
        return Jpanel_Right;
    }
    public Frm_KhachHang() {
        setTitle("Form Khách Hàng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);


        Jpanel_Right = new JPanel();
        JPanel_Right_Top = new JPanel();
        JPanel_Right_Bottom = new JPanel();
        JPanel_Right_Left = new JPanel();
        JPanel_Right_Left.setPreferredSize(new Dimension(400, 800));
        JPanel_Right_Left.setBackground(Color.WHITE);
        Jpanel_Right.setLayout(new BorderLayout());

        // MENU
        JPanel_Menu.setLayout(new BoxLayout(JPanel_Menu, BoxLayout.Y_AXIS));
        Color colorXanhDam = new Color(0,131,66);
        JPanel_Menu.setBackground(colorXanhDam); // Màu nền của MENU
        add(JPanel_Menu, BorderLayout.WEST);

        // tạo logo
        ImageIcon iconLogo = new ImageIcon(getClass().getResource("/Anh_HeThong/logo.png")); //SRC LOGO
        Image imgUser = iconLogo.getImage();
        Image scaledLogo = imgUser.getScaledInstance(250, 350, Image.SCALE_SMOOTH);
        ImageIcon scaledIconLogo = new ImageIcon(scaledLogo);

        // Tạo label chứa ảnh
        JLabel lblLogo = new JLabel(scaledIconLogo);
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel_Menu.add(lblLogo);

        // Thêm khoảng cách giữa logo và menu
        JPanel_Menu.add(Box.createRigidArea(new Dimension(0, 20))); // Khoảng cách dọc (10px)

        // Tạo các nút cho từng phần quản lý
        btnBanVe = createButton("Bán vé");
        btnTraCuu = createButton("Tra cứu");
        btnQuanLyKhachHang = createButton("Quản lý khách hàng");
        btnThongKeTheoCa = createButton("Thống kê theo ca");
        btnQuanLyChuyenTau = createButton("Quản lý chuyến tàu");
        btnQuanLyKhuyenMai = createButton("Quản lý chương trình khuyến mãi");
        btnQuanLyDoanhThu = createButton("Quản lý doanh thu");
        btnQuanLyNhanVien = createButton("Quản lý nhân viên");

        // Format nút với cùng kích thước, phông chữ và căn chỉnh
        Dimension buttonMenuSize = new Dimension(200, 60); // Tăng kích thước chiều cao của nút lên 60px
        Font fontMenu = new Font("Arial", Font.PLAIN, 16); // Đặt font chung cho tất cả các nút

        // Định dạng cho từng nút
        JButton[] buttons = {btnBanVe, btnTraCuu, btnThongKeTheoCa, btnQuanLyChuyenTau, btnQuanLyKhachHang, btnQuanLyKhuyenMai, btnQuanLyDoanhThu, btnQuanLyNhanVien};
        for (JButton btn : buttons) {
            btn.setPreferredSize(buttonMenuSize); // Đặt kích thước cố định cho nút
            btn.setFont(fontMenu); // Đặt font
            btn.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa text trên nút
            btn.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa nút trong JPanel_Menu
            setMaximumSize(buttonMenuSize);
            JPanel_Menu.add(Box.createRigidArea(new Dimension(0, 10))); // Thêm khoảng cách giữa các nút (10px)
            JPanel_Menu.add(btn); // Thêm nút vào JPanel_Menu
        }
        //MENU
        // set layout cho right_top(box dọc chứa các dòng), right_bottom(box ngang chứa hai bảng), right_left(box dọc chứa các dòng)
        Box box_righttop = Box.createHorizontalBox();
        Box box_rightbottom = Box.createHorizontalBox();
        Box box_rightleft = Box.createVerticalBox();
        //thêm box vào panel
        JPanel_Right_Top.add(box_righttop);
        JPanel_Right_Bottom.add(box_rightbottom);
        JPanel_Right_Left.add(box_rightleft);
        // thêm 3 panel vào right
        Jpanel_Right.add(JPanel_Right_Top, BorderLayout.CENTER);
        Jpanel_Right.add(JPanel_Right_Bottom, BorderLayout.SOUTH);
        Jpanel_Right.add(JPanel_Right_Left, BorderLayout.WEST);

        JPanel_Right_Top.setBackground(Color.WHITE);
        //BOX RIGHT_LEFT
        // Dòng 1: label chứa ảnh
        // Tạo ảnh
        ImageIcon iconUser = new ImageIcon(getClass().getResource("/Anh_HeThong/pink.png"));
        // Scale ảnh nếu cần
        Image imgAVT = iconUser.getImage();
        Image scaledImgAVT = imgAVT.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIconAVT = new ImageIcon(scaledImgAVT);

        // Tạo label chứa ảnh
        JLabel lblUser = new JLabel(scaledIconAVT);
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
        lbluserName = new Label("Khách hàng");
        lbluserName.setAlignment(Label.CENTER);
        lbluserName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbluserName.setForeground(Color.red);
        box_rightleft.add(Box.createRigidArea(new Dimension(10, 30)));
        box_rightleft.add(lbluserName);

        // DÒNG 3: Chứa nút thêm, sửa, lưu
        Box line3 = Box.createHorizontalBox();// Thay đổi màu nền và màu chữ cho các nút
        Color btnColor = new Color(70, 130, 180); // Màu xanh lam
        Color btnTextColor = Color.BLACK; // Màu chữ trắng

        btnThemKH = new RoundedButton("Thêm");
        btnThemKH.setBackground(btnColor);
        btnThemKH.setForeground(btnTextColor);
        btnThemKH.setFont(new Font("Arial", Font.BOLD, 14));
        btnSuaKH = new RoundedButton("Sửa");
        btnSuaKH.setBackground(btnColor);
        btnSuaKH.setForeground(btnTextColor);
        btnSuaKH.setFont(new Font("Arial", Font.BOLD, 14));
        btnLuu = new RoundedButton("Lưu");
        btnLuu.setBackground(btnColor);
        btnLuu.setForeground(btnTextColor);
        btnLuu.setFont(new Font("Arial", Font.BOLD, 14));
// chỉnh kích thước 3 nút
        Dimension buttonSize = new Dimension(100, 40);
        btnThemKH.setPreferredSize(buttonSize);
        btnSuaKH.setPreferredSize(buttonSize);
        btnLuu.setPreferredSize(buttonSize);

// Set kích thước tối đa để nút không bị co lại
        btnThemKH.setMaximumSize(buttonSize);
        btnSuaKH.setMaximumSize(buttonSize);
        btnLuu.setMaximumSize(buttonSize);

        //
        box_rightleft.add(Box.createRigidArea(new Dimension(10, 100)));
        line3.add(btnThemKH);
        line3.add(Box.createRigidArea(new Dimension(30, 10)));
        line3.add(btnSuaKH);
        line3.add(Box.createRigidArea(new Dimension(30, 10)));
        line3.add(btnLuu);
        box_rightleft.add(line3);

        // RIGHT_TOP:  title border là thông tin khách hàng, box_righttop chứa các dòng thông tin khách hàng
        TitledBorder titledBorder_righttop = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorXanhDam),"Thông tin khách hàng");
        Font font = new Font("Courier New", Font.BOLD|Font.ITALIC, 18);
        titledBorder_righttop.setTitleFont(font);
        titledBorder_righttop.setTitleColor(Color.red);
        JPanel_Right_Top.setBorder(titledBorder_righttop);
        // thêm từng dòng vô box_righttop
        Box tieuDe = Box.createVerticalBox();
        Box noiDung = Box.createVerticalBox();
        box_righttop.add(tieuDe);
        box_righttop.add(Box.createRigidArea(new Dimension(180, 10)));
        box_righttop.add(noiDung);
// Dòng mã khách hàng
        JLabel lblMaKH = new JLabel("Mã khách hàng: ");
        lblMaKH.setAlignmentX(Component.LEFT_ALIGNMENT);
        tieuDe.add(lblMaKH);
        tieuDe.add(Box.createRigidArea(new Dimension(30, 25)));

// Dòng loại khách hàng
        JLabel lblLoaiKH = new JLabel("Loại khách hàng: ");
        lblLoaiKH.setAlignmentX(Component.LEFT_ALIGNMENT);
        tieuDe.add(lblLoaiKH);
        tieuDe.add(Box.createRigidArea(new Dimension(30, 25)));

// Dòng số điện thoại
        JLabel lblSDT = new JLabel("Số điện thoại: ");
        lblSDT.setAlignmentX(Component.LEFT_ALIGNMENT);
        tieuDe.add(lblSDT);
        tieuDe.add(Box.createRigidArea(new Dimension(30, 25)));

// Dòng tên khách hàng
        JLabel lblTenKH = new JLabel("Tên khách hàng: ");
        lblTenKH.setAlignmentX(Component.LEFT_ALIGNMENT);
        tieuDe.add(lblTenKH);
        tieuDe.add(Box.createRigidArea(new Dimension(30, 25)));

// Dòng địa chỉ
        JLabel lblDiaChi = new JLabel("Địa chỉ: ");
        lblDiaChi.setAlignmentX(Component.LEFT_ALIGNMENT);
        tieuDe.add(lblDiaChi);
        tieuDe.add(Box.createRigidArea(new Dimension(30, 25)));

// Dòng CCCD
        JLabel lblCCCD = new JLabel("CCCD: ");
        lblCCCD.setAlignmentX(Component.LEFT_ALIGNMENT);
        tieuDe.add(lblCCCD);
        tieuDe.add(Box.createRigidArea(new Dimension(30, 25)));

// Dòng điểm tích lũy
        JLabel lblDiemTichLuy = new JLabel("Điểm tích lũy: ");
        lblDiemTichLuy.setAlignmentX(Component.LEFT_ALIGNMENT);
        tieuDe.add(lblDiemTichLuy);
        tieuDe.add(Box.createRigidArea(new Dimension(30, 25)));

// Dòng ngày sinh
        JLabel lblNgaySinh = new JLabel("Ngày sinh: ");
        lblNgaySinh.setAlignmentX(Component.LEFT_ALIGNMENT);
        tieuDe.add(lblNgaySinh);
        tieuDe.add(Box.createRigidArea(new Dimension(30, 25)));

// Dòng ngày tham gia
        JLabel lblNgayThamGia = new JLabel("Ngày tham gia: ");
        lblNgayThamGia.setAlignmentX(Component.LEFT_ALIGNMENT);
        tieuDe.add(lblNgayThamGia);
        tieuDe.add(Box.createRigidArea(new Dimension(30, 25)));

// Dòng hạng thành viên
        JLabel lblHangThanhVien = new JLabel("Hạng thành viên: ");
        lblHangThanhVien.setAlignmentX(Component.LEFT_ALIGNMENT);
        tieuDe.add(lblHangThanhVien);
        tieuDe.add(Box.createRigidArea(new Dimension(30, 25)));

        lblMaKH.setForeground(btnTextColor);
        lblLoaiKH.setForeground(btnTextColor);
        lblHangThanhVien.setForeground(btnTextColor);
        lblSDT.setForeground(btnTextColor);
        lblTenKH.setForeground(btnTextColor);
        lblDiaChi.setForeground(btnTextColor);
        lblCCCD.setForeground(btnTextColor);
        lblDiemTichLuy.setForeground(btnTextColor);
        lblNgaySinh.setForeground(btnTextColor);
        lblNgayThamGia.setForeground(btnTextColor);

        String tieuDeTableHeader[] = {"Hóa đơn", "Ngày lặp", "Khuyến mãi", "Chiếc khấu(%)", "Tiền giảm(vnd)"};
        modelNoiDung = new DefaultTableModel(tieuDeTableHeader, 0);
        tableNoiDung = new JTable(modelNoiDung);
        JScrollPane scrollNoiDung = new JScrollPane(tableNoiDung);

// Create the scroll pane for the table
//        scrollNoiDung.setPreferredSize(new Dimension(100, 200)); // Set preferred size if needed
//        scrollNoiDung.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200)); // Allow it to expand horizontally


        txtMaKH = new JTextField(50);
        txtLoaiKH = new JComboBox<String>();
        txtLoaiKH.addItem("Khách hàng thường");
        txtLoaiKH.addItem("Khách hàng vip");
        txtLoaiKH.addItem("Khách hàng doanh nghiệp");
        txtLoaiKH.addItem("Khách hàng thân thiết");
        txtLoaiKH.addItem("Khách hàng khuyến mãi");
        txtSDT = new JTextField(50);
        txtTenKH = new JTextField(50);
        txtDiaChi = new JTextField(50);
        txtCCCD = new JTextField(50);
        txtDiemTichLuy = new JTextField(50);
        txtNgaySinh = new JDateChooser();
        txtNgayThamGia = new JDateChooser();
        txtHangThanhVien = new JComboBox<String>();
        txtHangThanhVien.addItem("Silver");
        txtHangThanhVien.addItem("Gold");
        txtHangThanhVien.addItem("Diamond");

        noiDung.add(txtMaKH);
        txtMaKH.setMaximumSize(new Dimension(400, 30));
        txtMaKH.setEditable(false);
        noiDung.add(Box.createRigidArea(new Dimension(30, 20)));
        noiDung.add(txtLoaiKH);
        txtLoaiKH.setMaximumSize(new Dimension(400, 60));
        noiDung.add(Box.createRigidArea(new Dimension(30, 20)));
        noiDung.add(txtSDT);
        txtSDT.setMaximumSize(new Dimension(400, 60));
        noiDung.add(Box.createRigidArea(new Dimension(30, 20)));
        noiDung.add(txtTenKH);
        txtTenKH.setMaximumSize(new Dimension(400, 60));
        noiDung.add(Box.createRigidArea(new Dimension(30, 20)));
        noiDung.add(txtDiaChi);
        txtDiaChi.setMaximumSize(new Dimension(400, 60));
        noiDung.add(Box.createRigidArea(new Dimension(30, 20)));
        noiDung.add(txtCCCD);
        txtCCCD.setMaximumSize(new Dimension(400, 60));
        noiDung.add(Box.createRigidArea(new Dimension(30, 20)));
        noiDung.add(txtDiemTichLuy);
        txtDiemTichLuy.setMaximumSize(new Dimension(400, 60));
        noiDung.add(Box.createRigidArea(new Dimension(30, 20)));
        noiDung.add(txtNgaySinh);
        txtNgaySinh.setMaximumSize(new Dimension(400, 60));
        noiDung.add(Box.createRigidArea(new Dimension(30, 20)));
        noiDung.add(txtNgayThamGia);
        txtNgayThamGia.setMaximumSize(new Dimension(400, 60));
        noiDung.add(Box.createRigidArea(new Dimension(30, 20)));
        noiDung.add(txtHangThanhVien);
        txtHangThanhVien.setMaximumSize(new Dimension(400, 60));
        noiDung.add(Box.createRigidArea(new Dimension(30, 20)));
        txtHangThanhVien.setMaximumSize(new Dimension(400, 60));

        // đăng kí sự kiện
        btnThemKH.addActionListener(this);
        btnSuaKH.addActionListener(this);
        btnLuu.addActionListener(this);
        txtSDT.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) { // Kiểm tra phím Enter
                    String sdt = txtSDT.getText().trim();
                    try {
                        loadKH(sdt);
                    } catch (Exception ex) {
                        ex.printStackTrace(); // Xử lý lỗi nếu cần
                    }
                }
            }
        });
        btnBanVe.addActionListener(this);
        btnTraCuu.addActionListener(this);
        btnThongKeTheoCa.addActionListener(this);
        btnQuanLyChuyenTau.addActionListener(this);
        btnQuanLyKhuyenMai.addActionListener(this);
        btnQuanLyKhachHang.addActionListener(this);
        btnQuanLyDoanhThu.addActionListener(this);
        btnQuanLyNhanVien.addActionListener(this);
    }
    // gõ sdt, dò trong ds khách hàng load lên, nếu trùng, load khách hàng lên txt

    public void loadKH(String sdt) throws Exception {
        DAO_KhachHang dao_khachHang = new DAO_KhachHang();
        KhachHang kh = dao_khachHang.findCustomerByEncryptedPhone(sdt); // Tìm khách hàng đã mã hóa số điện thoại

        if (kh != null) {
            // giải mã thông tin của khách hàng trước khi hiển thị
            // giải mã và mã hóa hiển thị sdt
            String sdtEC = kh.getSoDienThoai();
            String sdtDE = dao_khachHang.decryptAES(sdtEC);
            String sdtHienThi = maHoaHienThiSDT(sdtDE);
            // giải mã và hiển thị
            String tenEC = kh.getTenKhachHang();
            String tenDC = dao_khachHang.decryptAES(tenEC);
            // giải mã và mã hóa hiển thị cccd
            String cccdEN = kh.getCCCD();
            String cccdDC = dao_khachHang.decryptAES(cccdEN);
            String cccdHienThi = maHoaHienThiCCCD(cccdDC);
            // giải mã và mã hóa hiển thị địa chỉ
            String diaChiEN = kh.getDiaChi();
            String diaChiDC = dao_khachHang.decryptAES(diaChiEN);
            String diaChiHienThi = maHoaHienThiDiaChi(diaChiDC);
            // Nếu tìm thấy, hiển thị thông tin khách hàng lên giao diện
            txtMaKH.setText(kh.getMaKhachHang());

            String maLoaiKH = kh.getLoaiKhachHang().getMaLoaiKhachHang();
            if (maLoaiKH.equalsIgnoreCase("KH001")) {
                txtLoaiKH.setSelectedItem("Khách hàng thường");
            } else if (maLoaiKH.equalsIgnoreCase("KH002")) {
                txtLoaiKH.setSelectedItem("Khách hàng VIP");
            } else if (maLoaiKH.equalsIgnoreCase("KH003")) {
                txtLoaiKH.setSelectedItem("Khách hàng thân thiết");
            } else if (maLoaiKH.equalsIgnoreCase("KH004")) {
                txtLoaiKH.setSelectedItem("Khách hàng khuyến mãi");
            } else if (maLoaiKH.equalsIgnoreCase("KH005")) {
                txtLoaiKH.setSelectedItem("Khách hàng thường");
            }

            txtSDT.setText(sdtHienThi);
            txtTenKH.setText(tenDC);
            txtCCCD.setText(cccdHienThi);
            txtDiaChi.setText(diaChiHienThi);
            txtDiemTichLuy.setText(String.valueOf(kh.getDiemTichLuy()));

            LocalDate ngaySinh = kh.getNgaySinh();
            Date birth = java.sql.Date.valueOf(ngaySinh);
            txtNgaySinh.setDate(birth);

            LocalDate ngayThamGia = kh.getNgayThamgGia();
            Date join = java.sql.Date.valueOf(ngayThamGia);
            txtNgayThamGia.setDate(join);

            String hangTV = kh.getHangThanhVien();
            if (hangTV.equalsIgnoreCase("Silver")) {
                txtHangThanhVien.setSelectedItem("Silver");
            } else if (hangTV.equalsIgnoreCase("Gold")) {
                txtHangThanhVien.setSelectedItem("Gold");
            } else if (hangTV.equalsIgnoreCase("Diamond")) {
                txtHangThanhVien.setSelectedItem("Diamond");
            }

            lbluserName.setText(txtTenKH.getText());

            // Sau khi load lên, không cho sửa bất kì trường nào, trừ khi ấn nút sửa
            txtMaKH.setEditable(false);
            txtLoaiKH.setEnabled(false);
            txtSDT.setEditable(false);
            txtTenKH.setEditable(false);
            txtCCCD.setEditable(false);
            txtDiemTichLuy.setEditable(false);
            txtDiaChi.setEditable(false);
            txtNgaySinh.setEnabled(false);
            txtNgayThamGia.setEnabled(false);
            txtHangThanhVien.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    public String maHoaHienThiSDT(String phoneNumber) {
        if (phoneNumber.length() == 10) {
            // Giữ 2 chữ số đầu và 4 chữ số cuối, thay phần giữa bằng dấu "*"
            String maskedPhone = phoneNumber.substring(0, 2) + "******" + phoneNumber.substring(6);
            return maskedPhone;
        }
        return phoneNumber; // Nếu không phải 10 ký tự, trả về nguyên gốc
    }

    public String maHoaHienThiCCCD(String cccd) {
        if (cccd.length() == 12) {
            // Giữ 1 chữ số đầu và 1 chữ số cuối, thay phần giữa bằng dấu "*"
            String maskedCCCD = cccd.substring(0, 1) + "**********" + cccd.substring(11);
            return maskedCCCD;
        }
        return cccd; // Nếu không phải 12 ký tự, trả về nguyên gốc
    }

    public String maHoaHienThiDiaChi(String diaChi) {
        if (diaChi != null && !diaChi.isEmpty()) {
            // Ẩn toàn bộ địa chỉ bằng dấu "*"
            return "***********"; // Hoặc bạn có thể sử dụng diaChi.replaceAll(".", "*") để thay thế tất cả ký tự bằng dấu "*"
        }
        return diaChi; // Trả về nguyên gốc nếu địa chỉ rỗng hoặc null
    }
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 20)); // Đặt font
        button.setPreferredSize(new Dimension(200, 60)); // Kích thước nút
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // Chiếm hết chiều ngang
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa nút
        button.setBackground(Color.cyan); // Màu nền
//        button.setOpaque(false); // Làm cho nền trong suốt
        button.setForeground(Color.white); // Màu chữ
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 1), // Viền trắng
                BorderFactory.createEmptyBorder(0, 0, 0, 0) // Padding nếu cần
        ));
//        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
//        button.setBorderPainted(false); // Tắt viền nút
//        button.setFocusable(false); // Tắt chế độ focus
        return button;
    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == btnThemKH) {
            // xóa rỗng hết toàn bộ, MaKH set sẳn tăng dần theo dbs, cho phép chỉnh sửa tất cả ngoại trừ maKH
            try {
                xoaRongVaSetMaKH();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (obj == btnSuaKH) {
            // cho phép chỉnh sửa nếu như tất cả các ô có dữ liệu, tránh trường hợp nhấn thêm, chưa có dữ liệu những ô còn lại lại ấn sửa thì sai
            // nhưng không được sửa maKH, loaiKH, diemTichLuy, hangThanhVien, ngayThamGia
            enableEditing();
        } else if (obj == btnLuu) {
            // chỉ được lưu khi tất cả dòng có dữ liệu, tránh trường hợp nhấn thêm, trường sẽ thiếu dữ liệu, nhấn lưu sẽ sai
            // nếu đủ dữ liệu, nếu KH (thêm mới) chưa tồn tại thì thêm vào  sql, nếu KH(cập nhập dữ liệu) thì sẽ xóa KH đó trong sql trước rồi mới thêm
            // Validate input and save the customer information
            if (isInputValid()) {
                try {
                    saveCustomer();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Lỗi khi lưu khách hàng: " + ex.getMessage(), "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin cần thiết trước khi lưu.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } else if (obj == btnBanVe) {
            FrmBanVe frmBanVe = new FrmBanVe();
            frmBanVe.setVisible(true);
        } else if (obj == btnQuanLyKhuyenMai) {
            FrmKhuyenMai frmKhuyenMai = new FrmKhuyenMai();
            frmKhuyenMai.setVisible(true);
        }

    }

    private void saveCustomer() throws SQLException {
        DAO_KhachHang dao_khachHang = new DAO_KhachHang();
        String maLoaiKH = "";
        if (txtLoaiKH.getSelectedItem().toString().equalsIgnoreCase("Khách hàng thường")) {
            maLoaiKH = "KH001";
        } else if (txtLoaiKH.getSelectedItem().toString().equalsIgnoreCase("Khách hàng VIP")) {
            maLoaiKH = "KH002";
        } else if (txtLoaiKH.getSelectedItem().toString().equalsIgnoreCase("Khách hàng doanh nghiệp")) {
            maLoaiKH = "KH003";
        } else if (txtLoaiKH.getSelectedItem().toString().equalsIgnoreCase("Khách hàng thân thiết")) {
            maLoaiKH = "KH004";
        } else if (txtLoaiKH.getSelectedItem().toString().equalsIgnoreCase("Khách hàng khuyến mãi")) {
            maLoaiKH = "KH005";
        }
        LoaiKhachHang tmp = new LoaiKhachHang(maLoaiKH, txtLoaiKH.getSelectedItem().toString());
        KhachHang khachHang = null;
        // Gather customer information
        // Get the dates from the date choosers
        LocalDate ngaySinh = new java.sql.Date(txtNgaySinh.getDate().getTime()).toLocalDate();
        LocalDate ngayThamGia = new java.sql.Date(txtNgayThamGia.getDate().getTime()).toLocalDate();

        khachHang = new KhachHang(
                txtMaKH.getText().trim(),
                tmp,
                txtSDT.getText().trim(),
                txtTenKH.getText().trim(),
                txtCCCD.getText().trim(),
                txtDiaChi.getText().trim(),
                Double.parseDouble(txtDiemTichLuy.getText().trim()),
                ngaySinh,
                ngayThamGia,
                txtHangThanhVien.getSelectedItem().toString()
        );


        // nếu tồn tại
        if (dao_khachHang.customerExists(khachHang.getSoDienThoai())) {
            // cập nhật
            if (dao_khachHang.updateCustomer(khachHang)) {
                JOptionPane.showMessageDialog(this, "Cập nhật khách hàng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            // Add new customer
            dao_khachHang.addCustomer(khachHang);
            JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

        // Reset the form after saving
        xoaRongVaSetMaKH();
    }

    private void enableEditing() {
        // Kiểm tra xem tất cả các trường cần thiết đều có dữ liệu không
        if (isInputValid()) {
            // Cho phép người dùng chỉnh sửa một số trường
            txtSDT.setEditable(true);
            txtTenKH.setEditable(true);
            txtDiaChi.setEditable(true);
            txtCCCD.setEditable(true);
            txtNgaySinh.setEnabled(true); // Cho phép chỉnh sửa ngày sinh

            // Không cho phép chỉnh sửa các trường khác
            txtMaKH.setEditable(false);
            txtLoaiKH.setEnabled(false);
            txtDiemTichLuy.setEditable(false); // Không cho phép chỉnh sửa điểm tích lũy
            txtNgayThamGia.setEnabled(false); // Không cho phép chỉnh sửa ngày tham gia
            txtHangThanhVien.setEnabled(false); // Không cho phép chỉnh sửa hạng thành viên
        } else {
            JOptionPane.showMessageDialog(this, "Chưa có thông tin để chỉnh sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Phương thức kiểm tra xem tất cả các trường cần thiết có dữ liệu không
    private boolean isInputValid() {
        return !txtSDT.getText().trim().isEmpty() &&
                !txtTenKH.getText().trim().isEmpty() &&
                !txtDiaChi.getText().trim().isEmpty() &&
                !txtCCCD.getText().trim().isEmpty();
    }

    private void xoaRongVaSetMaKH() throws SQLException {
        // set maKH
        FrmBanVe fbv = new FrmBanVe();
        String maKhachHangTiepTheo = fbv.generateCustomerCode();


        // Thiết lập giá trị cho trường mã khách hàng
        txtMaKH.setText(maKhachHangTiepTheo);
        txtMaKH.setEditable(false); // Không cho phép sửa mã khách hàng

        // Xóa rỗng tất cả các trường còn lại
        txtSDT.setText("");
        txtTenKH.setText("");
        txtDiaChi.setText("");
        txtCCCD.setText("");
        txtDiemTichLuy.setText("");
        txtNgaySinh.setDate(null); // Đặt lại ngày sinh
        txtNgayThamGia.setDate(null); // Đặt lại ngày tham gia

        // Đặt lại tên người dùng
        lbluserName.setText("Khách hàng");

        // Cho phép chỉnh sửa các trường khác
        // Khách hàng mới sẽ là khách hàng thường, hạng bạc, điểm tích lũy 0, ngày tham gia: hiện tại
        txtLoaiKH.setSelectedItem("Khách hàng thường");
        txtLoaiKH.setEnabled(false);
        txtSDT.setEditable(true);
        txtTenKH.setEditable(true);
        txtCCCD.setEditable(true);

        txtDiemTichLuy.setText("0.0");
        txtDiemTichLuy.setEditable(false);
        txtDiaChi.setEditable(true);
        txtNgaySinh.setEnabled(true);
//        / Đặt "Ngày tham gia" là ngày hiện tại
        Calendar calendar = Calendar.getInstance();
        txtNgayThamGia.setDate(calendar.getTime()); // Set today's date
        txtNgayThamGia.setEnabled(false); // Disable editing
        txtHangThanhVien.setSelectedItem("Silv");
        txtHangThanhVien.setEnabled(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static void main(String[] args) {
        Frm_KhachHang frm = new Frm_KhachHang();
        frm.setVisible(true);
    }
}
