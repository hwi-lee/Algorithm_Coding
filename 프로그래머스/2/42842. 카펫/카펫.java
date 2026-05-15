import java.util.*;
class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        answer=new int[2];
        for(int i=1;i<=yellow;i++){ 
            if(yellow%i==0){
                int r=i;
                int c=yellow/i;
                if(brown==(2*(r+2))+(2*c)){
                    answer[0]=r+2;
                    answer[1]=c+2;
                    break;
                }
            }
        }
        if(answer[0]<answer[1]){
            int temp=answer[0];
            answer[0]=answer[1];
            answer[1]=temp;
        }
        return answer;
    }
}
//24= 4 6
//6 + 6 + 6 + 6 -> 6, 8    //c=4, r=6
//8 + 8 + 4 + 4 -> 4, 10 ...8 6 인데,   //r=6, c=4

