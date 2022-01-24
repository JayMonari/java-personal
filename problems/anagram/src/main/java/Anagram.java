import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Anagram {
  private String sortedWord;
  private String originalWord;

  public Anagram(String word) {
    originalWord = word;
    sortedWord = sort(word);
  }

  public List<String> match(List<String> words) {
    return words.stream()
      .filter(w -> !originalWord.equalsIgnoreCase(w))
      .filter(w -> sortedWord.equals(sort(w)))
      .collect(Collectors.toList());
  }

  private String sort(String word) {
    return word.chars()
      .mapToObj(Character::toLowerCase)
      .sorted()
      .collect(Collector.of(
            StringBuilder::new,
            StringBuilder::append,
            StringBuilder::append,
            StringBuilder::toString));
  }
}
