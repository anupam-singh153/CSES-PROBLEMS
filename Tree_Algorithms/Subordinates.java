import java.io.*;
import java.util.*;

public class Subordinates {
    static List<Integer>[] adj;
    static int[] subCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        adj = new ArrayList[n + 1];
        subCount = new int[n + 1];

        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 2; i <= n; i++) {
            int p = Integer.parseInt(st.nextToken());
            adj[p].add(i);
        }

        dfs(1);

        for (int i = 1; i <= n; i++) {
            pw.print(subCount[i] + " ");
        }
        pw.println();
        pw.flush();
    }

    static int dfs(int node) {
        int cnt = 0;
        for (int child : adj[node]) {
            cnt += dfs(child) + 1;
        }
        subCount[node] = cnt;
        return cnt;
    }
}
