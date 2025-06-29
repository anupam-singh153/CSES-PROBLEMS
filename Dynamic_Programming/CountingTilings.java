import java.io.*;
import java.util.*;

public class CountingTilings {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        final int MOD = 1_000_000_007;
        
        // DP[mask] = number of ways to fill the grid up to current column with mask
        int[] dp = new int[1 << n];
        dp[0] = 1;
        
        for (int col = 0; col < m; col++) {
            for (int row = 0; row < n; row++) {
                int[] new_dp = new int[1 << n];
                for (int mask = 0; mask < (1 << n); mask++) {
                    if ((mask & (1 << row)) != 0) {
                        // Current cell is already filled, move to next
                        new_dp[mask ^ (1 << row)] = (new_dp[mask ^ (1 << row)] + dp[mask]) % MOD;
                    } else {
                        // Try placing a vertical domino (if possible)
                        if (row < n - 1 && (mask & (1 << (row + 1))) == 0) {
                            new_dp[mask | (1 << (row + 1))] = (new_dp[mask | (1 << (row + 1))] + dp[mask]) % MOD;
                        }
                        // Try placing a horizontal domino
                        new_dp[mask | (1 << row)] = (new_dp[mask | (1 << row)] + dp[mask]) % MOD;
                    }
                }
                dp = new_dp;
            }
        }
        
        pw.println(dp[0]);
        pw.flush();
    }
}
