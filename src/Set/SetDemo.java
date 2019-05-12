package Set;

public class SetDemo {
    public static void main(String[] args){
        BinarySearchTreeSet<Character> bstSet = new BinarySearchTreeSet<Character>();
        String str = "nihaoyaxiaopengyou00000";
        
        for(Character c : str.toCharArray()){
            bstSet.add(c);
        }
        System.out.println(bstSet.getSize());

        LinkedListSet<Character> llSet = new LinkedListSet<Character>();
        for(Character c : str.toCharArray()){
            llSet.add(c);
        }
        System.out.println(llSet.getSize());
    }
}
