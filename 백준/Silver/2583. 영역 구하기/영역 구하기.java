import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int M, N, K, cnt, count;
	static ArrayList<Integer> ans;
	static int[][] map;
	static boolean[][] visited;
	static int dr[] = { -1, 0, 1, 0 };
	static int dc[] = { 0, 1, 0, -1 };

	public static void init(int r_s, int r_e, int c_s, int c_e) {
		for (int i = r_s; i <= r_e; i++) {
			for (int j = c_s; j <= c_e; j++) {
				map[j][i] = 1;
			}
		}
	}

	public static void bfs(int i, int j) {

		Queue<int[]> q = new LinkedList<int[]>();
		cnt = 1;
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
					if (nr < 0 || nr >= M || nc < 0 || nc >= N)
						continue;
					if (!visited[nr][nc] && map[nr][nc] == 0) {
						q.offer(new int[] { nr, nc });
						visited[nr][nc] = true;
						cnt++;
					}

				}
			}
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		ans = new ArrayList<Integer>();
		visited = new boolean[M][N];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			init(x1, x2 - 1, y1, y2 - 1);

		}

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 0) {
					bfs(i, j);
					count++;
					ans.add(cnt);
				}
			}
		}
		System.out.println(count);
		Collections.sort(ans);
		for (int i = 0; i < ans.size(); i++) {
			System.out.print(ans.get(i) + " ");
		}

	}

}
