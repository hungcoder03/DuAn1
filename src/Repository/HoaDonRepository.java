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
public class HoaDonRepository {

    public ArrayList<HoaDon> getAll() {
        String sql = """
           SELECT *, tongTienTruocGiamGia - tongTienSauKhiGiamGia as tienKM FROM V_HoaDon
            ORDER BY ngayTao DESC
            """;

        ArrayList<HoaDon> list = new ArrayList<>();

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();

                hd.setMaHoaDon(rs.getString(1));
                hd.setTenBan(rs.getString(2));
                hd.setMaGiamGia(rs.getString(3));  // Đảm bảo lấy đúng kiểu dữ liệu
                hd.setMaNhanVien(rs.getString(4));
                hd.setMaKhachHang(rs.getString(5));
                hd.setTenNhanVien(rs.getString(6));
                hd.setTenKhachHang(rs.getString(7));
                hd.setNgayTao(rs.getString(8));
                hd.setTongTienThanhToan(rs.getString(9));
                hd.setTinhTrang(rs.getBoolean(10));
                hd.setSdt(rs.getString(11));
                hd.setTongTienSauKM(rs.getDouble(12));
                hd.setTienKM(rs.getDouble(13));

                list.add(hd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<HoaDon> Search(String maHD, String tenNhanVien, String sdt) {
        String sql = """
            SELECT *, 
                   tongTienTruocGiamGia - tongTienSauKhiGiamGia as tienKM 
            FROM V_HoaDon
            WHERE 
                (NULLIF(maHoaDon, '') IS NULL OR maHoaDon LIKE ?)
                AND (NULLIF(tenNhanVien, '') IS NULL OR tenNhanVien LIKE ?)
                AND (
                    NULLIF(SDT, '') IS NOT NULL OR SDT LIKE ?  -- Kiểm tra nếu SDT NULL hoặc thỏa mãn điều kiện LIKE '%0%'
                )
                AND (SDT IS NOT NULL OR NULLIF(SDT, '') IS NULL);  -- Chỉ áp dụng kiểm tra SDT nếu SDT không phải NULL
         """;

        ArrayList<HoaDon> list1 = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Truyền tham số vào PreparedStatement
            ps.setString(1, maHD == null || maHD.isEmpty() ? "%" : "%" + maHD + "%");
            ps.setString(2, tenNhanVien == null || tenNhanVien.isEmpty() ? "%" : "%" + tenNhanVien + "%");
            ps.setString(3, sdt == null || sdt.isEmpty() ? "%" : "%" + sdt + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getString(1));
                hd.setTenBan(rs.getString(2));
                hd.setMaGiamGia(rs.getString(3));  // Đảm bảo lấy đúng kiểu dữ liệu
                hd.setMaNhanVien(rs.getString(4));
                hd.setMaKhachHang(rs.getString(5));
                hd.setTenNhanVien(rs.getString(6));
                hd.setTenKhachHang(rs.getString(7));
                hd.setNgayTao(rs.getString(8));
                hd.setTongTienThanhToan(rs.getString(9));
                hd.setTinhTrang(rs.getBoolean(10));
                hd.setSdt(rs.getString(11));
                hd.setTongTienSauKM(rs.getDouble(12));
                hd.setTienKM(rs.getDouble(13));

                System.out.println("Hóa đơn: " + hd);
                list1.add(hd);
            }
//            System.out.println(list1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list1;
    }

//    public static void main(String[] args) {
//        HoaDonRepository repo = new HoaDonRepository();
//        ArrayList<HoaDon> list = repo.Search(null, "NV001", null);
//        for (HoaDon hd : list) {
//            System.out.println(hd);
//        }
//    }

    public ArrayList<HoaDon> getHdByMaHd(String maHD) {
        String sql = """
            SELECT *, 
                   tongTienTruocGiamGia - tongTienSauKhiGiamGia as tienKM 
            FROM V_HoaDon
            WHERE 
                maHoaDon like ?
                     order by ngayTao desc
         """;

        ArrayList<HoaDon> list1 = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Truyền tham số vào PreparedStatement
            ps.setString(1, "%" + maHD + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getString(1));
                hd.setTenBan(rs.getString(2));
                hd.setMaGiamGia(rs.getString(3));  // Đảm bảo lấy đúng kiểu dữ liệu
                hd.setMaNhanVien(rs.getString(4));
                hd.setMaKhachHang(rs.getString(5));
                hd.setTenNhanVien(rs.getString(6));
                hd.setTenKhachHang(rs.getString(7));
                hd.setNgayTao(rs.getString(8));
                hd.setTongTienThanhToan(rs.getString(9));
                hd.setTinhTrang(rs.getBoolean(10));
                hd.setSdt(rs.getString(11));
                hd.setTongTienSauKM(rs.getDouble(12));
                hd.setTienKM(rs.getDouble(13));

                System.out.println("Hóa đơn: " + hd);
                list1.add(hd);
            }
//            System.out.println(list1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list1;
    }
    
    public ArrayList<HoaDon> getHdByTenNhanVien(String tenNv) {
        String sql = """
            SELECT *, 
                   tongTienTruocGiamGia - tongTienSauKhiGiamGia as tienKM 
            FROM V_HoaDon
            WHERE 
                tenNhanVien like ?
                     order by ngayTao desc
         """;

        ArrayList<HoaDon> list1 = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Truyền tham số vào PreparedStatement
            ps.setString(1, "%" + tenNv + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getString(1));
                hd.setTenBan(rs.getString(2));
                hd.setMaGiamGia(rs.getString(3));  // Đảm bảo lấy đúng kiểu dữ liệu
                hd.setMaNhanVien(rs.getString(4));
                hd.setMaKhachHang(rs.getString(5));
                hd.setTenNhanVien(rs.getString(6));
                hd.setTenKhachHang(rs.getString(7));
                hd.setNgayTao(rs.getString(8));
                hd.setTongTienThanhToan(rs.getString(9));
                hd.setTinhTrang(rs.getBoolean(10));
                hd.setSdt(rs.getString(11));
                hd.setTongTienSauKM(rs.getDouble(12));
                hd.setTienKM(rs.getDouble(13));

                System.out.println("Hóa đơn: " + hd);
                list1.add(hd);
            }
//            System.out.println(list1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list1;
    }
    
    public ArrayList<HoaDon> getHdBySDT(String sdt) {
        String sql = """
            SELECT *, 
                   tongTienTruocGiamGia - tongTienSauKhiGiamGia as tienKM 
            FROM V_HoaDon
            WHERE 
                sdt like ?
                    order by ngayTao desc
         """;

        ArrayList<HoaDon> list1 = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Truyền tham số vào PreparedStatement
            ps.setString(1, "%" + sdt + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getString(1));
                hd.setTenBan(rs.getString(2));
                hd.setMaGiamGia(rs.getString(3));  // Đảm bảo lấy đúng kiểu dữ liệu
                hd.setMaNhanVien(rs.getString(4));
                hd.setMaKhachHang(rs.getString(5));
                hd.setTenNhanVien(rs.getString(6));
                hd.setTenKhachHang(rs.getString(7));
                hd.setNgayTao(rs.getString(8));
                hd.setTongTienThanhToan(rs.getString(9));
                hd.setTinhTrang(rs.getBoolean(10));
                hd.setSdt(rs.getString(11));
                hd.setTongTienSauKM(rs.getDouble(12));
                hd.setTienKM(rs.getDouble(13));

                System.out.println("Hóa đơn: " + hd);
                list1.add(hd);
            }
//            System.out.println(list1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list1;
    }
}
