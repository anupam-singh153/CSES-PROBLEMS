import java.io.*;
import java.util.*;

public class ShortestRoutesI {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        List<Pair>[] adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            adj[a].add(new Pair(b, c));
        }
        
        long[] dist = new long[n+1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;
        
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(1, 0));
        
        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int u = curr.node;
            if (curr.dist > dist[u]) continue;
            
            for (Pair edge : adj[u]) {
                int v = edge.node;
                long w = edge.dist;
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    pq.add(new Pair(v, dist[v]));
                }
            }
        }
        
	StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(dist[i]).append(" ");
        }

	pw.println(sb);
        pw.flush();
    }
    
    static class Pair implements Comparable<Pair> {
        int node;
        long dist;
        
        public Pair(int node, long dist) {
            this.node = node;
            this.dist = dist;
        }
        
        public int compareTo(Pair other) {
            return Long.compare(this.dist, other.dist);
        }
    }
}
