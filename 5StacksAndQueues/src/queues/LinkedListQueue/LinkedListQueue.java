package queues.LinkedListQueue;

/**
 * Реализация очереди на основе связанного списка.
 */
public class LinkedListQueue {
    private Node top;
    private Node last;

    public void push(double value) {
        Node node = new Node(value);

        if (top == null) {
            top = node;
        } else {
            last.setNext(node);
        }

        last = node;
    }

    private Node innerPop() {

        Node poped = top;
        top = top.getNext();

        return poped;
    }

    public double pop() {

        if (top == null) {
            throw new NullPointerException();
        }

        return innerPop().getValue();
    }

    public void display() {

        Node current = top;
        while (current != null) {
            System.out.println(current.getValue());
            current = current.getNext();
        }
    }
}
