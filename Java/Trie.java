import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Trie {

    Node root;
    int maxdepth = 0;

    private class Node {
        Map<Character, Node> children;
        Character data;

        public Node(Character data) {
            this.data = data;
            this.children = new HashMap<>();
        }
    }

    public Trie() {
        this.root = new Node('&');
    }

    public void addPattern(String pattern) {
        Node curr = root;
        for (int index=0; index < pattern.length(); index++) {
            Character c = pattern.charAt(index);
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new Node(c));
            }
            curr = curr.children.get(c);
        }
        curr.children.put('$', new Node('$'));
        maxdepth = Math.max(maxdepth, pattern.length());
        // System.out.println("Added pattern " + pattern);
    }

    public void print(Node node, String s) {
        if (node.data == '$') {
            System.out.println("Pattern : " + s);
        } else {
            for (Character c : node.children.keySet()) {
                print(node.children.get(c), s + c);
            }
        }
    }

    public void match(String text) {
        for (int index=0; index < text.length(); index++) {
            checkPatternAt(text, index);
        }
    }

    private void checkPatternAt(String text, int index) {
        Node curr = root;
        String pattern = "";
        while (index < text.length()) {
            pattern = pattern + text.charAt(index);
            if (curr.children.containsKey(text.charAt(index))) {
                curr = curr.children.get(text.charAt(index));
                if (curr.children.containsKey('$')) {
                    System.out.println("Match found for \"" + pattern + "\" at index " + index);
                }
            } else {
                break;
            }
            index++;
        }
    }

    private void findChildren(Node node, String s) {
        if (node.data == '$') {
            System.out.println("Match found : " + s);
        } else {
            for (Character c : node.children.keySet()) {
                findChildren(node.children.get(c), s + c);
            }
        }
    }

    // Like the autocomplete feature
    public void prefixMatch(String text) {
        System.out.println("Searching for prefix matches..");
        Node curr = root;
        String pattern = "";
        int index = 0;
        while (index < text.length()) {
            pattern = pattern + text.charAt(index);
            if (curr.children.containsKey(text.charAt(index))) {
                curr = curr.children.get(text.charAt(index));
            } else {
                break;
            }
            index++;
        }
        findChildren(curr, pattern);
    }

    // More like the conventional autocorrect and all
    public void approxMatch(String text, int maxErrors) {
        System.out.println("Searching for approximate matches upto " + maxErrors + " errors..");
        approxMatch(root, text, "", maxErrors);
    }

    private void approxMatch(Node node, String text, String pattern, int k) {
        // System.out.println("Root : " + node.data + " | Text : " + text + " | Errors : " + k);
        if (node.children.containsKey('$')) {
            System.out.println("Match Found at dist " + k + " : " + pattern);
            // return;
        }
        if (text.length() > 0 && k >= 0) {
            for (Character c : node.children.keySet()) {
                if (node.children.containsKey(text.charAt(0))) {
                    approxMatch(node.children.get(c), text.substring(1), pattern + c, k);
                } else if (k > 0) {
                    approxMatch(node.children.get(c), text.substring(0), pattern + c, k-1); // extra character
                    approxMatch(node.children.get(c), text.substring(1), pattern + c, k-1); // misspelled character
                }
            }
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.addPattern("allen");
        trie.addPattern("all");
        // trie.addPattern("issakka");
        trie.addPattern("super");
        trie.addPattern("cool");
        trie.addPattern("cold");
        // trie.print(trie.root, "");
        // trie.match("allen was going home super early but it is a cold day and all he could think of is cool wind allways and superrrr cool");
        trie.prefixMatch("al");
        trie.prefixMatch("co");
        trie.approxMatch("allan", 3);
        trie.approxMatch("could", 3);
    }
}