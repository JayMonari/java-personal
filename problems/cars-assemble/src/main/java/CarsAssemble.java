public class CarsAssemble {
  private static final int amountOfCars = 221;

  public double productionRatePerHour(int speed) {
    double successRate;
    if (speed < 5) {
      successRate = 1.00D;
    } else if (speed < 9) {
      successRate = 0.90D;
    } else if (speed == 9) {
      successRate = 0.80D;
    } else {
      successRate = 0.77D;
    }
    return speed * successRate * amountOfCars;
  }

  public int workingItemsPerMinute(int speed) {
    return (int) productionRatePerHour(speed) / 60;
  }
}
