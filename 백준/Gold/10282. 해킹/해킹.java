import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, d, t, c;
    static ArrayList<int[]> graph[];
    static int[] dist;
    static int INF=Integer.MAX_VALUE;
    static class Node implements Comparable<Node>{
        int v;
        int s;
        Node(int v, int s){
            this.v=v;
            this.s=s;
        }
        public int compareTo(Node o){
            return Integer.compare(s, o.s);
        }

    }
    static void dijkstra(int node){
        PriorityQueue<Node> q=new PriorityQueue<>();
        dist[node-1]=0;
        q.offer(new Node(node, 0));
        while(!q.isEmpty()){
            Node p=q.poll();
            int curnode=p.v;
            int curtime=p.s;

            if(dist[curnode-1]<curtime)
                continue;

            for(int i=0;i<graph[curnode-1].size();i++){
                int nextnode=graph[curnode-1].get(i)[0];
                int nexttime=graph[curnode-1].get(i)[1];
                if(curtime+nexttime<dist[nextnode-1]){
                    dist[nextnode-1]=curtime+nexttime;
                    q.offer(new Node(nextnode, dist[nextnode-1]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t=Integer.parseInt(br.readLine());
        for(int test_case=0;test_case<t;test_case++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken()); //시작점
            graph = new ArrayList[n];
            dist = new int[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
                dist[i] = INF;
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                graph[b - 1].add(new int[]{a, s});// b가 감염되면 s초 후 a도 감염됨
            }
            dijkstra(c);

            int cnt = 0;
            int cnt_s = 0;

            for (int i = 0; i < n; i++) {
                if (dist[i] != INF) {
                    cnt++;
                    cnt_s = Math.max(cnt_s, dist[i]);
                }
            }

            System.out.println(cnt + " " + cnt_s);

        }
    }
}
