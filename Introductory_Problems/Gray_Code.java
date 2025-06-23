import java.io.*;
import java.util.*;
 
public class Gray_Code {
    public static void main(String[] args) throws IOException {
        
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        
        List<String> grayCode = generateGrayCode(n);
        
        for (String code : grayCode) {
            out.println(code);
        }

	out.flush();
    }
    
    private static List<String> generateGrayCode(int n) {
        if (n == 1) {
            return Arrays.asList("0", "1");
        }
        
        // Recursively generate Gray code for n-1
        List<String> smallerGrayCode = generateGrayCode(n - 1);
        List<String> grayCode = new ArrayList<>();
        
        // Prepend '0' to all codes from smallerGrayCode
        for (String code : smallerGrayCode) {
            grayCode.add("0" + code);
        }
        
        // Prepend '1' to all codes from smallerGrayCode in reverse order
        for (int i = smallerGrayCode.size() - 1; i >= 0; i--) {
            grayCode.add("1" + smallerGrayCode.get(i));
        }
        
        return grayCode;
    }
}
