import queues.ArrayQueue.*;

public class Main {

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(12);

        queue.push(21);
        queue.push(21231);
        queue.push(421);
        queue.push(134);
        queue.push(261);
        queue.push(231);
        queue.push(221);

        queue.pop();
        queue.pop();
        queue.pop();
        queue.pop();

        queue.push(2551);
        queue.push(8);
        queue.push(1);

        queue.push(351);
        queue.push(26);

        queue.pop();
        queue.pop();
        queue.pop();
        queue.pop();

    }
}
