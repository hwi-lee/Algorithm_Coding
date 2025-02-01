import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, cnt;
	static int[][] graph;
	public static int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };
	public static int[] dx = { -1, -1, 1, 1 }, dy = { -1, 1, -1, 1 };// 대각선

	public static boolean check(int r, int c) {
		for (int i = 0; i < 4; i++) {
			int z = 1;
			while (true) {
				if (r + dr[i] * z < 0 || c + dc[i] * z >= N || r + dr[i] * z >= N || c + dc[i] * z < 0)
					break;
				if (graph[r + dr[i] * z][c + dc[i] * z] == 1) {
					return false;
				}
				z++;
			}
		}

		for (int i = 0; i < 4; i++) {
			int z = 1;
			while (true) {
				if (r + dx[i] * z < 0 || c + dy[i] * z >= N || r + dx[i] * z >= N || c + dy[i] * z < 0)
					break;
				if (graph[r + dx[i] * z][c + dy[i] * z] == 1) {
					return false;
				}
				z++;
			}
		}

		return true;

	}

	public static void dfs(int x) {
		if (x >= N) {
			cnt++;
			return;
		}
		for (int j = 0; j < N; j++) {
			if (check(x, j) == true) {
				graph[x][j] = 1;
				dfs(x + 1);
				graph[x][j] = 0;
			}
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		dfs(0);
		bw.write(String.valueOf(cnt));
		bw.flush();
		bw.close();
	}

}
