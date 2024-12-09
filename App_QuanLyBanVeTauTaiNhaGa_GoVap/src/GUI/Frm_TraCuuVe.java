package GUI;

import DAO.DAO_TraCuuVe;
import Database.ConnectDatabase;
import Entity.VeTau;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Frm_TraCuuVe extends JFrame {
    private final JPanel panelTimVe;
    private final JPanel panelChiTietVe;
    private JRadioButton radioMaVe;
    private JRadioButton radioGiayTo;
    private JRadioButton radioHoTen;
    private ButtonGroup buttonGroup;
    private JPanel contentPanel;
    private JPanel JPanel_Menu;
    private JPanel JPanel_Right = new JPanel(); // Panel chứa nội dung
    private JPanel panel_ThongTinVe = new JPanel();
    private JTable table;
    private DefaultTableModel tableModel;
    private  Color MauXanh = new Color(0,131,66);
    private JTextField txtHoTen;
    private JButton btnTimVe;
    private JTextField txtMaVe;
    private JTextField txtMaLich;
    private JTextField txtMaCho;
    private JTextField txtTenKH;
    private JTextField txtGiayTo;
    private JTextField txtNgayDi;
    private JTextField txtDoiTuong;
    private JTextField txtGiaVe;
    private JTextField txtTrangThai;
    private List<VeTau> veTauList;
    public Component get_TraCuuVe_Panel() {
        return JPanel_Right;
    }

    public Frm_TraCuuVe() {
        DAO_TraCuuVe daoTraCuuVe = new DAO_TraCuuVe();
        // Kết nối database
        ConnectDatabase.getInstance().connect();

        // Thiết lập cửa sổ chính
        setTitle("Form tra cứu vé");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Thêm menu và panel bên phải
        add(JPanel_Menu, BorderLayout.WEST);
        add(JPanel_Right, BorderLayout.CENTER);

        // Cấu hình layout cho panel bên phải (vẫn giữ BorderLayout)
        JPanel_Right.setLayout(new BorderLayout());

        // Khởi tạo panel TraCuuVe và cấu hình layout
        JPanel panel_TraCuuVe = new JPanel();
        panel_TraCuuVe.setLayout(new BoxLayout(panel_TraCuuVe, BoxLayout.X_AXIS)); // Chỉ áp dụng BoxLayout cho panel_TraCuuVe
        JPanel_Right.add(panel_TraCuuVe, BorderLayout.CENTER);

        // Khởi tạo panel ThongTinVe
        JPanel panel_ThongTinVe = new JPanel();
        panel_ThongTinVe.setLayout(new BorderLayout());
        JPanel_Right.add(panel_ThongTinVe, BorderLayout.SOUTH);

        // Khởi tạo các panel con
        panelTimVe = new JPanel();
        panelChiTietVe = new JPanel();
        panelChiTietVe.setLayout(new BorderLayout());
        panelTimVe.setLayout(new BorderLayout());

        // Cấu hình các panel con với BorderLayout cho panelTimVe và panelChiTietVe
        panel_TraCuuVe.add(panelTimVe);
        panel_TraCuuVe.add(createPanelChiTietVe());

        // Thiết lập kích thước cho panel_TraCuuVe
        panel_TraCuuVe.setPreferredSize(new Dimension(0, 0));
        panel_TraCuuVe.setMinimumSize(new Dimension(0, 0));

        // Gọi phương thức revalidate và repaint cho panel_TraCuuVe để cập nhật giao diện
        panel_TraCuuVe.revalidate();
        panel_TraCuuVe.repaint();



        // Khởi tạo và cấu hình panel TimChiTiet
        JPanel timChiTiet = createPanelTimChitiet();

        // Thêm khoảng cách bên trong cho các panel
        timChiTiet.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        createPanelTimNhanh().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//        createPanelTimTheoThoiGian().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));




        // Thêm các panel vào panelTimVe
        panelTimVe.add(createPanelTimNhanh(), BorderLayout.NORTH);
        panelTimVe.add(timChiTiet, BorderLayout.CENTER);
        panelTimVe.add(createPanelTimTheoThoiGian(), BorderLayout.SOUTH);

        // Thêm khoảng cách xung quanh panelTimVe và border
        panelTimVe.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        // Tạo giao diện bảng
        panel_ThongTinVe.setLayout(new BorderLayout());
        String[] columnNames = {
                "Mã vé", "Họ tên", "Giấy tờ", "Ngày đi", "Đối tượng", "Giá vé", "Trạng thái"
        };
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Tùy chỉnh tiêu đề bảng
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 25)); // Font chữ in đậm, kích thước 16
        header.setBackground(MauXanh); // Nền màu xanh

        header.setForeground(Color.WHITE); // Chữ màu trắng
        header.setOpaque(true);

        // Tùy chỉnh font chữ từng dòng
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setFont(new Font("Arial", Font.PLAIN, 20)); // Font chữ từng dòng, kích thước 14
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa nội dung
        table.setDefaultRenderer(Object.class, cellRenderer);

        // Chỉnh chiều cao dòng (mặc định cho tất cả dòng)
        table.setRowHeight(30); // Đặt chiều cao cho tất cả các dòng là 30px

        // Đưa bảng vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        panel_ThongTinVe.add(scrollPane, BorderLayout.CENTER);
        // Thêm sự kiện cho nút "Tìm vé"
        addSearchButtonListener();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow(); // Lấy chỉ số dòng được chọn
                if (row >= 0) {
                    // Lấy mã vé từ cột đầu tiên (Giả sử cột mã vé là cột 0)
                    String maVe = table.getValueAt(row, 0).toString(); // Cột mã vé (có thể thay đổi theo cột trong bảng của bạn)

                    // Tìm vé trong danh sách vé bằng mã vé
                    VeTau ve = findVeByMaVe(maVe); // Giả sử phương thức này tìm vé trong danh sách

                    if (ve != null) {
                        // Điền thông tin vé vào các JTextField
                        txtMaVe.setText(ve.getMaVe());
                        txtMaLich.setText("Tên tàu:"+ve.getLichTrinhTau().getTau().getTenTau()+", TG: "+ve.getLichTrinhTau().getNgayDi()+"-"+ve.getLichTrinhTau().getGioDi());
                        txtMaCho.setText(ve.getChoNgoi().getMaCho());
                        txtTenKH.setText(ve.getTenKhachHang());
                        txtGiayTo.setText(ve.getGiayTo());
                        txtNgayDi.setText(String.valueOf(ve.getNgayDi()));
                        txtDoiTuong.setText(ve.getDoiTuong());
                        txtGiaVe.setText(String.valueOf(ve.getGiaVe()));
                        txtTrangThai.setText(ve.getTrangThai());
                    }
                }
            }
        });


    }
    // Phương thức tìm vé theo mã vé
    private VeTau findVeByMaVe(String maVe) {
        for (VeTau ve : veTauList) {
            if (ve.getMaVe().equals(maVe)) {
                return ve; // Trả về vé tìm được
            }
        }
        return null; // Trả về null nếu không tìm thấy vé
    }
    // Phương thức tạo panel chi tiết vé
    private JPanel createPanelChiTietVe() {
        JPanel panelChiTietVe = new JPanel();
        panelChiTietVe.setLayout(new GridBagLayout());

        TitledBorder titledBorder = new TitledBorder("Chi tiết vé");
        titledBorder.setTitleFont(new Font("Arial", Font.ITALIC, 20));
        titledBorder.setTitleJustification(TitledBorder.LEFT);
        panelChiTietVe.setBorder(titledBorder);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Tạo các JLabel và JTextField cho từng trường
        JLabel lblMaVe = new JLabel("Mã vé:");
        lblMaVe.setFont(new Font("Arial", Font.BOLD, 18));
        txtMaVe = new JTextField(20);
        txtMaVe.setFont(new Font("Arial", Font.PLAIN, 18));
        txtMaVe.setPreferredSize(new Dimension(180, 30));

        JLabel lblMaLich = new JLabel("Thông tin:");
        lblMaLich.setFont(new Font("Arial", Font.BOLD, 20));
        txtMaLich = new JTextField(20);
        txtMaLich.setFont(new Font("Arial", Font.PLAIN, 15));
        txtMaLich.setPreferredSize(new Dimension(250, 35));

        JLabel lblMaCho = new JLabel("Chỗ ngồi:");
        lblMaCho.setFont(new Font("Arial", Font.BOLD, 20));
        txtMaCho = new JTextField(20);
        txtMaCho.setFont(new Font("Arial", Font.PLAIN, 18));
        txtMaCho.setPreferredSize(new Dimension(250, 35));

        JLabel lblTenKH = new JLabel("Tên khách hàng:");
        lblTenKH.setFont(new Font("Arial", Font.BOLD, 20));
        txtTenKH = new JTextField(20);
        txtTenKH.setFont(new Font("Arial", Font.PLAIN, 18));
        txtTenKH.setPreferredSize(new Dimension(250, 35));

        JLabel lblGiayTo = new JLabel("Giấy tờ:");
        lblGiayTo.setFont(new Font("Arial", Font.BOLD, 20));
        txtGiayTo = new JTextField(20);
        txtGiayTo.setFont(new Font("Arial", Font.PLAIN, 18));
        txtGiayTo.setPreferredSize(new Dimension(250, 35));

        JLabel lblNgayDi = new JLabel("Ngày đi:");
        lblNgayDi.setFont(new Font("Arial", Font.BOLD, 20));
        txtNgayDi = new JTextField(20);
        txtNgayDi.setFont(new Font("Arial", Font.PLAIN, 18));
        txtNgayDi.setPreferredSize(new Dimension(250, 35));

        JLabel lblDoiTuong = new JLabel("Đối tượng:");
        lblDoiTuong.setFont(new Font("Arial", Font.BOLD, 20));
        txtDoiTuong = new JTextField(20);
        txtDoiTuong.setFont(new Font("Arial", Font.PLAIN, 18));
        txtDoiTuong.setPreferredSize(new Dimension(250, 35));

        JLabel lblGiaVe = new JLabel("Giá vé:");
        lblGiaVe.setFont(new Font("Arial", Font.BOLD, 20));
        txtGiaVe = new JTextField(20);
        txtGiaVe.setFont(new Font("Arial", Font.PLAIN, 18));
        txtGiaVe.setPreferredSize(new Dimension(250, 35));

        JLabel lblTrangThai = new JLabel("Trạng thái:");
        lblTrangThai.setFont(new Font("Arial", Font.BOLD, 20));
        txtTrangThai = new JTextField(20);
        txtTrangThai.setFont(new Font("Arial", Font.PLAIN, 18));
        txtTrangThai.setPreferredSize(new Dimension(250, 35));

        // Sắp xếp các thành phần vào GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelChiTietVe.add(lblMaVe, gbc);
        gbc.gridx = 1;
        panelChiTietVe.add(txtMaVe, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelChiTietVe.add(lblMaLich, gbc);
        gbc.gridx = 1;
        panelChiTietVe.add(txtMaLich, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelChiTietVe.add(lblMaCho, gbc);
        gbc.gridx = 1;
        panelChiTietVe.add(txtMaCho, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelChiTietVe.add(lblTenKH, gbc);
        gbc.gridx = 1;
        panelChiTietVe.add(txtTenKH, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelChiTietVe.add(lblGiayTo, gbc);
        gbc.gridx = 1;
        panelChiTietVe.add(txtGiayTo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panelChiTietVe.add(lblNgayDi, gbc);
        gbc.gridx = 1;
        panelChiTietVe.add(txtNgayDi, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panelChiTietVe.add(lblDoiTuong, gbc);
        gbc.gridx = 1;
        panelChiTietVe.add(txtDoiTuong, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panelChiTietVe.add(lblGiaVe, gbc);
        gbc.gridx = 1;
        panelChiTietVe.add(txtGiaVe, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        panelChiTietVe.add(lblTrangThai, gbc);
        gbc.gridx = 1;
        panelChiTietVe.add(txtTrangThai, gbc);

        return panelChiTietVe;
    }
    private JPanel createPanelTimTheoThoiGian() {
        // Tạo panel chính với GridBagLayout
        JPanel panelTimTheoThoiGian = new JPanel();
        panelTimTheoThoiGian.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần

        // Hàng đầu tiên: Họ tên
        JLabel lblHoTen = new JLabel("Họ tên:");
        lblHoTen.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panelTimTheoThoiGian.add(lblHoTen, gbc);

        JTextField txtHoTen = new JTextField(25);
        txtHoTen.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // Chiếm 3 cột
        panelTimTheoThoiGian.add(txtHoTen, gbc);

        // Hàng thứ hai: Thời gian
        JLabel lblThoiGian = new JLabel("Thời gian:");
        lblThoiGian.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panelTimTheoThoiGian.add(lblThoiGian, gbc);

        // Calendar đầu tiên (JDateChooser)
        JDateChooser dateChooserFrom = new JDateChooser();
        dateChooserFrom.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 2.0;  // Tăng giá trị weightx để panel rộng hơn
        panelTimTheoThoiGian.add(dateChooserFrom, gbc);

        // Label "đến"
        JLabel lblDen = new JLabel("đến");
        lblDen.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 2;
        gbc.gridy = 1;
        panelTimTheoThoiGian.add(lblDen, gbc);

        // Calendar thứ hai (JDateChooser)
        JDateChooser dateChooserTo = new JDateChooser();
        dateChooserTo.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 2.0;  // Tăng giá trị weightx để panel rộng hơn
        panelTimTheoThoiGian.add(dateChooserTo, gbc);

        // Nút Lọc
        JButton btnLoc = new JButton("Lọc");
        btnLoc.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 4;
        gbc.gridy = 0; // Bắt đầu ở hàng đầu tiên
        gbc.gridheight = 2; // Chiếm cả hai hàng
        gbc.fill = GridBagConstraints.VERTICAL; // Đặt fill theo chiều dọc
        panelTimTheoThoiGian.add(btnLoc, gbc);
// Thêm sự kiện cho nút "Lọc"
        btnLoc.addActionListener(e -> {
            // Lấy giá trị từ các trường nhập liệu
            String hoTen = txtHoTen.getText().trim(); // Lấy tên khách hàng
            Date ngayDiFrom = dateChooserFrom.getDate(); // Lấy ngày bắt đầu
            Date ngayDiTo = dateChooserTo.getDate(); // Lấy ngày kết thúc

            // Kiểm tra dữ liệu nhập vào
            if (hoTen.isEmpty() || ngayDiFrom == null || ngayDiTo == null) {
                JOptionPane.showMessageDialog(panelTimTheoThoiGian, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Chuyển ngày sang định dạng chuẩn nếu cần thiết
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String strNgayDiFrom = sdf.format(ngayDiFrom);
            String strNgayDiTo = sdf.format(ngayDiTo);

            try {
                DAO_TraCuuVe daoTraCuuVe = new DAO_TraCuuVe();
                // Gọi phương thức tìm vé tàu theo thời gian (sử dụng strNgayDiFrom và strNgayDiTo)
                veTauList = daoTraCuuVe.timVeTauTheoTenKHVaThoiGian(hoTen, strNgayDiFrom, strNgayDiTo);

                // Kiểm tra nếu có kết quả
                if (!veTauList.isEmpty()) {
                    // Cập nhật bảng với danh sách vé tìm được
                    updateTableWithVeTau(veTauList);
                } else {
                    // Nếu không tìm thấy vé
                    JOptionPane.showMessageDialog(panelTimTheoThoiGian, "Không tìm thấy vé với thông tin này!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                // Xử lý lỗi khi truy vấn cơ sở dữ liệu
                JOptionPane.showMessageDialog(panelTimTheoThoiGian, "Lỗi khi tìm vé tàu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        return panelTimTheoThoiGian;
    }


    private JPanel createPanelTimChitiet() {
        // Tạo panel chính với GridBagLayout
        JPanel panelTimChitiet = new JPanel();
        panelTimChitiet.setLayout(new GridBagLayout());
        panelTimChitiet.revalidate();
        panelTimChitiet.repaint();
        // Tạo border với tiêu đề và căn tiêu đề bên trái
        TitledBorder titledBorder = new TitledBorder("Tìm chi tiết");
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 20)); // Đặt font cho tiêu đề
        titledBorder.setTitleJustification(TitledBorder.LEFT); // Căn tiêu đề về bên trái
        panelTimChitiet.setBorder(titledBorder); // Áp dụng border cho panel

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các thành phần

        // Tên khách hàng
        JLabel lblTenKhachHang = new JLabel("Tên khách hàng:");
        lblTenKhachHang.setFont(new Font("Arial", Font.PLAIN, 18)); // Tăng cỡ chữ
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelTimChitiet.add(lblTenKhachHang, gbc);

        JTextField txtTenKhachHang = new JTextField(30); // Tăng kích thước JTextField
        txtTenKhachHang.setFont(new Font("Arial", Font.PLAIN, 18)); // Tăng cỡ chữ
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelTimChitiet.add(txtTenKhachHang, gbc);

        // Đối tượng
        JLabel lblDoiTuong = new JLabel("Đối tượng:");
        lblDoiTuong.setFont(new Font("Arial", Font.PLAIN, 18)); // Tăng cỡ chữ
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelTimChitiet.add(lblDoiTuong, gbc);

        JComboBox<String> comboDoiTuong = new JComboBox<>(new String[]{"Sinh viên", "Người lớn", "Trẻ em"});
        comboDoiTuong.setFont(new Font("Arial", Font.PLAIN, 18)); // Tăng cỡ chữ
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelTimChitiet.add(comboDoiTuong, gbc);

        // Giấy tờ
        JLabel lblGiayTo = new JLabel("Giấy tờ:");
        lblGiayTo.setFont(new Font("Arial", Font.PLAIN, 18)); // Tăng cỡ chữ
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelTimChitiet.add(lblGiayTo, gbc);

        JTextField txtGiayTo = new JTextField(30); // Tăng kích thước JTextField
        txtGiayTo.setFont(new Font("Arial", Font.PLAIN, 18)); // Tăng cỡ chữ
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelTimChitiet.add(txtGiayTo, gbc);

        // Ngày đi
        JLabel lblNgayDi = new JLabel("Ngày đi:");
        lblNgayDi.setFont(new Font("Arial", Font.PLAIN, 18)); // Tăng cỡ chữ
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelTimChitiet.add(lblNgayDi, gbc);

        JTextField txtNgayDi = new JTextField(30); // Tăng kích thước JTextField
        txtNgayDi.setFont(new Font("Arial", Font.PLAIN, 18)); // Tăng cỡ chữ
        gbc.gridx = 1;
        gbc.gridy = 3;
        panelTimChitiet.add(txtNgayDi, gbc);

        // Mã chỗ ngồi
        JLabel lblMaChoNgoi = new JLabel("Mã chỗ ngồi:");
        lblMaChoNgoi.setFont(new Font("Arial", Font.PLAIN, 18)); // Tăng cỡ chữ
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelTimChitiet.add(lblMaChoNgoi, gbc);

        JTextField txtMaChoNgoi = new JTextField(30); // Tăng kích thước JTextField
        txtMaChoNgoi.setFont(new Font("Arial", Font.PLAIN, 18)); // Tăng cỡ chữ
        gbc.gridx = 1;
        gbc.gridy = 4;
        panelTimChitiet.add(txtMaChoNgoi, gbc);

        // Button Tìm vé
        JButton btnTimVe = new JButton("Tìm vé");
        btnTimVe.setFont(new Font("Arial", Font.BOLD, 18)); // Tăng cỡ chữ
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        panelTimChitiet.add(btnTimVe, gbc);
        // Thêm sự kiện cho nút "Tìm vé"
        btnTimVe.addActionListener(e -> {
            // Lấy giá trị từ các trường nhập liệu
            String tenKhachHang = txtTenKhachHang.getText().trim();
            String giayTo = txtGiayTo.getText().trim();
            String ngayDi = txtNgayDi.getText().trim();
            String maChoNgoi = txtMaChoNgoi.getText().trim();
            String doiTuong = (String) comboDoiTuong.getSelectedItem();

            // Kiểm tra dữ liệu nhập vào
            if (tenKhachHang.isEmpty() || giayTo.isEmpty() || ngayDi.isEmpty() || maChoNgoi.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                DAO_TraCuuVe daoTraCuuVe = new DAO_TraCuuVe();
                // Gọi phương thức tìm vé theo các tiêu chí từ DAO
                veTauList = daoTraCuuVe.timVeTauTheoChitiet(tenKhachHang, giayTo, ngayDi, maChoNgoi, doiTuong);

                if (!veTauList.isEmpty()) {
                    // Cập nhật bảng với danh sách vé tìm được
                    updateTableWithVeTau(veTauList);
                } else {
                    // Nếu không tìm thấy vé
                    JOptionPane.showMessageDialog(null, "Không tìm thấy vé với thông tin này!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                // Xử lý lỗi khi truy vấn cơ sở dữ liệu
                JOptionPane.showMessageDialog(null, "Lỗi khi tìm vé: " + ex.getMessage(), "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        });
        return panelTimChitiet;
    }

    private JPanel createPanelTimNhanh() {
        // Tạo panel chính với layout GridBagLayout
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        // Tạo border với tiêu đề và căn tiêu đề bên trái
        TitledBorder titledBorder = new TitledBorder("Tìm nhanh");
        titledBorder.setTitleFont(new Font("Arial", Font.ITALIC, 20)); // Đặt font cho tiêu đề
        titledBorder.setTitleJustification(TitledBorder.LEFT); // Căn tiêu đề về bên trái
        panel.setBorder(titledBorder);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Cài đặt khoảng cách giữa các phần tử
        gbc.insets = new Insets(10, 10, 10, 10);  // Khoảng cách: top, left, bottom, right

        // Cài đặt chiều rộng của các cột
        gbc.gridx = 0;  // Cột 0 - radio buttons
        gbc.gridy = 0;  // Dòng 0
        gbc.gridheight = 3;  // Chiếm 3 dòng
        panel.add(createRadioPanel(), gbc);  // Thêm panel chứa radio button

        gbc.gridx = 1;  // Cột 1 - text field
        gbc.gridy = 0;  // Dòng 0
        gbc.gridheight = 1;
        txtHoTen = new JTextField(20);
        txtHoTen.setFont(new Font("Arial", Font.PLAIN, 25)); // Cập nhật kích thước font của JTextField
        panel.add(txtHoTen, gbc);

        gbc.gridx = 2;  // Cột 2 - button Tìm vé
        gbc.gridy = 0;  // Dòng 0
        gbc.gridheight = 1;
        btnTimVe = new JButton("Tìm vé");
        btnTimVe.setFont(new Font("Arial", Font.PLAIN, 25)); // Cập nhật kích thước font của JButton
        panel.add(btnTimVe, gbc);

        // Thêm button Quét mã QR
        gbc.gridx = 2;  // Cột 2 - button Quét mã QR
        gbc.gridy = 1;  // Dòng 1
        gbc.gridheight = 1;
        JButton btnQuetQR = new JButton("Quét mã QR");
        btnQuetQR.setFont(new Font("Arial", Font.PLAIN, 25)); // Cập nhật kích thước font của JButton
        panel.add(btnQuetQR, gbc);

        // Đảm bảo chiều cao các nút button bằng nhau (2 button sẽ có chiều cao bằng nhau như radio button)
        gbc.gridx = 2;  // Cột 2
        gbc.gridy = 0;  // Dòng 0
        gbc.gridheight = 3;  // Chiếm 3 dòng, giống như các radio button
        btnQuetQR.addActionListener(e -> {
            // Khởi tạo cửa sổ quét mã QR
            QR_Scan qrScan = new QR_Scan();

            // Thêm WindowListener để nhận kết quả từ QR_Scan khi đóng
            qrScan.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    // Lấy chuỗi kết quả từ mã QR và hiển thị trong txtHoTen
                    String qrResult = qrScan.getQrResult(); // Phương thức này cần được thêm trong lớp QR_Scan
                    if (qrResult != null && !qrResult.isEmpty()) {
                        txtHoTen.setText(qrResult); // Gán kết quả vào JTextField
                    }
                }
            });
            qrScan.setVisible(true); // Hiển thị cửa sổ QR_Scan
        });
        return panel;
    }

    private JPanel createRadioPanel() {
        // Tạo một panel riêng cho các radio button
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));

        // Tạo các radio button
        radioMaVe = new JRadioButton("Mã vé");
        radioGiayTo = new JRadioButton("Giấy tờ");
        radioHoTen = new JRadioButton("Họ tên");

        // Cập nhật kích thước font cho các radio button
        radioMaVe.setFont(new Font("Arial", Font.PLAIN, 25));
        radioGiayTo.setFont(new Font("Arial", Font.PLAIN, 25));
        radioHoTen.setFont(new Font("Arial", Font.PLAIN, 25));

        // Nhóm các radio button lại với nhau
        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioMaVe);
        buttonGroup.add(radioGiayTo);
        buttonGroup.add(radioHoTen);

        // Thêm các radio button vào panel
        radioPanel.add(radioMaVe);
        radioPanel.add(radioGiayTo);
        radioPanel.add(radioHoTen);

        return radioPanel;
    }

    private void addSearchButtonListener() {
        btnTimVe.addActionListener(e -> {
            // Lấy tiêu chí tìm kiếm từ radio button
            if (radioMaVe.isSelected()) {
                String maVe = txtHoTen.getText().trim();
                if (!maVe.isEmpty()) {
                    try {
                        DAO_TraCuuVe daoTraCuuVe = new DAO_TraCuuVe();
                        // Gọi phương thức timVeTauTheoMa từ DAO để tìm vé theo mã
                        veTauList = daoTraCuuVe.timVeTauTheoMa(maVe);

                        if (!veTauList.isEmpty()) {
                            // Cập nhật bảng với danh sách vé tìm được
                            updateTableWithVeTau(veTauList);
                        } else {
                            // Nếu không tìm thấy vé
                            JOptionPane.showMessageDialog(null, "Không tìm thấy vé với mã này!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        // Xử lý lỗi khi truy vấn cơ sở dữ liệu
                        JOptionPane.showMessageDialog(null, "Lỗi khi tìm vé: " + ex.getMessage(), "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập mã vé!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            } else if (radioGiayTo.isSelected()) {
                String giayTo = txtHoTen.getText().trim();  // Giả sử txtGiayTo là ô nhập liệu cho giấy tờ
                if (!giayTo.isEmpty()) {
                    try {
                        DAO_TraCuuVe daoTraCuuVe = new DAO_TraCuuVe();
                        // Gọi phương thức timVeTauTheoGiayTo từ DAO để tìm vé theo giấy tờ
                        veTauList = daoTraCuuVe.timVeTauTheoGiayTo(giayTo);

                        if (!veTauList.isEmpty()) {
                            // Cập nhật bảng với danh sách vé tìm được
                            updateTableWithVeTau(veTauList);
                        } else {
                            // Nếu không tìm thấy vé
                            JOptionPane.showMessageDialog(null, "Không tìm thấy vé với giấy tờ này!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        // Xử lý lỗi khi truy vấn cơ sở dữ liệu
                        JOptionPane.showMessageDialog(null, "Lỗi khi tìm vé: " + ex.getMessage(), "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập giấy tờ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            } else if (radioHoTen.isSelected()) {
                String tenKhachHang = txtHoTen.getText().trim();  // Giả sử txtHoTen là ô nhập liệu cho tên khách hàng
                if (!tenKhachHang.isEmpty()) {
                    try {
                        DAO_TraCuuVe daoTraCuuVe = new DAO_TraCuuVe();
                        // Gọi phương thức timVeTauTheoTenKH từ DAO để tìm vé theo tên khách hàng
                        veTauList = daoTraCuuVe.timVeTauTheoTenKH(tenKhachHang);

                        if (!veTauList.isEmpty()) {
                            // Cập nhật bảng với danh sách vé tìm được
                            updateTableWithVeTau(veTauList);
                        } else {
                            // Nếu không tìm thấy vé
                            JOptionPane.showMessageDialog(null, "Không tìm thấy vé với tên khách hàng này!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        // Xử lý lỗi khi truy vấn cơ sở dữ liệu
                        JOptionPane.showMessageDialog(null, "Lỗi khi tìm vé: " + ex.getMessage(), "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập tên khách hàng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một tiêu chí tìm kiếm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });
    }



    private void updateTableWithVeTau(List<VeTau> veTauList) {
        tableModel.setRowCount(0); // Xóa dữ liệu cũ trong bảng

        if (veTauList != null && !veTauList.isEmpty()) {
            for (VeTau veTau : veTauList) {
                // Cập nhật dữ liệu hiển thị trong bảng với các trường tương ứng
                Object[] rowData = {
                        veTau.getMaVe(),
                        veTau.getTenKhachHang(),
                        veTau.getGiayTo(),
                        veTau.getNgayDi(),
                        veTau.getDoiTuong(),
                        veTau.getGiaVe(),
                        veTau.getTrangThai() // Trạng thái
                };
                tableModel.addRow(rowData); // Thêm dữ liệu mới vào bảng
            }
        } else {
            JOptionPane.showMessageDialog(null, "Không tìm thấy vé với thông tin đã nhập.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

        // Đặt font cho bảng
        table.setFont(new Font("Arial", Font.PLAIN, 20)); // Thay đổi kích thước chữ ở đây
    }




    public static void main(String[] args) {
        Frm_TraCuuVe frm = new Frm_TraCuuVe();
        frm.setVisible(true);
    }
}
