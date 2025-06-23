import java.util.Scanner;
 
public class Repetitions
{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    
	    String s = sc.next();
	    
	    int ans = 1, cnt = 1;
	    
	    for(int i = 1; i < s.length(); ++i){
	        
	        if(s.charAt(i-1) == s.charAt(i))
	            cnt += 1;
	            
	        else
	            cnt = 1;
	            
	       ans = Math.max(ans,cnt);
	    }
	    
	    System.out.println(ans);
	}
}
