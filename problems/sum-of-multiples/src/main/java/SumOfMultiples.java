import java.util.Arrays;
import java.util.stream.IntStream;

class SumOfMultiples {
  private int sum;

  SumOfMultiples(int limit, int[] set) {
    sum = Arrays.stream(set)
      .filter(i -> i != 0)
      .flatMap(i -> IntStream.iterate(i, n -> n < limit, n -> n + i))
      .distinct()
      .sum();
  }

  int getSum() {
    return sum;
  }
}
