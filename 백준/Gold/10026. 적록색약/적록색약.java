import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{

	static int N, cnt1, cnt2;
	static char[][] map1, map2;
	static int dr[] = { -1, 1, 0, 0 }, dc[] = { 0, 0, -1, 1 };
	static boolean[][] visited;

	public static void bfs1(int i, int j) {
		Queue<int[]> q = new LinkedList();
		q.add(new int[] { i, j });
		while (!q.isEmpty()) {
			int p[] = q.poll();
			int r = p[0];
			int c = p[1];

			for (int k = 0; k < 4; k++) {
				if (r + dr[k] < 0 || r + dr[k] >= N || c + dc[k] < 0 || c + dc[k] >= N)
					continue;

				if ((map1[r + dr[k]][c + dc[k]] == map1[r][c]) && (!visited[r + dr[k]][c + dc[k]])) {
					q.add(new int[] { r + dr[k], c + dc[k] });
					visited[r + dr[k]][c + dc[k]] = true;

				}
			}

		}
	}

	public static void bfs2(int i, int j) {
		Queue<int[]> q = new LinkedList();
		q.add(new int[] { i, j });
		while (!q.isEmpty()) {
			int p[] = q.poll();
			int r = p[0];
			int c = p[1];

			for (int k = 0; k < 4; k++) {
				if (r + dr[k] < 0 || r + dr[k] >= N || c + dc[k] < 0 || c + dc[k] >= N)
					continue;

				if ((map2[r + dr[k]][c + dc[k]] == map2[r][c]) && (!visited[r + dr[k]][c + dc[k]])) {
					q.add(new int[] { r + dr[k], c + dc[k] });
					visited[r + dr[k]][c + dc[k]] = true;

				}
			}

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		map1 = new char[N][N];
		map2 = new char[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < s.length(); j++) {
				map1[i][j] = s.charAt(j);
				map2[i][j] = s.charAt(j);
				if (s.charAt(j) == 'G')
					map2[i][j] = 'R';
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					bfs1(i, j);
					cnt1++;
				}
			}
		}
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					bfs2(i, j);
					cnt2++;
				}
			}
		}
		System.out.println(cnt1 + " " + cnt2);

	}

}
