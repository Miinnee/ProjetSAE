package factoryCIUP.personnes;

import factoryCIUP.structures.structuresTypes.Maison;

public class Etudiant extends Personne{
    Formation formation;
    String nom;
    String prenom;
    String anneeEtude;

    Maison maison;


    public Etudiant(String nom, String prenom, Formation formation, String anneeEtude) {
        super(nom, prenom, anneeEtude);
        this.formation = formation;

        this.nom = nom;
        this.prenom = prenom;
        this.anneeEtude = anneeEtude;
        this.maison = null;
    }

    public Maison getMaison() {
        return maison;
    }

    public void setMaison(Maison maison) {
        this.maison = maison;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
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

    public String getAnneeEtude() {
        return anneeEtude;
    }

    public void setAnneeEtude(String anneeEtude) {
        this.anneeEtude = anneeEtude;
    }
}
