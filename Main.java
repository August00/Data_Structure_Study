import Arrays.Array;
import Queue.ArrayQueue;
import Queue.LoopQueue;
import Stack.ArrayStack;

public class Main {
    public static void main(String[] args){
        Array<Integer> arrInt = new Array<Integer>();
        for(int i = 0; i < 10; i++){
            arrInt.add(i,i);
        }
        System.out.println("----------------Arrays.Array start--------------------");
        System.out.println(arrInt);
        arrInt.add(1,100);

        System.out.println(arrInt);
        for(int i = 0;i < 5; i++)
            arrInt.removeLast();
        System.out.println(arrInt);
        arrInt.removeLast();
        System.out.println(arrInt);
        System.out.println("----------------Arrays.Array end--------------------");

        System.out.println("----------------Stack.Stack start------------------");
        ArrayStack<Character> c = new ArrayStack<Character>();
        for(int i = 97; i< 107; i++)
            c.push((char)i);
        System.out.println(c);
        c.push('w');
        System.out.println(c);
        for(int i = 0; i < 6; i++) {
            c.pop();
        }
        System.out.println(c);
        System.out.println("----------------Stack.Stack end--------------------");

        System.out.println("----------------ArrayQueue start------------------");
        ArrayQueue<Character> cq = new ArrayQueue<Character>();
        for(int i = 97; i< 107; i++)
            cq.enqueue((char)i);
            System.out.println(cq);
            cq.enqueue('w');
            System.out.println(cq);
            for(int i = 0; i < 6; i++) {
                cq.dequeue();
            }
            System.out.println(cq);
        System.out.println("----------------ArrayQueue end--------------------");

        System.out.println("----------------LoopQueue start------------------");
        LoopQueue<Character> lq = new LoopQueue<Character>();
        for(int i = 65; i< 75; i++)
            lq.enqueue((char)i);
        System.out.println(lq);
        lq.enqueue('w');
        System.out.println(lq);
        for(int i = 0; i < 6; i++) {
            lq.dequeue();
        }
        System.out.println(lq);
        System.out.println("----------------LoopQueue end--------------------");
    }
}
