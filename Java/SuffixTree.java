import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class SuffixTree {

    private Node root;
    private int maxdepth = 0;

    private class Node {
        Character data;
        Map<Character, Node> children;

        public Node(Character data) {
            this.data = data;
            this.children = new HashMap<>();
        }
    }

    public SuffixTree() {
        this.root = new Node('&');
    }

    public void addText(String text) {
      addText(text, '$');
    }

    public void addText(String text, Character ender) {
        for (int outer_index=0; outer_index < text.length(); outer_index++) {
            Node curr = root;
            for (int index=outer_index; index < text.length(); index++) {
                Character c = text.charAt(index);
                if (!curr.children.containsKey(c)) {
                    curr.children.put(c, new Node(c));
                }
                curr = curr.children.get(c);
            }
            curr.children.put(ender, new Node(ender));
        }
        maxdepth = Math.max(maxdepth, text.length());
    }

    public void print(Node node, String s) {
        if (node.data == '$' || node.data == '#') {
            System.out.println("Pattern : " + s);
        } else {
            for (Character c : node.children.keySet()) {
                print(node.children.get(c), s + c);
            }
        }
    }

    public void match(String pattern) {
        checkText(pattern);
    }

    private void checkText(String pattern) {
        Node curr = root;
        int index = 0;
        for (; index < pattern.length(); index++) {
            if (curr.children.containsKey(pattern.charAt(index))) {
                System.out.println("Found character " + pattern.charAt(index));
                curr = curr.children.get(pattern.charAt(index));
            } else {
                break;
            }
        }
        if (index == pattern.length()) {
            System.out.println("Match found for \"" + pattern + "\"");
        }
    }

    public void findLongestCommonSubstring(String t1, String t2) {
      root = new Node('&');
      addText(t1, '$');
      addText(t2, '#');
      maxStr = "";
      findLCS(root, "");
      System.out.println("Longest Common Substring : " + maxStr);
    }

    String maxStr = "";
    private Set<Character> findLCS(Node node, String s) {
      Set<Character> hasSeen = new HashSet<>();
      if (node.data == '#' || node.data == '$') {
        hasSeen.add(node.data);
        return hasSeen;
      }
      for (Character c : node.children.keySet()) {
        // System.out.println("Parsed Character : " + c);
        Set<Character> res = findLCS(node.children.get(c), s + c);
        hasSeen.addAll(res);
        // if ((node.children.keySet().contains('#') && hasSeen == '$') ||
        //   (node.children.keySet().contains('$') && hasSeen == '#')) {
        //     System.out.println("Common Substring : " + s);
        // }
      }
      if (hasSeen.contains('$') && hasSeen.contains('#') && !s.isEmpty()) {
          System.out.println("Common Substring : " + s);
          if (s.length() > maxStr.length())
            maxStr = s;
      }
      return hasSeen;
    }

    public void findLongestPalindrome(String text) {
      root = new Node('&');
      StringBuilder sb = new StringBuilder(text);
      addText(text, '$');
      addText(sb.reverse().toString(), '#');
      maxStr = "";
      findLCS(root, "");
      System.out.println("Longest Palindrome : " + maxStr);
    }

    public void findLongestRepeatedSubstring(String text) {
      root = new Node('&');
      addText(text, '$');
      maxStr = "";
      // print(root, "");
      findLRS(root, "", 0);
      System.out.println("Longest Repeated Substring : " + maxStr);
    }

    private int findLRS(Node node, String s, int depth) {
      if (node.data == '$') {
        return depth-1;
      }
      if (node.children.keySet().size() > 1 && !s.isEmpty() && node.data != '&') {
          System.out.println("Common Substring : " + s);
          if (s.length() > maxStr.length())
            maxStr = s;
      }
      for (Character c : node.children.keySet()) {
        int maxdepth = findLRS(node.children.get(c), s + c, depth+1);
      }
      return maxdepth;
    }



    public static void main(String[] args) {
        SuffixTree suffixTree = new SuffixTree();
        // suffixTree.addText("allen was going home super early but it is a cold day and all he could think of is cool wind allways and superrrr cool");
        // suffixTree.addText("Hello world!");
        // suffixTree.print(suffixTree.root, "");
        // suffixTree.match("world");
        // suffixTree.findLongestCommonSubstring("ananamamapa", "xyananamapa");
        // suffixTree.findLongestPalindrome("forgeeksskeegfor");
        suffixTree.findLongestRepeatedSubstring("panamanamanamap");
        // suffixTree.findLongestRepeatedSubstring("pallenhalle");
        // suffixTree.findLongestRepeatedSubstring("pananx");
    }
}
