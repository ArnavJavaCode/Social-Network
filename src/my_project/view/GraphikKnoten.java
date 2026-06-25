package my_project.view;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

public class GraphikKnoten extends GraphicalObject {
    private double x, y;
    private String name;
    private int status; // 0: Normal, 1: Private (Rot), 2: Treffer (Grün), 3: kein Treffer (Blau)

    public GraphikKnoten(double pX, double pY, String pName) {
        this.x = pX;
        this.y = pY;
        this.name = pName;
        this.status = 0;
    }

    @Override
    public void draw(DrawTool drawTool) {
        // Farbe basierend auf Status wählen
        if (status == 1) drawTool.setCurrentColor(new Color(231, 85, 152));     // Rot
        else if (status == 2) drawTool.setCurrentColor(new Color(86, 204, 121));// Grün
        else if (status == 3) drawTool.setCurrentColor(new Color(87, 177, 199));// Blau
        else drawTool.setCurrentColor(new Color(207, 207, 207)); // Grau/Normal

        drawTool.drawFilledCircle(x, y, 27);
        drawTool.drawCircle(x, y, 32);

        drawTool.setCurrentColor(Color.WHITE);
        drawTool.drawCircle(x, y, 28);


        // Rahmen und Text
        drawTool.setCurrentColor(new Color(0, 0, 0));
        drawTool.drawText(x - 18, y + 5, name);
    }

    public void setStatus(int pStatus) { this.status = pStatus; }
    public int getStatus() {return this.status;}
    public double getX() { return x; }
    public double getY() { return y; }

    public String getName() {
        return name;
    }
}



