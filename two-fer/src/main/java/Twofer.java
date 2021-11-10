import java.util.Optional;

public class Twofer {
  public String twofer(String name) {
    return String.format("One for %s, one for me.",
        Optional.ofNullable(name).orElseGet(() -> new String("you")));
  }
}
