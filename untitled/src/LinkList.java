public class LinkList {

    private Node first;
    private Node last;

    public void insertFirst(int id, double element) {
        Node node = new Node(element, id);

        if (isEmpty()) {
            last = node;
        } else {
            first.prev = node;
            node.next = first;
        }
        first = node;
    }

    public void insertLast(int id, double element) {
        Node node = new Node(element, id);

        if (isEmpty()) {
            first = node;
        } else {
            node.prev = last;
            last.next = node;
        }
        last = node;
    }

    public Node deleteFirst() {
        Node node = first;

        if (first.next != null){
            first.next.prev = null;
        }
        first = first.next;
        return node;
    }

    public Node deleteLast() {
        Node node = last;

        if (last.prev != null) {
            last.prev.next = null;
        }
        last = last.prev;
        return node;
    }


    public boolean insertAfter(int id, double data, int afterId) {
        Node node = new Node(data, id);

        if (isEmpty()) {
            return false;
        }

        Node current = first;

        while (current != null && current.id != afterId) {
            current = current.next;
        }

        if (current == null) {
            return false;
        }

        if (current == last) {
            last = node;
        } else {
            node.next = current.next;
            current.next.prev = node;
        }

        current.next = node;
        node.prev = current;

        return true;
    }

    public Node deleteByKey(int id) {

        if (isEmpty()) {
            return null;
        }

        Node current = first;
        while (current.id != id) {
            if (current == null) {
                return null;
            }

            current = current.next;
        }

        if (current == first) {
            current.next.prev = null;
            first = current.next;

        } else if (current == last) {
            current.prev.next = null;
            last = current.prev;
        } else {
            current.next.prev = current.prev;
            current.prev.next = current.next;
        }
        current.next = null;
        current.prev = null;

        return current;
    }

    public Node find(int id) {
        Node current = first;

        while (current != null) {
            if (current.id == id) {
                return current;
            }
            current = current.next;
        }

        return null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void displayForward() {
        Node current = first;
        while (current != null) {
            current.display();
            current = current.next;
        }
    }

    public void displayBackward() {
        Node current = last;
        while (current != null) {
            current.display();
            current = current.prev;
        }
    }

    public static class Node {

        public int id;
        public double data;
        public Node next;
        public Node prev;

        public Node(double data, int id) {
            this.data = data;
            this.id = id;
        }

        public void display() {
            System.out.println("id: " + id + ", data: " + data);
        }
    }

    public Node getFirst() {
        return first;
    }

    public void setFirst(Node first) {
        this.first = first;
    }

    public Node getLast() {
        return last;
    }

    public void setLast(Node last) {
        this.last = last;
    }

    public LinkListIterator getIterator() {
        return new LinkListIterator(this);
    }
}
