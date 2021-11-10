import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimeFactorsCalculator {

  public List<Long> calculatePrimeFactorsOf(long num) {
    if (num < 2) {
      return Collections.emptyList();
    }

    List<Long> primeFactors = new ArrayList<>();
    for (long fac = 2L; num > 1; fac++) {
      while (num % fac == 0) {
        primeFactors.add(fac);
        num /= fac;
      }
    }
    return primeFactors;
  }
}
