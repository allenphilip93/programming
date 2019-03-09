public class AVLTree <T extends Number & Comparable<? super T>> extends BinarySearchTree<T> {

    public void isAVL() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            Boolean isAVL = isAVL(root);
            System.out.println("Is it an AVL tree : " + isAVL.toString().toUpperCase());
        }
    }

    private boolean isAVL(Node node) {
        if (node == null) {
            return true;
        }
        return (Math.abs(node.balanceFactor) <= 1) && isAVL(node.left) && isAVL(node.right);
    }

    public void addNode(T elem) {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            
        }
    }

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.addNode(5);
        tree.addNode(2);
        tree.addNode(8);
        tree.addNode(1);
        tree.addNode(4);
        tree.addNode(9);
        tree.addNode(6);
        tree.printTree();
        tree.isAVL();
    }
}