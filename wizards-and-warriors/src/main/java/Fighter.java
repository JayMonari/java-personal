abstract class Fighter {

  boolean isVulnerable() {
    return false;
  }

  abstract int damagePoints(Fighter fighter);
}

class Warrior extends Fighter {

  @Override
  public String toString() {
    return "Fighter is a Warrior";
  }

  @Override
  int damagePoints(Fighter wizard) {
    return wizard.isVulnerable() ? 10 : 6;
  }
}

class Wizard extends Fighter {
  private boolean preparedSpell = false;

  @Override
  boolean isVulnerable() {
    return !preparedSpell;
  }

  @Override
  int damagePoints(Fighter warrior) {
    return preparedSpell ? 12 : 3;
  }

  void prepareSpell() {
    preparedSpell = !preparedSpell;
  }
}
