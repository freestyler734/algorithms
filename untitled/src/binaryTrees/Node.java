package binaryTrees;

public class Node {
    private int key;
    private double data;
    private Node left;
    private Node right;

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

    public Node(int key, double data) {
        this.key = key;
        this.data = data;
    }

    public int getKey() {
        return key;
    }

    public double getData() {
        return data;
    }

    public void display() {
        System.out.println(data + " ");
    }

}
