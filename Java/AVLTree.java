public class AVLTree <T extends Number & Comparable<? super T>> extends BinarySearchTree<T> {

    public void isAVL() {
        
    }

    public void addNode(T elem) {
        root = addNode(root, elem);
    }

    // Add node - AVL style
    private Node addNode(Node node, T elem) {
        if (node == null) {
            return new Node(elem);
        }
        if (node.data.compareTo(elem) >= 0) {
            // left add
            node.left = addNode(node.left, elem);
            node.bf = node.bf - 1;
            if (node.bf < -1)
                return rightRotate(node);
        } else {
            // right add
            node.right = addNode(node.right, elem);
            node.bf = node.bf + 1;
            if (node.bf > 1)
                return leftRotate(node);
        }
        return node;
    }

    // Incoming node is left heavy
    private Node rightRotate(Node node) {
        if (node.left.bf > 0) {
            // Double Rot Needed
            Node leftNode = node.left;
            Node leftRightNode = leftNode.right;
            node.left = leftRightNode;
            leftNode.right = leftRightNode.left;
            leftRightNode.left = leftNode;       
            leftRightNode.bf = leftRightNode.bf - 1;
            leftNode.bf = leftNode.bf - 1;
        }
        // Linearly skewed
        Node leftNode = node.left;
        node.left = leftNode.right;
        leftNode.right = node;
        leftNode.bf = leftNode.bf + 1;
        node.bf = node.bf + 2;
        return leftNode;
    }

    // Incoming node is right heavy
    private Node leftRotate(Node node) {
        if (node.right.bf < 0) {
            // Double Rot Needed
            Node rightNode = node.right;
            Node rightLeftNode = rightNode.left;
            node.right = rightLeftNode;
            rightNode.left = rightLeftNode.right;
            rightLeftNode.right = rightNode;
            rightLeftNode.bf = rightLeftNode.bf + 1;
            rightNode.bf = rightNode.bf + 1;
        }
        // Linearly skewed
        Node rightNode = node.right;
        node.right = rightNode.left;
        rightNode.left = node;
        rightNode.bf = rightNode.bf - 1;
        node.bf = node.bf - 2;
        return rightNode;
    }

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.addNode(5);
        tree.addNode(12);
        tree.addNode(8);
        tree.addNode(21);
        tree.addNode(30);
        tree.addNode(4);
        tree.addNode(90);
        tree.addNode(66);
        System.out.println("OUTPUT:");
        System.out.println("========");
        tree.printTree();
    }
}