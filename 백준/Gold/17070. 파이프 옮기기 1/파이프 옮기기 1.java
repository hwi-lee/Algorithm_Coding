
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, cnt;
	static int[][] map;
	static boolean[][] visited;

	static int[] dr = { 0, 1, 1 };// 우 대 하
	static int[] dc = { 1, 1, 0 };

	// 0->1,2
	// 1->0,1,2
	// 2->0,1

	public static void bfs(int x_s, int y_s, int x_e, int y_e) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { x_s, y_s, x_e, y_e });
		while (!q.isEmpty()) {
		
			int p[] = q.poll();
			int r_s = p[0], c_s = p[1];
			int r_e = p[2], c_e = p[3];

			
			if (c_s == c_e) { // 상
				for (int j = 1; j <= 2; j++) {
					int nr = r_e + dr[j];
					int nc = c_e + dc[j];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 1)
						continue;
					if (j == 1 && (map[nr - 1][nc] == 1 || map[nr][nc - 1] == 1))
						continue;
					if (nr == N - 1 && nc == N - 1) {
						cnt++;
						continue;
					}
					q.offer(new int[] { r_e, c_e, nr, nc });

				}
			} else if (r_s == r_e) {// 좌
				for (int j = 0; j <= 1; j++) {
					int nr = r_e + dr[j];
					int nc = c_e + dc[j];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 1)
						continue;
					if (j == 1 && (map[nr - 1][nc] == 1 || map[nr][nc - 1] == 1))
						continue;
					if (nr == N - 1 && nc == N - 1) {
						cnt++;
						continue;
					}
					q.offer(new int[] { r_e, c_e, nr, nc });

				}

			} else {
				for (int j = 0; j <= 2; j++) {
					int nr = r_e + dr[j];
					int nc = c_e + dc[j];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 1)
						continue;
					if (j == 1 && (map[nr - 1][nc] == 1 || map[nr][nc - 1] == 1))
						continue;
					if (nr == N - 1 && nc == N - 1) {
						cnt++;
						continue;
					}
					q.offer(new int[] { r_e, c_e, nr, nc });

				}

			}

			
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
			}
		}

		bfs(0, 0, 0, 1);

		System.out.println(cnt);

	}

}
