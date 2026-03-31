import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, p, q;
    static ArrayList<int[]> graph[];
    static int[] p_arr, q_arr, dist;
    static int INF = Integer.MAX_VALUE;

    static class Node implements Comparable<Node> {
        int v;
        int d;

        Node(int v, int d) {
            this.v = v;
            this.d = d;
        }

        public int compareTo(Node o) {
            return Integer.compare(d, o.d);
        }
    }

    static void dijkstra(int[] node) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        for (int i = 0; i < node.length; i++) {
            dist[node[i] - 1] = 0;
            q.offer(new Node(node[i], 0));
        }

        while (!q.isEmpty()) {
            Node p = q.poll();
            ;
            int curnode = p.v;
            int curdist = p.d;

            if (dist[curnode - 1] < curdist)
                continue;
            for (int i = 0; i < graph[curnode - 1].size(); i++) {
                int nextnode = graph[curnode - 1].get(i)[0];
                int nextdist = graph[curnode - 1].get(i)[1];

                if (nextdist + curdist < dist[nextnode - 1]) {
                    dist[nextnode - 1] = nextdist + curdist;
                    q.offer(new Node(nextnode, dist[nextnode - 1]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n];
        dist = new int[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = INF;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a - 1].add(new int[]{b, c});
            graph[b - 1].add(new int[]{a, c});
        }
        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken()); //집
        q = Integer.parseInt(st.nextToken()); //편의점

        p_arr = new int[p];
        q_arr = new int[q];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < p; i++) {
            p_arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < q; i++) {
            q_arr[i] = Integer.parseInt(st.nextToken());
        }

        dijkstra(q_arr);

        //  System.out.println(Arrays.toString(dist));
        int ans = INF;
        int ans_node = p_arr[0];
        Arrays.sort(p_arr);
        for (int i = 0; i < p; i++) {
            if (dist[p_arr[i] - 1] < ans) {
                ans = dist[p_arr[i] - 1];
                ans_node = p_arr[i];
            }
        }

        System.out.println(ans_node);


    }
}
