public class ThreadedPreorderBinaryTree <T extends Number & Comparable<? super T>> extends BinaryTree<T> {

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
            newNode.left = node;
            newNode.right = node.right;
            node.left = newNode;
            node.Ltag = 1;
            if (node.Rtag == 0) {
                node.right = newNode;
            }
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
            Node lastNode = null;
            if (node.Ltag == 0) {
                lastNode = node;
            } else {
                Node curr = node.left;
                while (curr.Ltag == 1 || curr.Rtag == 1) {
                    while (curr.Rtag == 1)
                        curr = curr.right;
                    if (curr.Ltag == 1)
                        curr = curr.left;
                }
                lastNode = curr;
            }
            node.Rtag = 1;
            newNode.right = lastNode.right;
            newNode.left = lastNode;
            lastNode.right = newNode;
            System.out.println("Added node of value " + elem);
        }
    }

    public Node find(T elem) {
        Node curr = root;
        while (curr != null) {
            // curr.print();
            if (curr.data.compareTo(elem) == 0) {
                return curr;
            }
            if (curr.Ltag == 1) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return curr;
    }

    public void print() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            System.out.println("Threaded Preorder : " + threadedPreorder(root));
        }
    }

    public void threadedPreorder() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            String s = threadedPreorder(root);
            System.out.println("Threaded Preorder : " + s);
        }
    }

    private String threadedPreorder(Node node) {
        String s = "";
        while (node != null) {
            s = s + node.data + " ";
            if (node.Ltag == 1) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return s;
    }

    public static void main(String[] args) {
        ThreadedPreorderBinaryTree<Integer> tree = new ThreadedPreorderBinaryTree<>();
        tree.addRoot(1);
        tree.addLeftChild(tree.find(1), 2);
        tree.addRightChild(tree.find(1), 3);
        tree.addLeftChild(tree.find(2), 4);
        tree.addRightChild(tree.find(2), 5);
        tree.addLeftChild(tree.find(3), 6);
        tree.addRightChild(tree.find(3), 7);
        tree.addRightChild(tree.find(5), 9);
        tree.print();
    }
}