package CircledLinkedList;


import java.util.Hashtable;

/**
 * Односвязанный список, который может содержать циклы (не обязательно быть полностью циклом)
 */
public class CircledLinkedList {
    private Node top;
    private Node last;

    /**
     * Смыкает весь список в кольцо
     * @return
     */
    public boolean makeWholeCircle() {

        if (last != null && top != null) {
            last.setNext(top);
            return true;
        }

        return false;
    }

    /**
     * Смыкает часть списка в кольцо на nodeNum узле
     * @param nodeNum
     * @return
     */
    public boolean makePartCircle(int nodeNum) {

        // ограничение для списков, состоящих меньше чем из 2-х узлов
        if (nodeNum < 3) {
            return false;
        }

        Node current = top;

        while (nodeNum != 1) {

            if (current == null) {
                return false;
            }

            current = current.getNext();
            nodeNum--;
        }

        // проверка, чтобы элемент не замкнулся сам на себя
        if (last != null && last != current) {
            last.setNext(current);
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
     * Существует 2 случая циклов:
     * 1) Цикл состоит из всех элементов списка (полный цикл)
     *  В этом случае для каждого узла, кроме первого проходим с начала списка до текущего узла
     *  и проверяем, содержат ли два разных узла ссылки на одинаковый следующий элемент
     *  (такая ситуация возможна для последнего узла цикла списка и элемента перед которым начинается неполный цикл)
     * 2) Все элементы списка образуют цикл
     *  Т.к. мы всегда начинаем алгоритм со второго элемента,
     *  если мы попали в первый элемент, то цикл замкнут.
     * -----------------
     * Сложность - O(N2)
     */
    public boolean isCircledRetracing() {
        Node current = top.getNext();

        while(current.getNext() != null) {

            // проверка для цикла из всех элементов цикла
            if (current == top) {
                return true;
            }

            Node tracer = top;

            while(tracer != current) {

                // проверка для цикла из части списка
                if (current.getNext() == tracer.getNext()) {
                    return true;
                }

                tracer = tracer.getNext();


            }

            current = current.getNext();
        }

        return false;
    }


    /**
     * алгоритм реверсирует (меняет направление связей в списке)
     * используется для определеням циклов с помощью риверсии.
     * Возвращает ссылку на последний реверсированный элемент.
     * -------------------
     * Сложность - O(N)
     * @param start
     * @return
     */
    private Node reverseList(Node start) {

        Node prev = null;
        Node current = start;

        // проходим по списку и меняем связи.
        // while не зациклится,
        // 1) в случае частичного цикла в списке,
        //    т.к. из-за смены связей алгоритм вернется в начало.
        // 2) при полном цикле, проверяем что, если второй пришли в начало, значит цикл
        //    (это условие также актуальнои для первого случая)
        while (current != null) {

            // меняем ссылку
            Node next = current.getNext();
            current.setNext(prev);

            System.out.println(current.getValue());

            if (current == start && prev != null) {
                // т.к. мы не перешли к сл. элементу.
                return current;
            }

            // переходим к следующему элементу
            prev = current;
            current = next;
        }

        // prev, т.к. current = null
        return prev;
    }

    /**
     * Проеряет содержит ли список цикл.
     * Суть - 2 раза реверсируем цикл
     * и если оба раза последний реверсируемый элемент одинаковый,
     * значит цикл замкнутый.
     * Пр. во всех случаях повторное реверсирование восстанавливает исходный ссылки.
     * ----------------------
     * Сложность O(N).
     * @return
     */
    public boolean isCyrcledReversing() {
        Node first = this.reverseList(top);
        System.out.println("--------------------");
        Node second = this.reverseList(first);
        return first == second;
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

    /**
     * ТОЛЬКО ДЛЯ ПРОВЕРКИ РЕВЕРСИРОВАНИЯ СПИСКА!
     */
    public void reverseTest() {
        Node top = this.top;
        Node last = this.last;

        Node point = this.reverseList(top);
    }

}
