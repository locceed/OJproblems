import java.util.Scanner;

public class problem20 {
    public static void main(String[] args) {
        paintMatrix(generateSerpentineMatrix(getInput()));
    }
    public static boolean flag = true;//  ture 向右下走 false  往左上走
    public static int[] getInput() {
        Scanner scanner = new Scanner(System.in);
        int[] size = new int[2];
        size[0] = scanner.nextInt();
        size[1] = scanner.nextInt();
        return size;
    }
    public static int[][] generateSerpentineMatrix(int[] size){
        int[][] matrix = new int[size[0]][size[1]];//  [m][n]
        int[] tempLocation = new int[]{0,(size[1] - 1)};//  [m][n]
        int count = 1;
        while (!(tempLocation[0] == (size[0] - 1) && tempLocation[1] == 0)){
            matrix[tempLocation[0]][tempLocation[1]] = count;
            count++;
            tempLocation = getNextLocation(tempLocation[0],tempLocation[1],size[0],size[1]);
        }
        matrix[tempLocation[0]][tempLocation[1]] = count;//最后一个数的写入

        return matrix;
    }
    public static int[] getNextLocation(int x,int y,int m,int n){
        if(flag){
            if(x < (m - 1)){
                if(y < (n - 1)){
                    x++;
                    y++;
                    return new int[]{x,y};
                }
                else {
                    x++;
                    flag = !flag;
                    return new int[]{x,y};
                }
            }
            else {
                y--;
                flag = !flag;
                return new int[]{x,y};
            }
        }
        else {
            if(y > 0){
                if(x > 0){
                    x--;
                    y--;
                    return new int[]{x,y};
                }
                else{
                    y--;
                    flag = !flag;
                    return new int[]{x,y};
                }
            }
            else {
                x++;
                flag = !flag;
                return new int[]{x,y};
            }
        }
    }//好像多重if else 会出问题来着。。。但是没有
    public static void paintMatrix(int[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0;i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%3d",matrix[i][j]);
            }
            System.out.println();
        }
    }    //已经用了%3d
}
//写完一个点用方法找下一个点就行,起始格开始终止格结束
//mdzz,我怎么直接没上传程序按了submit?