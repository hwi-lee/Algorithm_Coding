import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, ans;
    static int[][] tri, dp;

    static void dp_function() {

        for (int i = 1; i < n ; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (j - 1 >=0) {
                    dp[i][j] = tri[i][j] + Math.max(dp[i-1][j - 1], dp[i-1][j]);
                }
                else{
                    dp[i][j] = tri[i][j] + dp[i-1][j];
                }
            }

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        tri = new int[n][n];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i + 1; j++) {
                tri[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = tri[0][0];

        dp_function();
        //dp[i][j] = i번째 행, j번째 헹 선택 했을 때까지의 최대값

        for(int i=0;i<n;i++){
            ans=Math.max(ans, dp[n-1][i]);
        }
        System.out.println(ans);
    }
}
