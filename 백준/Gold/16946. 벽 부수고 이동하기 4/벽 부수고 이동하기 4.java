import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    // visited를 2차원으로 선언하면 시간초과, 3차원이면 메모리초과
    // 0에서 bfs시작 -> 0의 개수 카운트 -> 인접한 위치에 카운트 수 누적
    static void bfs(int r, int c) {
        Queue<int[]> q = new ArrayDeque<>();
        ArrayList<int[]> list = new ArrayList<>();
        q.offer(new int[]{r, c, 1});
        visited[r][c] = true;
        int cnt = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                int[] p = q.poll();
                int cur_r = p[0];
                int cur_c = p[1];
                int cur_k = p[2];
                for (int i = 0; i < 4; i++) {
                    int nr = cur_r + dr[i];
                    int nc = cur_c + dc[i];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                        continue;
                    if (!visited[nr][nc] && map[nr][nc] == 0) {
                        visited[nr][nc] = true;
                        cur_k++;
                        cnt++;
                        q.offer(new int[]{nr, nc, cur_k});
                    }
                    if (map[nr][nc] > 0&&!visited[nr][nc]) {
                        list.add(new int[]{nr, nc});
                        visited[nr][nc]=true;
                    }
                }
            }
        }

        function(list, cnt);

    }

    static void function(ArrayList<int[]> list, int cnt) {
        for (int i = 0; i < list.size(); i++) {
            int p[] = list.get(i);
            int r = p[0];
            int c = p[1];
            map[r][c] += cnt;
            visited[r][c]=false;

        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String string = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = string.charAt(j) == '1' ? 1 : 0;
            }
        }
        visited = new boolean[N][M];
        int k = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    bfs(i, j);
                }

            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] % 10);
            }
            System.out.println();
        }


    }
}
