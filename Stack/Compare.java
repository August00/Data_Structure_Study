package Stack;

public class Compare {
    public static void main(String[] args){
        System.out.println(runFun(new ArrayStack<Integer>(),10000));
        System.out.println(runFun(new LinkedListStack<Integer>(),10000));
    }

    public static double runFun(Stack<Integer> s,int opCount){
        long time1 = System.nanoTime();
        for(int i =0 ; i< opCount; i++)
            s.push(i);
        for(int i =0; i < opCount; i++)
            s.pop();
        long time2 = System.nanoTime();
        return (time2-time1)/1000000000.0;
    }
}
