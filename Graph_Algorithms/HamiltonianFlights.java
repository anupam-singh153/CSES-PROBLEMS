import java.io.*;
import java.util.*;

public class HamiltonianFlights {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            adj[a].add(b);
        }
        
        final int MOD = 1_000_000_007;
        int total = 1 << n;
        long[][] dp = new long[total][n];
        dp[1][0] = 1; // Starting at city 1 (0-based) with mask 0001
        
        for (int mask = 1; mask < total; mask++) {
            for (int last = 0; last < n; last++) {
                if ((mask & (1 << last)) == 0) continue;
                if (dp[mask][last] == 0) continue;
                
                for (int next : adj[last]) {
                    if ((mask & (1 << next)) != 0) continue;
                    int newMask = mask | (1 << next);
                    dp[newMask][next] = (dp[newMask][next] + dp[mask][last]) % MOD;
                }
            }
        }
        
        pw.println(dp[total - 1][n - 1]);
        pw.flush();
    }
}
