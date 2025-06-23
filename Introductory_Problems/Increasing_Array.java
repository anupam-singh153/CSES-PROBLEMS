import java.util.*;
import java.io.*;
 
public class Increasing_Array
{
	public static void main(String[] args) throws IOException{
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    PrintWriter out = new PrintWriter(System.out);

	    int n = Integer.parseInt(bf.readLine());
	    long[] arr = new long[n];
	    
	    StringTokenizer st = new StringTokenizer(bf.readLine());

	    for(int i = 0; i < n; ++i){
	        arr[i] = Long.parseLong(st.nextToken());
	    }
	    
	    long ans = 0;
	    
	    for(int i = 1; i < n; ++i){
	        
	        if(arr[i-1] > arr[i]){
	            
	            long diff = arr[i-1] - arr[i];
	            arr[i] += diff;
	            ans += diff;
	        }
	    }
	    
	    System.out.println(ans);
	    
	    out.flush();
	}
}
