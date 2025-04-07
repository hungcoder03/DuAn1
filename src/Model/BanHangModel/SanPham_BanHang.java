/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.BanHangModel;

/**
 *
 * @author Admin
 */
public class SanPham_BanHang {
    private int ma;
    private String ten;
    private double gia;
    private boolean trangThai;

    public SanPham_BanHang() {
    }

    public SanPham_BanHang(int ma, String ten, double gia, boolean trangThai) {
        this.ma = ma;
        this.ten = ten;
        this.gia = gia;
        this.trangThai = trangThai;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "SanPham_BanHang{" + "ma=" + ma + ", ten=" + ten + ", gia=" + gia + ", trangThai=" + trangThai + '}';
    }
    
    
}
