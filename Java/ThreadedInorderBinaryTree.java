public class ThreadedInorderBinaryTree <T extends Number & Comparable<? super T>> extends BinaryTree<T> {

    public void addRoot(T elem) {
        Node node = new Node(elem);
        if (root == null) {
            root = node; 
            System.out.println("Root added");
        } else {
            System.out.println("Root is already present!");
        }
    }

    public void addLeftChild(Node node, T elem) {
        Node newNode = new Node(elem);
        if (node == null) {
            System.out.println("Parent node is null!");
        } else if (node.left != null && node.Ltag == 1) {
            System.out.println("Parent node already has a left child!");
        } else {
            newNode.left = node.left;
            newNode.right = node;
            node.left = newNode;
            node.Ltag = 1;
            System.out.println("Added node of value " + elem);
        }
    }

    public void addRightChild(Node node, T elem) {
        Node newNode = new Node(elem);
        if (node == null) {
            System.out.println("Parent node is null!");
        } else if (node.right != null && node.Rtag == 1)  {
            System.out.println("Parent node already has a right child!");
        } else {
            newNode.left = node;
            newNode.right = node.right;
            node.right = newNode;
            node.Rtag = 1;
            System.out.println("Added node of value " + elem);
        }
    }

    public Node find(T elem) {
        Node curr = root;
        while (curr.left != null) {
            curr = curr.left;
        }
        while (curr != null) {
            // curr.print();
            if (curr.data.compareTo(elem) == 0) {
                return curr;
            }
            if (curr.Rtag == 0) {
                curr = curr.right;
            } else {
                curr = curr.right;
                while (curr.Ltag == 1)
                    curr = curr.left;
            }
        }
        return curr;
    }

    public void print() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            System.out.println("Threaded Inorder : " + threadedInorder(root));
        }
    }
    
    public void threadedInorder() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            String s = threadedInorder(root);
            System.out.println("Threaded Inorder : " + s);
        }
    }

    private String threadedInorder(Node node) {
        String s = "";
        while (node.left != null) {
            node = node.left;
        }
        while (node != null) {
            s = s + node.data + " ";
            if (node.Rtag == 0) {
                node = node.right;
            } else {
                node = node.right;
                while (node.Ltag == 1)
                    node = node.left;
            }
        }
        return s;
    }

    public static void main(String[] args) {
        ThreadedInorderBinaryTree<Integer> tree = new ThreadedInorderBinaryTree<>();
        tree.addRoot(1);
        tree.addLeftChild(tree.find(1), 2);
        tree.addRightChild(tree.find(1), 3);
        tree.addLeftChild(tree.find(2), 4);
        tree.addRightChild(tree.find(2), 5);
        tree.addLeftChild(tree.find(3), 6);
        tree.addRightChild(tree.find(3), 7);
        tree.addRightChild(tree.find(5), 8);
        tree.print();
    }
}