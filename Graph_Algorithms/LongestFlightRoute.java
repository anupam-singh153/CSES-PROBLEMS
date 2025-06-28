import java.io.*;
import java.util.*;

public class LongestFlightRoute {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        List<Integer>[] adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        int[] inDegree = new int[n+1];
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            inDegree[b]++;
        }
        
        // Topological sort
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) q.add(i);
        }
        
        List<Integer> topo = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            topo.add(u);
            
            for (int v : adj[u]) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    q.add(v);
                }
            }
        }
        
        if (topo.size() != n) {
            pw.println("IMPOSSIBLE");
            pw.flush();
            return;
        }
        
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MIN_VALUE);
        dist[1] = 1;
        int[] parent = new int[n+1];
        
        for (int u : topo) {
            if (dist[u] != Integer.MIN_VALUE) {
                for (int v : adj[u]) {
                    if (dist[v] < dist[u] + 1) {
                        dist[v] = dist[u] + 1;
                        parent[v] = u;
                    }
                }
            }
        }
        
        if (dist[n] == Integer.MIN_VALUE) {
            pw.println("IMPOSSIBLE");
        } else {
            pw.println(dist[n]);
            List<Integer> path = new ArrayList<>();
            for (int v = n; v != 0; v = parent[v]) {
                path.add(v);
            }
            Collections.reverse(path);
            for (int node : path) {
                pw.print(node + " ");
            }
        }
        pw.flush();
    }
}
