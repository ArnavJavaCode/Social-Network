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
        // Farbe basierend auf Status wählen
        if (status == 1) drawTool.setCurrentColor(new Color(233, 85, 152));     // Rot
        else if (status == 2) drawTool.setCurrentColor(new Color(86, 204, 121));// Grün
        else if (status == 3) drawTool.setCurrentColor(new Color(87, 177, 199));// Blau
        else drawTool.setCurrentColor(new Color(207, 207, 207)); // Grau/Normal

        drawTool.drawFilledCircle(x, y, 25);
        drawTool.drawCircle(x, y, 30);

        drawTool.setCurrentColor(Color.WHITE);
        drawTool.drawCircle(x, y, 26);
        drawTool.drawCircle(x, y, 30);

        drawTool.setCurrentColor(Color.WHITE);
        drawTool.drawCircle(x, y, 26);
        drawTool.drawCircle(x, y, 30);

        // Rahmen und Text
        drawTool.setCurrentColor(new Color(0, 0, 0));
        drawTool.drawText(x - 14, y + 5, name);
    }

    //public NutzerProfil getProfil() { return profil; }
    public void setStatus(int pStatus) { this.status = pStatus; }
    public double getX() { return x; }
    public double getY() { return y; }

}
