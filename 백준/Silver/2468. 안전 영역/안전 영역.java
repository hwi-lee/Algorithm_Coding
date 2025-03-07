import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N, n, cnt, max;
	static int[][] map;
	static int check[];
	static ArrayList<Integer> a;
	static boolean[][] visited;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	
	public static void bfs(int i, int j, int n) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { i, j });
		visited[i][j]=true;
		while (!q.isEmpty()) {
			int[] p = q.poll();
			int r = p[0];
			int c = p[1];
			for(int k=0;k<4;k++) {
				int nr=r+dr[k];
				int nc=c+dc[k];
				if(nr<0||nr>=N||nc<0||nc>=N)
					continue;
				if(map[nr][nc]>n&&!visited[nr][nc]) {
					visited[nr][nc]=true;
					q.offer(new int[] {nr, nc});
					
				}
			}
		}
			
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		check = new int[101];
		a = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				check[map[i][j]]++;
			}
		}
        a.add(0);
		for(int i=0;i<check.length;i++) {
			if(check[i]>0)
				a.add(i);
		}
		
		for (int i = 0; i < a.size(); i++) {
			int n = a.get(i);
			visited = new boolean[N][N];
			cnt=0;
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if (!visited[j][k] && map[j][k] > n) {
						bfs(j, k, n);
						cnt++;
					}
				}
			}
			max=Math.max(max, cnt);
		}
		System.out.println(max);

	}

}
