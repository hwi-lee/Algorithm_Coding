import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr, dp;
    //연속 3개 안됨.
    //두칸 또는 한칸
    //1 2 3
    //2 3
    //1 3

    //3번째 계단일때 i일 떼,
    // i선택 시 -> arr[i]+arr[i-1]+dp[i-3]  , arr[i]+dp[i-2]

    //계산 2개이하일때
    //1 2
    //2
    //1 2

    //1 2 3
    // 1 3
    //2 3

    static void dp_function() {

        dp[0] = arr[0];
        if (N >= 2) { //2번째 계단 무조건 선택
            dp[1] = Math.max(dp[0], arr[1]+dp[0]);
        }
        if (N >= 3) { //3번째 계단 무조건 선택
            dp[2] =Math.max(arr[2] + dp[0], arr[2] + arr[1]);
        }
        for (int i = 3; i < N; i++) {
            dp[i] = (Math.max(arr[i] + arr[i - 1] + dp[i - 3], arr[i] + dp[i - 2]));

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //   StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        dp_function();
        System.out.println(dp[N-1]);

    }
}
