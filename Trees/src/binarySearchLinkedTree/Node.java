package binarySearchLinkedTree;

/**
 * Реализация класса для представления узла
 * связанного бинарного дерева поиска.
 */
public class Node {
    private int data;
    private Node left;
    private Node right;
    // флаг, для обозначения использования ссылки на левого потомка как ветки
    // или как треда (ссылки на отличный от потомка элемент)
    private boolean isLeftBranch;
    // флаг, для обозначения использования ссылки на правого потомка как ветки
    // или как треда (ссылки на отличный от потомка элемент)
    private boolean isRightBranch;

    public Node(int data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public boolean isLeftBranch() {
        return isLeftBranch;
    }

    public void setLeftBranch(boolean leftBranch) {
        isLeftBranch = leftBranch;
    }

    public boolean isRightBranch() {
        return isRightBranch;
    }

    public void setRightBranch(boolean rightBranch) {
        isRightBranch = rightBranch;
    }
}
