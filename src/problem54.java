import java.util.Scanner;

public class problem54 {
    public static void main(String[] args){
        String[] input = getInput();
        char luckyNumber = input[0].toCharArray()[0];
        System.out.printf(charToString(tweaks(sortInDescendingOrder(tweaks(input[1].toCharArray(),luckyNumber,'~')),'~',luckyNumber)));
    }
    public static String[] getInput(){
        Scanner scanner = new Scanner(System.in);
        String[] result = new String[2];
        result[0] = scanner.nextLine();
        result[1] = scanner.nextLine();
        return result;
    }
    public static char[] sortInDescendingOrder(char[] list){
        int i = 1;
        char temp = ' ';
        while (i < list.length) {
            if(list[i - 1] < list[i]){
                temp = list[i - 1];
                list[i - 1] = list[i];
                list[i] = temp;
                i = 0;
            }
            i++;
        }
        return list;
    }//按照ASCII排序char
    public static char[] tweaks(char[] list,char find,char tweaksTo){
        for(int i = 0; i < list.length; i++){
            if(list[i] == find){
                list[i] = tweaksTo;
            }
        }
        return list;
    }
    public static String charToString(char[] list){
        StringBuilder sb = new StringBuilder();
        for(char c : list){
            sb.append(c);
        }
        return sb.toString();
    }
}
//JAVA 不支持操作符重载可还行,麻了,那我就用ASCII排序