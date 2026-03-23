import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] graph;
	static int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };

	static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { r, c });
		while (!q.isEmpty()) {
			int[] p = (int[]) q.poll();
			int n = p[0];
			int m = p[1];
			if (n + 1 == N && m + 1 == M) {
				break;
			}
			for (int i = 0; i < 4; i++) {
				if (n + dr[i] < 0 || n + dr[i] >= N || m + dc[i] < 0 || m + dc[i] >= M)
					continue;
				if (graph[n + dr[i]][m + dc[i]] == 1) {
					q.offer(new int[] { n + dr[i], m + dc[i] });
					graph[n + dr[i]][m + dc[i]] = graph[n][m] + 1;
				}

			}
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s[] = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(s[j]);
			}
		}

		bfs(0, 0);
		System.out.println(graph[N - 1][M - 1]);

	}

}
