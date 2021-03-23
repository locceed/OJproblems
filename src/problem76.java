import java.util.Scanner;

public class problem76 {
    public static void main(String[] args) {
        findAndPaintLongestContinuous(getInput());
    }
    public static int[] getInput() {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int[] result = new int[count];
        for (int i = 0; i < count;i++){
            result[i] = scanner.nextInt();
        }
        return result;
    }
    public static void findAndPaintLongestContinuous(int[] input){
        int count = 1;
        int subIndex = 0;
        int maxIndex = 0;
        int[] result = new int[input.length];
        boolean flag = false;//false 由第一个向后写入  true  由最后一个向第一个写入
        result[0] = input[0];
        while (count < input.length){
            if(input[count - 1] > input[count]){
                if (subIndex >= maxIndex){
                    flag = !flag;
                    maxIndex = subIndex;
                }
                subIndex = -1;
            }
            subIndex++;
            if(flag){
                result[input.length - 1 - subIndex] = input[count];
            }
            else {
                result[subIndex] = input[count];
            }
            count++;
        }
        if(input[input.length - 1] > input[input.length - 2]){
            if (subIndex > maxIndex){
                maxIndex = subIndex;
                flag = !flag;
            }
        }
        //输出部分
        if(flag){
            for (int i = 0; i <= maxIndex;i++){
                System.out.printf("%d ",result[i]);
            }
        }
        else{
            for (int i = (input.length - 1); i >= (input.length - 1 - maxIndex);i--){
                System.out.printf("%d ",result[i]);
            }
        }
    }
}
//尝试用较少的内存达成目标?
/*
思路
新建一个数组,作为输出数组,开始时从前写入,若遇到非递增就从末向前写,
之后判断每次递增的个数是不是大于前一次的,若是就换边(前后)开始写,不是就直接在这边复写.
由于最坏情况是整个数组递增,所以我这样写空间算比较少的吧
 */
/*
30
1 2 3 4 5 6 7 8 9 10 4 5 6 7 8 3 4 5 6 7 8 5 6 4 3 9 0 8 5 3
 */