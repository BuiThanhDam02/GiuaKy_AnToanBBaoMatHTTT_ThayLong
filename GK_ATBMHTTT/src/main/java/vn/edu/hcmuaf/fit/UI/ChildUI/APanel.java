package vn.edu.hcmuaf.fit.UI.ChildUI;

import vn.edu.hcmuaf.fit.AsymmetricEncryption.RSA;


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


public class APanel extends JPanel implements ActionListener, DocumentListener {


    RSA encryptionRSA ;
    String currentStringPublicKey = null;
    String currentStringPrivateKey = null;
    String currentStringPlain = null;
    String currentStringCipher = null;

    JButton encryptButton;
    JButton decryptButton;
    JButton createKeyButton;
//    JButton createSubKeyButton;

    JButton deleteButton;
    JButton copyButton;

    JTextField publicKeyField;
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

    JTextField privateKeyField;
    public APanel(){
        setEncryption();
        cWest = new JPanel();
        cEast = new JPanel();
        cWest.setPreferredSize(new Dimension(635, 600));
        cEast.setPreferredSize(new Dimension(350, 600));

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

        cipherPanel = new JPanel();
        cipherPanel.setLayout(new BorderLayout());
        cipherPanel.setPreferredSize(new Dimension(300, 250));
        cipherPanel.setBackground(new Color(51, 213, 45));

        JLabel plainLabel = new JLabel();
        plainLabel.setText("Plain text");
        JLabel cipherLabel = new JLabel();
        cipherLabel.setText("Cipher text");
        JLabel keyLabel = new JLabel();
        keyLabel.setText("Public key");
        JLabel orLabel = new JLabel();
        orLabel.setText("or");

        createKeyButton = new JButton("Create  Key");
        createKeyButton.setBackground(Color.WHITE);
        createKeyButton.setPreferredSize(new Dimension(100, 20));
        createKeyButton.addActionListener(this);

        encryptButton = new JButton("Encrypt >>");
        encryptButton.setBackground(Color.WHITE);
        encryptButton.setPreferredSize(new Dimension(100, 20));
        encryptButton.addActionListener(this);

        decryptButton = new JButton("Decrypt <<");
        decryptButton.setBackground(Color.WHITE);
        decryptButton.setPreferredSize(new Dimension(100, 20));
        decryptButton.addActionListener(this);

//        JPanel bp = new JPanel();
//        bp.add(createKeyButton);


        publicKeyField = new JTextField();
        publicKeyField.setPreferredSize(new Dimension(280, 40));
        publicKeyField.getDocument().addDocumentListener(this);



        plainText = new JTextArea();
        cipherText = new JTextArea();
        plainText.setPreferredSize(new Dimension(280, 1000));

        cipherText.setPreferredSize(new Dimension(280, 1000));

        plainText.getDocument().addDocumentListener(this);
        cipherText.getDocument().addDocumentListener(this);
        plainText.setLineWrap(true); // Thiết lập tự động xuống dòng
        cipherText.setLineWrap(true); // Thiết lập tự động xuống dòng

        plainPanel.add(plainLabel,BorderLayout.NORTH);
        plainPanel.add(new JScrollPane(plainText),BorderLayout.CENTER);
        JPanel ebPanel = new JPanel();
        ebPanel.setPreferredSize(new Dimension(100, 30));
        ebPanel.add(encryptButton);
        plainPanel.add(ebPanel,BorderLayout.SOUTH);

        cipherPanel.add(cipherLabel,BorderLayout.NORTH);
        cipherPanel.add(new JScrollPane(cipherText),BorderLayout.CENTER);
        JPanel dbPanel = new JPanel();
        dbPanel.setPreferredSize(new Dimension(100, 30));
        dbPanel.add(decryptButton);
        cipherPanel.add(dbPanel,BorderLayout.SOUTH);

        cWestTop.setLayout(new BorderLayout());
//        cWestTop.add(bp, BorderLayout.SOUTH);
        cWestTop.add(plainPanel,BorderLayout.WEST);
        cWestTop.add(cipherPanel,BorderLayout.EAST);

        cEast.setLayout(new BorderLayout());
        cEastTop =new JPanel();
        cEastBot =new JPanel();
        cEastTop.setBackground(new Color(51, 213, 45));
//        cEastBot.setBackground(new Color(118, 241, 224));
        cEastTop.setPreferredSize(new Dimension(325, 315));
        cEastBot.setPreferredSize(new Dimension(325, 250));
        cEastTop.setLayout(new BorderLayout());
//        cEastBot.setLayout(new BorderLayout());

        JLabel resultLabel = new JLabel();
        resultLabel.setText("Result text");
        resultText = new JTextArea();
        resultText.setPreferredSize(new Dimension(310, 1000));
        resultText.setLineWrap(true);
        resultText.setEditable(false);

        JPanel bp2 = new JPanel();
        copyButton =  new JButton("Copy result");
        copyButton.setBackground(Color.WHITE);
        copyButton.setPreferredSize(new Dimension(100, 20));
        copyButton.addActionListener(this);
        bp2.add(copyButton);

        cEastTop.add(resultLabel,BorderLayout.NORTH);
        cEastTop.add(new JScrollPane(resultText),BorderLayout.CENTER);
        cEastTop.add(bp2,BorderLayout.SOUTH);

        keyPanel = new JPanel();
        keyPanel.setPreferredSize(new Dimension(300, 225));
//        keyPanel.setBackground(new Color(241, 118, 118));

 
            showOptionUI("Private key");


        cEastBot.add(keyPanel);

        cEastTop.setBorder(new MatteBorder(0, 1, 1, 0, new Color(197, 197, 197)));
        cEast.add(cEastTop,BorderLayout.NORTH);
        cEast.add(cEastBot,BorderLayout.SOUTH);


        cNorth = new JPanel();
        JLabel l = new JLabel();
        l.setText("RSA Encryption");
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
        this.textEnFileArea.setLineWrap(true);

        textDeFileArea = new JTextArea();
        textDeFileArea.setPreferredSize(new Dimension(200, 50));
        textDeFileArea.setEditable(false);
        this.textDeFileArea.setLineWrap(true);

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
        encryptFileButton.addActionListener(this);

        decryptFileButton = new JButton("Decrypt File");
        decryptFileButton.setBackground(Color.WHITE);
        decryptFileButton.setPreferredSize(new Dimension(110, 30));
        decryptFileButton.addActionListener(this);
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
        resultFileArea.setLineWrap(true);
        JPanel panelResultFileTitle = new JPanel();
        panelResultFileTitle.setPreferredSize(new Dimension(300, 20));
        panelResultFileTitle.setBackground(new Color(51, 213, 45));
        panelResultFileTitle.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel resultFileTitle = new JLabel();
        resultFileTitle.setText("Result file path");
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
        this.add(cEast,BorderLayout.EAST);
        this.add(logPanel,BorderLayout.SOUTH);
        this.setBorder(new MatteBorder(1, 1, 1, 1, new Color(197, 197, 197)));
        this.setPreferredSize(new Dimension(1025, 650));
    }
    public void setEncryption(){

            encryptionRSA = new RSA();
 
    }
    public void showInfo(String title, String maessage ){
        JOptionPane.showMessageDialog(this, maessage, title, JOptionPane.INFORMATION_MESSAGE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createKeyButton) {
            encryptionRSA.createKey();
            this.currentStringPublicKey = encryptionRSA.exportStringPublicKey();
            this.currentStringPrivateKey = encryptionRSA.exportStringPrivateKey();
            this.publicKeyField.setText(this.currentStringPublicKey);
            this.privateKeyField.setText(this.currentStringPrivateKey);
            this.logStatus.setText("Created key successfully!!");
//            System.out.println(encryptionRSA.getEncryptionName()+": "+encryptionRSA.exportStringKey());
        } else if (e.getSource() == encryptButton) {
            if (checkValidKey()){
                if (checkValidText(this.currentStringPlain,"Encrypt")){
                    String cipherContent = encryptionRSA.encryptToBase64(this.currentStringPlain);
                    if (checkValidContent(cipherContent,"Encrypt")){
                        this.currentStringCipher = cipherContent;
                        this.resultText.setText(this.currentStringCipher);
                        this.logStatus.setText("Encrypt text successfully!!");
                    }
                }
            }
        } else if (e.getSource() == decryptButton) {
            if (checkValidKey()){
                if (checkValidText(this.currentStringCipher,"Decrypt")){
                    String plaineContent = encryptionRSA.decryptFromBase64(this.currentStringCipher);
                    if (checkValidContent(plaineContent,"Decrypt")){
                        this.currentStringPlain = plaineContent;
                        this.resultText.setText(this.currentStringPlain);
                        this.logStatus.setText("Decrypt text successfully!!");
                    }
                }
            }
        }else if (e.getSource() == openEnFileButton){
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                this.textEnFileArea.setText(filePath);
                this.logStatus.setText("Open file path to encrypt successfully!!");
            }
        }else if (e.getSource() == openDeFileButton){
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                this.textDeFileArea.setText(filePath);
                this.logStatus.setText("Open file path to decrypt successfully!!");
            }
        }
        else if (e.getSource() == encryptFileButton) {
            if (checkValidKey()){
                if (checkValidFile(this.textEnFileArea.getText())){
                    String resultPath = generateResultFilePath(this.textEnFileArea.getText(),"encrypted");
                    try {
                        boolean r = encryptionRSA.encryptFile(this.textEnFileArea.getText(),resultPath);
                        if (r){
                            this.logStatus.setText("Encrypted file successfully!!");
                            File rf = new File(resultPath);
                            this.resultFileArea.setText(rf.getAbsolutePath());
                        }else{
                            logStatus.setText("Encrypted file fail!!");
                            showInfo("Encrypted file fail!!","Mã hóa file thất bại !!");
                        }
                    } catch (Exception ex) {
                        logStatus.setText("Encrypted file fail!!");
                        showInfo("Encrypted file fail!!","Mã hóa file thất bại !!");
                        throw new RuntimeException(ex);
                    }

                }
            }
        } else if (e.getSource() == decryptFileButton) {
            if (checkValidKey()){
                if (checkValidFile(this.textDeFileArea.getText())){
                    String resultPath = generateResultFilePath(this.textDeFileArea.getText(),"decrypted");
                    try {
                        boolean r = encryptionRSA.decryptFile(this.textDeFileArea.getText(),resultPath);
                        if (r){
                            this.logStatus.setText("Decrypted file successfully!!");
                            File rf = new File(resultPath);
                            this.resultFileArea.setText(rf.getAbsolutePath());

                        }else{
                            logStatus.setText("Decrypted file fail!!");
                            showInfo("Decrypted file fail!!","Giải mã file thất bại !!");
                        }
                    } catch (Exception ex) {
                        logStatus.setText("Decrypted file fail!!");
                        showInfo("Decrypted file fail!!","Giải mã file thất bại !!");
                        throw new RuntimeException(ex);
                    }
                }
            }
        }else if (e.getSource() == copyButton){
            StringSelection stringSelection = new StringSelection(resultText.getText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            this.logStatus.setText("Copy result successfully!!");

        }
    }

    public boolean checkValidKey(){
        if (this.currentStringPublicKey==null||this.currentStringPublicKey==""){
            logStatus.setText("Public key not found");
            showInfo("Public key is not created","Bạn cần tạo Public key trước khi thực hiện hành động này!!");
            return false;
        }else{
            if (encryptionRSA.checkPublicKeyValid(this.currentStringPublicKey)){
                if (this.currentStringPrivateKey==null||this.currentStringPrivateKey==""){
                    logStatus.setText("Private key not found");
                    showInfo("Private key is not created","Bạn cần tạo Private key trước khi thực hiện hành động này!!");
                    return false;
                }else{
                    if (encryptionRSA.checkPrivateKeyValid(this.currentStringPrivateKey)){
                        return true;
                    }else{
                        logStatus.setText("Private key is not valid");
                        showInfo("Private key is not valid","Khởi tạo key không thành công , bạn hãy chắc chắn rằng Private key của bạn " +
                                "phải có độ dài là "+encryptionRSA.getEncryptionBits()+" bit!!");
                        return false;
                    }
                }

            }else{
                logStatus.setText("Public key is not valid");
                showInfo("Public key is not valid","Khởi tạo key không thành công , bạn hãy chắc chắn rằng Public key của bạn " +
                        "phải có độ dài là "+encryptionRSA.getEncryptionBits()+" bit!!");
                return false;
            }
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
        if (e.getDocument() == publicKeyField.getDocument()){
            this.currentStringPublicKey = publicKeyField.getText();
            if (publicKeyField.getText().isEmpty()) {
                this.currentStringPublicKey = null;
            }
        } else if (e.getDocument() == privateKeyField.getDocument()) {
            this.currentStringPrivateKey = privateKeyField.getText();
            if (privateKeyField.getText().isEmpty()) {
                this.currentStringPrivateKey = null;
            }
//            System.out.println(this.currentStringPrivateKey);
        } else if (e.getDocument() == plainText.getDocument()) {
            this.currentStringPlain = plainText.getText();
            if (plainText.getText().isEmpty()) {
                this.currentStringPlain = null;
            }
//            System.out.println(this.currentStringPlain);
        } else if (e.getDocument() == cipherText.getDocument()) {
            this.currentStringCipher = cipherText.getText();
            if (cipherText.getText().isEmpty()) {
                this.currentStringCipher = null;
            }
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        if (e.getDocument() == publicKeyField.getDocument()){
            this.currentStringPublicKey = publicKeyField.getText();
            if (publicKeyField.getText().isEmpty()) {
                this.currentStringPublicKey = null;
            }
        } else if (e.getDocument() == privateKeyField.getDocument()) {
            this.currentStringPrivateKey = privateKeyField.getText();
            if (privateKeyField.getText().isEmpty()) {
                this.currentStringPrivateKey = null;
            }
//            System.out.println(this.currentStringPrivateKey);
        }else if (e.getDocument() == plainText.getDocument()) {
            this.currentStringPlain = plainText.getText();
            if (plainText.getText().isEmpty()) {
                this.currentStringPlain = null;
            }
//            System.out.println(this.currentStringPlain);
        } else if (e.getDocument() == cipherText.getDocument()) {
            this.currentStringCipher = cipherText.getText();
            if (cipherText.getText().isEmpty()) {
                this.currentStringCipher = null;
            }
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        if (e.getDocument() == publicKeyField.getDocument()){
            this.currentStringPublicKey = publicKeyField.getText();
            if (publicKeyField.getText().isEmpty()) {
                this.currentStringPublicKey = null;
            }
        }  else if (e.getDocument() == privateKeyField.getDocument()) {
            this.currentStringPrivateKey = privateKeyField.getText();
            if (privateKeyField.getText().isEmpty()) {
                this.currentStringPrivateKey = null;
            }
//            System.out.println(this.currentStringPrivateKey);
        }else if (e.getDocument() == plainText.getDocument()) {
            this.currentStringPlain = plainText.getText();
            if (plainText.getText().isEmpty()) {
                this.currentStringPlain = null;
            }
//            System.out.println(this.currentStringPlain);
        } else if (e.getDocument() == cipherText.getDocument()) {
            this.currentStringCipher = cipherText.getText();
            if (cipherText.getText().isEmpty()) {
                this.currentStringCipher = null;
            }
        }
    }

    public void showOptionUI(String subKey){
        JPanel Lpanel = new JPanel();

        JLabel keyLabel = new JLabel();
        keyLabel.setText("Public Key");
        JLabel subKeyLabel = new JLabel();
        subKeyLabel.setText(subKey);

        privateKeyField = new JTextField();
        privateKeyField.setPreferredSize(new Dimension(280, 40));
        privateKeyField.getDocument().addDocumentListener(this);
        JLabel orLabel = new JLabel();

//        createSubKeyButton  = new JButton("Create "+subKey+" Key");
//        createSubKeyButton.setBackground(Color.WHITE);
//        createSubKeyButton.setPreferredSize(new Dimension(100, 20));
//        createSubKeyButton.addActionListener(this);
//        keyPanel.add(keyLabel,BorderLayout.NORTH);
        Lpanel.setLayout(new GridLayout(6,1));
        Lpanel.setPreferredSize(new Dimension(300, 225));
//        Lpanel.setBackground(new Color(118, 241, 224));
        Lpanel.add(keyLabel);
        Lpanel.add(publicKeyField);
        Lpanel.add(subKeyLabel);
        Lpanel.add(privateKeyField);
        Lpanel.add(createKeyButton);
//        Lpanel.add(createSubKeyButton);
        keyPanel.add(Lpanel);

    }
}