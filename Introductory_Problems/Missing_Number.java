import java.util.Scanner;
 
public class Missing_Number
{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    
	    int n = sc.nextInt();
	    int[] arr = new int[n-1];
	    
	    for(int i = 0; i < n-1; ++i){
	        arr[i] = sc.nextInt();
	    }
	    
	    int xor = 0;
	    
	    for(int i = 1; i < n; ++i){
	        
	         xor ^= arr[i-1];
	         xor ^= i;
	    }
	    
	    xor ^= n;
	    
	    System.out.print(xor);
	}
}
