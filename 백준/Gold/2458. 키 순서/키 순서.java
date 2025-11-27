import java.util.Scanner;

public class Main {

	static int N,M,ans;
	static int[][] graph;
	static boolean[][] visited;
	public static void function() {
		for(int k=0;k<N;k++) {
			for(int i=0;i<N;i++){
				for(int j=0;j<N;j++) {
					if(graph[i][k]==1&&graph[k][j]==1)
						graph[i][j]=1;
				}
			}
		}
	}
	public static void check(int node) {
		int cnt=0;
		for(int i=0;i<N;i++) {
			if(graph[node-1][i]==1)
				cnt++;
		}
		for(int i=0;i<N;i++) {
			if(graph[i][node-1]==1)
				cnt++;
		}
		if(cnt==N-1)
			ans++;
	}
	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);
		//	int T=sc.nextInt();
			ans=0;
			N=sc.nextInt();
			M=sc.nextInt();
			visited=new boolean[N][N];
			graph=new int[N][N];
			for(int i=0;i<M;i++) {
				int u=sc.nextInt();
				int v=sc.nextInt();
				graph[u-1][v-1]=1;
			}
			
			
			function();
			
			for(int i=0;i<N;i++) {
				check(i+1);
			}
			System.out.println(ans);
			
		
	}
}
