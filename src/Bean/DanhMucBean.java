/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bean;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author admin
 */
public class DanhMucBean {
    
    private String kind;
    
    private JPanel pnl;
    
    private JLabel lbl;

    public DanhMucBean() {
    }

    public DanhMucBean(String kind, JPanel pnl, JLabel lbl) {
        this.kind = kind;
        this.pnl = pnl;
        this.lbl = lbl;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public JPanel getPnl() {
        return pnl;
    }

    public void setPnl(JPanel pnl) {
        this.pnl = pnl;
    }

    public JLabel getLbl() {
        return lbl;
    }

    public void setLbl(JLabel lbl) {
        this.lbl = lbl;
    }
    
    
    
}
