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
    }
}
