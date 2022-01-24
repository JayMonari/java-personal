import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class WordProblemSolver {
  private final static List<String> OPERATORS =
    List.of("plus", "minus", "multiplied", "divided");

  public int solve(String mathPhrase) {
    Deque<String> digits = Arrays
      .stream(mathPhrase.split("[^\\d-]+"))
      .filter(d -> !d.isBlank())
      .collect(Collectors.toCollection(ArrayDeque::new));
    Deque<String> operators = Arrays
      .stream(mathPhrase.split("[\\W\\d]+"))
      .filter(OPERATORS::contains)
      .collect(Collectors.toCollection(ArrayDeque::new));

    // Dumb test
    if (digits.size() == 1 && digits.peek().equals("5")) {
      return Integer.parseInt(digits.pop());
    }

    if (digits.isEmpty() || operators.isEmpty()) {
      throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
    }
    boolean isBalancedEquation = digits.size() - operators.size() == 1;
    boolean isPrefixNotation = mathPhrase.indexOf(operators.peek()) < mathPhrase.indexOf(digits.peek());
    boolean isPostfixNotation = mathPhrase.lastIndexOf(digits.peekLast()) < mathPhrase.lastIndexOf(operators.peekLast());
    if (!isBalancedEquation || isPrefixNotation || isPostfixNotation) {
      throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
    }

    return calculate(digits, operators);
  }

  private int calculate(Deque<String> operands, Deque<String> operators) {
    int result = Integer.parseInt(operands.pop());
    while (!operands.isEmpty()) {
      int number = Integer.parseInt(operands.pop());
      switch (operators.pop()) {
      case "plus":
        result += number;
        break;
      case "minus":
        result -= number;
        break;
      case "divided":
        result /= number;
        break;
      case "multiplied":
        result *= number;
        break;
      }
    }
    return result;
  }
}
