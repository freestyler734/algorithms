import stacks.LinkedListStack.LinkedListStack;

public class Main {

    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack();

        stack.push(21);
        stack.push(21231);
        stack.push(421);
        stack.push(134);
        stack.push(261);
        stack.push(231);
        stack.push(221);
        stack.push(2551);
        stack.push(8);
        stack.push(1);
        stack.push(351);
        stack.push(26);

        stack.display();

        System.out.println("--------------------------");
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();


        stack.display();
    }
}
