import java.util.Scanner;
 
public class Weird_Algorithm
{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    
	    long n = sc.nextInt();
	    System.out.print(n+" ");
	    
	    while(n != 1){
	        
	        if(n%2 == 1)
	           n = n*3 + 1;
	           
	        else
	           n >>= 1;
	           
	     System.out.print(n+" ");
	    }
	}
}
