package vn.edu.hcmuaf.fit.UI;

import javax.swing.*;
import java.awt.*;

public class HashPanel extends JPanel {
    public HashPanel(){
        JLabel l = new JLabel();
        l.setText("Hash Panel");
        this.add(l);
        this.setBackground(new Color(227, 227, 227));
        this.setPreferredSize(new Dimension(1050, 700));
    }
}
