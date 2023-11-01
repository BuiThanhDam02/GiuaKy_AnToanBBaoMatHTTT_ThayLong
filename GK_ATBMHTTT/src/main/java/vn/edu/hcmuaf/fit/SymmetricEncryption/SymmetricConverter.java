package vn.edu.hcmuaf.fit.SymmetricEncryption;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

public class SymmetricConverter {
    private static SymmetricConverter instance;

    public static SymmetricConverter getInstance(){
        if (instance ==null) instance = new SymmetricConverter();
        return instance;
    }

    private SymmetricConverter(){}

    public String secretKeyToString(SecretKey secretKey) {
        byte[] encodedKey = secretKey.getEncoded();
        String rs =Base64.getEncoder().encodeToString(encodedKey);
        return rs;
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
        String rs =Base64.getEncoder().encodeToString(bytes);
        return rs;
    }
    public byte[] hexStringToByteArray(String hexString) {
        int length = hexString.length();
        byte[] bytes = new byte[length / 2];

        bytes = Base64.getDecoder().decode(hexString);
        return bytes;
    }
    public SecretKey stringToSecretKey(String keyString, String algorithmName, int bits){
        byte[] keyBytes = hexStringToByteArray(keyString);
        byte[] truncatedKeyBytes = new byte[21];

        if (algorithmName.equals(new TripleDES().getKeyAlgoName())){
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

    public SecretKey generateSecretKey(String key , String algoName , int algoBits ){
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
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
                SecretKey secretKey = null;
                if (algoName.equals("RC5")||algoName.equals("RC6")){
                    KeyGenerator keyGenerator = KeyGenerator.getInstance(algoName,"BC");
                    keyGenerator.init(algoBits, new SecureRandom()); // Đặt kích thước khóa ( bits)
                    secretKey = keyGenerator.generateKey();
                }else{
                    KeyGenerator keyGenerator = KeyGenerator.getInstance(algoName);
                    keyGenerator.init(algoBits); // Đặt kích thước khóa ( bits)
                    secretKey = keyGenerator.generateKey();

                }
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
