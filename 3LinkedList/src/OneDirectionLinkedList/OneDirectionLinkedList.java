package OneDirectionLinkedList;

/**
 * Односвязанный список
 */
public class OneDirectionLinkedList {
    private Node top;

    /**
     * Вставка элемента в начало списка
     * @param value
     */
    public void addToTheBeginning(double value) {
        Node node = new Node(value);

        if (top != null) {
            node.setNext(top);
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
            Node current = top;

            while(current.getNext() != null) {
                current = current.getNext();
            }

            current.setNext(node);
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

    /**
     * Копирование списка
     * @return
     */
    public OneDirectionLinkedList copy() {
        OneDirectionLinkedList copy = new OneDirectionLinkedList();

        Node current = top;

        while (current != null) {
            copy.addToTheEnd(current.getValue());
            current = current.getNext();
        }

        return copy;
    }

    public void display() {
        Node current = top;

        while(current != null) {
            System.out.println(current.getValue());
            current = current.getNext();
        }
    }

}
