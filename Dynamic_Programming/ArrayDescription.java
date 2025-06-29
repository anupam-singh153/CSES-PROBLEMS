import java.io.*;
import java.util.*;

public class ArrayDescription {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        final int MOD = 1_000_000_007;
        long[][] dp = new long[n][m+2];
        
        // Initialize first element
        if (arr[0] == 0) {
            Arrays.fill(dp[0], 1, m+1, 1);
        } else {
            dp[0][arr[0]] = 1;
        }
        
        for (int i = 1; i < n; i++) {
            if (arr[i] == 0) {
                for (int j = 1; j <= m; j++) {
                    dp[i][j] = (dp[i-1][j-1] + dp[i-1][j] + dp[i-1][j+1]) % MOD;
                }
            } else {
                int j = arr[i];
                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j] + dp[i-1][j+1]) % MOD;
            }
        }
        
        long result = 0;
        if (arr[n-1] == 0) {
            for (int j = 1; j <= m; j++) {
                result = (result + dp[n-1][j]) % MOD;
            }
        } else {
            result = dp[n-1][arr[n-1]];
        }
        
        pw.println(result);
        pw.flush();
    }
}
