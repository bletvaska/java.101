package sk.tuke.mines.core;

import java.util.Random;

public class MineField {
    private final int rowCount;

    private final int columnCount;

    private final int mineCount;

    private FieldState state = FieldState.PLAYING;

    private Tile[][] tiles;

    public MineField(int rowCount, int columnCount, int mineCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.mineCount = mineCount;
        this.tiles = new Tile[rowCount][columnCount];
        generate();
    }

    private void generate() {
        generateMines();
        fillWithClues();
    }

    private void generateMines() {
        Random random = new Random();

        for (int counter = 0; counter < this.mineCount; counter++) {
            int row = random.nextInt(rowCount);
            var column = random.nextInt(columnCount);

            while (tiles[row][column] instanceof Mine) {
                row = random.nextInt(rowCount);
                column = random.nextInt(columnCount);
            }

            tiles[row][column] = new Mine();
        }
    }

    private void fillWithClues() {

    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public int getMineCount() {
        return mineCount;
    }

    public FieldState getState() {
        return state;
    }

    public Tile getTile(int row, int column) {
        return tiles[row][column];
    }
}
