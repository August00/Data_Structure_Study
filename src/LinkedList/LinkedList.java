package LinkedList;

public class LinkedList<E extends Comparable<E>> {

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
    private Node dummyhead;
    private int size;

    public LinkedList(){
        dummyhead = new Node();
        size = 0;
    }

    public void add(int index,E element){
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException("index error");
        Node pre = dummyhead;
        for(int i=0; i<index;i++) {
            pre = pre.next;
        }
        Node node = new Node(element,null);
        node.next = pre.next;
        pre.next = node;
        size ++;
    }

    //用递归算法实现插入元素从小到大排列
    public void addRecursive(E element){
        dummyhead = addNode(dummyhead,element);
    }

    //返回以node为头结点的链表
    public Node addNode(Node node,E element){
        if(node.next == null){
            node.next = new Node(element,null);
            size++;
            return node;
        }
        if(element.compareTo(node.next.data)>0)
           node.next = addNode(node.next,element);
        else{
           Node newnode = new Node(element,null);
           newnode.next = node.next;
           node.next = newnode;
           size ++;
        }
        return node;
    }


    public void addFirst(E element){
        add(0,element);
    }

    public void addLast(E element){
        add(size,element);
    }

    public E get(int index){
        if(index<0 || index>size-1)
            throw new IndexOutOfBoundsException("Index error");
        Node cur = dummyhead.next;
        for(int i = 0;i<index;i++)
            cur = cur.next;
        return cur.data;
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size-1);
    }

    public int getSize(){
        return size;
    }

    public void set(int index,E element){
        if(index<0 || index>size-1)
            throw new IndexOutOfBoundsException("Index error");
        Node cur = dummyhead.next;
        for(int i = 0;i<index;i++)
            cur = cur.next;
        cur.data = element;
    }

    public boolean contains(E element){
        Node cur = dummyhead.next;
        for(int i = 0;i<size;i++) {
            if (element.equals(cur.data))
                return true;
            cur = cur.next;
        }
        return false;
    }

    public E remove(int index){
        if(index<0 || index>size-1)
            throw new IndexOutOfBoundsException("Index error");
        if(isEmpty()) {
            System.out.println("LinkedList is empty");
            return null;
        }
        Node pre = dummyhead;
        for(int i=0;i<index;i++){
            pre = pre.next;
        }
        Node delNode = pre.next;
        pre.next = delNode.next;
        delNode.next = null;
        size --;
        return delNode.data;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }

    public boolean isEmpty(){
        return dummyhead.next == null;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("LinkedList: ");
        if(isEmpty()) {
            System.out.println("LinkedList is Empty");
            return null;
        }
        Node cur;
        for(cur = dummyhead.next ;cur != null;cur = cur.next){
            str.append(cur.data);
            str.append("->");
        }
        str.append("Null");
        return str.toString();
    }
}
