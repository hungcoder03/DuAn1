/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.BanHangRepository;

import Model.BanHangModel.Ban;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BanRepo {
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public ArrayList<Ban> getAllBan() {
        String sql = "SELECT maBan, tenBan, sucChua, viTri, trangThai\n"
                + "FROM Ban\n"
                + "WHERE tinhTrang = 1\n"
                + "ORDER BY tenBan ASC\n"
                + "";
        
        ArrayList<Ban> list = new ArrayList<>();
        
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Ban ban = new Ban(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getInt(5)
                );
                list.add(ban);
            }
            
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Ban> getAllViTriBan() {
        String sql = "SELECT DISTINCT viTri\n"
                + "FROM Ban";
        
        ArrayList<Ban> list = new ArrayList<>();
        
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Ban ban = new Ban(
                        rs.getString(1)
                );
                list.add(ban);
            }
            
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int insertBan(Ban ban) {
        String sql = "INSERT INTO Ban (tenBan, sucChua, viTri, tinhTrang, trangThai)\n"
                + "VALUES (?,?,?,1,0)";
        
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setObject(1, ban.getTenBan());
            ps.setObject(2, ban.getSucChua());
            ps.setObject(3, ban.getViTri());
//            ps.setObject(4, ban.getTrangThai());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int update(Ban ban, int maBan) {
        String sql = "UPDATE Ban\n"
                + "SET tenBan = ?, sucChua = ?, viTri = ?, tinhTrang = ?\n"
                + "WHERE maBan = ?";
        
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setObject(1, ban.getTenBan());
            ps.setObject(2, ban.getSucChua());
            ps.setObject(3, ban.getViTri());
            ps.setObject(4, ban.isTinhTrang());
            ps.setObject(5, maBan);
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public Ban getBanByName(String tenBan) {
        String sql = "SELECT maBan, trangThai\n"
                + "FROM Ban\n"
                + "WHERE tenBan = ?";
        
        Ban ban = new Ban();
        
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setObject(1, tenBan);
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                ban = new Ban(
                        rs.getInt(1),
                        rs.getInt(2)
                );
            }
            
            return ban;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Ban getBanByMa(int maBan) {
        String sql = "SELECT maBan, tenBan, sucChua, viTri, tinhTrang\n"
                + "FROM Ban\n"
                + "WHERE maBan = ?";
        
        Ban ban = new Ban();
        
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setObject(1, maBan);
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                ban = new Ban(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getBoolean(5)
                );
            }
            
            return ban;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkTonTai(String tenBan) {
        String sql = "SELECT 1\n"
                + "FROM Ban\n"
                + "WHERE tenBan = ?";
        
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setObject(1, tenBan);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
