package CircledLinkedList;

public class Node {
    private Node next;
    private double value;
    private boolean isMarked = false; // Служит меткой прохода узлов.

    public Node(double value) {
        this.value = value;
    }

    public Node(Node next, double value) {
        this.next = next;
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }
}
