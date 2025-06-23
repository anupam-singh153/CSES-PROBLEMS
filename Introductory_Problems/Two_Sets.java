import java.util.*;
import java.io.*;
 
public class Two_Sets
{
	public static void main(String[] args) throws IOException{
		
		PrintWriter out = new PrintWriter(System.out);
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		String s = bf.readLine();
		
		long n = Long.parseLong(s);
		
		if(n*(n+1)/2 % 2 == 1){
		    out.println("NO");
		    out.flush();
		    return;
		}
		
		List<Integer> l1 = new ArrayList<>();
		List<Integer> l2 = new ArrayList<>();
		
		int x = 0;
		
		if(n % 4 == 0)
		    x = 4;
		else
		    x = 3;
		    
		for(int k = 0; k < (n-x)/4; ++k){
		    
		    l1.add(4*k + 1 + x);
		    l1.add(4*k + 4 + x);
		    
		    l2.add(4*k + 2 + x);
		    l2.add(4*k + 3 + x);
		}
		
		if(n % 4 == 0){
		    l1.add(1);
		    l1.add(4);
		    
		    l2.add(2);
		    l2.add(3);
		}
		else{
		    l1.add(1);
		    l1.add(2);
		    
		    l2.add(3);
		}
		
		out.println("YES");
		out.println(l1.size());
		
		for(int v : l1)
		    out.print(v+" ");
		 
		out.println("\n"+l2.size());
		
		for(int v : l2)
		    out.print(v+" ");
		
		out.flush();
	}
}
