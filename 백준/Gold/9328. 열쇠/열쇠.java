import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T, h, w, ans;
    static char[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static HashSet<Character> keys;//a-z
    static ArrayList<int[]> doors[];//문들의 위치
    static boolean[][] visited;
    //A=65, a=97

    static void bfs(int i, int j) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{i, j});
        if (map[i][j] == '$')
            ans++;
        if ('A' <= map[i][j] && map[i][j] <= 'Z')
            if (!keys.contains((char) (map[i][j] + 32))){
                int idx = map[i][j] - 65;
                doors[idx].add(new int[]{i, j});
                return;
            }
        if ('a' <= map[i][j] && map[i][j] <= 'z')
            keys.add(map[i][j]);
        visited[i][j] = true;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int r = p[0];
            int c = p[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= h || nc < 0 || nc >= w)
                    continue;

                if (map[nr][nc] == '*')
                    continue;

                if (!visited[nr][nc]) {
                    if (map[nr][nc] == '$') { //문서 이전에 방문한적이 없으면 카운트+1
                        ans++;
                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc});
                    } else if (map[nr][nc] == '.') {
                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc});
                    } else if ('A' <= map[nr][nc] && map[nr][nc] <= 'Z') { //문
                        //해당 키 존재여부 확인
                        char key = (char) (map[nr][nc] + 32);
                        if (keys.contains(key)) { //키 존재
                            visited[nr][nc] = true;
                            q.offer(new int[]{nr, nc});
                        } else { //키 없음
                            int idx = map[nr][nc] - 65;
                            doors[idx].add(new int[]{nr, nc});
                        }
                    } else if ('a' <= map[nr][nc] && map[nr][nc] <= 'z') { //키
                        keys.add(map[nr][nc]);
                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc});
                        //여기서 다른 위치에서 열 수 있는 문 있는지 확인 필요 -> 그 위치 큐에 삽입
                        int idx = map[nr][nc] - 97;
                        for (int z = 0; z < doors[idx].size(); z++) {
                            int door_r = doors[idx].get(z)[0];
                            int door_c = doors[idx].get(z)[1];
                            if (!visited[door_r][door_c]) { //이전에 키가 없어서 문 연적 없음 -> 방문처리
                                visited[door_r][door_c] = true;
                                q.offer(new int[]{door_r, door_c});
                            }

                        }


                    }
                }

            }

        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            keys = new HashSet<>();
            doors = new ArrayList[26];
            for (int i = 0; i < 26; i++) {
                doors[i] = new ArrayList<>();
            }
            visited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                String s = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = s.charAt(j);
//                    if ('A' <= map[i][j] && map[i][j] <= 'Z') {
//                        int idx = map[i][j] - 65;
//                        doors[idx].add(new int[]{i, j});
//                    }
                }
            }

            String key = br.readLine();
            for (int i = 0; i < key.length(); i++) {
                keys.add(key.charAt(i));
            }


            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (i == 0 || i == h - 1) { //첫번째 행과 마지막 행
                        if (!visited[i][j] && map[i][j] != '*') {
                            bfs(i, j);
                        }
                    } else {
                        if (j == 0 || j == w - 1) { //첫번째 열과 마지막 열
                            if (!visited[i][j] && map[i][j] != '*') {
                                bfs(i, j);
                            }
                        }
                    }
                }
            }

            System.out.println(ans);
            ans = 0;

        }

    }
}
