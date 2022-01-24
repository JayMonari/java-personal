import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BaseConverter {
  private final int baseValue;

  public BaseConverter(int base, int[] nums) {
    List<Integer> digits = Arrays.stream(nums).boxed().collect(Collectors.toList());
    if (base < 2) {
      throw new IllegalArgumentException("Bases must be at least 2.");
    } else if (digits.stream().anyMatch(d -> d >= base)) {
      throw new IllegalArgumentException("All digits must be strictly less than the base.");
    } else if (digits.stream().anyMatch(d -> d < 0)) {
      throw new IllegalArgumentException("Digits may not be negative.");
    }
    baseValue = digits.stream().reduce(0, (acc, d) -> acc * base + d);
  }

  public int[] convertToBase(int base) {
    if (baseValue == 0) {
      return new int[] { 0 };
    } else if (base < 2) {
      throw new IllegalArgumentException("Bases must be at least 2.");
    }

    int seed = (int) Math.pow(base, (int) (Math.log(baseValue) / Math.log(base)));
    return IntStream.iterate(seed, n -> n > 0, n -> n / base)
      .map(n -> (baseValue / n) % base)
      .toArray();
  }
}
