package Queue;

public class CompareTime {
    public static void main(String[] args){

        System.out.println("ArrayQueue:"+runFun(new ArrayQueue<Integer>(),100000));
        System.out.println("LoopQueue:"+runFun(new LoopQueue<Integer>(),100000));
    }

    public static double runFun(Queue<Integer> q,int opCount){
        long time1 = System.nanoTime();
        for(int i =0 ; i< opCount; i++)
            q.enqueue(i);
        for(int i =0; i < opCount; i++)
            q.dequeue();
        long time2 = System.nanoTime();
        return (time2-time1)/1000000000.0;
    }
}
