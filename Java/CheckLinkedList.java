
public class CheckLinkedList {

    public static void findMerge(LinkedList.LL ll1, LinkedList.LL ll2) {
        System.out.println("=============================");
        if (ll1.size > 0 && ll2.size > 0) {
            LinkedList.Node node1 = ll1.head;
            LinkedList.Node node2 = ll2.head;
            int count = 1;
            while (node1 != node2) {
                if (node1.next == null) {
                    node1 = ll2.head;
                    count = 0;
                } else {
                    node1 = node1.next;
                }
                if (node2.next == null) {
                    node2 = ll1.head;
                    count = 0;
                } else {
                    node2 = node2.next;
                }
                count++;
            }
            System.out.println("Nodes merge at " + count + "th node with a value of " + node1.data);
        } else {
            System.out.println("Invalid case!");
        }
        System.out.println("=============================");
    }
    
    public static void findLoopStart(LinkedList.LL ll) {
        System.out.println("Finding the start node of the loop for the linked list..");
        System.out.println("==============================================================");
        if (ll.size > 0) {
            LinkedList.Node fast = ll.head;
            LinkedList.Node slow = ll.head;
            do {
                fast = fast.next.next;
                slow = slow.next;
            } while (slow != fast);
            fast = ll.head;
            int cnt = 1;
            while (fast != slow) {
                fast = fast.next;
                slow = slow.next;
                cnt++;
            }
            System.out.println("Linked list loops at the " + cnt + "th node with a value of " + slow.data);
        } else {
            System.out.println("Linked list is empty!");
        }
        System.out.println("==============================================================");
    }

    public static void removeLoop(LinkedList.LL ll) {
        if (ll.size > 0) {
            LinkedList.Node fast = ll.head;
            LinkedList.Node slow = ll.head;
            do {
                fast = fast.next.next;
                slow = slow.next;
            } while (slow != fast);
            fast = ll.head;
            while (fast.next != slow.next) {
                fast = fast.next;
                slow = slow.next;
            }
            slow.next = null;
            System.out.println("Loop has been removed!");
            ll.print();
        } else {
            System.out.println("List is empty!");
        }
    }

    public static void findLoopLength(LinkedList.LL ll) {
        if (ll.size > 0) {
            LinkedList.Node slow = ll.head;
            LinkedList.Node fast = ll.head;
            while (slow != null && fast != null) {
                slow = slow.next;
                fast = fast.next;
                if (fast == null) {
                    break;
                }
                fast = fast.next;
                if (slow == fast) {
                    break;
                }
            }
            int count = 0;
            do {
                count++;
                slow = slow.next;
            } while (slow != fast);
            System.out.println("Length of the loop is " + count);
        }
    }
    
    public static void check(LinkedList.LL ll) {
        System.out.println("=============================");
        if (ll.size > 0) {
            LinkedList.Node slow = ll.head;
            LinkedList.Node fast = ll.head;
            boolean isCircular = false;
            while (slow != null && fast != null) {
                slow = slow.next;
                fast = fast.next;
                if (fast == null) {
                    break;
                }
                fast = fast.next;
                if (slow == fast) {
                    isCircular = true;
                    break;
                }
            }
            if (isCircular) {
                System.out.println("It is circular linked list!");
            } else {
                System.out.println("It is a linear linked list!");
            }
        } else {
            System.out.println("List is empty!");
        }
        System.out.println("=============================");
    }

    public static void check(CircularLinkedList.CLL cll) {
        System.out.println("=============================");
        if (cll.size > 0) {
            CircularLinkedList.Node slow = cll.head;
            CircularLinkedList.Node fast = cll.head;
            boolean isCircular = false;
            while (slow != null && fast != null) {
                slow = slow.next;
                fast = fast.next;
                if (fast == null) {
                    break;
                }
                fast = fast.next;
                if (slow == fast) {
                    isCircular = true;
                    break;
                }
                
            }
            if (isCircular) {
                System.out.println("It is circular linked list!");
            } else {
                System.out.println("It is a linear linked list!");
            }
        } else {
            System.out.println("List is empty!");
        }
        System.out.println("=============================");
    }

    /**
     * Merge ll2 into ll1
     * 
     * @param ll1
     * @param ll2
     * @return
     */
    // TODO Again!
    public static LinkedList.LL merge(LinkedList.LL ll1, LinkedList.LL ll2) {
        LinkedList.Node n1 = ll1.head;
        LinkedList.Node n2 = ll2.head;
        LinkedList.Node prev = null;
        System.out.println("-------------------------------------------------");
        ll1.print();
        ll2.print();
        while (n1.next != null || n2 != null) {
            if (n1.data <= n2.data && n1.next != null) {
                prev = n1;
                n1 = n1.next;
            } else if (n1.data > n2.data) {
                if (prev != null) {
                    prev.next = n2;
                } else {
                    ll1.head = n2;
                }
                LinkedList.Node next = n2.next;
                n2.next = n1;
                prev = n2;
                n2 = next;
            } else if (n1.next == null) {
                n1.next = n2;
                break;
            }
        }
        System.out.println("-------------------------------------------------");
        ll1.print();
        System.out.println("-------------------------------------------------");        
        return ll1;
    }

    public static int recurseAdd(LinkedList.Node n1, LinkedList.Node n2, int diff) {
        int carry = 0;
        int sum = 0;
        if (n1 == null && n2 == null) {
            return carry;
        } else if (diff > 0) {
            carry = recurseAdd(n1.next, n2, diff-1);
            sum = carry + n1.data;
        } else {
            carry = recurseAdd(n1.next, n2.next, 0);
            sum = carry + n1.data + n2.data;
        }
        carry = sum / 10;
        n1.data = sum % 10;
        return carry;
    }

    public static void add(LinkedList.LL l1, LinkedList.LL l2) {
        LinkedList.Node h1 = l1.head;
        LinkedList.Node h2 = l2.head;
        int s1=0, s2=0;
        while (h1 != null) {
            s1++;
            h1 = h1.next;
        }
        while (h2 != null) {
            s2++;
            h2 = h2.next;
        }
        if (s1 < s2) {
            h2 = l1.head;
            h1 = l2.head;
        } else {
            h1 = l1.head;
            h2 = l2.head;
        }
        System.out.println("-------------------------------");
        l1.print();
        l2.print();
        System.out.println("-------------------------------");
        int diff = Math.abs(s1 - s2);
        System.out.println("size 1 : {" + s1 + "} | size 2 : {" + s2 + "} | diff : {" + diff + "}");
        System.out.println("-------------------------------");
        int carry = recurseAdd(h1, h2, diff);
        if (carry == 1) {
            if (s1 < s2) {
                l2.add(carry);
                l2.print();
            } else {
                l1.add(carry);
                l2.print();
            }
        }
    }

    public static void findCommonElems(LinkedList.LL l1, LinkedList.LL l2) {
        LinkedList.Node n1 = l1.head, n2 = l2.head;
        String commonNodes = "Common Nodes : ";
        l1.print();
        l2.print();
        System.out.println("-------------------------------------");
        while (n1 != null && n2 != null) {
            if (n1.data == n2.data) { 
                commonNodes = commonNodes + "{" + n1.data + "} ";
                n1 = n1.next;
                n2 = n2.next;
            } else if (n1.data > n2.data && n2 != null) {
                n2 = n2.next;
            } else if (n1.data < n2.data && n1 != null) {
                n1 = n1.next;
            }
        }
        System.out.println(commonNodes);
    }
    public static void main(String[] args) {
        LinkedList.LL ll = new LinkedList.LL();
        LinkedList.LL ll2 = new LinkedList.LL();
        CircularLinkedList.CLL cll = new CircularLinkedList.CLL();
        ll.add(9);
        // ll.add(9);
        // ll.add(9);
        // ll.add(9);
        // check(ll);
        // ll.addLoop(2);
        // findLoopLength(ll);
        // check(ll);
        // findLoopStart(ll);
        // removeLoop(ll);
        // check(ll);
        // ll2.add(7);
        ll2.add(9);
        ll2.add(9);
        ll2.add(9);
        add(ll, ll2);
        // ll.attach(ll2, 4);
        // findMerge(ll, ll2);
        // cll.add(1);
        // cll.add(2);
        // cll.add(3);
        // cll.add(4);
        // check(cll);
    }
}