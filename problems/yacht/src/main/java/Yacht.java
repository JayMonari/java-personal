import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.Collectors;

class Yacht {
  private int yachtScore;

  Yacht(int[] dice, YachtCategory yachtCategory) {
    yachtScore = calculateScore(Arrays.stream(dice).boxed(), yachtCategory);
  }

  int score() {
    return yachtScore;
  }

  private int calculateScore(Stream<Integer> diceStream, YachtCategory yachtCategory) {
    switch (yachtCategory) {
      case ONES:
        return 1 * (int) diceStream.filter(i -> i == 1).count();
      case TWOS:
        return 2 * (int) diceStream.filter(i -> i == 2).count();
      case THREES:
        return 3 * (int) diceStream.filter(i -> i == 3).count();
      case FOURS:
        return 4 * (int) diceStream.filter(i -> i == 4).count();
      case FIVES:
        return 5 * (int) diceStream.filter(i -> i == 5).count();
      case SIXES:
        return 6 * (int) diceStream.filter(i -> i == 6).count();
      case CHOICE:
        return diceStream.mapToInt(i -> i).sum();
      case FULL_HOUSE:
        var rcs = diceStream.collect(
            Collectors.groupingBy(Integer::valueOf, Collectors.counting()));
        if (rcs.size() > 2) return 0;
        return rcs.entrySet()
          .stream()
          .mapToInt(rollCount -> {
            if (rollCount.getValue() == 2) {
              return 2 * rollCount.getKey();
            } else if (rollCount.getValue() == 3) {
              return 3 * rollCount.getKey();
            }
            return 0;
          }).sum();
      case FOUR_OF_A_KIND:
        return diceStream.collect(
            Collectors.groupingBy(Integer::valueOf, Collectors.counting()))
          .entrySet()
          .stream()
          .filter(rollCount -> rollCount.getValue() >= 4)
          .mapToInt(rc -> rc.getKey() * 4)
          .findFirst()
          .orElseGet(() -> 0);
      case LITTLE_STRAIGHT:
        return diceStream.filter(i -> i != 6).distinct().count() == 5 ? 30 : 0;
      case BIG_STRAIGHT:
        return diceStream.filter(i -> i != 1).distinct().count() == 5 ? 30 : 0;
      case YACHT:
        return diceStream.distinct().count() == 1 ? 50 : 0;
    }
    return 0;
  }
}
