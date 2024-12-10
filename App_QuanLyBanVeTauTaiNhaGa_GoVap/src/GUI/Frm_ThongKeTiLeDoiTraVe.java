package GUI;

import DAO.DAO_ThongKeTLDoiTraVe;
import Database.ConnectDatabase;
import com.raven.chart.Chart;
import com.raven.chart.ModelChart;
import com.toedter.calendar.JDateChooser;
import javaswingdev.chart.ModelPieChart;
import javaswingdev.chart.PieChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Frm_ThongKeTiLeDoiTraVe extends JFrame {
    private JPanel contentPanel;
    private JPanel JPanel_Menu;
    private JPanel JPanel_Right = new JPanel(); //Panel chứa nội dung
    private  Color MauXanh = new Color(0,131,66);
    private PieChart pieChart;
    private Chart columnChart;
    // hàm lấy JPanel_Right để gắn sang màn hình chính khi nhấn nút tk tl đổi trả vé
    public Component getTKTLDTV_Panel() {
        return JPanel_Right;
    }

    public Frm_ThongKeTiLeDoiTraVe() {
        // Kết nối database
        ConnectDatabase.getInstance().connect();
        setTitle("Form TK tỉ lệ đổi trả vé");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        //======================================================================//
        // thêm code cho JPanel_Right
        //======================================================================//
        add(JPanel_Menu, BorderLayout.WEST);
        add(JPanel_Right, BorderLayout.CENTER);

        // Tạo JLabel tiêu đề
        JLabel lblTitle = new JLabel("Thống kê tỉ lệ đổi trả vé", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Khoảng cách bên trong

        // Thêm JLabel vào phía trên cùng
        JPanel_Right.setLayout(new BorderLayout());
        JPanel_Right.add(lblTitle, BorderLayout.NORTH);

        // Tạo panel tiêu chí
        JPanel panelTieuChi = new JPanel(new BorderLayout());
        panelTieuChi.add(createCriteriaPanel(), BorderLayout.NORTH);
        panelTieuChi.add(createReportPanel(),BorderLayout.SOUTH);
        // Thêm panel chứa biểu đồ
        JPanel chartPanel = createChartPanel();
        panelTieuChi.add(chartPanel,BorderLayout.CENTER);
        // Thêm panel tiêu chí vào vị trí center
        JPanel_Right.add(panelTieuChi, BorderLayout.CENTER);
    }
    private JPanel createChartPanel() {
        // Tạo biểu đồ hình tròn (Pie Chart)
        pieChart = new PieChart();
        pieChart.setChartType(PieChart.PeiChartType.DONUT_CHART);

        // Tạo biểu đồ cột (Bar Chart)
        columnChart = new Chart(); // Sử dụng Chart từ thư viện bạn đã có

        // Thêm dữ liệu vào biểu đồ
        columnChart.addLegend("Income", new Color(245, 189, 135));  // Ví dụ thêm các thông tin vào biểu đồ cột
        columnChart.addLegend("Expense", new Color(135, 189, 245));
        columnChart.addLegend("Profit", new Color(189, 135, 245));
        columnChart.addLegend("Cost", new Color(139, 229, 222));

        // Cấu hình panel cho biểu đồ
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(pieChart, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(columnChart, BorderLayout.CENTER);

        // Sử dụng JSplitPane để chia
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(600);

        JPanel chartPanel = new JPanel(new BorderLayout());
        chartPanel.add(splitPane, BorderLayout.CENTER);

        return chartPanel;
    }

    private JPanel createCriteriaPanel() {
        // Tạo JPanel chính
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(MauXanh);  // Giữ màu nền cho panel chính

        // Tạo JPanel cho các thành phần input
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(MauXanh); // Giữ màu nền cho inputPanel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các thành phần

        // Tạo các radio button để lựa chọn thống kê theo tháng hoặc theo năm
        JRadioButton radioButtonThang = new JRadioButton("Thống kê theo tháng");
        radioButtonThang.setSelected(true);  // Mặc định chọn thống kê theo tháng
        JRadioButton radioButtonNam = new JRadioButton("Thống kê theo năm");

        // Tạo ButtonGroup để nhóm các radio button lại
        ButtonGroup group = new ButtonGroup();
        group.add(radioButtonThang);
        group.add(radioButtonNam);

        // Tạo JComboBox cho chọn tháng và năm
        JComboBox<String> comboBoxThangTu = new JComboBox<>();
        JComboBox<String> comboBoxThangDen = new JComboBox<>();
        JComboBox<String> comboBoxNamTu = new JComboBox<>();
        JComboBox<String> comboBoxNamDen = new JComboBox<>();

        // Thêm tháng vào ComboBox
        for (int i = 1; i <= 12; i++) {
            comboBoxThangTu.addItem(i < 10 ? "0" + i : String.valueOf(i));
            comboBoxThangDen.addItem(i < 10 ? "0" + i : String.valueOf(i));
        }

        // Thêm năm vào ComboBox
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int year = 2000; year <= currentYear; year++) {
            comboBoxNamTu.addItem(String.valueOf(year));
            comboBoxNamDen.addItem(String.valueOf(year));
        }

        // Tạo nút thống kê
        JButton btnThongKe = new JButton("Thống kê");
        btnThongKe.setFont(new Font("Arial", Font.BOLD, 16)); // Chỉnh font cho Button
        btnThongKe.setPreferredSize(new Dimension(140, 60)); // Tăng kích thước cho Button

        // Cấu hình vị trí các thành phần trong GridBagLayout
        // Hàng 1: Các radio button
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;  // Canh lề trái
        inputPanel.add(radioButtonThang, gbc); // Thêm radioButtonThang vào panel

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(radioButtonNam, gbc); // Thêm radioButtonNam vào panel

        // Hàng 1: Thời gian từ
        gbc.gridx = 1;
        gbc.gridy = 0;
        JLabel lblThoiGianTuThang = new JLabel("Thời gian từ (Tháng):");
        lblThoiGianTuThang.setFont(new Font("Arial", Font.BOLD, 16)); // Đặt font chữ in đậm và lớn
        lblThoiGianTuThang.setForeground(Color.WHITE);  // Đặt màu chữ trắng
        inputPanel.add(lblThoiGianTuThang, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JLabel lblThoiGianTuNam = new JLabel("Thời gian từ (Năm):");
        lblThoiGianTuNam.setFont(new Font("Arial", Font.BOLD, 16)); // Đặt font chữ in đậm và lớn
        lblThoiGianTuNam.setForeground(Color.WHITE);  // Đặt màu chữ trắng
        inputPanel.add(lblThoiGianTuNam, gbc); // Thêm comboBoxThangTu vào panel

        // Hàng 2: Thời gian đến
        gbc.gridx = 2;
        gbc.gridy = 0;
        inputPanel.add(comboBoxThangTu,gbc);
        comboBoxThangTu.setFont(new Font("Arial", Font.BOLD, 16)); // Đặt font cho JComboBox
        comboBoxThangTu.setPreferredSize(new Dimension(120, 40)); // Tăng kích thước JComboBox

        gbc.gridx = 2;
        gbc.gridy = 1;
        inputPanel.add(comboBoxNamTu, gbc); // Thêm comboBoxNamTu vào panel
        comboBoxNamTu.setFont(new Font("Arial", Font.BOLD, 16)); // Đặt font cho JComboBox
        comboBoxNamTu.setPreferredSize(new Dimension(120, 40)); // Tăng kích thước JComboBox


        gbc.gridx = 3;
        gbc.gridy = 0;
        JLabel lblThoiGianDenThang = new JLabel("Thời gian đến (Tháng):");
        lblThoiGianDenThang.setFont(new Font("Arial", Font.BOLD, 16)); // Đặt font chữ in đậm và lớn
        lblThoiGianDenThang.setForeground(Color.WHITE);  // Đặt màu chữ trắng
        inputPanel.add(lblThoiGianDenThang, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        JLabel lblThoiGianDenNam = new JLabel("Thời gian đến (Năm):");
        lblThoiGianDenNam.setFont(new Font("Arial", Font.BOLD, 16)); // Đặt font chữ in đậm và lớn
        lblThoiGianDenNam.setForeground(Color.WHITE);  // Đặt màu chữ trắng
        inputPanel.add(lblThoiGianDenNam, gbc); // Thêm comboBoxNamTu vào panel

        gbc.gridx = 4;
        gbc.gridy = 0;
        inputPanel.add(comboBoxThangDen,gbc);
        comboBoxThangDen.setFont(new Font("Arial", Font.BOLD, 16)); // Đặt font cho JComboBox
        comboBoxThangDen.setPreferredSize(new Dimension(120, 40)); // Tăng kích thước JComboBox

        gbc.gridx = 4;
        gbc.gridy = 1;
        inputPanel.add(comboBoxNamDen, gbc); // Thêm comboBoxNamDen vào panel
        comboBoxNamDen.setFont(new Font("Arial", Font.BOLD, 16)); // Đặt font cho JComboBox
        comboBoxNamDen.setPreferredSize(new Dimension(120, 40)); // Tăng kích thước JComboBox

        // Cột 4: Button "Thống kê"
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridheight = 2; // Chiều cao button bằng 4 hàng
        gbc.fill = GridBagConstraints.VERTICAL;
        inputPanel.add(btnThongKe, gbc);

        // Lắng nghe sự kiện thay đổi lựa chọn giữa Tháng và Năm
        radioButtonThang.addActionListener(e -> {
            comboBoxThangTu.setEnabled(true);
            comboBoxThangDen.setEnabled(true);
            comboBoxNamTu.setEnabled(false);
            comboBoxNamDen.setEnabled(false);
        });

        radioButtonNam.addActionListener(e -> {
            comboBoxThangTu.setEnabled(false);
            comboBoxThangDen.setEnabled(false);
            comboBoxNamTu.setEnabled(true);
            comboBoxNamDen.setEnabled(true);
        });

        // Thêm inputPanel vào panel chính
        panel.add(inputPanel, BorderLayout.CENTER);

        return panel;
    }











    private JPanel createReportPanel() {
        // Tạo JPanel chính với BorderLayout
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tạo JPanel cho các ô màu và label giải thích
        JPanel leftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần

        // Tạo các ô vuông màu sắc và label giải thích bên phải
        JPanel whiteBox = createColorBox(Color.WHITE);
        JLabel lblWhite = new JLabel("Số lượng vé đã bán");
        lblWhite.setForeground(Color.BLACK);
        lblWhite.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel yellowBox = createColorBox(Color.YELLOW);
        JLabel lblYellow = new JLabel("Số lượng vé đã đổi");
        lblYellow.setForeground(Color.BLACK);
        lblYellow.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel redBox = createColorBox(Color.RED);
        JLabel lblRed = new JLabel("Số lượng vé đã trả");
        lblRed.setForeground(Color.BLACK);
        lblRed.setFont(new Font("Arial", Font.PLAIN, 16));

        // Cấu hình vị trí các thành phần
        gbc.gridx = 0;
        gbc.gridy = 0;
        leftPanel.add(whiteBox, gbc);

        gbc.gridx = 1;
        leftPanel.add(lblWhite, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        leftPanel.add(yellowBox, gbc);

        gbc.gridx = 1;
        leftPanel.add(lblYellow, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        leftPanel.add(redBox, gbc);

        gbc.gridx = 1;
        leftPanel.add(lblRed, gbc);

        // Tạo nút "Tạo báo cáo"
        JButton btnReport = new JButton("Tạo báo cáo");
        btnReport.setFont(new Font("Arial", Font.BOLD, 16));
        btnReport.setPreferredSize(new Dimension(150, 40));

        // Thêm khoảng cách dưới nút "Tạo báo cáo"
        gbc.gridx = 0;
        gbc.gridy = 4; // Di chuyển nút xuống dưới, cách xa ô màu cuối cùng
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 5, 5, 5); // Tăng khoảng cách trên nút
        leftPanel.add(btnReport, gbc);

        // Tạo JPanel bên phải cho bảng
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(MauXanh);

        // Tạo bảng với 3 cột: Số vé, Trạng thái, Lý do
        String[] columns = {"Số vé", "Trạng thái", "Lý do"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        // Tùy chỉnh tiêu đề bảng
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 25)); // Font chữ in đậm, kích thước 16
        header.setBackground(MauXanh); // Nền màu xanh

        header.setForeground(Color.WHITE); // Chữ màu trắng
        header.setOpaque(true);

        JScrollPane scrollPane = new JScrollPane(table);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        // Thêm leftPanel và rightPanel vào panel chính
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(300);  // Điều chỉnh vị trí chia tách
        panel.add(splitPane, BorderLayout.CENTER);

        return panel;
    }


    // Phương thức tạo ô vuông có màu
    private JPanel createColorBox(Color color) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setPreferredSize(new Dimension(50, 50)); // Kích thước ô vuông
        return panel;
    }





    public static void main(String[] args) {
        Frm_ThongKeTiLeDoiTraVe frm = new Frm_ThongKeTiLeDoiTraVe();
        frm.setVisible(true);
    }
}
