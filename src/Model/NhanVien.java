/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class NhanVien {

    private String manv;
    private String chucVu;
    private String hoten;
    private boolean gioitinh;
    private String ngaysinh;
    private String sdt;
    private String email;
    private String diachi;
    private String ngaytao;
    private String ngaysua;
    private boolean tinhtrang;

    public NhanVien(String manv, String hoten) {
        this.manv = manv;
        this.hoten = hoten;
    }
    
    

    public NhanVien(String manv, String chucVu, String hoten, boolean gioitinh, String ngaysinh, String sdt, String email, String diachi, String ngaytao, String ngaysua, boolean tinhtrang) {
        this.manv = manv;
        this.chucVu = chucVu;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
        this.email = email;
        this.diachi = diachi;
        this.ngaytao = ngaytao;
        this.ngaysua = ngaysua;
        this.tinhtrang = tinhtrang;
    }

    
    
    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    
    
    public NhanVien() {
    }

    public NhanVien(String manv, String hoten, boolean gioitinh, String ngaysinh, String sdt, String email, String diachi, String ngaytao, String ngaysua, boolean tinhtrang) {
        this.manv = manv;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
        this.email = email;
        this.diachi = diachi;
        this.ngaytao = ngaytao;
        this.ngaysua = ngaysua;
        this.tinhtrang = tinhtrang;
    }

    public NhanVien(String hoten, boolean gioitinh, String ngaysinh, String sdt, String email, String diachi, String ngaytao, String ngaysua, boolean tinhtrang) {
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
        this.email = email;
        this.diachi = diachi;
        this.ngaytao = ngaytao;
        this.ngaysua = ngaysua;
        this.tinhtrang = tinhtrang;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(String ngaytao) {
        this.ngaytao = ngaytao;
    }

    public String getNgaysua() {
        return ngaysua;
    }

    public void setNgaysua(String ngaysua) {
        this.ngaysua = ngaysua;
    }

    public boolean isTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(boolean tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public Object[] toDataRow() {
        return new Object[]{this.manv, this.chucVu, this.hoten, this.isGioitinh() ? "Nam" : "Nữ", this.ngaysinh, this.sdt, this.email, this.diachi, this.ngaytao, this.ngaysua, this.isTinhtrang() ? "Làm việc" : "Nghỉ việc"};
    }

    @Override
    public String toString() {
        return "NhanVien{" + "manv=" + manv + ", hoten=" + hoten + ", gioitinh=" + gioitinh + ", ngaysinh=" + ngaysinh + ", sdt=" + sdt + ", email=" + email + ", diachi=" + diachi + ", ngaytao=" + ngaytao + ", ngaysua=" + ngaysua + ", tinhtrang=" + tinhtrang + '}';
    }
    
    

}
