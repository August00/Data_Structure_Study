package Queue;

public class LoopQueue<E> implements Queue<E>{
    private E[] data;
    private int front,tail;
    private int size;
    public LoopQueue(){
        this(10);
    }
    public LoopQueue(int capacity){
        data = (E[])new Object[capacity+1];
        front = 0;
        tail = 0;
        size = 0;
    }
    @Override
    public void enqueue(E element) {
        if((tail+1)%data.length==front)
            resize(getCapacity()*2);
        data[tail] = element;
        tail = (tail+1)%data.length;
        size ++;
    }

    @Override
    public E dequeue() {
        if(isEmpty())
            throw new IndexOutOfBoundsException("LoopQueue is empty.");
        E e = data[front];
        data[front] = null;
        front = (front+1)%data.length;
        size --;
        if(size == getCapacity()/4 && getCapacity()/2 != 0)
            resize(getCapacity()/2);
        return e;
    }

    @Override
    public E getFront() {
        if(isEmpty())
            throw new IllegalArgumentException("LoopQueue is Empty.");
        return data[front];
    }

    @Override
    public E getTail() {
        return data[tail-1];
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public int getSize() {
        return size;
    }

    public int getCapacity(){
        return data.length-1;
    }

    public void resize(int newcapacity){
        E[] arr = (E[]) new Object[newcapacity+1];
        for(int i = 0; i < size; i++){
            arr[i] = data[(front+i)% data.length];
        }
        data = arr;
        front = 0;
        tail = size;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("{size = " + size + " capacity = "+ getCapacity() +"}");
        res.append("loopfront[");
        for(int i = 0; i < size; i++){
            res.append(data[i]);
            if(i != getSize()-1)
                res.append(",");
        }
        res.append("]looptail");
        return res.toString();
    }

}
