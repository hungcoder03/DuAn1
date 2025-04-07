/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DBConnect.DBConnect;
import Model.HoaDonModel.HoaDonChiTiet;
//import Model.ThongKe;
import Model.ThongKeModel.ThongKe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ThongKeRepository {

    public double getDoanhThuHomNay() {
        String sql = """
                 SELECT 
                     ISNULL(SUM(hdct.soLuong * hdct.donGia), 0) AS doanhThuHomNay
                 FROM 
                     HoaDonChiTiet hdct
                 LEFT JOIN 
                     HoaDon hd ON hdct.maHoaDon = hd.maHoaDon
                 WHERE 
                     CONVERT(date, hd.ngayTao) = CONVERT(date, GETDATE());
                 """;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("doanhThuHomNay");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0; // Trả về 0 nếu không có kết quả
    }
    // Phương thức lấy doanh thu hôm qua

    public double getDoanhThuHomQua() {
        String sql = """
                     SELECT 
                         ISNULL(SUM(hdct.soLuong * hdct.donGia), 0) AS doanhThuHomQua
                     FROM 
                         HoaDonChiTiet hdct
                    LEFT JOIN
                         HoaDon hd ON hdct.maHoaDon = hd.maHoaDon
                     WHERE 
                         CONVERT(date, hd.ngayTao) = CONVERT(date, GETDATE() - 1);
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("doanhThuHomQua");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0; // Trả về 0 nếu không có kết quả
    }

    public int getTongSanPhamHomNay() {
        String sql = """
                     SELECT 
                         ISNULL(SUM(hdct.soLuong), 0) AS tongSanPhamHomNay
                     FROM 
                         HoaDonChiTiet hdct
                     LEFT JOIN
                         HoaDon hd ON hdct.maHoaDon = hd.maHoaDon
                     WHERE 
                         CONVERT(date, hd.ngayTao) = CONVERT(date, GETDATE());
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("tongSanPhamHomNay");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Trả về 0 nếu không có kết quả
    }

    public int getTongDonHomNay() {
        String sql = """
                     SELECT 
                         COUNT(DISTINCT hd.maHoaDon) AS tongDonHomNay
                     FROM 
                         HoaDonChiTiet hdct
                     LEFT JOIN 
                         HoaDon hd ON hdct.maHoaDon = hd.maHoaDon
                     WHERE 
                         CONVERT(date, hd.ngayTao) = CONVERT(date, GETDATE());
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("tongDonHomNay");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Trả về 0 nếu không có kết quả
    }

    public ArrayList<ThongKe> getDoanhThuTheoNam(int nam) {
        ArrayList<ThongKe> list = new ArrayList<>();
        String sql = """
        SELECT MONTH(ngayTao) AS Thang, COUNT(maHoaDon) AS TongDon, SUM(tongTienThanhToan) AS TongTien
        FROM HoaDon
        WHERE YEAR(ngayTao) = ? and tinhTrang = 1
        GROUP BY MONTH(ngayTao)
        ORDER BY Thang
    """;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nam);  // Năm được chọn
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ThongKe thongKe = new ThongKe();
                thongKe.setThang(rs.getInt("Thang"));  // Đảm bảo tên trường trùng với alias trong SQL
                thongKe.setTongHoaDon(rs.getInt("TongDon"));  // Đảm bảo trùng tên với alias
                thongKe.setTongTienThanhToan(rs.getDouble("TongTien"));  // Nếu là double thì dùng getDouble

                list.add(thongKe);  // Thêm đối tượng vào danh sách
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;  // Trả về danh sách ThongKe
    }

    public ThongKe getDoanhThuTheokhoangThoiGian(int thangBatDau, int thangKetThuc) {

        String sql = """
        SELECT count(maHoaDon) as TongHoaDon,
               sum(tongTienThanhToan) as TongTien		
        FROM HoaDon hd
        WHERE (Month(hd.ngayTao) between ? and ?)
        	  and (Year(hd.ngayTao) = 2024)
    """;
        ThongKe thongKe = new ThongKe();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, thangBatDau);
            ps.setInt(2, thangKetThuc);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                thongKe.setTongHoaDon(rs.getInt(1));  // Đảm bảo trùng tên với alias
                thongKe.setTongTienThanhToan(rs.getDouble(2));  // Nếu là double thì dùng getDouble

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return thongKe;  // Trả về danh sách ThongKe
    }

}
