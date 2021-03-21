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
}
