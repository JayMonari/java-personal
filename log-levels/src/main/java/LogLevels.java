public class LogLevels {

    public static String message(String logLine) {
        return logLine.codePoints()
            .mapToObj(c -> (char) c)
            .dropWhile(c -> c != ' ')
            .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
            .toString()
            .trim();
    }

    public static String logLevel(String logLine) {
        return logLine.codePoints()
            .mapToObj(c -> (char) Character.toLowerCase(c))
            .filter(c -> c != '[')
            .takeWhile(c -> c != ']')
            .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
            .toString();
    }

    public static String reformat(String logLine) {
        return String.format("%s (%s)", message(logLine), logLevel(logLine));
    }
}
