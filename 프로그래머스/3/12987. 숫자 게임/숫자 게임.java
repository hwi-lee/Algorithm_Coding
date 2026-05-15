import java.util.*;
// 1 3 5 7 A
// 2 2 6 8 B
//
class Solution {
    static int n, ans;
    public int solution(int[] A, int[] B) {
        int answer = -1;
        n=A.length;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        for(int i=0;i<n;i++){
            int cnt=0;
            int idx=i;
            for(int j=0;j<n&&idx<n;j++, idx++){
                if(B[idx]>A[j]){
                    cnt++;
                }
            }
            
            ans=Math.max(cnt, ans);
            if(ans>n-i)
                break;
           
        }
      
        answer=ans;
        return answer;
    }
}