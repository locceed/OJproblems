import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem82 {
    public static void main(String[] args){
        judgeMapAndOutput(getMap());
    }
    public static char[][] getMap() {
        Reader reader = new Reader(System.in);
        int size = reader.nextInt();
        char[][] map = new char[size + 10][size + 10];
        for (int i = 5; i < (size + 5); i++){
            for (int j = 5; j < (size + 5); j++){
                map[i][j] = reader.nextChar();
            }
        }
        return map;
    }
    public static void judgeMapAndOutput(char[][] map){
        boolean notFind = true;
        for (int i = 5; i < (map.length - 5); i++){
            for (int j = 5; j < (map[i].length - 5); j++){
                if(map[i][j] == 'N'){
                    if(judgeThisStepWin(i,j,map)){
                        System.out.printf("(%d,%d)\n",(j - 4),(i - 4));//这里关于xy轴反转
                        notFind = false;
                    }
                }
            }
        }
        if(notFind){
                System.out.printf("No");
        }
    }
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
    public static boolean judgeThisStepWin(int x,int y,char[][] map){
        int maxJudgeLength = 5;
        int dirCount = 0;
        //x方向
        for (int i = 1; i <= maxJudgeLength; i++){
            if(map[x + i][y] == 'O'){
                dirCount++;
            }
            else {
                break;
            }
        }
        for (int i = 1; i <= maxJudgeLength; i++){
            if(map[x - i][y] == 'O'){
                dirCount++;
            }
            else {
                break;
            }
        }
        if(dirCount > 3){
            return true;
        }
        dirCount = 0;
        //y方向
        for (int i = 1; i <= maxJudgeLength; i++){
            if(map[x][y + i] == 'O'){
                dirCount++;
            }
            else {
                break;
            }
        }
        for (int i = 1; i <= maxJudgeLength; i++){
            if(map[x][y - i] == 'O'){
                dirCount++;
            }
            else {
                break;
            }
        }
        if(dirCount > 3){
            return true;
        }
        dirCount = 0;
        //xy方向
        for (int i = 1; i <= maxJudgeLength; i++){
            if(map[x + i][y + i] == 'O'){
                dirCount++;
            }
            else {
                break;
            }
        }
        for (int i = 1; i <= maxJudgeLength; i++){
            if(map[x - i][y - i] == 'O'){
                dirCount++;
            }
            else {
                break;
            }
        }
        if(dirCount > 3){
            return true;
        }
        dirCount = 0;
        //yx方向
        for (int i = 1; i <= maxJudgeLength; i++){
            if(map[x - i][y + i] == 'O'){
                dirCount++;
            }
            else {
                break;
            }
        }
        for (int i = 1; i <= maxJudgeLength; i++){
            if(map[x + i][y - i] == 'O'){
                dirCount++;
            }
            else {
                break;
            }
        }
        if(dirCount > 3){
            return true;
        }
        return false;
    }
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

        public char nextChar() { return next().charAt(0); }
        public int nextInt() { return Integer.parseInt(next()); }
    }
}
/*
试试自己学个Reader的实现试试
思路:写程序犯懒,直接给原来的map加上一个边框防止out of range,之后对每个点爆找就行
 */
