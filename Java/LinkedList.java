public class LinkedList {

    public static class Node {
        int data;
        Node next;
        public Node(int data, Node node) {
            this.data = data;
            this.next = node;
        }
    }

    public static class LL {
        Node head;
        int size;

        public void add(int data) {
            Node node = new Node(data, null);
            if (head == null){
                head = node;
            } else {
                node.next = head;
                head = node;
            }
            size++;
            System.out.println("Added node of value " + data);
            // print();
        }

        public void print() {
            Node top = head;
            String s = "HEAD => ";
            while (top!=null){
                s = s + top.data + " => ";
                top = top.next;
            }
            s = s + "null";
            System.out.println(s);
        }

        public void print(Node node) {
            Node top = node;
            String s = "HEAD => ";
            while (top!=null){
                s = s + top.data + " => ";
                top = top.next;
            }
            s = s + "null";
            System.out.println(s);
        }

        public void remove() {
            if (head == null) {
                System.out.println("List is empty!");
            } else {
                System.out.println("Removed node of value " + head.data);
                head = head.next;
            }
            size--;
            print();
        }

        public void addLoop(int pos) {
            if (pos < size) {
                int curr = 0;
                Node loopnode = head;
                Node lastnode = head;
                while (lastnode.next != null) {
                    if (curr < pos)
                        loopnode = loopnode.next;
                    curr++;
                    lastnode = lastnode.next;
                }
                lastnode.next = loopnode;
                System.out.println("Added a loop with from last node of value " + lastnode.data + " to node of value " + loopnode.data + "!");
            } else {
                System.out.println("Couldn't add a loop at the given position!");
            }
        }

        public void addBottom(int data) {
            Node node = head;
            while (node.next != null)
                node = node.next;
            node.next = new Node(data, null);
            ++size;
            System.out.println("Added at the bottom node of value " + data);
            print();
        }

        public void findNthEndNode(int n) {
            if (n <= size && n > 0) {
                Node ahead = head;
                Node node = head;
                int cnt = 0;
                while (ahead.next != null) {
                    ahead = ahead.next;
                    if (cnt >= (n-1)) {
                        node = node.next;
                    }
                    cnt++;
                }
                System.out.println(n + "th element from the end is " + node.data);
            } else {
                System.out.println("List of size " + size + " is smaller than the passed value " + n);
            }
        }

        public void attach(LL ll, int pos) {
            System.out.println("Attaching linked list");
            ll.print();
            System.out.println("Source linked list");
            this.print();
            if (pos < this.size && this.size > 0 && ll.size > 0) {
                int curr = 1;
                Node node = this.head;
                while (curr < pos) {
                    node = node.next;
                    curr++;
                }
                Node lastNode = ll.head;
                while (lastNode.next != null) {
                    lastNode = lastNode.next;
                }
                lastNode.next = node;
                System.out.println("Linked list have been merged!");
                ll.print();
            } else {
                System.out.println("Invalid case!");
            }
        }

        public void reverse() {
            System.out.println("----------------------------");
            if (size > 0) {
                Node curr, left, right;
                curr = head;
                left = null;
                while (curr != null) {
                    right = curr.next;
                    curr.next = left;
                    left = curr;
                    curr = right;
                }
                head = left;
                System.out.println("List has been reversed!");
                print();
            } else {
                System.out.println("List is empty!");
            }
            System.out.println("----------------------------");
        }

        public void recurseReverse() {
            System.out.println("----------------------------");
            System.out.println("Attempting recurse reverse");
            recurse(head);
            print();
            System.out.println("----------------------------");
        }
        

        public void reverse(int k) {
            System.out.println("--------------------------------");
            System.out.println("Reversing by block of size " + k);
            if (size > 0 && k > 0 && k <= size) {
                int count = 1;
                Node start, next, prev = null, actHead = head;
                start = head;
                Node node = head;
                while (node != null) {
                    if (count % k == 0) {
                        node = node.next;
                        Node tail = recurse(start, 1, k);
                        tail.next = node;
                        if (prev == null) {
                            actHead = head;
                        } else {
                            prev.next = head;
                        }
                        tail.next = node;
                        prev = tail;
                        start = tail.next;
                        count = 1;
                    } else {
                        count = count + 1;
                        node = node.next;
                    }
                }
                head = actHead;
                print();
            } else {
                System.out.println("Invalid case!");
            }
            System.out.println("--------------------------------");
        }

        public Node recurse(Node node, int count, int k) {
            if (node.next == null || count >= k) {
                head = node;
                return node;
            } else {
                count = count + 1;
                Node rec = recurse(node.next, count, k);
                rec.next = node;
                node.next = null;
                return node;
            }

        }

        private Node recurse(Node node) {
            if (node.next == null) {
                head = node;
                return node;
            } else {
                Node top = node;
                Node rec = recurse(node.next);
                rec.next = top;
                top.next = null;
                return top;
            }
        }

        private Node recurse(Node node, Node[] head) {
            if (node.next == null) {
                head[0] = node;
            } else {
                Node rec = recurse(node.next, head);
                rec.next = node;
                node.next = null;
            }
            return node;
        }

        public void isPalindrome() {
            Node slow = head;
            Node fast = head;
            Node[] headArr = new Node[1];
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            print(head);
            System.out.println("Second half of the linked list");
            print(slow.next);
            recurse(slow.next, headArr);
            System.out.println("Reversed second half");
            print(headArr[0]);
            Node h1 = head, h2 = headArr[0];
            boolean isPalindrome = true;
            while (h1 != null && h2 != null) {
                isPalindrome = isPalindrome & (h1.data == h2.data);
                h1 = h1.next;
                h2 = h2.next;
            }
            // recurse(slow.next, headArr);
            slow.next = headArr[0];
            if (isPalindrome) {
                System.out.println("Yes its a palindrome!");
            } else {
                System.out.println("No its not a palindrome!");
            }
            print();
        }

        public void pushOddOnesBack() {
            // System.out.println("----------------------");
            // print();
            // System.out.println("----------------------");
            Node node = head, odd = null;
            Node prev = null, next = node.next;
            Node[] headArr = new Node[2];
            headArr[0] = head;
            headArr[1] = null;
            while (node != null) {
                // System.out.println("----------------------");
                // System.out.println("Curr Node : " + node.data);
                if (node.data % 2 == 1) {
                    // delete node from list
                    // System.out.println("Deleting from even list");
                    // System.out.println("Prev : " +  ((prev != null) ? prev.data + "" : "null"));
                    // System.out.println("Node : " + node.data);
                    // System.out.println("Next : " + ((next != null) ? next.data + "" : "null"));
                    node.next = null;
                    if (prev != null) {
                        prev.next = next;
                    } else {
                        headArr[0] = next;
                        prev = null;
                    }
                    // print(headArr[0]);

                    // append to an odd list
                    // System.out.println("Adding to odd list");
                    // System.out.println("Prev : " + ((prev != null) ? prev.data + "" : "null"));
                    // System.out.println("Node : " + node.data);
                    // System.out.println("Next : " + ((next != null) ? next.data + "" : "null"));
                    if (odd == null) {
                        odd = node;
                        headArr[1] = odd;
                    } else {
                        odd.next = node;
                        odd = odd.next;
                    }
                    odd.next = null;
                    // print(headArr[1]);
                } else {
                    if (prev == null)
                        prev = headArr[0];
                    else
                        prev = prev.next;
                }
                // continue to next elem
                // if (prev == null)
                //     prev = node;
                node = next;
                if (node != null)
                    next = node.next;
                
                // System.out.println("Prev : " + ((prev != null) ? prev.data + "" : "null"));
                // System.out.println("Node : " + ((node != null) ? node.data + "" : "null"));
                // System.out.println("Next : " + ((next != null) ? next.data + "" : "null"));
            }
            System.out.println("----------------------");
            print(headArr[0]);
            print(headArr[1]);
            System.out.println("----------------------");
            if (prev != null && headArr[1] != null)
                prev.next = headArr[1];
            if (headArr[0] != null)
                head = headArr[0];
            print();
            System.out.println("----------------------");
        }

        // N % k
        public void findModularNode(int k) {
            Node slow = head;
            Node fast = head;
            int count = 0;
            while (fast != null) {
                if (count == k) {
                    slow = head;
                    count = 0;
                } else {
                    count++;
                }
                if (fast.next == null)
                    break;
                fast = fast.next;
                slow = slow.next;
            }
            System.out.println("N % " + k + " node is " + slow.data);
        }

        public void findFractionalNode(int k) {
            Node slow = head;
            Node fast = head;
            int count = 1;
            while (fast != null) {
                if (count == k) {
                    slow = slow.next;
                    count = 1;
                } else {
                    count++;
                }
                fast = fast.next;
            }
            System.out.println("Fractional node for k=" + k + " is of value " + slow.data);
        }

        public void findRootNthNode() {
            Node slow = head;
            Node fast = head;
            int count = 1;
            int bucket = 1;
            while (fast != null) {
                if (count >= bucket*bucket){
                    bucket++;
                    slow = slow.next;
                }
                fast = fast.next;
                count++;
            }
            System.out.println("Root Nth node is of value " + slow.data);
        }

        public void sort() {
            if (size > 1) {
                Node front = head;
                Node rear = head;
                Node curr = rear.next;
                Node next;
                while (curr != null) {
                    next = curr.next;
                    if (curr.data > rear.data) {
                        rear.next = curr;
                    } else if (curr.data < front.data) {
                        curr.next = front;
                        front = curr;
                    } else {
                        Node prev = front;
                        Node shift = front.next;
                        while (shift != rear) {
                            if (curr.data < shift.data) {
                                prev.next = curr;
                                curr.next = shift;
                                break;
                            }
                        }
                    }
                    curr = next;
                }
                head = front;
                rear.next = null;
                print();
            }
        }

    }
    public static void main(String[] args) {
        LL list = new LL();
        list.add(1);
        list.add(2);
        // list.add(3);
        // list.add(4);
        // list.add(5);
        // list.add(6);
        // list.add(7);
        // list.add(8);
        list.print();
        list.sort();
        // System.out.println("SIZE : " + list.size);
        // list.findRootNthNode();
        // list.findFractionalNode(2);
        // list.pushOddOnesBack();
        // list.add(1);
        // list.add(2);
        // list.add(3);
        // list.add(4);
        // list.print();
        // list.pushOddOnesBack();
        // list.isPalindrome();
        // list.recurseReverse();
        // list.add(5);
        // list.add(6);
        // list.add(7);
        // list.add(8);
        // list.add(9);
        // list.print();
        // System.out.println("SIZE : " + list.size);
        // list.findModularNode(7);
        // list.pushOddOnesBack();
        // list.isPalindrome();
        // list.recurse(list.head, 1, 3);
        // list.print();
        // list.reverse(3);
        // list.addBottom(12);
        // list.addBottom(32);
        // list.findNthEndNode(1);
        // list.findNthEndNode(3);
        // list.findNthEndNode(100);
        // list.findNthEndNode(5);
        // list.findNthEndNode(8);
        // list.reverse();
        // list.remove();
        // list.remove();
        // list.remove();
        // list.remove();
        // list.remove();
        // list.remove();
        // list.remove();
        // list.remove();
        // list.remove();
    }
}