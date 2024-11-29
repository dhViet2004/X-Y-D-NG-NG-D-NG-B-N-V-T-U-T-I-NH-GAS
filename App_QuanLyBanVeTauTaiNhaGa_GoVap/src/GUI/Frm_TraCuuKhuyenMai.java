package GUI;

import DAO.DAO_KhuyenMai;
import Entity.KhuyenMai;
import Entity.RoundedButton;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Frm_TraCuuKhuyenMai extends JFrame implements ActionListener, MouseListener {


    Color colorXanhDam = new Color(0, 131, 66);
    private final Font font_text;
    private final Font font_text_2;
    private final Font font_text_3;
    private JPanel JPanel_Menu;
    private JPanel JPanel_Main;
    private JPanel Main_Top;
    private JLabel tieuDe;
    private Box BoxKM = Box.createHorizontalBox();
    private TitledBorder titledBorder_BoxKM;
    private Box Box_KM_Title = Box.createVerticalBox();
    private final JLabel lblBegin;
    private final JLabel lblEnd;
    private final JLabel lblND;
    private final JLabel lblCK;
    private final JLabel lblDT;
    private Box Box_KM_TextBox = Box.createVerticalBox();
    private final JTextField txtBegin;
    private final JTextField txtEnd;
    private final JTextField txtND;
    private final JTextField txtCK;
    private final JTextField txtDT;
    //----------------------------------//
    private JPanel Main_Bottom;
    private Box Box_BTN = Box.createVerticalBox();
    private Box Box_BTN1 = Box.createHorizontalBox();
    private JLabel Box_BTN1_lbl;
    private JComboBox Box_BTN1_comboBox;
    private JButton btn_Loc;
    private Box Box_BTN2 = Box.createHorizontalBox();
    private JLabel Box_BTN2_lbl;
    private JTextField Box_BTN2_txt;
    private JDateChooser dateChooser;
    private DefaultTableModel model;
    private JTable jTable;
    private JScrollPane scrollPane;
    public  Component getTraCuuKM_Panel(){
        return JPanel_Main;
    }
    public Frm_TraCuuKhuyenMai()  {
        setTitle("From Tra Cứu Khuyến Mãi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(JPanel_Menu, BorderLayout.WEST);
        add(JPanel_Main, BorderLayout.CENTER);
        JPanel_Main.setBackground(Color.WHITE);
        font_text = new Font("Arial", Font.BOLD, 30);
        font_text_2 = new Font("Arial", Font.PLAIN, 15);
        font_text_3 = new Font("Arial", Font.PLAIN, 25);

        Main_Top = new JPanel(new BorderLayout());
        Main_Top.setBackground(Color.WHITE);

        tieuDe = new JLabel("Tra cứu khuyến mãi", SwingConstants.CENTER);
        tieuDe.setFont(font_text);

        titledBorder_BoxKM = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(colorXanhDam), // Đặt màu đường viền là màu xanh
                "Thông tin khuyến mãi", // Tiêu đề
                TitledBorder.LEFT, // Canh trái
                TitledBorder.TOP, // Vị trí tiêu đề
                font_text_3.deriveFont(Font.ITALIC) // Font in nghiêng
        );
        titledBorder_BoxKM.setTitleColor(Color.RED); // Đặt màu tiêu đề là màu đỏ
        BoxKM.setBorder(titledBorder_BoxKM);
        BoxKM.add(Box_KM_Title);
        BoxKM.add(Box.createRigidArea(new Dimension(400, 10)));
        BoxKM.add(Box_KM_TextBox);
        lblBegin = new JLabel("Thời gian bắt đầu");
        lblEnd = new JLabel("Thời gian kết thúc");
        lblND = new JLabel("Nội dung");
        lblCK = new JLabel("Chiết khấu");
        lblDT = new JLabel("Đối tượng áp dụng");
//      Thiết lập font_text_2 cho các JLabel
        lblBegin.setFont(font_text_2);
        lblEnd.setFont(font_text_2);
        lblND.setFont(font_text_2);
        lblCK.setFont(font_text_2);
        lblDT.setFont(font_text_2);
        Box_KM_Title.add(lblBegin);
        Box_KM_Title.add(Box.createRigidArea(new Dimension(10, 30)));
        Box_KM_Title.add(lblEnd);
        Box_KM_Title.add(Box.createRigidArea(new Dimension(10, 30)));
        Box_KM_Title.add(lblND);
        Box_KM_Title.add(Box.createRigidArea(new Dimension(10, 30)));
        Box_KM_Title.add(lblCK);
        Box_KM_Title.add(Box.createRigidArea(new Dimension(10, 30)));
        Box_KM_Title.add(lblDT);
        Box_KM_Title.add(Box.createRigidArea(new Dimension(10, 30)));
        txtBegin = new JTextField(50);
        txtEnd = new JTextField(50);
        txtND = new JTextField(50);
        txtCK = new JTextField(50);
        txtDT = new JTextField(50);
        txtBegin.setEditable(false);
        txtEnd.setEditable(false);
        txtND.setEditable(false);
        txtCK.setEditable(false);
        txtDT.setEditable(false);
        Box_KM_TextBox.add(txtBegin);
        Box_KM_TextBox.add(Box.createRigidArea(new Dimension(10, 30)));
        Box_KM_TextBox.add(txtEnd);
        Box_KM_TextBox.add(Box.createRigidArea(new Dimension(10, 30)));
        Box_KM_TextBox.add(txtND);
        Box_KM_TextBox.add(Box.createRigidArea(new Dimension(10, 30)));
        Box_KM_TextBox.add(txtCK);
        Box_KM_TextBox.add(Box.createRigidArea(new Dimension(10, 30)));
        Box_KM_TextBox.add(txtDT);
        Box_KM_TextBox.add(Box.createRigidArea(new Dimension(10, 30)));

        Main_Top.add(tieuDe, BorderLayout.NORTH);
        Main_Top.add(BoxKM, BorderLayout.CENTER);
        JPanel_Main.add(Main_Top, BorderLayout.NORTH);
        //---------------------------------------------------------//
        Main_Bottom = new JPanel(new BorderLayout());
        Main_Bottom.setBackground(Color.WHITE);


        JPanel_Main.add(Main_Bottom, BorderLayout.CENTER);
        Main_Bottom.setLayout(new BorderLayout());
        Main_Bottom.add(Box_BTN, BorderLayout.NORTH);
        Box_BTN.add(Box.createRigidArea(new Dimension(10, 30)));
        Box_BTN.add(Box_BTN1);
        Box_BTN1_lbl = new JLabel("Đối tượng áp dụng");
        Box_BTN1_lbl.setFont(font_text_2);
        btn_Loc = new RoundedButton("Lọc");
        btn_Loc.setSize(new Dimension(200, 50));
        btn_Loc.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn_Loc.setFont(font_text_2);
        btn_Loc.setForeground(Color.RED);
        btn_Loc.setBackground(Color.YELLOW);
        Box_BTN1_comboBox = new JComboBox();
        Box_BTN1.add(Box_BTN1_lbl);
        Box_BTN1.add(Box.createRigidArea(new Dimension(200, 10)));
        Box_BTN1.add(Box_BTN1_comboBox);
        Box_BTN1.add(Box.createRigidArea(new Dimension(200, 10)));
        Box_BTN1.add(btn_Loc);
        Box_BTN1.add(Box.createRigidArea(new Dimension(200, 10)));

        Box_BTN.add(Box.createRigidArea(new Dimension(10, 30)));

        Box_BTN.add(Box_BTN2);
        Box_BTN.add(Box.createRigidArea(new Dimension(10, 30)));
        Box_BTN2_lbl = new JLabel("Thời gian");
        Box_BTN2_lbl.setFont(font_text_2);
        dateChooser = new JDateChooser();
        Box_BTN2.add(Box_BTN2_lbl);
        Box_BTN2.add(Box.createRigidArea(new Dimension(265, 40)));
        Box_BTN2.add(dateChooser);
        Box_BTN2.add(Box.createRigidArea(new Dimension(465, 40)));

        String tieuDe[] = {"STT", "Mã KM", "Thời gian bắt đầu", "Thời gian kết thúc", "Nội dung", "Chiết khấu (%)", "Đối tượng áp dụng"};
        model = new DefaultTableModel(tieuDe, 0);
        jTable = new JTable(model);
        jTable.getTableHeader().setFont(font_text_2);
        jTable.getTableHeader().setBackground(Color.WHITE);
        jTable.setBackground(Color.WHITE);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        Main_Bottom.add(jScrollPane, BorderLayout.CENTER);

        //dk sự kiện
        Box_BTN1_comboBox.addActionListener(this);
        jTable.addMouseListener(this);
        btn_Loc.addActionListener(this);
        try {
            loadDoiTuong();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == btn_Loc) {
            model.setRowCount(0);
            // nếu không chọn ngày, thì mặc định lọc theo ngày hiện tại và selectedItem của comboBox
            try {
                loadKM();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int selectedRow = jTable.getSelectedRow();
        if (selectedRow != -1) {
            // Lấy dữ liệu từ các cột trong dòng được chọn
            txtBegin.setText(model.getValueAt(selectedRow, 2).toString());  // Thời gian bắt đầu
            txtEnd.setText(model.getValueAt(selectedRow, 3).toString());    // Thời gian kết thúc
            txtND.setText(model.getValueAt(selectedRow, 4).toString());     // Nội dung
            txtCK.setText(model.getValueAt(selectedRow, 5).toString());     // Chiết khấu
            txtDT.setText(model.getValueAt(selectedRow, 6).toString());     // Đối tượng áp dụng
        }
    }

    public void loadKM() throws SQLException {
        String doituongApDung = Box_BTN1_comboBox.getSelectedItem().toString();
        LocalDate date;
        if (dateChooser.getDate() == null) {
            date = LocalDate.now();
        } else {
            date = (dateChooser.getDate()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

        DAO_KhuyenMai dao_km = new DAO_KhuyenMai();
        try {
            dao_km = new DAO_KhuyenMai();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<KhuyenMai> list_km = new ArrayList<KhuyenMai>();
        try {
            list_km = dao_km.timKMTheoThoiGianApDung_DoiTuongApDung(date, doituongApDung);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // đổ dữ liệu vào model
        for (int i = 0; i < list_km.size(); i++) {
            model.addRow(new Object[]{
                    i + 1,
                    list_km.get(i).getMaKM(),
                    list_km.get(i).getThoiGianBatDau(),
                    list_km.get(i).getThoiGianKetThuc(),
                    list_km.get(i).getNoiDungKM(),
                    list_km.get(i).getChietKhau(),
                    list_km.get(i).getDoiTuongApDung()
            });
        }
    }

    public void loadDoiTuong() throws SQLException {
        List<KhuyenMai> list = new ArrayList<>();
        DAO_KhuyenMai dao = new DAO_KhuyenMai();
        list = dao.getKhuyenMais();
        Set<String> unique = new HashSet<>();
        for (KhuyenMai k : list) {
            unique.add(k.getDoiTuongApDung());
        }
        for (String doituong : unique) {
            Box_BTN1_comboBox.addItem(doituong);
        }
    }

    public static void main(String[] args) throws SQLException {
        Frm_TraCuuKhuyenMai frm = new Frm_TraCuuKhuyenMai();
        frm.setVisible(true);
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
}
