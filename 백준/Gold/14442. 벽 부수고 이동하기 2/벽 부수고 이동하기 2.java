import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K, cnt;
    static boolean[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[][][] visited;

    static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0, 0});

        while (!q.isEmpty()) {
            int size = q.size();
            cnt++;
            for (int s = 0; s < size; s++) {
                int[] p = q.poll();
                int r = p[0];
                int c = p[1];
                int k = p[2];

                if (r == N - 1 && c == M - 1) {
                    return cnt;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                        continue;

                    if (!visited[nr][nc][k]) {
                        if (map[nr][nc]) { //벽 부술 수 있음
                            if (k < K) { //아직 횟수 남음 -> 벽 부수고 가는 경우
                                visited[nr][nc][k] = true;
                                q.offer(new int[]{nr, nc, k + 1});
                            }
                        } else { //벽 없는 위치
                            q.offer(new int[]{nr, nc, k});
                            visited[nr][nc][k] = true;
                        }
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
        K = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        visited = new boolean[N][M][K + 1];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) == '1';
            }
        }


        int ans=bfs();
        System.out.println(ans);

    }

}
