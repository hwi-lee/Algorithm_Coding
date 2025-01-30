import java.io.*;
import java.util.*;

public class Main {

	private static int[] p;
	private static int N,R,count,M;
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
		perm(0);
		//System.out.println(count);

	}
	private static void perm(int cnt) {
		if(cnt==R) {
			count++;
			for(int j=0;j<nums.length;j++) {
				System.out.print(nums[j]+" ");
			}
			System.out.println();
			
			return;
			}
		for(int i=0;i<N;i++) {
			if(visited[i])
				continue;
			visited[i]=true;
			nums[cnt]=p[i];
			perm(cnt+1);
			nums[cnt]=0;
			visited[i]=false;
			
		}
	}

	
}
