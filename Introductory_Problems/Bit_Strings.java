import java.util.*;
import java.io.*;
 
public class Bit_Strings
{
	public static void main(String[] args) throws IOException{
 
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		int n = Integer.parseInt(bf.readLine());
		
		long ans = 1;
		
		for(int i = 1; i <= n; ++i){
		  ans = (ans * 2) % ((int)1e9 + 7);
		}
		
		out.println(ans);
		out.flush();
	}
}
