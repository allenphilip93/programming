public class DoublyLinkedList {

    private static class Node {
        int data;
        Node next;
        Node prev;
        Node(int data) {
            this.data = data;
        }
    }

    private static class DLL {
        private Node head;
        int size;

        public void add(int data) {
            Node node = new Node(data);
            if (head == null) {
                head = node;
                node.prev = head;
            } else {
                head.prev = node;
                node.next = head;
                head = node;
            }
            size++;
            System.out.println("Added a node of value " + data);
            print();
        }

        public void remove() {
            if (head == null) {
                System.out.println("List is empty!");
            } else {
                Node removedNode = head;
                if (head.next == null) {
                    head = head.next;
                    head.prev = null;
                } else {
                    head = null;
                }
                size--;
                System.out.println("Removed a node of value " + removedNode.data);
                print();
            }
        }

        public void addBottom(int data) {
            Node node = head;
            if (node == null) {
                add(data);
                return;
            }
            while (node.next != null)
                node = node.next;
            Node newNode = new Node(data);
            node.next = newNode;
            newNode.prev = node;
            add++;
            System.out.println("Added at the bottom a node of value " + data);
            print();
        }

        public void print() {
            Node top = head;
            if (top == null) {
                System.out.println("List is empty!");
            } else {
                String s = "HEAD <=> ";
                while (top.next != null) {
                    s = s + top.data + " <=> ";
                    top = top.next;
                }
                s = s + "null";
                System.out.println(s);
            }
        }
    }
    public static void main(String[] args) {
        DLL list = new DLL();
        list.add(1);
        list.add(2);
        list.add(5);
        list.addBottom(0);
        list.remove();
        list.add(9);
        list.add(9);
        list.remove();
        list.remove();
        list.remove();
        list.remove();
        list.remove();
        list.remove();
        list.remove();
    }
}