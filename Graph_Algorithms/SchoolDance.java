import java.io.*;
import java.util.*;

public class SchoolDance {
    static List<Integer>[] adj;
    static int[] pairU, pairV, dist;
    static int NIL = 0;
    
    static boolean bfs(int n) {
        Queue<Integer> q = new LinkedList<>();
        for (int u = 1; u <= n; u++) {
            if (pairU[u] == NIL) {
                dist[u] = 0;
                q.add(u);
            } else {
                dist[u] = Integer.MAX_VALUE;
            }
        }
        dist[NIL] = Integer.MAX_VALUE;
        
        while (!q.isEmpty()) {
            int u = q.poll();
            if (dist[u] < dist[NIL]) {
                for (int v : adj[u]) {
                    if (dist[pairV[v]] == Integer.MAX_VALUE) {
                        dist[pairV[v]] = dist[u] + 1;
                        q.add(pairV[v]);
                    }
                }
            }
        }
        return dist[NIL] != Integer.MAX_VALUE;
    }
    
    static boolean dfs(int u) {
        if (u != NIL) {
            for (int v : adj[u]) {
                if (dist[pairV[v]] == dist[u] + 1) {
                    if (dfs(pairV[v])) {
                        pairU[u] = v;
                        pairV[v] = u;
                        return true;
                    }
                }
            }
            dist[u] = Integer.MAX_VALUE;
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
        }
        
        pairU = new int[n+1];
        pairV = new int[m+1];
        dist = new int[n+1];
        
        int result = 0;
        while (bfs(n)) {
            for (int u = 1; u <= n; u++) {
                if (pairU[u] == NIL && dfs(u)) {
                    result++;
                }
            }
        }
        
        pw.println(result);
        for (int u = 1; u <= n; u++) {
            if (pairU[u] != NIL) {
                pw.println(u + " " + pairU[u]);
            }
        }
        pw.flush();
    }
}
