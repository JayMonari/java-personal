class Darts {
  private double x;
  private double y;

  Darts(double x, double y) {
    this.x = x;
    this.y = y;
  }

  int score() {
    double point = x*x + y*y;
    if (point <= 1.00) {
      return 10;
    } else if (point <= 25.00) {
      return 5;
    } else if (point <= 100.00) {
      return 1;
    }
    return 0;
  }
}
