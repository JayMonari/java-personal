import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Matrix {
  private List<Integer> rowMaxes;
  private List<Integer> colMins;
  private Set<MatrixCoordinate> saddlePoints;

  Matrix(List<List<Integer>> values) {
    rowMaxes = values.stream()
      .map(row -> Collections.max(row))
      .collect(Collectors.toList());
    colMins = findColumnMinimums(values);
    saddlePoints = findSaddlePoints(values);
  }

  Set<MatrixCoordinate> getSaddlePoints() {
    return saddlePoints;
  }

  private List<Integer> findColumnMinimums(List<List<Integer>> values) {
    if (values.isEmpty()) {
      return Collections.emptyList();
    }

    return IntStream.range(0, values.get(0).size())
      .mapToObj(i -> Collections.min(
          values.stream().map(l -> l.get(i)).collect(Collectors.toList()))
    )
      .collect(Collectors.toList());
  }

  private Set<MatrixCoordinate> findSaddlePoints(List<List<Integer>> values) {
    return IntStream.range(0, values.size()).boxed().flatMap(rIdx -> {
      return IntStream.range(0, values.get(0).size()).mapToObj(cIdx -> {
        int candidate = values.get(rIdx).get(cIdx);
        if (candidate == rowMaxes.get(rIdx) && candidate == colMins.get(cIdx)) {
          return new MatrixCoordinate(rIdx + 1, cIdx + 1);
        }
        return null;
      });
    })
    .filter(mc -> mc != null)
    .collect(Collectors.toSet());
  }
}
