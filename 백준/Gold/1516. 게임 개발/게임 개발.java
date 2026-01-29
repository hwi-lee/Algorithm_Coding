import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    static int N;
    static int[] indegree, ans;
    static ArrayList<Integer> graph[];
    static StringBuilder sb = new StringBuilder();

    static class Node implements Comparable<Node> {
        int n; // 건물 번호
        int t; // 시간

        Node(int n, int t) {
            this.n = n;
            this.t = t;
        }

        public int compareTo(Node o) {
            return Integer.compare(t, o.t);
        }
    }

    static void topology_sort() {
        PriorityQueue<Node> q = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                q.offer(new Node(i + 1, ans[i]));
            }
        }

        while (!q.isEmpty()) {
            Node p = q.poll();
            int n = p.n;
            int t = p.t;
            sb.append(n).append(" ");
            for (int i = 0; i < graph[n - 1].size(); i++) {
                int node = graph[n - 1].get(i);
                indegree[node - 1]--;

                if (indegree[node - 1] == 0) {
                    ans[node - 1] += ans[n - 1];
                    q.offer(new Node(node, ans[node - 1]));

                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        indegree = new int[N];
        ans = new int[N];
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            ans[i] = time;
            while (true) {
                int v = Integer.parseInt(st.nextToken());
                if (v == -1) {
                    break;
                }
                graph[v - 1].add(i + 1);
                indegree[i]++;
            }
        }
        topology_sort();

        //  System.out.println(sb);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }

    }
}
