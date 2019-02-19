public class BinaryTree<T extends Number> {

    private class TreeNode {
        T data;
        TreeNode left;
        TreeNode right;
        public TreeNode(T data){
            this.data = data;
        }
    }

    TreeNode root;

    public void addNode(T elem) {
        TreeNode node = new TreeNode(elem);
        if (root == null) {
            root = node;
        } else {
            // Level order add
            QueueLinkedList<TreeNode> queue = new QueueLinkedList<>();
            queue.enqueue(root);
            while (queue.size > 0) {
                TreeNode curr = queue.dequeue();
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

    public void removeNode(T elem) {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            // Level order delete
            // QueueLinkedList<TreeNode> queue = new QueueLinkedList<>();
            
        }
    }

    public void deleteTree() {
        root = null; // gc takes care of the rest
    }

    public void printTree() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            QueueLinkedList<TreeNode> queue = new QueueLinkedList<>();
            queue.enqueue(root);
            int level = 0;
            while (queue.size > 0) {
                String s = "";
                int size = queue.size;
                for (int idx=0; idx < size; idx++) {
                    TreeNode node = queue.dequeue();
                    if (node != null) {
                        s = s + node.data.toString() + " ";
                    } else {
                        s = s + "null" + " ";
                        continue;
                    }
                    // if (node.left != null)
                    queue.enqueue(node.left);
                    // if (node.right != null)
                    queue.enqueue(node.right);
                }
                System.out.println("Level " + level + " : " + s);
                level++;
            }            
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

    private int getTreeSizeRecursion(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return getTreeSizeRecursion(node.left) + getTreeSizeRecursion(node.right) + 1;
        }
    }

    private int getTreeSizeLevelOrder(TreeNode node) {
        QueueLinkedList<TreeNode> queue = new QueueLinkedList<>();
        int size = 0;
        if (node != null) {
            queue.enqueue(node);
            while (queue.size > 0) {
                TreeNode curr = queue.dequeue();
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

    private boolean findNodeRecurse(TreeNode node, T elem) {
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

    private boolean findNodeLevelOrder(TreeNode node, T elem) {
        boolean isFound = false;
        if (node != null) {
            QueueLinkedList<TreeNode> queue = new QueueLinkedList<>();
            queue.enqueue(node);
            while (queue.size > 0 && !isFound) {
                TreeNode curr = queue.dequeue();
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

    private TreeNode getNode(T elem) {
        TreeNode curr = null;
        boolean isFound = false;
        if (root != null) {
            QueueLinkedList<TreeNode> queue = new QueueLinkedList<>();
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
            TreeNode curr = root;
            QueueLinkedList<TreeNode> queue = new QueueLinkedList<>();
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

    private int countFullNodes(TreeNode node) {
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

    private int countHalfNodes(TreeNode node) {
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

    private boolean checkSameStructure(TreeNode n1, TreeNode n2) {
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

    private boolean checkSameTree(TreeNode n1, TreeNode n2) {
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

    private boolean checkMirrorTree(TreeNode n1, TreeNode n2) {
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

    private void mirrorTreeRecurse(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode temp = node.right;
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
    private int getNodeDepthRecursion(TreeNode root, TreeNode node) {
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
    private int getNodeHeightRecursion(TreeNode node) {
        if (node == null) {
            return -1;
        } else {
            return Math.max(1+getNodeHeightRecursion(node.left), 1+getNodeHeightRecursion(node.right));
        }
    }

    private int getNodeDepthLevelOrder(TreeNode root, TreeNode node) {
        int startLevel = 0;
        int endLevel = 0;
        int currLevel = 0;
        if (root != null) {
            QueueLinkedList<TreeNode> queue = new QueueLinkedList<>();
            queue.enqueue(root);
            TreeNode curr;
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

    private int getNodeHeightLevelOrder(TreeNode node) {
        int startLevel = 0;
        int endLevel = 0;
        int currLevel = 0;
        if (root != null) {
            QueueLinkedList<TreeNode> queue = new QueueLinkedList<>();
            queue.enqueue(node);
            TreeNode curr;
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
        TreeNode node = getNode(elem);
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
        TreeNode node = getNode(elem);
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
            QueueLinkedList<TreeNode> queue = new QueueLinkedList<>();
            queue.enqueue(root);
            boolean nullFound = false;
            while (queue.size > 0) {
                int size = queue.size;
                for (int idx=0; idx < size; idx++) {
                    TreeNode node = queue.dequeue();
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

    private String reversePreOrderTraverse(TreeNode node) {
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

    private String preOrderTraverse(TreeNode node) {
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
            LinkedListStack.Stack<TreeNode> stack = new LinkedListStack.Stack<>();
            stack.push(root);
            String s = "";
            while (stack.size > 0) {
                TreeNode node = stack.pop();
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
            LinkedListStack.Stack<TreeNode> stack = new LinkedListStack.Stack<>();
            TreeNode node = root;
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

    private String inOrderTraverse(TreeNode node) {
        if (node == null) {
            return "";
        }
        return inOrderTraverse(node.left) + node.data.toString() + " " + inOrderTraverse(node.right);
    }

    public void stackInOrder() {
        if (root == null) {
            System.out.println("Tree is empty!");
        } else {
            LinkedListStack.Stack<TreeNode> stack = new LinkedListStack.Stack<>();
            TreeNode node = root;
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

    private String postOrderTraverse(TreeNode node) {
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
            LinkedListStack.Stack<TreeNode> stack = new LinkedListStack.Stack<>();
            stack.push(root);
            String s = "";
            TreeNode prev = null;
            while (stack.size > 0) {
                TreeNode curr = stack.peek();
                if (prev == null || prev.left == curr || prev.right == curr) {
                    if (curr.left != null)
                        stack.push(curr.left);
                    else if (curr.right != null)
                        stack.push(curr.right);
                } else if (curr.left == prev) {
                    if (curr.right != null)
                        stack.push(curr.right);
                } else {
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
            LinkedListStack.Stack<TreeNode> stack = new LinkedListStack.Stack<>();
            TreeNode node = root;
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
            QueueLinkedList<TreeNode> queue = new QueueLinkedList<>();
            queue.enqueue(root);
            String s = "";
            while (queue.size > 0) {
                int size = queue.size;
                for (int idx=0; idx < size; idx++) {
                    TreeNode node = queue.dequeue();
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
            LinkedListStack.Stack<TreeNode> stack = new LinkedListStack.Stack<>();
            QueueLinkedList<TreeNode> queue = new QueueLinkedList<>();
            queue.enqueue(root);
            TreeNode node;
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
            QueueLinkedList<TreeNode> queue = new QueueLinkedList<>();
            queue.enqueue(root);
            int level = 0;
            while (queue.size > 0) {
                int size = queue.size;
                String s = "";
                LinkedListStack.Stack<TreeNode> stack = new LinkedListStack.Stack<>();
                for (int idx=0; idx < size; idx++) {
                    TreeNode node = queue.dequeue();
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

    private String printLeavesRecurse(TreeNode node) {
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

    private int countLeafNodesRecurse(TreeNode node) {
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
            QueueLinkedList<TreeNode> queue = new QueueLinkedList<>();
            queue.enqueue(root);
            while (queue.size > 0) {
                TreeNode curr = queue.dequeue();
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

    private int findMaxRecurse(TreeNode root, int max) {
        if (root == null) {
            return max;
        } else if (root.data.intValue() > max) {
            max = root.data.intValue();
        }
        max = findMaxRecurse(root.left, max);
        max = findMaxRecurse(root.right, max);
        return max;
    }

    private int findMaxLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("Tree is empty!");
            return -1;
        } else {
            QueueLinkedList<TreeNode> queue = new QueueLinkedList<>();
            queue.enqueue(root);
            int max = -1;
            while (queue.size > 0) {
                TreeNode node = queue.dequeue();
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

    public int findDiameterRecurse(TreeNode node) {
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

    private void rootToLeafPath(TreeNode node, LinkedListStack.Stack<T> stack) {
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

    private boolean checkPathSum(TreeNode node, int sum, LinkedListStack.Stack<T> stack) {
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
        // TreeNode n1 = getNode(elem1);
        // TreeNode n2 = getNode(elem2);
        TreeNode node = findLCA(root, elem1, elem2);
        System.out.println("Least Common Ancestor : " + node.data.toString());
    }

    private TreeNode findLCA(TreeNode node, T elem1, T elem2) {
        if (node == null) {
            return null;
        } else if (node.data.intValue() == elem1.intValue() || node.data.intValue() == elem2.intValue()) {
            return node;
        } else {
            TreeNode left = findLCA(node.left, elem1, elem2);
            TreeNode right = findLCA(node.right, elem1, elem2);
            if (left != null && right != null) {
                return node;
            } else {
                return (left != null) ? left : right;
            }
        }
    }

    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.addNode(1);
        binaryTree.addNode(2);
        binaryTree.addNode(3);
        binaryTree.addNode(4);
        binaryTree.addNode(5);
        binaryTree.addNode(6);
        binaryTree.addNode(7);
        binaryTree.addNode(8);
        binaryTree.printTree();
        binaryTree.preOrderTraverse();
        binaryTree.stackPreOrder();
        binaryTree.stackPreOrderAlt();
        binaryTree.inOrderTraverse();
        binaryTree.stackInOrder();
        binaryTree.postOrderTraverse();
        binaryTree.stackPostOrder();
        binaryTree.stackPostOrderAlt();


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