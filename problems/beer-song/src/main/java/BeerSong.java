import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BeerSong {

  public String sing(int start, int numberOfVerses) {
    return IntStream.iterate(start, v -> v > start - numberOfVerses, v -> v - 1)
      .mapToObj(this::verse)
      .collect(Collectors.joining("\n", "", "\n"));
  }

  public String verse(int number) {
    switch (number) {
      case 2:
        return String.format(
            "%d bottles of beer on the wall, %d bottles of beer.%n" +
            "Take one down and pass it around, %d bottle of beer on the wall.%n",
            number, number,
            number - 1);
      case 1:
        return String.format(
            "%d bottle of beer on the wall, %d bottle of beer.%n" +
            "Take it down and pass it around, no more bottles of beer on the wall.%n",
            number, number);
      case 0:
        return String.format(
            "No more bottles of beer on the wall, no more bottles of beer.%n" +
            "Go to the store and buy some more, 99 bottles of beer on the wall.%n");
      default:
        return String.format(
            "%d bottles of beer on the wall, %d bottles of beer.%n" +
            "Take one down and pass it around, %d bottles of beer on the wall.%n",
            number, number,
            number - 1);
    }
  }

  public String singSong() {
    return sing(99, 100);
  }
}
