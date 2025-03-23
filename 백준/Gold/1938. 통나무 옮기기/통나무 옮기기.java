import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

class T {
	int r, c, d;

	public T(int r, int c, int d) {
		this.r = r;
		this.c = c;
		this.d = d;
	}
}

public class Main {
	static int N, cnt, end_d;
	static char map[][];
	static boolean visited[][][];
	static int end[], start[];
	static int dr[] = { -1, 0, 1, 0, 0 };
	static int dc[] = { 0, 1, 0, -1, 0 };
	static int dx[] = { -1, 0, 1, 0, 0, -1, -1, 1, 1 };
	static int dy[] = { 0, 1, 0, -1, 0, 1, -1, -1, 1 };

	public static boolean check(int i, int r, int c, int d) {
		if (i == 4) {
			for (int j = 0; j < 9; j++) {
				int nr = r + dx[j];
				int nc = c + dy[j];
				if (nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] == '1')
					return false;
			}
		} else {
			if (d == 1) {
				if (r - 1 < 0 || r + 1 >= N || map[r][c] == '1' || map[r - 1][c] == '1' || map[r + 1][c] == '1') {
					return false;
				}
			} else {
				if (c - 1 < 0 || c + 1 >= N || map[r][c] == '1' || map[r][c - 1] == '1' || map[r][c + 1] == '1') {
					return false;
				}
			}
		}
		return true;
	}

	public static void bfs(int x, int y, int d) {
		Queue<T> q = new ArrayDeque<>();
		q.offer(new T(x, y, d));

		visited = new boolean[2][N][N];
		visited[d - 1][x][y] = true;
		cnt = 0;

		while (!q.isEmpty()) {
			int size = q.size();
			for (int t = 0; t < size; t++) {
				T cur = q.poll();
				int r = cur.r;
				int c = cur.c;
				int dir = cur.d;

				if (r == end[0] && c == end[1] && dir == end_d) {
					System.out.println(cnt);
					return;
				}

				for (int i = 0; i < 5; i++) {
					int nr = dr[i] + r;
					int nc = dc[i] + c;
					int newDir = dir;

					if (i != 4) {
						if (dir == 1) {
							if (nr - 1 < 0 || nr + 1 >= N || nc < 0 || nc >= N)
								continue;
						} else {
							if (nc - 1 < 0 || nc + 1 >= N || nr < 0 || nr >= N)
								continue;
						}
					} else {
						if (nr - 1 < 0 || nr + 1 >= N || nc - 1 < 0 || nc + 1 >= N)
							continue;
						newDir = dir == 1 ? 2 : 1;
					}

					if (!visited[newDir - 1][nr][nc] && check(i, nr, nc, dir)) {
						visited[newDir - 1][nr][nc] = true;
						q.offer(new T(nr, nc, newDir));
					}
				}
			}
			cnt++;
		}

		System.out.println(0);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		ArrayList<int[]> list_B = new ArrayList<>();
		ArrayList<int[]> list_E = new ArrayList<>();

		start = new int[2];
		end = new int[2];
		map = new char[N][N];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < s.length(); j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'B')
					list_B.add(new int[] { i, j });
				if (map[i][j] == 'E')
					list_E.add(new int[] { i, j });
			}
		}

		start = list_B.get(1);
		end = list_E.get(1);
		end_d = (map[end[0] - 1][end[1]] == 'E') ? 1 : 2;

		cnt = 0;
		visited = new boolean[2][N][N];

		int startDir = (map[start[0] - 1][start[1]] == 'B') ? 1 : 2;
		bfs(start[0], start[1], startDir);
	}
}
