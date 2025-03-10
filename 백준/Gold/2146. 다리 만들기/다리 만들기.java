import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, num = 2, cnt, min = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void init_bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { i, j });
		visited[i][j] = true;
		map[i][j] = num;
		while (!q.isEmpty()) {
			int p[] = q.poll();
			int r = p[0];
			int c = p[1];
			for (int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue;
				if (!visited[nr][nc] && map[nr][nc] == 1) {
					q.offer(new int[] { nr, nc });
					map[nr][nc] = num;
					visited[nr][nc] = true;
				}
			}
		}
	}

	public static int bfs(int i, int j, int n) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { i, j });
		visited[i][j] = true;
		while (!q.isEmpty()) {
			int size = q.size();

			for (int t = 0; t < size; t++) {
				int p[] = q.poll();
				int r = p[0];
				int c = p[1];
				for (int k = 0; k < 4; k++) {
					int nr = r + dr[k];
					int nc = c + dc[k];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N)
						continue;
					if (!visited[nr][nc] && map[nr][nc] == 0) {
						q.offer(new int[] { nr, nc });
						visited[nr][nc] = true;
					}
					if (!visited[nr][nc] && map[nr][nc] != 0 && map[nr][nc] != n) {
						return cnt;
					}
				}
			}
			cnt++;
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					init_bfs(i, j);
					num++;

				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0) {
					cnt = 0;
					visited = new boolean[N][N];
					cnt = bfs(i, j, map[i][j]);
					if (cnt != -1 && cnt != 0)
						min = Math.min(min, cnt);
				}
			}
		}

		System.out.println(min);
	}

}
