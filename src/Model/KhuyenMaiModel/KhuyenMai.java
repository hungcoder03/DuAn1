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
public class KhuyenMai {
    
    private String maGiamGia;
    
    private String loaiGiamGia;
    
    private int soLuong;
    
    private float mucGiam;
    
    private Date ngayBatDau;
    
    private Date ngayKetThuc;
    
    private boolean trangThai;

    public KhuyenMai(String maGiamGia, String loaiGiamGia,float mucGiam, int soLuong, Date ngayBatDau, Date ngayKetThuc, boolean trangThai) {
        this.maGiamGia = maGiamGia;
        this.loaiGiamGia = loaiGiamGia;
        this.mucGiam = mucGiam;
        this.soLuong = soLuong;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
    }
    
    

    public KhuyenMai(String maGiamGia, String loaiGiamGia, int soLuong, Date ngayBatDau, Date ngayKetThuc, boolean trangThai) {
        this.maGiamGia = maGiamGia;
        this.loaiGiamGia = loaiGiamGia;
        this.soLuong = soLuong;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
    }

    public KhuyenMai() {
    }

    public float getMucGiam() {
        return mucGiam;
    }

    public void setMucGiam(float mucGiam) {
        this.mucGiam = mucGiam;
    }

    
    
    public String getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(String maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public String getLoaiGiamGia() {
        return loaiGiamGia;
    }

    public void setLoaiGiamGia(String loaiGiamGia) {
        this.loaiGiamGia = loaiGiamGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
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

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "KhuyenMai{" + "maGiamGia=" + maGiamGia + ", loaiGiamGia=" + loaiGiamGia + ", soLuong=" + soLuong + ", mucGiam=" + mucGiam + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc + ", trangThai=" + trangThai + '}';
    }

    
    
    public Object[] toDataRow(int stt) {
        return new Object[] {
            stt,
            maGiamGia,
            loaiGiamGia,
            mucGiam,
            soLuong,
            ngayBatDau,
            ngayKetThuc,
            trangThai?"Đang diễn ra":"Kết thúc"
        };
    }
    
}
