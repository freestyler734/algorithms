package binaryTree;

/**
 * Узел для простого бинарного дерева (степень дерева 2)
 */
public class Node {
    private String data;
    private Node left;
    private Node right;

    public Node(String data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public String getData() {
        return data;
    }
}
