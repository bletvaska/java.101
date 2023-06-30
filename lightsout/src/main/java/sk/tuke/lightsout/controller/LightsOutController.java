package sk.tuke.lightsout.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.lightsout.core.Field;
import sk.tuke.lightsout.core.Light;

@RestController
@Scope(WebApplicationContext.SCOPE_SESSION)
public class LightsOutController {
    private Field field;

    @GetMapping("/")
    public String index() {
        this.field = new Field(5, 4, 1);
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
        renderState(sb);

        sb.append("</body>\n</html>\n");

        return sb.toString();
    }

    private void renderControls(StringBuilder sb) {
        sb.append("<div class='minesControl'>");
        sb.append("<a href='/'>New Game</a><br>");
//        sb.append("<a href='/easy'>Easy</a><br>");
//        sb.append("<a href='/medium'>Medium</a><br>");
//        sb.append("<a href='/hard'>Hard</a><br>");
        sb.append("</div>");
    }

    private void renderState(StringBuilder sb) {
        if (this.field.isSolved()) {
            sb.append("<p><b>Gratulujem. Vyhral si hru.</b></p>");
        }
    }

    @GetMapping("/act")
    public String act(int row, int column) {
        this.field.toggleLights(row, column);
        return render();
    }

    private void renderField(StringBuilder sb) {
        sb.append("<table class='minefield'>\n");
        for (var row = 0; row < this.field.getRowCount(); row++) {
            sb.append("<tr>\n");
            for (var column = 0; column < this.field.getColumnCount(); column++) {
                var light = this.field.getLight(row, column);
                sb.append("<td class='" + getLightClass(light) + "'>\n");
                if (!this.field.isSolved()) {
                    String link = String.format("<a href='/act?row=%d&column=%d'></a>\n", row, column);
                    sb.append(link);
                }
                sb.append("</td>\n");
            }
            sb.append("</tr>\n");
        }
        sb.append("</table>\n");
    }

    private String getLightClass(Light light) {
        if (light.isOn()) {
            return "open";
        }
        return "closed";
    }
}
