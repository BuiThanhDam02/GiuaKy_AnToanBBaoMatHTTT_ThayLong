package vn.edu.hcmuaf.fit.UI.SymmetricUI;

import vn.edu.hcmuaf.fit.SymmetricEncryption.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SEPanel extends JPanel implements ActionListener {

    String name ;

    JButton encryptButton;
    JButton decryptButton;
    JButton createKeyButton;

    JButton deleteButton;
    JButton copyButton;

    JTextField keyField;
    JTextArea plainText;
    JTextArea cipherText;
    JTextArea resultText;
    JPanel cWest ;
    JPanel cEast;
    JPanel cNorth;
    JPanel cWestTop;
    JPanel cWestBot;

    JPanel cEastTop;
    JPanel cEastBot;

    JPanel keyPanel;
    JPanel plainPanel;
    JPanel cipherPanel;

    JLabel logStatus ;

     JTextArea textEnFileArea;
     JButton openEnFileButton;
    JTextArea resultFileArea;
    JTextArea textDeFileArea;
    JButton openDeFileButton;
    JButton encryptFileButton;
    JButton decryptFileButton;
    public SEPanel(String nameEncryption){
        this.name = nameEncryption;
        cWest = new JPanel();
        cEast = new JPanel();
        cWest.setPreferredSize(new Dimension(635, 600));
        cEast.setPreferredSize(new Dimension(350, 600));

        cWestTop = new JPanel();
        cWestBot = new JPanel();
        cWestTop.setPreferredSize(new Dimension(650, 315));
        cWestBot.setPreferredSize(new Dimension(650, 250));
        cWest.setLayout(new BorderLayout());
        cWestBot.setLayout(new BorderLayout());

        keyPanel = new JPanel();
        keyPanel.setLayout(new BorderLayout());
        keyPanel.setPreferredSize(new Dimension(650, 50));

        plainPanel = new JPanel();
        plainPanel.setLayout(new BorderLayout());
        plainPanel.setPreferredSize(new Dimension(300, 100));
        plainPanel.setBackground(new Color(51, 213, 45));

        cipherPanel = new JPanel();
        cipherPanel.setLayout(new BorderLayout());
        cipherPanel.setPreferredSize(new Dimension(300, 100));
        cipherPanel.setBackground(new Color(51, 213, 45));

        JLabel plainLabel = new JLabel();
        plainLabel.setText("Plain text");
        JLabel cipherLabel = new JLabel();
        cipherLabel.setText("Cipher text");
        JLabel keyLabel = new JLabel();
        keyLabel.setText("Key");
        JLabel orLabel = new JLabel();
        orLabel.setText("or");

        createKeyButton = new JButton("Create  Key");
        createKeyButton.setBackground(Color.WHITE);
        createKeyButton.setPreferredSize(new Dimension(100, 20));

        encryptButton = new JButton("Encrypt >>");
        encryptButton.setBackground(Color.WHITE);
        encryptButton.setPreferredSize(new Dimension(100, 20));
        decryptButton = new JButton("Decrypt <<");
        decryptButton.setBackground(Color.WHITE);
        decryptButton.setPreferredSize(new Dimension(100, 20));
        JPanel bp = new JPanel();
        bp.add(createKeyButton);
        bp.add(encryptButton);
        bp.add(decryptButton);

        keyField = new JTextField();
        keyField.setPreferredSize(new Dimension(280, 40));
        keyPanel.add(keyLabel,BorderLayout.NORTH);
        keyPanel.add(keyField,BorderLayout.WEST);
        keyPanel.add(orLabel,BorderLayout.CENTER);
        keyPanel.add(bp,BorderLayout.EAST);

        plainText = new JTextArea();
        cipherText = new JTextArea();
        plainText.setPreferredSize(new Dimension(280, 80));
        cipherText.setPreferredSize(new Dimension(280, 80));

        plainPanel.add(plainLabel,BorderLayout.NORTH);
        plainPanel.add(plainText,BorderLayout.CENTER);
        cipherPanel.add(cipherLabel,BorderLayout.NORTH);
        cipherPanel.add(cipherText,BorderLayout.CENTER);

        cWestTop.setLayout(new BorderLayout());
        cWestTop.add(keyPanel, BorderLayout.NORTH);
        cWestTop.add(plainPanel,BorderLayout.WEST);
        cWestTop.add(cipherPanel,BorderLayout.EAST);

        cEast.setLayout(new BorderLayout());
        cEastTop =new JPanel();
        cEastBot =new JPanel();
        cEastTop.setBackground(new Color(51, 213, 45));
        cEastBot.setBackground(new Color(118, 241, 224));
        cEastTop.setPreferredSize(new Dimension(325, 315));
        cEastBot.setPreferredSize(new Dimension(325, 250));
        cEastTop.setLayout(new BorderLayout());
        cEastBot.setLayout(new BorderLayout());

        JLabel resultLabel = new JLabel();
        resultLabel.setText("Result text");
        resultText = new JTextArea();
        resultText.setPreferredSize(new Dimension(310, 250));

        JPanel bp2 = new JPanel();
        copyButton =  new JButton("Copy result");
        copyButton.setBackground(Color.WHITE);
        copyButton.setPreferredSize(new Dimension(100, 20));
        bp2.add(copyButton);

        cEastTop.add(resultLabel,BorderLayout.NORTH);
        cEastTop.add(resultText,BorderLayout.CENTER);
        cEastTop.add(bp2,BorderLayout.SOUTH);

        JLabel logLabel = new JLabel();
        logLabel.setText("Status");
        logLabel.setForeground(Color.RED);
        JPanel logPanel = new JPanel();
        logStatus = new JLabel("Status...");
        logPanel.setPreferredSize(new Dimension(300,200));
        logLabel.setFont(new Font("Arial", Font.BOLD, 20));
        logStatus.setFont(new Font("Arial", Font.PLAIN, 15));
        logPanel.setBackground(new Color(118, 241, 224));
        logPanel.add(logStatus);

        cEastBot.add(logLabel,BorderLayout.NORTH);
        cEastBot.add(logPanel,BorderLayout.SOUTH);

        cEast.add(cEastTop,BorderLayout.NORTH);
        cEast.add(cEastBot,BorderLayout.SOUTH);


        cNorth = new JPanel();
        JLabel l = new JLabel();
        l.setText(this.name+" Encryption");
        cNorth.add(l);

//        cWestBot
        JPanel panelEnTitle = new JPanel();
        panelEnTitle.setPreferredSize(new Dimension(300, 20));
        panelEnTitle.setBackground(new Color(51, 213, 45));
        panelEnTitle.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel panelDeTitle = new JPanel();
        panelDeTitle.setPreferredSize(new Dimension(300, 20));
        panelDeTitle.setBackground(new Color(51, 213, 45));
        panelDeTitle.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel encryptFileTitle = new JLabel();

        encryptFileTitle.setText("File to encrypt");
        JLabel decryptFileTitle = new JLabel();
        decryptFileTitle.setText("File to decrypt");


        panelEnTitle.add(encryptFileTitle);
        panelDeTitle.add(decryptFileTitle);

        JPanel cWestBotL = new JPanel();
        JPanel cWestBotR = new JPanel();
        cWestBotL.setLayout(new BorderLayout());
        cWestBotL.setPreferredSize(new Dimension(300, 225));
        cWestBotR.setPreferredSize(new Dimension(300, 225));
        cWestBotR.setLayout(new BorderLayout());

        textEnFileArea = new JTextArea();
        textEnFileArea.setPreferredSize(new Dimension(200, 50));
        textEnFileArea.setEditable(false);

        textDeFileArea = new JTextArea();
        textDeFileArea.setPreferredSize(new Dimension(200, 50));
        textDeFileArea.setEditable(false);

        openEnFileButton = new JButton("Open File");
        openEnFileButton.setBackground(Color.WHITE);
        openEnFileButton.setPreferredSize(new Dimension(110, 30));
        openEnFileButton.addActionListener(this);


        openDeFileButton = new JButton("Open File");
        openDeFileButton.setBackground(Color.WHITE);
        openDeFileButton.setPreferredSize(new Dimension(110, 30));
        openDeFileButton.addActionListener(this);


        encryptFileButton = new JButton("Encrypt File");
        encryptFileButton.setBackground(Color.WHITE);
        encryptFileButton.setPreferredSize(new Dimension(110, 30));

        decryptFileButton = new JButton("Decrypt File");
        decryptFileButton.setBackground(Color.WHITE);
        decryptFileButton.setPreferredSize(new Dimension(110, 30));
        JPanel cWestBotLT = new JPanel();
        cWestBotLT.setPreferredSize(new Dimension(300, 120));

        cWestBotLT.add(panelEnTitle);
        cWestBotLT.add(openEnFileButton);
        cWestBotLT.add(encryptFileButton);
        cWestBotLT.add(textEnFileArea);

        JPanel cWestBotLB = new JPanel();
        cWestBotLB.setPreferredSize(new Dimension(300, 120));
        cWestBotLB.add(panelDeTitle);
        cWestBotLB.add(openDeFileButton);
        cWestBotLB.add(decryptFileButton);
        cWestBotLB.add(textDeFileArea);

        cWestBotL.add(cWestBotLT, BorderLayout.NORTH);
        cWestBotL.add(cWestBotLB, BorderLayout.SOUTH);


        resultFileArea = new JTextArea();
        resultFileArea.setPreferredSize(new Dimension(300, 200));
        JPanel panelResultFileTitle = new JPanel();
        panelResultFileTitle.setPreferredSize(new Dimension(300, 20));
        panelResultFileTitle.setBackground(new Color(51, 213, 45));
        panelResultFileTitle.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel resultFileTitle = new JLabel();
        resultFileTitle.setText("Result file path");
        panelResultFileTitle.add(resultFileTitle);
        cWestBotR.add(panelResultFileTitle,BorderLayout.NORTH);
        cWestBotR.add(resultFileArea,BorderLayout.CENTER);


        cWestBot.add(cWestBotL,BorderLayout.WEST);
        cWestBot.add(cWestBotR,BorderLayout.EAST);

        this.setLayout(new BorderLayout());
        cWest.add(cWestTop,BorderLayout.NORTH);
        cWest.add(cWestBot,BorderLayout.SOUTH);
        this.add(cNorth,BorderLayout.NORTH);
        this.add(cWest,BorderLayout.WEST);
        this.add(cEast,BorderLayout.EAST);
        this.setPreferredSize(new Dimension(1025, 600));
    }
    public void setEncryption(String nameEncryption){

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}