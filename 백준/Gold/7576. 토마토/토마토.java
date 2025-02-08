import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {

	static int M, N, x = 0, y = 0, ans = 0, cnt = 0;
	static int a[][];

	static void bfs(Queue<int[]> startpoint) {
		Queue<int[]> q = new LinkedList();

		int size = startpoint.size();
		for (int i = 0; i < size; i++) {
			q.offer(startpoint.poll());
		}

		while (!q.isEmpty()) {
			int p[] = q.poll();
			cnt++;
			int n = p[0];
			int m = p[1];
			if (n - 1 >= 0 && a[n - 1][m] == 0) {
				q.offer(new int[] { n - 1, m });
				a[n - 1][m] = a[n][m] + 1;
				ans = Math.max(ans, a[n][m]);
			}
			if (n + 1 < N && a[n + 1][m] == 0) {
				q.offer(new int[] { n + 1, m });
				a[n + 1][m] = a[n][m] + 1;
				ans = Math.max(ans, a[n][m]);
			}
			if (m - 1 >= 0 && a[n][m - 1] == 0) {
				q.offer(new int[] { n, m - 1 });
				a[n][m - 1] = a[n][m] + 1;
				ans = Math.max(ans, a[n][m]);
			}
			if (m + 1 < M && a[n][m + 1] == 0) {
				q.offer(new int[] { n, m + 1 });
				a[n][m + 1] = a[n][m] + 1;
				ans = Math.max(ans, a[n][m]);
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		a = new int[N][M];
		Queue startpoint = new LinkedList();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
				if (a[i][j] == -1)
					x++;
				if (a[i][j] == 1) {
					startpoint.offer(new int[] { i, j });
					y++;
				}
			}
		}

		bfs(startpoint);

		if (N * M - x == cnt)
			bw.write(String.valueOf(ans));
		else
			bw.write(String.valueOf("-1"));

		bw.flush();
	}

}
