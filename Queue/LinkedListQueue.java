package Queue;


public class LinkedListQueue<E> implements Queue<E>{
    private class Node{
        public E data;
        public Node next;

        public Node(E data,Node next){
            this.data = data;
            this.next = next;
        }

        public Node(E data){
            this(data,null);
        }

        public Node(){
            this(null);
        }

        @Override
        public String toString(){
            return data.toString();
        }
    }

    private Node head,tail;
    private int size;

    public LinkedListQueue(){
        head = tail = null;
        size = 0;
    }

    @Override
    public void enqueue(E element) {
        if(head==null) {
            tail = new Node(element);
            head = tail;
        }else{
            tail.next = new Node(element);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if(isEmpty())
            throw new IllegalArgumentException("queue is Empty.");
        Node delNode = head;
        head = delNode.next;
        delNode.next = null;
        if(head == null)
            tail = null;
        size--;
        return delNode.data;
    }

    @Override
    public E getFront() {
        if(isEmpty())
            throw new IllegalArgumentException("queue is Empty.");
        return head.data;
    }

    @Override
    public E getTail() {
        if(isEmpty())
            throw new IllegalArgumentException("queue is Empty.");
        return tail.data;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString(){
        StringBuilder res =new StringBuilder();
        res.append("Queue:front ");
        Node cur = head;
        while (cur!= null){
            res.append(cur.data+"->");
            cur = cur.next;
        }
        res.append("Null tail");
        return res.toString();
    }
}
