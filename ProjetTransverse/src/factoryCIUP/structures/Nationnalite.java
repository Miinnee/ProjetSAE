package factoryCIUP.structures;

public enum Nationnalite {
    NEUTRE(""),

    FRANCAIS("france"),
    BELGE("belge"),
    ALLEMAND("allemand"),
    SUISSE("suisse"),
    AMERICAINE("ameri"),
    JAPONAIS("japon"),;

    String drapeau;

    Nationnalite(String drapeau) {
        this.drapeau = drapeau;
    }

    public String getDrapeau() {
        return drapeau;
    }
}


