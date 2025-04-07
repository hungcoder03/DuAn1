/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

//import com.sun.jdi.connect.spi.Connection;
import DBConnect.DBConnect;
import Model.SanPhamModel.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class RepoSanPham {

    private byte[] anh2;

    public void anh(byte[] anh) {
        anh2 = anh;
    }

    public List<SanPham> getAll(Boolean tt) {
        List<SanPham> lists = new ArrayList<>();
        String sql = "SELECT maSP, tenSp, giaBan, tinhTrang, hinhAnh\n"
                + "FROM  dbo.sanpham where (tinhTrang = ? or ? is null) ";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            // ResultSet <=> 1 table => du lieu tra ra cua cau lenh SQL 
            // TAT CA CAC CAU QUERY BAT DAU BANG SELECT : executeQuery
            ps.setObject(1, tt);
            ps.setObject(2, tt);
            ResultSet rs = ps.executeQuery();
//            ps.setObject(1,tt);
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getBoolean(4), rs.getBytes(5));
                lists.add(sp);

            }
        } catch (Exception e) {
            e.printStackTrace(System.out);

        }
        return lists;
    }

    public List<SanPham> SeachByName(String name) {
        List<SanPham> lists = new ArrayList<>();

        String sql = "SELECT maSP, tenSp, giaBan, tinhTrang, hinhAnh\n"
                + "FROM  dbo.sanpham WHERE tenSp LIKE";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            // ResultSet <=> 1 table => du lieu tra ra cua cau lenh SQL 
            // TAT CA CAC CAU QUERY BAT DAU BANG SELECT : executeQuery
            ps.setObject(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery(); // thuc thi cau len sql
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getBoolean(4), rs.getBytes(5));
                lists.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            // Xy ly ngoai le 
        }
        return lists;
    }

    public boolean addSp(SanPham sp) {
        int check = 0;

        String sql = "INSERT INTO sanpham(tenSp, giaBan, tinhTrang, hinhAnh) VALUES (?,?,?,?)\n";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {

            ps.setObject(1, sp.getTen());
            ps.setObject(2, sp.getGia());
            ps.setObject(3, sp.isTrangThai());
            ps.setObject(4, sp.getHinhAnh());
            
            
            check = ps.executeUpdate(); // TRA RA SO BAN GHI DUOC ADD THANH CONG
        } catch (Exception e) {
            e.printStackTrace(System.out);
            // Xy ly ngoai le 
        }
        return check > 0; // true 
    }
    
    public boolean addSpct(SanPham sp, int maSp) {
        int check = 0;

        String sql = "INSERT INTO SanPham_Size(idSize, maSP, ngayTao, tinhTrang, gia) VALUES (?,?,?,?,?)\n";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {

            ps.setObject(1, 1);
            ps.setObject(2, maSp);
            ps.setObject(3, new Date());
            ps.setObject(4, true);
            ps.setObject(5, sp.getGia());
            
            
            check = ps.executeUpdate(); // TRA RA SO BAN GHI DUOC ADD THANH CONG
        } catch (Exception e) {
            e.printStackTrace(System.out);
            // Xy ly ngoai le 
        }
        return check > 0; // true 
    }
    
    

    public boolean updateSp(SanPham nsp, int ma){
        int check=0;
        
        String sql = "UPDATE sanpham SET tenSp =?, giaBan=?, tinhTrang=?, hinhAnh=? WHERE maSP = ?";
        String sqlSpct = "UPDATE SanPham_Size SET tinhTrang=? WHERE maSP = ?";
      try (Connection con = DBConnect.getConnection()) {
        // Tắt tự động commit để sử dụng transaction
        con.setAutoCommit(false);

        try (PreparedStatement psSanPham = con.prepareStatement(sql);
             PreparedStatement psSpct = con.prepareStatement(sqlSpct)) {

            // Câu lệnh cập nhật bảng sanpham
            psSanPham.setObject(1, nsp.getTen());
            psSanPham.setObject(2, nsp.getGia());
            psSanPham.setObject(3, nsp.isTrangThai());
            if (anh2 != null) {
                psSanPham.setBytes(4, anh2);
            } else {
                psSanPham.setNull(4, java.sql.Types.BLOB);
            }
            psSanPham.setObject(5, ma);
            check += psSanPham.executeUpdate();

            // Câu lệnh cập nhật bảng spct
            psSpct.setObject(1, nsp.isTrangThai());
            psSpct.setObject(2, ma);
            check += psSpct.executeUpdate();

            // Commit transaction nếu cả hai bảng được cập nhật thành công
            con.commit();
        } catch (Exception e) {
            // Rollback nếu có lỗi
            con.rollback();
            e.printStackTrace(System.out);
        } finally {
            // Khôi phục lại trạng thái tự động commit
            con.setAutoCommit(true);
        }
    } catch (Exception e) {
        e.printStackTrace(System.out);
    }

    return check > 1; // Trả về true nếu cả hai bảng đều được cập nhật
    }

    public boolean deleteSp(int ma) {
        int check = 0;

        String sql = "DELETE FROM sanpham  WHERE maSP = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {

            ps.setObject(1, ma);
            check = ps.executeUpdate(); // TRA RA SO BAN GHI DUOC ADD THANH CONG
        } catch (Exception e) {
            e.printStackTrace(System.out);
            // Xy ly ngoai le 
        }
        return check > 0; // true 
    }
}
