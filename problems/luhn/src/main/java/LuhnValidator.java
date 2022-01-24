import java.util.stream.IntStream;

class LuhnValidator {

  boolean isValid(String candidate) {
    if (candidate.matches(".*[^0-9 ].*")) return false;

    String normalized = candidate.replaceAll("[^0-9]", "");
    if ("0".equals(normalized)) return false;

    boolean odd = normalized.length() % 2 == 1;
    return IntStream.iterate(normalized.length() - 1, n -> n >= 0, n -> n - 1)
      .map(i -> {
        int doubled = Character.getNumericValue(normalized.charAt(i));
        if (odd && i % 2 == 1 || !odd && i % 2 == 0) {
          doubled *= 2;
          if (doubled > 9) doubled -= 9;
        }
        return doubled;
      })
      .sum() % 10 == 0;
  }
}
