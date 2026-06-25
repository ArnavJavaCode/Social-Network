package my_project.control;

import KAGO_framework.model.abitur.datenstrukturen.BinarySearchTree;
import KAGO_framework.model.abitur.datenstrukturen.List;


public class BaumSucher {


    // Startet die Suche und gibt eine Liste mit allen Treffern zurück.
    // Das ist die Methode, die der Controller (Person 3) später aufruft.

    public List<NutzerProfil> sucheNachHobbyName(BinarySearchTree<NutzerProfil> pTree, String psuch) {
        List<NutzerProfil> ergebnisListe = new List<NutzerProfil>();
        durchsucheInorder(pTree, psuch, ergebnisListe);
        return ergebnisListe;
    }

    // Die rekursive Hilfsmethode, die den eigentlichen Baum durchläuft.
    // Traversierung: Inorder (Links - Wurzel - Rechts)

    private void durchsucheInorder(BinarySearchTree<NutzerProfil> pTree, String psuch, List<NutzerProfil> pListe) {

        if (pTree == null || pTree.isEmpty()) {
            return;
        }

        // Abbruchbedingung: Wenn der Baum leer ist, passiert nichts.
        if (!pTree.isEmpty()) {

            // 1. Zuerst den kompletten linken Teilbaum durchsuchen
            durchsucheInorder(pTree.getLeftTree(), psuch, pListe);

            // 2. Die aktuelle Wurzel prüfen
            NutzerProfil aktuellesProfil = pTree.getContent();

            // Check: Ist das Profil öffentlich UND stimmt das Hobby überein?
            // (equalsIgnoreCase ignoriert Groß-/Kleinschreibung)
            if (!aktuellesProfil.isPrivate() && aktuellesProfil.getHobby().equalsIgnoreCase(psuch)) {
                pListe.append(aktuellesProfil); // Treffer zur Liste hinzufügen
            }
        }


        // 3. Danach den kompletten rechten Teilbaum durchsuchen
        durchsucheInorder(pTree.getRightTree(), psuch, pListe);
    }

    public NutzerProfil findeProfilImmer(BinarySearchTree<NutzerProfil> pTree, String pName) {
        if (pTree == null || pTree.isEmpty()) {
            return null;
        }

        NutzerProfil aktuelles = pTree.getContent();

        if (aktuelles.getNutzername().equalsIgnoreCase(pName)) {
            return aktuelles;
        }

        if (pName.compareToIgnoreCase(aktuelles.getNutzername()) < 0) {
            return findeProfilImmer(pTree.getLeftTree(), pName);
        } else {
            return findeProfilImmer(pTree.getRightTree(), pName);
        }
    }
}







