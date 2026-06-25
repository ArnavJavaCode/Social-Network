package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.*;
import my_project.view.*;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.security.Key;

/**
 * Ein Objekt der Klasse ProgramController dient dazu das Programm zu steuern. Die updateProgram - Methode wird
 * mit jeder Frame im laufenden Programm aufgerufen.
 */
public class ProgramController {


    // Referenzen
    private ViewController viewController;
    private BinarySearchTree<NutzerProfil> bst;

    private BaumSucher baumSucher;
    private BreitensucheLogik bsl;
    private ShowProfile showtreffer;

    private Graph socialGraph;
    private BinarySearchTree socialTree;
    private List<GraphikKnoten> graphikKnoten;
    private List<GraphikKante> graphikKanten;
    private List<GraphikKnoten>  erkanteGraphen;
    private List<ShowProfile> offeneFenster;
    private List<String> AlleHobbys;
    private NutzerProfil jim;
    private NutzerProfil dan;
    private NutzerProfil lucy;
    private NutzerProfil nathan;

    int[] manuelleXPositionen = {200, 400, 600, 400};
    int[] manuelleYPositionen = {300, 300, 300, 500};
    private boolean isDialogOffen = false;
    private boolean tasteGedrueckt = false;
    private boolean hobbyexistenz = false;
    private boolean profilesoffen = false;
    public ProgramController(ViewController viewcontroller) {
        this.viewController = viewcontroller;

        bst = new BinarySearchTree<>();
        socialGraph = new Graph();
        socialTree = new BinarySearchTree<>();

        baumSucher = new BaumSucher();
        bsl = new BreitensucheLogik();

        graphikKnoten = new List<>();
        graphikKanten = new List<>();
        AlleHobbys = new List<>();
        offeneFenster = new List<>();
        erkanteGraphen = new List<>();
    }

    private GraphikKnoten findeGraphikKnoten(String id)
    {
        graphikKnoten.toFirst();

        while(graphikKnoten.hasAccess())
        {
            GraphikKnoten g = graphikKnoten.getContent();

            if(g.getName() != null && g.getName().equals(id))
            {
                return g;
            }

            graphikKnoten.next();
        }

        return null;
    }

    public void visualisiereGraph()
    {
        List<Vertex> knoten = socialGraph.getVertices();
        knoten.toFirst();

        int index = 0;

        while(knoten.hasAccess())
        {
            Vertex v = knoten.getContent();

            int x = 200;
            int y = 300;

            if (index < manuelleXPositionen.length) {
                x = manuelleXPositionen[index];
            }

            if (index < manuelleYPositionen.length) {
                y = manuelleYPositionen[index];
            }

            GraphikKnoten g = new GraphikKnoten(x, y, v.getID());
            graphikKnoten.append(g);


            index++;
            knoten.next();
        }

        visualisiereKanten();
    }

    private void visualisiereKanten()
    {

        if (graphikKanten != null) {
            graphikKanten.toFirst();
            while (graphikKanten.hasAccess()) {
                viewController.removeDrawable(graphikKanten.getContent());
                graphikKanten.next();
            }
        }
        graphikKanten = new List<GraphikKante>();

        List<Edge> kanten = socialGraph.getEdges();
        kanten.toFirst();

        while(kanten.hasAccess())
        {
            Edge e = kanten.getContent();
            Vertex start = e.getVertices()[0];
            Vertex ziel = e.getVertices()[1];

            GraphikKnoten startG = findeGraphikKnoten(start.getID());
            GraphikKnoten zielG = findeGraphikKnoten(ziel.getID());

            if (startG != null && zielG != null)
            {
                GraphikKante gk = new GraphikKante(startG, zielG);
                graphikKanten.append(gk);
                viewController.draw(gk);
            }

            kanten.next();
        }

        if (graphikKnoten != null) {
            graphikKnoten.toFirst();
            while (graphikKnoten.hasAccess()) {
                viewController.draw(graphikKnoten.getContent());
                graphikKnoten.next();
            }
        }
    }

    public void visualisiereSuchbaum() {
        if (bst != null && !bst.isEmpty()) {
            zeichneSuchbaumRekursiv(bst, 1000, 300, 180);
        }
    }


    private void zeichneSuchbaumRekursiv(BinarySearchTree<NutzerProfil> baum, int x, int y, int abstand) {
        if (baum == null || baum.isEmpty()) return;

        String name = baum.getContent().getNutzername();
        BSTKnoten aktuellerKnoten = new BSTKnoten(x, y, name);
        viewController.draw(aktuellerKnoten);

        if (!baum.getLeftTree().isEmpty()) {
            BSTKnoten kindLinks = new BSTKnoten(x - abstand, y + 80, baum.getLeftTree().getContent().getNutzername());
            viewController.draw(new BSTKante(aktuellerKnoten, kindLinks));
            zeichneSuchbaumRekursiv(baum.getLeftTree(), x - abstand, y + 80, abstand / 2);
        }

        if (!baum.getRightTree().isEmpty()) {
            BSTKnoten kindRechts = new BSTKnoten(x + abstand, y + 80, baum.getRightTree().getContent().getNutzername());
            viewController.draw(new BSTKante(aktuellerKnoten, kindRechts));
            zeichneSuchbaumRekursiv(baum.getRightTree(), x + abstand, y + 80, abstand / 2);
        }
    }


    public void sucheHobbyImSuchbaum(String hobby) {
        List<NutzerProfil> treffer = baumSucher.sucheNachHobby(bst, hobby);
        treffer.toFirst();
        int xpos = -100;
        while (treffer.hasAccess()) {
            NutzerProfil profil = treffer.getContent();
            xpos+=270;
            showtreffer = new ShowProfile(xpos, 40, profil);
            offeneFenster.append(showtreffer);
            GraphikKnoten gk = findeGraphikKnoten(profil.getNutzername());
            erkanteGraphen.append(gk);

            if (gk != null) {
                profilesoffen = true;
                gk.setStatus(2);
                viewController.draw(showtreffer);
            }

            treffer.next();
        }
    }


    /**
     * Diese Methode wird genau ein mal nach Programmstart aufgerufen. Achtung: funktioniert nicht im Szenario-Modus
     */
    public void startProgram() {
        jim = new NutzerProfil("Jimmy", "Klavier", false);
        dan = new NutzerProfil("Danskie", "Programmieren", false);
        lucy = new NutzerProfil("lucy", "Klavier", false);
        nathan = new NutzerProfil("Nathan", "Fußball", false);

        AlleHobbys.append(jim.getHobby());
        AlleHobbys.append(dan.getHobby());
        AlleHobbys.append(nathan.getHobby());

        bst.insert(dan);
        bst.insert(jim);
        bst.insert(lucy);
        bst.insert(nathan);


        Vertex jimV = new Vertex(jim.getNutzername());
        Vertex danV = new Vertex(dan.getNutzername());
        Vertex lucyV = new Vertex(lucy.getNutzername());
        Vertex nathanV = new Vertex(nathan.getNutzername());

        socialGraph.addVertex(danV);
        socialGraph.addVertex(jimV);
        socialGraph.addVertex(lucyV);
        socialGraph.addVertex(nathanV);


        socialGraph.addEdge(new Edge(jimV, danV, 1));
        socialGraph.addEdge(new Edge(jimV, lucyV, 1));
        socialGraph.addEdge(new Edge(danV, lucyV, 1));
        socialGraph.addEdge(new Edge(nathanV, lucyV, 1));
        socialGraph.addEdge(new Edge(danV, nathanV, 1));

        visualisiereGraph();
        visualisiereSuchbaum();


    }

    /**
     * Sorgt dafür, dass zunächst gewartet wird, damit der SoundController die
     * Initialisierung abschließen kann. Die Wartezeit ist fest und damit nicht ganz sauber
     * implementiert, aber dafür funktioniert das Programm auch bei falscher Java-Version
     * @param dt Zeit seit letzter Frame
     */
    public void updateProgram(double dt){
        if(viewController.isKeyDown(KeyEvent.VK_SPACE)) {
            if (!tasteGedrueckt && !isDialogOffen && offeneFenster.isEmpty()) {
                tasteGedrueckt = true;
                isDialogOffen = true;
                hobbyexistenz = false;

                String infotext = "Nach welchem Hobby wollen sie suchen?\nVerfügbare Hobbys: ";
                AlleHobbys.toFirst();
                AlleHobbys.toFirst();
                while (AlleHobbys.hasAccess()) {
                    infotext += AlleHobbys.getContent();
                    AlleHobbys.next();

                    if (AlleHobbys.hasAccess()) {
                        infotext += ", ";
                    }
                }
                String eingabe = JOptionPane.showInputDialog(null, infotext);
                isDialogOffen = false;
                AlleHobbys.toFirst();

                while(AlleHobbys.hasAccess()) {
                    if(eingabe.equals(AlleHobbys.getContent())) {
                        hobbyexistenz = true;
                        break;
                    }
                    else {
                        hobbyexistenz = false;
                    }
                    AlleHobbys.next();
                }
                if (eingabe != null && !eingabe.isEmpty() && hobbyexistenz) {
                    sucheHobbyImSuchbaum(eingabe);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Eingabe geht nicht!");
                }

            }
        }
        else {
            tasteGedrueckt = false;
        }
    }


    /**
     * Verarbeitet einen Mausklick.
     * @param e das Objekt enthält alle Informationen zum Klick
     */
    public void mouseClicked(MouseEvent e){
        if (!offeneFenster.isEmpty()) {
            profilesoffen = false;
            erkanteGraphen.toFirst();
            while (erkanteGraphen.hasAccess()) {
                erkanteGraphen.getContent().setStatus(4);
                erkanteGraphen.next();
            }
            offeneFenster.toFirst();

            while (offeneFenster.hasAccess()) {
                ShowProfile aktuellesFenster = offeneFenster.getContent();

                viewController.removeDrawable(aktuellesFenster);

                offeneFenster.next();
            }

            offeneFenster.toFirst();
            while (!offeneFenster.isEmpty()) {
                offeneFenster.remove();
            }

        }
    }
}
