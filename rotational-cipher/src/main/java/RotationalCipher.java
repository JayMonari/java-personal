class RotationalCipher {
  private final int ALPHABET_LENGTH = 26;
  private int shiftDistance;

  RotationalCipher(int shiftKey) {
    shiftDistance = shiftKey % ALPHABET_LENGTH;
  }

  String rotate(String data) {
    return data.chars()
      .mapToObj(this::encode)
      .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
      .toString();
  }

  private char encode(int ch) {
    if (!Character.isLetter(ch)) {
      return (char) ch;
    }

    char rotated = (char) (ch + shiftDistance);
    if (Character.isUpperCase(ch)) {
      rotated = enforceBounds(rotated, 'A' - 1);
    } else {
      rotated = enforceBounds(rotated, 'a' - 1);
    }
    return rotated;
  }

  private char enforceBounds(char c, int limit) {
    if (c <= (limit + ALPHABET_LENGTH)) {
      return c;
    }
    return (char) (limit + c % (limit + ALPHABET_LENGTH));
  }
}
