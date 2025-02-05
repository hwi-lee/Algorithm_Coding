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
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

	public static void bfs(int r, int c) {

		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { r, c });

		while (!q.isEmpty()) {
			int[] p = q.poll();
			int n = p[0];
			int m = p[1];
			for (int i = 0; i < 4; i++) {
				if (n + dr[i] < 0 || n + dr[i] >= N || m + dc[i] < 0 || m + dc[i] >= M)
					continue;
				if (!visited[n + dr[i]][m + dc[i]] && map[n + dr[i]][m + dc[i]] != 0) {
					map[n + dr[i]][m + dc[i]] = map[n][m] + 1;
					q.offer(new int[] { n + dr[i], m + dc[i] });
					visited[n + dr[i]][m + dc[i]] = true;

				}
			}
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		int x = 0, y = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					x = i;
					y = j;
				}

			}
		}
		map[x][y] = 0;
		bfs(x, y);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !visited[i][j])
					map[i][j] = -1;
			}

		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}

	}

}
