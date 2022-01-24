import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrepTool {

  public String grep(String regex, List<String> flags, List<String> files) {
    boolean flagLineNumber = flags.contains("-n");
    boolean flagFilesWithMatches = flags.contains("-l");
    boolean flagIgnoreCase = flags.contains("-i");
    boolean flagInvertMatch = flags.contains("-v");
    boolean flagLineRegexp = flags.contains("-x");
    boolean flagWithFilename = files.size() > 1;

    Pattern pattern = Pattern.compile(Pattern.quote(regex), flagIgnoreCase ? Pattern.CASE_INSENSITIVE : 0);
    Deque<CharSequence> matches = new ArrayDeque<>();

    for (String filename : files) {
      try (BufferedReader reader = Files.newBufferedReader(Path.of(filename))) {
        String line;
        for (int lineNumber = 1; (line = reader.readLine()) != null; lineNumber++) {
          Matcher matcher = pattern.matcher(line);
          if (flagInvertMatch ^ (flagLineRegexp ? matcher.matches() : matcher.find())) {
            if (flagFilesWithMatches) {
              matches.add(filename);
              break;
            } else {
              StringBuilder format = new StringBuilder();
              if (flagWithFilename)
                format.append(filename).append(':');
              if (flagLineNumber)
                format.append(lineNumber).append(':');
              matches.add(format.append(line));
            }
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return String.join("\n", matches);
  }
}
