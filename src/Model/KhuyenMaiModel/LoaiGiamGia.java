/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.KhuyenMaiModel;

/**
 *
 * @author admin
 */
public class LoaiGiamGia {
    
    private int id;
    
    private String loaiGiamGia;
    
    private float dieuKien;
    
    private double mucGiam;
    
    private double mucGiamToiDa;
    
    private boolean trangThai;

    public LoaiGiamGia() {
    }

    public LoaiGiamGia(int id, String loaiGiamGia) {
        this.id = id;
        this.loaiGiamGia = loaiGiamGia;
    }

    public LoaiGiamGia(int id) {
        this.id = id;
    }


    public LoaiGiamGia(int id, String loaiGiamGia, float dieuKien, double mucGiam, double mucGiamToiDa, boolean trangThai) {
        this.id = id;
        this.loaiGiamGia = loaiGiamGia;
        this.dieuKien = dieuKien;
        this.mucGiam = mucGiam;
        this.mucGiamToiDa = mucGiamToiDa;
        this.trangThai = trangThai;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getMucGiam() {
        return mucGiam;
    }

    public void setMucGiam(double mucGiam) {
        this.mucGiam = mucGiam;
    }

    public double getMucGiamToiDa() {
        return mucGiamToiDa;
    }

    public void setMucGiamToiDa(double mucGiamToiDa) {
        this.mucGiamToiDa = mucGiamToiDa;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
