import java.io.*;
import java.util.*;

public class LongestCommonSubsequence {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] b = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
        
        // DP table where dp[i][j] represents LCS length of a[0..i-1] and b[0..j-1]
        int[][] dp = new int[n+1][m+1];
        
        // Fill DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a[i-1] == b[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        
        // Length of LCS
        int lcsLength = dp[n][m];
        pw.println(lcsLength);
        
        // Reconstruct the LCS
        List<Integer> lcs = new ArrayList<>();
        int i = n, j = m;
        while (i > 0 && j > 0) {
            if (a[i-1] == b[j-1]) {
                lcs.add(a[i-1]);
                i--;
                j--;
            } else if (dp[i-1][j] > dp[i][j-1]) {
                i--;
            } else {
                j--;
            }
        }
        
        // Print the LCS in correct order
        Collections.reverse(lcs);
        for (int num : lcs) {
            pw.print(num + " ");
        }
        pw.println();
        pw.flush();
    }
}