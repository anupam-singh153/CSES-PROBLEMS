import java.io.*;
import java.util.*;

public class MessageRoute {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        List<Integer>[] adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }
        
        int[] parent = new int[n+1];
        Arrays.fill(parent, -1);
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        parent[1] = 0;
        
        while (!q.isEmpty()) {
            int u = q.poll();
            if (u == n) break;
            
            for (int v : adj[u]) {
                if (parent[v] == -1) {
                    parent[v] = u;
                    q.add(v);
                }
            }
        }
        
        if (parent[n] == -1) {
            pw.println("IMPOSSIBLE");
        } else {
            List<Integer> path = new ArrayList<>();
            for (int u = n; u != 0; u = parent[u]) {
                path.add(u);
            }
            Collections.reverse(path);
            pw.println(path.size());
            for (int node : path) {
                pw.print(node + " ");
            }
        }
        pw.flush();
    }
}
