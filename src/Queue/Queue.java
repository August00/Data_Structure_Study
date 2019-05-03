package Queue;

public interface Queue<E> {
    void enqueue(E element);
    E dequeue();
    E getFront();
    E getTail();
    boolean isEmpty();
    int getSize();
}
