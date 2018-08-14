import queues.LinkedListQueue.LinkedListQueue;
import stacks.ArrayStack.ArrayStack;
import stacks.LinkedListStack.LinkedListStack;
import stacks.StackAlgorithms.StackAlgorithms;

public class Main {

    public static void main(String[] args) {
        LinkedListQueue queue = new LinkedListQueue();

        queue.push(21);
        queue.push(21231);
        queue.push(421);
        queue.push(134);
        queue.push(261);
        queue.push(231);
        queue.push(221);

        queue.push(2551);
        queue.push(8);
        queue.push(1);

        queue.push(351);
        queue.push(26);

        queue.pop();
        queue.pop();
        queue.pop();
        queue.pop();

        queue.display();


    }
}
