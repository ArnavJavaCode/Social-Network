package my_project.view;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

public class GraphikKnoten extends GraphicalObject {
    private double x, y;
    private String name;
    private int status; // 0: Normal, 1: Private (Rot), 2: Treffer (Blau)

    public GraphikKnoten(double pX, double pY, String pName) {
        this.x = pX;
        this.y = pY;
        this.name = pName;
        this.status = 0;
    }

    @Override
    public void draw(DrawTool drawTool) {
        // Farbe basierend auf Status wählen
        if (status == 1) drawTool.setCurrentColor(new Color(154, 7, 82)); // Gelb
        else if (status == 2) drawTool.setCurrentColor(new Color(5, 210, 193)); // Grün
        else drawTool.setCurrentColor(new Color(200, 200, 200)); // Grau/Normal

        drawTool.drawCircle(x, y, 25);

        // Rahmen und Text
        drawTool.setCurrentColor(new Color(255, 255, 255));
        drawTool.drawText(x - 10, y + 5, name);
    }

    public void setStatus(int pStatus) { this.status = pStatus; }
    public double getX() { return x; }
    public double getY() { return y; }
}


class GraphikKante extends GraphicalObject {
    private GraphikKnoten start, ende;

    public GraphikKante(GraphikKnoten pStart, GraphikKnoten pEnde) {
        this.start = pStart;
        this.ende = pEnde;
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(255, 255, 255));
        drawTool.drawLine(start.getX(), start.getY(), ende.getX(), ende.getY());
    }
}
