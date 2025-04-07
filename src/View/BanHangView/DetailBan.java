/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View.BanHangView;

import Model.BanHangModel.Ban;
import Repository.BanHangRepository.BanRepo;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class DetailBan extends javax.swing.JDialog {
    
    private BanRepo banRepo = new BanRepo();
    
    private int maBan;

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
        txtMaBan.setText(String.valueOf(maBan));
        
        Ban ban = banRepo.getBanByMa(maBan);
        
        txtTenBan.setText(ban.getTenBan());
        txtSucChua.setText(String.valueOf(ban.getSucChua()));
        
        //Xóa dữ liệu Combo box Vị trí
        cboViTri.removeAllItems();

        //Truyền dữ liệu bảng bàn
        ArrayList<Ban> listBan = banRepo.getAllViTriBan();

        //Truyền dữ liệu bàn vào cbo
        for (Ban b : listBan) {
            cboViTri.addItem(b.getViTri());
        }
        
        cboViTri.setSelectedItem(ban.getViTri());
        
        if (ban.isTinhTrang()) {
            rdoSuDung.setSelected(true);
        } else {
            rdoKhongSuDung.setSelected(true);
        }
    }
    

    public DetailBan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        setLocationRelativeTo(null);
        
    }

    Ban readForm() {
        String tenBan;
        Integer sucChua = 1;
        String viTri;
        Integer trangThai = 0;
        Integer tinhTrang;

        boolean check = true;

        tenBan = txtTenBan.getText().toString().trim();
        if (tenBan.isEmpty()) {
            txtTenBan.requestFocus();
            txtTenBan.setBackground(Color.yellow);  // Nếu trống, đổi màu nền thành vàng
            check = false;
        } else {
            txtTenBan.setBackground(Color.WHITE);  // Nếu có dữ liệu, khôi phục lại màu nền
        }

        try {
            sucChua = Integer.parseInt(txtSucChua.getText().toString().trim());
            if (sucChua <= 0 || sucChua >= 20) {
                JOptionPane.showMessageDialog(this, "Sức chứa nằm trong khoảng 1 - 19");
                txtSucChua.requestFocus();
                txtSucChua.setBackground(Color.yellow);  // Nếu trống, đổi màu nền thành vàng
                check = false;
            }
        } catch (Exception e) {
            txtSucChua.requestFocus();
            txtSucChua.setBackground(Color.yellow);  // Nếu trống, đổi màu nền thành vàng
            check = false;
        }
        
        viTri = cboViTri.getSelectedItem().toString();
        
        if (rdoSuDung.isSelected()) {
            tinhTrang = 1;
        } else {
            tinhTrang = 0;
        }
        
        if (check) {
            return new Ban(tenBan, sucChua, viTri, tinhTrang);
        } else {
            return null;
        }
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rdoGroupTinhTrang = new javax.swing.ButtonGroup();
        cboViTri = new javax.swing.JComboBox<>();
        btnSua = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSucChua = new javax.swing.JTextField();
        txtTenBan = new javax.swing.JTextField();
        txtMaBan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rdoSuDung = new javax.swing.JRadioButton();
        rdoKhongSuDung = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cboViTri.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cboViTri.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSua.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSua.setText("Cập nhật");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnHuy.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thông tin bàn");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Mã bàn");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Tên bàn");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Sức chứa");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Vị trí");

        txtSucChua.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSucChua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSucChuaKeyTyped(evt);
            }
        });

        txtTenBan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtMaBan.setEditable(false);
        txtMaBan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtMaBan.setForeground(new java.awt.Color(255, 0, 0));
        txtMaBan.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        txtMaBan.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Tình trạng");

        rdoGroupTinhTrang.add(rdoSuDung);
        rdoSuDung.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rdoSuDung.setText("Sử dụng");

        rdoGroupTinhTrang.add(rdoKhongSuDung);
        rdoKhongSuDung.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rdoKhongSuDung.setText("Không sử dụng");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSua)
                        .addGap(38, 38, 38)
                        .addComponent(btnLamMoi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHuy))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rdoSuDung)
                                .addGap(18, 18, 18)
                                .addComponent(rdoKhongSuDung))
                            .addComponent(txtSucChua, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                            .addComponent(txtTenBan)
                            .addComponent(txtMaBan)
                            .addComponent(cboViTri, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboViTri, txtMaBan, txtSucChua, txtTenBan});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSucChua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cboViTri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(rdoSuDung)
                    .addComponent(rdoKhongSuDung))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua)
                    .addComponent(btnLamMoi)
                    .addComponent(btnHuy))
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (readForm() != null) {
            int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa?");

            if (chon == 0) {
                banRepo.update(readForm(), maBan);
                JOptionPane.showMessageDialog(this, "Sửa thành công!");
                this.dispose();
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        txtTenBan.setText(null);
        txtSucChua.setText(null);
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void txtSucChuaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSucChuaKeyTyped
        if (txtSucChua.getText().length() >= 2) {
            evt.consume(); // Ngăn không cho nhập thêm
        }
    }//GEN-LAST:event_txtSucChuaKeyTyped

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
            java.util.logging.Logger.getLogger(DetailBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetailBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetailBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetailBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DetailBan dialog = new DetailBan(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JComboBox<String> cboViTri;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.ButtonGroup rdoGroupTinhTrang;
    private javax.swing.JRadioButton rdoKhongSuDung;
    private javax.swing.JRadioButton rdoSuDung;
    private javax.swing.JTextField txtMaBan;
    private javax.swing.JTextField txtSucChua;
    private javax.swing.JTextField txtTenBan;
    // End of variables declaration//GEN-END:variables
}
