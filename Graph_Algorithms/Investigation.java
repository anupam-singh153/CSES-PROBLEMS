import java.io.*;
import java.util.*;

public class Investigation {
    static class Edge {
        int to;
        long weight;
        Edge(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    
    static class Node implements Comparable<Node> {
        int id;
        long dist;
        Node(int id, long dist) {
            this.id = id;
            this.dist = dist;
        }
        public int compareTo(Node other) {
            return Long.compare(this.dist, other.dist);
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
        
        long[] dist = new long[n+1];
        int[] count = new int[n+1];
        int[] minFlights = new int[n+1];
        int[] maxFlights = new int[n+1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;
        count[1] = 1;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int u = curr.id;
            if (curr.dist > dist[u]) continue;
            
            for (Edge e : adj[u]) {
                int v = e.to;
                long newDist = dist[u] + e.weight;
                
                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    count[v] = count[u];
                    minFlights[v] = minFlights[u] + 1;
                    maxFlights[v] = maxFlights[u] + 1;
                    pq.add(new Node(v, newDist));
                } else if (newDist == dist[v]) {
                    count[v] = (count[v] + count[u]) % 1000000007;
                    minFlights[v] = Math.min(minFlights[v], minFlights[u] + 1);
                    maxFlights[v] = Math.max(maxFlights[v], maxFlights[u] + 1);
                }
            }
        }
        
        pw.println(dist[n] + " " + count[n] + " " + minFlights[n] + " " + maxFlights[n]);
        pw.flush();
    }
}
