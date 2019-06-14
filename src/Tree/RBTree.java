package Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class RBTree<K extends Comparable<K>,V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node{
        public K key;
        public V value;
        public Node left,right;
        public boolean color;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }


    private Node leftRotate(Node node){
        Node x = node.right;
        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;
        return x;
    }

    private Node rightRotate(Node node){
        Node x = node.left;
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;
        return x;
    }

    private void flipColors(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    private boolean isRed(Node node){
        if(node == null){
            return BLACK;
        }
        return node.color;
    }

    //向红黑树中添加新元素，保持根节点为黑色
    public void add(K key, V value){
        root = add(root,key,value);
        root.color = BLACK;
    }

    //向以node为根的红黑树中插入新节点(key,value)
    //返回插入新节点元素之后的树的根节点
    private Node add(Node node,K key,V value){
        if(node == null){
            size ++;
            return new Node(key,value);
        }

        if(key.compareTo(node.key)<0)
            node.left = add(node.left,key,value);
        else if(key.compareTo(node.key)>0)
            node.right = add(node.right, key, value);
        else
            node.value = value;

        if(isRed(node.right) && !isRed(node.left))
            node = leftRotate(node);
        if(isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);
        if(isRed(node.left) && isRed(node.right))
            flipColors(node);
        return node;
    }

    public boolean contains(K key){
        return contains(root,key);
    }

    private boolean contains(Node node,K key){
        if(node == null)
            return false;
        else if(key.compareTo(node.key) == 0)
            return true;
        else if(key.compareTo(node.key)>0)
            return contains(node.right,key);
        else
            return contains(node.left,key);
    }

    //前序遍历(递归)
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){
        if(node == null)
            return;
        System.out.println(node.key);
        preOrder(node.left);
        preOrder(node.right);
    }

    //中序遍历(递归)
    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){
        if(node == null)
            return;
        inOrder(node.left);
        System.out.println(node.key);
        inOrder(node.right);
    }

    //后序遍历(递归)
    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node){
        if(node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.key);
    }

    //前序遍历(非-递归)
    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.key);

            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }
    }

    //中序遍历(非-递归)
    public void inOrderNR(){
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        while(cur !=null || !stack.isEmpty()){
            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            if(!stack.isEmpty()) {
                cur = stack.pop();
                System.out.println(cur.key);
                cur = cur.right;
            }
        }
    }

    //后序遍历(非-递归)
    public void postOrderNR(){
        Node cur = root;
        Stack<Node> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        int i = 1;
        while(cur != null || !stack1.empty())
        {
            while (cur != null)
            {
                stack1.push(cur);
                stack2.push(0);
                cur = cur.left;
            }

            while(!stack1.empty() && stack2.peek() == i)
            {
                stack2.pop();
                System.out.print(stack1.pop().key + "   ");
            }

            if(!stack1.empty())
            {
                stack2.pop();
                stack2.push(1);
                cur = stack1.peek();
                cur = cur.right;
            }
        }
    }


    //层序遍历
    public void levelOrder(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node cur = queue.remove();
            System.out.println(cur.key);
            if(cur.left != null)
                queue.add(cur.left);
            if(cur.right != null)
                queue.add(cur.right);
        }

    }

    //查找以node为根节点的二分搜索树中的最小值的节点
    public Node getMin(Node node){
        Node cur = node;
        if(node == null)
            return null;
        while(cur.left != null)
            cur = cur.left;
        return cur;
    }

    //查找以node为根节点的二分搜索树中的最大值的节点
    public Node getMax(Node node){
        Node cur = node;
        if(node == null)
            return null;
        while(cur.right != null)
            cur = cur.right;
        return cur;
    }


    //删除最小值
    public V removeMin(){
        V ret = getMin(root).value;
        root = removeMin(root);
        return ret;
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

    //删除最小值
    public V removeMax(){
        V ret = getMax(root).value;
        root = removeMax(root);
        return ret;
    }

    //删除以node为根节点的二分搜索树中的最小节点
    //返回删除节点后的新的二分搜索树的根
    private Node removeMax(Node node){
        if(node.right== null){
            Node cur = node.left;
            node.left = null;
            size--;
            return cur;
        }
        node.right = removeMin(node.right);
        return node;
    }

    //从二分搜索树中删除元素为e的节点
    public void remove(K key){
        if(isEmpty())
            throw new IndexOutOfBoundsException("BianrySearchTree is empty");
        if(!contains(key))
            throw new IllegalArgumentException("this element " + key + " is not in BST.");
        root = remove(root,key);
    }

    //返回删除之后的新的二分搜索树的根
    private Node remove(Node node,K key){
        if(node == null)
            return null;
        if(key.compareTo(node.key) > 0){
            node.right = remove(node.right,key);
            return node;
        }else if(key.compareTo(node.key) < 0) {
            node.left = remove(node.left,key);
            return node;
        }else{
            if(node.right == null) {
                Node newleft = node.left;
                node.left = null;
                size--;
                return newleft;
            }
            if(node.left == null) {
                Node newright = node.right;
                node.right = null;
                size--;
                return newright;
            }
            //用右子树中的最小值代替被删除的节点
            Node successor = getMin(node.right);
            //以下两句赋值的顺序不可错,若先赋值左子树
            //在removeMin中就会判断该节点有左子树了,会造成oom.
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
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
        res.append(generateBTSDepth(depth)+node.key+"("+isRed(node)+")\n");
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

