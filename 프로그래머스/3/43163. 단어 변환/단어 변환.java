import java.util.*;
class Solution {
    static int length;
    static boolean[] visited;
    public int bfs(String begin, String target, String[] words){
        Queue<String> q=new ArrayDeque<>();
        int ans=0;
        q.offer(begin);
        while(!q.isEmpty()){
            int size=q.size();
            System.out.println(size);
            for(int s=0;s<size;s++){
                String p=q.poll();
                if(p.equals(target))
                    return ans;
                for(int i=0;i<words.length;i++){
                    if(!visited[i]){
                        int cnt=0;
                        String string=words[i];
                        for(int j=0;j<length;j++){
                            if(p.charAt(j)==string.charAt(j))//p와 문자하나만 다른 문자찾기
                                cnt++;
                        }
                        if(cnt==length-1){
                            visited[i]=true;
                            q.offer(string);
                        }
                    }
                }
            }
            ans++;
        }
        return 0;
    }
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        // 모든 단어의 길이는 같다.
        length=begin.length();
        visited=new boolean[words.length];
        
        answer=bfs(begin, target, words);
      
        return answer;
        
    }
}