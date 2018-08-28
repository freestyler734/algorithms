package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    private Node root;

    public BinaryTree(){
        makeTestTree();
    }

    /**
     * Проход дерева в прямом порядке.
     * Проходим с node всех левых потомков, пока не дойдем до конца.
     * Далее выводим правых.
     * Т.е. первым делом обрабатывается сама вершина,
     * затем ее потомки, начиная с левого.
     * -------------------------
     * Сложность - O(N)
     * @param node
     */
    private void straightPassing(Node node) {

        if (node == null) return;

        System.out.println(node.getData());
        straightPassing(node.getLeft());
        straightPassing(node.getRight());
    }

    /**
     * Симметричный обход дерева.
     * Для каждого узла обрабатывается сперва левый потомок, потом он сам, затем правый.
     * Вывод начинается с самого левого элемента последнего уровня.
     * -------------------------
     * Сложность - O(N)
     * @param node
     */
    private void simetricPassing(Node node) {

        if (node == null) return;

        simetricPassing(node.getLeft());
        System.out.println(node.getData());
        simetricPassing(node.getRight());
    }

    /**
     * Реализация прохода дерева в обратном порядке.
     * Для каждой вершины, обрабатываем сперва его левого потомка,
     * за тем правого, затем саму вершину.
     * -------------------------
     * Сложность - O(N)
     * @param node
     */
    private void reversePassing(Node node) {
        if (node == null) return;

        reversePassing(node.getLeft());
        reversePassing(node.getRight());
        System.out.println(node.getData());
    }

    /**
     * Реализация обхода дерева в ширину.
     * Начиная с node, выводим сперва node, затем его потомков.
     * Для каждого потомка сохраняем его потомков в очередь и вывод целый уровень.
     * Т.о. данный вид обхода дерева - является обрработкой всех узлов уровня слева направо,
     * начиная с верхнего узла.
     * -------------------------
     * Сложность - O(N)
     * @param node
     */
    private void broadwisePassing(Node node) {
        // т.к. у узлов уровня нет связей, сохраняем эти узлы в очередь.
        LinkedList<Node> queue = new LinkedList<>();

        queue.push(node);

        // цикл работает, пока в очереди есть элементы
        while (queue.size() != 0) {
            Node current = queue.pollLast();
            System.out.println(current.getData());

            if (current.getLeft() != null) queue.push(current.getLeft());
            if (current.getRight() != null) queue.push(current.getRight());
        }
    }

    public void print(){
        broadwisePassing(root);
    }

    private void makeTestTree() {
        root = new Node("D");
        Node b = new Node("B");
        Node e = new Node("E");
        Node a = new Node("A");
        Node c = new Node("C");

        root.setLeft(b);
        root.setRight(e);

        b.setLeft(a);
        b.setRight(c);
    }
}
