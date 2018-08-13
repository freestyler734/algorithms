public class LinkListIterator {

    private LinkList list;
    private LinkList.Node current;


    public LinkListIterator(LinkList list) {
        list = list;
        reset();
    }

    public void reset() {
        current = list.getFirst();
    }

    public boolean isEnd() {
        return current.next == null;
    }

    public void next() {
        current = current.next;
    }



    public LinkList.Node getCurrent() {
        return current;
    }

    public void insertAfter(int id, double data) {
        LinkList.Node node = new LinkList.Node(data, id);

        if (list.isEmpty()) {
            list.setFirst(node);
            current = node;
        } else if (isEnd()){
            current.next = node;
            node.prev = current;
            list.setLast(node);
        } else {
            current.next.prev = node;
            node.next = current.next;
            node.prev = current;
            current.next = node;
            //next();
        }
    }

    public void insertBefore(int id, double data) {
        LinkList.Node node = new LinkList.Node(data, id);

        if (list.isEmpty()) {
            list.setFirst(node);
        } else if (list.getFirst().prev == null){
            node.next = list.getFirst();
            list.getFirst().prev = node;
        } else {
            current.prev.next = node;
            node.prev = current.prev;
            node.next = current;
            current.prev = node;
        }
    }

    public void deleteCurrent() {

        if(current.prev == null) // Если в начале списка
        {
            list.setFirst(current.next);
            reset();
        }
        else // Не в начале списка
        {
            current.prev.next = current.next;
            if( isEnd() )
                reset();
            else
                current = current.next;
        }

    }
}
