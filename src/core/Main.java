package core;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by J on 14/05/2016.
 */
public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid();
        ArrayList<Dot> gameDots = new ArrayList<Dot>();
        int i = 0;
        while (i < 50) {
            Dot dot = new Dot();
            //System.out.println("Dot " + i + " : " + dot.getX() + " , " + dot.getY());
            if (grid.doesNotOwn(dot)) {
                grid.addDot(dot);
                i++;
                gameDots.add(dot);
                //System.out.println(grid.afficheGrid());
            }
        }
        for (int j = 1; j < 11; j++) {
            int index = randInt(0, 49);
            Dot dot = gameDots.get(index);
            System.out.println("Dot " + j + " : " + dot.getX() + " , " + dot.getY() + " , Depth : " + grid.depthOf(dot));

            for (Dot dut : grid.findNeighbourhood(dot)) {
                System.out.println("Dot : " + dut.getX() + " , " + dut.getY() + " , Depth : " + grid.depthOf(dut));
            }
        }
    }

    private static int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

}
