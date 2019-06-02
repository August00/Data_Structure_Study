package Queue;

import Tree.Heap.MinHeap;

public class PriorityQueue<E extends Comparable<E>> implements Queue<E>{
    private MinHeap minHeap;
    public PriorityQueue(){
        this.minHeap = new MinHeap();
    }


    @Override
    public void enqueue(E element) {
        minHeap.add(element);
    }

    @Override
    public E dequeue() {
        return (E) minHeap.extractMin();
    }

    @Override
    public E getFront() {
        return (E) minHeap.findMin();
    }

    @Override
    public E getTail() {
        return null;
    }


    @Override
    public boolean isEmpty() {
        return minHeap.isEmpty();
    }

    @Override
    public int getSize() {
        return minHeap.size();
    }
}
