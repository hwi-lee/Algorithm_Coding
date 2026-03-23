import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // 1. 1초 후 X(수빈이 위치)-1
    // 2. 1초 후 X(수빈이 위치)+1
    // 3. 0초 후 2*X
    // N이 K될 때까지

    static int N, K;
    static boolean visited[];

    static class Node implements Comparable<Node>{
        int cur;
        int t;

        Node(int cur, int t) {
            this.cur = cur; //현재위치
            this.t = t; //시간
        }

        public int compareTo(Node o){
            return Integer.compare(t, o.t);
        }
    }

    static int bfs() {

        PriorityQueue<Node> q=new PriorityQueue<>();
        q.offer(new Node(N, 0));

        while (!q.isEmpty()) {
            Node p = q.poll();
            int curp = p.cur;
            int curt = p.t;

            if (curp == K) {
                return curt;
            }

            if(visited[curp])
                continue;
            visited[curp]=true;

            // N<K
            if(N<K) {
                if (curp * 2 <= 100000 && curp != 0)
                    q.offer(new Node(curp * 2, curt));
                if (curp - 1 >= 0)
                    q.offer(new Node(curp - 1, curt + 1));
                if (curp + 1 <= K)
                    q.offer(new Node(curp + 1, curt + 1));

            }
            // N>K
            else if(N>K) {
                if (curp - 1 >= K)
                    q.offer(new Node(curp - 1, curt + 1));
            }
        }

        return -1;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited=new boolean[100001];
        int ans=bfs();
        System.out.println(ans);

    }
}
