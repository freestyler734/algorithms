package AVLtree;

/**
 * Реализация АВЛ-дерева - бинарного дерева поиска,
 * где для каждого узла высота двух поддеревьев отличается не больше чем на единицу.
 */
public class AVLTree {
    private Node root;


    private void insert(Node current, int value) {

        if (current.getData() > value) {
            if (current.getLeft() == null) {
                current.setLeft(new Node(value));
                current.height++;
            }
            else insert(current.getLeft(), value);

        } else {
            if (current.getRight() == null) {
                current.setRight(new Node(value));
                current.height++;
            }
            else insert(current.getRight(), value);
        }
    }
}
