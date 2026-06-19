package my_project.control;

public class BaumSucher {

    /**
     * Startet die Suche und gibt eine Liste mit allen Treffern zurück.
     * Das ist die Methode, die der Controller (Person 3) später aufruft.
     */
    public List<NutzerProfil> sucheNachHobby(BinarySearchTree<NutzerProfil> pTree, String pHobby) {
        List<NutzerProfil> ergebnisListe = new List<NutzerProfil>();
        durchsucheInorder(pTree, pHobby, ergebnisListe);
        return ergebnisListe;
    }

    /**
     * Die rekursive Hilfsmethode, die den eigentlichen Baum durchläuft.
     * Traversierung: Inorder (Links - Wurzel - Rechts)
     */
    private void durchsucheInorder(BinarySearchTree<NutzerProfil> pTree, String pHobby, List<NutzerProfil> pListe) {
        // Abbruchbedingung: Wenn der Baum leer ist, passiert nichts.
        if (!pTree.isEmpty()) {

            // 1. Zuerst den kompletten linken Teilbaum durchsuchen
            durchsucheInorder(pTree.getLeftTree(), pHobby, pListe);

            // 2. Die aktuelle Wurzel prüfen
            NutzerProfil aktuellesProfil = pTree.getContent();

            // Check: Ist das Profil öffentlich UND stimmt das Hobby überein?
            // (equalsIgnoreCase ignoriert Groß-/Kleinschreibung)
            if (!aktuellesProfil.isPrivate() && aktuellesProfil.getHobby().equalsIgnoreCase(pHobby)) {
                pListe.append(aktuellesProfil); // Treffer zur Liste hinzufügen
            }

            // 3. Danach den kompletten rechten Teilbaum durchsuchen
            durchsucheInorder(pTree.getRightTree(), pHobby, pListe);
        }
    }
}

