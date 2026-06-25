package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.BinarySearchTree;
import KAGO_framework.model.abitur.datenstrukturen.List;


public class BaumSucher {


    public List<NutzerProfil> sucheNachHobby(BinarySearchTree<NutzerProfil> pTree, String psuch) {
        List<NutzerProfil> ergebnisListe = new List<NutzerProfil>();
        durchsucheInorder(pTree, psuch, ergebnisListe);
        return ergebnisListe;
    }


    private void durchsucheInorder(BinarySearchTree<NutzerProfil> pTree, String psuch, List<NutzerProfil> pListe) {

        if (pTree == null || pTree.isEmpty()) {
            return;
        }

        if (!pTree.isEmpty()) {

            durchsucheInorder(pTree.getLeftTree(), psuch, pListe);

            NutzerProfil aktuellesProfil = pTree.getContent();

            if (!aktuellesProfil.isPrivate() && aktuellesProfil.getHobby().equalsIgnoreCase(psuch)) {
                pListe.append(aktuellesProfil); // Treffer zur Liste hinzufügen
            }
        }


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







