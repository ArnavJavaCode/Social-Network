package my_project.view;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
//import my_project.model.NutzerProfil;

import java.awt.*;

public class BSTKnoten extends GraphicalObject {
    private double x, y;
    //private NutzerProfil profil; anstatt von String name
    private String name;
    private int status; // 0: Normal, 1: Private (Rot), 2: kein Treffer (Blau), 3: Treffer (Grün)

    // Hier muss NutzerProfile profile anstatt von String pName
    public BSTKnoten(double pX, double pY, String pName) {
        this.x = pX;
        this.y = pY;
        //this.profile = pProfile
        this.name = pName;
        this.status = 0;
    }

    public void draw(DrawTool drawTool) {

        // Grundfarbe passend zum Status bestimmen

        Color statusFarbe = switch (status) {
            case 1 -> new Color(233, 85, 152);       // Rot / Pink
            case 2 -> new Color(86, 204, 121);       // Grün
            default -> new Color(170, 170, 180);     // Grau / Normal
        };

// Äußerer, schwacher Glow
        drawTool.setCurrentColor(new Color(
                statusFarbe.getRed(),
                statusFarbe.getGreen(),
                statusFarbe.getBlue(),
                45
        ));
        drawTool.drawFilledCircle(x, y, 36);

// Mittlerer Glow
        drawTool.setCurrentColor(new Color(
                statusFarbe.getRed(),
                statusFarbe.getGreen(),
                statusFarbe.getBlue(),
                85
        ));
        drawTool.drawFilledCircle(x, y, 33);

// Leuchtender Außenring
        drawTool.setCurrentColor(new Color(
                statusFarbe.getRed(),
                statusFarbe.getGreen(),
                statusFarbe.getBlue(),
                150
        ));
        drawTool.drawFilledCircle(x, y, 30);

// Dunkler Hintergrund für mehr Kontrast
        drawTool.setCurrentColor(new Color(25, 25, 40));
        drawTool.drawFilledCircle(x, y, 27);

// Farbiger Innenkreis
        drawTool.setCurrentColor(statusFarbe);
        drawTool.drawFilledCircle(x, y, 24);

// Heller innerer Rahmen
        drawTool.setCurrentColor(new Color(
                Math.min(statusFarbe.getRed() + 60, 255),
                Math.min(statusFarbe.getGreen() + 60, 255),
                Math.min(statusFarbe.getBlue() + 60, 255),
                220
        ));
        drawTool.drawCircle(x, y, 25);

// Äußerer Rahmen
        drawTool.setCurrentColor(new Color(
                statusFarbe.getRed(),
                statusFarbe.getGreen(),
                statusFarbe.getBlue(),
                220
        ));
        drawTool.drawCircle(x, y, 30);


        // Text ungefähr zentrieren
        drawTool.setCurrentColor(Color.WHITE);
        double zeichenBreite = 6.85; // eventuell je nach Schriftart anpassen
        double textBreite = name.length() * zeichenBreite;

        double textX = (x - textBreite / 2);
        double textY = (y + 5);

        drawTool.drawText(textX, textY, name);
    }

    //public NutzerProfil getProfil() { return profil; }
    public void setStatus(int pStatus) { this.status = pStatus; }
    public double getX() { return x; }
    public double getY() { return y; }

}
