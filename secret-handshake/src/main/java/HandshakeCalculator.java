import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HandshakeCalculator {

  List<Signal> calculateHandshake(int number) {
    List<Signal> signals = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      boolean isActiveSignal = ((number >> i) & 1) == 1;
      if (!isActiveSignal) continue;

      if (i == 4) {
        Collections.reverse(signals);
        break;
      }
      signals.add(Signal.getValue(i));
    }
    return signals;
  }
}
