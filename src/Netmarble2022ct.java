import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 빙고 3가지
 * 매일 초기화되는 일간 빙고
 * 매주 초기화되는 주간 빙고
 * 매달 초기화되는 월간 빙고
 * <p>
 * 모든 빙고는 3X3 형태.
 * 일간 빙고는 1~9번 / 주간 빙고는 10번~18번 / 월간 빙고는 19번~27번
 * 가로 세로 대각선으로 3개의 체크를 만들 때마다 1점
 * <p>
 * 2차원 정수 logs 주어짐
 * 1<=logs<=300
 * logs의 원소는 [n,m] 형태의 1차원 정수배열
 * 2022년 12월 n 일에 m 번의 미션 성공했다는 뜻
 * 1<=n<=31
 * 1<=m<=27
 * logs의 원소들은 날짜순으로 정렬되어있음
 * 이미 체크되어 있는 미션을 또 성공하진 않음
 */

public class Netmarble2022ct {

    static int[][] daily = new int[3][3];
    static int[][] weekly = new int[3][3];
    static int[][] monthly = new int[3][3];

    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);
//        a= new int[sc.nextInt()];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] logs = new int[st.countTokens() / 2][2];
//        System.out.println(st.countTokens());
        int i = 0;
        while (st.hasMoreTokens()) {
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            logs[i][0] = n;
            logs[i++][1] = m;
        }
//        for(int j=0;j<logs.length;j++){
//            System.out.println(logs[j][1]);
//        }
        solution(logs);
    }

    public static int solution(int[][] logs) {
        int bingo = 0;

        int day = logs[0][0];
        int n = 0;

        for (int i = 0; i < logs.length; i++) { //빙고 채우기
            int num = logs[i][1];
            bingoArray(num);
            if(logs[i][0]!=day){
                //바뀐 날짜가 기존의 주간 날짜와도 8일이상 차이가 나는지?
                fillZero('w');
            }
            else fillZero('d');

        }
        return bingo;
    }

    public static void bingoArray(int m) {
        if (1 <= m && m <= 3)
            daily[0][m-1] = 1;
        else if (4<= m && m <=6) daily[0][m-4]=1;
        else if (7<= m && m <=9) daily[0][m-7]=1;
        else if (10<= m && m <=12) weekly[0][m-10]=1;
        else if (13<= m && m <=15) weekly[0][m-13]=1;
        else if (16<= m && m <=18) weekly[0][m-16]=1;
        else if (19<= m && m <=21) daily[0][m-19]=1;
        else if (22<= m && m <=24) daily[0][m-22]=1;
        else if (25<= m && m <=27) daily[0][m-25]=1;
    }
    public static void fillZero(char arr){
        if(arr=='d'){
            for (int i =0; i<3;i++){
               for(int j=0;j<3;j++){
                   daily[i][j]=0;
               }
            }
        } else if (arr=='w') {
            for (int i =0; i<3;i++){
                for(int j=0;j<3;j++){
                    daily[i][j]=0;
                    weekly[i][j]=0;
                }
            }
        }
    }

}
