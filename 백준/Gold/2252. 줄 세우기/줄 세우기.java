import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] indegree;
    static ArrayList<Integer> graph[];
    static StringBuilder sb=new StringBuilder();

    static void topology_sort(){
        Queue<Integer> q=new ArrayDeque<>();

        for(int i=0;i<N;i++){
            if(indegree[i]==0)
                q.offer(i+1);
        }
        while(!q.isEmpty()){
            int p=q.poll();
            sb.append(p).append(" ");
            for(int i=0;i<graph[p-1].size();i++){
                int node=graph[p-1].get(i);
                indegree[node-1]--;
                if(indegree[node-1]==0)
                    q.offer(node);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        indegree=new int[N];
        graph=new ArrayList[N];
        for(int i=0;i<N;i++){
            graph[i]=new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            indegree[B-1]++;
            graph[A-1].add(B);

        }
        topology_sort();
        System.out.print(sb);

    }
}
