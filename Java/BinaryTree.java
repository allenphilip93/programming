import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class BinaryTree<T extends Number & Comparable<? super T>> {

    class Node {
        T data;
        Node left;
        Node right;
        int Ltag, Rtag;
        Node nextSibling;
        int bf; // balance factor
        public Node(T data){
            this.data = data;
        }

        public void print() {
            System.out.println("Data : " + data + " | " + "Left : " + ((left != null && left.data != null) ? left.data.toString() : "null")
            + " | " + "Left Tag : " + Ltag + " | " + "Right Tag : " + Rtag + " | " + "Right : " + ((right != null && right.data != null) ? right.data.toString() : "null"));
        }
    }

    Node root;

    public void addNode(T elem) {
        Node node = new Node(elem);
        if (root == null) {
            root = node;
        } else {
            // Level order add
            QueueLinkedList<Node> queue = new QueueLinkedList<>();
            queue.enqueue(root);
            while (queue.size > 0) {
                Node curr = queue.dequeue();
                if (curr.left == null) {
                    curr.left = node;
                    break;
                } else if (curr.right == null) {
                    curr.right = node;
                    break;
                } else {
                    queue.enqueue(curr.left);
                    queue.enqueue(curr.right);
                }
            }
        }
    }

    public void addRoot(T elem) {
        root = new Node(elem);
    }

    public void addLeftChild(Node node, T elem) {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else if (node.left != null) {
            System.out.println("Node already has a left child!");
        } else {
            node.left = new Node(elem);
        }
    }

    public void addRightChild(Node node, T elem) {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else if (node.right != null) {
            System.out.println("Node already has a right child!");
        } else {
            node.right = new Node(elem);
        }
    }

    public void removeNode(T elem) {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            // Level order delete
            // QueueLinkedList<Node> queue = new QueueLinkedList<>();
            
        }
    }

    public void deleteTree() {
        root = null; // gc takes care of the rest
    }

    public void printNode(Node root) {
        int maxLevel = maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        printWhitespaces(firstSpaces);

        List<Node> newNodes = new ArrayList<Node>();
        for (Node node : nodes) {
            if (node != null) {
                System.out.print(node.data + "(" + node.bf + ")");
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    printWhitespaces(1);

                printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    printWhitespaces(1);

                printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private int maxLevel(Node node) {
        if (node == null)
            return 0;
        return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
    }

    private <K> boolean isAllElementsNull(List<K> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }
        return true;
    }

    public void printTree() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            // QueueLinkedList<Node> queue = new QueueLinkedList<>();
            // queue.enqueue(root);
            // int level = 0;
            // while (queue.size > 0) {
            //     String s = "";
            //     int size = queue.size;
            //     for (int idx=0; idx < size; idx++) {
            //         Node node = queue.dequeue();
            //         if (node != null) {
            //             s = s + node.data.toString() + " ";
            //         } else {
            //             s = s + "null" + " ";
            //             continue;
            //         }
            //         // if (node.left != null)
            //         queue.enqueue(node.left);
            //         // if (node.right != null)
            //         queue.enqueue(node.right);
            //     }
            //     System.out.println("Level " + level + " : " + s);
            //     level++;
            // }            
            printNode(root);
        }
    }

    // TODO : With and without recursion
    public void getTreeSize() {
        int size;
        if (root == null) {
            size = 0;
        } else {
            size = getTreeSizeRecursion(root);
            System.out.println("Tree Size Recursion : " + size);
            size = getTreeSizeLevelOrder(root);
            System.out.println("Tree Size Level Order : " + size);
        }
    }

    public int getTreeSizeRecursion(Node node) {
        if (node == null) {
            return 0;
        } else {
            return getTreeSizeRecursion(node.left) + getTreeSizeRecursion(node.right) + 1;
        }
    }

    public int getTreeSizeLevelOrder(Node node) {
        QueueLinkedList<Node> queue = new QueueLinkedList<>();
        int size = 0;
        if (node != null) {
            queue.enqueue(node);
            while (queue.size > 0) {
                Node curr = queue.dequeue();
                size++;
                if (curr.left != null)
                    queue.enqueue(curr.left);
                if (curr.right != null)
                    queue.enqueue(curr.right);
            }
        }
        return size;
    }

    // TODO : With and without recursion
    public void findNode(T elem) {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            boolean isFound = findNodeRecurse(root, elem);
            System.out.println("Found " + elem.toString() + " in tree by recursion : " + isFound);
            isFound = findNodeLevelOrder(root, elem);
            System.out.println("Found " + elem.toString() + " in tree by traversal : " + isFound);
        }
    }

    private boolean findNodeRecurse(Node node, T elem) {
        if (node == null) {
            return false;
        } else if (node.data.intValue() == elem.intValue()) {
            return true;
        } else {
            boolean isFound = findNodeRecurse(node.left, elem);
            isFound = isFound | findNodeRecurse(node.right, elem);
            return isFound;
        }
    }

    private boolean findNodeLevelOrder(Node node, T elem) {
        boolean isFound = false;
        if (node != null) {
            QueueLinkedList<Node> queue = new QueueLinkedList<>();
            queue.enqueue(node);
            while (queue.size > 0 && !isFound) {
                Node curr = queue.dequeue();
                if (curr.data.intValue() == elem.intValue()) {
                    isFound = true;
                } else {
                    if (curr.left != null)
                        queue.enqueue(curr.left);
                    if (curr.right != null)
                        queue.enqueue(curr.right);
                }
            }
        }
        return isFound;
    }

    public Node getNode(T elem) {
        Node curr = null;
        boolean isFound = false;
        if (root != null) {
            QueueLinkedList<Node> queue = new QueueLinkedList<>();
            queue.enqueue(root);
            while (queue.size > 0 && !isFound) {
                curr = queue.dequeue();
                if (curr.data.intValue() == elem.intValue()) {
                    isFound = true;
                } else {
                    if (curr.left != null)
                        queue.enqueue(curr.left);
                    if (curr.right != null)
                        queue.enqueue(curr.right);
                }
            }
            if (!isFound)
                curr = null;
        }
        return curr;
    }

    public void findDeepestNode() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            Node curr = root;
            QueueLinkedList<Node> queue = new QueueLinkedList<>();
            queue.enqueue(root);
            while (queue.size > 0) {
                int size = queue.size;
                for (int idx=0; idx < size; idx++) {
                    curr = queue.dequeue();
                    if (curr.left != null)
                        queue.enqueue(curr.left);
                    if (curr.right != null)
                        queue.enqueue(curr.right);
                }
            }
            System.out.println("Deepest Node is : " + curr.data.toString());            
        }
    }

    public void findNumberOfFullNodes() {
        int count = 1;
        if (root != null) {
            count = countFullNodes(root);
        }
        System.out.println("Number of full nodes : " + count);
    }

    private int countFullNodes(Node node) {
        if (node == null)
            return 0;
        if ((node.left != null && node.right != null) 
        || (node.left == null && node.right == null)) {
            return 1 + countFullNodes(node.left) + countFullNodes(node.right);
        }
        return countFullNodes(node.left) + countFullNodes(node.right);
    }

    public void findNumberOfHalfNodes() {
        int count = 0;
        if (root != null) {
            count = countHalfNodes(root);
        }
        System.out.println("Number of half nodes : " + count);
    }

    private int countHalfNodes(Node node) {
        if (node == null || (node.left == null && node.right == null))
            return 0;
        if (node.left == null || node.right == null)
            return 1 + countHalfNodes(node.left) + countHalfNodes(node.right);
        return countHalfNodes(node.left) + countHalfNodes(node.right);
    }

    public void checkSameStructure(BinaryTree<T> cmpTree) {
        if (root == null && cmpTree.root == null) {
            System.out.println("Both the trees are empty!");
        } else if (root == null || cmpTree.root == null) {
            System.out.println("One of the trees is empty!");
        } else {
            boolean isSame = checkSameStructure(root, cmpTree.root);
            if (isSame)
                System.out.println("Yes, the two trees are similar");
            else
                System.out.println("No, the two trees are not similar");
        }
    }

    private boolean checkSameStructure(Node n1, Node n2) {
        if (n1 == null || n2 == null) {
            return (n1 == null && n2 == null);
        }
        // System.out.println("Nodes parsed are (" + n1.data.toString() + ", " + n2.data.toString() + ")");
        // boolean bool = (n1.data.intValue() == n2.data.intValue());
        return checkSameStructure(n1.left, n2.left) & checkSameStructure(n1.right, n2.right);
    }

    public void checkSameTree(BinaryTree<T> cmpTree) {
        if (root == null && cmpTree.root == null) {
            System.out.println("Both the trees are empty!");
        } else if (root == null || cmpTree.root == null) {
            System.out.println("One of the trees is empty!");
        } else {
            boolean isSame = checkSameTree(root, cmpTree.root);
            if (isSame)
                System.out.println("Yes, the two trees are equal and same");
            else
                System.out.println("No, the two trees are not same");
        }
    }

    private boolean checkSameTree(Node n1, Node n2) {
        if (n1 == null || n2 == null) {
            return (n1 == null && n2 == null);
        }
        System.out.println("Nodes parsed are (" + n1.data.toString() + ", " + n2.data.toString() + ")");
        boolean bool = (n1.data.intValue() == n2.data.intValue());
        return bool & checkSameTree(n1.left, n2.left) & checkSameTree(n1.right, n2.right);
    }

    public void checkMirrorTree(BinaryTree<T> cmpTree) {
        if (root == null && cmpTree.root == null) {
            System.out.println("Both the trees are empty!");
        } else if (root == null || cmpTree.root == null) {
            System.out.println("One of the trees is empty!");
        } else {
            boolean isMirror = checkMirrorTree(root, cmpTree.root);
            if (isMirror)
                System.out.println("Yes, the two trees are mirror of each other");
            else
                System.out.println("No, the two trees are not mirror of each other");
        }
    }

    private boolean checkMirrorTree(Node n1, Node n2) {
        if (n1 == null || n2 == null) {
            return (n1 == null && n2 == null);
        }
        // System.out.println("Nodes parsed are (" + n1.data.toString() + ", " + n2.data.toString() + ")");
        return n1.data.intValue() == n2.data.intValue() 
            && checkMirrorTree(n1.left, n2.right) 
            && checkMirrorTree(n1.right, n2.left);
    }
    
    public void mirrorTree() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            mirrorTreeRecurse(root);
            System.out.println("Tree has been mirrored!");
            printTree();
        }
    }

    private void mirrorTreeRecurse(Node node) {
        if (node == null) {
            return;
        }
        Node temp = node.right;
        node.right = node.left;
        node.left = temp;
        mirrorTreeRecurse(node.left);
        mirrorTreeRecurse(node.right);
    }

    // With and without recursion
    public void getTreeDepth() {
        int depth = getNodeDepthRecursion(root, null) - 1;
        System.out.println("Tree Depth Recursion : " + depth);
        depth = getNodeDepthLevelOrder(root, null);
        System.out.println("Tree Depth Level Order : " + depth);
    }

    // With and without recursion
    public void getTreeHeight() {
        int height = getNodeHeightRecursion(root);
        System.out.println("Tree Height Recursion : " + height);
        height = getNodeHeightLevelOrder(root);
        System.out.println("Tree Height Level Order : " + height);
    }

    // With and without recursion
    private int getNodeDepthRecursion(Node root, Node node) {
        if (root == node) {
            return 0;
        } else if (root == null) {
            return -1;
        } else {
            int d1 = getNodeDepthRecursion(root.left, node);
            int d2 = getNodeDepthRecursion(root.right, node);
            if (d1 >= 0) {
                d1++;
            }
            if (d2 >= 0) {
                d2++;
            }
            return Math.max(d1, d2);
        }
    }

    // With and without recursion
    private int getNodeHeightRecursion(Node node) {
        if (node == null) {
            return -1;
        } else {
            return Math.max(1+getNodeHeightRecursion(node.left), 1+getNodeHeightRecursion(node.right));
        }
    }

    private int getNodeDepthLevelOrder(Node root, Node node) {
        int startLevel = 0;
        int endLevel = 0;
        int currLevel = 0;
        if (root != null) {
            QueueLinkedList<Node> queue = new QueueLinkedList<>();
            queue.enqueue(root);
            Node curr;
            boolean isFound = false;
            while (queue.size > 0 && !isFound) {
                int size = queue.size;
                for (int idx=0; idx < size; idx++) {
                    curr = queue.dequeue();
                    if (curr == node) {
                        endLevel = currLevel;
                        isFound = true;
                    } else {
                        if (curr.left != null)
                            queue.enqueue(curr.left);
                        if (curr.right != null)
                            queue.enqueue(curr.right);
                    }
                }
                currLevel++;
            }
            if (node == null)
                endLevel = currLevel-1;
        }
        return endLevel - startLevel;
    }

    private int getNodeHeightLevelOrder(Node node) {
        int startLevel = 0;
        int endLevel = 0;
        int currLevel = 0;
        if (root != null) {
            QueueLinkedList<Node> queue = new QueueLinkedList<>();
            queue.enqueue(node);
            Node curr;
            while (queue.size > 0) {
                int size = queue.size;
                for (int idx=0; idx < size; idx++) {
                    curr = queue.dequeue();
                    if (curr.left != null)
                        queue.enqueue(curr.left);
                    if (curr.right != null)
                        queue.enqueue(curr.right);
                }
                currLevel++;
            }
            endLevel = currLevel - 1;
        }
        return endLevel - startLevel;
    }

    // With and without recursion
    public void getNodeDepth(T elem) {
        Node node = getNode(elem);
        if (node != null) {
            int depth = getNodeDepthRecursion(root, node);
            System.out.println("Depth of node " + elem.toString() + " by recursion : " + depth);
            depth = getNodeDepthLevelOrder(root, node);
            System.out.println("Depth of node " + elem.toString() + " by level order : " + depth);
        } else {
            System.out.println("Couldn't find node " + elem.toString());
        }
    }

    // With and without recursion
    public void getNodeHeight(T elem) {
        Node node = getNode(elem);
        if (node != null) {
            int height = getNodeHeightRecursion(node);
            System.out.println("Height of node " + elem.toString() + " by recursion : " + height);
            height = getNodeHeightLevelOrder(node);
            System.out.println("Height of node " + elem.toString() + " by level order : " + height);            
        } else {
            System.out.println("Couldn't find node " + elem.toString());
        }
    }

    public boolean isStrictTree() {
        int count = countHalfNodes(root);
        return (count == 0);
    }

    public boolean isFullTree() {
        int height = getNodeHeightRecursion(root);
        int count = countLeafNodesRecurse(root);
        return (Math.pow(2, height) == count);
    }

    public boolean isCompleteTree() {
        boolean isCompleteTree = true;
        if (root != null) {
            QueueLinkedList<Node> queue = new QueueLinkedList<>();
            queue.enqueue(root);
            boolean nullFound = false;
            while (queue.size > 0) {
                int size = queue.size;
                for (int idx=0; idx < size; idx++) {
                    Node node = queue.dequeue();
                    if (node != null) {
                        queue.enqueue(node.left);
                        queue.enqueue(node.right);
                    } else {
                        nullFound = true;
                        break;
                    }
                }
                queue.print();
                if (nullFound) {
                    System.out.println("Null found");
                    queue.print();
                    // boolean res = true;
                    while (queue.size > 0) {
                        isCompleteTree = isCompleteTree && (queue.dequeue() == null);
                    }
                    // isCompleteTree = res;
                }
            }
        }
        return isCompleteTree;
    }

    public void reversePreOrderTraverse() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            String s = reversePreOrderTraverse(root);
            System.out.println("Reverse PreOrder Traversal : " + s);
        } 
    }

    private String reversePreOrderTraverse(Node node) {
        if (node == null) {
            return "";
        }
        String s = node.data.toString() + " ";
        return s + reversePreOrderTraverse(node.right) + reversePreOrderTraverse(node.left);
    }

    public void preOrderTraverse() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            String s = preOrderTraverse(root);
            System.out.println("PreOrder Traversal : " + s);
        }
    }

    private String preOrderTraverse(Node node) {
        if (node == null) {
            return "";
        }
        String s = node.data.toString() + " ";
        return s + preOrderTraverse(node.left) + preOrderTraverse(node.right);
    }

    public void stackPreOrder() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            LinkedListStack.Stack<Node> stack = new LinkedListStack.Stack<>();
            stack.push(root);
            String s = "";
            while (stack.size > 0) {
                Node node = stack.pop();
                s = s + node.data.toString() + " ";
                if (node.right != null)
                    stack.push(node.right);
                if (node.left != null)
                    stack.push(node.left);
            }
            System.out.println("Stack PreOrder Traversal : " + s);
        }
    }

    public void stackPreOrderAlt() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            LinkedListStack.Stack<Node> stack = new LinkedListStack.Stack<>();
            Node node = root;
            String s = "";
            while (true) {
                // Add left sub tree node
                while (node != null) {
                    s = s + node.data.toString() + " ";
                    stack.push(node);
                    node = node.left;
                }
                if (stack.size == 0)
                    break;
                node = stack.pop();
                // Address the right sub tree
                node = node.right;
            }
            System.out.println("Stack PreOrder Traversal : " + s);
        }
    }

    public void inOrderTraverse() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            String s = inOrderTraverse(root);
            System.out.println("InOrder Traversal : " + s);
        }
    }

    public String inOrderTraverse(Node node) {
        if (node == null) {
            return "";
        }
        return inOrderTraverse(node.left) + node.data.toString() + " " + inOrderTraverse(node.right);
    }

    public void stackInOrder() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            LinkedListStack.Stack<Node> stack = new LinkedListStack.Stack<>();
            Node node = root;
            String s = "";
            while (true) {
                // Add left sub tree node
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
                if (stack.size == 0)
                    break;
                node = stack.pop();
                s = s + node.data.toString() + " ";
                // Address the right sub tree
                node = node.right;
            }
            System.out.println("Stack InOrder Traversal : " + s);
        }
    }

    public void postOrderTraverse() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            String s = postOrderTraverse(root);
            System.out.println("PostOrder Traversal : " + s);
        }
    }

    private String postOrderTraverse(Node node) {
        if (node == null) {
            return "";
        }
        String s = node.data.toString() + " ";
        return postOrderTraverse(node.left) + postOrderTraverse(node.right) + s;
    }

    public void stackPostOrder() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            LinkedListStack.Stack<Node> stack = new LinkedListStack.Stack<>();
            stack.push(root);
            String s = "";
            Node prev = null;
            while (stack.size > 0) {
                Node curr = stack.peek();
                // if the execution is going downwards
                if (prev == null || prev.left == curr || prev.right == curr) {
                    if (curr.left != null)
                        stack.push(curr.left);
                    else if (curr.right != null)
                        stack.push(curr.right);
                } 
                // if the execution is going upwards
                else if (curr.left == prev) {
                    if (curr.right != null)
                        stack.push(curr.right);
                }
                // if curr and prev at the same point
                else {
                    s = s + curr.data.toString() + " ";
                    stack.pop();
                }
                prev = curr;
            }
            System.out.println("Stack PostOrder Traversal : " + s);
        }
    }

    public void stackPostOrderAlt() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            LinkedListStack.Stack<Node> stack = new LinkedListStack.Stack<>();
            Node node = root;
            String s = "";
            while (true) {
                // Add left sub tree node
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
                if (stack.size == 0)
                    break;
                node = stack.pop();
                if (node.right != null) {
                    node = node.right;
                    continue;
                }
                s = s + node.data.toString() + " ";
                // Address the right sub tree
                node = node.right;
            }
            System.out.println("Stack PostOrder Traversal : " + s);
        }
    }

    public void levelOrderTraverse() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            QueueLinkedList<Node> queue = new QueueLinkedList<>();
            queue.enqueue(root);
            String s = "";
            while (queue.size > 0) {
                int size = queue.size;
                for (int idx=0; idx < size; idx++) {
                    Node node = queue.dequeue();
                    s = s + node.data.toString() + " ";
                    if (node.left != null)
                        queue.enqueue(node.left);
                    if (node.right != null)
                        queue.enqueue(node.right);
                }
            }
            System.out.println("Level Order Traversal : " + s);
        }
    }

    public void reverseLevelOrder() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            LinkedListStack.Stack<Node> stack = new LinkedListStack.Stack<>();
            QueueLinkedList<Node> queue = new QueueLinkedList<>();
            queue.enqueue(root);
            Node node;
            while (queue.size > 0) {
                node = queue.dequeue();
                stack.push(node);
                if (node.right != null)
                    queue.enqueue(node.right);
                if (node.left != null)
                    queue.enqueue(node.left);
            }
            String s = "";
            while (stack.size > 0) {
                node = stack.pop();
                s = s + node.data.toString() + " ";
            }
            System.out.println("Reverse Level Order : " + s);
        }
    }

    public void zigzagTraverse() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            QueueLinkedList<Node> queue = new QueueLinkedList<>();
            queue.enqueue(root);
            int level = 0;
            while (queue.size > 0) {
                int size = queue.size;
                String s = "";
                LinkedListStack.Stack<Node> stack = new LinkedListStack.Stack<>();
                for (int idx=0; idx < size; idx++) {
                    Node node = queue.dequeue();
                    if (level%2 == 1) {
                        stack.push(node);
                    } else {
                        s = s + node.data.toString() + " ";
                    }
                    if (node.left != null)
                        queue.enqueue(node.left);
                    if (node.right != null)
                        queue.enqueue(node.right);
                }
                while (stack.size > 0) {
                    s = s + stack.pop().data.toString() + " ";
                }
                System.out.println("Level " + level + " : " + s);
                level++;
            }
        }
    }

    public void printLeafNodes() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            String s = printLeavesRecurse(root);
            System.out.println(s);
        }
    }

    private String printLeavesRecurse(Node node) {
        if (node == null)
            return "";
        if (node.left == null && node.right == null) 
            return node.data.toString() + " ";
        return printLeavesRecurse(node.left) + printLeavesRecurse(node.right);
    }

    public void countLeafNodes() {
        int count = -1;
        count = countLeafNodesRecurse(root);
        System.out.println("Count leaf nodes recursion : " + count);
        count = countLeafNodeLevelOrder();
        System.out.println("Count leaf node level order : " + count);
    }

    private int countLeafNodesRecurse(Node node) {
        if (node == null) {
            return 0;
        } else if (node.left == null && node.right == null) {
            return 1;
        } else {
            return countLeafNodesRecurse(node.left) + countLeafNodesRecurse(node.right);
        }
    }

    private int countLeafNodeLevelOrder() {
        int count = -1;
        if (root != null) {
            count = 0;
            QueueLinkedList<Node> queue = new QueueLinkedList<>();
            queue.enqueue(root);
            while (queue.size > 0) {
                Node curr = queue.dequeue();
                if (curr.left == null && curr.right == null) {
                    count++;
                }
                if (curr.left != null)
                    queue.enqueue(curr.left);
                if (curr.right != null)
                    queue.enqueue(curr.right);
            }
        }
        return count;
    }

    // TODO : With and without recursion
    public void findMaxNode() {
        if (root == null) {
            System.out.println("Max not found");
        } else {
            int max = findMaxRecurse(root, 0);
            System.out.println("Max Node Recurse : " + max);
            max = findMaxLevelOrder(root);
            System.out.println("Max Node Level Order : " + max);
        }
    }

    private int findMaxRecurse(Node root, int max) {
        if (root == null) {
            return max;
        } else if (root.data.intValue() > max) {
            max = root.data.intValue();
        }
        max = findMaxRecurse(root.left, max);
        max = findMaxRecurse(root.right, max);
        return max;
    }

    private int findMaxLevelOrder(Node root) {
        if (root == null) {
            System.out.println("Tree is empty!");
            return -1;
        } else {
            QueueLinkedList<Node> queue = new QueueLinkedList<>();
            queue.enqueue(root);
            int max = -1;
            while (queue.size > 0) {
                Node node = queue.dequeue();
                if (node.data.intValue() > max)
                    max = node.data.intValue();
                if (node.left != null)
                    queue.enqueue(node.left);
                if (node.right != null)
                    queue.enqueue(node.right);
            }
            return max;
        }
    }

    public void findDiameter() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            findDiameterRecurse(root);
            System.out.println("Diameter/Width of the tree is : " + diameter);
        }
    }

    int diameter=0;

    public int findDiameterRecurse(Node node) {
        if (node == null || (node.left == null && node.right == null)) {
            return 0;
        } else {
            int maxHeightL = findDiameterRecurse(node.left);
            int maxHeightR = findDiameterRecurse(node.right);
            int currDiameter = maxHeightL + maxHeightR + 1;
            if (currDiameter > diameter)
                diameter = currDiameter;
            return (1 + Math.max(maxHeightL, maxHeightR));
        }
    }

    public void printRootToLeafPath() {
        LinkedListStack.Stack<T> stack = new LinkedListStack.Stack<>();
        rootToLeafPath(root, stack);
    }

    private void rootToLeafPath(Node node, LinkedListStack.Stack<T> stack) {
        if (node == null) {
            return;
        } else if (node.left == null && node.right == null) {
            stack.push(node.data);
            stack.print();
            stack.pop();
            return;
        }
        // add the curr elem
        stack.push(node.data);
        rootToLeafPath(node.left, stack);
        // pop the last elem
        rootToLeafPath(node.right, stack);
        stack.pop();
    }

    public void checkPathSum(int sum) {
        LinkedListStack.Stack<T> stack = new LinkedListStack.Stack<>();
        if (checkPathSum(root, sum, stack)) {
            System.out.println("Path found with a sum of " + sum);
        } else {
            System.out.println("No path found with a sum of " + sum);
        }
        System.out.println("------------------------------");
    }

    private boolean checkPathSum(Node node, int sum, LinkedListStack.Stack<T> stack) {
        if (sum == 0) {
            // System.out.println("Yes, path found");
            stack.print();
            return true;
        } else if (node == null) {
            // System.out.println("Oops dead end!");
            return false;
        } else {
            sum = sum - node.data.intValue();
            stack.push(node.data);
            // System.out.println("Looking for sum of " + sum + " inside node " + node.data.toString());
            boolean res = checkPathSum(node.left, sum, stack) | checkPathSum(node.right, sum, stack);
            stack.pop();
            return res;
        }
    }

    public void findLeastCommonAncestor(T elem1, T elem2) {
        // Node n1 = getNode(elem1);
        // Node n2 = getNode(elem2);
        Node node = findLCA(root, elem1, elem2);
        System.out.println("Least Common Ancestor : " + node.data.toString());
    }

    private Node findLCA(Node node, T elem1, T elem2) {
        if (node == null) {
            return null;
        } else if (node.data.intValue() == elem1.intValue() || node.data.intValue() == elem2.intValue()) {
            return node;
        } else {
            Node left = findLCA(node.left, elem1, elem2);
            Node right = findLCA(node.right, elem1, elem2);
            if (left != null && right != null) {
                return node;
            } else {
                return (left != null) ? left : right;
            }
        }
    }

    public Node buildTreePreOrderAlt(List<T> inOrder, List<T> preOrder) {
        if (preOrder.size() == 0 || inOrder.size() == 0)
            return null;
        T elem = preOrder.get(0);
        preOrder.remove(0);
        Node node = new Node(elem);
        
        int idx = findElem(inOrder, elem);
        List<T> leftInorderTree = (idx > 0) ? new ArrayList<>(inOrder.subList(0, idx)) : new ArrayList<>();
        List<T> rightInorderTree = (idx < inOrder.size() - 1) ? new ArrayList<>(inOrder.subList(idx+1, inOrder.size())) : new ArrayList<>();
        
        List<T> leftPreorderTree = (leftInorderTree.size() == 0) ? new ArrayList<>() : new ArrayList<>(preOrder.subList(0, leftInorderTree.size()));
        List<T> rightPreorderTree = (rightInorderTree.size() == 0) ? new ArrayList<>() : new ArrayList<>(preOrder.subList(leftInorderTree.size(), preOrder.size()));

        node.left = buildTreePreOrderAlt(leftInorderTree, leftPreorderTree);
        node.right = buildTreePreOrderAlt(rightInorderTree, rightPreorderTree);
        return node;
    }

    public Node buildTreePostOrderAlt(List<T> inOrder, List<T> preOrder) {
        if (preOrder.size() == 0 || inOrder.size() == 0)
            return null;
        T elem = preOrder.get(preOrder.size()-1);
        preOrder.remove(preOrder.size()-1);
        Node node = new Node(elem);
        
        int idx = findElem(inOrder, elem);
        List<T> leftInorderTree = (idx > 0) ? new ArrayList<>(inOrder.subList(0, idx)) : new ArrayList<>();
        List<T> rightInorderTree = (idx < inOrder.size() - 1) ? new ArrayList<>(inOrder.subList(idx+1, inOrder.size())) : new ArrayList<>();
        
        List<T> leftPostorderTree = (leftInorderTree.size() == 0) ? new ArrayList<>() : new ArrayList<>(preOrder.subList(0, leftInorderTree.size()));
        List<T> rightPostorderTree = (rightInorderTree.size() == 0) ? new ArrayList<>() : new ArrayList<>(preOrder.subList(leftInorderTree.size(), preOrder.size()));

        node.left = buildTreePostOrderAlt(leftInorderTree, leftPostorderTree);
        node.right = buildTreePostOrderAlt(rightInorderTree, rightPostorderTree);
        return node;
    }

    public Node buildTreePreOrder(List<T> inOrder, List<T> preOrder) {
        if (preOrder.size() == 0 || inOrder.size() == 0)
            return null;
        T elem = preOrder.get(0);
        preOrder.remove(0);
        Node node = new Node(elem);
        
        int idx = findElem(inOrder, elem);
        List<T> leftTree = (idx > 0) ? inOrder.subList(0, idx) : new ArrayList<>();
        List<T> rightTree = (idx < inOrder.size() - 1) ? inOrder.subList(idx+1, inOrder.size()) : new ArrayList<>();
        
        node.left = buildTreePreOrder(leftTree, preOrder);
        node.right = buildTreePreOrder(rightTree, preOrder);
        return node;
    }

    private int findElem(List<T> list, T elem) {
        if (list.size() > 0) {
            int idx = 0;
            for (T node : list) {
                if (node.intValue() == elem.intValue())
                    return idx;
                idx++;
            }
        } 
        return -1;
    }

    public void maxPathSum() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            int max = maxPathSum(root);
            System.out.println("Max path sum is " + max);
        }
    }

    private int maxPathSum(Node node) {
        if (node == null)
            return 0;
        return Math.max(node.data.intValue() + maxPathSum(node.left), node.data.intValue() + maxPathSum(node.right));
    }

    public void findMaxPathSum() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            LinkedListStack.Stack<T> stack = new LinkedListStack.Stack<>();
            findMaxPathSum(root, maxPathSum(root), stack);
        }
    }
    
    private boolean findMaxPathSum(Node node, int sum, LinkedListStack.Stack<T> stack) {
        if (sum == 0) {
            // sum is zero so path found
            stack.print();
            return true;
        }
        if (node == null || sum < 0) {
            return false;
        }
        stack.push(node.data);
        boolean res= findMaxPathSum(node.left, sum - node.data.intValue(), stack) | findMaxPathSum(node.right, sum - node.data.intValue(), stack);
        stack.pop();
        return res;
    }

    public void findMinDepth() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            int minDepth = findMinDepth(root);
            System.out.println("Min depth is : " + minDepth);
        }
    }

    private int findMinDepth(Node node) {
        if (node == null) {
            return 0;
        } else {
            return Math.min(1 + findMinDepth(node.left), 1 + findMinDepth(node.right));
        }
    }

    public void findAllMinDepthNodes() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            QueueLinkedList<Integer> queue = new QueueLinkedList<>();
            findAllMinDepthNodes(root, findMinDepth(root), queue);
            System.out.println("Min Depth Leaves : ");
            queue.print();
        }
    }

    private void findAllMinDepthNodes(Node node, int depth, QueueLinkedList<Integer> queue) {
        if (node == null) {
            return;
        } else if (node.left == null && node.right == null && depth == 1) {
            queue.enqueue(node.data.intValue());
            return;
        }
        findAllMinDepthNodes(node.left, depth - 1, queue);
        findAllMinDepthNodes(node.right, depth - 1, queue);
    }

    public void flattenTree() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            flattenTree(root);
        }
    }

    // TODO Alternative way
    private Node flattenTreeAlt(Node node) {
        if (node == null)
            return null;
        Node left = flattenTreeAlt(node.left);
        Node right = flattenTreeAlt(node.right);
        return null;
    }

    protected Node flattenTree(Node node) {
        if (node != null) {
            Node left = null, right = null;
            if (node.left == null && node.right != null) {
                return node;
            } if (node.left != null) {
                left = flattenTree(node.left);
            }
            if (node.right != null) {
                right = flattenTree(node.right);
                if (left != null) {
                    Node leftTop = left;
                    while (left.right != null)
                        left = left.right;
                    left.right = right;
                    left = leftTop;
                } else {
                    left = right;
                }
            }
            node.left = null;
            node.right = left;
        }
        return node;
    }

    public void findLastElement() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            T elem = findLastElement(root);
            System.out.println("Last element is : " + elem.toString());
        }
    }

    private T findLastElement(Node node) {
        if (node == null)
            return null;
        if (node.left == null && node.right == null) {
            return node.data;
        }
        T elem = findLastElement(node.right);
        if (elem == null)
            elem = findLastElement(node.left);
        return elem;
    }

    public void serialize() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            QueueLinkedList<Node> queue = new QueueLinkedList<>();
            queue.enqueue(root);
            T lastElem = findLastElement(root);
            String s = "";
            boolean isEnd = false;
            while (queue.size > 0 && !isEnd) {
                int size = queue.size;
                for (int idx=0; idx < size; idx++) {
                    Node curr = queue.dequeue();
                    if (curr == null) {
                        s = s + "null" + " ";
                    }   else {
                        s = s + curr.data + " ";
                        if (curr.data.intValue() == lastElem.intValue()) {
                            isEnd = true;
                            break;
                        }
                    }
                    queue.enqueue((curr == null) ? null : curr.left);
                    queue.enqueue((curr == null) ? null : curr.right);
                }
            }
            System.out.println("Serialized binary tree is : " + s);
        }
    }

    public void deserialize(T[] arr) {
        QueueLinkedList<Node> queue = new QueueLinkedList<>();
        root = new Node(arr[0]);
        Node curr = root;
        for (int idx=1; idx < arr.length; idx++) {
            if (curr == null || arr[idx] == null) {
                queue.enqueue(null);
                if (idx%2 == 0)
                    curr = queue.dequeue();
            } else if (curr.left == null && idx%2 == 1 && arr[idx] != null) {
                curr.left = new Node(arr[idx]);
                queue.enqueue(curr.left);
            } else if (curr.right == null && idx%2 == 0 && arr[idx] != null) {
                curr.right = new Node(arr[idx]);
                queue.enqueue(curr.right);
                curr = queue.dequeue();
            }
        }
    }

    public void connectSiblings() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            QueueLinkedList<Node> queue = new QueueLinkedList<>();
            queue.enqueue(root);
            Node curr = null, prev = null;
            while (queue.size > 0) {
                int size = queue.size;
                for (int idx=0; idx < size; idx++) {
                    prev = curr;
                    curr = queue.dequeue();
                    if (prev != null)
                        prev.nextSibling = curr;
                    if (curr.left != null)
                        queue.enqueue(curr.left);
                    if (curr.right != null)
                        queue.enqueue(curr.right);
                }
            }
        }
    }

    public void siblingLevelOrder() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            Node node = root;
            String s = "";
            while (node != null) {
                s = s + node.data + " ";
                node = node.nextSibling;
            }
            System.out.println("Sibling Level Order : " + s);
        }
    }

    int maxHeight = 0;
    String view = "";
    public void allViews() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            maxHeight = 0;
            view = "";
            leftView(root, 0);
            System.out.println("Left view of the tree : " + view);
            maxHeight = 0;
            view = "";
            rightView(root, 0);
            System.out.println("Right view of the tree : " + view);
            Map<Integer, T> map = new TreeMap<>();
            topView(root, 0, map);
            System.out.println("Top view of the tree : " + Arrays.toString(map.values().toArray()));
            map = new TreeMap<>();
            bottomView(root, 0, map);
            System.out.println("Bottom view of the tree : " + Arrays.toString(map.values().toArray()));
        }
    }

    private int leftView(Node node, int height) {
        if (node == null)
            return height;
        if (height >= maxHeight) {
            view = view + node.data + " ";
            maxHeight = height + 1;
        }
        return Math.max(leftView(node.left, height+1), leftView(node.right, height+1));
    }

    private int rightView(Node node, int height) {
        if (node == null)
            return height;
        if (height == maxHeight) {
            view = view + node.data + " ";
            maxHeight++;
        }
        return Math.max(rightView(node.right, height+1), rightView(node.left, height+1));
    }

    private void topView(Node node, int distance, Map<Integer, T> map) {
        if (node == null)
            return;
        if (!map.containsKey(distance)) {
            map.put(distance, node.data);
        }
        topView(node.left, distance - 1, map);
        topView(node.right, distance + 1, map);
    }

    private void bottomView(Node node, int distance, Map<Integer, T> map) {
        if (node == null)
            return;
        map.put(distance, node.data);
        bottomView(node.left, distance-1, map);
        bottomView(node.right, distance+1, map);
    }

    
    public void verticalTraversal() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            Map<Integer, QueueLinkedList<T>> map = new TreeMap<>();
            QueueLinkedList<Integer> dist = new QueueLinkedList<>();
            QueueLinkedList<Node> queue = new QueueLinkedList<>();
            queue.enqueue(root);
            dist.enqueue(0);
            while (queue.size > 0 && dist.size > 0) {
                Node curr = queue.dequeue();
                int currDist = dist.dequeue();
                if (curr.left != null) {
                    queue.enqueue(curr.left);
                    dist.enqueue(currDist - 1);
                }
                if (curr.right != null) {
                    queue.enqueue(curr.right);
                    dist.enqueue(currDist + 1);
                }
                QueueLinkedList<T> mapQueue = null;
                if (map.containsKey(currDist)) {
                    mapQueue = map.get(currDist);
                } else {
                    mapQueue = new QueueLinkedList<>();
                }
                mapQueue.enqueue(curr.data);
                map.put(currDist, mapQueue);
            }
            System.out.println("Vertical Order Traversal : ");
            for (QueueLinkedList<T> mapQueue : map.values()) {
                mapQueue.print();
            }
        }
    }
    
    // Downside here is that the order gets messed up sometimes, 
    // like when the left half branches extend to the right side
    public void verticalTraversalAlt() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            Map<Integer, LinkedList<T>> map = new TreeMap<>();
            verticalTraversal(root, 0, map);
            System.out.println("Vertical Order Traversal:");
            for (LinkedList<T> ll : map.values()) {
                ll.reverse();
                String s = "";
                while (ll.head != null) {
                    s = s + ll.head.data + " ";
                    ll.head = ll.head.next;
                } 
                System.out.println(s);
            }
        }
    }

    private void verticalTraversal(Node node, int distance, Map<Integer, LinkedList<T>> map) {
        if (node == null)
            return;
        if (map.containsKey(distance)) {
            LinkedList<T> ll = map.get(distance);
            ll.add(node.data);
        } else {
            LinkedList<T> ll = new LinkedList<>();
            ll.add(node.data);
            map.put(distance, ll);
        }
        verticalTraversal(node.left, distance-1, map);
        verticalTraversal(node.right, distance+1, map);
    }

    // Inorder without stack/recursion
    public void morrisInorder() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            Node curr = root;
            String s = "";
            while (curr != null) {
                if (curr.left == null) {
                    s = s + curr.data + " ";
                    curr = curr.right;
                } else {
                    Node last = curr.left;
                    while (last.right != null && last.right != curr)
                        last = last.right;
                    if (last.right == null) {
                        last.right = curr;
                        curr = curr.left;
                    } else {
                        last.right = null;
                        s = s + curr.data + " ";
                        curr = curr.right;
                    }
                    
                }
            }
            System.out.println("Morris Inorder : " + s);
        }
    }

    // Preorder without stack/recursion
    public void morrisPreorder() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            Node curr = root;
            String s = "";
            while (curr != null) {
                if (curr.left == null) {
                    s = s + curr.data + " ";
                    curr = curr.right;
                } else {
                    Node last = curr.left;
                    while (last.right != null && last.right != curr)
                        last = last.right;
                    if (last.right == null) {
                        last.right = curr;
                        s = s + curr.data + " ";
                        curr = curr.left;
                    } else {
                        curr = curr.right;
                        last.right = null;
                    }
                }
            }
            System.out.println("Morris Preorder : " + s);
        }
    }

    public void morrisPostorder() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            // Node curr = root;
            String s = "";
            // ensures all descendants of root that are right-children
            //  (arrived at only by "recurring to right") get inner-threaded
            final Node fakeNode = new Node(null);    // prefer not to give data, but construction requires it as primitive
            fakeNode.left = root;

            Node curOuter = fakeNode;
            while (curOuter != null) {    // in addition to termination condition, also prevents fakeNode printing
                if (curOuter.left != null) {
                    final Node curOuterLeft = curOuter.left;

                    // Find in-order predecessor of curOuter
                    Node curOuterInOrderPred = curOuterLeft;
                    while (curOuterInOrderPred.right != null && curOuterInOrderPred.right != curOuter) {
                        curOuterInOrderPred = curOuterInOrderPred.right;
                    }

                    if (curOuterInOrderPred.right == null) {
                        // [Outer-] Thread curOuterInOrderPred to curOuter
                        curOuterInOrderPred.right = curOuter;

                        // "Recur" on left
                        curOuter = curOuterLeft;

                    } else {    // curOuterInOrderPred already [outer-] threaded to curOuter
                                //  -> [coincidentally] therefore curOuterLeft's left subtree is done processing
                        // Prep for [inner] threading (which hasn't ever been done yet here)...
                        Node curInner = curOuterLeft;
                        Node curInnerNext = curInner.right;
                        // [Inner-] Thread curOuterLeft [immediately backwards] to curOuter [its parent]
                        curOuterLeft.right = curOuter;
                        // [Inner-] Thread the same [immediately backwards] for all curLeft descendants
                        //  that are right-children (arrived at only by "recurring" to right)
                        while (curInnerNext != curOuter) {
                            // Advance [inner] Node refs down 1 level (to the right)
                            final Node backThreadPrev = curInner;
                            curInner = curInnerNext;
                            curInnerNext = curInnerNext.right;
                            // Thread immediately backwards
                            curInner.right = backThreadPrev;
                        }

                        // curInner, and all of its ancestors up to curOuterLeft,
                        //  are now indeed back-threaded to each's parent
                        // Print them in that order until curOuter
                        while (curInner != curOuter) {
                            /*
                                -> VISIT
                            */
                            s = s + curInner.data + " ";

                            // Move up one level
                            curInner = curInner.right;
                        }

                        // "Recur" on right
                        curOuter = curOuter.right;
                    }

                } else {
                    // "Recur" on right
                    curOuter = curOuter.right;
                }
            }
            System.out.println("Morris Postorder : " + s);
        }
    }

    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        // Generate tree from inorder & preorder (D)
        // Generate tree from inorder & postorder (D)
        // Serialize and deserialize from binary array (D)
        // Max path sum (D)

        // binaryTree.root = binaryTree.buildTreePreOrderAlt(new ArrayList<>(Arrays.asList(4, 2, 5, 1, 6, 3, 7)), new ArrayList<>(Arrays.asList(1, 2, 4, 5, 3, 6, 7)));
        // binaryTree.root = binaryTree.buildTreePostOrderAlt(new ArrayList<>(Arrays.asList(4, 2, 5, 1, 6, 3, 7)), new ArrayList<>(Arrays.asList(4, 5, 2, 6, 7, 3, 1)));
        // binaryTree.root = binaryTree.buildTreePreOrderAlt(new ArrayList<>(Arrays.asList(4, 2, 5, 1, 3)), new ArrayList<>(Arrays.asList(1, 2, 4, 5, 3)));
        
        // binaryTree.addNode(1);
        // binaryTree.addNode(2);
        // binaryTree.addNode(3);
        // binaryTree.addNode(4);
        // binaryTree.addNode(5);
        // binaryTree.addNode(6);
        // binaryTree.serialize();
        // binaryTree.printTree();
        // binaryTree.mirrorTree();
        // binaryTree.flattenTree();
        // binaryTree.serialize();
        // binaryTree.deserialize(new Integer[]{1, 2, 3, null, 5, null, 7, 8, null, 9, 10, 11, null, 12});
        // binaryTree.deserialize(new Integer[]{1, 2, 3, null, 2, 4, 7, 8, null, 3, null, 11, null, null, 12});
        binaryTree.deserialize(new Integer[]{1, 2, 3, null, 5, null, null, null, null, 6, 7});
        binaryTree.printTree();
        binaryTree.morrisInorder();
        binaryTree.inOrderTraverse();
        binaryTree.morrisPreorder();
        binaryTree.preOrderTraverse();
        binaryTree.morrisPostorder();
        // binaryTree.postOrderTraverse();
        // binaryTree.allViews();
        // binaryTree.verticalTraversal();
        // binaryTree.connectSiblings();
        // binaryTree.siblingLevelOrder();
        // binaryTree.serialize();
        // binaryTree.addNode(7);
        // binaryTree.printTree();
        // binaryTree.addNode(8);
        // binaryTree.flattenTree();
        // binaryTree.printTree();
        // binaryTree.findLastElement();
        
        // binaryTree.printTree();
        // binaryTree.findMinDepth();
        // binaryTree.findAllMinDepthNodes();
        // Print nodes at dist k from root (isn't that just level order by level ??)
        // binaryTree.printTree();
        // binaryTree.maxPathSum();
        // binaryTree.findMaxPathSum();
        // binaryTree.printTree();
        // binaryTree.preOrderTraverse();
        // binaryTree.stackPreOrder();
        // binaryTree.stackPreOrderAlt();
        // binaryTree.inOrderTraverse();
        // binaryTree.stackInOrder();
        // binaryTree.postOrderTraverse();
        // binaryTree.stackPostOrder();
        // binaryTree.stackPostOrderAlt();


        // binaryTree.mirrorTree();
        // boolean b = binaryTree.isStrictTree();
        // System.out.println(b);
        // b = binaryTree.isFullTree();
        // System.out.println(b);
        // b = binaryTree.isCompleteTree();
        // System.out.println(b);
        // binaryTree.zigzagTraverse();
        // binaryTree.findNumberOfFullNodes();
        // binaryTree.findNumberOfHalfNodes();
        // BinaryTree<Integer> binaryTree2 = new BinaryTree<>();
        // binaryTree2.addNode(1);
        // binaryTree2.addNode(2);
        // binaryTree2.addNode(3);
        // binaryTree2.addNode(4);
        // binaryTree2.addNode(5);
        // binaryTree2.addNode(6);
        // binaryTree2.addNode(7);
        // binaryTree.checkSameStructure(binaryTree2);
        // binaryTree.checkSameTree(binaryTree2);
        // binaryTree2.mirrorTree();
        // binaryTree.preOrderTraverse();
        // binaryTree2.reversePreOrderTraverse();
        // binaryTree2.preOrderTraverse();
        // binaryTree.checkSameStructure(binaryTree2);
        // binaryTree.checkMirrorTree(binaryTree2);
        // binaryTree.mirrorTree();
        // binaryTree.checkSameStructure(binaryTree2);

        // binaryTree.levelOrderTraverse();
        // binaryTree2.levelOrderTraverse();
        // binaryTree.checkMirrorTree(binaryTree2);
        // binaryTree.checkSameTree(binaryTree2);
        
        // binaryTree.levelOrderTraverse();
        // binaryTree.findMaxNode();
        // binaryTree.findNode(3);
        // binaryTree.findNode(5);
        // binaryTree.getTreeSize();
        // binaryTree.reverseLevelOrder();
        // binaryTree.getTreeHeight();
        // binaryTree.getNodeHeight(17);
        // binaryTree.getTreeDepth();
        // binaryTree.findDeepestNode();
        // binaryTree.countLeafNodes();
        // binaryTree.findDiameter();
        // binaryTree.printRootToLeafPath();
        // binaryTree.checkPathSum(2);
        // binaryTree.findLeastCommonAncestor(4, 5);
        // binaryTree.findLeastCommonAncestor(4, 7);
        // binaryTree.findLeastCommonAncestor(2, 7);
        // binaryTree.findLeastCommonAncestor(3, 6);
        // binaryTree.printLeafNodes();

        // binaryTree.getNodeDepth(17);
        // binaryTree.getNodeDepth(1);
        // binaryTree.getNodeDepth(2);
        // binaryTree.getNodeDepth(3);
        // binaryTree.getNodeDepth(4);
        // binaryTree.getNodeDepth(5);
        // binaryTree.getNodeDepth(6);
        // binaryTree.getNodeDepth(7);
        // binaryTree.getNodeDepth(8);
        // binaryTree.getNodeHeight(1);
        // binaryTree.getNodeHeight(2);
        // binaryTree.getNodeHeight(3);
        // binaryTree.getNodeHeight(4);
        // binaryTree.getNodeHeight(5);
        // binaryTree.getNodeHeight(6);
        // binaryTree.getNodeHeight(7);
        // binaryTree.getNodeHeight(8);
    }
}