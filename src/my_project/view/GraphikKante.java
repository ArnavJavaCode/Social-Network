package my_project.view;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

public class GraphikKante extends GraphicalObject {
    private GraphikKnoten start, ende;

    public GraphikKante(GraphikKnoten pStart, GraphikKnoten pEnde) {
        this.start = pStart;
        this.ende = pEnde;
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(255, 0, 0));
        drawTool.drawLine(start.getX(), start.getY(), ende.getX(), ende.getY());
    }
}
