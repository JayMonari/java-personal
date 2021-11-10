import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WordCount {

  public Map<String, Integer> phrase(String words) {
    Map<String, Integer> counter = new HashMap<>();
    Arrays.stream(words.toLowerCase().split("[^a-zA-Z_0-9']+"))
        .filter(s -> !s.isEmpty())
        .forEach(word -> {
          if (word.startsWith("'") && word.endsWith("'"))
            word = word.substring(1, word.length() - 1);
          counter.put(word, counter.getOrDefault(word, 0) + 1);
      });
    return counter;
  }
}
