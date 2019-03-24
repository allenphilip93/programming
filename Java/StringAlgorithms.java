import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

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

  public static void minContainingWindow(String text, String pattern) {
      Map<Character, Integer> windowMap = new HashMap<>();
      Map<Character, Integer> patternMap = new HashMap<>();
      // Update the required patternMap
      for (int index=0; index < pattern.length(); index++) {
          Character c = pattern.charAt(index);
          if (!patternMap.containsKey(c)) {
              patternMap.put(c, 1);
          } else {
              patternMap.put(c, patternMap.get(c)+1);
          }
      }
      // Update the window map assuming max window size of length of text
      for (int index=0; index < text.length(); index++) {
          Character c = text.charAt(index);
          if (!windowMap.containsKey(c)) {
              windowMap.put(c, 1);
          } else {
              windowMap.put(c, windowMap.get(c)+1);
          }
      }
      // Shrink from front
      int front=0;
      for (; front < text.length(); front++) {
          Character c = text.charAt(front);
          if (patternMap.containsKey(c) && windowMap.get(c) == patternMap.get(c)) {
              break;
          }
          if (patternMap.containsKey(c)) {
              windowMap.put(c, windowMap.get(c)-1);
          }
      }
      // Shrink from back
      int rear=text.length()-1;
      for (; rear > front; rear--) {
          Character c = text.charAt(rear);
          if (patternMap.containsKey(c) && windowMap.get(c) == patternMap.get(c)) {
              break;
          }
          if (patternMap.containsKey(c)) {
              windowMap.put(c, windowMap.get(c)-1);
          }
      }
      System.out.println("Min Enclosing Window Size : " + (rear - front + 1));
      System.out.println("Min Enclosing Window : " + text.substring(front, rear + 1));
  }

  // Assume that its a n x n input array
  public static void towDimWordSearch(char[][] array, String pattern) {
    Map<Character, Integer> patternMap = new HashMap<>();
    // Update the required patternMap
    for (int index=0; index < pattern.length(); index++) {
        Character c = pattern.charAt(index);
        if (!patternMap.containsKey(c)) {
            patternMap.put(c, 1);
        } else {
            patternMap.put(c, patternMap.get(c)+1);
        }
    }
    // find a valid starting point
    // for each match we will call the subsequent match finding block until we find a match
    int x=0, y=0;
    boolean found = false;
    int[][] history = new int[array.length][array.length];
    for (; x < array.length; x++) {
        for (y = x; y < array.length; y++) {
            history[x][y] = 1;
            if (patternMap.containsKey(array[x][y])) {
                Map<Character, Integer> patternCopy = new HashMap<Character, Integer>();
                // TODO - similarly need to clone and send history too
                patternCopy.putAll(patternMap);
                found = findTwoDimMatch(x, y, array.length, history, patternCopy, array);
                if (found)
                    break;
            }
        }
        if (found)
            break;
    }
  }

  private static boolean findTwoDimMatch(int x, int y, int length, int[][] history, Map<Character, Integer> patternMap, char[][] array) {
    Deque<List<Integer>> queue = new ArrayDeque<>();
    List<Integer> tuple = new ArrayList<>();
    tuple.add(x); tuple.add(y);
    queue.offer(tuple);
    while (queue.size() > 0) {
        List<Integer> pair = queue.remove();
        x = pair.get(0);
        y = pair.get(1);
        history[x][y] = 1;
        if (patternMap.containsKey(array[x][y])) {
            System.out.println("X : " + x + " Y : " + y + " CHAR : " + array[x][y]);
            int count = patternMap.get(array[x][y]) - 1;
            if (count == 0) {
                patternMap.remove(array[x][y]);
            } else {
                patternMap.put(array[x][y], count);
            }
        }
        // check for match
        if (patternMap.size() == 0) {
            break;
        }
        // offer all neighbours of current pos
        offerAllImmediatePos(x, y, array.length, queue, history, patternMap, array);
    }
    if (patternMap.size() > 0){
        System.out.println("Match not found!");
    } else {
        System.out.println("Match Found!");
    }
    return patternMap.size() == 0;
  }

  private static void offerAllImmediatePos(int x, int y, int length, Deque<List<Integer>> queue, int[][] history, Map<Character, Integer> patternMap, char[][] array) {
    if (x-1 >= 0 && y-1 >= 0 && history[x-1][y-1] == 0 && patternMap.containsKey(array[x-1][y-1])) {
        List<Integer> tuple = new ArrayList<>();
        tuple.add(x-1);
        tuple.add(y-1);
        queue.offer(tuple);
    }
    if (x-1 >= 0 && history[x-1][y] == 0 && patternMap.containsKey(array[x-1][y])) {
        List<Integer> tuple = new ArrayList<>();
        tuple.add(x-1);
        tuple.add(y);
        queue.offer(tuple);
    }
    if (y-1 >= 0 && history[x][y-1] == 0 && patternMap.containsKey(array[x][y-1])) {
        List<Integer> tuple = new ArrayList<>();
        tuple.add(x);
        tuple.add(y-1);
        queue.offer(tuple);
    }
    if (x-1 >= 0 && y+1 < length && history[x-1][y+1] == 0 && patternMap.containsKey(array[x-1][y+1])) {
        List<Integer> tuple = new ArrayList<>();
        tuple.add(x-1);
        tuple.add(y+1);
        queue.offer(tuple);
    }
    if (y+1 < length && history[x][y+1] == 0 && patternMap.containsKey(array[x][y+1])) {
        List<Integer> tuple = new ArrayList<>();
        tuple.add(x);
        tuple.add(y+1);
        queue.offer(tuple);
    }
    if (x+1 < length && y+1 < length && history[x+1][y+1] == 0 && patternMap.containsKey(array[x+1][y+1])) {
        List<Integer> tuple = new ArrayList<>();
        tuple.add(x+1);
        tuple.add(y+1);
        queue.offer(tuple);
    }
    if (x+1 < length && history[x+1][y] == 0 && patternMap.containsKey(array[x+1][y])) {
        List<Integer> tuple = new ArrayList<>();
        tuple.add(x+1);
        tuple.add(y);
        queue.offer(tuple);
    }
    if (x+1 < length && y-1 >= 0 && history[x+1][y-1] == 0 && patternMap.containsKey(array[x+1][y-1])) {
        List<Integer> tuple = new ArrayList<>();
        tuple.add(x+1);
        tuple.add(y-1);
        queue.offer(tuple);
    }
  }

  public static void interleave(String str1, String str2) {
    interleave(str1, str2, "");
  }

  private static void interleave(String str1, String str2, String res) {
    if (str1.length() == 0 && str2.length() > 0) {
        res = res + str2;
        System.out.println("Interleaved String : " + res);
    } else if (str1.length() > 0 && str2.length() == 0) {
        res = res + str1;
        System.out.println("Interleaved String : " + res);
    } else if (str1.length() > 0 && str2.length() > 0) {
        int startIndex = 0;
        int endIndex = str1.length();
            for (int index=startIndex; index <= endIndex; index++) {
                interleave(
                    getSubString(str1, index, str1.length()), 
                    getSubString(str2, 1, str2.length()),
                    res + getSubString(str1, 0, index) + str2.charAt(0)
                );
            }
    } else {
        System.out.println("Interleaved String : " + res);
    }
  }

  public static void rowColMatch(int[][] array) {
    // use tries of rows and compare the columns O(R+C)
  }

  public static void replace(String str, String find, String replace) {
    // Start doing from the end so that the no of overwrites is minimal
  }

  public static void encodeString(String str) {
    // Identify if encoding is necessary in O(n)
    char prev = str.charAt(0), curr = str.charAt(0);
    int count = 0;
    for (int index=1; index < str.length(); index++) {
        curr = str.charAt(index);
        if (curr == prev) {
            count++;
        } else {
            prev = curr;
        }
    }
    if ((str.length() - count) <= count) {
        // Encoding is useful
        StringBuilder sb = new StringBuilder();
        prev = str.charAt(0);
        curr = str.charAt(0);
        count = 1;
        for (int index=1; index < str.length(); index++) {
            curr = str.charAt(index);
            if (curr == prev) {
                count++;
            } else {
                sb.append(prev);
                sb.append(count);
                prev = curr;
                count = 1;
            }
        }
        if (prev == curr) {
            sb.append(curr);
            sb.append(count);
        }
        System.out.println("Encoded String : " + sb.toString());
    } else {
        // Encoding is not useful
        System.out.println("Unencoded String : " + str);
    }
  }

  public static void captureXOs(Character[][] c) {

  }


  public static void main(String[] args) {
    // StringAlgorithms.reverse("dracula");
    // StringAlgorithms.reverseWords("mridula sounds lot like dracula");
    // StringAlgorithms.allPermutations("cat");
    // StringAlgorithms.allCombinations("abc");
    // StringAlgorithms.recursivelyRemoveDuplicates("mississippi");
    // StringAlgorithms.recursivelyRemoveDuplicates("allen");
    StringAlgorithms.minContainingWindow("ABBACBAA", "AAB");
    // StringAlgorithms.towDimWordSearch(
    //     new char[][]{
    //         {'A', 'C', 'P', 'R', 'C'},
    //         {'X', 'S', 'O', 'P', 'C'},
    //         {'V', 'O', 'V', 'N', 'I'},
    //         {'W', 'G', 'F', 'M', 'N'},
    //         {'T', 'A', 'Q', 'I', 'Y'}
    //     }, "MICROSOFT");
    // StringAlgorithms.interleave("ABC", "DEF");
    StringAlgorithms.encodeString("PHILIP");
  }
}
