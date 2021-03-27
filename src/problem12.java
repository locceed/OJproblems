import java.util.Scanner;

public class problem12 {
    public static void main(String[] args){
        int numberOfMatrix = Integer.parseInt(scanner.nextLine());
        int[][] matrix = getMatrix();
        for (int i = 1;i < numberOfMatrix;i++){
            matrix = matrixMultiply(matrix,getMatrix());
        }
        paintMatrix(matrix);
    }
    public static Scanner scanner = new Scanner(System.in);//辣鸡scanner, 我只能放在全局变量里了，不然整出一堆奇怪问题
    public static int[][] getMatrix(){
        String initCondition = scanner.nextLine();
        int n = Integer.parseInt(initCondition.split(" ")[0]);
        int m = Integer.parseInt(initCondition.split(" ")[1]);
        int[][] matrix = new int[n][m];
        String temp = "";
        String[] tempLine = new String[m];
        for (int i = 0;i < n;i++){
            temp = scanner.nextLine();
            tempLine = temp.split(" ");
            for (int j = 0;j < m;j++){
                matrix[i][j] = Integer.parseInt(tempLine[j]);
            }
        }
        return matrix;
    }// int[行][列]   均使用 nextLine 读取以防止出问题
    public static int[][] matrixMultiply(int[][] matrix1,int[][] matrix2){
        int n = matrix1.length;
        int m = matrix2[0].length;
        int r = 0;
        if(matrix1[0].length == matrix2.length){
            r = matrix2.length;
        }
        else {
            return new int[][]{};
        }
        int[][] matrix = new int[n][m];
        for(int i = 0;i < n;i++){
            for(int j = 0;j < m;j++){
                for(int k = 0;k < r;k++){
                    matrix[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return matrix;
    }
    public static void paintMatrix(int[][] matrix){
        int n = matrix.length;
        int m = matrix[0].length;
        String temp = "";
        for (int i = 0;i < n;i++){
            for (int j = 0;j < m;j++){
                temp += String.format("%d ",matrix[i][j]);
            }
            System.out.println(temp.substring(0,temp.length() - 1));
            temp = "";
        }
    }
}
//直接按公式计算就行，没有思路