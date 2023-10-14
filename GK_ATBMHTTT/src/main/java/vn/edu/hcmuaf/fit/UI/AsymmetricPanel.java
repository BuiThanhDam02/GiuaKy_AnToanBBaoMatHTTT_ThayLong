package vn.edu.hcmuaf.fit.UI;

import javax.swing.*;
import java.awt.*;

public class AsymmetricPanel extends JPanel {

    public AsymmetricPanel(){
        JLabel l = new JLabel();
        l.setText("Asymmetric Panel");
        this.add(l);
        this.setBackground(new Color(227, 227, 227));
        this.setPreferredSize(new Dimension(1050, 700));
    }
}
