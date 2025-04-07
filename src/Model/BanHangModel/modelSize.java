/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.BanHangModel;

import java.util.Vector;

/**
 *
 * @author Admin
 */
public class modelSize {
    
    private int id;
    
    private String tenSize;
    
    private double gia;
    
    private boolean TrangThai;

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public modelSize(int id, String tenSize, double gia) {
        this.id = id;
        this.tenSize = tenSize;
        this.gia = gia;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public modelSize(String tenSize, double gia) {
        this.tenSize = tenSize;
        this.gia = gia;
    }
    
    public modelSize() {
    }

    public modelSize(String tenSize) {
        this.tenSize = tenSize;
    }

    public String getTenSize() {
        return tenSize;
    }

    public void setTenSize(String tenSize) {
        this.tenSize = tenSize;
    }

    @Override
    public String toString() {
        return "modelSize{" + "id=" + id + ", tenSize=" + tenSize + ", gia=" + gia + '}';
    }

}
