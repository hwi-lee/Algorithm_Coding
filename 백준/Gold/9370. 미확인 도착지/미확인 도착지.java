import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T, n, m, t, s, g, h;
    static ArrayList<int[]> graph[];
    static int[] e;
    static int INF = Integer.MAX_VALUE;
    static int[][] dist;

    //목적지마다 확인
    //g, h를 지나간다
    //최단경로면서 g, h 지나가는 경로
    //**최단거리 같으면서 포함여부에 따라 경로가 다른 경우 존재**
    //dist 2차원 -> g,h 경로 포함인지 확인용 -> dist[i][0]=g,h미포함한 최단거리, dist[i][0]=g,h포함한 최단거리
    static class Node implements Comparable<Node> {
        int v;
        int w;
        int f;

        Node(int v, int w, int f) {
            this.v = v;
            this.w = w;
            this.f = f;
        }

        public int compareTo(Node o) {
            return Integer.compare(w, o.w);
        }
    }

    static void dijkstra(int node) {

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(node, 0, 0));
        dist[node - 1][0] = 0;

        while (!q.isEmpty()) {
            Node p = q.poll();
            int curnode = p.v;
            int curdist = p.w;
            int isEdge = p.f;

            if (curdist > dist[curnode - 1][isEdge])
                continue;

            for (int i = 0; i < graph[curnode - 1].size(); i++) {
                int nextnode = graph[curnode - 1].get(i)[0];
                int nextdist = graph[curnode - 1].get(i)[1];

                int nextEdge = isEdge; //이전경로에서 g,h 지났는지 확인
                if ((curnode == g && nextnode == h) ||
                        (curnode == h && nextnode == g)) {
                    nextEdge = 1;
                }

                if (curdist + nextdist < dist[nextnode - 1][nextEdge]) {
                    dist[nextnode - 1][nextEdge] = curdist + nextdist;
                    q.offer(new Node(nextnode, dist[nextnode - 1][nextEdge], nextEdge));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {


            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken()); //목적지 개수

            e = new int[t];
            dist = new int[n][2];

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken()); //출발지
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList();
                dist[i][0] = INF;
                dist[i][1] = INF;

            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                graph[a - 1].add(new int[]{b, d});
                graph[b - 1].add(new int[]{a, d});
            }
            for (int i = 0; i < t; ) {
                e[i++] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(e);

            dijkstra(s);


            for (int i = 0; i < e.length; i++) {
                int idx = e[i]; //도착지 노드번호
                if (dist[idx - 1][1] <=dist[idx-1][0]&&dist[idx-1][1]!=INF) // g,h 지나고 최소거리면서 INF(도착불가한 경우)가 아닌 노드 출력

                    System.out.print(idx + " ");
            }

            System.out.println();

        }


    }
}
