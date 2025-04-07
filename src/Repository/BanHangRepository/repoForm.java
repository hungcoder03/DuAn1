/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.BanHangRepository;

import DBConnect.DBConnect;
import Model.modelForm;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class repoForm {
    
    public ArrayList<modelForm> getAllKhachHang() {
        ArrayList<modelForm> list = new ArrayList<>();
        String sql = "SELECT        tenDangNhap AS Expr3, matKhau\n" +
"FROM              dbo.TaiKhoan";
        try (Connection con = (Connection) DBConnect.getConnection();
                PreparedStatement ps = con.prepareCall(sql)) {
            // ResultSet <=> 1 table => du lieu tra ra cua cau lenh SQL 
            // TAT CA CAC CAU QUERY BAT DAU BANG SELECT : executeQuery
            ResultSet rs = ps.executeQuery(); // thuc thi cau len sql
            while (rs.next()) {
                modelForm form = new modelForm(rs.getString(1), rs.getString(2));
               list.add(form);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            // Xy ly ngoai le 
        }
        return list;
    }
    
}
