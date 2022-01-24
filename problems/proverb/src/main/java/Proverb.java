import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Proverb {
  private List<String> words;

  Proverb(String[] words) {
    this.words = Arrays.asList(words);
  }

  String recite() {
    if (words.isEmpty()) {
      return "";
    }

    String proverb = IntStream.range(1, words.size())
      .mapToObj(i -> String.format(
            "For want of a %s the %s was lost.\n", words.get(i - 1), words.get(i)))
      .collect(Collectors.joining());
    proverb += String.format("And all for the want of a %s.", words.get(0));
    return proverb;
  }
}
