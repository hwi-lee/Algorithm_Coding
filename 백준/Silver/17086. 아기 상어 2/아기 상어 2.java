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

	static int N, M, max, min = 100;
	static int[][] map, copymap;
	static boolean[][] visited;
	static ArrayList<int[]> shark;
	static int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dc = { 0, 0, -1, 1, -1, 1, -1, 1 };

	public static void bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<int[]>();
		visited = new boolean[N][M];
		copymap = new int[N][M];
		q.offer(new int[] { i, j });
		while (!q.isEmpty()) {
			int[] p = q.poll();
			int r = p[0];
			int c = p[1];
			visited[r][c] = true;
			for (int k = 0; k < 8; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;
				if (!visited[nr][nc]) {
					q.offer(new int[] { nr, nc });
					visited[nr][nc] = true;
					copymap[nr][nc] = copymap[r][c] + 1;
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		copymap = new int[N][M];
		visited = new boolean[N][M];
		shark = new ArrayList<int[]>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					shark.add(new int[] { i, j });
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					bfs(i, j);
					min = 100;
					for (int k = 0; k < shark.size(); k++) {
						min = Math.min(min, copymap[shark.get(k)[0]][shark.get(k)[1]]);
					}
					max = Math.max(max, min);
				}
			}
		}
		System.out.println(max);
	}

}
