import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main{

	static int N, M, min = Integer.MAX_VALUE, cnt;
	static int[][] map, cmap;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static ArrayList<int[]> position = new ArrayList<int[]>();
	static ArrayList<int[]> model[];

	static void count() {
		cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (cmap[i][j] == 0)
					cnt++;
			}
		}
		min = Math.min(min, cnt);
	}

	static void function(int[] n, int r, int c) {
		for (int i = 0; i < n.length; i++) {
			int d = n[i];
			int z = 1;
			while (true) {
				int nr = r + dr[d] * z;
				int nc = c + dc[d] * z;
				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					break;
				if (cmap[nr][nc] == 6)
					break;
				if (cmap[nr][nc] == 0) {
					cmap[nr][nc] = -1;
				} else if (cmap[nr][nc] < 0) {
					cmap[nr][nc] -= 1;
				}
				z++;
			}
		}
	}

	static void dfs(int cnt) {
		if (cnt >= position.size()) {
			count();
			return;
		}

		int[][] backup = new int[N][M];
		for (int i = 0; i < N; i++) {
			backup[i] = cmap[i].clone();
		}

		int r = position.get(cnt)[0];
		int c = position.get(cnt)[1];
		int n = map[r][c];

		for (int i = 0; i < model[n - 1].size(); i++) {
			function(model[n - 1].get(i), r, c);
			dfs(cnt + 1);

			// cmap 복원
			for (int j = 0; j < N; j++) {
				cmap[j] = backup[j].clone();
			}
		}
	}

	static void init() {
		model[0].add(new int[] { 0 });
		model[0].add(new int[] { 1 });
		model[0].add(new int[] { 2 });
		model[0].add(new int[] { 3 });

		model[1].add(new int[] { 0, 2 });
		model[1].add(new int[] { 1, 3 });

		model[2].add(new int[] { 0, 1 });
		model[2].add(new int[] { 1, 2 });
		model[2].add(new int[] { 2, 3 });
		model[2].add(new int[] { 3, 0 });

		model[3].add(new int[] { 0, 1, 2 });
		model[3].add(new int[] { 0, 1, 3 });
		model[3].add(new int[] { 1, 2, 3 });
		model[3].add(new int[] { 0, 2, 3 });

		model[4].add(new int[] { 0, 1, 2, 3 });
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cmap = new int[N][M];
		model = new ArrayList[5];
		for (int i = 0; i < 5; i++) {
			model[i] = new ArrayList<int[]>();
		}
		init();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				cmap[i][j] = map[i][j];
				if (map[i][j] > 0 && map[i][j] < 6) {
					position.add(new int[] { i, j });
				}
			}
		}

		dfs(0);
		System.out.println(min);
	}
}
