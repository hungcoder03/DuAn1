/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.ThongKeModel;

/**
 *
 * @author Admin
 */
public class ThongKe {

    private String tenSanPham;
    private double tongTienThanhToan;
    private int soLuong;
    private String maHoaDon;
    private double doanhThuHomNay;
    private double doanhThuHomQua;
    private int tongSanPhamHomNay;
    private int tongDonHomNay;
    //cbx 
    private int thang;
    private int thangBatDau;
    private int thangKetThuc;
    private int nam;
    private int tongHoaDon;
    private int tongSoLuong;

    public ThongKe() {
    }

    public ThongKe(String tenSanPham, double tongTienThanhToan, int soLuong, String maHoaDon, double doanhThuHomNay, double doanhThuHomQua, int tongSanPhamHomNay, int tongDonHomNay, int thangBatDau, int thangKetThuc, int nam, int tongHoaDon, int tongSoLuong) {
        this.tenSanPham = tenSanPham;
        this.tongTienThanhToan = tongTienThanhToan;
        this.soLuong = soLuong;
        this.maHoaDon = maHoaDon;
        this.doanhThuHomNay = doanhThuHomNay;
        this.doanhThuHomQua = doanhThuHomQua;
        this.tongSanPhamHomNay = tongSanPhamHomNay;
        this.tongDonHomNay = tongDonHomNay;
        this.thangBatDau = thangBatDau;
        this.thangKetThuc = thangKetThuc;
        this.nam = nam;
        this.tongHoaDon = tongHoaDon;
        this.tongSoLuong = tongSoLuong;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getThang() {
        return thang;
    }

    public ThongKe(int thang) {
        this.thang = thang;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public double getTongTienThanhToan() {
        return tongTienThanhToan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public double getDoanhThuHomNay() {
        return doanhThuHomNay;
    }

    public double getDoanhThuHomQua() {
        return doanhThuHomQua;
    }

    public int getTongSanPhamHomNay() {
        return tongSanPhamHomNay;
    }

    public int getTongDonHomNay() {
        return tongDonHomNay;
    }

    public int getThangBatDau() {
        return thangBatDau;
    }

    public int getThangKetThuc() {
        return thangKetThuc;
    }

    public int getNam() {
        return nam;
    }

    public int getTongHoaDon() {
        return tongHoaDon;
    }

    public int getTongSoLuong() {
        return tongSoLuong;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public void setTongTienThanhToan(double tongTienThanhToan) {
        this.tongTienThanhToan = tongTienThanhToan;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public void setDoanhThuHomNay(double doanhThuHomNay) {
        this.doanhThuHomNay = doanhThuHomNay;
    }

    public void setDoanhThuHomQua(double doanhThuHomQua) {
        this.doanhThuHomQua = doanhThuHomQua;
    }

    public void setTongSanPhamHomNay(int tongSanPhamHomNay) {
        this.tongSanPhamHomNay = tongSanPhamHomNay;
    }

    public void setTongDonHomNay(int tongDonHomNay) {
        this.tongDonHomNay = tongDonHomNay;
    }

    public void setThangBatDau(int thangBatDau) {
        this.thangBatDau = thangBatDau;
    }

    public void setThangKetThuc(int thangKetThuc) {
        this.thangKetThuc = thangKetThuc;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public void setTongHoaDon(int tongHoaDon) {
        this.tongHoaDon = tongHoaDon;
    }

    public void setTongSoLuong(int tongSoLuong) {
        this.tongSoLuong = tongSoLuong;
    }

    @Override
    public String toString() {
        return "ThongKe{" + "tenSanPham=" + tenSanPham + ", tongTienThanhToan=" + tongTienThanhToan + ", soLuong=" + soLuong + ", maHoaDon=" + maHoaDon + ", doanhThuHomNay=" + doanhThuHomNay + ", doanhThuHomQua=" + doanhThuHomQua + ", tongSanPhamHomNay=" + tongSanPhamHomNay + ", tongDonHomNay=" + tongDonHomNay + ", thangBatDau=" + thangBatDau + ", thangKetThuc=" + thangKetThuc + ", nam=" + nam + ", tongHoaDon=" + tongHoaDon + ", tongSoLuong=" + tongSoLuong + '}';
    }

}
