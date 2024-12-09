
package GUI;

import DAO.DAO_BanVe;
import DAO.DAO_DoiVe;
import DAO.DAO_KhachHang;
import DAO.DAO_NhanVien;
import Database.ConnectDatabase;
import Entity.*;
import com.itextpdf.io.exceptions.IOException;
import com.toedter.calendar.JDateChooser;

import javax.swing.Timer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;
import java.util.regex.Pattern;

public class FrmBanVe extends JFrame implements ActionListener, ItemListener {

private final JButton btnBanVe;
    private final DAO_DoiVe dao_DoiVe;
    private JButton btnTraCuu;
private final JButton btnQuanLyVe;
private final JButton btnThongKeTheoCa;
private final JButton btnQuanLyChuyenTau;
private final JButton btnQuanLyKhachHang;
private final JButton btnQuanLyKhuyenMai;
private final JButton btnQuanLyDoanhThu;
private final JButton btnQuanLyNhanVien;
private final JButton btnQuanLyLLV;


// quản lý vé
private JMenuItem doiVe = new JMenuItem();
private JMenuItem traVe = new JMenuItem();
// quản lý doanh thu
private JMenuItem tk_tl_DoiTraVe = new JMenuItem();
private JMenuItem tk_slkh = new JMenuItem();
private JMenuItem tk_sl_Ve = new JMenuItem();
private JMenuItem tk_DoanhThu_BanVe = new JMenuItem();
private JMenuItem taoBaoCao = new JMenuItem();
// tra cứu
private JMenuItem traCuuVe = new JMenuItem();
private JMenuItem traCuuKM = new JMenuItem();
private JMenuItem traCuuHoaDon = new JMenuItem();
private JMenuItem traCuuTuyen = new JMenuItem();
private JMenuItem traCuuLTT = new JMenuItem();

private final Font fontMenu;
private final Font font_text;
private final Font font_text_2;
private final Font font_text_3;
private final Color colorXanhDam;
//------------------------------------------------------------------------------------------------------------//
private JPanel contain;
private JPanel JPanel_Menu;
private JPanel Jpanel_Main;
private JLabel lab_Title;
private JPanel JPanel_BanVe;
private JPanel JPanel_DSTau;
private JTextField txt_GaDen;
private JButton btnTimChuyen;
private JPanel JPanel_NgayDi;
private JPanel JPanel_NgayVe;
private JRadioButton btnRadio_MotChieu;
private JRadioButton btnRadio_KhuHoi;
private JPanel JPanelKetQuaTimTau;
private JPanel JPanelTau;  // JPanel để hiển thị các nút chuyến tàu
private JPanel JPanel_Toa;
private JPanel JPanel_ChoNgoi;
private JPanel JPanel_ChoNgoi_A;
private JPanel JPanel_ChoNgoi_B;
private JPanel JPanel_ChoNgoi_C;
private JPanel JPanel_ChoNgoi_D;
private JPanel JPanel_ChoNgoi_E;
private JButton btnTiepTheo;
private JButton btnChonTatCa;
private JTextField txtGaDi;
private JLabel labGaDi;
private JPanel JPanel_XacNhanCho;
private JLabel lab_TenToaTau;
private JLabel lab_tb_ChoNgoiNull;
private JPanel JPanel_Tienich;
private JScrollBar scrollBar1;
private JDateChooser dateChooserNgayDi = new JDateChooser();
private JDateChooser dateChooserNgayVe = new JDateChooser();
private DAO_BanVe daoBanVe;
private ButtonGroup btnGroup;
private List<ChoNgoi> danhSachChoDaChon = new ArrayList<>();
private Map<String, JButton> buttonMap = new HashMap<>(); // Để lưu trữ mối quan hệ giữa mã chỗ ngồi và JButton
private List<LichTrinhTau> lichTrinhTaus = null;
private LichTrinhTau lichKhiChonTau = null;
private double tongThanhTien = 0.0; // Biến để lưu tổng thành tiền
private static int ticketCount = 0; // Số vé đã tạo trong ngày
private static LocalDate lastDate = LocalDate.now(); // Ngày cuối cùng đã tạo vé
private Component temp; // Khai báo biến toàn cục
Component chuyenTauPanel = new ChuyenTau().getjPanelMain();
Component khuyenMaiPanel = new FrmKhuyenMai().getKMPanel();
Component khachHangPanel = new Frm_KhachHang(new NhanVien()).getKHPanel();
Component nhanVienPanel = new FrmNhanVien().getJpannelNV();
Component doiVePanel = new Frm_DoiVe().getJpannelDoiVe();
Component soLuongKHPanel = new Frm_ThongKeKhachHang().getTKKHPanel();
Component traCuuKMPanel = new Frm_TraCuuKhuyenMai().getTraCuuKM_Panel();
Component llvPanel = new FrmLichLamViec().getPanel_LLV();
private List<LoaiKhachHang> danhSachLoaiKH = new ArrayList<>();
private KhuyenMai khuyenMai = null;
private Double chietKhau = 0.0;
private int counter = 0;
private int totalCustomerToday = 0;
private static double VAT = 10;
private NhanVien nhanVien;
private JTextField txtSoDienThoai= new JTextField();
private JTextField txtHoTenNguoiMua = new JTextField();
private JTextField txtCCCDNguoiMua = new JTextField();
private JTextField txtDiaChi = new JTextField();
private KhachHang khachHangMuaVe = null;
private Tau tauKhiChon = null;
private ToaTau toaKhiChon = null;
public TextField txtTienThua;
public TextField txtTienKhachDua;
private double total;
public JLabel lableTienThua = new JLabel("Tiền Thừa: ");
private boolean isChiTietHoaDonDisplayed = false;
private boolean isThanhToanDisplayed = false;
private List<TicketDetails> danhSachVe = new ArrayList<>();
private List<ChiTietHoaDon> chiTietHoaDonList = new ArrayList<>();
private HoaDon hoaDonDaThanhToan = null;
private  List<VeTau> ticketsToSave = new ArrayList<>();
private KhachHang khachHang = null;
    private VeTau thongTinVeDoi;

    public FrmBanVe(NhanVien nv) {
    nhanVien = nv;
    setTitle("Bán Vé");
    temp = Jpanel_Main;
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    add(contain);
    font_text = new Font("Arial", Font.BOLD, 30);
    font_text_2 = new Font("Arial", Font.PLAIN, 15);
    font_text_3 = new Font("Arial", Font.PLAIN, 25);
    // MENU
    JPanel_Menu.setLayout(new BoxLayout(JPanel_Menu, BoxLayout.Y_AXIS));
    colorXanhDam = new Color(0, 131, 66);
    JPanel_Menu.setBackground(colorXanhDam); // Màu nền của MENU
    add(JPanel_Menu, BorderLayout.WEST);

    //đổi
        dao_DoiVe= new DAO_DoiVe();
        //

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

    btnBanVe = createButton("Bán vé", null);
    btnTraCuu = createButton("Tra cứu", null);
    ImageIcon downArrowIcon = new ImageIcon(getClass().getResource("/Anh_HeThong/down-chevron.png"));
    // lấy ảnh từ icon
    Image imgArrow = downArrowIcon.getImage();
    // chỉnh kích thước
    Image scaleArrow = imgArrow.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    // tại icon mới từ ảnh đã sửa
    ImageIcon iconArrowNew = new ImageIcon(scaleArrow);
    // Gán icon vào nút lọc
    btnTraCuu = createButton("Tra cứu", iconArrowNew);
    // Điều chỉnh vị trí của icon nếu cần
    btnTraCuu.setHorizontalTextPosition(SwingConstants.LEFT); // Đặt text bên phải của icon
    btnQuanLyVe = createButton("Quản lý vé", iconArrowNew);
    btnThongKeTheoCa = createButton("Doanh thu theo ca", null);
    btnQuanLyChuyenTau = createButton("Quản lý chuyến tàu", null);
    btnQuanLyKhachHang = createButton("Quản lý khách hàng", null);
    btnQuanLyKhuyenMai = createButton("Quản lý CTKM", null);
    btnQuanLyDoanhThu = createButton("Quản lý doanh thu", iconArrowNew);
    btnQuanLyNhanVien = createButton("Quản lý nhân viên", null);
    btnQuanLyLLV = createButton("Quản lý lịch làm việc", null);

    // Format nút với cùng kích thước, phông chữ và căn chỉnh
    Dimension buttonSize = new Dimension(200, 60); // Tăng kích thước chiều cao của nút lên 60px
    fontMenu = new Font("Arial", Font.PLAIN, 16); // Đặt font chung cho tất cả các nút

    // Định dạng cho từng nút
    JButton[] buttons = {btnBanVe, btnTraCuu, btnQuanLyVe, btnThongKeTheoCa, btnQuanLyChuyenTau, btnQuanLyKhachHang, btnQuanLyKhuyenMai, btnQuanLyDoanhThu, btnQuanLyNhanVien, btnQuanLyLLV};
    for (JButton btn : buttons) {
        btn.setPreferredSize(buttonSize); // Đặt kích thước cố định cho nút
        btn.setFont(fontMenu); // Đặt font
        btn.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa text trên nút
        btn.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa nút trong JPanel_Menu
        JPanel_Menu.add(Box.createRigidArea(new Dimension(0, 10))); // Thêm khoảng cách giữa các nút (10px)
        JPanel_Menu.add(btn); // Thêm nút vào JPanel_Menu
    }
    //MENU
    // Calendar
    JPanel_NgayDi.add(dateChooserNgayDi);
    JPanel_NgayVe.add(dateChooserNgayVe);
    // Lấy ngày hiện tại
    Date today = new Date();

    // Thiết lập ngày tối đa cho date chooser
    dateChooserNgayDi.setMinSelectableDate(today); // Chỉ cho phép chọn ngày bằng hoặc trước hôm nay
    dateChooserNgayVe.setMinSelectableDate(today); // Tương tự cho ngày về
    // RadioGroup
    btnGroup = new ButtonGroup();
    btnGroup.add(btnRadio_MotChieu);
    btnGroup.add(btnRadio_KhuHoi);

    // Tạo URL cho biểu tượng của nút btnTiepTheo
    URL imageUrlBuocTiepTheo = getClass().getResource("/Anh_HeThong/Next.png");
    setButtonIcon(btnTiepTheo, imageUrlBuocTiepTheo, 100, 100);
    btnTiepTheo.setVerticalTextPosition(SwingConstants.CENTER);

    URL imageUrlTimChuyen = getClass().getResource("/Anh_HeThong/search_Train.png");
    setButtonIcon(btnTimChuyen, imageUrlTimChuyen, 50, 50); // Gọi phương thức với kích thước tùy chọn
    btnTimChuyen.setVerticalTextPosition(SwingConstants.CENTER);
    btnTimChuyen.setHorizontalTextPosition(SwingConstants.RIGHT);

    ConnectDatabase.getInstance().connect(); // Kết nối database

    daoBanVe = new DAO_BanVe(); // Khởi tạo DAO_BanVe
    btnTimChuyen.addActionListener(this); // Thêm sự kiện cho nút tìm tàu
    btnRadio_KhuHoi.addActionListener(this);
    btnTiepTheo.addActionListener(this);


    // thêm sự kiện cho các nút có chức năng con
    btnBanVe.addActionListener(this);
    btnTraCuu.addActionListener(e -> {
        showPopupMenu_TraCuu(e); // Hiển thị menu con
        addButtonAction(btnTraCuu); // Thực hiện thay đổi màu sắc nút
    });
    btnThongKeTheoCa.addActionListener(this);
    btnQuanLyChuyenTau.addActionListener(this);
    btnQuanLyVe.addActionListener(e -> {
        showPopupMenu_QLVe(e); // Hiển thị menu con
        addButtonAction(btnQuanLyVe); // Thực hiện thay đổi màu sắc nút
    });
    btnQuanLyKhachHang.addActionListener(this);
    btnQuanLyKhuyenMai.addActionListener(this);
    btnQuanLyDoanhThu.addActionListener(e -> {
        showPopupMenu_QLDT(e); // Hiển thị menu con
        addButtonAction(btnQuanLyDoanhThu); // Thực hiện thay đổi màu sắc nút
    });
    btnQuanLyNhanVien.addActionListener(this);
    btnQuanLyLLV.addActionListener(this);

    btnRadio_MotChieu.addItemListener((ItemListener) this);
    btnRadio_KhuHoi.addItemListener((ItemListener) this);


    btnRadio_MotChieu.setSelected(true);
    try {
        phanQuyen(nv.getMaNhanVien());
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    // Lấy thời gian hiện tại
    LocalDateTime now = LocalDateTime.now();
    // Định dạng thời gian thành NgàyThángNăm (yyyy-MM-dd)
    String datePart = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    counter = daoBanVe.getTotalInvoicesByDate();
    System.out.println("Số lượng hóa đơn: " + counter);
    // Định dạng thời gian thành NgàyThángNăm (yyyy-MM-dd)
    totalCustomerToday = daoBanVe.getTotalCustomersToday();
    System.out.println("Số lượng khách hàng: " + totalCustomerToday);
}


public static void main(String[] args) {
    FrmBanVe frm = new FrmBanVe(new NhanVien());
    frm.setVisible(true);

}

// Phương thức để vô hiệu hóa hoặc kích hoạt toàn bộ các thành phần con của JPanel
private void setPanelEnabled(JPanel panel, boolean isEnabled) {
    for (Component component : panel.getComponents()) {
        component.setEnabled(isEnabled);
    }
    panel.setEnabled(isEnabled); // Khóa luôn cả JPanel chính
}

public void setButtonIcon(JButton button, URL imageUrl, int width, int height) {
    if (imageUrl != null) {
        ImageIcon icon = new ImageIcon(imageUrl); // Tạo ImageIcon từ URL
        Image image = icon.getImage(); // Lấy hình ảnh từ ImageIcon
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Thay đổi kích thước hình ảnh
        icon = new ImageIcon(resizedImage); // Tạo lại ImageIcon với hình ảnh đã thay đổi kích thước
        button.setIcon(icon); // Gán biểu tượng vào nút

        // Tùy chọn: Căn chỉnh văn bản và biểu tượng
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setContentAreaFilled(false); // Loại bỏ vùng nội dung mặc định
        button.setBorderPainted(false); // Loại bỏ viền mặc định của nút

        // Thêm hiệu ứng hover
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(250, 196, 58)); // Màu nền khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(null); // Trả lại màu nền ban đầu khi không hover
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Hiệu ứng khi chọn (click)
                button.setBackground(new Color(255, 165, 0)); // Đổi màu nền khi nhấn nút (màu cam)
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Trả lại màu nền ban đầu khi thả nút
                button.setBackground(new Color(250, 196, 58)); // Màu nền hover
            }
        });

        // Hoặc dùng ActionListener nếu cần
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý hành động khi nút được nhấn (click)
                button.setBackground(new Color(255, 165, 0)); // Đổi màu nền khi click
                // Thực hiện các hành động cần thiết sau khi nhấn nút
            }
        });

    } else {
        System.out.println("URL hình ảnh không hợp lệ.");
    }
}

// CREATE A BUTTON
private JButton createButton(String text, ImageIcon icon) {
    JButton button = new JButton(text);
    button.setFont(new Font("Arial", Font.BOLD, 20)); // Đặt font
    button.setPreferredSize(new Dimension(200, 60)); // Kích thước nút
    button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // Chiếm hết chiều ngang
    button.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa nút
    button.setBackground(Color.cyan); // Màu nền
    button.setForeground(Color.white); // Màu chữ
    button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.WHITE, 1), // Viền trắng
            BorderFactory.createEmptyBorder(0, 0, 0, 0) // Padding nếu cần
    ));
    button.setFocusPainted(false);
    button.setOpaque(false);

    if (icon != null) {
        button.setIcon(icon); // Thiết lập biểu tượng nếu có
        button.setHorizontalTextPosition(SwingConstants.LEFT); // Đặt text bên phải của icon
        button.setIconTextGap(10); // Khoảng cách giữa icon và văn bản
    }

    return button;
}


// Phương thức hiển thị menu con khi click vào nút "Tra cứu"
private void showPopupMenu_TraCuu(ActionEvent e) {
    JPopupMenu popupMenu = new JPopupMenu();

    // Tạo các menu items
    traCuuVe = new JMenuItem("Tra cứu vé");
    traCuuVe.setFont(fontMenu);
    traCuuVe.setForeground(Color.white);
    traCuuVe.setBackground(colorXanhDam);
    traCuuVe.addActionListener(this);

    traCuuKM = new JMenuItem("Tra cứu khuyến mãi");
    traCuuKM.setFont(fontMenu);
    traCuuKM.setForeground(Color.white);
    traCuuKM.setBackground(colorXanhDam);
    traCuuKM.addActionListener(this);

    traCuuHoaDon = new JMenuItem("Tra cứu hóa đơn");
    traCuuHoaDon.setFont(fontMenu);
    traCuuHoaDon.setForeground(Color.white);
    traCuuHoaDon.setBackground(colorXanhDam);
    traCuuHoaDon.addActionListener(this);


    traCuuTuyen = new JMenuItem("Tra cứu tuyến");
    traCuuTuyen.setFont(fontMenu);
    traCuuTuyen.setForeground(Color.white);
    traCuuTuyen.setBackground(colorXanhDam);

    traCuuLTT = new JMenuItem("Tra cứu lịch trình tàu");
    traCuuLTT.setFont(fontMenu);
    traCuuLTT.setForeground(Color.white);
    traCuuLTT.setBackground(colorXanhDam);
    traCuuLTT.addActionListener(this);


    // Thêm các menu item vào JPopupMenu
    popupMenu.setBackground(Color.WHITE);
    popupMenu.add(traCuuVe);
    popupMenu.add(traCuuKM);
    popupMenu.add(traCuuHoaDon);
    popupMenu.add(traCuuTuyen);
    popupMenu.add(traCuuLTT);
    popupMenu.setPreferredSize(new Dimension(200, 200));

    // Lấy đối tượng nguồn của sự kiện (nút "Tra cứu")
    JButton sourceButton = (JButton) e.getSource();

    // Hiển thị menu con tại vị trí bên phải của nút
    popupMenu.show(sourceButton, sourceButton.getWidth(), 0); // Hiển thị menu bên phải nút
}

// Phương thức hiển thị menu con khi click vào nút "Quản lý doanh thu";
private void showPopupMenu_QLDT(ActionEvent e) {
    JPopupMenu popupMenu = new JPopupMenu();

    // Tạo các menu items
    tk_DoanhThu_BanVe = new JMenuItem("Thống kê doanh thu bán vé");
    tk_DoanhThu_BanVe.setFont(fontMenu);
    tk_DoanhThu_BanVe.setForeground(Color.white);
    tk_DoanhThu_BanVe.setBackground(colorXanhDam);
    tk_DoanhThu_BanVe.addActionListener(this);

    tk_slkh = new JMenuItem("Thống kê khách hàng");
    tk_slkh.addActionListener(this);
    tk_slkh.setFont(fontMenu);
    tk_slkh.setForeground(Color.white);
    tk_slkh.setBackground(colorXanhDam);
    tk_slkh.addActionListener(this);

    tk_sl_Ve = new JMenuItem("Thống kê số lượng vé");
    tk_sl_Ve.setFont(fontMenu);
    tk_sl_Ve.setForeground(Color.white);
    tk_sl_Ve.setBackground(colorXanhDam);
    tk_sl_Ve.addActionListener(this);

    tk_tl_DoiTraVe = new JMenuItem("Thống kê tỉ lệ đổi trả vé");
    tk_tl_DoiTraVe.setFont(fontMenu);
    tk_tl_DoiTraVe.setForeground(Color.white);
    tk_tl_DoiTraVe.setBackground(colorXanhDam);
    tk_tl_DoiTraVe.addActionListener(this);

    taoBaoCao = new JMenuItem("Tạo báo cáo doanh thu");
    taoBaoCao.setFont(fontMenu);
    taoBaoCao.setForeground(Color.white);
    taoBaoCao.setBackground(colorXanhDam);
    taoBaoCao.addActionListener(this);


    // Thêm các menu item vào JPopupMenu
    popupMenu.setBackground(Color.WHITE);
    popupMenu.add(tk_sl_Ve);
    popupMenu.add(tk_tl_DoiTraVe);
    popupMenu.add(tk_slkh);
    popupMenu.add(tk_DoanhThu_BanVe);
    popupMenu.add(taoBaoCao);
    popupMenu.setPreferredSize(new Dimension(200, 200));
    // Lấy đối tượng nguồn của sự kiện (nút "Tra cứu")
    JButton sourceButton = (JButton) e.getSource();

    // Hiển thị menu con tại vị trí bên phải của nút
    popupMenu.show(sourceButton, sourceButton.getWidth(), 0); // Hiển thị menu bên phải nút
}

// phuong thức hiển thị menu con khi click vào nút "Quản lý v"
private void showPopupMenu_QLVe(ActionEvent e) {
    JPopupMenu popupMenu = new JPopupMenu();

    // Tạo các menu items
    doiVe = new JMenuItem("Đổi vé");
    doiVe.setFont(fontMenu);
    doiVe.setForeground(Color.white);
    doiVe.setBackground(colorXanhDam);
    doiVe.addActionListener(this);

    traVe = new JMenuItem("Trả vé");
    traVe.setFont(fontMenu);
    traVe.setForeground(Color.white);
    traVe.setBackground(colorXanhDam);
    traVe.addActionListener(this);

    // Thêm các menu item vào JPopupMenu
    popupMenu.setBackground(Color.WHITE);
    popupMenu.add(doiVe);
    popupMenu.add(traVe);
    popupMenu.setPreferredSize(new Dimension(200, 80));
    // Lấy đối tượng nguồn của sự kiện (nút "Tra cứu")
    JButton sourceButton = (JButton) e.getSource();

    // Hiển thị menu con tại vị trí bên phải của nút
    popupMenu.show(sourceButton, sourceButton.getWidth(), 0); // Hiển thị menu bên phải nút
}


private void updateConfirmationPanel(LichTrinhTau lichTrinhTau) throws SQLException {
    JPanel_XacNhanCho.removeAll(); // Xóa nội dung cũ

    // Tạo JPanel chứa thông tin xác nhận chỗ
    JPanel contentPanel = new JPanel();
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS)); // Đặt layout theo chiều dọc
    contentPanel.setBackground(Color.WHITE);

    if (danhSachChoDaChon.isEmpty()) {
        JLabel label = new JLabel("Bạn chưa chọn chỗ ngồi nào.");
        contentPanel.add(label);
    } else {
        Tau tau = daoBanVe.getThongTin(lichTrinhTau.getTau().getMaTau());

        for (ChoNgoi choNgoi : danhSachChoDaChon) {
            // Tạo một panel cho từng chỗ ngồi
            JPanel choPanel = new JPanel();
            choPanel.setLayout(new BoxLayout(choPanel, BoxLayout.Y_AXIS)); // Đặt layout theo chiều dọc
            choPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Đường viền mỗi vé
            choPanel.setBackground(new Color(240, 248, 255)); // Màu nền

            DecimalFormat decimalFormat = new DecimalFormat("#");

            // Tạo JLabel hiển thị thông tin chỗ ngồi
            JLabel choLabel = new JLabel(" Chỗ ngồi: " + choNgoi.getTenCho() + " | " + " Giá vé: " + decimalFormat.format(choNgoi.getGia()) + "VND");
            JLabel gaDiLabel = new JLabel(" Ga đi: " + tau.getTuyenTau().getDiaDiemDi() + "  | " + "Ga đến: " + tau.getTuyenTau().getGaDen());
            JLabel ngayDiLabel = new JLabel(" Ngày đi: " + lichTrinhTau.getNgayDi() + " | " + "Giờ đi: " + lichTrinhTau.getGioDi());
            choLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            gaDiLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            ngayDiLabel.setFont(new Font("Arial", Font.PLAIN, 16));

            // Thêm các JLabel vào panel
            choPanel.add(choLabel);
            choPanel.add(gaDiLabel);
            choPanel.add(ngayDiLabel);

            // Tạo nút "Xóa"
            JButton deleteButton = new JButton();
            URL imageUrl = getClass().getResource("/Anh_HeThong/remove.png");
            setButtonIcon(deleteButton, imageUrl, 50, 50);
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    danhSachChoDaChon.remove(choNgoi); // Xóa chỗ ngồi khỏi danh sách đã chọn
                    JButton correspondingButton = getButtonForSeat(choNgoi); // Lấy nút chỗ ngồi
                    if (correspondingButton != null) {
                        correspondingButton.setBackground(choNgoi.getTinhTrang() ? Color.WHITE : Color.PINK);
                    }
                    try {
                        updateConfirmationPanel(lichTrinhTau); // Cập nhật lại panel xác nhận
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            choPanel.add(deleteButton); // Thêm nút "Xóa"

            // Tạo JLabel hiển thị bộ đếm ngược
            JLabel countdownLabel = new JLabel("Thời gian còn lại: 500 giây");
            choPanel.add(countdownLabel);
            // Khởi tạo bộ đếm ngược
            int countdownTime = 500; // Thời gian đếm ngược (500 giây)
            Timer timer = new Timer(1000, new ActionListener() {
                int timeLeft = countdownTime;

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (timeLeft > 0) {
                        timeLeft--;
                        countdownLabel.setText("Thời gian còn lại: " + timeLeft + " giây");
                    } else {
                        ((Timer) e.getSource()).stop(); // Dừng timer
                        deleteButton.doClick(); // Gọi hành động nút xóa
                    }
                }
            });
            timer.start(); // Bắt đầu bộ đếm ngược

            contentPanel.add(choPanel);
            contentPanel.add(Box.createVerticalStrut(10)); // Khoảng cách giữa các vé
        }
    }

    // Đặt contentPanel vào JScrollPane
    JScrollPane scrollPane = new JScrollPane(contentPanel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    // Thay thế nội dung của JPanel_XacNhanCho bằng JScrollPane
    JPanel_XacNhanCho.removeAll();
    JPanel_XacNhanCho.setLayout(new BorderLayout());
    JPanel_XacNhanCho.add(scrollPane, BorderLayout.CENTER);

    JPanel_XacNhanCho.revalidate(); // Cập nhật giao diện
    JPanel_XacNhanCho.repaint(); // Vẽ lại panel
}


// Hàm để lấy nút tương ứng với chỗ ngồi
private JButton getButtonForSeat(ChoNgoi choNgoi) {
    return buttonMap.get(choNgoi.getMaCho()); // Trả về nút tương ứng
}

private JPanel current;
final JButton[] selectedTauButton = {null};

@Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnTimChuyen) {
        JPanelTau.removeAll();
        JPanel_Toa.removeAll();
        JPanel_ChoNgoi_A.removeAll();
        JPanel_ChoNgoi_B.removeAll();
        JPanel_ChoNgoi_C.removeAll();
        JPanel_ChoNgoi_D.removeAll();
        lab_TenToaTau.setText("");
        // Lấy thông tin ga đến và ngày đi
        String gaDen = txt_GaDen.getText();

        // Định dạng ngày từ JDateChooser
        if (dateChooserNgayDi.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn ngày đi.");
            return; // Ngừng thực hiện nếu không có ngày
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ngayDi = sdf.format(dateChooserNgayDi.getDate());

        try {
            lichTrinhTaus = daoBanVe.getTrainsByDateAndDestination(gaDen, ngayDi);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        // Tạo container cho các nút toa
        JPanel tauContainerPanel = new JPanel();
        tauContainerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Sắp xếp các nút theo chiều ngang với khoảng cách 10px

        // Hiển thị kết quả dưới dạng các JButton có hình ảnh
        JPanelTau.removeAll(); // Xóa các nút trước đó nếu có
        JPanelTau.setLayout(new BorderLayout());
        tauContainerPanel.setPreferredSize(new Dimension(JPanelTau.getWidth(), 100)); // Chiều rộng giữ nguyên, chiều cao là 300

        if (lichTrinhTaus.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy chuyến tàu nào.");
        } else {

            for (LichTrinhTau lichTrinhTau : lichTrinhTaus) {
                String tauInfo = "Tên tàu: " + lichTrinhTau.getTau().getTenTau() + ", Giờ chạy: " + lichTrinhTau.getGioDi();
                JButton tauButton = new JButton(tauInfo);

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
                // Thêm hiệu ứng hover cho nút tàu
                Color hoverBackground = new Color(132, 255, 57); // Màu nền khi hover
                tauButton.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        tauButton.setBackground(hoverBackground); // Đổi màu khi hover
                        tauButton.setOpaque(true); // Đảm bảo màu được hiển thị
                    }

                });
                // Xử lý cho sự kiện di chuyển chuột ra ngoài
                tauButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseExited(MouseEvent e) {
                        // Nếu nút này đang được chọn, giữ nguyên màu nền vàng
                        if (tauButton == selectedTauButton[0]) {
                            tauButton.setBackground(hoverBackground); // Giữ màu vàng
                        } else {
                            tauButton.setBackground(null); // Nếu không được chọn, khôi phục lại màu nền ban đầu
                        }
                    }
                });
                // Biến lưu trữ nút toa đã chọn
                final JButton[] selectedToaButton = {null};
                // Thêm ActionListener cho mỗi nút tàu
                tauButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        if (!danhSachChoDaChon.isEmpty()) {
                            JOptionPane.showMessageDialog(tauButton, "Vui lòng xóa hết vé trong giỏ hàng để chọn tàu");
                        } else {

                            // Nếu có nút toa đã chọn, đổi lại màu ban đầu
                            if (selectedTauButton[0] != null) {
                                selectedTauButton[0].setBackground(null);  // Khôi phục màu nền ban đầu
                            }
                            tauButton.setBackground(hoverBackground); // Màu vàng khi chọn
                            selectedTauButton[0] = tauButton;
                            DAO_BanVe daoBanVe = new DAO_BanVe();
                            // Gọi DAO để tìm các toa của tàu
                            List<ToaTau> danhSachToa = null;
                            try {
                                danhSachToa = daoBanVe.getToaByMaTau(lichTrinhTau.getTau().getMaTau());
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            // Tạo container cho các nút toa
                            JPanel toaContainerPanel = new JPanel();
                            toaContainerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Sắp xếp các nút theo chiều ngang với khoảng cách 10px

                            // Xóa các nút cũ
                            JPanel_Toa.removeAll();
                            JPanel_Toa.setLayout(new BorderLayout());  // Đặt BorderLayout để chứa JScrollPane

                            if (danhSachToa.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Không tìm thấy toa tàu.");
                            } else {
                                // Kiểm tra thứ tự của toa để gán hình ảnh tương ứng
                                URL imageDauTau = getClass().getResource("/Anh_HeThong/DauTauHoaMauVang.png");
                                // Thêm hình ảnh cho nút
                                if (imageDauTau != null) {
                                    //Tao Đầu tau
                                    ImageIcon icon = new ImageIcon(imageDauTau);
                                    Image img = icon.getImage();
                                    Image resizedImage = img.getScaledInstance(100, 50, Image.SCALE_SMOOTH); // Tăng kích thước hình ảnh
                                    icon = new ImageIcon(resizedImage);
                                    JButton DautoaButton = new JButton(lichTrinhTau.getTau().getMaTau());
                                    DautoaButton.setIcon(icon);
                                    toaContainerPanel.add(DautoaButton);
                                    //Căng chỉnh btn
                                    DautoaButton.setHorizontalTextPosition(SwingConstants.CENTER);
                                    DautoaButton.setVerticalTextPosition(SwingConstants.BOTTOM);
                                    DautoaButton.setContentAreaFilled(false);
                                    DautoaButton.setBorderPainted(false);
                                    // Căn chỉnh biểu tượng với viền của nút
                                    DautoaButton.setIconTextGap(0);  // Không có khoảng cách giữa biểu tượng và văn bản
                                    DautoaButton.setMargin(new Insets(0, 0, 0, 0));  // Loại bỏ phần đệm của nút
                                    DautoaButton.setContentAreaFilled(false);  // Loại bỏ vùng nội dung mặc định của nút
                                    DautoaButton.setBorderPainted(false);  // Loại bỏ viền mặc định của nút
                                } else {
                                    System.out.println("Ảnh không tồn tại.");
                                }
                                for (ToaTau toaTau : danhSachToa) {
                                    URL image = getClass().getResource("/Anh_HeThong/ThanToaMauVang.png");
                                    JButton toaButton = new JButton("Toa: " + toaTau.getThuTu()); // Hiển thị mã toa
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
                                    Color hoverBackground = new Color(132, 255, 57); // Màu nền khi hover
                                    toaButton.setToolTipText(toaTau.getLoaiToa().getTenLoai()); // Đây là văn bản chú thích
                                    toaButton.addMouseListener(new java.awt.event.MouseAdapter() {
                                        @Override
                                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                                            toaButton.setBackground(hoverBackground); // Đổi màu khi hover
                                            toaButton.setOpaque(true); // Đảm bảo màu được hiển thị
                                        }

                                    });
                                    // Xử lý cho sự kiện di chuyển chuột ra ngoài
                                    toaButton.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mouseExited(MouseEvent e) {
                                            // Nếu nút này đang được chọn, giữ nguyên màu nền vàng
                                            if (toaButton == selectedToaButton[0]) {
                                                toaButton.setBackground(hoverBackground); // Giữ màu vàng
                                            } else {
                                                toaButton.setBackground(null); // Nếu không được chọn, khôi phục lại màu nền ban đầu
                                            }
                                        }
                                    });
                                    // Trong phần xử lý nút toa
                                    toaButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent event) {
                                            lab_TenToaTau.setText("Toa tàu số " + toaTau.getThuTu() + ": " + toaTau.getLoaiToa().getTenLoai());
                                            toaKhiChon = toaTau;
                                            // Nếu có nút toa đã chọn, đổi lại màu ban đầu
                                            if (selectedToaButton[0] != null) {
                                                selectedToaButton[0].setBackground(null);  // Khôi phục màu nền ban đầu
                                            }
                                            toaButton.setBackground(hoverBackground); // Màu vàng khi chọn
                                            selectedToaButton[0] = toaButton;

                                            DAO_BanVe daoBanVe = new DAO_BanVe();
                                            List<ChoNgoi> danhSachChoNgoi = null;
                                            lichKhiChonTau = lichTrinhTau;
                                            try {
                                                tauKhiChon = daoBanVe.getThongTin(lichKhiChonTau.getTau().getMaTau());
                                            } catch (SQLException ex) {
                                                throw new RuntimeException(ex);
                                            }
                                            try {
                                                // Gọi DAO để tìm các chỗ ngồi của toa
                                                danhSachChoNgoi = daoBanVe.getSeatsByMaToa(toaTau.getMaToa());
                                            } catch (SQLException ex) {
                                                throw new RuntimeException(ex);
                                            }
                                            // Hiển thị chỗ ngồi trong JPanel_ChoNgoi
                                            JPanel_ChoNgoi_A.removeAll();
                                            JPanel_ChoNgoi_A.setLayout(new FlowLayout());
                                            JPanel_ChoNgoi_B.removeAll();
                                            JPanel_ChoNgoi_B.setLayout(new FlowLayout());
                                            JPanel_ChoNgoi_C.removeAll();
                                            JPanel_ChoNgoi_C.setLayout(new FlowLayout());
                                            JPanel_ChoNgoi_D.removeAll();
                                            JPanel_ChoNgoi_D.setLayout(new FlowLayout());

                                            if (danhSachChoNgoi.isEmpty()) {
                                                JOptionPane.showMessageDialog(null, "Không tìm thấy chỗ ngồi.");
                                            } else {
                                                for (ChoNgoi choNgoi : danhSachChoNgoi) {
                                                    JButton choButton = new JButton();
                                                    choButton.setText(choNgoi.getTenCho()); // Gán tên chỗ

                                                    // Lưu nút vào buttonMap để dễ dàng truy cập sau này
                                                    buttonMap.put(choNgoi.getMaCho(), choButton); // Lưu nút vào map
                                                    // Kiểm tra xem chỗ ngồi đã được chọn hay chưa
                                                    if (danhSachChoDaChon.stream().anyMatch(c -> c.getMaCho().equals(choNgoi.getMaCho()))) {
                                                        choButton.setBackground(Color.YELLOW); // Nếu đã chọn, đổi màu thành vàng
                                                    } else {
                                                        // Thay đổi màu nền dựa trên tình trạng chỗ ngồi
                                                        if (choNgoi.getTinhTrang()) {
                                                            choButton.setBackground(Color.WHITE); // Còn trống
                                                        } else {
                                                            choButton.setBackground(Color.PINK); // Đã đặt
                                                        }
                                                    }
                                                    choButton.setPreferredSize(new Dimension(60, 60)); // Kích thước nút hình vuông
                                                    choButton.setOpaque(true);
                                                    // Kiểm tra vé cho mã chỗ ngồi
                                                    VeTau veTau = daoBanVe.getVeTaubyLichTrinhTauandMaCho(lichTrinhTau.getMaLichTrinh(), choNgoi.getMaCho());
                                                    if (veTau != null) {
                                                        // Nếu có vé, khóa nút và đổi màu
                                                        choButton.setEnabled(false); // Khóa nút
                                                        choButton.setBackground(Color.pink); // Đổi màu nút thành đỏ
                                                        choButton.setText("Đã có vé"); // Cập nhật văn bản để thể hiện trạng thái đã đặt
                                                    } else {
                                                        // Nếu không có vé, thêm ActionListener cho nút chỗ ngồi
                                                        choButton.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                // Kiểm tra xem chỗ ngồi đã được chọn hay chưa
                                                                if (danhSachChoDaChon.stream().anyMatch(c -> c.getMaCho().equals(choNgoi.getMaCho()))) {
                                                                    // Nếu chỗ ngồi đang được chọn, hủy chọn và đổi màu lại như ban đầu
                                                                    choButton.setBackground(choNgoi.getTinhTrang() ? Color.WHITE : Color.PINK); // Màu nền dựa trên tình trạng
                                                                    danhSachChoDaChon.removeIf(c -> c.getMaCho().equals(choNgoi.getMaCho())); // Xóa khỏi danh sách đã chọn
                                                                } else {
                                                                    //đổi
                                                                    if (thongTinVeDoi != null) { // Kiểm tra điều kiện
                                                                        if (!danhSachChoDaChon.isEmpty()) { // Nếu đã có chỗ ngồi trong danh sách
                                                                            JOptionPane.showMessageDialog(null,
                                                                                    "Bạn đã chọn một chỗ ngồi rồi. Vui lòng xóa vé khỏi giỏ hàng trước khi chọn chỗ mới.",
                                                                                    "Thông báo",
                                                                                    JOptionPane.WARNING_MESSAGE);
                                                                            return; // Dừng thực hiện thêm chỗ ngồi mới
                                                                        }
                                                                    }
                                                                    //
                                                                    choButton.setBackground(Color.YELLOW); // Màu vàng khi được chọn
                                                                    danhSachChoDaChon.add(choNgoi); // Thêm vào danh sách đã chọn
                                                                }
                                                                try {
                                                                    updateConfirmationPanel(lichTrinhTau); // Cập nhật panel xác nhận chỗ
                                                                } catch (SQLException ex) {
                                                                    throw new RuntimeException(ex);
                                                                }
                                                            }
                                                        });
                                                    }
                                                    String tenCho = choNgoi.getTenCho(); // Lấy tên chỗ
                                                    if (tenCho != null && !tenCho.isEmpty()) {
                                                        char cuoi = tenCho.charAt(tenCho.length() - 1); // Lấy ký tự cuối cùng
                                                        switch (cuoi) {
                                                            case 'A':
                                                                JPanel_ChoNgoi_A.add(choButton);
                                                                break;
                                                            case 'B':
                                                                JPanel_ChoNgoi_B.add(choButton);
                                                                break;
                                                            case 'C':
                                                                JPanel_ChoNgoi_C.add(choButton);
                                                                break;
                                                            case 'D':
                                                                JPanel_ChoNgoi_D.add(choButton);
                                                                break;
                                                            default:
                                                                break;
                                                        }
                                                    }
                                                }
                                                // Cập nhật giao diện cho các JPanel
                                                JPanel_ChoNgoi_A.revalidate();
                                                JPanel_ChoNgoi_A.repaint();
                                                JPanel_ChoNgoi_B.revalidate();
                                                JPanel_ChoNgoi_B.repaint();
                                                JPanel_ChoNgoi_C.revalidate();
                                                JPanel_ChoNgoi_C.repaint();
                                                JPanel_ChoNgoi_D.revalidate();
                                                JPanel_ChoNgoi_D.repaint();
                                            }
                                        }
                                    });
                                    toaContainerPanel.add(toaButton);
                                    toaButton.setHorizontalTextPosition(SwingConstants.CENTER);
                                    toaButton.setVerticalTextPosition(SwingConstants.BOTTOM);
                                    toaButton.setContentAreaFilled(false);
                                    toaButton.setBorderPainted(false);
                                    toaButton.setIconTextGap(0);  // Không có khoảng cách giữa biểu tượng và văn bản
                                    toaButton.setMargin(new Insets(0, 0, 0, 0));  // Loại bỏ phần đệm của nút
                                    toaButton.setContentAreaFilled(false);  // Loại bỏ vùng nội dung mặc định của nút
                                    toaButton.setBorderPainted(false);  // Loại bỏ viền mặc định của nút
                                }
                                JScrollPane scrollPane = new JScrollPane(toaContainerPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                                JPanel_Toa.add(scrollPane, BorderLayout.CENTER);
                                JPanel_Toa.revalidate();
                                JPanel_Toa.repaint();
                            }
                        }
                    }
                });

                // Thêm nút vào JPanelTau
                tauContainerPanel.add(tauButton);
                tauButton.setHorizontalTextPosition(SwingConstants.CENTER);
                tauButton.setVerticalTextPosition(SwingConstants.BOTTOM);
                tauButton.setContentAreaFilled(false);
                tauButton.setBorderPainted(false);
            }
            // Thêm container vào JScrollPane để có thanh cuộn nằm ngang
            JScrollPane scrollPane = new JScrollPane(tauContainerPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            JPanelTau.add(scrollPane, BorderLayout.CENTER);
            JPanelTau.revalidate(); // Cập nhật giao diện
            JPanelTau.repaint();
        }
    } else if (e.getSource() == btnTiepTheo) {
        // Kiểm tra xem danh sách chỗ ngồi đã chọn có trống không
        if (danhSachChoDaChon.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn chỗ ngồi nào.");
            return;
        }
        // Tạo cửa sổ mới để hiển thị danh sách chỗ ngồi đã chọn
        JDialog dialog = new JDialog(this, "Danh sách chỗ ngồi đã chọn", true);
        dialog.setSize(1350, 700); // Kích thước của cửa sổ
        dialog.setLocationRelativeTo(this); // Hiển thị ở giữa màn hình

        // Tạo tiêu đề các cột
        String[] columnNames = {"Họ tên", "Thông tin chỗ ngồi", "Giá vé (VND)", "Giảm đối tượng", "Chỗ ngồi", "Thành tiền"};

        // Tạo mô hình bảng
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Tạo danh sách lưu trữ các CustomPanel cho từng hàng trong bảng
        List<CustomPanel> danhSachPanel = new ArrayList<>();

        // Trong vòng lặp thêm hàng vào bảng
        for (ChoNgoi choNgoi : danhSachChoDaChon) {
            CustomPanel panel = new CustomPanel();
            //đổi
            if(thongTinVeDoi!= null){
                panel.setHoTen("Nguyen Van A"); // Thiết lập các thuộc tính cần thiết
                panel.setTrangThai(thongTinVeDoi.getDoiTuong(),choNgoi.getGia());
                panel.setCCCD("123456789");
                panel.setGiaVe(choNgoi.getGia());
            }
            //
            else{
                panel.setHoTen(""); // Thiết lập các thuộc tính cần thiết
                panel.setTrangThai("Người lớn", choNgoi.getGia());
                panel.setCCCD("");
                panel.setGiaVe(choNgoi.getGia());
            }


            danhSachPanel.add(panel); // Lưu lại panel này vào danh sách

            // Cập nhật discount và tính toán thành tiền
            double discount = panel.getDiscount();
            double tienThue = (choNgoi.getGia() * 10) / 100;
            double thanhTien = choNgoi.getGia() - discount + tienThue;
            tongThanhTien += thanhTien; // Cộng dồn vào tổng thành tiền

            Object[] rowData = new Object[]{
                    panel, // Đưa panel vào bảng
                    choNgoi.getMaCho(),
                    choNgoi.getGia(),
                    discount,
                    choNgoi.getTenCho(), // Khuyến mại
                    thanhTien
            };
            model.addRow(rowData);
        }
        // Tạo bảng với mô hình dữ liệu
        JTable table = new JTable(model);

        // Tùy chỉnh tiêu đề bảng
        JTableHeader header = table.getTableHeader();
        Color colorTieuDeBang = new Color(0, 131, 66);
        header.setBackground(colorTieuDeBang); // Đặt màu nền cho tiêu đề
        header.setForeground(Color.WHITE); // Đặt màu chữ cho tiêu đề

        header.setFont(new Font("Arial", Font.BOLD, 20)); // Đặt font cho tiêu đề (cỡ 16)

        // Tùy chỉnh font cho các dòng trong bảng
        table.setFont(new Font("Arial", Font.PLAIN, 18)); // Đặt font cho các dòng (cỡ 14)

        // Thiết lập renderer và editor cho cột đầu tiên
        table.getColumnModel().getColumn(0).setCellRenderer(new CustomCellRenderer());
        table.getColumnModel().getColumn(0).setCellEditor(new CustomCellEditor());

        // Tùy chỉnh chiều cao các hàng
        table.setRowHeight(120); // Chiều cao mỗi hàng

        // Tùy chỉnh chiều rộng các cột
        table.getColumnModel().getColumn(0).setPreferredWidth(200); // Họ tên
        table.getColumnModel().getColumn(1).setPreferredWidth(200); // Thông tin chỗ ngồi
        table.getColumnModel().getColumn(2).setPreferredWidth(100); // Giá vé
        table.getColumnModel().getColumn(3).setPreferredWidth(150); // Giảm đối tượng
        table.getColumnModel().getColumn(4).setPreferredWidth(100); // Khuyến mại
        table.getColumnModel().getColumn(5).setPreferredWidth(100); // Thành tiền

        // Tạo JScrollPane cho bảng
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panelThongTinNguoiDiTau = new JPanel();
        panelThongTinNguoiDiTau.setLayout(new BorderLayout());
        panelThongTinNguoiDiTau.add(scrollPane, BorderLayout.CENTER);

        // Tạo JPanel để hiển thị thông tin bên phải
        JPanel panelBenPhai = new JPanel();
        panelBenPhai.setLayout(new BoxLayout(panelBenPhai, BoxLayout.Y_AXIS)); // Sử dụng BoxLayout theo trục Y
        panelBenPhai.setPreferredSize(new Dimension(300, 0)); // Đặt kích thước cố định cho panel
        panelBenPhai.setBackground(new Color(230, 230, 250)); // Đặt màu nền

        // Thêm một số thành phần vào panel bên phải
        JLabel lblTitle = new JLabel("Thông tin chi tiết vé");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Thêm thành phần vào panel
        panelBenPhai.add(Box.createVerticalStrut(20)); // Thêm khoảng cách
        panelBenPhai.add(lblTitle);
        panelBenPhai.add(Box.createVerticalStrut(10)); // Khoảng cách giữa các thành phần
        panelBenPhai.add(Box.createVerticalStrut(10));


        // Thêm panel bên phải vào dialog
        dialog.add(panelBenPhai, BorderLayout.EAST);

        // Tạo JPanel để chứa thông tin người mua
        JPanel buyerInfoPanel = new JPanel(new BorderLayout()); // Tạo layout 4 hàng, 2 cột với khoảng cách 10px

        JPanel JpanelThanhTien = new JPanel(new GridLayout(1, 2, 5, 5));
        JpanelThanhTien.setBackground(colorTieuDeBang);
        JpanelThanhTien.setPreferredSize(new Dimension(buyerInfoPanel.getWidth(), 50)); // Thay đổi kích thước

        JLabel lblLoaiKH = new JLabel("Loại Khách Hàng");
        lblLoaiKH.setForeground(Color.WHITE);
        lblLoaiKH.setFont(new Font("Arial", Font.BOLD, 18));
        JLabel lbl_ThanhTien = new JLabel("  Tổng thành tiền: " + tongThanhTien + " VND    ");
        lbl_ThanhTien.setForeground(Color.RED);
        lbl_ThanhTien.setFont(new Font("Arial", Font.BOLD, 20)); // Đặt cỡ chữ lớn hơn

        // Đặt nền cho JLabel
        lbl_ThanhTien.setOpaque(true); // Bắt buộc để JLabel hiển thị màu nền
        lbl_ThanhTien.setBackground(Color.YELLOW); // Đặt màu nền là vàng nhạt


        try {
            danhSachLoaiKH = daoBanVe.getAllLoaiKhachHang();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        JComboBox<String> comboBox = new JComboBox<>();
        for (LoaiKhachHang loaiKH : danhSachLoaiKH) {
            comboBox.addItem(loaiKH.getTenLoaiKhachHang()); // Thêm từng loại vào JComboBox
        }
        // Thêm ActionListener cho JComboBox loại khách hàng
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedLoaiKH = (String) comboBox.getSelectedItem(); // Lấy lựa chọn hiện tại

                // Kiểm tra nếu loại khách hàng là "Khách hàng thân thiết"
                if ("Khách hàng thân thiết".equals(selectedLoaiKH)) {
                    // Hiển thị hộp thoại yêu cầu nhập số điện thoại
                    String soDienThoai = JOptionPane.showInputDialog(dialog, "Vui lòng nhập số điện thoại:");

                    // Kiểm tra nếu người dùng đã nhập số điện thoại
                    if (soDienThoai != null && !soDienThoai.trim().isEmpty()) {
                        try {
                            DAO_KhachHang dao_khachHang = new DAO_KhachHang();
                            khachHangMuaVe = dao_khachHang.findCustomerByEncryptedPhone(soDienThoai);
                            if (khachHangMuaVe != null) {
                                try {
                                    txtHoTenNguoiMua.setText(dao_khachHang.decryptAES(khachHangMuaVe.getTenKhachHang()));
                                    txtCCCDNguoiMua.setText(dao_khachHang.decryptAES(khachHangMuaVe.getCCCD()));
                                    txtSoDienThoai.setText(dao_khachHang.decryptAES(khachHangMuaVe.getSoDienThoai()));
                                    txtDiaChi.setText(dao_khachHang.decryptAES(khachHangMuaVe.getDiaChi()));
                                } catch (Exception ex) {
                                    throw new RuntimeException(ex);
                                }
                            }else{
                                JOptionPane.showMessageDialog(dialog,"Không tìm thấy Khách hàng");
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }


                    } else {
                        // Nếu không nhập, có thể thông báo lỗi hoặc làm gì đó tùy ý
                        JOptionPane.showMessageDialog(dialog, "Số điện thoại không được để trống.");
                    }
                }
            }
        });
        JPanel JPanel_MaKhuyenMai = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel_MaKhuyenMai.setBackground(colorTieuDeBang);

        JTextField txtMaKhuyenMai = new JTextField("Nhập mã giảm giá tại đây");
        txtMaKhuyenMai.setPreferredSize(new Dimension(150, 30));

// Sự kiện khi nhấp chuột vào JTextField
        txtMaKhuyenMai.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtMaKhuyenMai.setText(""); // Xóa nội dung hiện tại
            }
        });

        JButton btnApDung = new JButton("Áp Dụng");
        btnApDung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maKM = txtMaKhuyenMai.getText().trim(); // Lấy mã khuyến mãi từ TextField
                try {
                    khuyenMai = daoBanVe.findKhuyenMaiByMa(maKM); // Tìm khuyến mãi theo mã
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                if (khuyenMai != null) {
                    LocalDate now = LocalDate.now(); // Ngày hiện tại
                    if (khuyenMai.getThoiGianKetThuc().isBefore(now) || khuyenMai.getThoiGianKetThuc().isEqual(now)) {
                        // Nếu khuyến mãi đã hết hạn
                        JOptionPane.showMessageDialog(btnApDung,
                                "Khuyến mãi đã hết hạn vào ngày: " + khuyenMai.getThoiGianKetThuc(),
                                "Thông Báo",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        // Nếu khuyến mãi còn hiệu lực
                        chietKhau = khuyenMai.getChietKhau();
                        showKhuyenMaiDialog(khuyenMai); // Hiển thị dialog thông tin khuyến mãi
                    }
                } else {
                    JOptionPane.showMessageDialog(btnApDung,
                            "Không tìm thấy khuyến mãi với mã: " + maKM,
                            "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel_MaKhuyenMai.add(txtMaKhuyenMai);
        JPanel_MaKhuyenMai.add(btnApDung);


        JPanel JPanel_loaiKH = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel_loaiKH.setBackground(colorTieuDeBang);

        JPanel_loaiKH.add(lblLoaiKH);
        JPanel_loaiKH.add(comboBox);

        JpanelThanhTien.add(JPanel_loaiKH);
        JpanelThanhTien.add(JPanel_MaKhuyenMai);
        JpanelThanhTien.add(lbl_ThanhTien);

        buyerInfoPanel.add(JpanelThanhTien, BorderLayout.NORTH);

        JPanel thongTinNguoiMua = new JPanel(new GridLayout(4, 2, 5, 5));
// Tạo các label và text field cho thông tin người mua
        JLabel lblHoTenNguoiMua = new JLabel("  Họ tên người mua:");
        lblHoTenNguoiMua.setFont(new Font("Arial", Font.BOLD, 16)); // Tăng cỡ chữ

        txtHoTenNguoiMua.setPreferredSize(new Dimension(200, 30)); // Tăng kích thước text field
        txtHoTenNguoiMua.setFont(new Font("Arial", Font.PLAIN, 16)); // Tăng cỡ chữ trong text field
        txtHoTenNguoiMua.setText("");

        JLabel lblCCCDNguoiMua = new JLabel("  CCCD người mua:");
        lblCCCDNguoiMua.setFont(new Font("Arial", Font.BOLD, 16)); // Tăng cỡ chữ
        txtCCCDNguoiMua.setText("");
        txtCCCDNguoiMua.setPreferredSize(new Dimension(200, 30)); // Tăng kích thước text field
        txtCCCDNguoiMua.setFont(new Font("Arial", Font.PLAIN, 16)); // Tăng cỡ chữ trong text field

        JLabel lblSoDienThoai = new JLabel("  Số điện thoại:");
        lblSoDienThoai.setFont(new Font("Arial", Font.BOLD, 16)); // Tăng cỡ chữ
        txtSoDienThoai.setText("");
        txtSoDienThoai.setPreferredSize(new Dimension(200, 30)); // Tăng kích thước text field
        txtSoDienThoai.setFont(new Font("Arial", Font.PLAIN, 16)); // Tăng cỡ chữ trong text field

        JLabel lblDiaChi = new JLabel("  Địa chỉ:");
        lblDiaChi.setFont(new Font("Arial", Font.BOLD, 16)); // Tăng cỡ chữ
        txtDiaChi.setText("");
        txtDiaChi.setPreferredSize(new Dimension(200, 30)); // Tăng kích thước text field
        txtDiaChi.setFont(new Font("Arial", Font.PLAIN, 16)); // Tăng cỡ chữ trong text field

        // Thêm các label và text field vào panel
        thongTinNguoiMua.add(lblHoTenNguoiMua);
        thongTinNguoiMua.add(txtHoTenNguoiMua);

        thongTinNguoiMua.add(lblCCCDNguoiMua);
        thongTinNguoiMua.add(txtCCCDNguoiMua);

        thongTinNguoiMua.add(lblSoDienThoai);
        thongTinNguoiMua.add(txtSoDienThoai);

        thongTinNguoiMua.add(lblDiaChi);
        thongTinNguoiMua.add(txtDiaChi);
        buyerInfoPanel.add(thongTinNguoiMua, BorderLayout.CENTER);

        JButton btnThanToan = new JButton("Thanh Toán"); // Cộng dồn vào tổng thành tiền);

        // Đặt kích thước cho nút
        btnThanToan.setPreferredSize(new Dimension(150, 50)); // Kích thước nút: rộng 150, cao 50
        btnThanToan.setMaximumSize(new Dimension(150, 50));

        // Đặt màu nền và màu chữ cho nút
        btnThanToan.setBackground(new Color(0, 131, 66)); // Màu nền nút (cùng màu với tiêu đề bảng)
        btnThanToan.setForeground(Color.WHITE); // Màu chữ nút

        // Tùy chỉnh font cho nút
        btnThanToan.setFont(new Font("Arial", Font.BOLD, 18)); // Đặt font cho nú

        JPanel Jpanel_NutThanhToan = new JPanel(new FlowLayout());


        JButton btnChiTietHoaDon = new JButton("Xem Chi Tiết HD");
        btnChiTietHoaDon.setPreferredSize(new Dimension(200, 50));
        btnChiTietHoaDon.setFont(new Font("Arial", Font.BOLD, 18));
        btnChiTietHoaDon.setBackground(new Color(0, 131, 66));
        btnChiTietHoaDon.setForeground(Color.WHITE);

        JButton btnInHoaDon = new JButton("In Hóa Đơn");
        btnInHoaDon.setPreferredSize(new Dimension(200, 50));
        btnInHoaDon.setFont(new Font("Arial", Font.BOLD, 18));
        btnInHoaDon.setBackground(new Color(0, 131, 66));
        btnInHoaDon.setForeground(Color.WHITE);

        JButton btnInVe = new JButton("In vé");
        btnInVe.setPreferredSize(new Dimension(200, 50));
        btnInVe.setFont(new Font("Arial", Font.BOLD, 18));
        btnInVe.setBackground(new Color(0, 131, 66));
        btnInVe.setForeground(Color.WHITE);

        Jpanel_NutThanhToan.add(btnInVe);
        Jpanel_NutThanhToan.add(btnInHoaDon);
        Jpanel_NutThanhToan.add(btnChiTietHoaDon);
        Jpanel_NutThanhToan.add(btnThanToan, BorderLayout.CENTER);

        btnChiTietHoaDon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Tạo JPanel chứa thông tin chi tiết vé
                JPanel panelChiTietVe = new JPanel();
                panelChiTietVe.setLayout(new BoxLayout(panelChiTietVe, BoxLayout.Y_AXIS)); // Sắp xếp theo chiều dọc
                panelChiTietVe.setBackground(Color.WHITE);

                // Thêm thông tin của từng vé vào panel
                for (int row = 0; row < table.getRowCount(); row++) {
                    //đổi 13 thành
                    JPanel vePanel = new JPanel(new GridLayout(14, 1, 5, 5)); // Sử dụng GridLayout cho thông tin mỗi vé
                    vePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Đường viền mỗi vé
                    vePanel.setBackground(new Color(240, 248, 255)); // Màu nền

                    // Đặt kích thước cố định cho vePanel
                    int fixedWidth = 400;
                    //đổi 350 = 370
                    int fixedHeight = 370;
                    vePanel.setPreferredSize(new Dimension(fixedWidth, fixedHeight)); // Kích thước ưa thích
                    vePanel.setMinimumSize(new Dimension(fixedWidth, fixedHeight)); // Kích thước tối thiểu
                    vePanel.setMaximumSize(new Dimension(fixedWidth, fixedHeight)); // Kích thước tối đa

                    // Lấy thông tin vé từ bảng
                    String hoTen = ((CustomPanel) table.getValueAt(row, 0)).getHoTen();
                    String doiTuong = ((CustomPanel) table.getValueAt(row, 0)).getTrangThai();
                    String giayTo = ((CustomPanel) table.getValueAt(row, 0)).getCCCD();
                    String thongTinChoNgoi = (String) table.getValueAt(row, 1);

                    Object value = table.getValueAt(row, 2);
                    double giaVe = (value instanceof Number) ? ((Number) value).doubleValue() : 0.0;
                    double thanhTien = (double) table.getValueAt(row, 5);
                    //đổi
                    if(thongTinVeDoi!=null){
                        double giaVeDoi = thongTinVeDoi.getGiaVe();
                        double phiDoi = 20000;
                        thanhTien = thanhTien - giaVeDoi+ phiDoi;
                    }
                    //

                    try {
                        // Lấy thông tin chỗ ngồi từ DAO
                        ChoNgoi cn = daoBanVe.getChoNgoiByMaCho(thongTinChoNgoi);

                        // Kiểm tra nếu cn bị null
                        if (cn == null) {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin chỗ ngồi với mã: " + thongTinChoNgoi);
                            continue; // Bỏ qua vé này
                        }

                        // Thêm thông tin vé vào danh sách
                        TicketDetails ve = new TicketDetails(
                                hoTen,
                                doiTuong,
                                giayTo,
                                cn.getToaTau().getTau().getTuyenTau().getGaDi(),
                                cn.getToaTau().getTau().getTuyenTau().getGaDen(),
                                lichKhiChonTau.getTau().getTenTau(),
                                lichKhiChonTau.getNgayDi(),
                                lichKhiChonTau.getGioDi(),
                                cn.getToaTau().getTenToa(),
                                cn.getTenCho(),
                                giaVe,
                                thanhTien
                        );
                        danhSachVe.add(ve);

                        // Thêm các thông tin vé vào JPanel
                        vePanel.add(new JLabel("  THÔNG TIN HÀNH TRÌNH "));
                        add(new JLabel("    Ga đi-Ga đến: " + ve.getGaDi() + " - " + ve.getGaDen()));
                        vePanel.add(new JLabel("    Tàu/Train: " + ve.getTenTau()));
                        vePanel.add(new JLabel("    Ngày đi/Date: " + ve.getNgayDi()));
                        vePanel.add(new JLabel("    Giờ đi/Time: " + ve.getGioDi()));
                        vePanel.add(new JLabel("    Toa/Coach: " + ve.getToaTau()));
                        vePanel.add(new JLabel("    Chỗ/Seat: " + ve.getChoNgoi()));
                        vePanel.add(new JLabel("  THÔNG TIN HÀNH KHÁCH "));
                        vePanel.add(new JLabel("    Họ tên: " + ve.getHoTen()));
                        vePanel.add(new JLabel("    Đối tượng: " + ve.getDoiTuong()));
                        vePanel.add(new JLabel("    Giấy tờ: " + ve.getGiayTo()));
                        vePanel.add(new JLabel("    Giá vé: " + ve.getGiaVe() + " VND      |     VAT: 10%"));
                        //đổi
                        if(thongTinVeDoi!=null){

                            vePanel.add(new JLabel("  Phí đổi: 20000 VND"));
                            vePanel.add(new JLabel("  -Vé đổi: " + thongTinVeDoi.getGiaVe() + " VND"));
                        }
                        //
                        vePanel.add(new JLabel("    Thành tiền: " + ve.getThanhTien() + " VND"));

                        // Thêm vePanel vào panelChiTietVe
                        panelChiTietVe.add(vePanel);
                        panelChiTietVe.add(Box.createVerticalStrut(10)); // Khoảng cách giữa các vé

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Lỗi khi lấy thông tin chỗ ngồi: " + ex.getMessage());
                        ex.printStackTrace();
                    } catch (ClassCastException ex) {
                        JOptionPane.showMessageDialog(null, "Lỗi dữ liệu: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                }
                // Đặt panelChiTietVe vào JScrollPane
                JScrollPane scrollPane = new JScrollPane(panelChiTietVe);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                // Thay thế nội dung của panelBenPhai bằng JScrollPane
                panelBenPhai.removeAll();
                panelBenPhai.add(scrollPane);
                panelBenPhai.revalidate();
                panelBenPhai.repaint();

                isChiTietHoaDonDisplayed = true;
            }
        });

        // Gắn sự kiện cho nút btnInVe
        btnInVe.addActionListener(event -> {
            if (isChiTietHoaDonDisplayed&&isThanhToanDisplayed) {
                // Kiểm tra nếu danh sách vé không rỗng
                if (!danhSachVe.isEmpty()) {
                    // Tạo tên file PDF
                    String fileName = "danh_sach_ve.pdf";
                    // In danh sách vé ra file PDF
                    TicketPDFGenerator.generateTicketPdf(fileName, danhSachVe,chiTietHoaDonList);
                    JOptionPane.showMessageDialog(null, "In thanh công, danh sách vé được chứa tại file "+fileName);
                    // Mở file PDF sau khi in thành công
                    try {
                        // Tạo đối tượng File từ tên file
                        File pdfFile = new File(fileName);
                        if (pdfFile.exists()) {
                            // Sử dụng Desktop để mở file PDF
                            Desktop.getDesktop().open(pdfFile);
                        } else {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy file PDF.");
                        }
                    } catch (IOException | java.io.IOException e1) {
                        JOptionPane.showMessageDialog(null, "Không thể mở file PDF: " + e1.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Danh sách vé rỗng, không thể gửi email.");
                }
            } else {
                // Hiển thị thông báo nếu chi tiết hóa đơn chưa được hiển thị
                JOptionPane.showMessageDialog(null, "Vui lòng xem chi tiết hóa đơn và thanh toán trước khi in vé.");
            }
        });
        btnInHoaDon.addActionListener(event -> {
            // Kiểm tra nếu danh sách chi tiết hóa đơn không rỗng và đã thanh toán
            if (isChiTietHoaDonDisplayed && isThanhToanDisplayed) {
                if (chiTietHoaDonList.isEmpty()) {
                    // Kiểm tra nếu danh sách chi tiết hóa đơn rỗng
                    JOptionPane.showMessageDialog(null, "Danh sách chi tiết hóa đơn rỗng, không thể in hóa đơn.");
                    return;
                }

                // Tạo mã hóa đơn và tên file PDF
                String fileName = "hoa_don_" + hoaDonDaThanhToan.getMaHD() + ".pdf";

                try {
                    // Gọi phương thức tạo hóa đơn PDF
                    InvoicePDFGenerator.generateInvoicePdf(hoaDonDaThanhToan, khachHang, chiTietHoaDonList, danhSachVe);

                    // Thông báo thành công
                    JOptionPane.showMessageDialog(null, "In hóa đơn thành công, hóa đơn được chứa tại file " + fileName);

                    // Mở file PDF sau khi in thành công
                    openPdfFile(fileName);
                } catch (Exception e2) {
                    // Hiển thị thông báo lỗi nếu có vấn đề xảy ra
                    JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi in hóa đơn: " + e2.getMessage());
                }
            } else {
                // Thông báo nếu chi tiết hóa đơn chưa được hiển thị hoặc chưa thanh toán
                JOptionPane.showMessageDialog(null, "Vui lòng xem chi tiết hóa đơn và thanh toán trước khi in hóa đơn.");
            }
        });

        JPanel panelTienThua = new JPanel();
        panelTienThua.setLayout(new GridLayout(4, 1));
        panelTienThua.setPreferredSize(new Dimension(340, 200));
        panelTienThua.setBackground(new Color(240, 248, 255));
        JLabel lableTienKhachDua = new JLabel("Tiền khách đưa");
        lableTienKhachDua.setFont(new Font("Arial", Font.BOLD, 16));
        panelTienThua.add(lableTienKhachDua);
        txtTienKhachDua = new TextField();
        txtTienKhachDua.setPreferredSize(new Dimension(200, 30)); // Tăng kích thước text field
        txtTienKhachDua.setFont(new Font("Arial", Font.PLAIN, 16)); // Tăng cỡ chữ trong text field
        panelTienThua.add(txtTienKhachDua);

        lableTienThua.setFont(new Font("Arial", Font.BOLD, 16));
        lableTienThua.setText("Tiền thừa: ");
        lableTienThua.setForeground(Color.RED);
        // Đặt nền cho JLabel
        lableTienThua.setOpaque(true); // Bắt buộc để JLabel hiển thị màu nền
        lableTienThua.setBackground(Color.YELLOW); // Đặt màu nền là vàng nhạt
        panelTienThua.add(lableTienThua);

        JButton btnTinhTienThua = new JButton("Tính tiền thừa");
        btnTinhTienThua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateChange(total, txtTienKhachDua);
            }
        });
        panelTienThua.add(btnTinhTienThua);

        buyerInfoPanel.add(panelTienThua,BorderLayout.EAST);

        buyerInfoPanel.add(Jpanel_NutThanhToan, BorderLayout.SOUTH); // Thêm nút In vào phía dưới cùng

        panelThongTinNguoiDiTau.add(buyerInfoPanel, BorderLayout.SOUTH);
        dialog.add(panelThongTinNguoiDiTau, BorderLayout.CENTER);// Thêm ở phía trên
        buyerInfoPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                updateTotalAmount(lbl_ThanhTien, table, chietKhau); // Cập nhật tổng thành tiền khi chuột vào panel
                // Lấy thông tin của dòng đầu tiên trong bảng
                if (table.getRowCount() > 0) {
                    CustomPanel customPanel = (CustomPanel) table.getValueAt(0, 0); // Lấy đối tượng từ dòng đầu tiên
                    String hoTen = customPanel.getHoTen(); // Lấy họ tên
                    String cccd = customPanel.getCCCD(); // Lấy CCCD

                   if(khachHangMuaVe==null){
                       // Cập nhật thông tin vào các trường text
                       txtHoTenNguoiMua.setText(hoTen);
                       txtCCCDNguoiMua.setText(cccd);
                   }
                }
            }
        });

        btnThanToan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy thông tin từ các trường nhập
                String hoTenNguoiMua = txtHoTenNguoiMua.getText();
                String cccdNguoiMua = txtCCCDNguoiMua.getText();
                String soDienThoai = txtSoDienThoai.getText();
                String diaChi = txtDiaChi.getText();
                for (int row = 0; row < table.getRowCount(); row++) {
                    CustomPanel customPanel = (CustomPanel) table.getValueAt(row, 0);
                    String hoTen = customPanel.getHoTen();
                    String doiTuong = customPanel.getTrangThai();
                    String cccd = customPanel.getCCCD();
                    // Kiểm tra nếu thông tin còn thiếu
                    if (doiTuong.equals("Trẻ nhỏ")) {
                        if (hoTen.isEmpty()) {
                            JOptionPane.showMessageDialog(dialog,
                                    "Vui lòng điền đầy đủ thông tin cho trẻ.");
                            return; // Dừng nếu phát hiện thông tin không hợp lệ //đi
                        }
                    } else {
                        if (hoTen.isEmpty() || cccd.isEmpty()) {
                            JOptionPane.showMessageDialog(dialog,
                                    "Vui lòng điền đầy đủ thông tin người đi.");
                            return;
                        }
                    }
                }
                // Kiểm tra xem thông tin đã được điền đầy đủ chưa
                if (hoTenNguoiMua.isEmpty() || cccdNguoiMua.isEmpty() || soDienThoai.isEmpty() || diaChi.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng điền đầy đủ thông tin người mua.");
                    return;
                }

                if(khachHangMuaVe==null){
                    // Tạo mã khách hàng và đối tượng KhachHang
                    String maKH = generateCustomerCode(); // Sinh mã khách hàng mới
                            khachHang = new KhachHang(
                            maKH,
                            new LoaiKhachHang("KH001"), // Mã loại khách hàng mặc định
                            soDienThoai,
                            hoTenNguoiMua,
                            cccdNguoiMua,
                            diaChi,
                            0.0, // Điểm tích lũy ban đầu
                            LocalDate.of(1990, 1, 1), // Ngày sinh mẫu
                            LocalDate.now(), // Ngày tham gia
                            "Silver" // Hạng thành viên mặc định
                    );
                    DAO_BanVe daoBanVe = new DAO_BanVe(); // Tạo đối tượng DAO

                    // Lưu khách hàng vào cơ sở dữ liệu
                    try {
                        daoBanVe.saveCustomer(khachHang); // Gọi phương thức lưu khách hàng trước khi lưu hóa đơn
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                }else{
                    khachHang = khachHangMuaVe;
                }
                try {
                    // Tạo danh sách vé cần lưu


                    double tongTien = 0;

                    // Lặp qua tất cả các hàng trong bảng để lấy thông tin vé
                    for (int row = 0; row < table.getRowCount(); row++) {
                        CustomPanel customPanel = (CustomPanel) table.getValueAt(row, 0);
                        String hoTen = customPanel.getHoTen();
                        String doiTuong = customPanel.getTrangThai();
                        String cccd = customPanel.getCCCD();
                        String thongTinChoNgoi = (String) table.getValueAt(row, 1);
                        float giaVe = (float) table.getValueAt(row, 2);
                        float tienThue = (float) ((giaVe * VAT) / 100);
                        double thanhTien = (double) table.getValueAt(row, 5);

                        // Tạo mã vé và đối tượng VeTau
                        String maVe = generateTicketCode(lichKhiChonTau.getTau().getMaTau());
                        ChoNgoi cn = new ChoNgoi(thongTinChoNgoi);
                        VeTau ticket = new VeTau(maVe, lichKhiChonTau, cn, hoTen, cccd, lichKhiChonTau.getNgayDi(), doiTuong, giaVe, "Đã thanh toán");

                        ticketsToSave.add(ticket);
                        tongTien += thanhTien;

                        // Tạo chi tiết hóa đơn với mã vé tương ứng
                        ChiTietHoaDon chiTiet = new ChiTietHoaDon(maVe, "", 1, VAT, thanhTien, tienThue); // Mã hóa đơn sẽ thêm sau
                        chiTietHoaDonList.add(chiTiet);
                    }
                    // Lưu vé vào cơ sở dữ liệu trước
                    daoBanVe.saveTickets(ticketsToSave); // Phải lưu trước để mã vé tồn tại

                    // Tạo mã hóa đơn
                    String maHD = generateInvoiceCode();
                    LoaiHoaDon loaiHoaDon = new LoaiHoaDon("LHD01");
                    // Lưu hóa đơn
                    double tienChietKhau = tongTien * (chietKhau / 100);
                    tongTien -= tienChietKhau;
                    HoaDon hoaDon = new HoaDon(maHD, khachHang, khuyenMai, nhanVien, loaiHoaDon, LocalDateTime.now(), tienChietKhau, tongTien);
                    daoBanVe.saveInvoice(hoaDon); // Lưu hóa đơn
                    hoaDonDaThanhToan = hoaDon;

                    // Lưu chi tiết hóa đơn
                    for (ChiTietHoaDon ct : chiTietHoaDonList) {
                        ct.setMaHD(maHD); // Gán mã hóa đơn cho chi tiết
                        daoBanVe.saveInvoiceDetail(ct); // Lưu từng chi tiết hóa đơn
                    }

                    JOptionPane.showMessageDialog(dialog, "Lưu vé và hóa đơn thành công!");
                    isThanhToanDisplayed = true;
                    //đổi
                    if(thongTinVeDoi!= null){
                        JButton choNgoiCanDoi = getButtonForSeat(thongTinVeDoi.getChoNgoi());
                        choNgoiCanDoi.setBackground(Color.white);
                        choNgoiCanDoi.setEnabled(true);
                        choNgoiCanDoi.setText(choNgoiCanDoi.getText());
                        String maVeDoi = thongTinVeDoi.getMaVe();
                        dao_DoiVe.capNhatTrangThaiVe(maVeDoi,"Đã đổi");
                    }
                    //

                    // Cập nhật giao diện sau khi lưu
                    danhSachChoDaChon.clear();
                    JPanel_XacNhanCho.removeAll();
                    JPanel_XacNhanCho.revalidate();
                    JPanel_XacNhanCho.repaint();
                    // Khóa nút thanh toán sau khi thanh toán thành công
                    btnThanToan.setEnabled(false);
                    khachHangMuaVe = null;
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(dialog, "Lỗi khi lưu vé và hóa đơn: " + ex.getMessage());
                }


            }
        });

        dialog.setVisible(true); // Hiển thị cửa sổ
    } else if (e.getSource() == btnQuanLyKhachHang) {
        addButtonAction(btnQuanLyKhachHang);
        Jpanel_Main.removeAll();
        current = (JPanel) khachHangPanel;
        JPanel_XacNhanCho.setVisible(false);
        lab_Title.setVisible(false);
        JPanel_BanVe.setVisible(false);
        Jpanel_Main.add(khachHangPanel);
        Jpanel_Main.setVisible(true);

        // Cập nhật lại giao diện người dùng
        Jpanel_Main.revalidate(); // Cập nhật layout
        Jpanel_Main.repaint();
    } else if (e.getSource() == btnQuanLyChuyenTau) {
        addButtonAction(btnQuanLyChuyenTau);
        Jpanel_Main.removeAll();
        current = (JPanel) chuyenTauPanel;
        JPanel_XacNhanCho.setVisible(false);
        lab_Title.setVisible(false);
        JPanel_BanVe.setVisible(false);
        Jpanel_Main.add(chuyenTauPanel);
        Jpanel_Main.setVisible(true);

        // Cập nhật lại giao diện người dùng
        Jpanel_Main.revalidate(); // Cập nhật layout
        Jpanel_Main.repaint();    //
    } else if (e.getSource() == btnBanVe) {
        addButtonAction(btnBanVe);
        Jpanel_Main.removeAll();
        JPanel_XacNhanCho.setVisible(true);
        lab_Title.setVisible(true);
        JPanel_BanVe.setVisible(true);

        // thêm ngược lại
        Jpanel_Main.add(JPanel_XacNhanCho, BorderLayout.EAST);
        Jpanel_Main.add(lab_Title, BorderLayout.NORTH);
        Jpanel_Main.add(JPanel_BanVe, BorderLayout.CENTER);
        Jpanel_Main.revalidate(); // Cập nhật layout
        Jpanel_Main.repaint();
    } else if (e.getSource() == btnTraCuu) {

        addButtonAction(btnTraCuu);
        Jpanel_Main.removeAll();
//            current = (JPanel) khuyenMaiPanel;
        JPanel_XacNhanCho.setVisible(false);
        lab_Title.setVisible(false);
        JPanel_BanVe.setVisible(false);
        // Thêm KhuyenMai vào jPanel_Main
//            Jpanel_Main.add(khuyenMaiPanel);
        Jpanel_Main.setVisible(true);
        // Cập nhật lại giao diện người dùng
        Jpanel_Main.revalidate(); // Cập nhật layout
        Jpanel_Main.repaint();    //
    } else if (e.getSource() == btnQuanLyKhuyenMai) {
        addButtonAction(btnQuanLyKhuyenMai);
        Jpanel_Main.removeAll();
        current = (JPanel) khuyenMaiPanel;
        JPanel_XacNhanCho.setVisible(false);
        lab_Title.setVisible(false);
        JPanel_BanVe.setVisible(false);
        // Thêm KhuyenMai vào jPanel_Main
        Jpanel_Main.add(khuyenMaiPanel);
        Jpanel_Main.setVisible(true);
        // Cập nhật lại giao diện người dùng
        Jpanel_Main.revalidate(); // Cập nhật layout
        Jpanel_Main.repaint();    //
    } else if (e.getSource() == btnQuanLyDoanhThu) {
        addButtonAction(btnQuanLyDoanhThu);
        Jpanel_Main.removeAll();
//            current = (JPanel) khuyenMaiPanel;
        JPanel_XacNhanCho.setVisible(false);
        lab_Title.setVisible(false);
        JPanel_BanVe.setVisible(false);
        // Thêm KhuyenMai vào jPanel_Main
//            Jpanel_Main.add(khuyenMaiPanel);
        Jpanel_Main.setVisible(true);
        // Cập nhật lại giao diện người dùng
        Jpanel_Main.revalidate(); // Cập nhật layout
        Jpanel_Main.repaint();    //
    } else if (e.getSource() == btnQuanLyNhanVien) {
        addButtonAction(btnQuanLyNhanVien);
        Jpanel_Main.removeAll();
        current = (JPanel) nhanVienPanel;
        JPanel_XacNhanCho.setVisible(false);
        lab_Title.setVisible(false);
        JPanel_BanVe.setVisible(false);
        Jpanel_Main.add(nhanVienPanel);
        Jpanel_Main.setVisible(true);

        // Cập nhật lại giao diện người dùng
        Jpanel_Main.revalidate(); // Cập nhật layout
        Jpanel_Main.repaint();    //
    } else if (e.getSource() == btnQuanLyLLV) {
        addButtonAction(btnQuanLyLLV);
        Jpanel_Main.removeAll();
        current = (JPanel) llvPanel;
        JPanel_XacNhanCho.setVisible(false);
        lab_Title.setVisible(false);
        JPanel_BanVe.setVisible(false);
        Jpanel_Main.add(llvPanel);
        Jpanel_Main.setVisible(true);

        // Cập nhật lại giao diện người dùng
        Jpanel_Main.revalidate(); // Cập nhật layout
        Jpanel_Main.repaint();    //
    } else if (e.getSource() == btnThongKeTheoCa) {
        addButtonAction(btnThongKeTheoCa);
        Jpanel_Main.removeAll();
//            current = (JPanel) nhanVienPanel;
        JPanel_XacNhanCho.setVisible(false);
        lab_Title.setVisible(false);
        JPanel_BanVe.setVisible(false);
//            Jpanel_Main.add(nhanVienPanel);
        Jpanel_Main.setVisible(true);

        // Cập nhật lại giao diện người dùng
        Jpanel_Main.revalidate(); // Cập nhật layout
        Jpanel_Main.repaint();    //
    }
    // CÁC CHỨC NĂNG THỐNG KÊ NHỎ BÊN TRONG
    // 1. Thống kê số lượng khách hàng
    else if (e.getSource() == tk_slkh) {
        Jpanel_Main.removeAll();
        current = (JPanel) soLuongKHPanel;
        JPanel_XacNhanCho.setVisible(false);
        lab_Title.setVisible(false);
        JPanel_BanVe.setVisible(false);
        Jpanel_Main.add(soLuongKHPanel);
        Jpanel_Main.setVisible(true);

        // Cập nhật lại giao diện người dùng
        Jpanel_Main.revalidate(); // Cập nhật layout
        Jpanel_Main.repaint();    //
    }
    // 2. Thống kê doanh thu bán vé
    else if (e.getSource() == tk_DoanhThu_BanVe) {
        Jpanel_Main.removeAll();
//            current = (JPanel) ;
        JPanel_XacNhanCho.setVisible(false);
        lab_Title.setVisible(false);
        JPanel_BanVe.setVisible(false);
//            Jpanel_Main.add();
        Jpanel_Main.setVisible(true);

        // Cập nhật lại giao diện người dùng
        Jpanel_Main.revalidate(); // Cập nhật layout
        Jpanel_Main.repaint();    //
    }
    // 3. Thống kê số lượng vé theo thời gian
    else if (e.getSource() == tk_sl_Ve) {
        Jpanel_Main.removeAll();
//            current = (JPanel) ;
        JPanel_XacNhanCho.setVisible(false);
        lab_Title.setVisible(false);
        JPanel_BanVe.setVisible(false);
//            Jpanel_Main.add();
        Jpanel_Main.setVisible(true);

        // Cập nhật lại giao diện người dùng
        Jpanel_Main.revalidate(); // Cập nhật layout
        Jpanel_Main.repaint();    //
    }
    // 4. Thống kê tỉ lệ đổi trả vé
    else if (e.getSource() == tk_tl_DoiTraVe) {
        Jpanel_Main.removeAll();
//            current = (JPanel) ;
        JPanel_XacNhanCho.setVisible(false);
        lab_Title.setVisible(false);
        JPanel_BanVe.setVisible(false);
//            Jpanel_Main.add();
        Jpanel_Main.setVisible(true);

        // Cập nhật lại giao diện người dùng
        Jpanel_Main.revalidate(); // Cập nhật layout
        Jpanel_Main.repaint();    //
    }
    // 5. Tạo báo cáo danh thu
    else if (e.getSource() == taoBaoCao) {
        Jpanel_Main.removeAll();
//            current = (JPanel) ;
        JPanel_XacNhanCho.setVisible(false);
        lab_Title.setVisible(false);
        JPanel_BanVe.setVisible(false);
//            Jpanel_Main.add();
        Jpanel_Main.setVisible(true);

        // Cập nhật lại giao diện người dùng
        Jpanel_Main.revalidate(); // Cập nhật layout
        Jpanel_Main.repaint();    //
    }


    // CÁC CHỨC NĂNG TRA CỨU NHỎ BÊN TRONG
    // 1. Tra cứu khuyến mãi
    else if (e.getSource() == traCuuKM) {
        Jpanel_Main.removeAll();
        current = (JPanel) traCuuKMPanel;
        JPanel_XacNhanCho.setVisible(false);
        lab_Title.setVisible(false);
        JPanel_BanVe.setVisible(false);
        Jpanel_Main.add(traCuuKMPanel);
        Jpanel_Main.setVisible(true);

        // Cập nhật lại giao diện người dùng
        Jpanel_Main.revalidate(); // Cập nhật layout
        Jpanel_Main.repaint();    //
    }
    // 2. Tra cứu vé
    else if (e.getSource() == traCuuVe) {
        Jpanel_Main.removeAll();
//            current = (JPanel) ;
        JPanel_XacNhanCho.setVisible(false);
        lab_Title.setVisible(false);
        JPanel_BanVe.setVisible(false);
//            Jpanel_Main.add();
        Jpanel_Main.setVisible(true);

        // Cập nhật lại giao diện người dùng
        Jpanel_Main.revalidate(); // Cập nhật layout
        Jpanel_Main.repaint();    //
    }
    // 3. Tra cứu hóa đơn
    else if (e.getSource() == traCuuHoaDon) {
        Jpanel_Main.removeAll();
//            current = (JPanel) ;
        JPanel_XacNhanCho.setVisible(false);
        lab_Title.setVisible(false);
        JPanel_BanVe.setVisible(false);
//            Jpanel_Main.add();
        Jpanel_Main.setVisible(true);

        // Cập nhật lại giao diện người dùng
        Jpanel_Main.revalidate(); // Cập nhật layout
        Jpanel_Main.repaint();    //
    }
    // 4. Tra cứu theo tuyến
    else if (e.getSource() == traCuuTuyen) {
        Jpanel_Main.removeAll();
//            current = (JPanel) ;
        JPanel_XacNhanCho.setVisible(false);
        lab_Title.setVisible(false);
        JPanel_BanVe.setVisible(false);
//            Jpanel_Main.add();
        Jpanel_Main.setVisible(true);

        // Cập nhật lại giao diện người dùng
        Jpanel_Main.revalidate(); // Cập nhật layout
        Jpanel_Main.repaint();    //
    }
    // 5. Tra cứu theo lịch trình tàu
    else if (e.getSource() == traCuuLTT) {
        Jpanel_Main.removeAll();
//            current = (JPanel) ;
        JPanel_XacNhanCho.setVisible(false);
        lab_Title.setVisible(false);
        JPanel_BanVe.setVisible(false);
//            Jpanel_Main.add();
        Jpanel_Main.setVisible(true);

        // Cập nhật lại giao diện người dùng
        Jpanel_Main.revalidate(); // Cập nhật layout
        Jpanel_Main.repaint();    //
    }

    //CÁC CHỨC NĂNG CON CỦA QUẢN LÝ VÉ
    //1. Đổi vé
    else if (e.getSource() == doiVe) {
        Jpanel_Main.removeAll();
//            current = (JPanel) ;
        current = (JPanel) doiVePanel;
        JPanel_XacNhanCho.setVisible(false);
        lab_Title.setVisible(false);
        JPanel_BanVe.setVisible(false);
          Jpanel_Main.add(doiVePanel);
        Jpanel_Main.setVisible(true);

        // Cập nhật lại giao diện người dùng
        Jpanel_Main.revalidate(); // Cập nhật layout
        Jpanel_Main.repaint();    //
    }
    //2. Trả vé
    else if (e.getSource() == traVe) {
        Jpanel_Main.removeAll();
//            current = (JPanel) ;
        JPanel_XacNhanCho.setVisible(false);
        lab_Title.setVisible(false);
        JPanel_BanVe.setVisible(false);
//            Jpanel_Main.add();
        Jpanel_Main.setVisible(true);

        // Cập nhật lại giao diện người dùng
        Jpanel_Main.revalidate(); // Cập nhật layout
        Jpanel_Main.repaint();    //
    }
}

private void updateTotalAmount(JLabel lblThanhTien, JTable table, double chietKhau) {
    double totalAmount = 0;

    // Lặp qua từng hàng trong bảng để tính tổng thành tiền
    for (int row = 0; row < table.getRowCount(); row++) {
        double thanhTien = (double) table.getValueAt(row, 5); // Cột 5 là "Thành tiền"
        totalAmount += thanhTien;
    }
    double tienChietKhau = totalAmount * (chietKhau / 100);
    totalAmount -= tienChietKhau;

    //đổi
    if(thongTinVeDoi!=null){
        totalAmount = totalAmount - thongTinVeDoi.getGiaVe() + 20000;
    }
    // Cập nhật label với tổng thành tiền
    total = totalAmount;
    lblThanhTien.setText("  Tổng thành tiền: " + totalAmount + " VND");
}
    private void calculateChange(double total, TextField txtTienKhachDua) {
        try {
            double tienKhachDua = Double.parseDouble(txtTienKhachDua.getText().trim());
            double tienThua = tienKhachDua - total;
            // Cập nhật vào txtTienThua
           lableTienThua.setText("Tiền thừa: "+ tienThua+ "VNĐ");
        } catch (NumberFormatException e) {
            // Xử lý nếu nhập sai định dạng
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số tiền hợp lệ!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
        }
    }

private String generateTicketCode(String maTau) {

    // Lấy thời gian hiện tại bao gồm ngày tháng và giờ phút giây
    LocalDateTime currentDateTime = LocalDateTime.now();
    // Nếu là ngày mới, đặt lại số đếm về 0
    if (!currentDateTime.toLocalDate().isEqual(lastDate)) {
        ticketCount = 0; // Đặt lại số vé
        lastDate = currentDateTime.toLocalDate(); // Cập nhật ngày cuối cùng
    }
    // Định dạng ngày tháng giờ phút giây theo "yyyyMMddHHmmss"
    String dateTimePart = currentDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

    // Tăng số đếm nếu có nhiều vé trong cùng một giây (lên đến 1000)
    if (ticketCount >= 1000) {
        ticketCount = 1; // Đặt lại số đếm nếu vượt quá 1000
    } else {
        ticketCount++; // Tăng số đếm
    }
    // Tạo phần số đếm, đảm bảo có tối đa 3 chữ số
    String countPart = String.format("%03d", ticketCount);
    // Tạo mã vé với cấu trúc: mã tàu + ngày tháng giờ phút giây + số đếm
    return maTau + dateTimePart + "-" + countPart;
}

private String generateInvoiceCode() {
    // Tăng giá trị counter
    counter = (counter + 1) % 10000; // Reset lại khi đạt đến 10000
    LocalDate today = LocalDate.now();
    // Lấy ngày theo định dạng ddMMyy
    String datePart1 = today.format(DateTimeFormatter.ofPattern("ddMMyy"));
    // Tạo mã hóa đơn với counter
    String invoiceCode = String.format("HD%s%04d", datePart1, counter);
    return invoiceCode;
}

public String generateCustomerCode() {
    // Tăng biến đếm lên 1, nếu vượt quá 9999 thì quay lại 0
    totalCustomerToday = (totalCustomerToday + 1) % 10000;
    // Lấy thời gian hiện tại
    LocalDateTime now = LocalDateTime.now();
    // Định dạng thời gian thành NgàyThángNăm (ddMMyy)
    String datePart1 = now.format(DateTimeFormatter.ofPattern("ddMMyy"));

    // Tạo mã khách hàng với format KH + thời gian + số thứ tự
    String customerCode = String.format("KH%s%04d", datePart1, totalCustomerToday);
    return customerCode;
}

@Override
public void itemStateChanged(ItemEvent e) {
    if (e.getSource() == btnRadio_MotChieu) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            setPanelEnabled(JPanel_NgayVe, false); // Khóa ngày về
        }
    } else if (e.getSource() == btnRadio_KhuHoi) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            setPanelEnabled(JPanel_NgayVe, true); // Mở lại ngày về
        }
    }
}


private JButton currentlySelectedButton = null; // Nút hiện tại đang được chọn

private void addButtonAction(JButton btn) {
    // Đặt lại màu cho nút đang chọn trước đó
    if (currentlySelectedButton != null) {
        currentlySelectedButton.setBackground(Color.cyan); // Màu nền bình thường
        currentlySelectedButton.setForeground(Color.white); // Màu chữ bình thường
    }
    // Đặt màu cho nút vừa nhấn
    btn.setBackground(Color.YELLOW); // Màu nền khi nút được chọn
    btn.setForeground(Color.RED); // Màu chữ khi nút được chọn
    currentlySelectedButton = btn;
}

private void showKhuyenMaiDialog(KhuyenMai khuyenMai) {
    JDialog dialog = new JDialog();
    dialog.setTitle("Thông Tin Khuyến Mãi");

    // Tạo panel chính với khoảng cách bên trong
    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;

    // Các thành phần hiển thị
    gbc.gridy = 0;
    panel.add(new JLabel("Mã KM:"), gbc);
    gbc.gridx = 1;
    JLabel lblMaKM = new JLabel(khuyenMai.getMaKM());
    lblMaKM.setFont(new Font("Arial", Font.BOLD, 14));
    panel.add(lblMaKM, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    panel.add(new JLabel("Chiết khấu:"), gbc);
    gbc.gridx = 1;
    JLabel lblChietKhau = new JLabel(String.valueOf(khuyenMai.getChietKhau() + " %"));
    lblChietKhau.setFont(new Font("Arial", Font.BOLD, 14));
    lblChietKhau.setForeground(Color.BLUE); // Đổi màu chữ
    panel.add(lblChietKhau, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    panel.add(new JLabel("Thời gian bắt đầu:"), gbc);
    gbc.gridx = 1;
    panel.add(new JLabel(khuyenMai.getThoiGianBatDau().toString()), gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    panel.add(new JLabel("Thời gian kết thúc:"), gbc);
    gbc.gridx = 1;
    panel.add(new JLabel(khuyenMai.getThoiGianKetThuc().toString()), gbc);

    gbc.gridx = 0;
    gbc.gridy = 4;
    panel.add(new JLabel("Nội dung KM:"), gbc);

    gbc.gridx = 1;
    JTextArea txtNoiDungKM = new JTextArea(khuyenMai.getNoiDungKM());
    txtNoiDungKM.setWrapStyleWord(true);
    txtNoiDungKM.setLineWrap(true);
    txtNoiDungKM.setEditable(false);
    txtNoiDungKM.setBackground(new Color(240, 240, 240)); // Màu nền nhẹ
    txtNoiDungKM.setFont(new Font("Arial", Font.PLAIN, 12));
    txtNoiDungKM.setBorder(null); // Xóa viền của JTextArea

    JScrollPane scrollPane = new JScrollPane(txtNoiDungKM);
    scrollPane.setPreferredSize(new Dimension(200, 60));
    panel.add(scrollPane, gbc);
    // Nút đóng
    JButton btnClose = new JButton("Đóng");
    btnClose.setFont(new Font("Arial", Font.BOLD, 14));
    btnClose.addActionListener(e -> dialog.dispose());

    // Bố cục của dialog
    dialog.setLayout(new BorderLayout());
    dialog.add(panel, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(1, 0, 1, 0));
    buttonPanel.add(btnClose);

    dialog.add(buttonPanel, BorderLayout.SOUTH);

    dialog.setSize(355, 260); // Kích thước dialog
    dialog.setLocationRelativeTo(null); // Hiển thị giữa màn hình
    dialog.setModal(true); // Chặn thao tác ngoài dialog
    dialog.setVisible(true);
}
    // Phương thức mở file PDF
    private void openPdfFile(String fileName) {
        try {
            File pdfFile = new File(fileName);
            if (pdfFile.exists()) {
                // Sử dụng Desktop để mở file PDF
                try {
                    Desktop.getDesktop().open(pdfFile);
                } catch (java.io.IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy file PDF.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Không thể mở file PDF: " + e.getMessage());
        }
    }
    public String removeDiacritics(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("").replace("đ", "d").replace("Đ", "D");
    }
FrmDangNhap frmDangNhap = new FrmDangNhap();
DAO_NhanVien dao_nv = new DAO_NhanVien();
String role = frmDangNhap.getmaNV();
private void phanQuyen(String maNV) throws Exception {
    System.out.println(maNV);
    List<NhanVien> list = dao_nv.findAll();
    for (NhanVien nv : list) {
        if (nv.getMaNhanVien().equalsIgnoreCase(maNV) && nv.getChucVu().equalsIgnoreCase("Nhan vien")) {
            btnQuanLyNhanVien.setEnabled(false);
            btnQuanLyChuyenTau.setEnabled(false);
            btnQuanLyLLV.setEnabled(false);
        }if(nv.getMaNhanVien().equalsIgnoreCase(maNV) && nv.getChucVu().equalsIgnoreCase("Quan ly")){
            btnBanVe.setEnabled(false);
            btnQuanLyVe.setEnabled(false);
            btnTraCuu.setEnabled(false);
            btnQuanLyKhuyenMai.setEnabled(false);
            btnThongKeTheoCa.setEnabled(false);
        }
    }
}

    public void setThongTinVe(VeTau thongTinVeDoi) {
        this.thongTinVeDoi = thongTinVeDoi;
    }
}

