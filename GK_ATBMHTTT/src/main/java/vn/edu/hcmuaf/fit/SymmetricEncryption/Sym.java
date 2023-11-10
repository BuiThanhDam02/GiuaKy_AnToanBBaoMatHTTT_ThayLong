package vn.edu.hcmuaf.fit.SymmetricEncryption;

public interface Sym {

    public String getEncryptionName();
    public int getEncryptionBits();
    public boolean checkStringKeyValid(String keyString);
    public boolean encryptFile(String sourceFile, String destFile) throws Exception;
    public boolean decryptFile(String sourceFile, String destFile) throws Exception;
    public String decrypt(String inputString );
    public String encrypt(String inputString );
    public String exportStringKey();
    public void createKey(String keyString);
    public void createKey(String keyString, String ivString);

}
