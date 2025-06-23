import java.util.*;
import java.io.*;
 
public class Trailing_Zeros
{
	public static void main(String[] args) throws IOException{
 
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int n = Integer.parseInt(bf.readLine());
	
          	long ans = 0;
	    
	    	for(int i = 5; i <= n; i += 5){
	        
	       	    int k = i;
	       
	      	    while(k % 5 == 0){
	           	ans += 1;
	           	k /= 5;
	       	   }
	       }
	    
		out.println(ans);
		out.flush();
	  }
}
