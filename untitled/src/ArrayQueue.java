public class ArrayQueue {
    private double[] baseStructure;
    private int front;
    private int rear;
    private int elementCount;

    public ArrayQueue(int size) {
        baseStructure = new double[size];
        front = 0;
        rear = -1;
        elementCount = 0;
    }

    public void insert(double element) {

        if (isFull()) {
            throw new IndexOutOfBoundsException();
        }

        // Циклический перенос.
        if (rear == baseStructure.length - 1) {
            rear = -1;
        }

        baseStructure[++rear] = element;

        elementCount++;
    }

    public double remove() {

        if (isEmpty()) {
            throw new NullPointerException();
        }

        double temp = baseStructure[front++];
        if (front == baseStructure.length) {
            front = 0;
        }

        elementCount--;
        return temp;
    }

    public int size() {
        return elementCount;
    }

    public double peekFront() {

        if (isEmpty()) {
            throw new NullPointerException();
        }

        return baseStructure[front];
    }

    public boolean isEmpty() {
        return elementCount == 0;
    }

    public boolean isFull() {
        return elementCount == baseStructure.length;
    }

    public void displayBaseStructure() {

        for (int i = 0; i < baseStructure.length; i++) {
            System.out.print(baseStructure[i] + " ");
        }

        System.out.println();
        System.out.println("---------");
    }
}
