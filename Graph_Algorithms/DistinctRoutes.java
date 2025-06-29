import java.io.*;
import java.util.*;

public class DistinctRoutes {
    static class Edge {
        int to, rev;
        long cap, cost;
        Edge(int to, int rev, long cap, long cost) {
            this.to = to;
            this.rev = rev;
            this.cap = cap;
            this.cost = cost;
        }
    }
    
    static List<Edge>[] adj;
    static long[] dist;
    static int[] par, parEdge;
    static boolean[] inQueue;
    
    static void addEdge(int u, int v, long cap, long cost) {
        adj[u].add(new Edge(v, adj[v].size(), cap, cost));
        adj[v].add(new Edge(u, adj[u].size() - 1, 0, -cost));
    }
    
    static boolean spfa(int s, int t) {
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[s] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        inQueue[s] = true;
        
        while (!q.isEmpty()) {
            int u = q.poll();
            inQueue[u] = false;
            
            for (int i = 0; i < adj[u].size(); i++) {
                Edge e = adj[u].get(i);
                if (e.cap > 0 && dist[e.to] > dist[u] + e.cost) {
                    dist[e.to] = dist[u] + e.cost;
                    par[e.to] = u;
                    parEdge[e.to] = i;
                    
                    if (!inQueue[e.to]) {
                        inQueue[e.to] = true;
                        q.add(e.to);
                    }
                }
            }
        }
        return dist[t] != Long.MAX_VALUE;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        dist = new long[n+1];
        par = new int[n+1];
        parEdge = new int[n+1];
        inQueue = new boolean[n+1];
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            addEdge(a, b, 1, 1); // Capacity 1, cost 1 per edge
        }
        
        long maxFlow = 0;
        long minCost = 0;
        
        while (spfa(1, n)) {
            long flow = Long.MAX_VALUE;
            for (int v = n; v != 1; v = par[v]) {
                int u = par[v];
                int i = parEdge[v];
                flow = Math.min(flow, adj[u].get(i).cap);
            }
            
            for (int v = n; v != 1; v = par[v]) {
                int u = par[v];
                int i = parEdge[v];
                adj[u].get(i).cap -= flow;
                adj[v].get(adj[u].get(i).rev).cap += flow;
                minCost += flow * adj[u].get(i).cost;
            }
            maxFlow += flow;
        }
        
        pw.println(maxFlow);
        
        // Reconstruct paths
        for (int f = 0; f < maxFlow; f++) {
            List<Integer> path = new ArrayList<>();
            int u = 1;
            path.add(u);
            
            while (u != n) {
                for (Edge e : adj[u]) {
                    if (e.cap == 0 && e.cost == 1) {
                        adj[u].remove(e);
                        u = e.to;
                        path.add(u);
                        break;
                    }
                }
            }
            
            pw.println(path.size());
            for (int node : path) {
                pw.print(node + " ");
            }
            pw.println();
        }
        pw.flush();
    }
}
