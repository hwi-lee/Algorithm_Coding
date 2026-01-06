import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int L, R, C;
    static int cnt;
    static char[][][] map;
    static int dr[] = {-1, 0, 1, 0, 0, 0};
    static int dc[] = {0, 1, 0, -1, 0, 0};
    static int dl[] = {0, 0, 0, 0, -1, 1};
    static boolean visited[][][];
    static int[] S;

    static int bfs(int z, int x, int y) {

        cnt = 0;
        visited[z][x][y] = true;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{z, x, y});
        while (!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; s++) {

                int p[] = q.poll();
                int l = p[0];
                int r = p[1];
                int c = p[2];

                for (int i = 0; i < 6; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    int nl = l + dl[i];

                    if (nr < 0 || nr >= R || nc < 0 || nc >= C || nl < 0 || nl >= L)
                        continue;
                    if (map[nl][nr][nc] == 'E') {
                        cnt++;
                        return cnt;
                    }
                    if (map[nl][nr][nc] == '#' || map[nl][nr][nc] == 'S')
                        continue;

                    if (!visited[nl][nr][nc] && map[nl][nr][nc] == '.') {
                        q.add(new int[]{nl, nr, nc});
                        visited[nl][nr][nc] = true;
                    }
                }
            }
            cnt++;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L == 0 && R == 0 && C == 0)
                break;
            map = new char[L][R][C];
            visited = new boolean[L][R][C];
            S = new int[3];

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String s = br.readLine();
                    for (int k = 0; k < s.length(); k++) {
                        map[i][j][k] = s.charAt(k);
                        if (map[i][j][k] == 'S') {
                            S[0] = i;
                            S[1] = j;
                            S[2] = k;
                        }
                    }
                }
                st = new StringTokenizer(br.readLine());
            }

            int ans = bfs(S[0], S[1], S[2]);

            if (ans != -1)
                System.out.println("Escaped in " + ans + " minute(s).");
            else
                System.out.println("Trapped!");

        }

    }
}
