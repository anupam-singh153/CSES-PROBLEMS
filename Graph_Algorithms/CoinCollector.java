import java.io.*;
import java.util.*;

public class CoinCollector {
    static List<Integer>[] adj;
    static List<Integer>[] radj;
    static boolean[] vis;
    static List<Integer> order = new ArrayList<>();
    static int[] comp;
    static long[] sccSum;
    static List<Integer>[] dag;

    static void dfs1(int u) {
        vis[u] = true;
        for (int v : adj[u]) {
            if (!vis[v]) dfs1(v);
        }
        order.add(u);
    }

    static void dfs2(int u, int c, long[] coins) {
        comp[u] = c;
        sccSum[c] += coins[u];
        for (int v : radj[u]) {
            if (comp[v] == -1) dfs2(v, c, coins);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);

        long[] coins = new long[n];
        parts = br.readLine().split(" ");
        for (int i = 0; i < n; i++) coins[i] = Long.parseLong(parts[i]);

        adj = new ArrayList[n];
        radj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            radj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            parts = br.readLine().split(" ");
            int a = Integer.parseInt(parts[0]) - 1;
            int b = Integer.parseInt(parts[1]) - 1;
            adj[a].add(b);
            radj[b].add(a);
        }

        vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!vis[i]) dfs1(i);
        }

        comp = new int[n];
        Arrays.fill(comp, -1);
        sccSum = new long[n];
        int cid = 0;
        Collections.reverse(order);
        for (int u : order) {
            if (comp[u] == -1) {
                dfs2(u, cid++, coins);
            }
        }

        // Build DAG
        dag = new ArrayList[cid];
        for (int i = 0; i < cid; i++) dag[i] = new ArrayList<>();
        for (int u = 0; u < n; u++) {
            for (int v : adj[u]) {
                if (comp[u] != comp[v]) {
                    dag[comp[u]].add(comp[v]);
                }
            }
        }

        // DP on DAG
        long[] dp = new long[cid];
        Arrays.fill(dp, 0);
        for (int i = 0; i < cid; i++) dp[i] = sccSum[i];

        long maxCoins = 0;
        for (int i = 0; i < cid; i++) {
            for (int v : dag[i]) {
                if (dp[v] < dp[i] + sccSum[v]) {
                    dp[v] = dp[i] + sccSum[v];
                }
            }
        }

        for (long val : dp) {
            if (val > maxCoins) maxCoins = val;
        }

        System.out.println(maxCoins);
    }
}
