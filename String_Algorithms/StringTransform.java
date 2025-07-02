import java.io.*;
import java.util.*;

public class StringTransform {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        String bwt = br.readLine().trim();
        String original = inverseBWT(bwt);
        pw.println(original);
        pw.flush();
    }

    private static String inverseBWT(String bwt) {
        int n = bwt.length();
        List<String> table = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            table.add("");
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                table.set(j, bwt.charAt(j) + table.get(j));
            }
            Collections.sort(table);
        }
        for (String s : table) {
            if (s.endsWith("#")) {
                return s.substring(0, s.length() - 1);
            }
        }
        return "";
    }
}