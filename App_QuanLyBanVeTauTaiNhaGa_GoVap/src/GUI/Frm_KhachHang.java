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
        JPanel_Right_Left.setBackground(Color.BLACK);

        Jpanel_Right.setLayout(new BorderLayout());
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

        JPanel_Right_Top.setBackground(Color.LIGHT_GRAY);
        //BOX RIGHT_LEFT
        // Dòng 1: label chứa ảnh
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
        lbluserName = new Label("Khách hàng");
        lbluserName.setAlignment(Label.CENTER);
        lbluserName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbluserName.setForeground(Color.red);
        box_rightleft.add(Box.createRigidArea(new Dimension(10, 30)));
        box_rightleft.add(lbluserName);

        // DÒNG 3: Chứa nút thêm, sửa, lưu
        Box line3 = Box.createHorizontalBox();// Thay đổi màu nền và màu chữ cho các nút
        Color btnColor = new Color(70, 130, 180); // Màu xanh lam
        Color btnTextColor = Color.WHITE; // Màu chữ trắng

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
        TitledBorder titledBorder_righttop = BorderFactory.createTitledBorder("Thông tin khách hàng");
        Font font = new Font("Courier New", Font.BOLD, 18);
        titledBorder_righttop.setTitleFont(font);
        titledBorder_righttop.setTitleColor(btnTextColor);
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
        modelNoiDung = new DefaultTableModel(tieuDeTableHeader,0);
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
        txtHangThanhVien.addItem("Bạc");
        txtHangThanhVien.addItem("Vàng");
        txtHangThanhVien.addItem("Kim cương");

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
                    } catch (SQLException ex) {
                        ex.printStackTrace(); // Xử lý lỗi nếu cần
                    }
                }
            }
        });
    }
    // gõ sdt, dò trong ds khách hàng load lên, nếu trùng, load khách hàng lên txt

    public void loadKH(String sdt) throws SQLException {
        // lấy toàn bộ danh sách
        List<KhachHang> ds = new ArrayList<>();
        DAO_KhachHang dao_khachHang = new DAO_KhachHang();
        boolean found = false;
        ds = dao_khachHang.getAllList();
        for (int i = 0; i < ds.size(); i++) {
            KhachHang tmp = ds.get(i);
            if (sdt.equals(tmp.getSoDienThoai())) {
                found = true;
                txtMaKH.setText(tmp.getMaKhachHang());

                String maLoaiKH = tmp.getLoaiKhachHang().getMaLoaiKhachHang();
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
                txtSDT.setText(tmp.getSoDienThoai());
                txtTenKH.setText(tmp.getTenKhachHang());
                txtCCCD.setText(tmp.getCCCD());
                txtDiaChi.setText(tmp.getDiaChi());
                txtDiemTichLuy.setText(String.valueOf(tmp.getDiemTichLuy()));
                LocalDate ngaySinh = tmp.getNgaySinh(); // Lấy giá trị LocalDate từ đối tượng KhachHang
                Date birth = java.sql.Date.valueOf(ngaySinh); // Chuyển LocalDate sang java.util.Date
                txtNgaySinh.setDate(birth); // Set giá trị vào JDateChooser

                LocalDate ngayThamGia = tmp.getNgayThamgGia(); // Lấy giá trị LocalDate từ đối tượng KhachHang
                Date join = java.sql.Date.valueOf(ngayThamGia); // Chuyển LocalDate sang java.util.Date
                txtNgayThamGia.setDate(join); // Set giá trị vào JDateChooser

                String hangTV = tmp.getHangThanhVien();
                if (hangTV.equalsIgnoreCase("Bạc")) {
                    txtHangThanhVien.setSelectedItem("Bạc");
                } else if (hangTV.equalsIgnoreCase("Vàng")) {
                    txtHangThanhVien.setSelectedItem("Vàng");
                } else if (hangTV.equalsIgnoreCase("Kim cương")) {
                    txtHangThanhVien.setSelectedItem("Kim cương");
                }
                lbluserName.setText(txtTenKH.getText());
                // sau khi load lên, không cho sửa bất kì trường nào,trừ khi ấn nút sửa
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
                break;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
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
        DAO_KhachHang dao_khachHang = new DAO_KhachHang();
        List<KhachHang> ds = new ArrayList<>();
        ds = dao_khachHang.getAllList();
        int n = ds.size() + 1;
        String maKhachHangTiepTheo = ""; // Lấy mã khách hàng tiếp theo
        if (n <= 9) {
            maKhachHangTiepTheo = "KH00" + n;
        } else {
            maKhachHangTiepTheo = "KH0" + n;
        }

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
        txtHangThanhVien.setSelectedItem("Bạc");
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
