package vn.edu.hcmuaf.fit.SymmetricEncryption;

import java.io.FileNotFoundException;
import java.util.Random;

public class Vigenere implements Sym{
    private String key;
    private static String name = "Vigenere";
    private static String VIETNAMESE_DIACRITIC_CHARACTERS
            = "ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴ";

    private static String VIETNAMESE_ALPHABET
            = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + VIETNAMESE_DIACRITIC_CHARACTERS
            + "abcdefghijklmnopqrstuvwxyz"
            + VIETNAMESE_DIACRITIC_CHARACTERS.toLowerCase();

    public Vigenere() {
    }
    @Override
    public String getEncryptionName() {
        return name;
    }
    @Override
    public int getEncryptionBits() {
        return 10;
    }
    @Override
    public boolean checkStringKeyValid(String keyString) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        // Kiểm tra từng ký tự trong chuỗi
        for (int i = 0; i < keyString.length(); i++) {
            char c = keyString.charAt(i);
            // Kiểm tra xem ký tự có nằm trong bảng chữ cái hay không
            if (alphabet.indexOf(c) == -1) {
                return false; // Tìm thấy ký tự không nằm trong bảng chữ cái
            }
        }
        return true;
    }
    @Override
    public void createKey(String keyString){
        this.key = normalizeKey(keyString);
    }

    @Override
    public void createKey(String keyString, String ivString) {

    }
    @Override
    public String exportStringKey() {
        return this.key;
    }

    @Override
    public boolean encryptFile(String sourceFile, String destFile) throws Exception {
        return false;
    }

    @Override
    public boolean decryptFile(String sourceFile, String destFile) throws Exception {
        return false;
    }

    @Override
    public String encrypt(String inputString) {
        StringBuilder ciphertext = new StringBuilder();
        int keyIndex = 0;

        for (int i = 0; i < inputString.length(); i++) {
            char currentChar = inputString.charAt(i);
            if (currentChar == ' ') {
                ciphertext.append(' ');
            } else if (VIETNAMESE_ALPHABET.indexOf(currentChar) >= 0) {
                int shift = VIETNAMESE_ALPHABET.indexOf(exportStringKey().charAt(keyIndex));
                char encryptedChar = VIETNAMESE_ALPHABET.charAt((VIETNAMESE_ALPHABET.indexOf(currentChar) + shift) % VIETNAMESE_ALPHABET.length());
                ciphertext.append(encryptedChar);
                keyIndex = (keyIndex + 1) % exportStringKey().length();
            } else {
                ciphertext.append(currentChar);
            }
        }

        return ciphertext.toString();
    }



    @Override
    public String decrypt(String inputString) {
        StringBuilder plaintext = new StringBuilder();
        int keyIndex = 0;

        for (int i = 0; i < inputString.length(); i++) {
            char currentChar = inputString.charAt(i);
            if (currentChar == ' ') {
                plaintext.append(' ');
            } else if (VIETNAMESE_ALPHABET.indexOf(currentChar) >= 0) {
                int shift = VIETNAMESE_ALPHABET.indexOf(exportStringKey().charAt(keyIndex));
                char decryptedChar = VIETNAMESE_ALPHABET.charAt((VIETNAMESE_ALPHABET.indexOf(currentChar) - shift + VIETNAMESE_ALPHABET.length()) % VIETNAMESE_ALPHABET.length());
                plaintext.append(decryptedChar);
                keyIndex = (keyIndex + 1) % exportStringKey().length();
            } else {
                plaintext.append(currentChar);
            }
        }

        return plaintext.toString();
    }


    private String normalizeKey(String key) {
        StringBuilder normalizedKey = new StringBuilder();
        if (!(key == "" || key == null)){
            key = key.toUpperCase();
            for (int i = 0; i < key.length(); i++) {
                char currentChar = key.charAt(i);
                if (VIETNAMESE_ALPHABET.indexOf(currentChar) >= 0) {
                    normalizedKey.append(currentChar);
                }
            }
        }else{
            Random random = new Random();
            int length = random.nextInt(10) + 1;
            String randomKey = generateRandomString(length);
            randomKey = randomKey.toUpperCase();
            for (int i = 0; i < randomKey.length(); i++) {
                char currentChar = randomKey.charAt(i);
                if (VIETNAMESE_ALPHABET.indexOf(currentChar) >= 0) {
                    normalizedKey.append(currentChar);
                }
            }
        }

        return normalizedKey.toString();
    }
    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
//    @Override
//    public void encryptFile(String inputFile, String outputFile) throws FileNotFoundException {
//        if(exportKey()==null) throw new FileNotFoundException("Key not found");
//        File file=new File(inputFile);
//        if(file.isFile()){
//            try (FileInputStream inputStream = new FileInputStream(inputFile);
//                 FileOutputStream outputStream = new FileOutputStream(outputFile)) {
//
//                byte[] inputBuffer = new byte[1024];
//                StringBuilder plaintextBuilder = new StringBuilder();
//
//                int bytesRead;
//                while ((bytesRead = inputStream.read(inputBuffer)) != -1) {
//                    String plaintext = new String(inputBuffer, 0, bytesRead);
//                    plaintextBuilder.append(plaintext);
//                }
//
//                String ciphertext = encrypt(plaintextBuilder.toString());
//                outputStream.write(ciphertext.getBytes());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }else {
//            System.out.println("This is not a file");
//        }
//
//    }
//    @Override
//    public void decryptFile(String inputFile, String outputFile) throws FileNotFoundException {
//        if(exportKey()==null) throw new FileNotFoundException("Key not found");
//        File file=new File(inputFile);
//        if(file.isFile()){
//            try (FileInputStream inputStream = new FileInputStream(inputFile);
//                 FileOutputStream outputStream = new FileOutputStream(outputFile)) {
//
//                byte[] inputBuffer = new byte[1024];
//                StringBuilder ciphertextBuilder = new StringBuilder();
//
//                int bytesRead;
//                while ((bytesRead = inputStream.read(inputBuffer)) != -1) {
//                    String ciphertext = new String(inputBuffer, 0, bytesRead);
//                    ciphertextBuilder.append(ciphertext);
//                }
//
//                String plaintext = decrypt(ciphertextBuilder.toString());
//                outputStream.write(plaintext.getBytes());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }else {
//            System.out.println("This is not a file");
//        }
//
//    }

    public static void main(String[] args) throws FileNotFoundException {
//        String key = "KEY";
        Vigenere vigenereCipher = new Vigenere();
        vigenereCipher.createKey(null);
        System.out.println(vigenereCipher.exportStringKey());

//        vigenereCipher.encryptFile("en.zip","en2.zip");
//        vigenereCipher.decryptFile("en2.zip","en3.zip");

//        String ciphertext = vigenereCipher.encrypt(plaintext);
//        System.out.println("Ciphertext: " + ciphertext);
//
//        String decryptedText = vigenereCipher.decrypt(ciphertext);
//        System.out.println("Decrypted text: " + decryptedText);
    }
}