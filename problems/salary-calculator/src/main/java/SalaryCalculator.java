public class SalaryCalculator {
  private static final double baseSalary = 1000.00D;

  public double multiplierPerDaysSkipped(int daysSkipped) {
    return daysSkipped > 5 ? 0.85D : 1.00D;
  }

  public int multiplierPerProductsSold(int productsSold) {
    return productsSold > 20 ? 13 : 10;
  }

  public double bonusForProductSold(int productsSold) {
    return multiplierPerProductsSold(productsSold) * productsSold;
  }

  public double finalSalary(int daysSkipped, int productsSold) {
    double correctedSalary = baseSalary * multiplierPerDaysSkipped(daysSkipped);
    double fullSalary = correctedSalary + bonusForProductSold(productsSold);
    return fullSalary > 2000 ? 2000 : fullSalary;
  }
}
