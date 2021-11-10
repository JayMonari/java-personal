import java.util.stream.Collector;

public class Atbash {
  private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
  private static final String CIPHER = "zyxwvutsrqponmlkjihgfedcba";

  public String encode(String text) {
    return flip(text, CIPHER, ALPHABET).replaceAll("(.{5})", "$1 ").trim();
  }

  public String decode(String encoded) {
    return flip(encoded, ALPHABET, CIPHER);
  }

  private String flip(String word, String from, String to) {
    return word.replaceAll("\\W+", "").toLowerCase().chars()
      .mapToObj(c -> from.indexOf((char) c) == -1 ? (char) c : to.charAt(from.indexOf((char) c)))
      .collect(Collector.of(
            StringBuilder::new,
            StringBuilder::append,
            StringBuilder::append,
            StringBuilder::toString));
  }
}
