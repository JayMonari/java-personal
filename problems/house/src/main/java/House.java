import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class House {

  private class VerseLine {
    private String action;
    private String thing;

    public VerseLine(String action, String thing) {
      this.action = action;
      this.thing = thing;
    }
  }

private final List<VerseLine> VERSE_LINES = List.of(
    new VerseLine("lay in", "the house that Jack built."),
    new VerseLine("ate", "the malt"),
    new VerseLine("killed", "the rat"),
    new VerseLine("worried", "the cat"),
    new VerseLine("tossed", "the dog"),
    new VerseLine("milked", "the cow with the crumpled horn"),
    new VerseLine("kissed", "the maiden all forlorn"),
    new VerseLine("married", "the man all tattered and torn"),
    new VerseLine("woke", "the priest all shaven and shorn"),
    new VerseLine("kept", "the rooster that crowed in the morn"),
    new VerseLine("belonged to", "the farmer sowing his corn"),
    new VerseLine("", "the horse and the hound and the horn"));

  public String verse(int number) {
    number = number - 1;
    StringBuilder verses = new StringBuilder(
        String.format("This is %s", VERSE_LINES.get(number).thing));
    IntStream.iterate(number - 1, n -> n >= 0, n -> n - 1)
      .forEach(n ->
          verses.append(String.format(" that %s %s", VERSE_LINES.get(n).action, VERSE_LINES.get(n).thing)));
    return verses.toString();
  }

  public String verses(int startVerse, int endVerse) {
    return IntStream.rangeClosed(startVerse, endVerse)
      .mapToObj(v -> verse(v))
      .collect(Collectors.joining("\n"));
  }

  public String sing() {
    return verses(1, 12);
  }
}
