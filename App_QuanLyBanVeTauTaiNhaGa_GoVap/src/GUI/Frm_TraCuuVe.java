package GUI;

import DAO.DAO_TraCuuVe;
import Database.ConnectDatabase;
import Entity.VeTau;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
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

    public Component get_TraCuuVe_Panel() {
        return JPanel_Right;
    }

    public Frm_TraCuuVe() {
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
        createPanelTimTheoThoiGian().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));




        // Thêm các panel vào panelTimVe
        panelTimVe.add(createPanelTimNhanh(), BorderLayout.NORTH);
        panelTimVe.add(timChiTiet, BorderLayout.CENTER);
        panelTimVe.add(createPanelTimTheoThoiGian(), BorderLayout.SOUTH);

        // Thêm khoảng cách xung quanh panelTimVe và border
        panelTimVe.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        // Thiết lập layout và tạo bảng cho panel_ThongTinVe
        panel_ThongTinVe.setLayout(new BorderLayout());
        String[] columnNames = {"Mã vé", "Họ tên", "Giấy tờ", "Ngày đi", "Đối tượng", "Giá vé"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Cấu hình bảng
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setBackground(MauXanh);
        header.setForeground(Color.WHITE);
        header.setOpaque(true);

        // Cấu hình font chữ cho từng dòng
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setFont(new Font("Arial", Font.PLAIN, 25));
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, cellRenderer);

        // Thiết lập chiều cao cho các dòng
        table.setRowHeight(30);

        // Đưa bảng vào JScrollPane và thêm vào panel_ThongTinVe
        JScrollPane scrollPane = new JScrollPane(table);
        panel_ThongTinVe.add(scrollPane, BorderLayout.CENTER);

        // Tải dữ liệu vào bảng
        loadDataToTable();
    }

    private static JPanel createPanelChiTietVe() {
        // Tạo panel chi tiết vé
        JPanel panelChiTietVe = new JPanel();
        panelChiTietVe.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần

        // Tạo các JLabel và JTextField cho từng trường
        JLabel lblMaVe = new JLabel("Mã vé:");
        lblMaVe.setFont(new Font("Arial", Font.BOLD, 18));  // Giảm cỡ chữ
        JTextField txtMaVe = new JTextField(20);  // Giảm số lượng cột
        txtMaVe.setFont(new Font("Arial", Font.PLAIN, 18));  // Giảm cỡ chữ
        txtMaVe.setPreferredSize(new Dimension(180, 30)); // Giảm kích thước JTextField

        JLabel lblMaLich = new JLabel("Mã lịch:");
        lblMaLich.setFont(new Font("Arial", Font.BOLD, 20));  // Giảm cỡ chữ
        JTextField txtMaLich = new JTextField(20);
        txtMaLich.setFont(new Font("Arial", Font.PLAIN, 18));
        txtMaLich.setPreferredSize(new Dimension(250, 35));  // Giảm kích thước JTextField

        JLabel lblMaCho = new JLabel("Mã chỗ:");
        lblMaCho.setFont(new Font("Arial", Font.BOLD, 20));  // Giảm cỡ chữ
        JTextField txtMaCho = new JTextField(20);
        txtMaCho.setFont(new Font("Arial", Font.PLAIN, 18));
        txtMaCho.setPreferredSize(new Dimension(250, 35));  // Giảm kích thước JTextField

        JLabel lblTenKH = new JLabel("Tên khách hàng:");
        lblTenKH.setFont(new Font("Arial", Font.BOLD, 20));  // Giảm cỡ chữ
        JTextField txtTenKH = new JTextField(20);
        txtTenKH.setFont(new Font("Arial", Font.PLAIN, 18));
        txtTenKH.setPreferredSize(new Dimension(250, 35));  // Giảm kích thước JTextField

        JLabel lblGiayTo = new JLabel("Giấy tờ:");
        lblGiayTo.setFont(new Font("Arial", Font.BOLD, 20));  // Giảm cỡ chữ
        JTextField txtGiayTo = new JTextField(20);
        txtGiayTo.setFont(new Font("Arial", Font.PLAIN, 18));
        txtGiayTo.setPreferredSize(new Dimension(250, 35));  // Giảm kích thước JTextField

        JLabel lblNgayDi = new JLabel("Ngày đi:");
        lblNgayDi.setFont(new Font("Arial", Font.BOLD, 20));  // Giảm cỡ chữ
        JTextField txtNgayDi = new JTextField(20);
        txtNgayDi.setFont(new Font("Arial", Font.PLAIN, 18));
        txtNgayDi.setPreferredSize(new Dimension(250, 35));  // Giảm kích thước JTextField

        JLabel lblDoiTuong = new JLabel("Đối tượng:");
        lblDoiTuong.setFont(new Font("Arial", Font.BOLD, 20));  // Giảm cỡ chữ
        JTextField txtDoiTuong = new JTextField(20);
        txtDoiTuong.setFont(new Font("Arial", Font.PLAIN, 18));
        txtDoiTuong.setPreferredSize(new Dimension(250, 35));  // Giảm kích thước JTextField

        JLabel lblGiaVe = new JLabel("Giá vé:");
        lblGiaVe.setFont(new Font("Arial", Font.BOLD, 20));  // Giảm cỡ chữ
        JTextField txtGiaVe = new JTextField(20);
        txtGiaVe.setFont(new Font("Arial", Font.PLAIN, 18));
        txtGiaVe.setPreferredSize(new Dimension(250, 35));  // Giảm kích thước JTextField

        JLabel lblTrangThai = new JLabel("Trạng thái:");
        lblTrangThai.setFont(new Font("Arial", Font.BOLD, 20));  // Giảm cỡ chữ
        JTextField txtTrangThai = new JTextField(20);
        txtTrangThai.setFont(new Font("Arial", Font.PLAIN, 18));
        txtTrangThai.setPreferredSize(new Dimension(250, 35));  // Giảm kích thước JTextField

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

        // Calendar đầu tiên
        JTextField txtThoiGianFrom = new JTextField(10);
        txtThoiGianFrom.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panelTimTheoThoiGian.add(txtThoiGianFrom, gbc);

        // Label "đến"
        JLabel lblDen = new JLabel("đến");
        lblDen.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 2;
        gbc.gridy = 1;
        panelTimTheoThoiGian.add(lblDen, gbc);

        // Calendar thứ hai
        JTextField txtThoiGianTo = new JTextField(10);
        txtThoiGianTo.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 3;
        gbc.gridy = 1;
        panelTimTheoThoiGian.add(txtThoiGianTo, gbc);

        // Nút Lọc
        JButton btnLoc = new JButton("Lọc");
        btnLoc.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 4;
        gbc.gridy = 0; // Bắt đầu ở hàng đầu tiên
        gbc.gridheight = 2; // Chiếm cả hai hàng
        gbc.fill = GridBagConstraints.VERTICAL; // Đặt fill theo chiều dọc
        panelTimTheoThoiGian.add(btnLoc, gbc);

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
    private void loadDataToTable() {
        DAO_TraCuuVe daoTraCuuVe = new DAO_TraCuuVe();

        try {
            // Lấy danh sách vé tàu từ DAO
            List<VeTau> danhSachVe = daoTraCuuVe.getAllVeTau();

            // Xóa dữ liệu cũ (nếu có) trong bảng
            tableModel.setRowCount(0);

            // Duyệt qua danh sách vé và thêm vào bảng
            for (VeTau ve : danhSachVe) {
                Object[] row = {
                        ve.getMaVe(),
                        ve.getTenKhachHang(),
                        ve.getGiayTo(),
                        ve.getNgayDi(),
                        ve.getDoiTuong(),
                        ve.getGiaVe()
                };
                tableModel.addRow(row);
            }

            // Đặt font cho bảng
            table.setFont(new Font("Arial", Font.PLAIN, 20)); // Thay đổi kích thước chữ ở đây

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        Frm_TraCuuVe frm = new Frm_TraCuuVe();
        frm.setVisible(true);
    }
}
