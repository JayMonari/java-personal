import java.util.Map;
import java.util.HashMap;

public class NucleotideCounter {
  private Map<Character, Integer> counts;

  public NucleotideCounter(String strand) {
    if (!strand.matches("[AGCT]+") && !strand.isEmpty()) {
      throw new IllegalArgumentException("Strand can only consist of AGCT");
    }
    counts = countNucleotides(strand);
  }

  public Map<Character, Integer> nucleotideCounts() {
    return counts;
  }

  private Map<Character, Integer> countNucleotides(String strand) {
    var counter = new HashMap<>(Map.of('A', 0, 'C', 0, 'G', 0, 'T', 0));
    strand.chars()
      .mapToObj(c -> (char) c)
      .forEach(c -> counter.replace(c, counter.get(c) + 1));
    return counter;
  }
}
