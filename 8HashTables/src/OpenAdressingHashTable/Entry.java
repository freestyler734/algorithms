package OpenAdressingHashTable;

public class Entry {
    private int key;
    private double value;

    public Entry(int key, double value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public double getValue() {
        return value;
    }
}
