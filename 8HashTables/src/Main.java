import OpenAdressingHashTable.LinearProbingHashTable;


public class Main {

    public static void main(String[] args) {

        LinearProbingHashTable hashTable = new LinearProbingHashTable();

        hashTable.put(1,1);
        hashTable.put(2,2);
        hashTable.put(6,4);
        hashTable.put(11,3);
        hashTable.put(43,9);
        hashTable.put(8,5);
        hashTable.put(12,12);
        hashTable.put(544,324);
        hashTable.put(51246,649);
        hashTable.put(32,2135);
        hashTable.put(75,425135);
        hashTable.display();

        System.out.println();
        System.out.println("-----------");
        System.out.println(hashTable.get(51246));
        System.out.println(hashTable.remove(51246));
        System.out.println(hashTable.remove(75));
        System.out.println(hashTable.remove(32));
        System.out.println("-----------");
        hashTable.display();

    }
}
