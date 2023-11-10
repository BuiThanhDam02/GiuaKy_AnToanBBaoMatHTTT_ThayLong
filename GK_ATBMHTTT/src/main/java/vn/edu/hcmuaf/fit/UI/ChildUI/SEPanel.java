package vn.edu.hcmuaf.fit.UI.ChildUI;

import vn.edu.hcmuaf.fit.SymmetricEncryption.*;

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


public class SEPanel extends JPanel implements ActionListener, DocumentListener {

    String name ;
    Sym encryptionSym ;
    String currentStringKey = null;
    String currentStringIVKey = null;
    String currentStringPlain = null;
    String currentStringCipher = null;

    JButton encryptButton;
    JButton decryptButton;
    JButton createKeyButton;
//    JButton createSubKeyButton;

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

    JTextField subKeyField;
    public SEPanel(String nameEncryption){
        this.name = nameEncryption;
        setEncryption(nameEncryption);
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
        keyLabel.setText("Key");
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


        keyField = new JTextField();
        keyField.setPreferredSize(new Dimension(280, 40));
        keyField.getDocument().addDocumentListener(this);



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

        if (nameEncryption.equals("Twofish")){
            showOptionUI("Iv");
        }else{
            JPanel Lpanel = new JPanel();
//            Lpanel.setBackground(new Color(118, 241, 224));
            Lpanel.setPreferredSize(new Dimension(300, 225));
//        keyPanel.add(keyLabel,BorderLayout.NORTH);
            Lpanel.setLayout(new GridLayout(5,1));
            Lpanel.add(keyLabel);
            Lpanel.add(keyField);
            Lpanel.add(orLabel);
            Lpanel.add(createKeyButton);
            keyPanel.add(Lpanel);
        }

        cEastBot.add(keyPanel);

        cEastTop.setBorder(new MatteBorder(0, 1, 1, 0, new Color(197, 197, 197)));
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
    public void setEncryption(String nameEncryption){
//        String[] options = { "AES", "Blowfish", "RC5", "RC6", "DESede/ECB/PKCS5Padding", "Twofish","Vigenere","Hill" };
        if (nameEncryption.equals("AES")){
            encryptionSym = new AES();
        } else if (nameEncryption.equals("Blowfish")) {
            encryptionSym = new Blowfish();
        } else if (nameEncryption.equals("RC5")) {
            encryptionSym = new RC5();
        } else if (nameEncryption.equals("RC6")) {
            encryptionSym = new RC6();
        } else if (nameEncryption.equals("DESede/ECB/PKCS5Padding")) {
            encryptionSym =  new TripleDES();
        } else if (nameEncryption.equals("Twofish")) {
            encryptionSym = new Twofish();
        } else if (nameEncryption.equals("Vigenere")) {
            encryptionSym = new Vigenere();
        } else if (nameEncryption.equals("Hill")) {

        }
    }
    public void showInfo(String title, String maessage ){
        JOptionPane.showMessageDialog(this, maessage, title, JOptionPane.INFORMATION_MESSAGE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createKeyButton) {
            if (encryptionSym.getEncryptionName().equals("Twofish")){
                encryptionSym.createKey(null,null);
                String combined = encryptionSym.exportStringKey();
                String[] str = combined.split("###");
                this.currentStringIVKey = str[1];
                this.currentStringKey = str[0];
                this.keyField.setText(this.currentStringKey);
                this.subKeyField.setText(this.currentStringIVKey);
            }else{
                encryptionSym.createKey(null);
                this.currentStringKey = encryptionSym.exportStringKey();
                this.keyField.setText(this.currentStringKey);
            }

            this.logStatus.setText("Created key successfully!!");
//            System.out.println(encryptionSym.getEncryptionName()+": "+encryptionSym.exportStringKey());
        } else if (e.getSource() == encryptButton) {
            if (checkValidKey()){
                if (checkValidText(this.currentStringPlain,"Encrypt")){
                    String cipherContent = encryptionSym.encrypt(this.currentStringPlain);
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
                    String plaineContent = encryptionSym.decrypt(this.currentStringCipher);
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
                        boolean r = encryptionSym.encryptFile(this.textEnFileArea.getText(),resultPath);
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
                        boolean r = encryptionSym.decryptFile(this.textDeFileArea.getText(),resultPath);
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
//    public void readFile(String filePath) {
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            StringBuilder content = new StringBuilder();
//
//            while ((line = reader.readLine()) != null) {
//                content.append(line).append('\n');
//            }
//
//            textArea.setText(content.toString());
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
    public boolean checkEcryption(){
        if (encryptionSym.getEncryptionName().equals("Vigenere")){
            logStatus.setText("Encryption does not have this action!!");
            showInfo("Encryption does not have this action!!","Thuật toán Vigenere không có mã hóa file!!");
            return false;
        }else {
            return true;
        }
    }
    public boolean checkValidKey(){
        if (this.currentStringKey==null||this.currentStringKey==""){
            logStatus.setText("Key not found");
            showInfo("Key is not created","Bạn cần tạo key trước khi thực hiện hành động này!!");
            return false;
        }else{
            boolean validKey ;
            if (encryptionSym.getEncryptionName().equals("Twofish")){
                if (this.currentStringIVKey==null||this.currentStringIVKey==""){
                    logStatus.setText("Iv not found");
                    showInfo("Iv is not created","Bạn cần tạo Iv trước khi thực hiện hành động này!!");
                    return false;
                }else{
                    validKey = encryptionSym.checkStringKeyValid(this.currentStringKey+"###"+this.currentStringIVKey);
                }
            }else{
                validKey = encryptionSym.checkStringKeyValid(this.currentStringKey);
            }
            if (validKey){
                return true;
            }else{
                logStatus.setText("Key is not valid");
                showInfo("Key is not valid","Khởi tạo key không thành công , bạn hãy chắc chắn rằng key của bạn " +
                        "phải có độ dài là "+encryptionSym.getEncryptionBits()+" bit!!");
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
        if (e.getDocument() == keyField.getDocument()){
            this.currentStringKey = keyField.getText();
            if (keyField.getText().isEmpty()) {
                this.currentStringKey = null;
            }
        } else if (subKeyField != null&&e.getDocument() == subKeyField.getDocument()) {

            this.currentStringIVKey = subKeyField.getText();
            if (subKeyField.getText().isEmpty()) {
                this.currentStringIVKey = null;
            }
//            System.out.println(this.currentStringIVKey);
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
        if (e.getDocument() == keyField.getDocument()){
            this.currentStringKey = keyField.getText();
            if (keyField.getText().isEmpty()) {
                this.currentStringKey = null;
            }
        } else if (subKeyField != null&&e.getDocument() == subKeyField.getDocument()) {
            this.currentStringIVKey = subKeyField.getText();
            if (subKeyField.getText().isEmpty()) {
                this.currentStringIVKey = null;
            }
//            System.out.println(this.currentStringIVKey);
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
        if (e.getDocument() == keyField.getDocument()){
            this.currentStringKey = keyField.getText();
            if (keyField.getText().isEmpty()) {
                this.currentStringKey = null;
            }
        }  else if (subKeyField != null&&e.getDocument() == subKeyField.getDocument()) {
            this.currentStringIVKey = subKeyField.getText();
            if (subKeyField.getText().isEmpty()) {
                this.currentStringIVKey = null;
            }
//            System.out.println(this.currentStringIVKey);
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
        keyLabel.setText("Key");
        JLabel subKeyLabel = new JLabel();
        subKeyLabel.setText(subKey);

        subKeyField = new JTextField();
        subKeyField.setPreferredSize(new Dimension(280, 40));
        subKeyField.getDocument().addDocumentListener(this);
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
        Lpanel.add(keyField);
        Lpanel.add(subKeyLabel);
        Lpanel.add(subKeyField);
        Lpanel.add(createKeyButton);
//        Lpanel.add(createSubKeyButton);
        keyPanel.add(Lpanel);

    }
}