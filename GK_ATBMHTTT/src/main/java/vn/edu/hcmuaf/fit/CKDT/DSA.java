//package vn.edu.hcmuaf.fit.CKDT;
//
//import java.security.*;
//
//public class DSA {
//    private static String name = "SHA256withDSA";
//    private static String keyName = "DSA";
//    private static int bits = 1024;
//    private PublicKey publicKey ;
//    private PrivateKey privateKey;
//    public DSA(){
//        // Tạo cặp khóa DSA
//        try {
//            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(keyName);
//            keyPairGenerator.initialize(bits);
//            KeyPair keyPair = keyPairGenerator.generateKeyPair();
//            this.privateKey =  keyPair.getPrivate();
//            this.publicKey =keyPair.getPublic();
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public PublicKey exportPublicKey(){
//        return this.publicKey;
//    }
//    public PrivateKey exportPrivateKey(){
//        return this.privateKey;
//    }
//    public static void main(String[] args) throws Exception {
//
//
//        // Dữ liệu cần ký
//        byte[] data = "Hello, DSA!".getBytes();
//
//        // Tạo chữ ký
//        Signature signature = Signature.getInstance(name);
//        signature.initSign(privateKey);
//        signature.update(data);
//        byte[] signatureBytes = signature.sign();
//
//        // Thay đổi dữ liệu
//        data[0] = 0;
//
//        // Xác minh chữ ký
//        Signature verification = Signature.getInstance(name);
//        verification.initVerify(publicKey);
//        verification.update(data);
//        boolean isVerified = verification.verify(signatureBytes);
//
//        System.out.println("Data: " + new String(data));
//        System.out.println("Signature Verification: " + isVerified);
//    }
//}