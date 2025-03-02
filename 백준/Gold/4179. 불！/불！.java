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

	static int N, M, cnt;
	static char map[][];
	static boolean visited[][];
	static ArrayList<int[]> fire;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static Queue<int[]> fq = new LinkedList<int[]>();

	public static void diffuse() {
		int size = fq.size();
		for (int i = 0; i < size; i++) {
			int p[] = fq.poll();
			int r = p[0];
			int c = p[1];
			for (int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
					continue;
				}
				if (map[nr][nc] != '#' && map[nr][nc] != 'F') {
					fq.offer(new int[] { nr, nc });
					map[nr][nc] = 'F';
				}
			}
		}
	}

	public static int bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { i, j });
		visited = new boolean[N][M];
		visited[i][j] = true;
		int z = 1;
		cnt = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int t = 0; t < size; t++) {
				int p[] = q.poll();
				int r = p[0];
				int c = p[1];
				if (map[r][c] == 'F')
					continue;
				for (int k = 0; k < 4; k++) {
					int nr = r + dr[k];
					int nc = c + dc[k];
					if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
						return cnt;
					}

					if (map[nr][nc] == '.' && !visited[nr][nc]) {
						q.offer(new int[] { nr, nc });
						visited[nr][nc] = true;
						map[nr][nc] = 'J';
					}
				}
			}

			diffuse();

			cnt++;
		}
		cnt = -1;
		return cnt;

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		fire = new ArrayList<int[]>();
		int startpoint_x = 0;
		int startpoint_y = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'J') {
					startpoint_x = i;
					startpoint_y = j;
				}
				if (map[i][j] == 'F') {
					fire.add(new int[] { i, j });
				}
			}
		}
		for (int i = 0; i < fire.size(); i++)
			fq.offer(new int[] { fire.get(i)[0], fire.get(i)[1] });
		bfs(startpoint_x, startpoint_y);

		if (cnt != -1)
			System.out.println(cnt + 1);
		else
			System.out.println("IMPOSSIBLE");

	}

}
