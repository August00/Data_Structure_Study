package Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree<E extends Comparable<E>> {
    private class Node{
        public E data;
        public Node left,right;

        public Node(E data){
            this.data = data;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BinarySearchTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(E element){
        root = add(root,element);
    }

    //返回插入新节点元素之后的树的根节点
    private Node add(Node node,E element){
        if(node == null){
            size ++;
            return new Node(element);
        }else if(element.compareTo(node.data)<0)
            node.left = add(node.left,element);
        else if(element.compareTo(node.data)>0)
            node.right = add(node.right,element);
        return node;
    }

    public boolean contains(E element){
        return contains(root,element);
    }

    private boolean contains(Node node,E element){
        if(node == null)
            return false;
        else if(element.compareTo(node.data) == 0)
            return true;
        else if(element.compareTo(node.data)>0)
            return contains(node.right,element);
        else
            return contains(node.left,element);
    }

    //前序遍历(递归)
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){
        if(node == null)
            return;
        System.out.println(node.data);
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
        System.out.println(node.data);
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
        System.out.println(node.data);
    }

    //前序遍历(非-递归)
    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.data);

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
                System.out.println(cur.data);
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
                System.out.print(stack1.pop().data + "   ");
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
            System.out.println(cur.data);
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
    public E removeMin(){
        E ret = getMin(root).data;
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
    public E removeMax(){
        E ret = getMax(root).data;
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
    public void remove(E e){
        if(isEmpty())
            throw new IndexOutOfBoundsException("BianrySearchTree is empty");
        if(!contains(e))
            throw new IllegalArgumentException("this element " + e + " is not in BST.");
        root = remove(root,e);
    }

    //返回删除之后的新的二分搜索树的根
    private Node remove(Node node,E e){
        if(node == null)
            return null;
        if(e.compareTo(node.data) > 0){
            node.right = remove(node.right,e);
            return node;
        }else if(e.compareTo(node.data) < 0) {
            node.left = remove(node.left,e);
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
        res.append(generateBTSDepth(depth)+node.data+"\n");
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
