import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int V, E, M, S, x, y;
    static ArrayList<int[]> graph[];
    static int[] dist_m, dist_s;
    static ArrayList<Integer> list_m = new ArrayList<>(), list_s = new ArrayList<>();
    static int INF = Integer.MAX_VALUE;

    static class Node implements Comparable<Node> {
        int v;
        int w;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public int compareTo(Node o) {
            return Integer.compare(w, o.w);
        }
    }


    static void dijkstra(ArrayList<Integer> list, int[] dist) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        for (int i = 0; i < list.size(); i++) {
            int node = list.get(i);
            dist[node - 1] = 0;
            q.offer(new Node(node, dist[node - 1]));
        }


        while (!q.isEmpty()) {
            Node p = q.poll();
            int curnode = p.v;
            int curdist = p.w;
            if (dist[curnode - 1] < curdist) {
                continue;
            }
            for (int i = 0; i < graph[curnode - 1].size(); i++) {
                int nextnode = graph[curnode - 1].get(i)[0];
                int nextdist = graph[curnode - 1].get(i)[1];

                if (dist[nextnode - 1] > curdist + nextdist) {
                    dist[nextnode - 1] = curdist + nextdist;
                    q.offer(new Node(nextnode, dist[nextnode - 1]));
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new ArrayList[V];
        dist_m = new int[V];
        dist_s = new int[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
            dist_m[i] = INF;
            dist_s[i] = INF;
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u - 1].add(new int[]{v, w});
            graph[v - 1].add(new int[]{u, w});
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 맥도날드 수
        x = Integer.parseInt(st.nextToken()); // 조건


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int node = Integer.parseInt(st.nextToken());
            list_m.add(node);
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken()); // 스타벅스 수
        y = Integer.parseInt(st.nextToken()); // 조건

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {
            int node = Integer.parseInt(st.nextToken());
            list_s.add(node);
        }

        dijkstra(list_m, dist_m); // 맥도날드에서 시작해 각 노드(집)와 제일 가까운 맥도날드 노드 찾기
        dijkstra(list_s, dist_s);
        int ans = INF;
        for (int i = 0; i < V; i++) {
            if (!list_s.contains(i + 1) && !list_m.contains(i + 1)) {
                if (dist_m[i] <= x && dist_s[i] <= y) {
                    ans=Math.min(ans, dist_m[i]+dist_s[i]);
                }
            }
        }
     
        if (ans != INF)
            System.out.println(ans);
        else
            System.out.println(-1);

    }
}
