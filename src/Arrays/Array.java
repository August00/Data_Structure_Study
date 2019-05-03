package Arrays;

import java.lang.*;

public class Array<E> {
    private int size;
    private E[] arr;

    public Array(){
        this(10);
    }

    public Array(int capacity){
        arr = (E[]) new Object[capacity];
        size = 0;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void add(int index, E element){
        if(index > size || index <0)
            throw new IndexOutOfBoundsException("add index error:" + index);
        if(size == arr.length)
            resize(2*arr.length);
        for(int i = size - 1;i >= index; i--)
            arr[i+1] = arr[i];
        arr[index] = element;
        size ++;
    }

    public void addFirst(E element){
        add(0,element);
    }

    public void addLast(E element){
        add(size,element);
    }

    public E remove(int index){
        if(index < 0 || index > size -1)
            throw new IndexOutOfBoundsException("remove index error");
        E e = arr[index];
        for(int i = index;i < size-1;i++)
            arr[i] = arr[i+1];
        size --;
        arr[size] = null;
        if((size == arr.length/4) && (arr.length/2 !=0))
            resize(arr.length/2);
        return e;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }

    public void set(int index, E element){
        arr[index] = element;
    }

    public E get(int index){
        if(index < 0 || index > size-1)
            throw new IllegalArgumentException("index is illegal.");
        return arr[index];
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size-1);
    }

    public int getSize(){
        return size;
    }
    public int getCapacity(){
        return arr.length;
    }


    public void resize(int newcapacity){
        E[] data = (E[]) new Object[newcapacity];
        for(int i = 0; i < size; i++){
            data[i] = arr[i];
        }
        arr = data;
    }


    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("{size = " + size + " capacity = "+ arr.length +"}");
        res.append("[");
        for(int i = 0; i < size; i++){
            res.append(arr[i]);
            if(i != size - 1)
                res.append(",");
        }
        res.append("]");
        return res.toString();
    }
}
