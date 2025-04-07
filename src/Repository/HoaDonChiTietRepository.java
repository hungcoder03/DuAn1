/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DBConnect.DBConnect;
import Model.HoaDonModel.HoaDon;
import Model.HoaDonModel.HoaDonChiTiet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietRepository {

    public ArrayList<HoaDonChiTiet> getAll(String maHoaDon) {
        String sql = """
                    SELECT 
                        dbo.HoaDonChiTiet.maHoaDon, 
                        dbo.HoaDonChiTiet.maSp, 
                        dbo.HoaDonChiTiet.tenSp, 
                        MAX(Size.tenSize) AS tenSize, 
                        MAX(dbo.SanPham_Size.gia) AS gia, 
                        dbo.HoaDonChiTiet.soLuong, 
                        dbo.HoaDonChiTiet.ghiChu
                    FROM dbo.HoaDonChiTiet
                    JOIN dbo.SanPham_Size 
                        ON dbo.HoaDonChiTiet.maSp = dbo.SanPham_Size.maSP
                    JOIN Size 
                        ON Size.id = SanPham_Size.idSize
                    
                    GROUP BY dbo.HoaDonChiTiet.maHoaDon, 
                             dbo.HoaDonChiTiet.maSp, 
                             dbo.HoaDonChiTiet.tenSp, 
                             dbo.HoaDonChiTiet.soLuong, 
                             dbo.HoaDonChiTiet.ghiChu
                    HAVING dbo.HoaDonChiTiet.maHoaDon = ?
                 """;
        ArrayList<HoaDonChiTiet> al = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maHoaDon);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setMaHoaDon(rs.getString(1));
                hdct.setMaSanPham(rs.getInt(2));

                hdct.setTenSanPham(rs.getString(3));
                hdct.setTenSize(rs.getString(4));
                hdct.setDonGia(rs.getFloat(5));
                hdct.setSoLuong(rs.getInt(6));
                hdct.setGhiChu(rs.getString(7));

                al.add(hdct);

            }
//            System.out.println(al);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return al;
    }

//    public static void main(String[] args) {
//        System.out.println(new HoaDonChiTietRepository().getAll("HD110"));
//    }
}
