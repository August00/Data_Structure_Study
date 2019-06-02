package Tree.Heap;

import Arrays.Array;

public class MinHeap <E extends Comparable<E>>{
    private Array<E> data;

    public MinHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MinHeap(){
        data = new Array<>();
    }

    public MinHeap(E[] arr){
        data = new Array<>(arr);
        for(int i = parent(data.getSize()-1);i>0;i--){
            siftDown(i);
        }
    }

    public int size(){
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    private int parent(int index){
        if(index==0){
            throw new IndexOutOfBoundsException("index error in func parent");
        }
        return (index-1)/2;
    }

    private int leftChild(int index){
        return index*2+1;
    }

    private int rightChild(int index){
        return index*2+2;
    }

    public void add(E element) {
        data.addLast(element);
        siftUp(data.getSize()-1);
    }

    private void siftUp(int index){
        while(index>0 && data.get(index).compareTo(data.get(parent(index)))<0){
            data.swap(index,parent(index));
            index = parent(index);
        }
    }

    private E findMin(){
        if(data.getSize() == 0){
            throw new IllegalArgumentException("heap is empty");
        }
        return data.get(0);
    }

    public E extractMin(){

        E min = findMin();
        data.swap(0,data.getSize()-1);
        data.removeLast();
        siftDown(0);
        return min;
    }

    private void siftDown(int index){

        while(leftChild(index)<data.getSize()){
            int m = leftChild(index);
            if(rightChild(index)<data.getSize()
                && data.get(leftChild(index)).compareTo(data.get(rightChild(index)))>0)
                m = rightChild(index);
            if(data.get(m).compareTo(data.get(index))<0){
                data.swap(index,m);
                index = m;
            } else {
                break;
            }
        }
    }

    //get the minimum and replace it to e
    public E replace(E e){
        E min = findMin();
        data.set(0,e);
        siftDown(0);
        return min;
    }

    public void Heapify(){

    }
}
