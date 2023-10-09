//package vn.edu.hcmuaf.fit.SymmetricEncryption;
//
//public class Hill {
//    private final int[][] keyMatrix;
//
//    public HillCipher(int[][] keyMatrix) {
//        this.keyMatrix = keyMatrix;
//    }
//
//    public String encrypt(String plaintext) {
//        String normalizedPlaintext = normalizeText(plaintext);
//        int[] plaintextVector = convertTextToVector(normalizedPlaintext);
//        int[] encryptedVector = multiplyMatrix(keyMatrix, plaintextVector);
//        return convertVectorToText(encryptedVector);
//    }
//
//    public String decrypt(String ciphertext) {
//        int[] ciphertextVector = convertTextToVector(ciphertext);
//        int[][] inverseKeyMatrix = calculateInverseMatrix(keyMatrix);
//        int[] decryptedVector = multiplyMatrix(inverseKeyMatrix, ciphertextVector);
//        return convertVectorToText(decryptedVector);
//    }
//
//    private String normalizeText(String text) {
//        text = text.toUpperCase().replaceAll("[^A-Z]", "");
//        int paddingLength = keyMatrix.length - (text.length() % keyMatrix.length);
//        if (paddingLength != keyMatrix.length) {
//            StringBuilder sb = new StringBuilder(text);
//            for (int i = 0; i < paddingLength; i++) {
//                sb.append('X');
//            }
//            text = sb.toString();
//        }
//        return text;
//    }
//
//    private int[] convertTextToVector(String text) {
//        int[] vector = new int[text.length()];
//        for (int i = 0; i < text.length(); i++) {
//            vector[i] = text.charAt(i) - 'A';
//        }
//        return vector;
//    }
//
//    private String convertVectorToText(int[] vector) {
//        StringBuilder sb = new StringBuilder();
//        for (int value : vector) {
//            sb.append((char) (value + 'A'));
//        }
//        return sb.toString();
//    }
//
//    private int[] multiplyMatrix(int[][] matrix, int[] vector) {
//        int[] result = new int[vector.length];
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < vector.length; j++) {
//                result[i] += matrix[i][j] * vector[j];
//                result[i] %= 26; // Modulo 26 for English alphabet
//            }
//        }
//        return result;
//    }
//
//    private int[][] calculateInverseMatrix(int[][] matrix) {
//        int det = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
//        int detInverse = calculateModularInverse(det, 26); // Modulo 26 for English alphabet
//        int[][] inverseMatrix = {
//                {matrix[1][1], -matrix[0][1]},
//                {-matrix[1][0], matrix[0][0]}
//        };
//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < 2; j++) {
//                inverseMatrix[i][j] *= detInverse;
//                inverseMatrix[i][j] = (inverseMatrix[i][j] % 26 + 26) % 26; // Modulo 26 for English alphabet
//            }
//        }
//        return inverseMatrix;
//    }
//
//    private int calculateModularInverse(int a, int m) {
//        a = (a % m + m) % m;
//        for (int x = 1; x < m; x++) {
//            if ((a * x) % m == 1) {
//                return x;
//            }
//        }
//        return -1; // Modular inverse does not exist
//    }
//    public static void main(String[] args) {
//        int[][] keyMatrix = {
//                {6, 24},
//                {1, 13}
//        };
//
//        HillCipher hillCipher = new HillCipher(keyMatrix);
//
//        String plaintext = "HELLO";
//        String ciphertext = hillCipher.encrypt(plaintext);
//        System.out.println("Ciphertext: " + ciphertext);
//
//        String decryptedText = hillCipher.decrypt(ciphertext);
//        System.out.println("Decrypted text: " + decryptedText);
//    }
//}
