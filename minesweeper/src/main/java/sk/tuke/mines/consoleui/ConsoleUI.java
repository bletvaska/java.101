package sk.tuke.mines.consoleui;

import sk.tuke.mines.core.Clue;
import sk.tuke.mines.core.Mine;
import sk.tuke.mines.core.MineField;
import sk.tuke.mines.core.Tile;

import java.util.Scanner;

public class ConsoleUI {

    private MineField mineField;
    private Scanner scanner;
    private boolean isPlaying;

    public ConsoleUI(MineField mineField) {
        this.mineField = mineField;
        this.scanner = new Scanner(System.in);
    }

    public void play() {
        this.isPlaying = true;

        do{
            renderField();
            processInput();
        }while(this.isPlaying);
    }

    private void processInput() {
        // prompt, read and normalize input
        System.out.print("Zadaj príkaz: ");
        String[] input = scanner.nextLine().trim().toLowerCase().split(" ");

        // parse input
        switch(input[0]){
            case "koniec":
                this.isPlaying = false;
                break;

            default:
                System.out.println("Taký príkaz nepoznám.");
        }

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
