import java.io.*;
import java.util.*;

public class CountingTowers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int t = Integer.parseInt(br.readLine());
        final int MOD = 1_000_000_007;
        final int MAX = 1_000_000;
        
        long[] dp = new long[MAX+1];
        long[] dpExtend = new long[MAX+1];
        
        dp[1] = 1;
        dpExtend[1] = 1;
        
        for (int i = 2; i <= MAX; i++) {
            dp[i] = (2 * dp[i-1] + dpExtend[i-1]) % MOD;
            dpExtend[i] = (4 * dpExtend[i-1] + dp[i-1]) % MOD;
        }
        
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            pw.println((dp[n] + dpExtend[n]) % MOD);
        }
        pw.flush();
    }
}
