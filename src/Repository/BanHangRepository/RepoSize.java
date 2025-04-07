/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.BanHangRepository;

import DBConnect.DBConnect;
import Model.BanHangModel.SanPham_BanHang;
import Model.BanHangModel.modelSize;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class RepoSize {
    
    public int updateSizeStatus(modelSize size, int maSP, Boolean tt) {
    String sql = "UPDATE SanPham_Size SET tinhTrang = ? WHERE maSP = ? AND idSize = ?";
    try (Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareCall(sql)) {
        ps.setBoolean(1, tt);
        ps.setInt(2, maSP);
        ps.setInt(3, size.getId());
        System.out.println("Mã: " +maSP + " IdSize: " +size.getId() + " Tình trạng: " +tt);
        return ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
}


    public ArrayList<modelSize> getAllSizeOnSpctByMasp(int maSp) {
        ArrayList<modelSize> lists = new ArrayList<>();
        String sql = "SELECT spS.idSize, s.tenSize, s.gia\n"
                + "FROM SanPham_Size spS\n"
                + "JOIN Size s on s.id = spS.idSize\n"
                + "WHERE spS.maSp = ? and spS.tinhTrang = 1";

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareCall(sql)) {
            // ResultSet <=> 1 table => du lieu tra ra cua cau lenh SQL 
            // TAT CA CAC CAU QUERY BAT DAU BANG SELECT : executeQuery

            ps.setObject(1, maSp);

            ResultSet rs = ps.executeQuery();
//            ps.setObject(1,tt);
            while (rs.next()) {
                modelSize sp = new modelSize(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3)
                );
                lists.add(sp);

            }
        } catch (Exception e) {
            e.printStackTrace(System.out);

        }
        return lists;

    }
    
    public ArrayList<modelSize> getAllSizeOnSpctByMasp1(int maSp) {
        ArrayList<modelSize> lists = new ArrayList<>();
        String sql = "SELECT spS.idSize, s.tenSize, s.gia\n"
                + "FROM SanPham_Size spS\n"
                + "JOIN Size s on s.id = spS.idSize\n"
                + "WHERE spS.maSp = ?";

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareCall(sql)) {
            // ResultSet <=> 1 table => du lieu tra ra cua cau lenh SQL 
            // TAT CA CAC CAU QUERY BAT DAU BANG SELECT : executeQuery

            ps.setObject(1, maSp);

            ResultSet rs = ps.executeQuery();
//            ps.setObject(1,tt);
            while (rs.next()) {
                modelSize sp = new modelSize(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3)
                );
                lists.add(sp);

            }
        } catch (Exception e) {
            e.printStackTrace(System.out);

        }
        return lists;

    }
    
    

    public ArrayList<modelSize> getAllSize() {
        ArrayList<modelSize> lists = new ArrayList<>();
        String sql = "SELECT id,tenSize, gia\n"
                + "FROM Size\n";
        
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareCall(sql)) {
            // ResultSet <=> 1 table => du lieu tra ra cua cau lenh SQL 
            // TAT CA CAC CAU QUERY BAT DAU BANG SELECT : executeQuery

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                modelSize sp = new modelSize(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3)
                );
                lists.add(sp);

            }
            System.out.println("List Size:" +lists.toString());
        } catch (Exception e) {
            e.printStackTrace(System.out);

        }
        return lists;

    }

    public modelSize getIdByTenSize(String text) {
        String sql = """
                     Select id, tenSize, gia
                     from size
                     where tenSize = ?
                     """;
        modelSize size = new modelSize();
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareCall(sql)) {

            ps.setObject(1, text);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                size.setId(rs.getInt(1));
                size.setTenSize(rs.getString(2));
                size.setGia(rs.getInt(3));
            }

            return size;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    public List<modelSize> getAll() {
        List<modelSize> lists = new ArrayList<>();
        String sql = "SELECT tenSize\n"
                + "FROM dbo.Size";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            // ResultSet <=> 1 table => du lieu tra ra cua cau lenh SQL 
            // TAT CA CAC CAU QUERY BAT DAU BANG SELECT : executeQuery

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                modelSize sp = new modelSize(rs.getString(1));
                lists.add(sp);

            }
        } catch (Exception e) {
            e.printStackTrace(System.out);

        }
        return lists;
    }
    
    public modelSize getGiaSizeByName(String tenSize) {
        String sql = "SELECT id, tenSize, gia\n"
                + "FROM  dbo.Size\n"
                + "WHERE tenSize = ?";
        modelSize sp = new modelSize();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            // ResultSet <=> 1 table => du lieu tra ra cua cau lenh SQL 
            // TAT CA CAC CAU QUERY BAT DAU BANG SELECT : executeQuery
            ps.setObject(1, tenSize);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sp = new modelSize(rs.getInt(1),rs.getString(2), rs.getDouble(3));
            }
            
        } catch (Exception e) {
            e.printStackTrace(System.out);

        }
        return sp;
    }
    
    public modelSize getGiaSizeById(int idSize) {
        String sql = "SELECT id, tenSize, gia\n"
                + "FROM  dbo.Size\n"
                + "WHERE id = ?";
        modelSize sp = new modelSize();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            // ResultSet <=> 1 table => du lieu tra ra cua cau lenh SQL 
            // TAT CA CAC CAU QUERY BAT DAU BANG SELECT : executeQuery
            ps.setObject(1, idSize);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sp = new modelSize(rs.getInt(1),rs.getString(2), rs.getDouble(3));
            }
            
        } catch (Exception e) {
            e.printStackTrace(System.out);

        }
        return sp;
    }
}
