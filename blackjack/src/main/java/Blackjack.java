public class Blackjack {

    public int parseCard(String card) {
        return Card.valueOf(card.toUpperCase()).score();
    }

    public boolean isBlackjack(String card1, String card2) {
        return Card.valueOf(card1.toUpperCase()).score() +
            Card.valueOf(card2.toUpperCase()).score() == 21;
    }

    public String largeHand(boolean isBlackjack, int dealerScore) {
        if (!isBlackjack) {
            return Option.SPLIT.code();
        }

        if (dealerScore < 10) {
            return Option.AUTO_WIN.code();
        }

        return Option.STAND.code();
    }

    public String smallHand(int handScore, int dealerScore) {
        if (handScore > 16) {
            return Option.STAND.code();
        } else if (handScore < 12) {
            return Option.HIT.code();
        }

        return dealerScore > 6 ? Option.HIT.code() : Option.STAND.code();
    }

    // FirstTurn returns the semi-optimal decision for the first turn, given the
    // cards of the player and the dealer.
    // This function is already implemented and does not need to be edited. It pulls
    // the other functions together in a
    // complete decision tree for the first turn.
    public String firstTurn(String card1, String card2, String dealerCard) {
        int handScore = parseCard(card1) + parseCard(card2);
        int dealerScore = parseCard(dealerCard);

        if (20 < handScore)
            return largeHand(isBlackjack(card1, card2), dealerScore);

        else
            return smallHand(handScore, dealerScore);
    }

    private enum Card {
        ACE(11),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(10),
        QUEEN(10),
        KING(10);

        private final int score;

        private Card(int score) {
            this.score = score;
        }

        public int score() {
            return score;
        }
    }

    private enum Option {
        STAND("S"),
        HIT("H"),
        SPLIT("P"),
        AUTO_WIN("W");

        private String code;

        private Option(String code) {
            this.code = code;
        }

        public String code() {
            return code;
        }
    }

}
