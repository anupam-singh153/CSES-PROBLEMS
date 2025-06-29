import java.io.*;
import java.util.*;

public class DownloadSpeed {
    static class Edge {
        int to, rev;
        long cap;
        Edge(int to, int rev, long cap) {
            this.to = to;
            this.rev = rev;
            this.cap = cap;
        }
    }
    
    static List<Edge>[] adj;
    static int[] level;
    static int[] ptr;
    
    static void addEdge(int u, int v, long cap) {
        adj[u].add(new Edge(v, adj[v].size(), cap));
        adj[v].add(new Edge(u, adj[u].size() - 1, 0));
    }
    
    static boolean bfs(int s, int t) {
        Arrays.fill(level, -1);
        level[s] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        
        while (!q.isEmpty()) {
            int u = q.poll();
            for (Edge e : adj[u]) {
                if (e.cap > 0 && level[e.to] == -1) {
                    level[e.to] = level[u] + 1;
                    q.add(e.to);
                }
            }
        }
        return level[t] != -1;
    }
    
    static long dfs(int u, int t, long flow) {
        if (u == t) return flow;
        for (; ptr[u] < adj[u].size(); ptr[u]++) {
            Edge e = adj[u].get(ptr[u]);
            if (e.cap > 0 && level[e.to] == level[u] + 1) {
                long df = dfs(e.to, t, Math.min(flow, e.cap));
                if (df > 0) {
                    e.cap -= df;
                    adj[e.to].get(e.rev).cap += df;
                    return df;
                }
            }
        }
        return 0;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        level = new int[n+1];
        ptr = new int[n+1];
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            addEdge(a, b, c);
        }
        
        long maxFlow = 0;
        while (bfs(1, n)) {
            Arrays.fill(ptr, 0);
            long flow;
            while ((flow = dfs(1, n, Long.MAX_VALUE)) > 0) {
                maxFlow += flow;
            }
        }
        
        pw.println(maxFlow);
        pw.flush();
    }
}
