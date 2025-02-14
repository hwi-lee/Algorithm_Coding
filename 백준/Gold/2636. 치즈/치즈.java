import java.util.*;
import java.io.*;

public class Main {

    static int N, M, hour, cnt_cheese, cnt_hole;
    static int[][] map;
    static int[] dr = { -1, 1, 0, 0, }, dc = { 0, 0, -1, 1 };
    static ArrayList<Integer> nums;

    //map에서 값이 1인 칸들에게 사용
    public static void function(int r, int c) { // 삭제할 치즈 3로 바꾸기
        for (int i = 0; i < 4; i++) {
            if (r + dr[i] < 0 || r + dr[i] >= N || c + dc[i] < 0 || c + dc[i] >= M)
                continue;
            if (map[r + dr[i]][c + dc[i]] == 2) {
                map[r][c] = 3;
                cnt_cheese--;
                cnt_hole++;
                break;
            }
        }
    }

    //외부공기 체크
    //치즈 외부공기일때 값을 2로 변경
    public static void bfs(int i, int j) {// 외부공기->2
        Queue<int[]> q = new LinkedList<int[]>();
        q.add(new int[] { i, j });
        boolean visited[][] = new boolean[N][M];
        visited[i][j] = true;
        map[i][j] = 2;

        while (!q.isEmpty()) {
            int p[] = q.poll();
            int r = p[0];
            int c = p[1];
            for (int k = 0; k < 4; k++) {
                if (r + dr[k] < 0 || r + dr[k] >= N || c + dc[k] < 0 || c + dc[k] >= M)
                    continue;
                if (!visited[r + dr[k]][c + dc[k]] && map[r + dr[k]][c + dc[k]] == 0) {
                    visited[r + dr[k]][c + dc[k]] = true;
                    q.add(new int[] { r + dr[k], c + dc[k] });
                    map[r + dr[k]][c + dc[k]] = 2;
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
        map = new int[N][M];
        nums = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0)
                    cnt_hole++;
                if (map[i][j] == 1)
                    cnt_cheese++;
            }
        }

        while (N * M != cnt_hole) {

            nums.add(cnt_cheese);

            bfs(0, 0);

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1) {
                        function(i, j);
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 3 || map[i][j] == 2) {
                        map[i][j] = 0;
                    }
                }
            }

            hour++;
        }

        System.out.println(hour);

        if (nums.isEmpty()) {
            System.out.println(0);
        } else {
            for (int i = nums.size() - 1; i >= 0; i--) {
                if (nums.get(i) != 0) {
                    System.out.println(nums.get(i));
                    break;
                }
            }
        }
    }
}