import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M;
    static ArrayList<int[]> graph[];
    static int INF=Integer.MAX_VALUE;
    static int[] dist;
    static private class Node implements Comparable<Node>{
        int v;
        int w;

        Node (int v, int w){
            this.v=v;
            this.w=w;
        }
       public int compareTo(Node o){
            return Integer.compare(w, o.w);
        }
    }
    public static void dijkstra(int node){
        PriorityQueue<Node> q = new PriorityQueue<>();
        dist[node-1]=0;
        q.add(new Node(node, 0));
        while(!q.isEmpty()){
            Node p=q.poll();
            int curnode=p.v;
            int curdist=p.w;
            if (curdist > dist[curnode-1]) continue;
            for(int i=0;i<graph[curnode-1].size();i++){
                int nextnode=graph[curnode-1].get(i)[0];
                int nextdist=graph[curnode-1].get(i)[1];
                if(curdist+nextdist<dist[nextnode-1]){
                    dist[nextnode-1]=curdist+nextdist;
                    q.add(new Node(nextnode, dist[nextnode-1]));
                }
            }
        }
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph=new ArrayList[N];
        dist=new int[N];
        for(int i=0;i<N;i++){
            graph[i]=new ArrayList<>();
            dist[i]=INF;
        }
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u=Integer.parseInt(st.nextToken());
            int v=Integer.parseInt(st.nextToken());
            int w=Integer.parseInt(st.nextToken());
            graph[u-1].add(new int[]{v, w});
            graph[v-1].add(new int[]{u, w});

        }

        dijkstra(1);

        System.out.println(dist[N-1]);
    }
}
