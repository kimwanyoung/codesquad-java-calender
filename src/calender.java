import java.util.*;

class Year {
    Scanner scan = new Scanner(System.in);
    int year;
    int month;//연도 저장할 변수
    int[] MaxDayOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};    //각 월의 최대일수 배열
    String[] Schedule = new String[MaxDayOfMonth[month]]; //일정을 등록할 배열

    Year(int year, int month) {
        this.year = year;
        this.month = month;
        if (isLeapYear(year))
            MaxDayOfMonth[1] = 29;
    }

    //월의 시작 요일 함수
    int start_of_month() {
        int year = this.year - 1;
        int month_count = 0;
        for (int i = 0; i < month - 1; i++) {
            month_count += MaxDayOfMonth[i];
        }
        int result = ((year * 365 + year / 4 - year / 100 + year / 400 + 1) + month_count) % 7;
        return result;
    }

    //윤년 계산 함수
    boolean isLeapYear(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
            return true;
        else
            return false;
    }

    //달력 출력
    void print_cal() {
        int count = 0;
        String blank = "";
        String[] day_of_week = {"sun", "mun", "tue", "wed", "thu", "fri", "sat"};
        System.out.println("        " + "<<" + year + "," + month + ">>       ");
        for (int i = 0; i < day_of_week.length; i++) {
            System.out.printf("%4s", day_of_week[i]);
        }
        System.out.println();
        System.out.println("----------------------------");
        for (int i = 0; i < start_of_month(); i++) {
            System.out.printf("%4s", blank);
            count += 1;
        }
        for (int i = 1; i <= MaxDayOfMonth[month - 1]; i++) {
            System.out.printf("%4d", i);
            count += 1;
            if (count % 7 == 0)
                System.out.println();
        }
        System.out.println();
    }

    //일정 등록 함수
    void setSchedule() {
        int day;
        System.out.println("일정을 등록할 요일을 입력해주세요 : ");
        day = scan.nextInt();
        System.out.println("일정을 입력해주세요 : ");
        Schedule[day] = scan.next();
    }

    //일정 확인 함
    void showSchedule() {
        for (int i = 0; i < Schedule.length; i++) {
            if (Schedule[i] != null) {
                System.out.println(month + "월" + i + "일 : " + Schedule[i]);
            }
        }
    }

    void showMenu() {
        System.out.println("*--------------------*");
        System.out.println("* 1.일정등록 ----------*");
        System.out.println("* 2.일정보기 ----------*");
        System.out.println("* 3.달력보기 ----------*");
        System.out.println("* 4.종료    ----------*");
        System.out.println("*--------------------*");
    }

    void selectMenu() {
        int number;
        boolean flag = true;
        while (flag) {
            showMenu();
            number = scan.nextInt();
            switch (number) {
                case 1:
                    setSchedule();
                    break;
                case 2:
                    showSchedule();
                    break;
                case 3:
                    print_cal();
                    break;
                case 4:
                    flag = false;
                    break;
                default:
                    System.out.println("error!! 다시 입력해주세요.");
                    break;
            }
            if (flag == false)
                break;
        }
    }
}

public class calender {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int year, month;
        System.out.print("연도 입력 : ");
        year = input.nextInt();
        System.out.print("월 입력 : ");
        month = input.nextInt();
        Year yyyy = new Year(year, month);
        yyyy.print_cal();
        yyyy.selectMenu();


    }

}
