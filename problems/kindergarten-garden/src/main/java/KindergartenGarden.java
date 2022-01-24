import java.util.List;
import java.util.stream.Collectors;

class KindergartenGarden {
  private static final List<String> STUDENTS = List.of(
      "Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny",
      "Harriet", "Ileana", "Joseph", "Kincaid", "Larry");
  private List<Plant> firstRow;
  private List<Plant> secondRow;

  KindergartenGarden(String garden) {
    String[] rows = garden.split("\n");
    firstRow = mapRowPlants(rows[0]);
    secondRow = mapRowPlants(rows[1]);
  }

  List<Plant> getPlantsOfStudent(String student) {
    int plantIdx = STUDENTS.indexOf(student) * 2;
    return List.of(
        firstRow.get(plantIdx), firstRow.get(plantIdx+1),
        secondRow.get(plantIdx), secondRow.get(plantIdx+1));
  }

  private List<Plant> mapRowPlants(String row) {
    return row.chars()
      .mapToObj(c -> Plant.getPlant((char)c))
      .collect(Collectors.toList());
  }
}
