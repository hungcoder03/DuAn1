/*
 * Click nbfs:nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs:nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.HoaDonModel.HoaDon;
import Model.HoaDonModel.HoaDonChiTiet;
import Repository.HoaDonChiTietRepository;
import Repository.HoaDonRepository;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class ViewHoaDon1 extends javax.swing.JPanel {

    /**
     * Creates new form ViewHoaDonTest
     */
    private HoaDonRepository rp = new HoaDonRepository();
    private HoaDonChiTietRepository rphdct = new HoaDonChiTietRepository();
    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultTableModel dtmhdct = new DefaultTableModel();
    private ArrayList<HoaDon> list = new ArrayList<>();
    private ArrayList<HoaDonChiTiet> listhdct = new ArrayList<>();

    public ViewHoaDon1() {
        initComponents();

        dtm = (DefaultTableModel) tbl_formHoaDon_ViewHoaDon.getModel();
        dtmhdct = (DefaultTableModel) tblhoaDonChiTiet_FormViewHoaDon.getModel();
        list = rp.getAll();

        showDataTable(list);
        showDataTable2(listhdct);

    }

    public void showDataTable(ArrayList<HoaDon> list) {
        dtm.setRowCount(0);

        for (HoaDon hoaDon : list) {
            dtm.addRow(new Object[]{
                hoaDon.getMaHoaDon(), hoaDon.getMaGiamGia(), hoaDon.getTenBan()  == null ? "Giao hàng" : hoaDon.getTenBan(), hoaDon.getTenNhanVien(),
                hoaDon.getTongTienThanhToan(), hoaDon.getTienKM(), hoaDon.getTongTienSauKM(), hoaDon.getTenKhachHang() == null ? "Khách vãng lai" : hoaDon.getTenKhachHang(),hoaDon.getSdt(), hoaDon.getNgayTao(),  hoaDon.isTinhTrang() ? "Đã thanh toán" : "chưa thanh toán",
            });
        }

    }

    public void showDataTable2(ArrayList<HoaDonChiTiet> al) {
        dtmhdct.setRowCount(0);
        for (HoaDonChiTiet hoaDonChiTiet : al) {
            dtmhdct.addRow(new Object[]{
                hoaDonChiTiet.getMaHoaDon(), hoaDonChiTiet.getMaSanPham(),
                hoaDonChiTiet.getTenSanPham(), hoaDonChiTiet.getDonGia(),
                hoaDonChiTiet.getSoLuong(), hoaDonChiTiet.getGhiChu(), hoaDonChiTiet.getTenSize()
            });
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtSearchMaHoaDon_FormHoaDon = new javax.swing.JTextField();
        btnLamMoi_FormHoaDon = new javax.swing.JButton();
        txtSearchTenNhanVien_FormHoaDon = new javax.swing.JTextField();
        txtSearchsdtKhachHang_FormHoaDon = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbbLocTrangThai_FormViewHoaDon = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_formHoaDon_ViewHoaDon = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblhoaDonChiTiet_FormViewHoaDon = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1700, 850));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel4.setText("Mã hóa đơn");

        txtSearchMaHoaDon_FormHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchMaHoaDon_FormHoaDonActionPerformed(evt);
            }
        });
        txtSearchMaHoaDon_FormHoaDon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchMaHoaDon_FormHoaDonKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchMaHoaDon_FormHoaDonKeyTyped(evt);
            }
        });

        btnLamMoi_FormHoaDon.setText("Làm mới");
        btnLamMoi_FormHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi_FormHoaDonActionPerformed(evt);
            }
        });

        txtSearchTenNhanVien_FormHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchTenNhanVien_FormHoaDonActionPerformed(evt);
            }
        });
        txtSearchTenNhanVien_FormHoaDon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchTenNhanVien_FormHoaDonKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchTenNhanVien_FormHoaDonKeyTyped(evt);
            }
        });

        txtSearchsdtKhachHang_FormHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchsdtKhachHang_FormHoaDonActionPerformed(evt);
            }
        });
        txtSearchsdtKhachHang_FormHoaDon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchsdtKhachHang_FormHoaDonKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchsdtKhachHang_FormHoaDonKeyTyped(evt);
            }
        });

        jLabel5.setText("Tên nhân viên");

        jLabel7.setText("SĐT Khách Hàng");

        cbbLocTrangThai_FormViewHoaDon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đã thanh toán", "Chưa thanh toán" }));
        cbbLocTrangThai_FormViewHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocTrangThai_FormViewHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSearchMaHoaDon_FormHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                            .addComponent(txtSearchTenNhanVien_FormHoaDon)
                            .addComponent(txtSearchsdtKhachHang_FormHoaDon))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addComponent(btnLamMoi_FormHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(21, 21, 21))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addComponent(cbbLocTrangThai_FormViewHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(61, Short.MAX_VALUE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSearchMaHoaDon_FormHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLamMoi_FormHoaDon)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearchTenNhanVien_FormHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchsdtKhachHang_FormHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cbbLocTrangThai_FormViewHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Hóa đơn");

        tbl_formHoaDon_ViewHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Mã giảm giá", "Tên bàn", "Tên nhân viên", "Tổng tiền trước KM", "Tiền KM", "Tổng tiền sau KM", "Tên khách hàng", "SĐT khách hàng", "Ngày tạo", "Tình trạng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_formHoaDon_ViewHoaDon.setRowHeight(30);
        tbl_formHoaDon_ViewHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_formHoaDon_ViewHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_formHoaDon_ViewHoaDon);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1677, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel21.setText("Hóa đơn chi tiết");

        tblhoaDonChiTiet_FormViewHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Mã sản phẩm", "Tên sản phẩm", "Đơn giá", "Số lượng ", "Ghi chú", "Tên size"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblhoaDonChiTiet_FormViewHoaDon.setRowHeight(30);
        jScrollPane8.setViewportView(tblhoaDonChiTiet_FormViewHoaDon);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 1164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1701, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(238, 238, 238)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 869, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLamMoi_FormHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi_FormHoaDonActionPerformed
        txtSearchMaHoaDon_FormHoaDon.setText("");
        txtSearchTenNhanVien_FormHoaDon.setText("");
        txtSearchsdtKhachHang_FormHoaDon.setText("");
        dtmhdct.setRowCount(0);
        showDataTable(list);


    }//GEN-LAST:event_btnLamMoi_FormHoaDonActionPerformed

    private void tbl_formHoaDon_ViewHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_formHoaDon_ViewHoaDonMouseClicked
        int selectedRow = tbl_formHoaDon_ViewHoaDon.getSelectedRow();
        if (selectedRow != -1) {  // Kiểm tra có hàng được chọn không
            String maHoaDon = tbl_formHoaDon_ViewHoaDon.getValueAt(selectedRow, 0).toString();
            showDataTable2(rphdct.getAll(maHoaDon));
        }

    }//GEN-LAST:event_tbl_formHoaDon_ViewHoaDonMouseClicked

    private void cbbLocTrangThai_FormViewHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocTrangThai_FormViewHoaDonActionPerformed

        // Lấy trạng thái được chọn
        String selectedStatus = (String) cbbLocTrangThai_FormViewHoaDon.getSelectedItem();

        // Xóa dữ liệu hiện tại trong bảng
        dtm.setRowCount(0);

        // Lọc và thêm dữ liệu mới vào bảng
        for (HoaDon hd : list) {
            boolean isActive = hd.isTinhTrang(); // Kiểm tra trạng thái hóa đơn
            if (("Đã thanh toán".equals(selectedStatus) && isActive) || ("Chưa thanh toán".equals(selectedStatus) && !isActive)) {
                dtm.addRow(new Object[]{
                    hd.getMaHoaDon(),
                    hd.getMaGiamGia(),
                    hd.getTenBan(),
                    hd.getTenNhanVien(),
                    hd.getNgayTao(),
                    hd.getTongTienThanhToan(),
                    hd.isTinhTrang() ? "Đã thanh toán" : "Chưa thanh toán",
                    hd.getTenKhachHang(),
                    hd.getSdt(),
                    hd.getTongTienSauKM(),
                    hd.getTienKM()
                });
            }
        }
    }//GEN-LAST:event_cbbLocTrangThai_FormViewHoaDonActionPerformed

    private void txtSearchMaHoaDon_FormHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchMaHoaDon_FormHoaDonActionPerformed
        String maHd = txtSearchMaHoaDon_FormHoaDon.getText().toString().trim();

        if (rp.getHdByMaHd(maHd) != null) {
            showDataTable(rp.getHdByMaHd(maHd));
        } else {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu");
        }

        txtSearchTenNhanVien_FormHoaDon.setText(null);
        txtSearchsdtKhachHang_FormHoaDon.setText(null);
    }//GEN-LAST:event_txtSearchMaHoaDon_FormHoaDonActionPerformed

    private void txtSearchTenNhanVien_FormHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchTenNhanVien_FormHoaDonActionPerformed
        String tenNv = txtSearchTenNhanVien_FormHoaDon.getText().toString().trim();

        if (rp.getHdByTenNhanVien(tenNv) != null) {
            showDataTable(rp.getHdByTenNhanVien(tenNv));
        } else {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu");
        }

        txtSearchMaHoaDon_FormHoaDon.setText(null);
        txtSearchsdtKhachHang_FormHoaDon.setText(null);
    }//GEN-LAST:event_txtSearchTenNhanVien_FormHoaDonActionPerformed

    private void txtSearchsdtKhachHang_FormHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchsdtKhachHang_FormHoaDonActionPerformed
        String sdt = txtSearchsdtKhachHang_FormHoaDon.getText().toString().trim();

        if (rp.getHdBySDT(sdt) != null) {
            showDataTable(rp.getHdBySDT(sdt));
        } else {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu");
        }

        txtSearchMaHoaDon_FormHoaDon.setText(null);
        txtSearchTenNhanVien_FormHoaDon.setText(null);
    }//GEN-LAST:event_txtSearchsdtKhachHang_FormHoaDonActionPerformed

    private void txtSearchMaHoaDon_FormHoaDonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchMaHoaDon_FormHoaDonKeyTyped
        String maHd = txtSearchMaHoaDon_FormHoaDon.getText().toString().trim();

        if (rp.getHdByMaHd(maHd) != null) {
            showDataTable(rp.getHdByMaHd(maHd));
        } else {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu");
        }

        txtSearchTenNhanVien_FormHoaDon.setText(null);
        txtSearchsdtKhachHang_FormHoaDon.setText(null);
    }//GEN-LAST:event_txtSearchMaHoaDon_FormHoaDonKeyTyped

    private void txtSearchMaHoaDon_FormHoaDonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchMaHoaDon_FormHoaDonKeyReleased
        String maHd = txtSearchMaHoaDon_FormHoaDon.getText().toString().trim();

        if (rp.getHdByMaHd(maHd) != null) {
            showDataTable(rp.getHdByMaHd(maHd));
        } else {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu");
        }

        txtSearchTenNhanVien_FormHoaDon.setText(null);
        txtSearchsdtKhachHang_FormHoaDon.setText(null);
    }//GEN-LAST:event_txtSearchMaHoaDon_FormHoaDonKeyReleased

    private void txtSearchTenNhanVien_FormHoaDonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchTenNhanVien_FormHoaDonKeyReleased
        String tenNv = txtSearchTenNhanVien_FormHoaDon.getText().toString().trim();

        if (rp.getHdByTenNhanVien(tenNv) != null) {
            showDataTable(rp.getHdByTenNhanVien(tenNv));
        } else {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu");
        }

        txtSearchMaHoaDon_FormHoaDon.setText(null);
        txtSearchsdtKhachHang_FormHoaDon.setText(null);
    }//GEN-LAST:event_txtSearchTenNhanVien_FormHoaDonKeyReleased

    private void txtSearchTenNhanVien_FormHoaDonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchTenNhanVien_FormHoaDonKeyTyped
        String tenNv = txtSearchTenNhanVien_FormHoaDon.getText().toString().trim();

        if (rp.getHdByTenNhanVien(tenNv) != null) {
            showDataTable(rp.getHdByTenNhanVien(tenNv));
        } else {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu");
        }

        txtSearchMaHoaDon_FormHoaDon.setText(null);
        txtSearchsdtKhachHang_FormHoaDon.setText(null);
    }//GEN-LAST:event_txtSearchTenNhanVien_FormHoaDonKeyTyped

    private void txtSearchsdtKhachHang_FormHoaDonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchsdtKhachHang_FormHoaDonKeyReleased
        String sdt = txtSearchsdtKhachHang_FormHoaDon.getText().toString().trim();

        if (rp.getHdBySDT(sdt) != null) {
            showDataTable(rp.getHdBySDT(sdt));
        } else {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu");
        }

        txtSearchMaHoaDon_FormHoaDon.setText(null);
        txtSearchTenNhanVien_FormHoaDon.setText(null);
    }//GEN-LAST:event_txtSearchsdtKhachHang_FormHoaDonKeyReleased

    private void txtSearchsdtKhachHang_FormHoaDonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchsdtKhachHang_FormHoaDonKeyTyped
        String sdt = txtSearchsdtKhachHang_FormHoaDon.getText().toString().trim();

        if (rp.getHdBySDT(sdt) != null) {
            showDataTable(rp.getHdBySDT(sdt));
        } else {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu");
        }

        txtSearchMaHoaDon_FormHoaDon.setText(null);
        txtSearchTenNhanVien_FormHoaDon.setText(null);
    }//GEN-LAST:event_txtSearchsdtKhachHang_FormHoaDonKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi_FormHoaDon;
    private javax.swing.JComboBox<String> cbbLocTrangThai_FormViewHoaDon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable tbl_formHoaDon_ViewHoaDon;
    private javax.swing.JTable tblhoaDonChiTiet_FormViewHoaDon;
    private javax.swing.JTextField txtSearchMaHoaDon_FormHoaDon;
    private javax.swing.JTextField txtSearchTenNhanVien_FormHoaDon;
    private javax.swing.JTextField txtSearchsdtKhachHang_FormHoaDon;
    // End of variables declaration//GEN-END:variables
}
