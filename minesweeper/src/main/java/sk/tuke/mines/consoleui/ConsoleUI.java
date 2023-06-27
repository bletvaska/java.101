package sk.tuke.mines.consoleui;

import sk.tuke.mines.core.*;

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

        do {
            renderField();
            processInput();
        } while (this.isPlaying && this.mineField.getState() == FieldState.PLAYING);

        renderField();
    }

    private void processInput() {
        // prompt, read and normalize input
        System.out.print("Zadaj príkaz: ");
        String[] input = scanner.nextLine().trim().toLowerCase().split(" ");

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
                }
                break;
            }

            default: {
                System.out.println("Taký príkaz nepoznám.");
            }
        }
    }

    private boolean isInputValid(int row, int column) {
        if ((row >= 0 && row < this.mineField.getRowCount())
                && (column >= 0 && column < this.mineField.getColumnCount())
        ) {
            return true;
        }

        // else
        System.out.println("Zlý vstup.");
        return false;
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
