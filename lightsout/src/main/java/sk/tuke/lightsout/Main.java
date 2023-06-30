package sk.tuke.lightsout;

import sk.tuke.lightsout.consoleui.ConsoleUI;
import sk.tuke.lightsout.core.Field;

public class Main {
    public static void main(String[] args) {
        Field field = new Field(5,5,1);
        ConsoleUI ui = new ConsoleUI(field);
        ui.play();
    }
}
