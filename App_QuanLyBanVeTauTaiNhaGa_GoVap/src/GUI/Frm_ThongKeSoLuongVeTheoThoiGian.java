package GUI;

import DAO.DAO_HoaDon;
import Entity.RoundedButton;
import com.toedter.calendar.JDateChooser;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Map;

public class Frm_ThongKeSoLuongVeTheoThoiGian extends JFrame implements ActionListener {
    private JPanel contentPanel;
    private JPanel JPanel_Menu;
    private JPanel JPanel_Right = new JPanel(); // Panel chứa nội dung
    private final Color color_white_text = Color.white;
    Color colorXanhDam = new Color(0, 131, 66);
    private final Font font_text;
    private final Font font_text_2;
    private final Font font_text_3;
    private JPanel JPnlChart = new JPanel();
    private Box Chart;
    private JLabel lblThongTin; // Label để hiển thị thông tin max/min

    //------------------//
    private Box Box_Condition;
    private Box Box_Condition1;
    private JLabel lbl_thoiGianDi;
    private static JDateChooser dateChooser_TgDi;
    private JLabel lbl_thoiGianDen;
    private static JDateChooser dateChooser_TgDen;
    private JButton thongKe;
    DAO_HoaDon dao;
    private Date maxDate;
    private Date minDate;
    private int maxTickets;
    private int minTickets;
    public  Component getTKSLV(){
        return JPanel_Right;
    }
    public Frm_ThongKeSoLuongVeTheoThoiGian() {
        setTitle("Form TK số lượng vé");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        font_text = new Font("Arial", Font.BOLD, 30);
        font_text_2 = new Font("Arial", Font.BOLD, 20);
        font_text_3 = new Font("Arial", Font.BOLD, 15);
        JPanel_Right.setLayout(new BorderLayout());
        JPanel_Right.setBackground(color_white_text);

        JLabel lblKhachHang = new JLabel("Thống kê số lượng vé", SwingConstants.CENTER);
        lblKhachHang.setFont(font_text);
        JPanel_Right.add(lblKhachHang, BorderLayout.NORTH);

        JPnlChart.setLayout(new BorderLayout());
        JPnlChart.setBackground(color_white_text);
        JPanel_Right.add(JPnlChart, BorderLayout.CENTER);

        Box_Condition = Box.createVerticalBox();

        //----------------// Box condition1
        Box_Condition1 = Box.createHorizontalBox();
        lbl_thoiGianDi = new JLabel("Thời gian đi");
        lbl_thoiGianDi.setFont(font_text_2);
        lbl_thoiGianDen = new JLabel("Thời gian đến");
        lbl_thoiGianDen.setFont(font_text_2);
        dateChooser_TgDi = new JDateChooser();
        dateChooser_TgDen = new JDateChooser();
        thongKe = new RoundedButton("Thống kê");
        thongKe.setFont(font_text_2);
        thongKe.setBackground(Color.green);
        thongKe.setForeground(Color.red);
        Box_Condition1.add(Box.createRigidArea(new Dimension(30,5)));
        Box_Condition1.add(lbl_thoiGianDi);
        Box_Condition1.add(Box.createRigidArea(new Dimension(30, 5)));
        Box_Condition1.add(dateChooser_TgDi);
        Box_Condition1.add(Box.createRigidArea(new Dimension(70, 5)));
        Box_Condition1.add(lbl_thoiGianDen);
        Box_Condition1.add(Box.createRigidArea(new Dimension(30, 5)));
        Box_Condition1.add(dateChooser_TgDen);
        Box_Condition1.add(Box.createRigidArea(new Dimension(70, 5)));
        Box_Condition1.add(thongKe);
        Box_Condition.add((Box.createRigidArea(new Dimension(30,50))));
        Box_Condition.add(Box_Condition1);
        Box_Condition.add((Box.createRigidArea(new Dimension(30,50))));

        JPnlChart.add(Box_Condition, BorderLayout.NORTH);

        Chart = Box.createVerticalBox();
        JPnlChart.add(Chart, BorderLayout.CENTER);

        lblThongTin = new JLabel("Thông tin vé sẽ hiển thị tại đây.", SwingConstants.CENTER);
        lblThongTin.setFont(font_text_3);
        lblThongTin.setForeground(Color.BLUE);
        JPnlChart.add(lblThongTin, BorderLayout.SOUTH);

        add(JPanel_Menu, BorderLayout.WEST);
        add(JPanel_Right, BorderLayout.CENTER);

        thongKe.addActionListener(this);
        dao = new DAO_HoaDon();
    }

    private void updateChartPanel(ChartPanel chart) {
        Chart.removeAll();
        Chart.add(chart);
        Chart.add(Box.createRigidArea(new Dimension(10, 50)));
        Chart.revalidate();
        Chart.repaint();
    }

    public ChartPanel LineChart(Timestamp begin, Timestamp end) throws SQLException {
        DefaultCategoryDataset dataset = createLineDataset(begin, end);

        JFreeChart chart = ChartFactory.createLineChart(
                "",
                "Ngày",
                "Số lượng vé",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        Plot plot = chart.getPlot();
        if (plot instanceof CategoryPlot) {
            CategoryPlot categoryPlot = (CategoryPlot) plot;
            ValueAxis valueAxis = categoryPlot.getRangeAxis();

            if (valueAxis instanceof NumberAxis) {
                NumberAxis numberAxis = (NumberAxis) valueAxis;
                numberAxis.setNumberFormatOverride(NumberFormat.getIntegerInstance());
                numberAxis.setTickUnit(new NumberTickUnit(1));
            }
        }

        return new ChartPanel(chart);
    }

    private DefaultCategoryDataset createLineDataset(Timestamp begin, Timestamp end) throws SQLException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<java.sql.Date, Integer> ticketsByDate = dao.getTicketsByDateRange(begin, end);

        if (ticketsByDate.isEmpty()) {
            return dataset;
        }

        java.sql.Date maxDate = null, minDate = null;
        int maxTickets = Integer.MIN_VALUE, minTickets = Integer.MAX_VALUE;

        for (Map.Entry<java.sql.Date, Integer> entry : ticketsByDate.entrySet()) {
            java.sql.Date date = entry.getKey();
            int tickets = entry.getValue();

            dataset.addValue(tickets, "Số lượng vé", date);

            if (tickets > maxTickets) {
                maxTickets = tickets;
                maxDate = date;
            }
            if (tickets < minTickets) {
                minTickets = tickets;
                minDate = date;
            }
        }

        this.maxDate = maxDate;
        this.maxTickets = maxTickets;
        this.minDate = minDate;
        this.minTickets = minTickets;

        return dataset;
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == thongKe) {
            Date start = dateChooser_TgDi.getDate();
            Date end = dateChooser_TgDen.getDate();
            Timestamp startTimestamp = new Timestamp(start.getTime());
            Timestamp endTimestamp = new Timestamp(end.getTime());

            try {
                ChartPanel lineChart = LineChart(startTimestamp, endTimestamp);
                updateChartPanel(lineChart);

                lblThongTin.setText(String.format(
                        "Ngày có nhiều vé nhất: %s (%d vé), ít nhất: %s (%d vé)",
                        maxDate != null ? maxDate.toString() : "Không có",
                        maxTickets,
                        minDate != null ? minDate.toString() : "Không có",
                        minTickets
                ));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi cơ sở dữ liệu", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        Frm_ThongKeSoLuongVeTheoThoiGian frm = new Frm_ThongKeSoLuongVeTheoThoiGian();
        frm.setVisible(true);
    }
}

