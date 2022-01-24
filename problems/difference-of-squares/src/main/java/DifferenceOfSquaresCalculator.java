import java.util.stream.IntStream;

class DifferenceOfSquaresCalculator {

  int computeSquareOfSumTo(int input) {
    int sum = IntStream.range(0, input + 1).sum();
    return sum * sum;
  }

  int computeSumOfSquaresTo(int input) {
    return IntStream.range(0, input + 1)
      .map(n -> n * n)
      .sum();
  }

  int computeDifferenceOfSquares(int input) {
    return computeSquareOfSumTo(input) - computeSumOfSquaresTo(input);
  }
}
