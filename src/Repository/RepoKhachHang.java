/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.KhachHangModel.KhachHang;
import java.sql.*;
import java.util.ArrayList;

public class RepoKhachHang {

    private Connection con = null;
    private PreparedStatement pr = null;
    private ResultSet rs = null;

    public ArrayList<KhachHang> getAllKhachHang(Boolean tt, Boolean gt) {
        ArrayList<KhachHang> list = new ArrayList<>();
        String sql = "SELECT maKH, hoTen, gioiTinh, namSinh, SDT, email, diaChi, ngayTao, ngaySua, tinhTrang \n"
                + "FROM KhachHang \n"
                + "WHERE (tinhTrang = ? OR ? IS NULL) \n"
                + "  AND (gioiTinh = ? OR ? IS NULL)";
        try {
            con = DBConnect.DBConnect.getConnection();
            pr = con.prepareStatement(sql);

            pr.setObject(1, tt);
            pr.setObject(2, tt);
            pr.setObject(3, gt);
            pr.setObject(4, gt);

            rs = pr.executeQuery();
            while (rs.next()) {
                String maKH;
                String hoTen, SDT, mail, diaChi;
                Date ngayTao, NgaySua;
                int namSinh;
                boolean gioiTinh, tinhTrang;
                maKH = rs.getString(1);
                hoTen = rs.getString(2);
                gioiTinh = rs.getBoolean(3);
                namSinh = rs.getInt(4);
                SDT = rs.getString(5);
                mail = rs.getString(6);
                diaChi = rs.getString(7);
                ngayTao = rs.getDate(8);
                NgaySua = rs.getDate(9);
                tinhTrang = rs.getBoolean(10);
                KhachHang ml = new KhachHang(maKH, hoTen, gioiTinh, namSinh, SDT, mail, diaChi, ngayTao, NgaySua, tinhTrang);
                list.add(ml);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<KhachHang> getAllKhachHangBySDT(Boolean tt, String sdt) {
        ArrayList<KhachHang> list = new ArrayList<>();
        String sql = "SELECT maKH, hoTen, gioiTinh, namSinh, SDT, email, diaChi, ngayTao, ngaySua, tinhTrang \n"
                + "FROM KhachHang \n"
                + "WHERE tinhTrang = ? AND SDT LIKE ? \n";
        try {
            con = DBConnect.DBConnect.getConnection();
            pr = con.prepareStatement(sql);

            pr.setObject(1, tt);
            pr.setObject(2, "%" + sdt + "%");

            rs = pr.executeQuery();
            while (rs.next()) {
                String maKH;
                String hoTen, SDT, mail, diaChi;
                Date ngayTao, NgaySua;
                int namSinh;
                boolean gioiTinh, tinhTrang;
                maKH = rs.getString(1);
                hoTen = rs.getString(2);
                gioiTinh = rs.getBoolean(3);
                namSinh = rs.getInt(4);
                SDT = rs.getString(5);
                mail = rs.getString(6);
                diaChi = rs.getString(7);
                ngayTao = rs.getDate(8);
                NgaySua = rs.getDate(9);
                tinhTrang = rs.getBoolean(10);
                KhachHang ml = new KhachHang(maKH, hoTen, gioiTinh, namSinh, SDT, mail, diaChi, ngayTao, NgaySua, tinhTrang);
                list.add(ml);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public KhachHang getKhachHangByMakh(String makh) {
        KhachHang kh = new KhachHang();
        String sql = "SELECT maKH, hoTen, gioiTinh, namSinh, SDT, email, diaChi, ngayTao, ngaySua, tinhTrang \n"
                + "FROM KhachHang \n"
                + "WHERE maKH = ?\n";
        try {
            con = DBConnect.DBConnect.getConnection();
            pr = con.prepareStatement(sql);

            pr.setObject(1, makh);

            rs = pr.executeQuery();
            while (rs.next()) {
                String maKH;
                String hoTen, SDT, mail, diaChi;
                Date ngayTao, NgaySua;
                int namSinh;
                boolean gioiTinh, tinhTrang;
                maKH = rs.getString(1);
                hoTen = rs.getString(2);
                gioiTinh = rs.getBoolean(3);
                namSinh = rs.getInt(4);
                SDT = rs.getString(5);
                mail = rs.getString(6);
                diaChi = rs.getString(7);
                ngayTao = rs.getDate(8);
                NgaySua = rs.getDate(9);
                tinhTrang = rs.getBoolean(10);
                kh = new KhachHang(maKH, hoTen, gioiTinh, namSinh, SDT, mail, diaChi, ngayTao, NgaySua, tinhTrang);
            }
            return kh;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public KhachHang getKhachHangBySDT(Boolean tt, String sdt) {
        KhachHang kh = new KhachHang();
        String sql = "SELECT maKH, hoTen, gioiTinh, namSinh, SDT, email, diaChi, ngayTao, ngaySua, tinhTrang \n"
                + "FROM KhachHang \n"
                + "WHERE tinhTrang = ? AND SDT LIKE ? \n";
        try {
            con = DBConnect.DBConnect.getConnection();
            pr = con.prepareStatement(sql);

            pr.setObject(1, tt);
            pr.setObject(2, "%" + sdt + "%");

            rs = pr.executeQuery();
            while (rs.next()) {
                String maKH;
                String hoTen, SDT, mail, diaChi;
                Date ngayTao, NgaySua;
                int namSinh;
                boolean gioiTinh, tinhTrang;
                maKH = rs.getString(1);
                hoTen = rs.getString(2);
                gioiTinh = rs.getBoolean(3);
                namSinh = rs.getInt(4);
                SDT = rs.getString(5);
                mail = rs.getString(6);
                diaChi = rs.getString(7);
                ngayTao = rs.getDate(8);
                NgaySua = rs.getDate(9);
                tinhTrang = rs.getBoolean(10);
                kh = new KhachHang(maKH, hoTen, gioiTinh, namSinh, SDT, mail, diaChi, ngayTao, NgaySua, tinhTrang);
            }
            return kh;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<KhachHang> getAllKhachHangByEmail(Boolean tt, String email) {
        ArrayList<KhachHang> list = new ArrayList<>();
        String sql = "SELECT maKH, hoTen, gioiTinh, namSinh, SDT, email, diaChi, ngayTao, ngaySua, tinhTrang \n"
                + "FROM KhachHang \n"
                + "WHERE tinhTrang = ? AND email LIKE ? \n";
        try {
            con = DBConnect.DBConnect.getConnection();
            pr = con.prepareStatement(sql);

            pr.setObject(1, tt);
            pr.setObject(2, "%" + email + "%");

            rs = pr.executeQuery();
            while (rs.next()) {
                String maKH;
                String hoTen, SDT, mail, diaChi;
                Date ngayTao, NgaySua;
                int namSinh;
                boolean gioiTinh, tinhTrang;
                maKH = rs.getString(1);
                hoTen = rs.getString(2);
                gioiTinh = rs.getBoolean(3);
                namSinh = rs.getInt(4);
                SDT = rs.getString(5);
                mail = rs.getString(6);
                diaChi = rs.getString(7);
                ngayTao = rs.getDate(8);
                NgaySua = rs.getDate(9);
                tinhTrang = rs.getBoolean(10);
                KhachHang ml = new KhachHang(maKH, hoTen, gioiTinh, namSinh, SDT, mail, diaChi, ngayTao, NgaySua, tinhTrang);
                list.add(ml);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public KhachHang getKhachHangByEmail(Boolean tt, String email) {
        KhachHang kh = new KhachHang();
        String sql = "SELECT maKH, hoTen, gioiTinh, namSinh, SDT, email, diaChi, ngayTao, ngaySua, tinhTrang \n"
                + "FROM KhachHang \n"
                + "WHERE tinhTrang = ? AND Email LIKE ? \n";
        try {
            con = DBConnect.DBConnect.getConnection();
            pr = con.prepareStatement(sql);

            pr.setObject(1, tt);
            pr.setObject(2, "%" + email + "%");

            rs = pr.executeQuery();
            while (rs.next()) {
                String maKH;
                String hoTen, SDT, mail, diaChi;
                Date ngayTao, NgaySua;
                int namSinh;
                boolean gioiTinh, tinhTrang;
                maKH = rs.getString(1);
                hoTen = rs.getString(2);
                gioiTinh = rs.getBoolean(3);
                namSinh = rs.getInt(4);
                SDT = rs.getString(5);
                mail = rs.getString(6);
                diaChi = rs.getString(7);
                ngayTao = rs.getDate(8);
                NgaySua = rs.getDate(9);
                tinhTrang = rs.getBoolean(10);
                kh = new KhachHang(maKH, hoTen, gioiTinh, namSinh, SDT, mail, diaChi, ngayTao, NgaySua, tinhTrang);
            }
            return kh;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<KhachHang> timSDT(String sdtCantim, String maKh) {
        ArrayList<KhachHang> list = new ArrayList<>();
        String sql = "select maKH,hoTen,gioiTinh,namSinh,SDT,email,diaChi,ngayTao,ngaySua,tinhTrang \n"
                + "from KhachHang\n"
                + " where SDT like ? and maKh like ?";
        try {
            con = DBConnect.DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            pr.setObject(1, "%" + sdtCantim + "%");
            pr.setObject(2, "%" + maKh + "%");
            rs = pr.executeQuery();
            while (rs.next()) {
                String maKH;
                String hoTen, SDT, mail, diaChi;
                Date ngayTao, NgaySua;
                int namSinh;
                boolean gt, tinhTrang;
                maKH = rs.getString(1);
                hoTen = rs.getString(2);
                gt = rs.getBoolean(3);
                namSinh = rs.getInt(4);
                SDT = rs.getString(5);
                mail = rs.getString(6);
                diaChi = rs.getString(7);
                ngayTao = rs.getDate(8);
                NgaySua = rs.getDate(9);
                tinhTrang = rs.getBoolean(10);
                KhachHang ml = new KhachHang(maKH, hoTen, gt, namSinh, SDT, mail, diaChi, ngayTao, NgaySua, tinhTrang);
                list.add(ml);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    

    public int themKhachHang(KhachHang ml) {
        String sql = "INSERT INTO KhachHang (maKH, hoTen, gioiTinh, namSinh, SDT,email, diaChi, ngayTao, tinhTrang)\n"
                + "VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            con = DBConnect.DBConnect.getConnection();
            pr = con.prepareStatement(sql);

            pr.setObject(1, ml.getMakh());
            pr.setObject(2, ml.getHoTen());
            pr.setObject(3, ml.isGt());
            pr.setObject(4, ml.getNamSinh());
            pr.setObject(5, ml.getSoDT());
            pr.setObject(6, ml.getMail());
            pr.setObject(7, ml.getDiaChi());
            pr.setObject(8, ml.getNgayTao());
            pr.setObject(9, ml.isTinhTrang());
            return pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int suaThongTinKH(String maKH, KhachHang ml) {
        String sql = "update KhachHang\n"
                + "set hoTen=?,gioiTinh=?,namSinh=?,SDT=?,email=?,diaChi=?,ngaySua=?,tinhTrang=?\n"
                + "where maKH=?";
        try {
            con = DBConnect.DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            pr.setObject(1, ml.getHoTen());
            pr.setObject(2, ml.isGt());
            pr.setObject(3, ml.getNamSinh());
            pr.setObject(4, ml.getSoDT());
            pr.setObject(5, ml.getMail());
            pr.setObject(6, ml.getDiaChi());
            pr.setTimestamp(7, new java.sql.Timestamp(System.currentTimeMillis()));
            pr.setObject(8, ml.isTinhTrang());
            pr.setObject(9, maKH);
            return pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int xoaKH(String maKh) {
        String sql = "delete from KhachHang\n"
                + "where maKH=?";
        try {
            con = DBConnect.DBConnect.getConnection();
            pr = con.prepareStatement(sql);

            pr.setObject(1, maKh);

            return pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
