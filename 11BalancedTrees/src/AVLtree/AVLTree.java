package AVLtree;

/**
 * Реализация АВЛ-дерева - бинарного дерева поиска,
 * где для каждого узла высота двух поддеревьев отличается не больше чем на единицу.
 */
public class AVLTree {
    private Node root;


    private void insert(Node current, int value) {

        current.height = 0; //

        if (current.getData() > value) {
            if (current.getLeft() == null) {
                current.setLeft(new Node(value));
                current.height++;
            }
            else insert(current.getLeft(), value);
            current.height = current.getLeft().height + 1;
            balance(current, current.getLeft());

        } else {
            if (current.getRight() == null) {
                current.setRight(new Node(value));
                current.height++;
            }
            else insert(current.getRight(), value);
            current.height = current.getRight().height + 1;
            balance(current, current.getRight());
        }

    }

    public void add(int value) {
        if (root == null) root = new Node(value);
        else insert(root, value);
    }

    /**
     * Малое правое вращение
     * @param parent
     * @param node
     */
    private void smallRightRotate(Node parent, Node node) {
        Node left = node.getLeft();

        if (parent != null) {
            if (parent.getData() > node.getData()) parent.setLeft(left);
            else parent.setRight(left);
        } else {
            root = left;
        }

        node.setLeft(left.getRight());
        left.setRight(node);
    }

    /**
     * Малое левое вращение
     * @param parent
     * @param node
     */
    private void smallLeftRotate(Node parent, Node node) {
        Node right = node.getRight();

        if (parent != null) {
            if (parent.getData() > node.getData()) parent.setLeft(right);
            else parent.setRight(right);
        } else {
            root = right;
        }

        node.setRight(node.getLeft());
        right.setLeft(node);
    }

    /**
     * Большое правое вращение
     * @param parent
     * @param node
     */
    private void complexRightRotate(Node parent, Node node) {
        smallLeftRotate(node, node.getLeft());
        smallRightRotate(parent, node);
    }

    /**
     * Большое левое вращение
     * @param parent
     * @param node
     */
    private void complexLeftRotate(Node parent, Node node) {
        smallRightRotate(node, node.getRight());
        smallLeftRotate(parent, node);
    }

    private void balance(Node parent, Node current) {

        Node left = current.getLeft();
        Node right = current.getRight();
        if (left != null && right != null) {
            int balanceCoeff = current.getLeft().height - current.getRight().height;

            if (balanceCoeff > 1) {
                if (left.getLeft().height - left.getRight().height > 0) {
                    smallRightRotate(parent, current);
                } else if(left.getLeft().height - left.getRight().height > 0) {
                    complexRightRotate(parent, current);
                }
            } else if (balanceCoeff < -1) {
                if (left.getLeft().height - left.getRight().height > 0) {
                    smallLeftRotate(parent, current);
                } else if(left.getLeft().height - left.getRight().height > 0) {
                    complexLeftRotate(parent, current);
                }
            }
        }
    }

}
