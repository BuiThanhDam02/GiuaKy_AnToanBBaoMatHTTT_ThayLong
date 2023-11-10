package vn.edu.hcmuaf.fit.SymmetricEncryption;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.TwofishEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Twofish implements Sym{

    private static String name = "Twofish";

    private static int keyBits = 256;
    private static int ivBits = 128;

    private byte[] key = new byte[keyBits/8];
    private byte[] iv = new byte[ivBits/8];
    private static SymmetricConverter converter = SymmetricConverter.getInstance();


    public Twofish(){

    }
    @Override
    public String getEncryptionName() {
        return name;
    }
    @Override
    public int getEncryptionBits() {
        return keyBits;
    }
    @Override
    public boolean checkStringKeyValid(String keyString) {
        String[] str = keyString.split("###");
        String keyIVStr = str[1];
        String keyStr = str[0];

        byte[] ikey = converter.hexStringToByteArray(keyStr);
        byte[] checkKey = new byte[keyBits/8];
        if (ikey.length==checkKey.length){
            byte[] iivkey = converter.hexStringToByteArray(keyIVStr);
            byte[] checkIvKey = new byte[ivBits/8];
            if (iivkey.length==checkIvKey.length){
                this.iv = iivkey;
                this.key=ikey;
                return true;
            }else{
                System.out.println("Chiều dài IV không đúng với "+ivBits+" !! Vui lòng nhập đúng kích thước IV");
                return false;
            }

        }else{
            System.out.println("Chiều dài key không đúng với " + keyBits + " !! Vui lòng nhập đúng kích thước key");
            return false;
        }

    }
    @Override
    public void createKey(String keyString, String ivString){
        if ((keyString == ""||keyString== null)&&(ivString ==""||ivString==null)){
            this.key = converter.generateRandomKey(keyBits);
            this.iv = converter.generateRandomIV(ivBits);
            System.out.println("Khởi tạo Key và IV");
            System.out.println("Key của bạn là: " + converter.bytesToHex(this.key));
            System.out.println("IV của bạn là: " + converter.bytesToHex(this.iv));
        }else {
            byte[] ikey = converter.hexStringToByteArray(keyString);
            byte[] checkKey = new byte[keyBits/8];
            if (ikey.length==checkKey.length){
                byte[] iivkey = converter.hexStringToByteArray(ivString);
                byte[] checkIvKey = new byte[ivBits/8];
                if (iivkey.length==checkIvKey.length){
                    this.iv = iivkey;
                    this.key=ikey;
                }else{
                    System.out.println("Chiều dài IV không đúng với "+ivBits+" !! Vui lòng nhập đúng kích thước IV");
                }

            }else{
                System.out.println("Chiều dài key không đúng với " + keyBits + " !! Vui lòng nhập đúng kích thước key");
            }
        }
    }

    public byte[] exportKey(){return this.key;}

    public byte[] exportIV(){return this.iv;}
    @Override
    public String encrypt(String inputString) {

        try {
            BlockCipher cipher = new TwofishEngine();
            PaddedBufferedBlockCipher paddedCipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(cipher));
            ParametersWithIV parameters = new ParametersWithIV(new KeyParameter(exportKey()), exportIV());
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



    @Override
    public String exportStringKey() {
        return converter.bytesToHex(this.key)+"###"+converter.bytesToHex(this.iv);
    }

    @Override
    public void createKey(String keyString) {

    }

    @Override
    public String decrypt(String inputString) {

        byte[] ciphertext = Base64.getDecoder().decode(inputString);
        try {
            BlockCipher cipher = new TwofishEngine();
            PaddedBufferedBlockCipher paddedCipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(cipher));
            ParametersWithIV parameters = new ParametersWithIV(new KeyParameter(exportKey()), exportIV());
            paddedCipher.init(false, parameters);
            byte[] output = new byte[paddedCipher.getOutputSize(ciphertext.length)];
            int bytesProcessed = paddedCipher.processBytes(ciphertext, 0, ciphertext.length, output, 0);
            bytesProcessed += paddedCipher.doFinal(output, bytesProcessed);
            String decryptedStringData = new String(output, 0, bytesProcessed, StandardCharsets.UTF_8).trim();
            System.out.println("Encrypted data: " + inputString);
            System.out.println("Decrypted data: " + decryptedStringData);
            return decryptedStringData;
        } catch (Exception e) {
            System.out.println("Key hoặc IV bạn đang dùng không khớp với Key và IV đã mã hóa dữ liệu!!");
            e.printStackTrace();
            return null;
        }

    }
    @Override
    public boolean encryptFile(String sourceFile, String destFile) throws Exception {
        if(exportKey()==null) throw new FileNotFoundException("Key not found");
        if(exportIV()==null) throw new FileNotFoundException("Key not found");
        File file=new File(sourceFile);
        if(file.isFile()){
            try (FileInputStream inputStream = new FileInputStream(sourceFile);
                 FileOutputStream outputStream = new FileOutputStream(destFile)) {

                BlockCipher cipher = new TwofishEngine();
                BufferedBlockCipher bufferedCipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(cipher));
                ParametersWithIV parameters = new ParametersWithIV(new KeyParameter(exportKey()), exportIV());
                bufferedCipher.init(true, parameters);

                byte[] inputBuffer = new byte[bufferedCipher.getBlockSize()];
                byte[] outputBuffer = new byte[bufferedCipher.getOutputSize(inputBuffer.length)];

                int bytesRead;
                while ((bytesRead = inputStream.read(inputBuffer)) != -1) {
                    int bytesProcessed = bufferedCipher.processBytes(inputBuffer, 0, bytesRead, outputBuffer, 0);
                    outputStream.write(outputBuffer, 0, bytesProcessed);
                }

                int bytesProcessed = bufferedCipher.doFinal(outputBuffer, 0);
                outputStream.write(outputBuffer, 0, bytesProcessed);
                inputStream.close();
                outputStream.flush();
                outputStream.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }


        }else{
            System.out.println("This is not a file");
            return false;
        }

    }
    @Override
    public boolean decryptFile(String sourceFile, String destFile) throws Exception {
        if(exportKey()==null) throw new FileNotFoundException("Key not found");
        if(exportIV()==null) throw new FileNotFoundException("Key not found");
        File file=new File(sourceFile);
        if(file.isFile()){
            try (FileInputStream inputStream = new FileInputStream(sourceFile);
                 FileOutputStream outputStream = new FileOutputStream(destFile)) {

                BlockCipher cipher = new TwofishEngine();
                BufferedBlockCipher bufferedCipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(cipher));
                ParametersWithIV parameters = new ParametersWithIV(new KeyParameter(exportKey()), exportIV());
                bufferedCipher.init(false, parameters);

                byte[] inputBuffer = new byte[bufferedCipher.getBlockSize()];
                byte[] outputBuffer = new byte[bufferedCipher.getOutputSize(inputBuffer.length)];

                int bytesRead;
                while ((bytesRead = inputStream.read(inputBuffer)) != -1) {
                    int bytesProcessed = bufferedCipher.processBytes(inputBuffer, 0, bytesRead, outputBuffer, 0);
                    outputStream.write(outputBuffer, 0, bytesProcessed);
                }

                int bytesProcessed = bufferedCipher.doFinal(outputBuffer, 0);
                outputStream.write(outputBuffer, 0, bytesProcessed);
                inputStream.close();
                outputStream.flush();
                outputStream.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }else{
            System.out.println("This is not a file");
            return false;
        }

    }


    public static void main(String[] args) throws Exception {
        Twofish tf =new Twofish();
        tf.checkStringKeyValid("asdasd###asdsad");
//        tf.encryptFile("en.zip","en2.zip");
//        tf.decryptFile("en2.zip","en3.zip");


    }
}
