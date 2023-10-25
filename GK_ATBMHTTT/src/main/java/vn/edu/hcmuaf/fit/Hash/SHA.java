package vn.edu.hcmuaf.fit.Hash;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA {
    public static void main(String[] args) {
        String input = "xin chao";

        try {
            // Tạo đối tượng MessageDigest với thuật toán SHA-256
            MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");

            // Chuyển đổi đầu vào thành mảng byte
            byte[] inputBytes = input.getBytes();

            // Tính toán giá trị băm
            byte[] hashBytes = sha256Digest.digest(inputBytes);

            // Chuyển đổi giá trị băm thành dạng hexa
            String hashHex = bytesToHex(hashBytes);

            System.out.println("Input: " + input);
            System.out.println("SHA-256 Hash: " + hashHex);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}