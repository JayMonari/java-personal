import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class DiamondPrinter {

  List<String> printToList(char letter) {
    if (!Character.isUpperCase(letter)) {
      throw new IllegalArgumentException("letter must be an uppercase char");
    }

    int width = (letter - 'A') * 2 + 1;
    List<StringBuilder> diamond = Stream
      .generate(() -> new StringBuilder(" ".repeat(width)))
      .limit(width)
      .collect(Collectors.toList());
    char currLetter = 'A';
    int midIdx = width / 2;
    int endIdx = diamond.size() - 1;
    for (int idx = 0; idx < midIdx; idx++) {
      diamond.get(idx).setCharAt(midIdx - idx, currLetter);
      diamond.get(idx).setCharAt(midIdx + idx, currLetter);
      diamond.get(endIdx - idx).setCharAt(midIdx - idx, currLetter);
      diamond.get(endIdx - idx).setCharAt(midIdx + idx, currLetter);
      currLetter++;
    }
    // Add final middle section of diamond.
    diamond.get(endIdx/2).setCharAt(0, letter);
    diamond.get(endIdx/2).setCharAt(endIdx, letter);

    return diamond.stream().map(sb -> sb.toString()).collect(Collectors.toList());
  }
}
