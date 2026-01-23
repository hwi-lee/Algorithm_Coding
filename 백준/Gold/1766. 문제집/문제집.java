import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] indegree;
    static ArrayList<Integer> graph[];
    static StringBuilder sb=new StringBuilder();

    static class Node implements Comparable<Node>{
        int v;

        Node(int v){
            this.v=v;
        }

        public int compareTo(Node o){
            return Integer.compare(v, o.v);
        }
    }

    static void topology_sort(){
        PriorityQueue<Node> q=new PriorityQueue<>();
        for(int i=0;i<indegree.length;i++){
            if(indegree[i]==0)
                q.offer(new Node(i+1));
        }

        while(!q.isEmpty()){
            Node p=q.poll();
            sb.append(p.v).append(" ");

            for(int i=0;i<graph[p.v-1].size();i++){
                int node=graph[p.v-1].get(i);
                indegree[node-1]--;
                if(indegree[node-1]==0){
                    q.offer(new Node(node));
                }
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
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int A= Integer.parseInt(st.nextToken());
            int B=Integer.parseInt(st.nextToken());
            indegree[B-1]++;
            graph[A-1].add(B);
        }

        topology_sort();
        System.out.print(sb);

    }
}
