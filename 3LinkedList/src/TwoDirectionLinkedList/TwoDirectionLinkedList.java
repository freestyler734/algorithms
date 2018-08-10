package TwoDirectionLinkedList;

/**
 * Двусвязный список
 */
public class TwoDirectionLinkedList {

    private Node top;
    private Node last;

    /**
     * Вставка элемента в начало списка
     * @param value
     */
    public void addToTheBeginning(double value) {
        Node node = new Node(value);

        if (top != null) {

            // если top - еодинственный элемент в массиве,
            // то сохраняем на него ссылку в last.
            if (top.getNext() == null) {
                last = top;
            }

            node.setNext(top);
            top.setPrev(node);
        }

        top = node;
    }

    /**
     * Добавление элемента в конец списка
     * @param value
     */
    public void addToTheEnd(double value) {
        Node node = new Node(value);

        if (top == null) {
            top = node;
        } else {
            last.setNext(node);
            node.setPrev(last);
        }

        last = node;
    }

    /**
     * Удаление первого встретевшегося элемента
     */
    public boolean delete(double value) {

        Node current = top;

        while (current != null) {

            if (current.getValue() == value) {

                if (current == top) {

                    if (current.getNext() == null) {
                        top = null;
                        current = null;
                        last = null;
                    } else {
                        top = current.getNext();
                        current.getNext().setPrev(null);
                        current.setNext(null);
                    }

                } else if (current == last) {
                    last = current.getPrev();
                    current.getPrev().setNext(null);
                    current.setPrev(null);
                } else {
                    current.getNext().setPrev(current.getPrev());
                    current.getPrev().setNext(current.getNext());
                    current.setNext(null);
                    current.setPrev(null);

                }

                return true;
            }

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

    public void displayBackward() {
        Node current = last;

        while (current != null) {
            System.out.println(current.getValue());
            current = current.getPrev();
        }
    }
}
