import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main {
    static int T, K, M, P;
    static int[] indegree;
    static ArrayList<Integer> graph[];
    static int[] maxOrder; // 지금까지 들어온 강 중 가장 큰 Strahler 값
    static int[] count;// 그 maxOrder가 몇 개 들어왔는지
    static int[] Strahler;  //최종순서
    static ArrayList<Integer> list=new ArrayList<>();

    static void topology_sort() {
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < M; i++) {
            if (indegree[i] == 0) {
                q.offer(i + 1);
                Strahler[i] = 1;
            }
        }
        while (!q.isEmpty()) {
            int p = q.poll();
            list.add(p);
            int s= Strahler[p-1]; //최종순서
            for (int i = 0; i < graph[p - 1].size(); i++) {
                int nextnode = graph[p - 1].get(i);

                if(s>maxOrder[nextnode-1]){
                    count[nextnode-1]=1; // 최대값 변경돼서 다시 리셋
                    maxOrder[nextnode-1]=s;
                }else if(maxOrder[nextnode-1]==s){
                    count[nextnode-1]++;

                }


                indegree[nextnode - 1]--;
                if (indegree[nextnode - 1] == 0) {
                    if(count[nextnode-1]>1){
                        Strahler[nextnode-1]=maxOrder[nextnode-1]+1;
                    }
                    else{
                        Strahler[nextnode-1]=maxOrder[nextnode-1];
                    }
                    q.offer(nextnode);
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());


            graph = new ArrayList[M];
            indegree = new int[M];
            maxOrder = new int[M];
            count = new int[M];
            Strahler=new int[M];

            for (int i = 0; i < M; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int i = 0; i < P; i++) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                graph[A - 1].add(B);
                indegree[B - 1]++;
            }

            topology_sort();

            int idx=list.get(list.size()-1);

            System.out.println(test_case+" "+ Strahler[idx-1]);

        }
    }

}
