import java.util.stream.IntStream;

class LargestSeriesProductCalculator {
  private String digits;

  LargestSeriesProductCalculator(String inputNumber) {
    if (!inputNumber.matches("[0-9]+") && inputNumber.length() > 0) {
      throw new IllegalArgumentException("String to search may only contain digits.");
    }
    digits = inputNumber;
  }

  long calculateLargestProductForSeriesLength(int length) {
    if (length < 0) {
      throw new IllegalArgumentException("Series length must be non-negative.");
    } else if (length > digits.length()) {
      throw new IllegalArgumentException(
          "Series length must be less than or equal to the length of the string to search.");
    }

    return IntStream.iterate(length, l -> l <= digits.length(), l -> l + 1)
      .mapToLong(i -> digits.substring(i - length, i)
                  .chars()
                  .mapToObj(l -> (long)Character.getNumericValue(l))
                  .reduce(1L, (acc, num) -> acc * num))
      .max()
      .orElseGet(() -> 0);
  }
}
