import stacks.ArrayStack.ArrayStack;
import stacks.LinkedListStack.LinkedListStack;
import stacks.StackAlgorithms.StackAlgorithms;

public class Main {

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(12);

        stack.push(21);
        stack.push(21231);
        stack.push(421);
        stack.push(134);
        stack.push(261);
        stack.push(231);
        stack.push(221);
        stack.pop();
        stack.pop();
        stack.pop();
        stack.push(2551);
        stack.push(8);
        stack.push(1);
        stack.pop();
        stack.push(351);
        stack.push(26);

        stack.display();

        System.out.println("--------------------------");

        StackAlgorithms.insertionSort(stack, 7).display();
    }
}
