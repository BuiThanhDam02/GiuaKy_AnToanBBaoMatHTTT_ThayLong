package vn.edu.hcmuaf.fit.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;

public class MainFrame extends JFrame implements ActionListener {

    private JButton symButton = new JButton("Mã hóa đối xứng");
    private JButton asymButton = new JButton("Mã hóa bất đối xứng");
    private JButton hashButton = new JButton("Mã hóa hash");
    private JButton ckdtButton = new JButton("Mã hóa chữ ký điện tử");


    @Override
    public void actionPerformed(ActionEvent e) {

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
        // Đặt bộ mã mặc định cho ứng dụng Java
        System.setProperty("file.encoding", "UTF-8");

        // Thiết lập bộ mã mặc định cho các thành phần Swing
        setDefaultCharset(StandardCharsets.UTF_8);

        // Tạo và hiển thị giao diện Swing
//        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
}
