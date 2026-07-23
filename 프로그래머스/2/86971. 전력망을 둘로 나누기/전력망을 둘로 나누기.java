import java.util.*;
class Solution {
    static ArrayList<Integer> graph[];
    static int bfs(int start, int end, int n){
        Queue<Integer> q= new ArrayDeque<>();
        boolean visited[] =new boolean[n];
        visited[start-1]=true;
        q.offer(start);
        int cnt=0;
        while(!q.isEmpty()){
            int p=q.poll();
            cnt++;
            for(int i=0;i<graph[p-1].size();i++){
                int next_node=graph[p-1].get(i);
                if(!visited[next_node-1]&&next_node!=end){
                    visited[next_node-1]=true;
                    q.offer(next_node);
                }
            }
        }
        return cnt;
    }
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        graph=new ArrayList[n];
        for(int i=0;i<n;i++){
            graph[i]=new ArrayList<>();
        }
        for(int i=0;i<wires.length;i++){
            int a=wires[i][0];
            int b=wires[i][1];
            graph[a-1].add(b);
            graph[b-1].add(a);
        }
        for(int i=0;i<wires.length;i++){
            int cnt1=bfs(wires[i][0], wires[i][1], n);
            int cnt2=n-cnt1;
            answer=Math.min(Math.abs(cnt1-cnt2), answer);
        }
        return answer;
    }
}