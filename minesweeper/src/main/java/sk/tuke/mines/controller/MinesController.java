package sk.tuke.mines.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.mines.core.Clue;
import sk.tuke.mines.core.FieldState;
import sk.tuke.mines.core.MineField;
import sk.tuke.mines.core.Tile;

@RestController
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MinesController {
    private MineField mineField;
    private int rowCount;
    private int columnCount;
    private int mineCount;
    private boolean marking;

    public MinesController() {
//        this.marking = false;
        createField(9, 9, 10);
    }

    @GetMapping("/act")
    public String act(int row, int column) {
        if (this.marking) {
            mineField.markTile(row, column);
        } else {
            mineField.openTile(row, column);
        }

        return render();
    }

    @GetMapping("/")
    public String newGame() {
        mineField = new MineField(this.rowCount, this.columnCount, this.mineCount);
        return render();
    }

    @GetMapping("/changeMarking")
    public String changeMarking() {
        this.marking = !this.marking;
        return render();
    }


    @GetMapping("/easy")
    public String newEasyGame() {
        createField(9, 9, 10);
        return render();
    }

    @GetMapping("/medium")
    public String newMediumGame() {
        createField(16, 16, 40);
        return render();
    }

    @GetMapping("/hard")
    public String newHardGame() {
        createField(32, 32, 99);
        return render();
    }

    private void createField(int rowCount, int columnCount, int mineCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.mineCount = mineCount;

        this.mineField = new MineField(this.rowCount, this.columnCount, this.mineCount);
    }


    private String render() {
        var sb = new StringBuilder();

        sb.append("<html>\n");
        sb.append("<head>\n");
        sb.append("<link rel='stylesheet' href='/css/style.css'/>\n");
        sb.append("</head>\n");
        sb.append("<body>\n");

        renderControls(sb);
        renderField(sb);
        renderState(sb);

        sb.append("</body>\n</html>\n");

        return sb.toString();
    }

    private void renderState(StringBuilder sb) {
        switch (this.mineField.getState()) {
            case SOLVED: {
                sb.append("<p><b>Gratulujem. Vyhral si hru.</b></p>");
                break;
            }
            case FAILED: {
                sb.append("<p><b>Je mi to ľúto, ale našiel si mínu.</b></p>");
                break;
            }
            default: {
                // game continues
                break;
            }
        }
    }

    private void renderControls(StringBuilder sb) {
        sb.append("<div class='minesControl'>");
        sb.append("<a href='/'>New Game</a><br>");
        sb.append("<a href='/easy'>Easy</a><br>");
        sb.append("<a href='/medium'>Medium</a><br>");
        sb.append("<a href='/hard'>Hard</a><br>");

        String text = this.marking ? "Mark" : "Open";
        sb.append("<a href='/changeMarking'>Current state: " + text + "</a>");
        sb.append("</div>");
    }

    private void renderField(StringBuilder sb) {
        sb.append("<table class='minefield'>\n");
        for (var row = 0; row < this.mineField.getRowCount(); row++) {
            sb.append("<tr>\n");
            for (var column = 0; column < this.mineField.getColumnCount(); column++) {
                var tile = this.mineField.getTile(row, column);
                sb.append("<td class='" + getTileClass(tile) + "'>\n");
                if (this.mineField.getState() == FieldState.PLAYING) {
                    sb.append(String.format("<a href='/act?row=%d&column=%d'>\n", row, column));
                }
                sb.append("<span>" + getTileText(tile) + "</span>");
                sb.append("</a>\n");
                sb.append("</td>\n");
            }
            sb.append("</tr>\n");
        }
        sb.append("</table>\n");
    }

    public String getTileClass(Tile tile) {
        switch (tile.getState()) {
            case CLOSED:
                return "closed";
            case MARKED:
                return "marked";
            case OPEN:
                if (tile instanceof Clue)
                    return "open" + String.valueOf(((Clue) tile).getValue());
                else
                    return "mine";
            default:
                throw new IllegalArgumentException("Not valid tile state" + tile.getState());
        }
    }

    public String getTileText(Tile tile) {
        switch (tile.getState()) {
            case CLOSED:
                return "-";
            case MARKED:
                return "M";
            case OPEN:
                if (tile instanceof Clue)
                    return String.valueOf(((Clue) tile).getValue());
                else
                    return "X";
            default:
                throw new IllegalArgumentException("Not valid tile state" + tile.getState());
        }
    }
}
