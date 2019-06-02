package Tree.Heap;

import java.util.Random;

public class HeapDemo {
    public static void main(String[] args){
        MinHeap<Integer> minHeap = new MinHeap<>();
        Random random = new Random();
        for(int i = 0; i< 100;i++){
            minHeap.add(random.nextInt(Integer.MAX_VALUE));
        }

        int[] arr = new int[100];
        for(int i=0;i<100;i++){
            arr[i] = minHeap.extractMin();
//            System.out.println(arr[i]);
        }
        for(int i =1;i<100;i++){
            if(arr[i-1]>arr[i]){
                throw new IllegalArgumentException("sequence error");
            }
        }

        System.out.println("Test MinHeap completed.");

        Integer[] testData = new Integer[1000000];
        for(int i=0;i<1000000;i++){
            testData[i] = random.nextInt(Integer.MAX_VALUE);
        }
        double time1 = testHeap(testData,true);
        double time2 = testHeap(testData,false);

        System.out.println("isHeapify:"+time1);
        System.out.println("notHeapify:"+time2);

    }
    private static double testHeap(Integer[] testData,boolean isHeapify){
        long startTime = System.nanoTime();
        MinHeap<Integer> minHeap;
        if(isHeapify){
            minHeap = new MinHeap<>(testData);
        }else {
            minHeap = new MinHeap<>();
            for(int num:testData){
                minHeap.add(num);
            }
        }
        long endTime = System.nanoTime();

        return (endTime-startTime)/100000000.0;
    }
}
