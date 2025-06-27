import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, ans;
    static int[] start, end;
    static ArrayList<int[]> water;
    static char[][] map;
    static boolean[][] visited1;
    static boolean[][] visited2;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    //비어있는 칸  .
    //물 * -> 여러 개 있을 수 있음
    //현재 위치 S
    //돌 X
    //굴 D
    //물은 굴/돌 통과 못함
    //고슴도치는 물/돌 통과 못함(물 찰 예정인 위치도 통과 못함)
    //물은 분마다 상하좌우 퍼짐
//물이 먼저 이동(물 찰 예정인 위치 확인)

    public static int bfs() {
        int size = water.size();
        Queue<int[]> q_cur = new ArrayDeque<int[]>();
        Queue<int[]> q_water = new ArrayDeque<int[]>();
        q_cur.add(new int[]{start[0], start[1]});
        for (int s = 0; s < size; s++) {
            int p[] = water.remove(water.size() - 1);
            int x = p[0];
            int y = p[1];
            q_water.add(new int[]{x, y});
            visited2[x][y] = true;
        }

        visited1[start[0]][start[1]] = true;

        while (!q_cur.isEmpty()) {

            int size_q_water = q_water.size();
            for (int s = 0; s < size_q_water; s++) {//물 먼저 이동
                int w[] = q_water.poll();
                int r = w[0];
                int c = w[1];
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                        continue;
                    if (map[nr][nc] == 'D' || map[nr][nc] == 'X')
                        continue;
                    if (!visited2[nr][nc]) {
                        q_water.add(new int[]{nr, nc});
                        visited2[nr][nc] = true;
                    }
                }
            }

            int size_q = q_cur.size();
            for (int s = 0; s < size_q; s++) {//고슴도치 이동
                int p[] = q_cur.poll();
                int r = p[0];
                int c = p[1];
                if (r == end[0] && c == end[1]) {
                    return ans;
                }
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                        continue;
                    if (map[nr][nc] == '*' || map[nr][nc] == 'X')
                        continue;
                    if (visited2[nr][nc])//물 찬 위치
                        continue;
                    if (!visited1[nr][nc]) {
                        q_cur.add(new int[]{nr, nc});
                        visited1[nr][nc] = true;
                    }
                }
            }
            ans++;

        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited1 = new boolean[N][M];
        visited2 = new boolean[N][M];
        water = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String string = br.readLine();
            for (int j = 0; j < string.length(); j++) {
                map[i][j] = string.charAt(j);
                if (map[i][j] == 'S')
                    start = new int[]{i, j};
                if (map[i][j] == 'D')
                    end = new int[]{i, j};
                if (map[i][j] == '*')
                    water.add(new int[]{i, j});


            }
        }

        int n = bfs();
        if (n == -1)
            System.out.println("KAKTUS");
        else
            System.out.println(n);


    }
}
