/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.SanPhamModel;

import java.util.Vector;

/**
 *
 * @author Admin
 */
public class modelSize {
    private String tenSize;
    private int gia;
    private String ngayBatDau;
    private String ngayKetThuc;
    private boolean TrangThai;

    public modelSize() {
    }

    public modelSize(String tenSize, int gia, String ngayBatDau, String ngayKetThuc, boolean TrangThai) {
        this.tenSize = tenSize;
        this.gia = gia;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.TrangThai = TrangThai;
    }

    public String getTenSize() {
        return tenSize;
    }

    public void setTenSize(String tenSize) {
        this.tenSize = tenSize;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    @Override
    public String toString() {
        return "modelSize{" + "tenSize=" + tenSize + ", gia=" + gia + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc + ", TrangThai=" + TrangThai + '}';
    }
    
    public Object[] toDataRoww(){
        return new Object[]{
            tenSize
        };
    }
}
