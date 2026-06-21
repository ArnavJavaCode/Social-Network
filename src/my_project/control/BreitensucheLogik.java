package my_project.control;

public class BreitensucheLogik {

    /*
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

     */
}
