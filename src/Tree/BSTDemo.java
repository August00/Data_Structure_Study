package Tree;

public class BSTDemo {
    public static void main(String[] args){
        BinarySearchTree<Integer> bt = new BinarySearchTree<>();
        int[] arr ={6,4,7,2,5,88,1,3,9,8,10};
        for(int num : arr){
            bt.add(num);
        }
//        System.out.println(bt);
//        bt.inOrder();
//        bt.inOrderNR();
//        bt.postOrder();
//        System.out.println("\n");
        bt.postOrderNR();
    }
}
