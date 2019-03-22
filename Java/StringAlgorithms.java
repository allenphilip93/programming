public class StringAlgorithms {

  public static String reverse(String str) {
    char[] array = str.toCharArray();
    for (int index=0; index < array.length/2; index++) {
      array[index] = (char) (array[index] ^ array[array.length-1-index]);
      array[array.length-1-index] = (char) (array[index] ^ array[array.length-1-index]);
      array[index] = (char) (array[index] ^ array[array.length-1-index]);
    }
    System.out.println("Reversed String : " + String.valueOf(array));
    return String.valueOf(array);
  }

  public static void reverseWords(String line) {
    StringBuilder sb = new StringBuilder();
    for (String s : line.split("\\s")) {
      sb.append(reverse(s));
      sb.append(" ");
    }
    System.out.println("Reversed line : " + sb.toString());
  }

  public static void allPermutations(String str) {
    allPermuations("", str);
  }

  private static void allPermuations(String perm, String subStr) {
    if (subStr.length() == 0) {
      System.out.println("Permutation : " + perm);
    }
    for (int index=0; index < subStr.length(); index++) {
      String sub = getSubString(subStr, 0, index) + getSubString(subStr, index+1, subStr.length());
      allPermuations(perm + subStr.charAt(index), sub);
    }
  }

  private static String getSubString(String str, int start, int end) {
    if (start < 0 || end > str.length() || start >= end) {
      return "";
    } else {
      return str.substring(start, end);
    }
  }

  public static void allPermCombinations(String str) {
    for (int k=1; k <= str.length(); k++) {
      permCombinations(str, k);
    }
  }

  public static void permCombinations(String str, int k) {
    permCombinations("", str, k);
  }

  private static void permCombinations(String comb, String subStr, int k) {
    if (k == 0) {
      System.out.println("Combination : " + comb);
    } else {
      for (int index=0; index < subStr.length(); index++) {
        String sub = getSubString(subStr, 0, index) + getSubString(subStr, index+1, subStr.length());
        permCombinations(comb + subStr.charAt(index), sub, k-1);
      }
    }
  }

  public static void allCombinations(String str) {
    for (int k=1; k <= str.length(); k++) {
      allCombinations(str, k);
    }
  }

  public static void allCombinations(String str, int k) {
    combinations("", str, k);
  }

  private static void combinations(String comb, String subStr, int k) {
    if (k == 0) {
      System.out.println("Combination : " + comb);
    } else {
      for (int index=0; index < subStr.length(); index++) {
        String sub = getSubString(subStr, index+1, subStr.length());
        combinations(comb + subStr.charAt(index), sub, k-1);
      }
    }
  }

  public void strstr() {
    // TODO - frequent but dont know what this is
  }

  public static void recursivelyRemoveDuplicates(String str) {
    // can be done with stack recursively also
    boolean hasDulplicates = false;
    StringBuilder sb = new StringBuilder();
    for (int index=0; index < str.length(); index++) {
        if (checkNeighbours(str, index)) {
          hasDulplicates = true;
          continue;
        }
        sb.append(str.charAt(index));
    }
    if (hasDulplicates) {
      recursivelyRemoveDuplicates(sb.toString());
    } else {
      System.out.println("Processed String : " + sb);
    }
  }

  private static boolean checkNeighbours(String str, int index) {
    int leftIdx = ((index-1) >= 0) ? (index-1) : 0;
    int rightIdx = ((index+1) < str.length()) ? (index+1) : (str.length()-1);
    if (str.length() < 2)
      return false;
    if (leftIdx == index)
      return str.charAt(index) == str.charAt(rightIdx);
    else if (rightIdx == index)
      return str.charAt(index) == str.charAt(leftIdx);
    return (str.charAt(index) == str.charAt(leftIdx) || str.charAt(index) == str.charAt(rightIdx));
  }

  public void minContainingWindow(String text, String pattern) {

  }

  public void interleave(String str1, String str2) {

  }

  public void rowColMatch(int[][] array) {

  }

  public void replace(String str, String find, String replace) {

  }

  public void encodeString(String str) {

  }

  public void captureXOs(Character[][] c) {
    
  }


  public static void main(String[] args) {
    StringAlgorithms.reverse("dracula");
    StringAlgorithms.reverseWords("mridula sounds lot like dracula");
    StringAlgorithms.allPermutations("cat");
    StringAlgorithms.allCombinations("abc");
    StringAlgorithms.recursivelyRemoveDuplicates("mississippi");
    // StringAlgorithms.recursivelyRemoveDuplicates("allen");
  }
}
