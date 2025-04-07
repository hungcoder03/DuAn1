package View.BanHangView;

import Bean.DanhMucBean;
import Controller.ChuyenGiaoDien;
import Model.KhachHangModel.KhachHang;
import Repository.RepoKhachHang;
import View.GiaoDienChinh;
import java.awt.Color;
import java.awt.Frame;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AddClient extends javax.swing.JDialog {

    private RepoKhachHang khRepo = new RepoKhachHang();

    private Boolean checkFlag = false;

    public Boolean isCreate = false;

    public KhachHang kh;
    
    private GiaoDienChinh giaoDienChinh;

    public KhachHang getKh() {
        return kh;
    }

    public void setKh(KhachHang kh) {
        this.kh = kh;
    }
    
    public AddClient(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocationRelativeTo(null);

        //Combo box SĐT
        cboSdtKhachHang.removeAllItems();

        for (KhachHang khachHang : khRepo.getAllKhachHang(true, null)) {
            cboSdtKhachHang.addItem(khachHang.getSoDT());
        }

        //Combo box SĐT
        cboEmailKhachHang.removeAllItems();

        for (KhachHang khachHang : khRepo.getAllKhachHang(true, null)) {
            cboEmailKhachHang.addItem(khachHang.getMail());
        }

//        readSDT();
    }

    KhachHang readForm() {
        String maKH, hoTen, sdt, diaChi;
        boolean gioiTinh;

        boolean check = true;

        maKH = txtMaKH.getText().toString().trim();
        if (maKH.isEmpty()) {
            txtMaKH.requestFocus();
            txtMaKH.setBackground(Color.YELLOW);
            check = false;
        } else {
            txtMaKH.setBackground(Color.WHITE);
        }

        hoTen = txtHoTenKhachHang.getText().toString().trim();
        if (hoTen.isEmpty()) {
            txtHoTenKhachHang.requestFocus();
            txtHoTenKhachHang.setBackground(Color.YELLOW);
            check = false;
        } else {
            txtHoTenKhachHang.setBackground(Color.WHITE);
        }

        sdt = txtSdtKhachHang.getText().toString().trim();
        if (sdt.isEmpty()) {
            txtSdtKhachHang.requestFocus();
            txtSdtKhachHang.setBackground(Color.YELLOW);
            check = false;
        } else {
            txtSdtKhachHang.setBackground(Color.WHITE);
        }

        diaChi = txtDiaChiKhachHang.getText().toString().trim();
        if (diaChi.isEmpty()) {
            txtDiaChiKhachHang.requestFocus();
            txtDiaChiKhachHang.setBackground(Color.YELLOW);
            check = false;
        } else {
            txtDiaChiKhachHang.setBackground(Color.WHITE);
        }

        if (rdoNam.isSelected()) {
            gioiTinh = true;
        } else {
            gioiTinh = false;
        }

        if (check) {
            return new KhachHang(maKH, hoTen, gioiTinh, sdt, diaChi);
        } else {
            return null;
        }

    }

    private void showForm(KhachHang kh) {
        txtMaKH.setText(kh.getMakh());
        txtSdtKhachHang.setText(kh.getSoDT());
        txtEmailKhachHang.setText(kh.getMail());
        txtHoTenKhachHang.setText(kh.getHoTen());
        txtDiaChiKhachHang.setText(kh.getDiaChi());

        cboEmailKhachHang.setSelectedItem(kh.getMail());
        cboSdtKhachHang.setSelectedItem(kh.getSoDT());

        if (kh.isGt()) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlAddKhachHang = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtSdtKhachHang = new javax.swing.JTextField();
        txtEmailKhachHang = new javax.swing.JTextField();
        txtHoTenKhachHang = new javax.swing.JTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDiaChiKhachHang = new javax.swing.JTextArea();
        btnLamMoiKhachHang = new javax.swing.JButton();
        btnXacNhanKhachHang = new javax.swing.JButton();
        cboSdtKhachHang = new javax.swing.JComboBox<>();
        cboEmailKhachHang = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlAddKhachHang.setPreferredSize(new java.awt.Dimension(520, 399));
        pnlAddKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pnlAddKhachHangMouseReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Thông tin khách hàng");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel9.setText("Họ tên");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel10.setText("SĐT");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel11.setText("Email");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel12.setText("Giới tính");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel13.setText("Địa chỉ");

        txtSdtKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtSdtKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSdtKhachHangActionPerformed(evt);
            }
        });
        txtSdtKhachHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSdtKhachHangKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSdtKhachHangKeyTyped(evt);
            }
        });

        txtEmailKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtEmailKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailKhachHangActionPerformed(evt);
            }
        });
        txtEmailKhachHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmailKhachHangKeyTyped(evt);
            }
        });

        txtHoTenKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        rdoNu.setText("Nữ");

        txtDiaChiKhachHang.setColumns(20);
        txtDiaChiKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtDiaChiKhachHang.setRows(5);
        jScrollPane1.setViewportView(txtDiaChiKhachHang);

        btnLamMoiKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btnLamMoiKhachHang.setText("Làm mới");
        btnLamMoiKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiKhachHangActionPerformed(evt);
            }
        });

        btnXacNhanKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btnXacNhanKhachHang.setText("Tạo đơn");
        btnXacNhanKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanKhachHangActionPerformed(evt);
            }
        });

        cboSdtKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cboSdtKhachHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboSdtKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSdtKhachHangActionPerformed(evt);
            }
        });

        cboEmailKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cboEmailKhachHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboEmailKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEmailKhachHangActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel1.setText("Mã KH");

        txtMaKH.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtMaKH.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMaKH.setEnabled(false);

        javax.swing.GroupLayout pnlAddKhachHangLayout = new javax.swing.GroupLayout(pnlAddKhachHang);
        pnlAddKhachHang.setLayout(pnlAddKhachHangLayout);
        pnlAddKhachHangLayout.setHorizontalGroup(
            pnlAddKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAddKhachHangLayout.createSequentialGroup()
                .addContainerGap(169, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(165, 165, 165))
            .addGroup(pnlAddKhachHangLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(pnlAddKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlAddKhachHangLayout.createSequentialGroup()
                        .addGroup(pnlAddKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(pnlAddKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlAddKhachHangLayout.createSequentialGroup()
                                .addComponent(rdoNam)
                                .addGap(18, 18, 18)
                                .addComponent(rdoNu))
                            .addComponent(txtHoTenKhachHang)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlAddKhachHangLayout.createSequentialGroup()
                                .addGroup(pnlAddKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtEmailKhachHang, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSdtKhachHang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlAddKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboSdtKhachHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboEmailKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(pnlAddKhachHangLayout.createSequentialGroup()
                        .addComponent(btnLamMoiKhachHang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXacNhanKhachHang))
                    .addGroup(pnlAddKhachHangLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaKH)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlAddKhachHangLayout.setVerticalGroup(
            pnlAddKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(pnlAddKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAddKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtSdtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSdtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(pnlAddKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtEmailKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboEmailKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlAddKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtHoTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAddKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu))
                .addGap(18, 18, 18)
                .addGroup(pnlAddKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAddKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXacNhanKhachHang)
                    .addComponent(btnLamMoiKhachHang))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAddKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAddKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pnlAddKhachHangMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlAddKhachHangMouseReleased
    }//GEN-LAST:event_pnlAddKhachHangMouseReleased

    private void txtSdtKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSdtKhachHangActionPerformed
        if (checkFlag) {
            return;
        }

        checkFlag = true;

        cboSdtKhachHang.removeAllItems();

        String sdt = txtSdtKhachHang.getText().toString().trim();

        for (KhachHang khachHang : khRepo.getAllKhachHangBySDT(true, sdt)) {
            cboSdtKhachHang.addItem(khachHang.getSoDT());
        }

        cboSdtKhachHang.addItem("SĐT");

        cboSdtKhachHang.setSelectedIndex(0);

        checkFlag = false;
    }//GEN-LAST:event_txtSdtKhachHangActionPerformed

    private void txtSdtKhachHangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSdtKhachHangKeyPressed

    }//GEN-LAST:event_txtSdtKhachHangKeyPressed

    private void txtSdtKhachHangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSdtKhachHangKeyTyped
        if (txtSdtKhachHang.getText().length() >= 13) {
            evt.consume(); // Ngăn không cho nhập thêm
        }

        if (checkFlag) {
            return;
        }

        checkFlag = true;

        cboSdtKhachHang.removeAllItems();

        String sdt = txtSdtKhachHang.getText().toString().trim();

        for (KhachHang khachHang : khRepo.getAllKhachHangBySDT(true, sdt)) {
            cboSdtKhachHang.addItem(khachHang.getSoDT());
        }

        cboSdtKhachHang.addItem("SĐT");

        cboSdtKhachHang.setSelectedIndex(0);

        checkFlag = false;
    }//GEN-LAST:event_txtSdtKhachHangKeyTyped

    private void txtEmailKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailKhachHangActionPerformed
        if (checkFlag) {
            return;
        }

        checkFlag = true;

        cboEmailKhachHang.removeAllItems();

        String email = txtEmailKhachHang.getText().toString().trim();

        for (KhachHang khachHang : khRepo.getAllKhachHangByEmail(true, email)) {
            cboEmailKhachHang.addItem(khachHang.getMail());
        }

        cboEmailKhachHang.addItem("Email");

        cboEmailKhachHang.setSelectedIndex(0);

        checkFlag = false;
    }//GEN-LAST:event_txtEmailKhachHangActionPerformed

    private void cboSdtKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSdtKhachHangActionPerformed
        if (checkFlag) {
            return;
        }

        if (cboSdtKhachHang.getSelectedIndex() >= 0) {
            String sdt = cboSdtKhachHang.getSelectedItem().toString().trim();

            KhachHang kh = khRepo.getKhachHangBySDT(true, sdt);

            showForm(kh);
        }

        checkFlag = false;
    }//GEN-LAST:event_cboSdtKhachHangActionPerformed

    private void cboEmailKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEmailKhachHangActionPerformed
        if (checkFlag) {
            return;
        }

        if (cboEmailKhachHang.getSelectedIndex() >= 0) {
            String email = cboEmailKhachHang.getSelectedItem().toString().trim();

            KhachHang kh = khRepo.getKhachHangByEmail(true, email);

            showForm(kh);
        }

        checkFlag = false;
    }//GEN-LAST:event_cboEmailKhachHangActionPerformed

    private void txtEmailKhachHangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKhachHangKeyTyped
        if (checkFlag) {
            return;
        }

        checkFlag = true;

        cboEmailKhachHang.removeAllItems();

        String email = txtEmailKhachHang.getText().toString().trim();

        for (KhachHang khachHang : khRepo.getAllKhachHangByEmail(true, email)) {
            cboEmailKhachHang.addItem(khachHang.getMail());
        }

        cboEmailKhachHang.addItem("Email");

        cboEmailKhachHang.setSelectedIndex(0);

        checkFlag = false;
    }//GEN-LAST:event_txtEmailKhachHangKeyTyped

    private void btnLamMoiKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiKhachHangActionPerformed
        txtMaKH.setText(null);
        txtSdtKhachHang.setText(null);
        txtEmailKhachHang.setText(null);
        txtHoTenKhachHang.setText(null);
        txtDiaChiKhachHang.setText(null);

        cboEmailKhachHang.setSelectedItem("Email");
        cboEmailKhachHang.setSelectedItem("SĐT");

        rdoNam.setSelected(true);
    }//GEN-LAST:event_btnLamMoiKhachHangActionPerformed

    private void btnXacNhanKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanKhachHangActionPerformed

        if (readForm() != null) {
            JOptionPane.showMessageDialog(this, "Tạo đơn thành công");
            this.kh = readForm();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập các thông tin");
        }
    }//GEN-LAST:event_btnXacNhanKhachHangActionPerformed

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
            java.util.logging.Logger.getLogger(AddClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddClient dialog = new AddClient(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnLamMoiKhachHang;
    private javax.swing.JButton btnXacNhanKhachHang;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboEmailKhachHang;
    private javax.swing.JComboBox<String> cboSdtKhachHang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlAddKhachHang;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTextArea txtDiaChiKhachHang;
    private javax.swing.JTextField txtEmailKhachHang;
    private javax.swing.JTextField txtHoTenKhachHang;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSdtKhachHang;
    // End of variables declaration//GEN-END:variables
}
