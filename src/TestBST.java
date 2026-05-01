public class TestBST {
    public static void main(String[] args) {

        BST<Integer, String> tree = new BST<>();

        tree.put(5, "A");
        tree.put(2, "B");
        tree.put(8, "C");

        for (var elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }
}