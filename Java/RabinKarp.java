public class RabinKarp {

    private int computeHash(String key) {
        int hash = 0;
        for (int index=key.length()-1; index >= 0; index--) {
            hash += key.charAt(index);
        }
        return hash;
    }

    private int shiftHash(int hash, Character prevChar, Character nextChar) {
        return hash - prevChar + nextChar;
    }

    public void match(String text, String pattern) {
        int patternHash = computeHash(pattern);
        Integer rollingHash = null;
        boolean hasMatch = false;
        for (int startIdx=0; startIdx <= (text.length() - pattern.length()); startIdx++) {
            if (rollingHash == null) {
                rollingHash = computeHash(text.substring(startIdx, startIdx + pattern.length()));
            } else {
                rollingHash = shiftHash(rollingHash, text.charAt(startIdx-1), 
                    text.charAt(startIdx+pattern.length()-1));
            }
            if (rollingHash == patternHash) {
                if (text.substring(startIdx, startIdx + pattern.length()).equals(pattern)) {
                    System.out.println("Match found at index : " + startIdx);
                    hasMatch = true;
                }
            }
        }
        if (!hasMatch) {
            System.out.println("No matches found!");
        }
    }

    public static void main(String[] args) {
        RabinKarp matcher = new RabinKarp();
        matcher.match("Hello world this is allen philip!", "allen");
    }
}