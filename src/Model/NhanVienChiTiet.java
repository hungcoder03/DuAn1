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
public class NhanVienChiTiet {
    private String manv;
    private String tennv;
    private Boolean gioitinh;
    private Date ngaySinh;
    private String sdt;
    private String email;
    private String diachi;
    private Date ngayTao;
    private Date ngaySua;
    private String tenDangNhap;
    private String matkhau;
    private String chucvu;
    private Boolean tinhtrang;

    public NhanVienChiTiet(String manv, String tennv, boolean gioitinh, Date ngaySinh, String sdt, String email, String diachi, Date ngayTao, Date ngaySua, String tenDangNhap, String matkhau, String chucvu, boolean tinhtrang) {
        this.manv = manv;
        this.tennv = tennv;
        this.gioitinh = gioitinh;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.email = email;
        this.diachi = diachi;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.tenDangNhap = tenDangNhap;
        this.matkhau = matkhau;
        this.chucvu = chucvu;
        this.tinhtrang = tinhtrang;
    }
    
    public NhanVienChiTiet(String manv, String tennv, boolean gioitinh, Date ngaySinh, String sdt, String email, String diachi, String tenDangNhap, String matkhau, String chucvu, boolean tinhtrang) {
        this.manv = manv;
        this.tennv = tennv;
        this.gioitinh = gioitinh;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.email = email;
        this.diachi = diachi;
        this.tenDangNhap = tenDangNhap;
        this.matkhau = matkhau;
        this.chucvu = chucvu;
        this.tinhtrang = tinhtrang;
    }

    public NhanVienChiTiet() {
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
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

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public boolean isTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(boolean tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    @Override
    public String toString() {
        return "NhanVienChiTiet{" + "manv=" + manv + ", tennv=" + tennv + ", gioitinh=" + gioitinh + ", ngaySinh=" + ngaySinh + ", sdt=" + sdt + ", email=" + email + ", diachi=" + diachi + ", ngayTao=" + ngayTao + ", ngaySua=" + ngaySua + ", tenDangNhap=" + tenDangNhap + ", matkhau=" + matkhau + ", chucvu=" + chucvu + ", tinhtrang=" + tinhtrang + '}';
    }
    
}
