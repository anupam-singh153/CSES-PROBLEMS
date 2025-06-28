import java.io.*;
import java.util.*;

public class CycleFinding {
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
        
        Edge[] edges = new Edge[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            edges[i] = new Edge(a, b, c);
        }
        
        long[] dist = new long[n+1];
        int[] parent = new int[n+1];
        Arrays.fill(parent, -1);
        int cycleNode = -1;
        
        for (int i = 1; i <= n; i++) {
            cycleNode = -1;
            for (Edge e : edges) {
                if (dist[e.a] + e.c < dist[e.b]) {
                    dist[e.b] = dist[e.a] + e.c;
                    parent[e.b] = e.a;
                    cycleNode = e.b;
                }
            }
        }
        
        if (cycleNode == -1) {
            pw.println("NO");
        } else {
            pw.println("YES");
            // To find the actual cycle, we need to backtrack
            for (int i = 0; i < n; i++) {
                cycleNode = parent[cycleNode];
            }
            
            List<Integer> cycle = new ArrayList<>();
            for (int v = cycleNode;; v = parent[v]) {
                cycle.add(v);
                if (v == cycleNode && cycle.size() > 1) break;
            }
            Collections.reverse(cycle);
            
            for (int node : cycle) {
                pw.print(node + " ");
            }
        }
        pw.flush();
    }
}
