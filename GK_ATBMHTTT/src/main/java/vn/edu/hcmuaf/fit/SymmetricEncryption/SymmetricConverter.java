package vn.edu.hcmuaf.fit.SymmetricEncryption;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class SymmetricConverter {
    private static SymmetricConverter instance;

    public static SymmetricConverter getInstance(){
        if (instance ==null) instance = new SymmetricConverter();
        return instance;
    }

    private SymmetricConverter(){}

    public String secretKeyToString(SecretKey secretKey) {
        byte[] encodedKey = secretKey.getEncoded();
        return bytesToHex(encodedKey);
    }

    public byte[] generateRandomKey(int bits) {
        byte[] key = new byte[bits/8]; // Twofish sử dụng khóa 256-bit (32 bytes)
        SecureRandom random = new SecureRandom();
        random.nextBytes(key);
        return key;
    }

    public byte[] generateRandomIV(int bits) {
        byte[] iv = new byte[bits/8]; // Độ dài vector khởi tạo (IV) cần phù hợp với khối dữ liệu của thuật toán
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        return iv;
    }

    public String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
    public byte[] hexStringToByteArray(String hexString) {
        int length = hexString.length();
        byte[] bytes = new byte[length / 2];

        for (int i = 0; i < length; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return bytes;
    }
    public SecretKey stringToSecretKey(String keyString, String algorithmName, int bits){
        byte[] keyBytes = hexStringToByteArray(keyString);
        byte[] truncatedKeyBytes = new byte[21];

        if (algorithmName.equals(TripleDES.getInstance().getKeyAlgoName())){
            System.arraycopy(keyBytes, 0, truncatedKeyBytes, 0, 21);
        }else{
            truncatedKeyBytes = keyBytes;
        }

        int keyLength = truncatedKeyBytes.length * 8;
        System.out.println(keyLength);
        if (keyLength == bits){
            SecretKey secretKey = new SecretKeySpec(keyBytes, algorithmName);
            return secretKey;
        }else{
            System.out.println("Chiều dài key của thuật toán "+algorithmName+" phải có độ dài là: "+bits);
            return null;
        }
    }

    public SecretKey checkSecretKey( String key , String algoName , int algoBits ){
        if (!(key == "" || key == null)){
            System.out.println("KeyString tồn tại!");
            try {
                SecretKey sk = stringToSecretKey(key,algoName,algoBits);
                System.out.println("Chiều dài khóa hợp lệ!");
                return sk;
            }catch (Exception e){
                System.out.println("Chiều dài khóa không hợp lệ! Vui lòng cung cấp khóa có độ dài "+algoBits+" bits.");
            }

        }else {
            System.out.println("KeyString Không tồn tại!");
            try {

                KeyGenerator keyGenerator = KeyGenerator.getInstance(algoName);
                keyGenerator.init(algoBits); // Đặt kích thước khóa ( bits)
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
}
