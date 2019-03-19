import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class BinarySearchTree<T extends Number & Comparable<? super T>> extends BinaryTree<T> {

    public void addNode(T elem) {
        Node node = new Node(elem);
        if (root == null) {
            root = node;
        } else {
            Node curr = root;
            while (curr != null) {
                if (elem.compareTo(curr.data) <= 0) {
                    if (curr.left == null) {
                        curr.left = node;
                        break;
                    }
                    curr = curr.left;
                } else {
                    if (curr.right == null) {
                        curr.right = node;
                        break;
                    }
                    curr = curr.right;
                }
            }
        }
    }

    public void print() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            printNode(root);
        }
    }

    public void find(T elem) {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else if (elem == null) {
            System.out.println("Can't search for null element!");
        } else {
            boolean isFound = findNode(root, elem);
            System.out.println("Is element of value " + elem + " found in BST : " + isFound);
        }
    }

    private boolean findNode(Node node, T elem) {
        if (node == null) {
            return false;
        } else if (node.data.compareTo(elem) == 0) {
            return true;
        }
        if (node.data.compareTo(elem) < 0) {
            node = node.right;
        } else {
            node = node.left;
        }
        return findNode(node, elem);
    }

    public T removeLargestNode() {
        Node node = root, parent = null;
        T elem = null;
        while (node.right != null) {
            parent = node;
            node = node.right;
        }
        elem = node.data;
        if (node == root) {
            root = node.left;
        } else if (parent.right.left == null) {
            parent.right = null;
        } else {
            parent.right = parent.right.left;
        }
        return elem;
    }

    public void checkIfBST() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            boolean isBST = isBST(root, null);
            System.out.println("Is the tree BST : " + isBST);
        }
    }

    private boolean isBST(Node node, Node parent) {
        if (node == null) {
            return true;
        }
        boolean left = true, right = true;
        if (node.left != null) {
            left = (node.data.compareTo(node.left.data) >= 0) && isBST(node.left, node);
            if (parent != null)
                left = left && ((node.data.compareTo(parent.data) < 0) ? (node.left.data.compareTo(parent.data) < 0) : (node.left.data.compareTo(parent.data) > 0));
        }
        if (node.right != null) {
            right = (node.data.compareTo(node.right.data) <= 0) && isBST(node.right, node);
            if (parent != null)
                right = right && ((node.data.compareTo(parent.data) < 0) ? (node.right.data.compareTo(parent.data) < 0) : (node.right.data.compareTo(parent.data) > 0));
        }
        return left && right;
    }

    public void findDistance(T elem1, T elem2) {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else if (!findNode(root, elem1) || !findNode(root, elem2)) {
            System.out.println("Elements are not valid!");
        } else {
            int distance = findDistance(root, elem1, elem2);
            System.out.println("Shortest distance between the nodes : " + distance);
        }
    }

    private int findDistance(Node node, T elem1, T elem2) {
        if (node == null) {
            return 0;
        } else {
            if (node.data.compareTo(elem1) == 0 && node.data.compareTo(elem2) == 0) {
                return 0;
            }
            if (elem1.compareTo(node.data) <= 0 && elem2.compareTo(node.data) <= 0) {
                return (elem1.compareTo(elem2) == 0 ? 1 : 0) + findDistance(node.left, 
                ((elem1.compareTo(node.data) == 0) ? elem2 : elem1) , ((elem2.compareTo(node.data) == 0) ? elem1 : elem2));
            }
            if (elem1.compareTo(node.data) >= 0 && elem2.compareTo(node.data) >= 0) {
                return (elem1.compareTo(elem2) == 0 ? 1 : 0) + findDistance(node.right, 
                ((elem1.compareTo(node.data) == 0) ? elem2 : elem1) , ((elem2.compareTo(node.data) == 0) ? elem1 : elem2));
            }
            if (elem1.compareTo(node.data) < 0 && elem2.compareTo(node.data) > 0) {
            return 1 + findDistance(node.left, elem1, elem1) + findDistance(node.right, elem2, elem2);
            }
            if (elem1.compareTo(node.data) > 0 && elem2.compareTo(node.data) < 0) {
                return 1 + findDistance(node.left, elem2, elem2) + findDistance(node.right, elem1, elem1);
            }
            return 0;
        }
    }

    public void findLCA(T elem1, T elem2) {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else if (!findNode(root, elem1) || !findNode(root, elem2)) {
            System.out.println("Elements are not valid!");
        } else {
            Node node = findLCA(root, elem1, elem2);
            System.out.println("Least Common Ancestor of " + elem1 + " and " + elem2 + " : " + node.data);
        }
    }

    private Node findLCA(Node node, T elem1, T elem2) {
        if (node == null)
            return null;
        if ((elem1.compareTo(node.data) <= 0 && elem2.compareTo(node.data) >= 0) 
            || (elem1.compareTo(node.data) >= 0 && elem2.compareTo(node.data) <= 0)) {
            return node;
        }
        if (elem1.compareTo(node.data) <=0 && elem2.compareTo(node.data) <= 0) {
            return findLCA(node.left, elem1, elem2);
        } else {
            return findLCA(node.right, elem1, elem2);
        }
    }

    public void convertToDLL() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            Node head = convertToDLL(root);
            String s  = "";
            Node node = head;
            while (node.right != null) {
                s = s + node.data + " ";
                node = node.right;
            }
            s = s + node.data + " ";
            node.right = head;
            head.left = node;
            System.out.println("DLL has been created : " + s);
        }
    }

    private Node convertToDLL(Node node) {
        if (node == null) {
            return null;
        }
        Node leftNode = convertToDLL(node.left);
        Node rightNode = convertToDLL(node.right);
        if (rightNode != null) {
            rightNode.left = node;
        }
        if (leftNode != null && rightNode != null) {
            while (rightNode.right != null)
                rightNode = rightNode.right;
            rightNode.right = leftNode;
            leftNode.left = rightNode;
        } else if (leftNode != null) {
            leftNode.left = node;
            node.right = leftNode;
        }
        node.left = null;
        
        return node;
    }

    public void convertFromDLL() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            Node head = convertToDLL(root);
            root = convertFromDLL(head);
            System.out.println("Height balanced tree build from sorted DLL");
            print();
        }
    }

    private Node convertFromDLL(Node node) {
        if (node == null)
            return null;
        Node mid = node;
        Node curr = node;
        while (curr != null && curr.right != null) {
            mid = mid.right;
            curr = curr.right;
            if (curr != null)
                curr = curr.right;
        }
        Node leftHead = null, rightHead = null;
        if (mid.left != null) {
            mid.left.right = null;
            leftHead = convertFromDLL(node);
        }
        if (mid.right != null) {
            mid.right.left = null;
            rightHead = convertFromDLL(mid.right);
        }
        mid.left = leftHead;
        mid.right = rightHead;
        return mid;
    }

    public void convertFromArray(T[] arr) {
        root = convertFromArray(arr, 0, arr.length-1);
        System.out.println("Height balanced BST built from sorted array");
        print();
    }

    private Node convertFromArray(T[] arr, int startIdx, int endIdx) {
        if (arr.length == 0 || startIdx > endIdx)
            return null;
        if (startIdx == endIdx)
            return new Node(arr[startIdx]);
        int mid = (startIdx + endIdx)/2;
        Node node = new Node(arr[mid]);
        Node leftNode = convertFromArray(arr, startIdx, mid-1);
        Node rightNode = convertFromArray(arr, mid+1, endIdx);
        node.left = leftNode;
        node.right = rightNode;
        return node;
    }

    Node llHead;
    public void convertFromLL() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            // root = convertFromLL(root); --- O(nlogn)
            llHead = root;
            int size = 0;
            while (root != null) {
                root = root.right;
                size++;
            }
            System.out.println("Size of the tree : " + size);
            root = convertFromLL(0, size-1);  // -- O(n)
            System.out.println("Height balanced built from sorted LL");
            print();
        }
    }

    // O(nlogn) algo not optimal
    private Node convertFromLL(Node head) {
        if (head == null) {
            return null;
        }
        Node mid = head, curr = head, prev = null;
        while (curr != null && curr.right != null) {
            prev = mid;
            mid = mid.right;
            curr = curr.right;
            if (curr != null)
                curr = curr.right;
        }
        Node leftHead = null, rightHead = null;
        if (prev != null) {
            prev.right = null;
        }
        if (mid != head) {
            leftHead = head;
            rightHead = mid.right;
            mid.right = null;
        }
        Node leftNode = convertFromLL(leftHead);
        Node rightNode = convertFromLL(rightHead);
        mid.left = leftNode;
        mid.right = rightNode;
        return mid;
    }

    private Node convertFromLL(int startIdx, int endIdx) {
        if (startIdx > endIdx) {
            return null;
        }
        if (startIdx == endIdx) {
            Node res = llHead;
            llHead = llHead.right;
            res.right = null;
            return res;
        }
        int mid = startIdx + (endIdx - startIdx + 1)/2; // TODO : Why does this work??
        Node leftNode = convertFromLL(startIdx, mid - 1);
        Node parent = llHead;
        parent.left = leftNode;
        llHead = llHead.right;
        Node rightNode = convertFromLL(mid + 1, endIdx);
        parent.right = rightNode;
        return parent;
    }

    public void findKthSmallest(int k) {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            smallest = 0;
            T elem = findKthSmallest(root, k);
            System.out.println(k + "th smallest element is : " + elem);
        }
    }

    int smallest = 0;
    private T findKthSmallest(Node node, int k) {
        if (node == null)
            return null;
        T elem = findKthSmallest(node.left, k);
        if (elem != null)
            return elem;
        smallest++;
        if (smallest == k) {
            return node.data;
        }
        return findKthSmallest(node.right, k);
    }

    public void findNearestElem(T elem) {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            Integer bestDiff = findNearestElem(root, elem);
            bestDiff = bestDiff + elem.intValue();
            System.out.println("Nearest element to " + elem + " is : " + bestDiff);
        }
    }

    private Integer findNearestElem(Node node, T elem) {
        if (node == null)
            return null;
        Integer diff = node.data.intValue() - elem.intValue();
        if (diff == 0) {
            return 0;
        }
        Integer leftDiff = findNearestElem(node.left, elem);
        Integer rightDiff = findNearestElem(node.right, elem);
        if (leftDiff != null && rightDiff != null) {
            if (Math.abs(leftDiff) <= Math.min(Math.abs(diff), Math.abs(rightDiff))) {
                return leftDiff;
            } else if (Math.abs(diff) <= Math.min(Math.abs(leftDiff), Math.abs(rightDiff))) {
                return diff;
            } else {
                return rightDiff;
            }
        } else if (leftDiff == null && rightDiff == null) {
            return diff;
        } else {
            Integer childDiff = (leftDiff != null) ? leftDiff : rightDiff;
            if (Math.abs(childDiff) <= Math.abs(diff)) {
                return childDiff;
            } else {
                return diff;
            }
        }
    }

    public void pruneBST(T min, T max) {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            System.out.println("Pruning the tree within range of " + min + " and " + max);
            pruneBST(root, min, max);
            print();
        }
    }

    private Node pruneBST(Node node, T min, T max) {
        if (node == null)
            return null;
        if (node.data.compareTo(min) >= 0 && node.data.compareTo(max) <= 0) {
            Node left = pruneBST(node.left, min, max);
            Node right= pruneBST(node.right, min, max);
            node.left = left;
            node.right = right;
            return node;
        }
        return null;
    }

    public void printInRange(T min, T max) {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            String s = printInRange(root, min, max);
            System.out.println("Elements withing range " + min + " and " + max + " : " + s);
        }
    }

    private String printInRange(Node node, T min, T max) {
        if (node == null) 
            return "";
        if (node.data.compareTo(min) < 0 || node.data.compareTo(max) > 0) {
            return printInRange(node.left, min, max) + printInRange(node.right, min, max);
        } else {
            return node.data + " " + printInRange(node.left, min, max) + printInRange(node.right, min, max);
        }
    }

    public void compareBST(BinarySearchTree<T> compTree) {
        if (root == null || compTree.root == null) {
            System.out.println("Tree is empty!");
        } else {
            String s1 = inOrderTraverse(root);
            String s2 = compTree.inOrderTraverse(compTree.root);
            System.out.println("Trees similar by value : " + (s1.equals(s2)));
        }
    }

    public void countUniqueBSTs(int n) {
        memoizeMap = new HashMap<>();
        BigInteger count = countUniqueBST(n);
        System.out.println("Number of unique BSTs with " + n + " nodes is : " + count);
    }

    private int countUniqueBSTSlow(int n) {
        if (n <= 1)
            return 1;
        int sum = 0;
        for (int root = 1; root <= n; root++) {
            int left = countUniqueBSTSlow(root-1);
            int right = countUniqueBSTSlow(n-root);
            sum += left * right;
        }
        return sum;
    }

    Map<Integer, BigInteger> memoizeMap = new HashMap<>();
    private BigInteger countUniqueBST(int n) {
        if (n <= 1)
            return new BigInteger("1");
        if (memoizeMap.containsKey(n)) {
            return memoizeMap.get(n);
        }
        BigInteger sum = new BigInteger("0");
        for (int root = 1; root <= n; root++) {
            BigInteger left = countUniqueBST(root-1);
            BigInteger right = countUniqueBST(n-root);
            sum = sum.add(left.multiply(right));
        }
        memoizeMap.put(n, sum);
        return sum;
    }

    public static void main(String[] arfs) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        // BinarySearchTree<Integer> bstAlt = new BinarySearchTree<>();
        // bst.addNode(8);
        // bst.addNode(9);
        // bst.addNode(1);
        // bst.addNode(5);
        // bst.addNode(3);
        // bst.addNode(16);
        // bst.addNode(10);
        // bst.addNode(13);
        // bst.addNode(19);
        // bst.addNode(26);
        // bst.addNode(-1);
        // bst.deserialize(new Integer[]{1, 2});
        // bst.deserialize(new Integer[]{1, 2, 5, 3, 4});
        // bst.deserialize(new Integer[]{1, 5, 10, 6, 13, 11});
        // bst.deserialize(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        // bst.deserialize(new Integer[]{1, 2, 3, null, 5, null, 7, 8, null, 9, 10, 11, null, 12});
        // bst.deserialize(new Integer[]{7, 4, 8, 2, 5, null, null, 1, 3, null, 6});
        // bst.convertFromArray(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        // bst.convertFromArray(new Integer[] {0, 1, 2, 3, 4, 5});
        BigInteger prev = new BigInteger("1"), curr = new BigInteger("1");
        for (int i=0; i < 50; i++) {
         curr = bst.countUniqueBST(i);
         System.out.println("Ratio for i = " + i + " : " + (curr.divide(prev)));
         prev = curr;
        }


        // bst.addNode(4);
        // bst.addNode(3);
        // bst.addNode(5);
        // bst.addNode(1);
        // bst.addNode(6);

        // bstAlt.addNode(6);
        // bstAlt.addNode(5);
        // bstAlt.addNode(4);
        // bstAlt.addNode(3);
        // bstAlt.addNode(1);

        // bst.root = bst.flattenTree(bst.root);
        // bst.print();
        // bstAlt.print();
        // bst.compareBST(bstAlt);
        // bst.mirrorTree();
        // bst.convertFromLL();
        // bst.checkIfBST();
        // bst.printInRange(1, 13);
        // bst.pruneBST(1, 13);
        // bst.pruneBST(-1, 16);
        // bst.findNearestElem(6);
        // bst.findKthSmallest(2);
        // bst.findKthSmallest(5);
        // bst.findKthSmallest(6);
        // bst.findKthSmallest(4);
        // bst.mirrorTree();
        // bst.print();
        // bst.convertFromDLL();
        // bst.convertToDLL();
        // bst.verticalTraversal();
        // bst.findDistance(1, 1);
        // bst.findLCA(5, 10);
        // bst.checkIfBST();
        // bst.find(4);
    }
}