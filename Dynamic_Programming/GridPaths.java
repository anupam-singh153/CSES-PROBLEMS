import java.io.*;
import java.util.*;

public class GridPaths {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        final int MOD = 1_000_000_007;
        
        char[][] grid = new char[n][n];
        for (int i = 0; i < n; i++) {
            grid[i] = br.readLine().toCharArray();
        }
        
        long[][] dp = new long[n][n];
        dp[0][0] = grid[0][0] == '.' ? 1 : 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '*') continue;
                if (i > 0) dp[i][j] = (dp[i][j] + dp[i-1][j]) % MOD;
                if (j > 0) dp[i][j] = (dp[i][j] + dp[i][j-1]) % MOD;
            }
        }
        
        pw.println(dp[n-1][n-1]);
        pw.flush();
    }
}
