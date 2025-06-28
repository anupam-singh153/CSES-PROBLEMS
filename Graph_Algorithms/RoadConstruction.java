import java.io.*;
import java.util.*;

public class RoadConstruction {
    static int[] parent;
    static int[] size;
    static int components;
    static int maxSize;
    
    static int find(int u) {
        if (parent[u] != u) {
            parent[u] = find(parent[u]);
        }
        return parent[u];
    }
    
    static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) return;
        
        if (size[u] < size[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        parent[v] = u;
        size[u] += size[v];
        maxSize = Math.max(maxSize, size[u]);
        components--;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        parent = new int[n+1];
        size = new int[n+1];
        components = n;
        maxSize = 1;
        
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
            pw.println(components + " " + maxSize);
        }
        pw.flush();
    }
}
