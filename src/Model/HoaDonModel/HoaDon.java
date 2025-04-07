/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.HoaDonModel;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class HoaDon {

    private String maHoaDon;
    
    private int maBan;

    private String maGiamGia;
    private String maNhanVien;
    private String maKhachHang;
    private String tenNhanVien;
    private String tenKhachHang;
    private String ngayTao;
    private Date ngayTaoDate;
    
    private String tongTienThanhToan;
    private boolean tinhTrang;
    private String sdt;
    private String tenBan;
    
    private double tongTienSauKM;
    private double tienKM;

    public double getTienKM() {
        return tienKM;
    }

    public void setTienKM(double tienKM) {
        this.tienKM = tienKM;
    }
    

    public double getTongTienSauKM() {
        return tongTienSauKM;
    }

    public void setTongTienSauKM(double tongTienSauKM) {
        this.tongTienSauKM = tongTienSauKM;
    }
    
    public HoaDon(String maHoaDon, int maBan, String maNhanVien, String tenNhanVien, Date ngayTaoDate, boolean tinhTrang, String tenBan) {
        this.maHoaDon = maHoaDon;
        this.maBan = maBan;
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.ngayTaoDate = ngayTaoDate;
        this.tinhTrang = tinhTrang;
        this.tenBan = tenBan;
    }

    public Date getNgayTaoDate() {
        return ngayTaoDate;
    }

    public void setNgayTaoDate(Date ngayTaoDate) {
        this.ngayTaoDate = ngayTaoDate;
    }
    
    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }
    
    public HoaDon() {
    }

    public HoaDon(String maHoaDon, int maBan, String maGiamGia, String maNhanVien, String maKhachHang, String tenNhanVien, String tenKhachHang, String ngayTao, String tongTienThanhToan, boolean tinhTrang, String sdt) {
        this.maHoaDon = maHoaDon;
        this.maBan = maBan;
        this.maGiamGia = maGiamGia;
        this.maNhanVien = maNhanVien;
        this.maKhachHang = maKhachHang;
        this.tenNhanVien = tenNhanVien;
        this.tenKhachHang = tenKhachHang;
        this.ngayTao = ngayTao;
        this.tongTienThanhToan = tongTienThanhToan;
        this.tinhTrang = tinhTrang;
        this.sdt = sdt;
    }
    

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public int getMaBan() {
        return maBan;
    }

    public String getMaGiamGia() {
        return maGiamGia;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public String getTongTienThanhToan() {
        return tongTienThanhToan;
    }

    public boolean isTinhTrang() {
        return tinhTrang;
    }

    public String getSdt() {
        return sdt;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public void setMaGiamGia(String maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public void setTongTienThanhToan(String tongTienThanhToan) {
        this.tongTienThanhToan = tongTienThanhToan;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "maHoaDon=" + maHoaDon + ", maBan=" + maBan + ", maGiamGia=" + maGiamGia + ", maNhanVien=" + maNhanVien + ", maKhachHang=" + maKhachHang + ", tenNhanVien=" + tenNhanVien + ", tenKhachHang=" + tenKhachHang + ", ngayTao=" + ngayTao + ", ngayTaoDate=" + ngayTaoDate + ", tongTienThanhToan=" + tongTienThanhToan + ", tinhTrang=" + tinhTrang + ", sdt=" + sdt + ", tenBan=" + tenBan + ", tongTienSauKM=" + tongTienSauKM + ", tienKM=" + tienKM + '}';
    }
    
}
