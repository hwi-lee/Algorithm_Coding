import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, ans;
    static int[] arr, dp;

    static void dp_function() {

        dp[0] = arr[0];
        for (int i = 1; i < N; i++) {
            dp[i]=arr[i];
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i]=Math.max(dp[i], dp[j]+arr[i]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dp_function();
        for(int i=0;i<N;i++){
            ans=Math.max(ans, dp[i]);
        }
        System.out.println(ans);
       // System.out.println(Arrays.toString(dp));
    }
}
