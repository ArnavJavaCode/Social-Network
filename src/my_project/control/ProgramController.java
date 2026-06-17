package my_project.control;

import KAGO_framework.control.ViewController;
import my_project.model.BST;
import my_project.model.Graphen;
import my_project.view.BSTKnoten;
import my_project.view.GraphikKante;
import my_project.view.GraphikKnoten;

import java.awt.event.MouseEvent;

/**
 * Ein Objekt der Klasse ProgramController dient dazu das Programm zu steuern. Die updateProgram - Methode wird
 * mit jeder Frame im laufenden Programm aufgerufen.
 */
public class ProgramController {

    //Attribute


    // Referenzen
    private BST bst;
    private Graphen graphen;
    private BSTKnoten bstDarstellen;
    private GraphikKnoten graphikKnoten1 = new GraphikKnoten(500,500, "Bring");
    private GraphikKnoten graphikKnoten2 = new GraphikKnoten(300,300, "Dring");
    private GraphikKante graphikKante = new GraphikKante(graphikKnoten1, graphikKnoten2);
    private ViewController viewController;



    public ProgramController(ViewController viewcontroller) {
        this.viewController = viewcontroller;
    }


    /**
     * Diese Methode wird genau ein mal nach Programmstart aufgerufen. Achtung: funktioniert nicht im Szenario-Modus
     */
    public void startProgram() {

    }

    /**
     * Sorgt dafür, dass zunächst gewartet wird, damit der SoundController die
     * Initialisierung abschließen kann. Die Wartezeit ist fest und damit nicht ganz sauber
     * implementiert, aber dafür funktioniert das Programm auch bei falscher Java-Version
     * @param dt Zeit seit letzter Frame
     */
    public void updateProgram(double dt){
        graphikKnoten1.setStatus(2);
        graphikKnoten2.setStatus(1);
        viewController.draw(graphikKante);
        viewController.draw(graphikKnoten1);
        viewController.draw(graphikKnoten2);

    }


    /**
     * Verarbeitet einen Mausklick.
     * @param e das Objekt enthält alle Informationen zum Klick
     */
    public void mouseClicked(MouseEvent e){

    }
}
