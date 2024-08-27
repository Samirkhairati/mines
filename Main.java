import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

       Swarm swarm = new Swarm(1000);
       swarm.live();
       Collections.sort(swarm.bots, (b1, b2) -> Float.compare(b1.capital, b2.capital));
       swarm.logLogs();

       swarm.showTopBots(50);
       Bot topBot = swarm.bots.get(swarm.bots.size() - 45);

       Bot.retest(50, topBot.clone());
        System.out.println("\n" + topBot.getData());
//       int x = 1;
    }
}