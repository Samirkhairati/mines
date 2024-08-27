public class Round {

    final private int mines;
    final private int picks;

    public Round(
            int mines,
            int picks
    ) {
        this.mines = mines;
        this.picks = picks;
    }

    private float multiplier() {
        return (float) (picks * mines * 4 * 0.01);
    }

    public float play(float bet) {
        Board board = new Board(this.mines);
        for (int i = 0; i < picks; i++) {
            if (board.minePositions.contains(i)) {
                return -bet;
            }
        }
        return bet * multiplier();
    }

    public static class RoundResult {
        final float nextBet;
        final float profit;
        public RoundResult(float nextBet, float profit) {
            this.nextBet = nextBet;
            this.profit = profit;
        }
    }

}
