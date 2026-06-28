package my_project.view;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

public class BSTKnoten extends GraphicalObject {

    private double x, y;
    private String name;
    private int status; // 0: Normal, 1: Private (Rot), 2: kein Treffer (Blau), 3: Treffer (Grün)
    private double time;
    private double endOfAnimation = 0.5;

    public BSTKnoten(double pX, double pY, String pName) {
        this.x = pX;
        this.y = pY;
        this.name = pName;
        this.status = 0;
        this.time = 0;
    }

    public void update(double dt) {
        if (time < endOfAnimation) {
            time += dt;

            if (time > endOfAnimation) {
                time = endOfAnimation;
            }
        }
    }

    public void draw(DrawTool drawTool) {

        Color statusFarbe = switch (status) {
            case 1 -> new Color(233, 85, 152);       // Rot / Pink
            case 2 -> new Color(86, 204, 121);       // Grün
            default -> new Color(170, 170, 180);     // Grau / Normal
        };

        // Äußerer, schwacher Glow
        drawTool.setCurrentColor(fadeColor(statusFarbe, 45));
        drawTool.drawFilledCircle(x, y, 36);

        // Mittlerer Glow
        drawTool.setCurrentColor(fadeColor(statusFarbe, 85));
        drawTool.drawFilledCircle(x, y, 33);

        // Leuchtender Außenring
        drawTool.setCurrentColor(fadeColor(statusFarbe, 150));
        drawTool.drawFilledCircle(x, y, 30);

        // Dunkler Hintergrund für mehr Kontrast
        drawTool.setCurrentColor(new Color(25, 25, 40, getFadeAlpha(255)));
        drawTool.drawFilledCircle(x, y, 27);

        // Farbiger Innenkreis
        drawTool.setCurrentColor(fadeColor(statusFarbe, 255));
        drawTool.drawFilledCircle(x, y, 24);

        // Heller innerer Rahmen
        drawTool.setCurrentColor(new Color(
                Math.min(statusFarbe.getRed() + 60, 255),
                Math.min(statusFarbe.getGreen() + 60, 255),
                Math.min(statusFarbe.getBlue() + 60, 255),
                getFadeAlpha(220)
            )
        );
        drawTool.drawCircle(x, y, 25);

        // Äußerer Rahmen
        drawTool.setCurrentColor(fadeColor(statusFarbe, 220));
        drawTool.drawCircle(x, y, 30);

        // Text ungefähr zentrieren
        drawTool.setCurrentColor(new Color(255, 255, 255, getFadeAlpha(255)));

        double zeichenBreite = 6.85;
        double textBreite = name.length() * zeichenBreite;

        double textX = x - textBreite / 2;
        double textY = y + 5;

        drawTool.drawText(textX, textY, name);
    }

    private int getFadeAlpha(int maxAlpha) {
        double progress = time / endOfAnimation;

        if (progress > 1) {
            progress = 1;
        }

        if (progress < 0) {
            progress = 0;
        }

        return (int) (maxAlpha * progress);
    }

    private Color fadeColor(Color color, int maxAlpha) {
        return new Color(
                color.getRed(),
                color.getGreen(),
                color.getBlue(),
                getFadeAlpha(maxAlpha)
        );
    }

    public void setStatus(int pStatus) {
        this.status = pStatus;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
