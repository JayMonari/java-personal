import java.util.Arrays;
import java.util.stream.Collector;

class Acronym {
    private String acronym;

    Acronym(String phrase) {
        acronym = contract(phrase);
    }

    String get() {
        return acronym;
    }

    private String contract(String phrase) {
        return Arrays.stream(phrase.split("([^'a-zA-Z]|_)+"))
            .filter(s -> !s.isEmpty())
            .map(s -> Character.toUpperCase(s.charAt(0)))
            .collect(Collector.of(
                StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append,
                StringBuilder::toString)
            );
    }
}
