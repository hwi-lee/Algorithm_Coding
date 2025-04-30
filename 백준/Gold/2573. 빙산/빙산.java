import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, cnt, ans;
	static int[][] map;
	static int[][] copymap;
	static boolean[][] visited;
	static int dr[] = { -1, 0, 1, 0 };
	static int dc[] = { 0, 1, 0, -1 };

	static boolean check() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					return false;
				}
			}
		}
		return true;

	}

	static void function(int i, int j) {
		int cnt = 0;
		for (int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			if (nr < 0 || nr >= N || nc < 0 || nc >= M)
				continue;
			if (map[nr][nc] == 0) {
				cnt++;
			}
		}
		copymap[i][j] -= cnt;
		copymap[i][j] = copymap[i][j] < 0 ? 0 : copymap[i][j];

	}

	static void bfs(int i, int j) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] { i, j });
		visited[i][j] = true;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {
				int p[] = q.poll();
				int r = p[0];
				int c = p[1];
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (nr < 0 || nr >= N || nc < 0 || nc >= M)
						continue;
					if (map[nr][nc] != 0 && !visited[nr][nc]) {
						visited[nr][nc] = true;
						q.offer(new int[] { nr, nc });
					}

				}
			}

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		copymap = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				copymap[i][j] = map[i][j];
			}
		}
		int end = 0;
		while (check() != true) {
			end++;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] != 0) {
						function(i, j);
					}
				}
			}
			ans++;
			// map=copymap;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = copymap[i][j];
				}
			}

			int c = 0;
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] != 0 && !visited[i][j]) {
						bfs(i, j);
						c++;
					}
				}
			}
			if (c >= 2) {
				end = 0;
				break;
			}
		}
		if (end == 0)
			System.out.println(ans);
		else
			System.out.println(0);
	}

}
