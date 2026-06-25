package my_project.control;

import KAGO_framework.model.abitur.datenstrukturen.Graph;
import KAGO_framework.model.abitur.datenstrukturen.Vertex;
import KAGO_framework.model.abitur.datenstrukturen.Queue;
import KAGO_framework.model.abitur.datenstrukturen.List;

public class BreitensucheLogik {


    // Globale Attribute (Das Gedächtnis der Klasse)
    private Graph meinGraph;
    private Queue<Vertex> queue;
    private boolean sucheAktiv;


     // SCHRITT 1: Bereitet die Suche vor.
     // Wird vom Controller nur EINMAL am Anfang aufgerufen.

     public void initialisiereSuche(Graph pGraph, Vertex pStartKnoten) {

        this.meinGraph = pGraph;
        this.queue = new Queue<Vertex>();

        // Alle Knoten im Graphen auf "nicht besucht" (false) setzen
        this.meinGraph.setAllVertexMarks(false);

        // Startknoten markieren und in die Warteschlange einreihen
        pStartKnoten.setMark(true);
        this.queue.enqueue(pStartKnoten);

        this.sucheAktiv = true;
    }


     // SCHRITT 2: Führt exakt EINE Stufe der Breitensuche aus.
     // Wird vom Controller im Timer (z.B. alle 0.5 Sekunden) aufgerufen.
     // Gibt den Knoten zurück, der gerade verarbeitet wurde.

    public Vertex macheEinenSchritt() {
        // Wenn wir fertig sind oder die Suche nie gestartet wurde, brechen wir ab
        if (!this.sucheAktiv || this.queue.isEmpty()) {
            this.sucheAktiv = false;
            return null; // Null signalisiert: "Suche beendet"
        }

        // 1. Den vordersten Knoten aus der Schlange holen
        Vertex aktuellerKnoten = this.queue.front();
        this.queue.dequeue();

        // 2. Die Nachbarn dieses Knotens im Graphen abfragen
        List<Vertex> nachbarn = this.meinGraph.getNeighbours(aktuellerKnoten);

        // 3. Alle unbesuchten Nachbarn markieren und in die Schlange stellen
        nachbarn.toFirst();
        while (nachbarn.hasAccess()) {
            Vertex nachbar = nachbarn.getContent();

            // Wenn der Nachbar noch nicht entdeckt wurde
            if (!nachbar.isMarked()) {
                nachbar.setMark(true);       // Als "entdeckt" markieren
                this.queue.enqueue(nachbar); // Hinten anstellen
            }
            nachbarn.next(); // Zum nächsten Nachbarn in der Liste gehen
        }

        // 4. Den untersuchten Knoten zurückgeben, damit Person 2 ihn einfärben kann
        return aktuellerKnoten;
    }


     // Eine kleine Hilfsmethode, falls Person 3 wissen muss,
     // ob der Timer noch weiterlaufen soll.

    public boolean isSucheAktiv() {
        return this.sucheAktiv && !this.queue.isEmpty();
    }

    public int ermittleBekanntschaftsgrad(Graph pGraph, Vertex startKnoten, Vertex zielKnoten) {
        if (startKnoten == null || zielKnoten == null) return -1;

        // Sind es dieselben Personen? Dann Distanz 0
        if (startKnoten.getID().equals(zielKnoten.getID())) return 0;

        // 1. Graphen für die Suche vorbereiten (alle Markierungen löschen)
        pGraph.setAllVertexMarks(false);

        // 2. Queues vorbereiten
        Queue<Vertex> knotenQueue = new Queue<>();
        Queue<Integer> distanzQueue = new Queue<>(); // Speichert die jeweilige Distanz

        // 3. Startknoten einfügen
        knotenQueue.enqueue(startKnoten);
        distanzQueue.enqueue(0); // Der Startknoten hat Distanz 0 zu sich selbst
        startKnoten.setMark(true);

        // 4. Die Welle breitet sich aus...
        while (!knotenQueue.isEmpty()) {
            Vertex aktuell = knotenQueue.front();
            int aktuelleDistanz = distanzQueue.front();
            knotenQueue.dequeue();
            distanzQueue.dequeue();

            // Haben wir das Ziel gefunden?
            if (aktuell.getID().equals(zielKnoten.getID())) {
                return aktuelleDistanz;
            }

            // Wenn nicht: Alle unbesuchten Nachbarn in die Queue packen
            List<Vertex> nachbarn = pGraph.getNeighbours(aktuell);
            nachbarn.toFirst();
            while (nachbarn.hasAccess()) {
                Vertex nachbar = nachbarn.getContent();
                if (!nachbar.isMarked()) {
                    nachbar.setMark(true);
                    knotenQueue.enqueue(nachbar);
                    // Der Nachbar ist eine Ecke weiter weg als der aktuelle Knoten
                    distanzQueue.enqueue(aktuelleDistanz + 1);
                }
                nachbarn.next();
            }
        }

        // Wenn die Queue leer ist und das Ziel nicht gefunden wurde:
        return -1;
    }
}