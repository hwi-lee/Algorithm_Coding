import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		String s=scanner.next();
		int n=s.length();
		int i=0;
		int a,b;
		
		if(n%2==0) {
			a=n/2-1;
			b=n/2;
			}
		else {
			a=n/2-1;
			b=n/2+1;
		}
	
		while(n/2>i) {
			
			if(s.charAt(a)==s.charAt(b)) {
				i++;
				a=a-1;
				b=b+1;
			}
			else {
				System.out.print("0");
				break;
			}
		}
		if(i==n/2)
			System.out.print("1");
			
	}
	
}
