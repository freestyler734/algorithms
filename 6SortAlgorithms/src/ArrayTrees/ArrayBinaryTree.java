package ArrayTrees;

/**
 * Реализация бинарного дерева(дерево с неболее, чем двумя потомками) на базе массива.
 */
public class ArrayBinaryTree {

    private int size = 1;
    private double[] baseStructure;
    private int lastInserted = -1;

    public ArrayBinaryTree() {
        baseStructure = new double[size];
    }

    /**
     * Добавляет element в бинарное дерово.
     *
     * <strong>Способ хранения бинарного дерева в массиве следующий:<strong/>
     * <ul>
     *  <li>Для каждого узла с индексом в массиве i индексы его дочерних узлов равны: 2i + 1 для левого и 2i + 2 для правого
     *  (если значение меньше размера массива - 1).</li>
     *  <li>Для каждого узла с индексом в массиве i индекс его родителя равно (i - 1) / 2 округленное до меньшего
     *  (если значение больше или равно 0).</li>
     * </ul>
     * @param element
     */
    public void add(double element) {
        // если индекс последнего свободного элемента
        // больше размера массива - 1, то расширяем массив еще для одного уровня.
        if (++lastInserted > size - 1) {
            this.expandArray();
        }
        baseStructure[lastInserted] = element;
    }

    /**
     * Создает больший массив (на уровень дерева)
     * и переносит имеющиеся элементы.
     */
    private void expandArray() {
        double[] temp = baseStructure;
        size = 2 * size + 1;
        baseStructure = new double[size];

        for (int i = 0; i < temp.length; i++) {
            baseStructure[i] = temp[i];
        }
    }

    /**
     * Возвращает левый потомок узла.
     * Для каждого узла с индексом в массиве i индекс его левого дочернего узла: 2i + 1.
     * @param index
     * @return
     */
    private double getLeft(int index) {

        if (2 * index + 1 > size) {
            throw new IndexOutOfBoundsException();
        }

        return baseStructure[2 * index + 1];
    }

    /**
     * Устанавливает левого отомка для элемента.
     * @param index
     * @param value
     */
    private void setLeft(int index, double value) {
        if (2 * index + 1 > size) {
            throw new IndexOutOfBoundsException();
        }

        baseStructure[2 * index + 1] = value;
    }

    /**
     * Возвращает правый потомок узла.
     * Для каждого узла с индексом в массиве i индекс его правого дочернего узла: 2i + 2.
     * @param index
     * @return
     */
    private double getRight(int index) {

        if (2 * index + 2 > size) {
            throw new IndexOutOfBoundsException();
        }

        return baseStructure[2 * index + 2];
    }

    /**
     * Устанавливает правого потомка для элемента.
     * @param index
     * @param value
     */
    private void setRight(int index, double value) {
        if (2 * index + 2 > size) {
            throw new IndexOutOfBoundsException();
        }

        baseStructure[2 * index + 2] = value;
    }

    /**
     * Возвращает родителя узла.
     * ДДля каждого узла с индексом в массиве i индекс его родителя равно (i - 1) / 2 округленное до меньшего.
     * @param index
     * @return
     */
    private double getParent(int index) {

        if ((index - 1) / 2 < 0) {
            throw new IndexOutOfBoundsException();
        }

        return baseStructure[(index - 1) / 2];
    }

    public void delete(double value) {

        for (int i = 0; i < size; i++) {
            if (value == baseStructure[i]) {

                if (getRight(i) != 0) {
                    baseStructure[i] = getRight(i);
                    setRight(i,0);
                } else if (getLeft(i) != 0) {
                    baseStructure[i] = getLeft(i);
                    setLeft(i,0);
                } else {
                    baseStructure[i] = 0;
                }
            }
        }
    }

    /**
     * Возвращает потенциальную глубину дерева.
     * @return
     */
    private int getDepth() {

        int depth = 0;

        double i = 1;
        while (i <= size) {
            i *= 2;
            depth++;
        }

        return depth;
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
