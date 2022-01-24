public class ElonsToyCar {
  private int meters;
  private int battery;

  public ElonsToyCar() {
    this.meters = 0;
    this.battery = 100;
  }

  private ElonsToyCar(int meters, int battery) {
    this.meters = meters;
    this.battery = battery;
  }

  public static ElonsToyCar buy() {
    return new ElonsToyCar(0, 100);
  }

  public String distanceDisplay() {
    return String.format("Driven %d meters", meters);
  }

  public String batteryDisplay() {
    if (battery <= 0)
      return "Battery empty";

    return String.format("Battery at %02d%%", battery);
  }

  public void drive() {
    if (battery <= 0) return;

    meters += 20;
    battery -= 1;
  }
}
