import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main{

	static int N, cnt, sum, min = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][] visited;
	static int dr[] = { -1, 0, 1, 0 };
	static int dc[] = { 0, 1, 0, -1 };

	public static void function() {
		sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j]) {
					sum += map[i][j];
				}
			}
		}
	}

	public static void visite(int r, int c) {
		visited[r][c] = true;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			visited[nr][nc] = true;
		}
	}

	public static void backtrack(int r, int c) {
		visited[r][c] = false;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			visited[nr][nc] = false;
		}
	}

	public static boolean check(int r, int c) {
		if (visited[r][c])
			return false;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (visited[nr][nc])
				return false;
		}
		return true;
	}

	public static void dfs(int r, int c, int cnt) {

		if (cnt == 3) {
			function();
			min = Math.min(sum, min);
			return;
		}
		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < N - 1; j++) {
				if (check(i, j) && !visited[i][j]) {
					visite(i, j);
					dfs(i, j, cnt + 1);
					backtrack(i, j);

				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N][N];
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(1, 1, 0);
		System.out.println(min);

	}

}
