package vn.edu.hcmuaf.fit.UI;

import javax.swing.*;
import java.awt.*;

public class CKDTPanel extends JPanel {
    public CKDTPanel(){
        JLabel l = new JLabel();
        l.setText("CKDT Panel");
        this.add(l);
        this.setBackground(new Color(227, 227, 227));
        this.setPreferredSize(new Dimension(1050, 700));
    }
}
