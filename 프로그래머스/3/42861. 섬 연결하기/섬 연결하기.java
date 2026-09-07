import java.util.*;
class Solution {
    static int[] parent;
    static ArrayList<Edge> info=new ArrayList<>();
    static int find(int x) {
        if(parent[x]==x){
            return x;
        }
        return parent[x]=find(parent[x]);
    }
    static void union(int a, int b) {
        a=find(a);
        b=find(b);
        parent[a]=b;
        
    }
    class Edge implements Comparable<Edge>{
        int a;
        int b;
        int c;
        
        Edge(int a, int b, int c){
            this.a=a;
            this.b=b;
            this.c=c;
        }
        public int compareTo(Edge o){
            return Integer.compare(c, o.c);
        }
    }
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n+1];
        
        for(int i=0;i<costs.length;i++){
            int a= costs[i][0];
            int b= costs[i][1];
            int c= costs[i][2];
            info.add(new Edge(a, b, c));
        }
        Collections.sort(info);
        for(int i=1;i<=n;i++){
            parent[i]=i;
        }
        
        for(Edge edge : info){
            if(find(edge.a)!=find(edge.b)){ //루트 노드가 다르면 합치기
                union(edge.a, edge.b);
                answer+=edge.c;
            }
        }
        
        return answer;
    }
}