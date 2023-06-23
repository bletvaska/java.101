package sk.tuke.mines.core;

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
        tiles = new Tile[rowCount][columnCount];
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
}
