package DirectLinkingHashTable;

public class Node {
    private Node next;
    private int key;
    private double value;

    public Node(int key, double value) {
        this.key = key;
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }


}
