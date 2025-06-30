import java.io.*;
import java.util.*;

public class TreeDistancesII {
    static List<Integer>[] adj;
    static int[] size;
    static int[] res;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }
        size = new int[n + 1];
        res = new int[n + 1];
        dfs1(1, -1);
        dfs2(1, -1);

	StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            sb.append(res[i]).append(" ");
        }
	
	System.out.println(sb);
    }

    static void dfs1(int u, int parent) {
        size[u] = 1;
        for (int v : adj[u]) {
            if (v != parent) {
                dfs1(v, u);
                size[u] += size[v];
                res[u] += res[v] + size[v];
            }
        }
    }

    static void dfs2(int u, int parent) {
        for (int v : adj[u]) {
            if (v != parent) {
                res[v] = res[u] - size[v] + (n - size[v]);
                dfs2(v, u);
            }
        }
    }
}
