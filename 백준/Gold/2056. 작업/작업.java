import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main {
    static int N;
    static int[] indegree, ans;
    static ArrayList<Integer> graph[];
    static ArrayList<Integer> list=new ArrayList<>();
    static class Node implements Comparable<Node>{
        int n;
        int t;
        Node(int n, int t){
            this.n=n;
            this.t=t;
        }
        public int compareTo(Node o){
         return Integer.compare(t, o.t);
        }

    }

    static void topology_sort(){
        PriorityQueue<Node> q=new PriorityQueue<>();

        for(int i=0;i<N;i++){
            if(indegree[i]==0){
                q.offer(new Node(i+1, ans[i]));
            }
        }
        while(!q.isEmpty()){
            Node p=q.poll();
            int n=p.n;
            int t=p.t;
            list.add(n);
     
            for(int i=0;i<graph[n-1].size();i++){
                int nextnode=graph[n-1].get(i);
                indegree[nextnode-1]--;
                if(indegree[nextnode-1]==0){
                    ans[nextnode-1]+=ans[n-1];
                    q.offer(new Node(nextnode, ans[nextnode-1]));
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        graph=new ArrayList[N];
        indegree=new int[N];
        ans=new int[N];
        for(int i=0;i<N;i++){
            graph[i]=new ArrayList<>();
        }
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int t=Integer.parseInt(st.nextToken());
            int m=Integer.parseInt(st.nextToken());
            ans[i]=t;
            for(int j=0;j<m;j++){
                int n=Integer.parseInt(st.nextToken());
                graph[n-1].add(i+1);
                indegree[i]++;
            }

        }

        topology_sort();

        int idx=list.get(list.size()-1);

        System.out.println(ans[idx-1]);


    }

}
