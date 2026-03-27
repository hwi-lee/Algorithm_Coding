import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, ans;
    static int[] arr, dp;

    static void dp_function() {

        for (int i = 1; i < n; i++) {
            dp[i]=Math.max(arr[i], dp[i-1]+arr[i]);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new int[n]; //i번 째까지의 최대 합 -> i에서 끝나는 최대 합
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        dp[0] = arr[0];
        ans = arr[0];
        dp_function();
        for(int i=0;i<n;i++){
            ans=Math.max(ans, dp[i]);
        }
        System.out.println(ans);

    }
}
