import java.math.BigInteger;

/**
 * Open addressing hash table
 * @param <T>
 */
public class OAHashTable<T, V> {

    private Object[] table;
    private int size;
    private int step = 1;
    private int numElem = 0;
    private static final int defaultSize = 2;

    private class Node {
        T key;
        V value;

        public Node(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public OAHashTable() {
        this(defaultSize);
    }

    public OAHashTable(int size) {
        this.size = size;
        table = new Object[size];
    }

    public int prehash(T elem) {
        return elem.hashCode();
    }

    public int hash(int key, int k) {
        return (key + k*step) % defaultSize;
    }

    public void put(T key, V value) {
        int k = 0;
        int index = hash(prehash(key), k);
        while (table[index] != null)
            index = hash(prehash(key), k++);
        table[index] = new Node(key, value);
        numElem++;
        if (numElem == size) {
            rehash(nextPrime(size));
        }
    }

    /**
     * Find the next prime more than double num elems
     * 
     * @param size
     * @return
     */
    private int nextPrime(Integer n) {
        return Integer.parseInt(new BigInteger(n.toString()).nextProbablePrime().toString());
    }

    /**
     * Find a prev prime more than 2 * num elems and num elems less than size /4
     * 
     * @param size
     * @return
     */
    private int prevPrime(int n) {
        return n/6-1;
    }

    public V find(T key) {
        int k = 0;
        int index = hash(prehash(key), k);
        while (!((Node)table[index]).key.equals(key) && k < numElem) {
            index = hash(prehash(key), k++);
        }
        if (k < numElem) {
            return ((Node)table[index]).value;
        } else {
            return null;
        }
    }

    public V delete(T key) {
        int k = 0;
        int index = hash(prehash(key), k);
        while (!((Node)table[index]).key.equals(key) && k < numElem) {
            index = hash(prehash(key), k++);
        }
        if (k < numElem) {
            Node del = (Node) table[index];
            table[index] = null;
            numElem--;
            if (numElem <= size/4)
                rehash(prevPrime(size));
            return del.value;
        } else {
            return null;
        }
    }

    private void rehash(int size) {
        Object[] oldTable = table;
        table = new Object[size];
        this.size = size;
        numElem = 0;
        for (int index=0; index < oldTable.length; index++) {
            Node elem = (Node) table[index];
            put(elem.key, elem.value);
        }
    }

    public void print() {
        for (int index=0; index < table.length; index++) {
            if (table[index] == null) {
                System.out.format("Index [%d] : NULL\n", index);
            } else {
                System.out.format("Index [%d] : %s\n", index, ((Node)table[index]).value);
            }
        }
    }

    public static void main(String[] args) {
        OAHashTable<String, String> hashTable = new OAHashTable<>();
        hashTable.put("a", "apple");
        hashTable.put("b", "ball");
        hashTable.put("c", "cat");
        hashTable.put("d", "dog");
        hashTable.print();
    }
}