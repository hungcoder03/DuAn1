/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.HoaDonModel;

import Model.SanPhamModel.modelSize;
import Repository.BanHangRepository.RepoSize;

/**
 *
 * @author Admin
 */
public class HoaDonChiTiet {

    private modelSize size = new modelSize();
    
    private RepoSize sizeRepo = new RepoSize();
    
    private int stt;
    private int id;
    private String maHoaDon;
    private int maSanPham;
    private String maGiamGia;
    private String tenSanPham;
    private float donGia;
    private int soLuong;
    private String ghiChu;
    private int idSize;
    private String tenSize;
    private double giaSize;

    public double getGiaSize() {
        return giaSize;
    }

    public void setGiaSize(double giaSize) {
        giaSize = sizeRepo.getGiaSizeByName(tenSize).getGia();
        this.giaSize = giaSize;
    }

    public int getIdSize() {
        return idSize;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public void setIdSize(int idSize) {
        this.idSize = idSize;
    }

    public String getTenSize() {
        return tenSize;
    }

    public void setTenSize(String tenSize) {
        this.tenSize = tenSize;
    }
    
    public HoaDonChiTiet(int stt, int id, int idSize, String maHoaDon, int maSanPham, String tenSanPham, String tenSize, float donGia, int soLuong, String ghiChu) {
        this.stt = stt;
        this.id = id;
        this.idSize = idSize;
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.tenSize = tenSize;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.ghiChu = ghiChu;
    }

    public HoaDonChiTiet(int id, int idSize, String maHoaDon, int maSanPham, String tenSanPham, String tenSize, float donGia, int soLuong, String ghiChu) {
        this.id = id;
        this.idSize = idSize;
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.tenSize = tenSize;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.ghiChu = ghiChu;
    }

    public HoaDonChiTiet(String maHoaDon, int idSize, int maSanPham, String tenSanPham, float donGia, int soLuong, String ghiChu) {
        this.maHoaDon = maHoaDon;
        this.idSize = idSize;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.ghiChu = ghiChu;
    }
    
    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int id, String maHoaDon, int maSanPham, String maGiamGia, String tenSanPham, float donGia, int soLuong, String ghiChu) {
        this.id = id;
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.maGiamGia = maGiamGia;
        this.tenSanPham = tenSanPham;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.ghiChu = ghiChu;
    }

    public int getId() {
        return id;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public String getMaGiamGia() {
        return maGiamGia;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public float getDonGia() {
        return donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setMaGiamGia(String maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "HoaDonChiTiet{" + "stt=" + stt + ", id=" + id + ", maHoaDon=" + maHoaDon + ", maSanPham=" + maSanPham + ", maGiamGia=" + maGiamGia + ", tenSanPham=" + tenSanPham + ", donGia=" + (donGia+giaSize) + ", soLuong=" + soLuong + ", ghiChu=" + ghiChu + ", idSize=" + idSize + ", tenSize=" + tenSize + '}';
    }
    
    public Object[] toDataRow() {
        return new Object[] {
            stt,
            id,
            tenSanPham,
            tenSize,
            donGia,
            soLuong,
            (donGia+giaSize)*soLuong,
            ghiChu
        };
    }
    
}
