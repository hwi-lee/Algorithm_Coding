import java.util.*;

public class Main {

	static int N;
	static int[][] board;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		board = new int[N][N];
		StringBuilder sb = new StringBuilder();

		star(0, 0, N);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(board[i][j]==1) {
					sb.append("*");
				}else
					sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());

	}

	public static void star(int r, int c, int n) {

		if (n == 3) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if(i==1&&j==1)
						continue;
					board[r+i][c+j]=1;
					
				}
			}
		} else {

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if(i==1&&j==1)
						continue;
					star(r+i*n/3,c+j*n/3, n/3);
					
				}
			}
		}
	}

}
