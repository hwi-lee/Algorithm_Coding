import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {

	static int N, M, size;
	static char[][] graph;

	public static void function(int i, int j, int end_i, int end_j) {
		if (i > size / 2 && j >= size / 2) {
			return;
		}
		
		for (int x = i; x <= end_i; x++) {
			graph[i][x] = '*';
			graph[x][i] = '*';
			graph[end_i][x] = '*';
			graph[x][end_i] = '*';
		}
		function(i + 2, i + 2, end_i - 2, end_j - 2);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// StringTokenizer st = new StringTokenizer(br.readLine());
		Scanner sc = new Scanner(System.in);
		N = Integer.parseInt(br.readLine());
		size = 4 * N - 3;
		graph = new char[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				graph[i][j] = ' ';
			}
		}
		function(0, 0, size - 1, size - 1);

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(graph[i][j]);
			}
			System.out.println();
		}

	}

}
