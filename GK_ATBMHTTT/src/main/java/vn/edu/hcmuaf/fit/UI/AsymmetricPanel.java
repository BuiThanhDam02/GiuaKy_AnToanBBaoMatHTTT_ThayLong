package vn.edu.hcmuaf.fit.UI;

import vn.edu.hcmuaf.fit.UI.ChildUI.APanel;

import javax.swing.*;
import java.awt.*;

public class AsymmetricPanel extends JPanel {
    JPanel APanel = new APanel();
    public AsymmetricPanel(){
        JLabel l = new JLabel();
        l.setText("Asymmetric Panel");
        this.add(l);
        this.add(APanel,BorderLayout.CENTER);
        this.setBackground(new Color(224, 224, 224));
        this.setPreferredSize(new Dimension(1050, 700));
    }
}
