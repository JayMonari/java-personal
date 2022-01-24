import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ProteinTranslator {

  List<String> translate(String rnaSequence) {
    return IntStream.iterate(3, n -> n <= rnaSequence.length(), n -> n + 3)
      .mapToObj(i -> mapAminoAcid(rnaSequence.substring(i - 3, i)))
      .takeWhile(p -> !"STOP".equals(p))
      .collect(Collectors.toList());
  }


  private String mapAminoAcid(String codon) {
    switch (codon) {
      case "AUG":
        return "Methionine";
      case "UUU": case "UUC":
        return "Phenylalanine";
      case "UUA": case "UUG":
        return "Leucine";
      case "UCU": case "UCC": case "UCA": case "UCG":
        return "Serine";
      case "UAU": case "UAC":
        return "Tyrosine";
      case "UGU": case "UGC":
        return "Cysteine";
      case "UGG":
        return "Tryptophan";
      case "UAA": case "UAG": case "UGA":
        return "STOP";
      default:
        throw new UnsupportedOperationException("HOW YOU DO DAT?");
    }
  }
}
