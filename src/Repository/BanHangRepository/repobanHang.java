/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.BanHangRepository;

import DBConnect.DBConnect;
import Model.SanPhamModel.SanPham;
import Model.BanHangModel.SanPham_BanHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class repobanHang {

    public List<SanPham_BanHang> getAll() {
        List<SanPham_BanHang> lists = new ArrayList<>();
        String sql = "SELECT maSP, tenSp, giaBan, tinhTrang\n"
                + "FROM  dbo.sanpham";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            // ResultSet <=> 1 table => du lieu tra ra cua cau lenh SQL 
            // TAT CA CAC CAU QUERY BAT DAU BANG SELECT : executeQuery

            ResultSet rs = ps.executeQuery();
//            ps.setObject(1,tt);
            while (rs.next()) {
                SanPham_BanHang sp = new SanPham_BanHang(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getBoolean(4));
                lists.add(sp);

            }
        } catch (Exception e) {
            e.printStackTrace(System.out);

        }
        return lists;
    }
    
    public SanPham_BanHang getSanPham(int maSP) {
        String sql = "SELECT maSP, tenSp, giaBan, tinhTrang\n"
                + "FROM sanpham\n"
                + "WHERE maSP = ?";

        SanPham_BanHang sp = new SanPham_BanHang();
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setObject(1,maSP);
            
            ResultSet rs = ps.executeQuery();
            
            
            
            while (rs.next()) {
                sp = new SanPham_BanHang(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getBoolean(4));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);

        }
        return sp;
    }

    public List<SanPham_BanHang> SeachByName(String name) {
        List<SanPham_BanHang> lists = new ArrayList<>();

        String sql = "SELECT maSP, tenSp, giaBan, tinhTrang\n"
                + "FROM  dbo.sanpham WHERE tenSp LIKE";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            // ResultSet <=> 1 table => du lieu tra ra cua cau lenh SQL 
            // TAT CA CAC CAU QUERY BAT DAU BANG SELECT : executeQuery
            ps.setObject(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery(); // thuc thi cau len sql
            while (rs.next()) {
                SanPham_BanHang sp = new SanPham_BanHang(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getBoolean(4));
                lists.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            // Xy ly ngoai le 
        }
        return lists;
    }
    
    

}
