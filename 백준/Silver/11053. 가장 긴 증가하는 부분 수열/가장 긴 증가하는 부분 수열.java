import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, ans;
    static int[] A, dp;

    static void dp_function(){
        for(int i=0;i<N;i++){
            dp[i] = 1; // 증가 수열 존재하지 않으면 최대 부분 수열의 크기는 1
            for(int j=0;j<i;j++){
              if(A[i]>A[j]){
                  dp[i]=Math.max(dp[i], dp[j]+1); //
              }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        A=new int[N];
        dp=new int[N]; //i번까지의 최대 부분 수열 길이

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            A[i]=Integer.parseInt(st.nextToken());
        }

        dp_function();

        for(int i=0;i<N;i++){
            ans=Math.max(ans, dp[i]);
        }

        System.out.println(ans);




    }
}
