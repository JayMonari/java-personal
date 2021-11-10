public class Queen {
  public int row;
  public int col;

  public Queen(int row, int col) {
    if (row < 0) {
      throw new IllegalArgumentException("Queen position must have positive row.");
    } else if (row > 7) {
      throw new IllegalArgumentException("Queen position must have row <= 7.");
    } else if (col < 0) {
      throw new IllegalArgumentException("Queen position must have positive column.");
    } else if (col > 7) {
      throw new IllegalArgumentException("Queen position must have column <= 7.");
    }
    this.row = row;
    this.col = col;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null) return false;
    if (obj.getClass() != getClass()) return false;
    Queen that = (Queen) obj;
    return (that.row == row && that.col == col);
  }

  @Override
  public int hashCode() {
    return (row * 31) ^ (col * 37);
  }
}
