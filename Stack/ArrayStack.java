package Stack;

import Arrays.Array;

public class ArrayStack<E> implements Stack<E>{

    private Array<E> data = new Array<E>();
    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public E getPeek() {
        return data.getLast();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public void push(E element) {
        data.addLast(element);
    }

    @Override
    public E pop() {
        if(data.isEmpty())
            throw new IllegalArgumentException("nothing in stack");
        return data.removeLast();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("{size = " + data.getSize() + " capacity = "+ data.getCapacity() +"}");
        res.append("[");
        for(int i = 0;i < data.getSize();i++) {
            res.append(data.get(i));
            if(i != data.getSize()-1)
                res.append(",");
        }
        res.append("] top");
        return res.toString();
    }
}
