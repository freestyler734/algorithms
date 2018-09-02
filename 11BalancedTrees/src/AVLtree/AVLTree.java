package AVLtree;

import java.util.LinkedList;

/**
 * Реализация АВЛ-дерева - бинарного дерева поиска,
 * где для каждого узла высота двух поддеревьев отличается не больше чем на единицу.
 */
public class AVLTree {
    private Node root;


    private void insert(Node current, int value) {

        if (current.getData() > value) {
            if (current.getLeft() == null) {
                Node node = new Node(value);
                current.setLeft(node);
                node.height = 1;
            }
            else insert(current.getLeft(), value);
            current.height = current.getLeft().height + 1;
        } else {
            if (current.getRight() == null) {
                Node node = new Node(value);
                current.setRight(node);
                node.height = 1;
            }
            else insert(current.getRight(), value);
            current.height = current.getRight().height + 1;
        }

        balance(getParent(current), current);

    }

    public void add(int value) {
        if (root == null) root = new Node(value);
        else insert(root, value);
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
            // у удаляемого элемента нет родителя - удаляем корень
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
            // ищем крайний правый элемент левого потомка удаляемого элемента.
            while (currentRightChild.getRight() != null) {
                prevRightChild = currentRightChild;
                currentRightChild = currentRightChild.getRight();
            }


            if (prevRightChild != current) {// если у найденного узла есть потомки.
                // заменяем найденный элемент(крайний правый у левого потомка удаляемого элемента)
                // левым потомком.
                prevRightChild.setRight(currentRightChild.getLeft());
                // переносим потомки удаляемого элемента найденному
                // (крайнему правому у левого потомка удаляемого).
                //if (currentRightChild != current.getLeft())
                currentRightChild.setLeft(current.getLeft());
            }
            currentRightChild.setRight(current.getRight());


            if (parent != null) { // если удаляем не корневой элмент.
                // заменяем найденный элемент удаляемым.
                if (parent.getData() > value) parent.setLeft(currentRightChild);
                else parent.setRight(currentRightChild);
            } else { //  при удалении корня.
                root = currentRightChild;
            }

        } else if (current.getRight() != null) { // у удаляемого элемента только правый потомок
            if (parent != null) { // если удаляем не корневой элмент.
                // незнаем с какой стороны удаляемый элемент
                if (parent.getData() > value) parent.setLeft(current.getRight());
                else parent.setRight(current.getRight());
            } else { //  при удалении корня.
                root = current.getRight();
            }
        } else if (current.getLeft() != null) { // у удаляемого элемента только левый потомок
            if (parent != null) { // если удаляем не корневой элмент.
                // незнаем с какой стороны удаляемый элемент
                if (parent.getData() > value) parent.setLeft(current.getLeft());
                else parent.setRight(current.getLeft());
            } else { //  при удалении корня.
                root = current.getLeft();
            }
        } else  { // у удаляемого элемента нет потомков
            if (parent != null) { // если удаляем не корневой элмент.
                if (parent.getData() > value) parent.setLeft(null);
                else parent.setRight(null);
            } else { //  при удалении корня.
                root = null;
            }
        }

        deleteBalance(null, root, value);
        return true;
    }

    private void deleteBalance(Node parent, Node current, int value) {

        if (current == null) return;

        balance(parent, current);

        if (current.getData() > value) {
            deleteBalance(current, current.getLeft(), value);
        } else {
            deleteBalance(current, current.getRight(), value);
        }
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

    private Node getParent(Node node) {
        Node parent = null;
        Node current = root;
        while (current != null) {

            if (current == node) return parent;

            parent = current;
            if (current.getData() > node.getData()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        return parent;
    }

    /**
     * Малое правое вращение
     * @param parent
     * @param node
     */
    private void smallRightRotate(Node parent, Node node) {
        Node left = node.getLeft();

        if (parent != null) {
            if (parent.getData() > node.getData()) parent.setLeft(left);
            else parent.setRight(left);
        } else {
            root = left;
        }

        Node leftRight = left.getRight();
        node.setLeft(leftRight);
        left.setRight(node);

        node.height = Math.max(node.getLeft() == null ? 0 : node.getLeft().height, node.getRight() == null ? 0 : node.getRight().height) + 1;

    }

    /**
     * Малое левое вращение
     * @param parent
     * @param node
     */
    private void smallLeftRotate(Node parent, Node node) {
        Node right = node.getRight();

        if (parent != null) {
            if (parent.getData() > node.getData()) parent.setLeft(right);
            else parent.setRight(right);
        } else {
            root = right;
        }

        Node rightLeft = right.getLeft();
        node.setRight(rightLeft);
        right.setLeft(node);

        node.height = Math.max(node.getLeft() == null ? 0 : node.getLeft().height, node.getRight() == null ? 0 : node.getRight().height) + 1;
    }

    /**
     * Большое правое вращение
     * @param parent
     * @param node
     */
    private void complexRightRotate(Node parent, Node node) {
        smallLeftRotate(node, node.getLeft());
        smallRightRotate(parent, node);
    }

    /**
     * Большое левое вращение
     * @param parent
     * @param node
     */
    private void complexLeftRotate(Node parent, Node node) {
        smallRightRotate(node, node.getRight());
        smallLeftRotate(parent, node);
    }

    private void balance(Node parent, Node current) {

        int leftHeight = current.getLeft() != null ? current.getLeft().height : 0;
        int rightHeight = current.getRight() != null ? current.getRight().height : 0;

        int balanceCoeff = leftHeight - rightHeight;

        if (balanceCoeff > 1) {
            Node left = current.getLeft();
            int leftleftHeight = left.getLeft() != null ? left.getLeft().height : 0;
            int leftrightHeight = left.getRight() != null ? left.getRight().height : 0;

            if (leftleftHeight - leftrightHeight >= 0) {
                smallRightRotate(parent, current);
            } else {
                complexRightRotate(parent, current);
            }
        } else if (balanceCoeff < -1) {
            Node right = current.getRight();
            int rightleftHeight = right.getLeft() != null ? right.getLeft().height : 0;
            int rightRightHeight = right.getRight() != null ? right.getRight().height : 0;

            if (rightleftHeight - rightRightHeight <= 0) {
                smallLeftRotate(parent, current);
            } else {
                complexLeftRotate(parent, current);
            }
        }

    }

    private void wisebroadPassing(Node node) {
        LinkedList<Node> queue = new LinkedList();

        queue.push(node);

        while (queue.size() != 0) {
            Node current = queue.pollLast();

            System.out.println(current.getData());

            if (current.getLeft() != null) queue.push(current.getLeft());
            if (current.getRight() != null) queue.push(current.getRight());
        }
    }

    public void print() {
        wisebroadPassing(root);
    }
}
