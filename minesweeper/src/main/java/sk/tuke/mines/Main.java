package sk.tuke.mines;

import sk.tuke.mines.core.Clue;
import sk.tuke.mines.core.Mine;
import sk.tuke.mines.core.Tile;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");
        var tile = new Clue(5);
        System.out.println(tile.getValue());
    }
}
