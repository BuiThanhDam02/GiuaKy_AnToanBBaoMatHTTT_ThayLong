package vn.edu.hcmuaf.fit.UI.ChildUI;




import vn.edu.hcmuaf.fit.Hash.SHA;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


public class HPanel extends JPanel implements ActionListener, DocumentListener {


    SHA hashSHA ;

    String currentStringPlain = null;


    JButton hashButton;

    JButton copyButton;

    JTextArea plainText;

    JTextArea resultText;
    JPanel cWest ;

    JPanel cNorth;
    JPanel cWestTop;
    JPanel cWestBot;




    JPanel plainPanel;
    JPanel resultPanel;

    JLabel logStatus ;

    JTextArea textEnFileArea;
    JButton openEnFileButton;
    JTextArea resultFileArea;

    JButton hashFileButton;


    public HPanel(){
        setEncryption();
        cWest = new JPanel();

        cWest.setPreferredSize(new Dimension(635, 600));


        cWestTop = new JPanel();
        cWestBot = new JPanel();
        cWestTop.setPreferredSize(new Dimension(650, 315));
        cWestBot.setPreferredSize(new Dimension(650, 250));
        cWestTop.setBorder(new MatteBorder(0, 0, 1, 1, new Color(197, 197, 197)));
//        cWestBot.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
        cWest.setLayout(new BorderLayout());
        cWestBot.setLayout(new BorderLayout());


        plainPanel = new JPanel();
        plainPanel.setLayout(new BorderLayout());
        plainPanel.setPreferredSize(new Dimension(300, 250));
        plainPanel.setBackground(new Color(51, 213, 45));

        resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        resultPanel.setPreferredSize(new Dimension(300, 250));
        resultPanel.setBackground(new Color(51, 213, 45));

        JLabel plainLabel = new JLabel();
        plainLabel.setText("Plain text");
        JLabel cipherLabel = new JLabel();
        cipherLabel.setText("Result text");


        hashButton = new JButton("Hash >>");
        hashButton.setBackground(Color.WHITE);
        hashButton.setPreferredSize(new Dimension(100, 20));
        hashButton.addActionListener(this);


        plainText = new JTextArea();
        plainText.setPreferredSize(new Dimension(280, 1000));

        plainText.getDocument().addDocumentListener(this);

        plainText.setLineWrap(true); // Thiết lập tự động xuống dòng

        plainPanel.add(plainLabel,BorderLayout.NORTH);
        plainPanel.add(new JScrollPane(plainText),BorderLayout.CENTER);
        JPanel ebPanel = new JPanel();
        ebPanel.setPreferredSize(new Dimension(100, 30));
        ebPanel.add(hashButton);
        plainPanel.add(ebPanel,BorderLayout.SOUTH);
        resultText = new JTextArea();
        resultText.setPreferredSize(new Dimension(310, 1000));
        resultText.setLineWrap(true);
        resultText.setEditable(false);
        resultPanel.add(cipherLabel,BorderLayout.NORTH);
        resultPanel.add(new JScrollPane(resultText),BorderLayout.CENTER);
        JPanel dbPanel = new JPanel();
        copyButton =  new JButton("Copy result");
        copyButton.setBackground(Color.WHITE);
        copyButton.setPreferredSize(new Dimension(100, 20));
        copyButton.addActionListener(this);
        dbPanel.setPreferredSize(new Dimension(100, 30));
        dbPanel.add(copyButton);
        resultPanel.add(dbPanel,BorderLayout.SOUTH);

        cWestTop.setLayout(new BorderLayout());
//        cWestTop.add(bp, BorderLayout.SOUTH);
        cWestTop.add(plainPanel,BorderLayout.WEST);
        cWestTop.add(resultPanel,BorderLayout.EAST);

        JLabel resultLabel = new JLabel();
        resultLabel.setText("Result text");



        cNorth = new JPanel();
        JLabel l = new JLabel();
        l.setText("SHA Hash");
        cNorth.add(l);

//        cWestBot
        JPanel panelEnTitle = new JPanel();
        panelEnTitle.setPreferredSize(new Dimension(300, 20));
        panelEnTitle.setBackground(new Color(51, 213, 45));
        panelEnTitle.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel encryptFileTitle = new JLabel();

        encryptFileTitle.setText("File to Hash");



        panelEnTitle.add(encryptFileTitle);


        JPanel cWestBotL = new JPanel();
        JPanel cWestBotR = new JPanel();
//        cWestBotL.setLayout(new BorderLayout());
        cWestBotL.setPreferredSize(new Dimension(300, 225));
        cWestBotR.setPreferredSize(new Dimension(300, 225));
        cWestBotR.setLayout(new BorderLayout());

        textEnFileArea = new JTextArea();
        textEnFileArea.setPreferredSize(new Dimension(200, 50));
        textEnFileArea.setEditable(false);
        this.textEnFileArea.setLineWrap(true);



        openEnFileButton = new JButton("Open File");
        openEnFileButton.setBackground(Color.WHITE);
        openEnFileButton.setPreferredSize(new Dimension(110, 30));
        openEnFileButton.addActionListener(this);



        hashFileButton = new JButton("Hash File");
        hashFileButton.setBackground(Color.WHITE);
        hashFileButton.setPreferredSize(new Dimension(110, 30));
        hashFileButton.addActionListener(this);


        JPanel cWestBotLT = new JPanel();
        cWestBotLT.setPreferredSize(new Dimension(300, 200));

        cWestBotLT.add(panelEnTitle);
        cWestBotLT.add(openEnFileButton);
        cWestBotLT.add(hashFileButton);
        cWestBotLT.add(textEnFileArea);


        cWestBotL.add(cWestBotLT);



        resultFileArea = new JTextArea();
        resultFileArea.setPreferredSize(new Dimension(300, 200));
        resultFileArea.setLineWrap(true);
        JPanel panelResultFileTitle = new JPanel();
        panelResultFileTitle.setPreferredSize(new Dimension(300, 20));
        panelResultFileTitle.setBackground(new Color(51, 213, 45));
        panelResultFileTitle.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel resultFileTitle = new JLabel();
        resultFileTitle.setText("Result file hash");
        panelResultFileTitle.add(resultFileTitle);

        cWestBotR.add(panelResultFileTitle,BorderLayout.NORTH);
        cWestBotR.add(resultFileArea,BorderLayout.CENTER);

        //
        JLabel logLabel = new JLabel();
        logLabel.setText("[LOG Status]");
        logLabel.setForeground(Color.RED);
        JPanel logPanel = new JPanel();
        logStatus = new JLabel("Status...");
        logPanel.setPreferredSize(new Dimension(900,50));
        logLabel.setFont(new Font("Arial", Font.BOLD, 18));
        logStatus.setFont(new Font("Arial", Font.PLAIN, 15));
        logPanel.setBackground(new Color(118, 241, 224));
        logPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        logPanel.add(logLabel);
        logPanel.add(logStatus);

        cWestBot.add(cWestBotL,BorderLayout.WEST);
        cWestBot.add(cWestBotR,BorderLayout.EAST);

        this.setLayout(new BorderLayout());
        cWest.add(cWestTop,BorderLayout.NORTH);
        cWest.add(cWestBot,BorderLayout.SOUTH);
        this.add(cNorth,BorderLayout.NORTH);
        this.add(cWest,BorderLayout.WEST);

        this.add(logPanel,BorderLayout.SOUTH);
        this.setBorder(new MatteBorder(1, 1, 1, 1, new Color(197, 197, 197)));
        this.setPreferredSize(new Dimension(700, 650));
    }
    public void setEncryption(){

//        hashSHA = new RSA();

    }
    public void showInfo(String title, String maessage ){
        JOptionPane.showMessageDialog(this, maessage, title, JOptionPane.INFORMATION_MESSAGE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getSource() == hashButton) {
          
                if (checkValidText(this.currentStringPlain,"Encrypt")){
                    String hashContent = hashSHA.check(this.currentStringPlain);
                    if (checkValidContent(hashContent,"Hash")){
                        this.resultText.setText(hashContent);
                        this.logStatus.setText("Hash text successfully!!");
                    }
                }
            
        } else if (e.getSource() == openEnFileButton){
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                this.textEnFileArea.setText(filePath);
                this.logStatus.setText("Open file path to encrypt successfully!!");
            }
        }
        else if (e.getSource() == hashFileButton) {
            
                if (checkValidFile(this.textEnFileArea.getText())){

                    try {
                        String r = hashSHA.checkFile(this.textEnFileArea.getText());
                        if (checkValidContent(r,"Hash")){
                            this.resultFileArea.setText(r);
                            this.logStatus.setText("Hash file successfully!!");
                        }

                    } catch (Exception ex) {
                        logStatus.setText("Hash file fail!!");
                        showInfo("Hash file fail!!","Hash file thất bại !!");
                        throw new RuntimeException(ex);
                    }

                }
            
        } else if (e.getSource() == copyButton){
            StringSelection stringSelection = new StringSelection(resultText.getText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            this.logStatus.setText("Copy result successfully!!");

        }
    }

   
    public boolean checkValidText(String text, String type){
        if (text==null||text==""){
            logStatus.setText("Content "+type+ " is empty");
            showInfo("Content "+type+"  is empty","Bạn cần nhập đoạn nội dung "+type+" trước khi thực hiện hành động!!");
            return false;
        }else {
            return true;
        }
    }

    public boolean checkValidContent(String content, String type){
        if (content == null){
            logStatus.setText("Content "+type+ " is not valid");
            showInfo("Content "+type+" is not valid","Lỗi trong qua trình "+type);
            return false;
        }
        return true;

    }

    public boolean checkValidFile(String filePath){
        if (filePath == null || filePath == "") {
            logStatus.setText("File path is empty");
            showInfo("File path is empty","Yêu cầu nhập file path trước khi mã hóa!!");
            return false;
        }
        File file = new File(filePath);
        if (file.exists()){
            if (file.isFile()){
                return true;
            }else{
                logStatus.setText("Path is not file");
                showInfo("Path is not file","Đường dẫn bạn nhập không phải là file!!");
                return false;
            }
        }else{
            logStatus.setText("File path is not exist");
            showInfo("File path is not exist","Đường dẫn file bạn nhập không tồn tại!!");
            return false;
        }
    }

    public String generateResultFilePath(String srcPath, String type){

        Path path = Paths.get(srcPath);
        String fileName = path.getFileName().toString();

        String fileExtension = "";
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex != -1 && dotIndex < fileName.length() - 1) {
            fileExtension = fileName.substring(dotIndex + 1);
            fileName = fileName.substring(0, dotIndex);
        }
        String result = fileName+" "+type+"."+fileExtension;
        System.out.println("Tên file: " + result);
        return result;
    }
    @Override
    public void insertUpdate(DocumentEvent e) {
        if (e.getDocument() == plainText.getDocument()) {
            this.currentStringPlain = plainText.getText();
            if (plainText.getText().isEmpty()) {
                this.currentStringPlain = null;
            }
//            System.out.println(this.currentStringPlain);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
            if (e.getDocument() == plainText.getDocument()) {
            this.currentStringPlain = plainText.getText();
            if (plainText.getText().isEmpty()) {
                this.currentStringPlain = null;
            }

        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
if (e.getDocument() == plainText.getDocument()) {
            this.currentStringPlain = plainText.getText();
            if (plainText.getText().isEmpty()) {
                this.currentStringPlain = null;
            }
//            System.out.println(this.currentStringPlain);
        }
    }

}