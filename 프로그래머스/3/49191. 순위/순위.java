import java.util.*;
class Solution {
    // 내가 이긴 사람 + 날 이긴 사람 = n-1
    static int[][] map;
    static int ans;
    static void floy_warshall(int n){
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(map[i][k]==1&&map[k][j]==1){
                        map[i][j]=1;
                    }
                }
            }
        }
    }
    static void check(int n){
        for(int i=0;i<n;i++){
           int cnt=0;
            for(int j=0;j<n;j++){
                if(map[i][j]==1)
                    cnt++;
                if(map[j][i]==1){
                    cnt++;
                }
            }
            if(cnt==n-1)
                ans++;
        }
        
    }
    public int solution(int n, int[][] results) {
        int answer = 0;
        map=new int[n][n];
        for(int i=0;i<results.length;i++){
            int a=results[i][0];
            int b=results[i][1];
            map[a-1][b-1]=1;
        }
        floy_warshall(n);
        check(n);
        System.out.println(ans);
        answer=ans;
        return answer;
    }
}