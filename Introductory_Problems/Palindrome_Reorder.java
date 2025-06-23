import java.io.*;
import java.util.*;
 
public class Palindrome_Reorder {
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	PrintWriter out = new PrintWriter(System.out);

        String s = br.readLine().trim();
 
        int[] freq = new int[26];
        
        for(char c : s.toCharArray())
            freq[c-'A'] += 1;
            
        int extra = 0;
        char extChar = '_';
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < 26; ++i){
            
            if((freq[i] & 1) == 1){
                extChar = (char)('A'+i);
                extra += 1;
            }
            
            for(int k = 0; k < freq[i] >> 1; ++k)
                sb.append((char)('A'+i));
        }
            
        if(extra > 1){
            System.out.println("NO SOLUTION");
            return;
        }
        
        s = sb.toString();
        sb.reverse();
        
        if(extChar != '_')
            sb.append(extChar);
            
        sb.append(s);
        
        out.println(sb);
	out.flush();
    }
}
