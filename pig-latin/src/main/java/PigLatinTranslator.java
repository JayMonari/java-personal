import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PigLatinTranslator {
  private static final String STARTS_WITH_VOWEL_SOUND = "^([aeiou]|xr|yt).*";
  private static final Pattern STARTS_WITH_CONSONANT =
    Pattern.compile("^([bcdfghjklmnpqrstuvwxyz]+)([aeiou].*)$");
  private static final Pattern HAS_CONSONANT_AND_QU =
    Pattern.compile("^([bcdfghjklmnpqrstuvwxyz]*qu)(.*)$");
  private static final Pattern HAS_Y_AFTER_CONSONANT_CLUSTER =
    Pattern.compile("^([bcdfghjklmnpqrstuvwxyz]+)(y.*)$");
  private static final Pattern TWO_LETTER_WORD_ENDING_IN_Y =
    Pattern.compile("^(.)(y)$");

  public String translate(String phrase) {
    return Arrays.stream(phrase.split(" "))
      .map(this::convertWord)
      .collect(Collectors.joining(" "));
  }

  private String convertWord(String word) {
    if (word.matches(STARTS_WITH_VOWEL_SOUND)) {
      return word + "ay";
    }
    Matcher match;
    if ((match = HAS_CONSONANT_AND_QU.matcher(word)).matches()
        || (match = STARTS_WITH_CONSONANT.matcher(word)).matches()
        || (match = HAS_Y_AFTER_CONSONANT_CLUSTER.matcher(word)).matches()
        || (TWO_LETTER_WORD_ENDING_IN_Y.matcher(word)).matches()) {
      return match.group(2) + match.group(1) + "ay";
    }
    return word;
  }
}
