/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DBConnect.DBConnect;
import Model.NhanVien;
import Model.NhanVienChiTiet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepoNhanVien {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public RepoNhanVien() {//goi connect 1 lan de mo ket noi
        con = DBConnect.getConnection();
    }

    public ArrayList<Model.NhanVien> getAllByTinhTrangAndGioiTinh(boolean tt) {
        // lay toan bo du lieu tu sql server sang list
//        1. tuyệt đối k viết  select *
//        2. không viết trực tiếp câu lệnh sql tại đây
        sql = "select maNV, hoTen, gioiTinh, ngaySinh, SDT, email, diaChi, ngayTao, ngaySua, tinhTrang from NhanVien"
                + " where tinhtrang = ?";
        ArrayList<Model.NhanVien> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, tt);
            rs = ps.executeQuery();
            while (rs.next()) {
                String manv = rs.getString(1);
                String hoten = rs.getString(2);
                boolean gioitinh = rs.getBoolean(3);
                String ngaysinh = rs.getString(4);
                String diachi = rs.getString(5);
                String sdt = rs.getString(6);
                String email = rs.getString(7);
                String ngaytao = rs.getString(8);
                String ngaysua = rs.getString(9);
                boolean tinhtrang = rs.getBoolean(10);
                list.add(new Model.NhanVien(manv, hoten, gioitinh, ngaysinh, diachi, sdt, email, ngaytao, ngaysua, tinhtrang));
            }// dong while
//            System.out.println(list);
            return list;
        } catch (Exception e) {// loi
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Model.NhanVien> getAllTenNhanVien(boolean tt) {
        // lay toan bo du lieu tu sql server sang list
//        1. tuyệt đối k viết  select *
//        2. không viết trực tiếp câu lệnh sql tại đây
        sql = "select nv.maNV, nv.hoTen\n"
                + "from NhanVien nv\n"
                + "where tinhTrang = ?";

        ArrayList<Model.NhanVien> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, tt);
            rs = ps.executeQuery();
            while (rs.next()) {
                String manv = rs.getString(1);
                String hoten = rs.getString(2);
                list.add(new Model.NhanVien(manv, hoten));
            }// dong while
            return list;
        } catch (Exception e) {// loi
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Model.NhanVien> getAll() {
        // lay toan bo du lieu tu sql server sang list
//        1. tuyệt đối k viết  select *
//        2. không viết trực tiếp câu lệnh sql tại đây
        sql = "select nv.maNV, tk.chucVu, nv.hoTen, nv.gioiTinh, nv.ngaySinh, nv.SDT, nv.email, nv.diaChi, nv.ngayTao, nv.ngaySua, nv.tinhTrang \n"
                + "from NhanVien nv\n"
                + "join TaiKhoan tk on tk.maNV = nv.maNV";

        ArrayList<Model.NhanVien> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
//            ps.setObject(1, tt);
            rs = ps.executeQuery();
            while (rs.next()) {
                String manv = rs.getString(1);
                String chucVu = rs.getString(2);
                String hoten = rs.getString(3);
                boolean gioitinh = rs.getBoolean(4);
                String ngaysinh = rs.getString(5);
                String diachi = rs.getString(6);
                String sdt = rs.getString(7);
                String email = rs.getString(8);
                String ngaytao = rs.getString(9);
                String ngaysua = rs.getString(10);
                boolean tinhtrang = rs.getBoolean(11);
                list.add(new Model.NhanVien(manv, chucVu, hoten, gioitinh, ngaysinh, diachi, sdt, email, ngaytao, ngaysua, tinhtrang));
            }// dong while
//            System.out.println(list);
            return list;
        } catch (Exception e) {// loi
            e.printStackTrace();
            return null;
        }
    }

    // Kiểm tra mã nhân viên có tồn tại hay không
    public boolean isMaNvExists(String maNV) {
        String sqlCheck = "SELECT 1 FROM NhanVien WHERE maNV = ?";

//        boolean checked = true;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sqlCheck);

            ps.setObject(1, maNV);

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

    // Kiểm tra SĐT có tồn tại hay không
    public boolean isPhoneExists(String sdt) {
        String sqlCheck = "SELECT 1 FROM NhanVien WHERE sdt = ?";

        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sqlCheck);

            ps.setObject(1, sdt);

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

    // Kiểm tra SĐT có tồn tại hay không
    public boolean isEmailExists(String email) {
        String sqlCheck = "SELECT 1 FROM NhanVien WHERE email = ?";

        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sqlCheck);

            ps.setObject(1, email);

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

    // Kiểm tra SĐT có tồn tại hay không
    public boolean isTenDangNhapExists(String tenDangNhap) {
        String sqlCheck = "SELECT 1 FROM TaiKhoan WHERE tenDangNhap = ?";

        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sqlCheck);

            ps.setObject(1, tenDangNhap);

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

    public NhanVienChiTiet getNhanVienChiTietByMaNV(String maNV) {
        sql = """
              SELECT dbo.NhanVien.maNV, dbo.NhanVien.hoTen, dbo.NhanVien.gioiTinh, 
                    dbo.NhanVien.ngaySinh, dbo.NhanVien.SDT, dbo.NhanVien.email, 
                    dbo.NhanVien.diaChi,dbo.TaiKhoan.tenDangNhap, dbo.TaiKhoan.matKhau,
                    dbo.TaiKhoan.chucVu, dbo.NhanVien.tinhTrang 
               FROM dbo.NhanVien INNER JOIN
               dbo.TaiKhoan ON dbo.NhanVien.maNV = dbo.TaiKhoan.maNV
               WHERE dbo.NhanVien.maNV = ? """;
        NhanVienChiTiet nvct = new NhanVienChiTiet();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, maNV);

            rs = ps.executeQuery();
            while (rs.next()) {
                nvct = new NhanVienChiTiet(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getBoolean(3),
                        rs.getDate(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getBoolean(11)
                );
            }
            return nvct;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int addNhanVienchitiet(Model.NhanVienChiTiet nv) {
        String sqlAdd = "insert into NhanVien (maNV, hoTen, gioiTinh, ngaySinh, SDT, email, diaChi, ngayTao, tinhTrang)\n"
                + " values (?,?,?,?,?,?,?,?,?)\n"
                + "insert into TaiKhoan (maNV, tenDangNhap, matKhau, chucVu, ngayTao, tinhTrang)\n"
                + "values (?,?,?,?,?,?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sqlAdd);

            ps.setObject(1, nv.getManv());
            ps.setObject(2, nv.getTennv());
            ps.setObject(3, nv.isGioitinh());
            ps.setDate(4, new java.sql.Date(nv.getNgaySinh().getTime()));
            ps.setObject(5, nv.getSdt());
            ps.setObject(6, nv.getEmail());
            ps.setObject(7, nv.getDiachi());
            ps.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis()));
            ps.setObject(9, nv.isTinhtrang());

            ps.setObject(10, nv.getManv());
            ps.setObject(11, nv.getTenDangNhap());
            ps.setObject(12, nv.getMatkhau());
            ps.setObject(13, nv.getChucvu());
            ps.setTimestamp(14, new java.sql.Timestamp(System.currentTimeMillis()));
            ps.setObject(15, nv.isTinhtrang());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof SQLException) {
                SQLException sqlEx = (SQLException) e;
                System.out.println("SQLState: " + sqlEx.getSQLState());
                System.out.println("Error Code: " + sqlEx.getErrorCode());
                System.out.println("Message: " + sqlEx.getMessage());
            }
            return 0;
        }
    }

    public int updateNhanVienchitiet(String manv, Model.NhanVienChiTiet nv) {

        sql = " update TaiKhoan\n"
                + "set tenDangNhap = ?, matKhau = ?, chucVu = ?, ngaySua = ?, tinhTrang = ?\n"
                + "where maNV = ?\n"
                + "update NhanVien set maNV =?, hoTen=?, gioiTinh=?, ngaySinh=?, SDT=?, email=?, diaChi=?, ngaySua=?, tinhTrang=? \n"
                + "where maNV = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, nv.getTenDangNhap());
            ps.setObject(2, nv.getMatkhau());
            ps.setObject(3, nv.getChucvu());
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            ps.setBoolean(5, nv.isTinhtrang());
            ps.setObject(6, manv);
            ps.setObject(7, nv.getManv());
            ps.setObject(8, nv.getTennv());
            ps.setBoolean(9, nv.isGioitinh());
            ps.setDate(10, new java.sql.Date(nv.getNgaySinh().getTime()));
            ps.setObject(11, nv.getSdt());
            ps.setObject(12, nv.getEmail());
            ps.setObject(13, nv.getDiachi());
            ps.setTimestamp(14, new Timestamp(System.currentTimeMillis()));
            ps.setBoolean(15, nv.isTinhtrang());
            ps.setObject(16, manv);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteNhanVien(String maCanXoa) {
        sql = "delete from taiKhoan\n"
                + " where maNV = ?"
                + " delete from nhanVien"
                + " where maNv = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maCanXoa);
            ps.setObject(2, maCanXoa);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ArrayList<Model.NhanVien> getAllNhanVienByName(String ten) {
        // lay toan bo du lieu tu sql server sang list
//        1. tuyệt đối k viết  select *
//        2. không viết trực tiếp câu lệnh sql tại đây
        sql = "select nv.maNV, tk.chucVu, nv.hoTen, nv.gioiTinh, nv.ngaySinh, nv.SDT, nv.email, nv.diaChi, nv.ngayTao, nv.ngaySua, nv.tinhTrang \n"
                + "from NhanVien nv\n"
                + "join TaiKhoan tk on tk.maNV = nv.maNV\n"
                + "where nv.hoTen LIKE ?";

        ArrayList<Model.NhanVien> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, "%" + ten + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String manv = rs.getString(1);
                String chucVu = rs.getString(2);
                String hoten = rs.getString(3);
                boolean gioitinh = rs.getBoolean(4);
                String ngaysinh = rs.getString(5);
                String diachi = rs.getString(6);
                String sdt = rs.getString(7);
                String email = rs.getString(8);
                String ngaytao = rs.getString(9);
                String ngaysua = rs.getString(10);
                boolean tinhtrang = rs.getBoolean(11);
                list.add(new Model.NhanVien(manv, chucVu, hoten, gioitinh, ngaysinh, diachi, sdt, email, ngaytao, ngaysua, tinhtrang));
            }// dong while
//            System.out.println(list);
            return list;
        } catch (Exception e) {// loi
            e.printStackTrace();
            return null;
        }
    }

    public NhanVien getNhanVienByName(String tenNV) {
        String sqlNV = "SELECT maNv, hoTen\n"
                + " FROM nhanVien\n"
                + " where hoTen = ?";

        NhanVien nv = new NhanVien();

        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sqlNV);
            ps.setObject(1, tenNV);

            rs = ps.executeQuery();
            while (rs.next()) {
                nv = new NhanVien(rs.getString(1), rs.getString(2));
            }
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<NhanVien> getAllNhanVienByPhone(String searchPhone) {
        // lay toan bo du lieu tu sql server sang list
//        1. tuyệt đối k viết  select *
//        2. không viết trực tiếp câu lệnh sql tại đây
        sql = "select nv.maNV, tk.chucVu, nv.hoTen, nv.gioiTinh, nv.ngaySinh, nv.SDT, nv.email, nv.diaChi, nv.ngayTao, nv.ngaySua, nv.tinhTrang \n"
                + "from NhanVien nv\n"
                + "join TaiKhoan tk on tk.maNV = nv.maNV\n"
                + "where nv.SDT LIKE ?";

        ArrayList<Model.NhanVien> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, "%" + searchPhone + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String manv = rs.getString(1);
                String chucVu = rs.getString(2);
                String hoten = rs.getString(3);
                boolean gioitinh = rs.getBoolean(4);
                String ngaysinh = rs.getString(5);
                String diachi = rs.getString(6);
                String sdt = rs.getString(7);
                String email = rs.getString(8);
                String ngaytao = rs.getString(9);
                String ngaysua = rs.getString(10);
                boolean tinhtrang = rs.getBoolean(11);
                list.add(new Model.NhanVien(manv, chucVu, hoten, gioitinh, ngaysinh, diachi, sdt, email, ngaytao, ngaysua, tinhtrang));
            }// dong while
//            System.out.println(list);
            return list;
        } catch (Exception e) {// loi
            e.printStackTrace();
            return null;
        }
    }

}
