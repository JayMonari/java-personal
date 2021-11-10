public class PhoneNumber {
  private String phoneNumber;

  public PhoneNumber(String phoneNumber) {
    if (phoneNumber.chars().anyMatch(Character::isLetter)) {
      throw new IllegalArgumentException("letters not permitted");
    } else if (phoneNumber.contains("@") || phoneNumber.contains(":")) {
      throw new IllegalArgumentException("punctuations not permitted");
    }

    String sanitizedNumber = phoneNumber.replaceAll("[^0-9]", "");
    if (sanitizedNumber.length() < 10) {
      throw new IllegalArgumentException("incorrect number of digits");
    } else if (sanitizedNumber.length() > 11) {
      throw new IllegalArgumentException("more than 11 digits");
    }
    if (sanitizedNumber.length() == 11) {
      if (sanitizedNumber.charAt(0) != '1') {
        throw new IllegalArgumentException("11 digits must start with 1");
      }
      sanitizedNumber = sanitizedNumber.substring(1);
    }

    if (sanitizedNumber.charAt(0) == '0') {
      throw new IllegalArgumentException("area code cannot start with zero");
    } else if (sanitizedNumber.charAt(0) == '1') {
      throw new IllegalArgumentException("area code cannot start with one");
    }

    if (sanitizedNumber.charAt(3) == '0') {
      throw new IllegalArgumentException("exchange code cannot start with zero");
    } else if (sanitizedNumber.charAt(3) == '1') {
      throw new IllegalArgumentException("exchange code cannot start with one");
    }
    this.phoneNumber = sanitizedNumber;
  }

  public String getNumber() {
    return phoneNumber;
  }
}
