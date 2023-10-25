package vn.edu.hcmuaf.fit.CKDT;

import java.security.*;

public class DSA {
    public static void main(String[] args) throws Exception {
        // Tạo cặp khóa DSA
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // Lấy khóa riêng và khóa công khai
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Dữ liệu cần ký
        byte[] data = "Hello, DSA!".getBytes();

        // Tạo chữ ký
        Signature signature = Signature.getInstance("SHA256withDSA");
        signature.initSign(privateKey);
        signature.update(data);
        byte[] signatureBytes = signature.sign();

        // Thay đổi dữ liệu
        data[0] = 0;

        // Xác minh chữ ký
        Signature verification = Signature.getInstance("SHA256withDSA");
        verification.initVerify(publicKey);
        verification.update(data);
        boolean isVerified = verification.verify(signatureBytes);

        System.out.println("Data: " + new String(data));
        System.out.println("Signature Verification: " + isVerified);
    }
}