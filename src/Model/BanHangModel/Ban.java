/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.BanHangModel;

/**
 *
 * @author admin
 */
public class Ban {
    
    private int maBan;
    
    private String tenBan;
    
    private int sucChua;
    
    private String viTri;
    
    private boolean tinhTrang;
    
    private int trangThai;

    public boolean isTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public Ban(int maBan, String tenBan, int sucChua, String viTri, boolean tinhTrang) {
        this.maBan = maBan;
        this.tenBan = tenBan;
        this.sucChua = sucChua;
        this.viTri = viTri;
        this.tinhTrang = tinhTrang;
    }
    
    

    public Ban(int maBan, String tenBan, int sucChua, String viTri, int trangThai) {
        this.maBan = maBan;
        this.tenBan = tenBan;
        this.sucChua = sucChua;
        this.viTri = viTri;
        this.trangThai = trangThai;
    }

    public Ban(String viTri) {
        this.viTri = viTri;
    }

    public Ban(int maBan, int trangThai) {
        this.maBan = maBan;
        this.trangThai = trangThai;
    }
    
    public Ban(String tenBan, int sucChua, String viTri, int trangThai) {
        this.tenBan = tenBan;
        this.sucChua = sucChua;
        this.viTri = viTri;
        this.trangThai = trangThai;
    }
    
    public Ban() {
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public int getSucChua() {
        return sucChua;
    }

    public void setSucChua(int sucChua) {
        this.sucChua = sucChua;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "Ban{" + "maBan=" + maBan + ", tenBan=" + tenBan + ", sucChua=" + sucChua + ", viTri=" + viTri + ", trangThai=" + trangThai + '}';
    }
    
}
