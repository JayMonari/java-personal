class RomanNumerals {
  private final String[] ROMAN_CHARACTERS = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV",
      "I" };
  private final int[] ROMAN_VALUES = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
  private String romanNumeral;

  public RomanNumerals(int number) {
    if (number < 1) {
      throw new IllegalArgumentException("Number must be positive integer.");
    }
    romanNumeral = convertNumber(number);
  }

  public String getRomanNumeral() {
    return romanNumeral;
  }

  private String convertNumber(int number) {
    StringBuilder romanNumeral = new StringBuilder();
    for (int i = 0; number > 0; i++) {
      while (number >= ROMAN_VALUES[i]) {
        number -= ROMAN_VALUES[i];
        romanNumeral.append(ROMAN_CHARACTERS[i]);
      }
    }
    return romanNumeral.toString();
  }
}
