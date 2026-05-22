import java.util.*;
class Solution {
    static int[][] num;
    static int ans;
    static boolean[] visited;
    static void function(int k, int[][] num){
        int cnt=0;
        for(int i=0;i<num.length;i++){
            int d1=num[i][0];
            int d2=num[i][1];
            if(k>=d1){
                k-=d2;
                cnt++;
            }
        }
        ans=Math.max(ans, cnt);
    }
    static void perm(int n, int size, int[][] dungeons, int k){
        if(n>=size){
            // for(int a=0;a<size;a++){
            //     for(int b=0;b<2;b++){
            //         System.out.print(num[a][b]+" ");
            //     }
            //     System.out.print(", ");
            // }
            // System.out.println();
            function(k, num);
            return;
        }
        for(int i=0;i<size;i++){
            if(visited[i])
                continue;
            visited[i]=true;
            num[n][0]=dungeons[i][0];
            num[n][1]=dungeons[i][1];
            perm(n+1, size, dungeons, k);
            visited[i]=false;
        }
        
    }
   
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        visited=new boolean[dungeons.length];
        num=new int[dungeons.length][2];
        perm(0, dungeons.length, dungeons, k);
        System.out.println(ans);
        answer=ans;
        return answer;
    }
}
//최대 던전 수
//소모 피로도 작은 값부터 탐색 or 최소 필요 피로도 큰값부터 탐색 -> 예외 있음
//bfs보다 정렬..?순열?

