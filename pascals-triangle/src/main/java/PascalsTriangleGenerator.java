import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PascalsTriangleGenerator {

  public int[][] generateTriangle(int numRows) {
    int[][] triangle = new int[numRows][];
    if (numRows == 0) {
      return triangle;
    }

    triangle[0] = new int[] { 1 };
    IntStream.range(1, numRows)
      .forEach(i -> triangle[i] = makeNextRow(triangle[i - 1]));
    return triangle;
  }

  private int[] makeNextRow(int[] prevRow) {
    var row = new ArrayList<>(List.of(1));
    for (int colIdx = 1; colIdx < prevRow.length; colIdx++) {
      row.add(prevRow[colIdx - 1] + prevRow[colIdx]);
    }
    row.add(1);
    return row.stream().mapToInt(i -> i).toArray();
  }
}
