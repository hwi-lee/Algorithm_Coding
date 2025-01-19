import java.util.*;
import java.awt.*;
import java.io.*;
class Stack<T>{
	int top;
	int size;
	Object []s;
	
	public Stack(int size) {
		top=-1;
		this.size=size;
		s=new Object[size];
	}
	public void push(int n) {
		s[++top]=n;
	}
	int isEmpty() {
		if(top==-1)
			return 1;
		else
			return 0;
		
	}
	Object pop() {
		Object p=s[top];
		s[top]=0;
		top--;
		return p;
	}
	Object peek() {
		return s[top];
	}
}
public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int K=Integer.parseInt(br.readLine());
		Stack<Integer>s=new Stack<Integer>(K);
		int n;
		int sum=0;
		for(int i=0;i<K;i++) {
			n=Integer.parseInt(br.readLine());
			//n=scanner.nextInt();
			if(n==0) {
				s.pop();
			}
			else
				s.push(n);
		}
		
		while(s.top>-1) {
			sum+=(int)s.pop();
		}
		bw.write(String.valueOf(sum));
		bw.flush();
		bw.close();
	}

}
