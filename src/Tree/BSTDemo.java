package Tree;



public class BSTDemo {
    public static void main(String[] args){
        BinarySearchTree<Integer> bt = new BinarySearchTree<>();
        int[] arr ={7,5,6,2,88,1,3,9,8,10,99};

        for(int num : arr){
            bt.add(num);
        }
//        System.out.println(bt);
//        bt.inOrder();
//        bt.inOrderNR();
//        bt.postOrder();
//        System.out.println("\n");
//        bt.postOrderNR();
//        bt.levelOrder();
//        bt.remove(88);
//        System.out.println(bt);

        //以下代码是检验removeMax/removeMin是否ok
//        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
//        Random random = new Random();
//        int n = 1000;
//        for(int i=0;i<n;i++){
//            bst.add(random.nextInt(10000));
//        }
//        ArrayList<Integer> nums = new ArrayList();
//        while(!bst.isEmpty())
//            nums.add(bst.removeMin());
//        System.out.println(nums);
//        for(int i = 1;i< nums.size();i++)
//            if(nums.get(i-1) > nums.get(i))
//                throw new IllegalArgumentException("Error");
//        System.out.println("removeMin Over");

        //测试平衡二叉树
        int[] arr2 ={7,5,6};
        AVLTree<Integer> at = new AVLTree<>();
        for(int num : arr){
            at.add(num);
        }
        System.out.println("isBST :"+at.isBST());
        System.out.println("isBalance :"+at.isBalanced());
        System.out.println(at);

        for(int n:arr){
            at.remove(n);
            if(!at.isBST() || !at.isBalanced()){
                throw new RuntimeException("error");
            }
        }
    }
}
