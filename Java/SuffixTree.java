import java.util.HashMap;
import java.util.Map;

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
        for (int outer_index=0; outer_index < text.length(); outer_index++) {
            Node curr = root;
            for (int index=outer_index; index < text.length(); index++) {
                Character c = text.charAt(index);
                if (!curr.children.containsKey(c)) {
                    curr.children.put(c, new Node(c));
                }
                curr = curr.children.get(c);
            }
            curr.children.put('$', new Node('$'));
        }
        maxdepth = Math.max(maxdepth, text.length());
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

    public static void main(String[] args) {
        SuffixTree suffixTree = new SuffixTree();
        // suffixTree.addText("allen was going home super early but it is a cold day and all he could think of is cool wind allways and superrrr cool");
        suffixTree.addText("Hello world!");
        suffixTree.print(suffixTree.root, "");
        suffixTree.match("world");
    }
}