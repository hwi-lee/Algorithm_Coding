import java.util.*;
class Solution {
    static int ans;
    static void bfs(int start, int num, int idx, int numbers_length, int[] numbers, int target){
        Queue<int[]> q=new ArrayDeque<>();
        q.offer(new int[]{start+num, idx}); 
        q.offer(new int[]{start-num, idx});
        while(!q.isEmpty()){
            int size=q.size();
            for(int s=0;s<size;s++){
                int[] p=q.poll();
                int curnum=p[0]; //현재 계산된 값
                int curidx=p[1]; //현재 인덱스까지 더한 값
               
                if(curidx==numbers_length-1){ //현재 인덱스가 마지막 인덱스일 때 타킷이 되는 경우
                    if(target==curnum) ans++;
                }
                else{
                    q.offer(new int[]{curnum+numbers[curidx+1], curidx+1}); 
                    q.offer(new int[]{curnum-numbers[curidx+1], curidx+1});
                }
            }
            
        }
        
    }
    public int solution(int[] numbers, int target) {
        int answer = 0;
        int n=numbers.length;
        bfs(0, numbers[0], 0, n, numbers, target);
        System.out.println(ans);
        answer=ans;
        return answer;
    }
}