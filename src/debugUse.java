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
    public static void testTimeConsumed(){
        long time1 = System.currentTimeMillis();
        //

        //
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
    }//调试用
}
