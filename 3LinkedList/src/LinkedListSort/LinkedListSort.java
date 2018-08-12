package LinkedListSort;

import OneDirectionLinkedList.Node;
import OneDirectionLinkedList.OneDirectionLinkedList;

/**
 * Сортировка на примере односвязного списка
 */
public class LinkedListSort {
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

    /**
     * Сортировка методом вставки (по возрастанию).
     *
     * Суть - алгоритм сортирует массив по мере прохождения по его элементам.
     * На каждой итерации берется элемент и сравнивается с каждым элементом в уже отсортированной части массива,
     * таким образом находя «свое место», после чего элемент вставляется на свою позицию.
     *
     * (В данном случае формируем новый спсок, но можно вставлять и в текущий,
     * потому что формирование нового списка(массива) выгодно для массивов,
     * для избежания лишних перестановок элементов(неотсортированной части)).
     *
     * -----------------------------------------------------------------
     * Сложность - O(N2)
     */

    public void insertionSort() {

        Node sorted = null;
        Node current = top;

        while (current != null) {

            Node input = sorted;
            Node prev = null;

            while (input != null && input.getValue() <= current.getValue()) {
                prev = input;
                input = input.getNext();
            }

            Node cell = new Node(current.getValue());

            if (prev != null) {
                prev.setNext(cell);
            } else {
                sorted = cell;
            }

            if (input != null) {
                cell.setNext(input);
            }

            current = current.getNext();

        }

        top = sorted;
    }

    /**
     * Сортировка методом выбора.
     * Суть - находим макс. элемент в неотсортированной части списка
     * и вставляем его в начало списка.
     * ------------------------------------------------------------
     * Сложность - O(N2)
     */
    public void selectionSort() {
        Node current = top;

        Node firstSorted = null;

        while (current != null) {

            Node maxCurrent = current;
            Node maxPrev = null;

            Node max = current;
            Node prev = firstSorted; // граница отсортированной части массива

            // ищем максимальный элемент в неотсортированной части
            while (maxCurrent != null) {
                if (maxCurrent.getValue() > max.getValue()) {
                    prev = maxPrev;
                    max = maxCurrent;
                }

                maxPrev = maxCurrent;
                maxCurrent = maxCurrent.getNext();
            }

            // если элемент перед найденным максимальным не пустой предыдущий элемент,
            // то меняем ссылку
            if (prev != null) {
                prev.setNext(max.getNext());
            }

            // в ситуации, когда максимальный элемент - текущий внешнего цикла,
            // то двигаемся дальше, т.к. этот элемент будет перенесен в начало.
            if (current == max) {
                current = current.getNext();
            }

            // если первый раз находим элемент(наибольший в списке),
            // то сохраняем ссылку на него как границу сортированной части,
            // потому что все последующие элементы будут меньше него
            // и будут добавляться в начало.
            if (current == top) {
                firstSorted = max;
            }

            // переносим найденный максимальныйэлемент в начало.
            max.setNext(top);
            top = max;
        }


    }

    public void display() {
        Node current = top;

        while(current != null) {
            System.out.println(current.getValue());
            current = current.getNext();
        }
    }
}
