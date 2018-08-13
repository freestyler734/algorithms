package stacks.ArrayStack;

/**
 * Реализация стека на основе массива
 * Во избежание пустых ячеек и уменьшения скорости вставки удаления,
 * создадим курсор который будет указывать на последний элемент
 * и вставлять элементы будем в конец.
 */
public class ArrayStack {

    private double[] baseStructure;
    private int size;
    private int cursor; //указывает на последний вставленный элемент

    public ArrayStack(int size) {
        this.size = size;
        baseStructure = new double[size];
        cursor = -1;
    }

    /**
     * Вставляет элемент в стек.
     * @param value
     */
    public void push(double value) {
        if (cursor >= size - 1) {
            throw new IndexOutOfBoundsException();
        }

        baseStructure[++cursor] = value;
    }

    /**
     * Возвращает удаленный элемент из списка.
     * @return
     */
    public double pop() {

        if (cursor < 0) {
            throw new IndexOutOfBoundsException();
        }

        return baseStructure[cursor--];
    }

    /**
     * Возвращает true - если стек содержит элементы,
     * иначе false.
     * @return
     */
    public boolean hasElements() {
        return cursor >= 0;
    }

    public void display() {
        for (int i = 0; i <= cursor; i ++) {
            System.out.println(baseStructure[i]);
        }
    }
}
