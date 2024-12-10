    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
     */
    package GUI;

    import DAO.DAO_LichLamViec;
    import DAO.DAO_NhanVien;
    import Database.ConnectDatabase;
    import Entity.LichLamViec;
    import Entity.NhanVien;
    import helper.MessageDialogHelper;
    import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

    import javax.swing.*;
    import javax.swing.table.DefaultTableModel;
    import java.awt.*;
    import java.text.ParseException;
    import java.text.SimpleDateFormat;
    import java.time.LocalDate;
    import java.time.LocalDateTime;
    import java.time.LocalTime;
    import java.time.ZoneId;
    import java.time.format.DateTimeFormatter;
    import java.time.format.DateTimeParseException;
    import java.util.ArrayList;
    import java.util.Date;
    import java.util.List;
    import java.util.logging.Level;
    import java.util.logging.Logger;

    /**
     * @author viet6
     */
    public class FrmLichLamViec extends JFrame {
        private DAO_NhanVien nv_dao;
        private DAO_LichLamViec llv_dao;

        public Component getPanel_LLV() {
            return jPanelLLV;
        }

        public FrmLichLamViec() {
            initComponents();
            try {
                ConnectDatabase.getInstance().connect();

            } catch (Exception e) {
            }
            nv_dao = new DAO_NhanVien();
            llv_dao = new DAO_LichLamViec();
            setExtendedState(JFrame.MAXIMIZED_BOTH);

            try {
                hienThiDanhSachLichLamViec(llv_dao.findAll());
            } catch (Exception ex) {
                Logger.getLogger(FrmLichLamViec.class.getName()).log(Level.SEVERE, null, ex);
            }
            AutoCompleteDecorator.decorate(cbxNhanVien);
            //txtMaLLV.setText(phatSinhMaLLVTuDong());
            loadNhanVienIntoComboBox(cbxNhanVien);
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

            grTrangThai = new javax.swing.ButtonGroup();
            timePicker1 = new com.raven.swing.TimePicker();
            timePicker2 = new com.raven.swing.TimePicker();
            jPanelLLV = new javax.swing.JPanel();
            jScrollPane1 = new javax.swing.JScrollPane();
            jTableLLV = new javax.swing.JTable();
            jPanel1 = new javax.swing.JPanel();
            cbxCaLam = new JComboBox<>();
            cbxNhanVien = new JComboBox<>();
            jLabel1 = new javax.swing.JLabel();
            cbxTrangThai = new JComboBox<>();
            jLabel4 = new javax.swing.JLabel();
            jLabel2 = new javax.swing.JLabel();
            txtMaLLV = new javax.swing.JTextField();
            jLabel3 = new javax.swing.JLabel();
            jDateChooserKT = new com.toedter.calendar.JDateChooser();
            jLabel6 = new javax.swing.JLabel();
            jDateChooserBD = new com.toedter.calendar.JDateChooser();
            txtTimeBD = new javax.swing.JTextField();
            lblTimeBD = new javax.swing.JLabel();
            txtTimeKT = new javax.swing.JTextField();
            lblTimeKT = new javax.swing.JLabel();
            jLabel10 = new javax.swing.JLabel();
            jLabel9 = new javax.swing.JLabel();
            jLabel11 = new javax.swing.JLabel();
            jPanel3 = new javax.swing.JPanel();
            btnLoc = new javax.swing.JButton();
            jLabel7 = new javax.swing.JLabel();
            jDateChooseLocNBD = new com.toedter.calendar.JDateChooser();
            jLabel8 = new javax.swing.JLabel();
            jDateChooserLocNKT = new com.toedter.calendar.JDateChooser();
            locTrangThai = new javax.swing.JPanel();
            jRadioDungGio = new javax.swing.JRadioButton();
            jRadioTatCa = new javax.swing.JRadioButton();
            jRadioChuaLam = new javax.swing.JRadioButton();
            jRadioTre = new javax.swing.JRadioButton();
            locCaLam = new javax.swing.JPanel();
            ckCa3 = new javax.swing.JCheckBox();
            ckCaGay = new javax.swing.JCheckBox();
            ckCa2 = new javax.swing.JCheckBox();
            ckCa1 = new javax.swing.JCheckBox();
            jPanel4 = new javax.swing.JPanel();
            jLabel5 = new javax.swing.JLabel();
            filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
            jPanel2 = new javax.swing.JPanel();
            btnThem = new javax.swing.JButton();
            btnLamMoi = new javax.swing.JButton();
            btnSuaLLV = new javax.swing.JButton();
            jPanel6 = new javax.swing.JPanel();
            txtTimLLV = new javax.swing.JTextField();
            btnTimLLV = new javax.swing.JButton();
            jPanel5 = new javax.swing.JPanel();

            timePicker1.set24hourMode(true);
            timePicker1.setDisplayText(txtTimeBD);

            timePicker2.set24hourMode(true);
            timePicker2.setDisplayText(txtTimeKT);

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

            jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("DANH SÁCH LỊCH LÀM VIỆC"));
            jScrollPane1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

            jTableLLV.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
            jTableLLV.setModel(new DefaultTableModel(
                    new Object[][]{
                            {null, null, null, null, null, null},
                            {null, null, null, null, null, null},
                            {null, null, null, null, null, null},
                            {null, null, null, null, null, null}
                    },
                    new String[]{
                            "Mã lịch làm việc", "Mã nhân viên", "Giờ bắt đầu", "Giờ kết thúc", "Trạng thái", "Tên ca"
                    }
            ));

            jTableLLV.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
            jTableLLV.setRowHeight(50);
            jTableLLV.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jTableLLVMouseClicked(evt);
                }
            });
            jScrollPane1.setViewportView(jTableLLV);

            jPanel1.setBackground(new java.awt.Color(204, 204, 204));

            cbxCaLam.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            cbxCaLam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Ca 1", "Ca 2", "Ca 3", "Ca gãy"}));
            cbxCaLam.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    cbxCaLamMouseClicked(evt);
                }
            });
            cbxCaLam.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxCaLamActionPerformed(evt);
                }
            });

            cbxNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            cbxNhanVien.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxNhanVienActionPerformed(evt);
                }
            });

            jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jLabel1.setText("Mã lịch làm việc:");

            cbxTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            cbxTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Chua lam", "Di tre", "Dung gio"}));
            cbxTrangThai.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxTrangThaiActionPerformed(evt);
                }
            });

            jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jLabel4.setText("Trạng thái:");

            jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jLabel2.setText("Giờ bắt đầu:");

            txtMaLLV.setEditable(false);

            jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jLabel3.setText("Giờ kết thúc:");

            jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jLabel6.setText("Ca làm:");

            lblTimeBD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh_HeThong/clockNV.png"))); // NOI18N
            lblTimeBD.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lblTimeBDMouseClicked(evt);
                }
            });

            lblTimeKT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh_HeThong/clockNV.png"))); // NOI18N
            lblTimeKT.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lblTimeKTMouseClicked(evt);
                }
            });

            jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jLabel10.setText("Mã nhân viên:");

            jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jLabel9.setText("Ngày bắt đầu:");

            jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jLabel11.setText("Ngày kết thúc:");

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGap(46, 46, 46)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel9))
                                                    .addGap(18, 18, 18)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                    .addComponent(txtTimeBD, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addGap(18, 18, 18)
                                                                    .addComponent(lblTimeBD, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addComponent(jDateChooserBD, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(159, 159, 159))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                    .addComponent(jLabel6)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addComponent(cbxCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                    .addComponent(jLabel1)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(txtMaLLV, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addGap(170, 170, 170)))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel11))
                                    .addGap(26, 26, 26)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jDateChooserKT, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(txtTimeKT, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(lblTimeKT, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(cbxNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(45, Short.MAX_VALUE))
            );
            jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap(23, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jLabel4)
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                    .addComponent(jLabel10)
                                                                    .addGap(71, 71, 71)))
                                                    .addGap(35, 35, 35)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jLabel11)
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                    .addComponent(jLabel3)
                                                                    .addGap(55, 55, 55)))
                                                    .addGap(35, 35, 35))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(jLabel6)
                                                            .addComponent(cbxCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(11, 11, 11)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(txtMaLLV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel1))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(lblTimeBD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(txtTimeBD, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(jLabel2)))
                                                    .addGap(18, 18, 18)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jDateChooserBD, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                    .addComponent(jLabel9)
                                                                    .addGap(15, 15, 15)))
                                                    .addGap(25, 25, 25))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                    .addComponent(cbxNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                    .addGap(14, 14, 14)
                                                                    .addComponent(txtTimeKT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                    .addGap(18, 18, 18)
                                                                    .addComponent(lblTimeKT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jDateChooserKT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(17, 17, 17))))
            );

            jPanel3.setBackground(new java.awt.Color(255, 255, 255));
            jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Chọn điều kiện lọc"));
            jPanel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

            btnLoc.setBackground(new java.awt.Color(51, 255, 255));
            btnLoc.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
            btnLoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh_HeThong/filterNV.png"))); // NOI18N
            btnLoc.setText("Lọc");
            btnLoc.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnLocMouseClicked(evt);
                }
            });
            btnLoc.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnLocActionPerformed(evt);
                }
            });

            jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jLabel7.setText("Ngày bắt đầu:");

            jDateChooseLocNBD.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

            jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jLabel8.setText("Ngày kết thúc:");

            locTrangThai.setBorder(javax.swing.BorderFactory.createTitledBorder("Trạng thái"));
            locTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

            grTrangThai.add(jRadioDungGio);
            jRadioDungGio.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jRadioDungGio.setText("Đúng giờ");

            grTrangThai.add(jRadioTatCa);
            jRadioTatCa.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jRadioTatCa.setSelected(true);
            jRadioTatCa.setText("Tất cả");
            jRadioTatCa.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jRadioTatCaActionPerformed(evt);
                }
            });

            grTrangThai.add(jRadioChuaLam);
            jRadioChuaLam.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jRadioChuaLam.setText("Chưa làm");
            jRadioChuaLam.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jRadioChuaLamActionPerformed(evt);
                }
            });

            grTrangThai.add(jRadioTre);
            jRadioTre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jRadioTre.setText("Trễ");
            jRadioTre.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jRadioTreActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout locTrangThaiLayout = new javax.swing.GroupLayout(locTrangThai);
            locTrangThai.setLayout(locTrangThaiLayout);
            locTrangThaiLayout.setHorizontalGroup(
                    locTrangThaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(locTrangThaiLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(locTrangThaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(locTrangThaiLayout.createSequentialGroup()
                                                    .addComponent(jRadioTre, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jRadioTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(16, 16, 16))
                                            .addGroup(locTrangThaiLayout.createSequentialGroup()
                                                    .addComponent(jRadioDungGio, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                                                    .addComponent(jRadioChuaLam, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addContainerGap())))
            );
            locTrangThaiLayout.setVerticalGroup(
                    locTrangThaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(locTrangThaiLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(locTrangThaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jRadioDungGio)
                                            .addComponent(jRadioChuaLam))
                                    .addGap(2, 2, 2)
                                    .addGroup(locTrangThaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jRadioTre)
                                            .addComponent(jRadioTatCa))
                                    .addContainerGap(9, Short.MAX_VALUE))
            );

            locCaLam.setBorder(javax.swing.BorderFactory.createTitledBorder("Ca làm"));
            locCaLam.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

            ckCa3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            ckCa3.setText("ca 3");

            ckCaGay.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            ckCaGay.setText("Ca gãy");
            ckCaGay.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    ckCaGayActionPerformed(evt);
                }
            });

            ckCa2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            ckCa2.setText("ca 2");

            ckCa1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            ckCa1.setText("ca 1");
            ckCa1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    ckCa1ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout locCaLamLayout = new javax.swing.GroupLayout(locCaLam);
            locCaLam.setLayout(locCaLamLayout);
            locCaLamLayout.setHorizontalGroup(
                    locCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(locCaLamLayout.createSequentialGroup()
                                    .addGap(43, 43, 43)
                                    .addGroup(locCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ckCa1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ckCa2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                                    .addGroup(locCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ckCaGay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ckCa3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(41, 41, 41))
            );
            locCaLamLayout.setVerticalGroup(
                    locCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(locCaLamLayout.createSequentialGroup()
                                    .addGroup(locCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(ckCaGay)
                                            .addComponent(ckCa1))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(locCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(ckCa2)
                                            .addComponent(ckCa3))
                                    .addGap(0, 13, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addGap(43, 43, 43)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jDateChooseLocNBD, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jDateChooserLocNKT, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(40, 40, 40)
                                    .addComponent(locCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(78, 78, 78)
                                    .addComponent(locTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(155, 155, 155)
                                    .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(157, Short.MAX_VALUE))
            );
            jPanel3Layout.setVerticalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jDateChooseLocNBD, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jDateChooserLocNKT, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(locCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(locTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel4.setBackground(new java.awt.Color(19, 78, 44));
            jPanel4.setForeground(new java.awt.Color(255, 255, 255));
            jPanel4.setToolTipText("");

            jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
            jLabel5.setForeground(new java.awt.Color(255, 255, 255));
            jLabel5.setText("Quản lý lịch làm việc");

            javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
            jPanel4.setLayout(jPanel4Layout);
            jPanel4Layout.setHorizontalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(540, 540, 540)
                                    .addComponent(jLabel5)
                                    .addGap(244, 244, 244)
                                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(372, Short.MAX_VALUE))
            );
            jPanel4Layout.setVerticalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addContainerGap(58, Short.MAX_VALUE)
                                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(45, 45, 45))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addComponent(jLabel5)
                                    .addContainerGap(28, Short.MAX_VALUE))
            );

            jPanel2.setBackground(new java.awt.Color(204, 204, 204));

            btnThem.setBackground(new java.awt.Color(255, 255, 204));
            btnThem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
            btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh_HeThong/plus (1).png"))); // NOI18N
            btnThem.setText("Thêm");
            btnThem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnThemActionPerformed(evt);
                }
            });

            btnLamMoi.setBackground(new java.awt.Color(52, 213, 209));
            btnLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
            btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh_HeThong/undo.png"))); // NOI18N
            btnLamMoi.setText("Làm mới");
            btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnLamMoiActionPerformed(evt);
                }
            });

            btnSuaLLV.setBackground(new java.awt.Color(255, 153, 153));
            btnSuaLLV.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
            btnSuaLLV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh_HeThong/circular-arrow.png"))); // NOI18N
            btnSuaLLV.setText("Cập nhật");
            btnSuaLLV.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnSuaLLVActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                    .addGap(0, 0, Short.MAX_VALUE)
                                                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(btnSuaLLV, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(0, 0, Short.MAX_VALUE)))
                                    .addContainerGap())
            );
            jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(49, 49, 49)
                                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnSuaLLV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(54, Short.MAX_VALUE))
            );

            txtTimLLV.setText("MA NHAN VIEN");

            btnTimLLV.setBackground(new java.awt.Color(204, 255, 255));
            btnTimLLV.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
            btnTimLLV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh_HeThong/findNV.png"))); // NOI18N
            btnTimLLV.setText("Tìm");
            btnTimLLV.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnTimLLVActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
            jPanel6.setLayout(jPanel6Layout);
            jPanel6Layout.setHorizontalGroup(
                    jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(txtTimLLV, javax.swing.GroupLayout.PREFERRED_SIZE, 1156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(91, 91, 91)
                                    .addComponent(btnTimLLV, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 144, Short.MAX_VALUE))
            );
            jPanel6Layout.setVerticalGroup(
                    jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtTimLLV, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnTimLLV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(19, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanelLLVLayout = new javax.swing.GroupLayout(jPanelLLV);
            jPanelLLV.setLayout(jPanelLLVLayout);
            jPanelLLVLayout.setHorizontalGroup(
                    jPanelLLVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLLVLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanelLLVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLLVLayout.createSequentialGroup()
                                                    .addGap(0, 0, Short.MAX_VALUE)
                                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanelLLVLayout.createSequentialGroup()
                                                    .addGroup(jPanelLLVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanelLLVLayout.createSequentialGroup()
                                                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addGap(94, 94, 94)
                                                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1512, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(0, 0, Short.MAX_VALUE)))
                                    .addContainerGap())
                            .addGroup(jPanelLLVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelLLVLayout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addContainerGap()))
            );
            jPanelLLVLayout.setVerticalGroup(
                    jPanelLLVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLLVLayout.createSequentialGroup()
                                    .addContainerGap(116, Short.MAX_VALUE)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanelLLVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelLLVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelLLVLayout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addContainerGap(762, Short.MAX_VALUE)))
            );

            jPanel5.setPreferredSize(new java.awt.Dimension(250, 966));

            javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
            jPanel5.setLayout(jPanel5Layout);
            jPanel5Layout.setHorizontalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 250, Short.MAX_VALUE)
            );
            jPanel5Layout.setVerticalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 0, Short.MAX_VALUE)
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanelLLV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jPanelLLV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 871, Short.MAX_VALUE))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

        private void cbxNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxNhanVienActionPerformed
            // TODO add your handling code here:
        }//GEN-LAST:event_cbxNhanVienActionPerformed

        private void cbxCaLamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCaLamActionPerformed
            LocalDate currentDate = LocalDate.now();
            jDateChooseLocNBD.setDate(java.sql.Date.valueOf(currentDate));  // Đặt ngày hiện tại vào jDateChooser1

            String selectedCa = cbxCaLam.getSelectedItem().toString();

            String timeBD = "";
            String timeKT = "";

            switch (selectedCa) {
                case "Ca 1":
                    timeBD = "06:00";
                    timeKT = "14:00";
                    break;
                case "Ca 2":
                    timeBD = "14:00";
                    timeKT = "22:00";
                    break;
                case "Ca 3":
                    timeBD = "22:00";
                    timeKT = "06:00";
                    break;
                default:
                    timeBD = "";
                    timeKT = "";
                    break;
            }
            txtTimeBD.setText(timeBD);
            txtTimeKT.setText(timeKT);

/*
    // Cập nhật ngày cho jDateChooser2 và jDateChooser3
    Date selectedDate = jDateChooser3.getDate();  // Lấy ngày bắt đầu làm việc

    if (selectedDate != null) {
        // Cập nhật cho jDateChooser2
        if (selectedCa.equals("Ca 3")) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(selectedDate);
            cal.add(Calendar.DAY_OF_MONTH, 1);  // Thêm 1 ngày cho ca 3
            jDateChooser2.setDate(cal.getTime());  // Thiết lập ngày kết thúc
        } else {
            jDateChooser2.setDate(selectedDate);  // Ngày kết thúc giống ngày bắt đầu
        }
    }*/
        }//GEN-LAST:event_cbxCaLamActionPerformed

        private void cbxTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTrangThaiActionPerformed
            // TODO add your handling code here:
        }//GEN-LAST:event_cbxTrangThaiActionPerformed

        private void jRadioChuaLamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioChuaLamActionPerformed
            // TODO add your handling code here:
        }//GEN-LAST:event_jRadioChuaLamActionPerformed

        private void ckCaGayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckCaGayActionPerformed
            // TODO add your handling code here:
        }//GEN-LAST:event_ckCaGayActionPerformed

        private void jTableLLVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLLVMouseClicked
            int row = jTableLLV.rowAtPoint(evt.getPoint());
            if (row >= 0) {
                // Lấy dữ liệu từ các cột của dòng đã chọn
                String maLLV = jTableLLV.getValueAt(row, 0).toString();
                String maNV = jTableLLV.getValueAt(row, 1).toString();
                String ngayBD = jTableLLV.getValueAt(row, 2).toString();
                String ngayKT = jTableLLV.getValueAt(row, 3).toString();
                String trangThai = jTableLLV.getValueAt(row, 4).toString();
                String caLam = jTableLLV.getValueAt(row, 5).toString();
                txtMaLLV.setText(maLLV);
                cbxNhanVien.setSelectedItem(maNV);
                cbxCaLam.setSelectedItem(caLam);
                cbxTrangThai.setSelectedItem(trangThai);

                try {
                    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                    Date dateBD = dateTimeFormat.parse(ngayBD);
                    Date dateKT = dateTimeFormat.parse(ngayKT);
                    jDateChooserBD.setDate(dateBD);
                    jDateChooserKT.setDate(dateKT);

                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                    String timeBD = timeFormat.format(dateBD);
                    String timeKT = timeFormat.format(dateKT);

                    txtTimeBD.setText(timeBD);
                    txtTimeKT.setText(timeKT);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }//GEN-LAST:event_jTableLLVMouseClicked

        private void lblTimeBDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTimeBDMouseClicked
            Point location = lblTimeBD.getLocationOnScreen();
            timePicker1.showPopup(this, location.x, location.y + lblTimeBD.getHeight());
        }//GEN-LAST:event_lblTimeBDMouseClicked

        private void lblTimeKTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTimeKTMouseClicked
            Point location = lblTimeKT.getLocationOnScreen();
            timePicker2.showPopup(this, location.x, location.y + lblTimeBD.getHeight());
        }//GEN-LAST:event_lblTimeKTMouseClicked

        private void cbxCaLamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxCaLamMouseClicked

        }//GEN-LAST:event_cbxCaLamMouseClicked

        private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
            // TODO add your handling code here:
        }//GEN-LAST:event_btnLocActionPerformed

        private void btnLocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocMouseClicked
            LocalDate ngayBatDau = jDateChooseLocNBD.getDate() != null ? jDateChooseLocNBD.getDate().toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDate() : null;
            LocalDate ngayKetThuc = jDateChooserLocNKT.getDate() != null ? jDateChooserLocNKT.getDate().toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDate() : null;

            String trangThai = "";
            if (jRadioDungGio.isSelected()) {
                trangThai = "Dung gio";
            } else if (jRadioChuaLam.isSelected()) {
                trangThai = "Chua lam";
            } else if (jRadioTatCa.isSelected()) {
                trangThai = "Tat ca";
            } else if (jRadioTre.isSelected()) {
                trangThai = "Di tre";
            }
            List<String> caLam = new ArrayList<>();
            if (ckCa1.isSelected()) {
                caLam.add("Ca 1");
            }
            if (ckCa2.isSelected()) {
                caLam.add("Ca 2");
            }
            if (ckCa3.isSelected()) {
                caLam.add("Ca 3");
            }
            if (ckCaGay.isSelected()) {
                caLam.add("Ca gãy");
            }


            try {
                filterData(ngayBatDau, ngayKetThuc, trangThai, caLam);
            } catch (Exception ex) {
                Logger.getLogger(FrmLichLamViec.class.getName()).log(Level.SEVERE, null, ex);
            }
        }//GEN-LAST:event_btnLocMouseClicked

        private void ckCa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckCa1ActionPerformed
            // TODO add your handling code here:
        }//GEN-LAST:event_ckCa1ActionPerformed

        private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
            try {
                // Lấy dữ liệu từ form
                String maNV = (String) cbxNhanVien.getSelectedItem();
                String caLam = (String) cbxCaLam.getSelectedItem();
                String soCa = "";
                String trangThai = (String) cbxTrangThai.getSelectedItem();

                Date ngayBatDau = jDateChooserBD.getDate();
                Date ngayKetThuc = jDateChooserKT.getDate();
                String timeBD = txtTimeBD.getText();
                String timeKT = txtTimeKT.getText();

                if (maNV == null || caLam == null || ngayBatDau == null || ngayKetThuc == null || timeBD == null || timeKT == null) {
                    MessageDialogHelper.showErrorDialog(this, "Vui lòng điền đầy đủ thông tin", "Lỗi");
                    return;
                }

                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

                LocalDate ngayBD = ngayBatDau.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalTime gioBD = LocalTime.parse(timeBD, timeFormatter);
                LocalDateTime timeBatDau = LocalDateTime.of(ngayBD, gioBD);

                LocalDate ngayKT = ngayKetThuc.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalTime gioKT = LocalTime.parse(timeKT, timeFormatter);
                LocalDateTime timeKetThuc = LocalDateTime.of(ngayKT, gioKT);

                List<LichLamViec> lichLamViecList = new ArrayList<>();

                for (LocalDate date = ngayBD; !date.isAfter(ngayKT); date = date.plusDays(1)) {

                    if (caLam.equals("Ca gãy")) {
                        soCa = "4";
                    } else {
                        soCa = caLam.replaceAll("\\D+", "");
                    }
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                    String formattedDate = date.format(dateFormatter);

                    //ma lich
                    String maLLV = maNV.trim() + soCa + formattedDate;

                    LichLamViec llv = new LichLamViec();
                    llv.setMaLichLamViec(maLLV);
                    llv.setMaNhanVien(maNV);
                    llv.setTenCa(caLam);
                    llv.setTrangThai(trangThai);

                    llv.setGioBatDau(LocalDateTime.of(date, gioBD));

                    if (gioKT.isBefore(gioBD)) {
                        timeKetThuc = LocalDateTime.of(date.plusDays(1), gioKT);

                    } else {
                        timeKetThuc = LocalDateTime.of(date, gioKT);
                    }
                    llv.setGioKetThuc(timeKetThuc);

                    // Thêm đối tượng vào danh sách
                    lichLamViecList.add(llv);
                }

                // Gọi hàm thêm tất cả lịch làm việc từ DAO
                for (LichLamViec lichLamViec : lichLamViecList) {
                    if (llv_dao.insert(lichLamViec)) {
                        System.out.println("Them lich thanh cong");
                    } else {
                        MessageDialogHelper.showMessageDialog(this, "Thêm lịch thất bại", "Thông báo");
                        return;
                    }
                }

                hienThiDanhSachLichLamViec(llv_dao.findAll());  // Cập nhật lại bảng dữ liệu
                // txtMaLLV.setText(phatSinhMaLLVTuDong());

            } catch (DateTimeParseException e) {
                String errorMessage = "Thời gian không hợp lệ. Vui lòng kiểm tra lại định dạng giờ (HH:mm).";
                MessageDialogHelper.showErrorDialog(this, errorMessage, "Lỗi");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                MessageDialogHelper.showErrorDialog(this, e.getMessage(), "Lỗi");
            }
        }//GEN-LAST:event_btnThemActionPerformed

        private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
            txtMaLLV.setText("");
            txtTimeBD.setText("");
            txtTimeKT.setText("");
            cbxCaLam.setSelectedIndex(0);
            cbxNhanVien.setSelectedIndex(0);
            cbxTrangThai.setSelectedIndex(0);
        }//GEN-LAST:event_btnLamMoiActionPerformed

        private void btnSuaLLVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaLLVActionPerformed
            try {
                // Lấy dữ liệu từ form
                String maLLV = txtMaLLV.getText();
                String maNhanVien = (String) cbxNhanVien.getSelectedItem();
                String caLam = (String) cbxCaLam.getSelectedItem();

                String gioBatDauStr = txtTimeBD.getText();
                String gioKetThucStr = txtTimeKT.getText();

                LocalTime gioBatDauTime = LocalTime.parse(gioBatDauStr);
                LocalTime gioKetThucTime = LocalTime.parse(gioKetThucStr);

                LocalDateTime gioBatDau = LocalDateTime.of(jDateChooserBD.getDate().toInstant()
                                .atZone(ZoneId.systemDefault()).toLocalDate(),
                        gioBatDauTime);
                LocalDateTime gioKetThuc = LocalDateTime.of(jDateChooserKT.getDate().toInstant()
                                .atZone(ZoneId.systemDefault()).toLocalDate(),
                        gioKetThucTime);

                String trangThai = (String) cbxTrangThai.getSelectedItem();  // Trạng thái (Lấy từ ComboBox)

                if (gioKetThuc.isBefore(gioBatDau)) {
                    MessageDialogHelper.showErrorDialog(this, "Giờ kết thúc phải sau giờ bắt đầu", "Lỗi");
                    return;
                }

                LichLamViec llv = new LichLamViec(maLLV, maNhanVien, gioBatDau, gioKetThuc, trangThai, caLam);

                if (llv_dao.update(llv)) {
                    MessageDialogHelper.showMessageDialog(this, "Cập nhật lịch làm việc thành công", "Thông báo");
                    hienThiDanhSachLichLamViec(llv_dao.findAll());
                } else {
                    MessageDialogHelper.showMessageDialog(this, "Cập nhật lịch làm việc thất bại", "Cảnh báo");
                }
            } catch (Exception e) {
                e.printStackTrace();
                MessageDialogHelper.showErrorDialog(this, e.getMessage(), "Lỗi");
            }
        }//GEN-LAST:event_btnSuaLLVActionPerformed

        private void jRadioTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioTatCaActionPerformed
            // TODO add your handling code here:
        }//GEN-LAST:event_jRadioTatCaActionPerformed

        private void jRadioTreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioTreActionPerformed
            // TODO add your handling code here:
        }//GEN-LAST:event_jRadioTreActionPerformed

        private void btnTimLLVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimLLVActionPerformed
            String tuKhoa = txtTimLLV.getText().trim();

            if (tuKhoa.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khóa tìm kiếm.");
                return;
            }

            try {
                List<LichLamViec> danhSachLLV = llv_dao.findAll();
                List<LichLamViec> kqTimKiem = new ArrayList<>();

                for (LichLamViec lichLamViec : danhSachLLV) {
                    if (lichLamViec.getMaNhanVien().toLowerCase().contains(tuKhoa.toLowerCase())) {
                        kqTimKiem.add(lichLamViec);
                    }
                }

                if (kqTimKiem.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy lịch làm việc với từ khóa: " + tuKhoa);
                } else {
                    hienThiDanhSachLichLamViec(kqTimKiem);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi tìm kiếm: " + e.getMessage());
                e.printStackTrace();
            }
        }//GEN-LAST:event_btnTimLLVActionPerformed

        /**
         * @param args the command line arguments
         */
        public static void main(String args[]) {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        new FrmLichLamViec().setVisible(true);
                    } catch (Exception ex) {
                        Logger.getLogger(FrmLichLamViec.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnLamMoi;
        private javax.swing.JButton btnLoc;
        private javax.swing.JButton btnSuaLLV;
        private javax.swing.JButton btnThem;
        private javax.swing.JButton btnTimLLV;
        private JComboBox<String> cbxCaLam;
        private JComboBox<String> cbxNhanVien;
        private JComboBox<String> cbxTrangThai;
        private javax.swing.JCheckBox ckCa1;
        private javax.swing.JCheckBox ckCa2;
        private javax.swing.JCheckBox ckCa3;
        private javax.swing.JCheckBox ckCaGay;
        private javax.swing.Box.Filler filler1;
        private javax.swing.ButtonGroup grTrangThai;
        private com.toedter.calendar.JDateChooser jDateChooseLocNBD;
        private com.toedter.calendar.JDateChooser jDateChooserBD;
        private com.toedter.calendar.JDateChooser jDateChooserKT;
        private com.toedter.calendar.JDateChooser jDateChooserLocNKT;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel10;
        private javax.swing.JLabel jLabel11;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JLabel jLabel9;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JPanel jPanel4;
        private javax.swing.JPanel jPanel5;
        private javax.swing.JPanel jPanel6;
        public javax.swing.JPanel jPanelLLV;
        private javax.swing.JRadioButton jRadioChuaLam;
        private javax.swing.JRadioButton jRadioDungGio;
        private javax.swing.JRadioButton jRadioTatCa;
        private javax.swing.JRadioButton jRadioTre;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTable jTableLLV;
        private javax.swing.JLabel lblTimeBD;
        private javax.swing.JLabel lblTimeKT;
        private javax.swing.JPanel locCaLam;
        private javax.swing.JPanel locTrangThai;
        private com.raven.swing.TimePicker timePicker1;
        private com.raven.swing.TimePicker timePicker2;
        private javax.swing.JTextField txtMaLLV;
        private javax.swing.JTextField txtTimLLV;
        private javax.swing.JTextField txtTimeBD;
        private javax.swing.JTextField txtTimeKT;
        // End of variables declaration//GEN-END:variables

 /*   private String phatSinhMaLLVTuDong() throws Exception {
    txtMaLLV.setEditable(false);
    txtMaLLV.setEnabled(false);

    llv_dao = new DAO_LichLamViec();
    int soLuongLichLamViec = llv_dao.getSoLuongLichLamViec();
    system.out.println("Số lượng lịch làm việc hiện tại: " + soLuongLichLamViec);

    List<LichLamViec> danhSachLichLamViec = llv_dao.findAll();

    String maLichLamViecMoi;

    if (soLuongLichLamViec < 9) {
        maLichLamViecMoi = "LLV00" + (soLuongLichLamViec + 1);
    } else {
        maLichLamViecMoi = "LLV0" + (soLuongLichLamViec + 1);
    }
    boolean maTonTai = false;
    for (LichLamViec llv : danhSachLichLamViec) {
        if (llv.getMaLichLamViec().equalsIgnoreCase(maLichLamViecMoi)) {
            maTonTai = true;
            break;
        }
    }

    while (maTonTai) {
        if (soLuongLichLamViec < 9) {
            maLichLamViecMoi = "LLV00" + (soLuongLichLamViec + 2);  // Sinh mã tiếp theo
        } else {
            maLichLamViecMoi = "LLV0" + (soLuongLichLamViec + 2);  // Sinh mã tiếp theo
        }
        maTonTai = false;
        for (LichLamViec llv : danhSachLichLamViec) {
            if (llv.getMaLichLamViec().equalsIgnoreCase(maLichLamViecMoi)) {
                maTonTai = true;
                break;
            }
        }
    }
    return maLichLamViecMoi;
}*/

        private void loadNhanVienIntoComboBox(JComboBox<String> cbxNhanVien) {
            try {
                List<NhanVien> danhSachNhanVien = nv_dao.findAll();
                cbxNhanVien.removeAllItems();

                // Thêm nhân viên vào combobox
                for (NhanVien nv : danhSachNhanVien) {
                    cbxNhanVien.addItem(nv.getMaNhanVien());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Hàm hiển thị danh sách lịch làm việc lên bảng JTable
        public void hienThiDanhSachLichLamViec(List<LichLamViec> danhSachLichLamViec) {
            DefaultTableModel model = (DefaultTableModel) jTableLLV.getModel();

            model.setRowCount(0);

            for (LichLamViec llv : danhSachLichLamViec) {
                Object[] row = new Object[]{
                        llv.getMaLichLamViec(),
                        llv.getMaNhanVien(),
                        llv.getGioBatDau(),
                        llv.getGioKetThuc(),
                        llv.getTrangThai(),
                        llv.getTenCa()
                };

                model.addRow(row);
            }
            model.fireTableDataChanged();
        }

        private void filterData(LocalDate ngayBatDau, LocalDate ngayKetThuc, String trangThai, List<String> caLam) throws Exception {
            List<LichLamViec> danhSachDaLoc = new ArrayList<>();
            List<LichLamViec> danhSachLichLamViec = llv_dao.findAll();

            for (LichLamViec llv : danhSachLichLamViec) {
                boolean isDateInRange = (ngayBatDau == null || !llv.getGioBatDau().toLocalDate().isBefore(ngayBatDau)) &&
                        (ngayKetThuc == null || !llv.getGioBatDau().toLocalDate().isAfter(ngayKetThuc));

                boolean isTrangThaiValid = (trangThai.equals("Tat ca") || llv.getTrangThai().equals(trangThai));

                boolean isCaLamValid = (caLam.isEmpty() || caLam.contains(llv.getTenCa()));

                // Nếu tất cả điều kiện đều thỏa mãn, thêm vào danh sách đã lọc
                if (isDateInRange && isTrangThaiValid && isCaLamValid) {
                    danhSachDaLoc.add(llv);
                }
            }
            hienThiDanhSachLichLamViec(danhSachDaLoc);
        }


    }
