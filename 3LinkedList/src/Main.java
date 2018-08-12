import CircledLinkedList.CircledLinkedList;


public class Main {

    public static void main(String[] args) {
        CircledLinkedList list = new CircledLinkedList();

        list.addToTheEnd(3);
        list.addToTheEnd(2);
        list.addToTheEnd(321);
        list.addToTheEnd(53);
        list.addToTheEnd(343);
        list.addToTheEnd(323);
        list.addToTheEnd(37);

        list.makePartCircle(2);
        //list.makeWholeCircle();
        //list.addToTheEnd(3123);
        System.out.println("Is list circled: " + list.isCyrcledReversing());
        list.display();
        list.breakCircleFloydMethod();

        System.out.println("--------------------------");
        System.out.println("Is list circled: " + list.isCyrcledReversing());
        list.display();



    }
}
