package vn.edu.hcmuaf.fit.SymmetricEncryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Blowfish {
    private static Blowfish instance ;
    private static String name = "Blowfish";
    private static int bits = 128;
    private static SymmetricConverter converter = SymmetricConverter.getInstance();

    public static Blowfish getInstance(){
        if (instance == null) instance = new Blowfish();
        return instance;
    }

    private Blowfish(){}

    public String encrypt(String inputString, String keyString){
        SecretKey secretKey = converter.checkSecretKey(keyString,name,bits);
        if (secretKey != null){
            try {
                Cipher cipher = Cipher.getInstance(name);

                // Dữ liệu cần mã hóa
                byte[] dataToEncrypt = inputString.getBytes(StandardCharsets.UTF_8);

                // Mã hóa dữ liệu
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                byte[] encryptedData = cipher.doFinal(dataToEncrypt);
                String encryptedStringData = Base64.getEncoder().encodeToString(encryptedData);
                // In ra dữ liệu đã mã hóa
                System.out.println("Regular data: "+inputString);
                System.out.println("Encrypted data: " + encryptedStringData);
                return encryptedStringData;
            }catch (Exception e){
                System.out.println("Lỗi khởi tạo key!!");
                e.printStackTrace();
            }

        }
        return null;
    }

    public String decrypt(String inputString, String keyString){
        if ((keyString != null || keyString != "")){
            SecretKey secretKey = converter.checkSecretKey(keyString,name,bits);
            try {
                Cipher cipher = Cipher.getInstance(name);
                // Dữ liệu cần giải mã
                byte[] receivedData = Base64.getDecoder().decode(inputString);

                // Giải mã dữ liệu

                cipher.init(Cipher.DECRYPT_MODE, secretKey);
                byte[] decryptedData = cipher.doFinal(receivedData);

                String decryptedStringData = new String(decryptedData, StandardCharsets.UTF_8);
                // In ra dữ liệu
                System.out.println("Encrypted data: "+inputString);
                System.out.println("Decrypted data: " + decryptedStringData);
                return decryptedStringData;
            }catch (Exception e){
                System.out.println("Key giải mã bạn nhập vào khác với key đã mã hóa dữ liệu !! Vui lòng nhập đúng key.");
                e.printStackTrace();
            }

        }else {
            System.out.println("Bạn phải có key để giải mã!!");
        }
        return null;
    }

    public static void main(String[] args) {
//        Blowfish.getInstance().encrypt("Hello anh chi em",null);
//        Blowfish.getInstance().decrypt("UouNNoJiM92dvBZ7xEt3da9IerbR3qWA","B37EF459B25DEB50B4A2AFD6838F3E34");
    }
}
