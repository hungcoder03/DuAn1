/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.ThongKeModel.ThongKe;
import Model.ThongKeModel.ThongKeSPTop3;
import Repository.ThongKeRepository;
import Repository.ThongKeSPBanChay;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import jdk.jfr.consumer.EventStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author admin
 */
public class ViewThongKe11 extends javax.swing.JPanel {

    private ThongKeRepository rptk = new ThongKeRepository();
    private ThongKeSPBanChay rptkSP = new ThongKeSPBanChay();
    private ArrayList<ThongKe> list = new ArrayList<>();
    private ArrayList<ThongKeSPTop3> listSP = new ArrayList<>();
    private DefaultTableModel dtmTK = new DefaultTableModel();
    private DefaultTableModel dtmTop3 = new DefaultTableModel();

    /**
     * Creates new form ViewThongKeTest
     */
    public ViewThongKe11() {
        initComponents();

        dtmTK = (DefaultTableModel) tblFormThongKe_DoanhThuSanPham.getModel();
        dtmTop3 = (DefaultTableModel) tblSanPhamBanChay_FormViewThongKe.getModel();
        capNhatBangDoanhThu(list);
        listSP = rptkSP.getAllSPBanChay();
        ShowDataTableSPTop3(listSP);

        // Gọi phương thức để cập nhật doanh thu hôm nay
        double doanhThuHomNay = rptk.getDoanhThuHomNay();

        setDoanhThuHomNay(doanhThuHomNay); // Cập nhật doanh thu hôm nay lên giao diện
        double doanhThuHomQua = rptk.getDoanhThuHomQua();
        setDoanhThuHomQua(doanhThuHomQua);
        int tongSanPhamHomNay = rptk.getTongSanPhamHomNay();
        settongSanPhamHomNay(tongSanPhamHomNay);
        int TongDonHomNay = rptk.getTongDonHomNay();
        settongTongDonHomNay(TongDonHomNay);

        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã Hóa Đơn");
        model.addColumn("Tên Sản Phẩm");
        model.addColumn("Số Lượng");
        model.addColumn("Tổng Tiền");

        model.addRow(new Object[]{"HD001", "Sản phẩm A", 10, 500});
        model.addRow(new Object[]{"HD002", "Sản phẩm B", 5, 300});
        model.addRow(new Object[]{"HD003", "Sản phẩm C", 3, 150});

        table.setModel(model);

        cbxTimKiemTheoNam.setSelectedIndex(0);
        capNhatBangDoanhThu(rptk.getDoanhThuTheoNam(2024));
    }

    public void ShowDataTableSPTop3(ArrayList<ThongKeSPTop3> al) {
        dtmTop3.setRowCount(0);
        DecimalFormat df = new DecimalFormat("#.0"); // Chỉ hiển thị 1 chữ số sau dấu phẩy
        for (ThongKeSPTop3 thongK : al) {
            Float tongTien = thongK.getTongTienThanhToan();
            String formattedTongTien = (tongTien == null)
                    ? "0"
                    : df.format(tongTien);

            dtmTop3.addRow(new Object[]{
                thongK.getTenSanPham(),
                thongK.getSoLuong(),
                formattedTongTien
            });
        }
        
        // Tạo renderer để căn giữa dữ liệu
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho tất cả các cột
        for (int i = 0; i < dtmTop3.getColumnCount(); i++) {
            tblSanPhamBanChay_FormViewThongKe.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void capNhatDoanhThu() {
        // Lấy giá trị từ ComboBox tháng bắt đầu và tháng kết thúc
        String thangBatDauStr = cbxThangBatDau.getSelectedItem().toString();
        int thangBatDau = Integer.parseInt(thangBatDauStr.replace("Tháng ", "").trim());

        String thangKetThucStr = cbxThangKetThuc.getSelectedItem().toString();
        int thangKetThuc = Integer.parseInt(thangKetThucStr.replace("Tháng ", "").trim());

        // Kiểm tra điều kiện tháng bắt đầu phải nhỏ hơn hoặc bằng tháng kết thúc
        if (thangBatDau > thangKetThuc) {
            cbxThangBatDau.setSelectedIndex(0);
            // Hiển thị thông báo lỗi nếu tháng bắt đầu lớn hơn tháng kết thúc
            JOptionPane.showMessageDialog(null, "Tháng bắt đầu không thể lớn hơn tháng kết thúc!",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;  // Dừng phương thức nếu dữ liệu không hợp lệ
        } else {
            ThongKe tk = rptk.getDoanhThuTheokhoangThoiGian(thangBatDau, thangKetThuc);
            lbl_cbx_TongDon.setText(String.valueOf(tk.getTongHoaDon()));
            DecimalFormat df = new DecimalFormat("0.0");
            String formattedTongTien = (String.valueOf(tk.getTongTienThanhToan()) == null)
                    ? "0"
                    : df.format(tk.getTongTienThanhToan());
            lbl_cbx_TongTien.setText(String.valueOf(formattedTongTien));
            System.out.println(tk.getTongHoaDon());

            if (String.valueOf(tk.getTongTienThanhToan()) == null) {
                lbl_cbx_TongTien.setText("0");
            }
        }

    }

    public void capNhatBangDoanhThu(ArrayList<ThongKe> doanhThu) {
        // Đảm bảo DefaultTableModel là của bảng hiển thị doanh thu
        DefaultTableModel dtm = (DefaultTableModel) tblFormThongKe_DoanhThuSanPham.getModel();

        // Xóa dữ liệu cũ trong bảng
        dtm.setRowCount(0);

        DecimalFormat df = new DecimalFormat("#.0"); // Chỉ hiển thị 1 chữ số sau dấu phẩy

        // Duyệt qua danh sách doanh thu và thêm vào bảng
        for (ThongKe thongKe : doanhThu) {
            Double tongTien = thongKe.getTongTienThanhToan();
            String formattedTongTien = (tongTien == null)
                    ? "0"
                    : df.format(tongTien);

            dtm.addRow(new Object[]{
                thongKe.getThang(),
                thongKe.getTongHoaDon(),
                formattedTongTien
            });
        }
    }

    // Phương thức để hiển thị doanh thu hôm nay
    public void setDoanhThuHomNay(double doanhThuHomNay) {
        // Cập nhật JLabel với doanh thu hôm nay
        lblDoanhThuHomNay.setText(String.format("%.1f", doanhThuHomNay) + " VNĐ");
    }

    public void setDoanhThuHomQua(double doanhThuHomQua) {
        // Cập nhật JLabel với doanh thu hôm nay
        lblDoanhThuHomQua.setText(String.format("%.1f", doanhThuHomQua) + " VNĐ");
    }

    public void settongSanPhamHomNay(int tongSanPhamHomNay) {
        // Cập nhật JLabel với doanh thu hôm nay
        lblTongSanPhamBanHomNay.setText(String.valueOf(tongSanPhamHomNay));
    }

    public void settongTongDonHomNay(int TongDonHomNay) {
        // Cập nhật JLabel với doanh thu hôm nay
        lblTongDonBanHomNay.setText(String.valueOf(TongDonHomNay));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lbl_FormThongKe_DTHomNay = new javax.swing.JLabel();
        lbl_FormThongKe_DTHomNay1 = new javax.swing.JLabel();
        lbl_FormThongKe_DTHomNay2 = new javax.swing.JLabel();
        lbl_FormThongKe_DTHomNay3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPhamBanChay_FormViewThongKe = new javax.swing.JTable();
        lblDoanhThuHomQua = new javax.swing.JLabel();
        lblTongSanPhamBanHomNay = new javax.swing.JLabel();
        lblDoanhThuHomNay = new javax.swing.JLabel();
        lblTongDonBanHomNay = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblFormThongKe_DoanhThuSanPham = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        btn_FormThongKe_XuatExcel = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        cbxThangBatDau = new javax.swing.JComboBox<>();
        cbxThangKetThuc = new javax.swing.JComboBox<>();
        cbxTimKiemTheoNam = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbl_cbx_TongDon = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lbl_cbx_TongTien = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1700, 850));

        jPanel4.setPreferredSize(new java.awt.Dimension(1700, 850));

        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setBackground(new java.awt.Color(204, 204, 204));
        jLabel15.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel15.setText("Doanh Thu");

        lbl_FormThongKe_DTHomNay.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lbl_FormThongKe_DTHomNay.setForeground(new java.awt.Color(102, 102, 102));
        lbl_FormThongKe_DTHomNay.setText("Tổng Đơn Đã Bán Hôm Nay");

        lbl_FormThongKe_DTHomNay1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lbl_FormThongKe_DTHomNay1.setForeground(new java.awt.Color(255, 153, 0));
        lbl_FormThongKe_DTHomNay1.setText("Doanh Thu Hôm Qua");

        lbl_FormThongKe_DTHomNay2.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lbl_FormThongKe_DTHomNay2.setForeground(new java.awt.Color(255, 102, 51));
        lbl_FormThongKe_DTHomNay2.setText("Doanh Thu Hôm Nay");

        lbl_FormThongKe_DTHomNay3.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lbl_FormThongKe_DTHomNay3.setForeground(new java.awt.Color(102, 102, 102));
        lbl_FormThongKe_DTHomNay3.setText("Tổng Sản Phẩm Bán Hôm Nay");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thống kê sản phẩm"));

        tblSanPhamBanChay_FormViewThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Tổng số lượng bán", "Tổng tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPhamBanChay_FormViewThongKe.setRowHeight(35);
        jScrollPane1.setViewportView(tblSanPhamBanChay_FormViewThongKe);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1011, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        lblDoanhThuHomQua.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lblDoanhThuHomQua.setForeground(new java.awt.Color(255, 153, 0));
        lblDoanhThuHomQua.setText("0");

        lblTongSanPhamBanHomNay.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lblTongSanPhamBanHomNay.setForeground(new java.awt.Color(102, 102, 102));
        lblTongSanPhamBanHomNay.setText("0");

        lblDoanhThuHomNay.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lblDoanhThuHomNay.setForeground(new java.awt.Color(255, 102, 51));
        lblDoanhThuHomNay.setText("0");

        lblTongDonBanHomNay.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lblTongDonBanHomNay.setForeground(new java.awt.Color(102, 102, 102));
        lblTongDonBanHomNay.setText("0");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblTongDonBanHomNay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDoanhThuHomNay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_FormThongKe_DTHomNay2, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
                            .addComponent(lbl_FormThongKe_DTHomNay, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblDoanhThuHomQua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTongSanPhamBanHomNay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_FormThongKe_DTHomNay1, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
                            .addComponent(lbl_FormThongKe_DTHomNay3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(444, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_FormThongKe_DTHomNay1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_FormThongKe_DTHomNay2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDoanhThuHomQua)
                    .addComponent(lblDoanhThuHomNay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_FormThongKe_DTHomNay, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_FormThongKe_DTHomNay3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTongSanPhamBanHomNay)
                    .addComponent(lblTongDonBanHomNay))
                .addGap(26, 26, 26)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doanh Thu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Black", 0, 12))); // NOI18N

        tblFormThongKe_DoanhThuSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tháng", "Tổng đơn", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblFormThongKe_DoanhThuSanPham.setRowHeight(35);
        jScrollPane7.setViewportView(tblFormThongKe_DoanhThuSanPham);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel16.setForeground(new java.awt.Color(255, 255, 255));

        jLabel16.setBackground(new java.awt.Color(204, 204, 204));
        jLabel16.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/timkiem.png"))); // NOI18N
        jLabel16.setText("Tìm Kiếm");

        btn_FormThongKe_XuatExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/excel.png"))); // NOI18N
        btn_FormThongKe_XuatExcel.setText("Xuất Excel");
        btn_FormThongKe_XuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_FormThongKe_XuatExcelActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel17.setText("Từ tháng:");

        jLabel18.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel18.setText("Đến:");

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        cbxThangBatDau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12" }));
        cbxThangBatDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxThangBatDauActionPerformed(evt);
            }
        });

        cbxThangKetThuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12" }));
        cbxThangKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxThangKetThucActionPerformed(evt);
            }
        });

        cbxTimKiemTheoNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        cbxTimKiemTheoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTimKiemTheoNamActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Tổng đơn:");

        lbl_cbx_TongDon.setText("0  ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(lbl_cbx_TongDon)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_cbx_TongDon)
                .addGap(12, 12, 12))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tổng tiền:");

        lbl_cbx_TongTien.setText("0  ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(lbl_cbx_TongTien)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_cbx_TongTien)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(cbxThangBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbxThangKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(123, 123, 123))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(cbxTimKiemTheoNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnClear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_FormThongKe_XuatExcel)
                .addGap(25, 25, 25))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cbxThangBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cbxThangKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_FormThongKe_XuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxTimKiemTheoNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 225, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1688, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbxThangBatDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxThangBatDauActionPerformed

        capNhatDoanhThu(); // Cập nhật doanh thu khi chọn tháng bắt đầu

    }//GEN-LAST:event_cbxThangBatDauActionPerformed

    private void cbxThangKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxThangKetThucActionPerformed

        capNhatDoanhThu();
    }//GEN-LAST:event_cbxThangKetThucActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        capNhatBangDoanhThu(list);
        

    }//GEN-LAST:event_btnClearActionPerformed

    private void cbxTimKiemTheoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTimKiemTheoNamActionPerformed

        int nam = Integer.parseInt(cbxTimKiemTheoNam.getSelectedItem().toString());
        capNhatBangDoanhThu(rptk.getDoanhThuTheoNam(nam));


    }//GEN-LAST:event_cbxTimKiemTheoNamActionPerformed

    private void btn_FormThongKe_XuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_FormThongKe_XuatExcelActionPerformed
//        try {
//            // Kiểm tra nếu JComboBox không có giá trị
//            if (cbxTimKiemTheoNam.getSelectedItem() == null) {
//                JOptionPane.showMessageDialog(this, "Vui lòng chọn năm để xuất Excel!");
//                return;
//            }
//
//            // Lấy giá trị từ JComboBox và chuyển đổi thành Integer
//            String yearString = cbxTimKiemTheoNam.getSelectedItem().toString();
//            int year = Integer.parseInt(yearString);  // Chuyển từ String sang Integer
//
//            // Kiểm tra bảng JTable không có dữ liệu
//            if (tblFormThongKe_DoanhThuSanPham.getRowCount() == 0) {
//                JOptionPane.showMessageDialog(this, "Không có dữ liệu trong bảng để xuất Excel!");
//                return;
//            }
//
//            // Gọi phương thức exportTableToExcel
//            exportTableToExcel(tblFormThongKe_DoanhThuSanPham, year);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xuất Excel!");
//        }
//
    }//GEN-LAST:event_btn_FormThongKe_XuatExcelActionPerformed
//    public static void exportTableToExcel(JTable tblFormThongKe_DoanhThuSanPham, int year) {
//        try {
//            DefaultTableModel model = (DefaultTableModel) tblFormThongKe_DoanhThuSanPham.getModel();
//            if (model.getRowCount() == 0) {
//                JOptionPane.showMessageDialog(null, "Không có dữ liệu để xuất file!");
//                return;
//            }
//
//            Workbook workbook = new HSSFWorkbook();
//            Sheet sheet = workbook.createSheet("Doanh Thu");
//
//            // Tạo tiêu đề thời gian
//            String rangeHeader = "  Năm " + year;
//            Row titleRow = sheet.createRow(0);
//            Cell titleCell = titleRow.createCell(0);
//            titleCell.setCellValue(rangeHeader);
//
//            // Gộp ô cho tiêu đề
//            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, 0, 0, model.getColumnCount() - 1));
//
//            // Tạo hàng tiêu đề cột
//            Row headerRow = sheet.createRow(1);
//            for (int col = 0; col < model.getColumnCount(); col++) {
//                Cell cell = headerRow.createCell(col);
//                cell.setCellValue(model.getColumnName(col));
//            }
//
//            // Thêm dữ liệu từ JTable vào Excel
//            for (int row = 0; row < model.getRowCount(); row++) {
//                Row excelRow = sheet.createRow(row + 2);
//                for (int col = 0; col < model.getColumnCount(); col++) {
//                    Cell cell = excelRow.createCell(col);
//                    Object value = model.getValueAt(row, col);
//                    if (value != null) {
//                        cell.setCellValue(value.toString());
//                    }
//                }
//            }
//
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmm");
//            String formattedDate = sdf.format(new Date()); // Lấy ngày và giờ hiện tại
//            String uniqueFileName = "DoanhThu_" + year + "_" + formattedDate + ".xls";
//
//            JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.home")));
//            fileChooser.setDialogTitle("Chọn nơi lưu file");
//            fileChooser.setSelectedFile(new File(uniqueFileName));  // Đặt tên file duy nhất
//
//            int userSelection = fileChooser.showSaveDialog(null);
//            if (userSelection == JFileChooser.APPROVE_OPTION) {
//                File file = fileChooser.getSelectedFile();
//                try (FileOutputStream fileOut = new FileOutputStream(file)) {
//                    workbook.write(fileOut);
//                }
//                workbook.close();
//                JOptionPane.showMessageDialog(null, "Xuất file Excel thành công!");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Lỗi khi xuất file!");
//        }
//    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btn_FormThongKe_XuatExcel;
    private javax.swing.JComboBox<String> cbxThangBatDau;
    private javax.swing.JComboBox<String> cbxThangKetThuc;
    private javax.swing.JComboBox<String> cbxTimKiemTheoNam;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblDoanhThuHomNay;
    private javax.swing.JLabel lblDoanhThuHomQua;
    private javax.swing.JLabel lblTongDonBanHomNay;
    private javax.swing.JLabel lblTongSanPhamBanHomNay;
    private javax.swing.JLabel lbl_FormThongKe_DTHomNay;
    private javax.swing.JLabel lbl_FormThongKe_DTHomNay1;
    private javax.swing.JLabel lbl_FormThongKe_DTHomNay2;
    private javax.swing.JLabel lbl_FormThongKe_DTHomNay3;
    private javax.swing.JLabel lbl_cbx_TongDon;
    private javax.swing.JLabel lbl_cbx_TongTien;
    private javax.swing.JTable tblFormThongKe_DoanhThuSanPham;
    private javax.swing.JTable tblSanPhamBanChay_FormViewThongKe;
    // End of variables declaration//GEN-END:variables
}
