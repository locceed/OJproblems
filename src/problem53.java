import java.util.Scanner;

public class problem53 {
    public static void main(String[] args){
        int[] input = getInput();
        int daysInSingleWeek = input[0];
        int[] finalDate = new int[]{input[1],input[2],input[3]};
        System.out.printf("%d",getDayOfTheWeek(getCountOfLeapedDays(finalDate),daysInSingleWeek));
    }
    public static int[] getInput(){
        Scanner scanner = new Scanner(System.in);
        int[] result = new int[4];
        result[0] = Integer.parseInt(scanner.nextLine());
        String[] date = scanner.nextLine().split(" ");
        for(int i = 1; i < 4; i++){
            result[i] = Integer.parseInt(date[i - 1]);
        }
        return result;
    }
    public static int getDayOfTheWeek(int leapedDays, int daysInSingleWeek){
        if(leapedDays % daysInSingleWeek == 0){
            return daysInSingleWeek;
        }
        else {
            return leapedDays % daysInSingleWeek;
        }
    }
    public static int getCountOfLeapedDays(int[] parameters){
        int leapedDays = 0;
        int year = 1;
        int month = 1;
        while (year < parameters[0]){
            if(isLeapYear(year)){
                leapedDays += 366;
            }
            else {
                leapedDays += 365;
            }
            year++;
        }
        boolean isLeaped = isLeapYear(year);
        while (month < parameters[1]){
            leapedDays += getDaysInMonth(month,isLeaped);
            month++;
        }
        leapedDays += parameters[2];
        return leapedDays;
    }
    public static boolean isLeapYear(int year){
        if(year % 4 != 0){
            return false;
        }
        else if(year % 100 != 0){
            return true;
        }
        else if(year % 400 != 0){
            return false;
        }
        else {
            return true;
        }
    }
    public static int getDaysInMonth(int month,boolean isLeapYear){
        switch (month){
            case 1 :{
                return 31;
            }
            case 2 :{
                if(isLeapYear){
                    return 29;
                }
                else {
                    return 28;
                }
            }
            case 3 :{
                return 31;
            }
            case 4 :{
                return 30;
            }
            case 5 :{
                return 31;
            }
            case 6 :{
                return 30;
            }
            case 7 :{
                return 31;
            }
            case 8 :{
                return 31;
            }
            case 9 :{
                return 30;
            }
            case 10 :{
                return 31;
            }
            case 11 :{
                return 30;
            }
            case 12 :{
                return 31;
            }
            default :{
                return 0;
            }
        }
    }
}
/*
思路是找到两个日期间经过的天数,然后取个余数就是星期了,注意整除不是星期零
调试：
1900年和0001年都是星期一开头
7
100 2 28
7
100 3 1
7
400 2 28
7
400 3 1
 */