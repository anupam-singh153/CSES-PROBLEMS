import java.util.*;
import java.io.*;
 
public class Number_Spiral
{
	public static void main(String[] args) throws IOException {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    PrintWriter out = new PrintWriter(System.out);

	    int t = Integer.parseInt(bf.readLine());
	   
	    while(t-- > 0){

		StringTokenizer st = new StringTokenizer(bf.readLine());
	        long y = Long.parseLong(st.nextToken());
	        long x = Long.parseLong(st.nextToken());
	        
	        long ans,add;
	        
	        if(y > x){
	            ans = (y-1)*(y-1);
	            
	            if(y % 2 != 0)
	                    add = x;
	                    
	            else
	                    add = 2*y - x;
	        }
	        else{
	            ans = (x-1)*(x-1);
	             
	            if(x % 2 == 0)
	                    add = y;
	                    
	            else
	                    add = 2*x - y;
	        }
	         out.println(ans+add);
		 out.flush();
	    }
	}
}
