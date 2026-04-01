import java.util.*;
import java.io.*;
class Solution {
    //1 A: 25, B: 63
    //3 A: 26, B: 22
    //5 A: 2, B: 46
    //4 A: 35, B: 66
    //2(B) A: 48, B: 0
    //6(A) A: 0, B: 48
    //1 2 3 4 5 6
    //25 48 26 35 2 0
    //63 0 22 66 46 48
    
    //시작점에서 어느 지점까지 같이 간다....어느 지점까지의 최단경로
    //어느 지점부터 시작해서 각각 A, B까지의 최단경로의 합
    

    static ArrayList<int[]> graph[];
    static int[] dist, dist_copy;
    static int INF=Integer.MAX_VALUE;
    static class Node implements Comparable<Node>{
        int v;
        int w;
        Node(int v, int w){
            this.v=v;
            this.w=w;
        }
        public int compareTo(Node o){
            return Integer.compare(w, o.w);
        }
    }
    
    static void dijkstra(int s){
        PriorityQueue<Node> q=new PriorityQueue<>();
        dist[s-1]=0;
        q.offer(new Node(s, 0));
        while(!q.isEmpty()){
            Node p=q.poll();
            int curnode=p.v;
            int curdist=p.w;
            
            if(dist[curnode-1]<curdist)
                continue;
            for(int i=0;i<graph[curnode-1].size();i++){
                int nextnode=graph[curnode-1].get(i)[0];
                int nextdist=graph[curnode-1].get(i)[1];
                
                if(curdist+nextdist<dist[nextnode-1]){
                    dist[nextnode-1]=curdist+nextdist;
                    q.offer(new Node(nextnode, dist[nextnode-1]));
                }
                
            }
        }
        
    }
    
    static void dijkstra_copy(int node){
        PriorityQueue<Node> q=new PriorityQueue<>();
        
        for(int i=0;i<graph.length;i++){
            dist_copy[i]=INF;
        }
        
        dist_copy[node-1]=0;
        q.offer(new Node(node, 0));
        while(!q.isEmpty()){
            Node p=q.poll();
            int curnode=p.v;
            int curdist=p.w;
            
            if(dist_copy[curnode-1]<curdist)
                continue;
            for(int i=0;i<graph[curnode-1].size();i++){
                int nextnode=graph[curnode-1].get(i)[0];
                int nextdist=graph[curnode-1].get(i)[1];
                
                if(curdist+nextdist<dist_copy[nextnode-1]){
                    dist_copy[nextnode-1]=curdist+nextdist;
                    q.offer(new Node(nextnode, dist_copy[nextnode-1]));
                }
                
            }
        }
        
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        graph=new ArrayList[n];
        dist=new int[n];
        dist_copy=new int[n];
        for(int i=0;i<n;i++){
            graph[i]=new ArrayList<>();
            dist[i]=INF;
            dist_copy[i]=INF;
        }
        for(int i=0;i<fares.length;i++){
            int c=fares[i][0];
            int d=fares[i][1];
            int f=fares[i][2];
            graph[c-1].add(new int[]{d, f});
            graph[d-1].add(new int[]{c, f});
        }
        dijkstra(s);
        int ans=INF;
        for(int i=0;i<n;i++){
            dijkstra_copy(i+1); 
            ans=Math.min(dist[i]+dist_copy[a-1]+dist_copy[b-1], ans);
            
        }
        answer=ans;
       
        return answer;
    }
}