import java.util.stream.IntStream;

class IsbnVerifier {

  boolean isValid(String candidate) {
    if (candidate.matches(".*[A-WYZ].*")) {
      return false;
    }

    String isbn = candidate.replaceAll("[^0-9X]", "");
    if (isbn.length() != 10) {
      return false;
    } else if (isbn.contains("X") && isbn.charAt(9) != 'X') {
      return false;
    }

    return IntStream.range(0, isbn.length()).mapToObj(i -> {
      char num = isbn.charAt(i);
      if (num == 'X') {
        return 10;
      }
      return (10 - i) * Character.getNumericValue(isbn.charAt(i));
    }).reduce(0, (a, b) -> a + b) % 11 == 0;
  }

}
