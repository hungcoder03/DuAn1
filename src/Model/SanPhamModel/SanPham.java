/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.SanPhamModel;

/**
 *
 * @author admin
 */
public class SanPham {
    private int ma;
    private String ten;
    private double gia;
    private boolean trangThai;
    private byte[] hinhAnh;

    public SanPham() {
    }

    public SanPham(int ma, String ten, double gia, boolean trangThai, byte[] hinhAnh) {
        this.ma = ma;
        this.ten = ten;
        this.gia = gia;
        this.trangThai = trangThai;
        this.hinhAnh = hinhAnh;
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

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    @Override
    public String toString() {
        return "SanPham{" + "ma=" + ma + ", ten=" + ten + ", gia=" + gia + ", trangThai=" + trangThai + ", hinhAnh=" + hinhAnh + '}';
    }

    
    
    
    
}
