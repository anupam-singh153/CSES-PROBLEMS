import java.io.*;
import java.util.*;

public class HighScore {
    static class Edge {
        int a, b;
        long c;
        Edge(int a, int b, long c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            edges.add(new Edge(a, b, -c)); // We negate to find maximum
        }
        
        long[] dist = new long[n+1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;
        
        // Bellman-Ford algorithm
        for (int i = 1; i < n; i++) {
            for (Edge e : edges) {
                if (dist[e.a] != Long.MAX_VALUE && dist[e.b] > dist[e.a] + e.c) {
                    dist[e.b] = dist[e.a] + e.c;
                }
            }
        }
        
        // Check for negative cycles on path to n
        boolean hasNegativeCycle = false;
        for (Edge e : edges) {
            if (dist[e.a] != Long.MAX_VALUE && dist[e.b] > dist[e.a] + e.c) {
                // Negative cycle found, check if it's on path to n
                if (isReachable(e.b, n, edges)) {
                    hasNegativeCycle = true;
                    break;
                }
            }
        }
        
        if (hasNegativeCycle) {
            pw.println(-1);
        } else {
            pw.println(-dist[n]);
        }
        pw.flush();
    }
    
    static boolean isReachable(int start, int end, List<Edge> edges) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited.add(start);
        
        while (!q.isEmpty()) {
            int u = q.poll();
            if (u == end) return true;
            
            for (Edge e : edges) {
                if (e.a == u && !visited.contains(e.b)) {
                    visited.add(e.b);
                    q.add(e.b);
                }
            }
        }
        
        return false;
    }
}
