package factoryCIUP.personnes;

public class Directeur extends Personne{

    String nom;
    String prenom;

    public Directeur(String nom, String prenom) {
        super(nom, prenom);

        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
