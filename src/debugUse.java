public class debugUse {
    public static void paint(char[][] map){
        int n = map.length;
        int m = map[0].length;
        System.out.println();
        for (int i = 0;i < n;i++){
            for (int j = 0;j < m;j++){
                System.out.printf(map[i][j] + " ");
            }
            System.out.println();
        }
    }//调试用
    public static void paint(String[] strings){
        for (int i = 0;i < strings.length;i++){
            System.out.println(strings[i]);
        }
    }//调试用
    public static void paint(int[] ints){
        for (int i = 0;i < ints.length;i++){
            System.out.printf(ints[i] + " ");
        }
    }//调试用
    public static void paint(int[][] map){
        int n = map.length;
        int m = map[0].length;
        System.out.println();
        for (int i = 0;i < n;i++){
            for (int j = 0;j < m;j++){
                System.out.printf(map[i][j] + " ");
            }
            System.out.println();
        }
    }//调试用
    public static void testTimeConsumed(){
        long time1 = System.currentTimeMillis();
        //

        //
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
    }//调试用

    public static int[][] matrixMultiply(int[][] matrix1,int[][] matrix2){
        int m = matrix1.length;
        int n = matrix2[0].length;
        int r = 0;
        if(matrix1[0].length == matrix2.length){
            r = matrix2.length;
        }
        else {
            return new int[][]{};
        }
        int[][] matrix = new int[m][n];
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                for(int k = 0;k < r;k++){
                    matrix[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return matrix;
    }//抄了自己problem12的源码  m*r r*n
}
