import java.util.*;
class Solution {
    static boolean[] visited;
    public void bfs(int startnode, int n, int[][] computers){
        Queue<Integer> q=new ArrayDeque<>();
        q.offer(startnode);
        visited[startnode-1]=true;
        while(!q.isEmpty()){
            int size=q.size();
            for(int s=0;s<size;s++){
            int p=q.poll();
            for(int i=0;i<n;i++){
                int bool=computers[p-1][i];
                if(bool==1&&!visited[i]){ // p와 연결된 노드 큐에 삽입
                    q.offer(i+1);
                    visited[i]=true;
                }
            }
            }
        }
    }
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        visited=new boolean[n];
        for(int i=0;i<n;i++){
            if(!visited[i]){
            bfs(i+1, n, computers);
                answer++;
            }
            
        }
        return answer;
       
            
    }
}