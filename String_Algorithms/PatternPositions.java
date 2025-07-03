import java.io.*;
import java.util.*;

public class PatternPositions {
    static class Node {
        int[] next = new int[26];
        int link = -1;
        List<Integer> output = new ArrayList<>();
        Node() {
            Arrays.fill(next, -1);
        }
    }

    static List<Node> trie = new ArrayList<>();
    static List<List<Integer>> patternIdsAtNode = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        int k = Integer.parseInt(br.readLine());

        trie.add(new Node());
        patternIdsAtNode.add(new ArrayList<>());

        String[] patterns = new String[k];
        for (int i = 0; i < k; i++) {
            patterns[i] = br.readLine();
            insert(patterns[i], i);
        }

        buildLinks();

        int[] ans = new int[k];
        Arrays.fill(ans, -1);
        search(text, ans, patterns);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            sb.append(ans[i]).append("\n");
        }
        System.out.print(sb);
    }

    static void insert(String s, int id) {
        int node = 0;
        for (char c : s.toCharArray()) {
            int x = c - 'a';
            if (trie.get(node).next[x] == -1) {
                trie.add(new Node());
                patternIdsAtNode.add(new ArrayList<>());
                trie.get(node).next[x] = trie.size() - 1;
            }
            node = trie.get(node).next[x];
        }
        patternIdsAtNode.get(node).add(id);
    }

    static void buildLinks() {
        Queue<Integer> q = new LinkedList<>();
        for (int c = 0; c < 26; c++) {
            if (trie.get(0).next[c] != -1) {
                trie.get(trie.get(0).next[c]).link = 0;
                q.add(trie.get(0).next[c]);
            } else {
                trie.get(0).next[c] = 0;
            }
        }

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int c = 0; c < 26; c++) {
                int v = trie.get(u).next[c];
                if (v != -1) {
                    int f = trie.get(u).link;
                    while (trie.get(f).next[c] == -1) {
                        f = trie.get(f).link;
                    }
                    trie.get(v).link = trie.get(f).next[c];
                    q.add(v);
                } else {
                    trie.get(u).next[c] = trie.get(trie.get(u).link).next[c];
                }
            }
        }
    }

    static void search(String s, int[] ans, String[] patterns) {
        int node = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            node = trie.get(node).next[c - 'a'];

            int temp = node;
            while (temp != 0) {
                for (int id : patternIdsAtNode.get(temp)) {
                    if (ans[id] == -1) {
                        ans[id] = i - patterns[id].length() + 2; // 1-indexed
                    }
                }
                temp = trie.get(temp).link;
            }
        }
    }
}
