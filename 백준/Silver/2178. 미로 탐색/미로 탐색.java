import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {

	static int N, M;

	static void bfs(int[][] a, int i, int j) {
		Queue q = new LinkedList();
		q.offer(new int[] { i, j });

		while (!q.isEmpty()) {
			int[] p = (int[]) q.poll();
			int n = p[0];
			int m = p[1];
			if (n + 1 == N && m + 1 == M) {
				break;
			}
			if (n - 1 >= 0 && a[n - 1][m] == 1) {
				a[n - 1][m] = a[n][m] + 1;
				q.offer(new int[] { n - 1, m });
			}
			if (m - 1 >= 0 && a[n][m - 1] == 1) {
				a[n][m - 1] = a[n][m] + 1;
				q.offer(new int[] { n, m - 1 });
			}
			if (n + 1 < N && a[n + 1][m] == 1) {
				a[n + 1][m] = a[n][m] + 1;
				q.offer(new int[] { n + 1, m });
			}
			if (m + 1 < M && a[n][m + 1] == 1) {
				a[n][m + 1] = a[n][m] + 1;
				q.offer(new int[] { n, m + 1 });
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int a[][] = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s[] = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				a[i][j] = Integer.parseInt(s[j]);
			}
		}

		bfs(a, 0, 0);
		System.out.println(a[N - 1][M - 1]);

	}

}
