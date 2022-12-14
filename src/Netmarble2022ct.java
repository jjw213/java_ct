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
// 예제 입력값
// 1 1 1 2 1 3 1 4 1 5 1 6 1 7 1 8 1 9 1 10 15 19 15 9 15 10 15 21 15 25 15 1 15 27 15 13 24 5 24 16 24 23
// 출력값 : 10 (일간 빙고 8점, 월간 빙고 2점)
public class Netmarble2022ct {
    static int[][] daily = new int[3][3];
    static int[][] weekly = new int[3][3];
    static int[][] monthly = new int[3][3];
    static int bingo = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] logs = new int[st.countTokens() / 2][2];
        int i = 0;
        while (st.hasMoreTokens()) {
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            logs[i][0] = n;
            logs[i++][1] = m;
        }
        solution(logs);
    }

    public static int solution(int[][] logs) {
        int day = logs[0][0];
        for (int i = 0; i < logs.length; i++) { //빙고 채우기
            if (logs[i][0] != day) {
                //날짜가 바뀜 -> 초기화 전에 빙고 점수 계산
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        dfs(daily, j, k, "all", 0);
                    }
                }
                if (logs[i][0] >= day + 7) { //바뀐 날짜가 7일 이상 차이 난다면 주간 빙고도 계산 후 초기화
                    for (int j = 0; j < 3; j++) {
                        for (int k = 0; k < 3; k++) {
                            dfs(weekly, j, k, "all", 0);
                        }
                    }
                    fillZero('w');
                } else fillZero('d');
                day = logs[i][0];
            }
            int num = logs[i][1];
            bingoArray(num);
        }
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                dfs(monthly, j, k, "all", 0);
            }
        }
        System.out.println(bingo);
        return bingo;
    }

    public static void bingoArray(int m) { // logs 배열에 입력된 값들을 일간, 주간, 월간 빙고에 분류 후 입력
        if (1 <= m && m <= 3)
            daily[0][m - 1] = 1;
        else if (4 <= m && m <= 6) daily[1][m - 4] = 1;
        else if (7 <= m && m <= 9) daily[2][m - 7] = 1;
        else if (10 <= m && m <= 12) weekly[0][m - 10] = 1;
        else if (13 <= m && m <= 15) weekly[1][m - 13] = 1;
        else if (16 <= m && m <= 18) weekly[2][m - 16] = 1;
        else if (19 <= m && m <= 21) monthly[0][m - 19] = 1;
        else if (22 <= m && m <= 24) monthly[1][m - 22] = 1;
        else if (25 <= m && m <= 27) monthly[2][m - 25] = 1;
    }

    public static void fillZero(char arr) { // 날짜가 바뀜으로써 빙고를 초기화시킬 때 사용하는 클래스
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                daily[i][j] = 0;
                if (arr == 'w')
                    weekly[i][j] = 0;
            }
        }
    }

    public static void dfs(int[][] board, int row, int col, String dir, int bingoStack) {
        if (row >= board.length || col >= board[row].length || row < 0 || col < 0) {//배열의 범위를 벗어나면 리턴
            if (bingoStack >= 3)
                bingo++;
            return;
        }
        if (board[row][col] == 0) return; // 체크 안 된 빙고라면 리턴

        switch (dir) {
        //위에서부터 아래로 내려오기 때문에 위로가는 dfs는 할 필요 없음
        //오른쪽에서 왼쪽으로 가기 때문에 왼쪽으로 가는 dfs도 할 필요 없음.
            case "all":
                dfs(board, row, col + 1, "right", bingoStack + 1); //오른쪽
                dfs(board, row + 1, col, "down", bingoStack + 1);//아래
                if (row == 0 && col == 0) { // 1 번 째 칸일 때 우측하단으로 가는 대각선 빙고도 검색
                    dfs(board, row + 1, col + 1, "rightDown", bingoStack + 1);//우측 아래
                } else if (row == 0 && col == 2) { // 첫 줄, 마지막 칸일 때 좌측 하단 대각선
                    dfs(board, row + 1, col - 1, "leftDown", bingoStack + 1);//좌측 아래
                }
                break;
            case "right":
                dfs(board, row, col + 1, "right", bingoStack + 1); //오른쪽
                break;
            case "down":
                dfs(board, row + 1, col, "down", bingoStack + 1);//아래
                break;
            case "rightDown":
                dfs(board, row + 1, col + 1, "rightDown", bingoStack + 1);//아래
                break;
            case "leftDown":
                dfs(board, row + 1, col - 1, "leftDown", bingoStack + 1);//아래
                break;
        }
    }
}
