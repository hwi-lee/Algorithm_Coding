import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, cnt, max;
	static char map[][];
	static int copymap[][];
	static boolean visited[][];
	static ArrayList<int[]> land;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { i, j });
		visited = new boolean[N][M];
		visited[i][j] = true;
		int z = 1;
		cnt = 0;
		while (!q.isEmpty()) {
			int p[] = q.poll();
			int r = p[0];
			int c = p[1];
			if (copymap[r][c] > z) {
				cnt++;
				z = copymap[r][c];
			}
			for (int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;
				if (map[nr][nc] == 'L' && !visited[nr][nc]) {
					q.offer(new int[] { nr, nc });
					visited[nr][nc] = true;
					copymap[nr][nc] = copymap[r][c] + 1;

				}
			}

		}

		init();

	}

	public static void init() {
		for (int i = 0; i < land.size(); i++) {
			int r = land.get(i)[0];
			int c = land.get(i)[1];
			if (copymap[r][c] > 1) {
				copymap[r][c] = 1;
			}

		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		copymap = new int[N][M];
		land = new ArrayList<int[]>();
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'L') {
					copymap[i][j] = 1;
					land.add(new int[] { i, j });
				}
			}
		}

		for (int i = 0; i < land.size(); i++) {
			int r = land.get(i)[0];
			int c = land.get(i)[1];
			bfs(r, c);
			max = Math.max(cnt, max);
		}
		System.out.println(max);

	}

}
