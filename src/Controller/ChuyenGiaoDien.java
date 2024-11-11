/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Bean.DanhMucBean;
import View.ViewBanHangTest;
import View.ViewHoaDonTest;
import View.ViewKhachHang;
import View.ViewKhuyenMaiTest;
import View.ViewNhanVienTest;
import View.ViewSanPhamTest;
import View.ViewThongKeTest;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author admin
 */
public class ChuyenGiaoDien {
    
    private JPanel pnlRoot;
    
    private String kindSelected = "";
    
    private List<DanhMucBean> listItem = null;

    public ChuyenGiaoDien(JPanel pnlRoot) {
        this.pnlRoot = pnlRoot;
    }
    
    public void setView(JPanel pnlItem, JLabel lblItem) {
        kindSelected = "BanHang";
        pnlItem.setBackground(Color.GRAY);
        lblItem.setBackground(Color.GRAY);
        
        pnlRoot.removeAll();
        pnlRoot.setLayout(new BorderLayout());
        pnlRoot.add(new ViewBanHangTest());
        pnlRoot.validate();
        pnlRoot.repaint();
        
    }
    
    public void setEvent(List<DanhMucBean> listItem){
        this.listItem = listItem;
        for (DanhMucBean item : listItem) {
            item.getLbl().addMouseListener(new LabelEvent(item.getKind(), item.getPnl(), item.getLbl()));
        }
    }
    
    class LabelEvent implements MouseListener {

        private JPanel node;
        
        private String kind;
        
        private JPanel pnlItem;
        
        private JLabel lblItem;

        public LabelEvent(String kind, JPanel pnlItem, JLabel lblItem) {
            this.kind = kind;
            this.pnlItem = pnlItem;
            this.lblItem = lblItem;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            switch(kind) {
                case "BanHang":
                    node = new ViewBanHangTest();
                    break;
                case "KhuyenMai":
                    node = new ViewKhuyenMaiTest();
                    break;
                case "SanPham":
                    node = new ViewSanPhamTest();
                    break;
                case "HoaDon":
                    node = new ViewHoaDonTest();
                    break;
                case "NhanVien":
                    node = new ViewNhanVienTest();
                    break;
                case "KhachHang":
                    node = new ViewKhachHang();
                    break;
                case "ThongKe":
                    node = new ViewThongKeTest();
                    break;
                default:
                    break;
            }
            
            pnlRoot.removeAll();
            pnlRoot.setLayout(new BorderLayout());
            pnlRoot.add(node);
            pnlRoot.validate();
            pnlRoot.repaint();
            setChangeBackground(kind);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            pnlItem.setBackground(Color.GRAY);
            lblItem.setBackground(Color.GRAY);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            pnlItem.setBackground(Color.GRAY);
            lblItem.setBackground(Color.GRAY);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSelected.equalsIgnoreCase(kind)) {
                pnlItem.setBackground(new Color(222,184,142));
                lblItem.setBackground(new Color(222,184,142));
            }
        }
        
    }
    
    private void setChangeBackground(String kind) {
        for (DanhMucBean item : listItem) {
            if (item.getKind().equalsIgnoreCase(kind)) {
                item.getPnl().setBackground(Color.GRAY);
                item.getLbl().setBackground(Color.GRAY);
            } else {
                item.getPnl().setBackground(new Color(222,184,142));
                item.getLbl().setBackground(new Color(222,184,142));
            }
        }
    }
}
