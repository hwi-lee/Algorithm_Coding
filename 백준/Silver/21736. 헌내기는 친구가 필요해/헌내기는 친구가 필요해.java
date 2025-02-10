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
	static boolean visited[][];
	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

	public static void bfs(int r, int c) {

		Queue<int[]> q = new LinkedList();

		q.add(new int[] { r, c });
		while (!q.isEmpty()) {

			int p[] = q.poll();
			int x = p[0];
			int y = p[1];

			for (int i = 0; i < 4; i++) {
				if (x + dr[i] < 0 || x + dr[i] >= N || y + dc[i] < 0 || y + dc[i] >= M)
					continue;

				if (visited[x + dr[i]][y + dc[i]] == true)
					continue;

				if (map[x + dr[i]][y + dc[i]] == 'O') {
					q.add(new int[] { x + dr[i], y + dc[i] });
					visited[x + dr[i]][y + dc[i]] = true;
				}
				if (map[x + dr[i]][y + dc[i]] == 'P' && visited[x + dr[i]][y + dc[i]] == false) {
					q.add(new int[] { x + dr[i], y + dc[i] });
					visited[x + dr[i]][y + dc[i]] = true;
					cnt++;

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
		map = new char[N][M];
		visited = new boolean[N][M];
		int r = 0, c = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'I') {
					r = i;
					c = j;
				}
			}
		}
		bfs(r, c);
		if (cnt == 0)
			System.out.println("TT");
		else
			System.out.println(cnt);

	}

}
