import CircledLinkedList.CircledLinkedList;


public class Main {

    public static void main(String[] args) {
        CircledLinkedList list = new CircledLinkedList();

        list.addToTheBeginning(3);
        list.addToTheBeginning(2);
        list.addToTheEnd(321);
        list.addToTheBeginning(53);
        list.addToTheEnd(343);
        list.addToTheBeginning(323);
        list.addToTheBeginning(37);

        list.makeCircle();
        //list.addToTheEnd(3123);
        System.out.println("Is list circled: " + list.isCircledHashTable());
        list.display();

        list.breakCircle();
        System.out.println("------------------------");
        System.out.println("Is list circled: " + list.isCircledHashTable());
        list.display();


    }
}
