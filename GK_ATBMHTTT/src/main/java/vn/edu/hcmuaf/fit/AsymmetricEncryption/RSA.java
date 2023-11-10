package vn.edu.hcmuaf.fit.AsymmetricEncryption;

import java.io.*;
import java.security.*;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class RSA {

    private static String name = "RSA/ECB/PKCS1Padding";
    private static String keyName = "RSA";
    private static int bits = 2048;
    private PublicKey publicKey ;
    private PrivateKey privateKey;

    public RSA()   {
        // Tạo cặp khóa RSA

    }
    public void createKey(){
        try{
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(keyName);
            keyPairGenerator.initialize(bits); // Độ dài khóa 2048 bit
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            // Lấy khóa công khai và khóa bí mật từ cặp khóa
            this.publicKey = keyPair.getPublic();
            this.privateKey = keyPair.getPrivate();
        }catch (Exception e){

        }
    }
    public int getEncryptionBits(){
        return bits;
    }
    public String exportStringPublicKey()
    {
        byte[] keyBytes = exportPublicKey().getEncoded();
        String base64Key = Base64.getEncoder().encodeToString(keyBytes);
        return base64Key;
    }
    public String exportStringPrivateKey()
    {
        byte[] keyBytes = exportPrivateKey().getEncoded();
        String base64Key = Base64.getEncoder().encodeToString(keyBytes);
        return base64Key;
    }

    public PublicKey exportPublicKey(){
        return this.publicKey;
    }
    public PrivateKey exportPrivateKey(){
        return this.privateKey;
    }

    public boolean checkPublicKeyValid(String publicKeyBase64){
        try {
            byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyBase64);
            KeyFactory keyFactory = KeyFactory.getInstance(keyName);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            PublicKey publicKey = keyFactory.generatePublic(keySpec);

            // Check key size

            this.publicKey = publicKey;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean checkPrivateKeyValid(String privateKeyBase64){
        try {
            byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyBase64);
            KeyFactory keyFactory = KeyFactory.getInstance(keyName);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

            // Check key size
            this.privateKey = privateKey;
                return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public byte[] encrypt(String plainText) throws Exception {
        PublicKey pubk = exportPublicKey();
        Cipher cipher = Cipher.getInstance(name);
        cipher.init(Cipher.ENCRYPT_MODE, pubk);
        return cipher.doFinal(plainText.getBytes());
    }

    public String encryptToBase64(String plainText) {

        byte[] encryptedText = new byte[0];
        try {
            PublicKey pubk = exportPublicKey();
            Cipher cipher = Cipher.getInstance(name);
            cipher.init(Cipher.ENCRYPT_MODE, pubk);
            encryptedText = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encryptedText);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    public String decryptFromBase64(String plainText )  {
        try {
        PrivateKey prik = exportPrivateKey();
        byte[] detext = Base64.getDecoder().decode(plainText);
        Cipher cipher = Cipher.getInstance(name);
        cipher.init(Cipher.DECRYPT_MODE, prik);
        byte[] decryptedBytes = cipher.doFinal(detext);
        String decryptedStringData = new String(decryptedBytes,"UTF-8");
        return decryptedStringData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public String decrypt(byte[] byteData ) throws Exception {
        PrivateKey prik = exportPrivateKey();
        Cipher cipher = Cipher.getInstance(name);
        cipher.init(Cipher.DECRYPT_MODE, prik);
        byte[] decryptedBytes = cipher.doFinal(byteData);
        String decryptedStringData = new String(decryptedBytes,"UTF-8");
        return decryptedStringData;
    }

    public boolean encryptFile(String inputPath, String outputPath) {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            byte[] iv = new byte[16];
            IvParameterSpec spec = new IvParameterSpec(iv);
            SecretKey secretKey = keyGen.generateKey();
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, spec);

            CipherInputStream inputStream = new CipherInputStream(new BufferedInputStream(new FileInputStream(inputPath)), cipher);
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(outputPath)));

            String keyString = Base64.getEncoder().encodeToString(secretKey.getEncoded());
            dataOutputStream.writeUTF(encryptToBase64(keyString));
            dataOutputStream.writeLong(new File(inputPath).length());
            dataOutputStream.writeUTF(Base64.getEncoder().encodeToString(iv));
            byte[] buff = new byte[1024];
            int i;
            while ((i = inputStream.read(buff)) != -1) {
                dataOutputStream.write(buff, 0, 1);
            }
            inputStream.close();
            dataOutputStream.flush();
            dataOutputStream.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean decryptFile(String inputPath, String outputPath) {
        try {
            DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(inputPath)));
            String keyString = dis.readUTF();
            long size = dis.readLong();
            byte[] iv = Base64.getDecoder().decode(dis.readUTF());

            SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(decryptFromBase64(keyString)), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));

            CipherInputStream cis = new CipherInputStream(dis, cipher);
            BufferedOutputStream bof = new BufferedOutputStream(new FileOutputStream(outputPath));

            byte[] buff = new byte[1024];
            int i;
            while ((i = cis.read(buff)) != -1) {
                bof.write(buff, 0, i);
            }
            cis.close();
            bof.flush();
            bof.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

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