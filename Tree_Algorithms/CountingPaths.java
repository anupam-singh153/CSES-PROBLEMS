import java.io.*;
import java.util.*;

public class CountingPaths {
    static int n, m;
    static List<Integer>[] adj;
    static int[] depth;
    static int[][] up;
    static int LOG = 20;
    static long[] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) adj[i] = new ArrayList<>();

        // Read tree edges
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        depth = new int[n + 1];
        up = new int[n + 1][LOG];
        cnt = new long[n + 1];

        // Preprocess LCA
        dfs(1, 1);

        // Read and process paths
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            cnt[a]++;
            cnt[b]++;
            int lca = lca(a, b);
            cnt[lca]--;
            if (up[lca][0] != lca) cnt[up[lca][0]]--;
        }

        // Propagate counts
        propagate(1, 1);

        // Output result
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(cnt[i]).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    static void dfs(int v, int p) {
        up[v][0] = p;
        for (int i = 1; i < LOG; i++) {
            up[v][i] = up[up[v][i - 1]][i - 1];
        }
        for (int u : adj[v]) {
            if (u != p) {
                depth[u] = depth[v] + 1;
                dfs(u, v);
            }
        }
    }

    static int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int tmp = a; a = b; b = tmp;
        }
        for (int i = LOG - 1; i >= 0; i--) {
            if (depth[a] - (1 << i) >= depth[b]) {
                a = up[a][i];
            }
        }
        if (a == b) return a;
        for (int i = LOG - 1; i >= 0; i--) {
            if (up[a][i] != up[b][i]) {
                a = up[a][i];
                b = up[b][i];
            }
        }
        return up[a][0];
    }

    static void propagate(int v, int p) {
        for (int u : adj[v]) {
            if (u != p) {
                propagate(u, v);
                cnt[v] += cnt[u];
            }
        }
    }
}
