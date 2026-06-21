package my_project.view;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

public class BSTKante extends GraphicalObject {
    private BSTKnoten start, ende;

    public BSTKante(BSTKnoten pStart, BSTKnoten pEnde) {
        this.start = pStart;
        this.ende = pEnde;
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(255, 255, 255));
        drawTool.drawLine(start.getX(), start.getY(), ende.getX(), ende.getY());
    }
}