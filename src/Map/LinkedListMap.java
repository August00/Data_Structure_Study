package Map;



public class LinkedListMap<K,V> implements Map<K,V> {

    private class Node{
        public K key;
        public V value;
        public Node next;

        public Node(K key,V value,Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key,V value){
            this(key,value,null);
        }

        public Node(){
            this(null,null);
        }

        @Override
        public String toString(){
            return key.toString() + ":" + value.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedListMap(){
        dummyHead = new Node();
        size = 0;
    }

    private Node getNode(K key){
        Node node = dummyHead.next;
        while(node != null){
            if(node.key.equals(key))
                return node;
            node = node.next;
        }
        return null;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if(node == null) {
            node = new Node(key, value, null);
            node.next = dummyHead.next;
            dummyHead.next = node;
            size ++;
        } else {
            System.out.println("key: "+ key +"已存在于map中，如要更换其对应的value,请调用set方法");
        }
    }

    @Override
    public V remove(K key) {
        Node pre = dummyHead;
        while(pre.next != null){
            if(pre.next.key.equals(key)){
                break;
            }
            pre = pre.next;
        }
        if(pre.next != null){
            Node del = pre.next;
            pre.next = del.next;
            del.next = null;
            size --;
            return del.value;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        Node node = getNode(key);
        if(node == null){
            return false;
        }
        return true;
    }

    @Override
    public V get(K key) {
        return getNode(key).value;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(key);
        if(node == null){
            throw  new IllegalArgumentException(key+" doesn't exist.");
        }
        node.value = value;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("LinkedListMap: ");
        if(isEmpty()) {
            System.out.println("LinkedListMap is Empty");
            return null;
        }
        Node cur;
        for(cur = dummyHead.next ;cur != null;cur = cur.next){
            str.append("("+cur.key+","+cur.value+")");
            str.append("->");
        }
        str.append("Null");
        return str.toString();
    }
}
