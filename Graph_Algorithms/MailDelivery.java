import java.io.*;
import java.util.*;

public class MailDelivery {
    static List<Integer>[] adj;
    static Deque<Integer> circuit = new ArrayDeque<>();
    static Map<Long, Boolean> used = new HashMap<>();

    static long edgeKey(int u, int v) {
        if (u > v) {
            int temp = u;
            u = v;
            v = temp;
        }
        return ((long)u << 32) | v;
    }

    static void dfs(int u) {
        while (!adj[u].isEmpty()) {
            int v = adj[u].remove(adj[u].size() - 1);
            long key = edgeKey(u, v);
            if (used.getOrDefault(key, false)) continue;
            used.put(key, true);
            dfs(v);
        }
        circuit.push(u);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);

        adj = new ArrayList[n + 1];
        int[] deg = new int[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            parts = br.readLine().split(" ");
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[1]);
            adj[a].add(b);
            adj[b].add(a);
            deg[a]++;
            deg[b]++;
        }

        // Check degree condition
        for (int i = 1; i <= n; i++) {
            if (deg[i] % 2 != 0) {
                System.out.println("IMPOSSIBLE");
                return;
            }
        }

        dfs(1);

        if (circuit.size() != m + 1) {
            System.out.println("IMPOSSIBLE");
            return;
        }

        StringBuilder sb = new StringBuilder();
        while (!circuit.isEmpty()) {
            sb.append(circuit.pop()).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
