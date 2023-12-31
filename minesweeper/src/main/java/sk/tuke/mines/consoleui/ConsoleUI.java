package sk.tuke.mines.consoleui;

import sk.tuke.mines.core.*;

import java.util.Scanner;

public class ConsoleUI {

    private MineField mineField;
    private Scanner scanner;
    private boolean isPlaying;
    private int attempts;

    public ConsoleUI(MineField mineField) {
        this.mineField = mineField;
        this.scanner = new Scanner(System.in);
    }

    public void play() {
        this.isPlaying = true;
        this.attempts = 0;

        do {
            renderField();
            processInput();
        } while (this.isPlaying && this.mineField.getState() == FieldState.PLAYING);

        renderField();
    }

    private void processInput() {
        // prompt, read and normalize input
        System.out.print("Zadaj príkaz: ");
        var input = scanner.nextLine().trim().toLowerCase().split(" ");

        // parse input
        switch (input[0]) {
            case "koniec": {
                this.isPlaying = false;
                break;
            }

            case "oznac": {
                int row = Integer.valueOf(input[1]) - 1;
                int column = Integer.valueOf(input[2]) - 1;
                if (isInputValid(row, column)) {
                    this.mineField.markTile(row, column);
                }

                break;
            }

            case "otvor": {
                int row = Integer.valueOf(input[1]) - 1;
                int column = Integer.valueOf(input[2]) - 1;
                if (isInputValid(row, column)) {
                    this.mineField.openTile(row, column);
                    this.attempts++;
                }
                break;
            }

            default: {
                System.out.println("Taký príkaz nepoznám.");
            }
        }
    }

    private boolean isInputValid(int row, int column) {
        boolean isValid = true;

        // check valid row
        if (!(row >= 0 && row < this.mineField.getRowCount())) {
            System.out.println("Nesprávny riadok.");
            return false;
        }

        // check valid column
        if (!(column >= 0 && column < this.mineField.getColumnCount())) {
            isValid = false;
            System.out.println("Nesprávny stĺpec.");
            return false;
        }

        return isValid;
    }

    private void renderField() {
        // render header
        var header = String.format(
                "attempts: %02d state: %s",
                this.attempts, this.mineField.getState()
        );
        System.out.println(header);

        // render rows and columns
        for (var row = 0; row < this.mineField.getRowCount(); row++) {
            for (var column = 0; column < this.mineField.getColumnCount(); column++) {
                var tile = this.mineField.getTile(row, column);
                renderTile(tile);
            }
            System.out.println();
        }
    }

    private static void renderTile(Tile tile) {
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
}
