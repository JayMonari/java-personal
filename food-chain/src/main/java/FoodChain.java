import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FoodChain {
  private static final String VERSE_START = "I know an old lady who swallowed a";
  private static final String VERSE_END = "I don't know why she swallowed the fly. Perhaps she'll die.";
  private static final String PUNCH_LINE = "She's dead, of course!";

  private class VerseLine {
    private String animal;
    private String action;

    public VerseLine(String animal, String action) {
      this.animal = animal;
      this.action = action;
    }
  }

  private final Map<Integer, VerseLine> VERSE_LINES = Map.of(
      1, new VerseLine("fly", ""),
      2, new VerseLine("spider", "It wriggled and jiggled and tickled inside her.\n"),
      3, new VerseLine("bird", "How absurd to swallow a bird!\n"),
      4, new VerseLine("cat", "Imagine that, to swallow a cat!\n"),
      5, new VerseLine("dog", "What a hog, to swallow a dog!\n"),
      6, new VerseLine("goat", "Just opened her throat and swallowed a goat!\n"),
      7, new VerseLine("cow", "I don't know how she swallowed a cow!\n"),
      8, new VerseLine("horse", ""));

  public String verse(int verseNumber) {
    if (verseNumber == 8) {
      return String.format("%s %s.\n%s", VERSE_START,
          VERSE_LINES.get(verseNumber).animal,
          PUNCH_LINE);
    }

    StringBuilder verse = new StringBuilder(
        String.format("%s %s.\n%s", VERSE_START, VERSE_LINES.get(verseNumber).animal,
          VERSE_LINES.get(verseNumber).action));
    String spiderLine;
    for (int v = verseNumber; v > 1; v--) {
      if ("bird".equals(VERSE_LINES.get(v).animal)) {
        spiderLine = " that wriggled and jiggled and tickled inside her";
      } else {
        spiderLine = "";
      }
      verse.append(String.format("She swallowed the %s to catch the %s%s.\n",
          VERSE_LINES.get(v).animal, VERSE_LINES.get(v - 1).animal, spiderLine));
    }
    verse.append(VERSE_END);
    return verse.toString();
  }

  public String verses(int startVerse, int endVerse) {
    return IntStream.iterate(startVerse, v -> v <= endVerse, v -> v + 1)
      .mapToObj(i -> verse(i))
      .collect(Collectors.joining("\n\n"));
  }
}
