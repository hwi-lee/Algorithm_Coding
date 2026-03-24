import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static char[][] map, copymap;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1, 0};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1, 0};
    static boolean[][] visited;
    static ArrayList<int[]> list = new ArrayList<>();

    //시작점 map[7][0]
    //도착점 map[0][7]
    //벽은 1초에 아래 한칸 이동
    //욱제의 캐릭터 먼저 이동 -> 벽 이동
    //벽 이동 후 그 위치가 욱제의 캐릭터 있으면 0

    static void init(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                copymap[i][j]='.';
            }
        }
    }
    static void copy(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                map[i][j]=copymap[i][j];
            }
        }
    }

    static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{7, 0});
        while (!q.isEmpty()) {
            visited = new boolean[8][8];
            int size = q.size();
            for (int s = 0; s < size; s++) { //1초단위
                int[] p = q.poll();
                int r = p[0];
                int c = p[1];
                if (map[r][c] == '#') { //현재 위치가 벽 ->  이동불가
                    continue;
                }
                if (r == 0 && c == 7)
                    return 1;

                for (int d = 0; d < 9; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nr >= 8 || nc < 0 || nc >= 8)
                        continue;
                    if (!visited[nr][nc] && map[nr][nc] == '.') {
                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc});
                    }

                }
            }
            int list_size = list.size();
            for (int l = 0; l < list_size; l++) { //벽 이동, 기존 벽 위치 제거 -> 새로 이동된 위치 저장
                int[] lp = list.remove(0);
                int lr = lp[0];
                int lc = lp[1];

                if(lr+1>=8){
                    continue;
                }
                copymap[lr+1][lc]='#';
                list.add(new int[]{lr+1, lc});
            }
            copy();
            init();


        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //  StringTokenizer st = new StringTokenizer(br.readLine());
        map = new char[8][8];
        copymap = new char[8][8];
        init();

        for (int i = 0; i < 8; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == '#')
                    list.add(new int[]{i, j});
            }
        }

        int ans = bfs();
        System.out.println(ans);
    }

}
