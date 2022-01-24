import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Gigasecond {
    private LocalDateTime dateTime;

    public Gigasecond(LocalDate moment) {
        dateTime = LocalDateTime.of(moment, LocalTime.MIN)
            .plusSeconds((long) Math.pow(10, 9));
    }

    public Gigasecond(LocalDateTime moment) {
        dateTime = moment.plusSeconds((long) Math.pow(10, 9));
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
