import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static ArrayList<Integer>[] graph;
    static int[] depth;
    static boolean[] visited;

    public static void bfs(int n) {
        Queue<Integer> q = new LinkedList<>();
        visited[n - 1] = true;
        depth[0] = 1;//root degreed
        q.add(n);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                int cur = q.poll();
                for (int i = 0; i < graph[cur - 1].size(); i++) {
                    int node = graph[cur - 1].get(i);
                    if (visited[node - 1])
                        continue;
                    visited[node - 1] = true;
                    q.add(node);
                    if (depth[node - 1] == 0)
                        depth[node - 1] = depth[cur - 1] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N];
        depth = new int[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u - 1].add(v);
            graph[v - 1].add(u);
        }
        bfs(1);
//        for (int i = 0; i < depth.length; i++)
//            System.out.print(depth[i] + " ");
        for (int i = 1; i < depth.length; i++) {
            int ans = depth[i];
            for (int j = 0; j < graph[i].size(); j++) {
                int tmp = depth[graph[i].get(j) - 1];
                if (ans - tmp == 1) {
                    System.out.println(graph[i].get(j));
                    break;
                }
            }
        }

    }
}
