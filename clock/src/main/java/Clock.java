import java.time.LocalTime;

public class Clock {
  private LocalTime initialTime;

  enum TimeRate {
    MINUTES_PER_HOUR(60), HOURS_PER_DAY(24);

    private int base;

    private TimeRate(int base) {
      this.base = base;
    }

    int base() {
      return base;
    }

  }

  private class NormalizedPair {
    private int high;
    private int low;

    private NormalizedPair(int h, int l) {
      high = h;
      low = l;
    }
  }

  public Clock(int hour, int minute) {
    NormalizedPair hourMinPair = normalize(hour, minute, TimeRate.MINUTES_PER_HOUR.base());
    NormalizedPair dayHourPair = normalize(0, hourMinPair.high, TimeRate.HOURS_PER_DAY.base());
    initialTime = LocalTime.of(dayHourPair.low, hourMinPair.low);
  }

  public void add(int minutes) {
    initialTime = initialTime.plusMinutes(minutes);
  }

  private NormalizedPair normalize(int high, int low, int rate) {
    if (low < 0) {
      int norm = (-low - 1) / rate + 1;
      high -= norm;
      low += norm * rate;
    }
    if (low >= rate) {
      int norm = low / rate;
      high += norm;
      low -= norm * rate;
    }
    return new NormalizedPair(high, low);
  }

  @Override
  public String toString() {
    return initialTime.toString();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this)
      return true;
    if (other == null)
      return false;
    if (getClass() != other.getClass())
      return false;
    Clock clock = (Clock) other;
    return initialTime.equals(clock.initialTime);
  }

  @Override
  public int hashCode() {
    return initialTime.hashCode();
  }
}
