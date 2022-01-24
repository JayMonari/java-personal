import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Series {
  private String digits;

  public Series(String digits) {
    this.digits = digits;
  }

  public List<String> slices(int sliceLength) {
    if (sliceLength < 1) {
      throw new IllegalArgumentException("Slice size is too small.");
    } else if (digits.length() < sliceLength) {
      throw new IllegalArgumentException("Slice size is too big.");
    }

    return IntStream.rangeClosed(0, digits.length() - sliceLength)
      .mapToObj(i -> digits.substring(i, i + sliceLength))
      .collect(Collectors.toList());
  }
}
