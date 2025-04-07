/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.BanHangModel;

import java.util.Date;

/**
 *
 * @author admin
 */
public class HoaDonBanHang {
    
    private String maHD;
    
    private String maGG;
    
    private String tenBan;
    
    private Date ngayTao;
    
    private Double tongTien;
    
    private Double tongTienSauKM;
    
    private Boolean thanhToan;
    
    private String tenNhanVien;
    
    private String maNhanVien;
    
    private Double tienKhachTra;
    
    private Double tienThoi;
    
    private String diaChi;
    
    private String sdt;

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    
    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
    

    public Double getTienKhachTra() {
        return tienKhachTra;
    }

    public void setTienKhachTra(Double tienKhachTra) {
        this.tienKhachTra = tienKhachTra;
    }

    public String getMaGG() {
        return maGG;
    }

    public void setMaGG(String maGG) {
        this.maGG = maGG;
    }
    
    public Double getTienThoi() {
        return tienThoi;
    }

    public void setTienThoi(Double tienThoi) {
        this.tienThoi = tienThoi;
    }

    public HoaDonBanHang(Double tongTienSauKM, Double tienKhachTra, Double tienThoi) {
        this.tongTienSauKM = tongTienSauKM;
        this.tienKhachTra = tienKhachTra;
        this.tienThoi = tienThoi;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public HoaDonBanHang(String maHD, String maGG, String tenBan, Date ngayTao, Double tongTien, Double tongTienSauKM, Boolean thanhToan, String tenNhanVien, String maNhanVien, String diaChi, String sdt) {
        this.maHD = maHD;
        this.maGG = maGG;
        this.tenBan = tenBan;
        this.ngayTao = ngayTao;
        this.tongTien = tongTien;
        this.tongTienSauKM = tongTienSauKM;
        this.thanhToan = thanhToan;
        this.tenNhanVien = tenNhanVien;
        this.maNhanVien = maNhanVien;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }
    
    

    public HoaDonBanHang(String maHD, String tenBan, Date ngayTao, Double tongTien, Double tongTienSauKM, Boolean thanhToan, String tenNhanVien) {
        this.maHD = maHD;
        this.tenBan = tenBan;
        this.ngayTao = ngayTao;
        this.tongTien = tongTien;
        this.tongTienSauKM = tongTienSauKM;
        this.thanhToan = thanhToan;
        this.tenNhanVien = tenNhanVien;
    }

    public HoaDonBanHang() {
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public Double getTongTienSauKM() {
        return tongTienSauKM;
    }

    public void setTongTienSauKM(Double tongTienSauKM) {
        this.tongTienSauKM = tongTienSauKM;
    }

    public Boolean getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(Boolean thanhToan) {
        this.thanhToan = thanhToan;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    @Override
    public String toString() {
        return "HoaDonBanHang{" + "maHD=" + maHD + ", tenBan=" + tenBan + ", ngayTao=" + ngayTao + ", tongTien=" + tongTien + ", tongTienSauKM=" + tongTienSauKM + ", thanhToan=" + thanhToan + ", tenNhanVien=" + tenNhanVien + '}';
    }
    
    public Object[] toDataRow() {
        return new Object[] {
            maHD,
            (tenBan==null)?"Giao hàng":tenBan,
            ngayTao,
            tongTien,
            tongTienSauKM,
            thanhToan?"Đã thanh toán":"Chưa thanh toán",
            tenNhanVien
        };
    }
    
}
