/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View.BanHangView;

import Model.BanHangModel.modelSize;
import Model.HoaDonModel.HoaDonChiTiet;
import Repository.BanHangRepository.HoaDonRepo;
import Repository.BanHangRepository.RepoSize;
import Repository.BanHangRepository.repobanHang;
import View.GiaoDienChinh;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class AddProduct extends javax.swing.JDialog {

    private HoaDonRepo hdRepo = new HoaDonRepo();

    private RepoSize sizeRepo = new RepoSize();

    private DefaultTableModel dtms = new DefaultTableModel();

    private ViewBanHang viewBanHang = new ViewBanHang();

    private repobanHang banHangRepo = new repobanHang();

    public int maSanPham;

    public String tenSanPham;

    public String maHDTao;

    public void setMaHDTao(String maHDTao) {
        this.maHDTao = maHDTao;
        lblMaHDTao.setText(maHDTao);
    }

    public String getMaHDTao() {
        return maHDTao;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
        txtDonGia.setText(String.valueOf(banHangRepo.getSanPham(maSanPham).getGia()));
        txtTenSanPham.setText(String.valueOf(banHangRepo.getSanPham(maSanPham).getTen().toString()));

        //Tính tổng tiền
        Double tongTien;
        Double donGia = Double.valueOf(txtDonGia.getText().toString().trim());
        Integer soLuong = Integer.valueOf(txtSoLuong.getText().toString().trim());

        txtSoLuong.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTotalPrice();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTotalPrice();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTotalPrice();
            }
        });

        Double giaSize;
        String tenSize = cbbSize.getSelectedItem().toString();

        cbbSize.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    updateTotalPrice();
                }
            }
        });
        
        cbbSize.removeAllItems();
        
        for (modelSize size : sizeRepo.getAllSizeOnSpctByMasp1(maSanPham)) {
            cbbSize.addItem(size.getTenSize());
        }

        giaSize = sizeRepo.getGiaSizeByName(tenSize).getGia();

        tongTien = (donGia + giaSize) * soLuong;
        txtTongGiaSanPham.setText(String.valueOf(tongTien));

        System.out.println("Mã sản phẩm nhận được: " + maSanPham);
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
        System.out.println("Tên sản phẩm nhận được: " + tenSanPham);
    }
    
    public Integer idChon;

    public Integer getIdChon() {
        return idChon;
    }

    public void setIdChon(Integer idChon) {
        this.idChon = idChon;
        txtSoLuong.setText(String.valueOf(hdRepo.getAllHdChiTietBySTT(idChon).getSoLuong()));
        
        if (hdRepo.getAllHdChiTietBySTT(idChon).getGhiChu() == null) {
            txtaGhiChu.setText(null);
        } else {
            txtaGhiChu.setText(hdRepo.getAllHdChiTietBySTT(idChon).getGhiChu().toString());
        }
        
        cbbSize.setSelectedItem(hdRepo.getAllHdChiTietBySTT(idChon).getTenSize().toString());
        
        System.out.println(hdRepo.getAllHdChiTietBySTT(idChon));
    }

    public AddProduct(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        txtSoLuong.setText("1");
//        showDatatable(sizeRepo.getAll());
    }

    private void updateTotalPrice() {
        try {
            // Lấy số lượng
            int soLuong = Integer.parseInt(txtSoLuong.getText().trim());

            // Lấy đơn giá
            double donGia = Double.parseDouble(txtDonGia.getText().trim());

            // Lấy giá theo size (nếu có)
            double giaSize = 0.0;
            if (cbbSize.getSelectedItem() != null) {
                String tenSize = cbbSize.getSelectedItem().toString();
                giaSize = sizeRepo.getGiaSizeByName(tenSize).getGia();
            }

            // Tính tổng tiền
            double tongTien = (donGia + giaSize) * soLuong;

            // Cập nhật tổng tiền vào ô
            txtTongGiaSanPham.setText(String.valueOf(tongTien));
        } catch (NumberFormatException ex) {
            // Xử lý trường hợp nhập sai dữ liệu (ví dụ: không phải số)
            txtTongGiaSanPham.setText("0");
        }
    }

//    private void showDatatable(List<modelSize> lst) {
//        dtms.setRowCount(0);
//        for (modelSize sz : lst) {
//            dtms.addRow(new Object[]{
//                sz.getTenSize()
//            });
//        }
//    }

    HoaDonChiTiet readForm() {
        String maHD;
        Integer idSize;
        Integer maSp;
        String tenSp;
        Float donGia;
        Integer soLuong = 0;
        String ghiChu;

        boolean check = true;

        maHD = lblMaHDTao.getText().toString();
        if (maHD.isEmpty()) {
            check = false;
        }

        idSize = sizeRepo.getGiaSizeByName(cbbSize.getSelectedItem().toString()).getId();
        if (idSize < 0 || idSize == null) {
            check = false;
        }

        maSp = maSanPham;
        if (maSp == null) {
            check = false;
        }

        tenSp = txtTenSanPham.getText().toString();
        if (tenSp == null) {
            check = false;
        }

        donGia = Float.valueOf(txtDonGia.getText().toString().trim());
        if (donGia == null) {
            check = false;
        }

        try {
            soLuong = Integer.valueOf(txtSoLuong.getText().toString());
            if (soLuong <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng > 0");
                txtSoLuong.setBackground(Color.YELLOW);
                txtSoLuong.requestFocus();
                check = false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số");
            txtSoLuong.setBackground(Color.YELLOW);
            txtSoLuong.requestFocus();
            check = false;
        }

        ghiChu = txtaGhiChu.getText().toString().trim();

        if (check) {
            return new HoaDonChiTiet(maHD, idSize, maSp, tenSp, donGia, soLuong, ghiChu);
        } else {
            return null;
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

        txtTongGiaSanPham = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbbSize = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btXacNhan = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnHuy = new javax.swing.JButton();
        txtSoLuong = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btCong = new javax.swing.JButton();
        txtDonGia = new javax.swing.JTextField();
        btTru = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaGhiChu = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        lblMaHDTao = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtTongGiaSanPham.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTongGiaSanPham.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTongGiaSanPham.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtTongGiaSanPham.setEnabled(false);
        txtTongGiaSanPham.setSelectedTextColor(new java.awt.Color(255, 102, 102));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Số lượng");

        cbbSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "S", "M", "L" }));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(227, 116, 0));
        jLabel6.setText("Tên sản phẩm");

        txtTenSanPham.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTenSanPham.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTenSanPham.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtTenSanPham.setEnabled(false);
        txtTenSanPham.setSelectedTextColor(new java.awt.Color(255, 102, 102));
        txtTenSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSanPhamActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Size");

        btXacNhan.setText("Thêm vào hóa đơn");
        btXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXacNhanActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Ghi chú");

        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSoLuong.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSoLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSoLuongMouseClicked(evt);
            }
        });
        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });
        txtSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("Đơn giá");

        btCong.setText("+");
        btCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCongActionPerformed(evt);
            }
        });

        txtDonGia.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtDonGia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDonGia.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtDonGia.setEnabled(false);
        txtDonGia.setSelectedTextColor(new java.awt.Color(255, 102, 102));

        btTru.setText("-");
        btTru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTruActionPerformed(evt);
            }
        });

        txtaGhiChu.setColumns(20);
        txtaGhiChu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtaGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtaGhiChu);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("Tổng");

        lblMaHDTao.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTenSanPham)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(btXacNhan)
                        .addGap(18, 18, 18)
                        .addComponent(btnHuy))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblMaHDTao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDonGia)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(txtTongGiaSanPham)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btCong)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btTru))
                                    .addComponent(cbbSize, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(10, 10, 10))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btXacNhan, btnHuy, btnLamMoi});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(lblMaHDTao, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCong)
                    .addComponent(btTru)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTongGiaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btXacNhan)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btCong, btTru, cbbSize, txtDonGia, txtSoLuong, txtTongGiaSanPham});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btXacNhan, btnHuy, btnLamMoi});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        txtSoLuong.setText("0");
        txtaGhiChu.setText(null);
        cbbSize.setSelectedItem("S");
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCongActionPerformed
        // TODO add your handling code here:
        try {
            int currentQuantity = Integer.parseInt(txtSoLuong.getText());
            // Increase the quantity by 1
            txtSoLuong.setText(String.valueOf(currentQuantity + 1));
        } catch (NumberFormatException e) {
            // If the text is not a valid number, set it to 0
            txtSoLuong.setText("0");
        }
    }//GEN-LAST:event_btCongActionPerformed

    private void btTruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTruActionPerformed
        // TODO add your handling code here:
        try {
            int currentQuantity = Integer.parseInt(txtSoLuong.getText());
            // Decrease the quantity by 1, but not below 0
            if (currentQuantity > 0) {
                txtSoLuong.setText(String.valueOf(currentQuantity - 1));
            }
        } catch (NumberFormatException e) {
            // If the text is not a valid number, set it to 0
            txtSoLuong.setText("0");
        }
    }//GEN-LAST:event_btTruActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        Integer soLuong = 0;
        try {
            soLuong = Integer.parseInt(txtSoLuong.getText().toString().trim());
            if (soLuong < 0 || soLuong > 100) {
                JOptionPane.showMessageDialog(this, "Số lượng từ 0 - 100");
                txtSoLuong.requestFocus();
                txtSoLuong.setBackground(Color.YELLOW);
            } else {
                txtSoLuong.setBackground(Color.WHITE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu");
            txtSoLuong.requestFocus();
            txtSoLuong.setBackground(Color.YELLOW);
        }
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void txtSoLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSoLuongMouseClicked
        txtSoLuong.setText("1");
    }//GEN-LAST:event_txtSoLuongMouseClicked

    private void btXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXacNhanActionPerformed
        if (readForm() != null) {
            int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm?");

            if (chon == 0) {
                if (!hdRepo.checkSpOnHoaDonChiTiet(readForm().getMaHoaDon(), readForm().getMaSanPham(), readForm().getIdSize())) {
                    System.out.println("Thêm");
                    hdRepo.insertNewHoaDonChiTiet(readForm());
                } else {
                    System.out.println("Cập nhật");
                    hdRepo.updateSoLuongHoaDonChiTiet(readForm());
                }
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                this.dispose();
                Frame GiaoDienChinh = new GiaoDienChinh();
                viewBanHang.fillTableHdct(hdRepo.getAllHdChiTietByMaHD(maHDTao));
            }
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi thông tin!");
        }
    }//GEN-LAST:event_btXacNhanActionPerformed

    private void txtTenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSanPhamActionPerformed

    private void txtSoLuongKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyTyped
        if (txtSoLuong.getText().length() >= 2) {
            evt.consume(); // Ngăn không cho nhập thêm
        }
    }//GEN-LAST:event_txtSoLuongKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddProduct dialog = new AddProduct(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCong;
    private javax.swing.JButton btTru;
    private javax.swing.JButton btXacNhan;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JComboBox<String> cbbSize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMaHDTao;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtTongGiaSanPham;
    private javax.swing.JTextArea txtaGhiChu;
    // End of variables declaration//GEN-END:variables
}
