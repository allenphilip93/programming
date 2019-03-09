public class GenericTree <T extends Number & Comparable<? super T>> {

    public class Node {
        T data;
        Node nextChild;
        Node nextSibling;
        public Node(T data) {
            this.data = data;
        }
    }

    Node root;

    public void addRoot(T elem) {
        root = new Node(elem);
    }

    public void addSibling(Node node, T elem) {
        Node newSibling = new Node(elem);
        Node temp = node.nextSibling;
        newSibling.nextSibling = temp;
        node.nextSibling = newSibling;
    }

    public void addChild(Node node, T elem) {
        Node newChild = new Node(elem);
        Node temp = node.nextChild;
        newChild.nextSibling = temp;
        node.nextChild = newChild;
    }

    public Node findNode(T elem) {
        Node node = null;
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            node = findNode(root, elem);
        }
        return node;
    }

    private Node findNode(Node node, T elem) {
        if (node == null)
            return null;
        if (node.data.compareTo(elem) == 0)
            return node;
        Node res = findNode(node.nextChild, elem);
        if (res == null) {
            res = findNode(node.nextSibling, elem);
            if (res != null) {
                return res;
            }
        }
        return res;
    }

    public void findDepth() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            int depth = findDepth(root);
            System.out.println("Depth of the tree : " + depth);
        }
    }

    private int findDepth(Node node) {
        if (node == null)
            return 0;
        return Math.max(1 + findDepth(node.nextChild), findDepth(node.nextSibling));
    }

    public void print() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            QueueLinkedList<Node> queue = new QueueLinkedList<>();
            queue.enqueue(root);
            int level = 0;
            while (queue.size > 0) {
                String s = "";
                int size = queue.size;
                for (int idx=0; idx < size; idx++) {
                    Node curr = queue.dequeue();
                    s = s + curr.data + " ";
                    Node child = curr.nextChild;
                    while (child != null) {
                        queue.enqueue(child);
                        child = child.nextSibling;
                    }
                }
                System.out.println("Level " + level + " : " + s);
                level++;
            }
        }
    }

    public void levelOrder() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            String s = levelOrder(root);
            System.out.println("Level Order Traverse : " + s);
        }
    }

    private String levelOrder(Node node) {
        if (node == null) 
            return "";
        return node.data + " " + levelOrder(node.nextSibling) + levelOrder(node.nextChild);
    }

    public void findSum() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            int sum = findSum(root);
            System.out.println("Sum of all elements in the tree : " + sum);
        }
    }

    private int findSum(Node node) {
        if (node == null)
            return 0;
        return node.data.intValue() + findSum(node.nextChild) + findSum(node.nextSibling);
    }

    public void preOrder() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            String s = preOrder(root);
            System.out.println("PreOrder Traverse : " + s);
        }
    }

    private String preOrder(Node node) {
        if (node == null)
            return "";
        return node.data + " " + preOrder(node.nextChild) + preOrder(node.nextSibling);
    }

    public void inOrder() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            String s = inOrder(root);
            System.out.println("InOrder Traverse : " + s);
        }
    }

    private String inOrder(Node node) {
        if (node == null)
            return "";
        return inOrder(node.nextChild) + node.data + " " + inOrder(node.nextSibling);
    }

    public void postOrder() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            String s = postOrder(root);
            System.out.println("PostOrder Traverse : " + s);
        }
    }

    private String postOrder(Node node) {
        if (node == null)
            return "";
        return postOrder(node.nextChild) + postOrder(node.nextSibling) + node.data + " ";
    }

    public void checkIsomorphic(GenericTree<T> compTree) {
        if (root == null || compTree.root == null) {
            System.out.println("Tree is empty!");
        } else {
            Boolean isIsomorphic = isIsomorphic(root, compTree.root);
            System.out.println("Are the trees isomorphic : " + isIsomorphic.toString().toUpperCase());
        }
    }

    private boolean isIsomorphic(Node n1, Node n2) {
        if (n1 == null && n2 == null) {
            return true;
        } else if (n1 == null || n2 == null) {
            return false;
        }
        // boolean res = ((n1.nextChild == null) && (n2.nextChild == null)) || ((n1.nextChild != null) && (n2.nextChild != null));
        // res = res && ((n1.nextSibling == null && n2.nextSibling == null) || (n1.nextSibling != null && n2.nextSibling != null));
        return isIsomorphic(n1.nextChild, n2.nextChild) && isIsomorphic(n1.nextSibling, n2.nextSibling);
    }

    public void checkQuasimorphic(GenericTree<T> compTree) {
        if (root == null || compTree.root == null) {
            System.out.println("Tree is empty!");
        } else {
            Boolean isQuasimorphic = isQuasimorphic(root, compTree.root);
            System.out.println("Are the trees quasimorphic : " + isQuasimorphic.toString().toUpperCase());
        }
    }

    private boolean isQuasimorphic(Node n1, Node n2) {
        if (n1 == null && n2 == null) {
            return true;
        } else if (n1 == null || n2 == null) {
            return false;
        }
        return isQuasimorphic(n1.nextChild, n2.nextChild) && isQuasimorphic(n1.nextSibling, n2) && isQuasimorphic(n1, n2.nextSibling);
    }

    public void buildFullTree(int numChildren, int height, T elem) {
        System.out.println("Building a full tree");
        root = buildFullTree(root, numChildren, height, elem);
        print();
    }

    private Node buildFullTree(Node node, int numChildren, int height, T elem) {
        if (node == null)
            node = new Node(elem);
        if (height > 0) {
            node.nextChild = buildFullTree(null, numChildren, height-1, elem);
            for (int i=1; i < numChildren; i++) {
                Node newNode = buildFullTree(null, numChildren, height-1, elem);
                newNode.nextSibling = node.nextChild;
                node.nextChild = newNode;
            }
        }
        return node;
    }

    public static void main(String[] args) {
        GenericTree<Integer> tree = new GenericTree<>();
        GenericTree<Integer> tree2 = new GenericTree<>();
        GenericTree<Integer> tree3 = new GenericTree<>();
        tree.addRoot(1);
        tree.addChild(tree.root, 2);
        tree.addChild(tree.root, 3);
        // tree.addChild(tree.root, 4);
        // tree.addChild(tree.root, 5);
        tree.addChild(tree.findNode(2), 5);
        // tree.addChild(tree.findNode(2), 4);
        tree.addChild(tree.findNode(3), 7);
        tree.addChild(tree.findNode(3), 6);
        // tree.addChild(tree.findNode(2), 8);
        // tree.addChild(tree.findNode(3), 9);
        // tree.addChild(tree.findNode(4), 10);
        // tree.addChild(tree.findNode(4), 11);
        // tree.addChild(tree.findNode(4), 12);
        // tree.addChild(tree.findNode(4), 13);
        tree2.addRoot(4);
        tree2.addChild(tree2.root, 1);
        tree2.addChild(tree2.root, 5);
        // tree2.addChild(tree2.root, 4);
        // tree2.addChild(tree2.root, 5);
        tree2.addChild(tree2.findNode(1), 2);
        // tree2.addChild(tree2.findNode(1), 3);
        tree2.addChild(tree2.findNode(5), 9);
        tree2.addChild(tree2.findNode(5), 7);
        // tree2.addChild(tree2.findNode(5), 8);
        // tree2.print();


        // tree.print();
        tree3.buildFullTree(4, 3, 5);
        // tree.checkIsomorphic(tree2);
        // tree.levelOrder();
        // tree.findSum();
        // tree.findDepth();
        // tree.preOrder();
        // tree.inOrder();
        // tree.postOrder();
    }
}