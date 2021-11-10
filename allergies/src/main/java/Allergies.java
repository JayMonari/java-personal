import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Allergies {
    private List<Allergen> allergens;

    public Allergies(int score) {
        allergens = findAllergens(score);
    }

    public boolean isAllergicTo(Allergen allergen) {
        return allergens.contains(allergen);
    }

    public List<Allergen> getList() {
        return allergens;
    }

    private List<Allergen> findAllergens(int score) {
        return Arrays.stream(Allergen.values())
            .filter(a -> (score & a.getScore()) != 0)
            .collect(Collectors.toList());
    }
}
