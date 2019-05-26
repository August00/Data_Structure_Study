package Map;

public class BSTMap<K extends Comparable<K>,V> implements Map<K,V>{

    private class Node{
        public K key;
        public V value;
        public Node left,right;

        public Node(K key,V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }

        public Node(){
            this(null,null);
        }
    }

    private Node root;
    private int size;

    public BSTMap(){
        root = null;
        size = 0;
    }

    //去根节点为node的子树中找到key为key的节点,返回该节点
    private Node getNode(Node node,K key){
        if(node == null){
            return null;
        }
        if(key.compareTo(node.key) == 0)
            return node;
        else if(key.compareTo(node.key) > 0)
            return getNode(node.right,key);
        else
            return getNode(node.left,key);
    }

    @Override
    public void add(K key, V value) {
        root = add (root,key,value);

    }

    //在以node为根的子树中插入新的节点,返回插入了新节点后的子树的根节点
    private Node add(Node node,K key,V value){
        if(node == null){
            size++;
            node = new Node(key,value);
        }

        if(key.compareTo(node.key)>0)
            node.right = add(node.right,key,value);
        else if(key.compareTo(node.key)<0)
            node.left = add(node.left,key,value);
        else
            node.value = value;

        return node;
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root,key);
        if(node != null){
            root = remove(root,key);
            return node.value;
        }
        return null;
    }

    //删除以node为根的二分搜索树中键为key的节点
    //返回删除节点之后的新的子树的根节点
    private Node remove(Node node,K key){
        if(node == null)
            return null;
        if(key.compareTo(node.key)>0) {
            node.right = remove(node.right, key);
            return node;
        }
        else if(key.compareTo(node.key)<0) {
            node.left = remove(node.left, key);
            return node;
        }
        else{//当前node为要删除的节点，就要看他有没有左右子树的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            //左右子树都存在,就要找到这个子树中的前驱或者后继,以后继为例,用右子树中的最小值
            Node successor = minNode(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;

            return successor;
        }
    }

    //返回以node为根节点的子树中的最小节点
    private Node minNode(Node node){
        if(node == null)
            return null;
        while(node.left != null){
            node = node.left;
        }
        return node;
    }

    //删除以node为根节点的二分搜索树中的最小节点
    //返回删除节点后的新的二分搜索树的根
    private Node removeMin(Node node){
        if(node.left == null){
            Node cur = node.right;
            node.right = null;
            size--;
            return cur;
        }
        node.left = removeMin(node.left);
        return node;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root,key) != null;
    }

    @Override
    public V get(K key) {
        return getNode(root,key) != null? getNode(root,key).value: null;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root,key);
        if(node != null)
            node.value = value;
        else
            throw new IllegalArgumentException(key + "doesn't exist!");
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
        StringBuilder res = new StringBuilder();
        generateBTSString(root,0,res);
        return res.toString();
    }

    private void generateBTSString(Node node,int depth,StringBuilder res){
        if(node == null) {
            res.append(generateBTSDepth(depth) + "null\n");
            return;
        }
        res.append(generateBTSDepth(depth)+node.value+"\n");
        generateBTSString(node.left,depth+1,res);
        generateBTSString(node.right,depth+1,res);
    }

    private String generateBTSDepth(int depth){
        StringBuilder res = new StringBuilder();
        for(int i=0;i< depth;i++)
            res.append("--");
        return res.toString();
    }
}
