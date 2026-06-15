package my_project.view;

import KAGO_framework.model.GraphicalObject;

public class BSTDarstellen {

    public GraphicalObject BSTKreis(int x, int y) {
        GraphicalObject kreis = new GraphicalObject("src/my_project/resources/images/circle.png");
        kreis.setX(x);
        kreis.setY(y);
        return kreis;
    }
}
