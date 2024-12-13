
package GUI;

import DAO.DAO_DoiVe;
import DAO.DAO_KhachHang;
import Database.ConnectDatabase;
import Entity.KhachHang;
import Entity.LichTrinhTau;
import Entity.NhanVien;
import Entity.VeTau;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Frm_DoiVe extends JFrame {

    private final DAO_DoiVe dao_BanVe;
    private final DAO_KhachHang dao_KH;
    private FrmBanVe frmBanVe;
    private final DefaultTableModel tableModel;
    private KhachHang thongTinKH;
    private LichTrinhTau lichTrinhTau;
    private VeTau thongTinVeDoi;

    public Frm_DoiVe() {
        initComponents();
        try {
            ConnectDatabase.getInstance().connect();    
        } catch (Exception e) {

        }
        dao_BanVe = new DAO_DoiVe();
        try {
            dao_KH = new DAO_KhachHang();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        tableVeDoi.setFont(new Font("Arial", Font.PLAIN, 14));
        tableVeDoi.setRowHeight(35);  // Đặt chiều cao của các hàng (ví dụ: 35)
        tableVeDoi.setSelectionBackground(new Color(255, 223, 186));  // Màu nền khi chọn hàng

        // Thay đổi màu của tiêu đề cột
        JTableHeader header = tableVeDoi.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(new Color(0, 131, 66)); // Màu nền tiêu đề cột
                c.setForeground(Color.white);          // Màu chữ tiêu đề cột
                c.setFont(new Font("Arial", Font.BOLD, 20)); // Font chữ tiêu đề
                ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa
                return c;
            }
        });
        Color colorTieuDeBang = new Color(0, 131, 66);
        header.setBackground(new Color(0, 131, 66)); // Màu nền tiêu đề cột
        header.setForeground(Color.BLACK); // Màu chữ tiêu đề cột
        header.setFont(new Font("Arial", Font.BOLD, 20)); // Đặt font cho tiêu đề (cỡ 16)
        tableVeDoi.setFont(new Font("Arial", Font.PLAIN, 18)); // Đặt font cho các dòng (cỡ 14)
        tableModel = (DefaultTableModel) tableVeDoi.getModel();

        
        
        jPanelThongTin.setBorder(BorderFactory.createTitledBorder("Thông tin người đặt vé"));
        
    }

    private void initComponents() {

        buttonGroup1 = new ButtonGroup();
        jPanel1 = new JPanel();
        btnDoiVe = new JButton();
        jScrollPane4 = new JScrollPane();
        tableVeDoi = new JTable();
        jPanelThongTin = new JPanel();
        jLabel2 = new JLabel();
        txtTenKH = new JTextField();
        lblCcdcd = new JLabel();
        txtCccd = new JTextField();
        lblSdt = new JLabel();
        txtSdt = new JTextField();
        lblDiaChi = new JLabel();
        txtDiaChi = new JTextField();
        jLabel1 = new JLabel();
        jPanelFind = new JPanel();
        lblNhapMa = new JTextField();
        btnTimVe = new JButton();
        rbtnMaVe = new JRadioButton();
        rbtnMaHoaDon = new JRadioButton();
        lblThongTinVe = new JLabel();
        jPanelMenu = new JPanel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(BorderFactory.createEtchedBorder());

        btnDoiVe.setBackground(new Color(0, 131, 66));
        btnDoiVe.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
        btnDoiVe.setForeground(new Color(255, 255, 255));
        btnDoiVe.setIcon(new ImageIcon(getClass().getResource("/Anh_HeThong/Custom-Icon-Design-Flatastic-4-Tickets.48.png"))); // NOI18N
        btnDoiVe.setText("Đổi vé");
        btnDoiVe.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                btnDoiVeMouseClicked(evt);
            }
        });



        tableVeDoi.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã vé", "Họ Tên", "Giấy tờ", "Thông Tin Chỗ Ngồi", "Giá Vé", "Giảm đối tượng", "Trạng thái", "Ngày đi"
            }
        ));
        jScrollPane4.setViewportView(tableVeDoi);

        jPanelThongTin.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

        jLabel2.setFont(new Font("Segoe UI", 2, 18)); // NOI18N
        jLabel2.setText("Tên khách hàng: ");

        lblCcdcd.setFont(new Font("Segoe UI", 2, 18)); // NOI18N
        lblCcdcd.setText("Cccd:");

        lblSdt.setFont(new Font("Segoe UI", 2, 18)); // NOI18N
        lblSdt.setText("Số điện thoại:");

        lblDiaChi.setFont(new Font("Segoe UI", 2, 18)); // NOI18N
        lblDiaChi.setText("Địa chỉ:");

        GroupLayout jPanelThongTinLayout = new GroupLayout(jPanelThongTin);
        jPanelThongTin.setLayout(jPanelThongTinLayout);
        jPanelThongTinLayout.setHorizontalGroup(
            jPanelThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelThongTinLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanelThongTinLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelThongTinLayout.createSequentialGroup()
                        .addComponent(lblDiaChi)
                        .addGap(39, 39, 39)
                        .addComponent(txtDiaChi, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelThongTinLayout.createSequentialGroup()
                        .addComponent(lblSdt)
                        .addGap(39, 39, 39)
                        .addComponent(txtSdt, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelThongTinLayout.createSequentialGroup()
                        .addComponent(lblCcdcd)
                        .addGap(39, 39, 39)
                        .addComponent(txtCccd, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelThongTinLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(39, 39, 39)
                        .addComponent(txtTenKH, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        jPanelThongTinLayout.setVerticalGroup(
            jPanelThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelThongTinLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanelThongTinLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenKH, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelThongTinLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCcdcd)
                    .addComponent(txtCccd, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelThongTinLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSdt)
                    .addComponent(txtSdt, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelThongTinLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDiaChi)
                    .addComponent(txtDiaChi, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jLabel1.setFont(new Font("Segoe UI", 2, 24)); // NOI18N
        jLabel1.setText("Thông tin người đặt vé");

        jPanelFind.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

        btnTimVe.setBackground(new Color(0, 131, 66));
        btnTimVe.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
        btnTimVe.setForeground(new Color(255, 255, 255));
        btnTimVe.setIcon(new ImageIcon(getClass().getResource("/Anh_HeThong/findNV.png"))); // NOI18N
        btnTimVe.setText("TÌM VÉ");
        btnTimVe.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                btnTimVeMouseClicked(evt);
            }
        });

        buttonGroup1.add(rbtnMaVe);
        rbtnMaVe.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
        rbtnMaVe.setSelected(true);
        rbtnMaVe.setText("Mã vé");

        buttonGroup1.add(rbtnMaHoaDon);
        rbtnMaHoaDon.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
        rbtnMaHoaDon.setText("Mã hóa đơn");

        GroupLayout jPanelFindLayout = new GroupLayout(jPanelFind);
        jPanelFind.setLayout(jPanelFindLayout);
        jPanelFindLayout.setHorizontalGroup(
            jPanelFindLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFindLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanelFindLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(rbtnMaVe, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtnMaHoaDon, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblNhapMa, GroupLayout.PREFERRED_SIZE, 910, GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnTimVe, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanelFindLayout.setVerticalGroup(
            jPanelFindLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFindLayout.createSequentialGroup()
                .addGroup(jPanelFindLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFindLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(rbtnMaVe)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtnMaHoaDon))
                    .addGroup(jPanelFindLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanelFindLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNhapMa, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimVe, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        lblThongTinVe.setFont(new Font("Segoe UI", 2, 24)); // NOI18N
        lblThongTinVe.setText("Thông tin vé tàu");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDoiVe, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(321, 321, 321)
                        .addComponent(jPanelThongTin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jPanelFind, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, GroupLayout.PREFERRED_SIZE, 1312, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(lblThongTinVe)))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanelFind, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(22, 22, 22)
                .addComponent(jPanelThongTin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblThongTinVe)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(btnDoiVe, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        jPanelMenu.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        jPanelMenu.setPreferredSize(new Dimension(250, 2));

        GroupLayout jPanelMenuLayout = new GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanelMenuLayout);
        jPanelMenuLayout.setHorizontalGroup(
            jPanelMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 248, Short.MAX_VALUE)
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelMenu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMenu, GroupLayout.DEFAULT_SIZE, 843, Short.MAX_VALUE)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimVeMouseClicked(MouseEvent evt) {//GEN-FIRST:event_btnTimVeMouseClicked
       // Lấy mã vé hoặc mã hóa đơn từ giao diện
    String maDoi = lblNhapMa.getText().trim();
    
    if (maDoi.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập mã vé hoặc mã hóa đơn.");
        return; // Thoát nếu không có dữ liệu nhập
    }

    if (rbtnMaVe.isSelected()) {
        // Tìm vé theo mã vé
        VeTau thongTinVe = dao_BanVe.timVeTheoMaVe(maDoi);

        if (thongTinVe == null) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy vé với mã: " + maDoi);
            return; // Thoát nếu không tìm thấy vé
        }
        // Hiển thị thông tin vé trên bảng
        tableModel.setRowCount(0); // Reset bảng trước khi hiển thị
        Object[] rowData = {
            thongTinVe.getMaVe(),
            thongTinVe.getTenKhachHang(),
            thongTinVe.getGiayTo(),
            thongTinVe.getChoNgoi().getMaCho(),
            thongTinVe.getGiaVe(),
            thongTinVe.getDoiTuong(),
            thongTinVe.getTrangThai(),
            thongTinVe.getNgayDi()
        };

        tableModel.addRow(rowData);
        // Lấy thông tin khách hàng liên quan đến vé
        thongTinKH = dao_BanVe.timTTKHtheoMaVe(maDoi);
        if (thongTinKH != null) {
            try {
                txtCccd.setText(dao_KH.decryptAES(thongTinKH.getCCCD()));
                txtDiaChi.setText(dao_KH.decryptAES(thongTinKH.getDiaChi()));
                txtSdt.setText(dao_KH.decryptAES(thongTinKH.getSoDienThoai()));
                txtTenKH.setText(dao_KH.decryptAES(thongTinKH.getTenKhachHang()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin khách hàng cho mã vé: " + maDoi);
        }

    } else if (rbtnMaHoaDon.isSelected()) {
        // Tìm vé theo mã hóa đơn
        List<VeTau> danhSachVeTau = dao_BanVe.timVeTheoMaHoaDon(maDoi);

        tableModel.setRowCount(0); // Reset bảng trước khi hiển thị

        if (danhSachVeTau.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy vé nào thuộc mã hóa đơn: " + maDoi);
            return; // Thoát nếu không tìm thấy vé
        }

        // Hiển thị danh sách vé trong bảng
        for (VeTau thongTinVe : danhSachVeTau) {
            Object[] rowData = {
                thongTinVe.getMaVe(),
                thongTinVe.getTenKhachHang(),
                thongTinVe.getGiayTo(),
                thongTinVe.getChoNgoi().getMaCho(),
                thongTinVe.getGiaVe(),
                thongTinVe.getDoiTuong(),
                thongTinVe.getTrangThai(),
                thongTinVe.getNgayDi()
            };
            tableModel.addRow(rowData);
        }
        thongTinKH = dao_BanVe.timTTKHtheoMaHoaDon(maDoi);
        if (thongTinKH != null) {
            try {
                txtCccd.setText(dao_KH.decryptAES(thongTinKH.getCCCD()));
                txtDiaChi.setText(dao_KH.decryptAES(thongTinKH.getDiaChi()));
                txtSdt.setText(dao_KH.decryptAES(thongTinKH.getSoDienThoai()));
                txtTenKH.setText(dao_KH.decryptAES(thongTinKH.getTenKhachHang()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin khách hàng cho mã vé: " + maDoi);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Vui lòng chọn tìm theo mã vé hoặc mã hóa đơn.");
    }
    }//GEN-LAST:event_btnTimVeMouseClicked
    
    private void btnDoiVeMouseClicked(MouseEvent evt) {//GEN-FIRST:event_btnDoiVeMouseClicked

       int selectedRow = tableVeDoi.getSelectedRow();
            if (selectedRow == -1) {
            // Nếu không có dòng nào được chọn
            JOptionPane.showMessageDialog(this, "Vui lòng chọn vé cần đổi!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
         else {
        // Nếu đã chọn, lấy thông tin dòng đó
        String maVe = tableModel.getValueAt(selectedRow, 0).toString(); // Lấy mã vé từ cột 0
        String tenKH = tableModel.getValueAt(selectedRow, 1).toString(); // Lấy tên khách hàng từ cột 1
        String giayTo = tableModel.getValueAt(selectedRow, 2).toString(); // Lấy giấy tờ từ cột 2
        String choNgoi = tableModel.getValueAt(selectedRow, 3).toString(); // Lấy chỗ ngồi từ cột 3
        double giaVe = Double.parseDouble(tableModel.getValueAt(selectedRow, 4).toString()); // Lấy giá vé từ cột 4
        String doiTuong = tableModel.getValueAt(selectedRow, 5).toString(); // Lấy đối tượng từ cột 5
        String trangThai = tableModel.getValueAt(selectedRow, 6).toString(); // Lấy trạng thái từ cột 6
        LocalDate ngayDi = LocalDate.parse(tableModel.getValueAt(selectedRow, 7).toString());

        lichTrinhTau = dao_BanVe.timLichTrinhTheoMaVe(maVe);
        thongTinVeDoi = new VeTau(maVe, lichTrinhTau.getMaLichTrinh(), choNgoi, tenKH, giayTo, ngayDi, doiTuong, giaVe, trangThai);
         if (thongTinVeDoi!= null){
             //dien kien time
             boolean dieuKienDoi = dao_BanVe.kiemTraThoiGianDoi(thongTinVeDoi.getMaVe());
             if (dieuKienDoi && trangThai.equals("Đã thanh toán")) {
                 // Nếu đủ điều kiện đổi vé
                 if (frmBanVe == null) {
                     frmBanVe = new FrmBanVe(new NhanVien());
                 }
                 frmBanVe.setThongTinVe(thongTinVeDoi); // Cập nhật thông tin vé vào giao diện FrmBanVe
                 frmBanVe.setVisible(true); // Hiển thị cửa sổ FrmBanVe
             } else {
                 // Nếu không đủ điều kiện đổi vé
                 JOptionPane.showMessageDialog(this, "Không thể đổi vé, vé không thỏa điều kiện", "Thông báo", JOptionPane.ERROR_MESSAGE);
             }
         }

       }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Frm_DoiVe frmDoiVe = new Frm_DoiVe();
        frmDoiVe.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public Component getJpannelDoiVe() {
        return jPanel1;
    }
    private JButton btnDoiVe;
    private JButton btnTimVe;
    private ButtonGroup buttonGroup1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JPanel jPanelFind;
    private JPanel jPanelMenu;
    private JPanel jPanelThongTin;
    private JScrollPane jScrollPane4;
    private JLabel lblCcdcd;
    private JLabel lblDiaChi;
    private JTextField lblNhapMa;
    private JLabel lblSdt;
    private JLabel lblThongTinVe;
    private JRadioButton rbtnMaHoaDon;
    private JRadioButton rbtnMaVe;
    private JTable tableVeDoi;
    private JTextField txtCccd;
    private JTextField txtDiaChi;
    private JTextField txtSdt;
    private JTextField txtTenKH;


}
