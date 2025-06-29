import java.io.*;
import java.util.*;

public class CountingNumbers {
    static final long MOD = (long)1e18;
    
    static long countNumbers(String s) {
        int n = s.length();
        long[][][] dp = new long[n][2][10]; // pos, tight, last_digit
        
        for (int d = 1; d <= s.charAt(0) - '0'; d++) {
            int tight = (d == s.charAt(0) - '0') ? 1 : 0;
            dp[0][tight][d] = 1;
        }
        
        for (int i = 1; i < n; i++) {
            for (int tight = 0; tight < 2; tight++) {
                for (int last = 0; last < 10; last++) {
                    if (dp[i-1][tight][last] == 0) continue;
                    
                    int maxDigit = tight == 1 ? s.charAt(i) - '0' : 9;
                    
                    for (int d = 0; d <= maxDigit; d++) {
                        if (d == last) continue;
                        
                        int newTight = (tight == 1 && d == maxDigit) ? 1 : 0;
                        dp[i][newTight][d] = (dp[i][newTight][d] + dp[i-1][tight][last]) % MOD;
                    }
                }
            }
            
            // Add numbers starting at this position
            if (i > 0) {
                for (int d = 1; d <= 9; d++) {
                    dp[i][0][d] = (dp[i][0][d] + 1) % MOD;
                }
            }
        }
        
        long total = 0;
        for (int tight = 0; tight < 2; tight++) {
            for (int d = 0; d < 10; d++) {
                total = (total + dp[n-1][tight][d]) % MOD;
            }
        }
        return total;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        String[] arr = br.readLine().split(" ");
              
	String a = arr[0];
	String b = arr[1];

        long resultB = countNumbers(b);
        long resultA = countNumbers(a);
        
        // Check if a itself is valid
        boolean valid = true;
        for (int i = 1; i < a.length(); i++) {
            if (a.charAt(i) == a.charAt(i-1)) {
                valid = false;
                break;
            }
        }
        long adjustment = valid ? 1 : 0;
        
        long answer = (resultB - resultA + adjustment + MOD) % MOD;
        pw.println(answer);
        pw.flush();
    }
}
