package ArrayTrees;

/**
 * Реализация бинарного дерева на базе массива.
 */
public class ArrayBinaryTree {

    private int size;
    private double[] baseStructure;
    private int lastInserted = -1;

    public ArrayBinaryTree(int size) {
        this.size = size;
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
        if (++lastInserted > size) {
           // System.out.println("Дерево содержит максимальное количество элементов.");
            throw new IndexOutOfBoundsException();
        }
        baseStructure[lastInserted] = element;
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

    private void printSpaces(int count) {
        while (count-- != 0) {
            System.out.print(" ");
        }
    }

    /**
     * Выводит дерево в консоль.
     */
    public void display() {
        int spaceElements = (size - 1) / 2; // радиус дерева (кол-во мест для элементов слева до корня)
        int space = 5;

        printSpaces(space * spaceElements);
        spaceElements /= 2;

        System.out.print(baseStructure[0]);
        System.out.println();

        for (int i = 1; i < size; i = 2 * i) {
            printSpaces(space * spaceElements);

            int j = i;
            while (j <= 2 * i) {
                int index = 2 * i;
                if (index < size) {
                    System.out.print(baseStructure[(index + (j - i) - 1)]);
                    printSpaces(2 * space * spaceElements + space);
                    j++;
                }
            }

            System.out.println();
            spaceElements /= 2;

        }


    }
}
