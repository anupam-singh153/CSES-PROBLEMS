import java.io.*;
import java.util.*;

public class PlanetsQueriesI {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        
        int[][] up = new int[n+1][30]; // up[u][k] = 2^k-th ancestor of u
        st = new StringTokenizer(br.readLine());
        for (int u = 1; u <= n; u++) {
            up[u][0] = Integer.parseInt(st.nextToken());
        }
        
        // Binary lifting preprocessing
        for (int k = 1; k < 30; k++) {
            for (int u = 1; u <= n; u++) {
                up[u][k] = up[up[u][k-1]][k-1];
            }
        }
        
        // Process queries
        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            
            for (int i = 29; i >= 0; i--) {
                if ((k & (1 << i)) != 0) {
                    u = up[u][i];
                }
            }
            pw.println(u);
        }
        pw.flush();
    }
}
