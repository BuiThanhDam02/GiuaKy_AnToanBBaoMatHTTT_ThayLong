package vn.edu.hcmuaf.fit.AsymmetricEncryption;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.util.Base64;
import javax.crypto.Cipher;


public class RSA {

    private static String name = "RSA/ECB/PKCS1Padding";
    private static int bits = 2048;
    private PublicKey publicKey ;
    private PrivateKey privateKey;

    public RSA()   {
        // Tạo cặp khóa RSA
        try{
            KeyPair keyPair = generateRSAKeyPair();
            // Lấy khóa công khai và khóa bí mật từ cặp khóa
            this.publicKey = keyPair.getPublic();
            this.privateKey = keyPair.getPrivate();
        }catch (Exception e){

        }
    }

    public PublicKey exportPublicKey(){
        return this.publicKey;
    }
    public PrivateKey exportPrivateKey(){
        return this.privateKey;
    }

    public static KeyPair generateRSAKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(bits); // Độ dài khóa 2048 bit
        return keyPairGenerator.generateKeyPair();
    }

    public byte[] encrypt(String plainText) throws Exception {
        PublicKey pubk = exportPublicKey();
        Cipher cipher = Cipher.getInstance(name);
        cipher.init(Cipher.ENCRYPT_MODE, pubk);
        return cipher.doFinal(plainText.getBytes());
    }

    public String encryptToBase64(String plainText) throws Exception{
        byte[] encryptedText = encrypt(plainText);
       return Base64.getEncoder().encodeToString(encryptedText);
    }
    public String decryptFromBase64(String plainText ) throws Exception {
        PrivateKey prik = exportPrivateKey();
        byte[] detext = Base64.getDecoder().decode(plainText);
        Cipher cipher = Cipher.getInstance(name);
        cipher.init(Cipher.DECRYPT_MODE, prik);
        byte[] decryptedBytes = cipher.doFinal(detext);
        String decryptedStringData = new String(decryptedBytes,"UTF-8");
        return decryptedStringData;
    }
    public String decrypt(byte[] byteData ) throws Exception {
        PrivateKey prik = exportPrivateKey();
        Cipher cipher = Cipher.getInstance(name);
        cipher.init(Cipher.DECRYPT_MODE, prik);
        byte[] decryptedBytes = cipher.doFinal(byteData);
        String decryptedStringData = new String(decryptedBytes,"UTF-8");
        return decryptedStringData;
    }

    public static void main(String[] args) throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

//
//        // Văn bản cần mã hóa
//        String plainText = "Hello, RSA encryption!";
//
//        // Mã hóa văn bản bằng khóa công khai
//        byte[] encryptedText = encryptRSA(plainText, publicKey);
//
//        // Giải mã văn bản bằng khóa bí mật
//        String decryptedText = decryptRSA(encryptedText, privateKey);
//
//        System.out.println("Plain Text: " + plainText);
//        System.out.println("Encrypted Text: " + Base64.getEncoder().encodeToString(encryptedText));
//        System.out.println("Decrypted Text: " + decryptedText);
    }
}