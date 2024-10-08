import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Swarm {

    public List<Bot> bots = new ArrayList<>();
    final private int population;

    public Swarm (int population) {
        this.population = population;
        System.out.println("Generating " + population + " bots:");
        for (int i = 0; i < population; i++) {
            bots.add(createBot());
            System.out.println("Created " + i + " out of " + population + " bots:");
        }
    }

    public void thrive(int retests) {

        int counter = 1;
        for (Bot bot : bots) {
            for (int i = 0; i < retests; i++) {
                bot.play();
                System.out.printf("Bot %s ----- Round %s ----- Capital $%s%n", counter, i, bot.capital);
            }
            System.out.println();
            counter++;
        }
    }

    public void logCapital() {
        for (Bot bot : bots) {
            System.out.println(bot.capital);
        }
    }


    private Bot createBot() {
        int turns = random(20,50);
        int mines = random(1,10);
        int picks = random(1,10);
        float initialBet = random(0.01f, 10.0f);
        boolean increaseOnLoss = random(0,1) == 0;
        boolean increaseOnWin = random(0,1) == 0;
        float increaseOnLossAmount = random(1.0f, 2.0f);
        float increaseOnWinAmount = random(1.0f, 2.0f);
        return new Bot(
                turns, mines, picks, initialBet, increaseOnLoss, increaseOnWin, increaseOnLossAmount, increaseOnWinAmount
        );
    }

    public void showTopBots(int numberOfBots) {
        int size = bots.size();
        int start = Math.max(size - numberOfBots, 0);  // Calculate the starting index for the last 10 items
        for (int i = start; i < size; i++) {
            System.out.printf("Bot %s %s%n", i+1,  bots.get(i).getData());
        }
    }

    public static float random(float min, float max) {
        Random rand = new Random();
        float randomFloat = rand.nextFloat() * (max - min) + min;
        return Math.round(randomFloat * 100.0f) / 100.0f;
    }
    private static int random(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
