package returningAlgorithms;

import java.util.Collections;
import java.util.List;

public class ConeDevTest {

    public static boolean test(int[][] array, List result, int index) {

        if (index == 12) return true;

        for (int i = index; i < 12; i++) {

            if (check(result, array[i])) {

                result.add(array[i]);

                if (test(array, result, index + 1)) {
                    return true;
                }

                result.remove(index);
            }

        }

        return false;
    }

    private static boolean check(List list, int[] element) {

        if (list.size() == 0) return true;

        int[] first;
        int[] second;
        int[] third;
        int[] forth;
        int[] fifth;
        int[] sixth;
        int[] seventh;
        int[] eighth;
        int[] nineth;
        int[] tenth;
        int[] eleventh;


        switch (list.size()) {
            case 0:
                return true;
            case 1:
                first = (int[]) list.get(0);
                if (first[1] + element[0] > 10 || first[3] + element[2] > 10) return false;
                return true;
            case 2:
                first = (int[]) list.get(0);
                if (first[2] + element[1] > 10) return false;
                return true;
            case 3:
                first = (int[]) list.get(0);
                second = (int[]) list.get(1);
                third = (int[]) list.get(2);
                if (first[2] + element[0] + third[1] > 10 ||
                        first[3] + element[1] + second[2] > 10) return false;
                return true;
            case 4:
                first = (int[]) list.get(0);
                second = (int[]) list.get(1);
                forth = (int[]) list.get(3);
                if (forth[3] + element[2] > 10 ||
                        first[3] + second[2] + forth[1] + element[0] > 10) return false;
                return true;
            case 5:
                second = (int[]) list.get(1);
                fifth = (int[]) list.get(4);
                if (fifth[3] + element[2] > 10 ||
                        second[3] + fifth[1] + element[0] > 10) return false;
                return true;
            case 6:
                third = (int[]) list.get(2);
                forth = (int[]) list.get(3);
                if (third[2] + element[0] > 10 ||
                        third[3] + forth[2] + element[1] > 10) return false;
                return true;
            case 7:
                third = (int[]) list.get(2);
                forth = (int[]) list.get(3);
                fifth = (int[]) list.get(4);
                seventh = (int[]) list.get(6);
                if (seventh[3] + element[2] > 10 ||
                        third[3] + forth[2] + seventh[1] + element[0] > 10 ||
                        forth[3] + fifth[2] + element[1] > 10) return false;
                return true;
            case 8:
                forth = (int[]) list.get(3);
                fifth = (int[]) list.get(4);
                sixth = (int[]) list.get(5);
                eighth = (int[]) list.get(7);
                if (eighth[3] + element[2] > 10 ||
                        forth[3] + fifth[2] + eighth[1] + element[0] > 10 ||
                        fifth[3] + sixth[2] + element[1] > 10) return false;
                return true;
            case 9:
                fifth = (int[]) list.get(4);
                sixth = (int[]) list.get(5);
                nineth = (int[]) list.get(8);
                if (sixth[3] + element[1] > 10 ||
                        nineth[3] + element[2] > 10 ||
                        fifth[3] + sixth[2] + nineth[1] + element[0] > 10) return false;
                return true;
            case 10:
                seventh = (int[]) list.get(6);
                eighth = (int[]) list.get(7);
                nineth = (int[]) list.get(8);
                if (seventh[3] + eighth[2] + element[0] > 10 ||
                        eighth[3] + nineth[2] + element[1] > 10) return false;
                return true;
            case 11:
                eighth = (int[]) list.get(7);
                nineth = (int[]) list.get(8);
                tenth = (int[]) list.get(9);
                eleventh = (int[]) list.get(10);
                if (eleventh[3] + element[2] > 10 ||
                        nineth[3] + tenth[2] + element[1] > 10 ||
                        eighth[3] + nineth[2] + eleventh[1] + element[0] > 10) return false;
                return true;
        }
        return true;
    }


    private void displayBlock(int[] block) {

        System.out.print(block[0] + "  " + block[1]);
        System.out.println();
        System.out.println();
        System.out.print(block[2] + "  " + block[3]);
    }

    public void display(List list) {

        for (int i = 0; i < 12; i++) {
            int[] block = (int[]) list.get(i);
            if (i == 0 || i == 1 || i == 11 || i == 12) {
                System.out.print("    ");
                System.out.print(block[0] + "  " + block[1]);
            }
        }
    }
}
