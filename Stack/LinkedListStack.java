package Stack;
import LinkedList.LinkedList;

public class LinkedListStack<E extends Comparable<E>> implements Stack<E>{
    private LinkedList<E> lst = new LinkedList<E>();
    @Override
    public boolean isEmpty() {
        return lst.isEmpty();
    }

    @Override
    public E getPeek() {
        return lst.getFirst();
    }

    @Override
    public int getSize() {
        return lst.getSize();
    }

    @Override
    public void push(E element) {
        lst.addFirst(element);
    }

    @Override
    public E pop() {
        return lst.removeFirst();
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Stack:TOP[");
        str.append(lst+"]");
        return str.toString();
    }

}
