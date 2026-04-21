import java.util.*;
class Solution {
    // 7
    // 3 8
    // 8 1 0
    // 2 7 4 4
    // 4 5 2 6 5
static int[][] dp; //dp[i][j]에 위치한 값이 현재 위치까지의 최대값
    static void dp_function(int h, int[][] triangle){
        dp[0][0]=triangle[0][0];
        for(int i=1;i<h;i++){
            for(int j=0;j<i+1;j++){
                if(j==0){
                    dp[i][j]=Math.max(dp[i][j], dp[i-1][j]+triangle[i][j]);
                    continue;
                }
                dp[i][j]=Math.max(Math.max(dp[i][j], dp[i-1][j]+triangle[i][j]), dp[i-1][j-1]+triangle[i][j]);
                
            }
        }
        
    }
    public int solution(int[][] triangle) {
        int answer = 0;
        int h=triangle.length;
        dp=new int[h][h];
        
       dp_function(h, triangle);
        
        for(int i=0;i<h;i++){
            answer=Math.max(answer, dp[h-1][i]);
        }
        
        return answer;
    }
}