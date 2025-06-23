import java.io.*;
import java.util.*;
 
public class Creating_Strings {
    public static void main(String[] args) throws IOException {
        
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	PrintWriter out = new PrintWriter(System.out);

        String s = br.readLine();
        
        // Count character frequencies
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        List<String> result = new ArrayList<>();
        generatePermutations(count, new StringBuilder(), s.length(), result);
        
        // Output results
        out.println(result.size());

        for (String perm : result) {
            out.println(perm);
        }

	out.flush();
    }
    
    private static void generatePermutations(int[] count, StringBuilder current, 
                                          int remaining, List<String> result) {
        if (remaining == 0) {
            result.add(current.toString());
            return;
        }
        
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                count[i]--;
                current.append((char)('a' + i));
                
                generatePermutations(count, current, remaining - 1, result);
                
                // Backtrack
                current.deleteCharAt(current.length() - 1);
                count[i]++;
            }
        }
    }
}
