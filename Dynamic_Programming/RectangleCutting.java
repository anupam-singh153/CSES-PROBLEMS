import java.io.*;
import java.util.*;

public class RectangleCutting {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        
        int[][] dp = new int[a+1][b+1];
        
        for (int i = 1; i <= a; i++) {
            for (int j = 1; j <= b; j++) {
                if (i == j) {
                    dp[i][j] = 0;
                    continue;
                }
                
                dp[i][j] = Integer.MAX_VALUE;
                
                // Vertical cuts
                for (int k = 1; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[k][j] + dp[i-k][j] + 1);
                }
                
                // Horizontal cuts
                for (int k = 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[i][j-k] + 1);
                }
            }
        }
        
        pw.println(dp[a][b]);
        pw.flush();
    }
}
