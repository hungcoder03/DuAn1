/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.KhuyenMaiModel;

/**
 *
 * @author admin
 */
public class NhanVienAndKhuyenMai {
    
    private String maNV;
    
    private String hoTen;

    public NhanVienAndKhuyenMai(String maNV, String hoTen) {
        this.maNV = maNV;
        this.hoTen = hoTen;
    }

    public NhanVienAndKhuyenMai(String maNV) {
        this.maNV = maNV;
    }
    

    public NhanVienAndKhuyenMai() {
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    @Override
    public String toString() {
        return "NhanVienAndKhuyenMai{" + "maNV=" + maNV + ", hoTen=" + hoTen + '}';
    }
    
}
