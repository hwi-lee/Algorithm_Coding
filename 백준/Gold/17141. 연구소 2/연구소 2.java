import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

class virus_position {
	int x;
	int y;

	public virus_position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {

	static int N, M, min = Integer.MAX_VALUE, ans;
	static int[][] map;
	static ArrayList<virus_position> nums;
	static boolean[][] visited;
	static boolean[] v = new boolean[10];
	static ArrayList<virus_position> virus = new ArrayList<virus_position>();
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void combi(int cnt, int start) {
		if (cnt == M) {

			bfs(nums);
			return;
		}
		for (int i = start; i < virus.size(); i++) {
			if (v[i])
				continue;
			v[i] = true;
			nums.add(new virus_position(virus.get(i).x, virus.get(i).y));
			combi(cnt + 1, i + 1);
			nums.remove(cnt);
			v[i] = false;

		}

	}

	public static boolean check() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] != -1)
					return true;
			}
		}
		return false;
	}

	public static void bfs(ArrayList<virus_position> p) {
		ans = 0;
		visited = new boolean[N][N];

		Queue<int[]> q = new ArrayDeque<int[]>();
		for (int i = 0; i < p.size(); i++) {
			q.offer(new int[] { p.get(i).x, p.get(i).y });
			visited[p.get(i).x][p.get(i).y] = true;

		}

		while (!q.isEmpty()) {
			int size = q.size();

			for (int t = 0; t < size; t++) {
				int[] f = q.poll();
				int r = f[0];
				int c = f[1];
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N)
						continue;
					if (!visited[nr][nc] && map[nr][nc] != -1) {
						q.offer(new int[] { nr, nc });
						visited[nr][nc] = true;

					}

				}
			}
			ans++;
		}

		if (check() == false) {
			min = Math.min(min, ans);
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		nums = new ArrayList<virus_position>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					virus.add(new virus_position(i, j));
				}
				if (map[i][j] == 1) {
					map[i][j] = -1;
				}
			}
		}

		combi(0, 0);
		if (min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min - 1);
	}

}
