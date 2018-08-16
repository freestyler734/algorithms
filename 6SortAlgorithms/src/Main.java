import ArrayTrees.ArrayBinaryTree;
import arraySort.ArraySort;

public class Main {

    public static void main(String[] args) {

        ArrayBinaryTree tree = new ArrayBinaryTree(7);
        tree.add(7);
        tree.add(1);
        tree.add(10);
        tree.add(4);
        tree.add(6);
        tree.add(9);
        tree.add(2);

        tree.display();
    }

    public static void display(double[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
