import java.io.*;
import java.util.*;

public class TwoSetsII {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        final int MOD = 1_000_000_007;
        
        int total = n * (n + 1) / 2;
        if (total % 2 != 0) {
            pw.println(0);
            pw.flush();
            return;
        }
        
        int target = total / 2;
        long[] dp = new long[target + 1];
        dp[0] = 1;
        
        for (int num = 1; num <= n; num++) {
            for (int j = target; j >= num; j--) {
                dp[j] = (dp[j] + dp[j - num]) % MOD;
            }
        }
        
        // Multiply by modular inverse of 2
        pw.println((dp[target] * 500000004) % MOD);
        pw.flush();
    }
}
