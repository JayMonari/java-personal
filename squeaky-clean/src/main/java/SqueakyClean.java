import java.util.regex.Pattern;
import java.util.stream.Collector;

import static java.lang.Character.isSurrogate;

class SqueakyClean {

    public static String toCamelCase(String kebabCase) {
        var kebabCapture = Pattern.compile("([\\p{L} ])(?:-)(\\p{L})");
        return kebabCapture.matcher(kebabCase)
            .replaceAll(m -> m.group(1) + m.group(2).toUpperCase());
    }

    static String clean(String identifier) {
        return toCamelCase(identifier)
            .replaceAll(" ", "_")
            .replaceAll("\\p{Cntrl}", "CTRL")
            .replaceAll("[α-ω0-9]", "")
            .chars()
            .mapToObj(c -> (char) c)
            .filter(c -> !isSurrogate((char)c))
            .collect(Collector.of(
                StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append,
                StringBuilder::toString));
    }
}
