import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class BracketChecker {
  private char[] pairs;
  private final Map<Character, Character> closeMapping =
    Map.of( '(', ')', '{', '}', '[', ']');
  private List<Character> openPairs = new ArrayList<>();

  public BracketChecker(String text) {
    this.pairs = text.replaceAll("[^(){}\\[\\]]", "").toCharArray();
  }

  public boolean areBracketsMatchedAndNestedCorrectly() {
    return IntStream.range(0, pairs.length).mapToObj(i -> {
      char part = pairs[i];
      if ("}])".indexOf(part) != -1) {
        if (openPairs.size() == 0) return false;

        char openPart = openPairs.remove(openPairs.size() - 1);
        if (closeMapping.get(openPart) != part) return false;
      } else {
        openPairs.add(part);
      }
      return true;
    })
      .filter(bool -> !bool)
      .findFirst()
      .orElseGet(() -> openPairs.size() == 0);
  }
}
