import java.io.*;
import java.util.*;

public class Main {

	static int x, y, cnt = 0;

	static void dfs(int[][] a, int j, int i) {
		if (i < 0)
			return;
		if (i >= x)
			return;
		if (j < 0)
			return;
		if (j >= y)
			return;
		if (a[j][i] == 0)
			return;

		if (a[j][i] == 1) {
			a[j][i] = 0;
		}
		dfs(a, j - 1, i);
		dfs(a, j, i - 1);
		dfs(a, j + 1, i);
		dfs(a, j, i + 1);

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			cnt=0;
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int a[][] = new int[y][x];
			int n[][] = new int[K][2];

			for (int t = 0; t < K; t++) {
				st = new StringTokenizer(br.readLine());
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				a[j][i] = 1;
				n[t][0] = i;
				n[t][1] = j;

			}

			for (int i = 0; i < K; i++) {
				if (a[n[i][1]][n[i][0]] == 1) {
					
					dfs(a, n[i][1], n[i][0]);
					cnt++;
				}

			}
			bw.write(String.valueOf(cnt+"\n"));
		}

		bw.flush();
	}

}
