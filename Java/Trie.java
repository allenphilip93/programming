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

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.addPattern("allen");
        trie.addPattern("all");
        trie.addPattern("is");
        trie.addPattern("super");
        trie.addPattern("cool");
        trie.addPattern("cold");
        // trie.print(trie.root, "");
        trie.match("allen was going home super early but it is a cold day and all he could think of is cool wind allways and superrrr cool");
    }
}