import java.io.*;
import java.util.*;

public class PalindromeQueries {
    static final int MOD = (int)1e9 + 7;
    static final int BASE = 911;
    static int[] pow;

    static class SegmentTree {
        int n;
        long[] tree;

        SegmentTree(char[] s) {
            n = s.length;
            tree = new long[4 * n];
            build(1, 0, n - 1, s);
        }

        void build(int v, int tl, int tr, char[] s) {
            if (tl == tr) {
                tree[v] = s[tl] - 'a' + 1;
            } else {
                int tm = (tl + tr) / 2;
                build(v * 2, tl, tm, s);
                build(v * 2 + 1, tm + 1, tr, s);
                tree[v] = (tree[v * 2] * pow[tr - tm] + tree[v * 2 + 1]) % MOD;
            }
        }

        void update(int v, int tl, int tr, int pos, int val) {
            if (tl == tr) {
                tree[v] = val;
            } else {
                int tm = (tl + tr) / 2;
                if (pos <= tm) update(v * 2, tl, tm, pos, val);
                else update(v * 2 + 1, tm + 1, tr, pos, val);
                tree[v] = (tree[v * 2] * pow[tr - tm] + tree[v * 2 + 1]) % MOD;
            }
        }

        long query(int v, int tl, int tr, int l, int r) {
            if (l > r) return 0;
            if (l == tl && r == tr) {
                return tree[v];
            }
            int tm = (tl + tr) / 2;
            long left = query(v * 2, tl, tm, l, Math.min(r, tm));
            long right = query(v * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r);
            long res = (left * pow[Math.max(0, r - Math.max(l, tm + 1) + 1)] + right) % MOD;
            return res;
        }

        void update(int pos, char c) {
            update(1, 0, n - 1, pos, c - 'a' + 1);
        }

        long query(int l, int r) {
            return query(1, 0, n - 1, l, r);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");
        int n = Integer.parseInt(tokens[0]);
        int m = Integer.parseInt(tokens[1]);
        char[] s = br.readLine().toCharArray();

        pow = new int[n + 1];
        pow[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow[i] = (int)((long)pow[i - 1] * BASE % MOD);
        }

        SegmentTree st = new SegmentTree(s);
        char[] rs = new char[n];
        for (int i = 0; i < n; i++) rs[i] = s[n - 1 - i];
        SegmentTree rst = new SegmentTree(rs);

        StringBuilder sb = new StringBuilder();
        for (int q = 0; q < m; q++) {
            String[] parts = br.readLine().split(" ");
            int type = Integer.parseInt(parts[0]);
            if (type == 1) {
                int k = Integer.parseInt(parts[1]) - 1;
                char c = parts[2].charAt(0);
                st.update(k, c);
                rst.update(n - 1 - k, c);
            } else {
                int a = Integer.parseInt(parts[1]) - 1;
                int b = Integer.parseInt(parts[2]) - 1;
                long h1 = st.query(a, b);
                long h2 = rst.query(n - 1 - b, n - 1 - a);
                if (h1 == h2) {
                    sb.append("YES\n");
                } else {
                    sb.append("NO\n");
                }
            }
        }
        System.out.print(sb);
    }
}
