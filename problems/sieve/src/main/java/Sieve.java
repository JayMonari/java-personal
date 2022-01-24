import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Sieve {
  private List<Integer> primes;

  Sieve(int maxPrime) {
    primes = initializePrimes(maxPrime);
  }

  public List<Integer> getPrimes() {
    return primes;
  }

  private List<Integer> initializePrimes(int limit) {
    if (limit < 1) {
      return Collections.emptyList();
    }

    List<Boolean> isPrimeFactor = new ArrayList<>(limit);
    for (int i = 0; i <= limit; i++)
      isPrimeFactor.add(false);
    List<Integer> primes = new ArrayList<>(limit);
    for (int i = 2; i <= limit; i++) {
      if (isPrimeFactor.get(i)) {
        continue;
      }
      primes.add(i);
      for (int factor = i; factor <= limit; factor += i) {
        isPrimeFactor.set(factor, true);
      }
    }
    return primes;
  }

}
