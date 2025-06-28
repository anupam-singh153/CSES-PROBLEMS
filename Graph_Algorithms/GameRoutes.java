import java.io.*;
import java.util.*;

public class GameRoutes {
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
        
        long[] count = new long[n+1];
        count[1] = 1;
        final long MOD = (long)1e9 + 7;
        
        for (int u : topo) {
            for (int v : adj[u]) {
                count[v] = (count[v] + count[u]) % MOD;
            }
        }
        
        pw.println(count[n]);
        pw.flush();
    }
}
