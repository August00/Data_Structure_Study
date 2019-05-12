package Set;

import Tree.BinarySearchTree;

public class BinarySearchTreeSet<E extends Comparable<E>> implements Set<E>{
    private BinarySearchTree<E> bst;

    public BinarySearchTreeSet(){
        bst = new BinarySearchTree();
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public String toString() {
        return bst.toString();
    }
}
