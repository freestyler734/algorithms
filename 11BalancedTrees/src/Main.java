import AVLtree.AVLTree;

public class Main {

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.add(10);
        tree.add(6);
        tree.add(11);
        tree.add(5);
        tree.add(7);
        tree.add(8);
        tree.add(20);
        tree.add(0);
        tree.add(14);
        tree.add(1);
        tree.add(2);
        tree.add(40);
        tree.add(41);
        tree.add(1);
        tree.add(4);
        tree.add(0);
        tree.add(5);
        tree.add(6);


        tree.delete(4);


        tree.print();
    }
}
