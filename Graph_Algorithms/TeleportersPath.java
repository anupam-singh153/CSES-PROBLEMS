import java.io.*;
import java.util.*;

public class TeleportersPath {
    static List<Integer>[] adj;
    static Deque<Integer> path = new ArrayDeque<>();
    static int[] outDeg;

    static void dfs(int u) {
        while (outDeg[u] > 0) {
            int v = adj[u].get(--outDeg[u]);
            dfs(v);
        }
        path.push(u);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);

        adj = new ArrayList[n + 1];
        outDeg = new int[n + 1];
        int[] inDeg = new int[n + 1];

        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            parts = br.readLine().split(" ");
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[1]);
            adj[a].add(b);
            outDeg[a]++;
            inDeg[b]++;
        }

        // Check Eulerian path conditions
        int startExtra = 0, endExtra = 0;
        for (int i = 1; i <= n; i++) {
            if (outDeg[i] == inDeg[i] + 1) {
                startExtra++;
                if (i != 1) {
                    System.out.println("IMPOSSIBLE");
                    return;
                }
            } else if (inDeg[i] == outDeg[i] + 1) {
                endExtra++;
                if (i != n) {
                    System.out.println("IMPOSSIBLE");
                    return;
                }
            } else if (outDeg[i] != inDeg[i]) {
                System.out.println("IMPOSSIBLE");
                return;
            }
        }

        if (startExtra > 1 || endExtra > 1) {
            System.out.println("IMPOSSIBLE");
            return;
        }

        dfs(1);

        if (path.size() != m + 1) {
            System.out.println("IMPOSSIBLE");
            return;
        }

        StringBuilder sb = new StringBuilder();
        while (!path.isEmpty()) {
            sb.append(path.pop()).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
