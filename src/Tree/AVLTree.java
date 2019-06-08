package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class AVLTree<E extends Comparable<E>> {
    private class Node{
        public E data;
        public Node left,right;
        public int height;

        public Node(E data){
            this.data = data;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    //返回当前节点node的hight
    private int getHeight(Node node){
        if(node == null){
            return 0;
        }
        return node.height;
    }

    //返回当前node的平衡因子
    private int getBalanceFactor(Node node){
        if(node == null){
            return 0;
        }else {
            return getHeight(node.left)-getHeight(node.right);
        }
    }

    public boolean isBST(){
        ArrayList<E> list = new ArrayList<E>();
        inOrderNode(root,list);
        for(int i=1;i<list.size();i++){
            if(list.get(i-1).compareTo(list.get(i))>0)
                return false;
        }
        return true;
    }

    private void inOrderNode(Node node,ArrayList<E> arr){
        if(node == null){
            return;
        }
        inOrderNode(node.left,arr);
        arr.add(node.data);
        inOrderNode(node.right,arr);
    }

    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(Node node){
        if(node == null)
            return true;
        if(Math.abs(getBalanceFactor(node))>1)
            return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }

    /*LL: 在node节点的左子树L的左子树L新插入节点,打破了node的平衡
    *           node
    *           /  \
    *          x    N             x
    *         / \       --->     / \
    *        N   y              N   node
    *       /                  /     / \
    *      NEW                NEW   y   N
    * */

    private Node rightRotate(Node node){
        Node x = node.left;
        Node y = x.right;

        x.right = node;
        node.left = y;

        node.height = 1 + Math.max(getHeight(node.left),getHeight(node.right));
        x.height = 1 + Math.max(getHeight(x.left),getHeight(x.right));

        return x;
    }

    /*RR: 在node节点的右子树R的右子树R新插入节点,打破了node的平衡
     *       node
     *       / \
     *      N   x                  x
     *         / \       --->     / \
     *        y   N            node   N
     *           / \           / \   / \
     *          N  NEW        N   y N  NEW
     * */
    private Node leftRotate(Node node){
        Node x = node.right;
        Node y = x.left;

        x.left = node;
        node.right = y;

        node.height = 1 + Math.max(getHeight(node.left),getHeight(node.right));
        x.height = 1 + Math.max(getHeight(x.left),getHeight(x.right));
        return x;
    }

    /*LR: 在node节点的左子树L的右子树R新插入节点,打破了node的平衡
     *       node                     node
     *       /  \                     /  \
     *      x    N  (先对x进行左旋转)   y    N   (再对node进行右旋转)     y
     *     / \         --->         / \           --->            /    \
     *    N   y                    x  NEW                        x     node
     *       / \                  / \                          /  \    /  \
     *   (NEW)  New              N (NEW)                      N (NEW) NEW  N
     * */

    /*RL: 在node节点的右子树R的左子树L新插入节点,打破了node的平衡
     *       node                      node
     *       /  \                      /  \
     *      N    X   (先对x进行右旋转)   N    Y   (再对node进行左旋转)       Y
     *          / \       --->            / \         --->           /    \
     *         Y   N                   (NEW) X                    node     X
     *        / \                           / \                   /  \    / \
     *    (NEW)  NEW                       NEW  N                N (NEW) NEW N
     * */


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
        //更新该节点的height
        node.height = 1 + Math.max(getHeight(node.left),getHeight(node.right));
        //保持平衡
        if(getBalanceFactor(node)>1 && getBalanceFactor(node.left)>=0){
            //LL
            return rightRotate(node);
        }
        if(getBalanceFactor(node)<-1 && getBalanceFactor(node.right)<=0){
            //RR
            return leftRotate(node);
        }
        if(getBalanceFactor(node)>1 && getBalanceFactor(node.left)<0){
            //LR:先对该node的左子树进行左旋转
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if(getBalanceFactor(node)<-1 && getBalanceFactor(node.right)>0){
            //RL
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
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