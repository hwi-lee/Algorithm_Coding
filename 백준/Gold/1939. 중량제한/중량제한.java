import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, v1, v2;
    static ArrayList<int[]> graph[];
    static int[] dist;
   
    static class Node implements Comparable<Node> {
        int v;
        int w;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public int compareTo(Node o) {
            return Integer.compare(o.w, w);
        }
    }


    static void dijkstra(int v1) {
        PriorityQueue<Node> q = new PriorityQueue<>();

        dist[v1 - 1] = Integer.MAX_VALUE;
        q.add(new Node(v1, dist[v1 - 1]));

        while (!q.isEmpty()) {
            Node p = q.poll();
            int curnode = p.v; //현재노드
            int curdist = p.w; //현재까지의 값

            if (dist[curnode - 1] > curdist) continue;

            for (int i = 0; i < graph[curnode - 1].size(); i++) {
                int nextnode = graph[curnode - 1].get(i)[0]; //현재노드에서 갈 수 있는 노드들
                int nextdist = graph[curnode - 1].get(i)[1]; //현재노드에서 갈 수 있는 노드까지의 값

                int min = Math.min(nextdist, curdist);
                if(min>dist[nextnode-1]){
                    dist[nextnode-1]=min;
                    q.offer(new Node(nextnode, dist[nextnode-1]));
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dist = new int[N];
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = 0;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph[A - 1].add(new int[]{B, C});
            graph[B - 1].add(new int[]{A, C});

        }
        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        dijkstra(v1);

        System.out.println(dist[v2 - 1]);
    }
}
