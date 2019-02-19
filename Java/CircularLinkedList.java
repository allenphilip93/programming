public class CircularLinkedList {

    public static class Node {
        int data;
        Node next;
        public Node (int data) {
            this.data = data;
        }
    }

    public static class CLL {
        Node head;
        int size;

        public void add(int data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = newNode;
                head.next = head;
            } else {
                Node lastNode = head;
                while (lastNode.next != head) {
                    lastNode = lastNode.next;
                }
                newNode.next = head;
                head = newNode;
                lastNode.next = newNode;
            }
            size++;
            System.out.println("Added node of value " + data);
            print();
        }
        
        public void remove() {
            if (head == null) {
                System.out.println("List is empty!");
            } else {
                Node node = head;
                int val;
                if (head.next == head) {
                    val = head.data;
                    head = null;
                } else {
                    while (node.next != head) {
                        // System.out.println("Oh noooo....");
                        node = node.next;
                    }
                    val = head.data;
                    node.next = head.next;
                    head = node.next;
                }
                System.out.println("Removed a node of value " + val);
                print();
            }
        }

        public void print() {
            if (head == null) {
                System.out.println("List is empty!");
            } else {
                Node node = head;
                String s = "HEAD => ";
                do {
                    s = s + node.data + " => " ;
                    node = node.next;
                } while (node != head);
                s = s + "HEAD";
                System.out.println(s);
            }
        }

        public void print(Node custHead) {
            if (custHead == null) {
                System.out.println("List is empty!");
            } else {
                Node node = custHead;
                String s = "CUST_HEAD => ";
                do {
                    s = s + node.data + " => " ;
                    node = node.next;
                } while (node != custHead);
                s = s + "CUST_HEAD";
                System.out.println(s);
            }
        }

        public void split() {
            Node slow = head;
            Node fast = head;
            if (size > 1) {
                do {
                    fast = fast.next;
                    if (fast.next == head) {
                        break;
                    }
                    fast = fast.next;
                    slow = slow.next;
                    if (fast.next == head) {
                        break;
                    }
                } while (slow != fast);
                fast.next = slow.next;
                slow.next = head;
            }
            print();
            print(fast);
        }
    }
    public static void main(String[] args) {
        CLL list = new CLL();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.split();
        // list.remove();
        // list.remove();
        // list.remove();
        // list.remove();
        // list.remove();
        // list.remove();
        // list.remove();
    }
}