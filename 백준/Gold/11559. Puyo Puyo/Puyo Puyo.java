import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//같은 색 4개 이상 연결(상하좌우)
//동시 터짐 가능, 1개로 인정
//동시에..->한번 검색할 때 완탐
public class Main {
    static char[][] graph;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[][] visited;
    static boolean[][] copyvisited;
    static int cnt, check;

    public static void bfs(int i, int j) {
        Queue<int[]> q = new LinkedList<int[]>();
        q.add(new int[]{i, j});
        copyvisited[i][j] = true;
        int count = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                int cur[] = q.poll();
                int r = cur[0];
                int c = cur[1];
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr < 0 || nr >= 12 || nc < 0 || nc >= 6)
                        continue;
                    if (!copyvisited[nr][nc] && graph[nr][nc] == graph[r][c]) {
                        count++;
                        copyvisited[nr][nc] = true;
                        q.add(new int[]{nr, nc});
                    }
                }
            }
        }

        if(count>=4){
            for(int x=0;x<12;x++){
                for(int y=0;y<6;y++){
                    visited[x][y]=copyvisited[x][y];
                }
            }
        }else{
            for(int x=0;x<12;x++){
                for(int y=0;y<6;y++){
                    copyvisited[x][y]=visited[x][y];
                }
            }
        }

//        if(count>=4){//여기서 하면 안될듯-> 동시 터질 때 다른 그룹 영향 줄 수 있음-> 완탐하고 실행해야 함.
//            function(i, j);
//        }
    }
//위에꺼가 false 현재 위치는 true면 현재 위치 false로 변경
    public static void function(int i, int j) {
        if(i-1<0){// i가 첫번 쩨 줄일 때
            graph[i][j]='.';
        }else{
            while(i-1>=0){
                graph[i][j]=graph[i-1][j];
                visited[i][j]=visited[i-1][j];
                i--;
            }
            graph[i][j]='.';

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());

        graph = new char[12][6];

        for (int i = 0; i < 12; i++) {
            String string = br.readLine();
            for (int j = 0; j < string.length(); j++) {
                graph[i][j] = string.charAt(j);
            }
        }

        while(true) {
            check=0;
            visited = new boolean[12][6];
            copyvisited = new boolean[12][6];
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (graph[i][j] != '.' && !visited[i][j]) {
                        bfs(i, j);//터질 뿌요 true로 만들기, 여러 그룹 포함.
                    }
                }
            }

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (graph[i][j] != '.' && visited[i][j]) {
                        function(i, j);//중력 적용
                        check++;
                    }
                }
            }
            if(check==0)
                break;
            cnt++;
        }
        System.out.println(cnt);
    }
}
