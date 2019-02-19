import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StringPerm {
    public static void generate(String s, String o, int limit) {
        if (s.length() > 0 && o.length() < limit) {
            for (int idx=0; idx < s.length(); idx++) {
                StringBuilder sb = new StringBuilder(s);
                generate(sb.deleteCharAt(idx).toString(), o + s.charAt(idx), limit);
            }
        } else {
            System.out.println(o);
        }
    }

    public static void main(String[] args) {
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Enter input string : ");
            String input = buff.readLine().trim();
            System.out.print("Enter size of substring : ");
            int size = Integer.valueOf(buff.readLine().trim());
            generate(input, "", size);
        } catch (Exception e) {
            System.out.println("ERROR!");
        }
    }
}