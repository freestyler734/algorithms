package binaryTrees;

public class BinaryTree {
    private Node root;

    public void find(int key) {
        if (root == null) {
            System.out.println("Tree is empty");
        }

        Node current = root;
        while(current.getKey() != key) {

            if (current.getKey() > key) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }

            if (current == null) {
                System.out.println("Not found!");
                return;
            }
        }

        System.out.println("Found " + current.getData());
    }

    public void insert(int key, double data){

        Node node = new Node(key, data);
        if (root == null) {
            root = node;
        } else {
            Node current = root;
            Node parent;
            while (true) {

                parent = current;

                if (current.getKey() > key) {
                    current = current.getLeft();

                    if (current == null) {
                        parent.setLeft(node);
                        return;
                    }

                } else {
                    current = current.getRight();

                    if (current == null) {
                        parent.setRight(node);
                        return;
                    }
                }
            }
        }
    }

    public Node getMin() {

        if (root == null) {
            System.out.println("Tree is empty");
            return root;
        }

        Node current = root;

        while (current.getLeft() != null) {
            current = current.getLeft();
        }

        return current;
    }

    public Node getMax() {

        if (root == null) {
            System.out.println("Tree is empty");
            return root;
        }

        Node current = root;

        while (current.getRight() != null) {
            current = current.getRight();
        }

        return current;
    }

    // Удаление элементов.
    public boolean delete(int key){

        if (root == null) {
            return false;
        }

        Node current = root;
        Node parent = root;
        boolean isLeft = false;
        while (current.getKey() != key) {

            parent = current;

            if (current.getKey() > key) {
                isLeft = true;
                current = current.getLeft();
            } else {
                isLeft = false;
                current = current.getRight();
            }
        }

        if (current == null) {
            return false;
        }

        if (current.getLeft() == null && current.getRight() == null) {
            if (current == root) {
                root = null;
            } else if (isLeft) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        } else if (current.getRight() == null) {
            if (current == root) {
                root = current.getLeft();
            } else if (isLeft) {
                parent.setLeft(parent.getLeft());
            } else {
                parent.setRight(parent.getLeft());
            }
        } else if (current.getLeft() == null) {
            if (current == root) {
                root = parent.getRight();
            } else if (isLeft) {
                parent.setLeft(current.getRight());
            } else {
                parent.setRight(current.getRight());
            }
        } else {

            Node successor = getSuccessor(current);

            // Заменяем удаляемый элемент найденным.
            if (current == root) {
                root = successor;
            } else if(isLeft) {
                parent.setLeft(successor);
            } else {
                parent.setRight(successor);
            }
            // Левый элемент родителя в левый элемент приемника.
            successor.setLeft(current.getLeft());
        }

        return true;
    }

    //Поиск узла который заменит удаляемый узел с двумя потомками
    public Node getSuccessor(Node node) {
        //Смотрим всех левых потомков у правого потомка (наим. из наиб.)
        Node current = node.getRight();
        Node successor = node;
        Node successorParent = node;

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeft();
        }

        // Если найденый узел не непосредственный правый потомок удаляемого узла,
        // то мы вместо найденного узла ставим его правый потомок (даже при null)
        // и на место правого потомка найденного элемента ставим правый потомок удаляемого узла.
        if (node.getRight() != successor) {
            // Фактически заменяет преемника его правым поддеревом
            successorParent.setLeft(successor.getRight());
            // Оставляет правого потомка удаляемого узла на положенном месте
            // (это происходит автоматически, когда преемник является правым потомком удаляемого узла).
            successor.setRight(node.getRight());
        }

        return successor;
    }


    // обход дерева.

    private void inOrder(Node node) {
        if (node != null) {
            // node.display(); - Прямой обход дерева
            inOrder(node.getLeft());
            node.display(); //- Симметричный обход дерева
            inOrder(node.getRight());
            // node.display(); - Обратный обход дерева
        }
    }

    private void display() {
        inOrder(root);
    }

}
