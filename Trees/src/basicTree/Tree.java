package basicTree;

public class Tree {
    private Node root;
    private Node currentContainer; // узел в который добавляем элементы;
    private int width = 5; // макисмальное количество потомков у узла.

    public void add(String data) {
        Node current = new Node(data);

        if (root == null) {
            root = current;
            currentContainer = root;
        } else if (currentContainer.getChildrenCount() < width){
            currentContainer.addChild(current);
        } else {
            currentContainer = findCurrentContainer();
        }
    }


    /**
     * Находит элемент, к которому добавятся потомки.
     * @return
     */
    private Node findCurrentContainer() {
        Node current = root;



        return current;
    }
}
