package GUI;

import DAO.DAO_HoaDon;
import DAO.DAO_KhuyenMai;
import Entity.KhuyenMai;
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
import java.util.Date;
import java.util.List;

public class FrmKhuyenMai extends JFrame implements ActionListener, MouseListener {
    private JTable jTable2;
    private DefaultTableModel model;
    private DefaultTableModel model2;
    private JButton btnLammoi;
    private JButton btnLamMoiKM;
    private JPanel contenPanel;
    private JPanel JPanel_Menu;
    private JPanel Jpanel_Right;
    private JPanel JPanel_Right_Top;
    private JPanel JPanel_Right_Bottom;
    private JDateChooser dateChooser = new JDateChooser();
    private JButton loc;
    private JButton btnThemCTKM;
    private JButton btnSuaCTKM;

    public FrmKhuyenMai() {
        setTitle("Form Khuyến Mãi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        // thêm menu vào màn hình chính
        add(JPanel_Menu, BorderLayout.WEST);
        // thêm panel chứa toàn bộ nội dung qua bên phải
        add(Jpanel_Right, BorderLayout.CENTER);
        Jpanel_Right.setLayout(new BorderLayout());
        Jpanel_Right.setBackground(Color.WHITE);
        JPanel_Right_Bottom.setBackground(Color.WHITE);
        JPanel_Right_Top.setBackground(Color.WHITE);
        // thêm Right_Top vào Noth và Right_Bottom vào center của Right
        Jpanel_Right.add(JPanel_Right_Top, BorderLayout.CENTER);
        Jpanel_Right.add(JPanel_Right_Bottom, BorderLayout.NORTH);
        // tạo một title border cho panel chứa ds hóa đơn
        TitledBorder dshd = BorderFactory.createTitledBorder("Hóa đơn trong ngày");
        dshd.setTitleJustification(TitledBorder.LEFT);
        dshd.setTitlePosition(TitledBorder.TOP);
        Font font = new Font("Courier New", Font.BOLD, 18);
        dshd.setTitleFont(font);
        // set border cho Right_Top
        JPanel_Right_Top.setBorder(dshd);
        JPanel_Right_Top.setLayout(new BorderLayout());
        // tạo bảng
        String header[] = {"STT", "Ngày lặp", "Khách hàng", "Đối tượng", "Khuyến mãi", "Chiếc khấu(%)", "Tiền giảm(vnd)"};
        model = new DefaultTableModel(header, 0);
        JTable jTable = new JTable(model);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        //thêm ScrollPanel vào center của right-top
        JPanel_Right_Top.add(jScrollPane, BorderLayout.CENTER);

        // tạo title border cho right-bottom
        TitledBorder dskm = BorderFactory.createTitledBorder("Danh sách chương trình khuyến mãi");
        dskm.setTitleJustification(TitledBorder.LEFT);
        dskm.setTitlePosition(TitledBorder.TOP);
        // set bordet cho right-bottom
        JPanel_Right_Bottom.setBorder(dskm);
        Font font2 = new Font("Courier New", Font.BOLD, 18);
        dskm.setTitleFont(font);
        JPanel_Right_Bottom.setPreferredSize(new Dimension(0, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.5)));

        // set cho JPanel_Right là borderlayout
        JPanel_Right_Bottom.setLayout(new BorderLayout());
        // tạo box lớn
        Box main = Box.createVerticalBox();


        // box horizone chứa label và calendar
        Box search = Box.createHorizontalBox();
        // tạo label
        JLabel lblSearch = new JLabel("Ngày áp dụng");
        // thêm  lbl và txt và search;
        search.add(lblSearch);
        search.add(Box.createRigidArea(new Dimension(10, 10)));
        search.add(dateChooser);
        search.add(Box.createRigidArea(new Dimension(30, 5)));
        // Tạo icon từ hình ảnh
        ImageIcon iconLoc = new ImageIcon(getClass().getResource("/Anh_HeThong/filter.png"));
        // lấy ảnh từ icon
        Image imgLoc = iconLoc.getImage();
        // chỉnh kích thước
        Image scaleImgLoc = imgLoc.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        // tại icon mới từ ảnh đã sửa
        ImageIcon iconLocNew = new ImageIcon(scaleImgLoc);
        // Gán icon vào nút lọc
        loc = new JButton("Lọc", iconLocNew);

        // Điều chỉnh vị trí của icon nếu cần
        loc.setHorizontalTextPosition(SwingConstants.LEFT); // Đặt text bên phải của icon
        search.add(loc);

        // thêm main vào center của right_bottom
        JPanel_Right_Bottom.add(main, BorderLayout.CENTER);
        //thêm box search vào main
        main.add(search);
        main.add(Box.createRigidArea(new Dimension(20, 20)));

        // tạo nút thêm và sửa đổi, thêm vào south của right_bottom

        // 1 tạo icon cho nút thêm
        ImageIcon iconThem = new ImageIcon(getClass().getResource("/Anh_HeThong/plus.png"));
        Image imgThem = iconThem.getImage();
        Image scaleThem = imgThem.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon iconThemNew = new ImageIcon(scaleThem);
        btnThemCTKM = new JButton("Thêm", iconThemNew);
        btnThemCTKM.setHorizontalTextPosition(SwingConstants.LEFT);
        ImageIcon iconAdd = new ImageIcon(getClass().getResource("/Anh_HeThong/plus.png"));

        // 2 tạo icon cho nút sửa
        ImageIcon iconSua = new ImageIcon(getClass().getResource("/Anh_HeThong/edit.png"));
        Image imgSua = iconSua.getImage();
        Image scaleSua = imgSua.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon iconSuaNew = new ImageIcon(scaleSua);
        btnSuaCTKM = new JButton("Sửa", iconSuaNew);
        btnSuaCTKM.setHorizontalTextPosition(SwingConstants.LEFT);

        // 3 tạo icon cho nút làm mới bảng khuyến mãi
        ImageIcon iconLamMoiKM = new ImageIcon(getClass().getResource("/Anh_HeThong/rotate.png"));
        Image imgLamMoiKM = iconLamMoiKM.getImage();
        Image scaleLamMoiKM = imgLamMoiKM.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon iconLamMoiKMNew = new ImageIcon(scaleLamMoiKM);
        btnLamMoiKM = new JButton("Làm mới", iconLamMoiKMNew);
        btnLamMoiKM.setHorizontalTextPosition(SwingConstants.LEFT);

        Box themSua = Box.createHorizontalBox();
        JPanel_Right_Bottom.add(themSua, BorderLayout.SOUTH);
        themSua.add(Box.createRigidArea(new Dimension(450, 10)));
        themSua.add(btnThemCTKM);
        themSua.add(Box.createRigidArea(new Dimension(20, 20)));
        themSua.add(btnSuaCTKM);
        themSua.add(Box.createRigidArea(new Dimension(20, 20)));
        themSua.add(btnLamMoiKM);
        btnLamMoiKM.setPreferredSize(new Dimension(20, 20));

        // 3 tạo icon cho nút làm mới
        ImageIcon iconLamMoi = new ImageIcon(getClass().getResource("/Anh_HeThong/rotate.png"));
        Image imgLamMoi = iconLamMoi.getImage();
        Image scaleLamMoi = imgLamMoi.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon iconLamMoiNew = new ImageIcon(scaleLamMoi);
        btnLammoi = new JButton("Làm mới", iconLamMoiNew);
        btnLammoi.setHorizontalTextPosition(SwingConstants.LEFT);

        // tạo nút làm mới, thêm vào south của right_top
        Box lamMoi = Box.createHorizontalBox();
        JPanel_Right_Top.add(lamMoi, BorderLayout.SOUTH);
        lamMoi.add(Box.createRigidArea(new Dimension(1180, 20)));
        lamMoi.add(btnLammoi);

        //tạo bảng chứa CTKM
        String tieuDe[] = {"STT", "Mã KM", "Thời gian bắt đầu", "Thời gian kết thúc", "Nội dung", "Chiết khấu (%)", "Đối tượng áp dụng"};
        model2 = new DefaultTableModel(tieuDe, 0);
        jTable2 = new JTable(model2);
        JScrollPane jScrollPane2 = new JScrollPane(jTable2);
        main.add(jScrollPane2);


        // lấy dữ liệu lên bảng HD
        loadHD();
        // lấy ds ctkm
        loadKM();
        // đăng kí sự kiện
        btnThemCTKM.addActionListener(this);
        btnSuaCTKM.addActionListener(this);
        btnLamMoiKM.addActionListener(this);
        btnLammoi.addActionListener(this);
        loc.addActionListener(this);
        jTable2.addMouseListener(this);

    }

    // load lên bảng hóa đơn
    public void loadHD() {
        // lấy danh sách hóa đơn lên bảng
        List<Object[]> list_hd = new ArrayList<>();
        DAO_HoaDon dao_hoadon = new DAO_HoaDon();
        try {
            list_hd = dao_hoadon.layDSHD();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < list_hd.size(); i++) {
            model.addRow(new Object[]{
                    i + 1,
                    list_hd.get(i)[0],
                    list_hd.get(i)[1],
                    list_hd.get(i)[2],
                    list_hd.get(i)[3],
                    list_hd.get(i)[4],
                    list_hd.get(i)[5]
            });
        }

    }

    // lấy danh sách KM lên bảng
    public void loadKM() {
        DAO_KhuyenMai dao_km = null;
        try {
            dao_km = new DAO_KhuyenMai();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<KhuyenMai> list_km = new ArrayList<KhuyenMai>();
        try {
            list_km = dao_km.getKhuyenMais();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // đổ dữ liệu vào model2
        for (int i = 0; i < list_km.size(); i++) {
            model2.addRow(new Object[]{
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


    public static void main(String[] args) throws SQLException {
        FrmKhuyenMai frm = new FrmKhuyenMai();
        frm.setVisible(true);
        // kết nối với sql

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == loc) {
            DAO_KhuyenMai dao;
            try {
                dao = new DAO_KhuyenMai();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            // lấy ngày từ datechoser
            Date ngayLoc = dateChooser.getDate();
            LocalDate ngayLocLocal = ngayLoc.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // gọi hàm tìm km theo điều kiện lọc, sau đó xóa hết bảng, sau đó load km đó lên bảng
            // 1 tìm km thỏa
            List<KhuyenMai> kmLoc  = new ArrayList<>();
            try {
                kmLoc = dao.timKMTheoThoiGianApDung(ngayLocLocal);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            // xóa dữ liệu của bảng
            if (kmLoc != null) {
                model2.setRowCount(0);
                // load km tìm được lên bảng
                for (int i = 0; i < kmLoc.size(); i++) {
                    model2.addRow(new Object[]{
                            i+1,
                            kmLoc.get(i).getMaKM(),
                            kmLoc.get(i).getThoiGianBatDau(),
                            kmLoc.get(i).getThoiGianKetThuc(),
                            kmLoc.get(i).getNoiDungKM(),
                            kmLoc.get(i).getChietKhau(),
                            kmLoc.get(i).getDoiTuongApDung()
                    });
                }

            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy!");
            }
        } else if (o == btnThemCTKM) {
            // Khi nhấn nút "Thêm", hiển thị form thêm CTKM
            FrmThemKhuyenMai frmThem = new FrmThemKhuyenMai(this);
            frmThem.setVisible(true);
        } else if (o == btnSuaCTKM) {
            // chọn dòng nào, hiển thị dialog chứa thông tin dòng đó kèm với nút cập nhật và hủy
            int n = jTable2.getSelectedRow();
            if (n != -1) {
                loadDataToEdit(n);
            }
        } else if (o == btnLamMoiKM) {
            // hết dự liệu trên bảng
            model2.setRowCount(0);
            // load dữ liệu mới lên
            loadKM();
        }else if (o == btnLammoi) {
            model.setRowCount(0);
            loadHD();
        }

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

    private void loadDataToEdit(int row) {
        String maKM = model2.getValueAt(row, 1).toString(); // Mã KM
        Object thoiGianBatDauObj = model2.getValueAt(row, 2); // Thời gian bắt đầu
        Object thoiGianKetThucObj = model2.getValueAt(row, 3); // Thời gian kết thúc
        String noiDung = model2.getValueAt(row, 4).toString(); // Nội dung KM
        String chietKhau = model2.getValueAt(row, 5).toString(); // Chiết khấu
        String doiTuong = model2.getValueAt(row, 6).toString(); // Đối tượng áp dụng

        FrmSuaKhuyenMai frmSua = new FrmSuaKhuyenMai(this);

        // Chuyển đổi và thiết lập thời gian bắt đầu
        Date thoiGianBatDau = null;
        if (thoiGianBatDauObj instanceof Date) {
            thoiGianBatDau = (Date) thoiGianBatDauObj;
        } else if (thoiGianBatDauObj instanceof LocalDate) {
            LocalDate localDate = (LocalDate) thoiGianBatDauObj;
            thoiGianBatDau = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }

        // Chuyển đổi và thiết lập thời gian kết thúc
        Date thoiGianKetThuc = null;
        if (thoiGianKetThucObj instanceof Date) {
            thoiGianKetThuc = (Date) thoiGianKetThucObj;
        } else if (thoiGianKetThucObj instanceof LocalDate) {
            LocalDate localDate = (LocalDate) thoiGianKetThucObj;
            thoiGianKetThuc = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }

        // load dữ liệu vào frmSua
        frmSua.setMaKM(maKM);
        frmSua.setThoiGianBatDau(thoiGianBatDau);
        frmSua.setThoiGianKetThuc(thoiGianKetThuc);
        frmSua.setNoiDungKM(noiDung);
        frmSua.setChietKhau(chietKhau);
        frmSua.setDoiTuongApDung(doiTuong);

        frmSua.setVisible(true);
    }
}
