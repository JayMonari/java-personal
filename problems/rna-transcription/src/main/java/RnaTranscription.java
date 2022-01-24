import java.util.Map;

class RnaTranscription {
  private final Map<Character, Character> TRANSCRIPTS =
    Map.of('A', 'U', 'T', 'A', 'G', 'C', 'C', 'G');

  String transcribe(String dnaStrand) {
    return dnaStrand.chars().mapToObj(c -> TRANSCRIPTS.get((char) c))
      .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
      .toString();
  }
}
