public class ArrayPriorityQueue {

    private int elementCount;
    private double[] baseStructure;

    public ArrayPriorityQueue(int size) {
        baseStructure = new double[size];
        elementCount = 0;
    }

    public void insert(double element) {

        if (isFull()) {
            throw new IndexOutOfBoundsException();
        }

        if (elementCount == 0) {
            baseStructure[elementCount++] = element;
        } else {
            int i;
            for (i = elementCount - 1; i >= 0; i--) {
                if (element > baseStructure[i]) {
                    baseStructure[i + 1] = baseStructure[i];
                } else {
                    break;
                }
            }

            baseStructure[i + 1] = element;
            elementCount++;
        }
    }

    public double remove() {
        return baseStructure[--elementCount];
    }

    public double peekMin() {
        return baseStructure[elementCount - 1];
    }

    public void displayBaseStructure() {

        for (int i = 0; i < baseStructure.length; i++) {
            System.out.print(baseStructure[i] + " ");
        }

        System.out.println();
        System.out.println("---------");
    }

    public boolean isEmpty() {
        return elementCount == 0;
    }

    public boolean isFull() {
        return elementCount == baseStructure.length;
    }

    public int size() {
        return elementCount;
    }
}
