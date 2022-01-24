import java.util.stream.IntStream;

public class PangramChecker {

  public boolean isPangram(String input) {
    String low = input.toLowerCase();
    return IntStream.rangeClosed('a', 'z').allMatch(c -> low.indexOf(c) != -1);
  }
}
