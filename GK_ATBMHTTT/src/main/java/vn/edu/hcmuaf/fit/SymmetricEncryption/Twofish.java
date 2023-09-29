package vn.edu.hcmuaf.fit.SymmetricEncryption;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.TwofishEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Twofish {

    private static Twofish instance;
    private static String name = "Twofish";

    private static int keyBits = 256;
    private static int ivBits = 128;
    private static SymmetricConverter converter = SymmetricConverter.getInstance();

    public static Twofish getInstance(){
        if (instance == null) return instance = new Twofish();
        return instance;
    }

    private Twofish(){}

    public String encrypt(String inputString, String keyString, String ivString) {
        byte[] key = new byte[keyBits/8];
        byte[] iv = new byte[ivBits/8];
        if ((keyString == ""||keyString== null)&&(ivString ==""||ivString==null)){
            key = converter.generateRandomKey(keyBits);
            iv = converter.generateRandomIV(ivBits);
            System.out.println("Khởi tạo Key và IV");
            System.out.println("Key của bạn là: " + converter.bytesToHex(key));
            System.out.println("IV của bạn là: " + converter.bytesToHex(iv));
        }else {
            try {
                key = converter.hexStringToByteArray(keyString);
                try {
                    iv = converter.hexStringToByteArray(ivString);
                }catch (Exception e){
                    System.out.println("Chiều dài IV không đúng với "+ivBits+" !! Vui lòng nhập đúng kích thước IV");
                }
            }catch (Exception e) {
                System.out.println("Chiều dài key không đúng với " + keyBits + " !! Vui lòng nhập đúng kích thước key");
            }

        }
        try {
            BlockCipher cipher = new TwofishEngine();
            PaddedBufferedBlockCipher paddedCipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(cipher));
            ParametersWithIV parameters = new ParametersWithIV(new KeyParameter(key), iv);
            paddedCipher.init(true, parameters);
            byte[] input = inputString.getBytes(StandardCharsets.UTF_8);
            byte[] output = new byte[paddedCipher.getOutputSize(input.length)];
            int bytesProcessed = paddedCipher.processBytes(input, 0, input.length, output, 0);
            paddedCipher.doFinal(output, bytesProcessed);
            String encryptedData = Base64.getEncoder().encodeToString(output);
            System.out.println("Regular data: "+inputString);
            System.out.println("Encrypted data: " + encryptedData);
            return encryptedData;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public String decrypt(String inputString, String keyString, String ivString) {
        if ((keyString != ""||keyString!= null)&&(ivString !=""||ivString!=null)){
            byte[] ciphertext = Base64.getDecoder().decode(inputString);
            try {
                byte[] key = converter.hexStringToByteArray(keyString);
                try {
                    byte[] iv = converter.hexStringToByteArray(ivString);
                    try {
                        BlockCipher cipher = new TwofishEngine();
                        PaddedBufferedBlockCipher paddedCipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(cipher));
                        ParametersWithIV parameters = new ParametersWithIV(new KeyParameter(key), iv);
                        paddedCipher.init(false, parameters);
                        byte[] output = new byte[paddedCipher.getOutputSize(ciphertext.length)];
                        int bytesProcessed = paddedCipher.processBytes(ciphertext, 0, ciphertext.length, output, 0);
                        paddedCipher.doFinal(output, bytesProcessed);
                        String decryptedStringData = new String(output, StandardCharsets.UTF_8);
                        System.out.println("Encrypted data: "+inputString);
                        System.out.println("Decrypted data: " + decryptedStringData);
                        return decryptedStringData;
                    }catch (Exception e){
                        System.out.println("Key hoặc IV bạn đang dùng không khớp với Key và IV đã mã hóa dữ liệu!!");
                        e.printStackTrace();
                        return null;
                    }
                }catch (Exception e){
                    System.out.println("Chiều dài IV không đúng với "+ivBits+" !! Vui lòng nhập đúng kích thước IV");
                }
            }catch (Exception e) {
                System.out.println("Chiều dài key không đúng với " + keyBits + " !! Vui lòng nhập đúng kích thước key");
            }
        }else {
            System.out.println("Key hoặc IV của bạn đang rỗng !! Vui lòng cung cấp key và IV");

        }
        return null;

    }

    public static void main(String[] args) {
//        Twofish.getInstance().encrypt("Hello anh em toi","","");
        Twofish.getInstance().decrypt("suiEc82sijx/Q1YDSGJROxkWQw/yC7zsfCvOTPLn8zQ=","F49DADC97FF1761D941C55252651C8B2542D882D421C21A23D27D4F0B87DBB2A",
                "133703914FC53EDD362F58F5C5BAE132");
    }
}
