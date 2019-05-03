package Queue;

import Arrays.Array;

public class ArrayQueue<E> implements Queue<E>{
    private Array<E> data;

    public ArrayQueue(){
        data = new Array<>();
    }

    public ArrayQueue(int capacity){
        data = new Array<>(capacity);
    }
    @Override
    public void enqueue(E element) {
        data.addLast(element);
    }

    @Override
    public E dequeue() {
        return data.removeFirst();
    }

    @Override
    public E getFront() {
        return data.getFirst();
    }

    @Override
    public E getTail() {
        return data.getLast();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("{size = " + data.getSize() + " capacity = "+ data.getCapacity() +"}");
        res.append("front[");
        for(int i = 0; i < data.getSize(); i++){
            res.append(data.get(i));
            if(i != getSize()-1)
                res.append(",");
        }
        res.append("]tail");
        return res.toString();
    }
}
