import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, T;
    static int[] t, indegree;
    static ArrayList<Integer> graph[];
    static StringBuilder sb = new StringBuilder();

    // 작년 순위 바탕으로 그래프, 진입차수 배열 생성 -> 여러개 노드 연결해야함
    // 순위 불확실한 경우 ? 출력 -> 순위가 여려개 있는 경우(큐크기가 1보다 큼)
    // 데이터 일관성 없는 경우 impossible 출력 -> 모든 조건(순위 바뀐 정보) 만족 못할 때
    static int topology_sort() {
        Queue<Integer> q = new ArrayDeque<>();
        int cnt=0;
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0)
                q.offer(i + 1);
        }
        while (!q.isEmpty()) {
            int p = q.poll();
            sb.append(p).append(" ");
            cnt++;
            for (int i = 0; i < graph[p - 1].size(); i++) {
                int node = graph[p - 1].get(i);
                indegree[node - 1]--;
                if (indegree[node - 1] == 0) {
                    q.offer(node);
                    if (q.size() > 1) {
                        return 0; //정답이 여러개 있는 경우
                    }
                }
            }
        }
        if(cnt<n)
            return -1;
        return 1; //정답이 하나인 경우
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {

            n = Integer.parseInt(br.readLine());
            indegree = new int[n];
            graph = new ArrayList[n];
            t = new int[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                int num = Integer.parseInt(st.nextToken());
                t[i] = num;
            }

            for (int i = 0; i < n; i++) {
                int idx = t[i];
                for (int j = i + 1; j < n; j++) {
                    graph[idx - 1].add(t[j]);
                    indegree[t[j] - 1]++;
                }
            }
            m = Integer.parseInt(br.readLine());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (graph[b - 1].contains(a)) { //노드b가 노드a포함, b->a인 경우 => a->b로 바꿈

                    graph[b - 1].remove(Integer.valueOf(a));
                    graph[a - 1].add(b);

                    indegree[a - 1]--;
                    indegree[b - 1]++;
                } else if (graph[a - 1].contains(b)) {
                    graph[a - 1].remove(Integer.valueOf(b));
                    graph[b - 1].add(a);

                    indegree[b - 1]--;
                    indegree[a - 1]++;
                }
            }

            int ans = topology_sort();
            if (ans == 0) {
                System.out.println("?");
            } else if (ans == 1) {
                System.out.println(sb);
            } else if(ans==-1){
                System.out.println("IMPOSSIBLE");
            }
            sb.setLength(0);

        }
    }
}
