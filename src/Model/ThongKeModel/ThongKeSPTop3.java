/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.ThongKeModel;

/**
 *
 * @author Admin
 */
public class ThongKeSPTop3 {

    private String tenSanPham;
    private int maSanPham;
    private int soLuong;
    private float tongTienThanhToan;
    private float donGia;

    public ThongKeSPTop3() {
    }

    public ThongKeSPTop3(String tenSanPham, int maSanPham, int soLuong, float tongTienThanhToan, float donGia) {
        this.tenSanPham = tenSanPham;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.tongTienThanhToan = tongTienThanhToan;
        this.donGia = donGia;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public float getTongTienThanhToan() {
        return tongTienThanhToan;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public void setmaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setTongTienThanhToan(float tongTienThanhToan) {
        this.tongTienThanhToan = tongTienThanhToan;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        return "ThongKeSPTop3{" + "tenSanPham=" + tenSanPham + ", maHoaDon=" + maSanPham + ", soLuong=" + soLuong + ", tongTienThanhToan=" + tongTienThanhToan + ", donGia=" + donGia + '}';
    }

}
