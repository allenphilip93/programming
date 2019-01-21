public class Generics {
    public static <T> void printList(T t) {
        System.out.println("Printing the list!");
    }

    public static void main(String[] args) {
        // Generics g1 = new Generics();
        Generics g2 = new Generics();
        // Generics<Integer> g3 = new Generics<>();
        // g1.printList(new Object());
        g2.printList("Hellow");
        // g3.printList(new Integer(4));
    }
}