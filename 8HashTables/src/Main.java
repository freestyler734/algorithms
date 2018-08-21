import OpenAdressingHashTable.DoubleHashingHashTable;
import OpenAdressingHashTable.LinearProbingHashTable;
import OpenAdressingHashTable.SquareProbingHashTable;


public class Main {

    public static void main(String[] args) {

        DoubleHashingHashTable hashTable2 = new DoubleHashingHashTable();

        hashTable2.put(1,1);

        hashTable2.put(2,2);
        hashTable2.put(6,4);
        hashTable2.put(11,3);
        hashTable2.put(43,9);
        hashTable2.put(8,5);
        hashTable2.put(12,12);
        hashTable2.put(544,324);
        hashTable2.put(51246,649);
        hashTable2.put(32,2135);
        hashTable2.put(75,425135);
        hashTable2.put(101,1);
        hashTable2.put(1001,1);


        hashTable2.remove(75);
        hashTable2.display();

        System.out.println();
        System.out.println("-----------");
        System.out.println(hashTable2.get(11));
        System.out.println("-----------");


    }
}
