/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.BanHangView;

import Model.BanHangModel.Ban;
import Model.BanHangModel.HoaDonBanHang;
import Model.BanHangModel.SanPham_BanHang;
import Model.HoaDonModel.HoaDon;
import Model.HoaDonModel.HoaDonChiTiet;
import Model.KhachHangModel.KhachHang;
import Model.KhuyenMaiModel.KhuyenMai;
import Model.KhuyenMaiModel.KhuyenMaiChiTiet;
import Model.NhanVien;
import Repository.BanHangRepository.BanRepo;
import Repository.BanHangRepository.HoaDonRepo;
import Repository.BanHangRepository.RepoSize;
import Repository.BanHangRepository.repobanHang;
import Repository.KhuyenMaiRepo;
import Repository.RepoNhanVien;
import View.GiaoDienChinh;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author admin
 */
public class ViewBanHang extends javax.swing.JPanel {

    private HoaDonRepo hdRepo = new HoaDonRepo();

    private int tableCount = 0;

    private JButton btnAddNewBan = new JButton();

    private BanRepo banRepo = new BanRepo();

    private Integer maBanChon;

    public Integer maSanPhamChon;

    public Integer getMaSanPhamChon() {
        return maSanPhamChon;
    }

    public void setMaSanPhamChon(Integer maSanPhamChon) {
        this.maSanPhamChon = maSanPhamChon;
    }

    private RepoNhanVien nvRepo = new RepoNhanVien();

    private KhuyenMaiRepo kmRepo = new KhuyenMaiRepo();

//    private RepoSize repos = new RepoSize();
    private repobanHang repos = new repobanHang();

    private DefaultTableModel mol = new DefaultTableModel();

    private Integer indexTableHoaDon = -1;

    private KhachHang kh;

    public KhachHang getKh() {
        return kh;
    }

    public void setKh(KhachHang kh) {
        this.kh = kh;
    }

    private String maHoaDonChon;

    public ViewBanHang() {
        initComponents();

        if (maHoaDonChon == null) {
            tabPaneHoaDon.setEnabledAt(1, false);
        } else {
            tabPaneHoaDon.setEnabledAt(1, true);
        }

        //Định nghĩa tiêu đề các cột bảng sản phẩm
        initTable();

        showDataTableProduct(repos.getAll());

        //Hiển thị các bàn pnlBanView
        ttBan();

        //btnHuyDon ẩn
        btnHuyDon.setVisible(false);

        //Xóa các item mặc định của cboTenNhanVien
        cboTenNhanVien.removeAllItems();

        //Đổ dữ liệu từ repo vào cboTenNhanVien
        for (NhanVien nv : nvRepo.getAllTenNhanVien(true)) {
            cboTenNhanVien.addItem(nv.getHoten());
        }

        //Xóa các item mặc định của cboKhuyenMai
        cboMaGiamGia.removeAllItems();

        danhSachMaGiamGia();

        readTongTien();

        //Tạo đơn không cho phép nhấn vào khi chưa chọn bàn
        btn_FormThongTinHD_TaoDon.setEnabled(false);

        //Lấy dữ liệu các hóa đơn trong ngày hôm nay chưa thanh toán
        fillTable(hdRepo.getAllHdToday(false));

        //Đọc dữ liệu tiền khách trả
        readTienKhachTra();

        // Thêm KeyListener cho tiền khách trả
        txt_FormThongTinHD_TienKhachTra.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (txt_FormThongTinHD_TienKhachTra.getText().length() >= 8) {
                    e.consume(); // Ngăn không cho nhập thêm
                }
            }
        });

        //Nút thanh toán không cho nhấn
        btn_FormThongTinHD_ThanhToan.setEnabled(false);

        //Ẩn discount
        btnDiscount.setEnabled(false);

        boolean isLoad = true;

    }

    //Thao tác với combo box Mã giảm giá
    private void danhSachMaGiamGia() {
        cboMaGiamGia.removeAllItems();

        double tongTien = 0;
        try {
            tongTien = Double.parseDouble(txt_FormThongTinHD_TongTien.getText().toString());
        } catch (Exception e) {
            tongTien = 0;
        }

        // Lấy danh sách từ repository
        List<KhuyenMaiChiTiet> khuyenMaiList = kmRepo.getAllKhuyenMaiChiTiet(true);

        // Danh sách chứa đối tượng KhuyenMaiChiTiet
        List<KhuyenMaiChiTiet> danhSachKhuyenMai = new ArrayList<>(khuyenMaiList);

        // Chia danh sách thành hai phần
        List<KhuyenMaiChiTiet> danhSachDuDieuKien = new ArrayList<>();
        List<KhuyenMaiChiTiet> danhSachKhongDuDieuKien = new ArrayList<>();

        // Phân loại theo điều kiện
        for (KhuyenMaiChiTiet km : danhSachKhuyenMai) {
            if (km.getDieuKien() <= tongTien) {
                danhSachDuDieuKien.add(km);
            } else {
                danhSachKhongDuDieuKien.add(km);
            }
        }

        // Sắp xếp danh sách đủ điều kiện giảm dần
        danhSachDuDieuKien.sort((km1, km2) -> Double.compare(km2.getMucGiamToiDa(), km1.getMucGiamToiDa()));
        // Sắp xếp danh sách không đủ điều kiện tăng dần
        danhSachKhongDuDieuKien.sort(Comparator.comparingDouble(KhuyenMaiChiTiet::getDieuKien));

        // Danh sách chứa chuỗi hiển thị cho ComboBox
        List<String> danhSachTextDuDieuKien = new ArrayList<>();
        List<String> danhSachTextKhongDuDieuKien = new ArrayList<>();

        // Đổ dữ liệu vào ComboBox từ danh sách đủ điều kiện
        for (KhuyenMaiChiTiet km : danhSachDuDieuKien) {
            String text = km.getMaGiamGia() + ": " + km.getMucGiam()
                    + (km.getLoaiGiamGia().equals("Giảm theo VNĐ") ? "VNĐ" : "%")
                    + " - ĐK: >=" + km.getDieuKien() + " /Tối đa: " +km.getMucGiamToiDa();
            danhSachTextDuDieuKien.add(text);
            cboMaGiamGia.addItem(text);
        }

        // Đổ dữ liệu vào ComboBox từ danh sách không đủ điều kiện
        for (KhuyenMaiChiTiet km : danhSachKhongDuDieuKien) {
            String text = km.getMaGiamGia() + ": " + km.getMucGiam()
                    + (km.getLoaiGiamGia().equals("Giảm theo VNĐ") ? "VNĐ" : "%")
                    + " - ĐK: >=" + km.getDieuKien() + " /Tối đa: " +km.getMucGiamToiDa();
            danhSachTextKhongDuDieuKien.add(text);
            cboMaGiamGia.addItem(text);
        }

        // Thiết lập renderer để thay đổi màu sắc
        cboMaGiamGia.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value != null) {
                    String text = value.toString();
                    label.setText(text);
                    // Xác định màu sắc dựa vào danh sách
                    if (index >= 0 && index < danhSachDuDieuKien.size()) {
                        label.setForeground(Color.RED); // Mục đủ điều kiện
                    } else if (index >= danhSachDuDieuKien.size()) {
                        label.setForeground(Color.GRAY);  // Mục không đủ điều kiện
                    }
                }
                // Hiển thị mục được chọn
                if (isSelected) {
                    label.setBackground(Color.WHITE); // Màu nền khi chọn
                    label.setOpaque(true);
                } else {
                    label.setOpaque(false);
                }
                return label;
            }
        });

    }

    //Truyền dữ liệu bảng Hóa đơn
    private void fillTable(ArrayList<HoaDonBanHang> listHd) {
        mol = (DefaultTableModel) tblHoaDon.getModel();
        mol.setRowCount(0);
        for (HoaDonBanHang hdbh : listHd) {
            // Lấy dữ liệu của dòng hiện tại
            Object[] rowData = hdbh.toDataRow();

            // Tạo mảng mới để thêm giá trị cho cột cuối cùng
            Object[] extendedRowData = Arrays.copyOf(rowData, rowData.length + 1);

            // Gán giá trị cho cột cuối cùng
            extendedRowData[extendedRowData.length - 1] = hdbh.getDiaChi() == null ? "Tại quầy":"ĐC: " +hdbh.getDiaChi() +"/SĐT: " + hdbh.getSdt(); // Hoặc giá trị động
            
            mol.addRow(extendedRowData);
        }

        // Tạo renderer để căn giữa dữ liệu
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho tất cả các cột
        for (int i = 0; i < mol.getColumnCount(); i++) {
            tblHoaDon.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    //Truyền dữ liệu bảng Hóa đơn chi tiết
    public void fillTableHdct(ArrayList<HoaDonChiTiet> listHd) {
        mol = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        mol.setRowCount(0);
        for (HoaDonChiTiet hdct : listHd) {
            mol.addRow(hdct.toDataRow());
        }

        // Tạo renderer để căn giữa dữ liệu
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho tất cả các cột
        for (int i = 0; i < mol.getColumnCount(); i++) {
            tblHoaDonChiTiet.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Tùy chỉnh cột cuối cùng để hiển thị nút
        tblHoaDonChiTiet.getColumnModel().getColumn(mol.getColumnCount() - 1).setCellRenderer(new ButtonRenderer());
        tblHoaDonChiTiet.getColumnModel().getColumn(mol.getColumnCount() - 1).setCellEditor(new ButtonEditor(new JCheckBox()));
    }

    // Class để render các nút - Hóa đơn chi tiết
    class ButtonRenderer extends JPanel implements TableCellRenderer {

        private JButton btnDetail = new JButton("Detail");
        private JButton btnDelete = new JButton("Delete");

        public ButtonRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER));
            add(btnDetail);
            add(btnDelete);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // Class để xử lý sự kiện cho các nút - Hóa đơn chi tiết
    class ButtonEditor extends AbstractCellEditor implements TableCellEditor {

        private JPanel panel = new JPanel();
        private JButton btnDetail = new JButton("Detail");
        private JButton btnDelete = new JButton("Delete");
        private int currentRow;

        public ButtonEditor(JCheckBox checkBox) {
            panel.setLayout(new FlowLayout(FlowLayout.CENTER));
            panel.add(btnDetail);
            panel.add(btnDelete);

            // Xử lý sự kiện khi nhấn nút Detail
            btnDetail.addActionListener(e -> {
                currentRow = tblHoaDonChiTiet.convertRowIndexToModel(tblHoaDonChiTiet.getEditingRow());
                if (currentRow != -1) {
                    int index = tblHoaDon.getSelectedRow();
//                    DefaultTableModel model = (DefaultTableModel) tblHoaDonChiTiet.getModel();

                    // Lấy dữ liệu từ dòng hiện tại
                    int id = Integer.parseInt(tblHoaDonChiTiet.getValueAt(currentRow, 1).toString()); // Cột chứa ID
                    String maHoaDonChon = tblHoaDon.getValueAt(index, 0).toString();      // Cột chứa mã hóa đơn

                    // Tạo giao diện chi tiết sản phẩm
                    Frame GiaoDienChinh = new GiaoDienChinh();
                    DetailProduct detailProduct = new DetailProduct(GiaoDienChinh, true);

                    //Truyền dữ liệu
                    detailProduct.setMaSanPham(hdRepo.getAllHdChiTietBySTT(id).getMaSanPham());
                    detailProduct.setMaHDTao(maHoaDonChon);
                    detailProduct.setIdChon(id);

                    //Hiển thị JDialog Detail
                    detailProduct.setVisible(true);

                    kiemTraDieuKienMaGiamGia();

                    // Cập nhật lại bảng
                    fillTableHdct(hdRepo.getAllHdChiTietByMaHD(maHoaDonChon));
                    fillTable(hdRepo.getAllHdToday(false));
                    showDataHoaDon(indexTableHoaDon);
                } else {
                    JOptionPane.showMessageDialog(null, "Không thể xác định dòng dữ liệu!");
                }
            });

            // Xử lý sự kiện khi nhấn nút Delete
            btnDelete.addActionListener(e -> {
                currentRow = tblHoaDonChiTiet.convertRowIndexToModel(tblHoaDonChiTiet.getEditingRow());
                if (currentRow != -1) {
//                    DefaultTableModel model = (DefaultTableModel) tblHoaDonChiTiet.getModel();

                    // Lấy dữ liệu từ dòng hiện tại
                    int id = Integer.parseInt(tblHoaDonChiTiet.getValueAt(currentRow, 1).toString()); // Cột chứa ID

                    Frame GiaoDienChinh = new GiaoDienChinh();

                    int chon = JOptionPane.showConfirmDialog(GiaoDienChinh, "Bạn có chắc muốn xóa?");

                    if (chon == 0) {
                        hdRepo.deleteHoaDonChiTiet(id);
                        JOptionPane.showMessageDialog(null, "Xóa thành công");

                    }

                    kiemTraDieuKienMaGiamGia();

                    // Cập nhật lại bảng
                    fillTableHdct(hdRepo.getAllHdChiTietByMaHD(maHoaDonChon));
                    fillTable(hdRepo.getAllHdToday(false));
                    showDataHoaDon(indexTableHoaDon);
                } else {
                    JOptionPane.showMessageDialog(null, "Không thể xác định dòng dữ liệu!");
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            currentRow = row;
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }
    }

    private void kiemTraDieuKienMaGiamGia() {
        //Kiểm tra lại tổng tiền và điều kiện của mã giảm giá (Nếu có)
        HoaDonBanHang hdbh = hdRepo.getHdInAllHdToday(maHoaDonChon);

        if (!(hdbh.getMaGG() == null)) {
            Double tongTien = hdbh.getTongTien();

            String maGG = null;

            maGG = hdbh.getMaGG();

            Float dieuKien = kmRepo.getKhuyenMaiChiTietByMa(maGG).getDieuKien();

            if (tongTien < dieuKien) {
                hdRepo.updateMaGiamGia1(maHoaDonChon, tongTien);
            }
        }
    }

    //Hiển thị thông tin bảng sản phẩm
    private void showDataTableProduct(List<SanPham_BanHang> lst) {
        mol = (DefaultTableModel) tbl_HoaDon_SanPham1.getModel();
        mol.setRowCount(0);
        for (SanPham_BanHang sp : lst) {
            if (sp.isTrangThai()) {
                mol.addRow(new Object[]{
                    sp.getMa(), sp.getTen(), sp.getGia(), "đang bán"
                });
            }
        }

        // Tạo renderer để căn giữa dữ liệu
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho tất cả các cột
        for (int i = 0; i < mol.getColumnCount(); i++) {
            tbl_HoaDon_SanPham1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    //Thông tin các tiêu đề cột của bảng Sản phẩm
    private void initTable() {
        // Định nghĩa tiêu đề cột
        String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Giá", "Trạng thái"};
        mol = new DefaultTableModel(columnNames, 0); // Khởi tạo bảng với tiêu đề cột
        tbl_HoaDon_SanPham1.setModel(mol); // Gắn model vào bảng
    }

    //Hiển thị các bàn
    private void ttBan() {

        //Xóa dữ liệu cũ
        pnl_Ban_View.removeAll();

        ArrayList<Ban> listBan = banRepo.getAllBan();

        // Thêm các bàn
        for (Ban ban : listBan) {
            JPanel pnlBan = new JPanel();
            //Tạo đối tượng thuộc tính cho pnlBan:
            pnlBan.putClientProperty("isSelected", false);
            pnlBan.setPreferredSize(new Dimension(80, 80)); // Kích thước bàn
            pnlBan.setBorder(BorderFactory.createTitledBorder(ban.getTenBan()));

            // Tạo JLabel và căn giữa
            JLabel lblBan = new JLabel();
            lblBan.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa ngang
            lblBan.setVerticalAlignment(SwingConstants.CENTER);   // Căn giữa dọc
            pnlBan.add(lblBan, BorderLayout.CENTER); // Thêm vào giữa JPanel

            // Tạo JLabel và căn giữa
            JLabel lblSucChua = new JLabel();
            lblSucChua.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa ngang
            lblSucChua.setVerticalAlignment(SwingConstants.CENTER);   // Căn giữa dọc
            pnlBan.add(lblSucChua, BorderLayout.CENTER); // Thêm vào giữa JPanel
            
            lblSucChua.setText("("+String.valueOf(banRepo.getBanByMa(ban.getMaBan()).getSucChua())+")");
            
            if (ban.getTrangThai() == 0) {
                //Bàn trống
                lblBan.setText("Trống");
                pnlBan.setBackground(Color.WHITE);
            } else if (ban.getTrangThai() == 1) {
                //Có người - chưa thanh toán
                lblBan.setText("Có người");
                pnlBan.setBackground(new Color(204, 255, 153));
            }

            // Tạo menu ngữ cảnh cho mỗi bàn
            JPopupMenu popupMenu = createContextMenu(pnlBan, ban);

            // Sự kiện chuột
            pnlBan.addMouseListener(new MouseAdapter() {

                public Boolean check = false;

                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    } else {
                        check = true;
                        pnlBan.setBackground(Color.RED); // Đổi màu nền
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    // Đặt tất cả các pnlBan khác về trạng thái không được chọn
                    Component[] components = pnl_Ban_View.getComponents();

                    for (Component component : components) {
                        if (component instanceof JPanel) {
                            JPanel otherPanel = (JPanel) component;

                            otherPanel.putClientProperty("isSelected", false);
                            // Đặt lại màu nền theo trạng thái ban đầu
                            Ban otherBan = listBan.get(pnl_Ban_View.getComponentZOrder(otherPanel));

                            if (otherBan.getTrangThai() == 0) {
                                lblBan.setText("Trống");
                                otherPanel.setBackground(Color.WHITE);
                            } else if (otherBan.getTrangThai() == 1) {
                                //Có người - chưa thanh toán
                                lblBan.setText("Có người");
                                otherPanel.setBackground(new Color(204, 255, 153));
                            }
                        }
                    }

                    pnlBan.putClientProperty("isSelected", true);

                    if (ban.getTrangThai() == 0) {
                        lblBan.setText("Trống");
                        pnlBan.setBackground(Color.WHITE);
                        lbl_FormThongTinHD_MaHoaDon_Input.setText(null);
                        btnHuyDon.setVisible(false);
                        btn_FormThongTinHD_ThanhToan.setEnabled(false);
                        //Ẩn discount
                        btnDiscount.setEnabled(false);
                    } else if (ban.getTrangThai() == 1) {
                        //Có người - chưa thanh toán
                        lblBan.setText("Có người");
                        pnlBan.setBackground(new Color(204, 255, 153));
//                        lbl_FormThongTinHD_MaHoaDon_Input.setText();
                        btnHuyDon.setVisible(true);
                        btn_FormThongTinHD_TaoDon.setEnabled(false);
                        btn_FormThongTinHD_ThanhToan.setEnabled(true);
                        //Hiện discount
                        btnDiscount.setEnabled(true);
                        maBanChon = tblHoaDon.getSelectedRow();
                    }

                    //Lấy mã bàn
                    maBanChon = ban.getMaBan();

                    //Lấy mã hóa đơn theo tên bàn
                    String maHD = hdRepo.getHdTodayByBan(ban.getTenBan()).getMaHD();

                    int rowCount = tblHoaDon.getRowCount(); //Số dòng của bảng hóa đơn
                    int columnIndex = 0; //Cột vị trí mã hóa đơn
                    int rowIndex = -1; //Khởi tạo dòng vị trí mã hóa đơn

                    //Duyệt qua danh sách các dòng, nếu có giá trị giống mã hóa đơn thì trả về rowIndex = i
                    for (int i = 0; i < rowCount; i++) {
                        String value = tblHoaDon.getValueAt(i, columnIndex).toString();
                        if (value.equals(maHD)) {
                            rowIndex = i;
                        }
                    }

                    //Truyền dữ liệu lên form
                    if (rowIndex > -1) {
                        showDataHoaDon(rowIndex);

                    } else {
                        tblHoaDon.clearSelection();
                        txtTongTienSauKM.setText("0");
                        txt_FormThongTinHD_TongTien.setText("0");
                        txtTienKhuyenMai.setText("0");
                    }

                    //Set background
                    pnlBan.setBackground(Color.RED);

                    //Focus TabPanedHoaDon
                    tabPaneHoaDon.setSelectedIndex(0);

                    //Focus bảng hóa đơn chi tiết
                    tblHoaDonChiTiet.requestFocus();

                    if (ban.getTrangThai() == 0) {
                        //Nút tạo đơn hiển thị
                        btn_FormThongTinHD_TaoDon.setEnabled(true);
                    } else if (ban.getTrangThai() == 1) {
                        //Nút tạo đơn không hiển thị
                        btn_FormThongTinHD_TaoDon.setEnabled(false);
                    }

                    //Hiển thị thông tin tên bàn
                    lbl_FormThongTinHD_Ban_Input.setText(ban.getTenBan());

                    //Hiển thị thông tin ngày giờ hiện tại
                    lbl_FormThongTinHD_Ngay_Input.setText((new Date().toString().trim()));

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    pnlBan.setBackground(Color.RED);
                    tblHoaDonChiTiet.requestFocus();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    Boolean isSelected = (Boolean) pnlBan.getClientProperty("isSelected");
                    if (isSelected != null && isSelected) {
                        return; // Giữ nguyên màu đỏ nếu bàn đang được chọn
                    }

                    if (ban.getTrangThai() == 0) {
                        //Bàn trống
                        lblBan.setText("Trống");
                        pnlBan.setBackground(Color.WHITE);
                    } else if (ban.getTrangThai() == 1) {
                        //Có người - chưa thanh toán
                        lblBan.setText("Có người");
                        pnlBan.setBackground(new Color(204, 255, 153));
                    }
                }

            });

            pnl_Ban_View.add(pnlBan);
        }

        // Cập nhật giao diện
        pnl_Ban_View.revalidate();
        pnl_Ban_View.repaint();
    }

    //Thêm bàn pnlBanView
    private void newPanelBan1() {
        Frame GiaoDienChinh = new GiaoDienChinh();
        NewFormBan newFormBan1 = new NewFormBan(GiaoDienChinh, true);
        newFormBan1.setVisible(true);
        btnAddNewBan.setVisible(false);

        newFormBan1.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // Xử lý khi JDialog đóng
                pnl_Ban_View.removeAll();
                ttBan();
            }
        });

    }

    //Hiển thị nút thêm bàn pnlBanView
    private void showButtonAddTable1(int x, int y) {
        // Xóa nút cũ nếu đã tồn tại
        if (btnAddNewBan != null) {
            pnl_Ban_View.remove(btnAddNewBan);
        }

        // Tạo JButton
        btnAddNewBan = new JButton("Thêm bàn");
        btnAddNewBan.setBounds(x, y, 100, 40); // Đặt vị trí và kích thước nút
        btnAddNewBan.setForeground(Color.GRAY);

        // Thêm sự kiện cho nút
        btnAddNewBan.addActionListener(e -> newPanelBan1());

        // Thêm nút vào JPanel
        pnl_Ban_View.add(btnAddNewBan);

        // Làm mới giao diện
        pnl_Ban_View.revalidate();
        pnl_Ban_View.repaint();
    }

    //Hiển thị nút thêm bàn pnlGiaoHangView
    private void showButtonAddTable2(int x, int y) { //K sử dụng
//        // Xóa nút cũ nếu đã tồn tại
        if (btnAddNewBan != null) {
            pnl_GiaoHang_View.remove(btnAddNewBan);
        }

        // Tạo JButton
        btnAddNewBan = new JButton("Thêm đơn");
        btnAddNewBan.setBounds(x, y, 100, 40); // Đặt vị trí và kích thước nút
        btnAddNewBan.setForeground(Color.GRAY);

        // Thêm sự kiện cho nút
        btnAddNewBan.addActionListener(e -> newPanelBan2());

        // Thêm nút vào JPanel
        pnl_GiaoHang_View.add(btnAddNewBan);

        // Làm mới giao diện
        pnl_GiaoHang_View.revalidate();
        pnl_GiaoHang_View.repaint();
    }

    // Phương thức tạo menu ngữ cảnh
    private JPopupMenu createContextMenu(JPanel pnlBan, Ban ban) {
        JPopupMenu popupMenu = new JPopupMenu();

        // Tùy chọn "Thông tin bàn"
        JMenuItem menuItemInfo = new JMenuItem("Thông tin bàn");
        menuItemInfo.addActionListener(e -> {
            Frame GiaoDienChinh = new GiaoDienChinh();

            DetailBan detailBan = new DetailBan(GiaoDienChinh, true);

            detailBan.setMaBan(maBanChon);

            detailBan.setVisible(true);

            ttBan();
        });

        // Tùy chọn "Đổi trạng thái thành bàn trống"
        JMenuItem menuItemEmptyTable = new JMenuItem("Xóa bàn");
        menuItemEmptyTable.setEnabled(false);

        if (ban.getTrangThai() == 0) {
            menuItemEmptyTable.setEnabled(false);
        } else {
            menuItemEmptyTable.addActionListener(e -> {

                int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn đổi trạng thái bàn?");

//                if (chon == 0) {
//                    ban.setTrangThai(0);
//                    // Cập nhật giao diện của pnlBan
//                    pnlBan.putClientProperty("isSelected", false);
//                    pnlBan.setBackground(Color.WHITE); // Màu nền của bàn trống
//                    pnlBan.removeAll(); // Xóa tất cả các component cũ
//
//                    // Tạo JLabel cho bàn trống
//                    JLabel lblBan = new JLabel("Trống");
//                    lblBan.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa ngang
//                    lblBan.setVerticalAlignment(SwingConstants.CENTER);   // Căn giữa dọc
//                    pnlBan.add(lblBan, BorderLayout.CENTER);
//
//                    // Làm mới giao diện
//                    pnlBan.revalidate();
//                    pnlBan.repaint();
//                } else {
//                    JOptionPane.showMessageDialog(this, "Bạn không đổi trạng thái");
//                }
            });
        }

        // Thêm các mục vào menu ngữ cảnh
        popupMenu.add(menuItemInfo);
        popupMenu.add(menuItemEmptyTable);

        return popupMenu;
    }

    //Thêm bàn pnlGiaoHangView
    private void newPanelBan2() {
        ArrayList<Ban> listBan = new ArrayList<>();

        tableCount++; // Tăng số lượng bàn

        JButton btnBan = new JButton("Đơn " + tableCount);
//            btnBan.setPreferredSize(new Dimension(100, 50));
        btnBan.setPreferredSize(new Dimension(80, 80)); // Kích thước bàn
        btnBan.setBackground(Color.WHITE);
//            btnBan.setBackground(Color.GREEN); // Bàn trống

        // Sự kiện khi nhấn vào bàn
        btnBan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame GiaoDienChinh = new GiaoDienChinh();

                AddClient addClient = new AddClient(GiaoDienChinh, true);
                addClient.setVisible(true);

                kh = addClient.getKh();
                System.out.println("Khách hàng: " + kh);

                if (kh != null) {

                    hdRepo.insertNewHoaDonKhachHang(readFormNewHoaDon(), kh);
                    fillTable(hdRepo.getAllHdToday(false));

                }
            }
        });

        pnl_GiaoHang_View.add(btnBan);

        btnAddNewBan.setVisible(false);
    }

    public void createNewHoaDon(KhachHang kh) {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rdoGroupGioiTinhKH = new javax.swing.ButtonGroup();
        pnlBanHang = new javax.swing.JPanel();
        pnl_BanHang_HoaDon = new javax.swing.JPanel();
        pnl_HoaDon_ThongTin = new javax.swing.JPanel();
        pnl_HoaDon_FormThongTinHD = new javax.swing.JPanel();
        pnl_FormThongTinHD_Details = new javax.swing.JPanel();
        txt_FormThongTinHD_TongTien = new javax.swing.JTextField();
        lbl_FormThongTinHD_TienThoi = new javax.swing.JLabel();
        lbl_FormThongTinHD_MaHoaDon = new javax.swing.JLabel();
        lbl_FormThongTinHD_NhanVien = new javax.swing.JLabel();
        txt_FormThongTinHD_TienKhachTra = new javax.swing.JTextField();
        lbl_FormThongTinHD_TienKhachTra = new javax.swing.JLabel();
        lbl_FormThongTinHD_MaHoaDon_Input = new javax.swing.JLabel();
        lbl_FormThongTinHD_Ngay_Input = new javax.swing.JLabel();
        lbl_FormThongTinHD_Ban_Input = new javax.swing.JLabel();
        lbl_FormThongTinHD_TongTien = new javax.swing.JLabel();
        txt_FormThongTin_TienThoi = new javax.swing.JTextField();
        lbl_FormThongTinHD_Ban = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cboMaGiamGia = new javax.swing.JComboBox<>();
        cboTenNhanVien = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtTongTienSauKM = new javax.swing.JTextField();
        btnDiscount = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtTienKhuyenMai = new javax.swing.JTextField();
        pnl_FormThongTinHD_Method = new javax.swing.JPanel();
        btn_FormThongTinHD_TaoDon = new javax.swing.JButton();
        btn_FormThongTinHD_ThanhToan = new javax.swing.JButton();
        btnHuyDon = new javax.swing.JButton();
        tabPaneHoaDon = new javax.swing.JTabbedPane();
        sclHoaDon = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        sclHoaDonChiTiet = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new javax.swing.JTable();
        pnl_HoaDon_SanPham1 = new javax.swing.JPanel();
        pnl_HoaDon_SanPham_SearchAndAdd1 = new javax.swing.JPanel();
        pnl_SanPham_SearchAndAdd1 = new javax.swing.JPanel();
        lbl_SanPham_SearchAndAdd_Search1 = new javax.swing.JLabel();
        txtSearchProduct = new javax.swing.JTextField();
        btnLamMoi = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_HoaDon_SanPham1 = new javax.swing.JTable();
        pnl_BanHang_Ban = new javax.swing.JPanel();
        pnl_Ban_Ban = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pnl_Ban_View = new javax.swing.JPanel();
        pnl_Ban_GiaoHang = new javax.swing.JPanel();
        pnl_GiaoHang_LBL = new javax.swing.JPanel();
        lbl_GiaoHang_LBL = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        pnl_GiaoHang_View = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(1700, 850));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

        pnlBanHang.setMaximumSize(new java.awt.Dimension(1684, 1000));
        pnlBanHang.setPreferredSize(new java.awt.Dimension(1700, 850));
        pnlBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlBanHangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlBanHangMouseEntered(evt);
            }
        });
        pnlBanHang.setLayout(new javax.swing.BoxLayout(pnlBanHang, javax.swing.BoxLayout.LINE_AXIS));

        pnl_BanHang_HoaDon.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        pnl_BanHang_HoaDon.setLayout(new javax.swing.BoxLayout(pnl_BanHang_HoaDon, javax.swing.BoxLayout.PAGE_AXIS));

        pnl_HoaDon_ThongTin.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnl_HoaDon_ThongTin.setLayout(new javax.swing.BoxLayout(pnl_HoaDon_ThongTin, javax.swing.BoxLayout.PAGE_AXIS));

        txt_FormThongTinHD_TongTien.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_FormThongTinHD_TongTien.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_FormThongTinHD_TongTien.setText("0");
        txt_FormThongTinHD_TongTien.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        txt_FormThongTinHD_TongTien.setEnabled(false);

        lbl_FormThongTinHD_TienThoi.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lbl_FormThongTinHD_TienThoi.setText("Tiền thối");

        lbl_FormThongTinHD_MaHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lbl_FormThongTinHD_MaHoaDon.setText("Mã hóa đơn");

        lbl_FormThongTinHD_NhanVien.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lbl_FormThongTinHD_NhanVien.setText("Nhân viên");

        txt_FormThongTinHD_TienKhachTra.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_FormThongTinHD_TienKhachTra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_FormThongTinHD_TienKhachTra.setText("0");
        txt_FormThongTinHD_TienKhachTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_FormThongTinHD_TienKhachTraActionPerformed(evt);
            }
        });

        lbl_FormThongTinHD_TienKhachTra.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lbl_FormThongTinHD_TienKhachTra.setText("Tiền khách trả");

        lbl_FormThongTinHD_MaHoaDon_Input.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lbl_FormThongTinHD_MaHoaDon_Input.setText("Mã hóa đơn mới");

        lbl_FormThongTinHD_Ngay_Input.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lbl_FormThongTinHD_Ngay_Input.setText("Ngày");

        lbl_FormThongTinHD_Ban_Input.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lbl_FormThongTinHD_Ban_Input.setText("Chọn bàn");

        lbl_FormThongTinHD_TongTien.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lbl_FormThongTinHD_TongTien.setText("Tổng tiền");

        txt_FormThongTin_TienThoi.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_FormThongTin_TienThoi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_FormThongTin_TienThoi.setText("0");
        txt_FormThongTin_TienThoi.setDisabledTextColor(new java.awt.Color(0, 204, 0));
        txt_FormThongTin_TienThoi.setEnabled(false);

        lbl_FormThongTinHD_Ban.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lbl_FormThongTinHD_Ban.setText("Bàn");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel1.setText("Mã giảm giá");

        cboMaGiamGia.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cboMaGiamGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMaGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMaGiamGiaActionPerformed(evt);
            }
        });

        cboTenNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cboTenNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel2.setText("Tổng tiền sau KM");

        txtTongTienSauKM.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtTongTienSauKM.setForeground(new java.awt.Color(255, 51, 51));
        txtTongTienSauKM.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTongTienSauKM.setText("0");
        txtTongTienSauKM.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        txtTongTienSauKM.setEnabled(false);

        btnDiscount.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btnDiscount.setText("Discount");
        btnDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiscountActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setText("Khuyến mãi");

        txtTienKhuyenMai.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtTienKhuyenMai.setForeground(new java.awt.Color(255, 255, 51));
        txtTienKhuyenMai.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTienKhuyenMai.setText("0");
        txtTienKhuyenMai.setCaretColor(new java.awt.Color(255, 255, 0));
        txtTienKhuyenMai.setDisabledTextColor(new java.awt.Color(204, 0, 204));
        txtTienKhuyenMai.setEnabled(false);

        javax.swing.GroupLayout pnl_FormThongTinHD_DetailsLayout = new javax.swing.GroupLayout(pnl_FormThongTinHD_Details);
        pnl_FormThongTinHD_Details.setLayout(pnl_FormThongTinHD_DetailsLayout);
        pnl_FormThongTinHD_DetailsLayout.setHorizontalGroup(
            pnl_FormThongTinHD_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_FormThongTinHD_DetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_FormThongTinHD_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_FormThongTinHD_DetailsLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(cboMaGiamGia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_FormThongTinHD_DetailsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnl_FormThongTinHD_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_FormThongTinHD_DetailsLayout.createSequentialGroup()
                                .addComponent(lbl_FormThongTinHD_TongTien)
                                .addGap(18, 18, 18)
                                .addComponent(txt_FormThongTinHD_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_FormThongTinHD_DetailsLayout.createSequentialGroup()
                                .addComponent(lbl_FormThongTinHD_NhanVien)
                                .addGap(18, 18, 18)
                                .addComponent(cboTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl_FormThongTinHD_MaHoaDon, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(18, 18, 18)
                .addGroup(pnl_FormThongTinHD_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDiscount)
                    .addComponent(lbl_FormThongTinHD_Ngay_Input, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_FormThongTinHD_MaHoaDon_Input))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnl_FormThongTinHD_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_FormThongTinHD_DetailsLayout.createSequentialGroup()
                        .addGroup(pnl_FormThongTinHD_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_FormThongTinHD_TienThoi)
                            .addComponent(lbl_FormThongTinHD_TienKhachTra)
                            .addComponent(jLabel2))
                        .addGap(22, 22, 22)
                        .addGroup(pnl_FormThongTinHD_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTongTienSauKM, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_FormThongTinHD_TienKhachTra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_FormThongTin_TienThoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0))
                    .addGroup(pnl_FormThongTinHD_DetailsLayout.createSequentialGroup()
                        .addComponent(lbl_FormThongTinHD_Ban)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_FormThongTinHD_Ban_Input)
                        .addContainerGap())))
        );

        pnl_FormThongTinHD_DetailsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, lbl_FormThongTinHD_NhanVien, lbl_FormThongTinHD_TongTien});

        pnl_FormThongTinHD_DetailsLayout.setVerticalGroup(
            pnl_FormThongTinHD_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_FormThongTinHD_DetailsLayout.createSequentialGroup()
                .addGroup(pnl_FormThongTinHD_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_FormThongTinHD_DetailsLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnl_FormThongTinHD_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_FormThongTin_TienThoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_FormThongTinHD_TienThoi)))
                    .addGroup(pnl_FormThongTinHD_DetailsLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(pnl_FormThongTinHD_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_FormThongTinHD_MaHoaDon)
                            .addComponent(lbl_FormThongTinHD_MaHoaDon_Input)
                            .addComponent(lbl_FormThongTinHD_Ban)
                            .addComponent(lbl_FormThongTinHD_Ban_Input))
                        .addGap(26, 26, 26)
                        .addGroup(pnl_FormThongTinHD_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_FormThongTinHD_TongTien)
                            .addComponent(txt_FormThongTinHD_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(txtTongTienSauKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtTienKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addGroup(pnl_FormThongTinHD_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cboMaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDiscount)
                            .addComponent(txt_FormThongTinHD_TienKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_FormThongTinHD_TienKhachTra))
                        .addGap(26, 26, 26)
                        .addGroup(pnl_FormThongTinHD_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_FormThongTinHD_NhanVien)
                            .addComponent(cboTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_FormThongTinHD_Ngay_Input))))
                .addContainerGap())
        );

        pnl_HoaDon_FormThongTinHD.add(pnl_FormThongTinHD_Details);

        pnl_FormThongTinHD_Method.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_FormThongTinHD_TaoDon.setText("Tạo đơn");
        btn_FormThongTinHD_TaoDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_FormThongTinHD_TaoDonActionPerformed(evt);
            }
        });
        pnl_FormThongTinHD_Method.add(btn_FormThongTinHD_TaoDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 90, -1));

        btn_FormThongTinHD_ThanhToan.setText("Thanh toán");
        btn_FormThongTinHD_ThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_FormThongTinHD_ThanhToanActionPerformed(evt);
            }
        });
        pnl_FormThongTinHD_Method.add(btn_FormThongTinHD_ThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, -1));

        btnHuyDon.setBackground(new java.awt.Color(255, 102, 102));
        btnHuyDon.setForeground(new java.awt.Color(0, 0, 0));
        btnHuyDon.setText("Hủy đơn");
        btnHuyDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyDonActionPerformed(evt);
            }
        });
        pnl_FormThongTinHD_Method.add(btnHuyDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, -1));

        pnl_HoaDon_FormThongTinHD.add(pnl_FormThongTinHD_Method);

        pnl_HoaDon_ThongTin.add(pnl_HoaDon_FormThongTinHD);

        tabPaneHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tabPaneHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabPaneHoaDonMouseEntered(evt);
            }
        });

        sclHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sclHoaDonMouseEntered(evt);
            }
        });

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã HD", "Bàn", "Ngày", "Tổng tiền", "Tổng tiền sau KM", "Thanh toán", "Người tạo", "Thông tin"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.setRowHeight(30);
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        sclHoaDon.setViewportView(tblHoaDon);
        if (tblHoaDon.getColumnModel().getColumnCount() > 0) {
            tblHoaDon.getColumnModel().getColumn(0).setPreferredWidth(15);
            tblHoaDon.getColumnModel().getColumn(1).setPreferredWidth(15);
            tblHoaDon.getColumnModel().getColumn(5).setPreferredWidth(15);
            tblHoaDon.getColumnModel().getColumn(6).setPreferredWidth(10);
            tblHoaDon.getColumnModel().getColumn(7).setPreferredWidth(140);
        }

        tabPaneHoaDon.addTab("Hóa đơn", sclHoaDon);

        sclHoaDonChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sclHoaDonChiTietMouseEntered(evt);
            }
        });

        tblHoaDonChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "ID", "Tên sản phẩm", "Size", "Đơn giá", "Số lượng", "Tổng tiền", "Ghi chú", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonChiTiet.setRowHeight(30);
        sclHoaDonChiTiet.setViewportView(tblHoaDonChiTiet);
        if (tblHoaDonChiTiet.getColumnModel().getColumnCount() > 0) {
            tblHoaDonChiTiet.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblHoaDonChiTiet.getColumnModel().getColumn(8).setPreferredWidth(100);
        }

        tabPaneHoaDon.addTab("Hóa đơn chi tiết", sclHoaDonChiTiet);

        pnl_HoaDon_ThongTin.add(tabPaneHoaDon);

        pnl_BanHang_HoaDon.add(pnl_HoaDon_ThongTin);

        pnl_HoaDon_SanPham1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnl_HoaDon_SanPham1.setLayout(new java.awt.BorderLayout());

        pnl_HoaDon_SanPham_SearchAndAdd1.setLayout(new java.awt.BorderLayout());

        lbl_SanPham_SearchAndAdd_Search1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lbl_SanPham_SearchAndAdd_Search1.setText("Tìm kiếm sản phẩm");

        txtSearchProduct.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtSearchProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSearchProductMouseClicked(evt);
            }
        });
        txtSearchProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchProductActionPerformed(evt);
            }
        });

        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btnLamMoi.setText("làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_SanPham_SearchAndAdd1Layout = new javax.swing.GroupLayout(pnl_SanPham_SearchAndAdd1);
        pnl_SanPham_SearchAndAdd1.setLayout(pnl_SanPham_SearchAndAdd1Layout);
        pnl_SanPham_SearchAndAdd1Layout.setHorizontalGroup(
            pnl_SanPham_SearchAndAdd1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_SanPham_SearchAndAdd1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(pnl_SanPham_SearchAndAdd1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_SanPham_SearchAndAdd1Layout.createSequentialGroup()
                        .addComponent(txtSearchProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 824, Short.MAX_VALUE)
                        .addComponent(btnLamMoi))
                    .addGroup(pnl_SanPham_SearchAndAdd1Layout.createSequentialGroup()
                        .addComponent(lbl_SanPham_SearchAndAdd_Search1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnl_SanPham_SearchAndAdd1Layout.setVerticalGroup(
            pnl_SanPham_SearchAndAdd1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_SanPham_SearchAndAdd1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbl_SanPham_SearchAndAdd_Search1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_SanPham_SearchAndAdd1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLamMoi)
                    .addComponent(txtSearchProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pnl_HoaDon_SanPham_SearchAndAdd1.add(pnl_SanPham_SearchAndAdd1, java.awt.BorderLayout.CENTER);

        pnl_HoaDon_SanPham1.add(pnl_HoaDon_SanPham_SearchAndAdd1, java.awt.BorderLayout.PAGE_START);

        jScrollPane6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        tbl_HoaDon_SanPham1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_HoaDon_SanPham1.setRowHeight(30);
        tbl_HoaDon_SanPham1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_HoaDon_SanPham1MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbl_HoaDon_SanPham1);

        pnl_HoaDon_SanPham1.add(jScrollPane6, java.awt.BorderLayout.CENTER);

        pnl_BanHang_HoaDon.add(pnl_HoaDon_SanPham1);

        pnlBanHang.add(pnl_BanHang_HoaDon);

        pnl_BanHang_Ban.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnl_BanHang_Ban.setLayout(new javax.swing.BoxLayout(pnl_BanHang_Ban, javax.swing.BoxLayout.PAGE_AXIS));

        pnl_Ban_Ban.setLayout(new javax.swing.BoxLayout(pnl_Ban_Ban, javax.swing.BoxLayout.PAGE_AXIS));

        pnl_Ban_View.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bàn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N
        pnl_Ban_View.setPreferredSize(new java.awt.Dimension(520, 416));
        pnl_Ban_View.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pnl_Ban_ViewMouseReleased(evt);
            }
        });
        pnl_Ban_View.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));
        jScrollPane2.setViewportView(pnl_Ban_View);

        pnl_Ban_Ban.add(jScrollPane2);

        pnl_BanHang_Ban.add(pnl_Ban_Ban);

        pnl_Ban_GiaoHang.setLayout(new javax.swing.BoxLayout(pnl_Ban_GiaoHang, javax.swing.BoxLayout.PAGE_AXIS));

        lbl_GiaoHang_LBL.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_GiaoHang_LBL.setText("Giao hàng");

        javax.swing.GroupLayout pnl_GiaoHang_LBLLayout = new javax.swing.GroupLayout(pnl_GiaoHang_LBL);
        pnl_GiaoHang_LBL.setLayout(pnl_GiaoHang_LBLLayout);
        pnl_GiaoHang_LBLLayout.setHorizontalGroup(
            pnl_GiaoHang_LBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_GiaoHang_LBL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnl_GiaoHang_LBLLayout.setVerticalGroup(
            pnl_GiaoHang_LBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_GiaoHang_LBLLayout.createSequentialGroup()
                .addComponent(lbl_GiaoHang_LBL)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnl_Ban_GiaoHang.add(pnl_GiaoHang_LBL);

        jScrollPane3.setPreferredSize(new java.awt.Dimension(520, 401));

        pnl_GiaoHang_View.setPreferredSize(new java.awt.Dimension(520, 399));
        pnl_GiaoHang_View.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pnl_GiaoHang_ViewMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(pnl_GiaoHang_View);

        pnl_Ban_GiaoHang.add(jScrollPane3);

        pnl_BanHang_Ban.add(pnl_Ban_GiaoHang);

        pnlBanHang.add(pnl_BanHang_Ban);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    //Sự kiện nhấn btnThanhToan
    private void btn_FormThongTinHD_ThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_FormThongTinHD_ThanhToanActionPerformed

        double tongTien = Double.parseDouble(txt_FormThongTinHD_TongTien.getText().toString());

        if (readFormThanhToan() != null) {
            if (tongTien > 0) {
                if (readFormThanhToan().getTienKhachTra() >= readFormThanhToan().getTongTienSauKM()) {
                    int chon = JOptionPane.showConfirmDialog(
                            this,
                            "Bạn có muốn in hóa đơn không?",
                            "Xác nhận",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );

                    if (chon == JOptionPane.YES_OPTION) {
                        //Nếu có phiếu giảm giá sẽ trừ số lượng
                        if (hdRepo.getHdInAllHdToday(maHoaDonChon).getMaGG() != null) {
                            if (kmRepo.checkTrangThaiMaGiamGia(hdRepo.getHdInAllHdToday(maHoaDonChon).getMaGG())) {
                                hdRepo.updateMaGiamGia(hdRepo.getHdInAllHdToday(maHoaDonChon).getMaGG());
                            } else {
                                JOptionPane.showMessageDialog(this, "Mã giảm giá này đã hết!");
                                kiemTraDieuKienMaGiamGia();
                                return;
                            }
                        }

                        // Người dùng chọn "Yes" để in hóa đơn
                        JOptionPane.showMessageDialog(this, "Loading...");
                        hdRepo.thanhToan(maHoaDonChon, readFormThanhToan(), readFormNewHoaDon().getMaBan());
                        fillTable(hdRepo.getAllHdToday(false));

                        System.out.println("Mã giảm giá: " + hdRepo.getHdInAllHdToday(maHoaDonChon).getMaGG());

                        tabPaneHoaDon.setSelectedIndex(0);
                        tblHoaDon.requestFocus();
                        btnHuyDon.setVisible(false);
                        ttBan();
                        lamMoiFormThanhToan();
                        JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
                    } else if (chon == JOptionPane.NO_OPTION) {
                        //Nếu có phiếu giảm giá sẽ trừ số lượng
                        if (hdRepo.getHdInAllHdToday(maHoaDonChon).getMaGG() != null) {
                            if (kmRepo.checkTrangThaiMaGiamGia(hdRepo.getHdInAllHdToday(maHoaDonChon).getMaGG())) {
                                hdRepo.updateMaGiamGia(hdRepo.getHdInAllHdToday(maHoaDonChon).getMaGG());
                            } else {
                                JOptionPane.showMessageDialog(this, "Mã giảm giá này đã hết!");
                                kiemTraDieuKienMaGiamGia();
                                return;
                            }
                        }

                        // Người dùng chọn "No" (không in hóa đơn nhưng vẫn thanh toán)
                        hdRepo.thanhToan(maHoaDonChon, readFormThanhToan(), readFormNewHoaDon().getMaBan());
                        fillTable(hdRepo.getAllHdToday(false));

                        System.out.println("Mã giảm giá: " + hdRepo.getHdInAllHdToday(maHoaDonChon).getMaGG());

                        tabPaneHoaDon.setSelectedIndex(0);
                        tblHoaDon.requestFocus();
                        btnHuyDon.setVisible(false);
                        ttBan();
                        lamMoiFormThanhToan();
                        JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
                    } else {
                        // Người dùng chọn "Cancel" hoặc đóng hộp thoại
                        JOptionPane.showMessageDialog(this, "Hủy thanh toán!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Hóa đơn chưa có sản phẩm");
            }
        }

    }//GEN-LAST:event_btn_FormThongTinHD_ThanhToanActionPerformed

    //Đọc dữ liệu các ô cần thanh toán
    HoaDonBanHang readFormThanhToan() {
        double tienKhachTra = 0;
        double tienThoi = 0;
        double tongTienSauKm = 0;

        boolean check = true;

        try {
            tongTienSauKm = Double.parseDouble(txtTongTienSauKM.getText().toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu");
            check = false;
        }

        try {
            tienKhachTra = Double.parseDouble(txt_FormThongTinHD_TienKhachTra.getText().toString());
            if (tienKhachTra < tongTienSauKm) {
                JOptionPane.showMessageDialog(this, "Tiền trả chưa đủ");
                txt_FormThongTinHD_TienKhachTra.requestFocus();
                txt_FormThongTinHD_TienKhachTra.setBackground(Color.YELLOW);
                check = false;
            } else if (tienKhachTra > 10000000) {
                JOptionPane.showMessageDialog(this, "Tiền trả quá lớn");
                txt_FormThongTinHD_TienKhachTra.requestFocus();
                txt_FormThongTinHD_TienKhachTra.setBackground(Color.YELLOW);
                txt_FormThongTinHD_TienKhachTra.setText("10000000");
            } else {
                txt_FormThongTinHD_TienKhachTra.setBackground(Color.WHITE);
            }
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Tiền trả là số");
            txt_FormThongTinHD_TienKhachTra.requestFocus();
            txt_FormThongTinHD_TienKhachTra.setBackground(Color.YELLOW);
            check = false;
        }

        tienThoi = tienKhachTra - tongTienSauKm;
        if (tienThoi < 0) {
            txt_FormThongTin_TienThoi.setBackground(Color.YELLOW);
            check = false;
        } else {
            txt_FormThongTin_TienThoi.setBackground(Color.WHITE);
        }

        if (check) {
            return new HoaDonBanHang(tongTienSauKm, tienKhachTra, tienThoi);
        } else {
            return null;
        }

    }

    private void readTienKhuyenMai() {
        double tienKm = 0;
        double tongTien = 0;
        double tienSauKm = 0;

        try {
            if (txt_FormThongTinHD_TongTien.getText().toString().trim().isEmpty()) {
                tongTien = 0;
            } else {
                tongTien = Double.parseDouble(txt_FormThongTinHD_TongTien.getText().toString().trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        try {
            if (txtTongTienSauKM.getText().toString().trim().isEmpty()) {
                tienSauKm = 0;
            } else {
                tienSauKm = Double.parseDouble(txtTongTienSauKM.getText().toString().trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        try {
            tienKm = tongTien - tienSauKm;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        if (tienKm >= 0) {
            txtTienKhuyenMai.setText(String.valueOf(tienKm));
        } else {
            txtTienKhuyenMai.setText("0");
        }
    }

    private void readTongTien() {
        //Thêm documentListener vào txtTongTien
        txt_FormThongTinHD_TongTien.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                danhSachMaGiamGia();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                danhSachMaGiamGia();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                danhSachMaGiamGia();
            }
        });
    }

    //Thêm sự kiện ô text tiền khách trả
    private void readTienKhachTra() {
        // Thêm DocumentListener vào txtInput
        txt_FormThongTinHD_TienKhachTra.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateOutput();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateOutput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateOutput();
            }

            private void updateOutput() {
                try {
                    // Lấy giá trị nhập vào và chuyển đổi thành số
                    double tienKhachTra = Double.parseDouble(txt_FormThongTinHD_TienKhachTra.getText().toString());
                    double tongTienSauKm = Double.parseDouble(txtTongTienSauKM.getText().toString());
                    // Tính toán số lượng mới (ví dụ: nhân đôi)
                    double tienThoi = tienKhachTra - tongTienSauKm;

                    if (tienThoi < 0) {
                        txt_FormThongTin_TienThoi.setText("0");
                    } else {
                        // Cập nhật txtOutput
                        txt_FormThongTin_TienThoi.setText(String.valueOf(tienThoi));
                    }
                } catch (NumberFormatException ex) {
                    // Nếu không nhập đúng số, để ô txtOutput trống
                    txt_FormThongTin_TienThoi.setText("0");
                }
            }
        });
    }

    //Khi di chuyển chuột qua panel khác sẽ ẩn btnAddNewBan
    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        btnAddNewBan.setVisible(false);
    }//GEN-LAST:event_formMouseEntered

    //Khi nhấn chuột phải sẽ hiển thị btnAddNewBan - trên pnlBanView
    private void pnl_Ban_ViewMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_Ban_ViewMouseReleased
        if (SwingUtilities.isRightMouseButton(evt)) {
            showButtonAddTable1(evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_pnl_Ban_ViewMouseReleased

    //Khi di chuyển chuột qua panel khác sẽ ẩn btnAddNewBan
    private void pnlBanHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlBanHangMouseEntered
        btnAddNewBan.setVisible(false);
    }//GEN-LAST:event_pnlBanHangMouseEntered

    //Khi di chuyển chuột qua panel khác sẽ ẩn btnAddNewBan
    private void pnlBanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlBanHangMouseClicked
        btnAddNewBan.setVisible(false);
    }//GEN-LAST:event_pnlBanHangMouseClicked

    //Khi nhấn chuột phải sẽ hiển thị btnAddNewBan - trên pnlGiaoHangView
    private void pnl_GiaoHang_ViewMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_GiaoHang_ViewMouseReleased
        if (SwingUtilities.isRightMouseButton(evt)) {
            showButtonAddTable2(evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_pnl_GiaoHang_ViewMouseReleased

    //Khi di chuyển chuột qua panel khác sẽ ẩn btnAddNewBan
    private void tabPaneHoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabPaneHoaDonMouseEntered
        btnAddNewBan.setVisible(false);
    }//GEN-LAST:event_tabPaneHoaDonMouseEntered

    //Khi di chuyển chuột qua panel khác sẽ ẩn btnAddNewBan
    private void sclHoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sclHoaDonMouseEntered
        btnAddNewBan.setVisible(false);
    }//GEN-LAST:event_sclHoaDonMouseEntered

    //Khi di chuyển chuột qua panel khác sẽ ẩn btnAddNewBan
    private void sclHoaDonChiTietMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sclHoaDonChiTietMouseEntered
        btnAddNewBan.setVisible(false);
    }//GEN-LAST:event_sclHoaDonChiTietMouseEntered

    //Sự kiện btnTaoDon
    private void btn_FormThongTinHD_TaoDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_FormThongTinHD_TaoDonActionPerformed
        Integer maBan = maBanChon;
        System.out.println(maBan);

        ttBan();

        if (readFormNewHoaDon() != null) {
            int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm hóa đơn tại bàn: " + readFormNewHoaDon().getTenBan().toString() + "?");

            if (chon == 0) {
                JOptionPane.showMessageDialog(this, "Tạo đơn thành công");
                hdRepo.insertNewHoaDon(readFormNewHoaDon());
                fillTable(hdRepo.getAllHdToday(false));
                ttBan();
            } else {
                JOptionPane.showMessageDialog(this, "Tạo đơn thất bại!");
            }
        }

        btn_FormThongTinHD_TaoDon.setEnabled(false);
    }//GEN-LAST:event_btn_FormThongTinHD_TaoDonActionPerformed

    //Đọc từ form
    HoaDon readFormNewHoaDon() {
        String maHD;
        Integer maBan;
        String maNV;
        String tenNV;
        Date ngayTaoDate;
        Boolean tinhTrang;
        String tenBan;

        maHD = "HD100";

        tenBan = lbl_FormThongTinHD_Ban_Input.getText().toString().trim();

        maBan = banRepo.getBanByName(tenBan).getMaBan();

        tenNV = cboTenNhanVien.getSelectedItem().toString();

        maNV = nvRepo.getNhanVienByName(tenNV).getManv();

        ngayTaoDate = new Date();

        // Chuyển sang kiểu java.sql.Timestamp
        Timestamp sqlTimestamp = new Timestamp(ngayTaoDate.getTime());

        System.out.println("Ngày tạo 1: " + ngayTaoDate);
        System.out.println("Ngày tạo 2: " + sqlTimestamp);

        tinhTrang = false;

        return new HoaDon(maHD, maBan, maNV, tenBan, sqlTimestamp, tinhTrang, tenBan);
    }

    void showDataHoaDon(int index) {
        String maHD = tblHoaDon.getValueAt(index, 0).toString();

        lbl_FormThongTinHD_MaHoaDon_Input.setText(maHD);
        txt_FormThongTinHD_TongTien.setText(tblHoaDon.getValueAt(index, 3).toString());

        cboTenNhanVien.setSelectedItem(hdRepo.getHdInAllHdToday(maHD).getMaNhanVien().toString());

        lbl_FormThongTinHD_Ngay_Input.setText(hdRepo.getHdInAllHdToday(maHD).getNgayTao().toString());

        if (hdRepo.getHdInAllHdToday(maHD).getTenBan() != null) {
            lbl_FormThongTinHD_Ban_Input.setText(hdRepo.getHdInAllHdToday(maHD).getTenBan().toString());
        } else {
            lbl_FormThongTinHD_Ban_Input.setText("Giao hàng");
            lbl_FormThongTinHD_Ban.setVisible(false);
        }

        txtTongTienSauKM.setText(tblHoaDon.getValueAt(index, 4).toString());

        readTienKhuyenMai();

        tblHoaDon.setRowSelectionInterval(index, index);
    }

    //btnHuyDon - chỉ hủy được đơn hàng chưa thanh toán
    private void btnHuyDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyDonActionPerformed
        // Hiển thị khung xác nhận
        int confirm = JOptionPane.showConfirmDialog(this,
                "Đơn hàng đã được tạo. Bạn có chắc chắn muốn hủy đơn này?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            hdRepo.deleteHoaDon(maHoaDonChon, readFormNewHoaDon().getMaBan());
            JOptionPane.showMessageDialog(this, "Hủy thành công!");
            fillTable(hdRepo.getAllHdToday(false));
            tabPaneHoaDon.setSelectedIndex(0);
            tblHoaDon.requestFocus();
            btnHuyDon.setVisible(false);
            ttBan();
            lamMoiFormThanhToan();
        }
    }//GEN-LAST:event_btnHuyDonActionPerformed

    //Làm mới Form thanh toán
    private void lamMoiFormThanhToan() {
        txt_FormThongTinHD_TongTien.setText("0");
        txtTongTienSauKM.setText("0");
        txtTienKhuyenMai.setText("0");
        txt_FormThongTinHD_TienKhachTra.setText("0");
        txt_FormThongTinHD_TongTien.setText("0");

        lbl_FormThongTinHD_MaHoaDon_Input.setText(null);

        lbl_FormThongTinHD_Ban_Input.setText("Chọn bàn");

        txtSearchProduct.setText(null);
        showDataTableProduct(repos.getAll());

        ttBan();

        fillTable(hdRepo.getAllHdToday(false));

        tabPaneHoaDon.setSelectedIndex(0);

        tblHoaDon.requestFocus();

        indexTableHoaDon = -1;

        btnHuyDon.setVisible(false);

        btn_FormThongTinHD_ThanhToan.setEnabled(false);
    }

    //Tìm kiếm sản phẩm
    private void txtSearchProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchProductActionPerformed
        try {
            // Kiểm tra trường hợp người dùng không nhập tên sản phẩm

            if (txtSearchProduct.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên sản phẩm!");
                txtSearchProduct.setBackground(Color.PINK);
                return; // Dừng lại nếu chưa nhập gì
            } else {
                txtSearchProduct.setBackground(null);
            }

            // Lọc danh sách sản phẩm với tên chứa chuỗi tìm kiếm (không phân biệt chữ hoa/thường)
            List<SanPham_BanHang> listSearch = repos.getAll().stream()
                    .filter(s -> s.getTen().contains(txtSearchProduct.getText().toString()))
                    .collect(Collectors.toList());

            // Kiểm tra nếu không có kết quả tìm kiếm
            // Hiển thị kết quả tìm kiếm
            showDataTableProduct(listSearch);

        } catch (Exception e) {
            // Xử lý ngoại lệ và thông báo lỗi cho người dùng
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi tìm kiếm: " + e.getMessage());
            e.printStackTrace(); // Ghi lỗi chi tiết nếu cần
        }
    }//GEN-LAST:event_txtSearchProductActionPerformed

    //Button Làm mới
    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        lamMoiFormThanhToan();
        
        btn_FormThongTinHD_TaoDon.setEnabled(false);
    }//GEN-LAST:event_btnLamMoiActionPerformed

    //Sự kiện click chuột bảng Hóa đơn
    private void tbl_HoaDon_SanPham1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_HoaDon_SanPham1MouseClicked
        int index = tbl_HoaDon_SanPham1.getSelectedRow();

        if (indexTableHoaDon != -1) {
            String trangThai = tblHoaDon.getValueAt(indexTableHoaDon, 5).toString();

            if (trangThai.equals("Chưa thanh toán")) {
                maHoaDonChon = tblHoaDon.getValueAt(indexTableHoaDon, 0).toString();

                Integer maSpChon = Integer.parseInt(tbl_HoaDon_SanPham1.getValueAt(index, 0).toString());

                Frame GiaoDienChinh = new GiaoDienChinh();

                AddProduct addProduct = new AddProduct(GiaoDienChinh, true);

                addProduct.setMaSanPham(maSpChon);
                addProduct.setMaHDTao(maHoaDonChon);

                addProduct.setVisible(true);

                fillTableHdct(hdRepo.getAllHdChiTietByMaHD(maHoaDonChon));
                fillTable(hdRepo.getAllHdToday(false));
                showDataHoaDon(indexTableHoaDon);
            } else {
                JOptionPane.showMessageDialog(this, "Hóa đơn đã thanh toán. Cần thêm món vui lòng tạo hóa đơn mới!");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn hóa đơn");
        }

    }//GEN-LAST:event_tbl_HoaDon_SanPham1MouseClicked

    //Sự kiện nhấn vào tblHoaDon sẽ hiển thị xuống Form thông tin
    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked

        indexTableHoaDon = tblHoaDon.getSelectedRow();
        showDataHoaDon(indexTableHoaDon);

        String trangThai = tblHoaDon.getValueAt(indexTableHoaDon, 5).toString();
        maHoaDonChon = tblHoaDon.getValueAt(indexTableHoaDon, 0).toString();
        String tenBan = tblHoaDon.getValueAt(indexTableHoaDon, 1).toString(); // Giả sử cột 1 chứa tên bàn

        // Duyệt qua tất cả các bàn để cập nhật màu sắc
        Component[] components = pnl_Ban_View.getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel pnlBan = (JPanel) component;
                String tenBanPnl = ((TitledBorder) pnlBan.getBorder()).getTitle(); // Lấy tên bàn từ border

                if (tenBanPnl.equals(tenBan)) {
                    pnlBan.setBackground(Color.RED); // Tô màu đỏ cho bàn được chọn
                    pnlBan.putClientProperty("isSelected", true); // Đánh dấu bàn được chọn
                } else {
                    pnlBan.putClientProperty("isSelected", false); // Đánh dấu bàn không được chọn
                    // Cập nhật lại màu sắc bàn dựa trên trạng thái
                    Ban otherBan = banRepo.getBanByName(tenBanPnl); // Lấy trạng thái bàn từ cơ sở dữ liệu
                    if (otherBan.getTrangThai() == 0) {
                        pnlBan.setBackground(Color.WHITE); // Bàn trống
                    } else if (otherBan.getTrangThai() == 1) {
                        pnlBan.setBackground(new Color(204, 255, 153)); // Có người
                    }
                }
            }
        }

        if (trangThai.equals("Chưa thanh toán")) {
            btnHuyDon.setVisible(true);
        } else {
            btnHuyDon.setVisible(false);
        }

        tabPaneHoaDon.setSelectedIndex(1);
        tblHoaDonChiTiet.requestFocus();
        fillTableHdct(hdRepo.getAllHdChiTietByMaHD(maHoaDonChon));

        txt_FormThongTinHD_TienKhachTra.setText("0");
        txt_FormThongTin_TienThoi.setText("0");

        danhSachMaGiamGia();

        btn_FormThongTinHD_TaoDon.setEnabled(false);
        btn_FormThongTinHD_ThanhToan.setEnabled(true);

//        readTienKhuyenMai();
        //Hiện discount
        btnDiscount.setEnabled(true);
//        ttBan();

    }//GEN-LAST:event_tblHoaDonMouseClicked

    //Sự kiện click chuột ô search Sản phẩm
    private void txtSearchProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchProductMouseClicked
        txtSearchProduct.setText(null);
    }//GEN-LAST:event_txtSearchProductMouseClicked

    //Sự kiện cho cboMaGiamGia
    private void cboMaGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMaGiamGiaActionPerformed


    }//GEN-LAST:event_cboMaGiamGiaActionPerformed

    //Khi nhấn vào Discount mới add mã giảm giá vào hóa đơn
    private void btnDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiscountActionPerformed
        // Lấy mã giảm giá đã chọn từ ComboBox
        String selectedText = (String) cboMaGiamGia.getSelectedItem();

        if (selectedText == null || selectedText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn mã giảm giá trước!");
            return;
        }

        // Tìm đối tượng KhuyenMaiChiTiet tương ứng
        KhuyenMaiChiTiet selectedKhuyenMai = null;
        for (KhuyenMaiChiTiet km : kmRepo.getAllKhuyenMaiChiTiet(true)) {
            String text = km.getMaGiamGia() + ": " + km.getMucGiam()
                    + (km.getLoaiGiamGia().equals("Giảm theo VNĐ") ? "VNĐ" : "%")
                    + " - ĐK: >=" + km.getDieuKien() + " /Tối đa: " +km.getMucGiamToiDa();
            if (text.equals(selectedText)) {
                selectedKhuyenMai = km;
                break;
            }
        }

        // Kiểm tra nếu không tìm thấy mã giảm giá
        if (selectedKhuyenMai == null) {
            JOptionPane.showMessageDialog(this, "Mã giảm giá không hợp lệ!");
            return;
        }

        double tongTien = Double.parseDouble(txt_FormThongTinHD_TongTien.getText().toString());

        // Kiểm tra điều kiện áp dụng mã giảm giá
        if (selectedKhuyenMai.getDieuKien() > tongTien) {
            JOptionPane.showMessageDialog(this, "Tổng tiền không đủ điều kiện để áp dụng mã giảm giá!");
            return;
        }

        // Hiển thị khung xác nhận
        int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc chắn muốn thêm mã giảm giá này vào hóa đơn không?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            double mucGiam = selectedKhuyenMai.getMucGiam();
            double tongTienSauKm = 0;
            double soTienGiam;

            if (selectedKhuyenMai.getLoaiGiamGia().equals("Giảm theo VNĐ")) {
                tongTienSauKm = tongTien - mucGiam;
            } else {
                if ((tongTien * mucGiam / 100) < selectedKhuyenMai.getMucGiamToiDa()) {
                    tongTienSauKm = tongTien - tongTien * mucGiam / 100;
                } else {
                    tongTienSauKm = tongTien - selectedKhuyenMai.getMucGiamToiDa();
                }
            }

            soTienGiam = tongTien - tongTienSauKm;
            hdRepo.updateMaGiamGia2(selectedKhuyenMai.getMaGiamGia(), maHoaDonChon, tongTienSauKm);
            fillTable(hdRepo.getAllHdToday(false));
            JOptionPane.showMessageDialog(this, "Mã giảm giá đã được thêm vào hóa đơn!");
            txtTienKhuyenMai.setText(String.valueOf(soTienGiam));
            txtTongTienSauKM.setText(String.valueOf(tongTienSauKm));
        } else {
            JOptionPane.showMessageDialog(this, "Bạn đã hủy thao tác thêm mã giảm giá.");
        }
    }//GEN-LAST:event_btnDiscountActionPerformed


    private void txt_FormThongTinHD_TienKhachTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_FormThongTinHD_TienKhachTraActionPerformed

        if (readFormThanhToan() != null) {
            System.out.println("Tổng tiền: " + readFormThanhToan().getTongTienSauKM());
            System.out.println("Tiền khách trả: " + readFormThanhToan().getTienKhachTra());
            System.out.println("Tiền thối: " + readFormThanhToan().getTienThoi());
            txt_FormThongTin_TienThoi.setText(readFormThanhToan().getTienThoi().toString());
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu");
        }

    }//GEN-LAST:event_txt_FormThongTinHD_TienKhachTraActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDiscount;
    private javax.swing.JButton btnHuyDon;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btn_FormThongTinHD_TaoDon;
    private javax.swing.JButton btn_FormThongTinHD_ThanhToan;
    private javax.swing.JComboBox<String> cboMaGiamGia;
    private javax.swing.JComboBox<String> cboTenNhanVien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lbl_FormThongTinHD_Ban;
    private javax.swing.JLabel lbl_FormThongTinHD_Ban_Input;
    private javax.swing.JLabel lbl_FormThongTinHD_MaHoaDon;
    private javax.swing.JLabel lbl_FormThongTinHD_MaHoaDon_Input;
    private javax.swing.JLabel lbl_FormThongTinHD_Ngay_Input;
    private javax.swing.JLabel lbl_FormThongTinHD_NhanVien;
    private javax.swing.JLabel lbl_FormThongTinHD_TienKhachTra;
    private javax.swing.JLabel lbl_FormThongTinHD_TienThoi;
    private javax.swing.JLabel lbl_FormThongTinHD_TongTien;
    private javax.swing.JLabel lbl_GiaoHang_LBL;
    private javax.swing.JLabel lbl_SanPham_SearchAndAdd_Search1;
    private javax.swing.JPanel pnlBanHang;
    private javax.swing.JPanel pnl_BanHang_Ban;
    private javax.swing.JPanel pnl_BanHang_HoaDon;
    private javax.swing.JPanel pnl_Ban_Ban;
    private javax.swing.JPanel pnl_Ban_GiaoHang;
    private javax.swing.JPanel pnl_Ban_View;
    private javax.swing.JPanel pnl_FormThongTinHD_Details;
    private javax.swing.JPanel pnl_FormThongTinHD_Method;
    private javax.swing.JPanel pnl_GiaoHang_LBL;
    private javax.swing.JPanel pnl_GiaoHang_View;
    private javax.swing.JPanel pnl_HoaDon_FormThongTinHD;
    private javax.swing.JPanel pnl_HoaDon_SanPham1;
    private javax.swing.JPanel pnl_HoaDon_SanPham_SearchAndAdd1;
    private javax.swing.JPanel pnl_HoaDon_ThongTin;
    private javax.swing.JPanel pnl_SanPham_SearchAndAdd1;
    private javax.swing.ButtonGroup rdoGroupGioiTinhKH;
    private javax.swing.JScrollPane sclHoaDon;
    private javax.swing.JScrollPane sclHoaDonChiTiet;
    private javax.swing.JTabbedPane tabPaneHoaDon;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonChiTiet;
    private javax.swing.JTable tbl_HoaDon_SanPham1;
    private javax.swing.JTextField txtSearchProduct;
    private javax.swing.JTextField txtTienKhuyenMai;
    private javax.swing.JTextField txtTongTienSauKM;
    private javax.swing.JTextField txt_FormThongTinHD_TienKhachTra;
    private javax.swing.JTextField txt_FormThongTinHD_TongTien;
    private javax.swing.JTextField txt_FormThongTin_TienThoi;
    // End of variables declaration//GEN-END:variables

}
