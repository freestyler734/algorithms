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
        Node second = this.reverseList(first);
        return first == second;
    }


    /**
     * Алгоритм поиска циклов Флойда (алгоритм кролика и черепахи)
     * НЕ РАБОТАЕТ ДЛЯ ПОЛНЫХ ЦИКЛОВ, КРОЛИК И ЧЕРЕПАХА ВСТРЕЧАЮТСЯ В НАЧАЛЕ СПИСКА
     * ЭТУ ЗАДАЧУ(С ПОЛНЫМ СПИСКОМ) МОЖНО РЕШИТЬ ГОРАЗДО ПРОЩЕ.
     *                  Суть:
     * Дано:
     * Черепаха проходит по одной ячейки
     * Кролик проходит по две ячейки.
     * L - кол-во ячеек внутри цикла
     * T - кол-во шагов до начала цикла (кол-во шагов пройденных черепахой)
     * H - расстояние от начала цикла до того положения, в котором за T количество шагов черепахи оказался «кролик».
     *
     * Т.к. кролик движется в 2 раза быстрее то к моменту, когда черепаха попадет в цикл,
     * кролик пройдет 2 * T ячеек
     *
     * К этому моменту кролику, чтобы дойти до черепахи В ЦИКЛЕ (начало цикла) нужно пройти L - H ячеек.
     * Следовательно кролик догонит черепаху за L - H шагов в цикле, т.к. кролик за раз проодит 2 ячейки, черепаха 1.
     *
     * В момент встречи оба бадут находится за H до начала цикла, т.к. черепаха к этому времени пройдет (по 1 ячейке) L - H с начала цикла
     * следовательно в момент встречи оба будут на ячейке L - (L - H) = H.
     * Чтобы дойти до начала цикла, нужно пройти с текущей T ячеек, т.к. кролик оказался в ячейке H, когда черепаха прошла T ячеек.
     *
     *
     * стр. 92
     *
     * АЛГОРИТМ:
     * 1. Запускаем «черепаху» из начала списка со скоростью одна ячейка за шаг
     *    и «кролика» со скоростью две ячейки за шаг.
     * 2. Если «кролик» найдет ссылку null, список не содержит цикла.
     * 3. Если «кролик» догонит «черепаху», перезапускаем его из начала списка со скоростью одна ячейка за шаг,
     *    в то время как «черепаха» продолжает двигаться в прежнем темпе.
     * 4. Когда «кролик» и «черепаха» снова встретятся, они будут находиться в начале цикла.
     *    Оставляем «кролика» в этом месте, чтобы он мог «отдохнуть», пока «черепаха» движется по циклу.
     *    Момент, когда указатель Next «черепахи» покажет на ячейку, где ждет «кролик», и будет означать конец цикла.
     * 5. Чтобы прервать цикл, устанавливаем указатель «черепахи» Next на null.
     *
     * @return
     */
    public boolean breakCircleFloydMethod() {

        Node rabbit = top;
        Node turtle = top;

        // находим ячейку встречи кролика и черепахи,
        // если цикл есть
        while (rabbit != turtle || rabbit == top) {

            // если кролик пустой, значит цикла нет
            if (rabbit == null) {
                return false;
            }

            // кролик шагает по 2 ячейки
            Node rabbitNext = rabbit.getNext();
            if (rabbitNext == null) {
                return false;
            } else {
                rabbit = rabbitNext.getNext();
            }

            turtle = turtle.getNext();
        }

        rabbit = top;

        // запускаем кролика сначала, для отсчета T ячеек,
        // и встречи черепахи и кролика.
        while (rabbit != turtle) {
            rabbit = rabbit.getNext();
            turtle = turtle.getNext();
        }

        // ищем ячейку образующую цикл (последняя ячейка списка)
        while (turtle.getNext() != rabbit) {
            turtle = turtle.getNext();
        }

        // ломаем цикл
        turtle.setNext(null);

        return true;

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

        clearMarks(); // очищаем метки
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
