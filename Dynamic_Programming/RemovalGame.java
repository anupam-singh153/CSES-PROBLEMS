import java.io.*;
import java.util.*;

public class RemovalGame {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] arr = new int[n];
        long[] prefix = new long[n+1];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            prefix[i+1] = prefix[i] + arr[i];
        }
        
        long[][] dp = new long[n][n];
        
        for (int l = 0; l < n; l++) {
            for (int i = 0; i + l < n; i++) {
                int j = i + l;
                if (i == j) {
                    dp[i][j] = arr[i];
                } else {
                    long total = prefix[j+1] - prefix[i];
                    dp[i][j] = total - Math.min(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        
        pw.println(dp[0][n-1]);
        pw.flush();
    }
}
