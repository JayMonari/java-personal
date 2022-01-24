import java.util.Random;

public class Robot {
  private String name;

  public Robot() {
    name = generateName();
  }

  public String getName() {
    return name;
  }

  public void reset() {
    name = generateName();
  }

  private String generateName() {
    Random random = new Random();
    return String.format("%c%c%03d",
        'A' + random.nextInt(26),
        'A' + random.nextInt(26),
        random.nextInt(1000));
  }
}
