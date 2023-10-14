package vn.edu.hcmuaf.fit.UI;

import vn.edu.hcmuaf.fit.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;

public class MainFrame extends JFrame implements ActionListener {

    private JPanel westPanel = new JPanel();
    JPanel symmetricPanel = new SymmetricPanel();

    JPanel asymmetricPanel = new AsymmetricPanel();

    JPanel hashPanel = new HashPanel();

    JPanel ckdtPanel = new CKDTPanel();
    private JPanel contentContainer = new JPanel(new CardLayout());

    private JPanel mainPanel = new JPanel();
    private JPanel southPanel = new JPanel();
    private JButton symButton = new JButton("Mã hóa đối xứng");
    private JButton asymButton = new JButton("Mã hóa bất đối xứng");
    private JButton hashButton = new JButton("Mã hóa hash");
    private JButton ckdtButton = new JButton("Mã hóa chữ ký điện tử");

    public MainFrame(){
        this.setTitle("Ứng dụng mã hóa");
        this.setSize(1200, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        symButton.setActionCommand("Symmetric");
        asymButton.setActionCommand("Asymmetric");
        hashButton.setActionCommand("Hash");
        ckdtButton.setActionCommand("CKDT");
        symButton.addActionListener(this);
        asymButton.addActionListener(this);
        hashButton.addActionListener(this);
        ckdtButton.addActionListener(this);
//        this.setLayout(new BorderLayout());

        GridLayout gridLayout = new GridLayout(4, 1);

        westPanel.setLayout(gridLayout);
        westPanel.add(symButton);
        westPanel.add(asymButton);
        westPanel.add(hashButton);
        westPanel.add(ckdtButton);

        westPanel.setPreferredSize(new Dimension(150, 200));
        westPanel.setBackground(new Color(143, 152, 255));
        southPanel.setPreferredSize(new Dimension(200, 100));
        southPanel.setBackground(Color.WHITE);


        contentContainer.add(symmetricPanel, "Symmetric");
        contentContainer.add(asymmetricPanel, "Asymmetric");
        contentContainer.add(hashPanel, "Hash");
        contentContainer.add(ckdtPanel, "CKDT");


        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(westPanel, BorderLayout.WEST);
        mainPanel.add(contentContainer, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();
        String buttonValue = sourceButton.getActionCommand();
        switch (buttonValue){
            case "Symmetric":
                System.out.println("sym");
                changeCenterLayout("Symmetric");
                break;
            case "Asymmetric":
                System.out.println("as");
                changeCenterLayout("Asymmetric");
                break;
            case "Hash":
                System.out.println("h");
                changeCenterLayout("Hash");
                break;
            case "CKDT":
                System.out.println("c");
                changeCenterLayout("CKDT");
                break;
        }

    }
    private void changeCenterLayout(String layout){
        CardLayout cardLayout = (CardLayout) contentContainer.getLayout();
        cardLayout.show(contentContainer, layout);
    }
    private static void setDefaultCharset(java.nio.charset.Charset charset) {
        System.setProperty("sun.awt.font.NativeFontWrapper.fontcharset", charset.name());
        UIManager.put("TextField.font", new Font("Dialog", Font.PLAIN, 12));
        UIManager.put("TextArea.font", new Font("Dialog", Font.PLAIN, 12));
        UIManager.put("Label.font", new Font("Dialog", Font.PLAIN, 12));
        UIManager.put("Button.font", new Font("Dialog", Font.PLAIN, 12));
        UIManager.getLookAndFeelDefaults().put("ClassLoader", ClassLoader.getSystemClassLoader());
        UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("Dialog", Font.PLAIN, 12));

    }
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        setDefaultCharset(StandardCharsets.UTF_8);
//        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
}
