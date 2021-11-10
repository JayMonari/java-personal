public class QueenAttackCalculator {

  private Queen queen1;
  private Queen queen2;
  private int slope;

  public QueenAttackCalculator(Queen queen1, Queen queen2) {
    if (queen1 == null || queen2 == null) {
      throw new IllegalArgumentException("You must supply valid positions for both Queens.");
    } else if (queen1.equals(queen2)) {
      throw new IllegalArgumentException("Queens cannot occupy the same position.");
    }
    this.queen1 = queen1;
    this.queen2 = queen2;
    this.slope = calculateSlopeOfQueens(queen1, queen2);
  }

  public boolean canQueensAttackOneAnother() {
    if (queen1.row == queen2.row || queen1.col == queen2.col || slope == 1) {
      return true;
    } else {
      return false;
    }
  }

  private int calculateSlopeOfQueens(Queen queen1, Queen queen2) {
    try {
      return Math.abs((queen1.col - queen2.col) / (queen1.row - queen2.row));
    } catch (ArithmeticException e) {
      return 0;
    }
  }
}
