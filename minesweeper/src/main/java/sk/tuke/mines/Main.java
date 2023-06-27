package sk.tuke.mines;

import sk.tuke.mines.consoleui.ConsoleUI;
import sk.tuke.mines.core.*;

public class Main {
    public static void main(String[] args) {
        MineField field = new MineField(4, 8, 1);
        ConsoleUI ui = new ConsoleUI(field);
        ui.play();
    }
}
