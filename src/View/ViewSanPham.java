/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.SanPhamModel.SanPham;
import Model.SanPhamModel.SanPhamChiTiet;
import Model.BanHangModel.modelSize;
import Repository.RepoSanPham;
import Repository.BanHangRepository.RepoSize;
import Repository.repoSanPhamct;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.TabbedPaneUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class ViewSanPham extends javax.swing.JPanel {

    private RepoSize repoSize = new RepoSize();
    private RepoSanPham repo = new RepoSanPham();
    private repoSanPhamct repoCT = new repoSanPhamct();
    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultTableModel dtm2 = new DefaultTableModel();
    private String duongDanAnh = "C:\\Users\\Admin\\OneDrive\\Desktop\\DuAn1-master (3)\\DuAn1-master\\src\\img";
    private byte[] person_image = null;

    /**
     * Creates new form ViewSanPhamTest
     */
    public ViewSanPham() throws SQLException {
        initComponents();
//        tab.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
        //               int selectedIndex = tab.getSelectedIndex(); // Lấy tab đang được chọn
//                if (selectedIndex == 0) { // Tab "Sản phẩm"
//
//                } else if (selectedIndex == 1) { // Tab "Sản phẩm chi tiết"
//                    txtGiaSanPham.setVisible(true);
//                    cbSizeS.setVisible(true);
//                    cbSizeM.setVisible(true);
//                    cbSizeL.setVisible(true);
//                    lbSize.setVisible(true);
//                    lbGiaSanPham.setVisible(true);
//
//                }
//            }
//        });

//        rdoall.setSelected(true);
//
        showTableByTinhTrang(null);
//
////        lbThongBaoL.setVisible(true);
////        lbThongBaoS.setVisible(true);
////        lbThongBaoM.setVisible(true);
//        lbSize.setVisible(true);
//
//        cbSizeS.setVisible(true);
//        cbSizeL.setVisible(true);
//        cbSizeM.setVisible(true);
//        btThemSanPhamct.setVisible(false);
//        btSuaSanPhamct1.setVisible(false);

    }

    private void showDatatable(List<SanPham> lst) {
        dtm = (DefaultTableModel) tbDoUong.getModel();
        dtm.setRowCount(0);
        for (SanPham sp : lst) {
            dtm.addRow(new Object[]{
                sp.getMa(), sp.getTen(), sp.getGia(), sp.isTrangThai() ? "không bán" : "dang bán"
            });
        }

        // Tạo renderer để căn giữa dữ liệu
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho tất cả các cột
        for (int i = 0; i < dtm.getColumnCount(); i++) {
            tbDoUong.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void showDatatableSPCT(List<SanPhamChiTiet> lst) {
        dtm2 = (DefaultTableModel) tb_SanPhamct.getModel();
        dtm2.setRowCount(0);
        for (SanPhamChiTiet spct : lst) {
            dtm2.addRow(new Object[]{
                spct.getMaSPCT(), spct.getTenSPCT(), spct.getSize(), spct.getGiaSP(), spct.getGiaSize(), spct.getDonGia(), spct.isTinhTrangSPCT() ? "không bán" : "dang bán"
            });
        }

        // Tạo renderer để căn giữa dữ liệu
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho tất cả các cột
        for (int i = 0; i < dtm2.getColumnCount(); i++) {
            tb_SanPhamct.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void showTableByTinhTrang(Boolean tt) {
        if (rdoall.isSelected()) {
            showDatatable(repo.getAll(null));
        } else if (rdosd.isSelected()) {
            showDatatable(repo.getAll(true));
        } else if (rdoksd.isSelected()) {
            showDatatable(repo.getAll(false));
        }

    }

//    public void fillTable(ArrayList<SanPham> list) {
//        model = (DefaultTableModel) tbDoUong.getModel();
//        model.setRowCount(0);
//        for (SanPham sanPham : list) {
//            model.addRow(sanPham.toDataRow());
//        }
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbDoUong = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_SanPhamct = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        btTimKiem = new javax.swing.JButton();
        rdoall = new javax.swing.JRadioButton();
        rdosd = new javax.swing.JRadioButton();
        rdoksd = new javax.swing.JRadioButton();
        btLoc = new javax.swing.JButton();
        txtGiaMin = new javax.swing.JTextField();
        txtGiaMax = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btNew1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        rdoSuDung = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        rdoKhongSuDung = new javax.swing.JRadioButton();
        txt_TenSp = new javax.swing.JTextField();
        lbGiaSanPham = new javax.swing.JLabel();
        txtGiaSanPham = new javax.swing.JTextField();
        txtMaSp = new javax.swing.JTextField();
        lbhinhanh = new javax.swing.JLabel();
        btThemAnh = new javax.swing.JButton();
        btThem = new javax.swing.JButton();
        btSua = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btSuaSanPhamct1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lbThongBaoL = new javax.swing.JLabel();
        lbSize = new javax.swing.JLabel();
        cbSizeM = new javax.swing.JCheckBox();
        cbSizeL = new javax.swing.JCheckBox();
        lbThongBaoS = new javax.swing.JLabel();
        cbSizeS = new javax.swing.JCheckBox();
        lbThongBaoM = new javax.swing.JLabel();
        btThemSanPhamct = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(2000, 910));
        setPreferredSize(new java.awt.Dimension(1700, 850));

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        tbDoUong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Giá tiền", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDoUong.setRowHeight(30);
        tbDoUong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDoUongMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbDoUong);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        tb_SanPhamct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "size", "giá sản phẩm", "giá size", "đơn giá", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_SanPhamct.setRowHeight(30);
        tb_SanPhamct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_SanPhamctMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_SanPhamct);

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        btTimKiem.setText("Tìm kiếm");
        btTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTimKiemActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoall);
        rdoall.setSelected(true);
        rdoall.setText("All");
        rdoall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoallActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdosd);
        rdosd.setText("đang bán");
        rdosd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdosdActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoksd);
        rdoksd.setText("không bán");
        rdoksd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoksdActionPerformed(evt);
            }
        });

        btLoc.setText("Lọc theo giá");
        btLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLocActionPerformed(evt);
            }
        });

        txtGiaMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaMinActionPerformed(evt);
            }
        });

        txtGiaMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaMaxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 51, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btTimKiem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(rdoall)
                .addGap(18, 18, 18)
                .addComponent(rdosd)
                .addGap(18, 18, 18)
                .addComponent(rdoksd)
                .addGap(82, 82, 82)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btLoc)
                .addGap(18, 18, 18)
                .addComponent(txtGiaMin, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGiaMax, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtGiaMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btLoc)
                                .addComponent(txtGiaMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdoall)
                                .addComponent(rdosd)
                                .addComponent(rdoksd))
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btTimKiem)))))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        btNew1.setText("làm mới");
        btNew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNew1ActionPerformed(evt);
            }
        });

        jLabel10.setText("Trạng thái");

        buttonGroup1.add(rdoSuDung);
        rdoSuDung.setSelected(true);
        rdoSuDung.setText("đang bán");
        rdoSuDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoSuDungActionPerformed(evt);
            }
        });

        jLabel11.setText("Tên sản phẩm");

        jLabel13.setText("Mã sản phẩm");

        buttonGroup1.add(rdoKhongSuDung);
        rdoKhongSuDung.setText("không bán");
        rdoKhongSuDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoKhongSuDungActionPerformed(evt);
            }
        });

        txt_TenSp.setPreferredSize(new java.awt.Dimension(64, 25));
        txt_TenSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TenSpActionPerformed(evt);
            }
        });

        lbGiaSanPham.setText("Giá sản phẩm");

        txtGiaSanPham.setPreferredSize(new java.awt.Dimension(64, 25));
        txtGiaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaSanPhamActionPerformed(evt);
            }
        });

        txtMaSp.setEditable(false);
        txtMaSp.setPreferredSize(new java.awt.Dimension(64, 25));
        txtMaSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSpActionPerformed(evt);
            }
        });

        lbhinhanh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbhinhanh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbhinhanh.setPreferredSize(new java.awt.Dimension(160, 237));

        btThemAnh.setText("thêm ảnh");
        btThemAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThemAnhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbhinhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(btThemAnh)))
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10)
                        .addComponent(lbGiaSanPham)
                        .addComponent(txtGiaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(rdoSuDung, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(rdoKhongSuDung)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtMaSp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txt_TenSp, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaSp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addGap(7, 7, 7)
                        .addComponent(txt_TenSp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoSuDung)
                            .addComponent(rdoKhongSuDung)))
                    .addComponent(lbhinhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbGiaSanPham)
                    .addComponent(btThemAnh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGiaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btThem.setBackground(new java.awt.Color(255, 0, 51));
        btThem.setText("Thêm sản phẩm");
        btThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThemActionPerformed(evt);
            }
        });

        btSua.setText("Sửa sản phẩm");
        btSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btThem, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btSua, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btNew1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSua, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btThem, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNew1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        btSuaSanPhamct1.setText("Sửa sản phẩm chi tiết");
        btSuaSanPhamct1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuaSanPhamct1ActionPerformed(evt);
            }
        });

        lbThongBaoL.setText("Size L + 10.000");

        lbSize.setText("Size");

        cbSizeM.setText("M");

        cbSizeL.setText("L");

        lbThongBaoS.setText("Size S + 0");

        cbSizeS.setText("S");

        lbThongBaoM.setText("Size M + 7.000");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbSize, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(cbSizeL)
                        .addGap(18, 18, 18)
                        .addComponent(lbThongBaoL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(cbSizeM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbThongBaoM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(cbSizeS)
                        .addGap(18, 18, 18)
                        .addComponent(lbThongBaoS, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbSize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSizeS)
                    .addComponent(lbThongBaoS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSizeM)
                    .addComponent(lbThongBaoM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSizeL)
                    .addComponent(lbThongBaoL))
                .addContainerGap())
        );

        btThemSanPhamct.setText("Thêm sản phẩm chi tiết");
        btThemSanPhamct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThemSanPhamctActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btThemSanPhamct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btSuaSanPhamct1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btThemSanPhamct, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSuaSanPhamct1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 287, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
private void detailSanPham(int index) throws SQLException {
        String maSp = tbDoUong.getValueAt(index, 0).toString();

        SanPham sp = null;

        if (rdoall.isSelected()) {
            sp = repo.getAll(null).get(index);
        } else if (rdosd.isSelected()) {
            sp = repo.getAll(true).get(index);
        } else if (rdoksd.isSelected()) {
            sp = repo.getAll(false).get(index);
        }

        txtMaSp.setText(String.valueOf(sp.getMa()));
        txt_TenSp.setText(sp.getTen());
        if (sp.isTrangThai()) {
            rdoSuDung.setSelected(true);
        } else {
            rdoKhongSuDung.setSelected(true);
        }
        loadImageFromDatabase(sp.getHinhAnh());

    }

    private void detailSanPhamChiTiet(int index) throws SQLException {
        String maSp = tb_SanPhamct.getValueAt(index, 0).toString();
        SanPhamChiTiet sp = null;
        List<SanPhamChiTiet> sanPhamChiTietList = new ArrayList<>();

        if (rdoall.isSelected()) {
            sanPhamChiTietList = repoCT.getAll1(null);
        } else if (rdosd.isSelected()) {
            sanPhamChiTietList = repoCT.getAll1(true);
        } else if (rdoksd.isSelected()) {
            sanPhamChiTietList = repoCT.getAll1(false);
        }

        if (index < 0 || index >= sanPhamChiTietList.size()) {
            throw new IndexOutOfBoundsException("Index ngoài phạm vi danh sách sản phẩm.");
        }

        sp = sanPhamChiTietList.get(index);

        txtMaSp.setText(String.valueOf(sp.getMaSPCT()));
        txt_TenSp.setText(sp.getTenSPCT());
        txtGiaSanPham.setText(String.valueOf(sp.getGiaSP()));
        if (sp.isTinhTrangSPCT()) {
            rdoSuDung.setSelected(true);
        } else {
            rdoKhongSuDung.setSelected(true);
        }
//    cbSizeS

    }

    private int clickCount = 0;
    private int lastClickedRow = -1;
    private void rdoSuDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoSuDungActionPerformed
        // TODO add your handling code here:
//        if(rdoSuDung.isSelected()){
//            showDatatable(repo.getAll(true));
//        }else
//        {
//            showDatatable(repo.getAll(false));
//        }

    }//GEN-LAST:event_rdoSuDungActionPerformed

    private void rdoKhongSuDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoKhongSuDungActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoKhongSuDungActionPerformed

    private void txt_TenSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TenSpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TenSpActionPerformed
/////
//

    public void reset2() {
        txtMaSp.setText("");
        txt_TenSp.setText("");

        rdoSuDung.isSelected();
        lbhinhanh.setText("HINH ANH");
        lbhinhanh.setIcon(null);
        txtTimKiem.setText("");
        txtGiaMax.setText("");
        txtGiaMin.setText("");
        txtGiaSanPham.setText("");
//        btSua.setVisible(true);
//        btThem.setVisible(true);
//        btSuaSanPhamct1.setVisible(false);
//        btThemSanPhamct.setVisible(false);
// btTimKiem.setVisible(true);
//        txtTimKiem.setVisible(true);
//        btLoc.setVisible(true);
//        txtGiaMax.setVisible(true);
//        txtGiaMin.setVisible(true);
//        rdoall.setVisible(true);
//        rdoksd.setVisible(true);
//        rdosd.setVisible(true);
    }

    public void reset1() {
//        txtMaSp.setText("");
//        txt_TenSp.setText("");
//
//        rdoSuDung.isSelected();
//        lbhinhanh.setText("HINH ANH");
//        lbhinhanh.setIcon(null);
//        txtTimKiem.setText("");
//        txtGiaMax.setText("");
//        txtGiaMin.setText("");
//        //txtGiaSanPham.setText("");
//        btSua.setVisible(true);
//        btThem.setVisible(true);
//        btSuaSanPhamct1.setVisible(false);
//        btThemSanPhamct.setVisible(false);
//        btTimKiem.setVisible(true);
//        txtTimKiem.setVisible(true);
//        btLoc.setVisible(true);
//        txtGiaMax.setVisible(true);
//        txtGiaMin.setVisible(true);
//        rdoall.setVisible(true);
//        rdoksd.setVisible(true);
//        rdosd.setVisible(true);

    }
///

    private void btSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuaActionPerformed
        // TODO add your handling code here:
        try {

            int index = tbDoUong.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return; // Nếu không có dòng nào được chọn, dừng thực hiện
            }

            SanPham sp = getFormData(); // Giả định đây là đối tượng chứa dữ liệu sản phẩm
            if (sp == null) {
                JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ, vui lòng kiểm tra lại.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                txtMaSp.setBackground(Color.PINK);
                return;
            } else {
                txtMaSp.setBackground(null);
            }
            if (txt_TenSp.getText().isEmpty()) {
                txt_TenSp.setBackground(Color.PINK);
                JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống.", "Thông báo", JOptionPane.WARNING_MESSAGE);

                return;
            } else {
                txt_TenSp.setBackground(null);
            }

            int ma = 0;
            if (rdoall.isSelected()) {
                ma = repo.getAll(null).get(index).getMa();
            } else if (rdosd.isSelected()) {
                ma = repo.getAll(true).get(index).getMa();
            } else if (rdoksd.isSelected()) {
                ma = repo.getAll(false).get(index).getMa();
            }
            repo.updateSp(getFormData(), ma);

            if (rdoall.isSelected()) {
                showTableByTinhTrang(null);
            } else if (rdosd.isSelected()) {
                showTableByTinhTrang(true);
            } else if (rdoksd.isSelected()) {
                showTableByTinhTrang(false);
            }

            showDatatableSPCT(repoCT.getAllSpctByMa(ma));

            tbDoUong.setRowSelectionInterval(index, index);

            JOptionPane.showMessageDialog(this, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra trong quá trình cập nhật.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btSuaActionPerformed
    private boolean isProductExist(String productName) {
        // Kiểm tra xem tên sản phẩm có tồn tại trong danh sách sản phẩm không
        for (SanPham product : repo.getAll(null)) {
            if (product.getTen().equalsIgnoreCase(productName)) {
                return true; // Sản phẩm đã tồn tại
            }
        }
        return false; // Sản phẩm không tồn tại
    }
    private void btThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemActionPerformed
        
        SanPham newProduct = getFormData();

        // Kiểm tra xem dữ liệu nhập có hợp lệ không
        if (newProduct == null || newProduct.getTen().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            txt_TenSp.setBackground(Color.PINK);
            return; // Dừng lại nếu dữ liệu không hợp lệ
        } else {
            txt_TenSp.setBackground(null);
        }

        // Kiểm tra sản phẩm đã tồn tại chưa
        if (isProductExist(newProduct.getTen())) {
            JOptionPane.showMessageDialog(this, "Sản phẩm đã tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            txt_TenSp.setBackground(Color.PINK);
            return; // Dừng lại nếu sản phẩm đã tồn tại
        } else {
            txt_TenSp.setBackground(null);
        }

        repo.addSp(newProduct);
        // Cập nhật lại bảng sau khi thêm sản phẩm
        showDatatable(repo.getAll(null));

        tbDoUong.setRowSelectionInterval((tbDoUong.getRowCount() - 1), (tbDoUong.getRowCount() - 1));

        int maSpNew = Integer.parseInt(tbDoUong.getValueAt((tbDoUong.getRowCount() - 1), 0).toString());

        System.out.println("MaSpNew: " + maSpNew);
        System.out.println("GiaSP : " + newProduct.getGia());

        repo.addSpct(newProduct, maSpNew);

        List<SanPhamChiTiet> spctsList = repoCT.getAllSpctByMa(maSpNew);
        showDatatableSPCT(spctsList);

    }//GEN-LAST:event_btThemActionPerformed
    private SanPham getFormData() {
        // B1: Doc toan bo du lieu tu giao dien 
        int ma = Integer.parseInt(txtMaSp.getText());
        String ten = txt_TenSp.getText();
        double gia;
        try {
            gia = Double.parseDouble(txtGiaSanPham.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá tiền không hợp lệ!");
            return null; // Hoặc xử lý theo logic khác
        }

        Boolean trangThai = rdoSuDung.isSelected();
        ImageIcon imageIcon = (ImageIcon) lbhinhanh.getIcon();
        byte[] imageBytes = null;

        if (imageIcon != null) {
            imageBytes = convertImageToByteArray(imageIcon);
        }
        // B2: Return doi tuong tra ve 
        return new SanPham(ma, ten, gia, trangThai, imageBytes);
    }
//
//    private void showDataTable(List<SanPham> lsts) {
//        model.setRowCount(0); // Xóa các dòng cũ trong bảng
//        for (SanPham sp : lsts) {
//            model.addRow(new Object[]{
//                sp.getMaSanPham(),
//                sp.getTenSanPham(),
//                sp.getGiaTien(),
//                sp.isTrangThai() ? "Sử dụng" : "Không sử dụng",
//                sp.getHinhAnh()
//            });
//        }
//    }


    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTimKiemActionPerformed
        try {
            // Kiểm tra trường hợp người dùng không nhập tên sản phẩm

            if (txtTimKiem.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên sản phẩm!");
                txtTimKiem.setBackground(Color.PINK);
                return; // Dừng lại nếu chưa nhập gì
            } else {
                txtTimKiem.setBackground(null);
            }

            // Lọc danh sách sản phẩm với tên chứa chuỗi tìm kiếm (không phân biệt chữ hoa/thường)
            List<SanPham> listSearch = repo.getAll(null).stream()
                    .filter(s -> s.getTen().contains(txtTimKiem.getText()))
                    .collect(Collectors.toList());

            // Kiểm tra nếu không có kết quả tìm kiếm
            // Hiển thị kết quả tìm kiếm
            showDatatable(listSearch);

        } catch (Exception e) {
            // Xử lý ngoại lệ và thông báo lỗi cho người dùng
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi tìm kiếm: " + e.getMessage());
            e.printStackTrace(); // Ghi lỗi chi tiết nếu cần
        }


    }//GEN-LAST:event_btTimKiemActionPerformed


    private void rdoallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoallActionPerformed
        // TODO add your handling code here:
        showDatatable(repo.getAll(null));

    }//GEN-LAST:event_rdoallActionPerformed

    private void rdosdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdosdActionPerformed
        // TODO add your handling code here:
        showDatatable(repo.getAll(true));

    }//GEN-LAST:event_rdosdActionPerformed

    private void rdoksdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoksdActionPerformed
        // TODO add your handling code here:
        showDatatable(repo.getAll(false));

    }//GEN-LAST:event_rdoksdActionPerformed

    private void txtGiaMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaMaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaMaxActionPerformed

    private void txtGiaMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaMinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaMinActionPerformed

    private void btLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLocActionPerformed
        // TODO add your handling code here:
        try {
            // Lấy giá trị nhập từ người dùng, với kiểm tra hợp lệ
            double giaMin = txtGiaMin.getText().isEmpty() ? 0 : Double.parseDouble(txtGiaMin.getText());
            double giaMax = txtGiaMax.getText().isEmpty() ? Double.MAX_VALUE : Double.parseDouble(txtGiaMax.getText());

            // Kiểm tra logic giá trị nhập
            if (giaMin > giaMax) {
                JOptionPane.showMessageDialog(this, "Khoảng giá không hợp lệ! Giá tối thiểu phải nhỏ hơn giá tối đa.");
                txtGiaMax.setBackground(Color.PINK);
                txtGiaMin.setBackground(Color.PINK);
                return;
            } else {
                txtGiaMax.setBackground(null);
                txtGiaMin.setBackground(null);

            }

            // Kiểm tra giá trị không nhỏ hơn 0
            if (giaMin < 0 || giaMax < 0) {
                JOptionPane.showMessageDialog(this, "Giá trị không được nhỏ hơn 0. Vui lòng nhập lại!");
                txtGiaMin.setBackground(Color.PINK);
                txtGiaMax.setBackground(Color.PINK);
                return;
            } else {
                txtGiaMin.setBackground(null);
                txtGiaMax.setBackground(null);
            }

            // Lấy danh sách sản phẩm từ repo
            List<SanPham> allProducts = repo.getAll(null);
            if (allProducts == null || allProducts.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có sản phẩm nào để lọc!");
                return;
            }

            // Lọc sản phẩm theo khoảng giá
            List<SanPham> listSearch = allProducts.stream()
                    .filter(s -> s.getGia() >= giaMin && s.getGia() <= giaMax)
                    .collect(Collectors.toList());

            // Hiển thị kết quả
            if (listSearch.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm nào trong khoảng giá này!");
            } else {
                showDatatable(listSearch);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá trị nhập vào không hợp lệ. Vui lòng nhập số!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btLocActionPerformed

    public void loadImageFromDatabase(byte[] imageData) {
        try {
            if (imageData != null) {
                // Nếu có ảnh, chuyển byte array thành ImageIcon
                ImageIcon icon = new ImageIcon(imageData);
                Image image = icon.getImage();

                // Lấy kích thước của JLabel để thay đổi kích thước ảnh sao cho vừa
                int width = lbhinhanh.getWidth();
                int height = lbhinhanh.getHeight();
                int newWidth = width;
                int newHeight = (int) (image.getHeight(null) * ((double) width / image.getWidth(null)));

                // Nếu chiều cao sau khi thay đổi kích thước lớn hơn chiều cao của JLabel, điều chỉnh lại
                if (newHeight > height) {
                    newHeight = height;
                    newWidth = (int) (image.getWidth(null) * ((double) height / image.getHeight(null)));
                }

                // Thay đổi kích thước ảnh sao cho phù hợp với JLabel
                Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                lbhinhanh.setIcon(new ImageIcon(scaledImage));
            } else {
                // Nếu không có ảnh, hiển thị dòng chữ "Ảnh nhân viên" trên JLabel
                lbhinhanh.setText("Ảnh sản phẩm");
                lbhinhanh.setIcon(null); // Đảm bảo không có hình ảnh nào hiển thị
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi tải ảnh: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private byte[] convertImageToByteArray(ImageIcon imageIcon) {
        if (!lbhinhanh.getText().isEmpty()) {
            // Lấy Image từ ImageIcon
            Image image = imageIcon.getImage();

            // Chuyển Image thành BufferedImage
            BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();

            // Chuyển BufferedImage thành byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ImageIO.write(bufferedImage, "png", baos); // Lưu ảnh dưới định dạng PNG
                baos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return baos.toByteArray();
        } else {
            return null;
        }
    }


    private void btThemAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemAnhActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser("C:\\\\Users\\\\Admin\\\\OneDrive\\\\Desktop\\\\DuAn1-master (3)\\\\DuAn1-master\\\\src\\\\img");

        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        ImageIcon imageicon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(lbhinhanh.getWidth(), lbhinhanh.getHeight(), Image.SCALE_SMOOTH));
        lbhinhanh.setIcon(imageicon);
        try {
            // Mở tệp ảnh
            File image = new File(filename);
            FileInputStream fis = new FileInputStream(image);

            // Dùng BufferedInputStream để tối ưu việc đọc tệp ảnh
            BufferedInputStream bis = new BufferedInputStream(fis);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            // Đọc tệp ảnh và ghi vào ByteArrayOutputStream mà không cần kích thước bộ đệm cố định
            int readByte;
            while ((readByte = bis.read()) != -1) {
                bos.write(readByte);
            }

            // Chuyển đổi ByteArrayOutputStream thành byte[]
            person_image = bos.toByteArray();

            // Truyền byte[] ảnh vào phương thức anh()
            repo.anh(person_image);

            // Đóng các stream
            bis.close();
            fis.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_btThemAnhActionPerformed

    private void txtGiaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaSanPhamActionPerformed

    private void btThemSanPhamctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemSanPhamctActionPerformed
//         int index = tb_SanPhamct.getSelectedRow();
// 
//    int maSp = Integer.parseInt(tb_SanPhamct.getValueAt(index, 0).toString());
//    for (modelSize size : listSize) {
//        String tenSize = size.getTenSize();
//        if (cbSizeS.isSelected() && tenSize.equals(cbSizeS.getText()) 
//            || tenSize.equals("M") 
//            || tenSize.equals("L")) {
//            modelSize s = repoSize.getIdByTenSize(tenSize);
//            if (s == null) {
//                JOptionPane.showMessageDialog(this, "Không tìm thấy kích thước: " + tenSize);
//                continue;
//            }
//            int idSize = s.getId();
//            repoCT.insertSanPhamChiTiet(maSp, idSize, ngayTao, tinhTrang);
//        }
//    }

        add();


    }//GEN-LAST:event_btThemSanPhamctActionPerformed
    private void add() {
        int maSP = Integer.parseInt(txtMaSp.getText());

        ArrayList<modelSize> listSizeSPCT = repoSize.getAllSizeOnSpctByMasp(maSP);

        ArrayList<modelSize> listSize = repoSize.getAllSize();

        Date ngayTao = new Date();
        boolean tinhTrang = true;

        String sizeS = cbSizeS.getText();
        String sizeM = cbSizeM.getText();
        String sizeL = cbSizeL.getText();

        // Kiểm tra xem listSize có rỗng không
        if (listSizeSPCT == null || listSizeSPCT.isEmpty()) {
            // Kiểm tra kích thước và các checkbox tương ứng
            for (modelSize size : listSize) {
                String tenSize = size.getTenSize();

                // Kiểm tra nếu checkbox kích thước tương ứng đã được chọn
                if ((cbSizeS.isSelected() && tenSize.equals("S"))) {
                    // Lấy thông tin kích thước từ repo
                    modelSize s = repoSize.getIdByTenSize(tenSize);
                    if (s == null) {
                        JOptionPane.showMessageDialog(this, "Không tìm thấy kích thước: " + tenSize);
                        continue; // Tiếp tục với kích thước tiếp theo nếu không tìm thấy
                    }

                    // Thêm sản phẩm chi tiết
                    int idSize = s.getId();
                    double gia = s.getGia(); // Giả sử bạn cần giá từ modelSize

                    // Gọi phương thức thêm vào bảng SanPham_Size
//                    repoCT.insertSanPhamChiTiet(idSanPham, idSize, gia, ngayTao, tinhTrang);
                    repoCT.insertSanPhamChiTiet(maSP, idSize, gia, ngayTao, tinhTrang);

                    List<SanPhamChiTiet> spctsList = repoCT.getAllSpctByMa(maSP);
                    showDatatableSPCT(spctsList);
                } else if ((cbSizeL.isSelected() && tenSize.equals("M"))) {
                    // Lấy thông tin kích thước từ repo
                    modelSize s = repoSize.getIdByTenSize(tenSize);
                    if (s == null) {
                        JOptionPane.showMessageDialog(this, "Không tìm thấy kích thước: " + tenSize);
                        continue; // Tiếp tục với kích thước tiếp theo nếu không tìm thấy
                    }

                    // Thêm sản phẩm chi tiết
                    int idSize = s.getId();
                    double gia = s.getGia(); // Giả sử bạn cần giá từ modelSize

                    // Gọi phương thức thêm vào bảng SanPham_Size
//                    repoCT.insertSanPhamChiTiet(idSanPham, idSize, gia, ngayTao, tinhTrang);
                    repoCT.insertSanPhamChiTiet(maSP, idSize, gia, ngayTao, tinhTrang);

//                    // Kiểm tra kết quả chèn
//                    if (!isInserted) {
//                        JOptionPane.showMessageDialog(this, "Thêm sản phẩm chi tiết thất bại cho kích thước: " + tenSize);
//                    } else {
//                        JOptionPane.showMessageDialog(this, "Thêm sản phẩm chi tiết thành công cho kích thước: " + tenSize);
//                    }
                    List<SanPhamChiTiet> spctsList = repoCT.getAllSpctByMa(maSP);
                    showDatatableSPCT(spctsList);
                } else if ((cbSizeM.isSelected() && tenSize.equals("L"))) {
                    // Lấy thông tin kích thước từ repo
                    modelSize s = repoSize.getIdByTenSize(tenSize);
                    if (s == null) {
                        JOptionPane.showMessageDialog(this, "Không tìm thấy kích thước: " + tenSize);
                        continue; // Tiếp tục với kích thước tiếp theo nếu không tìm thấy
                    }

                    // Thêm sản phẩm chi tiết
                    int idSize = s.getId();
                    double gia = s.getGia(); // Giả sử bạn cần giá từ modelSize

                    // Gọi phương thức thêm vào bảng SanPham_Size
//                    repoCT.insertSanPhamChiTiet(idSanPham, idSize, gia, ngayTao, tinhTrang);
                    repoCT.insertSanPhamChiTiet(maSP, idSize, gia, ngayTao, tinhTrang);

//                    // Kiểm tra kết quả chèn
//                    if (!isInserted) {
//                        JOptionPane.showMessageDialog(this, "Thêm sản phẩm chi tiết thất bại cho kích thước: " + tenSize);
//                    } else {
//                        JOptionPane.showMessageDialog(this, "Thêm sản phẩm chi tiết thành công cho kích thước: " + tenSize);
//                    }
                    List<SanPhamChiTiet> spctsList = repoCT.getAllSpctByMa(maSP);
                    showDatatableSPCT(spctsList);
                }
            }
        } else {
            for (modelSize size : listSizeSPCT) {
                String tenSize = size.getTenSize();

                if ((cbSizeL.getText().equals(tenSize) && cbSizeL.isSelected())
                        || (cbSizeM.getText().equals(tenSize) && cbSizeM.isSelected())
                        || (cbSizeS.getText().equals(tenSize) && cbSizeS.isSelected())) {
                    JOptionPane.showMessageDialog(this, "Da co size nay!");
                } else {
                    for (modelSize Size : listSize) {
                        String tenSize1 = Size.getTenSize();
                        // Kiểm tra nếu checkbox kích thước tương ứng đã được chọn
                        if ((cbSizeS.isSelected() && tenSize1.equals("S"))) {
                            // Lấy thông tin kích thước từ repo
                            modelSize s = repoSize.getIdByTenSize(tenSize1);
                            if (s == null) {
                                JOptionPane.showMessageDialog(this, "Không tìm thấy kích thước: " + tenSize1);
                                continue; // Tiếp tục với kích thước tiếp theo nếu không tìm thấy
                            }

                            // Thêm sản phẩm chi tiết
                            int idSize = s.getId();
                            double gia = s.getGia(); // Giả sử bạn cần giá từ modelSize

                            // Gọi phương thức thêm vào bảng SanPham_Size
//                    repoCT.insertSanPhamChiTiet(idSanPham, idSize, gia, ngayTao, tinhTrang);
                            repoCT.insertSanPhamChiTiet(maSP, idSize, gia, ngayTao, tinhTrang);

//                    // Kiểm tra kết quả chèn
//                    if (!isInserted) {
//                        JOptionPane.showMessageDialog(this, "Thêm sản phẩm chi tiết thất bại cho kích thước: " + tenSize);
//                    } else {
//                        JOptionPane.showMessageDialog(this, "Thêm sản phẩm chi tiết thành công cho kích thước: " + tenSize);
//                    }
                            List<SanPhamChiTiet> spctsList = repoCT.getAllSpctByMa(maSP);
                            showDatatableSPCT(spctsList);
                        } else if ((cbSizeM.isSelected() && tenSize1.equals("M"))) {
                            // Lấy thông tin kích thước từ repo
                            modelSize s = repoSize.getIdByTenSize(tenSize1);
                            if (s == null) {
                                JOptionPane.showMessageDialog(this, "Không tìm thấy kích thước: " + tenSize1);
                                continue; // Tiếp tục với kích thước tiếp theo nếu không tìm thấy
                            }

                            // Thêm sản phẩm chi tiết
                            int idSize = s.getId();
                            double gia = s.getGia(); // Giả sử bạn cần giá từ modelSize

                            // Gọi phương thức thêm vào bảng SanPham_Size
//                    repoCT.insertSanPhamChiTiet(idSanPham, idSize, gia, ngayTao, tinhTrang);
                            repoCT.insertSanPhamChiTiet(maSP, idSize, gia, ngayTao, tinhTrang);

//                    // Kiểm tra kết quả chèn
//                    if (!isInserted) {
//                        JOptionPane.showMessageDialog(this, "Thêm sản phẩm chi tiết thất bại cho kích thước: " + tenSize);
//                    } else {
//                        JOptionPane.showMessageDialog(this, "Thêm sản phẩm chi tiết thành công cho kích thước: " + tenSize);
//                    }
                            List<SanPhamChiTiet> spctsList = repoCT.getAllSpctByMa(maSP);
                            showDatatableSPCT(spctsList);
                        } else if ((cbSizeL.isSelected() && tenSize1.equals("L"))) {
                            // Lấy thông tin kích thước từ repo
                            modelSize s = repoSize.getIdByTenSize(tenSize1);
                            if (s == null) {
                                JOptionPane.showMessageDialog(this, "Không tìm thấy kích thước: " + tenSize1);
                                continue; // Tiếp tục với kích thước tiếp theo nếu không tìm thấy
                            }

                            // Thêm sản phẩm chi tiết
                            int idSize = s.getId();
                            double gia = s.getGia(); // Giả sử bạn cần giá từ modelSize

                            // Gọi phương thức thêm vào bảng SanPham_Size
//                    repoCT.insertSanPhamChiTiet(idSanPham, idSize, gia, ngayTao, tinhTrang);
                            repoCT.insertSanPhamChiTiet(maSP, idSize, gia, ngayTao, tinhTrang);

//                    // Kiểm tra kết quả chèn
//                    if (!isInserted) {
//                        JOptionPane.showMessageDialog(this, "Thêm sản phẩm chi tiết thất bại cho kích thước: " + tenSize);
//                    } else {
//                        JOptionPane.showMessageDialog(this, "Thêm sản phẩm chi tiết thành công cho kích thước: " + tenSize);
//                    }
                            List<SanPhamChiTiet> spctsList = repoCT.getAllSpctByMa(maSP);
                            showDatatableSPCT(spctsList);
                        }
                    }

                }
            }
        }

    }
    private void btSuaSanPhamct1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuaSanPhamct1ActionPerformed
        // Lấy mã sản phẩm (maSp) từ đâu đó, ví dụ từ form
        int maSP = Integer.parseInt(txtMaSp.getText());

        //Size trong SPCT
        ArrayList<modelSize> listSizeSPCT = repoSize.getAllSizeOnSpctByMasp1(maSP);

        //Size trong bang Size
        ArrayList<modelSize> listSize = repoSize.getAllSize();

        // Lấy trạng thái checkbox từ giao diện
        boolean isSizeSChecked = cbSizeS.isSelected();
        boolean isSizeMChecked = cbSizeM.isSelected();
        boolean isSizeLChecked = cbSizeL.isSelected();

        // Duyệt qua danh sách size và so sánh trạng thái
        for (modelSize size : listSizeSPCT) {
            String tenSize = size.getTenSize();  // Lấy tên size (S, M, L)
            boolean currentStatus = size.isTrangThai(); // Trạng thái hiện tại trong CSDL
            boolean newStatus = currentStatus; // Trạng thái mới để so sánh

            // So sánh và cập nhật trạng thái dựa trên checkbox
            if (tenSize.equalsIgnoreCase("S")) {
                newStatus = isSizeSChecked;
            } else if (tenSize.equalsIgnoreCase("M")) {
                newStatus = isSizeMChecked;
            } else if (tenSize.equalsIgnoreCase("L")) {
                newStatus = isSizeLChecked;
            }

            System.out.println("Trạng thái: " + newStatus);

            // Chỉ cập nhật nếu trạng thái thay đổi
            if (currentStatus != newStatus) {
                size.setTrangThai(newStatus); // Cập nhật trạng thái trong đối tượng
                repoSize.updateSizeStatus(size, maSP, newStatus); // Gọi phương thức cập nhật trạng thái vào CSDL
            } // Chỉ cập nhật nếu trạng thái thay đổi
            if (currentStatus == newStatus) {
                size.setTrangThai(newStatus); // Cập nhật trạng thái trong đối tượng
                repoSize.updateSizeStatus(size, maSP, newStatus); // Gọi phương thức cập nhật trạng thái vào CSDL
            }
        }

        List<SanPhamChiTiet> spctsList = repoCT.getAllSpctByMa(maSP);
        System.out.println("Mã: " + maSP);
        System.out.println("List SPCT: " + spctsList);
        showDatatableSPCT(spctsList);
    }//GEN-LAST:event_btSuaSanPhamct1ActionPerformed

    private void btNew1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNew1ActionPerformed
        // TODO add your handling code here:
        reset2();
        if (rdoall.isSelected()) {
            showTableByTinhTrang(null);
        } else if (rdosd.isSelected()) {
            showTableByTinhTrang(true);
        } else if (rdoksd.isSelected()) {
            showTableByTinhTrang(false);
        }
        // Chuyển sang tab sản phẩm chi tiết và focus vào bảng
        //tab.setSelectedIndex(0);
        tbDoUong.requestFocusInWindow();

        txtTimKiem.setBackground(Color.WHITE);
        txtGiaMax.setBackground(Color.WHITE);
        txtGiaMin.setBackground(Color.WHITE);
    }//GEN-LAST:event_btNew1ActionPerformed

    private void txtMaSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSpActionPerformed

    private void tb_SanPhamctMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_SanPhamctMouseClicked
        // TODO add your handling code here:

        //        int index = tb_SanPhamct.getSelectedRow();
        //        try {
        //            detailSanPhamChiTiet(index);
        //        } catch (SQLException ex) {
        //            Logger.getLogger(ViewSanPham.class.getName()).log(Level.SEVERE, null, ex);
        //            JOptionPane.showMessageDialog(this, "Lỗi khi tải chi tiết sản phẩm!", "Error", JOptionPane.ERROR_MESSAGE);
        //            return; // Dừng nếu xảy ra lỗi
        //        }
        //
        //        txtGiaSanPham.setVisible(true);
        ////        txtGiaSize.setVisible(true);
//        cbSizeS.setVisible(true);
//        cbSizeM.setVisible(true);
//        cbSizeL.setVisible(true);
//        lbSize.setVisible(true);
//        lbGiaSanPham.setVisible(true);
//        // lbGiaSize.setVisible(true);
//        lbThongBaoS.setVisible(true);
//        lbThongBaoM.setVisible(true);
//        lbThongBaoL.setVisible(true);
//        btSuaSanPhamct1.setVisible(true);
//        btThemSanPhamct.setVisible(true);
//        btSua.setVisible(false);
//        btThem.setVisible(false);
        ////        btThemSanPhamct.setVisible(true);
        ////        btThem.setVisible(false);
        ////        btSua.setVisible(false);
    }//GEN-LAST:event_tb_SanPhamctMouseClicked

    private void tbDoUongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDoUongMouseClicked
        int index = tbDoUong.getSelectedRow();
        try {
            detailSanPham(index);
        } catch (SQLException ex) {
            Logger.getLogger(ViewSanPham.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Lỗi khi tải chi tiết sản phẩm!", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Dừng nếu xảy ra lỗi
        }

// Lấy mã sản phẩm từ bảng
        Integer ma = Integer.parseInt(tbDoUong.getValueAt(index, 0).toString());
//        String masp = ma.toString();

// Hiển thị dữ liệu sản phẩm chi tiết
        List<SanPhamChiTiet> details = repoCT.getAllSpctByMa(ma);

        System.out.println("Details by: " + ma + details.toString());

        showDatatableSPCT(details); // Phương thức hiển thị dữ liệu chi tiết trên bảng

// Chuyển sang tab sản phẩm chi tiết và focus vào bảng
//        tab.setSelectedIndex(1);
//        tb_SanPhamct.requestFocusInWindow();
//        btThemSanPhamct.setVisible(true);
//        btSuaSanPhamct1.setVisible(true);
//        btSua.setVisible(false);
//        btThem.setVisible(false);
//        txtGiaSanPham.setVisible(true);
//        cbSizeS.setVisible(true);
//        cbSizeM.setVisible(true);
//        cbSizeL.setVisible(true);
//        lbGiaSanPham.setVisible(true);
//
//        btTimKiem.setVisible(false);
//        txtTimKiem.setVisible(false);
//        btLoc.setVisible(false);
//        txtGiaMax.setVisible(false);
//        txtGiaMin.setVisible(false);
//        rdoall.setVisible(false);
//        rdoksd.setVisible(false);
//        rdosd.setVisible(false);
//        btThemSanPhamct.setVisible(false);
        double donGia = repoCT.getSanPhamByMa(ma).getGia();

        txtGiaSanPham.setText(String.valueOf(donGia));

        ArrayList<modelSize> listSize = repoSize.getAllSizeOnSpctByMasp(ma);

        cbSizeS.setSelected(false);
        cbSizeL.setSelected(false);
        cbSizeM.setSelected(false);
        for (modelSize size : listSize) {
            if (size.getTenSize().equals("S")) {
                cbSizeS.setSelected(true);
            }

            if (size.getTenSize().equals("M")) {
                cbSizeM.setSelected(true);
            }

            if (size.getTenSize().equals("L")) {
                cbSizeL.setSelected(true);
            }
        }


    }//GEN-LAST:event_tbDoUongMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLoc;
    private javax.swing.JButton btNew1;
    private javax.swing.JButton btSua;
    private javax.swing.JButton btSuaSanPhamct1;
    private javax.swing.JButton btThem;
    private javax.swing.JButton btThemAnh;
    private javax.swing.JButton btThemSanPhamct;
    private javax.swing.JButton btTimKiem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JCheckBox cbSizeL;
    private javax.swing.JCheckBox cbSizeM;
    private javax.swing.JCheckBox cbSizeS;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbGiaSanPham;
    private javax.swing.JLabel lbSize;
    private javax.swing.JLabel lbThongBaoL;
    private javax.swing.JLabel lbThongBaoM;
    private javax.swing.JLabel lbThongBaoS;
    private javax.swing.JLabel lbhinhanh;
    private javax.swing.JRadioButton rdoKhongSuDung;
    private javax.swing.JRadioButton rdoSuDung;
    private javax.swing.JRadioButton rdoall;
    private javax.swing.JRadioButton rdoksd;
    private javax.swing.JRadioButton rdosd;
    private javax.swing.JTable tbDoUong;
    private javax.swing.JTable tb_SanPhamct;
    private javax.swing.JTextField txtGiaMax;
    private javax.swing.JTextField txtGiaMin;
    private javax.swing.JTextField txtGiaSanPham;
    private javax.swing.JTextField txtMaSp;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txt_TenSp;
    // End of variables declaration//GEN-END:variables

}
