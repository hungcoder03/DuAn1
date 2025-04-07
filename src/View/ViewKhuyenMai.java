/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.KhuyenMaiModel.KhuyenMai;
import Model.KhuyenMaiModel.KhuyenMaiChiTiet;
import Model.KhuyenMaiModel.LoaiGiamGia;
import Model.KhuyenMaiModel.NhanVienAndKhuyenMai;
import Repository.KhuyenMaiRepo;
import java.awt.Color;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class ViewKhuyenMai extends javax.swing.JPanel {

    private KhuyenMaiRepo kmRepo = new KhuyenMaiRepo();

    private DefaultTableModel mol = new DefaultTableModel();

    private int index = -1;

    private boolean isInitializing;

    /**
     * Creates new form ViewKhuyenMaiTest
     */
    public ViewKhuyenMai() {
        initComponents();

//        dteNgayBatDau.setDate(new Date());
        //Combobox Sắp xếp theo ngày kết thúc
        cboSapXepTheoNgayKetThuc.removeAllItems();

        cboSapXepTheoNgayKetThuc.addItem("Mặc định");
        cboSapXepTheoNgayKetThuc.addItem("Sắp kết thúc");
        cboSapXepTheoNgayKetThuc.addItem("Ngày bắt đầu");

        cboSapXepTheoNgayKetThuc.setSelectedItem("Mặc định");

        for (int i = 0; i < cboSapXepTheoNgayKetThuc.getItemCount(); i++) {
            System.out.println(cboSapXepTheoNgayKetThuc.getItemAt(i));
        }

        rdoConHan.setSelected(true);
        fillTable(kmRepo.getAllKhuyenMai(true));

        //Nếu kmRepo.getAllKhuyenMai() không trống (hoặc lỗi) thì đẩy dữ liệu lên bảng
        rdoConHan.addActionListener(e -> {
            if (rdoConHan.isSelected()) {
                fillTable(kmRepo.getAllKhuyenMai(true));
            } else {
                fillTable(kmRepo.getAllKhuyenMai(false));
            }
            showForm(0);
            txtSearchByMaGiamGia.setText(null);
        });

        rdoHetHan.addActionListener(e -> {
            if (rdoHetHan.isSelected()) {
                fillTable(kmRepo.getAllKhuyenMai(false));
            } else {
                fillTable(kmRepo.getAllKhuyenMai(true));
            }
            showForm(0);
            txtSearchByMaGiamGia.setText(null);
        });

        //Xóa các item mặc định của cboLoaiGiam
        cboLoaiGiam.removeAllItems();

        //Đổ dữ liệu từ repo vào cboLoaiGiam
        for (LoaiGiamGia lgg : kmRepo.getAllTenLoaiGiamGia()) {
            cboLoaiGiam.addItem(lgg.getLoaiGiamGia());
        }

        //Xóa các item mặc định của cboTenNhanVien
        cboTenNhanVien.removeAllItems();

        //Đổ dữ liệu từ repo vào cboTenNhanVien
        for (NhanVienAndKhuyenMai nvAndKm : kmRepo.getAllTenNhanVien()) {
            cboTenNhanVien.addItem(nvAndKm.getHoTen());
        }

        showForm(0);

        isInitializing = false; // Đánh dấu đã khởi tạo xong

    }

    //Method đẩy dữ liệu lên bảng KhuyenMai
    void fillTable(List<KhuyenMai> listKM) {
        mol = (DefaultTableModel) tblKhuyenMai.getModel();
        mol.setRowCount(0);
        int stt = 1;
        for (KhuyenMai khuyenMai : listKM) {
            mol.addRow(khuyenMai.toDataRow(stt));
            stt++;
        }

        // Tạo renderer để căn giữa dữ liệu
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho tất cả các cột
        for (int i = 0; i < mol.getColumnCount(); i++) {
            tblKhuyenMai.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    KhuyenMaiChiTiet readForm() {
        String maGiamGia;
        String tenNhanVien;
        String loaiGiamGia;
        float dieuKien = 0;
        float mucGiam = 0, mucGiamToiDa = 0;
        int soLuong = 0;
        Date ngayBatDau, ngayKetThuc, ngayTao, ngaySua = null;
        String ghiChu;
        boolean trangThai;

        boolean check = true;

        // Lấy giá trị từ các trường nhập liệu
        maGiamGia = txtMaGiamGia.getText().toString().trim();
        if (maGiamGia.isEmpty()) {
            txtMaGiamGia.requestFocus();
            txtMaGiamGia.setBackground(Color.yellow);  // Nếu trống, đổi màu nền thành vàng
            check = false;
        } else {
            txtMaGiamGia.setBackground(Color.WHITE);  // Nếu có dữ liệu, khôi phục lại màu nền
        }

        tenNhanVien = cboTenNhanVien.getSelectedItem().toString();

        loaiGiamGia = cboLoaiGiam.getSelectedItem().toString();

        try {
            dieuKien = Float.parseFloat(txtDieuKien.getText().toString().trim());
            if (dieuKien < 0) {
                txtDieuKien.requestFocus();
                txtDieuKien.setBackground(Color.yellow);
                check = false;
            } else {
                txtDieuKien.setBackground(Color.WHITE);  // Khôi phục lại màu nền khi nhập hợp lệ
            }
        } catch (Exception e) {
            txtDieuKien.requestFocus();
            txtDieuKien.setBackground(Color.yellow);
            check = false;
        }

        try {
            mucGiam = Float.parseFloat(txtMucGiam.getText().toString().trim());
            if (mucGiam < 0) {
                txtMucGiam.requestFocus();
                txtMucGiam.setBackground(Color.yellow);
                check = false;
            } else {
                txtMucGiam.setBackground(Color.WHITE);  // Khôi phục lại màu nền khi nhập hợp lệ
            }
        } catch (Exception e) {
            txtMucGiam.requestFocus();
            txtMucGiam.setBackground(Color.yellow);
            check = false;
        }

        try {
            mucGiamToiDa = Float.parseFloat(txtMucGiamToiDa.getText().toString().trim());
            if (mucGiamToiDa < 0) {
                txtMucGiamToiDa.requestFocus();
                txtMucGiamToiDa.setBackground(Color.yellow);
                check = false;
            } else {
                txtMucGiamToiDa.setBackground(Color.WHITE);  // Khôi phục lại màu nền khi nhập hợp lệ
            }
        } catch (Exception e) {
            txtMucGiamToiDa.requestFocus();
            txtMucGiamToiDa.setBackground(Color.yellow);
            check = false;
        }

        try {
            soLuong = Integer.parseInt(txtSoLuongTon.getText().toString().trim());
            if (soLuong < 0) {
                txtSoLuongTon.requestFocus();
                txtSoLuongTon.setBackground(Color.yellow);
                check = false;
            } else {
                txtSoLuongTon.setBackground(Color.WHITE);  // Khôi phục lại màu nền khi nhập hợp lệ
            }
        } catch (Exception e) {
            txtSoLuongTon.requestFocus();
            txtSoLuongTon.setBackground(Color.yellow);
            check = false;
        }

        // Kiểm tra ngày bắt đầu và ngày kết thúc
        ngayBatDau = dteNgayBatDau.getDate();
        ngayKetThuc = dteNgayKetThuc.getDate();
        if (ngayKetThuc.compareTo(ngayBatDau) <= -1) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không được trước ngày bắt đầu!");
            check = false;
        }

        ngayTao = new Date();

        ghiChu = txtGhiChu.getText().toString().trim();
        if (ghiChu.isEmpty()) {
            txtGhiChu.requestFocus();
            txtGhiChu.setBackground(Color.yellow);  // Nếu trống, đổi màu nền thành vàng
            check = false;
        } else {
            txtGhiChu.setBackground(Color.WHITE);  // Khôi phục lại màu nền khi có dữ liệu
        }

        // Kiểm tra trạng thái
        if (rdoConHanDetail.isSelected()) {
            trangThai = true;
        } else {
            trangThai = false;
        }

        // Nếu không có lỗi, trả về đối tượng KhuyenMaiChiTiet, nếu có lỗi trả về null
        if (check) {
            return new KhuyenMaiChiTiet(
                    maGiamGia,
                    tenNhanVien,
                    loaiGiamGia,
                    dieuKien,
                    mucGiam,
                    mucGiamToiDa,
                    soLuong,
                    ngayBatDau,
                    ngayKetThuc,
                    ngayTao,
                    ngaySua,
                    ghiChu,
                    trangThai
            );
        } else {
            return null;
        }
    }

    //Show Khuyến mãi chi tiết
    void showForm(int index) {

        txtMaGiamGia.setText(kmRepo.getKhuyenMaiChiTietByMa(mol.getValueAt(index, 1).toString())
                .getMaGiamGia());

        cboTenNhanVien.setSelectedItem(kmRepo.getKhuyenMaiChiTietByMa(mol.getValueAt(index, 1).toString())
                .getTenNhanVien());

        cboLoaiGiam.setSelectedItem(kmRepo.getKhuyenMaiChiTietByMa(mol.getValueAt(index, 1).toString())
                .getLoaiGiamGia());

        if (kmRepo.getKhuyenMaiChiTietByMa(mol.getValueAt(index, 1).toString())
                .getLoaiGiamGia()
                .equalsIgnoreCase("Giảm theo tổng tiền")) {
            cboLoaiGiam.setSelectedItem("Giảm theo tổng tiền");
        } else {
            cboLoaiGiam.setSelectedItem("Giảm theo số lượng mua");
        }

        txtDieuKien.setText(String.valueOf(kmRepo.getKhuyenMaiChiTietByMa(mol.getValueAt(index, 1).toString())
                .getDieuKien()));

        txtMucGiam.setText(String.valueOf(kmRepo.getKhuyenMaiChiTietByMa(mol.getValueAt(index, 1).toString())
                .getMucGiam()));
        txtMucGiamToiDa.setText(String.valueOf(kmRepo.getKhuyenMaiChiTietByMa(mol.getValueAt(index, 1).toString())
                .getMucGiamToiDa()));

        txtSoLuongTon.setText(String.valueOf(kmRepo.getKhuyenMaiChiTietByMa(mol.getValueAt(index, 1).toString())
                .getSoLuong()));

        if (kmRepo.getKhuyenMaiChiTietByMa(mol.getValueAt(index, 1).toString())
                .isTrangThai()) {
            rdoConHanDetail.setSelected(true);
        } else {
            rdoHetHanDetail.setSelected(true);
        }

        if (kmRepo.getKhuyenMaiChiTietByMa(mol.getValueAt(index, 1).toString())
                .getGhiChu() == null) {
            txtGhiChu.setText("Không có thông tin sự kiện");
        } else {
            txtGhiChu.setText(kmRepo.getKhuyenMaiChiTietByMa(mol.getValueAt(index, 1).toString())
                    .getGhiChu());
        }

        dteNgayBatDau.setDate(kmRepo.getKhuyenMaiChiTietByMa(mol.getValueAt(index, 1).toString())
                .getNgayBatDau());
        dteNgayKetThuc.setDate(kmRepo.getKhuyenMaiChiTietByMa(mol.getValueAt(index, 1).toString())
                .getNgayKetThuc());

        tblKhuyenMai.setRowSelectionInterval(index, index);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rdoGrTinhTrang = new javax.swing.ButtonGroup();
        rdoGrTrangThai = new javax.swing.ButtonGroup();
        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblSearchByMaGiamGia = new javax.swing.JLabel();
        txtSearchByMaGiamGia = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboSapXepTheoNgayKetThuc = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        rdoConHan = new javax.swing.JRadioButton();
        rdoHetHan = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhuyenMai = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        txtGhiChu = new javax.swing.JTextArea();
        lblThongTinSuKien = new javax.swing.JLabel();
        lblNgayBatDau = new javax.swing.JLabel();
        dteNgayBatDau = new com.toedter.calendar.JDateChooser();
        lblNgayKetThuc = new javax.swing.JLabel();
        dteNgayKetThuc = new com.toedter.calendar.JDateChooser();
        lblMucGiamGia = new javax.swing.JLabel();
        txtMucGiam = new javax.swing.JTextField();
        btnTao = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        lblTinhTrang = new javax.swing.JLabel();
        rdoConHanDetail = new javax.swing.JRadioButton();
        rdoHetHanDetail = new javax.swing.JRadioButton();
        lblMucGiamToiDa = new javax.swing.JLabel();
        txtMucGiamToiDa = new javax.swing.JTextField();
        lblDieuKien = new javax.swing.JLabel();
        txtDieuKien = new javax.swing.JTextField();
        lblLoaiGiam = new javax.swing.JLabel();
        cboLoaiGiam = new javax.swing.JComboBox<>();
        lblNhanVienTao = new javax.swing.JLabel();
        lblSoLuongTon = new javax.swing.JLabel();
        txtSoLuongTon = new javax.swing.JTextField();
        cboTenNhanVien = new javax.swing.JComboBox<>();
        lblMaGiamGia = new javax.swing.JLabel();
        txtMaGiamGia = new javax.swing.JTextField();
        btnLamMoi = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1700, 850));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jPanel8.setPreferredSize(new java.awt.Dimension(1200, 480));
        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblSearchByMaGiamGia.setText("Tìm kiếm: ");

        txtSearchByMaGiamGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSearchByMaGiamGiaMouseClicked(evt);
            }
        });
        txtSearchByMaGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchByMaGiamGiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSearchByMaGiamGia)
                .addContainerGap(184, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(68, Short.MAX_VALUE)
                    .addComponent(txtSearchByMaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblSearchByMaGiamGia)
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(39, Short.MAX_VALUE)
                    .addComponent(txtSearchByMaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(39, Short.MAX_VALUE)))
        );

        jPanel10.add(jPanel2);

        jLabel1.setText("Sắp xếp theo ngày kết thúc");

        cboSapXepTheoNgayKetThuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboSapXepTheoNgayKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSapXepTheoNgayKetThucActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(cboSapXepTheoNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboSapXepTheoNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel1);

        jLabel20.setText("Trạng thái");

        rdoGrTrangThai.add(rdoConHan);
        rdoConHan.setText("Đang diễn ra");

        rdoGrTrangThai.add(rdoHetHan);
        rdoHetHan.setText("Kết thúc");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addComponent(rdoConHan)
                .addGap(18, 18, 18)
                .addComponent(rdoHetHan)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(rdoConHan)
                    .addComponent(rdoHetHan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel12);

        jPanel8.add(jPanel10);

        tblKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã giảm giá", "Loại giảm giá", "Mức giảm giá", "Số lượng tồn", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhuyenMai.setIntercellSpacing(new java.awt.Dimension(0, 5));
        tblKhuyenMai.setRowHeight(40);
        tblKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhuyenMai);

        jPanel8.add(jScrollPane2);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết khuyến mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        txtGhiChu.setColumns(20);
        txtGhiChu.setLineWrap(true);
        txtGhiChu.setRows(5);
        txtGhiChu.setWrapStyleWord(true);

        lblThongTinSuKien.setText("Thông tin sự kiện");

        lblNgayBatDau.setText("Ngày bắt đầu");

        lblNgayKetThuc.setText("Ngày kết thúc");

        lblMucGiamGia.setText("Mức giảm giá (%)");

        txtMucGiam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMucGiamActionPerformed(evt);
            }
        });
        txtMucGiam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMucGiamKeyTyped(evt);
            }
        });

        btnTao.setText("Tạo");
        btnTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        lblTinhTrang.setText("Tình trạng");

        rdoGrTinhTrang.add(rdoConHanDetail);
        rdoConHanDetail.setText("Đang diễn ra");

        rdoGrTinhTrang.add(rdoHetHanDetail);
        rdoHetHanDetail.setText("Kết thúc");

        lblMucGiamToiDa.setText("Mức giảm tối đa (VNĐ)");

        txtMucGiamToiDa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMucGiamToiDaKeyTyped(evt);
            }
        });

        lblDieuKien.setText("Điều kiện (Từ ... trở lên)");

        txtDieuKien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDieuKienActionPerformed(evt);
            }
        });
        txtDieuKien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDieuKienKeyTyped(evt);
            }
        });

        lblLoaiGiam.setText("Loại giảm");

        cboLoaiGiam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLoaiGiam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiGiamActionPerformed(evt);
            }
        });

        lblNhanVienTao.setText("Nhân viên tạo");

        lblSoLuongTon.setText("Số lượng tồn");

        txtSoLuongTon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongTonActionPerformed(evt);
            }
        });
        txtSoLuongTon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSoLuongTonKeyTyped(evt);
            }
        });

        cboTenNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblMaGiamGia.setText("Mã giảm giá");

        txtMaGiamGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMaGiamGiaKeyTyped(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(206, 206, 206)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblThongTinSuKien)
                    .addComponent(lblSoLuongTon)
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMucGiamGia)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblNgayBatDau, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dteNgayBatDau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMucGiam, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTinhTrang, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                                .addComponent(rdoConHanDetail)
                                .addGap(18, 18, 18)
                                .addComponent(rdoHetHanDetail))
                            .addComponent(lblDieuKien, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDieuKien, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(lblMaGiamGia)
                                .addGap(30, 30, 30)))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNgayKetThuc)
                                    .addComponent(lblLoaiGiam)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblNhanVienTao)
                                    .addComponent(cboLoaiGiam, 0, 214, Short.MAX_VALUE)
                                    .addComponent(cboTenNhanVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dteNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblMucGiamToiDa)
                                            .addComponent(txtMucGiamToiDa, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(75, 75, 75))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtMaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(133, 133, 133)))
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnTao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))))))
                .addContainerGap(281, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTao, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dteNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dteNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMucGiamGia)
                            .addComponent(btnSua)
                            .addComponent(lblMucGiamToiDa))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMucGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMucGiamToiDa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLamMoi))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDieuKien)
                            .addComponent(lblLoaiGiam))
                        .addGap(17, 17, 17))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblThongTinSuKien)
                                    .addComponent(lblNgayBatDau)
                                    .addComponent(lblNgayKetThuc)))
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblMaGiamGia)
                                .addComponent(txtMaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDieuKien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLoaiGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSoLuongTon))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTinhTrang)
                    .addComponent(lblNhanVienTao)
                    .addComponent(txtSoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoConHanDetail)
                    .addComponent(rdoHetHanDetail)
                    .addComponent(cboTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1700, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 94, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(144, 144, 144)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 95, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuyenMaiMouseClicked
        //Lấy thứ tự hàng
        int index = tblKhuyenMai.getSelectedRow();

        //Truyền dữ liệu vào form
        showForm(index);
    }//GEN-LAST:event_tblKhuyenMaiMouseClicked

    private void btnTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoActionPerformed
        //Kiểm tra form
        if (readForm() != null) {
            //Kiểm tra mã
            if (!kmRepo.isMaGiamGiaExists(readForm().getMaGiamGia())) {
                //Kiểm tra ngày bắt đầu
                if (checkNgayBatDau(readForm().getNgayBatDau())) {
                    if (!checkMucGiam()) {
                        return;
                    }

                    System.out.println((readForm().getNgayBatDau()).compareTo(new Date()));
                    int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm?");

                    if (chon == 0) {
//                    String maNV = kmRepo.getNhanVienByName(readForm().getTenNhanVien().toString());
//                    System.out.println("Mã NV: " +maNV);
//                    int maLoai = kmRepo.getLoaiGiamGiaByName(readForm().getLoaiGiamGia().toString());
//                    System.out.println("Mã Loại: " +maLoai);
                        System.out.println(readForm());

                        kmRepo.insertKhuyenMai(readForm());
                        if (readForm().isTrangThai()) {
                            rdoConHan.setSelected(true);
                            fillTable(kmRepo.getAllKhuyenMai(readForm().isTrangThai()));
//                            tblKhuyenMai.setRowSelectionInterval(index, index);
                        } else {
                            rdoHetHan.setSelected(true);
                            fillTable(kmRepo.getAllKhuyenMai(readForm().isTrangThai()));
//                            tblKhuyenMai.setRowSelectionInterval(index, index);
                        }
                        JOptionPane.showMessageDialog(this, "Thêm thành công!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Bạn không thêm dữ liệu!");
                    }
                } else {
                    System.out.println((readForm().getNgayBatDau()).compareTo(new Date()));
                    JOptionPane.showMessageDialog(this, "Ngày bắt đầu không ở quá khứ");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Đã có mã giảm giá này, vui lòng kiểm tra lại!");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu, vui lòng kiểm tra lại!");
        }
    }//GEN-LAST:event_btnTaoActionPerformed

    private Boolean checkNgayBatDau(Date ngayBatDau) {
        if (ngayBatDau.compareTo(new Date()) < -1) {
            return false;
        } else {
            return true;
        }
    }

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int index = tblKhuyenMai.getSelectedRow();
        String maGG = kmRepo.getKhuyenMaiChiTietByMa(mol.getValueAt(index, 1).toString())
                .getMaGiamGia();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng để sửa");
        } else {
            //Check form không rỗng
            if (readForm() != null) {

                //Check ngày bắt đầu
                if (checkNgayBatDau(readForm().getNgayBatDau())) {
                    if (!checkMucGiam()) {
                        return;
                    }

                    int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa??");

                    if (chon == 0) {

                        kmRepo.updateKhuyenMai(readForm(), maGG);
                        if (readForm().isTrangThai()) {
                            rdoConHan.setSelected(true);
                            fillTable(kmRepo.getAllKhuyenMai(readForm().isTrangThai()));
                            tblKhuyenMai.setRowSelectionInterval(index, index);
                        } else {
                            rdoHetHan.setSelected(true);
                            fillTable(kmRepo.getAllKhuyenMai(readForm().isTrangThai()));
                            tblKhuyenMai.setRowSelectionInterval(index, index);
                        }
                        JOptionPane.showMessageDialog(this, "Sửa thành công!");

                    } else {
                        JOptionPane.showMessageDialog(this, "Bạn không sửa dữ liệu!");
                    }
                } else {
                    System.out.println((readForm().getNgayBatDau()).compareTo(new Date()));
                    JOptionPane.showMessageDialog(this, "Ngày bắt đầu không ở quá khứ");
                }

            } else {
                JOptionPane.showMessageDialog(this, "Lỗi dữ liệu, vui lòng kiểm tra lại!");
            }
        }

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        txtMaGiamGia.setText(null);
        txtDieuKien.setText(null);
        txtGhiChu.setText(null);
        txtMucGiam.setText(null);
        txtMucGiamToiDa.setText(null);
        txtSoLuongTon.setText(null);
        txtSearchByMaGiamGia.setText(null);
        dteNgayBatDau.setDate(new Date());
        dteNgayKetThuc.setDate(new Date());
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void cboSapXepTheoNgayKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSapXepTheoNgayKetThucActionPerformed
        txtSearchByMaGiamGia.setText(null);

        if (isInitializing) {
            return; // Bỏ qua sự kiện nếu đang trong quá trình khởi tạo
        }

        Object selectedItem = cboSapXepTheoNgayKetThuc.getSelectedItem();
        if (selectedItem == null) {
            return; // Tránh lỗi NullPointerException
        }

//        cboSapXepTheoNgayKetThuc.setSelectedItem("Mặc định");
        String selecteDefault = cboSapXepTheoNgayKetThuc.getSelectedItem().toString();

        if (selecteDefault.equals("Mặc định")) {
            if (rdoConHan.isSelected()) {
                fillTable(kmRepo.getAllKhuyenMai(true));
            } else {
                fillTable(kmRepo.getAllKhuyenMai(false));
            }

        } else if (selecteDefault.equals("Sắp kết thúc")) {
            sortTableByDate(mol, 6, true);
        } else if (selecteDefault.equals("Ngày bắt đầu")) {
            sortTableByDate(mol, 5, false);
        }
    }//GEN-LAST:event_cboSapXepTheoNgayKetThucActionPerformed

    private void txtSearchByMaGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchByMaGiamGiaActionPerformed
        String maGG = txtSearchByMaGiamGia.getText().toString().trim();

        if (maGG.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã giảm giá!");
        } else {
            if (!kmRepo.searchByMaGiamGia(maGG).isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tìm thấy dữ liệu");
                fillTable(kmRepo.searchByMaGiamGia(maGG));
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu");
            }
        }
    }//GEN-LAST:event_txtSearchByMaGiamGiaActionPerformed

    private void txtSearchByMaGiamGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchByMaGiamGiaMouseClicked
        txtSearchByMaGiamGia.setText(null);
    }//GEN-LAST:event_txtSearchByMaGiamGiaMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        txtSearchByMaGiamGia.setText(null);
    }//GEN-LAST:event_formMouseClicked

    private void cboLoaiGiamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiGiamActionPerformed
        if (isInitializing) {
            return; // Bỏ qua sự kiện nếu đang trong quá trình khởi tạo
        }

        Object selectedItem = cboLoaiGiam.getSelectedItem();
        if (selectedItem == null) {
            return; // Tránh lỗi NullPointerException
        }

        if (cboLoaiGiam.getSelectedItem().equals("Giảm theo VNĐ")) {
            lblMucGiamGia.setText("Mức giảm giá (VNĐ)");
            // Kiểm tra giá trị của txtMucGiam
            String mucGiam = txtMucGiam.getText().trim();
            if (!mucGiam.isEmpty()) {
                txtMucGiamToiDa.setText(mucGiam); // Gán giá trị mới
            }
            lblMucGiamToiDa.setEnabled(false);
            txtMucGiamToiDa.setEnabled(false);
        } else {
            lblMucGiamGia.setText("Mức giảm giá (%)");
            lblMucGiamToiDa.setEnabled(true);
            txtMucGiamToiDa.setEnabled(true);
        }
    }//GEN-LAST:event_cboLoaiGiamActionPerformed

    private boolean checkMucGiam() {
        Float mucGiam = Float.parseFloat(txtMucGiam.getText().toString().trim());

        if (cboLoaiGiam.getSelectedItem().equals("Giảm theo VNĐ")) {
            if (mucGiam >= 1000000) {
                JOptionPane.showMessageDialog(this, "Mức giảm không được quá lớn");
                txtMucGiam.requestFocus();
                return false;
            } else if (mucGiam <= 0) {
                JOptionPane.showMessageDialog(this, "Mức giảm không được quá nhỏ");
                txtMucGiam.requestFocus();
                return false;
            } else {
                txtMucGiamToiDa.setText(txtMucGiam.getText().toString().trim());
                return true;
            }

        } else {
            if (mucGiam > 100) {
                JOptionPane.showMessageDialog(this, "Mức giảm không được quá lớn");
                txtMucGiam.requestFocus();
                return false;
            } else if (mucGiam <= 0) {
                JOptionPane.showMessageDialog(this, "Mức giảm không được quá nhỏ");
                txtMucGiam.requestFocus();
                return false;
            }
            return true;
        }
    }

    private void txtMucGiamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMucGiamActionPerformed
        if (isInitializing) {
            return; // Bỏ qua sự kiện nếu đang trong quá trình khởi tạo
        }

        Object selectedItem = cboLoaiGiam.getSelectedItem();
        if (selectedItem == null) {
            return; // Tránh lỗi NullPointerException
        }

        if (!checkMucGiam())
            return;
    }//GEN-LAST:event_txtMucGiamActionPerformed

    private void txtDieuKienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDieuKienActionPerformed
        if (isInitializing) {
            return; // Bỏ qua sự kiện nếu đang trong quá trình khởi tạo
        }

        Object selectedItem = cboLoaiGiam.getSelectedItem();
        if (selectedItem == null) {
            return; // Tránh lỗi NullPointerException
        }

        Float dieuKien = Float.parseFloat(txtDieuKien.getText().toString().trim());
        if (dieuKien < 0) {
            JOptionPane.showMessageDialog(this, "Điều kiện không được quá nhỏ");
            txtDieuKien.requestFocus();
        } else if (dieuKien > 5000000) {
            JOptionPane.showMessageDialog(this, "Điều kiện không được quá lớn");
            txtDieuKien.requestFocus();
        }


    }//GEN-LAST:event_txtDieuKienActionPerformed

    private void txtSoLuongTonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongTonActionPerformed
        if (isInitializing) {
            return; // Bỏ qua sự kiện nếu đang trong quá trình khởi tạo
        }

        Object selectedItem = cboLoaiGiam.getSelectedItem();
        if (selectedItem == null) {
            return; // Tránh lỗi NullPointerException
        }

        Integer soLuong = Integer.parseInt(txtSoLuongTon.getText().toString().trim());
        if (soLuong < 0) {
            JOptionPane.showMessageDialog(this, "Số lượng không được quá nhỏ");
            txtSoLuongTon.requestFocus();
        } else if (soLuong > 10000) {
            JOptionPane.showMessageDialog(this, "Số lượng không được quá lớn");
            txtSoLuongTon.requestFocus();
        }
    }//GEN-LAST:event_txtSoLuongTonActionPerformed

    private void txtSoLuongTonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongTonKeyTyped
        if (txtSoLuongTon.getText().length() >= 6) {
            evt.consume(); // Ngăn không cho nhập thêm
        }
    }//GEN-LAST:event_txtSoLuongTonKeyTyped

    private void txtDieuKienKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDieuKienKeyTyped
        if (txtDieuKien.getText().length() >= 8) {
            evt.consume(); // Ngăn không cho nhập thêm
        }
    }//GEN-LAST:event_txtDieuKienKeyTyped

    private void txtMucGiamToiDaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMucGiamToiDaKeyTyped
        if (txtMucGiamToiDa.getText().length() >= 8) {
            evt.consume(); // Ngăn không cho nhập thêm
        }
    }//GEN-LAST:event_txtMucGiamToiDaKeyTyped

    private void txtMucGiamKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMucGiamKeyTyped
        if (txtMucGiam.getText().length() >= 8) {
            evt.consume(); // Ngăn không cho nhập thêm
        }
    }//GEN-LAST:event_txtMucGiamKeyTyped

    private void txtMaGiamGiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaGiamGiaKeyTyped
        if (txtMaGiamGia.getText().length() >= 20) {
            evt.consume(); // Ngăn không cho nhập thêm
        }
    }//GEN-LAST:event_txtMaGiamGiaKeyTyped

    private void sortTableByDate(DefaultTableModel mol, int columnIndex, boolean ascending) {
        //Lấy dữ liệu từ model
        List<Object[]> rows = new ArrayList<>();
        for (int i = 0; i < mol.getRowCount(); i++) {
            Object[] row = new Object[mol.getColumnCount()];
            for (int j = 0; j < mol.getColumnCount(); j++) {
                row[j] = mol.getValueAt(i, j);
            }
            rows.add(row);
        }

        //Sắp xếp dữ liệu
        rows.sort((row1, row2) -> {
            String date1 = row1[columnIndex].toString();
            String date2 = row2[columnIndex].toString();

            int comparison = date1.compareTo(date2);
            return ascending ? comparison : -comparison;
        });

        //Cập nhật model
        mol.setRowCount(0);
        for (Object[] row : rows) {
            mol.addRow(row);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTao;
    private javax.swing.JComboBox<String> cboLoaiGiam;
    private javax.swing.JComboBox<String> cboSapXepTheoNgayKetThuc;
    private javax.swing.JComboBox<String> cboTenNhanVien;
    private com.toedter.calendar.JDateChooser dteNgayBatDau;
    private com.toedter.calendar.JDateChooser dteNgayKetThuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDieuKien;
    private javax.swing.JLabel lblLoaiGiam;
    private javax.swing.JLabel lblMaGiamGia;
    private javax.swing.JLabel lblMucGiamGia;
    private javax.swing.JLabel lblMucGiamToiDa;
    private javax.swing.JLabel lblNgayBatDau;
    private javax.swing.JLabel lblNgayKetThuc;
    private javax.swing.JLabel lblNhanVienTao;
    private javax.swing.JLabel lblSearchByMaGiamGia;
    private javax.swing.JLabel lblSoLuongTon;
    private javax.swing.JLabel lblThongTinSuKien;
    private javax.swing.JLabel lblTinhTrang;
    private javax.swing.JRadioButton rdoConHan;
    private javax.swing.JRadioButton rdoConHanDetail;
    private javax.swing.ButtonGroup rdoGrTinhTrang;
    private javax.swing.ButtonGroup rdoGrTrangThai;
    private javax.swing.JRadioButton rdoHetHan;
    private javax.swing.JRadioButton rdoHetHanDetail;
    private javax.swing.JTable tblKhuyenMai;
    private javax.swing.JTextField txtDieuKien;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtMaGiamGia;
    private javax.swing.JTextField txtMucGiam;
    private javax.swing.JTextField txtMucGiamToiDa;
    private javax.swing.JTextField txtSearchByMaGiamGia;
    private javax.swing.JTextField txtSoLuongTon;
    // End of variables declaration//GEN-END:variables
}
