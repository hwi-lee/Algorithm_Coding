import java.util.*;
//50 50 70 80
//50+80 >100 +1
//50+70>100 +1
//50+50=100 +1
//최대한 제한된 무게에 맞게 
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int l=0;
        int r=people.length-1;
        int cnt=0;
        Arrays.sort(people);
        while(l<=r){
            if(people[l]+people[r]>limit){ //무게초과
                r--;
                cnt++;
            }else{
                r--;
                l++;
                cnt++;
            }
        }
        System.out.println(cnt);
        answer=cnt;
        return answer;
    }
}