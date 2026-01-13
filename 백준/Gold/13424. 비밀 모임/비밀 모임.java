import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M,N,K, T;
    static ArrayList<int[]> graph[];
    static int[][] dist;
    static int INF=Integer.MAX_VALUE;

    //k개의 노드에서 시작해 임의의 노드까지 -> 합 -> 최소
    //k개의 노드에서 시작해 각 노드까지의 최소값 dist[k][n] 배열에 저장
    //각 노드기준[n]으로 dist[k][n] 값들을 더해서 최소값 출력
    
    static class Node implements Comparable<Node>{
        int v;
        int  w;

        Node(int v, int w){
            this.v=v;
            this.w=w;
        }

        public int compareTo(Node o){
            return Integer.compare(w, o.w);
        }

    }

    static void dijkstra(int index, int node){
        PriorityQueue<Node> q = new PriorityQueue<>();

        dist[index][node-1]=0;
        q.add(new Node(node, 0));
        while(!q.isEmpty()){
            Node p=q.poll();
            int curnode=p.v;
            int curdist=p.w;

            if (curdist > dist[index][curnode-1]) continue;
            for(int i=0;i<graph[curnode-1].size();i++){
                int nextnode=graph[curnode-1].get(i)[0];
                int nextdist=graph[curnode-1].get(i)[1];

                if(curdist+nextdist<dist[index][nextnode-1]){
                    dist[index][nextnode-1]=curdist+nextdist;
                    q.add(new Node(nextnode, dist[index][nextnode-1]));
                }
            }
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T=Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            graph = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                graph[u - 1].add(new int[]{v, w});
                graph[v - 1].add(new int[]{u, w});
            }
            K = Integer.parseInt(br.readLine());
            dist = new int[K][N];
            for (int i = 0; i < K; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = INF;
                }
            }
            st = new StringTokenizer(br.readLine());
            int[] node = new int[K];
            for (int i = 0; i < K; i++) {
                node[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < K; i++) {
                dijkstra(i, node[i]);
            }

            int min=0;
            for(int i=0;i<K;i++){
                min+=dist[i][0];
            }
            int sum=0;
            int ans = 1;
            for (int i = 1; i < N; i++) {
                sum = 0;
                for (int j = 0; j < K; j++) {
                    sum += dist[j][i];
                }
                if (sum < min) {
                    min = sum;
                    ans = i + 1;
                }
            }
            System.out.println(ans);
        }
    }
}
