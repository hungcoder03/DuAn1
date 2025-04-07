/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.KhuyenMaiModel.KhuyenMai;
import Model.KhuyenMaiModel.KhuyenMaiChiTiet;
import Model.KhuyenMaiModel.LoaiGiamGia;
import Model.KhuyenMaiModel.NhanVienAndKhuyenMai;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class KhuyenMaiRepo {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
//    private String sql = "";

    //Lấy dữ liệu lên bảng
    public List<KhuyenMai> getAllKhuyenMai(boolean trangThai) {
        String sql = """
              SELECT        dbo.phieuGiamGia.maGiamGia, dbo.LoaiGiamGia.loaiGiamGia, dbo.phieuGiamGia.mucGiam, dbo.phieuGiamGia.soLuong, dbo.phieuGiamGia.NgayBatDau, dbo.phieuGiamGia.NgayKetThuc, 
                                             dbo.phieuGiamGia.TrangThai
              FROM              dbo.phieuGiamGia INNER JOIN
                                             dbo.LoaiGiamGia ON dbo.LoaiGiamGia.maLoai = dbo.phieuGiamGia.maLoai
              WHERE dbo.phieuGiamGia.TrangThai = ?
              """;
        List<KhuyenMai> listKM = new ArrayList<>();

        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, trangThai);
            rs = ps.executeQuery();

            while (rs.next()) {
                KhuyenMai km = new KhuyenMai(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getInt(4),
                        rs.getDate(5),
                        rs.getDate(6),
                        rs.getBoolean(7)
                );
                listKM.add(km);
            }
            return listKM;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //Lấy dữ liệu lên bảng
    public List<KhuyenMai> searchByMaGiamGia(String maGiamGia) {
        String sql = """
              SELECT        dbo.phieuGiamGia.maGiamGia, dbo.LoaiGiamGia.loaiGiamGia, dbo.phieuGiamGia.mucGiam, dbo.phieuGiamGia.soLuong, dbo.phieuGiamGia.NgayBatDau, dbo.phieuGiamGia.NgayKetThuc, 
                                             dbo.phieuGiamGia.TrangThai
              FROM              dbo.phieuGiamGia INNER JOIN
                                             dbo.LoaiGiamGia ON dbo.LoaiGiamGia.maLoai = dbo.phieuGiamGia.maLoai
              WHERE dbo.phieuGiamGia.maGiamGia like ?
              """;
        List<KhuyenMai> listKM = new ArrayList<>();

        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, "%" + maGiamGia + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                KhuyenMai km = new KhuyenMai(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getInt(4),
                        rs.getDate(5),
                        rs.getDate(6),
                        rs.getBoolean(7)
                );
                listKM.add(km);
            }
            return listKM;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Lấy dữ liệu khi chọn 1 dòng trên bảng
    public KhuyenMaiChiTiet getKhuyenMaiChiTietByMa(String maGG) {
        String sql = """
              SELECT dbo.phieuGiamGia.maGiamGia, dbo.NhanVien.hoTen, dbo.LoaiGiamGia.loaiGiamGia, dbo.phieuGiamGia.dieuKien, dbo.phieuGiamGia.mucGiam, 
                dbo.phieuGiamGia.mucGiamToiDa, dbo.phieuGiamGia.soLuong, dbo.phieuGiamGia.NgayBatDau, dbo.phieuGiamGia.NgayKetThuc, dbo.phieuGiamGia.ngayTao, 
                dbo.phieuGiamGia.ngaySua, dbo.phieuGiamGia.ghiChu, dbo.phieuGiamGia.TrangThai
                FROM dbo.phieuGiamGia INNER JOIN
                dbo.LoaiGiamGia ON dbo.LoaiGiamGia.maLoai = dbo.phieuGiamGia.maLoai INNER JOIN
                dbo.NhanVien ON dbo.phieuGiamGia.maNV = dbo.NhanVien.maNV
              WHERE dbo.phieuGiamGia.maGiamGia = ?
              """;
        KhuyenMaiChiTiet kmct = new KhuyenMaiChiTiet();

        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maGG);
            rs = ps.executeQuery();

            while (rs.next()) {
                kmct = new KhuyenMaiChiTiet(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getFloat(4),
                        rs.getFloat(5),
                        rs.getFloat(6),
                        rs.getInt(7),
                        rs.getDate(8),
                        rs.getDate(9),
                        rs.getDate(10),
                        rs.getDate(11),
                        rs.getString(12),
                        rs.getBoolean(13)
                );
            }

            return kmct;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //Get All KM Chi tiết
    public ArrayList<KhuyenMaiChiTiet> getAllKhuyenMaiChiTiet(boolean tt) {
        String sql = """
              SELECT dbo.phieuGiamGia.maGiamGia, dbo.NhanVien.hoTen, dbo.LoaiGiamGia.loaiGiamGia, dbo.phieuGiamGia.dieuKien, dbo.phieuGiamGia.mucGiam, 
                dbo.phieuGiamGia.mucGiamToiDa, dbo.phieuGiamGia.soLuong, dbo.phieuGiamGia.NgayBatDau, dbo.phieuGiamGia.NgayKetThuc, dbo.phieuGiamGia.ngayTao, 
                dbo.phieuGiamGia.ngaySua, dbo.phieuGiamGia.ghiChu, dbo.phieuGiamGia.TrangThai
                FROM dbo.phieuGiamGia INNER JOIN
                dbo.LoaiGiamGia ON dbo.LoaiGiamGia.maLoai = dbo.phieuGiamGia.maLoai INNER JOIN
                dbo.NhanVien ON dbo.phieuGiamGia.maNV = dbo.NhanVien.maNV
              WHERE dbo.phieuGiamGia.TrangThai = ?
              """;
        ArrayList<KhuyenMaiChiTiet> listKmct = new ArrayList<>();

        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, tt);
            rs = ps.executeQuery();

            while (rs.next()) {
                KhuyenMaiChiTiet kmct = new KhuyenMaiChiTiet(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getFloat(4),
                        rs.getFloat(5),
                        rs.getFloat(6),
                        rs.getInt(7),
                        rs.getDate(8),
                        rs.getDate(9),
                        rs.getDate(10),
                        rs.getDate(11),
                        rs.getString(12),
                        rs.getBoolean(13)
                );
                listKmct.add(kmct);
            }

            return listKmct;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //Lấy dữ liệu loại giảm giá
    public List<LoaiGiamGia> getAllTenLoaiGiamGia() {
        String sql = """
              SELECT        maLoai, loaiGiamGia
              FROM              dbo.LoaiGiamGia
              """;
        List<LoaiGiamGia> listLGG = new ArrayList<>();

        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                LoaiGiamGia lgg = new LoaiGiamGia(
                        rs.getInt(1),
                        rs.getString(2)
                );
                listLGG.add(lgg);
            }
            return listLGG;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Kiểm tra mã giảm giá có tồn tại hay không
    public boolean isMaGiamGiaExists(String maGiamGia) {
        String sqlCheck = "SELECT 1 FROM phieuGiamGia WHERE maGiamGia = ?";

//        boolean checked = true;
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sqlCheck);

            ps.setObject(1, maGiamGia);

            rs = ps.executeQuery();

            if (rs.next()) {
                return true; // Có dữ liệu
            } else {
                return false; // Không có dữ liệu
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log lỗi nếu có
            return false; // Nếu có lỗi, giả sử là mã giảm giá không tồn tại
        }
    }
    
    public boolean checkTrangThaiMaGiamGia(String maGiamGia) {
        String sqlCheck = "SELECT 1 FROM phieuGiamGia \n"
                + "WHERE maGiamGia = ? and trangThai = 1";

//        boolean checked = true;
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sqlCheck);

            ps.setObject(1, maGiamGia);

            rs = ps.executeQuery();

            if (rs.next()) {
                return true; // Có dữ liệu
            } else {
                return false; // Không có dữ liệu
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log lỗi nếu có
            return false; // Nếu có lỗi, giả sử là mã giảm giá không tồn tại
        }
    }

    //Thêm khuyến mãi
    public int insertKhuyenMai(KhuyenMaiChiTiet kmct) {
        // Kiểm tra mã giảm giá có tồn tại không
        String sqlkm = """
                       EXEC P_insertPhieuGiamGia ?,?,?,?,?,?,?,?,?,?,?,?
                       """;

        if (isMaGiamGiaExists(kmct.getMaGiamGia())) {
            return 0; // Nếu mã giảm giá đã tồn tại, không thực hiện thêm mới

        } else {
            try {
                con = DBConnect.DBConnect.getConnection();
                ps = con.prepareStatement(sqlkm);

                ps.setString(1, kmct.getMaGiamGia());
                ps.setString(2, kmct.getTenNhanVien());
                ps.setString(3, kmct.getLoaiGiamGia());
                ps.setFloat(4, kmct.getDieuKien());
                ps.setFloat(5, kmct.getMucGiam());
                ps.setFloat(6, kmct.getMucGiamToiDa());
                ps.setInt(7, kmct.getSoLuong());
                ps.setDate(8, new java.sql.Date(kmct.getNgayBatDau().getTime()));
                ps.setDate(9, new java.sql.Date(kmct.getNgayKetThuc().getTime()));
                ps.setDate(10, new java.sql.Date(kmct.getNgayTao().getTime()));
                ps.setString(11, kmct.getGhiChu());
                ps.setBoolean(12, kmct.isTrangThai());

                return ps.executeUpdate(); // Thực thi câu lệnh INSERT
            } catch (SQLException e) {
                e.printStackTrace();  // In thông báo lỗi
                // Kiểm tra lỗi đặc biệt từ SQL
                if (e.getMessage().contains("Mã giảm giá đã tồn tại!")) {
                    JOptionPane.showMessageDialog(null, "Lỗi: Mã giảm giá đã tồn tại.");
                } else {
                    JOptionPane.showMessageDialog(null, "Lỗi khi thực thi: " + e.getMessage());
                }
                return 0; // Nếu có lỗi, trả về 0
            }
        }

    }

    //Cập nhật
    public int updateKhuyenMai(KhuyenMaiChiTiet kmct, String maGG) {
        // Kiểm tra mã giảm giá có tồn tại không
        String sql = """
                  EXEC P_updatePhieuGiamGia ?,?,?,?,?,?,?,?,?,?,?,?,?
                  """;

        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, kmct.getMaGiamGia());
            ps.setString(2, kmct.getTenNhanVien());
            ps.setString(3, kmct.getLoaiGiamGia());
            ps.setFloat(4, kmct.getDieuKien());
            ps.setFloat(5, kmct.getMucGiam());
            ps.setFloat(6, kmct.getMucGiamToiDa());
            ps.setInt(7, kmct.getSoLuong());
            ps.setDate(8, new java.sql.Date(kmct.getNgayBatDau().getTime()));
            ps.setDate(9, new java.sql.Date(kmct.getNgayKetThuc().getTime()));
            ps.setTimestamp(10, new java.sql.Timestamp(System.currentTimeMillis()));
            ps.setString(11, kmct.getGhiChu());
            ps.setBoolean(12, kmct.isTrangThai());
            ps.setString(13, maGG);

            return ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();  // In thông báo lỗi
            // Kiểm tra lỗi đặc biệt từ SQL
            JOptionPane.showMessageDialog(null, "Lỗi khi thực thi: " + e.getMessage());
            return 0; // Nếu có lỗi, trả về 0
        }

    }

    //Lấy tên nhân viên
    public List<NhanVienAndKhuyenMai> getAllTenNhanVien() {
        String sql = """
              SELECT maNV, hoTen
              FROM NhanVien
                     WHERE tinhTrang = 1
              """;
        List<NhanVienAndKhuyenMai> listNvAndKm = new ArrayList<>();

        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                NhanVienAndKhuyenMai nvAndKm = new NhanVienAndKhuyenMai(
                        rs.getString(1),
                        rs.getString(2)
                );
                listNvAndKm.add(nvAndKm);
            }
            return listNvAndKm;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Lấy thông tin nhân viên theo tên
    public String getNhanVienByName(String hoTen) {
        String sql = """
          SELECT maNV
          FROM NhanVien
          WHERE hoTen = ?
          """;

        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, hoTen);

            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("maNV"); // Trả về maNV
            }

            return null; // Nếu không tìm thấy nhân viên
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Lấy loại mã giảm giá theo tên loại mã giảm giá
    public Integer getLoaiGiamGiaByName(String loaiGiamGia) {
        String sql = """
          SELECT maLoai
          FROM LoaiGiamGia
          WHERE loaiGiamGia = ?
          """;

        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, loaiGiamGia);

            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("maLoai"); // Trả về maLoai
            }

            return null; // Nếu không tìm thấy loại giảm giá
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
