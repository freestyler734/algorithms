package CircledLinkedList;

import java.util.Hashtable;

/**
 * Односвязанный список, который может содержать циклы (не обязательно быть полностью циклом)
 */
public class CircledLinkedList {
    private Node top;
    private Node last;

    /**
     * Смыкает список в кольцо
     * @return
     */
    public boolean makeCircle() {

        if (last != null && top != null) {
            last.setNext(top);
            return true;
        }

        return false;
    }

    /**
     * Возвращает true - если список замкнут или имеет кольцо
     * Метод маркировок узлов.
     * Сложность - O(N)
     * @return
     */
    public boolean isCircledMarked() {

        Node current = top;

        // если встретился помеченный элемент, то список замкнут
        // непомеченные элементы помечаем.
        while (current != null) {

            if (current.isMarked()) {
                clearMarks(); // очищаем метки
                return true;
            } else {
                current.setMarked(true);
            }

            current = current.getNext();
        }

        clearMarks(); // очищаем метки

        return false;
    }

    /**
     * Возвращает true - если список замкнут или имеет кольцо
     * Метод использует хеш-таблицы (можно использовать любую структуру данных, просто они обеспечивают быстрый доступ).
     * Сохраняем в хеш таблицу найденные элементы.
     * Сложность - O(N)
     * @return
     */
    public boolean isCircledHashTable() {
        Node current = top;
        Hashtable<Node, Boolean> hashtable = new Hashtable<>();

        // если  элемент, то список замкнут
        // непомеченные элементы помечаем.
        while (current != null) {

            if (hashtable.containsKey(current)) {
                return true;
            } else {
                hashtable.put(current, true);
            }

            current = current.getNext();
        }

        clearMarks(); // очищаем метки

        return false;
    }

    /**
     * Возвращает true - если список замкнут или имеет кольцо
     * Метод для каждого элемента проходит с начала списка до этого элемента
     * и проверят встречался уже этот элемент или нет.
     * Сложность - O(N2)
     */
    public boolean isCircledRetracing() {
        Node current = top;

        while(current != null) {
            current = current.getNext();

            Node trace = top;

            while(trace != current) {
                if (trace)
            }
        }

        return false;
    }

    /**
     * разрывает цикл
     * @return
     */
    public boolean breakCircle() {
        Node current = top;
        Node prev = null;

        while (current != null) {

            if (current.isMarked()) {
                clearMarks(); // очищаем метки
                prev.setNext(null);
                return true;
            } else {
                current.setMarked(true);
            }

            prev = current;
            current = current.getNext();
        }

        clearMarks(); // очищаем метки
        return false;
    }

    /**
     * Очищает метки узлов
     */
    private void clearMarks() {
        Node current = top;

        while (current != null && current.isMarked()) {
            current.setMarked(false);
            current = current.getNext();
        }
    }

    /**
     * Вставка элемента в начало списка
     * @param value
     */
    public void addToTheBeginning(double value) {
        Node node = new Node(value);

        if (top != null) {
            node.setNext(top);
        } else {
            last = node;
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
        }

        last = node;
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

                    if (current == last) {
                        last = previous;
                    }

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
            // если список цикличен, то прерываем while.
            if (current.isMarked()) {
                clearMarks(); // очищаем метки
                System.out.println("Список замкнут:");
                System.out.println("элемент " + current.getValue());
                break;
            } else {
                current.setMarked(true);
            }

            System.out.println(current.getValue());

            current = current.getNext();
        }
    }

}
