package binaryTrees;

public class RBNode extends Node {

    private boolean isRed;

    public RBNode(int key, double data) {
        super(key, data);
    }

    public boolean isRed() {
        return isRed;
    }

    public void setRed(boolean red) {
        isRed = red;
    }
}
