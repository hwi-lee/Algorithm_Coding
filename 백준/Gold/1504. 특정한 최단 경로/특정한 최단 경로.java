import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    //무방향 그래프
    //1-N의 최단경로
    //임의의 두 정점 반드시 통과
    //한번 통과한 간선 다시 통과 가능
    //임의 두 정점 사이 간선 하나만 존재
    //1-v1의 최단경로 + v1-v2의 최단경로 + v2-N의 최단경로
    //1-v2의 최단경로 + v2-v1의 최단경로 + v1-N의 최단경로
    static int N, E, v1, v2, check=0;
    static int[] dist1, dist2;
    static int INF=160000000;
    static ArrayList<int[]> graph[];
    static class Node implements Comparable<Node>{
        int v;
        int d;
        Node(int v, int d){
            this.v=v;
            this.d=d;
        }
        public int compareTo(Node o){
            return Integer.compare(d, o.d);
        }
    }

    static void init(int[] dist){
        for(int i=0;i<N;i++){
            dist[i]=INF;
        }
    }
    static void dijksta(int s, int node, int[] dist){//특정노드일 때 종료, s=시작노드
        PriorityQueue<Node> q=new PriorityQueue<>();
        dist[s-1]=0;
        q.add(new Node(s, dist[s-1]));
        while(!q.isEmpty()){
            Node p=q.poll();
            int curnode=p.v;
            int curdist=p.d;
            if(curnode==node){
                check++;
                break;
            }
            for(int i=0;i<graph[curnode-1].size();i++){ //주변노드 탐색
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
        N=Integer.parseInt(st.nextToken());
        E=Integer.parseInt(st.nextToken());
        graph=new ArrayList[N];
        dist1=new int[N];
        dist2=new int[N];
        for(int i=0;i<N;i++){
            graph[i]=new ArrayList<>();
            dist1[i]=INF;
            dist2[i]=INF;
        }
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());
            graph[a-1].add(new int[]{b, c});
            graph[b-1].add(new int[]{a, c});
        }

        st = new StringTokenizer(br.readLine());
        v1=Integer.parseInt(st.nextToken());
        v2=Integer.parseInt(st.nextToken());

        int ans1=0, ans2=0;
        dijksta(1, v1, dist1);
        dijksta(1, v2, dist2);
        ans1+=dist1[v1-1];
        ans2+=dist2[v2-1];
        init(dist1);
        init(dist2);
        dijksta(v2, N, dist1);
        dijksta(v1, N, dist2);
        ans1+=dist1[N-1];
        ans2+=dist2[N-1];
        init(dist1);
        dijksta(v1, v2, dist1);
        ans1+=dist1[v2-1];
        ans2+=dist1[v2-1];


        if(check==5)
            System.out.println(Integer.min(ans1, ans2));
        else
            System.out.println(-1);


    }
}
