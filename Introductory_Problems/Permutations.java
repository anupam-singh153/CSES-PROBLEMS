import java.util.*;
import java.io.*;
 
public class Permutations
{
	public static void main(String[] args) throws IOException{

	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    PrintWriter out = new PrintWriter(System.out);
	    
	    int n = Integer.parseInt(bf.readLine());
	   
	    //Not possible to arrange them
	    if(n == 2 || n == 3){
	       System.out.println("NO SOLUTION"); 
	       return;
	    }
	    
	    //print first even numbers then odd
	    for(int i = 2; i <= n; i += 2){
	       out.print(i+" ");
	    }
	    
	    for(int i = 1; i <= n; i += 2){
	       out.print(i+" ");
	    }
		
	    out.flush();
	}
}
