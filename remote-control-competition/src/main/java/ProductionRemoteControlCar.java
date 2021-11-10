class ProductionRemoteControlCar implements RemoteControlCar, Comparable<ProductionRemoteControlCar> {
  private int distance = 0;
  private int victories = 0;

  @Override
  public void drive() {
    distance += 10;
  }

  @Override
  public int getDistanceTravelled() {
    return distance;
  }

  public int getNumberOfVictories() {
    return victories;
  }

  public void setNumberOfVictories(int numberofVictories) {
    victories = numberofVictories;
  }

  @Override
  public int compareTo(ProductionRemoteControlCar other) {
    if (victories == other.getNumberOfVictories()) return 0;
    return victories > other.getNumberOfVictories() ? 1 : -1;
  }
}
