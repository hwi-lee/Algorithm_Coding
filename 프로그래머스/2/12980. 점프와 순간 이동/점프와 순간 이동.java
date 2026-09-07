import java.util.*;
public class Solution {
    static int cnt;
    public void function(int n){
        while(n>0){
        if(n%2==0){ //짝수
            n/=2;
        }else{
            cnt++;
            n--;
        }
        }
    }
    public int solution(int n) {
        function(n);
        int ans=cnt;
     
        return ans;
    }
}