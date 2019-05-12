package Set;

public interface Set<E> {
    int getSize();
    void add(E e);
    void remove(E e);
    boolean contains(E e);
    boolean isEmpty();
}
