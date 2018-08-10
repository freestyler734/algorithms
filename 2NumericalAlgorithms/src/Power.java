/**
 * Возведение в степень
 */
public class Power {
    public static void main(String[] args) {
        System.out.println(raiseToPower(7,6));
    }

    /**
     * Эффектиный метод для возведения числа в большую степень
     * @param base
     * @param pow
     * @return
     */
    public static int raiseToPower(int base, int pow) {
        int power = 1;
        int value = 1;

        while (power <= pow) {
            value *= power == 1 ? base : value;
            power *= 2;
        }
        power = power / 2;

        int currentPow = power / 2;
        while (power != pow) {
            if(currentPow == 1) {
                value *= base;
                power++;
            } else if(power + currentPow <= pow) {
                value *= Math.sqrt(value);
                power += currentPow;
            }

            currentPow /= 2;

        }

        return value;
    }
}
