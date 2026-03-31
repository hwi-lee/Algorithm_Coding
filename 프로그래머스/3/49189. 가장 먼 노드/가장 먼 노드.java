import java.util.*;
class Solution {
    static ArrayList<Integer> graph[];
    static boolean[] visited;
    static int cnt, ans=1;
    static void bfs(){
        Queue<Integer> q=new ArrayDeque<>();
        q.offer(1);
        visited[0]=true;
        while(!q.isEmpty()){
            int size=q.size();
            cnt=0;
            for(int s=0;s<size;s++){
                int p=q.poll();
                for(int i=0;i<graph[p-1].size();i++){
                    int nextnode=graph[p-1].get(i);
                    if(!visited[nextnode-1]){
                        visited[nextnode-1]=true;
                        q.offer(nextnode);
                        cnt++;
                    }
                }
            }
            if(cnt!=0){
                ans=cnt;
            }
        }
        
    }
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        graph=new ArrayList[n];
        visited=new boolean[n];
        for(int i=0;i<n;i++){
            graph[i]=new ArrayList<>();
        }
        for(int i=0;i<edge.length;i++){
            for(int j=0;j<2;j++){
                int u=edge[i][0];
                int v=edge[i][1];
                graph[u-1].add(v);
                graph[v-1].add(u);
            }
        }
        bfs();
        answer=ans;
        return answer;
    }
}