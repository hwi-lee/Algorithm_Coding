import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int K, W, H, ans;
    static int[] dr = {-1, 0, 1, 0, -2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dc = {0, 1, 0, -1, -1, 1, -2, 2, -2, 2, -1, 1};
    static boolean[][][] visited;
    static int[][] map;

    //한번의 이동에 상하좌우, 말 이동 -> 5가지 경우
    //하나의 지점에 서로 다른 방식으로 올 수 있음 -> 말이동 포함 or 포함x
    //하나의 지점에 말이동 몇번 포함 했는지에 따라 다른 경로 저장 필요 -> 예를 들면 k=1이면 포함한 경우/포함 안한 경우 따로 저정


    static int bfs() {
        Queue<int[]> q = new ArrayDeque<int[]>();
        q.offer(new int[]{0, 0, 0});
        visited[0][0][0] = true; //시작점, 말이동 포함x
        while (!q.isEmpty()) {
            int size = q.size();

            for (int s = 0; s < size; s++) {
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];
                int kcnt = cur[2];
             //   System.out.println("size: " + size + ", r: " + r + ", c: " + c + ", kcnt: " + kcnt + ", ans: " + ans);
                if (r == H - 1 && c == W - 1) {
                    return kcnt;
                }

                for (int d = 0; d < 12; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nr >= H || nc < 0 || nc >= W)
                        continue;
                    if (!visited[nr][nc][kcnt] && map[nr][nc] == 0) { //맵이 0이고 방문안한 위치
                      // System.out.println("nr: "+nr+", nc: "+nc+", kcnt: "+kcnt+", d: "+d);
                        if (d >= 4) { //말이동 -> 가능한지 체크
                            if (kcnt < K&&!visited[nr][nc][kcnt+1]) { // 말이동 가능 -> 메모리 초과 원인
                                q.offer(new int[]{nr, nc, kcnt+1});
                               //System.out.println("nr: "+nr+", nc: "+nc+", kcnt: "+kcnt+", d: "+d);
                               visited[nr][nc][kcnt+1] = true;
                            }
                        } else { // 상하좌우 이동한 경우
                           // System.out.println("nr: "+nr+", nc: "+nc+", kcnt: "+kcnt+", d: "+d);
                            q.offer(new int[]{nr, nc, kcnt});
                            visited[nr][nc][kcnt] = true;
                        }
                    }



                }
            }
            ans++;
        }
        return -1;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        visited = new boolean[H][W][K+1];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        int a = bfs();

        if (a != -1)
            System.out.println(ans);
        else
            System.out.println(-1);

    }
}
