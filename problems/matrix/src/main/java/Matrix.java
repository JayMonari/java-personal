import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Matrix {
  private List<List<Integer>> matrix;

  public Matrix(String matrixAsString) {
    matrix = convertToList(matrixAsString);
  }

  public int[] getRow(int rowNumber) {
    return matrix.get(rowNumber - 1).stream().mapToInt(i -> i).toArray();
  }

  public int[] getColumn(int colNumber) {
    return matrix.stream().mapToInt(row -> row.get(colNumber - 1)).toArray();
  }

  private List<List<Integer>> convertToList(String matrixStr) {
    return Arrays.stream(matrixStr.split("\n"))
      .map(row -> Arrays.stream(row.split(" "))
                    .map(s -> Integer.valueOf(s))
                    .collect(Collectors.toList()))
      .collect(Collectors.toList());
  }
}
