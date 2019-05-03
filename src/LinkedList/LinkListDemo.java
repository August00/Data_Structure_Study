package LinkedList;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class LinkListDemo {
    public static void main(String[] args){
        LinkedList<Integer> ll = new LinkedList<Integer>();
        for(int i = 6;i>0;i--){
            ll.addRecursive(i);
        }
        ll.addRecursive(3);
        System.out.println(ll);
        System.out.println(ll.contains(3));

        for(int i = 0;i<5;i++){
            ll.removeFirst();
        }
        System.out.println(ll);
    }
}
