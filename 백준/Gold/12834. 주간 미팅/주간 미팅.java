import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, V, E, A, B, ans;
    static int INF=Integer.MAX_VALUE;
    static ArrayList<int[]> graph[];
    static int[] dist, home;
    static class Node implements Comparable<Node>{
        int v;
        int w;
        Node(int v, int w){
            this.v=v;
            this.w=w;
        }
        public int compareTo(Node o){
            return Integer.compare(w, o.w);
        }
    }
    static int dijkstra(int node, int endnode){
        PriorityQueue<Node> q=new PriorityQueue<>();
        dist[node-1]=0;
        q.offer(new Node(node, 0));
        while(!q.isEmpty()){
            Node p=q.poll();
            int curnode=p.v;
            int curdist=p.w;
            if(curnode==endnode){

                return dist[curnode-1];
            }
            if(curdist>dist[curnode-1]) continue;
            for(int i=0;i<graph[curnode-1].size();i++){
                int nextnode=graph[curnode-1].get(i)[0];
                int nextdist=graph[curnode-1].get(i)[1];
                if(curdist+nextdist<dist[nextnode-1]){
                    dist[nextnode-1]=curdist+nextdist;
                    q.offer(new Node(nextnode, dist[nextnode-1]));
                }
            }
        }
        return -1;
    }

    static void init(int[] dist){
        for(int i=0;i<V;i++){
            dist[i]=INF;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // KIST 기사단 팀원의 수 N
        V = Integer.parseInt(st.nextToken()); // 장소의 수 V
        E = Integer.parseInt(st.nextToken()); // 도로의 수 E

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken()); //KIST의 위치
        B = Integer.parseInt(st.nextToken()); //씨알푸드의 위치

        st = new StringTokenizer(br.readLine());
        home= new int[N];
        dist= new int[V];
        for(int i=0;i<N;i++){
            home[i]=Integer.parseInt(st.nextToken());
        }
        graph=new ArrayList[V];
        for(int i=0;i<V;i++){
            graph[i]=new ArrayList<>();
        }
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int v=Integer.parseInt(st.nextToken());
            int u=Integer.parseInt(st.nextToken());
            int w=Integer.parseInt(st.nextToken());
            graph[v-1].add(new int[]{u, w});
            graph[u-1].add(new int[]{v, w});
        }

        for(int i=0;i<home.length;i++){
            int node=home[i];
            init(dist);
            int a=dijkstra(node, A);
            init(dist);
            int b=dijkstra(node, B);
            ans+=(a+b);
        }
        System.out.println(ans);

    }

}
