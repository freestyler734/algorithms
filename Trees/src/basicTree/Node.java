package basicTree;

import java.util.LinkedList;
import java.util.List;

/**
 * Реализация класса для представления узла дерева,
 * которое может содержать сколько угодно потомков.
 */
public class Node {
    private String data;
    private List<Node> children;
    private int childrenCount;

    public Node(String data) {
        this.data = data;
        children = new LinkedList<>();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public List<Node> getChildren() {
        return children;
    }

    public int getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(int childrenCount) {
        this.childrenCount = childrenCount;
    }
}
