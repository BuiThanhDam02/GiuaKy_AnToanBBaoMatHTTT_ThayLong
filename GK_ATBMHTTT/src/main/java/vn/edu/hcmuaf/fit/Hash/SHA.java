package vn.edu.hcmuaf.fit.Hash;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA {

        public static  String check(String data){
            try {
                MessageDigest sha256Digest=MessageDigest.getInstance("SHA-256");
                byte[] inputBytes = data.getBytes();
                byte[] output=sha256Digest.digest(inputBytes);
                BigInteger num=new BigInteger(1,output);
                return num.toString(16);
            }catch (NoSuchAlgorithmException e){
                e.printStackTrace();
                return null;
            }
        }

        public static String checkFile(String path){
            try {
                MessageDigest md=MessageDigest.getInstance("SHA-256");
                DigestInputStream dis=new DigestInputStream(new BufferedInputStream(new FileInputStream(new File(path))),md);

                byte[] read=new byte[1024];
                int i;
                do{
                    i=dis.read(read);
                }while(i!=-1);
                BigInteger num=new BigInteger(1,dis.getMessageDigest().digest());
                return num.toString(16);
            }catch (NoSuchAlgorithmException | IOException e){
                e.printStackTrace();
                return null;
            }
        }

    public static void main(String[] args) {

    }


}