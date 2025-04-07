/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.KhachHangModel;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class KhachHang {
    private String makh;
    private String hoTen;
    private boolean gt;
    private int namSinh;
    private String soDT;
    private String mail;
    private String diaChi;
    private Date ngayTao;
    private Date ngaySua;
    private boolean tinhTrang;

    public KhachHang() {
    }

    public KhachHang(String makh, String hoTen, boolean gt, String soDT, String mail, String diaChi, boolean tinhTrang) {
        this.makh = makh;
        this.hoTen = hoTen;
        this.gt = gt;
        this.soDT = soDT;
        this.mail = mail;
        this.diaChi = diaChi;
        this.tinhTrang = tinhTrang;
    }

    @Override
    public String toString() {
        return "KhachHang{" + "makh=" + makh + ", hoTen=" + hoTen + ", gt=" + gt + ", soDT=" + soDT + ", mail=" + mail + ", diaChi=" + diaChi + '}';
    }
    
    
    
    public KhachHang(String makh, String hoTen, boolean gt, String soDT, String diaChi) {
        this.makh = makh;
        this.hoTen = hoTen;
        this.gt = gt;
        this.soDT = soDT;
        this.diaChi = diaChi;
    }

    public KhachHang(String makh, String hoTen, boolean gt, int namSinh, String soDT, String mail, String diaChi, Date ngayTao, boolean tinhTrang) {
        this.makh = makh;
        this.hoTen = hoTen;
        this.gt = gt;
        this.namSinh = namSinh;
        this.soDT = soDT;
        this.mail = mail;
        this.diaChi = diaChi;
        this.ngayTao = ngayTao;
        this.tinhTrang = tinhTrang;
    }
    
    public KhachHang(String hoTen, boolean gt, int namSinh, String soDT, String mail, String diaChi, Date ngayTao, Date ngaySua, boolean tinhTrang) {
        this.hoTen = hoTen;
        this.gt = gt;
        this.namSinh = namSinh;
        this.soDT = soDT;
        this.mail = mail;
        this.diaChi = diaChi;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.tinhTrang = tinhTrang;
    }
    

    public KhachHang(String makh, String hoTen, boolean gt, int namSinh, String soDT, String mail, String diaChi, Date ngayTao, Date ngaySua, boolean tinhTrang) {
        this.makh = makh;
        this.hoTen = hoTen;
        this.gt = gt;
        this.namSinh = namSinh;
        this.soDT = soDT;
        this.mail = mail;
        this.diaChi = diaChi;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.tinhTrang = tinhTrang;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public boolean isGt() {
        return gt;
    }

    public void setGt(boolean gt) {
        this.gt = gt;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
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

    public boolean isTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
    

   
    public Object[] todatarow(){
        return new Object[]{
            this.getMakh(),
            this.getHoTen(),
            this.isGt()?"Nam":"Nữ",
            this.getNamSinh(),
            this.getSoDT(),
            this.getMail(),
            this.getDiaChi(),
            this.getNgayTao(),
            this.getNgaySua(),
            this.isTinhTrang()?"Đang hoạt động":"Ngừng hoạt động",
        };
    }
    
}
