//package vn.edu.hcmuaf.fit.SymmetricEncryption;
//
//public class Vigenere {
//    private final String key;
//
//    public VigenereCipher(String key) {
//        this.key = key.toUpperCase();
//    }
//
//    public String encrypt(String plaintext) {
//        plaintext = plaintext.toUpperCase();
//        StringBuilder ciphertext = new StringBuilder();
//        int keyIndex = 0;
//
//        for (int i = 0; i < plaintext.length(); i++) {
//            char currentChar = plaintext.charAt(i);
//            if (Character.isLetter(currentChar)) {
//                int shift = key.charAt(keyIndex) - 'A';
//                char encryptedChar = (char) ((currentChar - 'A' + shift) % 26 + 'A');
//                ciphertext.append(encryptedChar);
//                keyIndex = (keyIndex + 1) % key.length();
//            } else {
//                ciphertext.append(currentChar);
//            }
//        }
//
//        return ciphertext.toString();
//    }
//
//    public String decrypt(String ciphertext) {
//        ciphertext = ciphertext.toUpperCase();
//        StringBuilder plaintext = new StringBuilder();
//        int keyIndex = 0;
//
//        for (int i = 0; i < ciphertext.length(); i++) {
//            char currentChar = ciphertext.charAt(i);
//            if (Character.isLetter(currentChar)) {
//                int shift = key.charAt(keyIndex) - 'A';
//                char decryptedChar = (char) ((currentChar - 'A' - shift + 26) % 26 + 'A');
//                plaintext.append(decryptedChar);
//                keyIndex = (keyIndex + 1) % key.length();
//            } else {
//                plaintext.append(currentChar);
//            }
//        }
//
//        return plaintext.toString();
//    }
//    public static void main(String[] args) {
//        String key = "KEY";
//        VigenereCipher vigenereCipher = new VigenereCipher(key);
//
//        String plaintext = "HELLO";
//        String ciphertext = vigenereCipher.encrypt(plaintext);
//        System.out.println("Ciphertext: " + ciphertext);
//
//        String decryptedText = vigenereCipher.decrypt(ciphertext);
//        System.out.println("Decrypted text: " + decryptedText);
//    }
//}
