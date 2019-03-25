import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCoding {

    private Node root;

    private class Node implements Comparable<Node> {
        Node left;
        Node right;
        String text;
        double frequency;
        
        public Node(String text, double frequency) {
            this.text = text;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Node node) {
            return (this.frequency >= node.frequency) ? 1 : -1;
        }
    }

    public void encode(String text) {
        root = null;
        // Find the frequency of all the nodes and add them into a map
        Map<String, Integer> count = new HashMap<>();
        for (int index=0; index < text.length(); index++) {
            String s = text.charAt(index) + "";
            if (count.containsKey(s)) {
                count.put(s, count.get(s)+1);
            } else {
                count.put(s, 1);
            }
        }
        // Add this frequency map into a min heap
        PriorityQueue<Node> minheap = new PriorityQueue<>();
        for (String key : count.keySet()) {
            minheap.offer(new Node(key, count.get(key)));
        }
        // Until the minheap is empty pop out and merge top two
        while (minheap.size() > 2) {
            Node n1 = minheap.remove();
            Node n2 = minheap.remove();
            if (n1 != null && n2 != null) {
                Node nParent = new Node(n1.text + n2.text, n1.frequency + n2.frequency);
                nParent.left = n1;
                nParent.right = n2;
                minheap.add(nParent);
            }
        }
        Node n1 = minheap.remove();
        Node n2 = minheap.remove();
        root = new Node(n1.text + n2.text, n1.frequency + n2.frequency);
        root.left = n1;
        root.right = n2;
        
        // Print the encoding table
        print(root, "");
    }

    private void print(Node node, String res) {
        if (node.text.length() == 1) {
            System.out.println("CHAR " + node.text + " | CODE : " + res);
        } else {
            print(node.left, res + "0");
            print(node.right, res + "1");
        }
    }

    public static void main(String[] args) {
        HuffmanCoding encoder = new HuffmanCoding();
        encoder.encode("aaabcdddef");
    }
}