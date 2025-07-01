import java.io.*;
import java.util.*;

public class DistinctColors {
    static List<Integer>[] adj;
    static int[] color, ans, subSize, euler, first, last;
    static int n, t = 0;
    static int MAXN;
    static int[] freq, colorFreq;
    static int BLOCK;

    static class Query implements Comparable<Query> {
        int l, r, idx;

        public Query(int l, int r, int idx) {
            this.l = l;
            this.r = r;
            this.idx = idx;
        }

        public int compareTo(Query o) {
            if (l / BLOCK != o.l / BLOCK)
                return l / BLOCK - o.l / BLOCK;
            return r - o.r;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        n = Integer.parseInt(br.readLine());
        color = new int[n + 1];
        adj = new ArrayList[n + 1];
        ans = new int[n + 1];
        subSize = new int[n + 1];
        euler = new int[2 * n + 2];
        first = new int[n + 1];
        last = new int[n + 1];

        MAXN = 2 * n + 2;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) color[i] = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        dfs(1, 0);

        BLOCK = (int) Math.sqrt(MAXN);
        Query[] queries = new Query[n];
        for (int i = 1; i <= n; i++) {
            queries[i - 1] = new Query(first[i], last[i], i);
        }

        Arrays.sort(queries);

        freq = new int[MAXN];
        colorFreq = new int[MAXN];
        int res = 0, l = 1, r = 0;

        for (Query q : queries) {
            while (r < q.r) {
                r++;
                if (++colorFreq[color[euler[r]]] == 1) res++;
            }
            while (r > q.r) {
                if (--colorFreq[color[euler[r]]] == 0) res--;
                r--;
            }
            while (l < q.l) {
                if (--colorFreq[color[euler[l]]] == 0) res--;
                l++;
            }
            while (l > q.l) {
                l--;
                if (++colorFreq[color[euler[l]]] == 1) res++;
            }
            ans[q.idx] = res;
        }

        for (int i = 1; i <= n; i++) {
            pw.print(ans[i] + " ");
        }

        pw.println();
        pw.flush();
    }

    static void dfs(int u, int p) {
        first[u] = ++t;
        euler[t] = u;
        for (int v : adj[u]) {
            if (v != p) dfs(v, u);
        }
        last[u] = t;
    }
}
