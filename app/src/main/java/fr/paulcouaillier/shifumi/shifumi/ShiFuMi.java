package fr.paulcouaillier.shifumi.shifumi;

import android.content.Context;

import fr.paulcouaillier.shifumi.R;


/**
 * Created by paulcouaillier on 10/06/16.
 */
public class ShiFuMi {
    public static final int GREEN = R.drawable.car_green;
    public static final int BLUE = R.drawable.car_blue;
    public static final int RED = R.drawable.car_green;

    private static boolean cheat = false;

    private static ShiFuMiHistory history;

    private static Integer computerChoice = null;

    public int getComputerChoice() {
        if(computerChoice == null) {
            ShiFuMi.computerChoice = randomShiFuMi();
        }
        return computerChoice;
    }

    public ShiFuMi(Context context) {
        if(history == null) {
            new ShiFuMiHistory(context);
        }
    }

    private static int randomShiFuMi() {
        double r = Math.random();
        if(r<1.0/3.0) {
            return BLUE;
        } else if(r<2.0/3.0) {
            return ShiFuMi.GREEN;
        }
        return ShiFuMi.RED;
    }

    public ShiFuMiTurn shiFuMi(final int computerColor, final int userColor, final Context context) {

        ShiFuMiTurn shiFuMiTurn = new ShiFuMiTurn(computerColor, userColor, runShiFuMi(computerColor, userColor));

        history.add(shiFuMiTurn);

        return shiFuMiTurn;

    }


    private int runShiFuMi(int firstColor, int secondColor) {

        if (firstColor == GREEN && secondColor == BLUE) {
            return GREEN;
        } else if (firstColor == BLUE && secondColor == RED) {
            return BLUE;
        } else if (firstColor == RED && secondColor == GREEN) {
            return RED;
        }
        return secondColor;
    }

    public static void setCheat(boolean cheat) {
        ShiFuMi.cheat = cheat;
    }
}
