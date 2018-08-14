package queues.ArrayQueue;

/**
 * Реализация очереди на основе массива.
 * Т.к. при вставке в очередь а потом и удалении элементов
 * указатели на первый и последний элемент смещаются к концу массива,
 * то в конце окажется ситуация при которой невозможно добавить новые элементы
 * при почти пустом массиве. Поэтому реализуем циклическую вставку в массив.
 */
public class ArrayQueue {
    private double[] baseStructure;
    private int size;
    private int next = 0; // место куда вставим следующий элемент.
    private int last = 0; // последний вставленный элемент.

    public ArrayQueue(int size) {
        this.size = size;
        baseStructure = new double[size];
    }

    public void push(double value) {

        if ((next + 1) % size == last) {
            throw new ArrayIndexOutOfBoundsException();
        }

        baseStructure[next] = value;
        next = (next + 1) % size;
    }

    public double pop() {

        if (next == last) throw new ArrayIndexOutOfBoundsException();

        double popped = baseStructure[last];
        last = (last + 1) % size;

        return popped;
    }
}
