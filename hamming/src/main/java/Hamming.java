import java.util.stream.IntStream;

public class Hamming {
  private int distance;

  public Hamming(String leftStrand, String rightStrand) {
    if (leftStrand.isEmpty() && !rightStrand.isEmpty()) {
      throw new IllegalArgumentException("left strand must not be empty.");
    } else if (rightStrand.isEmpty() && leftStrand.length() > 0) {
      throw new IllegalArgumentException("right strand must not be empty.");
    } else if (leftStrand.length() != rightStrand.length()) {
      throw new IllegalArgumentException("leftStrand and rightStrand must be of equal length.");
    }
    distance = (int) IntStream.range(0, leftStrand.length())
      .filter(i -> leftStrand.charAt(i) != rightStrand.charAt(i))
      .count();
  }

  public int getHammingDistance() {
    return distance;
  }
}
