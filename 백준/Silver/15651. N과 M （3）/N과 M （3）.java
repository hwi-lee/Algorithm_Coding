import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {

    private static int[] p, nums;
    private static int N, R, count, M;
    private static boolean[] visited;

    private static void perm(int cnt, BufferedWriter bw) throws IOException {
        if (cnt == R) {
            count++;
            for (int j = 0; j < nums.length; j++) {
                bw.write(nums[j] + " ");
            }
            bw.write("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
         //   if (visited[i]) 
           //     continue;
            visited[i] = true;
            nums[cnt] = p[i];
            perm(cnt + 1, bw);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        p = new int[N];
        nums = new int[M];
        visited = new boolean[N];
        count = 0;
        R = M;

        for (int i = 0; i < N; i++) {
            p[i] = i + 1;
        }

        perm(0, bw);
        bw.flush(); 
        br.close();
        bw.close();
    }
}