public class Testing {

    private final int num = 10;
    
    public class Inner {
        private int num = 5;
        public int getValue() {
            increaseNum(this);
            num = Testing.this.num;
            return num + 2;
        }
    }

    public void increaseNum(Inner inner) {
        inner.num = inner.num + 10;
    }
    
    public void gimmeFood(Integer dog) {
        System.out.println("Can't eat dog!");
    }

    public void gimmeFood(Double animal) {
        System.out.println("Yummy animal!");
    }

    public void printArray(Integer arr) {
        // for (Integer a : arr) {
        //     System.out.println(a.toString());
        // }
    }

    public static void main(String[] args) {
        System.out.println("Hello, world!");
        // java.util.List<Integer> intList = new java.util.ArrayList<>();
        // java.util.List<Number> numList = new java.util.ArrayList<>();
        // java.util.List<?> tempList = intList;
        // numList = tempList;
        // Animal a = new Dog();
        // Dog d = new Dog();
        // Testing test = new Testing();
        // test.gimmeFood(null);
        Testing test = new Testing();
        int [] arr = {1, 2 ,3};
        int a= 1;
        test.printArray(a);
    }
}