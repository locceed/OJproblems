import java.util.Scanner;

public class problem13 {
    public static void main(String[] args) {
        paintMap(flagMap(getMap()));
    }
    public static char[][] getMap() {
        Scanner scanner = new Scanner(System.in);
        String initCondition = scanner.nextLine();
        int m = Integer.parseInt(initCondition.split(" ")[0]);
        int n = Integer.parseInt(initCondition.split(" ")[1]);
        char[][] map = new char[m][n];
        char[] temp;
        for (int i = 0;i < m;i++){
            temp = scanner.nextLine().toCharArray();
            if (n >= 0) System.arraycopy(temp, 0, map[i], 0, n);
        }
        return map;
    }
    public static int[][] getAdjacentLocation(int x, int y, int m, int n){
        int[][] result = new int[][]{{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};
        if((x + 1) < m){
            result[0][0] = x + 1;
            result[0][1] = y;
            if((y + 1) < n){
                result[1][0] = x + 1;
                result[1][1] = y + 1;
            }
        }
        if((y + 1) < n){
            result[2][0] = x;
            result[2][1] = y + 1;
            if((x - 1) >= 0){
                result[3][0] = x - 1;
                result[3][1] = y + 1;
            }
        }
        if((x - 1) >= 0){
            result[4][0] = x - 1;
            result[4][1] = y;
            if((y - 1) >= 0){
                result[5][0] = x - 1;
                result[5][1] = y - 1;
            }
        }
        if((y - 1) >= 0){
            result[6][0] = x;
            result[6][1] = y - 1;
            if((x + 1) < m){
                result[7][0] = x + 1;
                result[7][1] = y - 1;
            }
        }
        return result;
    }//先列再行 .表示超界   按(右下左上)的顺序输出    x,y从0开始,m,n从1开始
    public static char[][] flagMap(char[][] map){
        int m = map.length;
        int n = map[0].length;
        char[][] flagMap = new char[m][n];
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                flagMap[i][j] = '-';
            }
        }
        int[][] temp;
        int x;
        int y;
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                if(map[i][j] == '*'){
                    flagMap[i][j] = 'F';
                    temp = getAdjacentLocation(i,j,m,n);
                    for (int k = 0; k < 8;k++){
                        if(temp[k][0] != -1){
                            switch (flagMap[temp[k][0]][temp[k][1]]){
                                case 'F' :
                                    break;
                                case '-' :
                                    flagMap[temp[k][0]][temp[k][1]] = '1';
                                    break;
                                case '1' :
                                    flagMap[temp[k][0]][temp[k][1]] = '2';
                                    break;
                                case '2' :
                                    flagMap[temp[k][0]][temp[k][1]] = '3';
                                    break;
                                case '3' :
                                    flagMap[temp[k][0]][temp[k][1]] = '4';
                                    break;
                                case '4' :
                                    flagMap[temp[k][0]][temp[k][1]] = '5';
                                    break;
                                case '5' :
                                    flagMap[temp[k][0]][temp[k][1]] = '6';
                                    break;
                                case '6' :
                                    flagMap[temp[k][0]][temp[k][1]] = '7';
                                    break;
                                case '7' :
                                    flagMap[temp[k][0]][temp[k][1]] = '8';
                                    break;
                            }
                        }
                    }
                }
            }
        }
        return flagMap;
    }
    public static void paintMap(char[][] map){
        int n = map[0].length;
        StringBuilder temp = new StringBuilder();
        for (char[] chars : map) {
            for (int j = 0; j < n; j++) {
                temp.append(chars[j]);
            }
            System.out.println(temp);
            temp = new StringBuilder();
        }
    }
}
/*
直接遍历，没有思路
麻了竟然遇到超时的问题,现在解决了,少用类型转换
 */
/*
    废弃的代码
    public static char[][] flagMap1(char[][] map){
        long time1 = System.currentTimeMillis();
        int m = map.length;
        int n = map[0].length;
        char[][] flagMap = new char[m][n];
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                flagMap[i][j] = '-';
            }
        }
        String[] temp;
        int x;
        int y;
        byte bombCount = 48;//ascII 码
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                if(map[i][j] == '*'){
                    flagMap[i][j] = 'F';
                }
                else {
                    temp = getAdjacentLocation1(i,j,m,n);
                    for (int k = 0; k < 8;k++){
                        if(temp[k] != "."){
                            x = Integer.parseInt(temp[k].split(" ")[0]);
                            y = Integer.parseInt(temp[k].split(" ")[1]);
                            if(map[x][y] == '*'){
                                bombCount++;
                            }
                        }
                    }
                    if(bombCount != 48){
                        flagMap[i][j] = (char)bombCount;
                    }
                    else {
                        flagMap[i][j] = '-';
                    }
                    bombCount = 48;
                }

            }
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        return flagMap;
    }
    public static char[][] flagMap2(char[][] map){
        long time1 = System.currentTimeMillis();
        int m = map.length;
        int n = map[0].length;
        char[][] flagMap = new char[m][n];
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                flagMap[i][j] = '-';
            }
        }
        String[] temp = new String[8];
        String tempS = "";
        int x = 0;
        int y = 0;
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                if(map[i][j] == '*'){
                    flagMap[i][j] = 'F';
                    temp = getAdjacentLocation1(i,j,m,n);
                    for (int k = 0; k < 8;k++){
                        if(temp[k] == "."){

                        }
                        else{
                            x = Integer.parseInt(temp[k].split(" ")[0]);
                            y = Integer.parseInt(temp[k].split(" ")[1]);
                            switch (flagMap[x][y]){
                                case 'F' :
                                    break;
                                case '-' :
                                    flagMap[x][y] = '1';
                                    break;
                                case '1' :
                                    flagMap[x][y] = '2';
                                    break;
                                case '2' :
                                    flagMap[x][y] = '3';
                                    break;
                                case '3' :
                                    flagMap[x][y] = '4';
                                    break;
                                case '4' :
                                    flagMap[x][y] = '5';
                                    break;
                                case '5' :
                                    flagMap[x][y] = '6';
                                    break;
                                case '6' :
                                    flagMap[x][y] = '7';
                                    break;
                                case '7' :
                                    flagMap[x][y] = '8';
                                    break;
                            }
                        }
                    }
                }
                else {

                }
            }
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        return flagMap;
    }
    public static char[][] flagMap3(char[][] map){
        long time1 = System.currentTimeMillis();
        int m = map.length;
        int n = map[0].length;
        char[][] flagMap = new char[m][n];
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                flagMap[i][j] = '-';
            }
        }
        String[] temp;
        int x;
        int y;
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                if(map[i][j] == '*'){
                    flagMap[i][j] = 'F';
                    temp = getAdjacentLocation1(i,j,m,n);
                    for (int k = 0; k < 8;k++){
                        if(temp[k] != "."){
                            x = Integer.parseInt(temp[k].split(" ")[0]);
                            y = Integer.parseInt(temp[k].split(" ")[1]);
                            switch (flagMap[x][y]){
                                case 'F' :
                                    break;
                                case '-' :
                                    flagMap[x][y] = '1';
                                    break;
                                case '1' :
                                    flagMap[x][y] = '2';
                                    break;
                                case '2' :
                                    flagMap[x][y] = '3';
                                    break;
                                case '3' :
                                    flagMap[x][y] = '4';
                                    break;
                                case '4' :
                                    flagMap[x][y] = '5';
                                    break;
                                case '5' :
                                    flagMap[x][y] = '6';
                                    break;
                                case '6' :
                                    flagMap[x][y] = '7';
                                    break;
                                case '7' :
                                    flagMap[x][y] = '8';
                                    break;
                            }
                        }
                    }
                }
            }
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        return flagMap;
    }

    public static String[] getAdjacentLocation1(int x, int y, int m, int n){
        String[] result = new String[]{".",".",".",".",".",".",".","."};
        if((x + 1) < m){
            result[0] = String.format("%d %d",x + 1,y);
            if((y + 1) < n){
                result[1] = String.format("%d %d",x + 1,y + 1);
            }
        }
        if((y + 1) < n){
            result[2] = String.format("%d %d",x,y + 1);
            if((x - 1) >= 0){
                result[3] = String.format("%d %d",x - 1,y + 1);
            }
        }
        if((x - 1) >= 0){
            result[4] = String.format("%d %d",x - 1,y);
            if((y - 1) >= 0){
                result[5] = String.format("%d %d",x - 1,y - 1);
            }
        }
        if((y - 1) >= 0){
            result[6] = String.format("%d %d",x,y - 1);
            if((x + 1) < m){
                result[7] = String.format("%d %d",x + 1,y - 1);
            }
        }
        return result;
            }//先列再行 .表示超界   按(右下左上)的顺序输出    x,y从0开始,m,n从1开始
    public static void paintMap1(char[][] map){
        long time1 = System.currentTimeMillis();
        int m = map.length;
        int n = map[0].length;
        String temp = "";
        for (int i = 0;i < m;i++){
            for (int j = 0;j < n;j++){
                temp += map[i][j];
            }
            System.out.println(temp);
            temp = "";
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
    }
 */