import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class sort implements Comparable<sort> {
	int r, c;

	public sort(int r, int c) {
		this.r = r;
		this.c = c;
	}

	public int compareTo(sort o) {
		if (this.r == o.r)
			return Integer.compare(this.c, o.c);
		else
			return Integer.compare(this.r, o.r);

	}

}

public class Main {

	static int N, shark_size = 2, cnt, ans, sum, startpoint_x, startpoint_y;
	static int[][] map, copymap;
	static boolean visited[][];
	static boolean check[][];
	static ArrayList<int[]> a;
	static PriorityQueue<sort> queue = new PriorityQueue<>();
	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };

	public static void update() {

		sort s = queue.poll();
		startpoint_x = s.r;
		startpoint_y = s.c;
		cnt++;
		check[s.r][s.c] = true;
		ans += copymap[s.r][s.c];
		copymap = new int[N][N];
		if (cnt == shark_size) {
			shark_size++;
			cnt = 0;
		}

		visited = new boolean[N][N];

		queue.clear();
	}

	public static int bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { i, j });
		map[i][j] = 0;
		visited[i][j] = true;
		int nextpoint[] = new int[2];
		while (!q.isEmpty()) {
			int size = q.size();

			for (int t = 0; t < size; t++) {

				int p[] = q.poll();
				int r = p[0];
				int c = p[1];
				for (int k = 0; k < 4; k++) {
					int nr = r + dr[k];
					int nc = c + dc[k];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N)
						continue;
					if (!visited[nr][nc] && (map[nr][nc] == 0 || map[nr][nc] == shark_size)) {// 지나감
						visited[nr][nc] = true;
						q.offer(new int[] { nr, nc });
						copymap[nr][nc] = copymap[r][c] + 1;
					}

					if (!check[nr][nc] && !visited[nr][nc] && map[nr][nc] > 0 && map[nr][nc] < shark_size) {// 물고기 먹음
						queue.offer(new sort(nr, nc));
						visited[nr][nc] = true;
						copymap[nr][nc] = copymap[r][c] + 1;
					}
				}

			}

			if (!queue.isEmpty()) {

				update();
				return 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		copymap = new int[N][N];
		visited = new boolean[N][N];
		check = new boolean[N][N];
		a = new ArrayList<int[]>();
		int r = 0;
		int c = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					r = i;
					c = j;
				}
			}
		}

		copymap[r][c] = 1;

		startpoint_x = r;
		startpoint_y = c;

		while (true) {
			int n = bfs(startpoint_x, startpoint_y);
			if (n == -1)
				break;
		}

        if(ans<=0)
            System.out.println(0);
        else
		    System.out.println(ans - 1);
	}

}
