import java.util.Arrays;

class BirdWatcher {
    private final int[] birdsPerDay;
    private final int todayIndex;

    public BirdWatcher(int[] birdsPerDay) {
        this.birdsPerDay = birdsPerDay.clone();
        todayIndex = birdsPerDay.length - 1;
    }

    public int[] getLastWeek() {
        return birdsPerDay;
    }

    public int getToday() {
        if (todayIndex < 0) return 0;
        return birdsPerDay[todayIndex];
    }

    public void incrementTodaysCount() {
        birdsPerDay[todayIndex] = birdsPerDay[todayIndex] + 1;
    }

    public boolean hasDayWithoutBirds() {
        return Arrays.stream(birdsPerDay).anyMatch(count -> count == 0);
    }

    public int getCountForFirstDays(int numberOfDays) {
        return Arrays.stream(birdsPerDay).limit(numberOfDays).sum();
    }

    public int getBusyDays() {
        return (int) Arrays.stream(birdsPerDay)
            .filter(count -> count >= 5)
            .count();
    }
}
