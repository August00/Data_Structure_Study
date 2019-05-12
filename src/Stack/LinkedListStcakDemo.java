package Stack;

public class LinkedListStcakDemo {
    public static void main(String[] args){
        LinkedListStack<Integer> ls = new LinkedListStack<Integer>();
        for(int i =0;i<6;i++){
            ls.push(i);
            System.out.println(ls);
        }
    }
}
