import java.util.*;
//한칸 또는 두칸
//dp[i] -> i칸까지 왔을 때의 경우의 수
//dp[1]=1 (1)
//dp[2]=2 (1,1) (2)
//dp[3]=3 (1,1,1) (1,2) (2,1) -> dp[2]+1 or dp[1]+2
//dp[4]=dp[3]+1, dp[2]+2 -> 마지막이 1또는2
//(1,1,1,1) (1,2,1) (2,1,1) -> dp[2]+1
//(1,1,2) (2,2)
//dp[n-1]+1, dp[n-2]+2


class Solution {
    static int dp[];
    static void dp_function(int n){
        if(n==1){
            dp[1]=1; //1칸
            return;
        }
        dp[1]=1;
        dp[2]=2;
        for(int i=3;i<=n;i++){ 
            dp[i]=(dp[i-1]+dp[i-2]) % 1234567;
        }
    }
    public long solution(int n) {
        long answer = 0;
        dp=new int[n+1];
        dp_function(n);
        answer=dp[n];
        return answer;
    }
}