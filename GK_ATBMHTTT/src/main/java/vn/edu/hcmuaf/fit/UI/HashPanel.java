package vn.edu.hcmuaf.fit.UI;

import vn.edu.hcmuaf.fit.UI.ChildUI.HPanel;

import javax.swing.*;
import java.awt.*;

public class HashPanel extends JPanel {
    JPanel hpanel = new HPanel();
    public HashPanel(){
        JLabel l = new JLabel();
        l.setText("");

        this.add(l,BorderLayout.NORTH);
        this.add(hpanel,BorderLayout.CENTER);
        this.setBackground(new Color(227, 227, 227));
        this.setPreferredSize(new Dimension(700, 700));
    }
}
