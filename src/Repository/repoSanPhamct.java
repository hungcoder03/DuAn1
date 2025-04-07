/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DBConnect.DBConnect;
import Model.SanPhamModel.SanPham;
import Model.SanPhamModel.SanPhamChiTiet;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class repoSanPhamct {

    public List<SanPhamChiTiet> getAllSpctByMa(Integer ma) {
        List<SanPhamChiTiet> lists = new ArrayList<>();
        String sql = "SELECT dbo.SanPham_Size.maSP, dbo.sanpham.tenSp, dbo.Size.tenSize, dbo.sanpham.giaBan , \n"
                + "dbo.Size.gia, dbo.SanPham_Size.gia, dbo.SanPham_Size.tinhTrang\n"
                + "FROM  dbo.sanpham INNER JOIN\n"
                + "         dbo.SanPham_Size ON dbo.sanpham.maSP = dbo.SanPham_Size.maSP INNER JOIN\n"
                + "         dbo.Size ON dbo.SanPham_Size.idSize = dbo.Size.id\n"
                + "where dbo.sanpham.maSP = ?";

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareCall(sql)) {
            // ResultSet <=> 1 table => du lieu tra ra cua cau lenh SQL 
            // TAT CA CAC CAU QUERY BAT DAU BANG SELECT : executeQuery
            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery();
//            ps.setObject(1,tt);
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getBoolean(7));
                lists.add(spct);

            }

        } catch (Exception e) {
            e.printStackTrace(System.out);

        }
        return lists;
    }

    public SanPham getSanPhamByMa(int ma) {
        SanPham sp = new SanPham();
        String sql = "SELECT dbo.sanpham.maSP, dbo.sanpham.tenSp, dbo.sanpham.giaBan, dbo.sanpham.tinhTrang\n"
                + "FROM  dbo.sanpham \n"
                + "where dbo.sanpham.maSP = ? ";

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareCall(sql)) {
            // ResultSet <=> 1 table => du lieu tra ra cua cau lenh SQL 
            // TAT CA CAC CAU QUERY BAT DAU BANG SELECT : executeQuery
            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery();
//            ps.setObject(1,tt);
            while (rs.next()) {
                sp.setMa(rs.getInt(1));
                sp.setTen(rs.getString(2));
                sp.setGia(rs.getDouble(3));
                sp.setTrangThai(rs.getBoolean(4));
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);

        }
        return sp;
    }

    public List<SanPhamChiTiet> getAll1(Boolean tt) {
        List<SanPhamChiTiet> lists = new ArrayList<>();
        String sql = """
        SELECT dbo.sanpham.maSP, dbo.sanpham.tenSp, dbo.Size.tenSize, dbo.sanpham.giaBan, 
               dbo.Size.gia, dbo.sanpham.giaBan + dbo.Size.gia AS donGia, dbo.Size.tinhTrang
        FROM dbo.sanpham 
        INNER JOIN dbo.SanPham_Size ON dbo.sanpham.idsize = dbo.SanPham_Size.idSize
        CROSS JOIN dbo.Size
        WHERE (dbo.Size.tinhTrang = ? OR ? IS NULL)
    """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1, tt);
            ps.setObject(2, tt);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet(
                        rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getDouble(4), rs.getDouble(5), rs.getDouble(6),
                        rs.getBoolean(7)
                );
                lists.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return lists;
    }

    public int insertSanPhamChiTiet(int maSp, int idSize, Date ngayTao, boolean tinhTrang) {
        String sql = "insert into SanPham_Size(maSP, idSize,ngayTao, tinhTrang) values (?,?,?,?)";
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareCall(sql)) {

            ps.setObject(1, maSp);
            ps.setObject(2, idSize);
            ps.setObject(3, ngayTao);
            ps.setObject(4, tinhTrang);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
    }

    public boolean insertSanPhamChiTiet(int maSp, int idSize, double gia, Date ngayTao, boolean tinhTrang) {
        String query = "INSERT INTO SanPham_Size (idSize, maSP, gia, ngayTao, tinhTrang) VALUES (?, ?, ?, ?, ?)";
        Connection connection = DBConnect.getConnection();
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idSize);
            ps.setInt(2, maSp);
            ps.setDouble(3, gia);
            ps.setObject(4, ngayTao);
            ps.setBoolean(5, tinhTrang);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateTinhTrangSanPhamChiTiet(int maSp, int idSize, double gia, Date ngayTao, boolean tinhTrang) {
    String sql = "UPDATE SanPham_Size SET tinhTrang = ?, ngaySua = ? WHERE maSP = ? AND idSize = ?";
    Connection connection = DBConnect.getConnection();
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setBoolean(1, tinhTrang);
        ps.setDate(2, new java.sql.Date(ngayTao.getTime()));
        ps.setInt(3, maSp);
        ps.setInt(4, idSize);
        int affectedRows = ps.executeUpdate();
        return affectedRows > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    
  
}
