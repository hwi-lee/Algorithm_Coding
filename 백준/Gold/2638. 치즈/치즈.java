import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main {
    static int N, M, ans;
    static int[][] map;
    static boolean visited[][];
    static int[] dr={-1, 0, 1, 0};
    static int[] dc={0, 1, 0, -1};
    //사라질 치즈 3으로 변경
    //안에 있는 공기랑 바깥공기 구분
    //치즈의 주변 바깥공기 2개 이상이면 ->3
    //visited=true -> 바깥공기
    public static void bfs(int i, int j){
        Queue<int[]> q=new ArrayDeque<>();
        visited=new boolean[N][M];
        visited[i][j]=true;
        //map[i][j]=2;
        q.add(new int[]{i,j});
        while (!q.isEmpty()) {
            int size=q.size();
            for(int s=0;s<size;s++){
                int p[]=q.poll();
                int r=p[0];
                int c=p[1];
                for(int d=0;d<4;d++){
                    int nr=r+dr[d];
                    int nc=c+dc[d];
                    if(nr<0||nr>=N||nc<0||nc>=M)
                        continue;
                    if(map[nr][nc]==0&&!visited[nr][nc]){
                        visited[nr][nc]=true;
                        q.add(new int[]{nr, nc});
                       // map[nr][nc]=2;
                    }
                }
            }

        }
    }
    public static void check(int r, int c){//사라질 치즈 체크->3
        int cnt=0;
        for(int d=0;d<4;d++){
            int nr=r+dr[d];
            int nc=c+dc[d];
            if(nr<0||nr>=N||nc<0||nc>=M)
                continue;
            if(visited[nr][nc]==true)
                cnt++;

        }
        if(cnt>=2)
            map[r][c]=3;

    }
    public static boolean function(){//치즈 남아 있는지
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j]==1)
                    return false;//치즈 남아있음
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int count=0;
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        map=new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
                if(map[i][j]==1)
                    count++;
            }
        }

        while(true){

            if(count==0)
                break;

            bfs(0, 0);

            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(map[i][j]==1)
                        check(i, j);
                }
            }

            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(map[i][j]==3) {
                        map[i][j] = 0;
                        count--;
                    }
                }
            }

          ans++;

        }
        System.out.println(ans);

    }

}