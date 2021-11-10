import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class IsogramChecker {

  boolean isIsogram(String phrase) {
    List<Integer> onlyAlphas = phrase.toLowerCase().chars()
      .filter(ch -> Character.isLetter(ch))
      .boxed()
      .collect(Collectors.toList());
    return Set.copyOf(onlyAlphas).size() == onlyAlphas.size();
  }
}
