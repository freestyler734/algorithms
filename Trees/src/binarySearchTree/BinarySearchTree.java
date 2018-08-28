package binarySearchTree;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

import java.util.LinkedList;

/**
 * Реализация двоичного дерева поиска -
 * бинарного упорядоченного дерева.
 * Упорядоченное дерево - для всех узлов значение текущего узла больше значения левого и
 * меньше значения правого.
 * Симметричный обход выведет упорядоченной дерево в сортированным виде.
 */
public class BinarySearchTree {
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
            if (parent.getLeft() == null) parent.setLeft(new Node(value));
            else insert(parent.getLeft(), value);
        } else {
            // правый потомок пустой - сохраняем в него новый узел,
            // иначе идем дальше
            if (parent.getRight() == null) parent.setRight(new Node(value));
            else insert(parent.getRight(), value);
        }
    }

    /**
     * Пу
     * @param value
     */
    public void add(int value) {
        if (root == null) root = new Node(value);
        else insert(root, value);
    }

    /**
     * Поиск первого с корня узла, по значению в поддереве узла node
     * --------------------
     * Сложность - O(log(N))
     * @param current
     * @param value
     * @return
     */
    private Node findFirst(Node current, int value) {

        if (current == null) return null;

        if (current.getData() == value) return current;

        if (current.getData() > value) {
            if (current.getLeft() == null) return null;
            return findFirst(current.getLeft(), value);
        } else {
            if (current.getRight() == null) return null;
            return findFirst(current.getRight(), value);
        }
    }

    private Node findParent(Node parent, int value) {

        if (parent == null) return null;

        if (parent.getData() == value) return null;

        if (parent.getData() > value) {
            if (parent.getLeft() == null) return null;
            else if (parent.getLeft().getData() == value) return parent;
            else return findParent(parent.getLeft(), value);
        } else {
            if (parent.getRight() == null) return null;
            else if (parent.getRight().getData() == value) return parent;
            else return findParent(parent.getRight(), value);
        }
    }

    public Node findFirst(int value) {
        return findParent(root, value);
    }

    /**
     * Удаление заданного элемента элемента.
     * Алгоритм можно разбить на несколько случаев:
     * 1) У удаляемого элемента нет потомков - тогда удаляем на него ссылку.
     * 2) У удаляемого элемента только один потомок - тогда заменяем удаляемый элемент потомком.
     * 3) У удаляемого элемента два потомка - тогда начиная с левого потомка ищем последний не пустой правый потомок.
     *    На место найденного элемента перемещаем его левый потомок, а сам элемент вставляем вместо удаляемого, добавляя к нему
     *    потомки удаляемого элемента.
     *    Проще говоря - мы ищем максимальный, меньший удаляемого элемент дерева и заменяем удаляемый им,
     *    сохраняя при этом структуру дерева(исходные ссылки).
     * @param value
     * @return
     */
    public boolean delete(int value) {
        // дерево пустое
        if (root == null) return false;

        Node current;

        // ищем родителя удаляемого элемента, если он есть.
        Node parent = findParent(root, value);
        if (parent == null) {
            // у удаляемого элемента нет родителя
            if (root.getData() == value) current = root;
            else return false;
        } else {
            // начиная с родителя получаем ссылку на сам удаляемый элемент
            // (не знаем правый он или левый потомок).
            current = findFirst(parent, value);
        }

        // третий случай.
        if (current.getRight() != null && current.getLeft()!= null) {
            Node prevRightChild = current;
            Node currentRightChild = current.getLeft(); // стартуем с левого потомка удаляемого элемента.
            while (currentRightChild.getRight() != null) {
                prevRightChild = currentRightChild;
                currentRightChild = currentRightChild.getRight();
            }

            if (prevRightChild != current) {
                // заменяем найденный элемент(крайний правый у левого потомка удаляемого элемента)
                // левым потомком.
                prevRightChild.setRight(currentRightChild.getLeft());
                // переносим потомки удаляемого элемента найденному
                // (крайнему правому у левого потомка удаляемого).
                //if (currentRightChild != current.getLeft())
                currentRightChild.setLeft(current.getLeft());
            }
            currentRightChild.setRight(current.getRight());


            if (parent != null) {
                // заменяем найденный элемент удаляемым.
                if (parent.getData() > value) parent.setLeft(currentRightChild);
                else parent.setRight(currentRightChild);
            } else { //  при удалении корня.
                root = currentRightChild;
            }

        } else if (current.getRight() != null) { // у удаляемого элемента только правый потомок
            if (parent != null) {
                // незнаем с какой стороны удаляемый элемент
                if (parent.getData() > value) parent.setLeft(current.getRight());
                else parent.setRight(current.getRight());
            } else { //  при удалении корня.
                root = current.getRight();
            }
        } else if (current.getLeft() != null) { // у удаляемого элемента только левый потомок
            if (parent != null) {
                // незнаем с какой стороны удаляемый элемент
                if (parent.getData() > value) parent.setLeft(current.getLeft());
                else parent.setRight(current.getLeft());
            } else { //  при удалении корня.
                root = current.getLeft();
            }
        } else  { // у удаляемого элемента нет потомков
            if (parent != null) {
                if (parent.getData() > value) parent.setLeft(null);
                else parent.setRight(null);
            } else { //  при удалении корня.
                root = null;
            }
        }

        return true;
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

        if (node == null) return;

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
}
