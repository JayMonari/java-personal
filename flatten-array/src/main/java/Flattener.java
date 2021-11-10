import java.util.ArrayList;
import java.util.List;

public class Flattener {

  public List<Object> flatten(List<Object> objects) {
    List<Object> flattened = new ArrayList<>();
    objects.forEach(o -> {
      if (o == null) return;

      if (o instanceof List<?>) {
        flattened.addAll(flatten((List<Object>) o));
      } else {
        flattened.add(o);
      }
    });
    return flattened;
  }
}
