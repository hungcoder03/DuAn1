/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.BanHangRepository;

import Model.BanHangModel.HoaDonBanHang;
import Model.HoaDonModel.HoaDon;
import Model.HoaDonModel.HoaDonChiTiet;
import Model.KhachHangModel.KhachHang;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class HoaDonRepo {

    private RepoSize sizeRepo = new RepoSize();

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public int insertNewHoaDon(HoaDon hd) {
        String sql = "INSERT INTO HoaDon (maHoaDon, maBan, maNhanVien, tenNhanVien, ngayTao, tinhTrang, tenBan)\n"
                + "VALUES (?,?,?,?,?,?,?)\n"
                + "\n"
                + "UPDATE Ban\n"
                + "SET trangThai = 1\n"
                + "WHERE maBan = ?";

        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, hd.getMaHoaDon());
            ps.setObject(2, hd.getMaBan());
            ps.setObject(3, hd.getMaNhanVien());
            ps.setObject(4, hd.getTenNhanVien());
            ps.setTimestamp(5, new java.sql.Timestamp(hd.getNgayTaoDate().getTime()));
            ps.setObject(6, hd.isTinhTrang());
            ps.setObject(7, hd.getTenBan());
            ps.setObject(8, hd.getMaBan());

            System.out.println(hd);

            return ps.executeUpdate();

        } catch (Exception e) {
            return 0;
        }
    }

    public int insertNewHoaDonKhachHang(HoaDon hd, KhachHang kh) {
        String sql = "INSERT INTO HoaDon (maHoaDon, maNhanVien, maKhachHang, tenNhanVien, tenKhachHang, ngayTao, tinhTrang)\n"
                + "VALUES (?,?,?,?,?,?,?)\n";

        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, hd.getMaHoaDon());
            ps.setObject(2, hd.getMaNhanVien());
            ps.setObject(3, kh.getMakh());
            ps.setObject(4, hd.getTenNhanVien());
            ps.setObject(5, kh.getHoTen());
            ps.setTimestamp(6, new java.sql.Timestamp(hd.getNgayTaoDate().getTime()));
            ps.setObject(7, hd.isTinhTrang());

            System.out.println("Hóa đơn New: " + hd);
            System.out.println("Khách hàng New: " + kh);

            return ps.executeUpdate();

        } catch (Exception e) {
            return 0;
        }
    }

    public ArrayList<HoaDonBanHang> getAllHdToday(boolean tt) {
        String sql = """
                     SELECT * FROM V_BangHoaDon
                     WHERE tinhTrang = ?
                     """;
        ArrayList<HoaDonBanHang> listHdBh = new ArrayList<>();
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, tt);

            rs = ps.executeQuery();

            while (rs.next()) {
                // Lấy timestamp và làm tròn đến giây
                java.sql.Timestamp timestamp = rs.getTimestamp(4);
                if (timestamp != null) {
                    timestamp.setNanos(0); // Đặt nano giây về 0
                }

                HoaDonBanHang hd = new HoaDonBanHang(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        timestamp,
                        rs.getDouble(5),
                        rs.getDouble(6),
                        rs.getBoolean(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11)
                );
                listHdBh.add(hd);
            }
            return listHdBh;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public HoaDonBanHang getHdTodayByBan(String tenBan) {
        String sql = """
                     SELECT * FROM V_BangHoaDon
                     WHERE tinhTrang = 0 and tenBan = ?
                     """;
        HoaDonBanHang hdbh = new HoaDonBanHang();
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, tenBan);

            rs = ps.executeQuery();

            while (rs.next()) {
                // Lấy timestamp và làm tròn đến giây
                java.sql.Timestamp timestamp = rs.getTimestamp(4);
                if (timestamp != null) {
                    timestamp.setNanos(0); // Đặt nano giây về 0
                }

                hdbh = new HoaDonBanHang(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        timestamp,
                        rs.getDouble(5),
                        rs.getDouble(6),
                        rs.getBoolean(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11)
                );
            }
            return hdbh;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public HoaDonBanHang getHdInAllHdToday(String maHD) {
        String sql = """
                     SELECT * FROM V_BangHoaDon
                     WHERE maHoaDon = ?
                     """;
        HoaDonBanHang hdbh = new HoaDonBanHang();
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, maHD);

            rs = ps.executeQuery();

            while (rs.next()) {
                // Lấy timestamp và làm tròn đến giây
                java.sql.Timestamp timestamp = rs.getTimestamp(4);
                if (timestamp != null) {
                    timestamp.setNanos(0); // Đặt nano giây về 0
                }

                hdbh = new HoaDonBanHang(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        timestamp,
                        rs.getDouble(5),
                        rs.getDouble(6),
                        rs.getBoolean(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11)
                );
            }
            return hdbh;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<HoaDonChiTiet> getAllHdChiTietByMaHD(String maHD) {
        String sql = """
                     SELECT ROW_NUMBER() OVER (ORDER BY hdct.id) AS STT,* FROM V_HoaDonChiTiet hdct
                     WHERE hdct.maHoaDon = ?
                     """;
        ArrayList<HoaDonChiTiet> listHdct = new ArrayList<>();
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, maHD);

            rs = ps.executeQuery();

            while (rs.next()) {
                HoaDonChiTiet hd = new HoaDonChiTiet(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(4),
                        rs.getString(3),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getFloat(8),
                        rs.getInt(9),
                        rs.getString(10)
                );
                listHdct.add(hd);
            }
            return listHdct;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public HoaDonChiTiet getAllHdChiTietBySTT(int id) {
        String sql = """
                     SELECT ROW_NUMBER() OVER (ORDER BY hdct.id) AS STT,* FROM V_HoaDonChiTiet hdct
                     WHERE hdct.id = ?
                     """;
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {
                hdct = new HoaDonChiTiet(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(4),
                        rs.getString(3),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getFloat(8),
                        rs.getInt(9),
                        rs.getString(10)
                );
            }
            return hdct;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int insertNewHoaDonChiTiet(HoaDonChiTiet hdct) {
        String sql = "INSERT INTO HoaDonChiTiet (maHoaDon, idSize, maSp, tenSp, donGia, soLuong, ghiChu)\n"
                + "VALUES (?,?,?,?,?,?,?)";

        Double donGia = hdct.getDonGia() + sizeRepo.getGiaSizeById(hdct.getIdSize()).getGia();
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, hdct.getMaHoaDon());
            ps.setObject(2, hdct.getIdSize());
            ps.setObject(3, hdct.getMaSanPham());
            ps.setObject(4, hdct.getTenSanPham());
            ps.setObject(5, donGia);
            ps.setObject(6, hdct.getSoLuong());
            ps.setObject(7, hdct.getGhiChu());

//            System.out.println(hdct);
            return ps.executeUpdate();

        } catch (Exception e) {
            return 0;
        }
    }

    public int updateHoaDonChiTiet(HoaDonChiTiet hdct, int idChon) {
        String sql = "UPDATE HoaDonChiTiet\n"
                + "SET maHoaDon = ?, idSize = ?, maSp = ?, tenSp = ?, donGia = ?, soLuong = ?, ghiChu = ?\n"
                + "WHERE id = ?";
        Double donGia = hdct.getDonGia() + sizeRepo.getGiaSizeById(hdct.getIdSize()).getGia();
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, hdct.getMaHoaDon());
            ps.setObject(2, hdct.getIdSize());
            ps.setObject(3, hdct.getMaSanPham());
            ps.setObject(4, hdct.getTenSanPham());
            ps.setObject(5, donGia);
            ps.setObject(6, hdct.getSoLuong());
            ps.setObject(7, hdct.getGhiChu());
            ps.setObject(8, idChon);

//            System.out.println(hdct);
            return ps.executeUpdate();

        } catch (Exception e) {
            return 0;
        }
    }

    public int updateMaGiamGia(String maGG) {
        String sql = 
                "UPDATE phieuGiamGia\n"
                + "SET soLuong = CASE \n"
                + " WHEN soLuong - 1 < 0 THEN 0 \n"
                + " ELSE soLuong - 1 \n"
                + " END\n"
                + "WHERE maGiamGia = ?";
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, maGG);

            return ps.executeUpdate();

        } catch (Exception e) {
            return 0;
        }
    }
    
    public int updateMaGiamGia1(String maHoaDon, double tongTienThanhToan) {
        String sql = "UPDATE HoaDon\n"
                + "SET maGiamGia = null, tongTienThanhToan = ?\n"
                + "WHERE maHoaDon = ?\n";
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, tongTienThanhToan);
            ps.setObject(2, maHoaDon);

            return ps.executeUpdate();

        } catch (Exception e) {
            return 0;
        }
    }
    
    public int updateMaGiamGia2(String maGG, String maHoaDon, double tongTienThanhToan) {
        String sql = "UPDATE HoaDon\n"
                + "SET maGiamGia = ?, tongTienThanhToan = ?\n"
                + "WHERE maHoaDon = ?\n";
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, maGG);
            ps.setObject(2, tongTienThanhToan);
            ps.setObject(3, maHoaDon);

            return ps.executeUpdate();

        } catch (Exception e) {
            return 0;
        }
    }

    public int updateSoLuongHoaDonChiTiet(HoaDonChiTiet hdct) {
        String sql = "UPDATE HoaDonChiTiet\n"
                + "SET soLuong = soLuong + ?\n"
                + "WHERE maSp = ? and idSize = ?";
        Double donGia = hdct.getDonGia() + sizeRepo.getGiaSizeById(hdct.getIdSize()).getGia();
        System.out.println(hdct.getMaSanPham());
        System.out.println(hdct.getIdSize());
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, hdct.getSoLuong());
            ps.setObject(2, hdct.getMaSanPham());
            ps.setObject(3, hdct.getIdSize());

//            System.out.println(hdct);
            return ps.executeUpdate();

        } catch (Exception e) {
            return 0;
        }
    }

    public int deleteHoaDonChiTiet(int idChon) {
        String sql = "DELETE FROM HoaDonChiTiet\n"
                + "WHERE id = ?";

        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, idChon);

//            System.out.println(hdct);
            return ps.executeUpdate();

        } catch (Exception e) {
            return 0;
        }
    }

    public int deleteHoaDon(String maHD, int maBan) {
        String sql = "DELETE FROM HoaDonChiTiet\n"
                + "WHERE maHoaDon = ?\n"
                + "DELETE FROM HoaDon\n"
                + "WHERE maHoaDon = ?\n"
                + "UPDATE Ban\n"
                + "SET trangThai = 0\n"
                + "WHERE maBan = ?";

        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, maHD);
            ps.setObject(2, maHD);
            ps.setObject(3, maBan);

            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean checkSpOnHoaDonChiTiet(String maHD, int maSp, int idSize) {
        String sql = "SELECT 1 FROM HoaDonChiTiet WHERE maHoaDon = ? and maSp = ? and idSize = ?";

        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, maHD);
            ps.setObject(2, maSp);
            ps.setObject(3, idSize);

            rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

    public int thanhToan(String maHoaDonChon, HoaDonBanHang hd, int maBan) {
        String sql = "UPDATE HoaDon\n"
                + "SET ngayTao = ?, tinhTrang = ?, tienKhachTra = ?, tienTraKhach = ?\n"
                + "WHERE maHoaDon = ?\n"
                + "UPDATE Ban\n"
                + "SET trangThai = 0\n"
                + "WHERE maBan = ?";

        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setTimestamp(1, new java.sql.Timestamp(System.currentTimeMillis()));
            ps.setObject(2, 1);
            ps.setObject(3, hd.getTienKhachTra());
            ps.setObject(4, hd.getTienThoi());
            ps.setObject(5, maHoaDonChon);
            ps.setObject(6, maBan);

            System.out.println("Time: " + new java.sql.Timestamp(System.currentTimeMillis()));
            System.out.println("Tình trạng: " + 1);
            System.out.println("Tiền khách trả: " + hd.getTienKhachTra());
            System.out.println("Tiền trả khách: " + hd.getTienThoi());
            System.out.println("Mã hóa đơn: " + maHoaDonChon);

            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }
}
