import java.util.stream.IntStream;

class NaturalNumber {
  private Classification classification;

  public NaturalNumber(int number) {
    if (number < 1) {
      throw new IllegalArgumentException("You must supply a natural number (positive integer)");
    }
    classification = findClassification(number);
  }

  public Classification getClassification() {
    return classification;
  }

  private Classification findClassification(int n) {
    int sum = IntStream.rangeClosed(1, n / 2)
      .filter(i -> n % i == 0)
      .sum();
    return Classification.getClassification(Integer.compare(sum, n));
  }
}
