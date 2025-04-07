/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DBConnect.DBConnect;
import Model.ThongKeModel.ThongKe;
import Model.ThongKeModel.ThongKeSPTop3;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ThongKeSPBanChay {

    public ArrayList<ThongKeSPTop3> getAllSPBanChay() {
        String sql = """
                    SELECT 
                          sp.tenSp, 
                          SUM(hdct.soLuong) AS tongSoLuong, 
                          SUM(hdct.soLuong * hdct.donGia) AS tongTien
                      FROM HoaDonChiTiet hdct
                      join SanPham_Size spS on spS.maSp = hdct.maSP
                      Right join SanPham sp on spS.maSp = sp.maSp
                      GROUP BY sp.tenSp
                      ORDER BY tongSoLuong DESC;
                 """;
        ArrayList<ThongKeSPTop3> listt = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ThongKeSPTop3 tkk = new ThongKeSPTop3();
                tkk.setTenSanPham(rs.getString(1));
                tkk.setTongTienThanhToan(rs.getFloat(3));
                tkk.setSoLuong(rs.getInt(2));

                listt.add(tkk);

            }
            System.out.println(listt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listt;
    }
}
