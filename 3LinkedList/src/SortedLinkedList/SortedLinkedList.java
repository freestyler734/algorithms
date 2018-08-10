package SortedLinkedList;

/**
 * Сортированный односвязанный список
 */
public class SortedLinkedList {
    private Node top;

    /**
     * Вставка элемента в список
     * @param value
     */
    public void add(double value) {
        Node node = new Node(value);

        if (top != null) {

            Node current = top;
            Node prev = null;

            while (current != null && value >= current.getValue()) {
                prev = current;
                current = current.getNext();
            }

            if (top == current) {
                node.setNext(current);
                top = node;
            } else {
                prev.setNext(node);
                node.setNext(current);
            }

        } else {
            top = node;
        }
    }

    /**
     * Удаление первого встретевшегося элемента
     */
    public boolean delete(double value) {

        Node current = top;
        Node previous = null;

        while (current != null) {

            if (current.getValue() == value) {

                if (previous == null) {
                    top = current.getNext();
                    current.setNext(null);
                } else {
                    previous.setNext(current.getNext());
                    current.setNext(null);
                }

                return true;
            }

            previous = current;
            current = current.getNext();
        }

        return false;
    }

    public void display() {
        Node current = top;

        while(current != null) {
            System.out.println(current.getValue());
            current = current.getNext();
        }
    }

}
