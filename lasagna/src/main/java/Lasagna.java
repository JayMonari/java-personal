public class Lasagna {
    public int expectedMinutesInOven() {
        return 40;
    }

    public int remainingMinutesInOven(int ovenTime) {
        return expectedMinutesInOven() - ovenTime;
    }

    public int preparationTimeInMinutes(int layers) {
        return layers * 2;
    }

    public int totalTimeInMinutes(int layers, int ovenTime) {
        return preparationTimeInMinutes(layers) + ovenTime;
    }
}
