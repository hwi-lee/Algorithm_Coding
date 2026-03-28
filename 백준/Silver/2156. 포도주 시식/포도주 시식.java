import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n, ans;
    static int[] arr;
    static int[] dp;
    //연속 3잔 마실 수 없음

    // i번째 마실때
    //1. i, i-1를 마신다
    //2. i 마시고, i-1 안마신다
    //4. i 안마신다
    static void dp_funcion() {

        dp[0] = arr[0]; //무조건 n이 1이상
        if (n >=2) { // 1 2
            dp[1]=arr[1]+dp[0];
        }
        if (n>=3) { //1 2 3
            dp[2]=Math.max(Math.max(arr[2]+arr[1], arr[2]+dp[0]), dp[1]);
        }
        for (int i = 3; i < n; i++) {
            dp[i] = Math.max(Math.max(arr[i] + arr[i - 1] + dp[i - 3], dp[i - 2] + arr[i]), dp[i - 1]);
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp_funcion();

        for(int i=0;i<n;i++){
            ans=Math.max(ans, dp[i]);
        }
        System.out.println(ans);

    }
}
