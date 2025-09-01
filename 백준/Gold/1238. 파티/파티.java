import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, T, A, B, X, ans, max;
    static ArrayList<int[]> map[];
    static int[] dist1;
    static int[] dist2;
    static int INF=10000000;
    //1~N -> X 의 dist[] 구하기
    //X -> 1~N 의 dist[] 구하기
    static class Node implements Comparable<Node> {
        int node;
        int T;
        Node(int node, int T){
            this.node=node;
            this.T=T;
        }
        public int compareTo(Node o){
            return Integer.compare(T, o.T);
        }
    }
    public static void dijktra(int node){
        PriorityQueue<Node> q=new PriorityQueue<>();
        dist1[node-1]=0; //1~N -> X
        q.add(new Node(node, 0));
        while(!q.isEmpty()){
            Node p=q.poll();
            int curnode=p.node;
            int curdist=p.T;
            for(int i=0;i<map[curnode-1].size();i++){
                int nextnode=map[curnode-1].get(i)[0];
                int nextdist=map[curnode-1].get(i)[1];
                if(curdist+nextdist<dist1[nextnode-1]){
                    dist1[nextnode-1]=curdist+nextdist;
                    q.add(new Node(nextnode, dist1[nextnode-1]));
                }
            }
        }
    }
    public static void dijktra_copy(int node){
        PriorityQueue<Node> q=new PriorityQueue<>();
        dist2[node-1]=0; //1~N -> X
        q.add(new Node(node, 0));
        while(!q.isEmpty()){
            Node p=q.poll();
            int curnode=p.node;
            int curdist=p.T;
            if(curnode==X)
                return;
            for(int i=0;i<map[curnode-1].size();i++){
                int nextnode=map[curnode-1].get(i)[0];
                int nextdist=map[curnode-1].get(i)[1];
                if(curdist+nextdist<dist2[nextnode-1]){
                    dist2[nextnode-1]=curdist+nextdist;
                    q.add(new Node(nextnode, dist2[nextnode-1]));
                }
            }
        }
    }
    public static void init(){
        for(int i=0;i<N;i++){
            dist2[i]=INF;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        dist1=new int[N];
        dist2=new int[N];

        map=new ArrayList[N];
        for(int i=0;i<N;i++){
            map[i]=new ArrayList<>();
            dist1[i]=INF;
            dist2[i]=INF;
        }
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());
            map[A-1].add(new int[]{B, T});
        }

        dijktra(X); //X -> 1~N 의 값

        for(int i=1;i<=N;i++){
            init();
            dijktra_copy(i);
            ans=Math.max(dist1[i-1]+dist2[X-1], ans);
        }

        System.out.println(ans);
    }
}
