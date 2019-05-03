package Stack;

public interface Stack<E> {
    boolean isEmpty();
    E getPeek();
    int getSize();
    void push(E element);
    E pop();
}
