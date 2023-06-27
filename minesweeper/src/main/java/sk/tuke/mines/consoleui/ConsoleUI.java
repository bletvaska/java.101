package sk.tuke.mines.consoleui;

import sk.tuke.mines.core.Clue;
import sk.tuke.mines.core.Mine;
import sk.tuke.mines.core.MineField;
import sk.tuke.mines.core.Tile;

public class ConsoleUI {

    private MineField mineField;

    public ConsoleUI(MineField mineField) {
        this.mineField = mineField;
    }

    public void play() {
        renderField();
    }

    private void renderField() {
        System.out.println(this.mineField.getState());

        for (var row = 0; row < this.mineField.getRowCount(); row++) {
            for (var column = 0; column < this.mineField.getColumnCount(); column++) {
                Tile tile = this.mineField.getTile(row, column);

                switch (tile.getState()) {
                    case OPEN:
                        if (tile instanceof Mine)
                            System.out.print("X");
                        else if (tile instanceof Clue) {
                            Clue clue = ((Clue) tile);
                            System.out.print(clue.getValue());
                        }
                        break;
                    case MARKED:
                        System.out.print("M");
                        break;
                    case CLOSED:
                        System.out.print("-");
                        break;
                }
            }
            System.out.println();
        }
    }
}
