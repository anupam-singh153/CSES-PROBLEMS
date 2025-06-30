import java.io.*;
import java.util.*;

public class TreeMatching {
    static List<Integer>[] adj;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        adj = new ArrayList[n + 1];
        dp = new int[n + 1][2];

        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        dfs(1, 0);

        pw.println(Math.max(dp[1][0], dp[1][1]));
        pw.flush();
    }

    static void dfs(int u, int p) {
        dp[u][0] = 0;
        dp[u][1] = 0;

        for (int v : adj[u]) {
            if (v == p) continue;
            dfs(v, u);
            dp[u][0] += Math.max(dp[v][0], dp[v][1]);
        }

        for (int v : adj[u]) {
            if (v == p) continue;
            int val = 1 + dp[v][0] + (dp[u][0] - Math.max(dp[v][0], dp[v][1]));
            dp[u][1] = Math.max(dp[u][1], val);
        }
    }
}
