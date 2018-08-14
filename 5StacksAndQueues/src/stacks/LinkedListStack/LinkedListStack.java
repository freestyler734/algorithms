package stacks.LinkedListStack;

public class LinkedListStack {
    private Node top;


    // добавляем в начало
    public void push(double value) {
        Node node = new Node(value);

        node.setNext(top);
        top = node;
    }

    private Node innerPop() {
        Node node = top;

        if (top != null) {
            top = top.getNext();
        }

        return node;
    }

    public double pop() {
        Node node = innerPop();

        return node.getValue();
    }

    public void display() {
        Node current = top;

        while (current != null) {
            System.out.println(current.getValue());
            current = current.getNext();
        }
    }
}
