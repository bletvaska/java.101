package sk.tuke.mines;

import sk.tuke.mines.core.Clue;
import sk.tuke.mines.core.Mine;
import sk.tuke.mines.core.MineField;
import sk.tuke.mines.core.Tile;

public class Main {
    public static void main(String[] args) {
        var field = new MineField(5, 4, 10);

        // render field
        for (var row = 0; row < field.getRowCount(); row++) {
            for (var column = 0; column < field.getColumnCount(); column++) {
                var tile = field.getTile(row, column);

                if (tile instanceof Mine)
                    System.out.print("X");
                else if (tile instanceof Clue)
                    System.out.print(((Clue) tile).getValue());
            }
            System.out.println();
        }
    }
}
