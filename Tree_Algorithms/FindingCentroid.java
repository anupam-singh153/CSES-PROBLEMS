import java.io.*;
import java.util.*;

public class FindingCentroid {
    static List<Integer>[] adj;
    static int[] subSize;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        n = Integer.parseInt(br.readLine());
        adj = new ArrayList[n + 1];
        subSize = new int[n + 1];

        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        int centroid = findCentroid(1, 0);
        pw.println(centroid);
        pw.flush();
    }

    static int findCentroid(int u, int p) {
        dfs(u, p);
        return go(u, p);
    }

    static void dfs(int u, int p) {
        subSize[u] = 1;
        for (int v : adj[u]) {
            if (v != p) {
                dfs(v, u);
                subSize[u] += subSize[v];
            }
        }
    }

    static int go(int u, int p) {
        for (int v : adj[u]) {
            if (v != p && subSize[v] > n / 2) {
                return go(v, u);
            }
        }
        return u;
    }
}
