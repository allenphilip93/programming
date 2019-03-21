import java.util.HashSet;
import java.util.Set;

/**
 * Separate chaining hash table
 * 
 * @param <T>
 * @param <V>
 */
public class HashTable<T, V> {

    private class Node {
        T key;
        V value;
        Node next;

        public Node(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int defaultSize = 2;
    private int size;
    private int num;
    private int loadFactor = 2;
    private Object[] table;

    public HashTable() {
        this.size = defaultSize;
        create(size);
    }

    public HashTable(int size) {
        this.size = size;
        create(size);
    }

    private void create(int size) {
        table = new Object[size];
    }

    public void insert(T key, V value) {
        Node node = new Node(key, value);
        int prehash = prehash(key);
        int index = hash(prehash);
        // System.out.format("Key : %s | Prehash : %d | Index : %d\n", key, prehash, index);
        if (containsKey(key)) {
            Node curr = (Node) table[index];
            while (curr != null) {
                if (curr.key.equals(key)) {
                    curr.value = value;
                }
                curr = curr.next;
            }
        } else {
            if (table[index] == null) {
                table[index] = node;
            } else {
                Node curr = (Node) table[index];
                while (curr.next != null) {
                    curr = curr.next;
                }
                curr.next = node;
            }
            num++;
            if ((num*1./size) >= loadFactor) {
                // System.out.println("==========================================");
                // System.out.println("Growing the hashtable : Num - " + num + " Size - " + size);
                // System.out.println("==========================================");
                // print();
                rehash(size*2);
            }
        }
    }

    private int prehash(T key) {
        return key.hashCode();
    }

    private int hash(int prehash) {
        return prehash % size;
    }

    public V find(T key) {
        int prehash = prehash(key);
        int index = hash(prehash);
        Node top = (Node) table[index];
        while (top != null) {
            if (top.key.equals(key)) {
                // System.out.format("Found (%s, %s)\n", top.key, top.value);
                return top.value;
            }
            top = top.next;
        }
        return null;
    }

    public void delete(T key) {
        int prehash = prehash(key);
        int index = hash(prehash);
        Node top = (Node) table[index];
        Node prev = null, del = null;
        while (top != null) {
            if (top.key.equals(key)) {
                if (prev == null) {
                    table[index] = top.next;
                } else {
                    prev.next = top.next;
                }
                del = top;
                del.next = null;
            }
            prev = top;
            top = top.next;
        }
        if (del == null) {
            // System.out.println("Key not found in the hashtable");
        } else {
            // System.out.println("(" + del.key + ", " + del.value + ") has been deleted from hashtable!");
            num--;
            if (num*1./size <= loadFactor/4. && size > 2) {
                // System.out.println("========================================");
                // System.out.println("Shrinking the hashtable : Num - " + num + " Size - " + size);
                // System.out.println("========================================");
                // print();
                rehash(size/2);
            }
        }
    }

    private void rehash(int newsize) {
        Object[] oldtable = table;
        table = new Object[newsize];
        int oldsize = size;
        size = newsize;
        num = 0;
        for (int index=0; index < oldsize; index++) {
            Node node = (Node) oldtable[index];
            while (node != null) {
                insert(node.key, node.value);
                node = node.next;
            }
        }
    }

    public boolean containsKey(T key) {
        int prehash = prehash(key);
        int index = hash(prehash);
        Node top = (Node) table[index];
        while (top != null) {
            if (top.key.equals(key))
                return true;
            top = top.next;
        }
        return false;
    }

    public void print() {
        for (int index=0; index < table.length; index++) {
            if (table[index] == null) {
                System.out.format("Index [%d] : NULL\n", index);
            } else {
                String s = "";
                Node top = (Node) table[index];
                while (top != null) {
                    s = s + "(" + top.key + ", " + top.value + ") => ";
                    top = top.next;
                }
                s = s + "NULL";
                System.out.format("Index [%d] : %s\n", index, s);
            }
        }
    }

    public static void removeDuplicateCharacters() {
        String input = "allen philip j";
        String output = "";
        int[] charDict = new int[256];
        for (int index=0; index < input.length(); index++) {
            int cInt = input.charAt(index);
            if (charDict[cInt] == 0) {
                charDict[cInt] = 1;
                output = output + input.charAt(index);
            }
        }
        System.out.println("Output String : " + output);
    }

    public static void checkIfArraysIdentical() {
        int[] arr1 = {1, 5, 2, 4, 3};
        int[] arr2 = {5, 4, 3, 1, 2};
        HashTable<Integer, Integer> countTable = new HashTable<>();
        for (int index=0; index < arr1.length; index++) {
            if (countTable.containsKey(arr1[index])) {
                int count = countTable.find(arr1[index]);
                countTable.insert(arr1[index], count+1);
            } else {
                countTable.insert(arr1[index], 1);
            }
        }
        for (int index=0; index < arr2.length; index++) {
            if (countTable.containsKey(arr2[index])) {
                int count = countTable.find(arr2[index]);
                if (count == 1) {
                    countTable.delete(arr2[index]);
                } else {
                    countTable.insert(arr2[index], count-1);
                }
            } else {
                System.out.println("No, the two arrays are not equivalent!");
                return;
            }
        }
        if (countTable.num == 0) {
            System.out.println("Yes, the two arrays are identical!");
        } else {
            System.out.println("No, the two arrays are not identical!");
        }
    }

    public static void checkForSymmetricPairs() {
        int[][] input = {{1,3}, {4,2}, {2,5}, {1,5}, {3,1}, {4,5}, {5,1}};
        Set<String> set = new HashSet<>();
        boolean hasSymmetricPairs = false;
        for (int index=0; index < input.length; index++) {
            int a = input[index][0];
            int b = input[index][1];
            String s = a + "," + b;
            String s_rev = b + "," + a;
            if (set.contains(s_rev)) {
                System.out.format("Symmetric pair found : (%d, %d)\n", a, b);
                hasSymmetricPairs = true;
            } else {
                set.add(s);
            }
        }
        if (!hasSymmetricPairs)
            System.out.println("No symmetric pairs found!");
    }

    public static void main(String[] args) {
        HashTable<String, String> hashtable = new HashTable<>();
        // hashtable.insert("a", "apple");
        // hashtable.insert("b", "ball");
        // hashtable.insert("c", "cat");
        // hashtable.insert("d", "dog");
        // hashtable.insert("e", "ear");
        // hashtable.insert("f", "fish");
        // hashtable.insert("g", "goat");
        // hashtable.insert("h", "hut");
        // hashtable.insert("i", "ink");
        // hashtable.insert("j", "jug");
        // hashtable.insert("k", "king");
        // hashtable.insert("l", "lion");
        // hashtable.print();
        // hashtable.find("g");
        // hashtable.find("a");
        // hashtable.find("k");
        // hashtable.delete("l");
        // hashtable.print();
        // hashtable.delete("a");
        // hashtable.print();
        // hashtable.delete("i");
        // hashtable.delete("b");
        // hashtable.delete("d");
        // hashtable.delete("c");
        // hashtable.delete("f");
        // hashtable.delete("e");
        // hashtable.delete("g");
        // hashtable.delete("h");
        // hashtable.print();
        // hashtable.find("j");
        // hashtable.find("k");
        // removeDuplicateCharacters();
        // checkIfArraysIdentical();
        checkForSymmetricPairs();
    }
}