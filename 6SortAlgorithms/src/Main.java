import ArrayTrees.ArrayBinaryTree;

public class Main {

    public static void main(String[] args) {

        ArrayBinaryTree tree = new ArrayBinaryTree();
        tree.add(7);
        tree.add(101);
        tree.add(5);
        tree.add(4);
        tree.add(6);
        tree.add(90);
        tree.add(2);


        tree.display(7);
    }

    public static void display(double[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
