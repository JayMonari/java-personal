import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class Meetup {
  private LocalDate meetup;

  public Meetup(int month, int year) {
    meetup = LocalDate.of(year, month, 1);
  }

  public LocalDate day(DayOfWeek day, MeetupSchedule meetupSchedule) {
    LocalDate date = meetup.with(TemporalAdjusters.firstInMonth(day));
    switch (meetupSchedule) {
      case FIRST:
        return date;
      case LAST:
        return meetup.with(TemporalAdjusters.lastInMonth(day));
      case TEENTH:
        return meetup.withDayOfMonth(12).with(TemporalAdjusters.next(day));
      // Fallthrough for each
      case FOURTH:
        date = date.with(TemporalAdjusters.next(day));
      case THIRD:
        date = date.with(TemporalAdjusters.next(day));
      case SECOND:
        date = date.with(TemporalAdjusters.next(day));
      default:
        return date;
    }
  }
}
