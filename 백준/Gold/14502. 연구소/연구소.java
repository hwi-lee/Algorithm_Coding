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
	static int N, M, R = 3, cnt_2, count, min = 64, cnt_1;
	static int[][] map;
	static ArrayList<int[]> a, blank, nums;
	static boolean visited_1[][], visited_2[][];
	static int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };

	static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<int[]>();

		q.offer(new int[] { r, c });
		while (!q.isEmpty()) {
			int[] p = (int[]) q.poll();
			int n = p[0];
			int m = p[1];
			cnt_2++;
			for (int i = 0; i < 4; i++) {
				if (n + dr[i] < 0 || n + dr[i] >= N || m + dc[i] < 0 || m + dc[i] >= M)
					continue;
				if (map[n + dr[i]][m + dc[i]] == 0 && !visited_2[n + dr[i]][m + dc[i]]) {
					q.offer(new int[] { n + dr[i], m + dc[i] });
					visited_2[n + dr[i]][m + dc[i]] = true;

				}
			}
		}

	}

	public static void combi(int cnt, int start) {

		if (cnt == R) {
			count++;
			cnt_2 = 0;
			visited_2 = new boolean[N][M];

			for (int j = 0; j < a.size(); j++) {
				bfs(a.get(j)[0], a.get(j)[1]);

			}
			min = Math.min(min, cnt_2);

			return;

		}
		for (int i = start; i < blank.size(); i++) {
			map[blank.get(i)[0]][blank.get(i)[1]] = 1;
			combi(cnt + 1, i + 1);
			map[blank.get(i)[0]][blank.get(i)[1]] = 0;

		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited_1 = new boolean[N][M];
		visited_2 = new boolean[N][M];
		a = new ArrayList<int[]>();
		blank = new ArrayList<int[]>();
		nums = new ArrayList<int[]>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					a.add(new int[] { i, j });
				}
				if (map[i][j] == 1) {
					cnt_1++;
				}
				if (map[i][j] == 0) {
					blank.add(new int[] { i, j });
				}
			}

		}

		combi(0, 0);

		System.out.println(N * M - (cnt_1 + 3) - min);

	}
}