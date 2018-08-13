public class ArrayStack {
    private double[] baseStructure;
    private int top;

    public ArrayStack(int length) {
        baseStructure = new double[length];
        top = -1;
    }

    public void push(double element) {
        if (!isFull()) {
            baseStructure[++top] = element;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    // Извлечение элемента с удалением.
    public double pop() {
        if (!isEmpty()) {
            return baseStructure[--top];
        } else  {
            throw new NullPointerException();
        }
    }

    public double peek() {
        if (!isEmpty()) {
            return baseStructure[top];
        } else {
            throw new NullPointerException();
        }
    }

    public void displayBaseStructure() {

        for (int i = 0; i < baseStructure.length; i++) {
            System.out.print(baseStructure[i] + " ");
        }

        System.out.println();
        System.out.println("---------");
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == baseStructure.length - 1;
    }

}
