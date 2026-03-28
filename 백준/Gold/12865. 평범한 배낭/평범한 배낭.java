import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K, cost;
    static Node[] info;
    static int[][] dp; //i번째까지 물건 선택, j는 i번째 선택 시의 제한 무게

    static class Node {
        int w;
        int v;

        Node(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }

    static void dp_function() {
        for (int i = info[0].w; i <= K; i++) { //첫번 쩨 물건 담기
            dp[0][i] = info[0].v;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= K; j++) {
                if (j >= info[i].w) {
                    dp[i][j] = Math.max(info[i].v + dp[i - 1][j - info[i].w], dp[i - 1][j]);
                }
                else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        info = new Node[N];
        dp = new int[N][K + 1];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            info[i] = new Node(w, v);
            info[i].w = w;
            info[i].v = v;

        }

        dp_function();
        
        System.out.println(dp[N-1][K]);//마지막 물건 선택했을 때 K용량인 가치


    }
}
