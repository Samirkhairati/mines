import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

       Swarm swarm = new Swarm(1000);
       swarm.thrive(10);
       Collections.sort(swarm.bots, (b1, b2) -> Float.compare(b1.capital, b2.capital));

       swarm.logCapital();

       swarm.showTopBots(20);
       Bot topBot = swarm.bots.get(swarm.bots.size() - 45);
       System.out.println("\n" + topBot.getData());

       Bot.logProfits(topBot);
       Bot.retest(50, topBot.clone());

    }
}