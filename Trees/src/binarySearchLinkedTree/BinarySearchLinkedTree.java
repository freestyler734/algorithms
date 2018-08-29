package binarySearchLinkedTree;

/**
 * Реализация связанного бинарного дерева поиска,
 * со связями для обеспечения симметричного обхода дерева.
 * Дополнительные связи образуются таким образом,
 * что левые потомки текущего узла указывают левой ссылкой на прадеда, правой на родителя,
 * а правые потомки указывают левой ссылкой на родителя, а правой на прадеда.
 * Данное утверждение верно для всех узлов, кроме крайнего левого  и крайнего правого.
 * Левая ссылка крайнего левого узла указывает на null,
 * правая ссылка крайнего правого узла указывает на null.
 * Для связей веток и тредов используется одна переменная, однако для каждой ссылки(левой и правой) есть флаг указывающий,
 * является ссылка веткой или тредом.
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
            if (!parent.isLeftBranch()) {
                Node node = new Node(value);
                node.setLeft(parent.getLeft()); // тред
                node.setRight(parent); // тред
                parent.setLeft(node);
                parent.setLeftBranch(true);
            }
            else insert(parent.getLeft(), value);
        } else {
            // правый потомок пустой - сохраняем в него новый узел,
            // иначе идем дальше
            if (!parent.isRightBranch()){
                Node node = new Node(value);
                node.setRight(parent.getRight()); // тред
                node.setLeft(parent); // тред
                parent.setRight(node);
                parent.setRightBranch(true);
            }
            else insert(parent.getRight(), value);
        }
    }

    /**
     * Симметричный обход дерева с использованием тредов.
     * @param current
     */
    private void simmetricPassing(Node current) {

        // указывыет идем мы по треду или по ветки.
        boolean viaBranch = true;

        // пока не дойдем до конца(крайний правый тред).
        while (current != null) {

            // если идем по ветки, то находим крайний левый элемент.
            if (viaBranch) {
                while (current.isLeftBranch()) {
                    current = current.getLeft();
                }
            }

            System.out.println(current.getData());

            // если есть правая ветка, то идем по ветки,
            // иначе идем по треду
            if (current.isRightBranch()) {
                viaBranch = true;
            } else {
                viaBranch = false;
            }
            // получаем ссылку на  правую ветку или правый тред.
            current = current.getRight();
        }
    }

    /**
     * Печатает дерево в консоль.
     */
    public void print() {
        simmetricPassing(root);
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
