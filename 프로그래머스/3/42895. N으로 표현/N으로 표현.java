import java.util.*;
//dp[i] = N을 i개 사용해서 만들 수 있는 모든 숫자들의 집합
//i=1일 때, 5
//i=2일 때, 55, 5+5, 5-5, 5/5, 5*5 -> 55, 10, 0, 1, 25
//i=3일 때, 555, 5+5+5, 5-5-5, 5/5/5, 5*5*5, 5+5-5, 5+5/5 5+5*5 ...
//dp[1], dp[3] 순서에 따라 값이 다름
//2 -> 1,1
//3 -> 1,2  2,1
//4 -> 1,3  2,2  3,1
//5 -> 1,4  2,3  3,2  4,1
class Solution {
    static HashSet<Integer> dp[];
    
    static void calc(int a, int b, int n, int N){ //dp[1] dp[4] -> dp[5]에 저장
       for (int x : dp[a]){
           for (int y : dp[b]){
               dp[n].add(x+y);
               dp[n].add(x-y);
               dp[n].add(x*y);
               if(y!=0)
                   dp[n].add(x/y);
            }
       }
    }
    
    static int dp_function(int N, int number){
        dp[1].add(N);
         if(dp[1].contains(number)){
                return 1;
            }
        
        for(int i=2;i<9;i++){ //dp[2]~dp[8] 구하기
            int target=i;
            
            int conc=0;
            int k=1;
            for(int c=0;c<target;c++){
                conc+=N*k;
                k*=10;
            }
            dp[target].add(conc);
            
            for(int j=1;j<target;j++){
                calc(j, target-j, target, N);
            }
            if(dp[i].contains(number)){
                return i;
            }
        }
        
        return -1;
    }
    public int solution(int N, int number) {
        int answer = 0;
        dp=new HashSet[9];
        for(int i=0;i<9;i++){
            dp[i]=new HashSet<>();
        }
        answer=dp_function(N, number);
        return answer;
    }
}