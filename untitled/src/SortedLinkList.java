public class SortedLinkList {

    private Node first;

    public static class Node {

        public int id;
        public double data;
        public Node next;

        public Node(double data, int id) {
            this.data = data;
            this.id = id;
        }

        public void display() {
            System.out.println("id: " + id + ", data: " + data);
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void displayList() {
        Node current = first;
        while (current != null) {
            current.display();
            current = current.next;
        }
    }

    public void insert(int id, double data) {

        Node node = new Node(data, id);

        Node prev = null;
        Node current = first;

        while (current != null && current.id < id) {
            prev = current;
            current = current.next;
        }

        if (prev == null) {
            first = node;
        } else {
            prev.next = node;
        }

        node.next = current;
    }

    public Node remove(int id) {
        Node node;

        if (first.id == id) {
            node = first;
            first = first.next;
            return node;
        }

        Node prev = first;
        Node current = first.next;

        while (current != null) {
            if (current.id == id) {
                prev.next = current.next;
                return current;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }
}
