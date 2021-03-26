import java.util.Scanner;

public class problem56 {
    public static void main(String[] args){
        char[][] map = getMap();
        char[][] map1 = deleteDeadEnd(map);
        String[] a = hasPath(map1);
        findPath(map1,a);
    }
    public static char[][] getMap(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        char[][] map = new char[n][m];
        String temp = "";
        scanner.nextLine();//把没用的行丢掉
        for (int j = 0;j < n;j++){
            temp = scanner.nextLine();
            for (int i = 0;i < m;i++){
                map[j][i] = temp.toCharArray()[i];
            }
        }
        return map;
    }// char[行][列]
    public static String[] getAdjacentLocation(int x,int y,int n,int m){//做了一点修改，现在会按(右下左上)的顺序寻路
        String[] result = new String[4];
        if((x - 1) >= 0){
            result[3] = String.format("%d %d",x - 1,y);
        }
        else {
            result[3] = "W";
        }
        if((y - 1) >= 0){
            result[2] = String.format("%d %d",x,y - 1);
        }
        else {
            result[2] = "W";
        }
        if((x + 1) < n){
            result[1] = String.format("%d %d",x + 1,y);
        }
        else {
            result[1] = "W";
        }
        if((y + 1) < m){
            result[0] = String.format("%d %d",x,y + 1);
        }
        else {
            result[0] = "W";
        }
        return result;
    }//先列再行 W表示超界，当墙处理
    public static char[][] deleteDeadEnd(char[][] map){
        int n = map.length;
        int m = map[0].length;//由于是矩阵~
        int flag = 0;
        String[] temp = new String[4];
        for (int i = 0;i < n;i++){
            for (int j = 0;j < m;j++){
                temp = getAdjacentLocation(i,j,n,m);
                for (int k = 0;k < 4;k++){
                    if(temp[k] == "W"){
                        flag += 1;
                    }
                    else if(map[Integer.parseInt(temp[k].split(" ")[0])][Integer.parseInt(temp[k].split(" ")[1])] == 'W'){
                        flag += 1;
                    }
                }
                if(flag > 2){
                    map[i][j] = 'W';
                    if((i == 0 && j == 0) || (i == (n - 1) && j == (m - 1))){
                        map[i][j] = 'C';
                    }
                }
                flag = 0;
            }
        }
        return map;
    }//注意应当新建一个数组？
    public static String[] hasPath(char[][] map){
        int n = map.length;
        int m = map[0].length;//由于是矩阵~
        boolean hasPath = false;
        boolean findPathSuccess = false;
        char tempC = ' ';
        String[] temp = new String[4];
        int pathLength = 1;
        int i1 = 0;
        int j1 = 0;
        int i = 0;
        int j = 0;
        while (true) {
            temp = getAdjacentLocation(i,j,n,m);
            for (int k = 0;k < 4;k++) {
                if (temp[k] != "W") {
                    i1 = Integer.parseInt(temp[k].split(" ")[0]);
                    j1 = Integer.parseInt(temp[k].split(" ")[1]);
                    tempC = map[i1][j1];
                    if (tempC == 'C') {
                        findPathSuccess = true;
                        map[i][j] = 'c';
                        pathLength++;
                        i = i1;
                        j = j1;
                        break;
                    }
                }
            }
            if (i == n - 1 && j == m - 1) {
                map[i][j] = 'c';
                hasPath = true;
                break;
            }
            if(!findPathSuccess){
                break;
            }
            findPathSuccess = false;
        }
        return new String[]{String.valueOf(hasPath),String.valueOf(pathLength)};
    }//虽然但是，我这次不想用arraylist了        返回的string[]中第一个是判断，第二个是长度
    public static void findPath(char[][] map,String[] judgeResult){
        if(judgeResult[0] == "false"){
            System.out.println("No");
        }
        else if(judgeResult[0] == "true"){
            int n = map.length;
            int m = map[0].length;//由于是矩阵~
            char tempC = ' ';
            String[] temp = new String[4];
            int i1 = 0;
            int j1 = 0;
            int i = 0;
            int j = 0;
            String[] path = new String[Integer.parseInt(judgeResult[1])];
            int alreadyWalked = 0;
            boolean flag = true;
            while (flag) {
                if (i == n - 1 && j == m - 1) {
                    path[alreadyWalked] = String.format("(%d,%d)",i + 1,j + 1);
                    flag = false;
                    break;
                }
                temp = getAdjacentLocation(i, j, n, m);
                for (int k = 0; k < 4; k++) {
                    if (temp[k] != "W") {
                        i1 = Integer.parseInt(temp[k].split(" ")[0]);
                        j1 = Integer.parseInt(temp[k].split(" ")[1]);
                        tempC = map[i1][j1];
                        if (tempC == 'c') {
                            map[i][j] = 'C';
                            path[alreadyWalked] = String.format("(%d,%d)",i + 1,j + 1);
                            alreadyWalked++;
                            i = i1;
                            j = j1;
                            break;
                        }
                    }
                }
            }
            System.out.println("Yes");
            for (String s : path){
                System.out.println(s);
            }
        }
    }
}
/*
这里由于这个题目给出了简单化条件：死路最多为一格
所以可以简化搜索方式：一个格子周围有3个墙就能判定是死路，把它记作W
 */