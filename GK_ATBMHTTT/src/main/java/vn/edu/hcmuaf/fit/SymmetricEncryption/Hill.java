package vn.edu.hcmuaf.fit.SymmetricEncryption;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularMatrixException;

public class Hill {
    private final int[][] keyMatrix;

    public Hill(int[][] keyMatrix) {
        this.keyMatrix = keyMatrix;
    }

    public String encrypt(String plaintext) {
        String normalizedPlaintext = normalizeText(plaintext);
        System.out.println(normalizedPlaintext);
        int[][] plaintextVector = convertTextToVector(normalizedPlaintext);
        int[][] encryptedVector = multiplyMatrix(keyMatrix, plaintextVector);
        return convertVectorToText(encryptedVector);
    }

    public String decrypt(String ciphertext) {
        int[][] ciphertextVector = convertTextToVector(ciphertext);
        int[][] inverseKeyMatrix = calculateInverseMatrix(keyMatrix);
        int[][] decryptedVector = multiplyMatrix(inverseKeyMatrix, ciphertextVector);
        return convertVectorToText(decryptedVector);
    }

    private String normalizeText(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "");
        int paddingLength = keyMatrix.length - (text.length() % keyMatrix.length);
        if (paddingLength != keyMatrix.length) {
            StringBuilder sb = new StringBuilder(text);
            for (int i = 0; i < paddingLength; i++) {
                sb.append('X');
            }
            text = sb.toString();
        }
        return text;
    }

    private int[][] convertTextToVector(String text) {
        int numCol = text.length()/this.keyMatrix[0].length;
        int[][] vector = new int[this.keyMatrix[0].length][numCol];
        int count = 0;
        for (int col = 0; col < vector[0].length; col++) {
            for (int row = 0; row<vector.length;row++){
                vector[row][col] = (text.charAt(count)-65)%26;
                count++;
            }

        }
        return vector;
    }

    private String convertVectorToText(int[][] vector) {
        StringBuilder sb = new StringBuilder();
        for (int col = 0; col < vector[0].length; col++) {
            for (int row = 0; row<vector.length;row++){
                sb.append((char)vector[row][col]);
            }

        }
        return sb.toString();
    }

    private int[][] multiplyMatrix(int[][] matrix, int[][] vector) {
        int[][] result = new int[vector.length][vector[0].length];


        for (int col = 0; col < result[0].length; col++) {
            for (int row = 0; row<result.length;row++){
                int c = 0;
                for (int i = 0; i < matrix.length; i++) {
                    c += vector[i][col]*matrix[row][i];
                }
                result[row][col] = (c%26)+65;
            }

        }

        return result;
    }


    private int[][] calculateInverseMatrix(int[][] matrix) {
        double[][] doubleMatrix = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                doubleMatrix[i][j] = (double) matrix[i][j];
            }
        }

        RealMatrix realMatrix = new Array2DRowRealMatrix(doubleMatrix);
        try {
            RealMatrix inverseMatrix = MatrixUtils.inverse(realMatrix);
            // Calculate determinant modulo 26
            int determinant = (int) Math.round(realMatrix.getDeterminant());
            int determinantInverse = findMultiplicativeInverse(determinant, 26);

            // Calculate adjugate matrix modulo 26
            RealMatrix adjugateMatrix = inverseMatrix.scalarMultiply(determinantInverse);
            adjugateMatrix = adjugateMatrix.scalarMultiply(26);
            for (int i = 0; i < adjugateMatrix.getRowDimension(); i++) {
                for (int j = 0; j < adjugateMatrix.getColumnDimension(); j++) {
                    adjugateMatrix.setEntry(i, j, adjugateMatrix.getEntry(i, j) % 26);
                }
            }
            return inverseMatrix;
        } catch (SingularMatrixException e) {
            throw new IllegalArgumentException("Matrix is singular and does not have an inverse.");
        }
    }
    private static int findMultiplicativeInverse(int determinant, int modulo) {
        for (int i = 1; i < modulo; i++) {
            if ((determinant * i) % modulo == 1) {
                return i;
            }
        }
        return -1;
    }
    private int calculateModularInverse(int a, int m) {
        a = (a % m + m) % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[][] keyMatrix = {
                {1, 0,2},
                {10, 20,15},
                {0, 1,2}
        };

        Hill hillCipher = new Hill(keyMatrix);

        String plaintext = "retreatnow";
        String ciphertext = hillCipher.encrypt(plaintext);
        System.out.println("Ciphertext: " + ciphertext);
//        System.out.println(55%26);

        String decryptedText = hillCipher.decrypt(ciphertext);
        System.out.println("Decrypted text: " + decryptedText);
    }
}
