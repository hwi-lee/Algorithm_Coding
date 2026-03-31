import java.util.*;
class Solution {
    static int[] dr={-1, 0, 1, 0};
    static int[] dc={0, 1, 0, -1};
    static int ans;
    static boolean[][] visited;
    
    static int bfs(int n, int m, int[][] maps){
        Queue<int[]> q=new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        
        while(!q.isEmpty()){
            int size=q.size();
            ans++;
            for(int s=0;s<size;s++){
                int[] p=q.poll();
                int r=p[0];
                int c=p[1];
             
                if(r==n-1&&c==m-1){
                    return ans;
                }
                for(int d=0;d<4;d++){
                    int nr=r+dr[d];
                    int nc=c+dc[d];
                    if(nr<0||nr>=n||nc<0||nc>=m)
                        continue;
                    
                    if(!visited[nr][nc]&&maps[nr][nc]==1){
                        visited[nr][nc]=true;
                        q.offer(new int[]{nr, nc});
                    }
                }
                
            }
        }
        
    return -1;
    }
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        int n=maps.length;
        int m=maps[0].length;
        
        visited=new boolean[n][m];
        
        answer= bfs(n, m, maps);
        return answer;
    }
}