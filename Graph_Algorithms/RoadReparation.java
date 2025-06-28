import java.io.*;
import java.util.*;

public class RoadReparation {
    static class Edge implements Comparable<Edge> {
        int u, v;
        long w;
        Edge(int u, int v, long w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
        public int compareTo(Edge other) {
            return Long.compare(this.w, other.w);
        }
    }
    
    static int[] parent;
    
    static int find(int u) {
        if (parent[u] != u) {
            parent[u] = find(parent[u]);
        }
        return parent[u];
    }
    
    static boolean union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) return false;
        parent[v] = u;
        return true;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        parent = new int[n+1];
        for (int i = 1; i <= n; i++) parent[i] = i;
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            pq.add(new Edge(a, b, c));
        }
        
        long total = 0;
        int edgesUsed = 0;
        
        while (!pq.isEmpty() && edgesUsed < n - 1) {
            Edge e = pq.poll();
            if (union(e.u, e.v)) {
                total += e.w;
                edgesUsed++;
            }
        }
        
        if (edgesUsed == n - 1) {
            pw.println(total);
        } else {
            pw.println("IMPOSSIBLE");
        }
        pw.flush();
    }
}
