import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int w, h, cnt;
	static int map[][];
	static int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 }, dc = { 0, 0, -1, 1, 1, -1, -1, 1 };
	static boolean visited[][];

	public static void bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] { i, j });

		while (!q.isEmpty()) {
			int p[] = q.poll();
			int r = p[0];
			int c = p[1];
			for (int k = 0; k < 8; k++) {
				if (r + dr[k] < 0 || r + dr[k] >= h || c + dc[k] < 0 || c + dc[k] >= w)
					continue;
				if (map[r + dr[k]][c + dc[k]] == 1 && !visited[r + dr[k]][c + dc[k]]) {
					q.add(new int[] { r + dr[k], c + dc[k] });
					visited[r + dr[k]][c + dc[k]] = true;

				}
			}
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if (w == 0 && h == 0)
				break;
			cnt = 0;
			map = new int[h][w];
			visited = new boolean[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (!visited[i][j] && map[i][j] == 1) {
						bfs(i, j);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}
	}

}
