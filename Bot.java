import java.util.ArrayList;
import java.util.HashMap;

public class Bot {
    final private int turns;
    final private int mines;
    final private int picks;
    final private boolean increaseOnLoss;
    final private float increaseOnLossAmount;
    final private boolean increaseOnWin;
    final private float increaseOnWinAmount;
    final private float initialBet;

    private float bet;
    public ArrayList<Boolean> logs = new ArrayList<Boolean>();
//    public HashMap<Float, Float> = new HashMap<Float, Float>();
    public float capital = 1000;


    public Bot (
            int turns,
            int mines,
            int picks,
            float initialBet,
            boolean increaseOnLoss,
            boolean increaseOnWin,
            float increaseOnLossAmount,
            float increaseOnWinAmount
    ) {
        this.turns = turns;
        this.mines = mines;
        this.picks = picks;
        this.increaseOnLoss = increaseOnLoss;
        this.increaseOnWin = increaseOnWin;
        this.increaseOnLossAmount = increaseOnLossAmount;
        this.increaseOnWinAmount = increaseOnWinAmount;
        this.initialBet = initialBet;

        this.bet = initialBet;
    }

    private float changeBet(boolean thisRoundWin) {
        float nextBet;
        if (thisRoundWin) {
            if (increaseOnWin) {
                nextBet = bet * increaseOnWinAmount;
            } else {
                nextBet = initialBet;
            }
        } else {
            if (increaseOnLoss) {
                nextBet = bet * increaseOnLossAmount;
            } else {
                nextBet = initialBet;
            }
        }
        return nextBet;
    }

    public void play() {
        for (int i = 0; i < turns; i++) {
            Round round = new Round(this.mines, this.picks);
            float profit = round.play(bet);
            boolean thisRoundWin = profit > 0;
            this.logs.add(thisRoundWin);
            this.bet = changeBet(thisRoundWin);
            this.capital += profit;
        }
    }

    public String getData() {
        return "Bot{" +
                "turns=" + turns +
                ", mines=" + mines +
                ", picks=" + picks +
                ", initialBet=" + initialBet +
                ", increaseOnLoss=" + increaseOnLoss +
                ", increaseOnWin=" + increaseOnWin +
                ", increaseOnLossAmount=" + increaseOnLossAmount +
                ", increaseOnWinAmount=" + increaseOnWinAmount +
                ", bet=" + this.bet +
                ", initialBet=" + this.initialBet +
                ", capital=" + this.capital +
                '}';
    }

    public static void retest(int rounds,Bot bot) {
        System.out.println("-----------------------------------------------------------------");
        for (int j = 0; j < rounds; j++) {
            bot.bet = bot.initialBet;
            for (int i = 0; i < bot.turns; i++) {
                Round round = new Round(bot.mines, bot.picks);
                float profit = round.play(bot.bet);
                boolean thisRoundWin = profit > 0;
                bot.logs.add(thisRoundWin);
                bot.bet = bot.changeBet(thisRoundWin);
                bot.capital += profit;
               // System.out.println(bot.getData());
            }
            System.out.print(bot.capital + " ");
        }
    }
    @Override
    public Bot clone() {
        return new Bot(
                this.turns,
                this.mines,
                this.picks,
                this.initialBet,
                this.increaseOnLoss,
                this.increaseOnWin,
                this.increaseOnLossAmount,
                this.increaseOnWinAmount
        );
    }

}
