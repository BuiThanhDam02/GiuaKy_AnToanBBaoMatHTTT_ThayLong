package SymmetricEncryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.util.Base64;

public class TripleDES {

    private static TripleDES instance;
    private static String name = "DESede/ECB/PKCS5Padding";

    private static String keyName = "DESede";
    private static int bits = 168;
    private static SymmetricConverter converter = SymmetricConverter.getInstance();

    public static TripleDES getInstance(){
        if (instance == null) return instance = new TripleDES();
        return instance;
    }

    private TripleDES(){}

    public String getKeyAlgoName(){
        return keyName;
    }

    public String encrypt(String inputString, String keyString){
        SecretKey secretKey = converter.checkSecretKey(keyString,keyName,bits);
        if (secretKey != null){
            try {
                Cipher cipher = Cipher.getInstance(name);

                // Dữ liệu cần mã hóa
                byte[] dataToEncrypt = inputString.getBytes();

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
            SecretKey secretKey = converter.checkSecretKey(keyString,keyName,bits);
            try {
                Cipher cipher = Cipher.getInstance(name);
                // Dữ liệu cần giải mã
                byte[] receivedData = Base64.getDecoder().decode(inputString);

                // Giải mã dữ liệu

                cipher.init(Cipher.DECRYPT_MODE, secretKey);
                byte[] decryptedData = cipher.doFinal(receivedData);

                String decryptedStringData = new String(decryptedData);
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
//          TripleDES.getInstance().encrypt("Hello everybody!!",null);
//          TripleDES.getInstance().decrypt("eBJOWZkVVxXL4TLTmZVaOuTWjhLqpZv5","75A81304D5649EC1F731DF5786EADA708AE5106451104908");
    }

}
