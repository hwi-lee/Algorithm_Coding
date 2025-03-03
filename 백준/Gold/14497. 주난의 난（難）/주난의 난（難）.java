import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, cnt;
	static char map[][];
	static int startpoint[];
	static int endpoint[];
	static boolean visited[][];
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static int bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { i, j });
		visited[i][j] = true;
		while (!q.isEmpty()) {
			int p[] = q.poll();
			int r = p[0];
			int c = p[1];
			for (int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;
				if (map[nr][nc] == '#') {
					return 1;
				}
				if (map[nr][nc] == '1' && !visited[nr][nc]) {
					map[nr][nc] = '0';
					visited[nr][nc] = true;
				}
				if (map[nr][nc] == '0' && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.offer(new int[] { nr, nc });
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];

		startpoint = new int[2];
		endpoint = new int[2];
		st = new StringTokenizer(br.readLine());
		startpoint[0] = Integer.parseInt(st.nextToken());
		startpoint[1] = Integer.parseInt(st.nextToken());
		endpoint[0] = Integer.parseInt(st.nextToken());
		endpoint[1] = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		while (true) {
			visited = new boolean[N][M];
			int n = bfs(startpoint[0] - 1, startpoint[1] - 1);
			cnt++;
			if (n == 1)
				break;
		}
		System.out.println(cnt);

	}

}
