import java.io.*;
import java.util.*;

public class FlightDiscount {
    static class Pair {
        int node;
        long dist;
        boolean used;
        
        Pair(int node, long dist, boolean used) {
            this.node = node;
            this.dist = dist;
            this.used = used;
        }
    }
    
    static class Edge {
        int to;
        long weight;
        Edge(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        List<Edge>[] adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            adj[a].add(new Edge(b, c));
        }
        
        long[][] dist = new long[n+1][2];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }
        dist[1][0] = 0;
        dist[1][1] = 0;
        
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingLong(p -> p.dist));
        pq.add(new Pair(1, 0, false));
        
        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int u = curr.node;
            boolean used = curr.used;
            
            if (curr.dist > dist[u][used ? 1 : 0]) continue;
            
            for (Edge e : adj[u]) {
                int v = e.to;
                long w = e.weight;
                
                // Case 1: Don't use the discount
                if (!used && dist[v][0] > dist[u][0] + w) {
                    dist[v][0] = dist[u][0] + w;
                    pq.add(new Pair(v, dist[v][0], false));
                }
                
                // Case 2: Use the discount (either now or already used)
                long newDist = used ? dist[u][1] + w : dist[u][0] + w / 2;
                int usedState = used ? 1 : 1;
                if (dist[v][1] > newDist) {
                    dist[v][1] = newDist;
                    pq.add(new Pair(v, dist[v][1], true));
                }
            }
        }
        
        pw.println(Math.min(dist[n][0], dist[n][1]));
        pw.flush();
    }
}
