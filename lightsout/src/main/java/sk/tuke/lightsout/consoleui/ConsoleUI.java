package sk.tuke.lightsout.consoleui;

import sk.tuke.lightsout.core.Field;

import java.util.Scanner;

// Vytvárame Consolove rozhranie;
public class ConsoleUI {
    private Field field;

    private Scanner scenner;
    private boolean stop;
    private int attempts;

    // vytvorenie construktora so vstupom MainField - hracie pole
    public ConsoleUI(Field mainField) {

        this.field = mainField;
        this.scenner = new Scanner(System.in);
    }


    public void play() {

        this.attempts = 0;
        do {
            renderField();
            processInput();
        }
        while (!this.field.isSolved() && !stop);

        renderField();
    }

    private void processInput() {
        System.out.print("Zadaj prikaz: ");
        //trim odstranenie medzier pokial by niekto zadala vstup s medzerami....
        String[] input = scenner.nextLine().trim().toLowerCase().split(" ");
        //System.out.println(String.format("Prikaz: '%s', '%s'",input[0], input[1]));
        // na mieste % vypsem premennu za ciarkou a 's' ze je to string
        // to lower Case aby keby niekto zadal velke male pismena...
        //parse input
        // switch porovna ci inupt[0] ==  koniec
        switch (input[0]) {
            case "koniec": {
                stop = true;
                break;
            }
            // -1 preto aby som mal cislovane riadky a stlpce od 1
            case "prepni": {
                int row = Integer.valueOf(input[1]) - 1;
                int column = Integer.valueOf(input[2]) - 1;
                if (row >= 0 && row < field.getRowCount() && column >= 0 && column < field.getColumnCount()) {
                    // z podmienky urobili metodu...isInputValid a dat do otvor a oznac
                    this.field.toggleLights(row, column);
                } else
                    System.out.println("Zly vstup");
                break;

            }
            default: {
                System.out.println("Taky príkaz nepoznám.");
                // pri case ak niekto zada vec ktora nieje v case tak default...
            }
        }
    }

    // keby som nechal public static - static metoda ktora patri triede a nepotrebujem spravit instanciu abz som sa k nej dostal
    // v console nebude staticka metoda...
    // renderfiel je sucastou consoloveho rozhrania zrejme si zoberie z private Mainfield
    public void renderField() {
        String header = String.format("attempts: %02d state: %s", this.attempts, this.field.isSolved());
        System.out.println(header);

        for (var row = 0; row < this.field.getRowCount(); row++) {
            for (var column = 0; column < this.field.getColumnCount(); column++) {
                var light = this.field.getLight(row, column);
                System.out.print(light.isOn() ? "📀 " : "💿 ");
            }
            System.out.println();
        }

    }
}


