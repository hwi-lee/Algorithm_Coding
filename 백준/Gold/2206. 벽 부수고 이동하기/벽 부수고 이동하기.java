

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static int N, M, min = Integer.MAX_VALUE, cnt;
	static ArrayList<int[]> a;
	static boolean[][] map; // 01 01 지도의 정보
	static boolean[][][] visited;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int bfs(int i, int j) {

		Queue<int[]> q = new ArrayDeque<int[]>();
		visited = new boolean[N][M][2]; // 한 번만 벽을 부술 수 있으니까 0,1 k번 부술 수 있다고 하면 배열의 세번째를 k로 초기화하는거지
		// visited=new boolean[N][M][K+1];
		visited[i][j][0] = true;
		q.offer(new int[] { i, j, 0 });

		cnt = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			cnt++;
			for (int t = 0; t < size; t++) {

				int p[] = q.poll();
				int r = p[0];
				int c = p[1];
				int b = p[2]; // 벽을 부순 횟수
				if (r == N - 1 && c == M - 1)
					return cnt;
				for (int k = 0; k < 4; k++) {
					int nr = r + dr[k];
					int nc = c + dc[k];
					if (nr < 0 || nr >= N || nc < 0 || nc >= M)// 맵을 벗어나면 다음 걸로 넘어감
						continue;
					if (map[nr][nc] == false && !visited[nr][nc][b]) // 이동 가능하고 방문하지 않았다면 큐에 삽입
					{
						q.offer(new int[] { nr, nc, b });
						visited[nr][nc][b] = true;
					}
					/**
					 * 다음 이동할 좌표가 벽이 있다. 현재 4번 부셨어 벽을 그러면 다음 벽을 부수려면 당연히 5번째 부순 횟수가 된다. 그렇다면 다음 이동할
					 * 좌표가 혹시 5번 이미 깨서 온 적이 있는 지를 검사해야함
					 * 
					 * 1번 케이스 : 아직 벽을 안 부수고 벽에 도달함 b = 0 인데 현재 부수면 당연히 b가 1이 되겠지? 그 런 데 이미 이전에 벽을
					 * 부셔서 내가 지금 가고자 하는 좌표에 온 경로가 있다면 visited[nr][nc][b+1]이 true일 거임 나는 최소가 아니라는 뜻
					 * 그래서 continue해야함 아 나는 이전이 항상 최단 거리는 것을 알아. -> 아 이미 누가 왔으니까 지금 내가 온 순간은 최소가
					 * 아니겠꾸나 : 이미 누가 최소로 왔어 그니까 나는 최소 일 수 없어 2번 케이스
					 */
					if (map[nr][nc] == true && b == 0 && !visited[nr][nc][b + 1]) {// 벽이 있는 곳이고 방문하지 않았다면
						q.offer(new int[] { nr, nc, b + 1 });
						visited[nr][nc][b] = true;
					}

				}

			}
		}
		return -1;

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new boolean[N][M];

		a = new ArrayList<int[]>();

		a.add(new int[] { 0, 0 });
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < s.length(); j++) {
				if (s.charAt(j) == '1') {
					map[i][j] = true;
				} else
					map[i][j] = false;
			}
		}

		System.out.println(bfs(0, 0));

	}

}
