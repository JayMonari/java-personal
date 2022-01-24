class SpaceAge {
  private static final double EARTH_YEAR = 31557600D;
  private static final double MERCURY_YEAR = EARTH_YEAR * 0.2408467;
  private static final double VENUS_YEAR = EARTH_YEAR * 0.61519726;
  private static final double MARS_YEAR = EARTH_YEAR * 1.8808158;
  private static final double JUPITER_YEAR = EARTH_YEAR * 11.862615;
  private static final double SATURN_YEAR = EARTH_YEAR * 29.447498;
  private static final double URANUS_YEAR = EARTH_YEAR * 84.016846;
  private static final double NEPTUNE_YEAR = EARTH_YEAR * 164.79132;
  private double seconds;
  private double earthAge;
  private double mercuryAge;
  private double venusAge;
  private double marsAge;
  private double jupiterAge;
  private double saturnAge;
  private double uranusAge;
  private double neptuneAge;

  SpaceAge(double seconds) {
    this.seconds = seconds;
    earthAge = seconds / EARTH_YEAR;
    mercuryAge = seconds / MERCURY_YEAR;
    venusAge = seconds / VENUS_YEAR;
    marsAge = seconds / MARS_YEAR;
    jupiterAge = seconds / JUPITER_YEAR;
    saturnAge = seconds / SATURN_YEAR;
    uranusAge = seconds / URANUS_YEAR;
    neptuneAge = seconds / NEPTUNE_YEAR;
  }

  double getSeconds() {
    return seconds;
  }

  double onEarth() {
    return earthAge;
  }

  double onMercury() {
    return mercuryAge;
  }

  double onVenus() {
    return venusAge;
  }

  double onMars() {
    return marsAge;
  }

  double onJupiter() {
    return jupiterAge;
  }

  double onSaturn() {
    return saturnAge;
  }

  double onUranus() {
    return uranusAge;
  }

  double onNeptune() {
    return neptuneAge;
  }
}
