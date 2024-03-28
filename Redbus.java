package nirmal_workspace;
import java.util.Scanner;
public class Redbus {
	public static void main(String args[]) {
	Scanner s = new Scanner(System.in);
	int a[]=new int[25];
	for(int i=0;i<a.length;i++) {
		a[i]=i+1;
	}
	while(true) {
		System.out.println("1.To book bus ticket");
		System.out.println("2.TO exit");
		System.out.println("Enter the input");
		int b=s.nextInt();
		String flag = "not booked";
		if (b == 1) {
			System.out.println("Enter the seat number:");
			int c = s.nextInt();
			for(int i=0;i<a.length;i++) {
				if(a[i]==c) {
					flag = "booked";
					a[i]=0;
					break;			
				}
				else {
					flag ="not booked";
				}
			}
			if(flag.equals("booked")) 
			{
				System.out.println("Booked");
			}
			else {
				System.out.println("Please try again with different seat no");
			}		
	}
		else if(b==2) {
			System.out.println("Exit");
			break;
		}
		else 
		{
			System.out.println("Press any 1 or 2");
		}
	}
 }
}
