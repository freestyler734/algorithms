import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        /*LinkList theList = new LinkList(); // Создание нового списка
        theList.insertLast(22, 2.99); // Вставка 4 элементов
        theList.insertLast(44, 4.99);
        theList.insertLast(26, 6.99);
        theList.insertLast(8, 8.99);
        theList.insertLast(9, 8.99);
        theList.insertAfter(12, 8.99, 26);
        theList.insertAfter(2, 8.99, 12);
      //  theList.insertAfter(1, 8.99, 9);
        theList.deleteByKey(22);
        theList.insertLast(90, 8.99);*/


        /*LinkList.Node f = theList.find(44); // Поиск элемента
        if( f != null)
            System.out.println("Found link with key " + f.id);
        else
            System.out.println("Can’t find link");
        LinkList.Node d = theList.remove(66); // Удаление элемента
        if( d != null )
            System.out.println("Deleted link with key " + d.id);
        else
            System.out.println("Can’t delete link");
        theList.displayList(); // Вывод содержимого списка*/

        double[] res = {120,11,112,53,111,30,645,23,65,50};
        //double[] res = {10,11,12,5,111,300,65,123,165,50};
        //double[] res = {0, 4, 10, 3, 30};
        //int mid = (0 + res.length - 1) / 2;
        //System.out.println("mid = " + mid);

//        RecursionUtil.merge(res, 0, mid + 1, res.length - 1);

        //ComplexSort.shellSort(res);
        ComplexSort.quickSort(res);

        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}

