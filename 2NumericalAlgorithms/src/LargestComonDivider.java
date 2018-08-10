/**
 * Наибольший общий делитель (НОД)
 */
public class LargestComonDivider {

    public static void main(String[] args) {
        System.out.println(euclidAlgorithm(3,4));
    }

    /**
     * Алгоритм Евклида для нахождения НОД.
     * @param first
     * @param second
     * @return
     */
    public static int euclidAlgorithm(int first, int second) {
        int a, b;
        if(first == second ) {
            return first;
        } else if(first > second) {
            a = first;
            b = second;
        } else {
            a = second;
            b = first;
        }

        while (b != 0) {
            int rest = a % b;
            a = b;
            b = rest;
        }
        return a;
    }
}
