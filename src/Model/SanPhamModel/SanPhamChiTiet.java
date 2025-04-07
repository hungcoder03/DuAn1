/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.SanPhamModel;

/**
 *
 * @author Admin
 */
public class SanPhamChiTiet {
    private int maSPCT;
    private String tenSPCT;
    private String size;
    private double giaSP;
    private double giaSize;
    private double donGia;
    private boolean tinhTrangSPCT;

    public SanPhamChiTiet() {
    }

    public SanPhamChiTiet(int maSPCT, String tenSPCT, String size, double giaSP, double giaSize, double donGia, boolean tinhTrangSPCT) {
        this.maSPCT = maSPCT;
        this.tenSPCT = tenSPCT;
        this.size = size;
        this.giaSP = giaSP;
        this.giaSize = giaSize;
        this.donGia = donGia;
        this.tinhTrangSPCT = tinhTrangSPCT;
    }

    public int getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(int maSPCT) {
        this.maSPCT = maSPCT;
    }

    public String getTenSPCT() {
        return tenSPCT;
    }

    public void setTenSPCT(String tenSPCT) {
        this.tenSPCT = tenSPCT;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(double giaSP) {
        this.giaSP = giaSP;
    }

    public double getGiaSize() {
        return giaSize;
    }

    public void setGiaSize(double giaSize) {
        this.giaSize = giaSize;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public boolean isTinhTrangSPCT() {
        return tinhTrangSPCT;
    }

    public void setTinhTrangSPCT(boolean tinhTrangSPCT) {
        this.tinhTrangSPCT = tinhTrangSPCT;
    }

    @Override
    public String toString() {
        return "SanPhamChiTiet{" + "maSPCT=" + maSPCT + ", tenSPCT=" + tenSPCT + ", size=" + size + ", giaSP=" + giaSP + ", giaSize=" + giaSize + ", donGia=" + donGia + ", tinhTrangSPCT=" + tinhTrangSPCT + '}';
    }

    
    
    
}
