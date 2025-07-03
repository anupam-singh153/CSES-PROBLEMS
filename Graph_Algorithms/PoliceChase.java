import java.io.*;
import java.util.*;

public class PoliceChase {
    static class Edge {
        int to, rev;
        int cap;

        Edge(int to, int rev, int cap) {
            this.to = to;
            this.rev = rev;
            this.cap = cap;
        }
    }

    static List<Edge>[] graph;
    static int[] level;
    static int[] ptr;
    static int n;

    static void addEdge(int u, int v) {
        graph[u].add(new Edge(v, graph[v].size(), 1));
        graph[v].add(new Edge(u, graph[u].size() - 1, 1));  // undirected -> add both ways
    }

    static boolean bfs(int s, int t) {
        Arrays.fill(level, -1);
        level[s] = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(s);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (Edge e : graph[u]) {
                if (e.cap > 0 && level[e.to] == -1) {
                    level[e.to] = level[u] + 1;
                    q.add(e.to);
                }
            }
        }
        return level[t] != -1;
    }

    static int dfs(int u, int t, int flow) {
        if (u == t || flow == 0) return flow;
        for (; ptr[u] < graph[u].size(); ptr[u]++) {
            Edge e = graph[u].get(ptr[u]);
            if (level[e.to] == level[u] + 1 && e.cap > 0) {
                int pushed = dfs(e.to, t, Math.min(flow, e.cap));
                if (pushed > 0) {
                    e.cap -= pushed;
                    graph[e.to].get(e.rev).cap += pushed;
                    return pushed;
                }
            }
        }
        return 0;
    }

    static int maxFlow(int s, int t) {
        int flow = 0;
        while (bfs(s, t)) {
            ptr = new int[n + 1];
            int pushed;
            while ((pushed = dfs(s, t, Integer.MAX_VALUE)) > 0) {
                flow += pushed;
            }
        }
        return flow;
    }

    static void markReachable(int u, boolean[] visited) {
        visited[u] = true;
        for (Edge e : graph[u]) {
            if (e.cap > 0 && !visited[e.to]) {
                markReachable(e.to, visited);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        List<int[]> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            parts = br.readLine().split(" ");
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[1]);
            addEdge(a, b);
            edges.add(new int[]{a, b});
        }

        level = new int[n + 1];

        int flow = maxFlow(1, n);
        System.out.println(flow);

        boolean[] reachable = new boolean[n + 1];
        markReachable(1, reachable);

        for (int[] e : edges) {
            if (reachable[e[0]] && !reachable[e[1]]) {
                System.out.println(e[0] + " " + e[1]);
            } else if (reachable[e[1]] && !reachable[e[0]]) {
                System.out.println(e[1] + " " + e[0]);
            }
        }
    }
}
