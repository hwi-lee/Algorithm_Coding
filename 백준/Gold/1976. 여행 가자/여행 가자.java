import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, ans;
    static int[][] map;
    static int city[];
    public static void FloydWarshall(){
        for(int k=0;k<N;k++){
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(map[i][k]==1&&map[k][j]==1){
                        map[i][j]=1;
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());
        M=Integer.parseInt(br.readLine());
        city=new int[M];
        map=new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++){
            city[i]=Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j]==1)
                    map[j][i]=1;
                if(i==j)
                    map[i][j]=1;
            }
        }

        FloydWarshall();

        for(int i=0;i<city.length-1;i++){
            if(map[city[i]-1][city[i+1]-1]==1)
                ans++;
        }
        if(ans==M-1)
            System.out.println("YES");
        else
            System.out.println("NO");

    }
}
