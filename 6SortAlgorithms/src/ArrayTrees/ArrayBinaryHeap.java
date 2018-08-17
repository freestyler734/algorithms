package ArrayTrees;

/**
 * Реализация бинарной кучи на основе массива.
 * Бинарная куча(пирамида, сортирующее дерево) - бинарное дерово удовлетворяющее условиям:
 *  1) Значения в узлах потомков меньше или равно значению
 *     в узле родителя.
 *  2) Глубина всех листьев отличается не более чем на один слой (уровень).
 *  3) Последний слой(уровень) заполняется слева направо.
 */
public class ArrayBinaryHeap {
    private int size = 1;
    private double[] baseStructure;
    private int nextIndex = 0; // индекс в массиве, по которому произойдет очередная вставка.

    public ArrayBinaryHeap() {
        baseStructure = new double[size];
    }

    /**
     *
     * @param element
     */
    public void add(double element) {

        if (nextIndex >= size) {
            expandBaseStructure();
        }

        baseStructure[nextIndex++] = element;
        normalizeLastInsertedElement();
    }

    /**
     * Создает больший массив (на уровень дерева)
     * и переносит имеющиеся элементы.
     */
    private void expandBaseStructure() {
        double[] temp = baseStructure;
        size = 2 * size + 1;
        baseStructure = new double[size];

        for (int i = 0; i < temp.length; i++) {
            baseStructure[i] = temp[i];
        }
    }

    /**
     * Перемещает последний вставленный элемент
     * в подходящее место, чтобы дерево соответствовало условиям кучи.
     * Т.е. если элемент больше родителя, то меням их местами,
     * пока ненайдется больший родитель или не пройдем дальше корня.
     */
    private void normalizeLastInsertedElement() {
        int lastInsertedIndex = nextIndex - 1;
        int currentParentIndex = (lastInsertedIndex - 1) / 2; // родитель элемента с индексом i равен (i - 1) / 2, окрегленное до меньшего.

        // цикл продолжается пока последний вставленный элемент нестанет в корень и пока его родитель меньше него самого.
        while (lastInsertedIndex != 0 && baseStructure[lastInsertedIndex] > baseStructure[currentParentIndex]) {
            swap(lastInsertedIndex, currentParentIndex);

            lastInsertedIndex = currentParentIndex;
            currentParentIndex = (currentParentIndex - 1) / 2;
        }
    }

    /**
     * Удаляет верхний элемент из кучи.
     * Суть - заменяем первый элемент последним,
     * и меняем новый корень с большим потомком местами,
     * до тех пор пока не окажется больших потомков,
     * или не выйдем за пределы кучи.
     * @return
     */
    public double removeTop() {
        // заменяем первый элемент последним, а последний зануляем
        double removed = baseStructure[0];
        baseStructure[0] = baseStructure[nextIndex - 1];
        baseStructure[nextIndex - 1] = 0;

        int currentIndex = 0; // текущий индекс нового корня
        while (true) {
            int currentLeftChild = 2 * currentIndex + 1;
            int currentRightChild = 2 * currentIndex + 2;

            // вышли за пределы массива
            if (currentLeftChild > size || currentRightChild > size) {
                break;
            }

            // потомки меньше родитея (куча удовлетворяет условию)=> выходим из цикла
            if (baseStructure[currentIndex] >= baseStructure[currentLeftChild]
                && baseStructure[currentIndex] >= baseStructure[currentRightChild]) {
                break;
            }

            // меняем текщий элемент с большим потомком местами, и сохраняем его индекс
            if (baseStructure[currentLeftChild] > baseStructure[currentRightChild]) {
                swap(currentIndex, currentLeftChild);
                currentIndex = currentLeftChild;
            } else {
                swap(currentIndex, currentRightChild);
                currentIndex = currentRightChild;
            }
        }

        // удалили элемент, смешаем курсор индекса нового для вставки элемента
        // возвращаем значение удаленного корня.
        nextIndex--;
        return removed;
    }

    /**
     * Меняет местами элементы с указанными индексами в базовом массиве.
     * @param index1
     * @param index2
     */
    private void swap(int index1, int index2) {
        double temp = baseStructure[index1];
        baseStructure[index1] = baseStructure[index2];
        baseStructure[index2] = temp;
    }

    /**
     * Выводит дерево в консоль.
     */
    public void display(int elementSize) {
        int emptyElementCountOnLevel = (size - 1) / 2; // радиус дерева (кол-во мест для элементов слева до корня)

        // вывод корневого элемента
        printSpaces(elementSize * emptyElementCountOnLevel);
        emptyElementCountOnLevel /= 2;
        System.out.print(formatValueDisplayed(String.valueOf(baseStructure[0]), elementSize));
        System.out.println();

        // выводим оставшиеся узлы
        // цикл начинается с левого потомка корня(второго по счету узла)
        // и проходит по уровням дерева (или всем крайним левым элементам).
        for (int i = 1; i < size; i = 2 * i + 1) {
            printSpaces(elementSize * emptyElementCountOnLevel); // печатаем в консоль пробелы  упорядочевания дерева в консоли.

            // начиная с текущего узла,
            // печатаем все элементы уровня в дереве,
            // добавляя пробелы.
            int j = i;
            while (j < 2 * i + 1) {
                String element = String.valueOf(baseStructure[j]);
                System.out.print(formatValueDisplayed(element, elementSize));
                printSpaces(2 * elementSize * emptyElementCountOnLevel + elementSize);
                j++;
            }

            System.out.println();
            emptyElementCountOnLevel /= 2; // на каждом уровне кол-во пустых мест для элементов уменьшается вдвое.
        }
    }

    private String formatValueDisplayed(String value, int spaceForElament) {

        StringBuilder builder = new StringBuilder(value);

        while (builder.length() < spaceForElament) {
            builder.append(" ");

            if (builder.length() < spaceForElament) {
                builder.insert(0, " ");
            }
        }

        return builder.toString();
    }

    /**
     * Вывод заданное кол-во пробелов в консоль
     * @param count
     */
    private void printSpaces(int count) {
        while (count-- != 0) {
            System.out.print(" ");
        }
    }
}
