package my_project.view;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
//import my_project.model.NutzerProfil;

import java.awt.*;

public class ShowProfile extends GraphicalObject {

    //private NutzerProfil aktuellesProfil;
    private double breite;
    private double hoehe;

    public ShowProfile(double pX, double pY) {
        // x und y bestimmen die obere linke Ecke der Profilkarte
        this.x = pX;
        this.y = pY;
        this.breite = 300;
        this.hoehe = 200;
        //this.aktuellesProfil = null; // Startet unsichtbar/leer
    }

    /*
    @Override
    public void draw(DrawTool drawTool) {
        // Wenn kein Profil gesetzt ist, zeichnen wir gar nichts
        if (aktuellesProfil == null) {
            return;
        }

        // 1. Hintergrund-Box (Die Karte)
        drawTool.setCurrentColor(new Color(245, 245, 245)); // Helles Grau
        drawTool.drawFilledRectangle(x, y, breite, hoehe);

        // 2. Rahmen und Header-Linie
        drawTool.setCurrentColor(Color.BLACK);
        drawTool.drawRectangle(x, y, breite, hoehe);
        drawTool.drawLine(x, y + 40, x + breite, y + 40); // Trennlinie für den Namen

        // 3. Überschrift / Name (Groß und fettgedruckt im oberen Bereich)
        drawTool.setCurrentColor(new Color(40, 40, 40));
        drawTool.drawText(x + 15, y + 25, "Profil: " + aktuellesProfil.getNutzername());

        // 4. Details anzeigen (Alter, Hobby, Status)
        drawTool.setCurrentColor(Color.DARK_GRAY);
        drawTool.drawText(x + 15, y + 70, "Alter: " + aktuellesProfil.getAlter() + " Jahre");
        drawTool.drawText(x + 15, y + 105, "Hobby: " + aktuellesProfil.getHobby());

        // Zeile 3: Privatsphäre-Status
        String statusText = aktuellesProfil.isPrivate() ? "Privates Profil" : "Öffentliches Profil";

        if (aktuellesProfil.isPrivate()) {
            drawTool.setCurrentColor(new Color(154, 7, 82)); // Dunkelrot für privat
        } else {
            drawTool.setCurrentColor(new Color(3, 106, 32));  // Grün für öffentlich
        }

        drawTool.drawText(x + 15, y + 140, "Status: " + statusText);

        // 5. Schließen-Hinweis für den Benutzer
        drawTool.setCurrentColor(Color.GRAY);
        drawTool.drawText(x + 15, y + 185, "[Klicke irgendwo, um zu schließen]");
    }


     // Setzt das anzuzeigende Profil und macht die Karte sichtbar.

    public void zeigeProfil(NutzerProfil pProfil) {
        this.aktuellesProfil = pProfil;
    }


     // Schließt die Ansicht (macht sie unsichtbar).

    public void schliesseAnsicht() {
        this.aktuellesProfil = null;
    }


     // Prüft, ob die Ansicht gerade für den Benutzer sichtbar ist.

    public boolean istSichtbar() {
        return this.aktuellesProfil != null;
    }

     */
}
