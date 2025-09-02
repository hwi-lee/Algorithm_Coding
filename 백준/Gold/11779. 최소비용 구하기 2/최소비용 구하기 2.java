import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n,m, A,B,C, s, e;
    static ArrayList<int[]> graph[], ans;
    static int[] dist, cnt;
    static int INF=100000000;
    static class Node implements Comparable<Node> {
        int node;
        int C;
        ArrayList<Integer> l;
        Node(int node, int C, ArrayList<Integer> l){
            this.node=node;
            this.C=C;
            this.l=l;
        }
        public int compareTo(Node o){
            return Integer.compare(C, o.C);
        }
    }
    public static void dijkstra(int node) {
        PriorityQueue<Node> q=new PriorityQueue<>();
        dist[node-1]=0;
        cnt[node-1]++;
        ArrayList<Integer> l=new ArrayList<>();
        l.add(node);
        q.add(new Node(node, 0, l));
        while(!q.isEmpty()){
            Node p=q.poll();
            int curnode=p.node;
            int curcost=p.C;
            ArrayList curlist=p.l;
       
            if(curnode==e){
                ans=curlist;
                return;
            }
            for(int i=0;i<graph[curnode-1].size();i++){
                int nextnode=graph[curnode-1].get(i)[0];
                int nextcost=graph[curnode-1].get(i)[1];

                if(curcost+nextcost<dist[nextnode-1]){
                    dist[nextnode-1]=curcost+nextcost;
                    curlist.add(nextnode);
                    q.add(new Node(nextnode, dist[nextnode-1], new ArrayList<>(curlist)));
                   // System.out.println("before: "+ curlist);
                   curlist.remove(curlist.size()-1);
                  //   System.out.println("after: "+ curlist);
                    cnt[nextnode-1]=cnt[curnode-1]+1;

                }

            }
        }



    }    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n=Integer.parseInt(br.readLine());
        m=Integer.parseInt(br.readLine());

        graph=new ArrayList[n];
        dist=new int[n];
        cnt=new int[n];
        for(int i=0;i<n;i++){
            graph[i]=new ArrayList<>();
            dist[i]=INF;
        }
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            A=Integer.parseInt(st.nextToken());
            B=Integer.parseInt(st.nextToken());
            C=Integer.parseInt(st.nextToken());
            graph[A-1].add(new int[]{B, C});

        }
        st = new StringTokenizer(br.readLine());
        s=Integer.parseInt(st.nextToken());
        e=Integer.parseInt(st.nextToken());

        dijkstra(s);
        System.out.println(dist[e-1]);
        System.out.println(cnt[e-1]);
        for(int i=0;i<ans.size();i++){
            System.out.print(ans.get(i)+" ");
        }

    }
}
