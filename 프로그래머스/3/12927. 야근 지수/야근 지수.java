import java.util.*;
class Solution {
    static long ans;
    static void select(int n, int[] works){
    PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
    for(int i=0;i<works.length;i++){
        q.offer(works[i]);
    }
    for(int i=0;i<n;i++){
        int num=q.poll();
        if(num>0){
            num--;
            q.offer(num);
        }
        else{
            q.offer(num);
            break; //제일 큰 값이 0이면 모든 값이 0
        }
    }
    for(int i=0;i<works.length;i++){
        works[i]=q.poll();
        ans+=works[i]*works[i];
    }
        
    }
    public long solution(int n, int[] works) {
        long answer = 0;
        
        select(n, works);
        answer=ans;
        
        return answer;
    }
}