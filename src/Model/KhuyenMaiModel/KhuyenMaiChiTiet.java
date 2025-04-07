/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.KhuyenMaiModel;

import java.util.Date;

/**
 *
 * @author admin
 */
public class KhuyenMaiChiTiet {
    
    private String maGiamGia;
    
    private String tenNhanVien;
    
    private String loaiGiamGia;
    
    private float dieuKien;
    
    private float mucGiam;
    
    private float mucGiamToiDa;
    
    private int soLuong;
    
    private Date ngayBatDau;
    
    private Date ngayKetThuc;
    
    private Date ngayTao;
    
    private Date ngaySua;
    
    private String ghiChu;
    
    private boolean trangThai;

    public KhuyenMaiChiTiet() {
    }

    public KhuyenMaiChiTiet(String maGiamGia, String tenNhanVien, String loaiGiamGia, float dieuKien, float mucGiam, float mucGiamToiDa, int soLuong, Date ngayBatDau, Date ngayKetThuc, Date ngayTao, Date ngaySua, String ghiChu, boolean trangThai) {
        this.maGiamGia = maGiamGia;
        this.tenNhanVien = tenNhanVien;
        this.loaiGiamGia = loaiGiamGia;
        this.dieuKien = dieuKien;
        this.mucGiam = mucGiam;
        this.mucGiamToiDa = mucGiamToiDa;
        this.soLuong = soLuong;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }

    

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(String maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getLoaiGiamGia() {
        return loaiGiamGia;
    }

    public void setLoaiGiamGia(String loaiGiamGia) {
        this.loaiGiamGia = loaiGiamGia;
    }

    public float getDieuKien() {
        return dieuKien;
    }

    public void setDieuKien(float dieuKien) {
        this.dieuKien = dieuKien;
    }

    public float getMucGiam() {
        return mucGiam;
    }

    public void setMucGiam(float mucGiam) {
        this.mucGiam = mucGiam;
    }

    public float getMucGiamToiDa() {
        return mucGiamToiDa;
    }

    public void setMucGiamToiDa(float mucGiamToiDa) {
        this.mucGiamToiDa = mucGiamToiDa;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "KhuyenMaiChiTiet{" + "maGiamGia=" + maGiamGia + ", tenNhanVien=" + tenNhanVien + ", loaiGiamGia=" + loaiGiamGia + ", dieuKien=" + dieuKien + ", mucGiam=" + mucGiam + ", mucGiamToiDa=" + mucGiamToiDa + ", soluong= " + soLuong + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc + ", ngayTao=" + ngayTao + ", ngaySua=" + ngaySua + ", ghiChu=" + ghiChu + ", trangThai=" + trangThai + '}';
    }
    
}
