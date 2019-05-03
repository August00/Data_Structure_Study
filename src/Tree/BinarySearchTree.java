package Tree;

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
