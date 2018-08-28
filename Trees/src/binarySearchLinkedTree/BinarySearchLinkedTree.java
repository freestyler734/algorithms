package binarySearchLinkedTree;

/**
 * Реализация связанного бинарного дерева поиска,
 * со связями для обеспечения симметричного обхода дерева.
 */
public class BinarySearchLinkedTree {
    private Node root;

    /**
     * Реализация вставки элемента в двоичное поддерево поиска,
     * начиная с узла node.
     * ------------------------
     * Сложность - O(log(N))
     * @param parent
     * @param value
     */
    private void insert(Node parent, int value) {

        // Если значение меньше узла, то смотрим левого потомка,
        // иначе правого
        if (parent.getData() > value) {
            // левый потомок пустой - сохраняем в него новый узел,
            // иначе идем дальше
            if (!parent.getLeft().isLeftBranch()) {
                Node node = new Node(value);
                parent.setLeft(node);
            }
            else insert(parent.getLeft(), value);
        } else {
            // правый потомок пустой - сохраняем в него новый узел,
            // иначе идем дальше
            if (!parent.getRight().isRightBranch()) parent.setRight(new Node(value));
            else insert(parent.getRight(), value);
        }
    }

    /**
     * Публичный метод для вставки элемента.
     * @param value
     */
    public void add(int value) {
        if (root == null) root = new Node(value);
        else insert(root, value);
    }
}
