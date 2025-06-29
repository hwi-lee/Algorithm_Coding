import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, min = Integer.MAX_VALUE;
    static int[][] map, copymap;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    //큐에 들어갈 때 오름차순으로 정렬-> 같은 값이 있으면? 상관 없을 듯
    public static void bfs() {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> {
            return Integer.compare(copymap[a[0]][a[1]], copymap[b[0]][b[1]]);
        });
        visited[0][0] = true;
        q.add(new int[]{0, 0});
        while (!q.isEmpty()) {
            int size = q.size();

            for (int s = 0; s < size; s++) {
                int cur[] = q.poll();
                int r = cur[0];
                int c = cur[1];

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                        continue;
                    if (!visited[nr][nc]) {
                        visited[nr][nc] = true;
                        copymap[nr][nc] = copymap[r][c] + copymap[nr][nc];
                        q.add(new int[]{nr, nc});
                    }
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int cnt = 0;
        while (true) {
            N = Integer.parseInt(br.readLine());
            cnt++;
            if (N == 0)
                break;
            map = new int[N][N];
            copymap = new int[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    copymap[i][j] = map[i][j];
                }
            }
            bfs();
            System.out.println("Problem " + cnt + ": " + copymap[N - 1][N - 1]);

        }

    }
}
