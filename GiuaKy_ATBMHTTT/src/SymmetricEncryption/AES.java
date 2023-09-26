package SymmetricEncryption;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class AES {
    private static AES instance ;
    private static String name = "AES";

    public static AES getInstance(){
        if (instance == null) instance = new AES();
        return instance;
    }

    private AES(){}

    private static String secretKeyToString(SecretKey secretKey) {
        byte[] encodedKey = secretKey.getEncoded();
        return bytesToHex(encodedKey);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
    private static byte[] hexStringToByteArray(String hexString) {
        int length = hexString.length();
        byte[] bytes = new byte[length / 2];

        for (int i = 0; i < length; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return bytes;
    }
    private static SecretKey stringToSecretKey(String keyString){
        byte[] keyBytes = hexStringToByteArray(keyString);
        // Create the SecretKey using SecretKeySpec
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
        return secretKey;
    }

    public SecretKey checkSecretKey( String key  ){
        if (!(key == "" || key == null)){
            System.out.println("KeyString tồn tại!");
            try {
                SecretKey sk = stringToSecretKey(key);
                System.out.println("Chiều dài khóa hợp lệ!");
                return sk;
            }catch (Exception e){
                System.out.println("Chiều dài khóa không hợp lệ! Vui lòng cung cấp khóa có độ dài 128 bits.");
            }

        }else {
            System.out.println("KeyString Không tồn tại!");
            try {
                KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                keyGenerator.init(128); // Đặt kích thước khóa (128 bits)
                SecretKey secretKey = keyGenerator.generateKey();
                System.out.println("Khởi tạo Key");
                System.out.println("Key của bạn là: " + secretKeyToString(secretKey));
                return secretKey;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String encrypt(String inputString, String keyString){
        SecretKey secretKey = checkSecretKey(keyString);
        if (secretKey != null){
            try {
                Cipher cipher = Cipher.getInstance("AES");

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
                e.printStackTrace();
            }

        }
        return null;
    }

    public String decrypt(String inputString, String keyString){
        if ((keyString != null || keyString != "")){
            SecretKey secretKey = checkSecretKey(keyString);
            try {
                Cipher cipher = Cipher.getInstance("AES");
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
                e.printStackTrace();
            }

        }else {
            System.out.println("Bạn phải có key để giải mã!!");
        }
        return null;
    }


    public static void main(String[] args) throws Exception {
//       String encryptedStringData = AES.getInstance().encrypt("Hello, i'm mr.Dam",null);
//       AES.getInstance().decrypt("fpMI2kMuqQMHzPw1YrjfPKW/FtXdqTmFO8oF7jXVqbM=","BEB374755A73CED098FEA5B8DC10F540");

    }
}
