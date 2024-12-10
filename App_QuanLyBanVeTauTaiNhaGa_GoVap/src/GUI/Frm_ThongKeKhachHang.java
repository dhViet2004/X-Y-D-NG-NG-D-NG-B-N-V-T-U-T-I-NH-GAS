package GUI;

import DAO.DAO_HoaDon;
import Entity.RoundedButton;
import com.raven.chart.Chart;
import com.raven.chart.ModelChart;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Frm_ThongKeKhachHang extends JFrame implements ActionListener {


    private JPanel JPanel_Main;
    private JPanel JPanel_Menu;
    private final Color color_white_text;
    private final Font font_text;
    private final Font font_text_2;
    Color colorXanhDam = new Color(0, 131, 66);
    private JPanel panelHeader;
    private Box header;
    private JPanel header_1;
    private JLabel lblKhachHang;
    private JPanel header_2;
    private JPanel header_3;
    private final JComboBox<String> select_day;
    private final Box box_header_3;

    private JPanel body;
    private Box body_header;
    private final JPanel pnl_two_button;
    private final JButton btn_tongQuan;
    private final JButton btn_chiTiet;
    private final JPanel pnl_two_lbl;
    private final JLabel lbl_1;
    private final JLabel lbl_2;
    private JPanel body_chart;

    public Component getTKKHPanel() {
        return JPanel_Main;
    }

    public Frm_ThongKeKhachHang() {
        setTitle("Form Khuyến Mãi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(JPanel_Menu, BorderLayout.WEST);
        add(JPanel_Main, BorderLayout.CENTER);
        font_text = new Font("Arial", Font.PLAIN, 25);
        font_text_2 = new Font("Arial", Font.PLAIN, 15);
        // HEADER
        header = Box.createVerticalBox();
        JPanel_Main.add(header, BorderLayout.NORTH);

        // Dòng 1
        header_1 = new JPanel(new BorderLayout());  // Sử dụng BorderLayout cho header_1
        header_1.setBackground(colorXanhDam);
        header_1.setBorder(new LineBorder(Color.WHITE, 2));  // Thêm viền màu trắng dày 2px

        lblKhachHang = new JLabel("Khách hàng");
        color_white_text = Color.WHITE;
        lblKhachHang.setForeground(color_white_text);
        lblKhachHang.setFont(font_text);
        lblKhachHang.setBorder(new EmptyBorder(10, 5, 10, 5));

        // Đưa lblKhachHang vào phía trái của header_1
        header_1.add(lblKhachHang, BorderLayout.WEST);
        header.add(header_1);
        //dòng 2
        header_2 = new JPanel();
        header_2.setBackground(color_white_text);
//        header.add(header_2);
        // Dòng 3
        header_3 = new JPanel();
        header_3.setBorder(new LineBorder(Color.WHITE, 2));  // Thêm viền màu trắng dày 2px
        box_header_3 = Box.createHorizontalBox();
        header_3.setLayout(new FlowLayout(FlowLayout.LEFT)); // Đặt layout là FlowLayout với căn trái
        header_3.setBackground(colorXanhDam);

        // Tạo icon từ hình ảnh
        ImageIcon iconTime = new ImageIcon(getClass().getResource("/Anh_HeThong/clock.png"));
        Image imgTime = iconTime.getImage();
        Image scaleImgTime = imgTime.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon iconLocNew = new ImageIcon(scaleImgTime);

        // Tạo JLabel chứa icon và thêm vào header_3 trước select_day
        JLabel lblIconTime = new JLabel(iconLocNew);
        header_3.add(lblIconTime);  // Thêm icon trước

        // Tạo JComboBox và thêm vào header_3 sau icon
        select_day = new JComboBox<>();
        select_day.setBackground(color_white_text);
        select_day.setFont(font_text_2);
        select_day.addItem("Hôm qua");
        select_day.addItem("Hôm nay");
        select_day.addItem("Tuần này");
        select_day.addItem("Tháng này");
        select_day.addItem("Tháng trước");
        select_day.addItem("Năm nay");
        select_day.addItem("Năm trước");
        header_3.add(select_day);
        header.add(header_3);


        //BODY
        body = new JPanel();
        body.setLayout(new BorderLayout());
        body.setBackground(color_white_text);
        JPanel_Main.add(body, BorderLayout.CENTER);

        //body_header
        body_header = Box.createVerticalBox();
        body.add(body_header, BorderLayout.NORTH);
        body_header.add(Box.createVerticalStrut(30));
        btn_tongQuan = new RoundedButton("Tổng quan");
        btn_chiTiet = new RoundedButton("Chi tiết");
        btn_chiTiet.setOpaque(true);
        btn_chiTiet.setBackground(Color.gray);
        btn_chiTiet.setForeground(color_white_text);
        btn_chiTiet.setBorder(new EmptyBorder(6, 30, 6, 30));
        btn_chiTiet.setFont(font_text_2);
        btn_tongQuan.setOpaque(true);
        btn_tongQuan.setBackground(Color.gray);
        btn_tongQuan.setForeground(color_white_text);
        btn_tongQuan.setBorder(new EmptyBorder(6, 15, 6, 15));
        btn_tongQuan.setFont(font_text_2);

        pnl_two_button = new JPanel();
        pnl_two_button.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnl_two_button.setBackground(color_white_text);
        pnl_two_button.add(btn_tongQuan);
        pnl_two_button.add(btn_chiTiet);
        body_header.add(pnl_two_button);


        body_header.add(Box.createVerticalStrut(30));

        pnl_two_lbl = new JPanel();
        pnl_two_lbl.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnl_two_lbl.setBackground(color_white_text);
        // Tạo icon từ hình ảnh cho lbl_1
        ImageIcon iconCustomer = new ImageIcon(getClass().getResource("/Anh_HeThong/customer.png"));
        Image imgCustomer = iconCustomer.getImage();
        Image scaledCustomer = imgCustomer.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledIconCustomer = new ImageIcon(scaledCustomer);

        // Tạo JLabel cho "Số lượng khách" với icon bên trái
        lbl_1 = new JLabel("               ");
        lbl_1.setPreferredSize(new Dimension(200, 50)); // Đặt kích thước cố định cho lbl_1
        lbl_1.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_1.setBorder(new LineBorder(Color.BLACK, 2));
        lbl_1.setIcon(scaledIconCustomer);
        lbl_1.setHorizontalTextPosition(JLabel.RIGHT); // Đặt icon bên trái text
        lbl_1.setIconTextGap(10); // Khoảng cách giữa text và icon
        pnl_two_lbl.add(lbl_1);

        // Tạo icon từ hình ảnh cho lbl_2
        ImageIcon iconInvoice = new ImageIcon(getClass().getResource("/Anh_HeThong/invoice.png"));
        Image imgInvoice = iconInvoice.getImage();
        Image scaledInvoice = imgInvoice.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scaleIconInvoice = new ImageIcon(scaledInvoice);

        // Tạo JLabel cho "Số lượng hóa đơn" với icon bên trái
        lbl_2 = new JLabel("               ");
        lbl_2.setPreferredSize(new Dimension(200, 50)); // Đặt kích thước cố định cho lbl_2
        lbl_2.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_2.setBorder(new LineBorder(Color.BLACK, 2));
        lbl_2.setIcon(scaleIconInvoice);
        lbl_2.setHorizontalTextPosition(JLabel.RIGHT); // Đặt icon bên trái text
        lbl_2.setIconTextGap(10); // Khoảng cách giữa text và icon
        lbl_2.setAlignmentX(Component.LEFT_ALIGNMENT); // Đặt căn lề sát trái
        pnl_two_lbl.add(lbl_2);
        body_header.add(pnl_two_lbl);

        //body_chart
        body_chart = new JPanel(new BorderLayout());

        body.add(body_chart, BorderLayout.CENTER);


        // đăng kí sự kiện
        select_day.addActionListener(this);
        btn_tongQuan.addActionListener(this);
        btn_chiTiet.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        String day = select_day.getSelectedItem().toString();  // Lấy giá trị được chọn trong JComboBox
        int year = LocalDate.now().getYear();                  // Năm hiện tại để làm mặc định
        LocalDate currentDate = LocalDate.now(); // Ngày hiện tại
        LocalDate yesterday = currentDate.minusDays(1); // Ngày hôm qua
        LocalDate startOfWeek = currentDate.with(DayOfWeek.MONDAY); // Bắt đầu tuần này
        LocalDate endOfWeek = currentDate.with(DayOfWeek.SUNDAY); // Kết thúc tuần này
        LocalDate startOfMonth = currentDate.withDayOfMonth(1); // Ngày đầu tháng
        LocalDate endOfMonth = currentDate.withDayOfMonth(currentDate.lengthOfMonth()); // Ngày cuối tháng


        try {
            if (o == btn_tongQuan) {
                switch (day) {
                    case "Hôm nay": {
                        List<Object[]> list;
                        list = dao.SLKH_TODAY_TOTAL(currentDate);
                        if(list!=null){
                            System.out.println(list.size());
                        }
                        updateChartPanel_Day_TongQuan(list);
                        break;
                    }
                    case "Hôm qua": {
                        List<Object[]> list;
                        list = dao.SLKH_TODAY_TOTAL(currentDate.minusDays(1));
                        updateChartPanel_Day_TongQuan(list);
                        break;
                    }
                    case "Tuần này": {
                        List<Object[]> list;
                        list = dao.SLKH_WEEK_TOTAL(currentDate);
                        updateChartPanel_Week_TongQuan(list);
                        break;
                    }
                    case "Tháng này": {
                        LocalDate thang = LocalDate.now().withDayOfMonth(1); // Ngày đầu tháng
//                        LocalDate thang = LocalDate.of(LocalDate.now().getYear(), 10, 1); // Ngày đầu tháng
                        List<Object[]> list;
                        try {
                            list = dao.SLKH_MONTH_TOTAL(thang);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        updateChartPanel_Month_TongQuan(list);
                        break;
                    }
                    case "Tháng trước": {
                        LocalDate thang = LocalDate.now().minusMonths(1).withDayOfMonth(1); // Ngày đầu tháng
                        List<Object[]> list;
                        try {
                            list = dao.SLKH_MONTH_TOTAL(thang);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        updateChartPanel_Month_TongQuan(list);
                        break;
                    }
                    case "Năm nay": {
                        List<Object[]> list;
                        list = dao.SLKH_MONTH_YEAR_TOTAL(year);
                        if (list != null) {
                            System.out.println(list.size());
                        }
                        updateChartPanel_Year_TongQuan(list);
                        break;
                    }
                    case "Năm trước": {
                        List<Object[]> list;
                        list = dao.SLKH_MONTH_YEAR_TOTAL(year - 1);
                        updateChartPanel_Year_TongQuan(list);
                        break;
                    }
                }
            } else if (o == btn_chiTiet) {
                switch (day) {
                    case "Hôm nay": {
                        List<Object[]> list;
                        list = dao.SLKH_TODAY_DETAIL(currentDate);
                        updateChartPanel_Day_ChiTiet(list);
                        break;
                    }
                    case "Hôm qua": {
                        List<Object[]> list;
                        list = dao.SLKH_TODAY_DETAIL(currentDate.minusDays(1));
                        updateChartPanel_Day_ChiTiet(list);
                        break;
                    }
                    case "Tuần này": {
                        List<Object[]> list;
                        list = dao.SLKH_WEEK_DETAIL(currentDate);
                        updateChartPanel_Week_ChiTiet(list);
                        break;
                    }
                    case "Tháng này": {
                        LocalDate thang = LocalDate.now().withDayOfMonth(1); // Ngày đầu tháng
//                        LocalDate thang = LocalDate.of(LocalDate.now().getYear(), 10, 1); // Ngày đầu tháng
                        List<Object[]> list;
                        try {
                            list = dao.SLKH_MONTH_DETAIL(thang);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        updateChartPanel_Month_ChiTiet(list);
                        break;
                    }
                    case "Tháng trước": {
                        LocalDate thang = LocalDate.now().minusMonths(1).withDayOfMonth(1); // Ngày đầu tháng
//                        LocalDate thang = LocalDate.of(LocalDate.now().getYear(), 10, 1); // Ngày đầu tháng
                        List<Object[]> list;
                        try {
                            list = dao.SLKH_MONTH_DETAIL(thang);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        updateChartPanel_Month_ChiTiet(list);
                        break;
                    }
                    case "Năm nay": {
                        List<Object[]> list;
                        list = dao.SLKH_MONTH_YEAR_DETAIL(year);
                        updateChartPanel_Year_ChiTiet(list);
                        break;
                    }
                    case "Năm trước": {
                        List<Object[]> list;
                        list = dao.SLKH_MONTH_YEAR_DETAIL(year - 1);
                        updateChartPanel_Year_ChiTiet(list);
                        break;
                    }
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void updateChartPanel_Year_ChiTiet(List<Object[]> list) {
        // Xóa bỏ các thành phần cũ trong panel (nếu cần)
        body_chart.removeAll();

        // Tạo một đối tượng Chart mới
        Chart chart = new Chart();

        int sum_customer = 0;
        int sum_invoice = 0;

        // Thêm các biểu tượng legend cho chart
        chart.addLegend("Số lượng hóa đơn", new Color(245, 189, 135));  // Màu cho hóa đơn
        chart.addLegend("Số lượng khách hàng", new Color(135, 189, 245));  // Màu cho khách hàng

        // Tạo mảng để lưu số lượng khách hàng và hóa đơn cho mỗi tháng
        int[] customerCounts = new int[12]; // Có 12 tháng
        int[] invoiceCounts = new int[12]; // Có 12 tháng

        // Thêm dữ liệu từ list với nhãn tháng đúng
        for (Object[] row : list) {
            String monthString = (String) row[0]; // row[0] là tên tháng
            // Lấy số tháng từ chuỗi "Tháng x"
            int monthIndex = Integer.parseInt(monthString.replace("Tháng ", "")) - 1; // Chuyển đổi sang chỉ số mảng
            int customerCount = (int) row[1];    // row[1] là số lượng khách hàng
            int invoiceCount = (int) row[2];      // row[2] là số lượng hóa đơn

            customerCounts[monthIndex] = customerCount; // Gán số lượng khách hàng cho tháng
            invoiceCounts[monthIndex] = invoiceCount;   // Gán số lượng hóa đơn cho tháng
        }

        // Thêm dữ liệu vào biểu đồ
        for (int i = 0; i < customerCounts.length; i++) {
            // Nhãn tháng
            String monthLabel = "Tháng " + (i + 1);

            // Thêm dữ liệu vào chart
            chart.addData(new ModelChart(monthLabel, new double[]{invoiceCounts[i], customerCounts[i]}));

            // Cộng dồn tổng số lượng khách hàng và hóa đơn
            sum_customer += customerCounts[i];
            sum_invoice += invoiceCounts[i];
        }

        // Hiển thị tổng số lượng khách hàng và hóa đơn
        lbl_1.setText(": " + sum_customer + " khách hàng");
        lbl_2.setText(": " + sum_invoice + " hóa đơn");

        // Thêm chart vào panel hoặc container
        body_chart.add(chart);

        // Cập nhật giao diện (revalidate và repaint)
        body_chart.revalidate();
        body_chart.repaint();

        // Bắt đầu animation nếu cần
        chart.start();
    }

    private void updateChartPanel_Year_TongQuan(List<Object[]> list) {
        // Xóa bỏ các thành phần cũ trong panel (nếu cần)
        body_chart.removeAll();

        // Tạo một đối tượng Chart mới
        Chart chart = new Chart();

        int sum_customer = 0;
        int sum_invoice = 0;

        // Thêm các biểu tượng legend cho chart
        chart.addLegend("Số lượng hóa đơn", new Color(245, 189, 135));  // Màu cho hóa đơn
        chart.addLegend("Số lượng khách hàng", new Color(135, 189, 245));  // Màu cho khách hàng

        // Tạo mảng để lưu số lượng khách hàng và hóa đơn cho mỗi tháng
        int[] customerCounts = new int[12]; // Có 12 tháng
        int[] invoiceCounts = new int[12]; // Có 12 tháng

        // Thêm dữ liệu từ list với nhãn tháng đúng
        for (Object[] row : list) {
            String monthString = (String) row[0]; // row[0] là tên tháng
            // Lấy số tháng từ chuỗi "Tháng x"
            int monthIndex = Integer.parseInt(monthString.replace("Tháng ", "")) - 1; // Chuyển đổi sang chỉ số mảng
            int customerCount = (int) row[1];    // row[1] là số lượng khách hàng
            // row[2] là số lượng hóa đơn

            customerCounts[monthIndex] = customerCount; // Gán số lượng khách hàng cho tháng
        }

        // Thêm dữ liệu vào biểu đồ
        for (int i = 0; i < customerCounts.length; i++) {
            // Nhãn tháng
            String monthLabel = "Tháng " + (i + 1);

            // Thêm dữ liệu vào chart
            chart.addData(new ModelChart(monthLabel, new double[]{0, customerCounts[i]}));

            // Cộng dồn tổng số lượng khách hàng và hóa đơn
            sum_customer += customerCounts[i];
        }

        // Hiển thị tổng số lượng khách hàng và hóa đơn
        lbl_1.setText(": " + sum_customer + " khách hàng");

        // Thêm chart vào panel hoặc container
        body_chart.add(chart);

        // Cập nhật giao diện (revalidate và repaint)
        body_chart.revalidate();
        body_chart.repaint();

        // Bắt đầu animation nếu cần
        chart.start();
    }

    private void updateChartPanel_Week_TongQuan(List<Object[]> list) {
        // Xóa bỏ các thành phần cũ trong panel (nếu cần)
        body_chart.removeAll();

        // Tạo một đối tượng Chart mới
        Chart chart = new Chart();

        // Khởi tạo biến tổng số lượng khách hàng
        int sum_customer = 0;

        // Thêm các biểu tượng legend cho chart
        chart.addLegend("Số lượng hóa đơn", new Color(245, 189, 135));
        chart.addLegend("Số lượng khách hàng", new Color(135, 189, 245));

        for (Object[] row : list) {
            String ngay = "" + row[0];
            int customerCount = (int) row[1];
            sum_customer += customerCount;
            chart.addData(new ModelChart(ngay, new double[]{0, customerCount}));
        }
        // Hiển thị tổng số lượng khách hàng
        lbl_1.setText(": " + sum_customer + " khách hàng");

        // Thêm chart vào panel hoặc container
        body_chart.add(chart);

        // Cập nhật giao diện (revalidate và repaint)
        body_chart.revalidate();
        body_chart.repaint();

        // Bắt đầu animation nếu cần
        chart.start();
    }

    private void updateChartPanel_Week_ChiTiet(List<Object[]> list) {
        // Xóa bỏ các thành phần cũ trong panel (nếu cần)
        body_chart.removeAll();

        // Tạo một đối tượng Chart mới
        Chart chart = new Chart();

        // Khởi tạo biến tổng số lượng khách hàng
        int sum_customer = 0;
        int sum_invoice = 0;

        // Thêm các biểu tượng legend cho chart
        chart.addLegend("Số lượng hóa đơn", new Color(245, 189, 135));
        chart.addLegend("Số lượng khách hàng", new Color(135, 189, 245));

        for (Object[] row : list) {
            String ngay = "" + row[0];
            int customerCount = (int) row[1];
            int invoiceCount = (int) row[2];
            sum_customer += customerCount;
            sum_invoice += invoiceCount;
            chart.addData(new ModelChart(ngay, new double[]{invoiceCount, customerCount}));
        }
        // Hiển thị tổng số lượng khách hàng
        lbl_1.setText(": " + sum_customer + " khách hàng");
        lbl_2.setText(": " + sum_invoice + " hóa đơn");

        // Thêm chart vào panel hoặc container
        body_chart.add(chart);

        // Cập nhật giao diện (revalidate và repaint)
        body_chart.revalidate();
        body_chart.repaint();

        // Bắt đầu animation nếu cần
        chart.start();
    }

    private void updateChartPanel_Day_TongQuan(List<Object[]> list) {
        // Xóa bỏ các thành phần cũ trong panel (nếu cần)
        body_chart.removeAll();

        // Tạo một đối tượng Chart mới
        Chart chart = new Chart();

        // Khởi tạo biến tổng số lượng khách hàng
        int sum_customer = 0;

        // Thêm các biểu tượng legend cho chart
        chart.addLegend("Số lượng hóa đơn", new Color(245, 189, 135));
        chart.addLegend("Số lượng khách hàng", new Color(135, 189, 245));

        // Lặp qua danh sách dữ liệu
        for (Object[] row : list) {
            // Giả sử row[0] chứa dữ liệu kiểu LocalDate, nếu không thì cần điều chỉnh lại
            LocalDate ngay = (LocalDate) row[0];

            // Định dạng ngày chỉ lấy ngày và tháng (DD-MM)
            String formattedDate = ngay.format(DateTimeFormatter.ofPattern("dd-MM"));

            int customerCount = (int) row[1];  // Số lượng khách hàng

            // Cộng dồn số lượng khách hàng
            sum_customer += customerCount;

            // Thêm dữ liệu vào chart: ngày và số lượng khách hàng (SLKH)
            // Lấy ngày đã định dạng (ngày-tháng) từ đối tượng `ngay`, và số lượng khách hàng từ `customerCount`
            chart.addData(new ModelChart(formattedDate, new double[]{0, customerCount})); // 0 là placeholder cho SLHD
        }

        // Hiển thị tổng số lượng khách hàng
        lbl_1.setText(": " + sum_customer + " khách hàng");

        // Thêm chart vào panel hoặc container
        body_chart.add(chart);

        // Cập nhật giao diện (revalidate và repaint)
        body_chart.revalidate();
        body_chart.repaint();

        // Bắt đầu animation nếu cần
        chart.start();
    }

    public void updateChartPanel_Day_ChiTiet(List<Object[]> list) {
        // Xóa bỏ các thành phần cũ trong panel (nếu cần)
        body_chart.removeAll();

        // Tạo một đối tượng Chart mới
        Chart chart = new Chart();

        // Khởi tạo biến tổng số lượng khách hàng và hóa đơn
        int sum_customer = 0;
        int sum_invoice = 0;

        // Thêm các biểu tượng legend cho chart
        chart.addLegend("Số lượng hóa đơn", new Color(245, 189, 135));  // Màu cho SLHD
        chart.addLegend("Số lượng khách hàng", new Color(135, 189, 245));  // Màu cho SLKH

        // Lặp qua danh sách dữ liệu
        for (Object[] row : list) {
            // Giả sử row[0] chứa dữ liệu kiểu LocalDate
            LocalDate ngay = (LocalDate) row[0];

            // Định dạng ngày chỉ lấy ngày và tháng (DD-MM)
            String formattedDate = ngay.format(DateTimeFormatter.ofPattern("dd-MM"));

            int customerCount = (int) row[1];  // Số lượng khách hàng
            int invoiceCount = (int) row[2];   // Số lượng hóa đơn

            // Cộng dồn số lượng khách hàng và hóa đơn
            sum_customer += customerCount;
            sum_invoice += invoiceCount;

            // Thêm dữ liệu vào chart: ngày và số lượng khách hàng, số lượng hóa đơn
            chart.addData(new ModelChart(formattedDate, new double[]{invoiceCount, customerCount}));
        }

        lbl_1.setText(": " + sum_customer + " khách hàng");
        lbl_2.setText(": " + sum_invoice + " hóa đơn");

        // Thêm chart vào panel hoặc container
        body_chart.add(chart);

        // Cập nhật giao diện (revalidate và repaint)
        body_chart.revalidate();
        body_chart.repaint();

        // Bắt đầu animation nếu cần
        chart.start();
    }

    public void updateChartPanel_Month_TongQuan(List<Object[]> list) {
        // Xóa bỏ các thành phần cũ trong panel (nếu cần)
        body_chart.removeAll();

        // Tạo một đối tượng Chart mới
        Chart chart = new Chart();

        // Khởi tạo biến tổng số lượng khách hàng
        int sum_customer = 0;

        // Thêm các biểu tượng legend cho chart
        chart.addLegend("Số lượng hóa đơn", new Color(245, 189, 135));
        chart.addLegend("Số lượng khách hàng", new Color(135, 189, 245));

        // Lặp qua danh sách dữ liệu
        for (Object[] row : list) {
            // Giả sử row[0] chứa dữ liệu kiểu LocalDate, nếu không thì cần điều chỉnh lại
            LocalDate ngay = (LocalDate) row[0];

            // Định dạng ngày chỉ lấy ngày và tháng (DD-MM)
            String formattedDate = ngay.format(DateTimeFormatter.ofPattern("dd-MM"));

            int customerCount = (int) row[1];  // Số lượng khách hàng

            // Cộng dồn số lượng khách hàng
            sum_customer += customerCount;

            // Thêm dữ liệu vào chart: ngày và số lượng khách hàng (SLKH)
            // Lấy ngày đã định dạng (ngày-tháng) từ đối tượng `ngay`, và số lượng khách hàng từ `customerCount`
            chart.addData(new ModelChart(formattedDate, new double[]{0, customerCount})); // 0 là placeholder cho SLHD
        }

        // Hiển thị tổng số lượng khách hàng
        lbl_1.setText(": " + sum_customer + " khách hàng");

        // Thêm chart vào panel hoặc container
        body_chart.add(chart);

        // Cập nhật giao diện (revalidate và repaint)
        body_chart.revalidate();
        body_chart.repaint();

        // Bắt đầu animation nếu cần
        chart.start();
    }

    public void updateChartPanel_Month_ChiTiet(List<Object[]> list) {
        // Xóa bỏ các thành phần cũ trong panel (nếu cần)
        body_chart.removeAll();

        // Tạo một đối tượng Chart mới
        Chart chart = new Chart();

        // Khởi tạo biến tổng số lượng khách hàng và hóa đơn
        int sum_customer = 0;
        int sum_invoice = 0;

        // Thêm các biểu tượng legend cho chart
        chart.addLegend("Số lượng hóa đơn", new Color(245, 189, 135));  // Màu cho SLHD
        chart.addLegend("Số lượng khách hàng", new Color(135, 189, 245));  // Màu cho SLKH

        // Lặp qua danh sách dữ liệu
        for (Object[] row : list) {
            // Giả sử row[0] chứa dữ liệu kiểu LocalDate
            LocalDate ngay = (LocalDate) row[0];

            // Định dạng ngày chỉ lấy ngày và tháng (DD-MM)
            String formattedDate = ngay.format(DateTimeFormatter.ofPattern("dd-MM"));

            int customerCount = (int) row[1];  // Số lượng khách hàng
            int invoiceCount = (int) row[2];   // Số lượng hóa đơn

            // Cộng dồn số lượng khách hàng và hóa đơn
            sum_customer += customerCount;
            sum_invoice += invoiceCount;

            // Thêm dữ liệu vào chart: ngày và số lượng khách hàng, số lượng hóa đơn
            chart.addData(new ModelChart(formattedDate, new double[]{invoiceCount, customerCount}));
        }

        lbl_1.setText(": " + sum_customer + " khách hàng");
        lbl_2.setText(": " + sum_invoice + " hóa đơn");

        // Thêm chart vào panel hoặc container
        body_chart.add(chart);

        // Cập nhật giao diện (revalidate và repaint)
        body_chart.revalidate();
        body_chart.repaint();

        // Bắt đầu animation nếu cần
        chart.start();
    }


    private com.raven.chart.Chart chart2 = new Chart();
    DAO_HoaDon dao = new DAO_HoaDon();

    public static void main(String[] args) {
        Frm_ThongKeKhachHang frm = new Frm_ThongKeKhachHang();
        frm.setVisible(true);
        LocalDate thang = LocalDate.now().withDayOfMonth(1); // Ngày đầu tháng
        System.out.println(thang);
    }


}
