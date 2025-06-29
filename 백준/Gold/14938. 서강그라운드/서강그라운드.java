import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m, r, max, cnt;
    static int[][] graph;
    static int[] items;

    public static void FloydWarshall() {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j && graph[i][k] > 0 && graph[k][j] > 0) {
                        if (graph[i][k] + graph[k][j] <= m) {
                            if (graph[i][j] > 0)
                                graph[i][j] = graph[i][j] < graph[i][k] + graph[k][j] ? graph[i][j] : graph[i][k] + graph[k][j];
                            else
                                graph[i][j] = graph[i][k] + graph[k][j];
                        }
                    }
                }
            }
        }
    }

    public static void function(int node) {
        cnt = items[node];
        for (int i = 0; i < n; i++) {
            if (graph[node][i] > 0 && graph[node][i] <= m) {
                cnt += items[i];
            }
        }
        max = Math.max(max, cnt);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());//노드 수
        m = Integer.parseInt(st.nextToken());//수색 범위
        r = Integer.parseInt(st.nextToken());//간선 수

        graph = new int[n][n];

        items = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            graph[a - 1][b - 1] = l;
            graph[b - 1][a - 1] = l;
        }
        FloydWarshall();

        for (int i = 0; i < n; i++) {
            function(i);
        }
        System.out.println(max);

    }
}
