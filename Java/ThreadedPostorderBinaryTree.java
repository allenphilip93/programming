public class ThreadedPostorderBinaryTree <T extends Number & Comparable<? super T>> extends BinaryTree<T> {

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
            node.Ltag = 1;
            node.left = newNode;
            newNode.left = node; // wrong
            newNode.right = ;
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
            
            System.out.println("Added node of value " + elem);
        }
    }
}