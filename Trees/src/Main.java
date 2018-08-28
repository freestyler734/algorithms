import binarySearchTree.BinarySearchTree;
import binaryTree.BinaryTree;

public class Main {

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.add(60);
        tree.add(35);
        tree.add(76);
        tree.add(17);
        tree.add(42);
        tree.add(68);
        tree.add(11);
        tree.add(24);
        tree.add(63);
        tree.add(69);
        tree.add(23);
        //tree.print();

        System.out.println(tree.delete(35));
        System.out.println(tree.delete(60));
        System.out.println(tree.delete(17));

        tree.print();
    }
}
