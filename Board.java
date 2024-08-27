import java.util.ArrayList;
import java.util.Collections;

public class Board {
    public ArrayList<Integer> minePositions = new ArrayList<>();

    public Board (int mines) {
        randomizeBoard(mines);
    }

    private void randomizeBoard(int mines) {

        ArrayList<Integer> numberBoard = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            numberBoard.add(i);
        }

        Collections.shuffle(numberBoard);
        for (int i = 0; i < mines; i++) {
            minePositions.add(numberBoard.get(i));
        }
    }

    public void displayBoard() {
        for (int i = 0; i < 25; i++) {
            if (i != 0 && i % 5 == 0) {
                System.out.println();
            }
            if ( !minePositions.contains(i) ) {
                System.out.print("💎 ");
            } else {
                System.out.print("💣 ");
            }
        }
    }
}
