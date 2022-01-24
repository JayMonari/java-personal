import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class PrimeCalculator {
  private final int MAX_SIZE = 200_000;
  private final List<Integer> primes;

  public PrimeCalculator() {
    primes = collectPrimesToMaxSize();
  }

  int nth(int number) {
    if (number < 1) {
      throw new IllegalArgumentException("Must be positive integer.");
    }
    return primes.get(number - 1);
  }

  private List<Integer> collectPrimesToMaxSize() {
    List<Boolean> arePrimes = findPrimesToMaxSize();
    return IntStream.iterate(2, n -> n < arePrimes.size(), n -> n + 1)
      .filter(i -> arePrimes.get(i))
      .boxed()
      .collect(Collectors.toList());
  }

  private List<Boolean> findPrimesToMaxSize() {
    List<Boolean> arePrimes = IntStream.rangeClosed(0, MAX_SIZE)
      .mapToObj(i -> true)
      .collect(Collectors.toList());
    for (int prime = 2; prime * prime < MAX_SIZE; prime++) {
      if (arePrimes.get(prime) == true) {
        for (int factor = prime * prime; factor < MAX_SIZE; factor += prime) {
          arePrimes.set(factor, false);
        }
      }
    }
    return arePrimes;
  }
}
