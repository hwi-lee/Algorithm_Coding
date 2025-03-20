import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {

	static int M, N, H, x = 0, ans = 0, cnt = 0;
	static int a[][][];

	static void bfs(Queue<int[]> startpoint) {
		Queue<int[]> q = new LinkedList();

		int size = startpoint.size();
		for (int i = 0; i < size; i++) {
			q.offer(startpoint.poll());
		}

		while (!q.isEmpty()) {
			cnt++;
			int[] p = q.poll();
			int h = p[0];
			int n = p[1];
			int m = p[2];
			if (h - 1 >= 0 && a[h - 1][n][m] == 0) {
				a[h - 1][n][m] = a[h][n][m] + 1;
				ans = Math.max(ans, a[h][n][m]);
				q.offer(new int[] { h - 1, n, m });
			}
			if (h + 1 < H && a[h + 1][n][m] == 0) {
				a[h + 1][n][m] = a[h][n][m] + 1;
				ans = Math.max(ans, a[h][n][m]);
				q.offer(new int[] { h + 1, n, m });
			}
			if (n - 1 >= 0 && a[h][n - 1][m] == 0) {
				a[h][n - 1][m] = a[h][n][m] + 1;
				ans = Math.max(ans, a[h][n][m]);
				q.offer(new int[] { h, n - 1, m });
			}
			if (n + 1 < N && a[h][n + 1][m] == 0) {
				a[h][n + 1][m] = a[h][n][m] + 1;
				ans = Math.max(ans, a[h][n][m]);
				q.offer(new int[] { h, n + 1, m });
			}
			if (m - 1 >= 0 && a[h][n][m - 1] == 0) {
				a[h][n][m - 1] = a[h][n][m] + 1;
				ans = Math.max(ans, a[h][n][m]);
				q.offer(new int[] { h, n, m - 1 });
			}
			if (m + 1 < M && a[h][n][m + 1] == 0) {
				a[h][n][m + 1] = a[h][n][m] + 1;
				ans = Math.max(ans, a[h][n][m]);
				q.offer(new int[] { h, n, m + 1 });
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		a = new int[H][N][M];
		Queue<int[]> startpoint = new LinkedList();

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < M; k++) {
					a[i][j][k] = Integer.parseInt(st.nextToken());
					if (a[i][j][k] == 1) {
						startpoint.offer(new int[] { i, j, k });
					}
					if (a[i][j][k] == -1)
						x++;
				}
			}
		}
		bfs(startpoint);

		if (N * M * H - x == cnt)
			bw.write(String.valueOf(ans));
		else
			bw.write(String.valueOf("-1"));

		bw.flush();
	}

}
