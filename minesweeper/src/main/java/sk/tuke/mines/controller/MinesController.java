package sk.tuke.mines.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.tuke.mines.core.Clue;
import sk.tuke.mines.core.MineField;
import sk.tuke.mines.core.Tile;

@RestController
public class MinesController {
    private MineField mineField = new MineField(9, 9, 10);

    @GetMapping("/act")
    public String act(int row, int column) {
        mineField.openTile(row, column);
        return render();
    }

    @GetMapping("/")
    public String newGame() {
        mineField = new MineField(9, 9, 10);
        return render();
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

        sb.append("</body>\n</html>\n");

        return sb.toString();
    }

    private void renderControls(StringBuilder sb) {
        sb.append("<div class='minesControl'>");
        sb.append("<a href='/'>New Game</a>");
        sb.append("</div>");
    }

    private void renderField(StringBuilder sb) {
        sb.append("<table class='minefield'>\n");
        for (var row = 0; row < this.mineField.getRowCount(); row++) {
            sb.append("<tr>\n");
            for (var column = 0; column < this.mineField.getColumnCount(); column++) {
                var tile = this.mineField.getTile(row, column);
                sb.append("<td class='" + getTileClass(tile) + "'>\n");
                sb.append(String.format("<a href='/act?row=%d&column=%d'>\n", row, column));
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
