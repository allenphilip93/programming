import java.util.Arrays;

public class KnuthMorrisPratt {

    private int[] build(String pattern) {
        int[] boundArray = new int[pattern.length()];
        int prefix = 0;
        for(int index=1; index < pattern.length(); index++) {
            if (pattern.charAt(index) == pattern.charAt(prefix)) {
                prefix++;
                boundArray[index] = prefix;
            } else {
                prefix = 0;
            }
        }
        System.out.println("Bound array : " + Arrays.toString(boundArray));
        return boundArray;
    }

    public void match(String text, String pattern) {
        int[] bound = build(pattern);
        int patternIdx = 0;
        for(int index=0; index < text.length(); index++) {
            if (text.charAt(index) == pattern.charAt(patternIdx)) {
                patternIdx++;
                if (patternIdx == pattern.length()) {
                    System.out.println("Match found at index : " + (index - pattern.length() + 1));
                    patternIdx = bound[patternIdx-1];
                }
            } else {
                patternIdx = bound[patternIdx];
            }
        }
    }
    public static void main(String[] args) {
        KnuthMorrisPratt matcher = new KnuthMorrisPratt();
        matcher.match("hallah world this is alla philip jalla!", "alla");
    }
}