import java.io.*;
import java.util.StringTokenizer;

public class problem60 {
    public static void main(String[] args){
        int numberOfMatrix = reader.nextInt();
        int exponent = reader.nextInt();
        int[][] result = getMatrix();
        for (int i = 1; i < numberOfMatrix; i++){
            result = matrixMultiplyByModulo(result,getMatrix(),mod);
        }
        result = nthPowerOfMatrix(result,exponent);
        paintMatrix(result);
    }
    public static int mod = 1000000007;
    public static Reader reader = new Reader(System.in);
    public static int[][] getMatrix(){
        int m = reader.nextInt();
        int n = reader.nextInt();
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                matrix[i][j] = reader.nextInt();
            }
        }
        return matrix;
    }// int[m行][n列]
    public static int[] decimalToBinary(int input){
        StringBuilder sb = new StringBuilder();
        while (input != 0){
            sb.append(input % 2);
            input /= 2;
        }
        int[] result = new int[sb.length()];
        for(int i = 0; i < sb.length(); i++){
            result[i] = Integer.parseInt(String.valueOf(sb.charAt(i)));
        }
        return result;
    }
    public static void paintMatrix(int[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;
        StringBuilder temp = new StringBuilder();
        for (int i = 0;i < m;i++){
            for (int j = 0;j < n;j++){
                temp.append(String.format("%d ", matrix[i][j]));
            }
            System.out.println(temp.toString());
            temp = new StringBuilder();
        }
    }//抄了自己problem12的源码  m*n
    public static int[][] nthPowerOfMatrix(int[][] matrix,int exponent){
        if(matrix.length != matrix[0].length || exponent < 1){
            return new int[1][1];
        }
        int[] binExponent = decimalToBinary(exponent);
        int[][] result = getIdentityMatrix(matrix.length);
        int i = 0;
        while (i < binExponent.length){
            if(binExponent[i] == 1){
                result = matrixMultiplyByModulo(matrix,result,mod);
            }
            matrix = matrixMultiplyByModulo(matrix,matrix,mod);
            i++;
        }
        return result;
    }
    public static int[][] getIdentityMatrix(int size){
        int[][] result = new int[size][size];
        for (int i = 0; i < size; i++){
            result[i][i] = 1;
        }
        return result;
    }
    public static int[][] matrixMultiplyByModulo(int[][] matrix1,int[][] matrix2,int modulo){
        int m = matrix1.length;
        int n = matrix2[0].length;
        int r = 0;
        if(matrix1[0].length == matrix2.length){
            r = matrix2.length;
        }
        else {
            return new int[][]{};
        }
        long temp = 0;
        int[][] matrix = new int[m][n];
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                for(int k = 0;k < r;k++){
                    temp += ((long)matrix1[i][k] * (long)matrix2[k][j]) % modulo;
                }
                matrix[i][j] = (int)(temp % modulo);
                temp = 0;
            }
        }
        return matrix;
    }//m*r r*n
    //这部分是抄题目里的源码
    private static class Reader {
        BufferedReader in;
        StringTokenizer tokenizer;

        public Reader(InputStream inputStream) {
            in = new BufferedReader(new InputStreamReader(inputStream));
        }

        private String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(in.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
/*
这道题只是要求算出模1E9+7之后的矩阵，每次乘法都模一下就行了
公式:
(a + b) % c = ((a % c) + (b % c)) % c
(a * b) % c = ((a % c) * (b % c)) % c
 */

/*
3 3
1 2 3
4 5 6
7 8 9
2 2
0 1
1 1
 */
/*

        int[][] a = nthPowerOfMatrix(getMatrix(),7);
        for (int[] b : a){
            System.out.println(Arrays.toString(b));
        }
 */