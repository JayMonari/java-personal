import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class SimpleLinkedList<T> {
  private List<T> list;

  public SimpleLinkedList() {
    list = new ArrayList<>();
  }

  public SimpleLinkedList(T[] values) {
    list = new ArrayList<>(List.of(values));
  }

  public int size() {
    return list.size();
  }

  public void push(T value) {
    list.add(value);
  }

  public T pop() {
    if (list.isEmpty()) {
      throw new NoSuchElementException();
    }
    return list.remove(list.size() - 1);
  }

  public void reverse() {
    Collections.reverse(list);
  }

  public T[] asArray(Class<?> clazz) {
    List<T> revList = new ArrayList<>(list);
    Collections.reverse(revList);
    return (T[]) revList.toArray();
  }
}
