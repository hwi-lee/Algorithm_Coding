import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static ArrayList<int[]> graph[];
    static int INF = 800000000; //
    static int[] dist1; //달빛여우
    static double[][] dist2; //달빛늑대
    //양방행 그래프, 간선 하나만
    //1번에서 시작
    //두배 -> 0.5배 -> 두배 ->
    //두배 속도= *0.5

    //4
    //여우: 7s    1-2-4
    //늑대: 1+4+2    1-3-2-4

    //2까지-> 1,5
    //2까지-> 5  3지나고
    //4까지-> 1.5+8
    //4까지-> 5+2 =7

    //5
    //여우: 6s    1-3-5
    //늑대: 1.5+4+2=7.5s    1-2-3-5

    static class Node1 implements Comparable<Node1> {
        int node;
        int d;

        Node1(int node, int d) {
            this.node = node;
            this.d = d;
        }

        public int compareTo(Node1 o) {
            return Integer.compare(d, o.d);
        }
    }

    static class Node2 implements Comparable<Node2> {
        int node;
        double d;
        int t;

        Node2(int node, double d, int t) {
            this.node = node;
            this.d = d;
            this.t = t;
        }

        public int compareTo(Node2 o) {
            return Double.compare(d, o.d);
        }
    }

    public static void dijkstra1() {
        PriorityQueue<Node1> q = new PriorityQueue<>();

        dist1[0] = 0;
        q.add(new Node1(1, 0));

        while (!q.isEmpty()) {
            Node1 p = q.poll();
            int curnode = p.node;
            int curdist = p.d;

            if(curdist>dist1[curnode-1])
                continue;

            for (int i = 0; i < graph[curnode - 1].size(); i++) {
                int nextnode = graph[curnode - 1].get(i)[0];
                int nextdist = graph[curnode - 1].get(i)[1];
                if (curdist + nextdist < dist1[nextnode - 1]) {
                    dist1[nextnode - 1] = curdist + nextdist;
                    q.add(new Node1(nextnode, dist1[nextnode - 1]));
                }

            }

        }

    }

    public static void dijkstra2() {
        PriorityQueue<Node2> q = new PriorityQueue<>();

        dist2[0][0] = 0;

        q.add(new Node2(1, 0, 0));

        while (!q.isEmpty()) {
            Node2 p = q.poll();
            int curnode = p.node;
            double curdist = p.d;
            int t = p.t;


            if(curdist>dist2[curnode-1][t])
                continue;

            for (int i = 0; i < graph[curnode - 1].size(); i++) {
                int nextnode = graph[curnode - 1].get(i)[0];
                double nextdist = graph[curnode - 1].get(i)[1];


                int time = (t == 0) ? 1 : 0; //다음 노드 상태
                nextdist = (t == 0) ? (nextdist * 0.5) : (nextdist * 2);
                if (time == 0) {
                    if (curdist + nextdist < dist2[nextnode - 1][0]) {
                        dist2[nextnode - 1][0] = curdist + nextdist;
                        q.add(new Node2(nextnode, dist2[nextnode - 1][0], time));
                    }
                }
                else{
                    if (curdist + nextdist < dist2[nextnode - 1][1]) {
                        dist2[nextnode - 1][1] = curdist + nextdist;
                        q.add(new Node2(nextnode, dist2[nextnode - 1][1], time));
                    }
                }

            }

        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist1 = new int[N];
        dist2 = new double[N][2];
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
            dist1[i] = INF;
            dist2[i][0] = INF;
            dist2[i][1] = INF;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph[a - 1].add(new int[]{b, d});
            graph[b - 1].add(new int[]{a, d});
        }

        dijkstra1();
        dijkstra2();

        int ans=0;
        for(int i=0;i<N;i++){
            if(dist1[i]<Math.min(dist2[i][0], dist2[i][1])){
                ans++;
            }
        }

        System.out.println(ans);


    }
}
