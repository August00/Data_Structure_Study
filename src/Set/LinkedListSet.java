package Set;

import LinkedList.LinkedList;

public class LinkedListSet<E extends Comparable<E>> implements Set<E>{
    private LinkedList<E> lSet;

    public LinkedListSet(){
        lSet = new LinkedList<E>();
    }
    @Override
    public int getSize() {
        return lSet.getSize();
    }

    @Override
    public void add(E e) {
        if(!lSet.contains(e))
            lSet.addFirst(e);
    }

    @Override
    public void remove(E e) {
        lSet.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return lSet.contains(e);
    }

    @Override
    public boolean isEmpty() {
        return lSet.isEmpty();
    }

}
