package my_project.control;

import KAGO_framework.model.abitur.datenstrukturen.BinarySearchTree;
import KAGO_framework.model.abitur.datenstrukturen.Graph;
import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.model.abitur.datenstrukturen.Vertex;
import KAGO_framework.model.abitur.datenstrukturen.Edge;

public class LogikTester {

    // Dies ist der Startpunkt, wenn du auf "Play" drückst
    public static void main(String[] args) {
        System.out.println("=== TEST 1: BAUM-SUCHE ===");
        testBaum();

        System.out.println("\n=== TEST 2: GRAPHEN-SUCHE ===");
        testGraph();
    }

    private static void generiereZufallsNetzwerk(BinarySearchTree<NutzerProfil> baum, Graph netzwerk) {
        // Unsere Dummy-Daten
        String[] namen = {"Alice", "Bob", "Charlie", "David", "Emma", "Fiona", "Gabriel", "Hannah", "Ian", "Julia"};
        String[] hobbys = {"Gaming", "Sport", "Musik", "Lesen", "Kunst"};

        // Array, um uns die erstellten Knoten für die Verknüpfungen zu merken
        Vertex[] knotenListe = new Vertex[namen.length];

        System.out.println("--- GENERIERE NETZWERK ---");

        // 1. Profile erstellen und in Baum & Graph einfügen
        for (int i = 0; i < namen.length; i++) {
            // Zufälliges Hobby auswählen
            int hobbyIndex = (int) (Math.random() * hobbys.length);
            // Ca. 20% Chance, dass das Profil privat ist
            boolean isPrivat = Math.random() < 0.2;

            NutzerProfil neuesProfil = new NutzerProfil(namen[i], hobbys[hobbyIndex], isPrivat);

            // In den Baum einfügen
            baum.insert(neuesProfil);

            // Als Knoten in den Graphen einfügen
            knotenListe[i] = new Vertex(namen[i]);
            netzwerk.addVertex(knotenListe[i]);

            System.out.println("Erstellt: " + namen[i] + " | Hobby: " + hobbys[hobbyIndex] + " | Privat: " + isPrivat);
        }

        // 2. Zufällige Freundschaften (Kanten) erstellen
        for (int i = 0; i < knotenListe.length; i++) {
            // Jeder bekommt ca. 1-2 zufällige Freunde
            int anzahlFreunde = (int) (Math.random() * 2) + 1;
            for (int f = 0; f < anzahlFreunde; f++) {
                int zufallsFreund = (int) (Math.random() * knotenListe.length);

                // Man kann nicht mit sich selbst befreundet sein
                if (i != zufallsFreund) {
                    netzwerk.addEdge(new Edge(knotenListe[i], knotenListe[zufallsFreund], 1));
                }
            }
        }
        System.out.println("Freundschaften wurden zufällig verknüpft!\n");
    }

    private static void testBaum() {
        // 1. Leeren Baum erstellen
        BinarySearchTree<NutzerProfil> baum = new BinarySearchTree<>();

        // 2. Test-Daten einfügen (Gemischt: öffentlich und privat)
        baum.insert(new NutzerProfil("Alice", "Gaming", false));
        baum.insert(new NutzerProfil("Bob", "Sport", false));
        baum.insert(new NutzerProfil("Charlie", "Gaming", true)); // Privat! Darf nicht gefunden werden
        baum.insert(new NutzerProfil("David", "Gaming", false));

        // 3. Deine Logik-Klasse nutzen
        BaumSucher sucher = new BaumSucher();
        List<NutzerProfil> ergebnis = sucher.sucheNachHobby(baum, "Gaming");

        // 4. Ergebnis auf der Konsole ausgeben
        System.out.println("Suche nach 'Gaming' in öffentlichen Profilen:");
        ergebnis.toFirst();
        while(ergebnis.hasAccess()) {
            System.out.println("- Gefunden: " + ergebnis.getContent().getNutzername());
            ergebnis.next();
        }
    }

    private static void testGraph() {
        // 1. Graph und Knoten (Vertices) erstellen
        Graph netzwerk = new Graph();
        Vertex v1 = new Vertex("Alice");
        Vertex v2 = new Vertex("Bob");
        Vertex v3 = new Vertex("Charlie");
        Vertex v4 = new Vertex("David");

        netzwerk.addVertex(v1);
        netzwerk.addVertex(v2);
        netzwerk.addVertex(v3);
        netzwerk.addVertex(v4);

        // 2. Kanten (Freundschaften) hinzufügen (Gewicht ist hier einfach 1)
        netzwerk.addEdge(new Edge(v1, v2, 1)); // Alice ist befreundet mit Bob
        netzwerk.addEdge(new Edge(v2, v3, 1)); // Bob ist befreundet mit Charlie
        // David ist mit niemandem befreundet

        // 3. Deine Breitensuche testen
        BreitensucheLogik bfs = new BreitensucheLogik();
        bfs.initialisiereSuche(netzwerk, v1); // Wir starten bei Alice

        System.out.println("Starte schrittweise Breitensuche ab Alice:");

        // Wir simulieren hier den Timer von Person 3, indem wir die Schleife per Hand durchlaufen
        while(bfs.isSucheAktiv()) {
            Vertex aktuell = bfs.macheEinenSchritt();
            if (aktuell != null) {
                System.out.println("- Aktuell untersuchter Knoten: " + aktuell.getID());
            }
        }
    }
}