import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K, cnt;
    static boolean[][] map;
    static boolean[][][] visited;
    static int[] dr={-1, 0, 1, 0};
    static int[] dc={0, 1, 0, -1};

    //처음에는 낮-> 밤
    //같은 자리 그대로 있으면 낮/밤 변경 -> 벽은 낮에만 부술 수 있으니까 현재 위치에서 밤에서 낮으로 변경
    //다음 위치가 벽이면 현재 상태가 낮이면 벽을 부순다, 현재 상태가 밤이면 그대로 있는다(큐에 꺼내고, 상태만 변경해서 삽입)
    //벽은 낮에만 부술 수 있음

    static class Node{
        int r;
        int c;
        int k;
        int t;//상태

        Node(int r, int c, int k, int t){
            this.r=r;
            this.c=c;
            this.k=k;
            this.t=t;
        }


    }
    static int bfs(){
        Queue<Node> q=new ArrayDeque<>();
        // 0는 낮, 1는 밤
        q.offer(new Node(0, 0,  0, 0));
        visited[0][0][0]=true;

        while(!q.isEmpty()){
            int size=q.size();
            cnt++;

            for(int s=0;s<size;s++){
                Node p=q.poll();
                int r=p.r;
                int c=p.c;
                int k=p.k;
                int t=p.t; //현재상태

                //다음칸 이동 -> 벽이 있음->
                //현재상태가 밤이면 가만히 있기
                //현재상태가 낮이면 벽 부순다

                if(r==N-1&&c==M-1){
                    return cnt;
                }

                for(int d=0;d<4;d++){
                    int nr=r+dr[d];
                    int nc=c+dc[d];
                    if(nr<0||nr>=N||nc<0||nc>=M)
                        continue;

                    if(map[nr][nc]){ //벽
                       if(t==1){//현재상태가 밤이고, 다음 위치에 벽이 있음
                           q.offer(new Node(r, c, k, 0)); //현재 위치를 낮으로 변경하고 다시 큐에 삽입
                       }
                       else{ //현재 상태가 낮 -> 벽 부술 수 있음
                           if(k<K&&!visited[nr][nc][k+1]){
                               visited[nr][nc][k+1]=true;
                               q.offer(new Node(nr, nc, k+1, 1));
                           }
                       }
                    }else{ //벽 없음
                        if(!visited[nr][nc][k]){
                            int time=(t==1)?0:1;
                            visited[nr][nc][k]=true;
                            q.offer(new Node(nr, nc, k, time));
                        }

                    }

                }
            }
        }

        return -1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map=new boolean[N][M];
        visited=new boolean[N][M][K+1];

        for(int i=0;i<N;i++){
            String s=br.readLine();
            for(int j=0;j<M;j++){
                map[i][j]=s.charAt(j)=='1';
            }
        }


        int ans=bfs();
        System.out.println(ans);
    }


}
