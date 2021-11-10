import java.util.stream.Collectors;
import java.util.stream.IntStream;

class TwelveDays {
  private static final String[] PRESENTS = { " a Partridge in a Pear Tree.\n", " two Turtle Doves,",
      " three French Hens,", " four Calling Birds,", " five Gold Rings,", " six Geese-a-Laying,",
      " seven Swans-a-Swimming,", " eight Maids-a-Milking,", " nine Ladies Dancing,", " ten Lords-a-Leaping,",
      " eleven Pipers Piping,", " twelve Drummers Drumming," };
  private static final String[] DAYS = { "first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth",
      "ninth", "tenth", "eleventh", "twelfth" };

  String verse(int position) {
    var verse = new StringBuilder(
        String.format("On the %s day of Christmas my true love gave to me:", DAYS[position - 1]));
    if (position == 1)
      return verse.append(PRESENTS[0]).toString();

    for (int i = position - 1; i >= 0; i--) {
      if (i == 0) {
        verse.append(" and");
      }
      verse.append(PRESENTS[i]);
    }
    return verse.toString();
  }

  String verses(int startVerse, int endVerse) {
    return IntStream.rangeClosed(startVerse, endVerse)
      .mapToObj(i -> verse(i))
      .collect(Collectors.joining("\n"));
  }

  String sing() {
    return verses(1, 12);
  }
}
