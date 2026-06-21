package my_project.control;

import KAGO_framework.model.abitur.datenstrukturen.ComparableContent;

public class NutzerProfil implements ComparableContent<NutzerProfil> {

    private String nutzername;
    private String hobby;
    private boolean isPrivate;

    public NutzerProfil(String nutzername, String hobby, boolean isPrivate) {
        this.nutzername = nutzername;
        this.hobby = hobby;
        this.isPrivate = isPrivate;
    }

    // Getter und Setter
    public String getNutzername() { return nutzername; }
    public String getHobby() { return hobby; }
    public boolean isPrivate() { return isPrivate; }

    // --- Ab hier: Umsetzung des Interfaces ComparableContent ---

    @Override
    public boolean isGreater(NutzerProfil pContent) {
        if (pContent == null) return false;
        // Alphabetischer Vergleich der Nutzernamen
        return this.nutzername.compareTo(pContent.getNutzername()) > 0;
    }

    @Override
    public boolean isLess(NutzerProfil pContent) {
        if (pContent == null) return false;
        return this.nutzername.compareTo(pContent.getNutzername()) < 0;
    }

    @Override
    public boolean isEqual(NutzerProfil pContent) {
        if (pContent == null) return false;
        return this.nutzername.equals(pContent.getNutzername());
    }
}
