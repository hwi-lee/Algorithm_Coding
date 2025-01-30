import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
//조합
	private static int[] p;
	private static int N,R,count,sum=0,M;
	private static int[] nums;
	private static boolean[] visited;
 	public static void main(String[] args) throws IOException {
 		
 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
 		p=new int[N];
		for(int i=0;i<N;i++) {
			p[i]=i+1;
		}
 		
		count=0;
		R=M;
		nums=new int[R];
		visited= new boolean[N];
		
		
		combi(0,0);
		//System.out.println(count);

	}
	private static void combi(int cnt,int start) {
		
		if(cnt==R) {
			count++;
			for(int j=0;j<nums.length;j++) {
				System.out.print(nums[j]+" ");
			}
			System.out.println();
			
			//System.out.println(Arrays.toString(nums));
	
			return;
			}
		for(int i=start;i<N;i++) {
			
			visited[i]=true;
			nums[cnt]=p[i];
			combi(cnt+1, i+1);
			nums[cnt]=0;
			visited[i]=false;
			
		}
	}

}


