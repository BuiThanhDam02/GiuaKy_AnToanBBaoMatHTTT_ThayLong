package vn.edu.hcmuaf.fit.UI;

import vn.edu.hcmuaf.fit.UI.ChildUI.SEPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SymmetricPanel extends JPanel implements ActionListener {
    private JPanel contentContainer ;
    private JPanel contentPanel = new SEPanel("AES");
    private String[] options = { "AES", "Blowfish", "RC5", "RC6", "DESede/ECB/PKCS5Padding", "Twofish","Vigenere","Hill" };
    private JComboBox<String> comboBox;
    private JLabel l;

    private JPanel northPanel ;
    public SymmetricPanel(){
        contentContainer = new JPanel(new CardLayout());
        northPanel = new JPanel();
        comboBox = new JComboBox<>(options);
        comboBox.addActionListener(this);
        l = new JLabel();
        l.setText("Symmetric");

        northPanel.add(comboBox);
        contentContainer.add(this.contentPanel, "AES");
        this.add(l);
        this.add(northPanel,BorderLayout.NORTH);
        this.add(contentContainer, BorderLayout.CENTER);
        this.setBackground(new Color(224, 224, 224));
        this.setPreferredSize(new Dimension(1050, 700));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBox) {
            String selectedOption = (String) comboBox.getSelectedItem();
            this.contentPanel = new SEPanel(selectedOption);
            contentContainer.add(this.contentPanel, selectedOption);
            changeCenterLayout(selectedOption);
            System.out.println("Selected option: " + selectedOption);
        }
    }
    private void changeCenterLayout(String layout){
        CardLayout cardLayout = (CardLayout) contentContainer.getLayout();
        cardLayout.show(contentContainer, layout);
    }
}
