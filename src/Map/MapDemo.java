package Map;

public class MapDemo {
    public static void main(String[] args) {
        LinkedListMap<Integer, Integer> ll = new LinkedListMap();
        for (int i = 6; i > 0 ; i--) {
            ll.add(i,i+100);
        }

        System.out.println(ll);
        ll.set(4,4);
        ll.add(4,99);
        for (int i = 0; i < 4; i++) {
            ll.remove(i);
            System.out.println(ll);
        }
//        ll.set(9,109);

        BSTMap<Integer, Integer> bt = new BSTMap();
        int[] arr ={6,4,7,2,5,88,1,3,9,8,10,99};
        for(int num : arr){
            bt.add(num,num);
        }
        System.out.println(bt);
        System.out.println("\n");
        bt.remove(88);
        System.out.println(bt);

    }
}
