package vn.edu.hcmuaf.fit.CKDT;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.util.Base64;

public class DSA {
//    private static String name = "SHA256withDSA";
    private static String name = "SHA-256";
//    private static String keyName = "DSA";
    private static int bits = 2048;

    public static String calculateHash(String filePath) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance(name);
        FileInputStream fis = new FileInputStream(filePath);

        byte[] buffer = new byte[bits];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            md.update(buffer, 0, bytesRead);
        }

        fis.close();
        byte[] hashBytes = md.digest();
        String base64Hash = Base64.getEncoder().encodeToString(hashBytes);

        return base64Hash;
    }

    public static boolean verifyFileIntegrity(String filePath, String expectedHash) throws NoSuchAlgorithmException, IOException {
        String fileHash = calculateHash(filePath);
        return fileHash.equals(expectedHash);
    }

    public static void main(String[] args) {
        try {
            String filePath = "file.txt";
            String expectedHash = "y8TKsEeOYqZm9/ETrQ0Oa3f9Dm+O01/gBtao13wn0iw=";

            boolean isIntegrityVerified = verifyFileIntegrity(filePath, expectedHash);

            if (isIntegrityVerified) {
                System.out.println("File integrity verified. The file has not been tampered with.");
            } else {
                System.out.println("File integrity verification failed. The file may have been tampered with.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}