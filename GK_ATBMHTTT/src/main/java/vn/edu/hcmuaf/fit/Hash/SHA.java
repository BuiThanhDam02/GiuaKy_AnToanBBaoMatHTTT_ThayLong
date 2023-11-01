package vn.edu.hcmuaf.fit.Hash;
import java.math.BigInteger;
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
            BigInteger outputs = new BigInteger(1,hashBytes);
            // Chuyển đổi giá trị băm thành dạng hexa
            String hashHex = outputs.toString(16);
            System.out.println("Input: " + input);
            System.out.println("SHA-256 Hash: " + hashHex);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


}