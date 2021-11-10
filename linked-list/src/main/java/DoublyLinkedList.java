import java.util.ArrayDeque;
import java.util.Deque;

public class DoublyLinkedList<T> {
  private Deque<T> doublyLinkedList = new ArrayDeque<>();

  public void push(T element) {
    doublyLinkedList.addLast(element);
  }

  public T pop() {
    return doublyLinkedList.removeLast();
  }

  public void unshift(T element) {
    doublyLinkedList.addFirst(element);
  }

  public T shift() {
    return doublyLinkedList.removeFirst();
  }
}
