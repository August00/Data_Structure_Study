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
        ll.set(9,109);

    }
}
